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
            	$("#listForm").validate({
                    rules : {
                    	orgId : {
                    		required : true
                        }
                    }
                });
            	
                $('#recordDateShow').datepicker({
                    format : 'yyyy-mm',
                    viewMode : 1,
                    minViewMode : 1
                });

                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/storeReport/search");
                    $("#listForm").submit();
                });
            });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>入库信息</h3>
                        </legend>
                    </div>
                    <div class="span3">
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
                    </div>
                    <div class="span3">
                        <label class="control-label">开单日期 :</label>
                        <input id="recordDateShow" name="recordDateShow" type="text" class="input-medium" value="${recordDateShow }"/>
                    </div>
                    <div class="span6">
                        <label class="control-label">入库单号 :</label>
                        <input name="recordNo" type="text" class="input-medium" value="${recordNo }"/>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th>
                                        机构
                                    </th>
                                    <th>
                                        入库单号
                                    </th>
                                    <th>
                                        供应商
                                    </th>
                                    <th>
                                        入库类型
                                    </th>
                                    <th>
                                        开单日期
                                    </th>
                                    <th>
                                        入库日期
                                    </th>
                                    <th>
                                        统筹日期
                                    </th>
                                    <th>
                                        开单金额
                                    </th>
                                    <th>
                                        入库金额
                                    </th>
                                    <th>
                                        入库人
                                    </th>
                                    <th>
                                        审核
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${storeRunList}" var="storeRun">
                                    <tr>
                                        <td>
                                            ${storeRun.orgName}
                                        </td>
                                        <td>
                                            ${storeRun.recordNo}
                                        </td>
                                        <td>
                                            ${storeRun.supplierName}
                                        </td>
                                        <td>
                                            <c:if test="${storeRun.storeType == 'A'}">
                                                挂账采购
                                            </c:if>
                                            <c:if test="${storeRun.storeType == 'B'}">
                                                现结采购
                                            </c:if>
                                            <c:if test="${storeRun.storeType == 'C'}">
                                                货商补欠
                                            </c:if>
                                        </td>
                                        <td>
                                            ${storeRun.recordDateShow}
                                        </td>
                                        <td>
                                            ${storeRun.intoDateShow}
                                        </td>
                                        <td>
                                            ${storeRun.planDateShow}
                                        </td>
                                        <td <c:if test="${storeRun.recordAmt != storeRun.optAmt}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                            ${storeRun.recordAmt}
                                        </td>
                                        <td <c:if test="${storeRun.recordAmt != storeRun.optAmt}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                            ${storeRun.optAmt}
                                        </td>
                                        <td>
                                            ${storeRun.optPerName}
                                        </td>
                                        <td>
                                            <c:if test="${storeRun.auditFlg == 'true'}">
                                                已审核
                                            </c:if>
                                            <c:if test="${storeRun.auditFlg == 'false'}">
                                                未审核
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/storeReport/detail/${storeRun.recordNo}" target="_blank" class="btn btn-warning"/>详细</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${!empty storeRunList}" >
                                    <tr>
                                        <td	colspan="7">
                                            合计:
                                        </td>
                                        <td>
                                            ${totalStoreRun.recordAmt}
                                        </td>
                                        <td>
                                            ${totalStoreRun.optAmt}
                                        </td>
                                        <td	colspan="3"></td>
                                    </tr>
                                </c:if>
                            </tbody>
                            <c:if test="${empty	storeRunList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="12"	class="rounded-foot-left">
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