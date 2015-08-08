/**
 * 
 */
package com.tjhx.dao.order;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.order.ReplenishOrder;

public interface ReplenishOrderMyBatisDao {
	/**
	 * 根据批次号删除要货单信息
	 * 
	 * @param batchId
	 */
	public void delReplenishOrderByBatchId(String batchId);

	/**
	 * 取得要货单列表
	 * 
	 * @param param
	 * @return
	 */
	public List<ReplenishOrder> getReplenishOrderList(Map<String, String> param);

	/**
	 * 根据补货单编号删除要货单信息
	 * 
	 * @param orderNo
	 */
	public void delReplenishOrderByOrderNo(String orderNo);

	/**
	 * 根据补货单门店错填信息列表
	 * 
	 * @param orgId(例如：01D)
	 */
	public List<ReplenishOrder> getReceiveErrNumInfo(Map<String, String> param);
}
