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
            $(function() {
            	$("#listForm").validate({
                    rules : {
                    	orgId : {
                    		required : true                 		
                        }
                    }
                });

                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/salesWeekGoodsTotalReport/search");
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
                            <h3>商品周销售信息一览</h3>
                        </legend>
                    </div>
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
                    </div>
                    <div class="span9" style="margin-top: 20px;">
                        <label class="control-label">货号/条码 :</label>
                        <input id="barcode" name="barcode" type="text" class="input-medium" value="${barcode }"/>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th rowspan="2" class="center">
                                        货号
                                    </th>
                                    <th rowspan="2" class="center">
                                        名称
                                    </th>
                                    <th colspan="6" class="center">
                                        销量(件)
                                    </th>
                                    <th colspan="5" class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                        销售额(元)
                                    </th>
                                    <th	width="55" rowspan="2">
                                        &nbsp;
                                    </th>
                                </tr>
                                <tr>
                                	<th class="center">
                                        近一周
                                    </th>
                                    <th class="center">
                                        近二周
                                    </th>
                                    <th class="center">
                                        近三周
                                    </th>
                                    <th class="center">
                                        近四周
                                    </th>
                                    <th class="center">
                                        合计
                                    </th>
                                    <th class="center">
                                        库存量
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
                                    <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
                                        合计
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${salesWeekGoodsList}" var="salesWeekGoods">
                                    <tr>
                                        <td>
                                        	${salesWeekGoods.barcode}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.productName}
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
                                        	<fmt:formatNumber value="${salesWeekGoods.posQtyTotal}" maxFractionDigits="0"/>
                                        </td>
                                        <td class="right">
                                        	<fmt:formatNumber value="${salesWeekGoods.stockQty}" maxFractionDigits="0"/>
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
                                        <td class="right">
                                        	<fmt:formatNumber value="${salesWeekGoods.posAmtTotal}" maxFractionDigits="0"/>
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/salesWeekGoodsTotalReport/contrast/${salesWeekGoods.barcode}" target="_blank" class="btn btn-warning"/>对比</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	salesWeekGoodsList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="14" class="rounded-foot-left">
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