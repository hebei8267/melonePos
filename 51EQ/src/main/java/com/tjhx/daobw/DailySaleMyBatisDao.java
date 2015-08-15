package com.tjhx.daobw;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.bw.DailySale;

public interface DailySaleMyBatisDao {

	/**
	 * 按日/门店编号取得百威系统销售额信息
	 * 
	 * @param operDate
	 */
	public List<DailySale> getDailySaleList(String operDate);

	/**
	 * 按日/门店编号取得百威系统金卡预存/销售额信息
	 * 
	 * @param operDateMap
	 * @return
	 */
	public List<DailySale> getDailyGoldCardSaleList(Map<String, String> operDateMap);

	/**
	 * 按日/门店编号取得支付宝系统销售额信息
	 * 
	 * @param operDateMap
	 */
	public List<DailySale> getDailyZFBSaleList(Map<String, String> operDateMap);
}
