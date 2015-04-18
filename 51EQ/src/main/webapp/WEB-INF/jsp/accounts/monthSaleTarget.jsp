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
<!DOCTYPE html>
<html>
    <head>
    	<style>
        ._warn1 {
			color: #FF9224;
		}
    	</style>
        <script>
        $(function() {
        	$("#searchForm").validate({
                rules : {
                	optDateY : {
                		required : true                 		
                    },
                    saleRamtTarget : {
                  		money : true
                  	}
                }
            });
        	$("#showBtn").click(function() {
        		$("#searchForm").attr('target', '_self');
                $("#searchForm").attr("action", "${sc_ctx}/monthSaleTarget/show");
                $("#searchForm").submit();
            });
            $("#exportBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#searchForm").attr('target', '_self');
                $("#searchForm").attr("action", "${sc_ctx}/monthSaleTarget/export");
                $("#searchForm").submit();
			});
            $("#saveBtn").click(function() {
        		$("#searchForm").attr('target', '_self');
                $("#searchForm").attr("action", "${sc_ctx}/monthSaleTarget/save");
                $("#searchForm").submit();
            });
        });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <form method="post"	class="form-inline"	id="searchForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>机构月销售目标管理</h3>
                        </legend>
                    </div>
                    <div class="span3">
                        <label class="control-label">年份 : </label>
                        <select name="optDateY" class="input-medium">
                        	<c:forEach items="${yearList}" var="year">
	                        	<c:if test="${year.key == optDateY}">
	                                <option value="${year.key }" selected>${year.value }</option>
	                            </c:if>
	                            <c:if test="${year.key != optDateY}">
	                                <option value="${year.key }">${year.value }</option>
	                            </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="span9">
                    	<label class="control-label">机构 : </label>
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
                        &nbsp;&nbsp;
                        <button	id="showBtn" class="btn	btn-primary" type="button">显示</button>
                        <button	id="exportBtn" class="btn btn-warning" type="button">数据导出</button>
                    </div>
                    
                    <c:if test="${showFlg}" >
                    <div class="span12" style="margin-top: 10px;">
                    	<span class="_warn1">"橙色文字"是去年同期销售额</span>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th class="center">机构</th>
						            <th width="80" class="center">01月</th>
						            <th width="80" class="center">02月</th>
						            <th width="80" class="center">03月</th>
						            <th width="80" class="center">04月</th>
						            <th width="80" class="center">05月</th>
						            <th width="80" class="center">06月</th>
						            <th width="80" class="center">07月</th>
						            <th width="80" class="center">08月</th>
						            <th width="80" class="center">09月</th>
						            <th width="80" class="center">10月</th>
						            <th width="80" class="center">11月</th>
						            <th width="80" class="center">12月</th>
                                </tr>
                            </thead>
                            <tbody>
                            <tr>
                            <td class="center">合计</td>
                            <c:forEach items="${totalList}" var="total">
                            <td class="center">${total.saleRamt2 }</td>
                            </c:forEach>
                            </tr>
                            <c:forEach items="${lastYearSalesList}" var="_lastYearSalesList" varStatus="status">
                            	<tr>
                            	<input type="hidden" name="_orgId" value="${orgNameList.get(status.index) }">
                            	<td class="center">
                            		${orgNameList.get(status.index) }
                            	</td>
                            	<c:forEach items="${_lastYearSalesList}" var="lastYearSale">
                            	<td class="center">
                            		<span class="_warn1">${lastYearSale.saleRamt1}</span><br><br>
                            		<input type="text" class="input-mini" name="saleRamtTarget" value="${lastYearSale.saleRamt2}">
                            	</td>
                            	</c:forEach>
                            	</tr>
                            </c:forEach>
                            </tbody>
                            <c:if test="${empty	lastYearSalesList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="13" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                    <div class="span12" style="text-align: right;">
                        <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                    </div>
                    </c:if>
                </div>
            </form>
        </div>
    </body>
</html>