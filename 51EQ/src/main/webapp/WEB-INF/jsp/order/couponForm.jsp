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
	    		padding: 5px;
				background-color: #99FF33;
			}
			._warn2 {
				padding: 5px;
				background-color: #FFCC33;
			}
        </style>
        <script>
            $(function() {
                $("#inputForm").validate({
                    rules : {
                        couponNo : {
                            required : true,
                            maxlength : 8
                        },
                        name : {
                            required : true,
                            maxlength : 32
                        },
                        rate : {
                            required : true,
                            rate : true
                        },
                        descTxt : {
                            maxlength : 255
                        }
                    }
                });

				$("#couponNo").blur(function() {
					if($("#couponNo").val() == ""){
						return;
					}
					$("#couponNo").val(addZero($("#couponNo").val(), 8));
					
					var _val = $("#couponNo").val();
					var _start = _val.length - 8;
                    var _end = _val.length;
                                      	
                   	$("#couponNo").val(_val.substring(_start, _end));
				});
				
                $("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });
                    
                    // 保存选择组织信息
					var _funIdsItem1 = $("#appOrgIds option");
					for(var i = 0; i < _funIdsItem1.length; i++) {
						_funIdsItem1[i].selected = true;
					}

                    $("#inputForm").attr("action", "${sc_ctx}/coupon/save");
                    $("#inputForm").submit();
                });
                
                $("#lMove2").click(function() {
					moveShowItems('allOrgIds', 'appOrgIds');
				});
				$("#rMove2").click(function() {
					moveShowItems('appOrgIds', 'allOrgIds');
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
			
			function addZero(num, n) {
                if ((num + "").length >= n) return num;
                return addZero("0" + num, n);
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
                        <h3>代金卷信息
                        <c:if test="${empty	coupon.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty coupon.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="coupon">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">编号 :</label>
                            <div class="controls">
                                <form:input	path="couponNo" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">名称 :</label>
                            <div class="controls">
                                <form:input	path="name" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">汇率 :</label>
                            <div class="controls">
                                <form:input	path="rate" />（代金卷/现金）例0.75【每1元代金卷价值0.75元人民币】
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">机构 :</label>
                            <div class="controls">
                                <p>
                				<table>
				      				<tr>
										<td>全部机构</td>
										<td></td>
										<td>适用机构</td>
									</tr>
				                	<tr>
				                		<td>
				                			<form:select path="allOrgIds" multiple="true" class="text ui-widget-content ui-corner-all" size="8">
				                			<c:forEach items="${allOrgList}" var="org">
				                			<option value="${org.id}">${org.name}</option>
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
				                			<form:select path="appOrgIds" multiple="true" class="text ui-widget-content ui-corner-all" size="8">
				                			<c:forEach items="${appOrgList}" var="org">
				                			<option value="${org.id}">${org.name}</option>
				                			</c:forEach>
											</form:select>
				                		</td>
				                	</tr>
				             	</table>
                				</p>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">统计至日销售额 :</label>
                            <div class="controls">
                            	<c:if test="${empty	coupon.uuid}">
	                            	<form:radiobutton path="subTotalFlg" value="true"/>
	                                <span class='_warn1'>统计</span>
	                                <form:radiobutton path="subTotalFlg" value="false"/>
	                                <span class='_warn2'>不统计</span>
		                        </c:if>
		                        <c:if test="${!empty coupon.uuid}">
		                        	<form:hidden path="subTotalFlg" />
                            		<c:if test="${coupon.subTotalFlg == true}">
                                   		<span class='_warn1'>统计</span>
                                  	</c:if>
                                 	<c:if test="${coupon.subTotalFlg != true}">
                                     	<span class='_warn2'>不统计</span>
                                  	</c:if>
                        		</c:if>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">有效标记 :</label>
                            <div class="controls">
                                <form:radiobutton path="delFlg" value="false"/>
                                <span class='_warn1'>有效</span>
                                <form:radiobutton path="delFlg" value="true"/>
                                <span class='_warn2'>无效</span>
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
                                &nbsp;<a href="${sc_ctx}/coupon/list" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
