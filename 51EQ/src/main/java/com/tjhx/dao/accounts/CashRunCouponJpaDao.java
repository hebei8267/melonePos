package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.CashRunCoupon;

public interface CashRunCouponJpaDao extends CrudRepository<CashRunCoupon, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@SuppressWarnings("rawtypes")
	@Query("select c from CashRunCoupon c where c.orgId = :orgId and c.optDate = :optDate and c.jobType = :jobType")
	public Iterable getCashRunCouponList(@Param("orgId") String orgId, @Param("optDate") String optDate,
			@Param("jobType") Integer jobType);
}
