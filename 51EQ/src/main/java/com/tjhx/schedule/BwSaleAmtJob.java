package com.tjhx.schedule;

import java.text.ParseException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.info.OrgWeekSalesRankingManager;
import com.tjhx.service.info.SalesDayTotalManager;

/**
 * (百威系统销售额)
 * 
 * 被Spring的Quartz MethodInvokingJobDetailFactoryBean定时执行的普通Spring Bean.
 */
public class BwSaleAmtJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(BwSaleAmtJob.class);
	@Autowired
	private CashDailyManager cashDailyManager;
	@Resource
	private SalesDayTotalManager salesDayTotalManager;
	@Resource
	private OrgWeekSalesRankingManager orgWeekSalesRankingManager;

	@Override
	public void execute() throws ParseException {
		logger.info("同步百威销售额 Begin");
		cashDailyManager.synBwSaleAmt();
		logger.info("同步百威销售额 End");

		logger.info("同步支付宝销售额 Begin");
		cashDailyManager.synZFBSaleAmt();
		logger.info("同步支付宝销售额 End");

		logger.info("同步微信销售额 Begin");
		cashDailyManager.synWXSaleAmt();
		logger.info("同步微信销售额 End");

		logger.info("同步百威金卡销售额 Begin");
		cashDailyManager.synBwGoldCardSaleAmt();
		logger.info("同步百威金卡销售额 End");

		logger.info("同步百威销售额（销售增长率） Begin");
		salesDayTotalManager.synBwSaleAmt();
		logger.info("同步百威销售额（销售增长率） End");

		logger.info("计算机构销售等级／排名 Begin");
		orgWeekSalesRankingManager.calOrgWeekSalesRanking();
		logger.info("计算机构销售等级／排名 End");
	}

}
