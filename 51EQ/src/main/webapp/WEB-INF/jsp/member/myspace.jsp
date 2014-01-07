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
            <div class="row">
                <c:forEach items="${msgInfoList}" var="msgInfo">
                    <div class="span3 pricing-table animate go-up">
                        <ul>
                            <li class="pricing-header-row-1">
                                <div class="package-title">
                                    <h2 class="no-bold">${msgInfo.msgSubject}</h2>
                                </div>
                            </li>
                            <li class="pricing-content-row-odd">
                                ${msgInfo.msgContent}
                            </li>
                            <li class="pricing-content-row-even">
                                发信人 : ${msgInfo.sendNameSet}
                                <span style="float: right">${msgInfo.optDateShow}</span>
                            </li>
                            <li class="pricing-footer">
                                <a href="${sc_ctx}/msgInfo/view/${msgInfo.uuid}" class="btn"><i class="icon-search"></i> 查看</a>
                            </li>
                        </ul>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>