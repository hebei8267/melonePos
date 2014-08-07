<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<%@	page import="com.tjhx.globals.Constants"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="_ROOT_ORG_ID">
    <%=Constants.ROOT_ORG_ID %>
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<style type="text/css">
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
			.left-control-label {
				font-size: 16px;
				color: #000;
				font-weight: normal;
			}
        </style>
	    <script>
	    	jQuery.validator.addMethod("mySelectRequired", function(value, element, param) {
			    var _freightTypeVal = $("input[name='"+param+"']:radio:[checked]").val();
			    if(_freightTypeVal == 2){
			    	return value != '';
			    } else {//普通调货类别-无需输入限时日期
			    	return true;
			    }
			}, '请填写此字段');

	    	$(function() {
	    		$('#appDate').datepicker({
                	format : 'yyyy-mm-dd'
            	});
             
            	$('#limitedDate').datepicker({
                	format : 'yyyy-mm-dd'
            	});
            	
            	$("#inputForm").validate({
	                 rules : {
	           			appOrgId : {
	                    	required : true
	                  	},
	                  	appDate : {
	                     	required : true,
	                     	date : true,
                         	dateGreaterThan : $("#_current_date").val()
	                  	},
	                  	applicant : {
	                    	required : true,
	                     	maxlength : 32
	                  	},
	                	packFlg : {
	                     	required : true
	                   	},
	                 	packNum : {
	                     	required : true,
	                     	number : true
	                 	},
	               		packUnit : {
	                      	required : true
	                   	},
	                 	targetOrgId : {
	                    	required : true
	                 	},
	              		freightType : {
	                    	required : true
	                  	},
	                 	limitedDate : {
	                    	mySelectRequired : "freightType",
	                      	date : true,
                         	dateGreaterThan : $("#_current_date").val()
	                 	},
	                 	approver : {
	                    	required : true,
	                     	maxlength : 32
	                  	}
	                 }
	     		});
	     		
	     		$("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr('target', '_self');
                    $("#inputForm").attr("action", "${sc_ctx}/freight/save");
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
                        <h3>商品调货单
                        <c:if test="${empty	freightApp.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty freightApp.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                	<input type="hidden" id="_current_date" value="<%=DateUtils.getNextDateFormatDate(-1, "yyyy-MM-dd")%>">
                	<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="freightApp">
                        <form:hidden path="uuid"/>
                        <%//总部新增时（货运申请状态-强制为【01已审批】） %>
                        <c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
                        <input id="status" name="status" type="hidden" value="01"/>
                        </c:if>
                        <%//门店新增时（货运申请状态-为【00申请】） %>
                        <c:if test="${sessionScope.__SESSION_USER_INFO.orgId != _ROOT_ORG_ID}">
                        <input id="status" name="status" type="hidden" value="00"/>
                        </c:if>
                        <div class="control-group">
                            <label class="control-label">调出机构 :</label>
                            <div class="controls">
                            <c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
	                            <select name="appOrgId">
			                  	<c:forEach items="${orgList}" var="org">
			                  		<c:if test="${org.key == appOrgId}">
			                   		<option value="${org.key }" selected>${org.value }</option>
			                      	</c:if>
			                		<c:if test="${org.key != appOrgId}">
			                    	<option value="${org.key }">${org.value }</option>
			                     	</c:if>
			                	</c:forEach>
			             		</select>
		             		</c:if>
		             		<c:if test="${sessionScope.__SESSION_USER_INFO.orgId != _ROOT_ORG_ID}">
		             			<input id="appOrgId" name="appOrgId" type="hidden" value="${sessionScope.__SESSION_USER_INFO.orgId}"/>
		             			<input type="text" value="${sessionScope.__SESSION_USER_INFO.orgName}" readonly/>
		             		</c:if>
		             		</div>
                        </div>
                       	<div class="control-group">
                            <label class="control-label">申请日期 :</label>
                           	<div class="controls">
                          		<form:input	path="appDate" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">申请人 :</label>
                           	<div class="controls">
                          		<form:input	path="applicant" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否打包 :</label>
                           	<div class="controls">
                          		<form:radiobutton path="packFlg" value="1"/>
	                                <span class='_warn1'>已打包</span>
	                          	<form:radiobutton path="packFlg" value="0"/>
	                                <span class='_warn2'>未打包</span>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">打包件数 :</label>
                           	<div class="controls">
                          		<form:input	path="packNum" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">打包单位 :</label>
                           	<div class="controls">
                           		<form:radiobutton path="packUnit" value="1"/>
	                                <span>箱</span>
	                          	<form:radiobutton path="packUnit" value="2"/>
	                                <span>袋</span>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">调入机构 :</label>
                            <div class="controls">
                            <select name="targetOrgId">
		                  	<c:forEach items="${orgList}" var="org">
		                  		<c:if test="${org.key == targetOrgId}">
		                   		<option value="${org.key }" selected>${org.value }</option>
		                      	</c:if>
		                		<c:if test="${org.key != targetOrgId}">
		                    	<option value="${org.key }">${org.value }</option>
		                     	</c:if>
		                	</c:forEach>
		             		</select>
		             		</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">调货类别 :</label>
                           	<div class="controls">
                           		<form:radiobutton path="freightType" value="1"/>
	                                <span class='_warn1'>普通</span>
	                          	<form:radiobutton path="freightType" value="2"/>
	                                <span class='_warn2'>限时</span>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">限时日期 :</label>
                           	<div class="controls">
                          		<form:input	path="limitedDate" />
                        	</div>
                        </div>
                        <c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
                        <div class="control-group">
                            <label class="control-label">审批人 :</label>
                           	<div class="controls">
                          		<form:input	path="approver" />
                        	</div>
                        </div>
                        </c:if>
                        <div class="control-group">
                            <div class="controls">
                                <%//总部 （保存按钮显示为【审批】） %>
		                        <c:if test="${sessionScope.__SESSION_USER_INFO.orgId == _ROOT_ORG_ID}">
		                        <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">审批</button>
		                        </c:if>
		                        <%//门店（保存按钮显示为【00申请】） %>
		                        <c:if test="${sessionScope.__SESSION_USER_INFO.orgId != _ROOT_ORG_ID}">
		                        <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
		                        </c:if>
                                &nbsp;<a href="${sc_ctx}/freight/list" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>