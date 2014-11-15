<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<title>Office Automation System
			<sitemesh:title />
		</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<%
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		%>
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
		<meta http-equiv="Expires" content="-1" />

		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link type="text/css" href="${ctx}/static/css/bootstrap.css" rel="stylesheet">
		<link type="text/css" href="${ctx}/static/css/bootstrap-responsive.css" rel="stylesheet">
		<link type="text/css" href="${ctx}/static/css/datepicker.css" rel="stylesheet">
		<link type="text/css" href="${ctx}/static/css/mystyle.css" rel="stylesheet">

		<script src="${ctx}/static/js/jquery-1.7.2.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.js"></script>
		<script src="${ctx}/static/js/bootstrap-datepicker.js"></script>
		<script src="${ctx}/static/js/jquery.validate-1.10.0.js"></script>
		<script src="${ctx}/static/js/jquery.validate_cn.js"></script>

		<link rel="shortcut icon" href="${ctx}/static/img/favicon.ico" type="image/x-icon">
		<link rel="icon" href="${ctx}/static/img/favicon.ico" type="image/x-icon">
		<sitemesh:head />
	</head>
	<body>
		<%@ include file="/WEB-INF/layouts/infoList.jsp"%>
		<sitemesh:body />
	</body>
</html>