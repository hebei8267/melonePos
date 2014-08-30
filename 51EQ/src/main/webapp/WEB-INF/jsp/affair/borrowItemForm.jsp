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
    	<script>
            $(function() {
            	$('#optDate').datepicker({
                    format : 'yyyy-mm-dd'
                });
                
                $("#inputForm").validate({
					rules : {
						itemNo : {
	                    	required : true,
	                		maxlength : 8
	                	},
	                	itemName : {
	                   		required : true,
	                    	maxlength : 32
	                	},
	                	itemType : {
	                    	required : true
	                	},
	                	orgId : {
	                    	required : true
	                	},
	                	optDate : {
	                   		required : true,
	                    	date : true
	                	},
	                	descTxt : {
	                    	maxlength : 255
	                	}
					}
				});
				
				$("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr("target", "_self");
                    $("#inputForm").attr("action", "${sc_ctx}/borrowItem/save");
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
                        <h3>物件借还
                        <c:if test="${empty	borrowItem.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty borrowItem.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                	<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="borrowItem">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">物件编号 :</label>
                           	<div class="controls">
                           		<c:if test="${empty	borrowItem.uuid}">
	                            	<form:input	path="itemNo" />
		                        </c:if>
		                        <c:if test="${!empty borrowItem.uuid}">
		                            <label class="left-control-label">${borrowItem.itemNo}</label>
	                        	</c:if>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">物件名称 :</label>
                           	<div class="controls">
                           		<form:input	path="itemName" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">物件类型 :</label>
                           	<div class="controls">
                           		<select name="itemType">
			                		<c:forEach items="${itemTypeList}" var="itemType">
			                   		<c:if test="${itemType.key == borrowItem.itemType}">
			                      		<option value="${itemType.key }" selected>${itemType.value }</option>
			                     	</c:if>
			                     	<c:if test="${itemType.key != borrowItem.itemType}">
			                         	<option value="${itemType.key }">${itemType.value }</option>
			                      	</c:if>
			                  		</c:forEach>
			                 	</select>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">所属机构 :</label>
                           	<div class="controls">
                           		<select name="orgId">
			                		<c:forEach items="${orgList}" var="org">
			                   		<c:if test="${org.key == borrowItem.orgId}">
			                      		<option value="${org.key }" selected>${org.value }</option>
			                     	</c:if>
			                     	<c:if test="${org.key != borrowItem.orgId}">
			                         	<option value="${org.key }">${org.value }</option>
			                      	</c:if>
			                  		</c:forEach>
			                 	</select>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">接收时间 :</label>
                           	<div class="controls">
                           		<form:input	path="optDate" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注 :</label>
                           	<div class="controls">
                           		<form:textarea path="descTxt" class="input-xlarge" rows="4"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                            	<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>&nbsp;
                                <a href="${sc_ctx}/borrowItem/list" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>