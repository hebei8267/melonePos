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
    	</style>
    	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/dhtmlxchart.css">
    	<script src="${ctx}/static/js/dhtmlxchart.js" type="text/javascript"></script>
    	<script>
            $(function() {             
                $("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

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
                            <div id="chart1${status1.index + 1}" style="width:900px;height:900px;border:1px solid #A4BED4;"></div>
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
				
				var barChart1${status1.index + 1} = new dhtmlXChart({
		            view: "barH",
		            container: "chart1${status1.index + 1}",
		            value: "#saleRamt1#",
		            label: "#saleRamt1#",
		            color: "#FF8000",
		            tooltip: {
		                template: "#saleRamt1#元(#optDateYM1#)"
		            },
		            width: 60,
		            yAxis: {
		                template: "#optDateM#月"
		            },
		            xAxis: {
		            	title : "销 售 金 额"
		            },
		            padding : {
						left : 70
					},
		            legend: {
		                values: [{
		                    text: "${optDateYM1}年",
		                    color: "#FF8000"
		                }, {
		                    text: "${optDateYM2}年",
		                    color: "#00FF40"
		                }, {
		                    text: "${optDateYM3}年",
		                    color: "#0174DF"
		                }, {
		                    text: "${optDateYM4}年",
		                    color: "#FFCC00"
		                }],
		                valign: "middle",
		                align: "right",
		                width: 70,
		                layout: "y"
		            }
		        });
				barChart1${status1.index + 1}.addSeries({
		            value: "#saleRamt2#",
		            label: "#saleRamt2#",
		            color: "#00FF40",
		            tooltip: {
		            	template: "#saleRamt2#元(#optDateYM2#)"
		            }
		        });
				barChart1${status1.index + 1}.addSeries({
		            value: "#saleRamt3#",
		            label: "#saleRamt3#",
		            color: "#0174DF",
		            tooltip: {
		            	template: "#saleRamt3#元(#optDateYM3#)"
		            }
		        });
				barChart1${status1.index + 1}.addSeries({
		            value: "#saleRamt4#",
		            label: "#saleRamt4#",
		            color: "#FFCC00",
		            tooltip: {
		            	template: "#saleRamt4#元(#optDateYM4#)"
		            }
		        });
		        barChart1${status1.index + 1}.parse(orgSumSalesJson${status1.index + 1}, "json");
				</c:forEach>
			});
		</script>
    </body>
</html>