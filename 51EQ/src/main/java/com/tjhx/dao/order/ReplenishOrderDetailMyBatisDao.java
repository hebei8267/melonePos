/**
 * 
 */
package com.tjhx.dao.order;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.order.ReplenishOrderDetail;

public interface ReplenishOrderDetailMyBatisDao {
	/**
	 * 根据批次号删除要货单明细信息
	 * 
	 * @param batchId
	 */
	public void delReplenishOrderDetailByBatchId(String batchId);

	/**
	 * 取得调货单明细信息-根据调货单编号
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<ReplenishOrderDetail> findReplenishOrderDetailByOrderNo(Map<String, String> param);

	/**
	 * 根据补货单编号删除要货单明细信息
	 * 
	 * @param orderNo
	 */
	public void delReplenishOrderDetailByOrderNo(String orderNo);
}
