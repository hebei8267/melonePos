<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="em_ctx">
    ${ctx}/em
</c:set>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
           	<div class="row">
	            <div class="span12">
	                <legend>
	                    <h3>店铺周销售对比</h3>
	                </legend>
	            </div>
	            
	            <div class="span12"	style="margin-top: 10px;">
	            	<table class="table	table-striped table-bordered table-condensed mytable">
	                   	<thead>
	                    	<tr>
	                    		<th rowspan="2" class="center">级别编号</th>
	                    		<th rowspan="2" class="center">门店编号</th>
	                    		<th colspan="2" class="center" style="background-image: linear-gradient(to bottom,#fbb450,#f89406);">${optDateWSection}</th>
	                    		<th colspan="2" class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">${optDateWSection_Last}</th>
	                    		<th rowspan="2" class="center">环比趋势</th>
	                    	</tr>
	                    	<tr>
	                    		<th class="center" style="background-image: linear-gradient(to bottom,#fbb450,#f89406);">销售金额</th>
	                    		<th class="center" style="background-image: linear-gradient(to bottom,#fbb450,#f89406);">排名</th>
	                    		<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">销售金额</th>
	                    		<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">排名</th>
	                    	</tr>
	                    </thead>
	                    <tbody>
	                    	<c:forEach items="${orgWeekSalesRankingList}" var="orgWeekSalesRanking">
	                    	<tr>
	                    		<td class="center">${orgWeekSalesRanking.level}</td>
	                    		<td class="center">${orgWeekSalesRanking.orgId}</td>
	                    		<td class="right">${orgWeekSalesRanking.saleCashAmt}</td>
	                    		<td class="center">${orgWeekSalesRanking.level}${orgWeekSalesRanking.rankingLevel}</td>
	                    		<td class="right">${orgWeekSalesRanking.saleCashAmt_Last}</td>
	                    		<td class="center">${orgWeekSalesRanking.level_Last}${orgWeekSalesRanking.rankingLevel_Last}</td>
	                    		<c:if test="${orgWeekSalesRanking.gap == 0}" ><td class="center">-</td></c:if>
	                    		<c:if test="${orgWeekSalesRanking.gap > 0}" ><td class="center font2" style="color : #FF0000">↑${orgWeekSalesRanking.gap}</td></c:if>
	                    		<c:if test="${orgWeekSalesRanking.gap < 0}" ><td class="center font2" style="color : #5bc0de">↓${orgWeekSalesRanking.gap}</td></c:if>
	                    	</tr>
	                    	</c:forEach>
	                    </tbody>
	                    
	                    <c:if test="${empty	orgWeekSalesRankingList}" >
                        <tfoot>
                            <tr>
                                <td	colspan="7" class="rounded-foot-left">
                                    无记录信息
                                </td>
                            </tr>
                        </tfoot>
                    	</c:if>
                	</table>
	            </div>
        	</div>
    	</div>
	</body>
</html>