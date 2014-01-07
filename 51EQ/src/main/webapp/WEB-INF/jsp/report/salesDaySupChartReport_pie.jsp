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
                $("#listForm").validate({
                    rules : {
                        optDateShow_start : {
                            required : true,
                            date : true
                        },
                        optDateShow_end : {
                            required : true,
                            date : true,
                    		compareDate : "#optDateShow_start"
                        }
                    }
                });

                $('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
                });

                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/salesDaySupChartReport/pie_search");
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
                            <h3>货商销售金额对比(图形)</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <label class="control-label">销售日期 :</label>
                        <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                        ～
                        <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>

                </div>

				<c:forEach items="${saleRamtJsonList}" var="saleRamtJson" varStatus="status1">
				<div class="row">
					<div class="span12 cash_daily"></div>
					<div class="span12"><h4>${orgNameList.get(status1.index) } - 销售金额</h4></div>
					<div class="span6"	style="margin-top: 10px;">
	                	<div id="chart1${status1.index + 1}" style="width:570px;height:600px;border:1px solid #A4BED4;"></div>
	                </div>
	                <div class="span6"	style="margin-top: 10px;">
	                	<div id="chart2${status1.index + 1}" style="width:570px;height:600px;border:1px solid #A4BED4;"></div>
	             	</div>
             	</div>
             	</c:forEach>
            </form>
        </div>
        
        
		<script>
			$(function() {
				<c:forEach items="${saleRamtJsonList}" var="saleRamtJson" varStatus="status1">
				var _saleRamtJson${status1.index + 1} = ${saleRamtJson}
				
				
				var pieChart${status1.index + 1} = new dhtmlXChart({
					view : "pie",
					container : "chart1${status1.index + 1}",
					value : "#saleAmt#",
					labelOffset : -30,
					label : function(obj) {
						var sum = pieChart${status1.index + 1}.sum("#saleAmt#");
						var text = Math.round(parseFloat(obj.saleAmt) / sum * 100) + "%";
						return "<div class='label' style='border:1px solid'>" + text + "</div>";
					},
					tooltip: "#saleAmt#元    #supplierName#",
					legend: "#supplierName#"
				});
				pieChart${status1.index + 1}.parse(_saleRamtJson${status1.index + 1}, "json");
				
				var barChart${status1.index + 1} = new dhtmlXChart({
					view : "barH",
					container : "chart2${status1.index + 1}",
					value : "#saleAmt#",
					label : "#saleAmt#",
					barWidth : 30,
					radius : 2,
					tooltip : {
						template : "#saleAmt#元    #supplierName#"
					},
					yAxis : {
						template : "#supplierName#"
					},
					xAxis: {
		            	title : "销 售 金 额"
		            },
					padding : {
						left : 210,
						right : 70
					}
				});
				barChart${status1.index + 1}.parse(_saleRamtJson${status1.index + 1}, "json");
				</c:forEach>
			});
		</script>
    </body>
</html>