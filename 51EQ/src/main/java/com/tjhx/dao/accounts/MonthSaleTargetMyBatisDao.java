package com.tjhx.dao.accounts;

import com.tjhx.entity.accounts.MonthSaleTarget;

public interface MonthSaleTargetMyBatisDao {

	/**
	 * 删除指定（年度）所有机构销售目标
	 * 
	 * @param optDateY
	 */
	public void delMonthSaleTargetInfo_All(String optDateY);

	/**
	 * 删除指定（年度/机构）销售目标
	 * 
	 * @param param.optDateY
	 * @param param.orgId
	 */
	public void delMonthSaleTargetInfo(MonthSaleTarget param);
}
