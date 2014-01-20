<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <div class="span3">
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
                    <div class="span9">
                        <label class="control-label">货号/条码 :</label>
                        <input id="barcode" name="barcode" type="text" class="input-medium" value="${barcode }"/>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th>
                                        货号
                                    </th>
                                    <th>
                                        名称
                                    </th>
                                    <th>
                                        近一周(销量)
                                    </th>
                                    <th>
                                        近两周(销量)
                                    </th>
                                    <th>
                                        近三周(销量)
                                    </th>
                                    <th>
                                        近四周(销量)
                                    </th>
                                    <th>
                                        合计(销量)
                                    </th>
                                    <th>
                                        库存量
                                    </th>
                                    <th	width="55">
                                        &nbsp;
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
                                        <td>
                                        	${salesWeekGoods.posQty1}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posQty2}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posQty3}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posQty4}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.posQtyTotal}
                                        </td>
                                        <td>
                                        	${salesWeekGoods.stockQty}
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
                                        <td	colspan="8"	class="rounded-foot-left">
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