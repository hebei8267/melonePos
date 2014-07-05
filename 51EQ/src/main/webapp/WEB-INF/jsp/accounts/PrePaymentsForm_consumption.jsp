<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
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
			$().ready(function() {
				$('#optDateShow').datepicker({
                    format : 'yyyy-mm-dd'
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
                        phoneNum : {
                            required : true,
                            isPhone : true
                        },
                        name : {
                            required : true,
                            maxlength : 32
                        },
                        amt : {
                            required : true,
                            money : true
                        }
                    }
                });
                
				$("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

					$("#inputForm").attr('target', '_self');
                    $("#inputForm").attr("action", "${sc_ctx}/prePayments/save");
                    $("#inputForm").submit();
                });
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />
		<input type="hidden" id="_tomorrow_date" value="<%=DateUtils.getNextDateFormatDate(1, "yyyy-MM-dd")%>">
		
		<div class="container">
            <div class="row">
                <div class="span12">
                    <legend>
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName} 顾客/会员预付款<span class="_warn1">(消费)</span>信息
                        <c:if test="${empty	prePayments.uuid}">
                            新增
                        </c:if>
                        </h3>
                    </legend>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="prePayments">
                        <%//使用方式(1充值、2消费) %>
                        <input id="inOutFlg" name="inOutFlg" type="hidden" value="2"/>
                        <input type="hidden" id="orgId" name="orgId" value="${sessionScope.__SESSION_USER_INFO.orgId}">
                        
                        <div class="control-group">
                            <label class="control-label">消费日期 :</label>
                            <div class="controls">
                          		<form:input	path="optDateShow" />
                          	</div>
                        </div>
                        <div class="control-group">
                        	<label class="control-label">上班类型 :</label>
                        	<div class="controls">
                        		<form:select path="jobType" items="${jobTypeList}"/>
                      		</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">(顾客)电话号码 :</label>
                            <div class="controls">
                          		<form:input	path="phoneNum" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">(顾客)会员名称 :</label>
                            <div class="controls">
                          		<form:input	path="name" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">金额 :</label>
                            <div class="controls">
                          		<form:input	path="amt" class="input-small"/>
                          	</div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/prePayments/list" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
	    </div>
	</body>
</html>