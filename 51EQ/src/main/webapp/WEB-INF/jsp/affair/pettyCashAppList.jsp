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
    	$("#searchBtn").click(function() {
            $("input[type='text'],textarea").each(function(i) {
                this.value = $.trim(this.value);
            });

			$("#searchForm").attr("target", "_self");
            $("#searchForm").attr("action", "${sc_ctx}/pettyCashApp/search");
            $("#searchForm").submit();
        });
    });
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
                            <h3>备用金申请/审批</h3>
                        </legend>
                    </div>
                    <div class="span3">
                        <label class="control-label">申请编号 :</label>
                        <input id="appNo" name="appNo" type="text" class="input-medium" value="${appNo }"/>
                    </div>
                    <div class="span5">
                        <label class="control-label">申请时间 :</label>
                        <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                         ～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                    </div>
                    <div class="span4">
                        <label class="control-label">申请人 :</label>
                        <input id="appPer" name="appPer" type="text" class="input-medium" value="${appPer }"/>
                        <button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
                    </div>
              	</form>
              	
              	<form method="post" class="form-inline" id="listForm">
              		<div class="span12" style="padding-top: 10px;">
                        <a href="${sc_ctx}/pettyCashApp/new"	class="btn btn-primary">申请</a>
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
                                    <th class="center">
                                        申请编号
                                    </th>
                                    <th class="center">
                                        申请人
                                    </th>
                                    <th class="center">
                                        请款金额
                                    </th>
                                    <th class="center">
                                        申请时间
                                    </th>
                                    <th class="center">
                                        付款期限
                                    </th>
                                    <th class="center">
                                        状态
                                    </th>
                                    <th	width="75">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${appList}" var="app">
                            	<tr>
                            		<td></td>
                            		<td class="center">${app.appNo}</td>
                            		<td class="center">${app.appPerName}</td>
                            		<td class="center">${app.amount}</td>
                            		<td class="center">${app.appDate}</td>
                            		<td class="center">${app.paymentPeriod}</td>
                            		<td class="center">
                            		<c:if test="${app.appState.equals('00')}">
                                        编辑中
                                    </c:if>
                                    <c:if test="${app.appState.equals('01')}">
                                        审核中(1)
                                    </c:if>
                                    <c:if test="${app.appState.equals('02')}">
                                        审核中(2)
                                    </c:if>
                                    <c:if test="${app.appState.equals('03')}">
                                        审核中(3)
                                    </c:if>
                                    <c:if test="${app.appState.equals('99')}">
                                        归档
                                    </c:if>
                            		</td>
                            		<td class="center">
                            		<c:if test="${app.appState.equals('00')}">
                                        <a href="${sc_ctx}/pettyCashApp/edit/${app.uuid}" class="btn btn-warning"/>编辑</a>
                                    </c:if>
                                    <c:if test="${app.appState.equals('01') || app.appState.equals('02') || app.appState.equals('03')}">
                                        <a href="${sc_ctx}/pettyCashApp/edit/${app.uuid}" class="btn btn-warning"/>审核</a>
                                    </c:if>
                                    <c:if test="${app.appState.equals('99')}">
                                        <a href="${sc_ctx}/pettyCashApp/fileEdit/${app.uuid}" class="btn btn-warning"/>归档</a>
                                    </c:if>
                            		</td>
                            	</tr>
                            </c:forEach>
                            </tbody>
                            <c:if test="${empty	appList}" >
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
              	</form>
       		</div>
       	</div>
	</body>
</html>