package com.tjhx.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.SalesDayTotalGoodsManager;
import com.tjhx.service.info.SalesWeekTotalGoodsManager;

/**
 * 每日/每月各店销售汇总Job(按商品)
 */
public class OrgSalesTotalGoodsJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(OrgSalesTotalGoodsJob.class);
	@Autowired
	private SalesDayTotalGoodsManager salesDayTotalGoodsManager;
	@Autowired
	private SalesWeekTotalGoodsManager salesWeekTotalGoodsManager;

	@Override
	public void execute() throws Exception {
		logger.info("取得百威系统各门店日销售信息(按商品) Begin");
		// 取得百威系统各门店日销售信息
		salesDayTotalGoodsManager.getOrgSalesDayTotal();
		logger.info("取得百威系统各门店日销售信息(按商品) End");

		// =================================================================
		// 以下方法依赖上面的数据
		// =================================================================

		logger.info("计算近一周销售商品合计 Begin");
		salesWeekTotalGoodsManager.calSalesWeekTotalGoodsInfo_1();
		logger.info("计算近一周销售商品合计 End");

		// -------------------------------------------
		logger.info("计算近两周销售商品合计 Begin");
		salesWeekTotalGoodsManager.calSalesWeekTotalGoodsInfo_2();
		logger.info("计算近两周销售商品合计 End");

		// -------------------------------------------
		logger.info("计算近三周销售商品合计 Begin");
		salesWeekTotalGoodsManager.calSalesWeekTotalGoodsInfo_3();
		logger.info("计算近三周销售商品合计 End");

		// -------------------------------------------
		logger.info("计算近四周销售商品合计 Begin");
		salesWeekTotalGoodsManager.calSalesWeekTotalGoodsInfo_4();
		logger.info("计算近四周销售商品合计 End");

	}

}
