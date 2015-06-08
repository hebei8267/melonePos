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
		.form-horizontal .control-label {
            width: 130px;
        }
        .form-horizontal .controls {
            margin-left: 145px;
        }
        ._warn1 {
    		padding: 5px;
			background-color: #99FF33;
		}
		._warn2 {
			padding: 5px;
			background-color: #FFCC33;
		}
		.left-control-label {
			font-size: 16px;
			color: #000;
			font-weight: normal;
		}
    </style>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        <form method="post"	class="form-horizontal">
        <div class="container">
        	<div class="row">
                <div class="span12">
                    <legend>
                        <h3>货单查看</h3>
                    </legend>
                </div>
                
                <div class="span3">
					<label class="control-label">货单编号 :</label>
					<label class="left-control-label">${order.orderNo}</label>
                </div>
                
                <div class="span3">
                	<label class="control-label">收货机构 :</label>
                	<label class="left-control-label">${order.replenishOrgId}</label>
				</div>
				
				<div class="span3">
					<label class="control-label">货单状态 :</label>
					<label class="left-control-label">
					<c:if test="${order.orderState == '01'}">
                        编辑中
                    </c:if>
                    <c:if test="${order.orderState == '02'}">
                        已发货
                    </c:if>
                    <c:if test="${order.orderState == '03'}">
                        收货中
                    </c:if>
                    <c:if test="${order.orderState == '99'}">
                        已完结
                    </c:if>
					</label>
                </div>
                
                <div class="span3">
					<label class="control-label">错填次数 :</label>
					<label class="left-control-label"><span class="warn_text">${order.errorNum}</span></label>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                	<table class="table	table-striped table-bordered table-condensed mytable">
                		<thead>
                            <tr>
                                <th class="center">
                                    条码
                                </th>
                                <th class="center">
                                    名称
                                </th>
                                <th class="center">
                                    类型
                                </th>
                                <th class="center">
                                    货商名称
                                </th>
                                <th class="center">
                                    补货数量
                                </th>
                                <th class="center">
                                    收货数量
                                </th>
                                
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${order.detailList}" var="detail">
                        	<tr>
                                <td	class="center">
                                	${detail.productBarcode}
                                </td>
                                <td	class="center">
                                    ${detail.goodsName}
                                </td>
                                <td	class="center">
                                    ${detail.goodsItemNo}
                                </td>
                                <td	class="center">
                                	${detail.supplierName}
                                </td>
                                <td	class="center">
                                    ${detail.replenishNum}
                                </td>
                                <td	class="center">
                                    ${detail.receiptNum}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <c:if test="${empty	order.detailList}" >
                            <tfoot>
                                <tr>
                                    <td	colspan="6" class="rounded-foot-left">
                                        无记录信息
                                    </td>
                                </tr>
                            </tfoot>
                        </c:if>
                	</table>
                </div>
                
                <div class="control-group">
	                <label class="control-label">备注 :</label>
	                <label class="left-control-label">${order.description}</label>
                </div>
                
          	</div>
        </div>
        </form>
	</body>
</html>