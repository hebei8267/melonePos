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
    <head></head>
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
                    <div class="offset9 span3 right_text">
                        <%String nowM =	DateUtils.getCurrentMonth(); %>
                        <%String lastM=	DateUtils.getNextMonthFormatDate(-1, "yyyy-MM"); %>(开单日期)
                        <a href="${sc_ctx}/storeRunAudit/list/<%=nowM	%>"><%=nowM	%></a> | <a	href="${sc_ctx}/storeRunAudit/list/<%=lastM %>"><%=lastM %></a>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
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
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${storeRunList}" var="storeRun">
                                    <tr>
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
                                            <c:if test="${storeRun.auditFlg == 'false'}">
                                                <a href="${sc_ctx}/storeRunAudit/confirmInit/${storeRun.uuid}" class="btn btn-warning"/>审核</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${!empty storeRunList}" >
                                    <tr>
                                        <td	colspan="6">
                                            合计:
                                        </td>
                                        <td>
                                            ${totalStoreRun.recordAmt}
                                        </td>
                                        <td>
                                            ${totalStoreRun.optAmt}
                                        </td>
                                        <td	colspan="2"></td>
                                    </tr>
                                </c:if>
                            </tbody>
                            <c:if test="${empty	storeRunList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="10"	class="rounded-foot-left">
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