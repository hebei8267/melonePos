<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<script src="${ctx}/static/amcharts_3.20/amcharts/amcharts.js"></script>
		<script src="${ctx}/static/amcharts_3.20/amcharts/serial.js"></script>
		<script src="${ctx}/static/amcharts_3.20/amcharts/themes/light.js"></script>
		<script>
			$(function() {
				$("#checkAll").click(function() {
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='orgId']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
                });
				
				$("#listForm").validate({
					rules : {
						orgId : {
                            requiredSelect : 'orgId'
                        }
					}
				});
				
				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					if ($("#itemType").val() == '') {
						$('#__search_tip').modal('show');
						return;
					}
					if (!$("#listForm").valid()) {
						return;
					}

					$("#listForm").attr('target', '_self');
					$("#listForm").attr("action", "${sc_ctx}/salesMonthChartReport/search");
					$("#listForm").submit();
				});
			});
		</script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
        	<div class="row">
            	<div class="span12">
            		<legend>
                    	<h3>月销售信息(图形)</h3>
                	</legend>
            	</div>
            	
            	<div class="span12">
            		<form method="post"	id="listForm">
						<input type="checkbox" id="checkAll"></input>全机构
						<c:forEach items="${orgList}" var="org">
							<c:if test="${orgIds.contains(org.key)}">
							<input type="checkbox" name="orgId" value="${org.key }" checked></input>${org.value }
							</c:if>
							<c:if test="${!orgIds.contains(org.key)}">
							<input type="checkbox" name="orgId" value="${org.key }"></input>${org.value }
							</c:if>
						</c:forEach>
					</form>
				</div>
				
				<div class="span12">
            		<button id="searchBtn" class="btn btn-primary" type="button" style="margin-top: -6px">
						查询
					</button>
            	</div>
            	<div id="chart" class="span12" style="height:550px;border:1px solid #A4BED4;margin-top: 15px;margin-bottom: 35px;"></div>
            	
            	<div id="chart1" class="span12" style="height:350px;border:1px solid #A4BED4;margin-top: 15px;margin-bottom: 35px;"></div>
           	</div>
        </div>
        
        
        <script>
			$(function() {
				var _dataList = ${dataList};
				var _totalList = ${totalList};
				
				var chart = AmCharts.makeChart("chart", {
				    "type": "serial",
				    "theme": "light",
				    "dataProvider": _dataList,
				    "chartCursor": {
				        "cursorPosition": "mouse"
				    },
				    "legend": {
				        "valueWidth": 90,
				        "position": "top",
				        "valueText": "[[value]]元"
				    },
				    "valueAxes": [{
				        "position": "left"
				    }],
				    "graphs": [
					<c:forEach items="${orgIds}" var="orgId" varStatus="status">
						{
					        "bullet": "round",
					        "balloonText": "<b>门店 ${fn:substring(orgId,3,6)}</b><br>销售金额 [[saleRamt${fn:substring(orgId,3,5)}]] 元<br>时间 [[optDateYM]]",
					        "title": "${fn:substring(orgId,3,6)}",
					        "valueField": "saleRamt${fn:substring(orgId,3,5)}"
					    }
						
						<c:if test="${!status.last}">
						,
						</c:if>
					
					</c:forEach>
					],
				    "chartScrollbar": {},
				    "categoryField": "optDateYM"
				});
				
				
				var chart1 = AmCharts.makeChart("chart1", {
				    "type": "serial",
				    "theme": "light",
				    "dataProvider": _totalList,
				    "chartCursor": {
				        "cursorPosition": "mouse"
				    },
				    "titles": [{
				    	"size": 18,
				       	"text": "合计销售金额/卖场数量"
					}],
					"valueAxes": [{
				        "id":"v1",
				        "axisColor": "#FF6600",
				        "axisThickness": 2,
				        "gridAlpha": 0,
				        "axisAlpha": 1,
				        "position": "left"
				    }, {
				        "id":"v2",
				        "axisColor": "#67b7dc",
				        "axisThickness": 2,
				        "gridAlpha": 0,
				        "axisAlpha": 1,
				        "position": "right"
				    }],
				    "graphs": [{
					    "valueAxis": "v1",
					    "lineColor": "#FF6600",
					    "bullet": "square",
					    "balloonText": "销售时间 [[optDateYM]]<br>合计销售金额 [[saleTotalRamt]] 元<br>卖场数量 [[orgCount]] 个",
					    "bulletBorderThickness": 1,
					    "hideBulletsCount": 80,
					    "valueField": "saleTotalRamt",
					    "fillAlphas": 0
					},
					{
					    "valueAxis": "v2",
					    "lineColor": "#67b7dc",
					    "fillAlphas": 0.8,
				        "lineAlpha": 0.2,
				    	"type": "column",
					    "balloonText": "卖场数量 [[orgCount]]",
					    "bulletBorderThickness": 1,
				        "hideBulletsCount": 80,
					    "valueField": "orgCount"
					}],
				    "chartScrollbar": {},
				    "categoryField": "optDateYM"
				});
			});
		</script>
    </body>
</html>