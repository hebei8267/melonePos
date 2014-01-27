package com.tjhx.dao.accounts;

import java.util.List;

import com.tjhx.entity.accounts.CashDaily;

public interface CashDailyMyBatisDao {

	public List<CashDaily> getCashDailyList(CashDaily cashDaily);

	public List<CashDaily> getCashDailyListByAllOrg(CashDaily cashDaily);

	public List<CashDaily> getCashDailyChartList(CashDaily cashDaily);

	public List<CashDaily> getAllOrgCashDailyChartList(CashDaily cashDaily);

	/**
	 * 取得指定机构最终销售流水日结信息
	 * 
	 * @param orgId
	 * @return
	 */
	public CashDaily getLastCashDailyInfoByOrg(String orgId);

	/**
	 * 删除指定（机构/日期）销售流水日结信息
	 * 
	 * @param cashDaily(orgId/optDate)
	 */
	public void delCashDailyInfo(CashDaily cashDaily);
}
