<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
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
        </style>
        <script>
        $(function() {
        	$('#startDate').datepicker({
                format : 'yyyy-mm-dd'
            });
        	
        	$('#endDate').datepicker({
                format : 'yyyy-mm-dd'
            });
        	
        	$("#inputForm").validate({
                rules : {
                	orgId : {
                        required : true
                    },
                    startDate : {
                    	required : true,
                        date : true
                    },
                    endDate : {
                    	required : true,
                		date : true,
                		compareDate : "#startDate"
                    },
                    payFrequent : {
                    	required : true
                    },
                    computePayAmt : {
                    	required : true,
                        money : true
                    },
                    terms : {
                    	maxlength : 255
                    }
                }
            });
        	
        	$("#saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#inputForm").attr("target", "_self");
                $("#inputForm").attr("action", "${sc_ctx}/orgContract/save");
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
                        <h3>机构合同信息
                        <c:if test="${empty	orgContract.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty orgContract.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
          		</div>
          		
          		<div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm" modelAttribute="orgContract">
                    	<form:hidden path="uuid"/>
                    	
                    	<div class="control-group">
                            <label class="control-label">机构 :</label>
                           	
                           	<c:if test="${empty	orgContract.uuid}">
                           	<div class="controls">
	                            <form:select path="orgId" items="${orgList}"/>
	                        </div>
	                        </c:if>
	                        
	                        <c:if test="${!empty orgContract.uuid}">
	                            <form:hidden path="orgId"/>
	                            
	                            <label class="left-control-label">
	                            <c:if test="${fn:length(orgContract.orgId) > 4}">
	             					${fn:substring(orgContract.orgId,3,6)}
	             				</c:if>
	             				<c:if test="${fn:length(orgContract.orgId) < 4}">
	             					总部
	             				</c:if>
	                            </label>
	                        </c:if>
                        </div>
                        <div class="control-group">
                            <label class="control-label">[合同] 开始时间 :</label>
                            
                          	
                          	<c:if test="${empty	orgContract.uuid}">
                           	<div class="controls">
                            	<form:input	path="startDate" />
                          	</div>
	                        </c:if>
	                        
	                        <c:if test="${!empty orgContract.uuid}">
	                           	<form:hidden path="startDate"/>
	                            <label class="left-control-label">${orgContract.startDate}</label>
	                        </c:if>
	                        
                        </div>
                        <div class="control-group">
                            <label class="control-label">[合同] 结束时间 :</label>
                            <div class="controls">
                            	<form:input	path="endDate" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">缴交频率 :</label>
                            <div class="controls">
                            	<form:select path="payFrequent" items="${payFrequentList}"/>
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">预估缴交金额 :</label>
                            <div class="controls">
                            	<form:input	path="computePayAmt" /> 元
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">优惠条款 :</label>
                            <div class="controls">
                            	<form:textarea path="terms" class="input-xlarge" rows="4"/>
                          	</div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/orgContract/init" class="btn btn-large">返回</a>
                            </div>
                        </div>
              		</form:form>
            	</div>
      		</div>
   		</div>
	</body>
</html>