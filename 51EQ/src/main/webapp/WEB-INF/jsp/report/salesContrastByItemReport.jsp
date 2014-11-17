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
		<script src="${ctx}/static/js/moment.js"></script>
		<link type="text/css" href="${ctx}/static/css/select2.css" rel="stylesheet">
		<script src="${ctx}/static/js/select2.min.js"></script>
		<script src="${ctx}/static/js/select2_locale_zh-CN.js"></script>
		<style>
		.font2 {
			font-family : "Microsoft YaHei" ! important;
			font-size : 16px;
			font-weight :bold;
		}
		</style>
		<script>
			$(function() {
				$("#listForm").validate({
					rules : {
						optDate1_start : {
							required : true,
							date : true
						},
						optDate1_end : {
							required : true,
							date : true,
							compareDate : "#optDate1_start"
						},
						optDate2_start : {
							required : true,
							date : true
						},
						optDate2_end : {
							required : true,
							date : true,
							compareDate : "#optDate2_start"
						}
					}
				});

				$('#optDate1_start').datepicker({
					format : 'yyyy-mm-dd'
				});
				$('#optDate1_end').datepicker({
					format : 'yyyy-mm-dd'
				});

				$('#optDate2_start').datepicker({
					format : 'yyyy-mm-dd'
				});
				$('#optDate2_end').datepicker({
					format : 'yyyy-mm-dd'
				});

				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					if ($("#itemType").val() == '') {
						$('#__search_tip').modal('show');
						return;
					}

					$("#listForm").attr('target', '_self');
					$("#listForm").attr("action", "${sc_ctx}/salesContrastByItem/search");
					$("#listForm").submit();
				});

				$("input[name='dateMode']").change(function() {
					initDateMode();
				});

				$("#itemType").select2({
					tags : itemTypeListJson
				});
			});
			var itemTypeListJson = ${itemTypeList};
			function initDateMode() {

				var dateMode = $("input[name='dateMode']:checked").val();
				if (dateMode == 'week') {//周对比
					$('#optDate2_end').val(moment().add('days', -1).format("YYYY-MM-DD"));
					$('#optDate2_start').val(moment().add('days', -7).format("YYYY-MM-DD"));

					$('#optDate1_end').val(moment().add('days', -8).format("YYYY-MM-DD"));
					$('#optDate1_start').val(moment().add('days', -14).format("YYYY-MM-DD"));
				} else {//月对比
					$('#optDate2_end').val(moment().add('days', -1).format("YYYY-MM-DD"));
					$('#optDate2_start').val(moment().add('months', -1).format("YYYY-MM-DD"));

					$('#optDate1_end').val(moment().add('months', -1).add('days', -1).format("YYYY-MM-DD"));
					$('#optDate1_start').val(moment().add('months', -2).format("YYYY-MM-DD"));
				}
			}
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
							<h3>类别销售信息对比</h3>
						</legend>
					</div>

					<div class="span6">
						<label class="control-label">销售日期(一) :</label>
						<input id="optDate1_start" name="optDate1_start" type="text" class="input-medium" value="${optDate1_start }"/>
						～
						<input id="optDate1_end" name="optDate1_end" type="text" class="input-medium" value="${optDate1_end }"/>
					</div>

					<div class="span6">
						<label class="control-label">销售日期(二) :</label>
						<input id="optDate2_start" name="optDate2_start" type="text" class="input-medium" value="${optDate2_start }"/>
						～
						<input id="optDate2_end" name="optDate2_end" type="text" class="input-medium" value="${optDate2_end }"/>
					</div>

					<div class="span4" style="margin-top: 20px;">
						<label class="control-label">商品类型 :</label>

						<input type="hidden" id="itemType" name="itemType" class="select2 input-xlarge" value="${itemType}">

					</div>

					<div class="span8" style="margin-top: 20px;">
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

						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="dateMode" value="week">
						<span style="background-color: #5bc0de; padding: 5px">周对比</span>
						<input type="radio" name="dateMode" value="month" >
						<span style="background-color: #62c462; padding: 5px">月对比</span>
						&nbsp;&nbsp;
						<button id="searchBtn" class="btn btn-primary" type="button" style="margin-top: -6px">
							查询
						</button>
					</div>
				</div>
			</form>

			
			
			<div class="row">
				<div class="span12" style="margin-top: 10px;">
					
					<table class="table	table-striped table-bordered table-condensed mytable">
						<thead>
							<tr>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 店号 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#ff6600,#ff6633);"> 类别 </th>
								<th class="center"> 销量一 </th>
								<th class="center"> 销量二 </th>
								<th>趋势</th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 销售额一 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 销售额二 </th>
								<th style="background-image: linear-gradient(to bottom,#62c462,#51a351);">趋势</th>
								<th class="center"> 均价一 </th>
								<th class="center"> 均价二 </th>
								<th>趋势</th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 销售额增长/下降率 </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${contrastList}" var="subContrastList">
							<c:forEach items="${subContrastList}" var="salesContrastVo">
							<tr>
								<td class="center"> ${salesContrastVo.orgName} </td>
								<td class="center"> ${salesContrastVo.itemName} </td>
								<td class="center font2"> ${salesContrastVo.saleRqty1} </td>
								<td class="center font2"> ${salesContrastVo.saleRqty2} </td>
								
								<c:if test="${salesContrastVo.saleRqty2 > salesContrastVo.saleRqty1}" >
								<td class="center font2" style="color : #FF0000">↑</td>
								</c:if>
								<c:if test="${salesContrastVo.saleRqty2 < salesContrastVo.saleRqty1}" >
								<td class="center font2" style="color : #00FF00">↓</td>
								</c:if>
								<c:if test="${salesContrastVo.saleRqty2 == salesContrastVo.saleRqty1}" >
								<td class="center font2">－</td>
								</c:if>
								
								<td class="center"> ${salesContrastVo.saleRamt1} </td>
								<td class="center"> ${salesContrastVo.saleRamt2} </td>
								
								<c:if test="${salesContrastVo.saleRamt2 > salesContrastVo.saleRamt1}" >
								<td class="center font2" style="color : #FF0000">↑</td>
								</c:if>
								<c:if test="${salesContrastVo.saleRamt2 < salesContrastVo.saleRamt1}" >
								<td class="center font2" style="color : #00FF00">↓</td>
								</c:if>
								<c:if test="${salesContrastVo.saleRamt2 == salesContrastVo.saleRamt1}" >
								<td class="center font2">－</td>
								</c:if>
								
								<td class="center  font2"> ${salesContrastVo.salePrice1} </td>
								<td class="center  font2"> ${salesContrastVo.salePrice2} </td>
								
								<c:if test="${salesContrastVo.salePrice2 > salesContrastVo.salePrice1}" >
								<td class="center font2" style="color : #FF0000">↑</td>
								</c:if>
								<c:if test="${salesContrastVo.salePrice2 < salesContrastVo.salePrice1}" >
								<td class="center font2" style="color : #00FF00">↓</td>
								</c:if>
								<c:if test="${salesContrastVo.salePrice2 == salesContrastVo.salePrice1}" >
								<td class="center font2">－</td>
								</c:if>
								
								
								
								<c:if test="${salesContrastVo.salesContrast > 0}" >
								<td class="center" style="color : #FF0000">↑ ${salesContrastVo.salesContrast} %</td>
								</c:if>
								<c:if test="${salesContrastVo.salesContrast < 0}" >
								<td class="center" style="color : #00FF00">↓ ${-salesContrastVo.salesContrast} %</td>
								</c:if>
								<c:if test="${salesContrastVo.salesContrast == 0}" >
								<td class="center"> ${salesContrastVo.salesContrast} %</td>
								</c:if>
							</tr>
							</c:forEach>
							<tr>
							<td colspan="12"></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					
				</div>
			</div>
		</div>

		<div class="modal hide fade  __model37" id="__search_tip">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">×</a>
				<h4>系统消息</h4>
			</div>
			<div class="modal-body">
				<center>
					<p class="error">
						请选择商品类型!
					</p>
				</center>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">关闭</a>
			</div>
		</div>
	</body>
</html>