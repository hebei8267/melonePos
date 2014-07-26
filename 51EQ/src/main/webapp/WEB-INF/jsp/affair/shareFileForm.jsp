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
			$().ready(function() {
				$("#inputForm").validate({
					rules : {
						fileNo : {
	                         required : true,
	                         maxlength : 8
	                	},
	                	fileName : {
	                         required : true,
	                         maxlength : 64
	                	},
	                	fileShortName : {
	                         required : true,
	                         maxlength : 32
	                	},
	                	status : {
	                         required : true
	                	},
	                	file : {
	                         required : true
	                	}
					}
				});
				
				
				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});
					
					$("#inputForm").attr("target", "_self");
					$("#inputForm").attr("action", "${sc_ctx}/shareFile/save");
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
                        <h3>共享文件
                        <c:if test="${empty	shareFile.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty shareFile.uuid}">
                            编辑
                        </c:if>
                        </h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                	<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="shareFile" enctype="multipart/form-data">
                        <form:hidden path="uuid"/>
                        
                        <div class="control-group">
                            <label class="control-label">编号 :</label>
                            <c:if test="${empty	shareFile.uuid}">
                                <div class="controls">
                                    <form:input	path="fileNo" class="input-medium"/>
                                </div>
                            </c:if>
                            <c:if test="${!empty shareFile.uuid}">
                                <label class="left-control-label">${shareFile.fileNo}</label>
                            </c:if>
                        </div>
                        <div class="control-group">
                            <label class="control-label">名称 :</label>
                         	<div class="controls">
                           		<form:input	path="fileName" class="input-xxlarge"/>
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">简称 :</label>
                         	<div class="controls">
                           		<form:input	path="fileShortName" class="input-large"/>
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">状态 :</label>
                         	<div class="controls">
                              	<select name="status" class="input-medium">
		                            <c:forEach items="${statusList}" var="_status">
		                                <c:if test="${_status.key == shareFile.status}">
		                                    <option value="${_status.key }" selected>${_status.value }</option>
		                                </c:if>
		                                <c:if test="${_status.key != shareFile.status}">
		                                    <option value="${_status.key }">${_status.value }</option>
		                                </c:if>
		                            </c:forEach>
		                        </select>
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">上传文件 :</label>
                            <c:if test="${empty	shareFile.uuid}">
                                <div class="controls">
                                    <input type="file" name="file"/>
                                </div>
                            </c:if>
                            <c:if test="${!empty shareFile.uuid}">
                                <label class="left-control-label">${shareFile.storePath}</label>
                            </c:if>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                            	<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>&nbsp;
                                <a href="${sc_ctx}/shareFile/managerList" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>