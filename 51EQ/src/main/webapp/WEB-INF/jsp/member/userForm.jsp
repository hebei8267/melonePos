<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<script>
			$(function() {
				$("#inputForm").validate({
					rules : {
						loginName : {
							required : true,
							maxlength : 32
						},
						name : {
							required : true,
							maxlength : 32
						},
						roleUuid : {
							required : true
						},
						orgUuid : {
							required : true
						},
						descTxt : {
							maxlength : 255
						}
					}
				});

				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					$("#inputForm").attr('target', '_self');
					$("#inputForm").attr("action", "${sc_ctx}/user/save");
					$("#inputForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="container">
			<div class="row">
				<div class="span12">
					<legend>
						<h3>用户信息
						<c:if test="${empty	user.uuid}">
							新增
						</c:if>
						<c:if test="${!empty user.uuid}">
							编辑
						</c:if></h3>
					</legend>
				</div>
				<div class="span12"	style="margin-top: 10px;">
					<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="user">
						<form:hidden path="uuid"/>
						<div class="control-group">
							<label class="control-label">用户帐号 :</label>
							<c:if test="${empty	user.uuid}">
								<div class="controls">
									<form:input	path="loginName" />
								</div>
							</c:if>
							<c:if test="${!empty user.uuid}">
								<label class="left-control-label">${user.loginName}</label>
							</c:if>
						</div>
						<div class="control-group">
							<label class="control-label">用户名称 :</label>
							<div class="controls">
								<form:input	path="name" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">用户角色 :</label>
							<div class="controls">
								<form:select path="roleUuid" items="${roleList}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">所属机构 :</label>
							<div class="controls">
								<form:select path="orgUuid" items="${orgList}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">账户状态 :</label>
							<div class="controls">
								<form:radiobutton path="valid" value="true"/>
								启用
								<form:radiobutton path="valid" value="false"/>
								停用
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备注 :</label>
							<div class="controls">
								<form:textarea path="descTxt" class="input-xlarge" rows="4"/>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<c:if test="${!empty user.uuid}">
									<form:checkbox path="initPwdFlg"/>
									<label style="font-weight: bold;color: #CC3300">初始化密码</label>
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">
									保存
								</button>
								&nbsp;<a href="${sc_ctx}/user" class="btn btn-large">返回</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>
