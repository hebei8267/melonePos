package com.tjhx.dao.order;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.order.Coupon;

/**
 * 代金卷JpaDao
 */
public interface CouponJpaDao extends CrudRepository<Coupon, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得代金卷信息
	 * 
	 * @param couponNo 代金卷编号
	 * @return
	 */
	public List<Coupon> findByCouponNo(String couponNo, Sort sort);
}
