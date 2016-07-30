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
    	<style type="text/css">
			._warn1 {
				padding: 10px;
				background-color: #00DB00;
			}
			._warn2 {
				padding: 10px;
				background-color: #FF9224;
			}
    	</style>
    
    <script>	 	
	 	function detail_list(){
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
                            <h3>${orgName} (${supplier.name})类别 销售排名</h3>
                        </legend>
                    </div>
                    
                    <div class="span12">
                    	<h4>统计时间  ${optDateStart} ～ ${optDateEnd}</h4>
                    </div>
                    
                    <div class="span12">
                    	<span class="_warn1">畅销商品</span>
                    </div>
                    <div class="span12">                    	
                    	<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                	<th class="center">
                                        NO
                                    </th>
                                    <th class="center">
                                        货号商品名称
                                    </th>
                                    <th class="center">
                                        销售量
                                    </th>
                                    <th class="center">
                                        销售金额 <span style="color: #FF9224">↓</span>
                                    </th>
                                    <th class="center">
                                        日均销量
                                    </th>
                                    <th class="center">
                                        库存量
                                    </th>
                                    <th class="center">
                                        供应商名称
                                    </th>
                                    <th	width="85">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${topList}" var="goodsInfo" varStatus="status">
                            	<tr>
                            		<td class="center">
                                 		${status.index + 1}
                                  	</td>
                                 	<td class="center">
                                 		${goodsInfo.itemBarcode}<br>${goodsInfo.goodsName}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.posQty}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.posAmt}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.averageDailySales}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.stockQty}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.supName}
                                  	</td>
                                  	<td class="center">
                                    	<a href="${sc_ctx}/salesWeekGoodsTotalReport/contrast/${goodsInfo.itemSubno}" target="_blank" class="btn btn-warning"/>门店对比</a>
                                  	</td>
                              	</tr>
                            	</c:forEach>
                            </tbody>
                    	</table>
                    </div>
                    
                    <div class="span12">
                    	<span class="_warn2">滞销商品</span>
                    </div>
                    <div class="span12">
                    	<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                	<th class="center">
                                        NO
                                    </th>
                                    <th class="center">
                                        货号商品名称
                                    </th>
                                    <th class="center">
                                        销售量
                                    </th>
                                    <th class="center">
                                        销售金额 <span style="color: #FF9224">↓</span>
                                    </th>
                                    <th class="center">
                                        日均销量
                                    </th>
                                    <th class="center">
                                        库存量
                                    </th>
                                    <th class="center">
                                        供应商名称
                                    </th>
                                    <th	width="85">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${downList}" var="goodsInfo" varStatus="status">
                            	<tr>
                                 	<td class="center">
                                 		${status.index + 1}
                                  	</td>
                                 	<td class="center">
                                 		${goodsInfo.itemBarcode}<br>${goodsInfo.goodsName}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.posQty}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.posAmt}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.averageDailySales}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.stockQty}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.supName}
                                  	</td>
                                  	<td class="center">
                                    	<a href="${sc_ctx}/salesWeekGoodsTotalReport/contrast/${goodsInfo.itemSubno}" target="_blank" class="btn btn-warning"/>门店对比</a>
                                  	</td>
                              	</tr>
                            	</c:forEach>
                            </tbody>
                    	</table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>