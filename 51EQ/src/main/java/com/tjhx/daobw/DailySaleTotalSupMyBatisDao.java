package com.tjhx.daobw;

import java.util.List;

import com.tjhx.entity.bw.DailySaleTotalSup;

public interface DailySaleTotalSupMyBatisDao {

	/**
	 * 取得指定门店指定日期的销售信息(按供货商分类)
	 * 
	 * @param dailySaleTotalSup
	 * @return
	 */
	public List<DailySaleTotalSup> getDailySaleTotalList(DailySaleTotalSup dailySaleTotalSup);

}
