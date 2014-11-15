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
	<head></head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="container">
			<form method="post"	class="form-horizontal"	id="listForm">
				<div class="row">
					<div class="span12">
						<legend>
							<h3>角色管理</h3>
						</legend>
					</div>
					<div class="span12">
						<a href="${sc_ctx}/role/new" class="btn btn-primary">新增</a>
					</div>

					<div class="span4"	style="margin-top: 10px;">
						<input type="hidden" name="uuids" id="uuids"/>
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th class="center"> 角色名称 </th>
									<th	width="55"> &nbsp; </th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${roleList}" var="role">
									<tr>
										<td> ${role.name} </td>
										<td><a href="${sc_ctx}/role/edit/${role.uuid}" class="btn btn-warning"/>修改</a></td>
									</tr>
								</c:forEach>
							</tbody>
							<c:if test="${empty	roleList}" >
								<tfoot>
									<tr>
										<td	colspan="3" class="rounded-foot-left"> 无记录信息 </td>
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