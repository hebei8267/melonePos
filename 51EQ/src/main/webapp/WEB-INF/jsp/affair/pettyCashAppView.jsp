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
    	<style>
    	.form-horizontal .control-label {
            width: 130px;
        }
        .form-horizontal .controls {
            margin-left: 145px;
        }
        ._warn1 {
    		padding: 5px;
			background-color: #99FF33;
		}
		._warn2 {
			padding: 5px;
			background-color: #FFCC33;
		}
    	</style>
    	<script>
    	$(function() {
    		
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
                        <h3>资金-归档</h3>
                    </legend>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="pettyCashApp">
                	<form:hidden path="uuid"/>
                	<div class="control-group">
                        <label class="control-label">请款人 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.appPerName}</label>
                      		<input type="hidden" name="appPer" value="${pettyCashApp.appPer}">
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">会计科目机构 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.appOrgName}</label>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">请款日期 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.appDate}</label>
                      		<input type="hidden" id="appDate" name="appDate" value="${pettyCashApp.appDate}">
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">付款期限 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.paymentPeriod}</label>
                      		<input type="hidden" name="paymentPeriod" value="${pettyCashApp.paymentPeriod}">
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">请款金额 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.amount} 元</label>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">审批金额 :</label>
                        <div class="controls">
                        	<label class="left-control-label">${pettyCashApp.confirmAmount} 元</label>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">请款事由 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.reason}</label>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">付款方式 :</label>
                        <div class="controls">
                        	<c:if test="${pettyCashApp.paymentMode.equals('00')}">
                        	<label class="left-control-label">现金</label>
                        	</c:if>
                        	<c:if test="${pettyCashApp.paymentMode.equals('01')}">
                        	<label class="left-control-label">转帐</label>
                        	</c:if>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">付款方式说明 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.paymentExplain}</label>
                      	</div>
                    </div>
                    
                    <div class="control-group">
                        <label class="control-label">审批人(1) :</label>
                        <div class="controls">
                        	<label class="left-control-label">${pettyCashApp.approvalPer1Name}</label>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">审批人(1)意见 :</label>
                        <div class="controls">
                        	<label class="left-control-label">${pettyCashApp.approvalPerComment1}</label>
                      	</div>
                    </div>
                    
                    <c:if test="${pettyCashApp.appLevel >= ('02')}">
                    <div class="control-group">
                        <label class="control-label">审批人(2) :</label>
                        <div class="controls">
                        	<label class="left-control-label">${pettyCashApp.approvalPer2Name}</label>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">审批人(2)意见 :</label>
                        <div class="controls">
                        	<label class="left-control-label">${pettyCashApp.approvalPerComment2}</label>
                      	</div>
                    </div>
                    </c:if>
                    
                    <c:if test="${pettyCashApp.appLevel >= ('03')}">
                    <div class="control-group">
                        <label class="control-label">审批人(3) :</label>
                        <div class="controls">
                        	<label class="left-control-label">${pettyCashApp.approvalPer3Name}</label>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">审批人(3)意见 :</label>
                        <div class="controls">
                        	<label class="left-control-label">${pettyCashApp.approvalPerComment3}</label>
                      	</div>
                    </div>
                    </c:if>
                </form:form>
               	</div>
   			</div>
   		</div>
   	</body>
</html>