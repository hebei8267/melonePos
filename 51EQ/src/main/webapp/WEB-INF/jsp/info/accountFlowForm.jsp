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
                        <h3>会计记账信息
                        <c:if test="${empty	accountFlow.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty accountFlow.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
          		</div>
          		
          		<div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm" modelAttribute="accountFlow">
                    	<form:hidden path="uuid"/>
                    	
                    	<div class="control-group">
                            <label class="control-label">日期 :</label>
                            <div class="controls">
                          		<form:input	path="optDate" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">拨入来源 :</label>
                            <div class="controls">
                          		<form:input	path="inAmtDesc" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">拨入金额 :</label>
                            <div class="controls">
                          		<form:input	path="inAmt" /> 元
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出金额 :</label>
                            <div class="controls">
                          		<form:input	path="outAmt" /> 元
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出大类 :</label>
                            <div class="controls">
                          		<form:input	path="outAmtLClass" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出细类 :</label>
                            <div class="controls">
                          		<form:input	path="outAmtSClass" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注 :</label>
                            <div class="controls">
                          		<form:textarea path="descTxt" class="input-xlarge" rows="4"/>
                          	</div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/accountFlow/list" class="btn btn-large">返回</a>
                            </div>
                        </div>
              		</form:form>
            	</div>
      		</div>
   		</div>
	</body>
</html>