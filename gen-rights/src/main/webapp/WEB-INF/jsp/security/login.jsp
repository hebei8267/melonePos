<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<!DOCTYPE html>
<html>
    <head>
        <link href="${ctx}/static/plugins/select2/select2_metro.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="login">

        <div class="logo">
            <h3>Gen-Rights</h3>
        </div>

        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form" action="index.html" method="post">
                <h3 class="form-title">请输入用户名和密码</h3>

                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">用户名</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn blue pull-right"> 登录 <i class="m-icon-swapright m-icon-white"></i></button>
                </div>
                <div class="forget-password">
                    <h4>忘记密码 ?</h4>
                    <p>
                        请点击 <a href="javascript:;"  id="forget-password">这里</a>
                        重置您的密码.
                    </p>
                </div>
            </form>
            <!-- END LOGIN FORM -->

            <!-- BEGIN FORGOT PASSWORD FORM -->
            <form class="forget-form" action="index.html" method="post">
                <h3 >忘记密码 ?</h3>
                <p>
                    输入您的邮箱地址.
                </p>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="fa fa-envelope"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="邮箱地址" name="email" />
                    </div>
                </div>
                <div class="form-actions">
                    <button type="button" id="back-btn" class="btn"><i class="m-icon-swapleft"></i> 后退 </button>
                    <button type="submit" class="btn blue pull-right"> 确定 <i class="m-icon-swapright m-icon-white"></i></button>
                </div>
            </form>
            <!-- END FORGOT PASSWORD FORM -->
        </div>
        <!-- END LOGIN -->
        
        <%@ include file="/WEB-INF/layouts/commonFooter.jsp"%>
        
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${ctx}/static/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/scripts/app.js" type="text/javascript"></script>
        <script src="${ctx}/static/scripts/login-soft.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script>
            jQuery(document).ready(function() {
                App.init();
                Login.init();
            });
        </script>
    </body>
</html>