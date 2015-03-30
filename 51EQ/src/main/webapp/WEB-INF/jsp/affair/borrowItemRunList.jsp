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
    	</style>
    	<script>
            $().ready(function() {
                
                //-----------------------------------
                // 全选/全部选
                //-----------------------------------
                $("#checkAll").click(function() {
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='uuid']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
						} else {
							$(this).attr("checked", false);
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
                $("#listForm").attr("action", "${sc_ctx}/borrowItemRun/del/${borrowItem.itemNo}");
                $("#listForm").submit();
            }
        </script>
	</head>
	<body>
		<div class="container">
			<form method="post"	class="form-inline">
	            <div class="row">
	            	<div class="span12">
	                    <legend>
	                    	<h3>借还物件信息</h3>
	                    </legend>
	                </div>
	                <div class="span4">
	                    <label class="control-label">物件编号 :</label> <spam class="_warn1">${borrowItem.itemNo}</spam>
	                </div>
	                <div class="span4">
	                    <label class="control-label">物件名称 :</label> <spam class="_warn1">${borrowItem.itemName}</spam>
	                </div>
	                <div class="span4">
	                    <label class="control-label">物件类型 :</label> 
	                    <c:if test="${borrowItem.itemType == '01'}" >
                 		<spam class="_warn1">证书</spam>
                       	</c:if>
                      	<c:if test="${borrowItem.itemType == '02'}" >
                        <spam class="_warn1">执照</spam>
                      	</c:if>
                     	<c:if test="${borrowItem.itemType == '03'}" >
                      	<spam class="_warn1">公章</spam>
                     	</c:if>
                     	<c:if test="${borrowItem.itemType == '04'}" >
                      	<spam class="_warn1">押金收据</spam>
                      	</c:if>
                     	<c:if test="${borrowItem.itemType == '99'}" >
                     	<spam class="_warn1">其他</spam>
                    	</c:if>
	                </div>
	            </div><br>
	            <div class="row">
	                <div class="span4">
	                    <label class="control-label">所属机构 :</label> 
						<c:if test="${fn:length(borrowItem.orgId) > 4}">
		             	<spam class="_warn1">${fn:substring(borrowItem.orgId,3,6)}</spam>
		             	</c:if>
		            	<c:if test="${fn:length(borrowItem.orgId) < 4}">
		             	<spam class="_warn1">总部</spam>
		             	</c:if>
	                </div>
	                <div class="span4">
	                    <label class="control-label">接收时间 :</label> 
	                    <fmt:parseDate value="${borrowItem.optDate}" var="_optDate" pattern="yyyyMMdd" />
                     	<spam class="_warn1"><fmt:formatDate pattern="yyyy-MM-dd" value="${_optDate}" /></spam>
	                </div>
	                <div class="span4">
	                    <label class="control-label">物件状态 :</label> 
	                    <c:if test="${borrowItem.status == '1'}" >
                     	<spam class="_warn1">在库</spam>
                     	</c:if>
                     	<c:if test="${borrowItem.status == '0'}" >
                   		<spam class="_warn2">借出</spam>
                     	</c:if>
	                </div>
	            </div>
            </form>
            
            <div class="row">
            	<form method="post" class="form-inline" id="listForm">
	            	<div class="span12">
	                    <legend>
	                    	<h3>借还明细信息</h3>
	                    </legend>
	                </div>
	                <div class="span12" style="padding-top: 10px;">
	                	<c:if test="${borrowItem.status == '1'}" >
                        <a href="${sc_ctx}/borrowItemRun/lend/${borrowItem.itemNo}"	class="btn btn-primary">借出</a>
                        </c:if>
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
	                              		借用人
	                               	</th>
	                               	<th class="center">
	                              		借用日期
	                               	</th>
	                               	<th class="center">
	                              		预计归还日期
	                               	</th>
	                               	<th class="center">
	                              		归还人
	                               	</th>
	                               	<th class="center">
	                              		实际归还日期
	                               	</th>
		                            <th	width="114">
	                              		&nbsp;
	                              	</th>
	                          	</tr>
	                      	</thead>
	                      	<tbody>
	                       		<c:forEach items="${borrowItemRunList}" var="borrowItemRun">
	                          	<tr>
	                            	<td class="center">
	                            		<input type="checkbox" name="uuid" value="${borrowItemRun.uuid}">
	                            	</td>
	                            	<td class="center">${borrowItemRun.borrower}</td>
	                            	<td class="center">${borrowItemRun.borrowDate}</td>
	                            	<td class="center">${borrowItemRun.expReturnDate}</td>
	                            	<td class="center">${borrowItemRun.returnPeople}</td>
	                            	<td class="center">${borrowItemRun.actReturnDate}</td>
	                            	<td class="center">
	                            		<c:if test="${empty borrowItemRun.returnAttn}">
		                                <a href="${sc_ctx}/borrowItemRun/return/${borrowItem.itemNo}/${borrowItemRun.uuid}" class="btn btn-warning"/>还入</a>
		                                </c:if>
	                            	</td>                         	
	                         	</tr>
	                            </c:forEach>
	                        </tbody>
	                   		<c:if test="${empty	borrowItemRunList}" >
	                      	<tfoot>
	                      		<tr>
	                          		<td	colspan="7" class="rounded-foot-left">
	                            	无记录信息
	                            	</td>
	                        	</tr>
	                      	</tfoot>
	                   		</c:if>
	            		</table>
	          		</div>
            	</form>
            </div>
        </div>
	</body>
</html>