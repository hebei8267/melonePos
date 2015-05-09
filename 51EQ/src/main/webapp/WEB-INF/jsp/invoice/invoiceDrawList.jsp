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
                $('#appDateShow').datepicker({
                    format : 'yyyy-mm',
                    viewMode : 1,
                    minViewMode : 1
                });

                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/invoiceDraw/search");
                    $("#listForm").submit();
                });
                
                $("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/invoiceDraw/export");
                    $("#listForm").submit();
                });
            });
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
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 发票开具信息</h3>
                        </legend>
                    </div>
                    <div class="span3">
                        <label class="control-label">申请机构 :</label>
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
                        <label class="control-label">申请时间 :</label>
                        <input id="appDateShow" name="appDateShow" type="text" class="input-medium" value="${appDateShow }"/>
                    </div>
                    <div class="span6">
                        <label class="control-label">发票号 :</label>
                        <input id="invoiceNum" name="invoiceNum" type="text" class="input-medium" value="${invoiceNum }"/>
                        &nbsp;&nbsp;<button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
                        <button	id="exportBtn" class="btn btn-warning" type="button">数据导出</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th>
                                        申请机构
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
                            	<c:forEach items="${invoiceDrawList}" var="invoiceDraw">
                            		<tr>
                            			<td>
                                            ${invoiceDraw.orgName}
                                        </td>
                                        <td>
                                            ${invoiceDraw.appDateShow}
                                        </td>
                                        <td>
                                            ${invoiceDraw.appName}
                                        </td>
                                        <td>
                                        	<c:if test="${invoiceDraw.invoiceType == 1}">
                                                机打
                                            </c:if>
                                            <c:if test="${invoiceDraw.invoiceType == 2}">
                                                手写
                                            </c:if>
                                            <c:if test="${invoiceDraw.invoiceType == 4}">
                                                其他
                                            </c:if>
                                        </td>
                                        <td>
                                            ${invoiceDraw.title}
                                        </td>
                                        <td>
                                            ${invoiceDraw.amt}
                                        </td>
                                        <td class="center">
                                        	<c:if test="${invoiceDraw.needPost == 0}">
                                                否
                                            </c:if>
                                            <c:if test="${invoiceDraw.needPost == 1}">
                                                是
                                            </c:if>
                                        </td>
                                        <td>
                                        	${invoiceDraw.invoiceNum}
                                        </td>
                                        <td>
                                            <c:if test="${invoiceDraw.invoiceStatus == '1'	}">
                                                <a href="${sc_ctx}/invoiceDraw/drawInit/${invoiceDraw.uuid}" class="btn btn-warning"/>开票</a>
                                            </c:if>
                                            <c:if test="${invoiceDraw.invoiceStatus == '2'	}">
                                                <a href="${sc_ctx}/invoiceDraw/drawInit/${invoiceDraw.uuid}" class="btn btn-primary"/>查看</a>
                                            </c:if>
                                        </td>
                            		</tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	invoiceDrawList}" >
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