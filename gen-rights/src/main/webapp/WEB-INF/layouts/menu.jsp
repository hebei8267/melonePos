<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>


<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner">
		<!-- BEGIN LOGO -->
   		<a class="navbar-brand" href="${sc_ctx}/mainFrame"> LOGO </a>
    	<!-- END LOGO -->
     	
     	<!-- BEGIN RESPONSIVE MENU TOGGLER -->
     	<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="${ctx}/static/img/menu-toggler.png" alt="" /> </a>
   		<!-- END RESPONSIVE MENU TOGGLER -->
	</div>
  	<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->

<div class="clearfix"></div>


<!-- BEGIN SIDEBAR -->
<div class="page-sidebar navbar-collapse collapse">
	<!-- BEGIN SIDEBAR MENU -->
	<ul class="page-sidebar-menu">
 		<li>
  		<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
   		<div class="sidebar-toggler hidden-phone"></div>
  		<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
      	</li>
                    
      	<li>
      		<br>
    	</li>
                    
      	<li class="start active">
  			<a href="#"> <i class="fa fa-home"></i> <span class="title">首页</span> <span class="selected"></span> </a>
     	</li>

   
		<li class="">
         	<a href="#"> <i class="fa fa-sitemap"></i> <span class="title">权限管理</span> <span class="arrow "></span> </a>
       		<ul class="sub-menu">
           		<li>
             		<a href="layout_session_timeout.html">用户管理</a>
            	</li>
              	<li>
              		<a href="layout_idle_timeout.html">角色管理</a>
           		</li>
         		<li>
           			<a href="layout_language_bar.html">组织管理</a>
             	</li>
             	<li>
               		<a href="layout_language_bar.html">功能配置</a>
            	</li>
          	</ul>
     	</li>

      	<li class="">
        	<a href="#"> <i class="fa fa-cogs"></i> <span class="title">系统管理</span> <span class="arrow "></span> </a>
          	<ul class="sub-menu">
           		<li>
              		<a href="layout_language_bar.html">功能配置</a>
             	</li>
           	</ul>
       	</li>

    	<li class="">
           	<a href="#"> <i class="fa fa-user"></i> <span class="title">用户相关</span> <span class="arrow "></span> </a>
          	<ul class="sub-menu">
          		<li>
               		<a href="userInfo.html">个人信息</a>
              	</li>
            	<li>
                	<a href="layout_language_bar.html">密码修改</a>
             	</li>
           	</ul>
      	</li>

  	</ul>
    <!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->