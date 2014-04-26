<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<script src="${ctx}/static/js/jquery.jcryption-1.2.js"></script>
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
            }
            .form-signin {
                max-width: 300px;
                padding: 19px 29px 29px;
                margin: 50px auto 20px;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
            }
            input[type="text"], input[type="password"] {
                margin-bottom: 20px;
            }
            label.error {
                margin-bottom: 15px;
            }
            label {
                display: block;
            }
        </style>
        <script>
	        var _hashObj = new jsSHA(generateUUID(), "ASCII");
	        var _key = _hashObj.getHash("SHA-512", "HEX");

	        $(function() {
		        $.jCryption.authenticate(_key, "${sc_ctx}/encrypt?generateKeyPair=true", "${sc_ctx}/encrypt?handshake=true",
					function(AESKey) {
						// Authentication ok
					},
					function() {
						// Authentication failed
						alert("无法取得通讯密码！请联系系统管理员。");
					}
				);
        });
        </script>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" id="inputForm" method="post">
                <h2>用户登录</h2>
                <input type="text" class="input-block-level" name="loginName" id="loginName" placeholder="User ID" value="">
                <input type="password" class="input-block-level" name="passWord" id="passWord" placeholder="Password" value="">
                <input type="hidden" name="_loginName" id="_loginName" >
                <input type="hidden" name="_passWord" id="_passWord" >
                <input type="submit" value="登录" id="loginBtn" class="btn btn-large btn-primary"/>
            </form>
            <center>
            <a href="${ctx}/map.jsp" target="_blank" title="各门店详细地址信息">
				<img src="${ctx}/static/img/search-map.png" />
			</a>
			</center>
        </div>
        <script>
            $(function() {
                $("#inputForm").validate({
                    rules : {
                        loginName : "required",
                        passWord : {
                            required : true,
                            minlength : 6
                        }
                    }
                });

                $('#loginName').focus();

                $('#loginName').keyup(function(event) {
                    if (event.keyCode == 13) {
                        $('#passWord').focus();
                    }
                });

                $('#passWord').keyup(function(event) {
                    if (event.keyCode == 13) {
                        _userLoginFun();
                    }
                });
                
                $("#loginBtn").click(function() {
		        	_userLoginFun();
	       		});
            });
            
            function _userLoginFun() {
            	var _res = $("#inputForm").valid();
		        if(!_res){
		        	return;
		        }
		        	
		        var enLoginName = $.jCryption.encrypt($("#loginName").val(), _key);
		        var enPassWord = $.jCryption.encrypt($("#passWord").val(), _key);
					
				$("#loginName").val("????????");
				$("#passWord").val("????????");
					
				$("#_loginName").val(enLoginName);
				$("#_passWord").val(enPassWord);
					
				$("#inputForm").attr("action", "${sc_ctx}/member/login");
	          	$("#inputForm").submit();
            }
        </script>
    </body>
</html>