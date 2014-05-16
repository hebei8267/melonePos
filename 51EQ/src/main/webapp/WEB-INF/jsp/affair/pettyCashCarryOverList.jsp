<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
		._warn1 {
			color: #006dcc;
			padding: 5px;
			font-weight:bold;
		}
		._warn2 {
			color: #f89406;
			padding: 5px;
			font-weight:bold;
		}
		._warn3 {
			background-color: #FFDEAD;
			padding: 5px;
		}
		.control-label_1 {
			width: 240px;
		}
		</style>
		<script>
        $().ready(function() {
        	$("#searchForm").validate({
				rules : {
					orgId : {
						required : true
					}
				}
			});
        	$("#searchBtn").click(function() {
				$("input[type='text'],textarea").each(function(i) {
					this.value = $.trim(this.value);
				});
	
				$("#searchForm").attr('target', '_self');
				$("#searchForm").attr("action", "${sc_ctx}/pettyCash/carryOverSearch");
				$("#searchForm").submit();
			});
        	
            $("#listForm").validate({
                rules : {
                	overBtn : {
                		requiredOneSelect : 'uuid'
                    },
                    inspectTrsId : {
                    	required : true,
                        maxlength : 16
                    }
                }
            });
            
            // 结转按钮点击
            $("#overBtn").click(function() {
                if ($("#listForm").valid()) {
                    $('#__pettyCashCarryOver_confirm').modal({
                        backdrop : true,
                        keyboard : true,
                        show : true
                    });
                }
            });
         	// 备用金结转
			$("#carryOverBtn").click(function() {
            	$("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });
            	
            	$("#listForm").attr('target', '_self');
                $("#listForm").attr("action", "${sc_ctx}/pettyCash/carryOverConfirm");
                $("#listForm").submit();
            });
         	
			$("#inspectTrsId").blur(function() {
				if($("#inspectTrsId").val() == ""){
					return;
				}
				$("#inspectTrsId").val(addZero($("#inspectTrsId").val(), 8));
				
				var _val = $("#inspectTrsId").val();
				var _start = _val.length - 8;
                var _end = _val.length;
                                  	
               	$("#inspectTrsId").val(_val.substring(_start, _end));
			});
        });
        
        function addZero(num, n) {
            if ((num + "").length >= n) return num;
            return addZero("0" + num, n);
        }
    </script>
	</head>
	<body>
		<% // 系统菜单 %>
		<page:applyDecorator name="menu" />

		<div class="container">
            <div class="row">
                <div class="span12">
                    <legend>
                        <h3>门店备用金(结转)</h3>
                    </legend>
                </div>
                <div class="span12">
                    <form method="post" class="form-inline" id="searchForm">
                        <label class="control-label">机构 :</label>
                        <select id="orgId" name="orgId" class="input-medium">
                            <c:forEach items="${orgList}" var="org">
                                <c:if test="${org.key == orgId}">
                                    <option value="${org.key }" selected>${org.value }</option>
                                </c:if>
                                <c:if test="${org.key != orgId}">
                                    <option value="${org.key }">${org.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>&nbsp;&nbsp;
                        <button id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </form>
                </div>

                <form method="post"	class="form-horizontal"	id="listForm">
                    <div class="span12">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="55" class="center">
                                        结转
                                    </th>
                                    <th width="115">
                                        业务编号(UID)
                                    </th>
                                    <th width="35" class="center">
                                        星期
                                    </th>
                                    <th width="90" class="center">
                                        业务日期
                                    </th>
                                    <th width="80" class="center">
                                        填写时间
                                    </th>
                                    <th width="150" class="right">
                                        支出/拨入(金额)
                                    </th>
                                    <th>
                                        支出/拨入(事项)
                                    </th>
                                    <th width="110" class="right">
                                        备用金余额
                                    </th>
                                    <th width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pettyCashList}" var="pettyCash">
                                    <tr>
                                        <td class="center">
                                            <input type="radio" name="uuid" id="uuids" value="${pettyCash.uuid}"></input>
                                        </td>
                                        <td>
                                            ${pettyCash.optUid}
                                        </td>
                                        <td class="center">
                                            ${pettyCash.week}
                                        </td>
                                        <td>
                                            <c:if test="${!pettyCash.optDateShow.equals(pettyCash.createDateStr)}">
                                                <span class="_warn3">
                                            </c:if>
                                            ${pettyCash.optDateShow}
                                            <c:if test="${!pettyCash.optDateShow.equals(pettyCash.createDateStr)}">
                                                </span>
                                            </c:if>
                                        </td>
                                        <td>
                                            ${pettyCash.createDateStr}
                                        </td>
                                        <td class="right">
                                            <% //操作类型 0-支出 1-拨入 %>
                                            <c:if test="${pettyCash.optType == 0}">
                                                <span class="_warn1">
                                            </c:if>

                                            <c:if test="${pettyCash.optType == 1}">
                                                <span class="_warn2">
                                            </c:if>

                                            ${pettyCash.optAmt}
                                            </span>
                                        </td>
                                        <td>
                                            <% //操作类型 0-支出 1-拨入 %>
                                            <c:if test="${pettyCash.optType == 0}">
                                                ${pettyCash.expReason}
                                            </c:if>

                                            <c:if test="${pettyCash.optType == 1}">
                                                <c:if test="${pettyCash.incomeSource.equals('1')}">
                                                    正常拨入
                                                </c:if>
                                                <c:if test="${pettyCash.incomeSource.equals('2')}">
                                                    非常拨入
                                                </c:if>
                                                <c:if test="${pettyCash.incomeSource.equals('4')}">
                                                    会计调帐
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <td class="right">
                                            ${pettyCash.balanceAmt}
                                        </td>
                                        <td><a href="${sc_ctx}/pettyCash/view/${pettyCash.uuid}" target="_blank" class="btn" />查看</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	pettyCashList}">
                                <tfoot>
                                    <tr>
                                        <td colspan="8" class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>

                    <div class="span12">
                    	<input type="hidden" name="orgId" id="orgId" value="${orgId}"/>
                        <label class="control-label_1">巡查报告流水号 :</label>&nbsp;
                        <input type="text" id="inspectTrsId" name="inspectTrsId" class="input-medium">&nbsp;&nbsp;
                        <input id="overBtn" name="overBtn" type="button" class="btn btn-danger" value="结转"/>
                    </div>
                </form>
            </div>
        </div>
        
        <div class="modal hide fade  __model37" id="__pettyCashCarryOver_confirm">
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4>系统消息</h4>
            </div>
            <div class="modal-body">
                <center>
                    <p class="error">
                    确定要结转选择的备用金信息吗？
                    </p>
                </center>
            </div>
            <div class="modal-footer">
                <a href="#" id="carryOverBtn" class="btn btn-primary">确定</a>
                <a href="#" class="btn" data-dismiss="modal">关闭</a>
            </div>
        </div>
	</body>
</html>