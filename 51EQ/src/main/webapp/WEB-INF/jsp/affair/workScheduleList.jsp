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
    	<script>
    		function wtChangeTxt(index){
    			var selVal = $("#_wt"+index).val();

    			if(selVal != ""){
    				var cssNo = selVal % 8 ;
    				
    				if(cssNo == 1) {
    					$("#_wtTxt"+index).removeClass("_warn2");
    					$("#_wtTxt"+index).removeClass("_warn3");
    					$("#_wtTxt"+index).removeClass("_warn4");
    					
              			$("#_wtTxt"+index).addClass("_warn1");
    				} else if(cssNo == 2) {
    					$("#_wtTxt"+index).removeClass("_warn1");
    					$("#_wtTxt"+index).removeClass("_warn3");
    					$("#_wtTxt"+index).removeClass("_warn4");
    					
              			$("#_wtTxt"+index).addClass("_warn2");
    				} else if(cssNo == 3) {
    					$("#_wtTxt"+index).removeClass("_warn1");
    					$("#_wtTxt"+index).removeClass("_warn2");
    					$("#_wtTxt"+index).removeClass("_warn3");
    					
              			$("#_wtTxt"+index).addClass("_warn3");
    				} else {
    					$("#_wtTxt"+index).removeClass("_warn1");
    					$("#_wtTxt"+index).removeClass("_warn2");
    					$("#_wtTxt"+index).removeClass("_warn3");
    					
              			$("#_wtTxt"+index).addClass("_warn4");
    				}
    				
    			} else {
    				$("#_wtTxt"+index).removeClass("_warn1");
					$("#_wtTxt"+index).removeClass("_warn2");
					$("#_wtTxt"+index).removeClass("_warn3");
          			$("#_wtTxt"+index).removeClass("_warn4");
    			}
    			$("#_wtTxt"+index).html(_get_wt_data(selVal));
    		}
    		
    		var _wt_data_set = ${_wt_data_set};
    		
    		function _get_wt_data(key){
    			for(var p in _wt_data_set){
        		    if(key == p){
        		    	return _wt_data_set[p];
        		    }
        		}
    			return "";
    		}
    		
    		$().ready(function() {
    			
    			$("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });
                    
                    $("#listForm").attr('target', '_self');
                    $("#listForm").attr("action", "${sc_ctx}/workSchedule/save");
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
            	<input type="hidden" name="_wt_data_set" value='${_wt_data_set}'>
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 排班表 维护</h3>
                        </legend>
                    </div>
                 	
                 	<div class="span3" style="padding-top: 10px;">
                    	<span class="_warn1 center">早班</span>
						<span class="_warn2 center">晚班</span>
                 		<span class="_warn3 center">全天班</span>
                 		<span class="_warn4 center">其它</span>
                 	</div>
                 	
                 	<div class="span9" style="text-align: right;">
                        <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                        &nbsp;<a href="${sc_ctx}/workSchedule/list" class="btn btn-large">重置</a>
                    </div>
                </div>
                <div class="row">
                    <div class="span12"	style="margin-top: 10px;">
                    	<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="100" class="center">
                                        日期
                                    </th>
                                    <th width="50" class="center">
                                        星期
                                    </th>
                                    <c:forEach items="${empList}" var="emp">
                                    <th class="center">
                                        ${emp.name}
                                    </th>
                                    </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
	                            <c:forEach items="${wsList}" var="ws" varStatus="status1">
	                            <tr>	                            	
	                            	<td class="center">${ws.scheduleDate}</td>
	                            	<td class="center">${ws.week}</td>
	                            	<c:forEach items="${ws.scheduleList}" var="subSchedule" varStatus="status2">
                            		<td class="center">
                            			<c:if test="${ws.editFlg}">
                            			<input type="hidden" name="scheduleDate" value="${ws.scheduleDate}">
                            			<input type="hidden" name="empCode" value="${subSchedule.empCode}">
                            			<table class="table myTable" style="margin-bottom :0;border :0">
                            				<tr>
                            					<td class="center">
			                            			<select name="scheduleTimeSelect" class="input-small2" id="_wt${status1.index + 1}${status2.index + 1}" onchange="wtChangeTxt(${status1.index + 1}${status2.index + 1})">
							                        	<c:forEach items="${wtList}" var="wt">
							                        		<c:if test="${wt.key == subSchedule.workTypeUuid}">
							                        			<option value="${wt.key }" selected>${wt.value }</option>
							                                </c:if>
							                                <c:if test="${wt.key != subSchedule.workTypeUuid}">
							                                    <option value="${wt.key }">${wt.value }</option>
							                                </c:if>
							                            </c:forEach>
							                        </select>
                            					</td>
                            				</tr>
                            				<tr>
                            					<c:if test="${empty subSchedule.workTypeUuid }">
                            					<td class="center" id="_wtTxt${status1.index + 1}${status2.index + 1}">&nbsp;</td>
                            					</c:if>
                            					
                            					<c:if test="${!empty subSchedule.workTypeUuid }">
                            					
	                            					<c:if test="${(subSchedule.workTypeUuid % 8) == 1 }">
	                            					<td class="center _warn1" id="_wtTxt${status1.index + 1}${status2.index + 1}">${subSchedule.workTime}</td>
	                            					</c:if>
	                            					<c:if test="${(subSchedule.workTypeUuid % 8) == 2 }">
	                            					<td class="center _warn2" id="_wtTxt${status1.index + 1}${status2.index + 1}">${subSchedule.workTime}</td>
	                            					</c:if>
	                            					<c:if test="${(subSchedule.workTypeUuid % 8) == 3 }">
	                            					<td class="center _warn3" id="_wtTxt${status1.index + 1}${status2.index + 1}">${subSchedule.workTime}</td>
	                            					</c:if>
	                            					<c:if test="${((subSchedule.workTypeUuid % 8) != 1) && ((subSchedule.workTypeUuid % 8) != 2) && ((subSchedule.workTypeUuid % 8) != 3) }">
	                            					<td class="center _warn4" id="_wtTxt${status1.index + 1}${status2.index + 1}">${subSchedule.workTime}</td>
	                            					</c:if>
                            					
                            					</c:if>
                            				</tr>
                            			</table>
                            			</c:if>
                            			
                            			<c:if test="${!ws.editFlg}">
                            			<table class="table myTable" style="margin-bottom :0;border :0">
                            				<tr>
                            					<td class="center">${subSchedule.workTypeName}</td>
                            				</tr>
                            				<tr>
                            					
			                            		<c:if test="${empty subSchedule.workTypeUuid }">
                            					<td class="center">&nbsp;</td>
                            					</c:if>
                            					
                            					<c:if test="${!empty subSchedule.workTypeUuid }">
	                            					<td class="center">${subSchedule.workTime}</td>
                            					</c:if>
                            				</tr>
                            				
                            			</table>
                            			</c:if>
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