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
			margin-bottom: 30px;
		}
    	</style>
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

                    $("#listForm").attr("action", "${sc_ctx}/salesDayItemChartReport/bar_search");
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
                            <h3>类别销售信息一览(图形)</h3>
                        </legend>
                    </div>
                    <div class="span5">
                        <label class="control-label">销售日期 :</label>
                        <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                        ～
                        <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
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
                        </select>
                        &nbsp;&nbsp;<button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <c:if test="${showFlg == true}">
                        <div class="span12"	style="margin-top: 10px;">
                            <div id="chart1" style="width:900px;height:800px;border:1px solid #A4BED4;"></div>
                        </div>
                        <div class="span12"	style="margin-top: 10px;">
                            <div id="chart2" style="width:900px;height:800px;border:1px solid #A4BED4;"></div>
                        </div>
                        <div class="span12 cash_daily"></div>
                        <div class="span12"	style="margin-top: 10px;">
                        	<table class="table	table-striped table-bordered table-condensed mytable">
	                            <thead>
	                                <tr>
	                                    <th>
	                                        机构
	                                    </th>
	                                    <th class="center">
	                                        日期
	                                    </th>
	                                    <th class="right">
	                                        合计销售数量(个)
	                                    </th>
	                                    <th class="right">
	                                        合计销售金额(元)
	                                    </th>
	                                    <th class="right">
	                                        销售均价(元)
	                                    </th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                            	<c:forEach items="${sumSaleList}" var="sumSale">
	                            	<tr>
	                            		<td>${sumSale.orgName}</td>
	                            		<td width="240" class="center">${optDateShow_start } ～ ${optDateShow_end }</td>
	                            		<td class="right">${sumSale.saleRqty}</td>
	                            		<td class="right">${sumSale.saleRamt}</td>
	                            		<td class="right">${sumSale.salePrice}</td>
	                            	</tr>
	                            	</c:forEach>
	                            </tbody>
	                            <c:if test="${empty	sumSaleList}" >
	                                <tfoot>
	                                    <tr>
	                                        <td	colspan="5"	class="rounded-foot-left">
	                                            无记录信息
	                                        </td>
	                                    </tr>
	                                </tfoot>
	                            </c:if>
                            </table>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>

		<c:if test="${showFlg == true}">
		<script>
			$(function() {
				var _sumSaleRamtList = ${sumSaleRamtList}
				var _sumSaleRqtyList = ${sumSaleRqtyList}
				
				var barChart1 = new dhtmlXChart({
					view : "barH",
					container : "chart1",
					value : "#saleRamt#",
					label : "#saleRamt#",
					barWidth : 30,
					radius : 2,
					tooltip : {
						template : "#saleRamt#元    #itemName#"
					},
					yAxis : {
						template : "#itemShortName#"
					},
					xAxis: {
		            	title : "销 售 金 额"
		            },
					padding : {
						left : 90,
						right : 70
					}
				});
				barChart1.parse(_sumSaleRamtList, "json");
				
				
				var barChart2 = new dhtmlXChart({
					view : "barH",
					container : "chart2",
					value : "#saleRqty#",
					label : "#saleRqty#",
					barWidth : 30,
					radius : 2,
					tooltip : {
						template : "#saleRqty#个    #itemName#"
					},
					yAxis : {
						template : "#itemShortName#"
					},
					xAxis: {
		            	title : "销 售 数 量"
		            },
					padding : {
						left : 90,
						right : 70
					}
				});
				barChart2.parse(_sumSaleRqtyList, "json");
			});
		</script>
		
		</c:if>
    </body>
</html>