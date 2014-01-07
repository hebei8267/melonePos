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
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>库存信息(表格)</h3>
                        </legend>
                    </div>
                    <div class="span12" style="margin-top: -10px;">
                    	<h5>库存日期${maxOptDate}</h5>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th>
                                        机构
                                    </th>
                                    <th>
                                        库存数量(正)
                                    </th>
                                    <th>
                                        库存数量(负)
                                    </th>
                                    <th class="right">
                                        库存金额(正)
                                    </th>
                                    <th class="right">
                                        库存金额(负)
                                    </th>
                                    <th class="right">
                                        售价金额(正)
                                    </th>
                                    <th class="right">
                                        售价金额(负)
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${storeDayTotalList}" var="storeDayTotal">
                                    <tr>
                                        <td>
                                            ${storeDayTotal.orgName}
                                        </td>
                                        <td>
                                            ${storeDayTotal.stockTotalQty}
                                        </td>
                                        <td>
                                            ${storeDayTotal.stockTotalQty_Minus}
                                        </td>
                                        <td class="right">
                                            ${storeDayTotal.stockTotalAmt}
                                        </td>
                                        <td class="right">
                                            ${storeDayTotal.stockTotalAmt_Minus}
                                        </td>
                                        <td class="right">
                                            ${storeDayTotal.itemSaleTotalAmt}
                                        </td>
                                        <td class="right">
                                            ${storeDayTotal.itemSaleTotalAmt_Minus}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${!empty storeDayTotalList}" >
                                    <tr>
                                        <td>
                                            合计:
                                        </td>
                                        <td>
                                            ${totalStore.stockTotalQty}
                                        </td>
                                        <td>
                                            ${totalStore.stockTotalQty_Minus}
                                        </td>
                                        <td class="right">
                                            ${totalStore.stockTotalAmt}
                                        </td>
                                        <td class="right">
                                            ${totalStore.stockTotalAmt_Minus}
                                        </td>
                                        <td class="right">
                                            ${totalStore.itemSaleTotalAmt}
                                        </td>
                                        <td class="right">
                                            ${totalStore.itemSaleTotalAmt_Minus}
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                            <c:if test="${empty	storeDayTotalList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="7"	class="rounded-foot-left">
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