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
        	$("#searchForm").validate({
                rules : {
                	optDateY : {
                		required : true                 		
                    }
                }
            });
        	$("#showBtn").click(function() {
                $("#searchForm").attr("action", "${sc_ctx}/supplierSignRun/show");
                $("#searchForm").submit();
            });
        });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <form method="post"	class="form-inline"	id="searchForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>挂账供应商结算进度表</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <label class="control-label">年份 : </label>
                        <select name="optDateY" class="input-medium">
                        	<c:forEach items="${yearList}" var="year">
	                        	<c:if test="${year.key == optDateY}">
	                                <option value="${year.key }" selected>${year.value }</option>
	                            </c:if>
	                            <c:if test="${year.key != optDateY}">
	                                <option value="${year.key }">${year.value }</option>
	                            </c:if>
                            </c:forEach>
                        </select>
                        &nbsp;
                        <button	id="showBtn" class="btn	btn-primary" type="button">显示</button>
                    </div>
                    
                    <c:if test="${showFlg}" >
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th class="center">供应商</th>
									<th width="90" class="center">分类</th>
						            <th width="50" class="center">01月</th>
						            <th width="50" class="center">02月</th>
						            <th width="50" class="center">03月</th>
						            <th width="50" class="center">04月</th>
						            <th width="50" class="center">05月</th>
						            <th width="50" class="center">06月</th>
						            <th width="50" class="center">07月</th>
						            <th width="50" class="center">08月</th>
						            <th width="50" class="center">09月</th>
						            <th width="50" class="center">10月</th>
						            <th width="50" class="center">11月</th>
						            <th width="50" class="center">12月</th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${supSignRunList}" var="supplier" varStatus="status">
                            	<tr>
                            		<td rowspan="6" class="center">
                            		${supplier.supplierName}
                            		</td>
                            		<td class="center">赊购挂账</td>
                            		<c:forEach items="${supplier.loanList}" var="load">
                            		<td></td>
                            		</c:forEach>
                            		<td rowspan="6">
                            		<a href="${sc_ctx}/supplierSignRun/${supplier.supplierBwId}/${optDateY}" class="btn btn-warning" target="_blank"/>编辑</a>
                            		</td>
                            	</tr>
                            	<tr>
                            		<td class="center">对账通知</td>
                            		<c:forEach items="${supplier.noticeList}" var="notice">
                            		<td></td>
                            		</c:forEach>
                            	</tr>
                            	<tr>
                            		<td class="center">对账完成</td>
                            		<c:forEach items="${supplier.checkSuccessList}" var="checkSuccess">
                            		<td></td>
                            		</c:forEach>
                            	</tr>
                            	<tr>
                            		<td class="center">结算付款</td>
                            		<c:forEach items="${supplier.payList}" var="pay">
                            		<td></td>
                            		</c:forEach>
                            	</tr>
                            	<tr>
                            		<td class="center">退货申请</td>
                            		<c:forEach items="${supplier.appList}" var="app">
                            		<td></td>
                            		</c:forEach>
                            	</tr>
                            	<tr>
                            		<td class="center">退货确认</td>
                            		<c:forEach items="${supplier.confirmList}" var="confirm">
                            		<td></td>
                            		</c:forEach>
                            	</tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	supSignRunList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="15" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                    </c:if>
                </div>
            </form>
        </div>
    </body>
</html>