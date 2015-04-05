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
		<style type="text/css">
			._warn1 {
				padding: 5px;
				background-color: #99FF33;
			}
		</style>
		<script>
			$().ready(function() {
				//-----------------------------------
				// 表单效验
				//-----------------------------------
				$("#listForm").validate({
					rules : {
						delBtn : {
							requiredSelect : 'uuid'
						}
					}
				});
				//-----------------------------------
				// 全选/全部选
				//-----------------------------------
				$("#checkAll").click(function() {
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='uuid']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
                });

				//-----------------------------------
				// 删除按钮点击
				//-----------------------------------
				$("#delBtn").click(function() {
					if ($("#listForm").valid()) {
						$('#__del_confirm').modal({
							backdrop : true,
							keyboard : true,
							show : true
						});
					}
				});
			});
			//-----------------------------------
			// 删除
			//-----------------------------------
			function _del_confirm() {
				var $subCheckBox = $("input[name='uuid']");
				var uuids = "";
				$.each($subCheckBox, function(index, _checkBox) {
					if (_checkBox.checked) {
						uuids += _checkBox.value + ",";
					}
				});
				if (uuids.length > 0) {
					uuids = uuids.substring(0, uuids.length - 1);
				}

				$("#uuids").val(uuids);

				$("#listForm").attr('target', '_self');
				$("#listForm").attr("action", "${sc_ctx}/user/del");
				$("#listForm").submit();
			}
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="container">
			<form method="post"	class="form-horizontal"	id="listForm">
				<div class="row">
					<div class="span12">
						<legend>
							<h3>用户管理</h3>
						</legend>
					</div>
					<div class="span12">
						<a href="${sc_ctx}/user/new"	class="btn btn-primary">新增</a>
						<input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
					</div>
					<div class="span12"	style="margin-top: 10px;">
						<input type="hidden" name="uuids" id="uuids"/>
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th	width="25" class="center">
									<input id="checkAll" type="checkbox" />
									</th>
									<th width="80"> 账户状态 </th>
									<th> 用户帐号 </th>
									<th> 用户名称 </th>
									<th> 用户角色 </th>
									<th> 所属机构 </th>
									<th	width="55"> &nbsp; </th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList}" var="user">
									<tr>
										<td	class="center">
										<c:if test="${user.uuid != 1}">
											<input type="checkbox" name="uuid" value="${user.uuid}">
											</input>
										</c:if></td>
										<td class="center">
										<c:if test="${user.valid == true}">
											启用
										</c:if>
										<c:if test="${user.valid != true}">
											<span class='_warn1'>停用</span>
										</c:if>
										</td>
										<td> ${user.loginName} </td>
										<td> ${user.name} </td>
										<td> ${user.role.name} </td>
										<td> ${user.organization.name} </td>
										<td><a href="${sc_ctx}/user/edit/${user.uuid}" class="btn btn-warning"/>修改</a></td>
									</tr>
								</c:forEach>
							</tbody>
							<c:if test="${empty	userList}" >
								<tfoot>
									<tr>
										<td	colspan="7" class="rounded-foot-left"> 无记录信息 </td>
									</tr>
								</tfoot>
							</c:if>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>