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
    	<style>
    	.label {font-style: normal;}
    	</style>
        <script>
        $(function() {
        	$("#listForm").validate({
                 rules : {
                     optDateShow_start : {
                         required : true,
                         date : true
                     },
                     optDateShow_end : {
                         required : true,
                         date : true
                     }
                 }
     		});
     		$('#optDateShow_start').datepicker({
             	format : 'yyyy-mm-dd'
         	});
        	$('#optDateShow_end').datepicker({
            	format : 'yyyy-mm-dd'
         	});
     		
     		$("#searchBtn").click(function() {
          		$("input[type='text'],textarea").each(function(i) {
               		this.value = $.trim(this.value);
             	});

				$("#listForm").attr('target', '_self');
              	$("#listForm").attr("action", "${sc_ctx}/runInspect/search");
             	$("#listForm").submit();
          	});
          	
          	//-----------------------------------
            // 全选/全部选
        	//-----------------------------------
       		$("#checkAll").click(function() {
             	$('input[name="uuid"]').attr("checked", this.checked);
           	});
          	
          	var $subCheckBox = $("input[name='uuid']");
          	$subCheckBox.click(function() {
            	$("#checkAll").attr("checked", $subCheckBox.length == $("input[name='uuid']:checked").length ? true : false);
        	});
        	
        	//-----------------------------------
         	// 删除按钮点击
        	//-----------------------------------
         	$("#delBtn").click(function() {
             	if ($("#listForm").valid()) {
                	$('#__del_confirm').modal({
                  		backdrop : true,
                     	keyboard : true,
                    	show : true
                 	});
               	}
         	});
         	
         	$(".label").each(function(){
        		$(this).popover({trigger:'hover', html:'true'});
        	});
        });
        //-----------------------------------
    	// 删除
     	//-----------------------------------
    	function _del_confirm() {
         	var $subCheckBox = $("input[name='uuid']");
         	var uuids = "";
         	$.each($subCheckBox, function(index, _checkBox) {
              	if (_checkBox.checked) {
                 	uuids += _checkBox.value + ",";
              	}
         	});
        	if (uuids.length > 0) {
              	uuids = uuids.substring(0, uuids.length - 1);
           	}

          	$("#uuids").val(uuids);
                
          	$("#listForm").attr("target", "_self");
           	$("#listForm").attr("action", "${sc_ctx}/runInspect/del");
          	$("#listForm").submit();
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
                            <h3>门店巡查报告(运营)</h3>
                        </legend>
                    </div>
	                <div class="span5">
	               		<label class="control-label">巡查日期 :</label>
	                	<input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
	                        ～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
	              	</div>
	              	<div class="span7">
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
                        </select>&nbsp;&nbsp;
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12"	style="margin-top: 20px;">
                        <a href="${sc_ctx}/runInspect/new"	class="btn btn-primary">新增</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                    	<input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                	<th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th class="center">
                                        机构
                                    </th>
                                    <th class="center">
                                        评核日期
                                    </th>
                                    <th class="center">
                                        当班负责人
                                    </th>
                                    <th class="center">
                                        评核员
                                    </th>
                                    <th class="center">
                                        文字描述
                                    </th>
                                    <th class="center">
                                        收银台礼仪-得分
                                    </th>
                                    <th class="center">
                                        卖场巡检-得分
                                    </th>
                                    <th	width="117" class="center">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${runInspectList}" var="runInspect">
                                    <tr>
                                    	<td	class="center">
	                                       	<input type="checkbox" name="uuid" value="${runInspect.uuid}">
	                                       	</input>
                                        </td>
                                        <td class="center">
                                        	${runInspect.orgId}
                                        </td>
                                        <td class="center">
                                        	${runInspect.optDate}
                                        </td>
                                        <td class="center">
                                        	${runInspect.dutyPerson}
                                        </td>
                                        <td class="center">
                                        	${runInspect.assessors}
                                        </td>
                                        <td class="center">
	                                        <c:if test="${empty runInspect.comments}" >
	                                        <span class="label" title="" data-content='无' data-original-title="意见或建议(收银台礼仪)"><i class="icon-headphones icon-white"></i></span>
	                                        </c:if>
	                                        <c:if test="${!empty runInspect.comments}" >
	                                        <span class="label label-warning" title="" data-content='${runInspect.comments}' data-original-title="意见或建议(收银台礼仪)"><i class="icon-headphones icon-white"></i></span>
	                                        </c:if>
	                                        
	                                        <c:if test="${empty runInspect.comments2}" >
	                                        <span class="label" title="" data-content='无' data-original-title="意见或建议(卖场巡检)"><i class="icon-headphones icon-white"></i></span>
	                                        </c:if>
	                                        <c:if test="${!empty runInspect.comments2}" >
	                                        <span class="label label-warning" title="" data-content='${runInspect.comments2}' data-original-title="意见或建议(卖场巡检)"><i class="icon-headphones icon-white"></i></span>
	                                        </c:if>
                                        	
                                        	<c:if test="${empty runInspect.feedback}" >
                                        	<span class="label" title="" data-content='无' data-original-title="店铺反馈问题及跟进"><i class="icon-check icon-white"></i></span>
                                        	</c:if>
                                        	<c:if test="${!empty runInspect.feedback}" >
                                        	<span class="label label-warning" title="" data-content='${runInspect.feedback}' data-original-title="店铺反馈问题及跟进"><i class="icon-check icon-white"></i></span>
                                        	</c:if>
                                        	
                                        	<c:if test="${empty runInspect.goodsIssue}" >
                                        	<span class="label" title="" data-content='无' data-original-title="货品问题的发现及跟进"><i class="icon-gift icon-white"></i></span>
                                        	</c:if>
                                        	<c:if test="${!empty runInspect.goodsIssue}" >
                                        	<span class="label label-warning" title="" data-content='${runInspect.goodsIssue}' data-original-title="货品问题的发现及跟进"><i class="icon-gift icon-white"></i></span>
                                        	</c:if>
                                        	
                                        	<c:if test="${empty runInspect.penaltyCase}" >
                                        	<span class="label" title="" data-content='无' data-original-title="现场违纪违规及处罚情况"><i class="icon-eye-open icon-white"></i></span>
                                        	</c:if>
                                        	<c:if test="${!empty runInspect.penaltyCase}" >
                                        	<span class="label label-warning" title="" data-content='${runInspect.penaltyCase}' data-original-title="现场违纪违规及处罚情况"><i class="icon-eye-open icon-white"></i></span>
                                        	</c:if>
                                        	
                                        	<c:if test="${empty runInspect.trainingStatistics}" >
                                        	<span class="label" title="" data-content='无' data-original-title="培训统计"><i class="icon-leaf icon-white"></i></span>
                                        	</c:if>
                                        	<c:if test="${!empty runInspect.trainingStatistics}" >
                                        	<span class="label label-warning" title="" data-content='${runInspect.trainingStatistics}' data-original-title="培训统计"><i class="icon-leaf icon-white"></i></span>
                                        	</c:if>
                                        	
                                        	<c:if test="${empty runInspect.inventoryStatistics}" >
                                        	<span class="label" title="" data-content='无' data-original-title="库存统计"><i class="icon-hdd icon-white"></i></span>
                                        	</c:if>
                                        	<c:if test="${!empty runInspect.inventoryStatistics}" >
                                        	<span class="label label-warning" title="" data-content='${runInspect.inventoryStatistics}' data-original-title="库存统计"><i class="icon-hdd icon-white"></i></span>
                                        	</c:if>
                                        </td>
                                        <td class="center">
                                        	${runInspect.score1}
                                        </td>
                                        <td class="center">
                                        	${runInspect.score2}
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/runInspect/edit/${runInspect.uuid}" target="_self" class="btn btn-warning"/>详细</a>
                                            <a href="${sc_ctx}/runInspect/view/${runInspect.uuid}" target="_blank" class="btn"/>查看</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                            </tbody>
                            <c:if test="${empty	runInspectList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="9" class="rounded-foot-left">
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