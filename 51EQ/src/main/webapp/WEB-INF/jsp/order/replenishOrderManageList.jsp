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
					$("input[name='uuid']").each(function() {
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

				// 发货
				$("#sendBtn").click(function() {
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
					$("#listForm").attr("action", "${sc_ctx}/replenishOrder/manageSend");
					$("#listForm").submit();

				});
				
				// 数据导出
				$("#exportBtn").click(function() {
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
                    $("#listForm").attr("action", "${sc_ctx}/replenishOrder/export");
                    $("#listForm").submit();
                });

				// 查询
				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					$("#searchForm").attr('target', '_self');
					$("#searchForm").attr("action", "${sc_ctx}/replenishOrder/manageSearch");
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
				$("#listForm").attr("action", "${sc_ctx}/replenishOrder/manageDel");
				$("#listForm").submit();
			}
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="container">

			<div class="row">
				<div class="span12">
					<legend>
						<h3>发货管理</h3>
					</legend>
				</div>
			</div>
			
			<form method="post"	class="form-horizontal"	id="searchForm">
			<div class="row">
				<div class="span3">
					<label class="control-label">货单编号 : &nbsp;</label>
					<input id="orderNo" name="orderNo" type="text" class="input-medium" value="${orderNo }" />
				</div>

				<div class="span3">
					<label class="control-label">机构 : &nbsp;</label>
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
					<div class="span12" style="margin-top: 15px;">
						<input id="sendBtn" name="sendBtn" type="button" class="btn btn-primary" value="发货"/>
						<input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
						<input id="exportBtn" name="exportBtn" type="button" class="btn btn-warning" value="数据导出"/>
					</div>

					<div class="span12" style="margin-top: 10px;">
						<input type="hidden" name="uuids" id="uuids"/>
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th	width="25" class="center">
									<input id="checkAll" type="checkbox" />
									</th>
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
										<td	class="center"> <%//收货中与已完结不能删除 %>
										<c:if test="${replenishOrder.orderState != '03' || replenishOrder.orderState != '99'}">
											<input type="checkbox" name="uuid" value="${replenishOrder.orderNo}">
											</input>
										</c:if>
										</td>
										
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
										<td	class="center"> <span class="warn_text">${replenishOrder.errorNum} </span></td>
										<td	class="center">
										<c:if test="${!empty replenishOrder.description}">
											有
										</c:if>
										<c:if test="${empty replenishOrder.description}">
											无
										</c:if>
										</td>
										<td	class="center">
										<c:if test="${replenishOrder.orderState != '99' && replenishOrder.orderState != '03'}">
											<a href="${sc_ctx}/replenishOrder/manageEdit/${replenishOrder.orderNo}" class="btn btn-warning"/>编辑</a>
										</c:if>
										<c:if test="${replenishOrder.orderState == '03'}">
											<a href="${sc_ctx}/replenishOrder/manageSuccessSave/${replenishOrder.orderNo}" class="btn btn-danger"/>完结</a>
										</c:if>
											<a href="${sc_ctx}/replenishOrder/view/${replenishOrder.orderNo}" class="btn" target="_blank"/>查看</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<c:if test="${empty	replenishOrderList}" >
								<tfoot>
									<tr>
										<td	colspan="10" class="rounded-foot-left"> 无记录信息 </td>
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