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
            .form-horizontal .control-label {
                width: 70px;
            }
        </style>
        <script>
            $().ready(function() {
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/employee/search");
                    $("#listForm").submit();
                });

                $("#zkDataSynBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/employee/zkDataSyn");
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
                            <h3>职员管理</h3>
                        </legend>
                    </div>

                    <div class="span6">
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
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                        <button	id="zkDataSynBtn" class="btn	btn-warning" type="button">数据同步 (中控)</button>
                    </div>
                </div>
                <div class="row">
                    <div class="span6"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="55" class="center">
                                        序号
                                    </th>
                                    <th class="center">
                                        员工编号
                                    </th>
                                    <th class="center">
                                        员工姓名
                                    </th>
                                    <th class="center">
                                        所属机构
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${empList}" var="emp" varStatus="status">
                                    <tr>
                                        <td class="center">
                                            ${status.index + 1}
                                        </td>
                                        <td class="center">
                                            ${emp.code}
                                        </td>
                                        <td class="center">
                                            ${emp.name}
                                        </td>
                                        <td class="center">
                                            ${emp.orgName}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>