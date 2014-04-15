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
            ._warn0 {
                padding: 5px;
            }
            ._warn1 {
                background-color: #99FF33;
            }
            ._warn2 {
                background-color: #FFCC33;
            }
        </style>
        <script>
            $().ready(function() {

                //-----------------------------------
                // 表单效验
                //-----------------------------------
                $("#listForm").validate({
                    rules : {
                        name1 : {
                            required : true,
                            maxlength : 32
                        },
                        name2 : {
                            required : true,
                            maxlength : 32
                        },
                        name3 : {
                            required : true,
                            maxlength : 32
                        },
                        name4 : {
                            required : true,
                            maxlength : 32
                        },
                        name5 : {
                            required : true,
                            maxlength : 32
                        },
                        name6 : {
                            required : true,
                            maxlength : 32
                        },
                        name7 : {
                            required : true,
                            maxlength : 32
                        },
                        name8 : {
                            required : true,
                            maxlength : 32
                        },
                        name9 : {
                            required : true,
                            maxlength : 32
                        },
                        name10 : {
                            required : true,
                            maxlength : 32
                        }
                    }
                });

                $("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });
                    $("#listForm").attr("action", "${sc_ctx}/tmpEmployee/save");
                    $("#listForm").submit();
                });
            });

            function checkFun(index, value) {
                if (value == '0') {// 停用
                    $("#_ss" + index + '1').removeClass("_warn1");
                    $("#_ss" + index + '0').addClass("_warn2");
                } else {
                    $("#_ss" + index + '1').addClass("_warn1");
                    $("#_ss" + index + '0').removeClass("_warn2");
                }
            }
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post" class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 兼职信息 维护</h3>
                        </legend>
                    </div>

                    <div class="span6" style="padding-top: 10px; text-align: right;">
                        <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                        &nbsp;&nbsp;<a href="${sc_ctx}/tmpEmployee/list" class="btn btn-large">重置</a>
                    </div>
                </div>

                <div class="row">
                    <div class="span6" style="padding-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="55" class="center">
                                        序号
                                    </th>
                                    <th class="center">
                                        人员姓名
                                    </th>
                                    <th	width="175">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${empList}" var="emp" varStatus="status">
                                    <tr>
                                        <td class="center">
                                            <input type="hidden" name="uuid" value="${emp.uuid}">
                                            ${status.index + 1}
                                        </td>
                                        <td class="center">
                                            <input type="text" name="name${status.index + 1}" value="${emp.name}"/>
                                        </td>
                                        <td class="center">
                                            <c:if test="${emp.workFlg.equals('0')}">
                                                <input type="radio" name="workFlg[${status.index + 1}]" value="1" onchange="checkFun('${status.index }','1')">
                                                <span id="_ss${status.index }1" class="_warn0">启用</span>&nbsp;&nbsp;
                                                <input type="radio" name="workFlg[${status.index + 1}]" value="0" checked onchange="checkFun('${status.index }','0')">
                                                <span id="_ss${status.index }0" class="_warn0 _warn2">停用</span>
                                            </c:if>

                                            <c:if test="${emp.workFlg.equals('1')}">
                                                <input type="radio" name="workFlg[${status.index + 1}]" value="1" checked onchange="checkFun('${status.index }','1')">
                                                <span id="_ss${status.index }1" class="_warn0 _warn1">启用</span>&nbsp;&nbsp;
                                                <input type="radio" name="workFlg[${status.index + 1}]" value="0" onchange="checkFun('${status.index }','0')">
                                                <span id="_ss${status.index }0" class="_warn0">停用</span>
                                            </c:if>
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