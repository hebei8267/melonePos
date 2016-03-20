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
    	<script>
			$().ready(function() {
				$("#searchForm").validate({
					rules : {
						barcode : {
							required : true
						}
					}
				});

				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					$("#searchForm").attr('target', '_self');
					$("#searchForm").attr("action", "${sc_ctx}/salesWeekGoodsTotalReport/contrast/" + $("#barcode").val());
					$("#searchForm").submit();
				});
			});
		</script>
		<style>
		.font2 {
			font-family : "Microsoft YaHei" ! important;
			font-size : 16px;
			font-weight : bolder;
		}
		.font1 {
			font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
			font-size : 16px;
			font-weight : bolder;
		}
		</style>
		
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
           
            <div class="row">
                <div class="span12">
                    <legend>
                        <h3>商品周销售信息对比(按商品)</h3>
                    </legend>
                </div>
                <form method="post"	class="form-inline"	id="searchForm">
					<div class="span8">
						<label class="control-label">货号 : </label>
						<input id="barcode" name="barcode" type="text" class="input-xlarge" placeholder="多个货号需以英文半角逗号隔开"/>
						&nbsp;&nbsp;
						<button	id="searchBtn" class="btn btn-primary" type="button">
							查询
						</button>
					</div>
				</form>
				
				<c:forEach items="${list}" var="salesWeekGoodsList">
                <div class="span12"	style="margin-top: 10px;">
                    <table class="table	table-striped table-bordered table-condensed mytable">
                        <thead>
                            <tr>
                            	<th rowspan="2" class="center">
                                    机构
                                </th>
                                <th rowspan="2" class="center">
                                    货号
                                </th>
                                <th rowspan="2" class="center">
                                    名称
                                </th>
                                <th rowspan="2" class="center"  style="background-image: linear-gradient(to bottom,#fbb450,#f89406);">
                                    半月销售量
                                </th>
                                <th rowspan="2" class="center"  style="background-image: linear-gradient(to bottom,#fbb450,#f89406);">
                                    调货
                                </th>
                                <c:if test="${sessionScope.__SESSION_USER_INFO.orgUuid == 1}">
                                <th rowspan="2" class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                    进价
                                </th>
                                </c:if>
                                <th rowspan="2" class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                    售价
                                </th>
                                <th colspan="6" class="center">
                                    销量(件)
                                </th>
                                <th colspan="5" class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                    销售额(元)
                                </th>
                            </tr>
                            <tr>
                            	<th class="center">
                                    库存量
                                </th>
                                <th class="center">
                                    合计
                                </th>
                                <th class="center">
                                    近一周
                                </th>
                                <th class="center">
                                    近两周
                                </th>
                                <th class="center">
                                    近三周
                                </th>
                                <th class="center">
                                    近四周
                                </th>
                                
                                <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                    合计
                                </th>
                                <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                    近一周
                                </th>
                                <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                    近二周
                                </th>
                                <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                    近三周
                                </th>
                                <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                    近四周
                                </th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${salesWeekGoodsList}" var="salesWeekGoods" varStatus="status1">
                                <tr>
                                	<td class="center">
                                    	${salesWeekGoods.orgId}
                                    </td>
                                    <c:if test="${status1.index == 0}" >
                                    <td rowspan="${salesWeekGoodsList.size()}" class="center">
                                    	${salesWeekGoods.barcode}
                                    </td>
                                    
                                    <td rowspan="${salesWeekGoodsList.size()}" class="center">
                                    	${salesWeekGoods.productName}
                                    </td>
                                    </c:if>
                                    <td class="center font2">
                                    	<fmt:formatNumber value="${salesWeekGoods.hmPosQty}" maxFractionDigits="0"/>
                                    </td>
                                    
                                    <td class="center font2">
                                    	<c:if test="${status1.index == 0}" >
                                    		<c:if test="${salesWeekGoods.inQty > 0}" >
                                    		<fmt:parseNumber value="${salesWeekGoods.inQty}" var="a" />
                                    		<span style="color : #FF0000">调入 [${a}]</span>
                                    		</c:if>
                                    		
                                    		<c:if test="${salesWeekGoods.inQty < 0}" >
                                    		<fmt:parseNumber value="${salesWeekGoods.inQty}" var="b" />
                                    		<span style="color : #5bc0de">调出 [${-b}]</span>
                                    		</c:if>
                                    	</c:if>
                                    	
                                    	<c:if test="${status1.index != 0}" >
                                    		<c:if test="${salesWeekGoods.inQty > 0}" >
                                    		<fmt:parseNumber value="${salesWeekGoods.inQty}" var="a" />
                                    		<span style="color : #FF0000">调入 [${a}]</span>
                                    		</c:if>
                                    	
                                    		<c:if test="${salesWeekGoods.outQty > 0}" >
                                    		<fmt:parseNumber value="${salesWeekGoods.outQty}" var="b" />
                                    		<span style="color : #5bc0de">调出 [${b}]</span>
                                    		</c:if>
                                    	</c:if>
                                    </td>
                                    
                                    <c:if test="${sessionScope.__SESSION_USER_INFO.orgUuid == 1}">
                                    <td class="center">
                                    	${salesWeekGoods.stockAmt}
                                    </td>
                                    </c:if>
                                    
                                    <td class="center">
                                    	${salesWeekGoods.itemSaleAmt}
                                    </td>
                                    
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.stockQty}" maxFractionDigits="0"/>
                                    </td>
                                    <td class="right">
                                    	<label class="font1"><fmt:formatNumber value="${salesWeekGoods.posQtyTotal}" maxFractionDigits="0"/></label>
                                    </td>
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.posQty1}" maxFractionDigits="0"/>
                                    </td>
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.posQty2}" maxFractionDigits="0"/>
                                    </td>
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.posQty3}" maxFractionDigits="0"/>
                                    </td>
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.posQty4}" maxFractionDigits="0"/>
                                    </td>
                                    
                                    <td class="right">
                                    	<label class="font1"><fmt:formatNumber value="${salesWeekGoods.posAmtTotal}" maxFractionDigits="0"/></label>
                                    </td>
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.posAmt1}" maxFractionDigits="0"/>
                                    </td>
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.posAmt2}" maxFractionDigits="0"/>
                                    </td>
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.posAmt3}" maxFractionDigits="0"/>
                                    </td>
                                    <td class="right">
                                    	<fmt:formatNumber value="${salesWeekGoods.posAmt4}" maxFractionDigits="0"/>
                                    </td>
                                    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:forEach>
                
            </div>
          
        </div>
        
        <script>
			$(function() {
				
			});
		</script>
    </body>
</html>