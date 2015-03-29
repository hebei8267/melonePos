/**
 * 
 */
package com.tjhx.dao.order;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.order.ReplenishOrder;

public interface ReplenishOrderJpaDao extends CrudRepository<ReplenishOrder, Integer> {

}
