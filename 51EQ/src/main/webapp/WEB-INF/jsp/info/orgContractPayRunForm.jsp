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
        	$('#payDate').datepicker({
                format : 'yyyy-mm-dd'
            });
        	
        	$("#inputForm").validate({
                rules : {
                	orgId : {
                        required : true
                    },
                    payDate : {
                    	required : true,
                        date : true
                    },
                    payAmt : {
                    	required : true,
                        money : true
                    }
                }
            });
        	
        	$("#saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#inputForm").attr("target", "_self");
                $("#inputForm").attr("action", "${sc_ctx}/orgContractPayRun/save");
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
                        <h3>租金缴交信息
                        <c:if test="${empty	orgContractPayRun.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty orgContractPayRun.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
          		</div>
          		
          		<div class="span12"	style="margin-top: 10px;">
          			<form:form method="POST" class="form-horizontal" id="inputForm" modelAttribute="orgContractPayRun">
          				<form:hidden path="uuid"/>
          				<input type="hidden" name="selectOrgId" value="${selectOrgId}"/>
          				<div class="control-group">
                            <label class="control-label">机构 :</label>
                           	
                           	<c:if test="${empty	orgContractPayRun.uuid}">
                           	<div class="controls">
	                            <form:select path="orgId" items="${orgList}"/>
	                        </div>
	                        </c:if>
	                        
	                        <c:if test="${!empty orgContractPayRun.uuid}">
	                            <form:hidden path="orgId"/>
	                            
	                            <label class="left-control-label">
	                            <c:if test="${fn:length(orgContractPayRun.orgId) > 4}">
	             					${fn:substring(orgContractPayRun.orgId,3,6)}
	             				</c:if>
	             				<c:if test="${fn:length(orgContractPayRun.orgId) < 4}">
	             					总部
	             				</c:if>
	                            </label>
	                        </c:if>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">交费时间[预计] :</label>
                          	
                           	<div class="controls">
                            	<form:input	path="payDate" />
                          	</div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">交费金额 :</label>
                            <div class="controls">
                            	<form:input	path="payAmt" /> 元
                          	</div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">交费状态 :</label>
                            <div class="controls">
                            	<form:radiobutton path="payFlg" value="0"/> 未交费
                            	<form:radiobutton path="payFlg" value="1"/> 已交费
                          	</div>
                        </div>
                        
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/orgContract/init/?selectOrgId=${selectOrgId}" class="btn btn-large">返回</a>
                            </div>
                        </div>
          			
          			</form:form>
          		</div>
          	</div>
		</div>
	</body>
</html>