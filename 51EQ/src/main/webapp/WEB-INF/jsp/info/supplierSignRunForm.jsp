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
    	<style>
    	.left-control-label {
			padding-left: 0px;
		}
		.form-horizontal .my-control-label {
        	width: 150px;
        }
        .my-control-label h3 {
        	width: 150px;
        	text-align: center;
        	padding: 5px;
        }
        .noLoan {
        	background-image: linear-gradient(to bottom, #0088cc, #0044cc);
        }
        .loan {
        	background-image: linear-gradient(to bottom, #fbb450, #f89406);
        }
    	</style>
        <script>
        function getRunInfo(runType){
        	$.post("${sc_ctx}/supplierSignRun/getRunInfo", {
                "supplierBwId" : '${supId}' ,
                "optDateY" :  '${optDateY}' ,
                "optDateM" :  $("#optDateM").val() ,
                "runType" :  runType 
            }, function(result) {
            	var _jsResult = jQuery.parseJSON(result);
            	if(null != _jsResult && _jsResult.runType == 1){// 赊购挂账
                	$("#loan_txt").html("有赊购挂账");
                	if($("#loan_txt").hasClass("noLoan")){
                		$("#loan_txt").removeClass("noLoan")
                	}
                	if(!$("#loan_txt").hasClass("loan")){
                		$("#loan_txt").addClass("loan")
                	}
                	
                	$("#loanForm_uuid").val(_jsResult.uuid);
                } else if(null != _jsResult && _jsResult.runType == 2){// 通知对账
                	$("#checkNoticeDate").val(_jsResult.checkNoticeDate);
                	$('input[name="noticeMode"][value="' + _jsResult.noticeMode + '"]').attr('checked',true);
                	$("#notice_descTxt").val(_jsResult.descTxt);
                	
                	$("#noticeForm_uuid").val(_jsResult.uuid);
                } else if(null != _jsResult && _jsResult.runType == 3){// 对账完成
                	$("#checkDate").val(_jsResult.checkDate);
                	$("#checkAmt").val(_jsResult.checkAmt);
                	$("#check_descTxt").val(_jsResult.descTxt);
                	
                	$("#check_uuid").val(_jsResult.uuid);
                } else if(null != _jsResult && _jsResult.runType == 4){// 结算付款
                	$("#paymentDate").val(_jsResult.paymentDate);
                	$("#paymentAmt").val(_jsResult.paymentAmt);
                	$("#payment_descTxt").val(_jsResult.descTxt);

                	$("#payment_uuid").val(_jsResult.uuid);
                } else if(null != _jsResult && _jsResult.runType == 5){// 退货申请
                	$("#appDate").val(_jsResult.appDate);
                	$("#appAmt").val(_jsResult.appAmt);
                	$("#app_descTxt").val(_jsResult.descTxt);
                	
                	$("#app_uuid").val(_jsResult.uuid);
                } else if(null != _jsResult && _jsResult.runType == 6){// 退货确认
                	$("#confirmDate").val(_jsResult.confirmDate);
                	$("#confirmAmt").val(_jsResult.confirmAmt);
                	$("#confirm_descTxt").val(_jsResult.descTxt);
                	
                	$("#confirm_uuid").val(_jsResult.uuid);
                } else {
                	$("#loan_txt").html("无赊购挂账");
                	if($("#loan_txt").hasClass("loan")){
                		$("#loan_txt").removeClass("loan")
                	}
                	if(!$("#loan_txt").hasClass("noLoan")){
                		$("#loan_txt").addClass("noLoan")
                	}
                	
                	$("#checkNoticeDate").val('');
                	$('input[name="noticeMode"]').attr('checked',false);
                	$("#notice_descTxt").val('');
                	
                	$("#checkDate").val('');
                	$("#checkAmt").val('');
                	$("#check_descTxt").val('');
                	
                	$("#paymentDate").val('');
                	$("#paymentAmt").val('');
                	$("#payment_descTxt").val('');
                	
                	$("#appDate").val('');
                	$("#appAmt").val('');
                	$("#app_descTxt").val('');
                	
                	$("#confirmDate").val('');
                	$("#confirmAmt").val('');
                	$("#confirm_descTxt").val('');
                	
                	
                	$("#confirm_uuid").val('');
                	$("#app_uuid").val('');
                	$("#payment_uuid").val('');
                	$("#check_uuid").val('');
                	$("#noticeForm_uuid").val('');
                	$("#loanForm_uuid").val('');
                }
            });
        }
        $(function() {
        	$('#checkNoticeDate').datepicker({
                format : 'yyyy-mm-dd'
            });
            $('#checkDate').datepicker({
                format : 'yyyy-mm-dd'
            });
            $('#paymentDate').datepicker({
                format : 'yyyy-mm-dd'
            });
            $('#appDate').datepicker({
                format : 'yyyy-mm-dd'
            });
            $('#confirmDate').datepicker({
                format : 'yyyy-mm-dd'
            });
        	
        	// tab默认不显示
        	$("#tabDiv").hide();
        	
        	$('a[data-toggle="tab"]').on('shown', function (e) {
        		if("" == $("#optDateM").val()){//为选择操作月份
        			return;
        		}
        		var tabId = e.currentTarget.hash;
        		if(tabId == "#loan") {
        			getRunInfo(1);
        		} else if(tabId == "#notice") {
        			getRunInfo(2);
        		} else if(tabId == "#check") {
        			getRunInfo(3);
        		} else if(tabId == "#payment") {
        			getRunInfo(4);
        		} else if(tabId == "#app") {
        			getRunInfo(5);
        		} else if(tabId == "#confirm") {
        			getRunInfo(6);
        		}
        	});
        	
        	$("#optDateM").change(function(){
        		var checkValue = $("#optDateM").val();
        		if (checkValue == "00"){
        			$("#tabDiv").hide();
        		} else {
        			$("#tabDiv").show();
        			
        			// 显示第一个tab框
        			$('#myTab a:first').tab('show');
        			
        			$("#loanForm_optDateM").val(checkValue);
        			$("#noticeForm_optDateM").val(checkValue);
        			$("#check_optDateM").val(checkValue);
        			$("#payment_optDateM").val(checkValue);
        			$("#app_optDateM").val(checkValue);
        			$("#confirm_optDateM").val(checkValue);
        		}
        	});
        	

        	//=======================================================================
        	//赊购挂账-不挂
        	$("#noLoan_saveBtn").click(function() {
        		_tip_check(1);
            });
        	//清除按钮
        	$("#notice_cleanBtn").click(function() {
        		_tip_check(2);
        	});
        	//清除按钮
        	$("#check_cleanBtn").click(function() {
        		_tip_check(3);
        	});
        	//清除按钮
        	$("#payment_cleanBtn").click(function() {
        		_tip_check(4);
        	});
        	//清除按钮
        	$("#app_cleanBtn").click(function() {
        		_tip_check(5);
        	});
        	//清除按钮
        	$("#confirm_cleanBtn").click(function() {
        		_tip_check(6);
        	});
        	
        	
        	//赊购挂账-挂账
        	$("#loan_saveBtn").click(function() {
        		$("#loanForm").attr('target', '_self');
                $("#loanForm").attr("action", "${sc_ctx}/supplierSignRun/defaultSave");
                $("#loanForm").submit();
            });
            
            
            //=======================================================================
            $("#noticeForm").validate({
                rules : {
                	checkNoticeDate : {
                        required : true,
                        date : true
                    },
                    noticeMode : {
                        required : true
                    },
                    descTxt : {
                        maxlength : 255
                    }
                }
            });
            $("#notice_saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#noticeForm").attr('target', '_self');
                $("#noticeForm").attr("action", "${sc_ctx}/supplierSignRun/defaultSave");
                $("#noticeForm").submit();
            });
            
          	//=======================================================================
            $("#checkForm").validate({
                rules : {
                	checkDate : {
                        required : true,
                        date : true
                    },
                    checkAmt : {
                        required : true,
                        money : true
                    },
                    descTxt : {
                        maxlength : 255
                    }
                }
            });
            $("#check_saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#checkForm").attr('target', '_self');
                $("#checkForm").attr("action", "${sc_ctx}/supplierSignRun/defaultSave");
                $("#checkForm").submit();
            });
            
          	//=======================================================================
            $("#paymentForm").validate({
                rules : {
                	paymentDate : {
                        required : true,
                        date : true
                    },
                    paymentAmt : {
                        required : true,
                        money : true
                    },
                    descTxt : {
                        maxlength : 255
                    }
                }
            });
            $("#payment_saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#paymentForm").attr('target', '_self');
                $("#paymentForm").attr("action", "${sc_ctx}/supplierSignRun/defaultSave");
                $("#paymentForm").submit();
            });
            
          	//=======================================================================
            $("#appForm").validate({
                rules : {
                	appDate : {
                        required : true,
                        date : true
                    },
                    appAmt : {
                        required : true,
                        money : true
                    },
                    descTxt : {
                        maxlength : 255
                    }
                }
            });
            $("#app_saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#appForm").attr('target', '_self');
                $("#appForm").attr("action", "${sc_ctx}/supplierSignRun/defaultSave");
                $("#appForm").submit();
            });
            
          	//=======================================================================
            $("#confirmForm").validate({
                rules : {
                	confirmDate : {
                        required : true,
                        date : true
                    },
                    confirmAmt : {
                        required : true,
                        money : true
                    },
                    descTxt : {
                        maxlength : 255
                    }
                }
            });
            $("#confirm_saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#confirmForm").attr('target', '_self');
                $("#confirmForm").attr("action", "${sc_ctx}/supplierSignRun/defaultSave");
                $("#confirmForm").submit();
            });
        });
        
        function _tip_check(runType){
        	if(runType == 1){// 赊购挂账
        		if ($("#loanForm_uuid").val() == ''){
        			$('#_tip_modal_2').modal('show');
        		} else {
        			$("#loanForm").attr('target', '_self');
        			$("#loanForm").attr("action", "${sc_ctx}/supplierSignRun/clean");
                    $("#loanForm").submit();
        		}
        	} else if(runType == 2){// 对账通知
        		if ($("#noticeForm_uuid").val() == ''){
        			$('#_tip_modal_1').modal('show');
        		} else {
        			$("#noticeForm").attr('target', '_self');
        			$("#noticeForm").attr("action", "${sc_ctx}/supplierSignRun/clean");
                    $("#noticeForm").submit();
        		}
        	} else if(runType == 3){// 对账完成
        		if ($("#check_uuid").val() == ''){
        			$('#_tip_modal_1').modal('show');
        		} else {
        			$("#checkForm").attr('target', '_self');
        			$("#checkForm").attr("action", "${sc_ctx}/supplierSignRun/clean");
                    $("#checkForm").submit();
        		}
        	} else if(runType == 4){// 结算付款
        		if ($("#payment_uuid").val() == ''){
        			$('#_tip_modal_1').modal('show');
        		} else {
        			$("#paymentForm").attr('target', '_self');
        			$("#paymentForm").attr("action", "${sc_ctx}/supplierSignRun/clean");
                    $("#paymentForm").submit();
        		}
        	} else if(runType == 5){// 退货申请
        		if ($("#app_uuid").val() == ''){
        			$('#_tip_modal_1').modal('show');
        		} else {
        			$("#appForm").attr('target', '_self');
        			$("#appForm").attr("action", "${sc_ctx}/supplierSignRun/clean");
                    $("#appForm").submit();
        		}
        	} else if(runType == 6){// 退货确认
        		if ($("#confirm_uuid").val() == ''){
        			$('#_tip_modal_1').modal('show');
        		} else {
        			$("#confirmForm").attr('target', '_self');
        			$("#confirmForm").attr("action", "${sc_ctx}/supplierSignRun/clean");
                    $("#confirmForm").submit();
        		}
        	}
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
                 		<h3>供应商(挂账)结算进度 编辑</h3>
               		</legend>
              	</div>
	      		<div class="span12"	style="margin-top: 20px;">
		      		<form id="tmp1Form" class="form-horizontal">
		            	<div class="control-group">
		           			<label class="control-label">供应商 :</label>
		                	<div class="controls">
		                    	<label class="left-control-label">${supName}</label>
		                 	</div>
		               	</div>
		              	<div class="control-group">
		                    <label class="control-label">年份 :</label>
		                 	<div class="controls">
		                    	<label class="left-control-label">${optDateY}</label>
		                 	</div>
		              	</div>
		              	<div class="control-group">
		                    <label class="control-label">月份 :</label>
		                 	<div class="controls">
		                    	<select id="optDateM" name="optDateM" class="input-small">
		                    		<option value="00"></option>
		                    		<option value="01">01</option>
		                    		<option value="02">02</option>
		                    		<option value="03">03</option>
		                    		<option value="04">04</option>
		                    		<option value="05">05</option>
		                    		<option value="06">06</option>
		                    		<option value="07">07</option>
		                    		<option value="08">08</option>
		                    		<option value="09">09</option>
		                    		<option value="10">10</option>
		                    		<option value="11">11</option>
		                    		<option value="12">12</option>
		                    	</select>
		                 	</div>		                 	
		              	</div>
		  			</form>
		  			<div id="tabDiv">
		  			<ul id="myTab" class="nav nav-tabs">
		              <li><a href="#loan" data-toggle="tab">赊购挂账</a></li>
		              <li><a href="#notice" data-toggle="tab">对账通知</a></li>
					  <li><a href="#check" data-toggle="tab">对账完成</a></li>
					  <li><a href="#payment" data-toggle="tab">结算付款</a></li>
					  <li><a href="#app" data-toggle="tab">退货申请</a></li>
					  <li><a href="#confirm" data-toggle="tab">退货确认</a></li>
            		</ul>
            		
            		
            		<div id="myTabContent" class="tab-content">
				  		<div class="tab-pane" id="loan">
					  		<form id="loanForm" class="form-horizontal" action="" method="POST">
					  			<input type="hidden" name="supplierBwId" value="${supId}">
				  				<input type="hidden" name="optDateY" value="${optDateY}">
				  				<input type="hidden" name="optDateM" id="loanForm_optDateM" value="">
				  				<input type="hidden" name="runType" value="1">
				  				<input type="hidden" name="_uuid" id="loanForm_uuid" value="">
					  			<label class="my-control-label"><h3 id="loan_txt"></h3></label>
					  			<br>
					  			<input type="button" id="noLoan_saveBtn" class="btn btn-large btn-primary" value="不挂" />&nbsp;
								<button id="loan_saveBtn" class="btn btn-large btn-warning">挂账</button>&nbsp;
								<a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
							</form>
				  		</div>
				  		
				  		
				  		<div class="tab-pane" id="notice">
				  			<form id="noticeForm" class="form-horizontal" action="" method="POST">
				  				<input type="hidden" name="supplierBwId" value="${supId}">
				  				<input type="hidden" name="optDateY" value="${optDateY}">
				  				<input type="hidden" name="optDateM" id="noticeForm_optDateM" value="">
				  				<input type="hidden" name="runType" value="2">
				  				<input type="hidden" name="_uuid" id="noticeForm_uuid" value="">
					  			<div class="control-group">
	                            	<label class="control-label">通知时间 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="checkNoticeDate" id="checkNoticeDate">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">通知方式  :</label>
	                            	<div class="controls">
			                         	<input type="radio" name="noticeMode" id="noticeMode1" value="1">
			  							电话
			  							<input type="radio" name="noticeMode" id="noticeMode2" value="2">
			  							网络
			  							<input type="radio" name="noticeMode" id="noticeMode3" value="3">
			  							捎信
		                            	<label for="noticeMode" class="error" style="display: none;">请填写此字段</label>
		                            </div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">备注  :</label>
	                            	<div class="controls">
		                                <textarea id="notice_descTxt" name="descTxt" class="input-xlarge" rows="4"></textarea>
		                            </div>
	                        	</div>
	                        	<div class="control-group">
		                            <div class="controls">
		                                <button	id="notice_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>&nbsp;
		                                <input type="button" id="notice_cleanBtn" class="btn btn-large btn-danger" value="清除" />&nbsp;
		                                <a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
							</form>
				  		</div>
				  		
				  		
				  		<div class="tab-pane" id="check">
				  			<form id="checkForm" class="form-horizontal" action="" method="POST">
				  				<input type="hidden" name="supplierBwId" value="${supId}">
				  				<input type="hidden" name="optDateY" value="${optDateY}">
				  				<input type="hidden" name="optDateM" id="check_optDateM" value="">
				  				<input type="hidden" name="runType" value="3">
				  				<input type="hidden" name="_uuid" id="check_uuid" value="">
				  				<div class="control-group">
	                            	<label class="control-label">对账时间 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="checkDate" id="checkDate">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">对账金额 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="checkAmt" id="checkAmt">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">备注  :</label>
	                            	<div class="controls">
		                                <textarea id="check_descTxt" name="descTxt" class="input-xlarge" rows="4"></textarea>
		                            </div>
	                        	</div>
	                        	<div class="control-group">
		                            <div class="controls">
		                                <button	id="check_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>&nbsp;
		                                <input type="button" id="check_cleanBtn" class="btn btn-large btn-danger" value="清除" />&nbsp;
		                                <a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
				  			</form>
						</div>
						
						
				  		<div class="tab-pane" id="payment">
				  			<form id="paymentForm" class="form-horizontal" action="" method="POST">
				  				<input type="hidden" name="supplierBwId" value="${supId}">
				  				<input type="hidden" name="optDateY" value="${optDateY}">
				  				<input type="hidden" name="optDateM" id="payment_optDateM" value="">
				  				<input type="hidden" name="runType" value="4">
				  				<input type="hidden" name="_uuid" id="payment_uuid" value="">
				  				<div class="control-group">
	                            	<label class="control-label">付款时间 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="paymentDate" id="paymentDate">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">付款金额 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="paymentAmt" id="paymentAmt">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">备注  :</label>
	                            	<div class="controls">
		                                <textarea id="payment_descTxt" name="descTxt" class="input-xlarge" rows="4"></textarea>
		                            </div>
	                        	</div>
	                        	<div class="control-group">
		                            <div class="controls">
		                                <button	id="payment_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>&nbsp;
		                                <input type="button" id="payment_cleanBtn" class="btn btn-large btn-danger" value="清除" />&nbsp;
		                                <a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
				  			</form>
				  		</div>
				  		
				  		
				  		<div class="tab-pane" id="app">
				  			<form id="appForm" class="form-horizontal" action="" method="POST">
				  				<input type="hidden" name="supplierBwId" value="${supId}">
				  				<input type="hidden" name="optDateY" value="${optDateY}">
				  				<input type="hidden" name="optDateM" id="app_optDateM" value="">
				  				<input type="hidden" name="runType" value="5">
				  				<input type="hidden" name="_uuid" id="app_uuid" value="">
				  				<div class="control-group">
	                            	<label class="control-label">申请时间 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="appDate" id="appDate">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">申请金额 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="appAmt" id="appAmt">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">备注  :</label>
	                            	<div class="controls">
		                                <textarea id="app_descTxt" name="descTxt" class="input-xlarge" rows="4"></textarea>
		                            </div>
	                        	</div>
	                        	<div class="control-group">
		                            <div class="controls">
		                                <button	id="app_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>&nbsp;
		                                <input type="button" id="app_cleanBtn" class="btn btn-large btn-danger" value="清除" />&nbsp;
		                                <a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
				  			</form>
				  		</div>
				  
				  		<div class="tab-pane" id="confirm">
				  			<form id="confirmForm" class="form-horizontal" action="" method="POST">
				  				<input type="hidden" name="supplierBwId" value="${supId}">
				  				<input type="hidden" name="optDateY" value="${optDateY}">
				  				<input type="hidden" name="optDateM" id="confirm_optDateM" value="">
				  				<input type="hidden" name="runType" value="6">
				  				<input type="hidden" name="_uuid" id="confirm_uuid" value="">
				  				<div class="control-group">
	                            	<label class="control-label">确认时间 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="confirmDate" id="confirmDate">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">确认金额 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="confirmAmt" id="confirmAmt">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">备注  :</label>
	                            	<div class="controls">
		                                <textarea id="confirm_descTxt" name="descTxt" class="input-xlarge" rows="4"></textarea>
		                            </div>
	                        	</div>
	                        	<div class="control-group">
		                            <div class="controls">
		                                <button	id="confirm_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>&nbsp;
		                                <input type="button" id="confirm_cleanBtn" class="btn btn-large btn-danger" value="清除" />&nbsp;
		                                <a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
				  			</form>
				  		</div>
  					</div>
            		</div>
	           	</div>
       		</div>
        </div>
        
        
        <div id="_tip_modal_1" class="modal hide fade __model37">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h3>系统消息</h3>
		  </div>
		  <div class="modal-body">
		  	<center>
	            <p class="error">
	                无相关数据, 不需要清除! 
	            </p>
        	</center>
		  </div>
		  <div class="modal-footer">
		    <a href="#" class="btn" data-dismiss="modal">关闭</a>
		  </div>
		</div>
		
		<div id="_tip_modal_2" class="modal hide fade __model37">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h3>系统消息</h3>
		  </div>
		  <div class="modal-body">
		  	<center>
	            <p class="error">
	                无赊购挂账, 不需要解挂! 
	            </p>
        	</center>
		  </div>
		  <div class="modal-footer">
		    <a href="#" class="btn" data-dismiss="modal">关闭</a>
		  </div>
		</div>
    </body>
</html>