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
    	.cash_daily {
			border-bottom: 3px solid #F89406;
			margin-top: 20px;
			margin-bottom: 20px;
		}
    	</style>
		<script>
    		$(function() {
    			$('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
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
                        itemTypeNo : {
                    		required : true
                        }
                    }
                });
                
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/salesItemRankReport/search");
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
                            <h3>销售信息排名(按类别)</h3>
                        </legend>
                    </div>
                    
                
					<div class="span12">
						<form method="post"	class="form-inline" id="listForm">
				    		<div class="row">
				    			<div class="span12">
			                        <label class="control-label">排序方式 :</label>
			                        <c:set var = "qty" value="qty"/>
			                      	<c:if test="${orderMode.equals(qty)}" >
				                      	<input type="radio" name="orderMode" value="qty" checked="checked">
							  			<span style="background-color: #5bc0de; padding: 5px">销量</span>
							  			<input type="radio" name="orderMode" value="amt">
							  			<span style="background-color: #62c462; padding: 5px">销售额</span>
			                      	</c:if>
						            <c:if test="${!orderMode.equals(qty)}" >
				                      	<input type="radio" name="orderMode" value="qty">
							  			<span style="background-color: #5bc0de; padding: 5px">销量</span>
							  			<input type="radio" name="orderMode" value="amt" checked="checked">
							  			<span style="background-color: #62c462; padding: 5px">销售额</span>
			                      	</c:if>
			                    </div>
                    
				    			<div class="span5" style="margin-top: 20px;">
				    				<label class="control-label">销售日期 :</label>
				    				<input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
				                        ～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
				    			</div>
				    			<div class="span3" style="margin-top: 20px;">
				    				<label class="control-label">商品类别 :</label>
				    				<select name="itemTypeNo" class="input-medium">
				    				<c:forEach items="${itemTypeList}" var="itemType">
					               		<c:if test="${itemType.key == itemTypeNo}">
					                  		<option value="${itemType.key }" selected>${itemType.value }</option>
					                  	</c:if>
				                      	<c:if test="${itemType.key != itemTypeNo}">
				                       		<option value="${itemType.key }">${itemType.value }</option>
				                    	</c:if>
				                  	</c:forEach>
				                  	</select>
				    			</div>
				    			<div class="span3" style="margin-top: 20px;">
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
				                    <button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
				    			</div>
				    		</div>
				    	</form>
            		</div>
                
                	<c:forEach items="${goodList}" var="_goodList" varStatus="index1">
                	<div class="span12 cash_daily"></div>
                	<div class="span12"><h4>机构 : ${orgNameList.get(index1.index) }</h4></div>
                	<div class="span12"	style="margin-top: 10px;">
                		<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                	<th width="50" class="center">
                                        序号
                                    </th>
                                    <th class="center">
                                        货号
                                    </th>
                                    <th class="center">
                                        商品名称
                                    </th>
                                    <th class="center">
                                        供应商
                                    </th>
                                    <th class="center">
                                        销量(件)
                                    </th>
                                    <th style="background-image: linear-gradient(to bottom,#62c462,#51a351);" class="center">
                                        销售额(元)
                                    </th>
                                    <th class="center">
                                        库存数量
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            
                            <c:forEach items="${_goodList}" var="good" varStatus="status1">
	                            <tr>
	                            	<td class="center">${status1.index+1}</td>
                                    <td class="center">${good.itemSubno}</td>
	                            	<td class="center">${good.goodsName}</td>
	                            	<td class="center">${good.supName}</td>
	                            	<td class="center">${good.posQty}</td>
	                            	<td class="right">${good.posAmt}</td>
	                            	<td class="center">${good.stockQty}</td>
	                           	</tr>
                            </c:forEach>
                            
                            </tbody>
                            <c:if test="${empty	_goodList}" >
                           	<tfoot>
                            	<tr>
                              		<td	colspan="6"	class="rounded-foot-left">
                                 	无记录信息
                                 	</td>
                            	</tr>
                           	</tfoot>
                            </c:if>
                        </table>
                	</div>
                	</c:forEach>
                </div>
            </form>
        </div>
    </body>
</html>