/**
 * 
 */
package com.tjhx.dao.order;

public interface ReplenishOrderMyBatisDao {
	/**
	 * 根据批次号删除要货单信息
	 * 
	 * @param batchId
	 */
	public void delReplenishOrderByBatchId(String batchId);
}
