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
    	<script>
			$().ready(function() {
				$("#lMove1").click(function() {
					moveShowItems('allOrgIds', 'acceptOrgIds');
				});
				$("#rMove1").click(function() {
					moveShowItems('acceptOrgIds', 'allOrgIds');
				});
				
				$("#lMove2").click(function() {
					moveShowItems('allUserIds', 'acceptUserIds');
				});
				$("#rMove2").click(function() {
					moveShowItems('acceptUserIds', 'allUserIds');
				});
				
				$("#inputForm").validate({
					rules : {
						msgSubject : {
	                         required : true,
	                         maxlength : 128
	                	},
	                	msgContent : {
	                         required : true,
	                         maxlength : 1024
	                	},
	                	acceptOrgIds : {
	                         required : true
	                	},
	                	acceptUserIds : {
	                         required : true
	                	}
					}
				});
				
				
				$('a[data-toggle="tab"]').on('shown', function (e) {
					if(e.target.hash == "#org_tab"){						
						$('#acceptOrgIds').attr("disabled", false);
						$('#acceptUserIds').attr("disabled", true);
						
						$('#acceptType').val("1");
					} else {
						$('#acceptOrgIds').attr("disabled", true);
						$('#acceptUserIds').attr("disabled", false);
						
						$('#acceptType').val("2");
					}
				});
				
				$('#_myTab a:first').tab('show');
				
				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});
					
					// 保存选择组织信息
					var _funIdsItem1 = $("#acceptOrgIds option");
					for(var i = 0; i < _funIdsItem1.length; i++) {
						_funIdsItem1[i].selected = true;
					}
					
					// 保存选择人员信息
					var _funIdsItem2 = $("#acceptUserIds option");
					for(var i = 0; i < _funIdsItem2.length; i++) {
						_funIdsItem2[i].selected = true;
					}
					
					$("#inputForm").attr("target", "_self");
					$("#inputForm").attr("action", "${sc_ctx}/msgInfo/save");
					$("#inputForm").submit();
				});
			});
			
			function moveShowItems(srcObjId, discObjId) {
				var srcObj = document.getElementById(srcObjId);
				var discObj = document.getElementById(discObjId);
				if (srcObj != null && srcObj.options != null && discObj != null
						&& discObj.options != null) {
					var itemListOptions = srcObj.options;
					if (itemListOptions != null && itemListOptions.length > 0) {
						var values = new Array();
						for ( var i = itemListOptions.length - 1; i >= 0; i--) {
							if (itemListOptions[i].selected == true) {
								// new an Option
								var addrOption = document.createElement("OPTION");
								addrOption.value = itemListOptions[i].value;
								addrOption.text = itemListOptions[i].text;
								values.push(addrOption.value);
								// add Option
								discObj.options.add(addrOption);
								itemListOptions.remove(i);
							}
						}
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
                        <h3>公告/消息
                        <c:if test="${empty	msgInfo.uuid}">
                            新增
                        </c:if>
                        </h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                	<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="msgInfo">
                        <form:hidden path="uuid"/>
                        <form:hidden path="acceptType"/>
                        <div class="control-group">
                            <label class="control-label">日期 :</label>
                           	<div class="controls">
                           		<form:hidden path="optDateShow"/>
                          		<label class="left-control-label">${msgInfo.optDateShow}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">星期 :</label>
                           	<div class="controls">
                          		<label class="left-control-label">周${msgInfo.week}</label>
                          		<form:hidden path="week"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">发信人 :</label>
                           	<div class="controls">
                          		<label class="left-control-label">${msgInfo.sendUserName}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">主题 :</label>
                           	<div class="controls">
                          		<form:input	path="msgSubject" />
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">正文 :</label>
                           	<div class="controls">
                          		<form:textarea path="msgContent" class="input-xlarge" rows="8"/>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">收信人 :</label>
                           	<div class="controls">
                          		
                          		
                          		<div class="bs-docs-example">
						            <ul id="_myTab" class="nav nav-tabs">
						            	<li><a href="#org_tab" data-toggle="tab">机构</a></li>
						            	<li><a href="#per_tab" data-toggle="tab">人员</a></li>
						            </ul>
            						<div id="_myTabContent" class="tab-content">
              							<div class="tab-pane fade active in" id="org_tab">
                							<p>
                								<table>
                									<tr>
														<td>全部机构</td>
														<td></td>
														<td>发送机构</td>
													</tr>
                									<tr>
                										<td>
                											<form:select path="allOrgIds" multiple="true" class="text ui-widget-content ui-corner-all" size="8">
																<form:options items="${orgList}" itemValue="uuid"  itemLabel="name"/>
															</form:select>
														</td>
                										<td width="120px" align="center">
															<table>
																<tr><td><input type="button" id="lMove1" class="submit" value="->" /></td></tr>
																<tr><td><input type="button" id="rMove1" class="submit" value="<-" /></td></tr>
															</table>
														</td>
                										<td>
                											<form:select path="acceptOrgIds" multiple="true" class="text ui-widget-content ui-corner-all" size="8" disabled="true">
															</form:select>
                										</td>
                									</tr>
                								</table>
                							</p>
              							</div>
              							<div class="tab-pane fade" id="per_tab">
                							<p>
                								<table>
                									<tr>
														<td>全部人员</td>
														<td></td>
														<td>发送人员</td>
													</tr>
                									<tr>
                										<td>
                											<form:select path="allUserIds" multiple="true" class="text ui-widget-content ui-corner-all" size="8">
                												<c:forEach items="${userList}" var="user">
                												<option value="${user.loginName}">${user.name} - ( ${user.orgName} )</option>
                												</c:forEach>
															</form:select>
														</td>
                										<td width="120px" align="center">
															<table>
																<tr><td><input type="button" id="lMove2" class="submit" value="->" /></td></tr>
																<tr><td><input type="button" id="rMove2" class="submit" value="<-" /></td></tr>
															</table>
														</td>
                										<td>
                											<form:select path="acceptUserIds" multiple="true" class="text ui-widget-content ui-corner-all" size="8" disabled="true">
															</form:select>
                										</td>
                									</tr>
                								</table>
                							</p>
              							</div>
              
            						</div>
          						</div>
                          		
                        	</div>
                        </div>
                        
                        <div class="control-group">
                            <div class="controls">
                            	<c:if test="${empty msgInfo.uuid}">
                            	<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>&nbsp;
                        		</c:if>
                                <a href="${sc_ctx}/msgInfo" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>