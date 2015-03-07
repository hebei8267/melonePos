package com.tjhx.dao.info;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.info.SalesOrdersDayTotal;

public interface SalesOrdersDayTotalMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售单日汇总信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesOrdersDayTotalInfo(String optDate);

	/**
	 * @param param
	 * @return
	 */
	public List<SalesOrdersDayTotal> getSalesOrdersDayTotalList(Map<String, String> param);
}
