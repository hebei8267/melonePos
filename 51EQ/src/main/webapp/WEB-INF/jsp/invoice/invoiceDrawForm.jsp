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

                $("#inputForm").validate({
                    rules : {
                    	invoiceNum : {
                    		required : true,
                    		maxlength : 32,
                    		minlength : 8
                        },
                        invoiceSrc : {
                        	maxlength : 64
                        },
                        descTxt : {
                        	maxlength : 255
                        }
                    }
                });
                $("#needPost").click(function() {
                	if (this.checked) {
                		$("#needPost").val("1");
                    } else {
                    	$("#needPost").val("0");
                    }
                });
                $("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr('target', '_self');
                    $("#inputForm").attr("action", "${sc_ctx}/invoiceDraw/draw");
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
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName} 发票开具
                        </h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="invoice">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">申请机构 :</label>
                            <label class="left-control-label">${invoice.orgName}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">申请人 :</label>
                            <label class="left-control-label">${invoice.appName}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">申请日期 :</label>
                            <label class="left-control-label">${invoice.appDateShow}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票种类 :</label>
                            <c:if test="${invoice.invoiceType == 1}">
                            <label class="left-control-label">机打</label>
                            </c:if>
                            <c:if test="${invoice.invoiceType == 2}">
                            <label class="left-control-label">手写</label>
                            </c:if>
                            <c:if test="${invoice.invoiceType == 4}">
                            <label class="left-control-label">其他</label>
                            </c:if>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票台头 :</label>
                            <label class="left-control-label">${invoice.title}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票内容 :</label>
                            <label class="left-control-label">${invoice.content}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票金额 :</label>
                            <label class="left-control-label">${invoice.amt}</label>
                        </div>
                        <div class="control-group">
							<label class="control-label">税号 :</label>
							<label class="left-control-label">${invoice.taxNo}</label>
						</div>
                        <div class="control-group">
                            <label class="control-label">送达期限 :</label>
                            <label class="left-control-label">${invoice.serviceDateShow}</label>
                        </div>
                        <c:if test="${invoice.needPost == 1}">
                        <div class="control-group">
                            <label class="control-label">客户姓名 :</label>
                            <label class="left-control-label"><span class="warn_text">${invoice.customerName}</span></label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">客户电话 :</label>
                            <label class="left-control-label"><span class="warn_text">${invoice.customerTel}</span></label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">客户地址 :</label>
                            <label class="left-control-label"><span class="warn_text">${invoice.customerAdd}</span></label>
                        </div>
                        <div class="control-group">
							<label class="control-label">快递公司 :</label>
							<div class="controls">
                                <select name="expressCompany" class="input-medium">
		                            <c:forEach items="${companyList}" var="company">
		                                <c:if test="${company.key == invoice.expressCompany}">
		                                    <option value="${company.key }" selected>${company.value }</option>
		                                </c:if>
		                                <c:if test="${company.key != invoice.expressCompany}">
		                                    <option value="${company.key }">${company.value }</option>
		                                </c:if>
		                            </c:forEach>
		                        </select>
                            </div>
						</div>
                        </c:if>
                        
                        <div class="control-group">
                            <label class="control-label">发票号	:</label>
                            <div class="controls">
                                <form:input	path="invoiceNum" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票来源	:</label>
                            <div class="controls">
                                <form:input	path="invoiceSrc" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注	:</label>
                            <div class="controls">
                                <form:textarea path="descTxt" class="input-large" rows="4"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/invoiceDraw" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
