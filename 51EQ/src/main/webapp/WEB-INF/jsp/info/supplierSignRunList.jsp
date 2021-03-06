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
<!DOCTYPE html>
<html>
    <head>
    	<style>
        .myTip {
        	color: #fbb450;
        }
        .myTip2 {
        	color: #0044cc;
        }
    	</style>
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
        		$("#searchForm").attr('target', '_self');
                $("#searchForm").attr("action", "${sc_ctx}/supplierSignRun/show");
                $("#searchForm").submit();
            });
        	
        	$(".label").each(function(){
        		$(this).popover({trigger:'hover', html:'true'});
        	});
        	
        	$("#exportBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#searchForm").attr('target', '_self');
                $("#searchForm").attr("action", "${sc_ctx}/supplierSignRun/export");
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
                            <h3>供应商(挂账)结算进度表</h3>
                        </legend>
                    </div>
                    <div class="span3">
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
                    </div>
                    <div class="span9">
                    	<label class="control-label">供应商 : </label>
                        <select name="supplierBwId" class="input-medium">
                        	<c:forEach items="${supList}" var="sup">
	                        	<c:if test="${sup.key == supplierBwId}">
	                                <option value="${sup.key }" selected>${sup.value }</option>
	                            </c:if>
	                            <c:if test="${sup.key != supplierBwId}">
	                                <option value="${sup.key }">${sup.value }</option>
	                            </c:if>
                            </c:forEach>
                        </select>
                        &nbsp;&nbsp;
                        <button	id="showBtn" class="btn	btn-primary" type="button">显示</button>
                        <button	id="exportBtn" class="btn btn-warning" type="button">数据导出</button>
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
                            	<c:set var="gzTotal" value="0"	/>
                            	<c:forEach items="${supSignRunList}" var="supplier" varStatus="status">
                            	<tr>
                            		<td rowspan="6" class="center">
                            		${supplier.supplierName}
                            		<c:if test="${!empty supplier.lastSupplierSignRun.totalCheckAmt}" >
	                            		<br/><br/>
	                            		对账金额(合计): ${supplier.lastSupplierSignRun.totalCheckAmt}
                            		</c:if>
                            		<c:if test="${!empty supplier.lastSupplierSignRun.totalPayAmt}" >
	                            		<br/>
	                            		付款金额(合计): ${supplier.lastSupplierSignRun.totalPayAmt}
                            		</c:if>
                            		<c:if test="${!empty supplier.lastSupplierSignRun.lastPaymentDate}" >
	                            		<br/>
	                            		<fmt:parseDate value="${supplier.lastSupplierSignRun.lastPaymentDate}" var="lastPaymentDate" pattern="yyyyMMdd" />
	                            		最近付款时间: <span class="myTip2"><fmt:formatDate pattern="yyyy/MM/dd" value="${lastPaymentDate}" /></span>
                            		</c:if>
                            		<c:if test="${!empty supplier.lastSupplierSignRun.totalCheckAmt || !empty supplier.lastSupplierSignRun.totalPayAmt}" >
	                            		<br/>
	                            		挂账金额: <span class="myTip2">${supplier.lastSupplierSignRun.totalCheckAmt - supplier.lastSupplierSignRun.totalPayAmt}</span>
	                            		<c:set var="gzTotal" value="${gzTotal + supplier.lastSupplierSignRun.totalCheckAmt - supplier.lastSupplierSignRun.totalPayAmt}"/>
                            		</c:if>
                            		</td>
                            		<td class="center">赊购挂账</td>
                            		<c:forEach items="${supplier.loanList}" var="load">
                            		<td class="center">
                            		<c:if test="${!empty load.loanFlg}" >
                            		<span class="label label-warning" data-toggle="popover"><i class="icon-inbox icon-white"></i></span>
                            		</c:if>
                            		</td>
                            		</c:forEach>
                            		<td rowspan="6">
                            		<a href="${sc_ctx}/supplierSignRun/editInit/${supplier.supplierBwId}/${optDateY}" class="btn btn-warning" />编辑</a>
                            		</td>
                            	</tr>
                            	<tr>
                            		<td class="center">对账通知</td>
                            		<c:forEach items="${supplier.noticeList}" var="notice">
                            		<td class="center">
                            		<c:if test="${!empty notice.noticeMode}" >
                            		<fmt:parseDate value="${notice.checkNoticeDate}" var="checkNoticeDate" pattern="yyyyMMdd" />
                            		<span class="label label-warning" title="" data-content='通知时间: <span class="myTip"><fmt:formatDate pattern="yyyy/MM/dd" value="${checkNoticeDate}" /></span> <br/> 通知方式: <c:if test="${notice.noticeMode == 1}" ><span class="myTip">电话</span></c:if><c:if test="${notice.noticeMode == 2}" ><span class="myTip">网络</span></c:if><c:if test="${notice.noticeMode == 3}" ><span class="myTip">捎信</span></c:if> <br/> 备注: <span class="myTip">${notice.descTxt}</span>' data-original-title="对账通知"><i class="icon-envelope icon-white"></i></span>
                            		</c:if>
                            		</td>
                            		</c:forEach>
                            	</tr>
                            	<tr>
                            		<td class="center">对账完成</td>
                            		<c:forEach items="${supplier.checkSuccessList}" var="checkSuccess">
                            		<td class="center">
                            		<c:if test="${!empty checkSuccess.checkDate}" >
                            		<fmt:parseDate value="${checkSuccess.checkDate}" var="checkDate" pattern="yyyyMMdd" />
                            		<span class="label label-warning" title="" data-content='完成时间: <span class="myTip"><fmt:formatDate pattern="yyyy/MM/dd" value="${checkDate}" /></span> <br/> 对账金额: <span class="myTip">${checkSuccess.checkAmt}</span> <br/> 备注: <span class="myTip">${checkSuccess.descTxt}</span>' data-original-title="对账完成"><i class="icon-check icon-white"></i></span>
                            		</c:if>
                            		</td>
                            		</c:forEach>
                            	</tr>
                            	<tr>
                            		<td class="center">结算付款</td>
                            		<c:forEach items="${supplier.payList}" var="pay">
                            		<td class="center">
                            		<c:if test="${!empty pay.paymentDate}" >
                            		<fmt:parseDate value="${pay.paymentDate}" var="paymentDate" pattern="yyyyMMdd" />
                            		<span class="label label-warning" title="" data-content='付款时间: <span class="myTip"><fmt:formatDate pattern="yyyy/MM/dd" value="${paymentDate}" /></span> <br/> 付款金额: <span class="myTip">${pay.paymentAmt}</span> <br/> 备注: <span class="myTip">${pay.descTxt}</span>' data-original-title="结算付款"><i class="icon-hand-up icon-white"></i></span>
                            		</c:if>
                            		</td>
                            		</c:forEach>
                            	</tr>
                            	<tr>
                            		<td class="center">退货申请</td>
                            		<c:forEach items="${supplier.appList}" var="app">
                            		<td class="center">
                            		<c:if test="${!empty app.appDate}" >
                            		<fmt:parseDate value="${app.appDate}" var="appDate" pattern="yyyyMMdd" />
                            		<span class="label label-warning" title="" data-content='申请时间: <span class="myTip"><fmt:formatDate pattern="yyyy/MM/dd" value="${appDate}" /></span> <br/> 申请金额: <span class="myTip">${app.appAmt}</span> <br/> 备注: <span class="myTip">${app.descTxt}</span>' data-original-title="退货申请"><i class="icon-shopping-cart icon-white"></i></span>
                            		</c:if>
                            		</td>
                            		</c:forEach>
                            	</tr>
                            	<tr>
                            		<td class="center">退货确认</td>
                            		<c:forEach items="${supplier.confirmList}" var="confirm">
                            		<td class="center">
                            		<c:if test="${!empty confirm.confirmDate}" >
                            		<fmt:parseDate value="${confirm.confirmDate}" var="confirmDate" pattern="yyyyMMdd" />
                            		<span class="label label-warning" title="" data-content='确认时间: <span class="myTip"><fmt:formatDate pattern="yyyy/MM/dd" value="${confirmDate}" /></span> <br/> 申请金额: <span class="myTip">${confirm.confirmAmt}</span> <br/> 备注: <span class="myTip">${confirm.descTxt}</span>' data-original-title="退货确认"><i class="icon-eye-open icon-white"></i></span>
                            		</c:if>
                            		</td>
                            		</c:forEach>
                            	</tr>
                            	</c:forEach>
                            	<tr>
                            		<td class="center">挂账合计金额：${gzTotal}
                            		</td>
                            		<td colspan="14">
                            		</td>
                            	</tr>
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