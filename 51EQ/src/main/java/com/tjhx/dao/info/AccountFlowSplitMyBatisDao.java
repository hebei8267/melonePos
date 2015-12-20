package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.AccountFlowSplit;

public interface AccountFlowSplitMyBatisDao {

	/**
	 * 取得资金记账流水支出明细切分信息列表（根据记账信息UUID）
	 * 
	 * @param id 记账信息UUID
	 * @return
	 */
	List<AccountFlowSplit> getAccountFlowSplitByFlowUuid(Integer id);

	/**
	 * 删除
	 * 
	 * @param accountFlowUuid 记账信息UUID
	 */
	void delAccountFlowSplitByFlowUuid(int accountFlowUuid);

}
