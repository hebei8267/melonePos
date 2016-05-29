<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>日销售信息同期对比</h3>
                        </legend>
                    </div>
                    
                
					<div class="span12">
						<ul id="myTab" class="nav nav-tabs">
			              	<li><a href="#_tab1" data-toggle="tab">同期增长额</a></li>
			              	<li><a href="#_tab2" data-toggle="tab">同期增长率[近40天]</a></li>
			              <!-- 	<li><a href="#_tab3" data-toggle="tab">任务增长率[近40天]</a></li> -->
	            		</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade" id="_tab1" style="overflow: auto;-webkit-overflow-scrolling: touch;">
								<iframe src="${sc_ctx}/salesDayChartReport/init_tab1" frameborder="0" width="100%" height="1200"></iframe>
			              	</div>
							<div class="tab-pane fade" id="_tab2" style="overflow: auto;-webkit-overflow-scrolling: touch;">
								<iframe src="${sc_ctx}/salesDayChartReport/init_tab2" frameborder="0" width="100%" height="1700"></iframe>
	                		</div>
	                	<!--	<div class="tab-pane fade" id="_tab3" style="overflow: auto;-webkit-overflow-scrolling: touch;">
								<iframe src="${sc_ctx}/salesDayChartReport/init_tab3" frameborder="0" width="100%" height="1700"></iframe>
	                		</div> -->
	            		</div>
            		</div>
                </div>
            </form>
        </div>


		<script>
			$(function() {
				$('#myTab a:first').tab('show');
			});
		</script>
    </body>
</html>