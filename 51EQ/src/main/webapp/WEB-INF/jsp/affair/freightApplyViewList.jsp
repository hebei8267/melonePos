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
<!DOCTYPE html>
<html>
    <head>
    	<link rel="stylesheet" href="${ctx}/static/fontAwesome/css/font-awesome.css">
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
			background-color: #BDBDBD;
		}
    	</style>
    	<script>
            $().ready(function() {
                
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#searchForm").attr("target", "_self");
                    $("#searchForm").attr("action", "${sc_ctx}/freight/viewManagerSearch");
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
                            <h3>商品调货单</h3>
                        </legend>
                    </div>
                    
                    <div class="span3">
                        <label class="control-label">调出机构 :</label>
                        <select name="appOrgId" class="input-medium">
                            <c:forEach items="${orgList}" var="_appOrgId">
                                <c:if test="${_appOrgId.key == appOrgId}">
                                    <option value="${_appOrgId.key }" selected>${_appOrgId.value }</option>
                                </c:if>
                                <c:if test="${_appOrgId.key != appOrgId}">
                                    <option value="${_appOrgId.key }">${_appOrgId.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        &nbsp;&nbsp;
                    </div>
                  	

                 	<div class="span3">
                        <label class="control-label">调入机构 :</label>
                        <select name="targetOrgId" class="input-medium">
                            <c:forEach items="${orgList}" var="_targetOrgId">
                                <c:if test="${_targetOrgId.key == targetOrgId}">
                                    <option value="${_targetOrgId.key }" selected>${_targetOrgId.value }</option>
                                </c:if>
                                <c:if test="${_targetOrgId.key != targetOrgId}">
                                    <option value="${_targetOrgId.key }">${_targetOrgId.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        &nbsp;&nbsp;
                    </div>
		             	
                    
                    <div class="span6">
                        <label class="control-label">调货单状态 :</label>
                        <select name="status" class="input-medium">
                            <c:forEach items="${statusList}" var="_status">
                                <c:if test="${_status.key == status}">
                                    <option value="${_status.key }" selected>${_status.value }</option>
                                </c:if>
                                <c:if test="${_status.key != status}">
                                    <option value="${_status.key }">${_status.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>&nbsp;&nbsp;
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                </form>
                
                <form method="post" class="form-inline" id="listForm">
                    
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                        	<thead>
                                <tr>
                                    <th class="center">
                                        申请日期
                                    </th>
                                    <th class="center">
                                        调出机构
                                    </th>
                                    <th class="center">
                                        调入机构
                                    </th>
                                    <th class="center">
                                        调货类别
                                    </th>
                                    <th class="center">
                                        货运状态
                                    </th>
                                    <th class="center">
                                        预计<br>收货时间
                                    </th>
                                    <th class="center">
                                        实际<br>收货时间
                                    </th>
                                    <th class="center">
                                        预计<br>送货时间
                                    </th>
                                    <th class="center">
                                        实际<br>送货时间
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${freightAppList}" var="freightApp">
                            		<tr>
                            			<td class="center">
                            				${freightApp.appDate}
                            			</td>
                            			<td class="center">
                            				<c:if test="${fn:length(freightApp.appOrgId) > 4}">
					             			${fn:substring(freightApp.appOrgId,3,6)}
					             			</c:if>
					             			<c:if test="${fn:length(freightApp.appOrgId) < 4}">
					             			总部
					             			</c:if>
                            			</td>
                            			<td class="center">
                            				<c:if test="${fn:length(freightApp.targetOrgId) > 4}">
					             			${fn:substring(freightApp.targetOrgId,3,6)}
					             			</c:if>
					             			<c:if test="${fn:length(freightApp.targetOrgId) < 4}">
					             			总部
					             			</c:if>
                            			</td>
                            			<td class="center">
                            				<c:if test="${freightApp.freightType == '1'}">
					                   		<span class="_warn1">普通</span>
					                      	</c:if>
					                		<c:if test="${freightApp.freightType == '2'}">
					                   		<span class="_warn2">限时</span><br><span class="_warn2">${freightApp.limitedDate}</span>
					                      	</c:if>
                            			</td>
                            			<td class="center">
                            				<c:if test="${freightApp.status == '00'}">
					                   		<span class="_warn2">申请</span>
					                      	</c:if>
					                		<c:if test="${freightApp.status == '01'}">
					                   		<span class="_warn1">已审批</span>
					                      	</c:if>
					                      	<c:if test="${freightApp.status == '02'}">
					                   		<span class="_warn3">已送达</span>
					                      	</c:if>
                            			</td>
                            			<td class="center">
                            				<a href="${sc_ctx}/freight/viewEdit/${freightApp.uuid}/1" class="btn" target="_self"/><i class="fa fa-comments"></i> 预收</a>
                            				${freightApp.expReceiptDate}
                            			</td>
                            			<td class="center">
                            				<a href="${sc_ctx}/freight/viewEdit/${freightApp.uuid}/2" class="btn" target="_self"/><i class="fa fa-flag"></i> 实收</a>
                            				${freightApp.actReceiptDate}
                            			</td>
                            			<td class="center">
                            				<a href="${sc_ctx}/freight/viewEdit/${freightApp.uuid}/3" class="btn" target="_self"/><i class="fa fa-shopping-cart"></i> 预送</a>
                            				${freightApp.expDeliveryDate}
                            			</td>
                            			<td class="center">
                            				<a href="${sc_ctx}/freight/viewEdit/${freightApp.uuid}/4" class="btn" target="_self"/><i class="fa fa-plane"></i> 实送</a>
                            				${freightApp.actDeliveryDate}
                            			</td>
                                        <td>
                                      		<a href="${sc_ctx}/freight/viewEdit/${freightApp.uuid}/0" class="btn" target="_self"/>明细</a>
                                        </td>
                                    </tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	freightAppList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="10" class="rounded-foot-left">
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