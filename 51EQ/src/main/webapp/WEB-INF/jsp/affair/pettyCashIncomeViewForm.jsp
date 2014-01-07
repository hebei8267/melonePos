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
                        <h3>${pettyCash.orgName} 门店备用金<span class="_warn1">(拨入)</span>信息
                        查看
                        </h3>
                    </legend>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm">
                        
                        <div class="control-group">
                            <label class="control-label">拨款日期 :</label>
                            <div class="left-control-label">
                            	${pettyCash.optDateShow}
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">拨款来源 :</label>
                            <div class="left-control-label">
                            	<c:if test="${pettyCash.incomeSource.equals('1')}">
	                           	正常拨入
	                          	</c:if>
	                          	<c:if test="${pettyCash.incomeSource.equals('2')}">
	                          	非常拨入
	                        	</c:if>
	                          	<c:if test="${pettyCash.incomeSource.equals('4')}">
	                           	会计调帐
	                          	</c:if>
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">拨入金额 :</label>
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