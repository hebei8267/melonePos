<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="em_ctx">
    ${ctx}/em
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<script type="text/javascript">
    	$().ready(function() {
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
            $("#listForm").attr("action", "${sc_ctx}/accountFlow/del");
            $("#listForm").submit();
        }
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
                            <h3>会计记账</h3>
                        </legend>
                    </div>
                    <div class="span9">
                    	<a href="${sc_ctx}/accountFlow/new"	class="btn btn-primary">新增</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
              	</div>
            
            	<div class="row">
                    <div class="span12"	style="margin-top: 10px;">
                    	<input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                        	<thead>
                                <tr>
                                	<th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th class="center">
                                        余额
                                    </th>
                                    <th class="center">
                                        日期
                                    </th>
                                    <th class="center">
                                        拨入来源
                                    </th>
                                    <th class="center">
                                        拨入金额
                                    </th>
                                    <th class="center">
                                        支出金额
                                    </th>
                                    <th class="center">
                                        支出大类
                                    </th>
                                    <th class="center">
                                        支出细类
                                    </th>
                                    <th class="center" width="200">
                                        备注
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            	<c:forEach items="${accountFlowList}" var="accountFlow" varStatus="status">
                            	<tr>
                            		<td	class="center">
                            			<c:if test="${!accountFlow.lockFlg}">
                                		<input type="checkbox" name="uuid" value="${accountFlow.uuid}"></input>
                                		</c:if>
                                    </td>
                            		<td class="center">${accountFlow.balanceAmt}</td>
                            		<td class="center">
										<fmt:parseDate value="${accountFlow.optDate}" var="_optDate" pattern="yyyyMMdd" />
                            			<fmt:formatDate pattern="yyyy-MM-dd" value="${_optDate}" />
									</td>
                            		<td class="center">${accountFlow.inAmtDesc}</td>
                            		<td class="center">${accountFlow.inAmt}</td>
                            		<td class="center">${accountFlow.outAmt}</td>
                            		<td class="center">${accountFlow.outAmtLClass}</td>
                            		<td class="center">${accountFlow.outAmtSClass}</td>
                            		<td>${accountFlow.descTxt}</td>
                            		<td>
                            			<c:if test="${!accountFlow.lockFlg}">
                                    	<a href="${sc_ctx}/accountFlow/edit/${accountFlow.uuid}" class="btn btn-warning"/>修改</a>
                                    	<a href="${sc_ctx}/accountFlow/split/${accountFlow.uuid}" class="btn btn-info" style="margin-top: 5px"/>会计记账</a>
                                    	</c:if>
                                    	
                                    	<c:if test="${accountFlow.lockFlg}">
                                    	<a href="${sc_ctx}/accountFlow/split/${accountFlow.uuid}" class="btn" style="margin-top: 5px"/>会计记账</a>
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