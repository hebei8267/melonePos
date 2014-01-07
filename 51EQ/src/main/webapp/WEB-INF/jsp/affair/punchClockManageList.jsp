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
				background-color: #FF2400;
			}
			._warn2 {
				background-color: #5FFB17;
			}
    	</style>
    	<script>
	        $(function() {
	        	$("#listForm").validate({
	                rules : {
	                	optDateShow : {
	                		required : true
	                    }
	                }
	            });
	        	
	            $('#optDateShow').datepicker({
	            	format : 'yyyy-mm',
	            	viewMode : 1,
	            	minViewMode : 1
	            });
	            
	            $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/punchClock/manage/search");
                    $("#listForm").submit();
                });
	            
	            $("#exportBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/punchClock/manage/export");
                    $("#listForm").submit();
                });
	        });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        
        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>考勤信息</h3>
                        </legend>
                    </div>
                    <div class="span3">
                        <label class="control-label">考勤日期 :</label>
                        <input id="optDateShow" name="optDateShow" type="text" class="input-medium" value="${optDateShow }"/>
                    </div>
                    <div class="span9">
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
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                        <button	id="exportBtn" class="btn	btn-warning" type="button">数据导出</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="100" class="center">
                                        日期
                                    </th>
                                    <c:forEach items="${empList}" var="emp">
                                    <th class="center">
                                        ${emp.name}
                                    </th>
                                    </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
                          
                            <c:forEach items="${punchClockList}" var="punchClock">
                            <tr>
                            	<td>${punchClock.clockTime}</td>
                            	<c:forEach items="${punchClock.punchClockList}" var="subPunchClock">
                            	<td>
	                            	<table class="table myTable" style="margin-bottom :0;border :0">
	                            		<tr><td class="center">排班时间</td></tr>
		                            	<tr>
		                            		<c:if test="${subPunchClock.punchNormalState == 99}">
		                            		<td class="center _warn2">
		                            		</c:if>
		                            		<c:if test="${subPunchClock.punchNormalState != 99}">
		                            		<td class="center">
		                            		</c:if>
		                            		
				                            	<c:if test="${empty	subPunchClock.startScheduleTime}">
				                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
				                            	</c:if>
				                            	<c:if test="${!empty subPunchClock.startScheduleTime}">
				                            	${subPunchClock.startScheduleTime} <br>～<br>
				                            	</c:if>
				                            	<c:if test="${empty	subPunchClock.endScheduleTime}">
				                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                            	</c:if>
				                            	<c:if test="${!empty subPunchClock.endScheduleTime}">
				                            	${subPunchClock.endScheduleTime}
				                            	</c:if>
			                            	</td>
		                            	</tr>
		                            	
	                            		<tr><td class="center">考勤时间</td></tr>
		                            	<tr>
		                            		<c:if test="${subPunchClock.punchNormalState != 0 && subPunchClock.punchNormalState != 99}">
		                            		<td class="center _warn1">
		                            		</c:if>
		                            		<c:if test="${subPunchClock.punchNormalState == 0 || subPunchClock.punchNormalState == 99}">
		                            		<td class="center">
		                            		</c:if>
				                            	
				                            	<c:if test="${empty	subPunchClock.startClockTime}">
				                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
				                            	</c:if>
				                            	<c:if test="${!empty subPunchClock.startClockTime}">
				                            	${subPunchClock.startClockTime} <br>～<br>
				                            	</c:if>
				                            	<c:if test="${empty	subPunchClock.endClockTime}">
				                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                            	</c:if>
				                            	<c:if test="${!empty subPunchClock.endClockTime}">
				                            	${subPunchClock.endClockTime}
				                            	</c:if>
			                            	</td>
		                            	</tr>
	                            	</table>
                            	</td>
                            	</c:forEach>
                            </tr>
							</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>