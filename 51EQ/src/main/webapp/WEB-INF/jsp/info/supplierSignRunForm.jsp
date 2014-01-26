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
    	</style>
        <script>
        $(function() {
        	$('a[data-toggle="tab"]').on('shown', function (e) {
        		//alert(e.currentTarget.hash)
        	});
        	
        	$('#myTab a:first').tab('show');
        	
        	//赊购挂账-不挂
        	$("#noLoan").click(function() {

                $("#loanForm").attr("action", "${sc_ctx}/supplierSignRun/noLoan");
                $("#loanForm").submit();
            });
        	
        	//赊购挂账-挂账
        	$("#loan").click(function() {

                $("#loanForm").attr("action", "${sc_ctx}/supplierSignRun/loan");
                $("#loanForm").submit();
            });
            
            $("#noticeForm").click(function() {//TODO ??????????????????? 效验

                $("#loanForm").attr("action", "${sc_ctx}/supplierSignRun/loan");
                $("#loanForm").submit();
            });
        });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
       		<div class="row">
        		<div class="span12">
          			<legend>
                 		<h3>挂账供应商结算进度 编辑</h3>
               		</legend>
              	</div>
                    
	      		<div class="span12"	style="margin-top: 10px;">
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
		                    	<select name="optDateM" class="input-small">
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
					  		<form id="loanForm" class="form-horizontal" action="">
					  			<br>
					  			<button id="noLoan" class="btn btn-large btn-success">不挂</button>&nbsp;
								<button id="loan" class="btn btn-large btn-warning">挂账</button>&nbsp;
								<a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
							</form>
				  		</div>
				  		
				  		
				  		<div class="tab-pane" id="notice">
				  			<form id="noticeForm" class="form-horizontal" action="">
					  			<div class="control-group">
	                            	<label class="control-label">通知时间 :</label>
	                            	<div class="controls">
	                            		<input type="text" name="checkNoticeDate" id="checkNoticeDate">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label">通知方式  :</label>
	                            	<div class="controls">
		                                <input type="radio" name="noticeMode" id="noticeMode1" value="1" >
		  								电话
		  								<input type="radio" name="noticeMode" id="noticeMode2" value="2">
		  								网络
		  								<input type="radio" name="noticeMode" id="noticeMode3" value="3">
		  								捎信
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
		                                <button	id="notice_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>
		                                &nbsp;<a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
							</form>
				  		</div>
				  		
				  		
				  		<div class="tab-pane" id="check">
				  			<form id="checkForm" class="form-horizontal" action="">
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
		                                <button	id="check_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>
		                                &nbsp;<a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
				  			</form>
						</div>
						
						
				  		<div class="tab-pane" id="payment">
				  			<form id="paymentForm" class="form-horizontal" action="">
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
		                                <button	id="payment_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>
		                                &nbsp;<a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
				  			</form>
				  		</div>
				  		
				  		
				  		<div class="tab-pane" id="app">
				  			<form id="appForm" class="form-horizontal" action="">
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
		                                <button	id="app_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>
		                                &nbsp;<a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
				  			</form>
				  		</div>
				  
				  		<div class="tab-pane" id="confirm">
				  			<form id="confirmForm" class="form-horizontal" action="">
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
		                                <button	id="confirm_saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>
		                                &nbsp;<a href="${sc_ctx}/supplierSignRun/init" class="btn btn-large">返回</a>
		                            </div>
                        		</div>
				  			</form>
				  		</div>
  					</div>
            		
	           	</div>
       		</div>
        </div>
    </body>
</html>