<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<style>
		.font2 {
			font-family : "Microsoft YaHei" ! important;
			font-size : 16px;
			font-weight : bolder;
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
	                    <h3>月销售信息对比(列表)[按督导]</h3>
	                </legend>
	            </div>
	            
	            <div class="span12" style="text-align: right;"><a href="${sc_ctx}/salesMonthItemChartReport/mngUser_init">月销售信息对比(图形)[按督导]</a></div>
	            
	            <div class="span12" style="margin-top: 15px;">
	            	<table class="table	table-striped table-bordered table-condensed mytable">
	                    <tbody>
	                    	<tr>
	                            <td>统计日期</th>
	                            <td class="right">${optDate }</td>
	                            <td>当月任务额</td>
	                            <td class="right">${monthTaskAmt }</td>
	                            <td>当前完成额</td>
	                            <td class="right">${monthCompleteAmt }</td>
	                        </tr>
	                        <tr>
	                            <td>预计本月销售</td>
	                            <td class="right">${expMonthAmt }</td>
	                            <td>统计店铺数量</td>
	                            <td class="right">${orgCnt }</td>
	                            <td>任务完成店数</td>
	                            <td class="right">${orgCompleteCnt }</td>
	                        </tr>
	                    </tbody>
	            	</table>
	            </div>
	            
	            <div class="span12" style="margin-top: 15px;">
	            	<table class="table	table-striped table-bordered table-condensed mytable">
	            		<thead>
		            		<tr>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            督导
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            店铺<br>数量
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            本月<br>任务
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            当前<br>已完成
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            当前<br>完成率
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            预计<br>完成
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            预计<br>完成率
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            任务<br>增长<br>金额
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            任务<br>完成<br>店数<br>(全部)
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            任务<br>完成<br>店数<br>百分比
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            任务<br>贡献率<br>(区域)
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                            销售<br>贡献率<br>(区域)
		                        </th>
		                        <th class="center" style="background-image: linear-gradient(to bottom,#62c462,#51a351);">
		                        	销售<br>贡献<br>指数<br>增幅<br>(区域)
		                        </th>
		                    </tr>
		            	</thead>
		            	
		            	<tbody>
		            	<c:forEach items="${info.sDetailList}" var="sDetail" varStatus="status1">
		            		<tr>
		            			<td class="center">${sDetail.mngUserName }</td>
		            			<td class="center">${sDetail.orgCnt }</td>
		            			<td class="center">${sDetail.saleMonthTargetAmt }</td>
		            			<td class="center">${sDetail.amtByNow }</td>
		            			<td class="center">${sDetail.cmpRateByNow }%</td>
		            			<td class="center">${sDetail.expMonthAmt }</td>
		            			<td class="center">${sDetail.expCmpRateByNow }%</td>
		            			
		            			<c:if test="${sDetail.taskGrowthamt>=0}" >
								<td class="right font2" style="color : #FF0000">↑ ${sDetail.taskGrowthamt }</td>
								</c:if>
								<c:if test="${sDetail.taskGrowthamt<0}" >
		            			<td class="right font2" style="color : #5bc0de">↓ ${sDetail.taskGrowthamt }</td>
		            			</c:if>
		            			
		            			<td class="center">${sDetail.taskCmpOrgCnt }</td>
		            			<td class="center">${sDetail.taskCmpOrgRate }%</td>
		            			
		            			<c:if test="${(status1.index+1) == fn:length(info.sDetailList)}" >
		            			<td class="center">-</td>
		            			<td class="center">-</td>
		            			<td class="center">-</td>
		            			</c:if>
		            			
		            			<c:if test="${(status1.index+1) != fn:length(info.sDetailList)}" >
		            			<td class="center">${sDetail.expAmtRate }%</td>
		            			<td class="center">${sDetail.amtRate }%</td>
		            			
		            			<c:if test="${sDetail.ratescope>=0}" >
								<td class="center font2" style="color : #FF0000">↑ ${sDetail.ratescope }%</td>
								</c:if>
								<c:if test="${sDetail.ratescope<0}" >
		            			<td class="center font2" style="color : #5bc0de">↓ ${sDetail.ratescope }%</td>
		            			</c:if>
		            			
		            			</c:if>
		            		</tr>
		            	</c:forEach>
		            	</tbody>
	            	</table>
	            </div>
	            
	            <c:forEach items="${info.mngOrgMap}" var="map">
	            	<div class="span12" style="margin-top: 15px;">
	            		<table class="table	table-striped table-bordered table-condensed mytable">
	            			<thead>
                                <tr>
                                    <th class="center">
                                        督导
                                    </th>
                                    <th class="center">
                                        机构
                                    </th>
                                    <th class="center">
                                        本月<br>任务
                                    </th>
                                    <th class="center">
                                        当前<br>已完成
                                    </th>
                                    <th class="center">
                                        当前<br>完成率
                                    </th>
                                    <th class="center">
                                        预计<br>完成
                                    </th>
                                    <th class="center">
                                        预计<br>完成率
                                    </th>
                                    <th class="center">
                                        任务<br>增长<br>金额
                                    </th>
                                    <th class="center">
                                        任务<br>完成<br>店数<br>(区域)
                                    </th>
                                    <th class="center">
                                        店任务<br>贡献
                                    </th>
                                    <th class="center">
                                        当月<br>销售<br>贡献率<br>(店铺)
                                    </th>
                                    <th class="center">
                                        当月<br>任务<br>贡献率<br>(店铺)
                                    </th>
                                    <th class="center">
                                    	销售<br>贡献<br>指数<br>增幅<br>(店铺)
                                    </th>
                                    <th class="center">
                                    	当月<br>销售<br>贡献率<br>(全部)
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${map.value}" var="orgDetail" varStatus="status1">
                            	<tr>
                            		<c:if test="${status1.index == 0}" >
		                            <td class="center" rowspan="${fn:length(map.value)}">${orgDetail.mngUserName }</th>
		                            </c:if>
		                            
		                            <td class="center">${fn:substring(orgDetail.orgId,3,6) }</td>
		                            <td class="right">${orgDetail.saleMonthTargetAmt }</td>
		                            <td class="right">${orgDetail.amtByNow }</td>
		                            <td class="center">${orgDetail.cmpRateByNow }%</td>
		                            <td class="right">${orgDetail.expMonthAmt }</td>
		                            <td class="center">${orgDetail.expCmpRateByNow }%</td>
		                            
		                            <c:if test="${orgDetail.taskGrowthamt>=0}" >
									<td class="right font2" style="color : #FF0000">↑ ${orgDetail.taskGrowthamt }</td>
									</c:if>
									<c:if test="${orgDetail.taskGrowthamt<0}" >
			            			<td class="right font2" style="color : #5bc0de">↓ ${orgDetail.taskGrowthamt }</td>
			            			</c:if>
		            			
		                            
		                            <c:if test="${status1.index == 0}" >
		                            <td rowspan="${fn:length(map.value)}" class="center">${orgDetail.taskCmpOrgCnt }</th>
		                            <td rowspan="${fn:length(map.value)}" class="center">${orgDetail.expAmtRateTotal }%</th>
		                            </c:if>
		                            
		                            <td class="center">${orgDetail.amtRate }%</td>
		                            <td class="center">${orgDetail.expAmtRate }%</td>
		                            
		                            <c:if test="${orgDetail.ratescope>=0}" >
									<td class="center font2" style="color : #FF0000">↑ ${orgDetail.ratescope }%</td>
									</c:if>
									<c:if test="${orgDetail.ratescope<0}" >
			            			<td class="center font2" style="color : #5bc0de">↓ ${orgDetail.ratescope }%</td>
			            			</c:if>
			            			
		                            <td class="center">${orgDetail.amtRateTotal }%</td>
		                            
	                        	</tr>
                            	</c:forEach>
                            </tbody>
	            		</table>
	            	</div>
	            </c:forEach>
	            
	            
	    	</div>
     	</div>
     	
     </body>
</html>