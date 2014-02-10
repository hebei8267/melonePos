<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
    	<fmt:parseDate value="${optDate}" var="_optDate" pattern="yyyyMMdd" />
    	<h4>统计日期 : <fmt:formatDate pattern="yyyy/MM/dd" value="${_optDate}" /></h4>
		<table class="table	table-striped table-bordered table-condensed mytable">
			<thead>
				<tr>
					<th>日期/机构</th>
					<c:forEach items="${orgList}" var="org">
					<th>${org.name }</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${posAmtRateList}" var="_posAmtRateList" varStatus="status1">
				<tr>
					<fmt:parseDate value="${optDateList[status1.index]}" var="_tmpDate" pattern="yyyyMMdd" />
					<td><fmt:formatDate pattern="yyyy/MM/dd" value="${_tmpDate}" /></td>
					<c:forEach items="${_posAmtRateList}" var="posAmtRate">
					<td style="background-color: ${posAmtRate.htmlColor }">${posAmtRate.posAmtRate }</td>
					</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
			<c:if test="${empty	posAmtRateList}" >
			<tfoot>
		   		<tr>
		      		<td	colspan="${fn:length(posAmtRateList)}" class="rounded-foot-left">
					无记录信息
		       		</td>
		  		</tr>
			</tfoot>
			</c:if>
		</table>
    </body>
</html>