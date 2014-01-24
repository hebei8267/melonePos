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
        <script>
            $().ready(function() {
            	$("#listForm").validate({
                    rules : {
                    	signBillSupBtn : {
                            requiredSelect : 'supplierBwId'
                        }
                    }
                });
            	//-----------------------------------
                // 全选/全部选
                //-----------------------------------
                $("#checkAll").click(function() {
                    $('input[name="supplierBwId"]').attr("checked", this.checked);
                });
                var $subCheckBox = $("input[name='supplierBwId']");
                $subCheckBox.click(function() {
                    $("#checkAll").attr("checked", $subCheckBox.length == $("input[name='supplierBwId']:checked").length ? true : false);
                });
                
                
                $("#bwDataSynBtn").click(function() {
                    $("#listForm").attr("action", "${sc_ctx}/supplier/bwDataSynBtn");
                    $("#listForm").submit();
                });
                
                $("#signBillSupBtn").click(function() {
                	var $subCheckBox = $("input[name='supplierBwId']");
                    var supplierBwIds = "";
                    $.each($subCheckBox, function(index, _checkBox) {
                        if (_checkBox.checked) {
                        	supplierBwIds += _checkBox.value + ",";
                        }
                    });
                    if (supplierBwIds.length > 0) {
                    	supplierBwIds = supplierBwIds.substring(0, supplierBwIds.length - 1);
                    }

                    $("#supplierBwIds").val(supplierBwIds);

                    $("#listForm").attr("action", "${sc_ctx}/supplier/signBillSupDataSynBtn");
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
                            <h3>供应商管理</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <button	id="bwDataSynBtn" class="btn btn-warning" type="button">数据同步 (百威)</button>
                        <input type="button" id="signBillSupBtn" name="signBillSupBtn" class="btn btn-primary" value="数据同步 (挂账供应商)" />
                    </div>
                    
                    <input type="hidden" name="supplierBwIds" id="supplierBwIds"/>
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                	<th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th	width="45" class="center">
                                        序号
                                    </th>
                                    <th width="105" class="center">
                                        供应商编号
                                    </th>
                                    <th>
                                        供应商名称
                                    </th>
                                    <th>
                                        付款方式
                                    </th>
                                    <th>
                                        所在区域
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${supplierList}" var="supplier" varStatus="status">
                                    <tr>
                                    	<td	class="center">
                                    		<c:if test="${empty	supplier._supplierBwId}" >
                                    		<input type="checkbox" name="supplierBwId" value="${supplier.supplierBwId}"></input>
                                    		</c:if>
                                        	
                                        	<c:if test="${!empty supplier._supplierBwId}" >
                                    		<input type="checkbox" checked="true" name="supplierBwId" value="${supplier.supplierBwId}"></input>
                                    		</c:if>
                                        </td>
                                        <td	class="center">
                                            ${status.index + 1}
                                        </td>
                                        <td class="center">
                                            ${supplier.supplierBwId}
                                        </td>
                                        <td>
                                            ${supplier.name}
                                        </td>
                                        <td>
                                            <c:if test="${supplier.payType == '1'}">
                                                现款商户
                                            </c:if>
                                            <c:if test="${supplier.payType == '2'}">
                                                月结商户
                                            </c:if>
                                            <c:if test="${supplier.payType == '4'}">
                                                不定
                                            </c:if>
                                        </td>
                                        <td>
                                            ${supplier.region.name}
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/supplier/edit/${supplier.uuid}" class="btn btn-warning"/>修改</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	supplierList}" >
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
                </div>
            </form>
        </div>
    </body>
</html>