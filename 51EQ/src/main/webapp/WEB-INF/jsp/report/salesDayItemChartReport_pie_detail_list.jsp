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
    </head>
    <body>

        <div class="container">
        	<form method="post"	class="form-inline" id="listForm">
        		<div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${orgName} 类别销售信息</h3>
                        </legend>
                    </div>
                    
                    <div class="span12">
                    	<h4>统计时间  ${optDateStart} ～ ${optDateEnd}</h4>
                    	<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th class="center">
                                        种类名称
                                    </th>
                                    <th class="center">
                                        实销金额
                                    </th>
                                    <th class="center">
                                        实销数量
                                    </th>
                                    <th	width="125">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${sumSaleRamtList}" var="sumSaleRamt">
                            	<tr>
                                 	<td class="center">
                                 		${sumSaleRamt.itemShortName}
                                  	</td>
                                  	<td class="center">
                                    	${sumSaleRamt.saleRamt}
                                  	</td>
                                  	<td class="center">
                                    	${sumSaleRamt.saleRqty}
                                  	</td>
                                  	<td class="center">
                                    	<a href="#" onclick="setOrgId('${orgIdList.get(status1.index) }');" class="btn btn-warning"/>明细排名</a>
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