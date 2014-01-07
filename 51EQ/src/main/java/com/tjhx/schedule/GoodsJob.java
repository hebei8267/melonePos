package com.tjhx.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.GoodsManager;

/**
 * 商品信息Job
 */
public class GoodsJob implements IJob {

	private static Logger logger = LoggerFactory.getLogger(GoodsJob.class);
	@Autowired
	private GoodsManager goodsManager;

	@Override
	public void execute() throws Exception {
		logger.info("取得百威系统商品信息 Begin");
		// 取得百威系统各门店日销售信息
		goodsManager.bwDataSyn();
		logger.info("取得百威系统商品信息 End");
	}

}
