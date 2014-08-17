<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<%@	page import="com.tjhx.globals.Constants"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="_ROOT_ORG_ID"><%=Constants.ROOT_ORG_ID %></c:set>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${ctx}/static/fontAwesome/css/font-awesome.css">
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
        	<div class="row-fluid" style="padding-top: 40px;">
        		<div class="span4 alert alert-error">
		            <h2 class="alert-heading"><i class="fa fa-check-square-o"></i> 审批信息</h2>
		            <p><h4 class="alert-heading">已审批 (${approvalCount}) / 申请 (${applyCount})</h4></p>
	          	</div>
	          	
        		<div class="span4 alert alert-success">
		            <h2 class="alert-heading"><i class="fa fa-paperclip"></i> 收货计划</h2>
		            <p><h4 class="alert-heading">已打包 (${packedCount}) / 预收 (${expReceiptCount})</h4></p>
	          	</div>
	          	
	          	<div class="span4 alert alert-info">
		            <h2 class="alert-heading"><i class="fa fa-shopping-cart"></i> 收货信息</h2>
		            <p><h4 class="alert-heading">已发 (${orgActDeliveryCount}) / 已收 (${actReceiptCount})</h4></p>
	          	</div>
			</div>
        	<div class="row-fluid" style="padding-top: 10px;">
				
				<div class="span4 alert alert-success">
					<h3 class="alert-heading"><i class="fa fa-paperclip"></i> 送货计划</h3>
		            <p><h4 class="alert-heading">签收 (${orgActReceiptCount}) / 送达 (${actDeliveryCount}) / 预送 (${expDeliveryCount})</h4></p>
				</div>
				
	        	<div class="span4 alert alert-info">
	        		<h3 class="alert-heading"><i class="fa fa-plane"></i> 实际送达</h3>
		            <p><h4 class="alert-heading">签收 (${orgActReceiptCountComplete}) / 送达 (${actDeliveryCountComplete})</h4></p>
				</div>
				
				<div class="span4 alert alert-block">
	        		<center><p><h1 class="alert-heading"><a href="${sc_ctx}/freight/viewList"><i class="fa fa-th-large"></i> 详细</a></h1></p></center>
				</div>
			</div>
        </div>
    </body>
</html>