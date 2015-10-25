<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		.chartdiv {
			width		: 100%;
			height		: 1100px;
			font-size	: 11px;
			border:1px solid #A4BED4;
		}
    	</style>
    	<script src="http://www.amcharts.com/lib/3/amcharts.js"></script>
		<script src="http://www.amcharts.com/lib/3/serial.js"></script>
		<script src="http://www.amcharts.com/lib/3/themes/light.js"></script>
		<script>
            $(function() {             
                $("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/salesMonthItemChartReport/export");
                    $("#listForm").submit();
                });
            });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>月销售信息对比(图形)</h3>
                        </legend>
                    </div>
                    
                    <div class="span12">
                        <button	id="exportBtn" class="btn	btn-warning" type="button">数据导出</button>
                    </div>
                    
                    <c:forEach items="${orgSumSalesJsonList}" var="orgSumSalesJson" varStatus="status1">
                    	<div class="span12"><h4>${orgNameList.get(status1.index) } - 销售金额</h4></div>
                        <div class="span12"	style="margin-top: 10px;">
                            <div id="chart1${status1.index + 1}" class="chartdiv"></div>
                        </div>
                        <div class="span12 cash_daily"></div>
                    </c:forEach>
                </div>
            </form>
        </div>


		<script>
			$(function() {
				<c:forEach items="${orgSumSalesJsonList}" var="orgSumSalesJson" varStatus="status1">
				var orgSumSalesJson${status1.index + 1} = ${orgSumSalesJson}
				
				var chart${status1.index + 1} = AmCharts.makeChart("chart1${status1.index + 1}", {
					"type": "serial",
					"theme": "light",
					"categoryField": "optDateM",
					"rotate": true,
					"startDuration": 1,
					"categoryAxis": {
						"gridPosition": "start",
				        "axisAlpha": 0,
				        "gridAlpha": 0,
				        "position": "left"
					},
					"legend": {
				        "position": "top"
				    },
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "时间:[[optDateYM1]]  销售金额[[value]]元",
							"fillAlphas": 0.8,
							"labelText": "[[value]]元",
							"labelOffset": -120,
							"lineAlpha": 0.2,
							"title": "2015年",
							"type": "column",
							"valueField": "saleRamt1"
						},
						{
							"balloonText": "时间:[[optDateYM2]]  销售金额[[value]]元",
							"fillAlphas": 0.8,
							"labelText": "[[value]]元",
							"labelOffset": -120,
							"lineAlpha": 0.2,
							"title": "2014年",
							"type": "column",
							"valueField": "saleRamt2"
						},
						{
							"balloonText": "时间:[[optDateYM3]]  销售金额[[value]]元",
							"fillAlphas": 0.8,
							"labelText": "[[value]]元",
							"labelOffset": -120,
							"lineAlpha": 0.2,
							"title": "2013年",
							"type": "column",
							"valueField": "saleRamt3"
						},
						{
							"balloonText": "时间:[[optDateYM4]]  销售金额[[value]]元",
							"fillAlphas": 0.8,
							"labelText": "[[value]]元",
							"labelOffset": -120,
							"lineAlpha": 0.2,
							"title": "2013年",
							"type": "column",
							"valueField": "saleRamt4"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "ValueAxis-1",
							"position": "top",
							"axisAlpha": 0
						}
					],
					"allLabels": [],
					"balloon": {},
					"titles": [],
					"dataProvider": orgSumSalesJson${status1.index + 1},
					"export": {
				    		"enabled": true
					}
				
				});
				</c:forEach>
			});
		</script>
    </body>
</html>