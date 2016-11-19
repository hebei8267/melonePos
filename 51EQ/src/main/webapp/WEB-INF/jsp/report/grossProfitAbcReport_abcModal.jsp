<%@	page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h3 id="myModalLabel">销售商品明细信息</h3>
</div>
<div class="modal-body">
	<p>
		&nbsp;&nbsp;销售时间&nbsp;&nbsp;${optDateShowStart} ～ ${optDateShowEnd}<br><br>
		<span class="font1">&nbsp;&nbsp;${goodsName}&nbsp;[${itemSubno}]</span>
	</p>
	<table class="table table-striped table-bordered table-hover mytable1">
		<thead>
	        <tr>
	            <th class="center">店号</th>
	            <th class="center">销售数量</th>
	            <th class="center">当前库存数量</th>
	            <th class="center">库销比(数量)</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:forEach items="${goodList}" var="good" varStatus="status">
	    	<tr>
	    		<td class="center" <c:if test="${('合计' == good.orgId) || ('EQ+' == good.orgId) || ('Infancy' == good.orgId) || ('总部' == good.orgId)}" >style="color: red;"</c:if>>
					<c:if test="${fn:length(good.orgId) == 6}">
	             	${fn:substring(good.orgId,3,6)}&nbsp;
	             	</c:if>
	             	<c:if test="${fn:length(good.orgId) != 6}">
	             	${good.orgId}&nbsp;
	             	</c:if>
				</td>
				<td class="center">
					${good.posQty}
				</td>
				<td class="center">
					${good.stockQty}
				</td>
				
				<c:if test="${good.posQty.compareTo(BigDecimal.ZERO) == 0}">
                <td class="center">-</td>
                </c:if>
                <c:if test="${good.posQty.compareTo(BigDecimal.ZERO) != 0}">
                <td class="center">${good.stockQty / good.posQty}</td>
                </c:if>
	    	</tr>
	    	</c:forEach>
	    </tbody>
	</table>
</div>
<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
</div>