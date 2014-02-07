package com.tjhx.dao.info;

public interface SalesDayTotalMyBatisDao {
	/**
	 * 清除指定日期所有门店销售情况
	 */
	public void delSalesDayTotalInfo(String optDate);
}
