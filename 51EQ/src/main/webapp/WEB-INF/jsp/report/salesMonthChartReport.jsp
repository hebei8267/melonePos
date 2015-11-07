<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
				$("#checkAll").click(function() {
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='orgId']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
                });
				
				$("#listForm").validate({
					rules : {
						orgId : {
                            requiredSelect : 'orgId'
                        }
					}
				});
				
				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					if ($("#itemType").val() == '') {
						$('#__search_tip').modal('show');
						return;
					}
					if (!$("#listForm").valid()) {
						return;
					}

					$("#listForm").attr('target', '_self');
					$("#listForm").attr("action", "${sc_ctx}/salesMonthChartReport/search");
					$("#listForm").submit();
				});
			});
		</script>
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
            	
            	<div class="span12">
            		<form method="post"	id="listForm">
						<input type="checkbox" id="checkAll"></input>全机构
						<c:forEach items="${orgList}" var="org">
							<c:if test="${orgIds.contains(org.key)}">
							<input type="checkbox" name="orgId" value="${org.key }" checked></input>${org.value }
							</c:if>
							<c:if test="${!orgIds.contains(org.key)}">
							<input type="checkbox" name="orgId" value="${org.key }"></input>${org.value }
							</c:if>
						</c:forEach>
					</form>
				</div>
				
				<div class="span12">
            		<button id="searchBtn" class="btn btn-primary" type="button" style="margin-top: -6px">
						查询
					</button>
            	</div>
            	<div id="chart" class="span12" style="height:500px;border:1px solid #A4BED4;margin-top: 15px;margin-bottom: 35px;"></div>
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
				    "graphs": [
					<c:forEach items="${orgIds}" var="orgId" varStatus="status">
						{
					        "bullet": "round",
					        "balloonText": "门店 ${fn:substring(orgId,3,6)}<br>销售时间 [[optDateYM]]<br>销售金额 [[saleRamt${fn:substring(orgId,3,5)}]] 元",
					        "title": "${fn:substring(orgId,3,6)}",
					        "valueField": "saleRamt${fn:substring(orgId,3,5)}"
					    }
						
						<c:if test="${!status.last}">
						,
						</c:if>
					
					</c:forEach>
					],
				    "chartScrollbar": {},
				    "categoryField": "optDateYM"
				});
			});
		</script>
    </body>
</html>