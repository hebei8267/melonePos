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
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>月销售信息对比(图形)[按督导]</h3>
                        </legend>
                    </div>
                    
                    <div id="chart" class="span12" style="height:350px;border:1px solid #A4BED4;margin-top: 15px;margin-bottom: 35px;"></div>
            	</div>
            </form>
     	</div>

     	<script>
     	$(function() {
     		var _dataList = ${dataList};
     		
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
     	});
     	</script>
	</body>
</html>