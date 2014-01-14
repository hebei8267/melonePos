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
		<script>
    	$(function() {
    		$("#reCalBtn").click(function() {
                $("#listForm").attr("action", "${sc_ctx}/salesWeekTotal/reCal");
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
                            <h3>商品周销售信息＆重计算</h3>
                        </legend>
                    </div>
                    <div class="span4"><br><br>
                    <button	id="reCalBtn" class="btn btn-large btn-primary btn-block" type="button">商品周销售信息＆重计算</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>