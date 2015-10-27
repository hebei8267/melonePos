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
            	
            	<div id="chart" class="span12" style="height:500px;border:1px solid #A4BED4;margin-top: 5px;margin-bottom: 35px;"></div>
           	</div>
        </div>
        
        
        <script>
			$(function() {
				var _dataList = ${dataList}
				
				var chart = AmCharts.makeChart("chart", {
				    "type": "serial",
				    "theme": "light",
				    "dataProvider": _dataList,
				    "legend": {
				        "position": "top"
				    },
				    "valueAxes": [{
				        "position": "left"
				    }],
				    "graphs": [{
				        "bullet": "round",
				        "balloonText": "1店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt1]] 元",
				        "title": "1店",
				        "valueField": "saleRamt1"
				    },{
				        "bullet": "round",
				        "balloonText": "2店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt2]] 元",
				        "title": "2店",
				        "valueField": "saleRamt2"
				    },{
				        "bullet": "round",
				        "balloonText": "3店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt3]] 元",
				        "title": "3店",
				        "valueField": "saleRamt3"
				    },{
				        "bullet": "round",
				        "balloonText": "4店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt4]] 元",
				        "title": "4店",
				        "valueField": "saleRamt4"
				    },{
				        "bullet": "round",
				        "balloonText": "5店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt5]] 元",
				        "title": "5店",
				        "valueField": "saleRamt5"
				    },{
				        "bullet": "round",
				        "balloonText": "6店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt6]] 元",
				        "title": "6店",
				        "valueField": "saleRamt6"
				    },{
				        "bullet": "round",
				        "balloonText": "7店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt7]] 元",
				        "title": "7店",
				        "valueField": "saleRamt7"
				    },{
				        "bullet": "round",
				        "balloonText": "8店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt8]] 元",
				        "title": "8店",
				        "valueField": "saleRamt8"
				    },{
				        "bullet": "round",
				        "balloonText": "9店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt9]] 元",
				        "title": "9店",
				        "valueField": "saleRamt9"
				    },{
				        "bullet": "round",
				        "balloonText": "10店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt10]] 元",
				        "title": "10店",
				        "valueField": "saleRamt10"
				    },{
				        "bullet": "round",
				        "balloonText": "11店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt11]] 元",
				        "title": "11店",
				        "valueField": "saleRamt11"
				    },{
				        "bullet": "round",
				        "balloonText": "12店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt12]] 元",
				        "title": "12店",
				        "valueField": "saleRamt12"
				    },{
				        "bullet": "round",
				        "balloonText": "13店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt13]] 元",
				        "title": "13店",
				        "valueField": "saleRamt13"
				    },{
				        "bullet": "round",
				        "balloonText": "14店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt14]] 元",
				        "title": "14店",
				        "valueField": "saleRamt14"
				    },{
				        "bullet": "round",
				        "balloonText": "15店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt15]] 元",
				        "title": "15店",
				        "valueField": "saleRamt15"
				    },{
				        "bullet": "round",
				        "balloonText": "16店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt16]] 元",
				        "title": "16店",
				        "valueField": "saleRamt16"
				    },{
				        "bullet": "round",
				        "balloonText": "17店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt17]] 元",
				        "title": "17店",
				        "valueField": "saleRamt17"
				    },{
				        "bullet": "round",
				        "balloonText": "18店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt18]] 元",
				        "title": "18店",
				        "valueField": "saleRamt18"
				    },{
				        "bullet": "round",
				        "balloonText": "19店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt19]] 元",
				        "title": "19店",
				        "valueField": "saleRamt19"
				    },{
				        "bullet": "round",
				        "balloonText": "20店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt20]] 元",
				        "title": "20店",
				        "valueField": "saleRamt20"
				    },{
				        "bullet": "round",
				        "balloonText": "22店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt22]] 元",
				        "title": "22店",
				        "valueField": "saleRamt22"
				    },{
				        "bullet": "round",
				        "balloonText": "23店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt23]] 元",
				        "title": "23店",
				        "valueField": "saleRamt23"
				    },{
				        "bullet": "round",
				        "balloonText": "24店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt24]] 元",
				        "title": "24店",
				        "valueField": "saleRamt24"
				    },{
				        "bullet": "round",
				        "balloonText": "25店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt25]] 元",
				        "title": "25店",
				        "valueField": "saleRamt25"
				    },{
				        "bullet": "round",
				        "balloonText": "26店<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt26]] 元",
				        "title": "26店",
				        "valueField": "saleRamt26"
				    }],
				    "chartScrollbar": {},
				    "categoryField": "optDateYM"
				});
			});
		</script>
    </body>
</html>