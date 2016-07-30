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
	 	function detail_list(itemClsNo){
	 		$("#itemClsNo").val(itemClsNo);
            	
         	$("#listForm").attr("target", "_blank");
          	$("#listForm").attr("action", "${sc_ctx}/salesDayItemChartReport/org_goods_list");
         	$("#listForm").submit();
	 	}
 	</script>
    </head>
    <body>
    	<%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        
        <div class="container">
        	<form method="post"	class="form-inline" id="listForm">
        		<input type="hidden" name="orgId" id="orgId" value="${orgId}"/>
       			<input type="hidden" name="optDateShow_start" id="optDateShow_start" value="${optDateStart}"/>
            	<input type="hidden" name="optDateShow_end" id="optDateShow_end" value="${optDateEnd}"/>
            	<input type="hidden" name="itemClsNo" id="itemClsNo"/>
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
                                        实销金额 <span style="color: #FF9224">↓</span>
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
                                    	<a href="#" onclick="detail_list('${sumSaleRamt.itemClsNo}');" class="btn btn-warning"/>明细排名</a>
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