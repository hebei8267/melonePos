<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
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
            .mytable tbody tr td.right {
				padding-right: 10px;
			}
			.mytable tbody tr td {
				height: 40px;
				valign : middle;
			}
			select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input {
				margin-bottom: 0px;
			}
			.input-xlarge {
				width: 550px;
			}
        </style>
		<script>
			$().ready(function() {
				$("#inputForm").validate({
					rules: {
						name : {
                            required : true,
                            maxlength : 32
                        },
                        sex : {
                            required : true
                        },
                        idCardNo : {
                            required : true,
                            length18 : true
                        },
                        address : {
                            required : true,
                            maxlength : 64
                        },
                        phone : {
                            required : true,
                            isPhone : true,
                            maxlength : 16
                        },
                        emergencyContact : {
                            required : true,
                            maxlength : 32
                        },
                        emergencyPhone : {
                            required : true,
                            isPhone : true,
                            maxlength : 16
                        },
                        basicInfoDescTxt : {
                            maxlength : 1024
                        },
                        orgId : {
                            required : true
                        },
                        pos : {
                            maxlength : 16
                        },
                        entryTime : {
                        	required : true,
                            date : true
                        },
                        posTime : {
                            date : true
                        },
                        contractExpTime : {
                            date : true
                        },
                        renewTime : {
                            date : true
                        },
                        orgTransRecord : {
                            maxlength : 1024
                        },
                        posAdjustRecord : {
                            maxlength : 1024
                        }
					}
				});

				$('#startWorkTime').datepicker({
                    format : 'yyyy-mm',
                    viewMode : 1,
	            	minViewMode : 1
                });
                $('#entryTime').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#posTime').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#contractExpTime').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#renewTime').datepicker({
                    format : 'yyyy-mm-dd'
                });
				

				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});

					$("#inputForm").attr("action", "${sc_ctx}/employee2/save");
					$("#inputForm").submit();
				});
				
				$("#photoFile").change(function(){
					var objUrl = getObjectURL(this.files[0]);
					if (objUrl) {
						$("#photoImg").attr("src", objUrl) ;
					}
				});
			});
		</script>
		<script>
		//建立一個可存取到該file的url
		function getObjectURL(file) {
			var url = null ; 
			if (window.createObjectURL!=undefined) { // basic
				url = window.createObjectURL(file) ;
			} else if (window.URL!=undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file) ;
			} else if (window.webkitURL!=undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file) ;
			}
			return url ;
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
	                    <h3>员工信息
	                    <c:if test="${empty	employee.uuid}">
	                        新增
	                    </c:if>
	                    <c:if test="${!empty employee.uuid}">
	                        编辑
	                    </c:if></h3>
	                </legend>
                </div>
                
                <div class="span12">
                    <form:form method="POST" id="inputForm"	modelAttribute="employee2" enctype="multipart/form-data">
                        <form:hidden path="uuid"/>
                        <h4>基本信息</h4>
                        <table class="table table-bordered mytable">
                        	<tr>
                        		<td width="130" class="right">姓名 :</td>
                        		<td width="200">
	                                <div class="controls">
	                                    <form:input	path="name" />
	                                </div>
                        		</td>
                        		<td width="130" class="right">性别 :</td>
                        		<td width="240">
                        			<div class="controls">
                        				<form:select path="sex">
                        					<option value=""></option>
                        					<option value="1" <c:if test="${1 == employee2.sex}">selected</c:if>>男</option>
			                                <option value="2" <c:if test="${2 == employee2.sex}">selected</c:if>>女</option>
                        				</form:select>
	                                </div>
                        		</td>
                        		<td rowspan="6" class="center">
                        			<img src="${ctx}/imgservlet?FILE_PATH=${employee2.photoUrl}" id="photoImg" height="600" width="220"/>
                        			<input type="file" name="photoFile" id="photoFile" />
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">身份证号 :</td>
                        		<td>
                        			<div class="controls">
	                                    <form:input	path="idCardNo" />
	                                </div>
                        		</td>
                        		<td class="right">婚姻状况 :</td>
                        		<td>
                        			<div class="controls">
	                                    <form:select path="maritalStatus">
                        					<option value=""></option>
                        					<option value="0" <c:if test="${0 == employee2.maritalStatus}">selected</c:if>>未婚</option>
                        					<option value="1" <c:if test="${1 == employee2.maritalStatus}">selected</c:if>>已婚</option>
                        				</form:select>
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">学历 :</td>
                        		<td>
	                        		<div class="controls">
	                        			<form:select path="education">
                        					<option value=""></option>
                        					<option value="1" <c:if test="${1 == employee2.education}">selected</c:if>>小学</option>
                        					<option value="2" <c:if test="${2 == employee2.education}">selected</c:if>>初中</option>
                        					<option value="3" <c:if test="${3 == employee2.education}">selected</c:if>>高中</option>
                        					<option value="4" <c:if test="${4 == employee2.education}">selected</c:if>>大学</option>
                        				</form:select>
	                                </div>
                        		</td>
                        		<td class="right">专业 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="professional" />
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">参加工作时间 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="startWorkTime" />
	                                </div>
                        		</td>
                        		<td class="right">户口所在地 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="accountLocal" />
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">联系电话 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="phone" />
	                                </div>
                        		</td>
                        		<td class="right">常住地址 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="address" />
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">紧急联系电话 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="emergencyPhone" />
	                                </div>
                        		</td>
                        		<td class="right">紧急联系人 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="emergencyContact" />
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">备注 :</td>
                        		<td colspan=4>
	                        		<div class="controls">
	                                    <form:textarea	path="basicInfoDescTxt" class="input-xlarge" rows="6"/>
	                                </div>
                        		</td>
                        	</tr>
                        </table>
                        
                        <h4>入职信息</h4>
                        <table class="table table-bordered mytable">
                        	<tr>
                        		<td width="130" class="right">所属机构 :</td>
                        		<td width="200">
	                                <div class="controls">
	                                	<select name="orgId">
				                            <c:forEach items="${orgList}" var="org">
				                                <c:if test="${org.key == employee2.orgId}">
				                                    <option value="${org.key }" selected>${org.value }</option>
				                                </c:if>
				                                <c:if test="${org.key != employee2.orgId}">
				                                    <option value="${org.key }">${org.value }</option>
				                                </c:if>
				                            </c:forEach>
				                        </select>
	                                </div>
                        		</td>
                        		<td width="130" class="right">担任职务 :</td>
                        		<td width="200">
	                                <div class="controls">
	                                    <form:input	path="pos" />
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">入职时间 :</td>
                        		<td>
                        			<div class="controls">
	                                    <form:input	path="entryTime" />
	                                </div>
                        		</td>
                        		<td class="right">转正时间 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="posTime" />
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">合同到期时间 :</td>
                        		<td>
	                        		<div class="controls">
	                                    <form:input	path="contractExpTime" />
	                                </div>
                        		</td>
                        		<td class="right">续签时间 :</td>
                        		<td>
	                                <div class="controls">
	                                    <form:input	path="renewTime" />
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">聘用形式 :</td>
                        		<td colspan=3>
	                                <div class="controls">
	                                	<form:select path="employForm">
                        					<option value=""></option>
                        					<option value="1" <c:if test="${1 == employee2.employForm}">selected</c:if>>正式</option>
                        					<option value="2" <c:if test="${2 == employee2.employForm}">selected</c:if>>兼职</option>
                        				</form:select>
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">机构调动记录 :</td>
                        		<td colspan=3>
	                                <div class="controls">
	                                    <form:textarea	path="orgTransRecord" class="input-xlarge" rows="6"/>
	                                </div>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td class="right">职位调整记录 :</td>
                        		<td colspan=3>
	                                <div class="controls">
	                                    <form:textarea	path="posAdjustRecord" class="input-xlarge" rows="6"/>
	                                </div>
                        		</td>
                        	</tr>
                        </table>
                        <table>
                        	<tr>
                        		<td colspan="4">
                        			&nbsp;<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
                                	&nbsp;<a href="${sc_ctx}/employee2/list" class="btn btn-large">返回</a>
                        		</td>
                        	</tr>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
        
        
            
		
	</body>
</html>