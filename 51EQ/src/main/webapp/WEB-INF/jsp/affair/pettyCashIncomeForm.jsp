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
            ._warn1 {
				color: #FF6633;
				padding: 5px;
			}
        </style>
        <script>
        	$(function() {
	        	$('#optDateShow').datepicker({
	                format : 'yyyy-mm-dd'
	            });
	        	
	        	$("#inputForm").validate({
                    rules : {
                    	optDateShow : {
                            required : true,
                            date : true,
                            dateGreaterThan : '${pettyCash.editDate}',
                            dateLessThan : $("#_tomorrow_date").val()
                        },
                        incomeSource : {
                            required : true
                        },
                        optAmtShow : {
                            required : true,
                            negativeMoney : true
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

                    $("#inputForm").attr("action", "${sc_ctx}/pettyCash/save");
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
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName} 门店备用金<span class="_warn1">(拨入)</span>信息
                        <c:if test="${empty	pettyCash.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty pettyCash.uuid}">
                            编辑
                        </c:if>
                        </h3>
                    </legend>
                </div>
                
                <input type="hidden" id="_tomorrow_date" value="<%=DateUtils.getNextDateFormatDate(1, "yyyy-MM-dd")%>">
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="pettyCash">
                        <form:hidden path="uuid"/>
                        <%//操作类型 0-支出 1-拨入 %>
                        <input id="optType" name="optType" type="hidden" value="1"/>
                        
                        <div class="control-group">
                            <label class="control-label">拨款日期 :</label>
                            <div class="controls">
                          		<form:input	path="optDateShow" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">拨款来源 :</label>
                            <div class="controls">
                          		<select id="incomeSource" name="incomeSource" class="input-medium">
	                          		<c:forEach items="${incomeSourceList}" var="income">
	                            		<c:if test="${pettyCash.incomeSource.equals(income.key)}">
	                            			<option value="${income.key }" selected>${income.value }</option>
	                            		</c:if>
	                            		<c:if test="${!pettyCash.incomeSource.equals(income.key)}">
	                                   		<option value="${income.key }">${income.value }</option>
	                                   	</c:if>
	                         		</c:forEach>
	                        	</select>
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">拨入金额 :</label>
                            <div class="controls">
                          		<form:input	path="optAmtShow" /> 元
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
                                &nbsp;<a href="${sc_ctx}/pettyCash" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
	    </div>
	</body>
</html>