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

                    $("#listForm").attr("action", "${sc_ctx}/cardReport/search");
                    $("#listForm").submit();
                });
                
                $("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/cardReport/export");
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
                            <h3>刷卡信息</h3>
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
                        <button	id="exportBtn" class="btn	btn-warning" type="button">数据导出</button>
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
                                        刷卡金额(单据)
                                    </th>
                                    <th>
                                        刷卡金额(百威)
                                    </th>
                                    <th>
                                        刷卡笔数
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
                                        <td <c:if test="${cashDaily.cardAmt != cashDaily.cardAmtBw}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                        	${cashDaily.cardAmt}
                                        </td>
                                        <td <c:if test="${cashDaily.cardAmt != cashDaily.cardAmtBw}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                        	${cashDaily.cardAmtBw}
                                        </td>
                                        <td>
                                        	${cashDaily.cardNum}
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/cashReport/detail/${cashDaily.optDate}/${cashDaily.orgId}" target="_blank" class="btn btn-warning"/>详细</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${!empty cashDailyList}" >
                                    <tr>
                                        <td	colspan="2">
                                            合计:
                                        </td>
                                        <td>
                                            ${totalCashDaily.cardAmt}
                                        </td>
                                        <td>
                                            ${totalCashDaily.cardAmtBw}
                                        </td>
                                        <td	colspan="2"></td>
                                    </tr>
                                </c:if>
                            </tbody>
                            <c:if test="${empty	cashDailyList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="6"	class="rounded-foot-left">
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