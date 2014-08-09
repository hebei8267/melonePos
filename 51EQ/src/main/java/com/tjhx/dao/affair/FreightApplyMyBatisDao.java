package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.FreightApply;

public interface FreightApplyMyBatisDao {

	/**
	 * 取得货运申请信息列表（指定调入机构或目的机构）--门店调用使用
	 * 
	 * @param freightApply
	 * @return
	 */
	public List<FreightApply> getFreightApplyList(FreightApply freightApply);

	/**
	 * 取得货运申请信息列表--总部调用使用
	 * 
	 * @param freightApply
	 * @return
	 */
	public List<FreightApply> getFreightApplyList_Manager(FreightApply freightApply);

	/**
	 * 取得已申请未审批（货运信息）数量
	 * 
	 * @return
	 */
	public int getApplyNotApprovalCount();

	/**
	 * 取得已审批未完成（货运信息）数量
	 * 
	 * @return
	 */
	public int getApprovalNotCompleteCount();

	/**
	 * 取得预计收货（货运信息）数量
	 * 
	 * @return
	 */
	public int getExpReceiptCount();

	/**
	 * 取得预计送货（货运信息）数量
	 * 
	 * @return
	 */
	public int getExpDeliveryCount();

}
