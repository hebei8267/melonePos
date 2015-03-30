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
						orderNo : {
							maxlength : 12
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
						} else {
							$(this).attr("checked", false);
						}
                	}); 
                });
                
				
				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});
		
					$("#listForm").attr('target', '_self');
					$("#listForm").attr("action", "${sc_ctx}/replenishOrder/manageSearch");
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
                            <h3>发货管理</h3>
                        </legend>
                    </div>
                    
                    <div class="span3">
						<label class="control-label">货单编号 :</label>
						<input id="orderNo" name="orderNo" type="text" class="input-medium" value="${orderNo }" />
                    </div>
                    
                    <div class="span3">
                    	<label class="control-label">机构 :</label>
						<select id="orgId" name="orgId" class="input-medium">
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
					
                    <div class="span6">
						<label class="control-label">货单状态 :</label>
						<select id="orderState" name="orderState" class="input-medium">
							<c:forEach items="${stateList}" var="state">
								<c:if test="${state.key == orderState}">
									<option value="${state.key }" selected>${state.value }</option>
								</c:if>
								<c:if test="${state.key != orgId}">
									<option value="${state.key }">${state.value }</option>
								</c:if>
							</c:forEach>
						</select>&nbsp;&nbsp;
						<button id="searchBtn" class="btn btn-primary" type="button">查询</button>
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
                                        补货单<br>批次号
                                    </th>
                                    <th class="center">
                                        补货单<br>编号
                                    </th>
                                    <th class="center">
                                        补货机构
                                    </th>
                                    <th class="center">
                                        补货单<br>状态
                                    </th>
                                    <th class="center">
                                        门店点货<br>开始日期
                                    </th>
                                    <th class="center">
                                        错填次数
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${replenishOrderList}" var="replenishOrder">
                                	<tr>
                                        <td	class="center">
                                          	<input type="checkbox" name="uuid" value="${replenishOrder.uuid}"></input>
                                        </td>
                                        <td	class="center">
                                        	${replenishOrder.orderBatchId}
                                        </td>
                                        <td	class="center">
                                            ${replenishOrder.orderNo}
                                        </td>
                                        <td	class="center">
                                            ${replenishOrder.replenishOrgId}
                                        </td>
                                        <td	class="center">
                                        	<c:if test="${replenishOrder.orderState == '01'}">
                                                编辑中
                                            </c:if>
                                            <c:if test="${replenishOrder.orderState == '02'}">
                                                已发货
                                            </c:if>
                                            <c:if test="${replenishOrder.orderState == '03'}">
                                                收货中
                                            </c:if>
                                            <c:if test="${replenishOrder.orderState == '99'}">
                                                已完结
                                            </c:if>
                                        </td>
                                        <td	class="center">
                                            ${replenishOrder.receiveDate}
                                        </td>
                                        <td	class="center">
                                            ${replenishOrder.errorNum}
                                        </td>
                                        <td	class="center">
                                            <a href="${sc_ctx}/coupon/edit/${coupon.couponNo}" class="btn btn-warning"/>编辑</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	replenishOrderList}" >
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