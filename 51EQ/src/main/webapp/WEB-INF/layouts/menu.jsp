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
					<c:if test="${_permIdList.contains('65') || _permIdList.contains('66') || _permIdList.contains('68')}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">商品收发<b class="caret"></b></a>
							<ul class="dropdown-menu">
							<c:if test="${_permIdList.contains('65')}">
								<li>
									<a href="${sc_ctx}/replenishOrder/list">收货管理</a>
								</li>
							</c:if>
							<c:if test="${_permIdList.contains('66')}">
								<li>
									<a href="${sc_ctx}/replenishOrder/manageList">发货管理</a>
								</li>
							</c:if>
							<c:if test="${_permIdList.contains('68')}">
								<li>
									<a href="${sc_ctx}/replenishOrderReport">商品收发错填信息</a>
								</li>
							</c:if>
							</ul>
						</li>
					</c:if>

					<c:if test="${_permIdList.contains('1') || _permIdList.contains('2')
					|| _permIdList.contains('3') || _permIdList.contains('4')
					|| _permIdList.contains('5') || _permIdList.contains('6')
					|| _permIdList.contains('7') || _permIdList.contains('59')
					|| _permIdList.contains('48') || _permIdList.contains('64')
					|| _permIdList.contains('70')}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">系统管理<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:if test="${_permIdList.contains('1')}">
									<li>
										<a href="${sc_ctx}/user">用户管理</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('2')}">
									<li>
										<a href="${sc_ctx}/employee">职员管理</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('59')}">
									<li>
										<a href="${sc_ctx}/role/list">角色管理</a>
									</li>
									<li class="divider"></li>
								</c:if>
								
								<c:if test="${_permIdList.contains('3')}">
									<li>
										<a href="${sc_ctx}/organization">机构管理</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('4')}">
									<li>
										<a href="${sc_ctx}/supplier">供应商管理</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('5')}">
									<li>
										<a href="${sc_ctx}/bankCard">银行卡管理</a>
									</li>
									<li class="divider"></li>
								</c:if>
								
								<c:if test="${_permIdList.contains('6')}">
									<li>
										<a href="${sc_ctx}/goods">商品管理</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('7')}">
									<li>
										<a href="${sc_ctx}/itemType">商品类别管理</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('48')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/coupon/list">代金卷管理</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('64')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/budgetSubject/init">预算科目管理</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('70')}">
									<li>
										<a href="${sc_ctx}/accountSubject/init">记账科目管理</a>
									</li>
								</c:if>
							</ul>
						</li>
					</c:if>

					<c:if test="${_permIdList.contains('8') || _permIdList.contains('9')
					|| _permIdList.contains('46')}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">销售信息<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:if test="${_permIdList.contains('8')}">
									<li>
										<a href="${sc_ctx}/cashRun">录入</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('9')}">
									<li>
										<a href="${sc_ctx}/cashDaily">日结</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('46')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/salesDayChartReport/init_shop">日销售信息对比</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('21')}">
									<li>
										<a href="${sc_ctx}/salesWeekGoodsTotalReport/init">商品周销售信息一览</a>
									</li>
								</c:if>
							</ul>
						</li>
					</c:if>

					<c:if test="${_permIdList.contains('13') || _permIdList.contains('14')
					|| _permIdList.contains('15') || _permIdList.contains('16')
					|| _permIdList.contains('17') || _permIdList.contains('18')
					|| _permIdList.contains('19') || _permIdList.contains('20')
					|| _permIdList.contains('26')
					|| _permIdList.contains('40')}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">销售信息<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:if test="${_permIdList.contains('13')}">
									<li>
										<a href="${sc_ctx}/cardReport">刷卡信息</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('14')}">
									<li>
										<a href="${sc_ctx}/dailyReport">日结信息</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('15')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/bankCheck/init">存款信息</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('16')}">
									<li>
										<a href="${sc_ctx}/cashReport">销售信息(表格)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('17')}">
									<li>
										<a href="${sc_ctx}/cashChartReport">销售信息(图形)</a>
									</li>
								</c:if>
								<li class="divider"></li>
								<c:if test="${_permIdList.contains('18')}">
									<li>
										<a href="${sc_ctx}/storeChartReport/list">库存信息(表格)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('19')}">
									<li>
										<a href="${sc_ctx}/storeChartReport">库存信息(图形)</a>
									</li>
								</c:if>
								<li class="divider"></li>
								<c:if test="${_permIdList.contains('20')}">
									<li>
										<a href="${sc_ctx}/salesDayGoodsReport">商品销售信息一览(按条码)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('21')}">
									<li>
										<a href="${sc_ctx}/salesWeekGoodsTotalReport/init">商品周销售信息一览</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('26')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/salesWeekTotal/reCalInit">商品周销售信息＆重计算</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('40')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/cashCounterDaily/init">反日结(销售信息)</a>
									</li>
								</c:if>
							</ul>
						</li>
					</c:if>

					<c:if test="${_permIdList.contains('22') || _permIdList.contains('23')
					|| _permIdList.contains('24') || _permIdList.contains('25')
					|| _permIdList.contains('41') || _permIdList.contains('44')
					|| _permIdList.contains('45') || _permIdList.contains('60')
					|| _permIdList.contains('61') || _permIdList.contains('62')
					|| _permIdList.contains('63') || _permIdList.contains('69')}">

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">数据报表<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:if test="${_permIdList.contains('62')}">
									<li>
										<a href="${sc_ctx}/salesOrdersDayTotalContrast/init">店铺指标</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('63')}">
									<li>
										<a href="${sc_ctx}/membershipCardContrast/init">会员卡信息对比</a>
									</li>
									<li class="divider"></li>
								</c:if>
								<c:if test="${_permIdList.contains('60')}">
									<li>
										<a href="${sc_ctx}/salesContrastByItem/init">类别销售信息对比</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('61')}">
									<li>
										<a href="${sc_ctx}/salesContrastBySupplier/init">货商销售信息对比</a>
									</li>
									<li class="divider"></li>
								</c:if>
								
								<c:if test="${_permIdList.contains('22')}">
									<li>
										<a href="${sc_ctx}/salesDayItemChartReport/bar_init">类别销售信息一览(图形)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('23')}">
									<li>
										<a href="${sc_ctx}/salesDayItemChartReport/pie_init">类别销售信息对比(图形)</a>
									</li>
									<li class="divider"></li>
								</c:if>
								<c:if test="${_permIdList.contains('45')}">
									<li>
										<a href="${sc_ctx}/salesItemRankReport/init">销售信息排名(按类别)</a>
									</li>
									<li class="divider"></li>
								</c:if>
								<c:if test="${_permIdList.contains('24')}">
									<li>
										<a href="${sc_ctx}/salesDaySupChartReport/bar_init">货商销售信息一览(图形)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('25')}">
									<li>
										<a href="${sc_ctx}/salesDaySupChartReport/pie_init">货商销售信息对比(图形)</a>
									</li>
									<li class="divider"></li>
								</c:if>
								<c:if test="${_permIdList.contains('44')}">
									<li>
										<a href="${sc_ctx}/salesDayChartReport/init">日销售信息对比</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('41')}">
									<li>
										<a href="${sc_ctx}/salesMonthItemChartReport/bar_init">月销售信息对比(图形)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('72')}">
									<li>
										<a href="${sc_ctx}/salesMonthItemChartReport/brand_init">月销售信息对比(图形)[按品牌]</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('73')}">
									<li>
										<a href="${sc_ctx}/salesMonthItemChartReport/mngUser_init">月销售信息对比(图形)[按督导]</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('69')}">
									<li>
										<a href="${sc_ctx}/salesMonthChartReport">月销售信息(图形)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('47')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/pettyCash/analytics_init">门店备用金分析(图形)</a>
									</li>
								</c:if>
							</ul>
						</li>

					</c:if>

					<c:if test="${_permIdList.contains('27') || _permIdList.contains('28')
					|| _permIdList.contains('29') 
					|| _permIdList.contains('37') || _permIdList.contains('38') 
					|| _permIdList.contains('39') || _permIdList.contains('42') 
					|| _permIdList.contains('43') || _permIdList.contains('49') 
					|| _permIdList.contains('50') || _permIdList.contains('51') 
					|| _permIdList.contains('52') || _permIdList.contains('55') 
					|| _permIdList.contains('67') || _permIdList.contains('71')
					|| _permIdList.contains('74')}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">日常事务<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:if test="${_permIdList.contains('71')}">
									<li>
										<a href="${sc_ctx}/accountFlow/list">会计记账</a>
									</li>
									<li class="divider"></li>
								</c:if>
								<c:if test="${_permIdList.contains('27')}">
									<li>
										<a href="${sc_ctx}/invoiceApply">发票申请</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('28')}">
									<li>
										<a href="${sc_ctx}/invoiceDraw">发票开具</a>
									</li>
								</c:if>
								
								<c:if test="${_permIdList.contains('67')}">
									<li>
										<a href="${sc_ctx}/pettyCashApp/list">资金申请与审批</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('37')}">
									<li>
										<%//门店%>
										<a href="${sc_ctx}/pettyCash/list">门店备用金</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('38')}">
									<li class="divider"></li>
									<li>
										<%//总部%>
										<a href="${sc_ctx}/pettyCash/manageList">门店备用金</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('39')}">
									<li>
										<%//总部%>
										<a href="${sc_ctx}/pettyCash/carryOverInit">门店备用金(结转)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('29')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/inspect">门店巡查报告(财务)</a>
									</li>
								</c:if>
								<c:if test="${!_permIdList.contains('29') && _permIdList.contains('50')}">
									<li class="divider"></li>
								</c:if>
								<c:if test="${_permIdList.contains('50')}">
									<li>
										<a href="${sc_ctx}/runInspect/list">门店巡查报告(运营)</a>
									</li>
								</c:if>
								
								<%//总部-普通人员用%>
								<c:if test="${_permIdList.contains('49')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/monthSaleTarget/init">机构月销售目标管理</a>
									</li>
								</c:if>

								<c:if test="${_permIdList.contains('74')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/orgContract/init">机构合同/租金缴交管理</a>
									</li>
								</c:if>

								<%//总部-会计用%>
								<c:if test="${_permIdList.contains('42')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/supplierSignRun/init">供应商(挂账)结算进度表</a>
									</li>
								</c:if>

								<%//总部-BOSS用%>
								<c:if test="${_permIdList.contains('43')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/supplierSignRun/init_boss">供应商(挂账)结算进度表</a>
									</li>
								</c:if>
								
								<%//门店/总部-共享文件管理 %>
								<c:if test="${_permIdList.contains('55')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/freight/list">商品调货单</a>
									</li>
								</c:if>
							</ul>
						</li>
					</c:if>

					<c:if test="${_permIdList.contains('30') 
					|| _permIdList.contains('31') || _permIdList.contains('32') 
					|| _permIdList.contains('33') || _permIdList.contains('34')
					|| _permIdList.contains('35') || _permIdList.contains('36') 
					|| _permIdList.contains('53') || _permIdList.contains('54') 
					|| _permIdList.contains('58') || _permIdList.contains('57')}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">行政人事<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:if test="${_permIdList.contains('57')}">
									<li>
										<a href="${sc_ctx}/borrowItem/list">物件借还</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('36')}">
									<li>
										<a href="${sc_ctx}/msgInfo">公告/消息</a>
									</li>
								</c:if>
								<%//门店%>
								<c:if test="${_permIdList.contains('30')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/punchClock/list">考勤信息(查看)</a>
									</li>
								</c:if>
								<%//门店%>
								<c:if test="${_permIdList.contains('31')}">
									<li>
										<%String nowM =	DateUtils.getCurrentMonth(); %>
										<a href="${sc_ctx}/workSchedule/historyList/<%=nowM	%>">排班表(查看)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('32')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/workSchedule/list">排班表(维护)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('33')}">
									<li>
										<a href="${sc_ctx}/tmpEmployee/list">兼职信息(维护)</a>
									</li>
								</c:if>
								<c:if test="${_permIdList.contains('34')}">
									<li>
										<a href="${sc_ctx}/workType/list">工作时间(维护)</a>
									</li>
								</c:if>

								<%//总部%>
								<c:if test="${_permIdList.contains('35')}">
									<li>
										<a href="${sc_ctx}/punchClock/manage">考勤信息(查看)</a>
									</li>
								</c:if>
								
								<c:if test="${_permIdList.contains('58')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/employee2/list">员工管理</a>
									</li>
								</c:if>
								<%//总部-共享文件管理 %>
								<c:if test="${_permIdList.contains('53')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/shareFile/managerList">共享文件管理</a>
									</li>
								</c:if>

								<%//门店-共享文件管理 %>
								<c:if test="${_permIdList.contains('54')}">
									<li class="divider"></li>
									<li>
										<a href="${sc_ctx}/shareFile/storeList">共享文件管理</a>
									</li>
								</c:if>
							</ul>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
