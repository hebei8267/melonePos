<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ page import="java.util.Set" %>
<%@ page import="com.tjhx.entity.member.Permission" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container ">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a>
            <p class="navbar-text pull-left">
                [${sessionScope.__SESSION_USER_INFO.orgName}]&nbsp;${sessionScope.__SESSION_USER_INFO.name}&nbsp;您好，欢迎来到 EQ+！
            </p>

            <c:set var="_permIdList" value="${sessionScope.__SESSION_USER_INFO.role.permIdList }"></c:set>
            <div class="nav-collapse" style="height: auto;">
                <ul class="nav pull-right">
                	<li>
                        <a href="${sc_ctx}/member/myspace">首页</a>
                    </li>
                    <li>
                        <a href="${sc_ctx}/member/logout">用户退出</a>
                    </li>
                    <li>
                        <a href="${sc_ctx}/member/initModPwd">密码修改</a>
                    </li>
                    <c:if test="${_permIdList.contains('1') || _permIdList.contains('2') || _permIdList.contains('3') || _permIdList.contains('4')}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">系统管理<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <c:if test="${_permIdList.contains('1')}">
                                    <li>
                                        <a href="${sc_ctx}/user">用户管理</a>
                                    </li>
                                    <li>
                                        <a href="${sc_ctx}/employee">职员管理</a>
                                    </li>
                                </c:if>
                                <li class="divider"></li>
                                <c:if test="${_permIdList.contains('2')}">
                                    <li>
                                        <a href="${sc_ctx}/organization">机构管理</a>
                                    </li>
                                </c:if>
                                <c:if test="${_permIdList.contains('3')}">
                                    <li>
                                        <a href="${sc_ctx}/supplier">供应商管理</a>
                                    </li>
                                </c:if>
                                <c:if test="${_permIdList.contains('4')}">
                                    <li>
                                        <a href="${sc_ctx}/bankCard">银行卡管理</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a href="${sc_ctx}/goods">商品管理</a>
                                    </li>
                                    <li>
                                        <a href="${sc_ctx}/itemType">商品类别管理</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a href="${sc_ctx}/cashCounterDaily/init">反日结(销售信息)</a>
                                    </li>
                                </c:if>
                            </ul>
                        </li>
                    </c:if>

                    <c:if test="${_permIdList.contains('5') || _permIdList.contains('6')}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">销售信息<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <c:if test="${_permIdList.contains('5')}">
                                    <li>
                                        <a href="${sc_ctx}/cashRun">录入</a>
                                    </li>
                                </c:if>
                                <c:if test="${_permIdList.contains('6')}">
                                    <li>
                                        <a href="${sc_ctx}/cashDaily">日结</a>
                                    </li>
                                </c:if>
                            </ul>
                        </li>
                    </c:if>
<!-- 
                    <c:if test="${_permIdList.contains('7') || _permIdList.contains('8')}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">入库信息<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <c:if test="${_permIdList.contains('7')}">
                                    <li>
                                        <a href="${sc_ctx}/storeRun">录入</a>
                                    </li>
                                </c:if>
                                <c:if test="${_permIdList.contains('8')}">
                                    <li>
                                        <a href="${sc_ctx}/storeRunAudit">审核</a>
                                    </li>
                                </c:if>
                            </ul>
                        </li>
                    </c:if>
-->
					<c:if test="${_permIdList.contains('9') || _permIdList.contains('10') || _permIdList.contains('11') || _permIdList.contains('12')}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">报表<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                        	<!-- 
                        	<c:if test="${_permIdList.contains('9')}">
                            <li>
                                <a href="${sc_ctx}/storeReport">入库信息</a>
                            </li>
                            </c:if>
                            -->
                            <c:if test="${_permIdList.contains('10')}">
                            <li>
                                <a href="${sc_ctx}/cardReport">刷卡信息</a>
                            </li>
                            </c:if>
                            <c:if test="${_permIdList.contains('12')}">
                            <li>
                                <a href="${sc_ctx}/dailyReport">日结信息</a>
                            </li>
                            </c:if>
                            <c:if test="${_permIdList.contains('11')}">
                            <li class="divider"></li>
                            <li>
		                    	<a href="${sc_ctx}/bankCheck/init">存款信息</a>
		                 	</li>
                            <li>
		                    	<a href="${sc_ctx}/cashReport">销售信息(表格)</a>
		                 	</li>
		                  	<li>
		                   		<a href="${sc_ctx}/cashChartReport">销售信息(图形)</a>
		                  	</li>
		                  	<li class="divider"></li>
		                  	<li>
                                <a href="${sc_ctx}/storeChartReport/list">库存信息(表格)</a>
                            </li>
		                  	<li>
                                <a href="${sc_ctx}/storeChartReport">库存信息(图形)</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${sc_ctx}/salesDayGoodsReport">商品销售信息一览(按条码)</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${sc_ctx}/salesDayItemChartReport/bar_init">类别销售信息一览(图形)</a>
                            </li>
                            <li>
                                <a href="${sc_ctx}/salesDayItemChartReport/pie_init">类别销售信息对比(图形)</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${sc_ctx}/salesDaySupChartReport/bar_init">货商销售信息一览(图形)</a>
                            </li>
                            <li>
                                <a href="${sc_ctx}/salesDaySupChartReport/pie_init">货商销售信息对比(图形)</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${sc_ctx}/salesMonthItemChartReport/bar_init">月销售信息对比(图形)</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${sc_ctx}/salesWeekGoodsTotalReport/init">商品周销售信息一览</a>
                            </li>
                            <li>
                                <a href="${sc_ctx}/salesWeekTotal/reCalInit">商品周销售信息＆重计算</a>
                            </li>
                            </c:if>
                        </ul>
                    </li>
                    </c:if>
                    
                    <c:if test="${_permIdList.contains('13') || _permIdList.contains('14') 
                    || _permIdList.contains('15') || _permIdList.contains('16') || _permIdList.contains('17') 
                    || _permIdList.contains('18') || _permIdList.contains('19') || _permIdList.contains('20')
                    || _permIdList.contains('21')}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">日常事务<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                        	<c:if test="${_permIdList.contains('13')}">
                            <li>
                                <a href="${sc_ctx}/invoiceApply">发票申请</a>
                            </li>
                            </c:if>
                            <c:if test="${_permIdList.contains('14')}">
                            <li>
                                <a href="${sc_ctx}/invoiceDraw">发票开具</a>
                            </li>
                            </c:if>
                            <c:if test="${_permIdList.contains('18')}">
  							<li>
                                <a href="${sc_ctx}/msgInfo">公告/消息</a>
                            </li>
                            </c:if>
                            
                            
                            <c:if test="${_permIdList.contains('20')}">
  							<li>
                                <%//门店%>
                                <a href="${sc_ctx}/pettyCash/list">门店备用金</a>
                            </li>
                            </c:if>
                            
                            <c:if test="${_permIdList.contains('21')}">
                            <li class="divider"></li>
  							<li>
                                <%//总部%>
                                <a href="${sc_ctx}/pettyCash/manageList">门店备用金</a>
                                <a href="${sc_ctx}/pettyCash/carryOverInit">门店备用金(结转)</a>
                            </li>
                            <li class="divider"></li>
                            </c:if>
                            
                            
                            <!-- 
                            <li>
                                <a href="${sc_ctx}/reqBill">门店要货单</a>
                            </li>
                            -->
                            <c:if test="${_permIdList.contains('15')}">
                            <li>
                                <a href="${sc_ctx}/inspect">门店巡查报告</a>
                            </li>
                            </c:if>
                            
                            <%//门店%>
	    					<c:if test="${_permIdList.contains('16')}">
	    					<li class="divider"></li>
	    					<li>
		                 		<a href="${sc_ctx}/punchClock/list">考勤信息(查看)</a>
		                 		<%String nowM =	DateUtils.getCurrentMonth(); %>
		                 		<a href="${sc_ctx}/workSchedule/historyList/<%=nowM	%>">排班表(查看)</a>
		                   	</li>
		                   	</c:if>
		                   	<c:if test="${_permIdList.contains('19')}">
	    					<li class="divider"></li>
	    					<li>
		                 		<a href="${sc_ctx}/workSchedule/list">排班表(维护)</a>
		                   		<a href="${sc_ctx}/tmpEmployee/list">兼职信息(维护)</a>
		                   		<a href="${sc_ctx}/workType/list">工作时间(维护)</a>
		                   	</li>
		                   	</c:if>
		                    
		                    <%//总部%>
		                    <c:if test="${_permIdList.contains('17')}">
		                	<li>
		                   		<a href="${sc_ctx}/punchClock/manage">考勤信息(查看)</a>
		                 	</li>
		                 	</c:if>
                        </ul>
                    </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
