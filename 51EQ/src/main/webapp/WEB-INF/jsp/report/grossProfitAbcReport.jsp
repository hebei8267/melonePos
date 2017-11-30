<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="em_ctx">
    ${ctx}/em
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<script src="${ctx}/static/js/moment.js"></script>
		<link type="text/css" href="${ctx}/static/css/select2.css" rel="stylesheet">
		<script src="${ctx}/static/js/select2.min.js"></script>
		<script src="${ctx}/static/js/select2_locale_zh-CN.js"></script>
        <link href="${ctx}/assets/global/plugins/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
        <script src="${ctx}/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<link href="${ctx}/assets/global/css/components.css" rel="stylesheet" id="style_components" type="text/css" />
    	<script>
    		var itemTypeListJson = ${itemTypeList};
    		var supplierListJson = ${supplierList};
    		
			$(function() {
				var table = $('#table_1').dataTable({
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
					},
					"order" : [[0, "asc"]] // set first column as a default sort by asc
				});
				
				var table = $('#table_2').dataTable({
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
					},
					"order" : [[0, "asc"]] // set first column as a default sort by asc
				});
				
				
				var table = $('#table_3').dataTable({
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
					},
					"order" : [[0, "asc"]] // set first column as a default sort by asc
				});
				
				<c:if test="${dTable == '1'}">
				var table = $('#table_4').dataTable({
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
					},
					"order" : [[0, "asc"]] // set first column as a default sort by asc
				});
				</c:if>
				
				
				$("#itemType").select2({
					tags : itemTypeListJson
				});
				$("#supplier").select2({
					tags : supplierListJson
				});
				
				$('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
                });
				
				$('body').on('click', '.portlet > .portlet-title > .tools > .collapse, .portlet .portlet-title > .tools > .expand', function(e) {
		            e.preventDefault();
		            var el = $(this).closest(".portlet").children(".portlet-body");
		            if ($(this).hasClass("collapse")) {
		                $(this).removeClass("collapse").addClass("expand");
		                el.slideUp(200);
		            } else {
		                $(this).removeClass("expand").addClass("collapse");
		                el.slideDown(200);
		            }
		        });
				
				$("#listForm").validate({
                    rules : {
                    	optDateShow_start : {
                    		required : true,
                    		date : true                    		
                        },
                        optDateShow_end : {
                    		required : true,
                    		date : true,
                    		compareDate : "#optDateShow_start"
                        },
                        abcParam1 : {
                    		required : true,
                    		digits : true,
                    		add100 : true
                        },
                        abcParam2 : {
                    		required : true,
                    		digits : true,
                    		add100 : true
                        },
                        abcParam3 : {
                    		required : true,
                    		digits : true,
                    		add100 : true
                        },
                        abcType : {
                        	required : true
                        }
                    }
                });
				
				$("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/grossProfitAbc/search");
                    $("#listForm").submit();
                });
				
				$("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/grossProfitAbc/export");
                    $("#listForm").submit();
                });
			});
			//固定长度效验验证
			jQuery.validator.addMethod("add100", function(value, element) {
			    var abcParam1 = $("#abcParam1").val();
			    var abcParam2 = $("#abcParam2").val();
			    var abcParam3 = $("#abcParam3").val();
			    return (Number(abcParam1) + Number(abcParam2) + Number(abcParam3)) == 100
			}, "A,B,C百分比输入错误");
			
			function _abcModalClick(itemSubno,goodsName){
				var $modal_view = $('#_abcModal');

				setTimeout(function() {
					$modal_view.load('${em_ctx}/grossProfitAbc/abcModal', {
						'itemSubno' : itemSubno,
						'goodsName' : goodsName,
						'optDateShowStart' : $("#optDateShow_start").val(),
						'optDateShowEnd' : $("#optDateShow_end").val()
						}, function() {
						$modal_view.modal('show');
					});
				}, 200);
			}
		</script>
		<style>
		.font1 {
			font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
			font-size : 18px;
			font-weight : bolder;
		}
		.font2 {
			font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
			font-size : 8px;
			font-weight : bolder;
		}
		</style>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
        	<form method="post"	class="form-inline" id="listForm">
        	<div class="row">
	            <div class="span12">
	                <legend>
	                    <h3>ABC-D分析表</h3>
	                </legend>
	            </div>
	            
	            <div class="span12">
                	<div class="alert alert-info">
                		库销比 = 库存 / 销售&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存比(金额) = 库存金额 ／ 库存总金额
            		</div>
	            </div>
	            
	            <div class="span12">
                    <label class="control-label">查询时间 :</label>
                    <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-small2" value="${optDateShow_start }"/>
                   	～
                 	<input id="optDateShow_end" name="optDateShow_end" type="text" class="input-small2" value="${optDateShow_end }"/>
               		
               		&nbsp;&nbsp;
                    <label class="control-label">机构 :</label>
                    <select name="orgId" class="input-small2">
	                    <c:forEach items="${orgList}" var="org">
	                        <c:if test="${org.key == orgId}">
	                            <option value="${org.key }" selected>${org.value }</option>
	                        </c:if>
	                        <c:if test="${org.key != orgId}">
	                            <option value="${org.key }">${org.value }</option>
	                        </c:if>
	                    </c:forEach>
	                </select>
                	
                	&nbsp;&nbsp;
                    <label class="control-label">商品类别 :</label>
                    <input type="hidden" id="itemType" name="itemType" class="select2 input-small2" value="${itemType}">
                    
                    &nbsp;&nbsp;
                    <label class="control-label">货号 :</label>
                    <input id="itemSubno" name="itemSubno" type="text" class="input-small2" value="${itemSubno }"/>
                    
                    &nbsp;&nbsp;
                    <label class="control-label">商品名称 :</label>
                    <input id="itemName" name="itemName" type="text" class="input-small2" value="${itemName }"/>
                </div>
            </div>
            
			<div class="row" style="padding-top: 10px">    
                <div class="span12">
                    <label class="control-label">货商 :</label>
                    <input type="hidden" id="supplier" name="supplier" class="select2 input-medium" value="${supplier}">
                    
                    &nbsp;&nbsp;
                    <label class="control-label">ABC系数 :</label>
                    <select name="abcType" class="input-medium">
                    	<option value="1" <c:if test="${abcType == '1'}">selected</c:if>>合计销售金额</option>
                    	<option value="2" <c:if test="${abcType == '2'}">selected</c:if>>合计销售数量</option>
                    </select>
               
               		&nbsp;&nbsp;
                    <label class="control-label">A :</label>
                    <input id="abcParam1" name="abcParam1" type="text" class="input-mini_my" value="${abcParam1 }"/>%&nbsp;&nbsp;

                    <label class="control-label">B :</label>
                    <input id="abcParam2" name="abcParam2" type="text" class="input-mini_my" value="${abcParam2 }"/>%&nbsp;&nbsp;

                    <label class="control-label">C :</label>
                    <input id="abcParam3" name="abcParam3" type="text" class="input-mini_my" value="${abcParam3 }"/>%&nbsp;&nbsp;

					<label class="control-label">滞销表格 :</label>
					<select name="dTable" class="input-mini">
                    	<option value="0" <c:if test="${dTable == '0'}">selected</c:if>>隐藏</option>
                    	<option value="1" <c:if test="${dTable == '1'}">selected</c:if>>显示</option>
                    </select>
                	&nbsp;&nbsp;
                    <button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
                    <button	id="exportBtn" class="btn btn-warning" type="button">数据导出</button>
                </div>
	    	</div>
	    	
	    	<div class="row" style="padding-top: 10px">
         		<div class="span12">
         			<div class="portlet box green">
	                    <div class="portlet-title">
	                        <div class="caption">
	                        	分析表A
	                        	<c:if test="${abcType == '1' || abcType == '2'}">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalAStockAmt} / ${vo.totalAAmt} / ${vo.totalAmt}</span>&nbsp;&nbsp;<span class="font2">(元)  库存金额 / 销售金额 / 总销售金额</span>
		                        	
		                        	<div style="margin-top: 10px">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalAQty} / ${vo.totalQty}</span>&nbsp;&nbsp;<span class="font2">(个)  销售数量 / 总销售数量</span>
		                        	</div>
		                        	
		                        	<div style="margin-top: 10px">
		                        	<c:if test="${vo.totalAAmt.compareTo(BigDecimal.ZERO) != 0}">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalAStockAmt / vo.totalAAmt}</span>&nbsp;&nbsp;<span class="font2">库销比</span>
		                        	</c:if>
		                        	</div>
	                        	</c:if>
	                        </div>
	                        <div class="tools">
                                 <a href="#" class="collapse"> </a>
                         	</div>
	                    </div>
	                    
	                    <div class="portlet-body">
	                        <div class="table-scrollable">
	                            <table class="table table-striped table-bordered table-hover mytable1" id="table_1">
	                                <thead>
	                                    <tr>
	                                        <th class="center" scope="col" width="50">行号</th>
	                                        <th class="center" scope="col" width="70">货号</th>
	                                        <th class="center" scope="col">商品名称</th>
	                                        <th class="center" scope="col" width="70">货商</th>
	                                        <th class="center" scope="col" width="70">销售数量</th>
	                                        <th class="center" scope="col" width="70">库存数量</th>
	                                        <th class="center" scope="col" width="70">销售金额</th>
	                                        <th class="center" scope="col" width="70">库存金额</th>
	                                        <th class="center" scope="col" width="70">库销比(数量)</th>
	                                        <th class="center" scope="col" width="70">库销比(金额)</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach items="${vo.listA}" var="saleInfo" varStatus="status">
	                                    <tr>
	                                        <td class="center">${status.index + 1}</td>
	                                        <td class="center"><a style="text-decoration:underline;" target="_blank" href="${sc_ctx }/salesWeekGoodsTotalReport/contrast/${saleInfo.itemSubno }">${saleInfo.itemSubno }</a></td>
	                                        <td class="left"><a style="text-decoration:underline;cursor: pointer;" onclick="_abcModalClick('${saleInfo.itemSubno}','${saleInfo.goodsName }')" data-toggle="modal">${saleInfo.goodsName }</a></td>
	                                        <td class="center">${saleInfo.supName }</td>
	                                        <td class="right" style="font-size:20px">${saleInfo.posQty }</td>
	                                        <td class="right"> ${saleInfo.stockQty} </td>
	                                        <td class="right">${saleInfo.posAmt }</td>
	                                        <td class="right">${saleInfo.stockAmt }</td>

											<c:if test="${saleInfo.posQty.compareTo(BigDecimal.ZERO) == 0}">
	                                        <td class="center">-</td>
	                                        </c:if>
	                                        <c:if test="${saleInfo.posQty.compareTo(BigDecimal.ZERO) != 0}">
	                                        <td class="center">
	                                        
	                                        <c:set var="_tmpA" value="${saleInfo.stockQty / saleInfo.posQty}"/>
	                                       	<c:if test="${_tmpA <= 0.5}">
	                                        <span style="color: red;">
	                                        </c:if>
	                                        <c:if test="${_tmpA > 0.5 && _tmpA <= 0.8}">
	                                        <span style="color: orange;">
	                                        </c:if>
	                                        <c:if test="${_tmpA > 0.8}">
	                                        <span>
	                                        </c:if>
	                                        ${_tmpA}
	                                        </span>
	                                        </td>
	                                        </c:if>
	                                        	                                        
											<c:if test="${saleInfo.posAmt.compareTo(BigDecimal.ZERO) == 0}">
	                                        <td class="center">-</td>
	                                        </c:if>
	                                        <c:if test="${saleInfo.posAmt.compareTo(BigDecimal.ZERO) != 0}">
	                                        <td class="center">${saleInfo.stockAmt / saleInfo.posAmt}</td>
	                                        </c:if>
	                                    </tr>
	                                    </c:forEach>
	                                </tbody>
	                            </table>
	                        </div>
	            		</div>
	            	</div>
         		</div>

				<div class="span12">
         			<div class="portlet box blue">
	                    <div class="portlet-title">
	                        <div class="caption">
		                        分析表B
		                        
		                        <c:if test="${abcType == '1' || abcType == '2'}">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalBStockAmt} / ${vo.totalBAmt} / ${vo.totalAmt}</span>&nbsp;&nbsp;<span class="font2">(元)  库存金额 / 销售金额 / 总销售金额</span>
		                        	
		                        	<div style="margin-top: 10px">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalBQty} / ${vo.totalQty}</span>&nbsp;&nbsp;<span class="font2">(个)  销售数量 / 总销售数量</span>
		                        	</div>
		                        	
		                        	<div style="margin-top: 10px">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        	<c:if test="${vo.totalBAmt.compareTo(BigDecimal.ZERO) != 0}">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalBStockAmt / vo.totalBAmt}</span>&nbsp;&nbsp;<span class="font2">库销比</span>
		                        	</c:if>
		                        	</div>
	                        	</c:if>
	                        </div>
	                        <div class="tools">
                                 <a href="#" class="collapse"> </a>
                         	</div>
	                    </div>
	                    
	                    <div class="portlet-body">
	                        <div class="table-scrollable">
	                            <table class="table table-striped table-bordered table-hover mytable1" id="table_2">
	                                <thead>
	                                    <tr>
	                                        <th class="center" scope="col" width="50">行号</th>
	                                        <th class="center" scope="col" width="70">货号</th>
	                                        <th class="center" scope="col">商品名称</th>
	                                        <th class="center" scope="col" width="70">货商</th>
	                                        <th class="center" scope="col" width="70">销售数量</th>
	                                        <th class="center" scope="col" width="70">库存数量</th>
	                                        <th class="center" scope="col" width="70">销售金额</th>
	                                        <th class="center" scope="col" width="70">库存金额</th>
	                                        <th class="center" scope="col" width="70">库销比(数量)</th>
	                                        <th class="center" scope="col" width="70">库销比(金额)</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach items="${vo.listB}" var="saleInfo" varStatus="status">
	                                    <tr>
	                                        <td class="center">${status.index + 1}</td>
	                                        <td class="center"><a style="text-decoration:underline;" target="_blank" href="${sc_ctx }/salesWeekGoodsTotalReport/contrast/${saleInfo.itemSubno }">${saleInfo.itemSubno }</a></td>
	                                        <td class="left"><a style="text-decoration:underline;cursor: pointer;" onclick="_abcModalClick('${saleInfo.itemSubno}','${saleInfo.goodsName }')" data-toggle="modal">${saleInfo.goodsName }</a></td>
	                                        <td class="center">${saleInfo.supName }</td>
	                                        <td class="right" style="font-size:20px">${saleInfo.posQty }</td>
	                                        <td class="right"> ${saleInfo.stockQty} </td>
	                                        <td class="right">${saleInfo.posAmt }</td>
	                                        <td class="right">${saleInfo.stockAmt }</td>
											
											<c:if test="${saleInfo.posQty.compareTo(BigDecimal.ZERO) == 0}">
	                                        <td class="center">-</td>
	                                        </c:if>
	                                        <c:if test="${saleInfo.posQty.compareTo(BigDecimal.ZERO) != 0}">
	                                        <td class="center">
	                                        
	                                        <c:set var="_tmpA" value="${saleInfo.stockQty / saleInfo.posQty}"/>
	                                       	<c:if test="${_tmpA <= 0.5}">
	                                        <span style="color: red;">
	                                        </c:if>
	                                        <c:if test="${_tmpA > 0.5 && _tmpA <= 0.8}">
	                                        <span style="color: orange;">
	                                        </c:if>
	                                        <c:if test="${_tmpA > 0.8}">
	                                        <span>
	                                        </c:if>
	                                        ${_tmpA}</span>
	                                        </td>
	                                        </c:if>
	                                        
	                                        <c:if test="${saleInfo.posAmt.compareTo(BigDecimal.ZERO) == 0}">
	                                        <td class="center">-</td>
	                                        </c:if>
	                                        <c:if test="${saleInfo.posAmt.compareTo(BigDecimal.ZERO) != 0}">
	                                        <td class="center">${saleInfo.stockAmt / saleInfo.posAmt}</td>
	                                        </c:if>
	                                    </tr>
	                                    </c:forEach>
	                                </tbody>
	                            </table>
	                        </div>
	            		</div>
	            	</div>
         		</div>
         		
         		
         		<div class="span12">
         			<div class="portlet box yellow">
	                    <div class="portlet-title">
	                        <div class="caption">
		                        分析表C
		                        
		                        <c:if test="${abcType == '1' || abcType == '2'}">
		                        
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalCStockAmt} / ${vo.totalCAmt} / ${vo.totalAmt}</span>&nbsp;&nbsp;<span class="font2">(元)  库存金额 / 销售金额 / 总销售金额</span>
		                        	
		                        	<div style="margin-top: 10px">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalCQty} / ${vo.totalQty}</span>&nbsp;&nbsp;<span class="font2">(个)  销售数量 / 总销售数量</span>
		                        	</div>
		                        	
		                        	<div style="margin-top: 10px">
		                        	<c:if test="${vo.totalCAmt.compareTo(BigDecimal.ZERO) != 0}">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalCStockAmt / vo.totalCAmt}&nbsp;&nbsp;</span><span class="font2">库销比</span>
		                        	</c:if>
		                        	</div>
	                        	</c:if>
	                        </div>
	                        <div class="tools">
                                 <a href="#" class="collapse"> </a>
                         	</div>
	                    </div>
	                    
	                    <div class="portlet-body">
	                        <div class="table-scrollable">
	                            <table class="table table-striped table-bordered table-hover mytable1" id="table_3">
	                                <thead>
	                                    <tr>
	                                        <th class="center" scope="col" width="50">行号</th>
	                                        <th class="center" scope="col" width="70">货号</th>
	                                        <th class="center" scope="col">商品名称</th>
	                                        <th class="center" scope="col" width="70">货商</th>
	                                        <th class="center" scope="col" width="70">销售数量</th>
	                                        <th class="center" scope="col" width="70">库存数量</th>
	                                        <th class="center" scope="col" width="70">销售金额</th>
	                                        <th class="center" scope="col" width="70">库存金额</th>
	                                        <th class="center" scope="col" width="70">库销比(数量)</th>
	                                        <th class="center" scope="col" width="70">库销比(金额)</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                    <c:forEach items="${vo.listC}" var="saleInfo" varStatus="status">
	                                    <tr>
	                                        <td class="center">${status.index + 1}</td>
	                                        <td class="center"><a style="text-decoration:underline;" target="_blank" href="${sc_ctx }/salesWeekGoodsTotalReport/contrast/${saleInfo.itemSubno }">${saleInfo.itemSubno }</a></td>
	                                        <td class="left"><a style="text-decoration:underline;cursor: pointer;" onclick="_abcModalClick('${saleInfo.itemSubno}','${saleInfo.goodsName }')" data-toggle="modal">${saleInfo.goodsName }</a></td>
	                                        <td class="center">${saleInfo.supName }</td>
	                                        <td class="right" style="font-size:20px">${saleInfo.posQty }</td>
	                                        <td class="right"> ${saleInfo.stockQty} </td>
	                                        <td class="right">${saleInfo.posAmt }</td>
	                                        <td class="right">${saleInfo.stockAmt }</td>
	                                        
	                                        <c:if test="${saleInfo.posQty.compareTo(BigDecimal.ZERO) == 0}">
	                                        <td class="center">-</td>
	                                        </c:if>
	                                        <c:if test="${saleInfo.posQty.compareTo(BigDecimal.ZERO) != 0}">
	                                        <td class="center">
	                                        
	                                        <c:set var="_tmpA" value="${saleInfo.stockQty / saleInfo.posQty}"/>
	                                       	<c:if test="${_tmpA <= 0.5}">
	                                        <span style="color: red;">
	                                        </c:if>
	                                        <c:if test="${_tmpA > 0.5 && _tmpA <= 0.8}">
	                                        <span style="color: orange;">
	                                        </c:if>
	                                        <c:if test="${_tmpA > 0.8}">
	                                        <span>
	                                        </c:if>
	                                        ${_tmpA}</span>
	                                        </td>
	                                        </c:if>
	                                        
	                                        <c:if test="${saleInfo.posAmt.compareTo(BigDecimal.ZERO) == 0}">
	                                        <td class="center">-</td>
	                                        </c:if>
	                                        <c:if test="${saleInfo.posAmt.compareTo(BigDecimal.ZERO) != 0}">
	                                        <td class="center">${saleInfo.stockAmt / saleInfo.posAmt}</td>
	                                        </c:if>
	                                    </tr>
	                                    </c:forEach>
	                                </tbody>
	                            </table>
	                        </div>
	            		</div>
	            	</div>
         		</div>

         		<c:if test="${dTable == '1'}">
         		<div class="span12">
         			<div class="portlet box red">
	                    <div class="portlet-title">
	                        <div class="caption">
		                        分析表D（滞销商品）
		                        <c:if test="${abcType == '1' || abcType == '2'}">
		                        	<c:if test="${empty orgId}"><!-- 选择全部机构 -->
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalDAmt} / ${vo.totalDStockAmt}</span>&nbsp;&nbsp;<span class="font2">(元)  滞销库存金额 / 全机构库存总金额</span>
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalDAmt / vo.totalDStockAmt}</span>&nbsp;&nbsp;<span class="font2">库存比(金额)</span>
		                        	</c:if>
		                        	
		                        	<c:if test="${!empty orgId}"><!-- 选择非全部机构 -->
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalDAmt} / ${vo.totalDSelectStockAmt} / ${vo.totalDStockAmt}</span>&nbsp;&nbsp;<span class="font2">(元)  滞销库存金额 / 选择机构总库存金额 / 全机构库存总金额</span>
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalDAmt / vo.totalDSelectStockAmt}</span> / <span class="font1">${vo.totalDAmt / vo.totalDStockAmt}</span>&nbsp;&nbsp;<span class="font2">库存比(金额)</span>
		                        	</c:if>
		                        	<div style="margin-top: 10px">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="font1">${vo.totalDQty}</span>&nbsp;&nbsp;<span class="font2">(个)  库存数量</span>
		                        	</div>
		                        	
	                        	</c:if>
	                        </div>
	                        <div class="tools">
                                 <a href="#" class="collapse"> </a>
                         	</div>
	                    </div>
	                    
	                    <div class="portlet-body">
	                        <div class="table-scrollable">
	                            <table class="table table-striped table-bordered table-hover mytable1" id="table_4">
	                                <thead>
	                                    <tr>
	                                        <th class="center" scope="col" width="50">行号</th>
	                                        <th class="center" scope="col" width="70">货号</th>
	                                        <th class="center" scope="col">商品名称</th>
	                                        <th class="center" scope="col" width="70">货商</th>
	                                        <th class="center" scope="col" width="70">库存数量</th>
	                                        <th class="center" scope="col" width="70">库存金额</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                    <c:forEach items="${vo.listD}" var="saleInfo" varStatus="status">
	                                    <tr>
	                                        <td class="center">${status.index + 1}</td>
	                                        <td class="center"><a style="text-decoration:underline;" target="_blank" href="${sc_ctx }/salesWeekGoodsTotalReport/contrast/${saleInfo.itemSubno }">${saleInfo.itemSubno }</a></td>
	                                        <td class="left"><a style="text-decoration:underline;cursor: pointer;" onclick="_abcModalClick('${saleInfo.itemSubno}','${saleInfo.goodsName }')" data-toggle="modal">${saleInfo.goodsName }</a></td>
	                                        <td class="center">${saleInfo.supName }</td>
	                                        <td class="right"> ${saleInfo.stockQty} </td>
	                                        <td class="right">${saleInfo.stockAmt }</td>
	                                    </tr>
	                                    </c:forEach>
	                                </tbody>
	                            </table>
	                        </div>
	            		</div>
	            	</div>
         		</div>
         		</c:if>
         	</div>
         	</form>
        </div>
        
        <!-- Modal -->
		<div id="_abcModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		</div>
	</body>
</html>