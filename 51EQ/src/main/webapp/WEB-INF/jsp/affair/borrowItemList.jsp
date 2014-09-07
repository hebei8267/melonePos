<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<%@	page import="com.tjhx.globals.Constants"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="_ROOT_ORG_ID">
    <%=Constants.ROOT_ORG_ID %>
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
					$("#searchForm").attr("action", "${sc_ctx}/borrowItem/search");
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
                
                $("#listForm").attr("target", "_self");
                $("#listForm").attr("action", "${sc_ctx}/borrowItem/del");
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
                            <h3>物件借还</h3>
                        </legend>
                    </div>
                    
                    <div class="span3">
                        <label class="control-label">物件名称 :</label>
                        <input id="itemName" name="itemName" type="text" class="input-medium" value="${itemName}"/>
                    </div>
                    <div class="span3">
                        <label class="control-label">物件类型 :</label>
                        <select name="itemType" class="input-medium">
                            <c:forEach items="${itemTypeList}" var="_itemType">
                                <c:if test="${_itemType.key == itemType}">
                                    <option value="${_itemType.key }" selected>${_itemType.value }</option>
                                </c:if>
                                <c:if test="${_itemType.key != itemType}">
                                    <option value="${_itemType.key }">${_itemType.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="span6">
                        <label class="control-label">所属机构 :</label>
                        <select name="orgId" class="input-medium">
                            <c:forEach items="${orgList}" var="_orgId">
                                <c:if test="${_orgId.key == orgId}">
                                    <option value="${_orgId.key }" selected>${_orgId.value }</option>
                                </c:if>
                                <c:if test="${_orgId.key != orgId}">
                                    <option value="${_orgId.key }">${_orgId.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        &nbsp;&nbsp;
                        <button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
                    </div>
                </form>
                
                <form method="post" class="form-inline" id="listForm">
                	<div class="span12" style="padding-top: 10px;">
                        <a href="${sc_ctx}/borrowItem/new"	class="btn btn-primary">新建</a>
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
                                        物件编号
                                    </th>
                                    <th class="center">
                                        物件名称
                                    </th>
                                    <th class="center">
                                        物件类型
                                    </th>
                                    <th class="center">
                                        所属机构
                                    </th>
                                    <th class="center">
                                        接收时间
                                    </th>
                                    <th class="center">
                                        物件状态
                                    </th>
                                    <th	width="125">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${borrowItemList}" var="borrowItem">
                            	<tr>
                            		<td class="center">
                            		<input type="checkbox" name="uuid" value="${borrowItem.uuid}">
                            		</td>
                            		<td class="center">${borrowItem.itemNo}</td>
                            		<td class="center">${borrowItem.itemName}</td>
                            		<td class="center">
                            		<c:if test="${borrowItem.itemType == '01'}" >
                            		证书
                            		</c:if>
                            		<c:if test="${borrowItem.itemType == '02'}" >
                            		执照
                            		</c:if>
                            		<c:if test="${borrowItem.itemType == '03'}" >
                            		公章
                            		</c:if>
                            		<c:if test="${borrowItem.itemType == '04'}" >
                            		押金收据
                            		</c:if>
                            		<c:if test="${borrowItem.itemType == '99'}" >
                            		其他
                            		</c:if>
                            		</td>
                            		<td class="center">
                            		<c:if test="${fn:length(borrowItem.orgId) > 4}">
		             					${fn:substring(borrowItem.orgId,3,6)}
		             				</c:if>
		             				<c:if test="${fn:length(borrowItem.orgId) < 4}">
		             					总部
		             				</c:if>
                            		</td>
                            		<td class="center">
                            		<fmt:parseDate value="${borrowItem.optDate}" var="_optDate" pattern="yyyyMMdd" />
                            		<fmt:formatDate pattern="yyyy-MM-dd" value="${_optDate}" /></td>
                            		<td class="center">
									<c:if test="${borrowItem.status == '1'}" >
                            		在库
                            		</c:if>
                            		<c:if test="${borrowItem.status == '0'}" >
                            		借出
                            		</c:if>
									</td>
									<td class="center">
										<a href="${sc_ctx}/borrowItem/edit/${borrowItem.uuid}" class="btn btn-warning"/>修改</a>
										<a href="${sc_ctx}/borrowItemRun/list/${borrowItem.itemNo}" class="btn" target="_blank"/>借还</a>
									</td>
                            	</tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	borrowItemList}" >
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