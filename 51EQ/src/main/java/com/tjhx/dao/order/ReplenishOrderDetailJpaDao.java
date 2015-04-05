/**
 * 
 */
package com.tjhx.dao.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.order.ReplenishOrderDetail;

public interface ReplenishOrderDetailJpaDao extends CrudRepository<ReplenishOrderDetail, Integer> {

	/**
	 * @param orderNo
	 * @param productBarcode
	 */
	@Query("select d from ReplenishOrderDetail d where d.orderNo = :orderNo and d.productBarcode = :productBarcode")
	ReplenishOrderDetail findOneByOrderNoAndproductBarcode(@Param("orderNo") String orderNo,
			@Param("productBarcode") String productBarcode);

}
