<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#searchForm").attr("target", "_self");
                    $("#searchForm").attr("action", "${sc_ctx}/shareFile/search");
                    $("#searchForm").submit();
                });
                
                //-----------------------------------
                // 全选/全部选
                //-----------------------------------
                $("#checkAll").click(function() {
            		var checked = $("#checkAll").is(":checked");
                	$("input[name='uuid']").each(function(){
                		if (checked) {
							$(this).attr("checked", true);
						} else {
							$(this).attr("checked", false);
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
                $("#listForm").attr("action", "${sc_ctx}/shareFile/del");
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
                            <h3>共享文件管理</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <label class="control-label">文件状态 :</label>
                        <select name="status" class="input-medium">
                            <c:forEach items="${statusList}" var="_status">
                                <c:if test="${_status.key == status}">
                                    <option value="${_status.key }" selected>${_status.value }</option>
                                </c:if>
                                <c:if test="${_status.key != status}">
                                    <option value="${_status.key }">${_status.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>&nbsp;&nbsp;
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                </form>
                
                <form method="post" class="form-inline" id="listForm">
                	<div class="span12" style="padding-top: 10px;">
                        <a href="${sc_ctx}/shareFile/new"	class="btn btn-primary">新增</a>
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
                                        编号
                                    </th>
                                    <th class="center">
                                        简称
                                    </th>
                                    <th class="center">
                                        名称
                                    </th>
                                    <th class="center">
                                        状态
                                    </th>
                                    <th class="center">
                                        最后修改时间
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${shareFileList}" var="shareFile">
                            		<tr>
                            			<td class="center">
                            				<input type="checkbox" name="uuid" value="${shareFile.uuid}">
                            			</td>
                                        <td>
                                            ${shareFile.fileNo}
                                        </td>
                                        <td>
                                            ${shareFile.fileShortName}
                                        </td>
                                        <td class="center">
                                        	${shareFile.fileName}
                                        </td>
                                        <td class="center">
                                        	<c:if test="${shareFile.status.equals('00')}">
                                                <span class='_warn1'>在用</span>
                                            </c:if>
                                            <c:if test="${shareFile.status.equals('01')}">
                                                <span class='_warn2'>停用</span>
                                            </c:if>
                                            <c:if test="${shareFile.status.equals('99')}">
                                                <span class='_warn2'>废除</span>
                                            </c:if>
                                        </td>
                                        <td>
                                        	<fmt:formatDate value="${shareFile.updateDate}" pattern="yyyy年MM月dd日 HH时mm分ss秒 "/>
                                        </td>
                                        <td>
                                      		<a href="${sc_ctx}/shareFile/edit/${shareFile.uuid}" class="btn btn-warning" target="_blank"/>编辑</a>
                                        </td>
                                    </tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	shareFileList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="6" class="rounded-foot-left">
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