<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${ctx}/static/css/pricing-steelblue.css">
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container" style="padding-top: 10px;">
        	<c:if test="${!empty shareFileList}">
        	<div class="row">
        		<div class="span12">
                    <legend>
                        <h3>共享文件</h3>
                    </legend>
                </div>
        		<c:forEach items="${shareFileList}" var="shareFile">
        		<div class="span3"><a href="${sc_ctx}/shareFile/downLoad/${shareFile.uuid}">${shareFile.fileName}</a></div>
        		</c:forEach>
        	</div>
        	</c:if>
        	
        	<c:if test="${!empty msgInfoList}">
            <div class="row" style="padding-top: 10px;">
            	<div class="span12">
                    <legend>
                        <h3>消息/信息</h3>
                    </legend>
                </div>
                <c:forEach items="${msgInfoList}" var="msgInfo">
                	<c:if test="${msgInfo.readFlg == 0}">
                	<div class="span3 pricing6">
                	</c:if>
                	
                	<c:if test="${msgInfo.readFlg == 1}">
                	<div class="span3 pricing2">
                	</c:if>
		                <ul>
		                    <li class="head"><h4>${msgInfo.msgSubject}</h4></li>
		                    <li>${msgInfo.msgContent}</li>
		                    <li>发信人 : ${msgInfo.sendNameSet}</li>
		                    <li>${msgInfo.optDateShow}</li>
		                    <li class="footer"><a href="${sc_ctx}/msgInfo/view/${msgInfo.uuid}" class="btn btn-large">查 看</a></li>
		                </ul>
		            </div>
                </c:forEach>
            </div>
            </c:if>
        </div>
    </body>
</html>