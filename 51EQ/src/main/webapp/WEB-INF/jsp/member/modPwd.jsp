<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<script src="${ctx}/static/js/jquery.jcryption-1.2.js"></script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <div class="row">
                <div class="span12">
                    <legend>
                        <h2>密码修改</h2>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                    <c:if test="${sessionScope.__SESSION_USER_INFO.firstLoginFlg == true}">
                        <div class="alert alert-error">
                            为了您的帐户安全，请修改默认密码。
                        </div>
                    </c:if>
                    <form class="form-horizontal" id="inputForm" method="post">
                        <input id="uuid" name="uuid" type="hidden" value="${sessionScope.__SESSION_USER_INFO.uuid}"/>
                        <input type="hidden" name="_oldPassWord" id="_oldPassWord" >
                		<input type="hidden" name="_newPassWord" id="_newPassWord" >
                        <div class="control-group">
                            <label class="control-label">用户帐号 :</label>
                            <label class="left-control-label" id="_initAmt_label">${sessionScope.__SESSION_USER_INFO.loginName}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">原密码 :</label>
                            <div class="controls">
                                <input type="password" name="oldPassWord" id="oldPassWord">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">新密码 :</label>
                            <div class="controls">
                                <input type="password" name="newPassWord" id="newPassWord">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">确认密码 :</label>
                            <div class="controls">
                                <input type="password" name="confirmPassWord" id="confirmPassWord">
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="modBtn" class="btn	btn-large btn-primary" type="submit">修改</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
        	var _key = '${_encrypt_key}';
        	
            $(function() {
                $("#inputForm").validate({
                    rules : {
                        oldPassWord : {
                            required : true,
                            minlength : 6,
                            maxlength : 16
                        },
                        newPassWord : {
                            required : true,
                            minlength : 6,
                            maxlength : 16
                        },
                        confirmPassWord : {
                            equalTo : "#newPassWord"
                        }
                    }
                });
                $("#modBtn").click(function() {
                	var _res = $("#inputForm").valid();
		        	if(!_res){
		        		return;
		        	}
		        	
		        	var enOldPassWord = $.jCryption.encrypt($("#oldPassWord").val(), _key);
		        	var enNewPassWord = $.jCryption.encrypt($("#newPassWord").val(), _key);
					
					$("#oldPassWord").val("????????");
					$("#newPassWord").val("????????");
					$("#confirmPassWord").val("????????");
					
					$("#_oldPassWord").val(enOldPassWord);
					$("#_newPassWord").val(enNewPassWord);
                
                    $("#inputForm").attr("action", "${sc_ctx}/member/modPwd");
                    $("#inputForm").submit();
                });
            });
        </script>
    </body>
</html>