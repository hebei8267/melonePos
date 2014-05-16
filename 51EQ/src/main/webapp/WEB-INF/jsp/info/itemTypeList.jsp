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
            $().ready(function() {
                $("#bwDataSynBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/itemType/bwDataSynBtn");
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
                            <h3>商品类别管理</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <button	id="bwDataSynBtn" class="btn btn-warning" type="button">数据同步 (百威)</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th	width="45" class="center">
                                        序号
                                    </th>
                                    <th width="105" class="center">
                                        类别编号
                                    </th>
                                    <th>
                                        类别名称(短)
                                    </th>
                                    <th>
                                        类别名称
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${itemTypeList}" var="itemType" varStatus="status">
                                    <tr>
                                        <td	class="center">
                                            ${status.index + 1}
                                        </td>
                                        <td class="center">
                                            ${itemType.itemNo}
                                        </td>
                                        <td>
                                            ${itemType.itemShortName}
                                        </td>
                                        <td>
                                            ${itemType.itemName}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	itemTypeList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="4" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>