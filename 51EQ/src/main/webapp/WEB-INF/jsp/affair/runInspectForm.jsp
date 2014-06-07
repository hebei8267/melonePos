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
	     	$('#optDateShow').datepicker({
          		format : 'yyyy-mm-dd'
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
                        <h3>门店巡查报告(运营)
                        <c:if test="${empty	runInspect.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty runInspect.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                <div class="span3">
                	<label class="control-label">店铺名称 :</label>
                	<select name="orgId" class="input-small2">
                  	<c:forEach items="${orgList}" var="org">
                  		<c:if test="${org.key == orgId}">
                   		<option value="${org.key }" selected>${org.value }</option>
                      	</c:if>
                		<c:if test="${org.key != orgId}">
                    	<option value="${org.key }">${org.value }</option>
                     	</c:if>
                	</c:forEach>
             		</select>
				</div>
				<div class="span3">
                	<label class="control-label">当班负责人 :</label>
                	<input id="dutyPerson" name="dutyPerson" type="text" class="input-small2" value="${dutyPerson }"/>
				</div>
				<div class="span3">
                	<label class="control-label">评核日期 :</label>
                	<input id="optDateShow" name="optDateShow" type="text" class="input-small2" value="${optDateShow }"/>
				</div>
				<div class="span3">
                	<label class="control-label">评核员 :</label>
                	<input id="dutyPerson" name="dutyPerson" type="text" class="input-small2" value="${sessionScope.__SESSION_USER_INFO.name}" disabled/>
				</div>
				<div class="span8" style="margin-top: 20px;">
				<h4>收银台礼仪：（未得分项需注明具体原因）</h4>
				<table class="table	table-striped table-bordered table-condensed mytable">
					<tr>
						<input type="hidden" name="typeNo" value="01">
						<input type="hidden" name="id" value="00000001">
						<input type="hidden" name="score" value="10">
						<td width="450">1. 面带微笑 眼神交流</td>
						<td>&nbsp;
							<input type="radio" name="0100000001" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0100000001" value="1">
							<span>有</span>&nbsp;&nbsp;10分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="01">
						<input type="hidden" name="id" value="00000002">
						<input type="hidden" name="score" value="10">
						<td>2. 迎声 您好欢迎光临EQ+/infancy</td>
						<td>&nbsp;
							<input type="radio" name="0100000002" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0100000002" value="1">
							<span>有</span>&nbsp;&nbsp;10分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="01">
						<input type="hidden" name="id" value="00000003">
						<input type="hidden" name="score" value="20">
						<td>3. 是否双手接物</td>
						<td>&nbsp;
							<input type="radio" name="0100000003" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0100000003" value="1">
							<span>有</span>&nbsp;&nbsp;20分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="01">
						<input type="hidden" name="id" value="00000004">
						<input type="hidden" name="score" value="20">
						<td>4. 跟顾客核对商品数量</td>
						<td>&nbsp;
							<input type="radio" name="0100000004" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0100000004" value="1">
							<span>有</span>&nbsp;&nbsp;20分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="01">
						<input type="hidden" name="id" value="00000005">
						<input type="hidden" name="score" value="20">
						<td>5. 唱收唱付：现金/刷卡<br>
								&nbsp;&nbsp;&nbsp;双手接现金，现金唱收唱付，双手递过找零<br>
								&nbsp;&nbsp;&nbsp;核对POS机刷卡金额，勾出账单金额请顾客核对签字<br>
								&nbsp;&nbsp;&nbsp;双手递还所有单据及银行卡
						</td>
						<td>&nbsp;
							<input type="radio" name="0100000005" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0100000005" value="1">
							<span>有</span>&nbsp;&nbsp;20分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="01">
						<input type="hidden" name="id" value="00000006">
						<input type="hidden" name="score" value="10">
						<td>6. 帮顾客打包核对商品，双手递出</td>
						<td>&nbsp;
							<input type="radio" name="0100000006" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0100000006" value="1">
							<span>有</span>&nbsp;&nbsp;10分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="01">
						<input type="hidden" name="id" value="00000007">
						<input type="hidden" name="score" value="10">
						<td>7. 送声 欢迎您下次光临 微笑目送</td>
						<td>&nbsp;
							<input type="radio" name="0100000007" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0100000007" value="1">
							<span>有</span>&nbsp;&nbsp;10分
						</td>
					</tr>
				</table>
				</div>
				<div class="span8">
				<b>得分</b><br><br>
				<h5>意见或建议</h5>
				</div>
				
				
				<div class="span8" style="margin-top: 20px;">
				<h4>卖场巡检：（未得分项需注明具体原因）</h4><br>
				<h5>一.仪容仪表</h5>
				<table class="table	table-striped table-bordered table-condensed mytable">
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000001">
						<input type="hidden" name="score" value="5">
						<td width="450">1. 头发束起</td>
						<td>&nbsp;
							<input type="radio" name="0200000001" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000001" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000002">
						<input type="hidden" name="score" value="5">
						<td>2. 工作服是否整洁干净</td>
						<td>&nbsp;
							<input type="radio" name="0200000002" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000002" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000003">
						<input type="hidden" name="score" value="5">
						<td>3. 精神面貌是否良好</td>
						<td>&nbsp;
							<input type="radio" name="0200000003" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000003" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
				</table>
				</div>
				
				<div class="span8">
				<h5>二.店铺整理环境</h5>
				<table class="table	table-striped table-bordered table-condensed mytable">
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000004">
						<input type="hidden" name="score" value="5">
						<td width="450">1. 进店迎声 您好欢迎光临EQ+/infancy</td>
						<td>&nbsp;
							<input type="radio" name="0200000004" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000004" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000005">
						<input type="hidden" name="score" value="5">
						<td>2. 离店送声 慢走欢迎下次光临</td>
						<td>&nbsp;
							<input type="radio" name="0200000005" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000005" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000006">
						<input type="hidden" name="score" value="2">
						<td>3. 店内灯光是否柔和适度</td>
						<td>&nbsp;
							<input type="radio" name="0200000006" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000006" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000007">
						<input type="hidden" name="score" value="2">
						<td>4. 店内音乐是否让人听觉舒服</td>
						<td>&nbsp;
							<input type="radio" name="0200000007" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000007" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000008">
						<input type="hidden" name="score" value="2">
						<td>5. 店内温度让人体感觉舒适</td>
						<td>&nbsp;
							<input type="radio" name="0200000008" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000008" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000009">
						<input type="hidden" name="score" value="5">
						<td>6. 店铺整体感觉是否良好，有无货品，纸箱大量占道</td>
						<td>&nbsp;
							<input type="radio" name="0200000009" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000009" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
				</table>
				</div>
				
				
				
				<div class="span8">
				<h5>三.清洁卫生</h5>
				<table class="table	table-striped table-bordered table-condensed mytable">
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000010">
						<input type="hidden" name="score" value="5">
						<td width="450">1. 收银台是否整洁</td>
						<td>&nbsp;
							<input type="radio" name="0200000010" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000010" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000011">
						<input type="hidden" name="score" value="2">
						<td>2. 货架清洁</td>
						<td>&nbsp;
							<input type="radio" name="0200000011" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000011" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000012">
						<input type="hidden" name="score" value="2">
						<td>3. 堆码清洁</td>
						<td>&nbsp;
							<input type="radio" name="0200000012" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000012" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000013">
						<input type="hidden" name="score" value="2">
						<td>4. 商品清洁</td>
						<td>&nbsp;
							<input type="radio" name="0200000013" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000013" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000014">
						<input type="hidden" name="score" value="2">
						<td>5. 橱窗清洁</td>
						<td>&nbsp;
							<input type="radio" name="0200000014" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000014" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000015">
						<input type="hidden" name="score" value="2">
						<td>6. 地面清洁</td>
						<td>&nbsp;
							<input type="radio" name="0200000015" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000015" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
				</table>
				</div>
				
				
				
				<div class="span8">
				<h5>四.货品及陈列</h5>
				<table class="table	table-striped table-bordered table-condensed mytable">
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000016">
						<input type="hidden" name="score" value="2">
						<td width="450">1. 货架周围有无手写POP、过档物料及POP</td>
						<td>&nbsp;
							<input type="radio" name="0200000016" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000016" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000017">
						<input type="hidden" name="score" value="2">
						<td>2. 货品标签及宣传标签张贴和摆放是否正确</td>
						<td>&nbsp;
							<input type="radio" name="0200000017" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000017" value="1">
							<span>有</span>&nbsp;&nbsp;2分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000018">
						<input type="hidden" name="score" value="5">
						<td>3. 货品摆放位置是否合理</td>
						<td>&nbsp;
							<input type="radio" name="0200000018" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000018" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000019">
						<input type="hidden" name="score" value="5">
						<td>4. 货品摆放是否整齐</td>
						<td>&nbsp;
							<input type="radio" name="0200000019" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000019" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000020">
						<input type="hidden" name="score" value="5">
						<td>5. 员工是否清楚店内各种商品的库存量</td>
						<td>&nbsp;
							<input type="radio" name="0200000020" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000020" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000021">
						<input type="hidden" name="score" value="5">
						<td>6. 店内商品的定价是否存在问题</td>
						<td>&nbsp;
							<input type="radio" name="0200000021" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000021" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000022">
						<input type="hidden" name="score" value="5">
						<td>7. 店铺2个月内是否有最少一次的盘点</td>
						<td>&nbsp;
							<input type="radio" name="0200000022" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000022" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
				</table>
				</div>
				
				
				<div class="span8">
				<h5>五.服务</h5>
				<table class="table	table-striped table-bordered table-condensed mytable">
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000023">
						<input type="hidden" name="score" value="5">
						<td width="450">1. 服务用语</td>
						<td>&nbsp;
							<input type="radio" name="0200000023" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000023" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000024">
						<input type="hidden" name="score" value="5">
						<td>2. 服务主动性</td>
						<td>&nbsp;
							<input type="radio" name="0200000024" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000024" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000025">
						<input type="hidden" name="score" value="5">
						<td>3. 服务进度</td>
						<td>&nbsp;
							<input type="radio" name="0200000025" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000025" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
					<tr>
						<input type="hidden" name="typeNo" value="02">
						<input type="hidden" name="id" value="00000026">
						<input type="hidden" name="score" value="5">
						<td>4. 是否一站式服务</td>
						<td>&nbsp;
							<input type="radio" name="0200000026" value="0">
							<span>没有</span>&nbsp;&nbsp;0分
						</td>
						<td>&nbsp;
							<input type="radio" name="0200000026" value="1">
							<span>有</span>&nbsp;&nbsp;5分
						</td>
					</tr>
				</table>
				</div>
				
				<div class="span12">
				<h5>店铺反馈问题及跟进（人员，排休，货品问题，店铺硬件设施等）：</h5>
				</div>
				
				<div class="span12" style="margin-top: 20px;">
				<h5>货品问题的发现及跟进（价格，折扣，滞销，库存，协助调货安排，促销活动）：</h5>
				</div>
				
				<div class="span12" style="margin-top: 20px;">
				<h5>现场违纪违规及处罚情况（处罚标准：一般处罚20元，较严重问题50元，造成后果问题100元）：</h5>
				</div>
				
				
				<div class="span12" style="margin-top: 50px;">
				<center><h3>督导巡店三项工作统计</h3></center>
				</div>
				
				<div class="span12" style="margin-top: 20px;">
				<h5>一、	培训统计：（新员工、星级员工、店助、店长，参加《督导每周巡店员工培训事项安排》）</h5>
				</div>
				
				<div class="span12" style="margin-top: 20px;">
				<h5>二、	库存统计：（跟进店铺库存，抽查员工对店铺库存了解情况做好抽查记录，并详细记录所巡检店铺的负库存数及负库存金额，找出原因，制定解决方法，跟进过程，检察结果。每周五开会时提交各店总库存数量、金额，负库存数量、金额。）</h5>
				</div>
            </div>
        </div>
    </body>
</html>