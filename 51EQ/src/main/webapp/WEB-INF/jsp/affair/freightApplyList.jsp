<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<%@	page import="com.tjhx.globals.Constants"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="_ROOT_ORG_ID">
    <%=Constants.ROOT_ORG_ID %>
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<style type="text/css">
    	._warn1 {
    		padding: 5px;
			background-color: #99FF33;
		}
		._warn2 {
			padding: 5px;
			background-color: #FFCC33;
		}
		._warn3 {
			padding: 5px;
			background-color: #BDBDBD;
		}
    	</style>
    	<script>
            $().ready(function() {
                
                $("#searchForm").validate({
                    rules : {
                        status : {
                            required : true
                        }
                    }
                });
              	//-----------------------------------
                // 表单效验
                //-----------------------------------
                $("#listForm").validate({
                    rules : {
                        delBtn : {
                            requiredSelect : 'uuid'
                        }
                    }
                });
                
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#searchForm").attr("target", "_self");
					<c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
                    $("#searchForm").attr("action", "${sc_ctx}/freight/managerSearch");
                    </c:if>
                    <c:if test="${sessionScope.__SESSION_USER_INFO.orgId != _ROOT_ORG_ID}">
                    $("#searchForm").attr("action", "${sc_ctx}/freight/search");
                    </c:if>
                    $("#searchForm").submit();
                });
                
                //-----------------------------------
                // 全选/全部选
                //-----------------------------------
                $("#checkAll").click(function() {
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='uuid']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
                });

                //-----------------------------------
                // 删除按钮点击
                //-----------------------------------
                $("#delBtn").click(function() {
                    if ($("#listForm").valid()) {
                        $('#__del_confirm').modal({
                            backdrop : true,
                            keyboard : true,
                            show : true
                        });
                    }
                });
            });
            
            var _orgActReceiptUrl = null;
            // 收货
            function orgActReceipt(uuid){
            	$('#__orgActReceipt_confirm').modal({
                  	backdrop : true,
                   	keyboard : true,
                   	show : true
             	});
             	_orgActReceiptUrl = "${sc_ctx}/freight/orgActReceipt/" + uuid;
            }
            function _orgActReceipt_confirm() {
            	$("#hideForm").attr("target", "_self");
                $("#hideForm").attr("action", _orgActReceiptUrl);
                $("#hideForm").submit();
            }
            
            var _orgActDeliveryUrl = null;
            // 发货
            function orgActDelivery(uuid){
            	$('#__orgActDelivery_confirm').modal({
                    backdrop : true,
                   	keyboard : true,
                    show : true
            	});
            	_orgActDeliveryUrl = "${sc_ctx}/freight/orgActDelivery/" + uuid;
            }
            function _orgActDelivery_confirm() {
            	$("#hideForm").attr("target", "_self");
                $("#hideForm").attr("action", _orgActDeliveryUrl);
                $("#hideForm").submit();
            }
            
          	//-----------------------------------
            // 删除
            //-----------------------------------
            function _del_confirm() {
                var $subCheckBox = $("input[name='uuid']");
                var uuids = "";
                $.each($subCheckBox, function(index, _checkBox) {
                    if (_checkBox.checked) {
                        uuids += _checkBox.value + ",";
                    }
                });
                if (uuids.length > 0) {
                    uuids = uuids.substring(0, uuids.length - 1);
                }

                $("#uuids").val(uuids);
                
                $("#listForm").attr("target", "_self");
                $("#listForm").attr("action", "${sc_ctx}/freight/del");
                $("#listForm").submit();
            }
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
      	<div class="container">
            <div class="row">
                <form method="post"	class="form-inline"	id="searchForm">
                	<div class="span12">
                        <legend>
                            <h3>商品调货单</h3>
                        </legend>
                    </div>
                    
                    <c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
                    <div class="span3">
                        <label class="control-label">调出机构 :</label>
                        <select name="appOrgId" class="input-medium">
                            <c:forEach items="${orgList}" var="_appOrgId">
                                <c:if test="${_appOrgId.key == appOrgId}">
                                    <option value="${_appOrgId.key }" selected>${_appOrgId.value }</option>
                                </c:if>
                                <c:if test="${_appOrgId.key != appOrgId}">
                                    <option value="${_appOrgId.key }">${_appOrgId.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        &nbsp;&nbsp;
                    </div>
                  	</c:if>
                  	<c:if test="${sessionScope.__SESSION_USER_INFO.orgId != _ROOT_ORG_ID}">
		             		<input id="appOrgId" name="appOrgId" type="hidden" value="${sessionScope.__SESSION_USER_INFO.orgId}"/>
		           	</c:if>
          
          
                 	<c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
                 	<div class="span3">
                        <label class="control-label">调入机构 :</label>
                        <select name="targetOrgId" class="input-medium">
                            <c:forEach items="${orgList}" var="_targetOrgId">
                                <c:if test="${_targetOrgId.key == targetOrgId}">
                                    <option value="${_targetOrgId.key }" selected>${_targetOrgId.value }</option>
                                </c:if>
                                <c:if test="${_targetOrgId.key != targetOrgId}">
                                    <option value="${_targetOrgId.key }">${_targetOrgId.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        &nbsp;&nbsp;
                    </div>
                  	</c:if>
                  	<c:if test="${sessionScope.__SESSION_USER_INFO.orgId != _ROOT_ORG_ID}">
		           		<input id="targetOrgId" name="targetOrgId" type="hidden" value="${sessionScope.__SESSION_USER_INFO.orgId}"/>
		          	</c:if>
		             	
                    
                    <div class="span6">
                        <label class="control-label">调货单状态 :</label>
                        <select name="status" class="input-medium">
                            <c:forEach items="${statusList}" var="_status">
                                <c:if test="${_status.key == status}">
                                    <option value="${_status.key }" selected>${_status.value }</option>
                                </c:if>
                                <c:if test="${_status.key != status}">
                                    <option value="${_status.key }">${_status.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>&nbsp;&nbsp;
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                </form>
                
                <form method="post" class="form-inline" id="listForm">
                	<div class="span12" style="padding-top: 10px;">
                        <a href="${sc_ctx}/freight/new"	class="btn btn-primary">申请</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                        	<thead>
                                <tr>
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th class="center">
                                        申请<br>日期
                                    </th>
                                    <th class="center">
                                        申请人
                                    </th>
                                    <th class="center">
                                        调出<br>机构
                                    </th>
                                    <th class="center">
                                        调入<br>机构
                                    </th>
                                    <th class="center">
                                        调货<br>类别
                                    </th>
                                    <th class="center">
                                        审批人
                                    </th>
                                    <th class="center">
                                        货运<br>状态
                                    </th>
                                    <th class="center">
                                        是否<br>打包
                                    </th>
                                    <th class="center">
                                        预计<br>收货时间
                                    </th>
                                    <th class="center">
                                        实际<br>收货时间
                                    </th>
                                    <th class="center">
                                        门店<br>发货时间
                                    </th>
                                    <th class="center">
                                        预计<br>送货时间
                                    </th>
                                    <th class="center">
                                        实际<br>送货时间
                                    </th>
                                    <th class="center">
                                        门店<br>收货时间
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${freightAppList}" var="freightApp">
                            		<tr>
                            			<td class="center">
                            			<%//门店 -仅能删除申请状态的货运单%>
                            			<c:if test="${sessionScope.__SESSION_USER_INFO.orgId != _ROOT_ORG_ID}">
                            			<c:if test="${freightApp.status == '00'}">
                            				<input type="checkbox" name="uuid" value="${freightApp.uuid}">
                            			</c:if>
                            			</c:if>
                            			<%//总部 %>
                            			<c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
                            				<input type="checkbox" name="uuid" value="${freightApp.uuid}">
                            			</c:if>
                            			</td>
                            			<td class="center">
                            				${freightApp.appDate}
                            			</td>
                            			<td class="center">
                            				${freightApp.applicant}
                            			</td>
                            			<td class="center">
                            				<c:if test="${fn:length(freightApp.appOrgId) > 4}">
					             			${fn:substring(freightApp.appOrgId,3,6)}
					             			</c:if>
					             			<c:if test="${fn:length(freightApp.appOrgId) < 4}">
					             			总部
					             			</c:if>
                            			</td>
                            			<td class="center">
                            				<c:if test="${fn:length(freightApp.targetOrgId) > 4}">
					             			${fn:substring(freightApp.targetOrgId,3,6)}
					             			</c:if>
					             			<c:if test="${fn:length(freightApp.targetOrgId) < 4}">
					             			总部
					             			</c:if>
                            			</td>
                            			<td class="center">
                            				<c:if test="${freightApp.freightType == '1'}">
					                   		<span class="_warn1">普通</span>
					                      	</c:if>
					                		<c:if test="${freightApp.freightType == '2'}">
					                   		<span class="_warn2">限时</span><br><span class="_warn2">${freightApp.limitedDate}</span>
					                      	</c:if>
                            			</td>
                            			<td class="center">
                            				${freightApp.approver}
                            			</td>
                            			<td class="center">
                            				<c:if test="${freightApp.status == '00'}">
					                   		<span class="_warn2">申请</span>
					                      	</c:if>
					                		<c:if test="${freightApp.status == '01'}">
					                   		<span class="_warn1">已审批</span>
					                      	</c:if>
					                      	<c:if test="${freightApp.status == '02'}">
					                   		<span class="_warn3">已送达</span>
					                      	</c:if>
                            			</td>
                            			<td class="center">
                            				<c:if test="${freightApp.packFlg == '1'}">
					                   		<span class="_warn1">已打包</span>
					                      	</c:if>
					                		<c:if test="${freightApp.packFlg == '0'}">
					                   		<span class="_warn2">未打包</span>
					                      	</c:if>
                            			</td>
                            			<td class="center">
                            				${freightApp.expReceiptDate}
                            			</td>
                            			<td class="center">
                            				${freightApp.actReceiptDate}
                            			</td>
                            			<td class="center">
                            				<c:if test="${(freightApp.status == '01') && (sessionScope.__SESSION_USER_INFO.orgId == freightApp.appOrgId)}">
                            				<a href="#" onclick="javescript:orgActDelivery('${freightApp.uuid}');" class="btn" target="_self"/>发货</a>
                            				<br>
                            				</c:if>
                            				${freightApp.orgActDeliveryDate}
                            			</td>
                            			<td class="center">
                            				${freightApp.expDeliveryDate}
                            			</td>
                            			<td class="center">
                            				${freightApp.actDeliveryDate}
                            			</td>
                            			<td class="center">
                            				<c:if test="${(freightApp.status == '01') && (sessionScope.__SESSION_USER_INFO.orgId == freightApp.targetOrgId)}">
                            				<a href="#" onclick="javescript:orgActReceipt('${freightApp.uuid}');" class="btn" target="_self"/>收货</a>
                            				<br>
                            				</c:if>
                            				${freightApp.orgActReceiptDate}
                            			</td>
                                        <td>
                                        <c:if test="${sessionScope.__SESSION_USER_INFO.orgId != _ROOT_ORG_ID}">
                                        <c:if test="${freightApp.status != '02'}">
                                      		<a href="${sc_ctx}/freight/edit/${freightApp.uuid}" class="btn btn-warning" target="_self"/>编辑</a>
                                      	</c:if>
                                      	</c:if>
                                      	
                                      	<c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
                                      		<a href="${sc_ctx}/freight/edit/${freightApp.uuid}" class="btn btn-warning" target="_self"/>审批</a>
                                        </c:if>
                                        </td>
                                    </tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	freightAppList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="15" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
               		</div>
                </form>
                
                <form method="post" class="form-inline" id="hideForm">
                </form>
            </div>
       	</div>
       	
       	<div class="modal hide fade  __model37" id="__orgActDelivery_confirm">
		    <div class="modal-header">
		        <a class="close" data-dismiss="modal">×</a>
		        <h4>系统消息</h4>
		    </div>
		    <div class="modal-body">
		        <center>
		            <p class="error">
		                确定已发出该调货信息吗？
		            </p>
		        </center>
		    </div>
		    <div class="modal-footer">
		        <input type="button" class="btn btn-primary" id="__orgActDelivery_Btn" onclick="javescript:_orgActDelivery_confirm();" value="确定">
		        <a href="#" class="btn" data-dismiss="modal">关闭</a>
		    </div>
		</div>
		
		<div class="modal hide fade  __model37" id="__orgActReceipt_confirm">
		    <div class="modal-header">
		        <a class="close" data-dismiss="modal">×</a>
		        <h4>系统消息</h4>
		    </div>
		    <div class="modal-body">
		        <center>
		            <p class="error">
		                确定已收取该调货信息吗？
		            </p>
		        </center>
		    </div>
		    <div class="modal-footer">
		        <input type="button" class="btn btn-primary" id="__orgActReceipt_Btn" onclick="javescript:_orgActReceipt_confirm();" value="确定">
		        <a href="#" class="btn" data-dismiss="modal">关闭</a>
		    </div>
		</div>
    </body>
</html>