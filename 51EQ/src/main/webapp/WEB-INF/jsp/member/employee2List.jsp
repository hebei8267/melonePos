<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
<html>
	<head>
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
	                <div class="span3">
	                    <label class="control-label">员工姓名 : </label>
	                    <input id="name" name="name" type="text" class="input-medium" value="${name}"/>
	                </div>
	                <div class="span3">
	                    <label class="control-label">联系电话 : </label>
	                    <input id="phone" name="phone" type="text" class="input-medium" value="${phone}"/>
	                </div>
	                <div class="span6">
	                    <label class="control-label">身份证号 : </label>
	                    <input id="idCardNo" name="idCardNo" type="text" class="input-medium" value="${idCardNo}"/>
	                    &nbsp;&nbsp;
	                    <button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
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
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th class="center">
                                        所属机构
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
                                        聘用形式
                                    </th>
                                    <th	width="117">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${employeeList}" var="employee">
                            	<tr>
                                    <td	class="center">
                                        <input type="checkbox" name="uuid" value="${employee.uuid}" />
                                    </td>
                                    <td class="center">
                                        ${employee.orgId}
                                    </td>
                                    <td>
                                        ${employee.name}
                                    </td>
                                    <td>
                                        ${employee.phone}
                                    </td>
                                    <td>
                                        ${employee.address}
                                    </td>
                                    <td>
                                       	${employee.entryTime}
                                    </td>
                                    <td>
                                        ${employee.pos}
                                    </td>
                                    <td>
                                        ${employee.employForm}
                                    </td>
                                    <td>
                                   		<a href="${sc_ctx}/employee2/edit/${employee.uuid}" class="btn btn-warning"/>修改</a>
                                   		<a href="${sc_ctx}/employee2/view/${employee.uuid}" target="_blank" class="btn"/>查看</a>
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