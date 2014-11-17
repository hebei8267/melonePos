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
			$(function() {

				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});

					$("#inputForm").attr('target', '_self');
					$("#inputForm").attr("action", "${sc_ctx}/role/save");
					$("#inputForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="container">
			<div class="row">
				<div class="span12">
					<legend>
						<h3>角色信息
						<c:if test="${empty	user.uuid}">
							新增
						</c:if>
						<c:if test="${!empty user.uuid}">
							编辑
						</c:if></h3>
					</legend>
				</div>
				<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="role">
				<div class="span12"	style="margin-top: 10px;">
					
					<form:hidden path="uuid"/>
					<div class="control-group">
						<label class="control-label">角色名称 :</label>
						<div class="controls">
							<form:input	path="name" />
						</div>
					</div>
				</div>
				
				<div class="span3">
					<h4>日常事务模块<br>
					<table class="table	table-striped table-bordered" style="margin-top: 10px;">
						<tr>
							<c:if test="${role.permIdList.contains('57')}">
							<td><input type="checkbox" name="perm" value="57" checked>物件借还</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('57')}">
							<td><input type="checkbox" name="perm" value="57">物件借还</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('28')}">
							<td><input type="checkbox" name="perm" value="28" checked>发票开具</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('28')}">
							<td><input type="checkbox" name="perm" value="28">发票开具</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('36')}">
							<td><input type="checkbox" name="perm" value="36" checked>公告/消息</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('36')}">
							<td><input type="checkbox" name="perm" value="36">公告/消息</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('38')}">
							<td><input type="checkbox" name="perm" value="38" checked>门店备用金</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('38')}">
							<td><input type="checkbox" name="perm" value="38">门店备用金</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('39')}">
							<td><input type="checkbox" name="perm" value="39" checked>门店备用金(结转)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('39')}">
							<td><input type="checkbox" name="perm" value="39">门店备用金(结转)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('29')}">
							<td><input type="checkbox" name="perm" value="29" checked>门店巡查报告(财务)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('29')}">
							<td><input type="checkbox" name="perm" value="29">门店巡查报告(财务)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('50')}">
							<td><input type="checkbox" name="perm" value="50" checked>门店巡查报告(运营)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('50')}">
							<td><input type="checkbox" name="perm" value="50">门店巡查报告(运营)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('32')}">
							<td><input type="checkbox" name="perm" value="32" checked>排班表(维护)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('32')}">
							<td><input type="checkbox" name="perm" value="32">排班表(维护)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('33')}">
							<td><input type="checkbox" name="perm" value="33" checked>兼职信息(维护)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('33')}">
							<td><input type="checkbox" name="perm" value="33">兼职信息(维护)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('34')}">
							<td><input type="checkbox" name="perm" value="34" checked>工作时间(维护)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('34')}">
							<td><input type="checkbox" name="perm" value="34">工作时间(维护)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('35')}">
							<td><input type="checkbox" name="perm" value="35" checked>考勤信息(查看)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('35')}">
							<td><input type="checkbox" name="perm" value="35">考勤信息(查看)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('42')}">
							<td><input type="checkbox" name="perm" value="42" checked>供应商(挂账)结算进度表</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('42')}">
							<td><input type="checkbox" name="perm" value="42">供应商(挂账)结算进度表</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('49')}">
							<td><input type="checkbox" name="perm" value="49" checked>机构月销售目标管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('49')}">
							<td><input type="checkbox" name="perm" value="49">机构月销售目标管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('53')}">
							<td><input type="checkbox" name="perm" value="53" checked>共享文件管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('53')}">
							<td><input type="checkbox" name="perm" value="53">共享文件管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('55')}">
							<td><input type="checkbox" name="perm" value="55" checked>商品调货单</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('55')}">
							<td><input type="checkbox" name="perm" value="55">商品调货单</td>
							</c:if>
						</tr>
					</table>
				</div>
				
				<div class="span3">
					<h4>销售信息模块<br>
					<table class="table	table-striped table-bordered" style="margin-top: 10px;">
						<tr>
							<c:if test="${role.permIdList.contains('13')}">
							<td><input type="checkbox" name="perm" value="13" checked>刷卡信息</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('13')}">
							<td><input type="checkbox" name="perm" value="13">刷卡信息</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('14')}">
							<td><input type="checkbox" name="perm" value="14" checked>日结信息</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('14')}">
							<td><input type="checkbox" name="perm" value="14">日结信息</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('15')}">
							<td><input type="checkbox" name="perm" value="15" checked>存款信息</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('15')}">
							<td><input type="checkbox" name="perm" value="15">存款信息</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('16')}">
							<td><input type="checkbox" name="perm" value="16" checked>销售信息(表格)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('16')}">
							<td><input type="checkbox" name="perm" value="16">销售信息(表格)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('17')}">
							<td><input type="checkbox" name="perm" value="17" checked>销售信息(图形)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('17')}">
							<td><input type="checkbox" name="perm" value="17">销售信息(图形)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('18')}">
							<td><input type="checkbox" name="perm" value="18" checked>库存信息(表格)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('18')}">
							<td><input type="checkbox" name="perm" value="18">库存信息(表格)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('19')}">
							<td><input type="checkbox" name="perm" value="19" checked>库存信息(图形)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('19')}">
							<td><input type="checkbox" name="perm" value="19">库存信息(图形)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('20')}">
							<td><input type="checkbox" name="perm" value="20" checked>商品销售信息一览(按条码)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('20')}">
							<td><input type="checkbox" name="perm" value="20">商品销售信息一览(按条码)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('21')}">
							<td><input type="checkbox" name="perm" value="21" checked>商品周销售信息一览</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('21')}">
							<td><input type="checkbox" name="perm" value="21">商品周销售信息一览</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('26')}">
							<td><input type="checkbox" name="perm" value="26" checked>商品周销售信息＆重计算</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('26')}">
							<td><input type="checkbox" name="perm" value="26">商品周销售信息＆重计算</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('40')}">
							<td><input type="checkbox" name="perm" value="40" checked>反日结(销售信息)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('40')}">
							<td><input type="checkbox" name="perm" value="40">反日结(销售信息)</td>
							</c:if>
						</tr>
					</table>
				</div>

				
				<div class="span3">
					<h4>系统管理模块<br>
					<table class="table	table-striped table-bordered" style="margin-top: 10px;">
						<tr>
							<c:if test="${role.permIdList.contains('1')}">
							<td><input type="checkbox" name="perm" value="1" checked>用户管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('1')}">
							<td><input type="checkbox" name="perm" value="1">用户管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('2')}">
							<td><input type="checkbox" name="perm" value="2" checked>职员管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('2')}">
							<td><input type="checkbox" name="perm" value="2">职员管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('59')}">
							<td><input type="checkbox" name="perm" value="59" checked>角色管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('59')}">
							<td><input type="checkbox" name="perm" value="59">角色管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('3')}">
							<td><input type="checkbox" name="perm" value="3" checked>机构管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('3')}">
							<td><input type="checkbox" name="perm" value="3">机构管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('4')}">
							<td><input type="checkbox" name="perm" value="4" checked>供应商管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('4')}">
							<td><input type="checkbox" name="perm" value="4">供应商管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('5')}">
							<td><input type="checkbox" name="perm" value="5" checked>银行卡管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('5')}">
							<td><input type="checkbox" name="perm" value="5">银行卡管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('6')}">
							<td><input type="checkbox" name="perm" value="6" checked>商品管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('6')}">
							<td><input type="checkbox" name="perm" value="6">商品管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('7')}">
							<td><input type="checkbox" name="perm" value="7" checked>商品类别管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('7')}">
							<td><input type="checkbox" name="perm" value="7">商品类别管理</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('48')}">
							<td><input type="checkbox" name="perm" value="48" checked>代金卷管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('48')}">
							<td><input type="checkbox" name="perm" value="48">代金卷管理</td>
							</c:if>
						</tr>
					</table>
				</div>
				
				
				
				<div class="span3">
					<h4>数据报表模块<br>
					<table class="table	table-striped table-bordered" style="margin-top: 10px;">
						<tr>
							<c:if test="${role.permIdList.contains('60')}">
							<td><input type="checkbox" name="perm" value="60" checked>类别销售信息对比</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('60')}">
							<td><input type="checkbox" name="perm" value="60">类别销售信息对比</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('22')}">
							<td><input type="checkbox" name="perm" value="22" checked>类别销售信息一览(图形)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('22')}">
							<td><input type="checkbox" name="perm" value="22">类别销售信息一览(图形)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('23')}">
							<td><input type="checkbox" name="perm" value="23" checked>类别销售信息对比(图形)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('23')}">
							<td><input type="checkbox" name="perm" value="23">类别销售信息对比(图形)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('45')}">
							<td><input type="checkbox" name="perm" value="45" checked>销售信息排名(按类别)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('45')}">
							<td><input type="checkbox" name="perm" value="45">销售信息排名(按类别)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('24')}">
							<td><input type="checkbox" name="perm" value="24" checked>货商销售信息一览(图形)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('24')}">
							<td><input type="checkbox" name="perm" value="24">货商销售信息一览(图形)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('25')}">
							<td><input type="checkbox" name="perm" value="25" checked>货商销售信息对比(图形)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('25')}">
							<td><input type="checkbox" name="perm" value="25">货商销售信息对比(图形)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('44')}">
							<td><input type="checkbox" name="perm" value="44" checked>日销售信息对比</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('44')}">
							<td><input type="checkbox" name="perm" value="44">日销售信息对比</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('41')}">
							<td><input type="checkbox" name="perm" value="41" checked>月销售信息对比(图形)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('41')}">
							<td><input type="checkbox" name="perm" value="41">月销售信息对比(图形)</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${role.permIdList.contains('47')}">
							<td><input type="checkbox" name="perm" value="47" checked>门店备用金分析(图形)</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('47')}">
							<td><input type="checkbox" name="perm" value="47">门店备用金分析(图形)</td>
							</c:if>
						</tr>
					</table>
				</div>
				
				
				
				<div class="span3">
					<h4>行政人事模块<br>
					<table class="table	table-striped table-bordered" style="margin-top: 10px;">
						<tr>
							<c:if test="${role.permIdList.contains('58')}">
							<td><input type="checkbox" name="perm" value="58" checked>员工管理</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('58')}">
							<td><input type="checkbox" name="perm" value="58">员工管理</td>
							</c:if>
						</tr>
					</table>
				</div>
				
				<div class="span3">
					<h4>物流模块<br>
					<table class="table	table-striped table-bordered" style="margin-top: 10px;">
						<tr>
							<c:if test="${role.permIdList.contains('56')}">
							<td><input type="checkbox" name="perm" value="56" checked>物流</td>
							</c:if>
							<c:if test="${!role.permIdList.contains('56')}">
							<td><input type="checkbox" name="perm" value="56">物流</td>
							</c:if>
						</tr>
					</table>
				</div>
				<div class="span12">
					<div class="control-group">
						<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">
							保存
						</button>
						&nbsp;<a href="${sc_ctx}/role/list" class="btn btn-large">返回</a>
					</div>
				</div>
				</form:form>
			</div>
		</div>
	</body>
</html>