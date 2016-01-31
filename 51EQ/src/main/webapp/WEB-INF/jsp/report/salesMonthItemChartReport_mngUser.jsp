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
		<script src="https://www.amcharts.com/lib/3/pie.js"></script>
	</head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>月销售信息对比(图形)[按督导]</h3>
                        </legend>
                    </div>
                    
                    <div id="chart1" class="span12" style="height:350px;border:1px solid #A4BED4;margin-top: 15px;margin-bottom: 15px;"></div>
                    
                    <div id="chart2" class="span6" style="height:350px;border:1px solid #A4BED4;"></div>
            	</div>
            </form>
     	</div>

     	<script>
     	$(function() {
     		var _dataList = ${dataList};
     		
     		var chart1 = AmCharts.makeChart("chart1", {
			    "type": "serial",
			    "theme": "light",
			    "dataProvider": _dataList,
			    "chartCursor": {
			        "cursorPosition": "mouse"
			    },
			    "legend": {
			        "valueWidth": 105,
			        "valueText": "[[value]]元",
			        "position": "top"
			    },
			    "valueAxes": [{
			        "position": "left"
			    }],
			    "graphs": [
				<c:forEach var="_index" begin="1" end="${mngUserNum}" varStatus="status">
				<%String mngUser="mngUser"+pageContext.getAttribute("_index");%>
					{
				        "bullet": "round",
				        "balloonText": "督导员 [[mngUser${_index}]]<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt${_index}]]元",
				        "title": "<%=request.getAttribute(mngUser)%>",
				        "valueField": "saleRamt${_index}"
				    }
					
					<c:if test="${!status.last}">
					,
					</c:if>
				
				</c:forEach>
				],
			    "chartScrollbar": {},
			    "categoryField": "optDateYM"
			});
     		
     		var chart2 = AmCharts.makeChart("chart2", {
     		    "type": "pie",
     		    "theme": "light",
     		    "dataProvider": _pie_data,
     		    "valueField": "saleRamt",
     		    "titleField": "name",
     		    "outlineAlpha": 0.4,
     		    "depth3D": 15,
     		   	"legend":{
     			   	"position":"top",
     			    "autoMargins":false ,
			        "valueWidth": 100,
			        "valueText": "[[value]]元"
     			  },
     		    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
     		    "angle": 30,
     		    "export": {
     		        "enabled": true
     		    }
     		});
     		
     		// CURSOR
            var chartCursor = new AmCharts.ChartCursor();
            chart1.addChartCursor(chartCursor);
            chartCursor.addListener("changed", myChartCursor);
            
            var _pie_data = {};
            
            function myChartCursor(obj){
            	var _sub_Data = _dataList[obj.index];
        		if(null != _sub_Data){
        			_pie_data = new Array();
        			
        			
        			<c:forEach var="_index" begin="1" end="${mngUserNum}" varStatus="status">
    				<%String _mngUser="mngUser"+pageContext.getAttribute("_index");%>
    				
    				var _mng = new Object();
    				_mng.name = '<%=request.getAttribute(_mngUser)%>';
    				_mng.saleRamt = _sub_Data.saleRamt${_index};
        			_pie_data.push(_mng);
    				
    				</c:forEach>
        			
        			chart2.titles = [{"size": 18,"text":"销售时间["+_sub_Data.optDateYM+"]"}];
        			_pie_data.sort(function(a,b){return a.saleRamt>b.saleRamt?-1:1})
        			chart2.dataProvider = _pie_data;
        			chart2.validateNow();
        			chart2.validateData();
        		}
        	}
     		
     		
     	});
     	</script>
	</body>
</html>