<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<%@	page import="com.tjhx.globals.Constants"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<link rel="stylesheet" href="${ctx}/static/fontAwesome/css/font-awesome.css">
    	<link rel="stylesheet" href="${ctx}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
        <script src="${ctx}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${ctx}/static/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
        <c:if test="${freightApp.editFlg == 2}">
        <link type="text/css" href="${ctx}/static/css/bootstrap-switch.css" rel="stylesheet">
        </c:if>
        <script src="${ctx}/static/js/bootstrap-switch.js"></script>
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
				padding-left: 0px;
			}
			.input-append .add-on,
			.input-prepend .add-on {
  				height: 26px;
			}
			[class^="icon-"], [class*=" icon-"] {
				margin-top: 6px;
			}
        </style>
	    <script>

	    	$(function() {
	     		
	     		$("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr('target', '_self');
                    $("#inputForm").attr("action", "${sc_ctx}/freight/viewSave");
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
                        <c:if test="${freightApp.editFlg == 1}">
                            预计收货
                        </c:if>
                        <c:if test="${freightApp.editFlg == 2}">
                            实际收货
                        </c:if>
                        <c:if test="${freightApp.editFlg == 3}">
                            预计送货
                        </c:if>
                        <c:if test="${freightApp.editFlg == 4}">
                            实际送货
                        </c:if>
                        </h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                	
                	<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="freightApp">
                        <form:hidden path="uuid"/>
                        <input type="hidden" id="editFlg" name="editFlg" value="${freightApp.editFlg}">
                        <div class="control-group">
                            <label class="control-label">申请日期 :</label>
                           	<div class="controls">
		                        <fmt:parseDate value="${freightApp.appDate}" var="_appDate" pattern="yyyyMMdd" />
		                        <label class="left-control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${_appDate}" /></label>
                        	</div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">调出机构 :</label>
                            <div class="controls">
                            	<c:if test="${fn:length(freightApp.appOrgId) > 4}">
		             			<label class="left-control-label">${fn:substring(freightApp.appOrgId,3,6)}</label>
		             			</c:if>
		             			<c:if test="${fn:length(freightApp.appOrgId) < 4}">
		             			<label class="left-control-label">总部</label>
		             			</c:if>
		             		</div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">调入机构 :</label>
                            <div class="controls">
		             			<c:if test="${fn:length(freightApp.targetOrgId) > 4}">
		             			<label class="left-control-label">${fn:substring(freightApp.targetOrgId,3,6)}</label>
		             			</c:if>
		             			<c:if test="${fn:length(freightApp.targetOrgId) < 4}">
		             			<label class="left-control-label">总部</label>
		             			</c:if>
		             		</div>
                        </div>
                       	
                        <div class="control-group">
                            <label class="control-label">申请人 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${freightApp.applicant}</label>
                        	</div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">是否打包 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">
                           		<c:if test="${freightApp.packFlg == 1}">已打包</c:if>
                           		<c:if test="${freightApp.packFlg == 0}">未打包</c:if>
                           		</label>
                        	</div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">打包件数 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${freightApp.packNum}</label>
                        	</div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">打包单位 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">
                           		<c:if test="${freightApp.packUnit == 1}">箱</c:if>
                           		<c:if test="${freightApp.packUnit == 2}">袋</c:if>
                           		</label>
                        	</div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label">调货类别 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">
                           		<c:if test="${freightApp.freightType == 1}">普通</c:if>
                           		<c:if test="${freightApp.freightType == 2}">限时</c:if>
                           		</label>
                        	</div>
                        </div>
                        
                        <%//限时 %>
                        <c:if test="${freightApp.freightType == 2}">
                        <div class="control-group">
                            <label class="control-label">限时日期 :</label>
                           	<div class="controls">
                           		<fmt:parseDate value="${freightApp.limitedDate}" var="_limitedDate" pattern="yyyyMMdd" />
                           		<label class="left-control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${_limitedDate}" /></label>
                           	</div>
                        </div>
                        </c:if>
                        
                        <div class="control-group">
                            <label class="control-label">审批人 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${freightApp.approver}</label>
                        	</div>
                        </div>
                        
                        <c:if test="${freightApp.editFlg == 1}">
                        <div class="control-group">
                            <label class="control-label">预计收货时间 :</label>
                            <div class="controls">
                            	<input size="16" name="expReceiptDate" type="text" value="${freightApp.expReceiptDate}" class="expReceiptDate">
								<script type="text/javascript">
								    $(".expReceiptDate").datetimepicker({
								    	format: 'yyyy-mm-dd hh:ii',
								    	todayBtn: 1,
								    	autoclose: 1,
								    	todayHighlight: 1,
								    	language: 'zh-CN'});
								</script>
							</div>
                        </div>
                        </c:if>
                        <c:if test="${freightApp.editFlg != 1}">
                        <div class="control-group">
                        	<label class="control-label">预计收货时间 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${freightApp.expReceiptDate}</label>
                        	</div>
                        </div>
                        </c:if>
                        
                        <c:if test="${freightApp.editFlg == 2}">
                        <div class="control-group">
                            <label class="control-label">实际收货 :</label>
                            <div class="controls">
								<div class="switch switch-large" data-on-label="收货" data-off-label="未收">
                					<input name="actReceiptDateChkFlg" type="checkbox" value="1" />
            					</div>
							</div>
						</div>
                        </c:if>
                        <c:if test="${freightApp.editFlg != 2}">
                        <div class="control-group">
                        	<label class="control-label">实际收货时间 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${freightApp.actReceiptDate}</label>
                        	</div>
                        </div>
                        </c:if>
                        
                        <c:if test="${freightApp.editFlg == 3}">
                        <div class="control-group">
                            <label class="control-label">预计送货时间 :</label>
                            <div class="controls">
                            	<input name="expDeliveryDate" type="text" value="${freightApp.expDeliveryDate}" readonly class="expDeliveryDate">
								<script type="text/javascript">
								    $(".expDeliveryDate").datetimepicker({
								    	format: 'yyyy-mm-dd hh:ii',
								    	todayBtn: 1,
								    	autoclose: 1,
								    	todayHighlight: 1,
								    	language: 'zh-CN'});
								</script>
							</div>
						</div>
                        </c:if>
                        <c:if test="${freightApp.editFlg != 3}">
                        <div class="control-group">
                        	<label class="control-label">预计送货时间 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${freightApp.expDeliveryDate}</label>
                        	</div>
                        </div>
                        </c:if>
                        
                        <c:if test="${freightApp.editFlg == 4}">
                        <div class="control-group">
                            <label class="control-label">实际送货 :</label>							
							<div class="controls">
								<div class="switch switch-large" data-on-label="送货" data-off-label="未送">
                					<input name="actDeliveryDateChkFlg" type="checkbox" value="1" />
            					</div>
							</div>
						</div>
                        </c:if>
                        <c:if test="${freightApp.editFlg != 4}">
                        <div class="control-group">
                        	<label class="control-label">实际送货时间 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${freightApp.actDeliveryDate}</label>
                        	</div>
                        </div>
                        </c:if>
                        
                        <div class="control-group">
                            <div class="controls">
		                        <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
		                        
                                &nbsp;<a href="${sc_ctx}/freight/viewList" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>