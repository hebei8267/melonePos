<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="DEFAULT_RETAINED_AMT">
    ${DEFAULT_RETAINED_AMT}
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
                            <h3>商品周销售信息对比(按商品)</h3>
                        </legend>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                	<th rowspan="2" class="center">
                                        机构
                                    </th>
                                    <th rowspan="2" class="center">
                                        货号
                                    </th>
                                    <th rowspan="2" class="center">
                                        名称
                                    </th>
                                    <th colspan="6" class="center">
                                        销量(件)
                                    </th>
                                    <th colspan="5" class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                        销售额(元)
                                    </th>
                                </tr>
                                <tr>
                                    <th class="center">
                                        近一周
                                    </th>
                                    <th class="center">
                                        近两周
                                    </th>
                                    <th class="center">
                                        近三周
                                    </th>
                                    <th class="center">
                                        近四周
                                    </th>
                                    <th class="center">
                                        合计
                                    </th>
                                    <th class="center">
                                        库存量
                                    </th>
                                    <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                        近一周
                                    </th>
                                    <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                        近二周
                                    </th>
                                    <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                        近三周
                                    </th>
                                    <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                        近四周
                                    </th>
                                    <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                        合计
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${salesWeekGoodsList}" var="salesWeekGoods" varStatus="status1">
                                    <tr>
                                    	<td class="center">
                                        	${salesWeekGoods.orgId}
                                        </td>
                                        <c:if test="${status1.index == 0}" >
                                        <td rowspan="${salesWeekGoodsList.size()}" class="center">
                                        	${salesWeekGoods.barcode}
                                        </td>
                                        
                                        <td rowspan="${salesWeekGoodsList.size()}" class="center">
                                        	${salesWeekGoods.productName}
                                        </td>
                                        </c:if>
                                        <td>
                                        	${salesWeekGoods.posQty1}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posQty2}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posQty3}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posQty4}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posQtyTotal}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.stockQty}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posAmt1}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posAmt2}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posAmt3}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posAmt4}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posAmtTotal}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	salesWeekGoodsList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="14" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                    	<div id="chart2" style="width:1000px;height:800px;border:1px solid #A4BED4;"></div>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                    	<div id="chart1" style="width:1000px;height:1000px;border:1px solid #A4BED4;"></div>
                    </div>
                </div>
            </form>
        </div>
        
        <script>
			$(function() {
				var _salesWeekGoodsListJson = ${salesWeekGoodsListJson}
				var barChart1 = new dhtmlXChart({
					view : "barH",
					container : "chart1",
					value : "#posQty1#",
					label : "#posQty1#",
					color: "#FF8000",
		            tooltip: {
		                template: "#posQty1#个"
		            },
		            width: 60,
		            yAxis: {
		                template: "#orgId#"
		            },
		            xAxis: {
		            	title : "销 售 数 量"
		            },
		            padding : {
						left : 70
					},
		            legend: {
		                values: [{
		                    text: "近一周",
		                    color: "#FF8000"
		                }, {
		                    text: "近两周",
		                    color: "#00FF40"
		                }, {
		                    text: "近三周",
		                    color: "#0174DF"
		                }, {
		                    text: "近四周",
		                    color: "#FFCC00"
		                }, {
		                    text: "库存量",
		                    color: "#6E6E6E"
		                }],
		                valign: "middle",
		                align: "right",
		                width: 70,
		                layout: "y"
		            }
				});
				barChart1.addSeries({
		            value: "#posQty2#",
		            label: "#posQty2#",
		            color: "#00FF40",
		            tooltip: {
		            	template: "#posQty2#个"
		            }
		        });
				barChart1.addSeries({
		            value: "#posQty3#",
		            label: "#posQty3#",
		            color: "#0174DF",
		            tooltip: {
		            	template: "#posQty3#个"
		            }
		        });
				barChart1.addSeries({
		            value: "#posQty4#",
		            label: "#posQty4#",
		            color: "#FFCC00",
		            tooltip: {
		            	template: "#posQty4#个"
		            }
		        });
				barChart1.addSeries({
		            value: "#stockQty#",
		            label: "#stockQty#",
		            color: "#6E6E6E",
		            tooltip: {
		            	template: "#stockQty#个"
		            }
		        });
				barChart1.parse(_salesWeekGoodsListJson, "json");
				
				
				
				var barChart2 = new dhtmlXChart({
					view : "barH",
					container : "chart2",
					value : "#posAmt1#",
					label : "#posAmt1#",
					color: "#FF8000",
		            tooltip: {
		                template: "#posAmt1#元"
		            },
		            width: 60,
		            yAxis: {
		                template: "#orgId#"
		            },
		            xAxis: {
		            	title : "销 售 金 额"
		            },
		            padding : {
						left : 70
					},
		            legend: {
		                values: [{
		                    text: "近一周",
		                    color: "#FF8000"
		                }, {
		                    text: "近两周",
		                    color: "#00FF40"
		                }, {
		                    text: "近三周",
		                    color: "#0174DF"
		                }, {
		                    text: "近四周",
		                    color: "#FFCC00"
		                }],
		                valign: "middle",
		                align: "right",
		                width: 70,
		                layout: "y"
		            }
				});
				barChart2.addSeries({
		            value: "#posAmt2#",
		            label: "#posAmt2#",
		            color: "#00FF40",
		            tooltip: {
		            	template: "#posAmt2#元"
		            }
		        });
				barChart2.addSeries({
		            value: "#posAmt3#",
		            label: "#posAmt3#",
		            color: "#0174DF",
		            tooltip: {
		            	template: "#posAmt3#元"
		            }
		        });
				barChart2.addSeries({
		            value: "#posAmt4#",
		            label: "#posAmt4#",
		            color: "#FFCC00",
		            tooltip: {
		            	template: "#posAmt4#元"
		            }
		        });
				barChart2.parse(_salesWeekGoodsListJson, "json");
			});
		</script>
    </body>
</html>