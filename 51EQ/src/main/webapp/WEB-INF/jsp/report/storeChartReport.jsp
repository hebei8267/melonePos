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
    	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/dhtmlxchart.css">
    	<script src="${ctx}/static/js/dhtmlxchart.js" type="text/javascript"></script>
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
                    <div class="span12" style="margin-top: -10px;">
                    	<h5>库存日期${maxOptDate}</h5>
                    </div>
                    
                    <div class="span6"	style="margin-top: 10px;">
                        <div id="chart1" style="width:500px;height:600px;border:1px solid #A4BED4;"></div>
                    </div>
                    
                    <div class="span6"	style="margin-top: 10px;">
                        <div id="chart2" style="width:500px;height:600px;border:1px solid #A4BED4;"></div>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                        <div id="chart3" style="width:1100px;height:600px;border:1px solid #A4BED4;"></div>
                    </div>
                </div>
            </form>
        </div>
        
        <script>
        	var _data_set = ${data_set}
        	//------------------------------------------------------------
	        var barChart1 = new dhtmlXChart({
	            view: "barH",
	            container: "chart1",
	            value: "#stockTotalQty#",
	            label: "#stockTotalQty#",
	            color: "#0174DF",
	            tooltip: {
	                template: "#stockTotalQty#"
	            },
	            width: 60,
	            yAxis: {
	                template: "#orgName#"
	            },
	            xAxis: {
	            	title : "库 存 数 量"
	            },
	            padding : {
					left : 70
				},
	            legend: {
	                values: [{
	                    text: "正库存",
	                    color: "#0174DF"
	                }, {
	                    text: "负库存",
	                    color: "#FF8000"
	                }],
	                valign: "middle",
	                align: "right",
	                width: 70,
	                layout: "y"
	            }
	        });
	        barChart1.addSeries({
	            value: "#stockTotalQty_Minus#",
	            label: "-#stockTotalQty_Minus#",
	            color: "#FF8000",
	            tooltip: {
	                template: "-#stockTotalQty_Minus#"
	            }
	        });
	        barChart1.parse(_data_set, "json");
	      	//------------------------------------------------------------
	        var barChart2 = new dhtmlXChart({
	            view: "barH",
	            container: "chart2",
	            value: "#stockTotalAmt#",
	            label: "#stockTotalAmt#",
	            color: "#0174DF",
	            tooltip: {
	                template: "#stockTotalAmt#元"
	            },
	            width: 60,
	            yAxis: {
	                template: "#orgName#"
	            },
	            xAxis: {
	            	title : "库 存 金 额"
	            },
	            padding : {
					left : 70
				},
	            legend: {
	                values: [{
	                    text: "正库存",
	                    color: "#0174DF"
	                }, {
	                    text: "负库存",
	                    color: "#FF8000"
	                }],
	                valign: "middle",
	                align: "right",
	                width: 70,
	                layout: "y"
	            }
	        });
	        barChart2.addSeries({
	            value: "#stockTotalAmt_Minus#",
	            label: "-#stockTotalAmt_Minus#",
	            color: "#FF8000",
	            tooltip: {
	                template: "-#stockTotalAmt_Minus#元"
	            }
	        });
	        barChart2.parse(_data_set, "json");
	      	//------------------------------------------------------------
	        var barChart3 = new dhtmlXChart({
	            view: "barH",
	            container: "chart3",
	            value: "#itemSaleTotalAmt#",
	            label: "#itemSaleTotalAmt#",
	            color: "#0174DF",
	            tooltip: {
	                template: "#itemSaleTotalAmt#元"
	            },
	            width: 60,
	            yAxis: {
	                template: "#orgName#"
	            },
	            xAxis: {
	            	title : "售 价 金 额"
	            },
	            padding : {
					left : 70
				},
	            legend: {
	                values: [{
	                    text: "正库存",
	                    color: "#0174DF"
	                }, {
	                    text: "负库存",
	                    color: "#FF8000"
	                }],
	                valign: "middle",
	                align: "right",
	                width: 70,
	                layout: "y"
	            }
	        });
	        barChart3.addSeries({
	            value: "#itemSaleTotalAmt_Minus#",
	            label: "-#itemSaleTotalAmt_Minus#",
	            color: "#FF8000",
	            tooltip: {
	                template: "-#itemSaleTotalAmt_Minus#元"
	            }
	        });
	        barChart3.parse(_data_set, "json");
		</script>
	</body>
</html>