package com.tjhx.dao.info;

public interface SalesWeekTotalGoodsMyBatisDao {

	/**
	 * 清除近1周销售合计情况
	 */
	public void delSalesWeekTotalGoodsInfo_1();

	/**
	 * 清除近2周销售合计情况
	 */
	public void delSalesWeekTotalGoodsInfo_2();

	/**
	 * 清除近3周销售合计情况
	 */
	public void delSalesWeekTotalGoodsInfo_3();

	/**
	 * 清除近4周销售合计情况
	 */
	public void delSalesWeekTotalGoodsInfo_4();
}
