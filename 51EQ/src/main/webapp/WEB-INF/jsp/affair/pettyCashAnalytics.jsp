<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
	<head>
		<script>
			$(function() {
				$("#listForm").validate({
					rules : {
						optDateShow_start : {
							required : true
						},
						optDateShow_end : {
							required : true
						}
					}
				});
				
				$('#optDateShow_start').datepicker({
                    format : 'yyyy-mm',
	            	viewMode : 1,
	            	minViewMode : 1
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm',
	            	viewMode : 1,
	            	minViewMode : 1
                });
                
				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});
		
					$("#listForm").attr("action", "${sc_ctx}/pettyCash/analytics");
					$("#listForm").submit();
				});
				
				$("#exportBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});
		
					$("#listForm").attr("action", "${sc_ctx}/pettyCash/analytics_export");
					$("#listForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<% // 系统菜单 %>
		<page:applyDecorator name="menu" />
		
		<div class="container">
			<form method="post" class="form-inline" id="listForm">
				<div class="row">
					<div class="span12">
						<legend>
							<h3>门店备用金分析(图形)</h3>
						</legend>
					</div>
					<div class="span4">
						<label class="control-label">业务日期 :</label>
						<input id="optDateShow_start" name="optDateShow_start" type="text" class="input-small" value="${optDateShow_start }"/>
                        ～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-small" value="${optDateShow_end }"/>
					</div>
					<div class="span8">
						<label class="control-label">机构 :</label>
						<select id="orgId" name="orgId" class="input-medium">
							<c:forEach items="${orgList}" var="org">
								<c:if test="${org.key == orgId}">
									<option value="${org.key }" selected>${org.value }</option>
								</c:if>
								<c:if test="${org.key != orgId}">
									<option value="${org.key }">${org.value }</option>
								</c:if>
							</c:forEach>
						</select>
						<button id="searchBtn" class="btn btn-primary" type="button">查询</button>
					</div>
					
					<div class="span12"	style="margin-top: 10px;">
						<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                	<th class="center">
                                        机构
                                    </th>
                                    <th class="center">
                                        业务日期
                                    </th>
                                    <th class="center">
                                        支出金额(备用金)
                                    </th>
                                    <th class="center">
                                        当月销售额
                                    </th>
                                    <th class="center">
                                        支出占用比(备用金)
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pettyCashList}" var="pettyCash" varStatus="status1">
                            	<tr>
                            		<td class="center">${fn:substring(pettyCash.orgId,3,6)}</td>
                            		<td class="center">${pettyCash.optDate}</td>
                            		<td class="right">${pettyCash.optAmtShow}</td>
                            		<td class="right">${pettyCash.totalSaleRamt}</td>
                            		<td class="right">${pettyCash.rate} %</td>
                            		<td></td>
                            	</tr>
                            </c:forEach>
                            </tbody>
                            <c:if test="${empty	pettyCashList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="6"	class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                    	</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>