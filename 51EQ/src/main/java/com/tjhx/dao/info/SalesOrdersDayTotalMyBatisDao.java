package com.tjhx.dao.info;

public interface SalesOrdersDayTotalMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售单日汇总信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesOrdersDayTotalInfo(String optDate);
}
