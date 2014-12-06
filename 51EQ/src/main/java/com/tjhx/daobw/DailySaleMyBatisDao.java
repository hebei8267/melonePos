package com.tjhx.daobw;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.bw.DailySale;

public interface DailySaleMyBatisDao {

	public List<DailySale> getDailySaleList(String operDate);

	/**
	 * 按日/门店编号取得百威系统金卡预存/销售额信息
	 * 
	 * @param operDateMap
	 * @return
	 */
	public List<DailySale> getDailyGoldCardSaleList(Map<String, String> operDateMap);
}
