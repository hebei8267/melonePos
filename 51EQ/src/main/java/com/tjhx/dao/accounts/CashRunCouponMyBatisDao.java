package com.tjhx.dao.accounts;

import com.tjhx.entity.accounts.CashRunCoupon;

public interface CashRunCouponMyBatisDao {

	/**
	 * 删除指定（机构/日期/班次）销售流水-代金卷明细
	 * 
	 * @param CashRunCoupon(orgId/optDate/jobType)
	 */
	public void delCashRunCouponInfo(CashRunCoupon cashRunCoupon);

}
