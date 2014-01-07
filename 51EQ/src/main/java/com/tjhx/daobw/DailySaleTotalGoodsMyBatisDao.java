package com.tjhx.daobw;

import java.util.List;

import com.tjhx.entity.bw.DailySaleTotalGoods;

public interface DailySaleTotalGoodsMyBatisDao {

	/**
	 * 取得指定门店指定日期的销售信息（按商品）
	 * 
	 * @param dailySaleTotal
	 * @return
	 */
	public List<DailySaleTotalGoods> getDailySaleTotalList(DailySaleTotalGoods dailySaleTotal);

}
