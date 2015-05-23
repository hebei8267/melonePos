<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
<head></head>
<body>
	<%
		// 系统菜单
	%>
	<page:applyDecorator name="menu" />

	<div class="container">
		<div class="row">
			<div class="span12">
				<legend>
					<h3>${sessionScope.__SESSION_USER_INFO.orgName} 发票申请-已开具</h3>
				</legend>
			</div>
			<div class="span12" style="margin-top: 10px;">
				<form:form method="POST" class="form-horizontal" id="inputForm"
					modelAttribute="invoice">
					<form:hidden path="uuid" />
					<div class="control-group">
						<label class="control-label">申请机构 :</label>
						<label class="left-control-label">${invoice.orgName}</label>
					</div>
					<div class="control-group">
						<label class="control-label">申请人 :</label>
						<label class="left-control-label">${invoice.appName}</label>
					</div>
					<div class="control-group">
						<label class="control-label">申请日期 :</label>
						<label class="left-control-label">${invoice.appDateShow}</label>
					</div>
					<div class="control-group">
						<label class="control-label">发票种类 :</label>
						<c:if test="${invoice.invoiceType == 1}">
							<label class="left-control-label">机打</label>
						</c:if>
						<c:if test="${invoice.invoiceType == 2}">
							<label class="left-control-label">手写</label>
						</c:if>
						<c:if test="${invoice.invoiceType == 4}">
							<label class="left-control-label">其他</label>
						</c:if>
					</div>
					<div class="control-group">
						<label class="control-label">发票台头 :</label>
						<label class="left-control-label">${invoice.title}</label>
					</div>
					<div class="control-group">
						<label class="control-label">发票内容 :</label>
						<label class="left-control-label">${invoice.content}</label>
					</div>
					<div class="control-group">
						<label class="control-label">发票金额 :</label>
						<label class="left-control-label">${invoice.amt}</label>
					</div>
					<div class="control-group">
						<label class="control-label">税号 :</label>
						<label class="left-control-label">${invoice.taxNo}</label>
					</div>
					<div class="control-group">
						<label class="control-label">送达期限 :</label>
						<label class="left-control-label">${invoice.serviceDateShow}</label>
					</div>
					<c:if test="${invoice.needPost == 1}">
						<div class="control-group">
							<label class="control-label">客户姓名 :</label>
							<label class="left-control-label"><span class="warn_text">${invoice.customerName}</span></label>
						</div>
						<div class="control-group">
							<label class="control-label">客户电话 :</label>
							<label class="left-control-label"><span class="warn_text">${invoice.customerTel}</span></label>
						</div>
						<div class="control-group">
							<label class="control-label">客户地址 :</label>
							<label class="left-control-label"><span class="warn_text">${invoice.customerAdd}</span></label>
						</div>
					</c:if>
					<div class="control-group">
						<label class="control-label">发票号 :</label>
						<label class="left-control-label"><span class="warn_text">${invoice.invoiceNum}</span></label>
					</div>
					<div class="control-group">
						<label class="control-label">发票来源 :</label>
						<label class="left-control-label"><span class="warn_text">${invoice.invoiceSrc}</span></label>
					</div>
					<c:if test="${invoice.needPost == 1}">
					<div class="control-group">
						<label class="control-label">快递公司 :</label>
						<label class="left-control-label">
						<c:if test="${invoice.expressCompany == '01'}">
                            申通
                        </c:if>
                        <c:if test="${invoice.expressCompany == '02'}">
                            中通
                        </c:if>
                        <c:if test="${invoice.expressCompany == '03'}">
                            顺丰
                        </c:if>
                        <c:if test="${invoice.expressCompany == '04'}">
                            圆通
                        </c:if>
                        <c:if test="${invoice.expressCompany == '05'}">
                            韵达
                        </c:if>
                        <c:if test="${invoice.expressCompany == '06'}">
                            汇通
                        </c:if>
						</label>
					</div>
					</c:if>
					<div class="control-group">
						<label class="control-label">备注 :</label>
						<label class="left-control-label"><span class="warn_text">${invoice.descTxt}</span></label>
					</div>
					<div class="control-group">
						<div class="controls">
							<a href="${sc_ctx}/invoiceApply" class="btn btn-large">返回</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
