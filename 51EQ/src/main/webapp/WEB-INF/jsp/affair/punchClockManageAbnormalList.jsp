<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<style type="text/css">
    		.myTable th, .myTable td {
				border: 0;
			}
			._warn1 {
				padding: 10px;
				background-color: #00DB00;
			}
			._warn2 {
				padding: 10px;
				background-color: #B15BFF;
			}
			._warn3 {
				padding: 10px;
				background-color: #FF9224;
			}
			._warn4 {
				padding: 10px;
				background-color: #B87070;
			}
    	</style>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        
        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>异常考勤查看</h3>
                        </legend>
                    </div>
                    <div class="span2">
                        考勤日期 : ${optDateShow }
                    </div>
                    <div class="span6">
                    	
                        机构 : 
                        <c:if test="${fn:length(orgId) > 3}">
                    	${fn:substring(orgId,3,6)}
                    	</c:if>
                    	<c:if test="${fn:length(orgId) <= 3}">
                    	总部
                    	</c:if>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th class="center">
                                        职员名称
                                    </th>
                                    <th class="center">
                                        异常类型
                                    </th>
                                    <th class="center" colspan="2">
                                        考勤时间
                                    </th>
                                    <th class="center" colspan="2">
                                        排班时间
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                          
                            <c:forEach items="${abnormalClockList}" var="abnormalClock">
                            <tr>
	                            <td class="center">${abnormalClock.empName}</td>
	                            <td class="center">
	                            <c:if test="${abnormalClock.punchNormalState == 99}">
		                    	<span class="_warn1">非预期加班</span>
		                    	</c:if>
		                    	<c:if test="${abnormalClock.punchNormalState == 1}">
		                    	<span class="_warn2">迟到</span>
		                    	</c:if>
		                    	<c:if test="${abnormalClock.punchNormalState == 2}">
		                    	<span class="_warn3">早退</span>
		                    	</c:if>
		                    	<c:if test="${abnormalClock.punchNormalState == 3}">
		                    	<span class="_warn4">工作时长不满</span>
		                    	</c:if>
	                            </td>
	                            <td class="center">${abnormalClock.startClockTime}</td>
	                            <td class="center">${abnormalClock.endClockTime}</td>
	                            <td class="center">${abnormalClock.startScheduleTime}</td>
	                            <td class="center">${abnormalClock.endScheduleTime}</td>
                            </tr>
							</c:forEach>
                            </tbody>
                            
                            <c:if test="${empty	abnormalClockList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="5" class="rounded-foot-left">
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