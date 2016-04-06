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
    	<script src="${ctx}/static/amcharts_3.20/amcharts/amcharts.js"></script>
		<script src="${ctx}/static/amcharts_3.20/amcharts/serial.js"></script>
		<script src="${ctx}/static/amcharts_3.20/amcharts/themes/light.js"></script>
		
		<script>
            $(function() {

                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/replenishOrderReport/search");
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
                            <h3>商品收发错填信息</h3>
                        </legend>
                    </div>
                    
                    <div class="span7">
                        <label class="control-label">机构 :</label>
                        <select name="orgId" class="input-medium">
                            <c:forEach items="${orgList}" var="org">
                                <c:if test="${org.key == orgId}">
                                    <option value="${org.key }" selected>${org.value }</option>
                                </c:if>
                                <c:if test="${org.key != orgId}">
                                    <option value="${org.key }">${org.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>&nbsp;&nbsp;
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    
                	<div id="chart1" class="span12" style="height:360px;border:1px solid #A4BED4;margin-top: 10px;"></div>
                	
                	<div id="chart2" class="span12" style="height:360px;border:1px solid #A4BED4;margin-top: 10px;"></div>
				</div>
			</div>
		</div>
		<script>
        	var _data_set = ${odList};
        	var _data_set2 = _data_set[_data_set.length-1].orgReplenishOrderList;
        	var receiveDateYM = _data_set[_data_set.length-1].receiveDateYM;
        	//------------------------------------------------------------
        	var chart1 = AmCharts.makeChart("chart1", {
			    "theme": "light",
			    "type": "serial",
			    "titles": [{
			    	"size": 18,
			       	"text": "商品收发错填信息[全月份]"
				}],
			    "legend": {
			        "useGraphSettings": true,
			        "valueWidth": 35,
			        "valueText": "[[value]]",
			        "position" : "top"
			    },
			    "chartScrollbar": {},
			    "chartCursor": {
			        "cursorPosition": "mouse"
			    },
			    "dataProvider": _data_set,
			    "valueAxes": [{
			        "id":"v1",
			        "axisColor": "#FCD202",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "axisAlpha": 1,
			        "position": "left"
			    }, {
			        "id":"v2",
			        "axisColor": "#FF6600",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "axisAlpha": 1,
			        "position": "right"
			    }, {
			        "id":"v3",
			        "axisColor": "#67b7dc",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "offset": 50,
			        "axisAlpha": 1,
			        "position": "left"
			    }],
			    "startDuration": 1,
			    "graphs": [{
			    	"valueAxis": "v3",
			    	"fillAlphas": 0.8,
			        "lineAlpha": 0.2,
			    	"type": "column",
			        "balloonText": "收货 [[receiveNum]] 次",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 80,
			        "title": "收货次数",
			        "valueField": "receiveNum"
			    },
			    {
			        "valueAxis": "v1",
			        "lineColor": "#FCD202",
			        "bullet": "square",
			        "balloonText": "错填 [[errorNum]] 次",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 80,
			        "title": "错填次数",
			        "valueField": "errorNum",
			        "fillAlphas": 0
			    },
			    {
			        "valueAxis": "v2",
			        "lineColor": "#FF6600",
			        "bullet": "square",
			        "balloonText": "错填率 [[errorRate]]",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 80,
			        "title": "错填率",
			        "valueField": "errorRate",
			        "fillAlphas": 0
			    }],
			    "categoryField": "receiveDateYM"
			});
        	
        	// CURSOR
            var chartCursor = new AmCharts.ChartCursor();
            chart1.addChartCursor(chartCursor);
            chartCursor.addListener("changed", myChartCursor);
        	
        	var chart2 = AmCharts.makeChart("chart2", {
			    "theme": "light",
			    "type": "serial",
			    "titles": [{
			    	"size": 18,
			       	"text": "商品收发错填信息[单月份("+receiveDateYM+")]"
				}],
			    "legend": {
			        "useGraphSettings": true,
			        "valueWidth": 35,
			        "valueText": "[[value]]",
			        "position" : "top"
			    },
			    "chartCursor": {
			        "cursorPosition": "mouse"
			    },
			    "dataProvider": _data_set2,
			    "valueAxes": [{
			        "id":"v1",
			        "axisColor": "#FCD202",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "axisAlpha": 1,
			        "position": "left"
			    }, {
			        "id":"v2",
			        "axisColor": "#FF6600",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "axisAlpha": 1,
			        "position": "right"
			    }, {
			        "id":"v3",
			        "axisColor": "#67b7dc",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "offset": 50,
			        "axisAlpha": 1,
			        "position": "left"
			    }],
			    "startDuration": 1,
			    "graphs": [{
			    	"valueAxis": "v3",
			    	"fillAlphas": 0.8,
			        "lineAlpha": 0.2,
			    	"type": "column",
			        "balloonText": "收货 [[receiveNum]] 次",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 80,
			        "title": "收货次数",
			        "valueField": "receiveNum"
			    },
			    {
			        "valueAxis": "v1",
			        "lineColor": "#FCD202",
			        "bullet": "square",
			        "balloonText": "错填 [[errorNum]] 次",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 80,
			        "title": "错填次数",
			        "valueField": "errorNum",
			        "fillAlphas": 0
			    },
			    {
			        "valueAxis": "v2",
			        "lineColor": "#FF6600",
			        "bullet": "square",
			        "balloonText": "错填率 [[errorRate]]",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 80,
			        "title": "错填率",
			        "valueField": "errorRate",
			        "fillAlphas": 0
			    }],
			    "categoryField": "replenishOrgId"
			});
        	
        	function myChartCursor(obj){
        		if(null != _data_set[obj.index]){
        			//console.log(obj.index);
        			
        			receiveDateYM = _data_set[obj.index].receiveDateYM;
        			_data_set2 = _data_set[obj.index].orgReplenishOrderList;
        			chart2.titles = [{"size": 18,"text":"商品收发错填信息[单月份("+receiveDateYM+")]"}];
        			chart2.dataProvider = _data_set2;
        			chart2.validateNow();
        			chart2.validateData();
        		}
        	}
		</script>
	</body>
</html>