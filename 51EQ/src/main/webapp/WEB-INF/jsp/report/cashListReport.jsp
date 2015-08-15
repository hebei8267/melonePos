<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="DEFAULT_RETAINED_AMT">
    ${DEFAULT_RETAINED_AMT}
</c:set>
<!DOCTYPE html>
<html>
    <head>
        <script>
            $(function() {
            	$("#listForm").validate({
                    rules : {
                    	optDateShow_start : {
                    		required : true,
                    		date : true                    		
                        },
                        optDateShow_end : {
                    		required : true,
                    		date : true,
                    		compareDate : "#optDateShow_start"
                        }
                    }
                });
            	
            	$('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
                });

                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/cashReport/search");
                    $("#listForm").submit();
                });
                
                $("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/cashReport/export");
                    $("#listForm").submit();
                });
                
                $("#exportBtn2").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/cashReport/export2");
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
                            <h3>销售信息(表格)</h3>
                        </legend>
                    </div>
                    <div class="span5">
                        <label class="control-label">销售日期 :</label>
                        <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                        ～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                    </div>
                    <div class="span7">
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
                        </select>&nbsp;&nbsp;
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                        <button	id="exportBtn" class="btn	btn-warning" type="button">数据导出 (明细)</button>
                        <button	id="exportBtn2" class="btn	btn-warning" type="button">数据导出 (合计)</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th class="center">
                                        机构
                                    </th>
                                    <th class="center">
                                        日结<br>日期
                                    </th>
                                    <th class="center">
                                        昨日<br>余额
                                    </th>
                                    <th class="center">
                                        金卡<br>预存<br>[现金]<br>[刷卡]
                                    </th>
                                    <th class="center">
                                        金卡<br>预存<br>百威<br>对账
                                    </th>
                                    <th class="center">
                                        现金<br>盈亏
                                    </th>
                                    <th class="center">
                                        当日<br>收现
                                    </th>
                                    <th class="center">
                                        刷卡<br>金额
                                    </th>
                                    <th class="center">
                                        存款<br>金额
                                    </th>
                                    <th class="center">
                                        当日销售额<br><br>普通 <br>[金卡消费]<br>[金卡返利]<br>代金卷
                                    </th>
                                    <th class="center">
                                        金卡<br>消费<br>百威<br>对账
                                    </th>
                                    <th class="center">
                                        代金卷<br>价值
                                    </th>
                                    <th class="center">
                                        销售额<br>合计
                                    </th>
                                    <th class="center">
                                        百威<br>对账
                                    </th>
                                    <th class="center">
                                        报商场<br>销售额
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${cashDailyList}" var="cashDaily">
                                    <tr>
                                        <td class="center">
                                        	${cashDaily.orgName}
                                        </td>
                                        <td class="center">
                                        	${cashDaily.optDate}
                                        </td>
                                        <td class="right">
                                        	${cashDaily.initAmt}
                                        </td>
                                        <td class="right">
                                            ${cashDaily.prePayCashAmt}<br>${cashDaily.prePayCardAmt}
                                        </td>
                                        <c:set var="_difference_prePayCardAmt" value="${cashDaily.prePayCashAmt + cashDaily.prePayCardAmt - cashDaily.prePayTotalAmt }" />
                                        <td  class="right" <c:if test="${(_difference_prePayCardAmt > BW_SALE_DIF_AMOUNT) || (_difference_prePayCardAmt < -BW_SALE_DIF_AMOUNT)}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                            ${cashDaily.prePayTotalAmt}
                                        </td>
                                        <td class="right">
                                        	${cashDaily.adjustAmt}
                                        </td>
                                        <td class="right">
                                        	${cashDaily.saleCashAmt}
                                        </td>
                                        <td class="right" <c:if test="${cashDaily.cardAmt != cashDaily.cardAmtBw}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                        	${cashDaily.cardAmt}
                                        </td>
                                        <td class="center">
                                        	<c:if test="${cashDaily.depositAmt > 0 && cashDaily.bankCheckFlg == 1}">
                                        		<i class="icon-ok" trigger="hover" data-toggle="popover" data-placement="right" title="已审核"></i>
                                        	</c:if>
                                        	<c:if test="${cashDaily.depositAmt > 0 && cashDaily.bankCheckFlg != 1}">
                                        	<i class="icon-exclamation-sign" trigger="hover" data-toggle="popover" data-placement="right" title="未审核"></i>
                                        	</c:if>
                                        	${cashDaily.depositAmt}
                                        </td>
                                        <c:set var="_difference" value="${cashDaily.saleAmt + cashDaily.goldCardAmt + cashDaily.rebateAmt +  cashDaily.couponValue - cashDaily.bwSaleAmt }" />
                                        <td class="right">
                                        	${cashDaily.saleAmt}<br>${cashDaily.goldCardAmt}<br>${cashDaily.rebateAmt}<br>${cashDaily.couponValue}
                                        </td>
                                        <c:set var="_difference_goldCarAmt" value="${cashDaily.goldCardAmt - cashDaily.goldCardTotalAmt }" />
                                        <td class="right" <c:if test="${(_difference_goldCarAmt > BW_SALE_DIF_AMOUNT) || (_difference_goldCarAmt < -BW_SALE_DIF_AMOUNT)}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                        	${cashDaily.goldCardTotalAmt}
                                        </td>
                                        <td class="right">
                                        	${cashDaily.couponCashValue}
                                        </td>
                                        
                                        <td class="right" <c:if test="${(_difference > BW_SALE_DIF_AMOUNT) || (_difference < -BW_SALE_DIF_AMOUNT)}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                        	${cashDaily.saleAmt + cashDaily.goldCardAmt + cashDaily.rebateAmt + cashDaily.couponValue}
                                        </td>
                                        
                                        <td class="right">
                                        	${cashDaily.bwSaleAmt}
                                        </td>
                                        <td class="right">
                                        	${cashDaily.reportAmt}
                                        </td>
                                        <td class="center">
                                            <a href="${sc_ctx}/cashReport/detail/${cashDaily.optDate}/${cashDaily.orgId}" target="_blank" class="btn btn-warning"/>详细</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${!empty cashDailyList}" >
                                    <tr>
                                        <td	colspan="3">
                                            合计:
                                        </td>
                                        <td>
                                            ${totalCashDaily.prePayCashAmt}<br>${totalCashDaily.prePayCardAmt}
                                        </td>
                                        <td>
                                        	${totalCashDaily.prePayTotalAmt}
                                        </td>
                                        <td class="center">
                                            ${totalCashDaily.adjustAmt}
                                        </td>
                                        <td class="center">
                                            ${totalCashDaily.saleCashAmt}
                                        </td>
                                        <td class="center">
                                            ${totalCashDaily.cardAmt}
                                        </td>
                                        <td class="center">
                                            ${totalCashDaily.depositAmt}
                                        </td>
                                        <td class="center">${totalCashDaily.saleAmt} / ${totalCashDaily.goldCardAmt} / ${totalCashDaily.rebateAmt} / ${totalCashDaily.couponValue}</td>
                                       	<td>
                                        	${totalCashDaily.goldCardTotalAmt}
                                        </td>
                                       	<td class="center">${totalCashDaily.couponCashValue}</td>
                                        <td class="center">
                                            ${totalCashDaily.saleAmt + totalCashDaily.goldCardAmt + totalCashDaily.rebateAmt + totalCashDaily.couponValue}
                                        </td>
                                        <td class="center">
                                        	${totalCashDaily.bwSaleAmt}
                                        </td>
                                        <td class="center">
                                        	${totalCashDaily.reportAmt}
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                            <c:if test="${empty	cashDailyList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="16" class="rounded-foot-left">
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