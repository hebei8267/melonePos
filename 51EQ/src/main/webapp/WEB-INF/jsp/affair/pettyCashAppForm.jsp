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
    		$('#paymentPeriod').datepicker({
            	format : 'yyyy-mm-dd'
        	});
         
        	$("#inputForm").validate({
                 rules : {
                  	paymentPeriod : {
                     	required : true,
                     	date : true,
                     	dateGreaterThan : $("#appDate").val()
                  	},
                  	amount : {
                    	required : true,
                     	number : true
                  	},
                	reason : {
                		required : true,
                     	maxlength : 255
                   	},
                 	paymentMode : {
                     	required : true
                 	},
               		paymentExplain : {
                      	maxlength : 255
                   	},
                   	confirmAmount : {
                   		required : true,
                     	number : true
                  	},
                  	approvalPerComment1 : {
                		required : true,
                     	maxlength : 255
                   	},
                   	approvalPerComment2 : {
                		required : true,
                     	maxlength : 255
                   	},
                   	approvalPerComment3 : {
                		required : true,
                     	maxlength : 255
                   	}
                 }
     		});
     		
     		$("#saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#inputForm").attr('target', '_self');
                $("#inputForm").attr("action", "${sc_ctx}/pettyCashApp/save");
                $("#inputForm").submit();
            });
                
            $("#confirmBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#inputForm").attr('target', '_self');
                $("#inputForm").attr("action", "${sc_ctx}/pettyCashApp/confirm");
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
                        <h3>
                        <c:if test="${empty	pettyCashApp.uuid}">
                            备用金-申请
                        </c:if>
                        <c:if test="${!empty pettyCashApp.uuid && pettyCashApp.appState.equals('00')}">
                            备用金-编辑
                        </c:if>
                        <c:if test="${!empty pettyCashApp.uuid && !pettyCashApp.appState.equals('00')}">
                            备用金-审批
                        </c:if>
                        </h3>
                    </legend>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="pettyCashApp">
                	<form:hidden path="uuid"/>
                	<c:if test="${empty pettyCashApp.uuid}">
                	<div class="control-group">
                        <label class="control-label">请款人 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${sessionScope.__SESSION_USER_INFO.name}</label>
                      		<input type="hidden" name="appPer" value="${sessionScope.__SESSION_USER_INFO.uuid}">
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">会计科目机构 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${sessionScope.__SESSION_USER_INFO.subName}</label>
                      		<input type="hidden" name="appOrg" value="${sessionScope.__SESSION_USER_INFO.subUuid}">
                      	</div>
                    </div>
                    </c:if>
                    <c:if test="${!empty pettyCashApp.uuid}">
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
                      		<input type="hidden" name="appOrg" value="${pettyCashApp.appOrg}">
                      	</div>
                    </div>
                    </c:if>
                    
                    
                    <div class="control-group">
                        <label class="control-label">请款日期 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.appDate}</label>
                      		<input type="hidden" id="appDate" name="appDate" value="${pettyCashApp.appDate}">
                      	</div>
                    </div>
                    
                    
                    <c:if test="${empty pettyCashApp.uuid || pettyCashApp.appState.equals('00')}">
                    <div class="control-group">
                        <label class="control-label">付款期限 :</label>
                        <div class="controls">
                      		<form:input	path="paymentPeriod" />
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">请款金额 :</label>
                        <div class="controls">
                      		<form:input	path="amount" /> 元
                      	</div>
                    </div>
                    </c:if>
                    <c:if test="${!empty pettyCashApp.uuid && !pettyCashApp.appState.equals('00')}">
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
                    </c:if>
                    
                   
                    <c:if test="${!empty pettyCashApp.uuid && !pettyCashApp.appState.equals('00') && !pettyCashApp.appState.equals('99')}">
                    <div class="control-group">
                        <label class="control-label">审批金额 :</label>
                        <div class="controls">
                      		<form:input	path="confirmAmount" /> 元
                      	</div>
                    </div>
                    </c:if>
                    <c:if test="${!empty pettyCashApp.uuid && pettyCashApp.appState.equals('99')}">
                    <div class="control-group">
                        <label class="control-label">审批金额 :</label>
                        <div class="controls">
                        	<label class="left-control-label">${pettyCashApp.confirmAmount} 元</label>
                      	</div>
                    </div>
                    </c:if>
                    
                    
                    <c:if test="${empty pettyCashApp.uuid || pettyCashApp.appState.equals('00')}">
                    <div class="control-group">
                        <label class="control-label">请款事由 :</label>
                        <div class="controls">
                      		<form:textarea path="reason" rows="3"/>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">付款方式 :</label>
                        <div class="controls">
                      		<form:radiobutton path="paymentMode" value="00"/>
                                <span class='_warn1'>现金</span>
                          	<form:radiobutton path="paymentMode" value="01"/>
                                <span class='_warn2'>转帐</span>
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">付款方式描述 :</label>
                        <div class="controls">
                      		<form:textarea path="paymentExplain" rows="3"/>
                      	</div>
                    </div>
                    </c:if>
                    <c:if test="${!empty pettyCashApp.uuid && !pettyCashApp.appState.equals('00')}">
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
                        <label class="control-label">付款方式描述 :</label>
                        <div class="controls">
                      		<label class="left-control-label">${pettyCashApp.paymentExplain}</label>
                      	</div>
                    </div>
                    </c:if>
                    
                    
                    <c:if test="${!empty pettyCashApp.uuid && pettyCashApp.appState.equals('01')}">
                    <div class="control-group">
                        <label class="control-label">审批人(1) :</label>
                        <div class="controls">
                      		<label class="left-control-label">${sessionScope.__SESSION_USER_INFO.name}</label>
                      		<input type="hidden" name="approvalPer1" value="${sessionScope.__SESSION_USER_INFO.uuid}">
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">审批人(1)意见 :</label>
                        <div class="controls">
                      		<form:textarea path="approvalPerComment1" rows="3"/>
                      	</div>
                    </div>
                    </c:if>
                    <c:if test="${!empty pettyCashApp.uuid && !pettyCashApp.appState.equals('00') && !pettyCashApp.appState.equals('01')}">
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
                    </c:if>
                    
                    
                    <c:if test="${!empty pettyCashApp.uuid && pettyCashApp.appState.equals('02')}">
                    <div class="control-group">
                        <label class="control-label">审批人(2) :</label>
                        <div class="controls">
                      		<label class="left-control-label">${sessionScope.__SESSION_USER_INFO.name}</label>
                      		<input type="hidden" name="approvalPer2" value="${sessionScope.__SESSION_USER_INFO.uuid}">
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">审批人(2)意见 :</label>
                        <div class="controls">
                      		<form:textarea path="approvalPerComment2" rows="3"/>
                      	</div>
                    </div>
                    </c:if>
                    <c:if test="${!empty pettyCashApp.uuid && !pettyCashApp.appState.equals('00') && !pettyCashApp.appState.equals('01') && !pettyCashApp.appState.equals('02')}">
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
                    
                    
                    <c:if test="${!empty pettyCashApp.uuid && pettyCashApp.appState.equals('03')}">
                    <div class="control-group">
                        <label class="control-label">审批人(3) :</label>
                        <div class="controls">
                      		<label class="left-control-label">${sessionScope.__SESSION_USER_INFO.name}</label>
                      		<input type="hidden" name="approvalPer3" value="${sessionScope.__SESSION_USER_INFO.uuid}">
                      	</div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">审批人(3)意见 :</label>
                        <div class="controls">
                      		<form:textarea path="approvalPerComment3" rows="3"/>
                      	</div>
                    </div>
                    </c:if>
                    <c:if test="${!empty pettyCashApp.uuid && !pettyCashApp.appState.equals('00') && !pettyCashApp.appState.equals('01') && !pettyCashApp.appState.equals('02') && !pettyCashApp.appState.equals('03')}">
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
                    
                    <div class="control-group">
                        <div class="controls">
	                        <button	id="confirmBtn" class="btn	btn-large btn-warning" type="submit">提交</button>
	                        <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                            <a href="${sc_ctx}/pettyCashApp/list" class="btn btn-large">返回</a>
                        </div>
                    </div>
                </form:form>
                </div>
            </div>
        </div>
	</body>
</html>