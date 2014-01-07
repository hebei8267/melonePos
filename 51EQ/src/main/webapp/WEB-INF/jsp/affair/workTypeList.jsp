<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    	<style type="text/css">
    	._warn0 {
			padding: 5px;
		}
    	._warn1 {
			background-color: #99FF33;
		}
		._warn2 {
			background-color: #FFCC33;
		}
    	</style>
    	<script>
	    	jQuery.validator.addMethod("_myCompareDate", function(value, element, param) {
	    		
	    		var check = $("input[name='useFlg" + param + "']:checked").val();
	    		if(1 == check){
	    			var startDateHr = $("#startDateHr"+param).val();
	          		var startDateMinute = $("#startDateMinute"+param).val();

					var endDateHr = $("#endDateHr"+param).val();
	          		var endDateMinute = $("#endDateMinute"+param).val();	
	          		
	          		return (startDateHr+startDateMinute) <= (endDateHr+endDateMinute);
	    		}
	    	   
	    	    return true;
	    	}, '结束时间必须大于开始时间');
	    	
            $().ready(function() {
            	<c:forEach items="${workTypeList}" var="workType" varStatus="status">
            	checkFun('${status.index + 1 }','${workType.useFlg}');
            	</c:forEach>
                
              	//-----------------------------------
                // 表单效验
                //-----------------------------------
               	$("#listForm").validate({
                    rules : {
                        name4 : {
                            required : true,
                            maxlength : 32
                        },
                        name5 : {
                            required : true,
                            maxlength : 32
                        },
                        name6 : {
                            required : true,
                            maxlength : 32
                        },
                        name7 : {
                            required : true,
                            maxlength : 32
                        },
                        name8 : {
                            required : true,
                            maxlength : 32
                        },
                        
                        startDateHr1 : {
                        	required : true
                        },
                        startDateHr2 : {
                        	required : true
                        },
                        startDateHr3 : {
                        	required : true
                        },
                        startDateHr4 : {
                        	required : true
                        },
                        startDateHr5 : {
                        	required : true
                        },
                        startDateHr6 : {
                        	required : true
                        },
                        startDateHr7 : {
                        	required : true
                        },
                        startDateHr8 : {
                        	required : true
                        },
                        
                        startDateMinute1 : {
                        	required : true
                        },
                        startDateMinute2 : {
                        	required : true
                        },
                        startDateMinute3 : {
                        	required : true
                        },
                        startDateMinute4 : {
                        	required : true
                        },
                        startDateMinute5 : {
                        	required : true
                        },
                        startDateMinute6 : {
                        	required : true
                        },
                        startDateMinute7 : {
                        	required : true
                        },
                        startDateMinute8 : {
                        	required : true
                        },
                        
                        endDateHr1 : {
                        	required : true
                        },
                        endDateHr2 : {
                        	required : true
                        },
                        endDateHr3 : {
                        	required : true
                        },
                        endDateHr4 : {
                        	required : true
                        },
                        endDateHr5 : {
                        	required : true
                        },
                        endDateHr6 : {
                        	required : true
                        },
                        endDateHr7 : {
                        	required : true
                        },
                        endDateHr8 : {
                        	required : true
                        },
                        
                        endDateMinute1 : {
                        	required : true,
                        	_myCompareDate : 1
                        },
                        endDateMinute2 : {
                        	required : true,
                        	_myCompareDate : 2
                        },
                        endDateMinute3 : {
                        	required : true,
                        	_myCompareDate : 3
                        },
                        endDateMinute4 : {
                        	required : true,
                        	_myCompareDate : 4
                        },
                        endDateMinute5 : {
                        	required : true,
                        	_myCompareDate : 5
                        },
                        endDateMinute6 : {
                        	required : true,
                        	_myCompareDate : 6
                        },
                        endDateMinute7 : {
                        	required : true,
                        	_myCompareDate : 7
                        },
                        endDateMinute8 : {
                        	required : true,
                        	_myCompareDate : 8
                        }
                    }
                });
                
                $("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });
                    $("#listForm").attr("action", "${sc_ctx}/workType/save");
                    $("#listForm").submit();
                });
            });
            
          	function calStartDate(index){
          		var startDateHr = $("#startDateHr"+index).val();
          		var startDateMinute = $("#startDateMinute"+index).val();
          		
          		$("#startDate"+index).val(startDateHr + startDateMinute);
          	}
          	function calEndDate(index){
          		var endDateHr = $("#endDateHr"+index).val();
          		var endDateMinute = $("#endDateMinute"+index).val();
          		
          		$("#endDate"+index).val(endDateHr + endDateMinute);
          	}
          	function checkFun(index,value){
          		if(value=='0'){// 停用
          			$("#_ss"+index+'1').removeClass("_warn1");
          			$("#_ss"+index+'0').addClass("_warn2");

          			if(1 != index && 2 != index && 3 != index){
          				$("#name"+index).attr("disabled", true);
          			} 
          			$("#startDateHr"+index).attr("disabled", true); 
              		$("#startDateMinute"+index).attr("disabled", true);
              		$("#endDateHr"+index).attr("disabled", true);
              		$("#endDateMinute"+index).attr("disabled", true);
          		} else {// 启用
          			$("#_ss"+index+'1').addClass("_warn1");
          			$("#_ss"+index+'0').removeClass("_warn2");
          			
          			if(1 != index && 2 != index && 3 != index){
          				$("#name"+index).attr("disabled", false);
          			} 
          			$("#startDateHr"+index).attr("disabled", false); 
              		$("#startDateMinute"+index).attr("disabled", false);
              		$("#endDateHr"+index).attr("disabled", false);
              		$("#endDateMinute"+index).attr("disabled", false);
          		}
          	}
        </script>
	</head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <form method="post" class="form-inline" id="listForm">
            	<div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 工作时间 维护</h3>
                        </legend>
                    </div>

                    <div class="span8" style="padding-top: 10px; text-align: right;">
                        <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                        &nbsp;<a href="${sc_ctx}/workType/list" class="btn btn-large">重置</a>
                    </div>
                </div>
                
                <div class="row">
                    <div class="span8" style="padding-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="55" class="center">
                                        序号
                                    </th>
                                    <th class="center">
                                        人员姓名
                                    </th>
                                    <th class="center">
                                        工作时间 (开始时间 ～ 结束时间)
                                    </th>
                                    <th	width="205">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${workTypeList}" var="workType" varStatus="status">
                                    <tr>
                                        <td class="center">
                                        	<input type="hidden" name="uuid" value="${workType.uuid}">
                                            ${status.index + 1}
                                        </td>
                                        
                                        <c:if test="${workType.editFlg}">
                                        <td class="center">
                                            <input type="text" class="input-small" id="name${status.index + 1}" name="name${status.index + 1}" value="${workType.name}"/>
                                        </td>
                                        </c:if>
                                        
                                        <c:if test="${!workType.editFlg}">
                                        <td>
                                        	<input type="hidden" name="name${status.index + 1}" value="${workType.name}">
                                            <span>&nbsp;${workType.name}</span>
                                        </td>
                                        </c:if>
                                        
                                        <td class="center">
	                                        <select id="startDateHr${status.index + 1}" name="startDateHr${status.index + 1}" class="input-small" onchange="calStartDate('${status.index + 1}')">
	                            			<c:forEach items="${hrList}" var="hr">
	                            				<c:if test="${workType.startDateHr.equals(hr.key)}">
	                            				<option value="${hr.key }" selected>${hr.value }</option>
	                            				</c:if>
	                            				<c:if test="${!workType.startDateHr.equals(hr.key)}">
	                                    		<option value="${hr.key }">${hr.value }</option>
	                                    		</c:if>
	                            			</c:forEach>
	                        				</select>
	                        				<select id="startDateMinute${status.index + 1}" name="startDateMinute${status.index + 1}" class="input-small" onchange="calStartDate('${status.index + 1}')">
	                            			<c:forEach items="${minuteList}" var="minute">
	                            				<c:if test="${workType.startDateMinute.equals(minute.key)}">
	                            				<option value="${minute.key }" selected>${minute.value }</option>
	                            				</c:if>
	                            				<c:if test="${!workType.startDateMinute.equals(minute.key)}">
	                            				<option value="${minute.key }">${minute.value }</option>
	                            				</c:if>
	                            			</c:forEach>
	                        				</select>
	                        				<input type="hidden" id="startDate${status.index + 1}" name="startDate${status.index + 1}" value="${workType.startDate}">
	                        				～
	                        				
	                        				<select id="endDateHr${status.index + 1}" name="endDateHr${status.index + 1}" class="input-small" onchange="calEndDate('${status.index + 1}')">
	                            			<c:forEach items="${hrList}" var="hr">
	                                    		<c:if test="${workType.endDateHr.equals(hr.key)}">
	                            				<option value="${hr.key }" selected>${hr.value }</option>
	                            				</c:if>
	                            				<c:if test="${!workType.endDateHr.equals(hr.key)}">
	                            				<option value="${hr.key }">${hr.value }</option>
	                            				</c:if>
	                            			</c:forEach>
	                        				</select>
	                        				<select id="endDateMinute${status.index + 1}" name="endDateMinute${status.index + 1}" class="input-small" onchange="calEndDate('${status.index + 1}')">
	                            			<c:forEach items="${minuteList}" var="minute">
	                            				<c:if test="${workType.endDateMinute.equals(minute.key)}">
	                            				<option value="${minute.key }" selected>${minute.value }</option>
	                            				</c:if>
	                            				<c:if test="${!workType.endDateMinute.equals(minute.key)}">
	                            				<option value="${minute.key }">${minute.value }</option>
	                            				</c:if>
	                            			</c:forEach>
	                        				</select>
	                        				<input type="hidden" id="endDate${status.index + 1}" name="endDate${status.index + 1}" value="${workType.endDate}">
                                        </td>
                                        
                                        <td class="center">
                                        	<c:if test="${workType.useFlg.equals('0')}">
                                        	<input type="radio" name="useFlg${status.index + 1}" value="1" onchange="checkFun('${status.index + 1 }','1')"> <span id="_ss${status.index + 1 }1" class="_warn0">启用</span>&nbsp;&nbsp;
                                        	<input type="radio" name="useFlg${status.index + 1}" value="0" checked onchange="checkFun('${status.index + 1 }','0')"> <span id="_ss${status.index + 1 }0" class="_warn0 _warn2">停用</span>
                                        	</c:if>
											
											<c:if test="${workType.useFlg.equals('1')}">
                                        	<input type="radio" name="useFlg${status.index + 1}" value="1" checked onchange="checkFun('${status.index + 1 }','1')"> <span id="_ss${status.index + 1 }1" class="_warn0 _warn1">启用</span>&nbsp;&nbsp;
                                        	<input type="radio" name="useFlg${status.index + 1}" value="0" onchange="checkFun('${status.index + 1 }','0')"> <span id="_ss${status.index + 1 }0" class="_warn0">停用</span>
                                        	</c:if>
                                        </td>
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