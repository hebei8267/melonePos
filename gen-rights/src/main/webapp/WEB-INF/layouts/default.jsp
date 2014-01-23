<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

    <head>
        <meta charset="utf-8" />   
        <title>Gen-Rights<sitemesh:title /></title>
        <%@ include file="/WEB-INF/layouts/commonHead.jsp"%>
        <sitemesh:head />
    </head>
    
	<body class="<sitemesh:getProperty property='body.class'/>">
        <sitemesh:body />
    </body>
</html>