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
        </style>
        <script>
        $(function() {
        	$('#optDate').datepicker({
                format : 'yyyy-mm-dd'
            });
        	
        	$("#inputForm").validate({
                rules : {
                	optDate : {
                        required : true,
                        date : true
                    },
                    inAmtDesc : {
                    	maxlength : 255
                    },
                    inAmt : {
                    	required : true,
                        money : true
                    },
                    outAmt : {
                    	required : true,
                        money : true
                    },
                    outAmtLClass : {
                    	maxlength : 255
                    },
                    outAmtSClass : {
                    	maxlength : 255
                    },
                    descTxt : {
                    	maxlength : 255
                    }
                }
            });
        	
        	$("#saveBtn").click(function() {
                $("input[type='text'],textarea").each(function(i) {
                    this.value = $.trim(this.value);
                });

				$("#inputForm").attr("target", "_self");
                $("#inputForm").attr("action", "${sc_ctx}/accountFlow/save");
                $("#inputForm").submit();
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
                        <h3>会计[支出]记账信息</h3>
                    </legend>
          		</div>
          		
          		<div class="span6"	style="margin-top: 10px;">
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
                            <label class="left-control-label" style="color:#F89406;">${accountFlow.outAmt} 元</label>
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
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">记账</button>
                                &nbsp;<a href="${sc_ctx}/accountFlow/list" class="btn btn-large">返回</a>
                            </div>
                        </div>
              		</form:form>
            	</div>
            	
            	<div class="span6" style="padding-top: 10px;">
            		<table class="table	table-striped table-bordered table-condensed mytable">
	                    <thead>
	                        <tr>
	                            <th width="55" class="center">
	                                序号
	                            </th>
	                            <th class="center">
	                                记账科目
	                            </th>
	                            <th	class="center" width="175">
	                                金额
	                            </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    	<c:forEach items="${accountFlowSplitList}" var="accountFlowSplit" varStatus="status">
	                    	<tr>
	                    		<td>${status.index + 1}</td>
	                    		<td></td>
	                    		<td></td>
	                    	</tr>
	                    	</c:forEach>
	                    </tbody>
	            	</table>
            	</div>
      		</div>
   		</div>
	</body>
</html>