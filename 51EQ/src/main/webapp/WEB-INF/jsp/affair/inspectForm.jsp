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
    		.left-control-label {
				padding-left: 5px;
			}
	    	.form-horizontal .control-label_1 {
				float: left;
				width: 200px;
				padding-top: 5px;
				text-align: left;
				color: #F89406;
				font-weight:bold;
			}
			.form-horizontal .control-label {
	        	width: 180px;
	    	}
	    	.form-horizontal .controls {
	        	margin-left: 195px;
	    	}
    	</style>
    	<script>
    	 $(function() {
    		 $('#optDateShow').datepicker({
                 format : 'yyyy-mm-dd'
             });
    		 
   			$("#inputForm").validate({
                 rules : {
                	 trsId : {
                         required : true,
                         maxlength : 16
                     },
                     optDateShow : {
                         required : true,
                         date : true
                     },
                     orgId : {
                         required : true
                     },
                     optTime : {
                         required : true
                     },
                     jobType : {
                         required : true
                     },
                     dutyPer : {
                         required : true,
                         maxlength : 16
                     },
                     initAmt : {
                    	 required : true,
                         money : true
                     },
                     cashAmt : {
                    	 required : true,
                         money : true
                     },
                     depositAmt : {
                         required : true,
                         money : true
                     },
                     saleCashAmt : {
                    	 required : true,
                         money : true
                     },
                     cashConclusion : {
                    	 required : true
                     },
                     cashConclusionTxt : {
                    	 maxlength : 255
                     },
                     imprestCalance : {
                    	 required : true,
                         money : true
                     },
                     expImprestAmt : {
                    	 required : true,
                         money : true
                     },
                     clearImprestAmt : {
                    	 required : true,
                         money : true
                     },
                     imprestConclusion : {
                    	 required : true
                     },
                     imprestConclusionTxt : {
                    	 maxlength : 255
                     },
                     inspectPer : {
                         required : true,
                         maxlength : 16
                     }
                 }
     		});
    		 
    		$("#saveBtn").click(function() {
                 $("input[type='text'],textarea").each(function(i) {
                     this.value = $.trim(this.value);
                 });

				$("#inputForm").attr("target", "_self");
				$("#inputForm").attr("action", "${sc_ctx}/inspect/save");
				$("#inputForm").submit();
      		});
    		 
    		$("#initAmt").change(function() {
    			cashSubtotal();
    			adjustAmt();
        	});
    		$("#cashAmt").change(function() {
    			cashSubtotal();
    			adjustAmt();
         	});
    		$("#depositAmt").change(function() {
    			cashSubtotal();
    			adjustAmt();
        	});
    		$("#saleCashAmt").change(function() {
    			cashSubtotal();
    			adjustAmt();
         	});
    		
    		$("#imprestCalance").change(function() {
    			imprestSubtotal1();
    			imprestSubtotal2();
        	});
    		$("#expImprestAmt").change(function() {
    			imprestSubtotal1();
    			imprestSubtotal2();
        	});
    		$("#clearImprestAmt").change(function() {
    			imprestSubtotal1();
    			imprestSubtotal2();
        	});
    		
    		$("#trsId").blur(function() {
				if($("#trsId").val() == ""){
					return;
				}
				$("#trsId").val(addZero($("#trsId").val(), 8));
				
				var _val = $("#trsId").val();
				var _start = _val.length - 8;
                var _end = _val.length;
                                  	
               	$("#trsId").val(_val.substring(_start, _end));
			});
  		});
    	function addZero(num, n) {
             if ((num + "").length >= n) return num;
             return addZero("0" + num, n);
        }
    	 
    	// A ～ C 合计金额
        function cashSubtotal() {
         	var _result = numAdd($("#initAmt").val(), $("#cashAmt").val());
         	var _result = numSub(_result, $("#depositAmt").val());

             $("#_cashSubtotal").html(_result + " 元");
             $("#cashSubtotal").val(_result);
        }
    	// A ～ C 合计 - D
        function adjustAmt() {
         	var _result = numSub($("#cashSubtotal").val(), $("#saleCashAmt").val());

 			$("#_adjustAmt").html(_result + " 元");
     		$("#adjustAmt").val(_result);
        }
    	// E 减 F 金额
        function imprestSubtotal1() {
          	var _result = numSub($("#imprestCalance").val(), $("#expImprestAmt").val());

 			$("#_imprestSubtotal1").html(_result + " 元");
            $("#imprestSubtotal1").val(_result);
        }
    	// E 减 F 减 G 金额
    	function imprestSubtotal2() {
    		var _result = numSub($("#imprestSubtotal1").val(), $("#clearImprestAmt").val());

 			$("#_imprestSubtotal2").html(_result + " 元");
            $("#imprestSubtotal2").val(_result);
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
                        <h3>门店巡查报告(财务)
                        <c:if test="${empty	inspect.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty inspect.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                	<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="inspect">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">报告流水号 :</label>
                            <c:if test="${empty	inspect.uuid}">
                                <div class="controls">
                                    <form:input	path="trsId" />
                                </div>
                            </c:if>
                            <c:if test="${!empty inspect.uuid}">
                            	<div class="controls">
                                <label class="left-control-label">${inspect.trsId}</label>
                                <form:hidden path="trsId"/>
                                </div>
                            </c:if>
                        </div>
                        <div class="control-group">
                            <label class="control-label">巡查日期 :</label>
                           	<div class="controls">
                          		<form:input	path="optDateShow" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">巡查门店 :</label>
                           	<div class="controls">
                           		<form:select path="orgId" items="${orgList}"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">巡查时间 :</label>
                           	<div class="controls">
                          		<form:input	path="optTime" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">当前班次 :</label>
                           	<div class="controls">
                           		<form:select path="jobType" items="${jobTypeList}"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">当班负责人 :</label>
                           	<div class="controls">
                          		<form:input	path="dutyPer" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label_1">营业留存现金检查部分</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">班前余额 A :</label>
                           	<div class="controls">
                          		<form:input	path="initAmt" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">百威现金销售额 B :</label>
                           	<div class="controls">
                          		<form:input	path="cashAmt" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"><span class="warn2_text">存款金额 C :</span></label>
                           	<div class="controls">
                          		<form:input	path="depositAmt" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">实点现金 D :</label>
                           	<div class="controls">
                          		<form:input	path="saleCashAmt" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">A ～ C 合计金额 :</label>
                           	<div class="controls">
                          		<label class="left-control-label" id="_cashSubtotal">${inspect.cashSubtotal} 元</label>
                            	<form:hidden path="cashSubtotal"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">合计金额减 D :</label>
                           	<div class="controls">
                           		<label class="left-control-label" id="_adjustAmt">${inspect.adjustAmt} 元</label>
                            	<form:hidden path="adjustAmt"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">小结结论 :</label>
                           	<div class="controls">
                           		<form:select path="cashConclusion" items="${conclusionList}"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注 :</label>
                            <div class="controls">
                                <form:textarea path="cashConclusionTxt" class="input-xlarge" rows="4"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label_1">备用金检查部分</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备用金日记账余额 E :</label>
                            <div class="controls">
                                <form:input path="imprestCalance" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">已报销未记账的支出 F :</label>
                            <div class="controls">
                                <form:input path="expImprestAmt" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">实际清点的备用金额 G :</label>
                            <div class="controls">
                                <form:input path="clearImprestAmt" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">E 减 F 金额 :</label>
                           	<div class="controls">
                          		<label class="left-control-label" id="_imprestSubtotal1">${inspect.imprestSubtotal1} 元</label>
                            	<form:hidden path="imprestSubtotal1"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">E 减 F 减 G 金额 :</label>
                           	<div class="controls">
                          		<label class="left-control-label" id="_imprestSubtotal2">${inspect.imprestSubtotal2} 元</label>
                            	<form:hidden path="imprestSubtotal2"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">小结结论 :</label>
                           	<div class="controls">
                           		<form:select path="imprestConclusion" items="${conclusionList}"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注 :</label>
                            <div class="controls">
                                <form:textarea path="imprestConclusionTxt" class="input-xlarge" rows="4"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">巡查人 :</label>
                           	<div class="controls">
                          		<form:input	path="inspectPer" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/inspect" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>