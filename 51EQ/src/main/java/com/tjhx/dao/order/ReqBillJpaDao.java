package com.tjhx.dao.order;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.order.ReqBill;

public interface ReqBillJpaDao extends CrudRepository<ReqBill, Integer> {

}
