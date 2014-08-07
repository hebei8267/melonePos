package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.FreightApplication;

public interface FreightApplicationMyBatisDao {

	/**
	 * 取得货运申请信息列表（指定调入机构或目的机构）--门店调用使用
	 * 
	 * @param freightApplication
	 * @return
	 */
	public List<FreightApplication> getFreightApplicationList(FreightApplication freightApplication);
	
	/**
	 * 取得货运申请信息列表--总部调用使用
	 * 
	 * @param freightApplication
	 * @return
	 */
	public List<FreightApplication> getFreightApplicationList_Manager(FreightApplication freightApplication);

}
