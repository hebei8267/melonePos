<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
<html>
	<head>
		<style>
		.my-input-medium {
  			width: 170px;
		}
		</style>
		<script>
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
				
				//--------------------------------------------------------------------
				// 查询按钮点击
				//--------------------------------------------------------------------
				$("#searchBtn").click(function() {
					$("input[type='text']").each(function(i){
  						this.value = $.trim(this.value);
 					});
					$("#searchForm").attr("action", "${sc_ctx}/employee2/search");
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
                $("#listForm").attr("action", "${sc_ctx}/employee2/del");
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
	                        <h3>员工管理</h3>
	                    </legend>
	                </div>
	                <div class="span2">
	                    <label class="control-label">机构 :</label>
						<select id="orgId" name="orgId" class="my-input-medium">
							<c:forEach items="${orgList}" var="org">
								<c:if test="${org.key == orgId}">
									<option value="${org.key }" selected>${org.value }</option>
								</c:if>
								<c:if test="${org.key != orgId}">
									<option value="${org.key }">${org.value }</option>
								</c:if>
							</c:forEach>
						</select>
	                </div>
	                <div class="span2">
	                    <label class="control-label">员工姓名 : </label>
	                    <input id="name" name="name" type="text" class="input-medium" value="${name}"/>
	                </div>
	                <div class="span2">
	                    <label class="control-label">联系电话 : </label>
	                    <input id="phone" name="phone" type="text" class="input-medium" value="${phone}"/>
	                </div>
	                
	                <div class="span2">
	                    <label class="control-label">身份证号 : </label>
	                    <input id="idCardNo" name="idCardNo" type="text" class="input-medium" value="${idCardNo}"/>
	                </div>
	                
	                <div class="span2">
	                    <label class="control-label">状态 : </label>
	                    <select id="delFlg" name="delFlg" class="my-input-medium">
              				<c:if test="${delFlg == 0}">
								<option value="0" selected>在职</option>
              					<option value="1" >离职</option>
							</c:if>
							<c:if test="${delFlg == 1}">
								<option value="0">在职</option>
              					<option value="1" selected>离职</option>
							</c:if>
   						</select>
	                </div>
	                <div class="span2">
	                	<button	id="searchBtn" class="btn btn-primary" type="button" style="margin-top:25px">查询</button>
	                </div>
	      		</form>
	      		
	      		<form method="post"	class="form-inline"	id="listForm">
	                <div class="span12" style="margin-top: 15px;">
	                    <a href="${sc_ctx}/employee2/new"	class="btn btn-primary">新增</a>
	                    <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
	                </div>
                    <div class="span12"	style="margin-top: 10px;">
                    	<input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                        	<thead>
                                <tr>
                                	<th class="center">
                                        序号
                                    </th>
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th class="center">
                                        所属<br/>机构
                                    </th>
                                    <th class="center">
                                        考勤<br/>编号
                                    </th>
                                    <th class="center">
                                        员工姓名
                                    </th>
                                    <th class="center">
                                        联系电话
                                    </th>
                                    <th class="center">
                                        常住地址
                                    </th>
                                    <th class="center">
                                        入职时间
                                    </th>
                                    <th class="center">
                                        职务
                                    </th>
                                    <th class="center">
                                        聘用<br/>形式
                                    </th>
                                    <th	width="177">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${employeeList}" var="employee" varStatus="status">
                            	<tr>
                            		<td	class="center">
                                        ${status.index+1}
                                    </td>
                                    <td	class="center">
                                        <input type="checkbox" name="uuid" value="${employee.uuid}" />
                                    </td>
                                    <td class="center">
                                    	<c:if test="${fn:length(employee.orgId) > 4}">
			             					${fn:substring(employee.orgId,3,6)}
			             				</c:if>
			             				<c:if test="${fn:length(employee.orgId) < 4}">
			             					总部
			             				</c:if>
                                    </td>
                                    <td class="center">
                                        ${employee.employeeNo}
                                    </td>
                                    <td class="center">
                                        ${employee.name}
                                    </td>
                                    <td class="center">
                                        ${employee.phone}
                                    </td>
                                    <td class="center">
                                        ${employee.address}
                                    </td>
                                    <td class="center">
                                       	${employee.entryTime}
                                    </td>
                                    <td class="center">
                                        ${employee.pos}
                                    </td>
                                    <td class="center">
                                    	<c:if test="${1 == employee.employForm}">正式</c:if>
                						<c:if test="${2 == employee.employForm}">兼职</c:if>
                                    </td>
                                    <td>
                                   		<a href="${sc_ctx}/employee2/edit/${employee.uuid}" class="btn btn-warning"/>修改</a>
                                   		<a href="${sc_ctx}/employee2/view/${employee.uuid}" target="_blank" class="btn"/>查看</a>
                                   		<a href="${sc_ctx}/employee2/quit/${employee.uuid}" class="btn btn-danger"/>离职</a>
                                    </td>
                                </tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	employeeList}" >
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
                </form>
            </div>
		</div>
	</body>
</html>