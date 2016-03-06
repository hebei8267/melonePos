<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<%@ page import="com.tjhx.globals.Constants" %>
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
        </style>
        <script>
        function clean(index) {
        	$("#_subId" + index).val("");
          	$("#_amt" + index).val("");
          	reCal();
        }
        function reCal(){
        	var _total = 0;
    		$(".amt").each(function(){
    			var _val = $(this).val();
    			if(/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/.test(_val)){
    				_total = numAdd(_total, _val);
    			}
    		});
    		
    		$('#_total').text(_total);
    		$('#_total_Cal').val(_total);
        }
        $(function() {
        	$('#optDate').datepicker({
                format : 'yyyy-mm-dd'
            });
        	
        	$(".amt").change(function() {
        		reCal();
            });
        	$("#saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#inputForm").attr("target", "_self");
                $("#inputForm").attr("action", "${sc_ctx}/accountFlow/splitSave");
                $("#inputForm").submit();
            });
        	
        	//-----------------------------------
            // 表单效验
            //-----------------------------------
            $("#inputForm").validate({
                rules : {
                	"saveBtn" : {
                		myEqualAmt : true
                    },
                	"subId0" : {
                		requiredAmt : "_amt0"
                    },
                    "subId1" : {
                    	requiredAmt : "_amt1"
                    },
                    "subId2" : {
                    	requiredAmt : "_amt2"
                    },
                    "subId3" : {
                    	requiredAmt : "_amt3"
                    },
                    "subId4" : {
                    	requiredAmt : "_amt4"
                    },
                    "subId5" : {
                    	requiredAmt : "_amt5"
                    },
                    "subId6" : {
                    	requiredAmt : "_amt6"
                    },
                    "subId7" : {
                    	requiredAmt : "_amt7"
                    },
                    "subId8" : {
                    	requiredAmt : "_amt8"
                    },
                    "subId9" : {
                    	requiredAmt : "_amt9"
                    },
                	"amt0" : {
                		requiredSubject : "_subId0",
                    	money : true
                    },
                    "amt1" : {
                    	requiredSubject : "_subId1",
                    	money : true
                    },
                    "amt2" : {
                    	requiredSubject : "_subId2",
                    	money : true
                    },
                    "amt3" : {
                    	requiredSubject : "_subId3",
                    	money : true
                    },
                    "amt4" : {
                    	requiredSubject : "_subId4",
                    	money : true
                    },
                    "amt5" : {
                    	requiredSubject : "_subId5",
                    	money : true
                    },
                    "amt6" : {
                    	requiredSubject : "_subId6",
                    	money : true
                    },
                    "amt7" : {
                    	requiredSubject : "_subId7",
                    	money : true
                    },
                    "amt8" : {
                    	requiredSubject : "_subId8",
                    	money : true
                    },
                    "amt9" : {
                    	requiredSubject : "_subId9",
                    	money : true
                    },
                	"orgId0" : {
                		requiredSubject : "_subId0"
                    },
                    "orgId1" : {
                    	requiredSubject : "_subId1"
                    },
                    "orgId2" : {
                    	requiredSubject : "_subId2"
                    },
                    "orgId3" : {
                    	requiredSubject : "_subId3"
                    },
                    "orgId4" : {
                    	requiredSubject : "_subId4"
                    },
                    "orgId5" : {
                    	requiredSubject : "_subId5"
                    },
                    "orgId6" : {
                    	requiredSubject : "_subId6"
                    },
                    "orgId7" : {
                    	requiredSubject : "_subId7"
                    },
                    "orgId8" : {
                    	requiredSubject : "_subId8"
                    },
                    "orgId9" : {
                    	requiredSubject : "_subId9"
                    }
                    
                }
            });
        });
        
        jQuery.validator.addMethod("requiredSubject", function(value, element, param) {
        	if(value != ""){
        		return ($("#"+ param +"").val() != "");
        	}
    		return true;
       	}, "请选择记账科目");
        
        jQuery.validator.addMethod("requiredAmt", function(value, element, param) {
        	if(value != ""){
        		return ($("#"+ param +"").val() != "");
        	}
    		return true;
       	}, "请输入正确的金额");
        
        jQuery.validator.addMethod("myEqualAmt", function(value, element, param) {
    		return numAdd($("#_total_Actual").val(),0) == numAdd($('#_total_Cal').val(),0);
       	}, "请输入正确的金额(合计)");
 		</script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        
		<div class="container">
            <div class="row">
           		<div class="span12">
                    <legend>
                        <h3>会计[支出]记账信息</h3>
                    </legend>
          		</div>
          		
          		<div class="span5"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" modelAttribute="accountFlow">
                    	<form:hidden path="uuid"/>
                    	
                    	<div class="control-group">
                            <label class="control-label">日期 :</label>
                            <label class="left-control-label">${accountFlow.optDate}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">拨入来源 :</label>
                            <label class="left-control-label">${accountFlow.inAmtDesc}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">拨入金额 :</label>
                            <label class="left-control-label">${accountFlow.inAmt} 元</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出金额 :</label>
                            <label class="left-control-label" style="color:red;">${accountFlow.outAmt} 元</label>
                            <input type="hidden" id="_total_Actual" value="${accountFlow.outAmt}">
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出大类 :</label>
                            <label class="left-control-label">${accountFlow.outAmtLClass}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出细类 :</label>
                            <label class="left-control-label">${accountFlow.outAmtSClass}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注 :</label>
                            <label class="left-control-label">${accountFlow.descTxt}</label>
                        </div>
              		</form:form>
            	</div>
            	
            	<div class="span7" style="padding-top: 10px;">
            		<form method="POST" id="inputForm">
            		<input type="hidden" name="accountFlowUuid" value="${accountFlow.uuid}">
            		<table class="table	table-striped table-bordered table-condensed mytable">
	                    <thead>
	                        <tr>
	                            <th width="55" class="center">
	                                序号
	                            </th>
	                            <th class="center">
	                                记账科目
	                            </th>
	                            <th class="center">
	                                记账机构
	                            </th>
	                            <th	class="center" width="150">
	                                金额
	                            </th>
	                            <th></th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    	<c:set var="_total" value="0"/>
	                    	<c:forEach items="${splitList}" var="split" varStatus="status">
	                    	<c:set var="_total" value="${_total+split.amt}"/>
	                    	<tr>
	                    		<td class="center">${status.index + 1}</td>
	                    		
	                    		<td class="center">
	                    			<select class="layout-option form-control mini_select" name="subId${status.index}" id="_subId${status.index}">
										<c:if test="${split.subId == null}">
										<option value="" selected><%=Constants.BLANK_SELECT_TEXT %></option>
										</c:if>
										<c:if test="${split.subId != null}">
										<option value=""><%=Constants.BLANK_SELECT_TEXT %></option>
										</c:if>
										
										<c:forEach items="${subList}" var="sub">
											<c:if test="${split.subId == sub.subId}">
											<option value="${sub.subId}" selected>${sub.subName}</option>
											</c:if>
											
											<c:if test="${split.subId != sub.subId}">
											<option value="${sub.subId}">${sub.subName}</option>
											</c:if>
										</c:forEach>
									</select>
	                    		</td>
	                    		
	                    		<td class="center">
	                    			<select class="layout-option form-control mini_select" name="orgId${status.index}" id="_orgId${status.index}">
	                    				<c:if test="${split.orgId == null}">
										<option value="" selected><%=Constants.BLANK_SELECT_TEXT %></option>
										</c:if>
										<c:if test="${split.orgId != null}">
										<option value=""><%=Constants.BLANK_SELECT_TEXT %></option>
										</c:if>
										
										<c:forEach items="${orgList}" var="org">
											<c:if test="${split.orgId == org.key}">
											<option value="${org.key}" selected>${org.value}</option>
											</c:if>
											
											<c:if test="${split.orgId != org.key}">
											<option value="${org.key}">${org.value}</option>
											</c:if>
										</c:forEach>
	                    			</select>
	                    		</td>
	                    		
	                    		<td class="center">
	                    			<input class="mini_text amt" type="text" name="amt${status.index}" id="_amt${status.index}" value="${split.amt}"/> 元
	                    		</td>
	                    		<td class="center">
	                    			<input type="button" class="btn btn-warning" onclick="clean('${status.index}')" value="清空">
	                    		</td>
	                    	</tr>
	                    	</c:forEach>
	                    	<tr>
		                    	<td colspan="2" class="right">合计
		                    	</td>
		                    	<td colspan="2" class="left">
		                    		<span id="_total" style="color:red">${_total}</span> 元
		                    		<input type="hidden" id="_total_Cal" value="${_total}">
		                    	</td>
	                    	</tr>
	                    	<tr>
	                    		<td colspan="4" class="right">
		                    		<input type="button" id="saveBtn" name="saveBtn" class="btn	btn-large btn-primary" value="记账">
	                                &nbsp;<a href="${sc_ctx}/accountFlow/list" class="btn btn-large">返回</a>
	                    		</td>
	                    	</tr>
	                    </tbody>
	            	</table>
	            	
	            	</form>
            	</div>
      		</div>
   		</div>
	</body>
</html>