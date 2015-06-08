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
    	<script src="http://www.amcharts.com/lib/3/amcharts.js"></script>
		<script src="http://www.amcharts.com/lib/3/serial.js"></script>
		<script src="http://www.amcharts.com/lib/3/themes/light.js"></script>
		<script>
		$(function() {
			$("#searchBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#listForm").attr('target', '_self');
                $("#listForm").attr("action", "${sc_ctx}/storeChartReport/search");
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
                            <h3>库存信息(图形)</h3>
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
                        <button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
                    </div>
                    
                    <div style="margin-top: 10px;" class="span12">
                        <div id="chart1" style="width:700px;height:400px;border:1px solid #A4BED4;"></div>
                    </div>
                    
                    <div style="margin-top: 10px;" class="span12">
                        <div id="chart2" style="width:700px;height:400px;border:1px solid #A4BED4;"></div>
                    </div>
                </div>
            </form>
        </div>
        
        <script>
        	var _data_set = ${data_set}
        	//------------------------------------------------------------
        	var chart1 = AmCharts.makeChart("chart1", {
			    "theme": "light",
			    "type": "serial",
			    "legend": {
			        "useGraphSettings": true,
			        "valueWidth": 130,
			        "valueText": "[[value]] 元",
			        "position" : "top"
			    },
			    "chartScrollbar": {},
			    "chartCursor": {
			        "cursorPosition": "mouse"
			    },
			    "dataProvider": _data_set,
			    "valueAxes": [{
			        "id": "v1",
			        "axisColor": "#FF6600",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "axisAlpha": 1,
			        "position": "right"
			    },
			    {
			        "id": "v2",
			        "axisColor": "#FCD202",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "axisAlpha": 1,
			        "position": "left"
			    }],
			    "startDuration": 1,
			    "graphs": [{
			        "valueAxis": "v1",
			        "lineColor": "#FF6600",
			        "bullet": "square",
			        "balloonText": "[[value]] 元",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 80,
			        "title": "库存金额",
			        "valueField": "stockTotalAmt",
			        "fillAlphas": 0
			    },
			    {
			        "valueAxis": "v2",
			        "lineColor": "#FCD202",
			        "bullet": "square",
			        "balloonText": "[[value]] 元",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 80,
			        "title": "负库存金额",
			        "valueField": "stockTotalAmt_Minus",
			        "fillAlphas": 0
			    }],
			    "categoryField": "optDate"
			});

	        var chart2 = AmCharts.makeChart("chart2", {
			    "theme": "light",
			    "type": "serial",
			    "legend": {
			        "useGraphSettings": true,
			        "valueWidth": 130,
			        "valueText": "[[value]] 个",
			        "position" : "top"
			    },
			    "chartScrollbar": {},
			    "chartCursor": {
			        "cursorPosition": "mouse"
			    },
			    "dataProvider": _data_set,
			    "valueAxes": [{
			        "id": "v1",
			        "axisColor": "#FF6600",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "axisAlpha": 1,
			        "position": "right"
			    },
			    {
			        "id": "v2",
			        "axisColor": "#FCD202",
			        "axisThickness": 2,
			        "gridAlpha": 0,
			        "axisAlpha": 1,
			        "position": "left"
			    }],
			    "startDuration": 1,
			    "graphs": [{
			        "valueAxis": "v1",
			        "lineColor": "#FF6600",
			        "bullet": "square",
			        "balloonText": "[[value]] 个",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 30,
			        "title": "库存数量",
			        "valueField": "stockTotalQty",
			        "fillAlphas": 0
			    },
			    {
			        "valueAxis": "v2",
			        "lineColor": "#FCD202",
			        "bullet": "square",
			        "balloonText": "[[value]] 个",
			        "bulletBorderThickness": 1,
			        "hideBulletsCount": 30,
			        "title": "负库存数量",
			        "valueField": "stockTotalQty_Minus",
			        "fillAlphas": 0
			    }],
			    "categoryField": "optDate"
			});
		</script>
	</body>
</html>