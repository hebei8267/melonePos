<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

                    $("#listForm").attr("action", "${sc_ctx}/cashReport/search");
                    $("#listForm").submit();
                });
                
                $("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/cashReport/export");
                    $("#listForm").submit();
                });
                
                $("#exportBtn2").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

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
                        </select>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                        <button	id="exportBtn" class="btn	btn-warning" type="button">数据导出 (明细)</button>
                        <button	id="exportBtn2" class="btn	btn-warning" type="button">数据导出 (合计)</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th>
                                        机构
                                    </th>
                                    <th>
                                        日结日期
                                    </th>
                                    <th>
                                        昨日余额
                                    </th>
                                    <th>
                                        现金盈亏
                                    </th>
                                    <th>
                                        当日收现
                                    </th>
                                    <th>
                                        刷卡金额(单)
                                    </th>
                                    <th>
                                        刷卡笔数
                                    </th>
                                    <th>
                                        存款金额
                                    </th>
                                    <th>
                                        留存金额
                                    </th>
                                    <th>
                                        当日销售额
                                    </th>
                                    <th>
                                        百威对账
                                    </th>
                                    <th>
                                        商场汇报销售额
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${cashDailyList}" var="cashDaily">
                                    <tr>
                                        <td>
                                        	${cashDaily.orgName}
                                        </td>
                                        <td>
                                        	${cashDaily.optDateShow}
                                        </td>
                                        <td>
                                        	${cashDaily.initAmt}
                                        </td>
                                        <td>
                                        	${cashDaily.adjustAmt}
                                        </td>
                                        <td>
                                        	${cashDaily.saleCashAmt}
                                        </td>
                                        <td <c:if test="${cashDaily.cardAmt != cashDaily.cardAmtBw}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                        	${cashDaily.cardAmt}
                                        </td>
                                        <td>
                                        	${cashDaily.cardNum}
                                        </td>
                                        <td>
                                        	<c:if test="${cashDaily.depositAmt > 0 && cashDaily.bankCheckFlg == 1}">
                                        		<i class="icon-ok" trigger="hover" data-toggle="popover" data-placement="right" title="已审核"></i>
                                        	</c:if>
                                        	<c:if test="${cashDaily.depositAmt > 0 && cashDaily.bankCheckFlg != 1}">
                                        	<i class="icon-exclamation-sign" trigger="hover" data-toggle="popover" data-placement="right" title="未审核"></i>
                                        	</c:if>
                                        	${cashDaily.depositAmt}
                                        </td>
                                        <td <c:if test="${cashDaily.retainedAmt > DEFAULT_RETAINED_AMT}">style="background-color:#FFDEAD;color:#00000"</c:if>>
                                        	${cashDaily.retainedAmt}
                                        </td>
                                        <c:set var="_difference" value="${cashDaily.saleAmt - cashDaily.bwSaleAmt }" />
                                        <td <c:if test="${(_difference > BW_SALE_DIF_AMOUNT) || (_difference < -BW_SALE_DIF_AMOUNT)}">style="background-color:#FF6633;color:#FFFFFF"</c:if>>
                                        	${cashDaily.saleAmt}
                                        </td>
                                        <td>
                                        	${cashDaily.bwSaleAmt}
                                        </td>
                                        <td>
                                        	${cashDaily.reportAmt}
                                        </td>
                                        <td>
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
                                            ${totalCashDaily.adjustAmt}
                                        </td>
                                        <td>
                                            ${totalCashDaily.saleCashAmt}
                                        </td>
                                        <td>
                                            ${totalCashDaily.cardAmt}
                                        </td>
                                        <td>
                                            ${totalCashDaily.cardNum}
                                        </td>
                                        <td>
                                            ${totalCashDaily.depositAmt}
                                        </td>
                                        <td>
                                        </td>
                                        <td>
                                            ${totalCashDaily.saleAmt}
                                        </td>
                                        <td>
                                        	${totalCashDaily.bwSaleAmt}
                                        </td>
                                        <td>
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
                                        <td	colspan="13"	class="rounded-foot-left">
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