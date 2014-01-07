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
            //-----------------------------------
            // 表单效验
            //-----------------------------------
            $("#listForm").validate({
                rules : {
                    delBtn : {
                        requiredSelect : 'uuid'
                    }
                }
            });
            //-----------------------------------
            // 全选/全部选
            //-----------------------------------
            $("#checkAll").click(function() {
                $('input[name="uuid"]').attr("checked", this.checked);
            });
            var $subCheckBox = $("input[name='uuid']");
            $subCheckBox.click(function() {
                $("#checkAll").attr("checked", $subCheckBox.length == $("input[name='uuid']:checked").length ? true : false);
            });

            //-----------------------------------
            // 删除按钮点击
            //-----------------------------------
            $("#delBtn").click(function() {
                if ($("#listForm").valid()) {
                    $('#__del_confirm').modal({
                        backdrop : true,
                        keyboard : true,
                        show : true
                    });
                }
            });
        });
        //-----------------------------------
        // 删除
        //-----------------------------------
        function _del_confirm() {
            var $subCheckBox = $("input[name='uuid']");
            var uuids = "";
            $.each($subCheckBox, function(index, _checkBox) {
                if (_checkBox.checked) {
                    uuids += _checkBox.value + ",";
                }
            });
            if (uuids.length > 0) {
                uuids = uuids.substring(0, uuids.length - 1);
            }

            $("#uuids").val(uuids);
            $("#listForm").attr("action", "${sc_ctx}/invoiceApply/del");
            $("#listForm").submit();
        }
    	</script>
    </head>
    <body>
    	<%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 发票申请信息</h3>
                        </legend>
                    </div>
                    <div class="span9">
                        <a href="${sc_ctx}/invoiceApply/new"	class="btn btn-primary">申请</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
                    <div class="span3 right_text">
                        <%String nowM =	DateUtils.getCurrentMonth(); %>
                        <%String lastM=	DateUtils.getNextMonthFormatDate(-1, "yyyy-MM"); %>(申请日期)
                        <a href="${sc_ctx}/invoiceApply/list/<%=nowM	%>"><%=nowM	%></a> | <a	href="${sc_ctx}/invoiceApply/list/<%=lastM %>"><%=lastM %></a>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th>
                                        申请时间
                                    </th>
                                    <th>
                                        申请人
                                    </th>
                                    <th>
                                        发票种类
                                    </th>
                                    <th>
                                        发票台头
                                    </th>
                                    <th>
                                        发票金额
                                    </th>
                                    <th width="80" class="center">
                                        邮寄客户
                                    </th>
                                    <th>
                                        发票号
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${invoiceApplyList}" var="invoiceApply">
                            		<tr>
                            			<td	class="center">
                                            <c:if test="${invoiceApply.invoiceStatus == '1'	}">
                                                <input type="checkbox" name="uuid" value="${invoiceApply.uuid}">
                                                </input>
                                            </c:if>
                                        </td>
                                        <td>
                                            ${invoiceApply.appDateShow}
                                        </td>
                                        <td>
                                            ${invoiceApply.appName}
                                        </td>
                                        <td>
                                        	<c:if test="${invoiceApply.invoiceType == 1}">
                                                机打
                                            </c:if>
                                            <c:if test="${invoiceApply.invoiceType == 2}">
                                                手写
                                            </c:if>
                                            <c:if test="${invoiceApply.invoiceType == 4}">
                                                其他
                                            </c:if>
                                        </td>
                                        <td>
                                            ${invoiceApply.title}
                                        </td>
                                        <td>
                                            ${invoiceApply.amt}
                                        </td>
                                        <td class="center">
                                        	<c:if test="${invoiceApply.needPost == 0}">
                                                否
                                            </c:if>
                                            <c:if test="${invoiceApply.needPost == 1}">
                                                是
                                            </c:if>
                                        </td>
                                        <td>
                                        	${invoiceApply.invoiceNum}
                                        </td>
                                        <td>
                                            <c:if test="${invoiceApply.invoiceStatus == '1'	}">
                                                <a href="${sc_ctx}/invoiceApply/edit/${invoiceApply.uuid}" class="btn btn-warning"/>修改</a>
                                            </c:if>
                                            <c:if test="${invoiceApply.invoiceStatus == '2'	}">
                                                <a href="${sc_ctx}/invoiceApply/view/${invoiceApply.uuid}" class="btn btn-primary"/>查看</a>
                                            </c:if>
                                        </td>
                            		</tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	invoiceApplyList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="9"	class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>