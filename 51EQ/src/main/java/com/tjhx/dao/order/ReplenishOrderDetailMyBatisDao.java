/**
 * 
 */
package com.tjhx.dao.order;

public interface ReplenishOrderDetailMyBatisDao {
	/**
	 * 根据批次号删除要货单明细信息
	 * 
	 * @param batchId
	 */
	public void delReplenishOrderDetailByBatchId(String batchId);
}
