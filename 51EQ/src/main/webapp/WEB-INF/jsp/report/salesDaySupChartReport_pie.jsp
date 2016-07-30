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
    	<script src="${ctx}/static/amcharts_3.20/amcharts/amcharts.js"></script>
		<script src="${ctx}/static/amcharts_3.20/amcharts/serial.js"></script>
		<script src="${ctx}/static/amcharts_3.20/amcharts/themes/light.js"></script>

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

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/salesDaySupChartReport/pie_search");
                    $("#listForm").submit();
                });
            });
            
            function setOrgId(orgId){
            	$("#orgId").val(orgId);
            	
            	$("#listForm").attr("target", "_blank");
            	$("#listForm").attr("action", "${sc_ctx}/salesDaySupChartReport/pie_detail_list");
                $("#listForm").submit();
            }
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
                            <h3>货商销售信息对比(图形)</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <label class="control-label">销售日期 :</label>
                        <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                        ～
                        <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                        &nbsp;&nbsp;<button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>

                </div>

				<input type="hidden" name="orgId" id="orgId"/>
				<c:forEach items="${saleRamtJsonList}" var="saleRamtJson" varStatus="status1">
				<div class="row">
					<div class="span12 cash_daily"></div>
					
					<div class="span12" align="right">
						<c:if test="${status1.index != 0}" >
						<a href="#" onclick="setOrgId('${orgIdList.get(status1.index) }');" class="btn btn-warning"/>>>更多</a>
						</c:if>
					</div>
					
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
				var chart1${status1.index + 1} = AmCharts.makeChart("chart1${status1.index + 1}", {
					"type": "serial",
					"theme": "light",
					"categoryField": "supplierName",
					"rotate": true,
					"startDuration": 1,
					"categoryAxis": {
						"gridPosition": "start",
				        "axisAlpha": 0,
				        "gridAlpha": 0,
				        "position": "left"
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "<b>供应商:[[category]]<br>销售金额[[value]]元<br>销售数量[[saleQty]]个</b>",
							"fillAlphas": 0.8,
							"labelText": "[[value]]元",
							"labelOffset": 0,
							"lineAlpha": 0.2,
							"type": "column",
							"valueField": "saleAmt"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "ValueAxis-1",
							"position": "top",
							"axisAlpha": 0,
							"title": "${orgNameList.get(status1.index) } - 销售金额"
						}
					],
					"allLabels": [],
					"balloon": {},
					"titles": [],
					"dataProvider": _saleRamtJson${status1.index + 1},
					"export": {
				    		"enabled": true
					}
				
				});
				</c:forEach>
				
				
				<c:forEach items="${saleRqtyJsonList}" var="saleRqtyJson" varStatus="status1">
				var _saleRqtyJson${status1.index + 1} = ${saleRqtyJson}				
				var chart2${status1.index + 1} = AmCharts.makeChart("chart2${status1.index + 1}", {
					"type": "serial",
					"theme": "light",
					"categoryField": "supplierName",
					"rotate": true,
					"startDuration": 1,
					"categoryAxis": {
						"gridPosition": "start",
				        "axisAlpha": 0,
				        "gridAlpha": 0,
				        "position": "left"
					},
					"trendLines": [],
					"graphs": [
						{
							"balloonText": "<b>类别:[[category]]<br>销售金额[[saleAmt]]元<br>销售数量[[value]]个</b>",
							"fillAlphas": 0.8,
							"labelText": "[[value]]个",
							"labelOffset": 0,
							"lineAlpha": 0.2,
							"type": "column",
							"valueField": "saleQty"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "ValueAxis-1",
							"position": "top",
							"axisAlpha": 0,
							"title": "${orgNameList.get(status1.index) } - 销售数量"
						}
					],
					"allLabels": [],
					"balloon": {},
					"titles": [],
					"dataProvider": _saleRqtyJson${status1.index + 1},
					"export": {
				    		"enabled": true
					}
				
				});
				
				</c:forEach>
			});
		</script>
    </body>
</html>