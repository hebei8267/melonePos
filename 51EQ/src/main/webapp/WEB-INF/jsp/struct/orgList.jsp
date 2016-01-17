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
                //-----------------------------------
                // 表单效验
                //-----------------------------------
                $("#listForm").validate({
                    rules : {
                        delBtn : {
                            requiredSelect : 'uuid'
                        }
                    }
                });
                //-----------------------------------
                // 全选/全部选
                //-----------------------------------
                $("#checkAll").click(function() {
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='uuid']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
                });

                //-----------------------------------
                // 删除按钮点击
                //-----------------------------------
                $("#delBtn").click(function() {
                    if ($("#listForm").valid()) {
                        $('#__del_confirm').modal({
                            backdrop : true,
                            keyboard : true,
                            show : true
                        });
                    }
                });
            });
            //-----------------------------------
            // 删除
            //-----------------------------------
            function _del_confirm() {
                var $subCheckBox = $("input[name='uuid']");
                var uuids = "";
                $.each($subCheckBox, function(index, _checkBox) {
                    if (_checkBox.checked) {
                        uuids += _checkBox.value + ",";
                    }
                });
                if (uuids.length > 0) {
                    uuids = uuids.substring(0, uuids.length - 1);
                }

                $("#uuids").val(uuids);
                
                $("#listForm").attr('target', '_self');
                $("#listForm").attr("action", "${sc_ctx}/organization/del");
                $("#listForm").submit();
            }
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
                            <h3>机构管理</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <a href="${sc_ctx}/organization/new"	class="btn btn-primary">新增</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th	class="center">
                                        机构编号
                                    </th>
                                    <th	class="center">
                                        机构编号(中控)
                                    </th>
                                    <th	class="center">
                                        机构资金编号(百威)
                                    </th>
                                    <th	class="center">
                                        机构名称
                                    </th>
                                    <th	class="center">
                                        机构地址
                                    </th>
                                    <th	class="center">
                                        门店品牌
                                    </th>
                                    <th	class="center">
                                        督导员
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${orgList}" var="org">
                                    <tr>
                                        <td	class="center">
                                            <c:if test="${org.uuid != 1}">
                                                <input type="checkbox" name="uuid" value="${org.uuid}">
                                                </input>
                                            </c:if>
                                        </td>
                                        <td	class="center">
                                            ${org.bwId}
                                        </td>
                                        <td	class="center">
                                            ${org.zkId}
                                        </td>
                                        <td	class="center">
                                            ${org.bwBranchNo}
                                        </td>
                                        <td	class="center">
                                            ${org.name}
                                        </td>
                                        <td class="right">
                                            ${org.orgAddShort}
                                        </td>
                                        <td class="center">
                                            ${org.brand}
                                        </td>
                                        <td class="center">
                                            ${org.mngUserName}
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/organization/edit/${org.uuid}" class="btn btn-warning"/>修改</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	orgList}" >
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