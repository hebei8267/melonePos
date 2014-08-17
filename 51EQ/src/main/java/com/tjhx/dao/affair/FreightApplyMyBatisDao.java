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
	 * 取得已申请（货运信息）数量
	 * 
	 * @return
	 */
	public int getApplyCount();

	/**
	 * 取得已审批（货运信息）数量
	 * 
	 * @return
	 */
	public int getApprovalCount();

	/**
	 * 取得预收货（货运信息）数量
	 * 
	 * @return
	 */
	public int getExpReceiptCount();

	/**
	 * 取得已打包（货运信息）数量
	 * 
	 * @return
	 */
	public int getPackedCount();

	/**
	 * 取得已收货（货运信息）数量
	 * 
	 * @return
	 */
	public int getActReceiptCount();

	/**
	 * 取得预送货（货运信息）数量
	 * 
	 * @return
	 */
	public int getExpDeliveryCount();

	/**
	 * 已送货（货运信息）数量
	 * 
	 * @return
	 */
	public int getActDeliveryCount();

	/**
	 * 取得门店发货（货运信息）数量
	 * 
	 * @return
	 */
	public int getOrgActDeliveryCount();

	/**
	 * 取得门店收货（货运信息）数量
	 * 
	 * @return
	 */
	public int getOrgActReceiptCount();

	/**
	 * 取得送货完结（货运信息）数量
	 * 
	 * @return
	 */
	public int getActDeliveryCount_Complete();

	/**
	 * 取得门店收货完结（货运信息）数量
	 * 
	 * @return
	 */
	public int getOrgActReceiptCount_Complete();
}
