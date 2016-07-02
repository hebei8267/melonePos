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
		
		<link href="${ctx}/assets/global/css/components.css" rel="stylesheet" id="style_components" type="text/css" />
    	<script>
    		var itemTypeListJson = ${itemTypeList};
			$(function() {
				$("#itemType").select2({
					tags : itemTypeListJson
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
			});
			//固定长度效验验证
			jQuery.validator.addMethod("add100", function(value, element) {
			    var abcParam1 = $("#abcParam1").val();
			    var abcParam2 = $("#abcParam2").val();
			    var abcParam3 = $("#abcParam3").val();
			    return (Number(abcParam1) + Number(abcParam2) + Number(abcParam3)) == 100
			}, "A,B,C百分比输入错误");
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
	                    <h3>毛利ABC分析表</h3>
	                </legend>
	            </div>
	            
	            <div class="span6">
                    <label class="control-label">查询时间 :</label>
                    <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                   	～
                 	<input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                </div>
                
                <div class="span6">
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
            
			<div class="row" style="padding-top: 10px">
				<div class="span3">
                    <label class="control-label">商品类别 :</label>
                    <input type="hidden" id="itemType" name="itemType" class="select2 input-medium" value="${itemType}">
                </div>
                
                <div class="span3">
                    <label class="control-label">货号 :</label>
                    <input id="itemSubno" name="itemSubno" type="text" class="input-medium" value="${itemSubno }"/>
                </div>
                
                <div class="span3">
                    <label class="control-label">商品名称 :</label>
                    <input id="itemName" name="itemName" type="text" class="input-medium" value="${itemName }"/>
                </div>
            </div>
            
			<div class="row" style="padding-top: 10px">    
                <div class="span3">
                    <label class="control-label">ABC系数 :</label>
                    <select name="abcType" class="input-medium">
                    	<option value=""></option>
                    	<option value="1" <c:if test="${abcType == '1'}">selected</c:if>>合计销售金额</option>
                    	<option value="2" <c:if test="${abcType == '2'}">selected</c:if>>合计销售数量</option>
                    	<option value="3" <c:if test="${abcType == '3'}">selected</c:if>>合计销售毛利</option>
                    </select>
                </div>
                
                <div class="span4">
                    <label class="control-label">A :</label>
                    <input id="abcParam1" name="abcParam1" type="text" class="input-mini_my" value="${abcParam1 }"/>%&nbsp;&nbsp;

                    <label class="control-label">B :</label>
                    <input id="abcParam2" name="abcParam2" type="text" class="input-mini_my" value="${abcParam2 }"/>%&nbsp;&nbsp;

                    <label class="control-label">C :</label>
                    <input id="abcParam3" name="abcParam3" type="text" class="input-mini_my" value="${abcParam3 }"/>%&nbsp;&nbsp;
                </div>
                
                <div class="span5">    
                    <button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
                </div>
	    	</div>
	    	
	    	<div class="row" style="padding-top: 10px">
         		<div class="span12">
         			<div class="portlet box green">
	                    <div class="portlet-title">
	                        <div class="caption">分析表A</div>
	                        <div class="tools">
                                 <a href="#" class="collapse"> </a>
                         	</div>
	                    </div>
	                    
	                    <div class="portlet-body">
	                        <div class="table-scrollable">
	                            <table class="table table-striped table-bordered table-hover mytable1">
	                                <thead>
	                                    <tr>
	                                        <th class="center" scope="col">行号</th>
	                                        <th class="center" scope="col">货号</th>
	                                        <th class="center" scope="col">商品名称</th>
	                                        <th class="center" scope="col">日均销量</th>
	                                        <th class="center" scope="col">库存数量</th>
	                                        <th class="center" scope="col">销售数量</th>
	                                        <th class="center" scope="col">销售金额</th>
	                                        <th class="center" scope="col">有库存店数</th>
	                                        <th class="center" scope="col">有销售店数</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach items="${vo.listA}" var="saleInfo" varStatus="status">
	                                    <tr>
	                                        <td class="center">${status.index + 1}</td>
	                                        <td class="center">${saleInfo.itemSubno }</td>
	                                        <td class="left">${saleInfo.goodsName }</td>
	                                        <td class="right">${saleInfo.posQty }?</td>
	                                        <td class="right"> Table data </td>
	                                        <td class="right">${saleInfo.posQty }</td>
	                                        <td class="right">${saleInfo.posAmt }</td>
	                                        <td class="right"> Table data </td>
	                                        <td class="right"> Table data </td>
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
	                        <div class="caption">分析表B</div>
	                        <div class="tools">
                                 <a href="#" class="collapse"> </a>
                         	</div>
	                    </div>
	                    
	                    <div class="portlet-body">
	                        <div class="table-scrollable">
	                            <table class="table table-striped table-bordered table-hover mytable1">
	                                <thead>
	                                    <tr>
	                                        <th class="center" scope="col">行号</th>
	                                        <th class="center" scope="col">货号</th>
	                                        <th class="center" scope="col">商品名称</th>
	                                        <th class="center" scope="col">日均销量</th>
	                                        <th class="center" scope="col">库存数量</th>
	                                        <th class="center" scope="col">销售数量</th>
	                                        <th class="center" scope="col">销售金额</th>
	                                        <th class="center" scope="col">有库存店数</th>
	                                        <th class="center" scope="col">有销售店数</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach items="${vo.listB}" var="saleInfo" varStatus="status">
	                                    <tr>
	                                        <td class="center">${status.index + 1}</td>
	                                        <td class="center">${saleInfo.itemSubno }</td>
	                                        <td class="left">${saleInfo.goodsName }</td>
	                                        <td class="right">${saleInfo.posQty }?</td>
	                                        <td class="right"> Table data </td>
	                                        <td class="right">${saleInfo.posQty }</td>
	                                        <td class="right">${saleInfo.posAmt }</td>
	                                        <td class="right"> Table data </td>
	                                        <td class="right"> Table data </td>
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
	                        <div class="caption">分析表C</div>
	                        <div class="tools">
                                 <a href="#" class="collapse"> </a>
                         	</div>
	                    </div>
	                    
	                    <div class="portlet-body">
	                        <div class="table-scrollable">
	                            <table class="table table-striped table-bordered table-hover mytable1">
	                                <thead>
	                                    <tr>
	                                        <th class="center" scope="col">行号</th>
	                                        <th class="center" scope="col">货号</th>
	                                        <th class="center" scope="col">商品名称</th>
	                                        <th class="center" scope="col">日均销量</th>
	                                        <th class="center" scope="col">库存数量</th>
	                                        <th class="center" scope="col">销售数量</th>
	                                        <th class="center" scope="col">销售金额</th>
	                                        <th class="center" scope="col">有库存店数</th>
	                                        <th class="center" scope="col">有销售店数</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                    <c:forEach items="${vo.listC}" var="saleInfo" varStatus="status">
	                                    <tr>
	                                        <td class="center">${status.index + 1}</td>
	                                        <td class="center">${saleInfo.itemSubno }</td>
	                                        <td class="left">${saleInfo.goodsName }</td>
	                                        <td class="right">${saleInfo.posQty }?</td>
	                                        <td class="right"> Table data </td>
	                                        <td class="right">${saleInfo.posQty }</td>
	                                        <td class="right">${saleInfo.posAmt }</td>
	                                        <td class="right"> Table data </td>
	                                        <td class="right"> Table data </td>
	                                    </tr>
	                                    </c:forEach>
	                                </tbody>
	                            </table>
	                        </div>
	            		</div>
	            	</div>
         		</div>
         		
         	</div>
         	</form>
        </div>
	</body>
</html>