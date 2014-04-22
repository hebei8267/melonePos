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
					$("#listForm").attr('target', '_self').submit();
					$("#listForm").submit();
				});
				
				$("#exportBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});
		
					$("#listForm").attr("action", "${sc_ctx}/pettyCash/analytics_export");
					$("#listForm").attr('target', '_self').submit();
					$("#listForm").submit();
				});
			});
			
			function detail_month(orgId, optDate) {
				$("#optDate_detail_hidden").val(optDate);
				detail(orgId);
			}
			function detail(orgId) {
				$("input[type='text'],textarea").each(function(i) {
					this.value = $.trim(this.value);
				});
		
				$("#orgId_detail_hidden").val(orgId);
				$("#listForm").attr("action", "${sc_ctx}/pettyCash/analytics_detail");
				$("#listForm").attr('target', '_blank').submit();
			}
		</script>
	</head>
	<body>
		<% // 系统菜单 %>
		<page:applyDecorator name="menu" />
		
		<div class="container">
			<form method="post" class="form-inline" id="listForm">
				<input type="hidden" name="orgId_detail_hidden" id="orgId_detail_hidden" >
				<input type="hidden" name="optDate_detail_hidden" id="optDate_detail_hidden" >
				<div class="row">
					<div class="span12">
						<legend>
							<h3>门店备用金分析</h3>
						</legend>
					</div>
					<div class="span12">
			    		<label class="control-label">统计方式 :</label>
			       		<c:set var = "month" value="month"/>
			          	<c:if test="${analyzeMode.equals(month)}" >
				    		<input type="radio" name="analyzeMode" value="month" checked="checked">
							<span style="background-color: #5bc0de; padding: 5px">月次</span>
							<input type="radio" name="analyzeMode" value="total">
							<span style="background-color: #62c462; padding: 5px">合计</span>
			        	</c:if>
						<c:if test="${!analyzeMode.equals(month)}" >
				        	<input type="radio" name="analyzeMode" value="month">
							<span style="background-color: #5bc0de; padding: 5px">月次</span>
							<input type="radio" name="analyzeMode" value="total" checked="checked">
							<span style="background-color: #62c462; padding: 5px">合计</span>
			          	</c:if>
			    	</div>
			                    
					<div class="span4" style="margin-top: 20px;">
						<label class="control-label">业务日期 :</label>
						<input id="optDateShow_start" name="optDateShow_start" type="text" class="input-small" value="${optDateShow_start }"/>
                        ～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-small" value="${optDateShow_end }"/>
					</div>
					<div class="span8" style="margin-top: 20px;">
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
						</select>&nbsp;&nbsp;
						<button id="searchBtn" class="btn btn-primary" type="button">查询</button>
					</div>
					
					<div class="span12"	style="margin-top: 10px;">
						<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                	<th class="center">
                                        机构
                                    </th>
                                    <th class="center" width="190">
                                        业务日期
                                    </th>
                                    <th class="center">
                                        支出金额(备用金)
                                    </th>
                                    <c:if test="${analyzeMode.equals(month)}" >
                                    <th class="center">
                                        当月销售额
                                    </th>
                                    </c:if>
									<c:if test="${!analyzeMode.equals(month)}" >
									<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
										合计销售额
									</th>
									</c:if>
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
                            		<c:if test="${analyzeMode.equals(month)}" >
                            		<td class="center">${pettyCash.optDate}</td>
                            		</c:if>
									<c:if test="${!analyzeMode.equals(month)}" >
									<td class="center">${optDateShow_start } 至 ${optDateShow_end }</td>
									</c:if>
                            		<td class="right">${pettyCash.optAmtShow}</td>
                            		<td class="right">${pettyCash.totalSaleRamt}</td>
                            		<td class="right">${pettyCash.rate} %</td>
                            		<td>
                            		<c:if test="${analyzeMode.equals(month)}" ><%//月份 %>
                            		<a href="#" onclick="detail_month('${pettyCash.orgId}' , '${pettyCash.optDate}');" class="btn btn-warning">详细</a>
                            		</c:if>
									<c:if test="${!analyzeMode.equals(month)}" ><%//合计 %>
									<a href="#" onclick="detail('${pettyCash.orgId}');" class="btn btn-warning">详细</a>
									</c:if>
                            		</td>
                            	</tr>
                            </c:forEach>
                            <c:if test="${!empty pettyCashList}" >
                               <tr>
                            		<td class="center" colspan="2">合计</td>
                            		<td class="right">${totalPettyCash.optAmtShow}</td>
                            		<td class="right">${totalPettyCash.totalSaleRamt}</td>
                            		<td class="right">${totalPettyCash.rate} %</td>
                            		<td></td>
                            	</tr>
                            </c:if>
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