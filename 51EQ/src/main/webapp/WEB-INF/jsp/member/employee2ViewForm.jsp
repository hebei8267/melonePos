<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	</head>
	<body>

		<div class="container">
            <div class="row">
	            <div class="span12">
	            	<legend>
	                    <h3>员工信息</h3>
	                </legend>
                </div>
                
                <div class="span12">
                	<table class="table table-bordered mytable">
                		<tr>
                			<td rowspan="7" class="center"><h4>基本信息</h4></td>
                			<td class="right">姓名 :</td>
                			<td>${employee2.name}</td>
                			<td class="right" width="120">性别 :</td>
                			<td>
                				<c:if test="${1 == employee2.sex}">男</c:if>
                				<c:if test="${2 == employee2.sex}">女</c:if>
                			</td>
                			<td rowspan="6" class="center"><img src="${ctx}/imgservlet?FILE_PATH=${employee2.photoUrl}" height="570" width="210"/></td>
                		</tr>
                		<tr>
                			<td class="right">身份证号 :</td>
                			<td>${employee2.idCardNo}</td>
                			<td class="right">婚姻状况 :</td>
                			<td>
                				<c:if test="${0 == employee2.maritalStatus}">未婚</c:if>
                				<c:if test="${1 == employee2.maritalStatus}">已婚</c:if>
                			</td>
                		</tr>
                		<tr>
                			<td class="right">学历 :</td>
                			<td>
                				<c:if test="${1 == employee2.education}">小学</c:if>
                				<c:if test="${2 == employee2.education}">初中</c:if>
                				<c:if test="${2 == employee2.education}">高中</c:if>
                				<c:if test="${2 == employee2.education}">大学</c:if>
                			</td>
                			<td class="right">专业 :</td>
                			<td>${employee2.professional}</td>
                		</tr>
                		<tr>
                			<td class="right">参加工作时间 :</td>
                			<td>${employee2.startWorkTime}</td>
                			<td class="right">户口所在地 :</td>
                			<td>${employee2.accountLocal}</td>
                		</tr>
                		<tr>
                			<td class="right">联系电话 :</td>
                			<td>${employee2.phone}</td>
                			<td class="right">常住地址 :</td>
                			<td>${employee2.address}</td>
                		</tr>
                		<tr>
                			<td class="right">紧急联系电话 :</td>
                			<td>${employee2.emergencyPhone}</td>
                			<td class="right">紧急联系人 :</td>
                			<td>${employee2.emergencyContact}</td>
                		</tr>
                		<tr>
                			<td class="right">备注 :</td>
                			<td colspan="4">${employee2.basicInfoDescTxt}</td>
                		</tr>
                		<tr>
                			<td rowspan="6" class="center"><h4>入职信息</h4></td>
                			<td class="right">所属机构 :</td>
                			<td>
							<c:if test="${fn:length(employee2.orgId) > 4}">
             					${fn:substring(employee2.orgId,3,6)}
             				</c:if>
             				<c:if test="${fn:length(employee2.orgId) < 4}">
             					总部
             				</c:if>
							</td>
                			<td class="right">担任职务 :</td>
                			<td colspan="2">${employee2.pos}</td>
                		</tr>
                		<tr>
                			<td class="right">入职时间 :</td>
                			<td>${employee2.entryTime}</td>
                			<td class="right">转正时间 :</td>
                			<td colspan="2">${employee2.posTime}</td>
                		</tr>
                		<tr>
                			<td class="right">合同到期时间 :</td>
                			<td>${employee2.contractExpTime}</td>
                			<td class="right">续签时间 :</td>
                			<td colspan="2">${employee2.renewTime}</td>
                		</tr>
                		<tr>
                			<td class="right">聘用形式 :</td>
                			<td colspan="4">
                				<c:if test="${1 == employee2.employForm}">正式</c:if>
                				<c:if test="${2 == employee2.employForm}">兼职</c:if>
                			</td>
                		</tr>
                		<tr>
                			<td class="right">机构调动记录 :</td>
                			<td colspan="4">${employee2.orgTransRecord}</td>
                		</tr>
                		<tr>
                			<td class="right">职位调整记录 :</td>
                			<td colspan="4">${employee2.posAdjustRecord}</td>
                		</tr>
                	</table>
                </div>
            </div>
        </div>
	</body>
</html>