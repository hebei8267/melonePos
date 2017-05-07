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
                        bankId : {
                            required : true
                        },
                        bankCardNo : {
                            required : true,
                            maxlength : 32
                        },
                        accountName: {
                            maxlength : 32
                        },
                        orgId : {
                            required : true
                        }
                    }
                });

                $("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr('target', '_self');
                    $("#inputForm").attr("action", "${sc_ctx}/bankCard/save");
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
                        <h3>银行卡信息
                        <c:if test="${empty	bankCard.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty bankCard.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="bankCard">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">银行 :</label>
                            <c:if test="${empty	bankCard.uuid}">
                                <div class="controls">
                                    <form:select path="bankId" items="${bankList}" />
                                </div>
                            </c:if>
                            <c:if test="${!empty bankCard.uuid}">
                                <label class="left-control-label">${bankCard.bank.name}</label>
                            </c:if>
                        </div>
                        <div class="control-group">
                            <label class="control-label">银行帐号 :</label>
                            <div class="controls">
                                <form:input	path="bankCardNo" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">开户人名称 :</label>
                            <div class="controls">
                                <form:input	path="accountName" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">适用机构 :</label>
                            <div class="controls">
                                <form:select path="orgId" items="${orgList}" />
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/bankCard" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
