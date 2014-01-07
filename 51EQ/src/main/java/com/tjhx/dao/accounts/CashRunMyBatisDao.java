package com.tjhx.dao.accounts;

import java.util.List;

import com.tjhx.entity.accounts.CashRun;

public interface CashRunMyBatisDao {

	public List<CashRun> getCashRunList(CashRun cashRun);

	public List<CashRun> getCashRunList_OptDate_Interval(CashRun cashRun);

	/**
	 * 删除指定（机构/大于日期）销售流水日结信息
	 * 
	 * @param cashDaily(orgId/optDate)
	 */
	public void delCashRunInfo(CashRun cashRun);

}
