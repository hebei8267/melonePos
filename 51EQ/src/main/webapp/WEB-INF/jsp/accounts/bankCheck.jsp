<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="DEFAULT_RETAINED_AMT">
    ${DEFAULT_RETAINED_AMT}
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
                    		date : true,
                    		compareDate : "#optDateShow_start"
                        },
                        orgId : {
                    		required : true
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

                    $("#listForm").attr("action", "${sc_ctx}/bankCheck/search");
                    $("#listForm").submit();
                });
                
              	//-----------------------------------
                // 确认按钮点击
                //-----------------------------------
                $("#auditBtn").click(function() {
                    if ($("#listForm").valid()) {
                        $('#__audit_confirm').modal({
                            backdrop : true,
                            keyboard : true,
                            show : true
                        });
                    }
                });
              	
                $("#__audit_confirm_Btn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});
		
					$("#listForm").attr("action", "${sc_ctx}/bankCheck/audit");
					$("#listForm").submit();
				});
            });
            
            function bankCheckFlg_btn_click(index){
				var _css = $("#ex_btn_"+index).hasClass("btn-info");

				if(_css){
					$("#ex_btn_"+index).removeClass("btn-info");
					$("#ex_btn_"+index).addClass("btn-danger");
					$("#ex_btn_"+index).val("未审核");
					$("#bankCheckFlg_"+index).val("0");
				} else {
					$("#ex_btn_"+index).removeClass("btn-danger");
					$("#ex_btn_"+index).addClass("btn-info");
					$("#ex_btn_"+index).val("已审核");
					$("#bankCheckFlg_"+index).val("1");
				}
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
                            <h3>存款信息</h3>
                        </legend>
                    </div>
                    <div class="span12">
                		<div class="alert alert-error">
                		注意：单个门店同一天有2笔以上 (含2笔) 存款信息时 , 必须保持其审核信息一致 !
						</div>
                	</div>
                	
                    <div class="span5">
                        <label class="control-label">销售日期 :</label>
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
                        </select>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th>
                                        销售日期
                                    </th>
                                    <th>
                                        存款金额
                                    </th>
                                    <th>
                                        存款卡号
                                    </th>
                                    <th>
                                        存款人
                                    </th>
                                    <th	width="70">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${cashRunList}" var="cashRun" varStatus="status1">
                                    <tr>
                                        <td>
                                        	${cashRun.optDateShow}
                                        </td>
                                        <td>
                                        	${cashRun.depositAmt}
                                        </td>
                                        <td>
                                        	${cashRun.bankCardNo}
                                        </td>
                                        <td>
                                        	${cashRun.depositor}
                                        </td>
                                        <td>
                                            <c:if test="${cashRun.bankCheckFlg == 1}">
											<input type="button" id="ex_btn_${status1.index + 1}" onclick="bankCheckFlg_btn_click('${status1.index + 1}')" class="btn btn-info" value="已审核">
											</c:if>
											<c:if test="${cashRun.bankCheckFlg == 0}">
											<input type="button" id="ex_btn_${status1.index + 1}" onclick="bankCheckFlg_btn_click('${status1.index + 1}')" class="btn btn-danger" value="未审核">
											</c:if>
											<input type="hidden" name="bankCheckFlg" id="bankCheckFlg_${status1.index + 1}" value="${cashRun.bankCheckFlg }">
											<input type="hidden" name="uuid" value="${cashRun.uuid }">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            
                            <c:if test="${empty	cashRunList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="5"	class="rounded-foot-left">
                                            无记录信息
                                        </td>
                                    </tr>
                                </tfoot>
                            </c:if>
                        </table>
                    </div>
                    
                    <c:if test="${!empty cashRunList}">
						<div class="span12" style="text-align: right;">
							<button id="auditBtn" class="btn btn-large btn-warning" type="button">确认</button>
						</div>
					</c:if>
                </div>
            </form>
        </div>
        
        <div class="modal hide fade  __model37" id="__audit_confirm">
		    <div class="modal-header">
		        <a class="close" data-dismiss="modal">×</a>
		        <h4>系统消息</h4>
		    </div>
		    <div class="modal-body">
		        <center>
		            <p class="error">
		                确定要提交审核(存款)信息吗？
		            </p>
		        </center>
		    </div>
		    <div class="modal-footer">
		        <input type="button" class="btn btn-primary" id="__audit_confirm_Btn" value="确定">
		        <a href="#" class="btn" data-dismiss="modal">关闭</a>
		    </div>
		</div>
    </body>
</html>