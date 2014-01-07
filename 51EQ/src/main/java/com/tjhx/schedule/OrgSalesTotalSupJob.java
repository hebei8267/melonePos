package com.tjhx.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.SalesDayTotalSupManager;

/**
 * 每日各店销售汇总Job(按供应商)
 */
public class OrgSalesTotalSupJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(OrgSalesTotalSupJob.class);
	@Autowired
	private SalesDayTotalSupManager salesDayTotalSupManager;

	@Override
	public void execute() throws Exception {
		logger.info("取得百威系统各门店日销售信息(按供应商) Begin");
		// 取得百威系统各门店日销售信息(按供应商)
		salesDayTotalSupManager.getOrgSalesDayTotal();
		logger.info("取得百威系统各门店日销售信息(按供应商) End");

	}

}
