<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="em_ctx">
    ${ctx}/em
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<script type="text/javascript">
    	$().ready(function() {
    		//-----------------------------------
            // 表单效验
            //-----------------------------------
            $("#listForm1").validate({
                rules : {
                    delBtn1 : {
                        requiredSelect : 'uuid'
                    }
                }
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
            $("#delBtn1").click(function() {
                if ($("#listForm1").valid()) {
                    $('#__del_confirm').modal({
                        backdrop : true,
                        keyboard : true,
                        show : true
                    });
                }
            });
    	});
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
            
            $("#listForm1").attr("target", "_self");
            $("#listForm1").attr("action", "${sc_ctx}/orgContract/del");
            $("#listForm1").submit();
        }
    	</script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        
        <div class="container">
           	<div class="row">
	      		<div class="span12">
	          		<legend>
	            		<h3>机构合同/租金缴交管理</h3>
	          		</legend>
	      		</div>
	      		
	      		<form:form method="POST" class="form-horizontal" id="listForm1"	modelAttribute="role">
	      			<input type="hidden" name="uuids" id="uuids"/>
	      			<div class="span6">
						<h4>机构合同</h4>
						
						<a href="${sc_ctx}/orgContract/new"	class="btn btn-primary">新增</a>
                        <input id="delBtn1" name="delBtn1" type="button" class="btn btn-danger" value="删除"/>		
                        				
						<table class="table	table-striped table-bordered table-condensed mytable" style="margin-top: 5px;">
                        	<thead>
                                <tr>
                                	<th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th class="center">
                                        机构名称
                                    </th>
                                    <th class="center">
                                        合同期限
                                    </th>
                                    <th class="center">
                                        缴交频率
                                    </th>
                                    <th class="center">
                                        缴交金额
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            	<c:if test="${empty	orgContractList}" >
                                    <tfoot>
                                        <tr>
                                            <td	colspan="6" class="rounded-foot-left">
                                                无记录信息
                                            </td>
                                        </tr>
                                    </tfoot>
                                </c:if>
                            	<c:forEach items="${orgContractList}" var="orgContract">
                            	<tr>
                            		<td class="center">
                            			<input type="checkbox" name="uuid" value="${orgContract.uuid}"></input>
                            		</td>
                            		<td class="center">
                            		<c:if test="${fn:length(orgContract.orgId) > 4}">
		             					${fn:substring(orgContract.orgId,3,6)}
		             				</c:if>
		             				<c:if test="${fn:length(orgContract.orgId) < 4}">
		             					总部
		             				</c:if>
                            		</td>
                            		<fmt:parseDate value="${orgContract.startDate}" var="_startDate" pattern="yyyyMMdd" />
                            		<fmt:parseDate value="${orgContract.endDate}" var="_endDate" pattern="yyyyMMdd" />
                            		<td class="center"><fmt:formatDate pattern="yyyy-MM-dd" value="${_startDate}" /> ~ <fmt:formatDate pattern="yyyy-MM-dd" value="${_endDate}" /></td>
                            		<td class="center">
										<c:if test="${orgContract.payFrequent == 1}">
                                        	季度
                                   		</c:if>
                                      	<c:if test="${orgContract.payFrequent == 2}">
                                        	月份
                                     	</c:if>
									</td>
                            		<td class="right">${orgContract.computePayAmt}</td>
                            		<td>
                            		<a href="${sc_ctx}/orgContract/edit/${orgContract.uuid}" class="btn"/>编辑</a>
                            		<br>
                            		<a href="${sc_ctx}/orgContract/edit/${orgContract.uuid}" style="margin-top: 5px;" class="btn"/>明细</a>
                            		</td>
                            	</tr>
                            	</c:forEach>
                            </tbody>
                        </table>
					</div>
				</form:form>
				
				
					<div class="span6">
						<h4>租金缴交</h4>
						
						<a href="${sc_ctx}/orgContract/new"	class="btn btn-primary">新增</a>
                        <input id="delBtn2" name="delBtn2" type="button" class="btn btn-danger" value="删除"/>		
                        				
						<table class="table	table-striped table-bordered table-condensed mytable" style="margin-top: 5px;">
                        	<thead>
                                <tr>
                                	<th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th class="center">
                                        机构名称
                                    </th>
                                    <th class="center">
                                        缴费时间
                                    </th>
                                    <th class="center">
                                        缴交金额
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                        </table>
					</div>
	      		
	      		
      		</div>
       	</div>
    </body>
</html>