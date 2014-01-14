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
                                	<th>
                                        机构
                                    </th>
                                    <th>
                                        货号
                                    </th>
                                    <th>
                                        名称
                                    </th>
                                    <th>
                                        近一周(销量)
                                    </th>
                                    <th>
                                        近两周(销量)
                                    </th>
                                    <th>
                                        近三周(销量)
                                    </th>
                                    <th>
                                        近四周(销量)
                                    </th>
                                    <th>
                                        合计(销量)
                                    </th>
                                    <th>
                                        库存量
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${salesWeekGoodsList}" var="salesWeekGoods">
                                    <tr>
                                    	<td>
                                        	${salesWeekGoods.orgId}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.barcode}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.productName}
                                        </td>
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
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	salesWeekGoodsList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="8"	class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                    	<div id="chart1" style="width:1000px;height:1000px;border:1px solid #A4BED4;"></div>
                    </div>
                </div>
            </form>
        </div>
        
        <script>
			$(function() {
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
				barChart1.parse(${salesWeekGoodsListJson}, "json");
			});
		</script>
    </body>
</html>