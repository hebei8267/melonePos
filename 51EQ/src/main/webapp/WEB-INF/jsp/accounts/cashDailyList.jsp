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
            function cashDailyBtn(optDate) {
                $('#__cashDaily_confirm').modal({
                    backdrop : true,
                    keyboard : true,
                    show : true
                });
                $('#dailyOptDate').val(optDate);

            }

            $(function() {
                $("#confirmBtn").click(function() {
                	$("#listForm").attr("target", "_self");
                    $("#listForm").attr("action", "${sc_ctx}/cashDaily/confirm/" + $('#dailyOptDate').val());
                    $("#listForm").submit();
                });
            });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        <input type="hidden" id="dailyOptDate">
        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 日结信息</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <b>未日结信息列表</b>
                    </div>
                    <div class="span12" style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th class="center">
                                        日期
                                    </th>
                                    <th class="center">
                                        昨日<br>余额
                                    </th>
                                    <th class="center">
                                        金卡预存<br>现金
                                    </th>
                                    <th class="center">
                                        金卡预存<br>刷卡
                                    </th>
                                    <th class="center">
                                        现金<br>盈亏
                                    </th>
                                    <th class="center">
                                        当日<br>收现
                                    </th>
                                    <th class="center">
                                        刷卡<br>金额(单)
                                    </th>
                                    <th class="center">
                                        存款<br>金额
                                    </th>
                                    <th class="center">
                                        留存<br>金额
                                    </th>
                                    <th class="center">
                                        当日销售额<br>(普通 / 金卡[消费/返利] / 代金卷)
                                    </th>
                                    <th class="center">
                                        商场汇报<br>销售额
                                    </th>
                                    <th width="120">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${noCashDailyList}" var="noCashDaily">
                                    <tr>
                                        <td class="center">
                                            ${noCashDaily.optDateShow}
                                        </td>
                                        <td>
                                            ${noCashDaily.initAmt}
                                        </td>
                                        <td>
                                            ${noCashDaily.prePayCashAmt}
                                        </td>
                                        <td>
                                            ${noCashDaily.prePayCardAmt}
                                        </td>
                                        <td>
                                            ${noCashDaily.adjustAmt}
                                        </td>
                                        <td>
                                            ${noCashDaily.saleCashAmt}
                                        </td>
                                        <td>
                                            ${noCashDaily.cardAmt}
                                        </td>
                                        <td>
                                            ${noCashDaily.depositAmt}
                                        </td>
                                        <td>
                                            ${noCashDaily.retainedAmt}
                                        </td>
                                        <td class="center">
                                            ${noCashDaily.saleAmt} / ${noCashDaily.goldCardAmt} / ${noCashDaily.rebateAmt} / ${noCashDaily.couponValue}<br>(${noCashDaily.saleAmt + noCashDaily.goldCardAmt + noCashDaily.rebateAmt + noCashDaily.couponValue})
                                        </td>
                                        <td>
                                            ${noCashDaily.reportAmt}
                                        </td>
                                        <td>
                                        	<a href="${sc_ctx}/cashDaily/detail/${noCashDaily.optDate}" class="btn btn-warning" target="_blank"/>查看</a>
                                            <a href="javascript:cashDailyBtn('${noCashDaily.optDate}')" id="cashDailyBtn"	class="btn btn-danger">日结</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	noCashDailyList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="13" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                    <div class="span12 cash_daily"></div>
                    <div class="span9">
                        <b>已日结信息列表</b>
                    </div>
                    <div class="pan3 right_text">
                        <%String nowM =	DateUtils.getCurrentMonth(); %>
                        <%String lastM=	DateUtils.getNextMonthFormatDate(-1, "yyyy-MM"); %>(日结日期)
                        <a href="${sc_ctx}/cashDaily/list/<%=nowM	%>"><%=nowM	%></a> | <a	href="${sc_ctx}/cashDaily/list/<%=lastM %>"><%=lastM %></a>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th class="center">
                                        日期
                                    </th>
                                    <th class="center">
                                        昨日<br>余额
                                    </th>
                                    <th class="center">
                                        预存<br>现金
                                    </th>
                                    <th class="center">
                                        预存<br>刷卡
                                    </th>
                                    <th class="center">
                                        现金<br>盈亏
                                    </th>
                                    <th class="center">
                                        当日<br>收现
                                    </th>
                                    <th class="center">
                                        刷卡<br>金额(单)
                                    </th>
                                    <th class="center">
                                        存款<br>金额
                                    </th>
                                    <th class="center">
                                        留存<br>金额
                                    </th>
                                    <th class="center">
                                        当日销售额<br>(普通 / 金卡[消费/返利] / 代金卷)
                                    </th>
                                    <th class="center">
                                        商场汇报<br>销售额
                                    </th>
                                    <th width="55">
                                        
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty	cashDailyList}" >
                                    <tfoot>
                                        <tr>
                                            <td	colspan="13" class="rounded-foot-left">
                                                无记录信息
                                            </td>
                                        </tr>
                                    </tfoot>
                                </c:if>
                                <c:forEach items="${cashDailyList}" var="cashDaily">
                                    <tr>
                                        <td>
                                            ${cashDaily.optDateShow}
                                        </td>
                                        <td>
                                            ${cashDaily.initAmt}
                                        </td>
                                        <td>
                                            ${cashDaily.prePayCashAmt}
                                        </td>
                                        <td>
                                            ${cashDaily.prePayCardAmt}
                                        </td>
                                        <td>
                                            ${cashDaily.adjustAmt}
                                        </td>
                                        <td>
                                            ${cashDaily.saleCashAmt}
                                        </td>
                                        <td>
                                            ${cashDaily.cardAmt}
                                        </td>
                                        <td>
                                            ${cashDaily.depositAmt}
                                        </td>
                                        <td>
                                            ${cashDaily.retainedAmt}
                                        </td>
                                        <td class="center">
                                            ${cashDaily.saleAmt} / ${cashDaily.goldCardAmt} / ${cashDaily.rebateAmt} / ${cashDaily.couponValue}<br>(${cashDaily.saleAmt + cashDaily.goldCardAmt + cashDaily.rebateAmt + cashDaily.couponValue})
                                        </td>
                                        <td>
                                            ${cashDaily.reportAmt}
                                        </td>
                                        <td>
                                        	<a href="${sc_ctx}/cashDaily/detail/${cashDaily.optDate}" class="btn btn-warning" target="_blank"/>查看</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>

        <div class="modal hide fade  __model37" id="__cashDaily_confirm">
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4>系统消息</h4>
            </div>
            <div class="modal-body">
                <center>
                    <p class="error">
                        日结该笔销售流水信息吗？
                    </p>
                </center>
            </div>
            <div class="modal-footer">
                <a href="#" id="confirmBtn" class="btn btn-primary">确定</a>
                <a href="#" class="btn" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </body>
</html>