<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            	$('#actReturnDate').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $("#inputForm").validate({
					rules : {
	                	actReturnDate : {
	                   		required : true,
	                    	date : true
	                	},
	                	returnPeople : {
	                   		required : true,
	                    	maxlength : 32
	                	},
	                	returnAttn : {
	                    	required : true,
	                    	maxlength : 32
	                	}
					}
				});
				
				$("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr("target", "_self");
                    $("#inputForm").attr("action", "${sc_ctx}/borrowItemRun/save");
                    $("#inputForm").submit();
                });
            });
        </script>
	</head>
	<body>
		<div class="container">
            <div class="row">
                <div class="span12">
                    <legend>
                        <h3>物件-还入</h3>
                    </legend>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                	<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="borrowItemRun">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">物件编号 :</label>
                           	<div class="controls">
                           		<input id="itemNo" name="itemNo" type="hidden" value="${borrowItem.itemNo}"/>
		                      	<label class="left-control-label">${borrowItem.itemNo}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">物件名称 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${borrowItem.itemName}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">物件类型 :</label>
                           	<div class="controls">
                           		<c:if test="${borrowItem.itemType == '01'}" >
                            	<label class="left-control-label">证书</label>
                            	</c:if>
                            	<c:if test="${borrowItem.itemType == '02'}" >
                            	<label class="left-control-label">执照</label>
                            	</c:if>
                            	<c:if test="${borrowItem.itemType == '03'}" >
                            	<label class="left-control-label">公章</label>
                            	</c:if>
                            	<c:if test="${borrowItem.itemType == '04'}" >
                            	<label class="left-control-label">押金收据</label>
                            	</c:if>
                            	<c:if test="${borrowItem.itemType == '99'}" >
                            	<label class="left-control-label">其他</label>
                            	</c:if>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">所属机构 :</label>
                           	<div class="controls">
                           		<c:if test="${fn:length(borrowItem.orgId) > 4}">
		             				<label class="left-control-label">${fn:substring(borrowItem.orgId,3,6)}</label>
		             			</c:if>
		             			<c:if test="${fn:length(borrowItem.orgId) < 4}">
		             				<label class="left-control-label">总部</label>
		             			</c:if>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">接收时间 :</label>
                           	<div class="controls">
                           		<fmt:parseDate value="${borrowItem.optDate}" var="_optDate" pattern="yyyyMMdd" />
                           		<label class="left-control-label">
                            		<fmt:formatDate pattern="yyyy-MM-dd" value="${_optDate}" />
                            	</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">借用人 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${borrowItemRun.borrower}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">借用日期 :</label>
                           	<div class="controls">
                           		<fmt:parseDate value="${borrowItemRun.borrowDate}" var="_borrowDate" pattern="yyyyMMdd" />
                           		<label class="left-control-label">
                           			<fmt:formatDate pattern="yyyy-MM-dd" value="${_borrowDate}" />
                           		</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">借用-经办人 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${borrowItemRun.borrowAttn}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">用途 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${borrowItemRun.useDesc}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">预计归还日期 :</label>
                           	<div class="controls">
                           		<fmt:parseDate value="${borrowItemRun.expReturnDate}" var="_expReturnDate" pattern="yyyyMMdd" />
                           		<label class="left-control-label">
                           			<fmt:formatDate pattern="yyyy-MM-dd" value="${_expReturnDate}" />
                           		</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">实际归还日期 :</label>
                           	<div class="controls">
                           		<form:input	path="actReturnDate" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">归还人 :</label>
                           	<div class="controls">
                           		<form:input	path="returnPeople" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">归还-经办人 :</label>
                           	<div class="controls">
                           		<form:input	path="returnAttn" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                            	<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>&nbsp;
                                <a href="${sc_ctx}/borrowItemRun/list/${borrowItem.itemNo}" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
	</body>
</html>