<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<style type="text/css">
    	.cash_daily {
			border-bottom: 3px solid #F89406;
			margin-top: 40px;
			margin-bottom: 10px;
		}
    	</style>
    	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/dhtmlxchart.css">
    	<script src="${ctx}/static/js/dhtmlxchart.js" type="text/javascript"></script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>日销售信息对比</h3>
                        </legend>
                    </div>
                    
                
					<div class="span12">
						<ul id="myTab" class="nav nav-tabs">
			              	<li><a href="#_tab1" data-toggle="tab">同期增长额</a></li>
			              	<li><a href="#_tab2" data-toggle="tab">增长率折线图</a></li>
	            		</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade" id="_tab1">
								<fmt:parseDate value="${optDate}" var="_optDate" pattern="yyyyMMdd" />
								<h4>统计日期 : <fmt:formatDate pattern="yyyy/MM/dd" value="${_optDate}" /></h4>
			                	<table class="table	table-striped table-bordered table-condensed mytable">
			                		<thead>
			                			<tr>
				                			<th class="center">机构</th>
				                			<th class="center">当日销售</th>
				                			<th class="center">截止金额</th>
				                			<th class="center">截止天数</th>
				                			<th class="center">本月天数</th>
				                			<th class="center">预计本月销售</th>
				                			<th class="center">去年同期销售</th>
				                			<th class="center">增长额</th>
				                			<th class="center">增长率</th>
				                			<th class="center">日需销售金额</th>
				                			<th class="center">排名</th>
			                			</tr>			                			
			                		</thead>
			                		<tbody>
			                			<c:forEach items="${salesDayTotalList}" var="salesDayTotal">
			                			<tr>
			                				<td class="center">${salesDayTotal.orgId}</td>
			                				<td class="right">${salesDayTotal.posAmt}</td>
			                				<td class="right">${salesDayTotal.posAmtByNow}</td>
			                				<td class="center">${salesDayTotal.nowDays}</td>
			                				<td class="center">${salesDayTotal.monthDays}</td>
			                				<td class="right">${salesDayTotal.expMonthPosAmt}</td>
			                				<td class="right">${salesDayTotal.preYearMonthPosAmt}</td>
			                				<td class="right">${salesDayTotal.posAmtIncrease}</td>
			                				<td class="right">${salesDayTotal.posAmtRate}%</td>
			                				<td class="right">${salesDayTotal.dayNeededPosAmt}</td>
			                				<td class="center">${salesDayTotal.ranking}</td>
			                			</tr>
			                			</c:forEach>
			                		</tbody>
			                		<c:if test="${empty	salesDayTotalList}" >
		                                <tfoot>
		                                    <tr>
		                                        <td	colspan="11" class="rounded-foot-left">
		                                            无记录信息
		                                        </td>
		                                    </tr>
		                                </tfoot>
		                            </c:if>
			                	</table>
			              	</div>
							<div class="tab-pane fade" id="_tab2">
								<h4>统计月份 : <fmt:formatDate pattern="yyyy/MM" value="${_optDate}" /></h4>
								<table class="table	table-striped table-bordered table-condensed mytable">
									<thead>
										<tr>
										<th>日期/机构</th>
										<c:forEach items="${orgList}" var="org">
										<th>${org.name }</th>
										</c:forEach>
										</tr>
									</thead>
								</table>
	                		</div>
	            		</div>
            		</div>
                </div>
            </form>
        </div>


		<script>
			$(function() {
				$('#myTab a:first').tab('show');
			});
		</script>
    </body>
</html>