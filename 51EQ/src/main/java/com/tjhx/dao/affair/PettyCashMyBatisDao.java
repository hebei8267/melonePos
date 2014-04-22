package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.PettyCash;

public interface PettyCashMyBatisDao {

	/**
	 * 取得门店备用金分析信息 统计方式-门店月次
	 * 
	 * @param pettyCash
	 * @return
	 */
	public List<PettyCash> getPettyCashAnalyticsInfo_month(PettyCash pettyCash);

	/**
	 * 取得门店备用金分析信息 统计方式-门店合计
	 * 
	 * @param pettyCash
	 * @return
	 */
	public List<PettyCash> getPettyCashAnalyticsInfo_total(PettyCash pettyCash);

}
