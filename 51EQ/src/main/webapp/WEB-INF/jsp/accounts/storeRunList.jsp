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
                    $('input[name="uuid"]').attr("checked", this.checked);
                });
                var $subCheckBox = $("input[name='uuid']");
                $subCheckBox.click(function() {
                    $("#checkAll").attr("checked", $subCheckBox.length == $("input[name='uuid']:checked").length ? true : false);
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
                
                $("#listForm").attr("target", "_self");
                $("#listForm").attr("action", "${sc_ctx}/storeRun/del");
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
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 入库信息</h3>
                        </legend>
                    </div>
                    <div class="span9">
                        <a href="${sc_ctx}/storeRun/new"	class="btn btn-primary">新增</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
                    <div class="span3 right_text">
                        <%String nowM =	DateUtils.getCurrentMonth(); %>
                        <%String lastM=	DateUtils.getNextMonthFormatDate(-1, "yyyy-MM"); %>(开单日期)
                        <a href="${sc_ctx}/storeRun/list/<%=nowM	%>"><%=nowM	%></a> | <a	href="${sc_ctx}/storeRun/list/<%=lastM %>"><%=lastM %></a>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th>
                                        入库单号
                                    </th>
                                    <th>
                                        供应商
                                    </th>
                                    <th>
                                        入库类型
                                    </th>
                                    <th>
                                        开单日期
                                    </th>
                                    <th>
                                        入库日期
                                    </th>
                                    <th>
                                        统筹日期
                                    </th>
                                    <th>
                                        开单金额
                                    </th>
                                    <th>
                                        入库金额
                                    </th>
                                    <th>
                                        入库人
                                    </th>
                                    <th>
                                        店长审核
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${storeRunList}" var="storeRun">
                                    <tr>
                                        <td	class="center">
                                            <c:if test="${storeRun.editFlg == 'true'	}">
                                                <input type="checkbox" name="uuid" value="${storeRun.uuid}">
                                                </input>
                                            </c:if>
                                        </td>
                                        <td>
                                            ${storeRun.recordNo}
                                        </td>
                                        <td>
                                            ${storeRun.supplierName}
                                        </td>
                                        <td>
                                            <c:if test="${storeRun.storeType == 'A'}">
                                                挂账采购
                                            </c:if>
                                            <c:if test="${storeRun.storeType == 'B'}">
                                                现结采购
                                            </c:if>
                                            <c:if test="${storeRun.storeType == 'C'}">
                                                货商补欠
                                            </c:if>
                                        </td>
                                        <td>
                                            ${storeRun.recordDateShow}
                                        </td>
                                        <td>
                                            ${storeRun.intoDateShow}
                                        </td>
                                        <td>
                                            ${storeRun.planDateShow}
                                        </td>
                                        <td>
                                            ${storeRun.recordAmt}
                                        </td>
                                        <td>
                                            ${storeRun.optAmt}
                                        </td>
                                        <td>
                                            ${storeRun.optPerName}
                                        </td>
                                        <td>
                                            <c:if test="${storeRun.auditFlg == 'true'}">
                                                已审核
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${storeRun.editFlg == 'true'}">
                                                <a href="${sc_ctx}/storeRun/edit/${storeRun.uuid}" class="btn btn-warning"/>修改</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${!empty storeRunList}" >
                                    <tr>
                                        <td	colspan="7">
                                            合计:
                                        </td>
                                        <td>
                                            ${totalStoreRun.recordAmt}
                                        </td>
                                        <td>
                                            ${totalStoreRun.optAmt}
                                        </td>
                                        <td	colspan="3"></td>
                                    </tr>
                                </c:if>
                            </tbody>
                            <c:if test="${empty	storeRunList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="12" class="rounded-foot-left">
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