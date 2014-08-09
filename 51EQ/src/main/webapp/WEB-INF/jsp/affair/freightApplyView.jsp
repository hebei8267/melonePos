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

        <div class="container" style="padding-top: 40px;">
        	<div class="row" style="padding-top: 10px;">
        		<div class="span12">
        			<p><center><h2> 已提交调货申请、未审批数量为（${applyNotApprovalCount}）</h2><a class="btn btn-large btn-info" href="${sc_ctx}/freight/viewList"><i class="fa fa-angle-double-right"></i> 详细</a></center></p>
	          	</div>
        	</div>
        	<div class="row-fluid" style="padding-top: 40px;">
        		<div class="span3 alert alert-error">
		            <h2 class="alert-heading"><i class="fa fa-comments"></i> 预计收货</h2>
		            <p><h4 class="alert-heading"> 预计（${expReceiptCount}）/审批（${approvalNotCompleteCount}）</h4></p>
	          	</div>
	          	
	        	<div class="span3 alert alert-block">
					<h3 class="alert-heading"><i class="fa fa-shopping-cart"></i> 预计送货</h3>
		            <p><h4 class="alert-heading"> 预计（${expDeliveryCount}）/审批（${approvalNotCompleteCount}）</h4></p>
				</div>
				
	        	<div class="span3 alert alert-success">
	        		<h3 class="alert-heading"><i class="fa fa-flag"></i> 实际收货</h3>
		            <p><h4 class="alert-heading"> 预计（${expReceiptCount}）</h4></p>
				</div>
				
	        	<div class="span3 alert alert-info">
	        		<h3 class="alert-heading"><i class="fa fa-plane"></i> 实际送达</h3>
		            <p><h4 class="alert-heading"> 预计（${expDeliveryCount}）</h4></p>
				</div>
			</div>
        </div>
    </body>
</html>