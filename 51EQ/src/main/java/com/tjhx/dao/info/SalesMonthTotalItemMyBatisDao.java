package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.SalesMonthTotalItem;

public interface SalesMonthTotalItemMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesMonthTotalInfo(String optYMDate);

	/**
	 * 取得指定门店指定年份合计销售信息
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalList_ByOrgAndYear(SalesMonthTotalItem param);

	/**
	 * 取得全部门店指定年份合计销售信息
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalList_ByYear(SalesMonthTotalItem param);
}
