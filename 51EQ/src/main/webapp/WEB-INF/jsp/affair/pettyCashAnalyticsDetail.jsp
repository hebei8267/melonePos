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
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/dhtmlxchart.css">
        <script src="${ctx}/static/js/dhtmlxchart.js" type="text/javascript"></script>
	</head>
	<body>
		<% // 系统菜单 %>
		<page:applyDecorator name="menu" />
		
		<div class="container">
			<div class="row">
                <div class="span12">
                    <legend>
                        <h3>门店备用金分析(图形)</h3>
                    </legend>
                </div>
                
            	<div class="span12"	style="margin-top: 10px;">
					<table class="table	table-striped table-bordered table-condensed mytable">
                    	<thead>
                     		<tr>
                        		<th class="center">UID</th>
                            	<th class="center" width="90">星期</th>
                            	<th class="center">业务日期</th>
                              	<th class="center">支出类型</th>
								<th class="center">支出/拨入(事项)</th>
								<th class="center">支出金额</th>
                              	<th class="center">备用金余额</th>
                         	</tr>
                      	</thead>
                      	<tbody>
                            <c:forEach items="${pettyCashList}" var="pettyCash" varStatus="status1">
                       		<tr>
                          		<td class="center">${pettyCash.optUid}</td>
                          		<td class="center">${pettyCash.week}</td>
                            	<td class="center">${pettyCash.createDateStr}</td>
		                     	<td class="center">${pettyCash.expType}</td>
		                     	<td>${pettyCash.expReason}</td>
		                     	<td class="right">${pettyCash.optAmt}</td>
		                     	<td class="right">${pettyCash.balanceAmt}</td>
                          	</tr>
                            </c:forEach>
                            <c:if test="${!empty pettyCashList}" >
                          	<tr>
                         		<td class="center" colspan="5">合计</td>
                            	<td class="right">${totalPettyCash.totalSaleRamt}</td>
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
				
				<div class="span9" style="margin-top: 10px;">
	            	<div id="chart1" style="width:570px;height:400px;border:1px solid #A4BED4;"></div>
	            	<br/><br/>
	          	</div>
        	</div>
		</div>
		
		<script>
			$(function() {
				var _pettyCashJsonList = ${pettyCashJsonList};
				
				var pieChart = new dhtmlXChart({
					view : "pie",
					container : "chart1",
					value : "#optAmtShow#",
					labelOffset : -30,
					label : function(obj) {
						var sum = pieChart.sum("#optAmtShow#");
						var text = Math.round(parseFloat(obj.optAmtShow) / sum * 100) + "%";
						return "<div class='label' style='border:1px solid'>" + text + "</div>";
					},
					tooltip: "#optAmtShow#元    #expType#",
					legend: "#expType#"
				});
				pieChart.parse(_pettyCashJsonList, "json");
			});
		</script>
	</body>
</html>