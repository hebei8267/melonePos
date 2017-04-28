package com.tjhx.daobw;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.bw.MShopDailySale;

public interface MShopDailySaleMyBatisDao {

	/**
	 * 取得指定门店日销售流水信息
	 * 
	 * @param operDate
	 */
	public List<MShopDailySale> getDailySaleList(Map<String, String> param);
}
