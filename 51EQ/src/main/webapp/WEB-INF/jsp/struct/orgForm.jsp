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
        <style type="text/css">
            .form-horizontal .control-label {
                width: 180px;
            }
            .form-horizontal .controls {
                margin-left: 195px;
            }
        </style>
        <script>
            $(function() {
                $("#inputForm").validate({
                    rules : {
                        bwId : {
                            required : true,
                            maxlength : 16
                        },
                        zkId : {
                            required : true,
                            digits : true
                        },
                        name : {
                            required : true,
                            maxlength : 32
                        },
                        orgAdd : {
                            maxlength : 64
                        },
                        orgAddShort : {
                            maxlength : 32
                        }
                    }
                });

                $("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr('target', '_self');
                    $("#inputForm").attr("action", "${sc_ctx}/organization/save");
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
                        <h3>机构信息
                        <c:if test="${empty	org.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty org.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="org">
                        <form:hidden path="uuid"/>
                        <c:if test="${org.uuid != 1}">
                            <div class="control-group">
                                <label class="control-label">父层机构 :</label>
                                <label class="left-control-label">${rootOrg.name}</label>
                            </div>
                        </c:if>
                        <div class="control-group">
                            <label class="control-label">机构编号 :</label>
                            <c:if test="${empty	org.uuid}">
                                <div class="controls">
                                    <form:input	path="bwId" />
                                </div>
                            </c:if>
                            <c:if test="${!empty org.uuid}">
                                <label class="left-control-label">${org.bwId}</label>
                            </c:if>
                        </div>
                        <div class="control-group">
                            <label class="control-label">机构编号(中控) :</label>
                            <div class="controls">
                                <form:input	path="zkId" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">机构资金编号(百威):</label>
                            <div class="controls">
                                <form:input	path="bwBranchNo" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">机构名称 :</label>
                            <div class="controls">
                                <form:input	path="name" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">机构地址缩写 :</label>
                            <div class="controls">
                                <form:input	path="orgAddShort" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">机构地址 :</label>
                            <div class="controls">
                                <form:input	path="orgAdd" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">门店品牌 :</label>
                            <div class="controls">
                                <form:select path="brand" items="${brandList}" />
                            </div>
                        </div>
                        <div class="control-group">
							<label class="control-label">状态 :</label>
							<div class="controls">
								<form:radiobutton path="closedFlg" value="true"/>
								正常
								<form:radiobutton path="closedFlg" value="false"/>
								闭店
							</div>
						</div>
                        <div class="control-group">
                            <label class="control-label">督导员 :</label>
                            <div class="controls">
                            	<form:select path="mngUserId" items="${mngUserList}" />
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/organization" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
