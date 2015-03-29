/**
 * 
 */
package com.tjhx.dao.order;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.order.ReplenishOrderDetail;

public interface ReplenishOrderDetailJpaDao extends CrudRepository<ReplenishOrderDetail, Integer> {

}
