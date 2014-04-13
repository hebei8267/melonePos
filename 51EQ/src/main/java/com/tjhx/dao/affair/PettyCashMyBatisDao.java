package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.PettyCash;

public interface PettyCashMyBatisDao {

	/**取得门店备用金月度分析信息
	 * @param pettyCash
	 * @return
	 */
	public List<PettyCash> getPettyCashAnalyticsInfo(PettyCash pettyCash);

}
