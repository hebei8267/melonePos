<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<script src="${ctx}/static/js/excelexport.js"></script>
		<link href="${ctx}/assets/global/plugins/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
        <script src="${ctx}/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <link href="${ctx}/assets/global/css/components.css" rel="stylesheet" id="style_components" type="text/css" />
		<style>
		.font2 {
			font-family : "Microsoft YaHei" ! important;
			font-size : 16px;
			font-weight : bolder;
		}
		</style>
		<script>
			$(function() {
				var table = $('#content-table').dataTable({
					"lengthMenu" : [[30,50,100,-1], [30,50, 100, "全部"] // change per page values here
					],
					"language" : {
						"sProcessing" : "正在加载中......",
						"sLengthMenu" : "显示 _MENU_ 条记录",
						"sZeroRecords" : "对不起,查询不到相关数据!",
						"sEmptyTable" : "无数据!",
						"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
						"sInfoEmpty" : "当前显示 0 到 0 条，共 0 条记录",
						"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
						"sSearch" : "搜索 ",
						"paginate" : {
							"previous" : "上一页",
							"next" : "下一页",
							"last" : "末页",
							"first" : "首页"
						}
					}
				});
				
				var ee = excelExport("content-table").parseToCSV().parseToXLS("excelexport sheet");
	            $(".dl-xls-ext").click(function() {
					ee.downloadXLS("${ctx}/excelFile", "excelexport.xls");
				});
				//-----------------------------------
                // 全选/全部选
                //-----------------------------------
                $("#eqCheckAll").click(function() {
            		var checked = $("#eqCheckAll").is(":checked");
                	$("input[name='eqOrgId']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
                });
                $("#infCheckAll").click(function() {
            		var checked = $("#infCheckAll").is(":checked");
                	$("input[name='infOrgId']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
                });
                $("#amCheckAll").click(function() {
            		var checked = $("#amCheckAll").is(":checked");
                	$("input[name='amOrgId']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
                });
                
				$("#listForm").validate({
					rules : {
						orgId : {
                            requiredSelect : 'orgId'
                        },
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
					if (!$("#listForm").valid()) {
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
				
				$("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/salesContrastByItem/export");
                    $("#listForm").submit();
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
					<c:if test="${sessionScope.__SESSION_USER_INFO.orgId == '000'}" >
						<div class="span12">
							<input type="checkbox" id="eqCheckAll"></input>EQ门店
							<c:forEach items="${eqOrgList}" var="org">
								<c:if test="${orgIdList.contains(org.key)}">
								<input type="checkbox" name="eqOrgId" value="${org.key }" checked></input>${org.value }
								</c:if>
								<c:if test="${!orgIdList.contains(org.key)}">
								<input type="checkbox" name="eqOrgId" value="${org.key }"></input>${org.value }
								</c:if>
							</c:forEach>
						</div>
						
						<div class="span12" style="margin-top: 10px;">
							<input type="checkbox" id="infCheckAll"></input>Infancy门店
							<c:forEach items="${infOrgList}" var="org">
								<c:if test="${orgIdList.contains(org.key)}">
								<input type="checkbox" name="infOrgId" value="${org.key }" checked></input>${org.value }
								</c:if>
								<c:if test="${!orgIdList.contains(org.key)}">
								<input type="checkbox" name="infOrgId" value="${org.key }"></input>${org.value }
								</c:if>
							</c:forEach>
						</div>
						
						<div class="span12" style="margin-top: 10px;">
							<input type="checkbox" id="amCheckAll"></input>AmpleLife门店
							<c:forEach items="${amOrgList}" var="org">
								<c:if test="${orgIdList.contains(org.key)}">
								<input type="checkbox" name="amOrgId" value="${org.key }" checked></input>${org.value }
								</c:if>
								<c:if test="${!orgIdList.contains(org.key)}">
								<input type="checkbox" name="amOrgId" value="${org.key }"></input>${org.value }
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${sessionScope.__SESSION_USER_INFO.orgId != '000'}" >
						<input type="hidden" name="orgId" value="${sessionScope.__SESSION_USER_INFO.orgId}">
					</c:if>
					
				</div>
				<div class="row" style="margin-top: 20px;">
					<div class="span4">
						<label class="control-label">类别 :</label>

						<input type="hidden" id="itemType" name="itemType" class="select2 input-xlarge" value="${itemType}">
					</div>
					
					<div class="span5">
						<label class="control-label">排序方式 :</label>

						<c:set var = "amt" value="amt"/>
						<c:if test="${orderMode.equals(amt)}" >
						<input type="radio" name="orderMode" value="qty" >
						<span style="background-color: #5bc0de; padding: 5px">销售量</span>
						<input type="radio" name="orderMode" value="amt" checked="checked">
						<span style="background-color: #62c462; padding: 5px">销售额</span>
						</c:if>
						<c:if test="${!orderMode.equals(amt)}" >
						<input type="radio" name="orderMode" value="qty" checked="checked">
						<span style="background-color: #5bc0de; padding: 5px">销售量</span>
						<input type="radio" name="orderMode" value="amt">
						<span style="background-color: #62c462; padding: 5px">销售额</span>
						</c:if>
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

					<div class="span8" style="margin-top: 20px;">
						<input type="radio" name="dateMode" value="week">
						<span style="background-color: #5bc0de; padding: 5px">周对比</span>
						<input type="radio" name="dateMode" value="month" >
						<span style="background-color: #62c462; padding: 5px">月对比</span>
						&nbsp;&nbsp;
						<button id="searchBtn" class="btn btn-primary" type="button" style="margin-top: -6px">
							查询
						</button>
						<button	class="btn btn-warning dl-xls-ext" type="button" style="margin-top: -6px">数据导出</button>
					</div>
				</div>
			</form>

			
			
			<div class="row">
				<div class="span12">
                	<div class="alert alert-info">
                		库销比(数量) = 库存(数量) / 销售(数量)
            		</div>
	            </div>
	            
				<div class="span12">
					
					<div class="table-scrollable">
	                <table class="table table-striped table-bordered table-hover mytable1" id="content-table">
						<thead>
							<tr>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 店号 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#ff6600,#ff6633);"> 类别 </th>
								<th class="center"> 销量一 </th>
								<th class="center"> 销量二 </th>
								<th class="center">趋势</th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 销售额一 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 销售额二 </th>
								<th style="background-image: linear-gradient(to bottom,#62c462,#51a351);">趋势</th>
								<th class="center"> 均价一 </th>
								<th class="center"> 均价二 </th>
								<th class="center">趋势</th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 库存量一 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 库存量二 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">趋势</th>
								<th class="center"> 库存金额一 </th>
								<th class="center"> 库存金额二 </th>
								<th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);"> 销售额增长/下降率 </th>
								<th class="center"> 库销比一 </th>
								<th class="center"> 库销比二 </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${contrastList}" var="subContrastList">
							<c:forEach items="${subContrastList}" var="salesContrastVo">
							<tr>
								<td class="center"> ${salesContrastVo.orgName} </td>
								<td class="center"> ${salesContrastVo.itemName} </td>
								
								<fmt:parseNumber var="_saleRqty1" integerOnly="true" type="number" value="${salesContrastVo.saleRqty1}" />
								<fmt:parseNumber var="_saleRqty2" integerOnly="true" type="number" value="${salesContrastVo.saleRqty2}" />
								<td class="center font2"> <c:out value="${_saleRqty1}" /> </td>
								<td class="center font2"> <c:out value="${_saleRqty2}" /> </td>
								
								<fmt:parseNumber var="saleRqty1" type="number" value="${salesContrastVo.saleRqty1}" />
								<fmt:parseNumber var="saleRqty2" type="number" value="${salesContrastVo.saleRqty2}" />
								<c:if test="${saleRqty2 > saleRqty1}" >
								<td class="center font2" style="color : #FF0000">↑</td>
								</c:if>
								<c:if test="${saleRqty2 < saleRqty1}" >
								<td class="center font2" style="color : #5bc0de">↓</td>
								</c:if>
								<c:if test="${saleRqty2 == saleRqty1}" >
								<td class="center font2">－</td>
								</c:if>
								
								<fmt:parseNumber var="_saleRamt1" integerOnly="true" type="number" value="${salesContrastVo.saleRamt1}" />
								<fmt:parseNumber var="_saleRamt2" integerOnly="true" type="number" value="${salesContrastVo.saleRamt2}" />
								<td class="center"> <c:out value="${_saleRamt1}" /> </td>
								<td class="center"> <c:out value="${_saleRamt2}" /> </td>
								
								<fmt:parseNumber var="saleRamt1" type="number" value="${salesContrastVo.saleRamt1}" />
								<fmt:parseNumber var="saleRamt2" type="number" value="${salesContrastVo.saleRamt2}" />
								<c:if test="${saleRamt2 > saleRamt1}" >
								<td class="center font2" style="color : #FF0000">↑</td>
								</c:if>
								<c:if test="${saleRamt2 < saleRamt1}" >
								<td class="center font2" style="color : #5bc0de">↓</td>
								</c:if>
								<c:if test="${saleRamt2 == saleRamt1}" >
								<td class="center font2">－</td>
								</c:if>
								
	
								<td class="center  font2"> ${salesContrastVo.salePrice1} </td>
								<td class="center  font2"> ${salesContrastVo.salePrice2} </td>
								
								<fmt:parseNumber var="salePrice1" type="number" value="${salesContrastVo.salePrice1}" />
								<fmt:parseNumber var="salePrice2" type="number" value="${salesContrastVo.salePrice2}" />
								<c:if test="${salePrice2 > salePrice1}" >
								<td class="center font2" style="color : #FF0000">↑</td>
								</c:if>
								<c:if test="${salePrice2 < salePrice1}" >
								<td class="center font2" style="color : #5bc0de">↓</td>
								</c:if>
								<c:if test="${salePrice2 == salePrice1}" >
								<td class="center font2">－</td>
								</c:if>
								
								<fmt:parseNumber var="_stockTotalQty1" integerOnly="true" type="number" value="${salesContrastVo.stockTotalQty1}" />
								<fmt:parseNumber var="_stockTotalQty2" integerOnly="true" type="number" value="${salesContrastVo.stockTotalQty2}" />
								<td class="center font2"> <c:out value="${_stockTotalQty1}" /> </td>
								<td class="center font2"> <c:out value="${_stockTotalQty2}" /> </td>
								
								<fmt:parseNumber var="stockTotalQty1" type="number" value="${salesContrastVo.stockTotalQty1}" />
								<fmt:parseNumber var="stockTotalQty2" type="number" value="${salesContrastVo.stockTotalQty2}" />
								<c:if test="${stockTotalQty2 > stockTotalQty1}" >
								<td class="center font2" style="color : #FF0000">↑</td>
								</c:if>
								<c:if test="${stockTotalQty2 < stockTotalQty1}" >
								<td class="center font2" style="color : #5bc0de">↓</td>
								</c:if>
								<c:if test="${stockTotalQty2 == stockTotalQty1}" >
								<td class="center font2">－</td>
								</c:if>
								
								<fmt:parseNumber var="_stockTotalAmt1" integerOnly="true" type="number" value="${salesContrastVo.stockTotalAmt1}" />
								<fmt:parseNumber var="_stockTotalAmt2" integerOnly="true" type="number" value="${salesContrastVo.stockTotalAmt2}" />
								<td class="center font2"> <c:out value="${_stockTotalAmt1}" /> </td>
								<td class="center font2"> <c:out value="${_stockTotalAmt2}" /> </td>
								
								<fmt:parseNumber var="salesContrast" type="number" value="${salesContrastVo.salesContrast}" />
								<c:if test="${salesContrast > 0}" >
								<td class="center" style="color : #FF0000">↑ ${salesContrastVo.salesContrast} %</td>
								</c:if>
								<c:if test="${salesContrast < 0}" >
								<td class="center" style="color : #5bc0de">↓ ${-salesContrastVo.salesContrast} %</td>
								</c:if>
								<c:if test="${salesContrast == 0}" >
								<td class="center"> ${salesContrastVo.salesContrast} %</td>
								</c:if>
								<!-- 库销比1 -->
								<c:if test="${salesContrastVo.saleRqty1.compareTo(BigDecimal.ZERO) == 0}">
				                <td class="center">-</td>
				                </c:if>
				                <c:if test="${salesContrastVo.saleRqty1.compareTo(BigDecimal.ZERO) != 0}">
				                <td class="center">${salesContrastVo.stockTotalQty1 / salesContrastVo.saleRqty1}</td>
				                </c:if>
				                <!-- 库销比2 -->
				                <c:if test="${salesContrastVo.saleRqty2.compareTo(BigDecimal.ZERO) == 0}">
				                <td class="center">-</td>
				                </c:if>
				                <c:if test="${salesContrastVo.saleRqty2.compareTo(BigDecimal.ZERO) != 0}">
				                <td class="center">${salesContrastVo.stockTotalQty2 / salesContrastVo.saleRqty2}</td>
				                </c:if>
							</tr>
							</c:forEach>
							</c:forEach>
						</tbody>
					</table>
					</div>
					
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