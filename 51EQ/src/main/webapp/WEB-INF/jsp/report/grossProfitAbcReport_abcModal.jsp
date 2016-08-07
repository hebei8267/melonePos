<%@	page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h3 id="myModalLabel">销售商品明细信息</h3>
</div>
<div class="modal-body">
	<p class="font1">&nbsp;&nbsp;${goodsName}&nbsp;[${itemSubno}]</p>
	<table class="table table-striped table-bordered table-hover mytable1">
		<thead>
	        <tr>
	            <th class="center" scope="col">有销售店数</th>
	            <th class="center" scope="col">有库存店数</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<tr>
	    		<td class="center"><c:out value="${fn:length(saleOrgList)}"></c:out></td>
	    		<td class="center"><c:out value="${fn:length(storeOrgList)}"></c:out></td>
	    	</tr>
	    	<tr>
	    		<td class="center">
	    			<c:forEach items="${saleOrgList}" var="saleOrg" varStatus="status">
	    				<c:if test="${fn:length(saleOrg) > 4}">
		             	${fn:substring(saleOrg,3,6)}&nbsp;
		             	</c:if>
	    			</c:forEach>
	    		</td>
	    		<td class="center">
	    			<c:forEach items="${storeOrgList}" var="saleOrg" varStatus="status">
	    				<c:if test="${fn:length(saleOrg) > 4}">
		             	${fn:substring(saleOrg,3,6)}&nbsp;
		             	</c:if>
	    			</c:forEach>
	    		</td>
	    	</tr>
	    </tbody>
	</table>
</div>
<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
</div>