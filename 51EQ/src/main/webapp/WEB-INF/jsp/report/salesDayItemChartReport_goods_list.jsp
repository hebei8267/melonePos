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
                            <h3>${orgName} 类别()销售排名</h3>
                        </legend>
                    </div>
                    
                    <div class="span12">
                    	<h4>统计时间  ${optDateStart} ～ ${optDateEnd}</h4>
                    	<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th class="center">
                                        货号 / 商品名称
                                    </th>
                                    <th class="center">
                                        销售量
                                    </th>
                                    <th class="center">
                                        销售金额
                                    </th>
                                    <th class="center">
                                        日均销量
                                    </th>
                                    <th class="center">
                                        库存量
                                    </th>
                                    <th class="center">
                                        库存金额
                                    </th>
                                    <th class="center">
                                        进价 / 售价
                                    </th>
                                    <th	width="125">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${topList}" var="goodsInfo">
                            	<tr>
                                 	<td class="center">
                                 		${goodsInfo.itemBarcode} / ${goodsInfo.goodsName}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.posQty}
                                  	</td>
                                  	<td class="center">
                                    	${goodsInfo.posAmt}
                                  	</td>
                                  	<td class="center">
                                    	
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