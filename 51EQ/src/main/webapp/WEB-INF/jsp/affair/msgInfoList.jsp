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
    	._warn1 {
    		padding: 5px;
			background-color: #99FF33;
		}
		._warn2 {
			padding: 5px;
			background-color: #FFCC33;
		}
    	</style>
    	<script>
            $().ready(function() {
            	$('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
                });
                
              	//-----------------------------------
                // 表单效验
                //-----------------------------------
                $("#listForm").validate({
                    rules : {
                        delBtn : {
                            requiredSelect : 'uuid'
                        }
                    }
                });
                $("#searchForm").validate({
                    rules : {
                    	optDateShow_start : {
                            required : true
                        },
                        optDateShow_end : {
                            required : true
                        }
                    }
                });
                //-----------------------------------
                // 全选/全部选
                //-----------------------------------
                $("#checkAll").click(function() {
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='uuid']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
							$(this).prop("checked", true);
						} else {
							$(this).attr("checked", false);
							$(this).prop("checked", false);
						}
                	}); 
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
                
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#searchForm").attr("target", "_self");
                    $("#searchForm").attr("action", "${sc_ctx}/msgInfo/search");
                    $("#searchForm").submit();
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
                $("#listForm").attr("action", "${sc_ctx}/msgInfo/del");
                $("#listForm").submit();
            }
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
      	<div class="container">
            <div class="row">
                <form method="post"	class="form-inline"	id="searchForm">
                	<div class="span12">
                        <legend>
                            <h3>公告/消息</h3>
                        </legend>
                    </div>
                	<div class="span5">
                        <label class="control-label">收发日期 :</label>
                        <input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                         ～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
                    </div>
                    <div class="span7">
                        <label class="control-label">信息类型 :</label>
                        <select name="msgType" class="input-medium">
                            <c:forEach items="${msgTypeList}" var="_msgType">
                                <c:if test="${_msgType.key == msgType}">
                                    <option value="${_msgType.key }" selected>${_msgType.value }</option>
                                </c:if>
                                <c:if test="${_msgType.key != msgType}">
                                    <option value="${_msgType.key }">${_msgType.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>&nbsp;&nbsp;
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                </form>
                
                <form method="post" class="form-inline" id="listForm">
                	<div class="span12" style="padding-top: 10px;">
                        <a href="${sc_ctx}/msgInfo/new"	class="btn btn-primary">新增</a>
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
                                    <th width="90" class="center">
                                        日期
                                    </th>
                                    <th width="50" class="center">
                                        星期
                                    </th>
                                    <th width="50" class="center">
                                        类型
                                    </th>
                                    <th width="350">
                                        发信人 / 收信人
                                    </th>
                                    <th>
                                        主题
                                    </th>
                                    <th	width="75">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${msgInfoList}" var="msgInfo">
                            		<tr>
                            			<td class="center">
                            				<input type="checkbox" name="uuid" value="${msgInfo.uuid}">
                            			</td>
                                        <td>
                                            ${msgInfo.optDateShow}
                                        </td>
                                        <td class="center">
                                        	周${msgInfo.week}
                                        </td>
                                        <td class="center">
                                        	<c:if test="${msgInfo.msgType.equals('1')}">
                                                <span class='_warn1'>发信</span>
                                            </c:if>
                                            <c:if test="${msgInfo.msgType.equals('2')}">
                                                <span class='_warn2'>收信</span>
                                            </c:if>
                                        </td>
                                        <td>
                                        	<c:if test="${msgInfo.msgType.equals('1')}">
                                                <span class='_warn1'>${msgInfo.sendNameSet}</span> / ${msgInfo.acceptNameSet}
                                            </c:if>
                                            <c:if test="${msgInfo.msgType.equals('2')}">
                                                ${msgInfo.sendNameSet} / <span class='_warn2'>${msgInfo.acceptNameSet}</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            ${msgInfo.msgSubject}
                                        </td>
                                        <td>
                                        	<c:if test="${msgInfo.readFlg.equals('0')}">
                                                <a href="${sc_ctx}/msgInfo/view/${msgInfo.uuid}" class="btn btn-warning" target="_blank"/><i class="icon-ok icon-white"></i> 查看</a>
                                            </c:if>
                                            <c:if test="${msgInfo.readFlg.equals('1')}">
                                                <a href="${sc_ctx}/msgInfo/view/${msgInfo.uuid}" class="btn btn-warning" target="_blank"/><i class="icon-remove icon-white"></i> 查看</a>
                                            </c:if>
                                        </td>
                                    </tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	msgInfoList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="7" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
               		</div>
                </form>
            </div>
       	</div>
    </body>
</html>