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
    	<style type="text/css">
    	.cash_daily {
			border-bottom: 3px solid #F89406;
			margin-top: 20px;
			margin-bottom: 20px;
		}
    	</style>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>日销售信息对比</h3><h4>当前机构：[${sessionScope.__SESSION_USER_INFO.orgName}]</h4>
                        </legend>
                    </div>
                    
                	<fmt:parseDate value="${optDate1}" var="_optDate1" pattern="yyyyMMdd" />
                	<div class="span12"><h4>销售日期 : <fmt:formatDate pattern="yyyy-MM-dd" value="${_optDate1}" /></h4></div>
					<div class="span12">
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th class="center">机构</th>
									<th class="center">当日销售</th>
									<th class="center">截止金额</th>
									<th class="center">截止天数</th>
									<th class="center">本月天数</th>
									<th class="center">预计本月销售</th>
									<th class="center">去年同期销售</th>
									<th class="center">增长额</th>
									<th class="center">增长率</th>
									<th class="center">日需销售金额</th>
									<th class="center">增长率排名</th>
								</tr>			                			
							</thead>
							<tbody>
								<c:forEach items="${optDate1SalesList}" var="salesDayTotal">
								<c:if test="${!empty salesDayTotal}" >
								<tr>
									<!-- 本店 -->
									<c:if test="${sessionScope.__SESSION_USER_INFO.orgId == salesDayTotal.orgId}" >
									<td class="center">${salesDayTotal.orgId}</td>
									<td class="right">${salesDayTotal.posAmt}</td>
									<td class="right">${salesDayTotal.posAmtByNow}</td>
									<td class="center">${salesDayTotal.nowDays}</td>
									<td class="center">${salesDayTotal.monthDays}</td>
									<td class="right">${salesDayTotal.expMonthPosAmt}</td>
									<td class="right">${salesDayTotal.preYearMonthPosAmt}</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtIncrease > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtIncrease < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtIncrease}</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtRate > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtRate < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtRate}%</td>
									<td class="right">${salesDayTotal.dayNeededPosAmt}</td>
									<td class="center">${salesDayTotal.ranking}</td>
									</c:if>
									
									<!-- 非本店 -->
									<c:if test="${sessionScope.__SESSION_USER_INFO.orgId != salesDayTotal.orgId}" >
									<td class="center">${salesDayTotal.orgId}</td>
									<td class="right">*******</td>
									<td class="right">*******</td>
									<td class="center">${salesDayTotal.nowDays}</td>
									<td class="center">${salesDayTotal.monthDays}</td>
									<td class="right">*******</td>
									<td class="right">*******</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtIncrease > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtIncrease < 0}" >style="background-color: #00FF00"</c:if>>*******</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtRate > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtRate < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtRate}%</td>
									<td class="right">*******</td>
									<td class="center">${salesDayTotal.ranking}</td>
									</c:if>									
							 	</tr>
							 	</c:if>
								</c:forEach>
							</tbody>
							<c:if test="${empty	optDate1SalesList}" >
							<tfoot>
						   		<tr>
						      		<td	colspan="11" class="rounded-foot-left">
									无记录信息
						       		</td>
						  		</tr>
							</tfoot>
							</c:if>
						</table>
            		</div>
            		<div class="span12 cash_daily"></div>
            		
            		
            		
            		
            		<fmt:parseDate value="${optDate2}" var="_optDate2" pattern="yyyyMMdd" />
                	<div class="span12"><h4>销售日期 : <fmt:formatDate pattern="yyyy-MM-dd" value="${_optDate2}" /></h4></div>
					<div class="span12">
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th class="center">机构</th>
									<th class="center">当日销售</th>
									<th class="center">截止金额</th>
									<th class="center">截止天数</th>
									<th class="center">本月天数</th>
									<th class="center">预计本月销售</th>
									<th class="center">去年同期销售</th>
									<th class="center">增长额</th>
									<th class="center">增长率</th>
									<th class="center">日需销售金额</th>
									<th class="center">增长率排名</th>
								</tr>			                			
							</thead>
							<tbody>
								<c:forEach items="${optDate2SalesList}" var="salesDayTotal">
								<c:if test="${!empty salesDayTotal}" >
								<tr>
									<!-- 本店 -->
									<c:if test="${sessionScope.__SESSION_USER_INFO.orgId == salesDayTotal.orgId}" >
									<td class="center">${salesDayTotal.orgId}</td>
									<td class="right">${salesDayTotal.posAmt}</td>
									<td class="right">${salesDayTotal.posAmtByNow}</td>
									<td class="center">${salesDayTotal.nowDays}</td>
									<td class="center">${salesDayTotal.monthDays}</td>
									<td class="right">${salesDayTotal.expMonthPosAmt}</td>
									<td class="right">${salesDayTotal.preYearMonthPosAmt}</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtIncrease > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtIncrease < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtIncrease}</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtRate > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtRate < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtRate}%</td>
									<td class="right">${salesDayTotal.dayNeededPosAmt}</td>
									<td class="center">${salesDayTotal.ranking}</td>
									</c:if>
									
									<!-- 非本店 -->
									<c:if test="${sessionScope.__SESSION_USER_INFO.orgId != salesDayTotal.orgId}" >
									<td class="center">${salesDayTotal.orgId}</td>
									<td class="right">*******</td>
									<td class="right">*******</td>
									<td class="center">${salesDayTotal.nowDays}</td>
									<td class="center">${salesDayTotal.monthDays}</td>
									<td class="right">*******</td>
									<td class="right">*******</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtIncrease > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtIncrease < 0}" >style="background-color: #00FF00"</c:if>>*******</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtRate > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtRate < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtRate}%</td>
									<td class="right">*******</td>
									<td class="center">${salesDayTotal.ranking}</td>
									</c:if>									
							 	</tr>
							 	</c:if>
								</c:forEach>
							</tbody>
							<c:if test="${empty	optDate2SalesList}" >
							<tfoot>
						   		<tr>
						      		<td	colspan="11" class="rounded-foot-left">
									无记录信息
						       		</td>
						  		</tr>
							</tfoot>
							</c:if>
						</table>
            		</div>
            		<div class="span12 cash_daily"></div>
            		
            		
            		
            		
            		<fmt:parseDate value="${optDate3}" var="_optDate3" pattern="yyyyMMdd" />
                	<div class="span12"><h4>销售日期 : <fmt:formatDate pattern="yyyy-MM-dd" value="${_optDate3}" /></h4></div>
					<div class="span12">
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th class="center">机构</th>
									<th class="center">当日销售</th>
									<th class="center">截止金额</th>
									<th class="center">截止天数</th>
									<th class="center">本月天数</th>
									<th class="center">预计本月销售</th>
									<th class="center">去年同期销售</th>
									<th class="center">增长额</th>
									<th class="center">增长率</th>
									<th class="center">日需销售金额</th>
									<th class="center">增长率排名</th>
								</tr>			                			
							</thead>
							<tbody>
								<c:forEach items="${optDate3SalesList}" var="salesDayTotal">
								<c:if test="${!empty salesDayTotal}" >
								<tr>
									<!-- 本店 -->
									<c:if test="${sessionScope.__SESSION_USER_INFO.orgId == salesDayTotal.orgId}" >
									<td class="center">${salesDayTotal.orgId}</td>
									<td class="right">${salesDayTotal.posAmt}</td>
									<td class="right">${salesDayTotal.posAmtByNow}</td>
									<td class="center">${salesDayTotal.nowDays}</td>
									<td class="center">${salesDayTotal.monthDays}</td>
									<td class="right">${salesDayTotal.expMonthPosAmt}</td>
									<td class="right">${salesDayTotal.preYearMonthPosAmt}</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtIncrease > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtIncrease < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtIncrease}</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtRate > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtRate < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtRate}%</td>
									<td class="right">${salesDayTotal.dayNeededPosAmt}</td>
									<td class="center">${salesDayTotal.ranking}</td>
									</c:if>
									
									<!-- 非本店 -->
									<c:if test="${sessionScope.__SESSION_USER_INFO.orgId != salesDayTotal.orgId}" >
									<td class="center">${salesDayTotal.orgId}</td>
									<td class="right">*******</td>
									<td class="right">*******</td>
									<td class="center">${salesDayTotal.nowDays}</td>
									<td class="center">${salesDayTotal.monthDays}</td>
									<td class="right">*******</td>
									<td class="right">*******</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtIncrease > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtIncrease < 0}" >style="background-color: #00FF00"</c:if>>*******</td>
									<td class="right" <c:if test="${salesDayTotal.posAmtRate > 0}" >style="background-color: #FF0000"</c:if><c:if test="${salesDayTotal.posAmtRate < 0}" >style="background-color: #00FF00"</c:if>>${salesDayTotal.posAmtRate}%</td>
									<td class="right">*******</td>
									<td class="center">${salesDayTotal.ranking}</td>
									</c:if>
							 	</tr>
							 	</c:if>
								</c:forEach>
							</tbody>
							<c:if test="${empty	optDate3SalesList}" >
							<tfoot>
						   		<tr>
						      		<td	colspan="11" class="rounded-foot-left">
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