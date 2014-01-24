<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<link href="${ctx}/static/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
        <link href="${ctx}/static/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
        <link href="${ctx}/static/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
        <link href="${ctx}/static/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css"/>
    	<link href="${ctx}/static/css/pages/tasks.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="page-header-fixed">
        <div class="page-container">
        	<%@ include file="/WEB-INF/layouts/menu.jsp"%>
        	
            <div class="page-content">    
                <!-- BEGIN PAGE HEADER-->
                <div class="row">
                    <div class="col-md-12">
                        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                        <h3 class="page-title"> 首页</h3>
                        <ul class="page-breadcrumb breadcrumb">
                            <li>
                                <i class="fa fa-home"></i>
                                <a href="${sc_ctx}/mainFrame">首页</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- END PAGE HEADER-->
                
                
                
                
                <!-- BEGIN PAGE CONTENT-->
                
                
                <!-- END PAGE CONTENT-->
            </div>
        </div>
        
        
        <%@ include file="/WEB-INF/layouts/commonFooter.jsp"%>
        
        
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${ctx}/static/scripts/app.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script>
            jQuery(document).ready(function() {
                App.init();
                // initlayout and core plugins
            });
        </script>
    </body>
</html>