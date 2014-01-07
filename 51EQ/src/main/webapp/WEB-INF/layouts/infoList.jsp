<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tjhx.globals.Constants" %>
<script>
    $().ready(function() {
        $("#__del_confirm_Btn").click(function() {
            $('#__del_confirm').modal('hide');
            _del_confirm();
        });
    }); 
</script>
<c:if test="${!empty __SESSION_TIP_MSG_LIST || !empty __SESSION_WARN_MSG_LIST || !empty __SESSION_ERR_MSG_LIST}" >
    <script>
        $().ready(function() {
            $('#__dialog_message').modal({
                backdrop : true,
                keyboard : true,
                show : true
            });
        });
    </script>
</c:if>
<div class="modal hide fade __model37" id="__dialog_message">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h4>系统消息</h4>
    </div>
    <div class="modal-body">
        <center>
            <c:if test="${!empty __SESSION_TIP_MSG_LIST}" >
                <c:forEach items="${__SESSION_TIP_MSG_LIST}" var="tipMsg">
                    <p class="info">
                        <c:out value="${tipMsg}" />
                    </p>
                </c:forEach>
            </c:if>
            <c:if test="${!empty __SESSION_WARN_MSG_LIST}" >
                <c:forEach items="${__SESSION_WARN_MSG_LIST}" var="warnMsg">
                    <p class="success">
                        <c:out value="${warnMsg}" />
                    </p>
                </c:forEach>
            </c:if>
            <c:if test="${!empty __SESSION_ERR_MSG_LIST}" >
                <c:forEach items="${__SESSION_ERR_MSG_LIST}" var="errMsg">
                    <p class="error">
                        <c:out value="${errMsg}" />
                    </p>
                </c:forEach>
            </c:if>
        </center>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
    </div>
</div>

<div class="modal hide fade  __model37" id="__del_confirm">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h4>系统消息</h4>
    </div>
    <div class="modal-body">
        <center>
            <p class="error">
                确定要删除选择的信息项吗？
            </p>
        </center>
    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" id="__del_confirm_Btn" value="确定">
        <a href="#" class="btn" data-dismiss="modal">关闭</a>
    </div>
</div>