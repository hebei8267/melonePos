package com.tjhx.daobw;

import java.util.List;

import com.tjhx.entity.bw.DailySaleTotalItem;

public interface DailySaleTotalItemMyBatisDao {

	/**
	 * 取得指定门店指定日期的销售信息
	 * 
	 * @param dailySaleTotal
	 * @return
	 */
	public List<DailySaleTotalItem> getDailySaleTotalList(DailySaleTotalItem dailySaleTotal);

}
