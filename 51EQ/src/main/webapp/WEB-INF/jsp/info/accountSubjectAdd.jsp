<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<%@ page import="com.tjhx.globals.Constants" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<form class="form-horizontal" id="accountSubject-add-form" action="${sc_ctx}/accountSubject/init">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">×</a>
		<h4 class="modal-title">记账科目信息－新增</h4>
	</div>

	<div class="modal-body form">
		<div class="form-body modal-form-body">
			<div class="row">

				<div class="span12">
					<div class="control-group">
						<label class="control-label">上层记账科目<span class="required">※</span></label>
						<div class="controls">
							<select class="layout-option form-control" name="parentSubUuid" id="parentSubUuid">
								<option value=""><%=Constants.BLANK_SELECT_TEXT %></option>
								<c:forEach items="${subList}" var="sub">
									<option value="${sub.uuid}">${sub.subName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">科目名称<span class="required">※</span></label>
						<div class="controls">
							<input type="text" class="form-control" name="subName">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">排序(显示)</label>
						<div class="controls">
							<input type="text" class="form-control input-inline input-mini" name="sortIndex">
							<span class="help-inline">1-99</span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-success" id="org-save-btn">保存</button>
							<button type="button" data-dismiss="modal" class="btn default">取消</button>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</form>
<script>
	function accountSubjectAdd(form) {
		$.post('${sc_ctx}/accountSubject/save', $('#accountSubject-add-form').serialize(), function(result) {
			if (result) {
				form.submit();
			} else {
			}
		});
	}

	$(function() {
		$("#accountSubject-add-form").validate({
			rules : {
				parentSubUuid : {
					required : true
				},
				subName : {
					required : true,
					maxlength : 32
				},
				sortIndex : {
					digits : true
				}
			},
			submitHandler : function(form) {
				accountSubjectAdd(form);
			}
		});
	}); 
</script>