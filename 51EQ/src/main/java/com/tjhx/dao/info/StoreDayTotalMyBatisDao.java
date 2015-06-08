package com.tjhx.dao.info;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.info.StoreDayTotal;

public interface StoreDayTotalMyBatisDao {

	/**
	 * 取得最近的库存信息日期
	 * 
	 * @return
	 */
	public StoreDayTotal getMaxOptDate();

	/**
	 * 根据日期取得各门店库存信息
	 * 
	 * @param optDate
	 * @return
	 */
	public List<StoreDayTotal> getStoreDayTotalList(String optDate);

	/**
	 * @param optDate
	 */
	public void delStoreDayTotal(String optDate);

	/**
	 * 删除库存合计信息,仅保留近90天数据
	 * 
	 * @param optDate
	 */
	public void delStoreDayTotal_GreaterThan90Day(String _optDate);

	/**
	 * 根据门店库存合计信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<StoreDayTotal> getStoreTotalListGroupByDay(Map<String, String> param);
}
