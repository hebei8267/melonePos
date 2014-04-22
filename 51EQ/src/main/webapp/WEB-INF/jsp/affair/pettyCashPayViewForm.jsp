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
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <div class="row">
                <div class="span12">
                    <legend>
                        <h3>${pettyCash.orgName} 门店备用金<span class="_warn1">(支出)</span>信息
                        查看
                        </h3>
                    </legend>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm">
                        <div class="control-group">
                            <label class="control-label">业务编号(UID) :</label>
                            <div class="left-control-label">
                            	${pettyCash.optUid}
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">业务日期 :</label>
                            <div class="left-control-label">
                            	${pettyCash.optDateShow}
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出金额 :</label>
                            <div class="left-control-label">
                          		${pettyCash.optAmtShow} 元
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备用金余额 :</label>
                            <div class="left-control-label">
                          		<span class="_warn1">${pettyCash.balanceAmt} 元</span>
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出类型 :</label>
                            <div class="left-control-label">
                            	${pettyCash.expType}
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出事项 :</label>
                            <div class="left-control-label">
                            	${pettyCash.expReason}
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注 :</label>
                            <div class="left-control-label">
                            	${pettyCash.descTxt}
                          	</div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>