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
			margin-bottom: 10px;
		}
    	</style>
    	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/dhtmlxchart.css">
    	<script src="${ctx}/static/js/dhtmlxchart.js" type="text/javascript"></script>

        <script>
            $(function() {
            	$("#listForm").validate({
                    rules : {
                    	optDateShow : {
                    		required : true,
                    		date : true                    		
                        }
                    }
                });
            	
            	$('#optDateShow').datepicker({
                    format : 'yyyy-mm',
                    viewMode : 1,
                    minViewMode : 1
                });
            	
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/cashChartReport/search");
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
                            <h3>销售信息(图形)</h3>
                        </legend>
                    </div>
                    <div class="span3">
                        <label class="control-label">销售日期 :</label>
                        <input id="optDateShow" name="optDateShow" type="text" class="input-medium" value="${optDateShow }"/>
                    </div>
                    <div class="span9">
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
                    
                    <c:if test="${showFlg == true}">
                    <div class="span12" style="margin-top: 20px;"><h4>${orgName} - 销售金额  (合计: ${total} 元)</h4></div>
                    <div class="span12">
                        <div id="chart1" style="width:1050px;height:400px;border:1px solid #A4BED4;"></div>
                    </div>
                    <div class="span12 cash_daily"></div>
                    </c:if>
                    
                    <c:forEach items="${orgNameList}" var="orgName" varStatus="status1">
                    	<div class="span12" style="margin-top: 20px;"><h4>${orgName} - 销售金额  (合计: ${totalList[status1.index]} 元)</h4></div>
                        <div class="span12">
                            <div id="chart2${status1.index + 1}" style="width:1050px;height:400px;border:1px solid #A4BED4;"></div>
                        </div>
                        <div class="span12 cash_daily"></div>
                    </c:forEach>
                </div>
            </form>
        </div>
        

		<c:if test="${showFlg == true}">
		<script>
			var _data_set = ${data_set}
			$(function() {
				var chart = new dhtmlXChart({
					view : "bar",
					container : "chart1",
					value : "#saleAmt#",
					label : "#saleAmt#",
					radius : 0,
					width : 20,
					tooltip : {
						template : "#saleAmt#元"
					},
					xAxis : {
						title : "${optDateShow }",
						template : "#optDate#",
						lines : false
					},
					yAxis: {
						title : "销 售 额"
				    },
					padding : {
						left : 70
					}
				});
				chart.parse(_data_set, "json");
			});
			
			
			<c:forEach items="${allSubOrgCashDailyList}" var="subOrgCashDailyList" varStatus="status1">
			var _data_set${status1.index + 1} = ${subOrgCashDailyList}
			$(function() {
				var chart${status1.index + 1} = new dhtmlXChart({
					view : "bar",
					container : "chart2${status1.index + 1}",
					value : "#saleAmt#",
					label : "#saleAmt#",
					radius : 0,
					width : 20,
					tooltip : {
						template : "#saleAmt#元"
					},
					xAxis : {
						title : "${optDateShow }",
						template : "#optDate#",
						lines : false
					},
					yAxis: {
						title : "销 售 额"
				    },
					padding : {
						left : 70
					}
				});
				chart${status1.index + 1}.parse(_data_set${status1.index + 1}, "json");
			});
			</c:forEach>
		</script>
		</c:if>
    </body>
</html>