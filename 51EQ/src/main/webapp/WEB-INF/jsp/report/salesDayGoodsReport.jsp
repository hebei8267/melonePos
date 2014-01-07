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
                    },
                    barcode : {
                		required : true              		
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

                $("#listForm").attr("action", "${sc_ctx}/salesDayGoodsReport/search");
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
                            <h3>商品销售信息一览(按条码)</h3>
                        </legend>
                    </div>
                    <div class="span5">
                        <label class="control-label">销售日期 :</label>
                        <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                        ～
                        <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                    </div>
                    
                    <div class="span7">
                        <label class="control-label">货号/条码 :</label>
                        <input id="barcode" name="barcode" type="text" class="input-medium" value="${barcode }"/>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                        	<thead>
                                <tr>
                                    <th>
                                        机构
                                    </th>
                                    <th>
                                        货号 / 商品名称
                                    </th>
                                    <th width="60">
                                        销售量
                                    </th>
                                    <th width="75">
                                        销售金额
                                    </th>
                                    <th width="75">
                                        日均销量
                                    </th>
                                    <th width="60">
                                        库存量
                                    </th>
                                    <th width="75">
                                        库存金额
                                    </th>
                                    <th width="120">
                                        进价 / 售价
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sumSaleList}" var="sumSale">
                                <tr>
                                	<td>
                                    	${sumSale.orgName}
                                    </td>
                                    <td>
                                    	${sumSale.itemSubno} / ${sumSale.goodsName}
                                    </td>
                                    <td>
                                    	${sumSale.posQty}
                                    </td>
                                    <td class="right">
                                    	${sumSale.posAmt}
                                    </td>
                                    <td>
                                    	${sumSale.averageDailySales}
                                    </td>
                                    <td>
                                    	${sumSale.stockQty}
                                    </td>
                                    <td>
                                    	${sumSale.stockAmt}
                                    </td>
                                    <td>
                                    	${sumSale.stockAmt/sumSale.stockQty} / ${sumSale.saleAmt}
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                    <div class="span6"	style="margin-top: 10px;">
                    	<c:if test="${showFlg == true}">
	                	<div id="chart1" style="width:500px;height:400px;border:1px solid #A4BED4;"></div>
	                	</c:if>
	             	</div>
                </div>
            </form>
        </div>
        <c:if test="${showFlg == true}">
        <script>
			$(function() {
				var _saleRamtJson = ${saleRamtJson}
				
				var pieChart = new dhtmlXChart({
					view : "pie",
					container : "chart1",
					value : "#posAmt#",
					labelOffset : -30,
					label : function(obj) {
						var sum = pieChart.sum("#posAmt#");
						var text = Math.round(parseFloat(obj.posAmt) / sum * 100) + "%";
						return "<div class='label' style='border:1px solid'>" + text + "</div>";
					},
					tooltip: "#posAmt#元    #orgName#",
					legend: "#orgName#"
				});
				pieChart.parse(_saleRamtJson, "json");
				
				
			});
		</script>
		</c:if>
    </body>
</html>