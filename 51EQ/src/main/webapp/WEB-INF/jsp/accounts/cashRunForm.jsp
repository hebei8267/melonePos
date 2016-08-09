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
            .form-horizontal .control-label {
                width: 130px;
            }
            .form-horizontal .controls {
                margin-left: 145px;
            }
            .mytable tbody tr td.right {
				padding-right: 10px;
			}
			.mytable tbody tr td {
				height: 40px;
				valign : middle;
			}
			.cash_daily {
				border-bottom: 3px solid #F89406;
			}
        </style>
        <script>
            $(function() {
                var oldDateVal = "";
                var oldBankIdVal = "";

                $('#optDateShow').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow').datepicker().on('changeDate', function(ev) {
                    if (oldDateVal == $('#optDateShow').val()) {
                        return;
                    }

                    oldDateVal = $('#optDateShow').val();

                    if ("" != $('#optDateShow').val()) {
                    	//置空上班类型
                    	$('#jobType').get(0).selectedIndex = 0;
                    	
                        $.post("${sc_ctx}/cashRun/calInitAmt", {
                            "optDateShow" : $('#optDateShow').val()
                        }, function(result) {

                            $('#_initAmt_label').html(result + " 元");
                            $('#initAmt').val(result);
                            
                            calRetainedAmt();
                        });
                    }
                });
                
                $("#inputForm").validate({
                    rules : {
                        optDateShow : {
                            required : true,
                            date : true,
                            dateLessThan : $("#_tomorrow_date").val()
                        },
                        jobType : {
                            required : true
                        },
                        mngUserId : {
                            required : true
                        },
                        saleCashAmt : {
                            required : true
                        },
                        cashAmt : {
                            required : true,
                            money : true
                        },
                        cardAmt : {
                            required : true,
                            money : true
                        },
                        cardAmtBw : {
                            required : true,
                            money : true
                        },
                        zfbSaleAmt : {
                            required : true,
                            money : true
                        },
                        wxSaleAmt : {
                            required : true,
                            money : true
                        },
                        cardNum : {
                            required : true,
                            digits : true
                        },
                        cardCertNo : {
                            myRequired : "#cardAmt",
                            maxlength : 32
                        },
                        /*couponNo : {
                            myRequired : "#couponValue"
                        },*/
                        couponValue : {
                            money : true
                        },
                        depositAmt : {
                            required : true,
                            money : true
                        },
                        prePayCashAmt : {
                            required : true,
                            money : true
                        },
                        prePayCardAmt : {
                            required : true,
                            money : true
                        },
                        goldCardAmt : {
                            required : true,
                            money : true
                        },
                        rebateAmt : {
                            required : true,
                            money : true
                        },
                        depositor : {
                            myRequired : "#depositAmt",
                            maxlength : 16
                        },
                        bankId : {
                            myRequired : "#depositAmt"
                        },
                        bankCardNo : {
                            myRequired : "#depositAmt"
                        },
                        descTxt : {
                            maxlength : 255
                        },
                        reportAmt : {
                            required : true,
                            money : true
                        }
                    }
                });

                $("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr("target", "_self");
                    $("#inputForm").attr("action", "${sc_ctx}/cashRun/save");
                    $("#inputForm").submit();
                });

                $("#cashAmt").change(function() {
                	carryingCashAmt();

                    adjustAmt();
                    
                    saleAmt();
                });
                $("#cardAmt").change(function() {
                    saleAmt();
                });
                $("#zfbSaleAmt").change(function() {
                    saleAmt();
                });
                $("#wxSaleAmt").change(function() {
                    saleAmt();
                });
                $("#depositAmt").change(function() {
                    calRetainedAmt();
                });
                $("#saleCashAmt").change(function() {
                    carryingCashAmt();

                    adjustAmt();
                    
                    saleAmt();
                });
                
                $("input[name$='couponValue']").each(function(){
                	$(this).change(function() {
                    	saleAmt();
                	});
                });
                
                $("#prePayCashAmt").change(function() {
                	carryingCashAmt();

                    adjustAmt();
                });
                
                $("#goldCardAmt").change(function() {
                	saleAmt();
                });
                
                $("#rebateAmt").change(function() {
                	saleAmt();
                });
            });

            // 账面应有现金 = 班前余额+外销收现+预付款(收现)
            function carryingCashAmt() {
                var _result = numAdd($("#initAmt").val(), $("#saleCashAmt").val());
                _result = numAdd(_result, $("#prePayCashAmt").val());

                $("#_carryingCashAmt").html(_result + " 元");
                $("#carryingCashAmt").val(_result);
            }

            // 现金盈亏 = 实点现金-账面应有现金
            function adjustAmt() {
                var _result = numSub($("#cashAmt").val(), $("#carryingCashAmt").val());

                $("#_adjustAmt").html(_result + " 元");
                $("#adjustAmt").val(_result);
            }

            // 留存金额 = 实点现金-存款金额
            function calRetainedAmt() {
                var _result = numSub($("#cashAmt").val(), $("#depositAmt").val());

                $("#_retainedAmt_label").html(_result + " 元");
                $("#retainedAmt").val(_result);
            }

            // 当班销售金额 = 销售收现 + 支付宝销售额 + 微信销售额 + 刷卡金额(单据) + 代金卷销售 + 金卡余额消费 + 金卡返利金额
            function saleAmt() {
                var _result = numAdd($("#saleCashAmt").val(), $("#cardAmt").val());
                
                //金卡消费金额
                var _result3 = 0;
                _result3 = numAdd(_result3, $("#goldCardAmt").val());
                _result3 = numAdd(_result3, $("#rebateAmt").val());
                
                // 代金卷销售合计
                var _result2 = 0;
                $("input[name$='couponValue']").each(function(){
                	_result2 = numAdd(_result2, $(this).val());
                });
                
                // 支付宝销售额
                var _result4 = 0;
                _result4 = numAdd(_result4, $("#zfbSaleAmt").val());
                
             	// 微信销售额
                var _result5 = 0;
                _result5 = numAdd(_result5, $("#wxSaleAmt").val());

                $("#_saleAmt").html("(现金／刷卡)" + _result + " 元 + (支付宝)" + _result4 + " 元 + (微信)" + _result5 + " 元 + (金卡)" + _result3 + " 元 + (代金卷)" + _result2 + " 元");
                $("#saleAmt").val(_result);
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
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName} 销售信息
                        <c:if test="${empty	cashRun.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty cashRun.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                <input type="hidden" id="_tomorrow_date" value="<%=DateUtils.getNextDateFormatDate(1, "yyyy-MM-dd")%>">
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" id="inputForm"	modelAttribute="cashRun">
                        <form:hidden path="uuid"/>
                        <table class="mytable">
                        	<tr>
                        		<td width="130" class="right">督导员 :</td>
                        		<td width="350">
	                        		<select name="mngUserId">
			                            <c:forEach items="${mngPerList}" var="mngPer">
			                                <c:if test="${mngPer.key == cashRun.mngUserId}">
			                                    <option value="${mngPer.key }" selected>${mngPer.value }</option>
			                                </c:if>
			                                <c:if test="${mngPer.key != cashRun.mngUserId}">
			                                    <option value="${mngPer.key }">${mngPer.value }</option>
			                                </c:if>
			                            </c:forEach>
			                        </select>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td width="130" class="right">日期 :</td>
                        		<td width="350">
	                        		<c:if test="${empty	cashRun.uuid}">
		                                <div class="controls">
		                                    <form:input	path="optDateShow" />
		                                </div>
		                            </c:if>
		                            <c:if test="${!empty cashRun.uuid}">
		                                <label class="left-control-label">${cashRun.optDateShow}</label>
		                                <form:hidden path="optDateShow"/>
		                            </c:if>
                        		</td>
                        		<td width="130" class="right">上班类型 :</td>
                        		<td width="350">
                        			<c:if test="${empty	cashRun.uuid}">
		                                <div class="controls">
		                                    <form:select path="jobType" items="${jobTypeList}"/>
		                                </div>
		                            </c:if>
		                            <c:if test="${!empty cashRun.uuid}">
		                                <label class="left-control-label">
		                                    <c:if test="${cashRun.jobType == 1}">
		                                        早班
		                                    </c:if>
		                                    <c:if test="${cashRun.jobType == 2}">
		                                        晚班
		                                    </c:if>
		                                    <c:if test="${cashRun.jobType == 4}">
		                                        全天班
		                                    </c:if> </label>
		                            </c:if>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">金卡充值(收现) :</td>
                        		<td>
                            		<form:input	path="prePayCashAmt" />&nbsp;
								</td>
                        		<td class="right">金卡充值(刷卡)</td>
                        		<td>
                            		<form:input	path="prePayCardAmt" />&nbsp;
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">班前余额 :</td>
                        		<td>
                        			<label class="left-control-label" id="_initAmt_label">${cashRun.initAmt} 元</label>
                            		<form:hidden path="initAmt"/>
                        		</td>
                        		<td class="right">销售收现 :</td>
                        		<td>
                        			<form:input	path="saleCashAmt" />&nbsp;元
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">账面应有现金 :</td>
                        		<td>
                        			<label class="left-control-label" id="_carryingCashAmt">${cashRun.carryingCashAmt} 元</label>
                            		<form:hidden path="carryingCashAmt"/>
                        		</td>
                        		<td class="right">实点现金 :</td>
                        		<td>
                        			<form:input	path="cashAmt" />&nbsp;元
                                </td>
                        	</tr>
                        	<tr>
                        		<td class="right">现金盈亏 :</td>
                        		<td colspan="3">
                        			<label class="left-control-label" id="_adjustAmt" style="color:#FF6633">${cashRun.adjustAmt} 元</label>
                            		<form:hidden path="adjustAmt"/>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td colspan="4" class="cash_daily"></td>
                        	</tr>
                        	<tr>
                        		<td colspan="4"><br></td>
                        	</tr>
                        	
                        	
                        	<tr>
                        		<td class="right">刷卡金额(单据) :</td>
                        		<td>
                        			<form:input	path="cardAmt" />&nbsp;元
                        		</td>
                        		<td class="right">刷卡金额(百威) :</td>
                        		<td>
                        			<form:input	path="cardAmtBw" />&nbsp;元
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">刷卡笔数 :</td>
                        		<td><form:input	path="cardNum" /></td>
                        		<td class="right">凭证号 :</td>
                        		<td><form:input	path="cardCertNo" /></td>
                        	</tr>
                        	<tr>
                        		<td colspan="4" class="cash_daily"></td>
                        	</tr>
                        	<tr>
                        		<td colspan="4"><br></td>
                        	</tr>
                        	
                        	
                        	<tr>
                        		<td class="right" style="vertical-align: middle;">
                        		<img style="-webkit-user-select: none" src="${ctx}/static/img/270-alipay.png">
                        		支付宝
                        		</td>
                        		<td>
                        			<form:input	path="zfbSaleAmt" />&nbsp;元
                        		</td>
                        		
                        		<td class="right" style="vertical-align: middle;">
                        		<div>
                        		<img style="-webkit-user-select: none" src="${ctx}/static/img/270-weixin.png">
                        		微信
                        		</td>
                        		<td>
                        			<form:input	path="wxSaleAmt" />&nbsp;元
                        		</td>
                        	</tr>
                        	<tr>
                        		<td colspan="4" class="cash_daily"></td>
                        	</tr>
                        	<tr>
                        		<td colspan="4"><br></td>
                        	</tr>
                        	
                        	
                        	<tr>
                        		<td class="right">金卡余额消费 :</td>
                        		<td><form:input	path="goldCardAmt" />&nbsp;元</td>
                        		<td class="right">金卡返利金额 :</td>
                        		<td><form:input	path="rebateAmt" />&nbsp;元</td>
                        	</tr>
                        	<tr>
                        		<td colspan="4" class="cash_daily"></td>
                        	</tr>
                        	<tr>
                        		<td colspan="4"><br></td>
                        	</tr>
                        	
                        	
                        	<tr>
                        		<td class="right">代金卷1 :</td>
                        		<td>
                        			<select name="couponNo">
			                            <c:forEach items="${couponList}" var="coupon">
			                                <c:if test="${coupon.key == cashRun.couponNo[0]}">
			                                    <option value="${coupon.key }" selected>${coupon.value }</option>
			                                </c:if>
			                                <c:if test="${coupon.key != cashRun.couponNo[0]}">
			                                    <option value="${coupon.key }">${coupon.value }</option>
			                                </c:if>
			                            </c:forEach>
			                        </select>
                        		</td>
                        		<td class="right">代金卷面值1 :</td>
                        		<td>
                        			<input id="couponValue" name="couponValue" type="text" value="${cashRun.couponValue[0]}">
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">代金卷2 :</td>
                        		<td>
                        			<select name="couponNo">
			                            <c:forEach items="${couponList}" var="coupon">
			                                <c:if test="${coupon.key == cashRun.couponNo[1]}">
			                                    <option value="${coupon.key }" selected>${coupon.value }</option>
			                                </c:if>
			                                <c:if test="${coupon.key != cashRun.couponNo[1]}">
			                                    <option value="${coupon.key }">${coupon.value }</option>
			                                </c:if>
			                            </c:forEach>
			                        </select>
                        		</td>
                        		<td class="right">代金卷面值2 :</td>
                        		<td>
                        			<input id="couponValue" name="couponValue" type="text" value="${cashRun.couponValue[1]}">
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">代金卷3 :</td>
                        		<td>
                        			<select name="couponNo">
			                            <c:forEach items="${couponList}" var="coupon">
			                                <c:if test="${coupon.key == cashRun.couponNo[2]}">
			                                    <option value="${coupon.key }" selected>${coupon.value }</option>
			                                </c:if>
			                                <c:if test="${coupon.key != cashRun.couponNo[2]}">
			                                    <option value="${coupon.key }">${coupon.value }</option>
			                                </c:if>
			                            </c:forEach>
			                        </select>
                        		</td>
                        		<td class="right">代金卷面值3 :</td>
                        		<td>
                        			<input id="couponValue" name="couponValue" type="text" value="${cashRun.couponValue[2]}">
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">代金卷4 :</td>
                        		<td>
                        			<select name="couponNo">
			                            <c:forEach items="${couponList}" var="coupon">
			                                <c:if test="${coupon.key == cashRun.couponNo[3]}">
			                                    <option value="${coupon.key }" selected>${coupon.value }</option>
			                                </c:if>
			                                <c:if test="${coupon.key != cashRun.couponNo[3]}">
			                                    <option value="${coupon.key }">${coupon.value }</option>
			                                </c:if>
			                            </c:forEach>
			                        </select>
                        		</td>
                        		<td class="right">代金卷面值4 :</td>
                        		<td>
                        			<input id="couponValue" name="couponValue" type="text" value="${cashRun.couponValue[3]}">
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">代金卷5 :</td>
                        		<td>
                        			<select name="couponNo">
			                            <c:forEach items="${couponList}" var="coupon">
			                                <c:if test="${coupon.key == cashRun.couponNo[4]}">
			                                    <option value="${coupon.key }" selected>${coupon.value }</option>
			                                </c:if>
			                                <c:if test="${coupon.key != cashRun.couponNo[4]}">
			                                    <option value="${coupon.key }">${coupon.value }</option>
			                                </c:if>
			                            </c:forEach>
			                        </select>
                        		</td>
                        		<td class="right">代金卷面值5 :</td>
                        		<td>
                        			<input id="couponValue" name="couponValue" type="text" value="${cashRun.couponValue[4]}">
                        		</td>
                        	</tr>
                        	
                        	<tr>
                        		<td colspan="4" class="cash_daily"></td>
                        	</tr>
                        	<tr>
                        		<td colspan="4"><br></td>
                        	</tr>
                        	
                        	
                        	<tr>
                        		<td class="right">存款金额 :</td>
                        		<td> <form:input path="depositAmt" />&nbsp;元</td>
                        		<td class="right">存款人 :</td>
                        		<td><form:input	path="depositor" /></td>
                        	</tr>
                        	<tr>
                        		<td class="right">存款银行卡号 :</td>
                        		<td colspan="3"><form:select path="bankCardNo" items="${bankCardList}"/></td>
                        	</tr>
                        	<tr>
                        		<td class="right">留存金额 :</td>
                        		<td>
                        			<label class="left-control-label" id="_retainedAmt_label">${cashRun.retainedAmt} 元</label>
                        			<form:hidden path="retainedAmt"/>
                        		</td>
                        		<td class="right">当班销售金额 :</td>
                        		<td>
                        			<label class="left-control-label" id="_saleAmt">
                        			(现金／刷卡)${cashRun.saleAmt} 元 + (支付宝)${cashRun.zfbSaleAmt} 元 + (微信)${cashRun.wxSaleAmt} + (金卡)${cashRun.goldCardAmt + cashRun.rebateAmt} 元 + (代金卷)${cashRun.totalCouponValue} 元</label>
		                            <form:hidden path="saleAmt"/>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">商场汇报销售额 :</td>
                        		<td colspan="3"><form:input	path="reportAmt" />&nbsp;元</td>
                        	</tr>
                        	<tr>
                        		<td class="right">备注 :</td>
                        		<td colspan="3"><form:textarea path="descTxt" class="input-xlarge" rows="4"/></td>
                        	</tr>
                        	<tr>
                        		<td colspan="4"></td>
                        	</tr>
                        	<tr>
                        		<td>&nbsp;</td>
                        		<td colspan="3">
                        			<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                	&nbsp;<a href="${sc_ctx}/cashRun" class="btn btn-large">返回</a>
                        		</td>
                        	</tr>
                        </table>
						<br>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
