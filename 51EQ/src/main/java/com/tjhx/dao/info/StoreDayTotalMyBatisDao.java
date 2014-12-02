package com.tjhx.dao.info;

import java.util.List;

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

}
