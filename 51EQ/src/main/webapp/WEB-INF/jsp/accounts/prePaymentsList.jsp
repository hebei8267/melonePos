<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
<html>
	<head>
		<style>
			._warn1 {
				background-color: #f89406;
				padding: 5px 10px 5px 10px;
			}
			._warn2 {
				background-color: #0044cc;
				padding: 5px 10px 5px 10px;
			}
		</style>
		<script>
			$().ready(function() {
				$("#searchForm").validate({
	                rules : {
	                    optDateShow_start : {
	                        required : true
	                    },
	                    optDateShow_end : {
	                        required : true
	                    },
	                    phoneNum : {
                            isPhone : true
                        }
	                }
	            });
				$("#listForm").validate({
	                rules : {
	                    delBtn : {
	                        requiredSelect : 'uuid'
	                    }
	                }
	            });
	            
	            $('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
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
 					
 					$("#searchForm").attr('target', '_self');
					$("#searchForm").attr("action", "${sc_ctx}/prePayments/search");
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
	            
	            $("#listForm").attr('target', '_self');
	            $("#listForm").attr("action", "${sc_ctx}/prePayments/del");
	            $("#listForm").submit();
	        }
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />
		
		<div class="container">
			<div class="row">
         		<div class="span12">
               		<legend>
                   		<h3>会员预付款信息</h3>
                 	</legend>
             	</div>
                    
               	<form method="post"	class="form-inline"	id="searchForm">
               	<div class="span5">
               		<label class="control-label">业务日期 :</label>
                  	<input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
                  	～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
              	</div>
             	<div class="span7">
                  	<label class="control-label">(顾客)电话号码 :</label>
                   	<input id="phoneNum" name="phoneNum" type="text" class="input-medium" value="${phoneNum }"/>
                  	&nbsp;&nbsp;
                	<button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
              	</div>
               	</form>
                    
                
                <form method="post"	class="form-inline"	id="listForm">
              	<div class="span12" style="margin-top: 15px;">
                	<a href="${sc_ctx}/prePayments/consumption"	class="btn btn-primary">消费</a>
                 	<a href="${sc_ctx}/prePayments/recharge"	class="btn btn-warning">充值</a>
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
                                        会员名称
                                    </th>
                                    <th class="center">
                                    	电话号码
                                    </th>
                                    <th class="center">
                                        金额
                                    </th>
                                    <th class="center" width="125">
                                        业务方式
                                    </th>
                                    <th class="center">
                                        班次信息
                                    </th>
                                    <th class="center">
                                        业务时间
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	
                            	<c:forEach items="${prePaymentsList}" var="prePayments">
                            	<tr>
                               		<td	class="center">
                                   		<input type="checkbox" name="uuid" value="${prePayments.uuid}">
                                   	</td>
                                  	<td	class="center">
                                   		${prePayments.name}
                                   	</td>
                                   	<td	class="center">
                                   		${prePayments.phoneNum}
                                   	</td>
                                   	<td	class="center">
                                   		${prePayments.amt}
                                   	</td>
                                   	<td	class="left">
                                   		<%//使用方式(1充值、2消费) %>
                                   		<c:if test="${prePayments.inOutFlg == 1}">
										<span class="_warn1">充值</span>
										</c:if>
										<c:if test="${prePayments.inOutFlg == 2}">
										<span class="_warn2">消费</span>
										</c:if>
										<c:if test="${prePayments.rechargeWay == 1}">
										（现金）
										</c:if>
										<c:if test="${prePayments.rechargeWay == 2}">
										（刷卡）
										</c:if>
                                   	</td>
                                   	<td	class="center">
                                   		<%//使用方式(1充值、2消费) %>
                                   		<c:if test="${prePayments.jobType == 1}">
										早班
										</c:if>
										<c:if test="${prePayments.jobType == 2}">
										晚班
										</c:if>
										<c:if test="${prePayments.jobType == 2}">
										全天班
										</c:if>
                                   	</td>
                                   	<td	class="center">
                                   		<fmt:formatDate value="${prePayments.createDate}" var="createDate" pattern="yyyy-MM-dd HH:mm:ss" />
                                   		${createDate}
                                   	</td>
                                </tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	prePaymentsList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="5" class="rounded-foot-left">
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