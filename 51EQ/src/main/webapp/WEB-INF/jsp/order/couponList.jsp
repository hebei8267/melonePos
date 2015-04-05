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
            ._warn1 {
	    		padding: 5px;
				background-color: #99FF33;
			}
			._warn2 {
				padding: 5px;
				background-color: #FFCC33;
			}
        </style>
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
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='uuid']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
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
                
                $("#listForm").attr('target', '_self');
                $("#listForm").attr("action", "${sc_ctx}/coupon/del");
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
                            <h3>代金卷管理</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <a href="${sc_ctx}/coupon/new"	class="btn btn-primary">新增</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th width="90">
                                        代金卷编号
                                    </th>
                                    <th width="160">
                                        代金卷名称
                                    </th>
                                    <th width="200">
                                        汇率（代金卷/现金）
                                    </th>
                                    <th>
                                        适用机构
                                    </th>
                                    <th class="center" width="120">
                                        统计至日销售额
                                    </th>
                                    <th class="center" width="90">
                                        有效标记
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${couponList}" var="coupon">
                                    <tr>
                                        <td	class="center">
                                          	<input type="checkbox" name="uuid" value="${coupon.uuid}">
                                                </input>
                                        </td>
                                        <td>
                                        	${coupon.couponNo}
                                        </td>
                                        <td>
                                            ${coupon.name}
                                        </td>
                                        <td>
                                            ${coupon.rate}
                                        </td>
                                        <td>
                                            ${coupon.orgId}
                                        </td>
                                        <td class="center">
                                            <c:if test="${coupon.subTotalFlg == true}">
                                                <span class='_warn1'>统计</span>
                                            </c:if>
                                            <c:if test="${coupon.subTotalFlg != true}">
                                                <span class='_warn2'>不统计</span>
                                            </c:if>
                                        </td>
                                        <td class="center">
                                            <c:if test="${coupon.delFlg != true}">
                                                <span class='_warn1'>有效</span>
                                            </c:if>
                                            <c:if test="${coupon.delFlg == true}">
                                                <span class='_warn2'>无效</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/coupon/edit/${coupon.couponNo}" class="btn btn-warning"/>修改</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	couponList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="8" class="rounded-foot-left">
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