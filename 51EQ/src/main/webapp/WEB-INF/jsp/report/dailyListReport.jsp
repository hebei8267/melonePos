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
	                	orgId : {
	                		required : true
	                    }
	                }
	            });
	        	
	            $('#optDateShow').datepicker({
	            	format : 'yyyy-mm-dd'
	            });
	            
	            $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/dailyReport/search");
                    $("#listForm").submit();
                });
	        });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <form method="post"	class="form-inline"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>日结信息</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <label class="control-label">日结日期 :</label>
                        <input id="optDateShow" name="optDateShow" type="text" class="input-medium" value="${optDateShow }"/>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12" style="margin-top: 10px;">
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
                                        头日余额
                                    </th>
                                    <th>
                                        当日收现
                                    </th>
                                    <th>
                                        刷卡金额(单)
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
                                        <c:if test="${empty	cashDaily.optDate}" >
                                        <td colspan="6" class="rounded-foot-left">
                                            未日结
                                        </td>
                                        </c:if>
                                        <c:if test="${!empty cashDaily.optDate}" >
                                        <td>
                                            ${cashDaily.initAmt}
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
                                        <td <c:if test="${cashDaily.retainedAmt > DEFAULT_RETAINED_AMT}">style="background-color:#FF6633;color:#FFFFFF"</c:if>>
                                            ${cashDaily.retainedAmt}
                                        </td>
                                        <td>
                                            ${cashDaily.saleAmt}
                                        </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	cashDailyList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="8" class="rounded-foot-left">
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