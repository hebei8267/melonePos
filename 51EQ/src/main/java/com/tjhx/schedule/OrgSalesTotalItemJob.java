package com.tjhx.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.SalesDayTotalItemManager;
import com.tjhx.service.info.SalesMonthTotalItemManager;

/**
 * 每日/每月各店销售汇总Job(按商品分类)
 */
public class OrgSalesTotalItemJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(OrgSalesTotalItemJob.class);
	@Autowired
	private SalesDayTotalItemManager salesDayTotalItemManager;
	@Autowired
	private SalesMonthTotalItemManager salesMonthTotalItemManager;

	@Override
	public void execute() throws Exception {
		logger.info("取得百威系统各门店日销售信息(按商品分类) Begin");
		// 取得百威系统各门店日销售信息
		salesDayTotalItemManager.getOrgSalesDayTotal();
		logger.info("取得百威系统各门店日销售信息(按商品分类) End");

		// #################################################
		logger.info("门店月销售计算(按商品分类) Begin");
		// 门店月销售计算
		salesMonthTotalItemManager.calOrgSalesMonthTotal();
		logger.info("门店月销售计算(按商品分类) End");
	}
}
