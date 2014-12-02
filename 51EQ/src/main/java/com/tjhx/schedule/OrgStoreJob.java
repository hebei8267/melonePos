package com.tjhx.schedule;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.StoreDetailManager;

/**
 * 各门店每日库存定时任务
 * 
 */
public class OrgStoreJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(OrgStoreJob.class);
	@Autowired
	private StoreDetailManager storeDetailManager;

	/**
	 * 定时计算打卡记录
	 * 
	 * @throws ParseException
	 */
	@Override
	public void execute() throws ParseException {
		logger.info("清理t_store_detail Begin");
		// 清理t_store_detail(数据量过大,其数据已汇总到t_store_day_total)
		// storeDetailManager.initTable();
		logger.info("清理t_store_detail End");

		// ##########################################################
		logger.info("取得门店库存信息 Begin");
		// 取得门店库存信息
		storeDetailManager.getOrgStoreDetail();
		logger.info("取得门店库存信息 End");

		// ##########################################################
		logger.info("计算门店库存合计信息 Begin");
		// 计算门店库存合计信息
		storeDetailManager.calOrgStoreDayTotal();
		logger.info("计算门店库存合计信息 End");

	}
}
