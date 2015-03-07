<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
	${ctx}/sc
</c:set>
<c:set var="DEFAULT_RETAINED_AMT">
	${DEFAULT_RETAINED_AMT}
</c:set>
<!DOCTYPE html>
<html>
	<head>
		<style>
		.font2 {
			font-family : "Microsoft YaHei" ! important;
			font-size : 16px;
			font-weight : bolder;
		}
		</style>
		<script src="${ctx}/static/js/moment.js"></script>
		<script>
			$().ready(function() {
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

				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					$("#listForm").attr('target', '_self');
					$("#listForm").attr("action", "${sc_ctx}/salesOrdersDayTotalContrast/search");
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
							<h3>店铺指标</h3>
						</legend>
					</div>

					<div class="span12">
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
				</div>
				<div class="row">
					<div class="span6" style="margin-top: 20px;">
						<label class="control-label">销售日期(一) :</label>
						<input id="optDate1_start" name="optDate1_start" type="text" class="input-medium" value="${optDate1_start }"/>
						～
						<input id="optDate1_end" name="optDate1_end" type="text" class="input-medium" value="${optDate1_end }"/>
					</div>
					<div class="span6" style="margin-top: 20px;">
						<label class="control-label">销售日期(二) :</label>
						<input id="optDate2_start" name="optDate2_start" type="text" class="input-medium" value="${optDate2_start }"/>
						～
						<input id="optDate2_end" name="optDate2_end" type="text" class="input-medium" value="${optDate2_end }"/>
					</div>
					
					<div class="span4" style="margin-top: 20px;">
						<label class="control-label">排序方式 :</label>

						<c:set var = "amt" value="amt"/>
						<c:if test="${orderMode.equals(amt)}" >
							<input type="radio" name="orderMode" value="qty" >
							<span style="background-color: #5bc0de; padding: 5px">客单数</span>
							<input type="radio" name="orderMode" value="amt" checked="checked">
							<span style="background-color: #62c462; padding: 5px">客单价</span>
						</c:if>
						<c:if test="${!orderMode.equals(amt)}" >
							<input type="radio" name="orderMode" value="qty" checked="checked">
							<span style="background-color: #5bc0de; padding: 5px">客单数</span>
							<input type="radio" name="orderMode" value="amt">
							<span style="background-color: #62c462; padding: 5px">客单价</span>
						</c:if>
					</div>
					
					<div class="span8" style="margin-top: 20px;">
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
								<th class="center"> 上期客单数 </th>
								<th class="center"> 本期客单数 </th>
								<th class="center">趋势</th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 上期客单价 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 本期客单价 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">趋势</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${quotaList}" var="quota">
							<tr>
								<td class="center"> ${quota.orgName} </td>
								<td class="center"> ${quota.ordersNum1} </td>
								<td class="center font2"> ${quota.ordersNum2} </td>
								
								<td class="center font2">
								<c:if test="${quota.qtyTrend > 0}" >
								<span style="color : #FF0000">↑</span>
								</c:if>
								
								<c:if test="${quota.qtyTrend < 0}" >
								<span style="color : #5bc0de">↓</span>
								</c:if>
								
								<c:if test="${!empty quota.qtyTrend}" >
								<c:if test="${quota.qtyTrend > 0}" >
								<span style="color : #FF0000"><fmt:formatNumber value="${quota.qtyTrend}" pattern="##.##" minFractionDigits="2" />%</span>
								</c:if>
								<c:if test="${quota.qtyTrend < 0}" >
								<span style="color : #5bc0de"><fmt:formatNumber value="${quota.qtyTrend}" pattern="##.##" minFractionDigits="2" />%</span>
								</c:if>
								</c:if>
								</td>
								
								<td class="center"> ${quota.ordersAvgPrice1} </td>
								<td class="center font2"> ${quota.ordersAvgPrice2} </td>
								<td class="center">
								<c:if test="${quota.amtTrend > 0}" >
								<span style="color : #FF0000">↑</span>
								</c:if>
								
								<c:if test="${quota.amtTrend < 0}" >
								<span style="color : #5bc0de">↓</span>
								</c:if>
								
								<c:if test="${!empty quota.amtTrend}" >
								<c:if test="${quota.amtTrend > 0}" >
								<span style="color : #FF0000"><fmt:formatNumber value="${quota.amtTrend}" pattern="##.##" minFractionDigits="2" />%</span>
								</c:if>
								<c:if test="${quota.amtTrend < 0}" >
								<span style="color : #5bc0de"><fmt:formatNumber value="${quota.amtTrend}" pattern="##.##" minFractionDigits="2" />%</span>
								</c:if>
								</c:if>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<script>
			$(function() {
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
				$("input[name='dateMode']").change(function() {
					initDateMode();
				});
			});
			
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
	</body>
</html>