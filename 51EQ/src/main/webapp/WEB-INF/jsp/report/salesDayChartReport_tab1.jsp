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
    	<script>
    	$(function() {
    		$('#optDate').datepicker({
                format : 'yyyy-mm-dd'
            });
    		
    		$("#listForm").validate({
                rules : {
                	optDate : {
                		required : true,
                		date : true                    		
                    }
                }
            });
    		
    		$("#searchBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

                $("#listForm").attr("action", "${sc_ctx}/salesDayChartReport/search_tab1");
                $("#listForm").submit();
            });
    	});
    	</script>
    </head>
    <body>
    	<form method="post"	class="form-inline" id="listForm">
    		<fmt:parseDate value="${optDate}" var="_optDate" pattern="yyyyMMdd" />
    		<label class="control-label">销售日期 :</label>
    		<input id="optDate" name="optDate" type="text" class="input-medium" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${_optDate}" />"/>
    		<button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
    	</form>
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
					<td class="right" <c:if test="${salesDayTotal.posAmtIncrease > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtIncrease < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtIncrease}</td>
					<td class="right" <c:if test="${salesDayTotal.posAmtRate > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtRate < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtRate}%</td>
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
    </body>
</html>