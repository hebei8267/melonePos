<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
	${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${ctx}/static/css/pricing-steelblue.css">
		<script>
			$().ready(function() {
				$("#searchForm").validate({
					rules : {
						barcode : {
							required : true
						}
					}
				});

				$("#searchBtn").click(function() {
					_search();
				});
				
				$('#barcode').keydown(function(event) {
                    if (event.keyCode == 13) {
                    	_search();
                        return false;
                    }
                });
			});
			
			function _search(){
				$("input[type='text'],textarea").each(function(i) {
					this.value = $.trim(this.value);
				});

				$("#searchForm").attr('target', '_blank');
				$("#searchForm").attr("action", "${sc_ctx}/salesWeekGoodsTotalReport/contrast/" + $("#barcode").val());
				$("#searchForm").submit();
			}
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="container" style="padding-top: 10px;">
			<div class="row" style="padding-top: 10px;">
				<form method="post"	class="form-inline"	id="searchForm">
					<div class="span12">
						<legend>
							<h3>商品周销售信息对比(按商品)</h3>
						</legend>
					</div>
					<div class="span8">
						<label class="control-label">货号 : </label>
						<input id="barcode" name="barcode" type="text" class="input-xlarge" placeholder="多个货号需以英文半角逗号隔开"/>
						&nbsp;&nbsp;
						<button	id="searchBtn" class="btn btn-primary" type="button">
							查询
						</button>
					</div>
				</form>
			</div>

			<c:if test="${!empty msgInfoList}">
				<div class="row" style="padding-top: 10px;">
					<div class="span12">
						<legend>
							<h3>消息/信息</h3>
						</legend>
					</div>
					<c:forEach items="${msgInfoList}" var="msgInfo">
					<c:if test="${msgInfo.readFlg == 0}">
					<div class="span3 pricing6">
					</c:if>

					<c:if test="${msgInfo.readFlg == 1}">
					<div class="span3 pricing2">
					</c:if>
						<ul>
							<li class="head">
								<h4>${msgInfo.msgSubject}</h4>
							</li>
							<li>
								${msgInfo.msgContent}
							</li>
							<li>
								发信人 : ${msgInfo.sendNameSet}
							</li>
							<li>
								${msgInfo.optDateShow}
							</li>
							<li class="footer">
								<a href="${sc_ctx}/msgInfo/view/${msgInfo.uuid}" class="btn btn-large">查 看</a>
							</li>
						</ul>
					</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</body>
</html>