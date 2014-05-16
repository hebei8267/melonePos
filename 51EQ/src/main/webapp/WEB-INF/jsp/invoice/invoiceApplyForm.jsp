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
                $('#serviceDateShow').datepicker({
                    format : 'yyyy-mm-dd'
                });
                
                <c:if test="${invoice.needPost == 1}">
                $("#needPost").attr("checked", "true");
            	</c:if>

                $("#inputForm").validate({
                    rules : {
                    	appName : {
                    		required : true,
                    		maxlength : 32
                        },
                        invoiceType : {
                            required : true
                        },
                        title : {
                        	required : true,
                    		maxlength : 64
                        },
                        content : {
                        	required : true,
                    		maxlength : 255
                        },
                        amt : {
                            required : true,
                            money : true
                        },
                        customerName : {
                            myRequired : "#needPost",
                            maxlength : 64
                        },
                        customerTel : {
                            myRequired : "#needPost",
                            isPhone : true
                        },
                        customerAdd : {
                            myRequired : "#needPost",
                            maxlength : 64
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
                    $("#inputForm").attr("action", "${sc_ctx}/invoiceApply/save");
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
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName} 发票申请
                        </h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="invoice">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">申请机构 :</label>
                            <label class="left-control-label">${sessionScope.__SESSION_USER_INFO.orgName}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">申请人	:</label>
                            <div class="controls">
                                <form:input	path="appName" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票种类	:</label>
                            <div class="controls">
                                <form:select path="invoiceType" items="${invoiceTypeList}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票台头	:</label>
                            <div class="controls">
                                <form:input	path="title" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票内容	:</label>
                            <div class="controls">
                                <form:textarea path="content" class="input-large" rows="4"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发票金额	:</label>
                            <div class="controls">
                                <form:input	path="amt" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">送达期限	:</label>
                            <div class="controls">
                                <form:input	path="serviceDateShow" />
                            </div>
                        </div>
                        <div class="control-group" style="margin: -15px 0 0 -30px;">
                        	<div class="span4 cash_daily"></div>
                        </div>
                        <div class="control-group">
                        	<label class="checkbox">
                        		<input type="checkbox" name="needPost" id="needPost" value="${invoice.needPost}">是否邮寄客户
    						</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">客户姓名	:</label>
                            <div class="controls">
                                <form:input	path="customerName" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">客户电话	:</label>
                            <div class="controls">
                                <form:input	path="customerTel" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">客户地址	:</label>
                            <div class="controls">
                                <form:input	path="customerAdd" />
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/invoiceApply" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
