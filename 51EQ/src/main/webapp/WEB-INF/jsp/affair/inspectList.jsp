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
		._warn3 {
			padding: 5px;
			background-color: #FF3300;
		}
    	</style>
        <script>
            $(function() {
                $("#searchForm").validate({
                    rules : {
                        optDateShow_start : {
                            required : true,
                            date : true
                        },
                        optDateShow_end : {
                            required : true,
                            date : true,
                            compareDate : "#optDateShow_start"
                        }
                    }
                });
                
                $("#listForm").validate({
                    rules : {
                        delBtn : {
                            requiredSelect : 'uuid'
                        }
                    }
                });

                $('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
                });

                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#searchForm").attr("action", "${sc_ctx}/inspect/search");
                    $("#searchForm").submit();
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
                $("#listForm").attr("action", "${sc_ctx}/inspect/del");
                $("#listForm").submit();
            }
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <div class="row">
                <form method="post"	class="form-inline"	id="searchForm">
                    <div class="span12">
                        <legend>
                            <h3>门店巡查报告</h3>
                        </legend>
                    </div>
                    <div class="span5">
                        <label class="control-label">巡查日期 :</label>
                        <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                        ～
                        <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                    </div>
                    <div class="span7">
                        <label class="control-label">机构 :</label>
                        <select name="orgId" class="input-medium">
                            <c:forEach items="${orgList}" var="org">
                                <c:if test="${org.key == orgId}">
                                    <option value="${org.key }" selected>${org.value }</option>
                                </c:if>
                                <c:if test="${org.key != orgId}">
                                    <option value="${org.key }">${org.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                </form>
                
                <form method="post" class="form-inline" id="listForm">
                    <div class="span12" style="padding-top: 10px;">
                        <a href="${sc_ctx}/inspect/new"	class="btn btn-primary">新增</a>
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
                                    <th>
                                        报告流水号
                                    </th>
                                    <th>
                                        巡查日期
                                    </th>
                                    <th>
                                        巡查人
                                    </th>
                                    <th>
                                        巡查机构
                                    </th>
                                    <th>
                                        当班负责人
                                    </th>
                                    <th>
                                        结论(留存现金)
                                    </th>
                                    <th>
                                        结论(备用金)
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${inspectList}" var="inspect">
                                    <tr>
                                        <td	class="center">
                                            <input type="checkbox" name="uuid" value="${inspect.uuid}">
                                        </td>
                                        <td>
                                            ${inspect.trsId}
                                        </td>
                                        <td>
                                            ${inspect.optDateShow}
                                        </td>
                                        <td>
                                            ${inspect.inspectPer}
                                        </td>
                                        <td>
                                            ${inspect.orgId}
                                        </td>
                                        <td>
                                            ${inspect.dutyPer}
                                        </td>
                                        <td>
                                            <c:if test="${inspect.cashConclusion == 1}">
                                                <span class='_warn1'>完全相等</span>
                                            </c:if>
                                            <c:if test="${inspect.cashConclusion == 2}">
                                                <span class='_warn2'>基本相等</span>
                                            </c:if>
                                            <c:if test="${inspect.cashConclusion == 4}">
                                                <span class='_warn3'>不符，调查原因</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${inspect.imprestConclusion == 1}">
                                                <span class='_warn1'>完全相等</span>
                                            </c:if>
                                            <c:if test="${inspect.imprestConclusion == 2}">
                                                <span class='_warn2'>基本相等</span>
                                            </c:if>
                                            <c:if test="${inspect.imprestConclusion == 4}">
                                                <span class='_warn3'>不符，调查原因</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/inspect/edit/${inspect.uuid}" class="btn btn-warning"/>修改</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	inspectList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="9" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>