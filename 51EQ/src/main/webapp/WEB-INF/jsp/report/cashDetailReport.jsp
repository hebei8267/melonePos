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
            .form-horizontal .control-label {
                width: 130px;
            }
            .form-horizontal .controls {
                margin-left: 145px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="span12">
                    <legend>
                        <h3>${cashRun1.orgName} 销售明细信息</h3>
                    </legend>
                </div>
                <c:if test="${!empty	cashRun1}" >
                    <div class="span6"	style="margin-top: 10px;">
                        <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="cashRun1">
                            <form:hidden path="uuid"/>
                            <div class="control-group">
                                <label class="control-label">日期 :</label>
                                <label class="left-control-label">${cashRun1.optDateShow}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">上班类型 :</label>
                                <label class="left-control-label">
                                    <c:if test="${cashRun1.jobType == 1}">
                                        早班
                                    </c:if>
                                    <c:if test="${cashRun1.jobType == 2}">
                                        晚班
                                    </c:if>
                                    <c:if test="${cashRun1.jobType == 4}">
                                        全天班
                                    </c:if> </label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">班前余额 :</label>
                                <label class="left-control-label">${cashRun1.initAmt} 元</label>
                            </div>
                            <div class="control-group">
	                            <label class="control-label">销售收现 :</label>
	                            <label class="left-control-label">${cashRun1.saleCashAmt} 元</label>
	                        </div>
	                        <div class="control-group">
                            	<label class="control-label">账面应有现金 :</label>
                            	<label class="left-control-label">${cashRun1.carryingCashAmt} 元</label>
                            	<form:hidden path="carryingCashAmt"/>
                        	</div>
                        	<div class="control-group">
	                            <label class="control-label">实点现金 :</label>
	                            <label class="left-control-label">${cashRun1.cashAmt} 元</label>
                        	</div>
                        	<div class="control-group">
	                            <label class="control-label">现金盈亏 :</label>
	                            <label class="left-control-label">${cashRun1.adjustAmt} 元</label>
	                        </div>
                            <div class="control-group">
                                <label class="control-label">刷卡金额(单据) :</label>
                                <label class="left-control-label">
                                <c:if test="${cashRun1.cardAmt != cashRun1.cardAmtBw}">
                                    <span class="warn_text">
                                </c:if> ${cashRun1.cardAmt}
                                <c:if test="${cashRun1.cardAmt != cashRun1.cardAmtBw}">
                                    </span>
                                </c:if> 元
                                </label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡金额(百威) :</label>
                                <label class="left-control-label">
                                <c:if test="${cashRun1.cardAmt != cashRun1.cardAmtBw}">
                                    <span class="warn_text">
                                </c:if> ${cashRun1.cardAmtBw}
                                <c:if test="${cashRun1.cardAmt != cashRun1.cardAmtBw}">
                                    </span>
                                </c:if> 元
                                </label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡笔数 :</label>
                                <label class="left-control-label">${cashRun1.cardNum}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">凭证号 :</label>
                                <label class="left-control-label">${cashRun1.cardCertNo}</label>
                            </div>
                            
                            <div class="control-group">
                                <label class="control-label" style="color:#FF6633;font-weight:bold;">代金卷 :</label>
                                <label class="left-control-label">${cashRun1.couponNo}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label" style="color:#FF6633;font-weight:bold;">代金卷面值 :</label>
                                <label class="left-control-label">${cashRun1.couponValue}</label>
                            </div>
                            
                            <div class="control-group">
                                <label class="control-label">存款金额 :</label>
                                <label class="left-control-label">${cashRun1.depositAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款人 :</label>
                                <label class="left-control-label">${cashRun1.depositor}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款银行卡号 :</label>
                                <label class="left-control-label">${cashRun1.bankCardNo}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">留存金额 :</label>
                                <label class="left-control-label">
                                <c:if test="${cashRun1.retainedAmt > DEFAULT_RETAINED_AMT}">
                                	<span class="warn2_text">
                                </c:if>${cashRun1.retainedAmt}
                                <c:if test="${cashRun1.retainedAmt > DEFAULT_RETAINED_AMT}">
                                	</span>
                                </c:if> 元
                                </label>
                            </div>
                            <div class="control-group">
	                            <label class="control-label">当班销售金额 :</label>
	                            <label class="left-control-label">${cashRun1.saleAmt} 元</label>
	                        </div>
                            <div class="control-group">
                                <label class="control-label">备注 :</label>
                                <label class="left-control-label">${cashRun1.descTxt}</label>
                            </div>
                            <div class="control-group">
                            	<label class="control-label">商场汇报销售额 :</label>
                            	<label class="left-control-label">${cashRun1.reportAmt}</label>
                        	</div>
                        </form:form>
                    </div>
                </c:if>

                <c:if test="${!empty	cashRun2}" >
                    <div class="span12 cash_daily visible-phone"></div>
                    <div class="span6"	style="margin-top: 10px;">
                        <form:form method="POST" class="form-horizontal" id="inputForm2"	modelAttribute="cashRun2">
                            <form:hidden path="uuid"/>
                            <div class="control-group">
                                <label class="control-label">日期 :</label>
                                <label class="left-control-label">${cashRun2.optDateShow}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">上班类型 :</label>
                                <label class="left-control-label">
                                    <c:if test="${cashRun2.jobType == 1}">
                                        早班
                                    </c:if>
                                    <c:if test="${cashRun2.jobType == 2}">
                                        晚班
                                    </c:if>
                                    <c:if test="${cashRun2.jobType == 4}">
                                        全天班
                                    </c:if> </label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">班前余额 :</label>
                                <label class="left-control-label">${cashRun2.initAmt} 元</label>
                            </div>
                            <div class="control-group">
                            	<label class="control-label">销售收现 :</label>
                            	<label class="left-control-label">${cashRun2.saleCashAmt} 元</label>
                        	</div>
                        	<div class="control-group">
                            	<label class="control-label">账面应有现金 :</label>
                            	<label class="left-control-label">${cashRun2.carryingCashAmt} 元</label>
                            	<form:hidden path="carryingCashAmt"/>
                        	</div>
                        	<div class="control-group">
	                            <label class="control-label">实点现金 :</label>
	                            <label class="left-control-label">${cashRun2.cashAmt} 元</label>
                        	</div>
                        	<div class="control-group">
	                            <label class="control-label">现金盈亏 :</label>
	                            <label class="left-control-label">${cashRun2.adjustAmt} 元</label>
	                        </div>
                            <div class="control-group">
                                <label class="control-label">刷卡金额(单据) :</label>
                                <label class="left-control-label">
                                <c:if test="${cashRun2.cardAmt != cashRun2.cardAmtBw}">
                                    <span class="warn_text">
                                </c:if> ${cashRun2.cardAmt}
                                <c:if test="${cashRun2.cardAmt != cashRun2.cardAmtBw}">
                                    </span>
                                </c:if> 元
                                </label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡金额(百威) :</label>
                                <label class="left-control-label">
                                <c:if test="${cashRun2.cardAmt != cashRun2.cardAmtBw}">
                                    <span class="warn_text">
                                </c:if> ${cashRun2.cardAmtBw}
                                <c:if test="${cashRun2.cardAmt != cashRun2.cardAmtBw}">
                                    </span>
                                </c:if> 元
                                </label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡笔数 :</label>
                                <label class="left-control-label">${cashRun2.cardNum}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">凭证号 :</label>
                                <label class="left-control-label">${cashRun2.cardCertNo}</label>
                            </div>
                            
                            <div class="control-group">
                                <label class="control-label" style="color:#FF6633;font-weight:bold;">代金卷 :</label>
                                <label class="left-control-label">${cashRun2.couponNo}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label" style="color:#FF6633;font-weight:bold;">代金卷面值 :</label>
                                <label class="left-control-label">${cashRun2.couponValue}</label>
                            </div>
                            
                            <div class="control-group">
                                <label class="control-label">存款金额 :</label>
                                <label class="left-control-label">${cashRun2.depositAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款人 :</label>
                                <label class="left-control-label">${cashRun2.depositor}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款银行卡号 :</label>
                                <label class="left-control-label">${cashRun2.bankCardNo}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">留存金额 :</label>
                                <label class="left-control-label">
                                <c:if test="${cashRun2.retainedAmt > DEFAULT_RETAINED_AMT}">
                                	<span class="warn2_text">
                                </c:if>${cashRun2.retainedAmt}
                                <c:if test="${cashRun2.retainedAmt > DEFAULT_RETAINED_AMT}">
                                	</span>
                                </c:if> 元
                                </label>
                            </div>
                            <div class="control-group">
	                            <label class="control-label">当班销售金额 :</label>
	                            <label class="left-control-label">${cashRun2.saleAmt} 元</label>
	                        </div>
                            <div class="control-group">
                                <label class="control-label">备注 :</label>
                                <label class="left-control-label">${cashRun2.descTxt}</label>
                            </div>
                            <div class="control-group">
                            	<label class="control-label">商场汇报销售额 :</label>
                            	<label class="left-control-label">${cashRun2.reportAmt}</label>
                        	</div>
                        </form:form>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>