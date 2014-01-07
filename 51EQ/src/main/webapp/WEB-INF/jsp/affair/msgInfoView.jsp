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
                            查看
                        </c:if>
                        </h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                	<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="msgInfo">
                		<form:hidden path="uuid"/>
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
                          		<label class="left-control-label">${msgInfo.sendNameSet}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">收信人 :</label>
                           	<div class="controls">
                          		<label class="left-control-label">${msgInfo.acceptNameSet}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">主题 :</label>
                           	<div class="controls">
                          		<label class="left-control-label">${msgInfo.msgSubject}</label>
                        	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">正文 :</label>
                           	<div class="controls">
                           		<label class="left-control-label">${msgInfo.msgContent}</label>
                        	</div>
                        </div>
                        
                        <div class="control-group">
                            <div class="controls">
                            	<c:if test="${msgInfo.readFlg.equals('0')}">
                            	<a href="${sc_ctx}/msgInfo/read/${msgInfo.uuid}" class="btn	btn-large btn-primary"/>已读</a>&nbsp;
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