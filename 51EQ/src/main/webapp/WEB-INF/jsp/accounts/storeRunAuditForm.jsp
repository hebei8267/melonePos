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
                $("#saveBtn").click(function() {
                    $('#__storeRun_confirm').modal({
                        backdrop : true,
                        keyboard : true,
                        show : true
                    });
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
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName} 入库信息 审核</h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="storeRun">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">入库单号 :</label>
                            <label class="left-control-label">${storeRun.recordNo}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">供应商 :</label>
                            <label class="left-control-label">${storeRun.supplierName}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入库类型 :</label>
                            <label class="left-control-label">
                                <c:if test="${storeRun.storeType == 'A'}">
                                    挂账采购
                                </c:if>
                                <c:if test="${storeRun.storeType == 'B'}">
                                    现结采购
                                </c:if>
                                <c:if test="${storeRun.storeType == 'C'}">
                                    货商补欠
                                </c:if> </label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">开单日期 :</label>
                            <label class="left-control-label">${storeRun.recordDateShow}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入库日期 :</label>
                            <label class="left-control-label">${storeRun.intoDateShow}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">统筹日期 :</label>
                            <label class="left-control-label">${storeRun.planDateShow}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">开单金额	:</label>
                            <label class="left-control-label">${storeRun.recordAmt} 元</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入库金额	:</label>
                            <label class="left-control-label">${storeRun.optAmt} 元</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入库人	:</label>
                            <label class="left-control-label">${storeRun.optPerName}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注 :</label>
                            <label class="left-control-label">${storeRun.descTxt}</label>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="button">审核</button>
                                &nbsp;<a href="${sc_ctx}/storeRunAudit" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <div class="modal hide fade  __model37" id="__storeRun_confirm">
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4>系统消息</h4>
            </div>
            <div class="modal-body">
                <center>
                    <p class="error">
                        审核通过该笔入库流水信息吗？
                    </p>
                </center>
            </div>
            <div class="modal-footer">
                <a href="${sc_ctx}/storeRunAudit/confirm/${storeRun.uuid}" class="btn btn-primary">确定</a>
                <a href="#" class="btn" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </body>
</html>
