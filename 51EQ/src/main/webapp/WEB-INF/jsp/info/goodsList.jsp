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
            ._warn1 {
				color: #FF6633;
				padding: 5px;
			}
        </style>
        <script>
            $().ready(function() {
                $("#bwDataSynBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/goods/bwDataSynBtn");
                    $("#listForm").submit();
                });
            });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>商品管理</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <button	id="bwDataSynBtn" class="btn btn-warning" type="button">数据同步 (百威)</button>
                    </div>
                    <div class="span12"	style="margin-top: 50px;">
                        <h1>现有商品信息 <span class="_warn1">${goodsCount}</span> 个</h1>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>