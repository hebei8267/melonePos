/**
 * 
 */
package com.tjhx.dao.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.order.ReplenishOrder;

public interface ReplenishOrderJpaDao extends CrudRepository<ReplenishOrder, Integer> {
	/**
	 * 取得调货单信息-根据调货单编号
	 * 
	 * @param orderNo
	 * @return
	 */
	@Query("select o from ReplenishOrder o where o.orderNo = :orderNo")
	public ReplenishOrder findByOrderNo(@Param("orderNo") String orderNo);
}
