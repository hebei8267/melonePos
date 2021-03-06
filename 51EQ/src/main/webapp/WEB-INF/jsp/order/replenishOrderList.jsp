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
            .form-horizontal .my-control-label {
				float: left;
				text-align: left;
				padding-top: 5px;
				padding-left: 5px;
				font-weight: bold;
				font-size: 20px;
            }
        </style>
        <link type="text/css" href="${ctx}/static/css/select2.css" rel="stylesheet">
		<script src="${ctx}/static/js/select2.min.js"></script>
		<script src="${ctx}/static/js/select2_locale_zh-CN.js"></script>
		<script>
			$(function() {
				$('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
                });
                
				$("#searchForm").validate({
					rules : {
						orderNo : {
							maxlength : 12
						},
						orderState : {
							required : true
						},
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

				// 查询
				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					$("#searchForm").attr('target', '_self');
					$("#searchForm").attr("action", "${sc_ctx}/replenishOrder/search");
					$("#searchForm").submit();
				});
				
				$("#itemType").select2({
					tags : itemTypeListJson
				});
				
				$("#supplier").select2({
					tags : supplierListJson
				});
			});
			var itemTypeListJson = ${itemTypeList};
			var supplierListJson = ${supplierList};
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="container">

			<div class="row">
				<div class="span12">
					<legend>
						<h3>收货管理</h3>
					</legend>
				</div>
			</div>
				
			<form method="post"	class="form-horizontal"	id="searchForm">
				<div class="row">
					<div class="span3">
						<label class="control-label">机构 : &nbsp;</label>
						<label class="my-control-label">${sessionScope.__SESSION_USER_INFO.orgName}</label>
					</div>
					
					<div class="span3">
						<label class="control-label">货单编号 : &nbsp;</label>
						<input id="orderNo" name="orderNo" type="text" class="input-medium" value="${orderNo }" />
					</div>

					<div class="span6">
						<label class="control-label">货单状态 : &nbsp;</label>
						<select id="orderState" name="orderState" class="input-medium">
							<c:forEach items="${stateList}" var="state">
								<c:if test="${state.key == orderState}">
									<option value="${state.key }" selected>${state.value }</option>
								</c:if>
								<c:if test="${state.key != orderState}">
									<option value="${state.key }">${state.value }</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="row" style="padding-top: 10px">
					<div class="span3">
						<label class="control-label">货号 : &nbsp;</label>
						<input id="barcode" name="barcode" value="${barcode }" type="text" class="input-medium"/>
					</div>
	
					<div class="span3">
						<label class="control-label">商品类别 : &nbsp;</label>
						<input type="hidden" id="itemType" name="itemType" class="select2 input-medium" value="${itemType}">
					</div>
						
					<div class="span6">
						<label class="control-label">货商 : &nbsp;</label>
						<input type="hidden" id="supplier" name="supplier" class="select2 input-medium" value="${supplier}">
					</div>
				</div>
				
				<div class="row">
					<div class="span12" style="padding-top: 10px">
         				<label class="control-label">发货日期 : &nbsp;</label>
              			<input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
       					～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                        
             			&nbsp;&nbsp;
						<button id="searchBtn" class="btn btn-primary" type="button">
							查询
						</button>
        			</div>
        		</div>
			</form>

				<form method="post"	class="form-horizontal"	id="listForm">
				<div class="row">
					<div class="span12" style="margin-top: 20px;">
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th class="center"> 补货单
									<br>
									批次号 </th>
									<th class="center"> 补货单
									<br>
									编号 </th>
									<th class="center"> 补货机构 </th>
									<th class="center"> 补货单
									<br>
									状态 </th>
									<th class="center"> 总部发货
									<br>
									日期 </th>
									<th class="center"> 门店点货
									<br>
									开始日期 </th>
									<th class="center"> 错填次数 </th>
									<th class="center"> 有无<br>描述 </th>
									<th	width="155"> &nbsp; </th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${replenishOrderList}" var="replenishOrder">
									<tr>
										
										<td	class="center"> ${replenishOrder.orderBatchId} </td>
										<td	class="center"> ${replenishOrder.orderNo} </td>
										<td	class="center"> ${replenishOrder.replenishOrgId} </td>
										<td	class="center">
										<c:if test="${replenishOrder.orderState == '01'}">
											编辑中
										</c:if>
										<c:if test="${replenishOrder.orderState == '02'}">
											收货中
										</c:if>
										<c:if test="${replenishOrder.orderState == '03'}">
											收货完成
										</c:if>
										<c:if test="${replenishOrder.orderState == '99'}">
											已完结
										</c:if></td>
										<td	class="center"> ${replenishOrder.sendDate} </td>
										<td	class="center"> ${replenishOrder.receiveDate} </td>
										<td	class="center"><span class="warn_text"> ${replenishOrder.errorNum} </span></td>
										<td	class="center">
										<c:if test="${!empty replenishOrder.description}">
											有
										</c:if>
										<c:if test="${empty replenishOrder.description}">
											无
										</c:if>
										</td>
										<td	class="center">
										<c:if test="${replenishOrder.orderState == '02'}">
											<a href="${sc_ctx}/replenishOrder/edit/${replenishOrder.orderNo}" class="btn btn-warning"/>收货</a>
										</c:if>
										<c:if test="${replenishOrder.orderState != '02'}">
											<a href="${sc_ctx}/replenishOrder/view/${replenishOrder.orderNo}" class="btn" target="_blank"/>查看</a>
										</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<c:if test="${empty	replenishOrderList}" >
								<tfoot>
									<tr>
										<td	colspan="9" class="rounded-foot-left"> 无记录信息 </td>
									</tr>
								</tfoot>
							</c:if>
						</table>
					</div>
				</div>
				</form>
			</div>

		</div>
	</body>
</html>