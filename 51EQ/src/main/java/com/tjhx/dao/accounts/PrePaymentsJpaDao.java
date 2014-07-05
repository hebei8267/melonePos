package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.PrePayments;

public interface PrePaymentsJpaDao extends CrudRepository<PrePayments, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得顾客/会员预付款（充值、消费）信息信息
	 * 
	 * @param orgId 机构信息
	 * @param optDateY 业务日期YYYY
	 * @param optDateM 业务日期MM
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Query("select p from PrePayments p where p.orgId = :orgId and p.optDateY = :optDateY and p.optDateM = :optDateM")
	public Iterable getPrePaymentsList_By_optDateYM(@Param("orgId") String orgId, @Param("optDateY") String optDateY,
			@Param("optDateM") String optDateM, Sort sort);

	/**
	 * 取得顾客/会员预付款（充值、消费）信息信息
	 * 
	 * @param orgId 机构信息
	 * @param optDateStart 业务日期-开始YYYYMMDD
	 * @param optDateEnd 业务日期-结束YYYYMMDD
	 * @param sort
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Query("select p from PrePayments p where p.orgId = :orgId and p.optDate <= :optDateStart and p.optDate >= :optDateEnd")
	public Iterable getPrePaymentsList_By_optDateInterval(@Param("orgId") String orgId,
			@Param("optDateStart") String optDateStart, @Param("optDateEnd") String optDateEnd, Sort sort);

	/**
	 * 取得顾客/会员预付款（充值、消费）信息信息
	 * 
	 * @param orgId 机构信息
	 * @param optDateStart 业务日期-开始YYYYMMDD
	 * @param optDateEnd 业务日期-结束YYYYMMDD
	 * @param phoneNum (顾客)电话号码
	 * @param sort
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Query("select p from PrePayments p where p.orgId = :orgId and p.optDate <= :optDateStart and p.optDate >= :optDateEnd and p.phoneNum like :phoneNum")
	public Iterable getPrePaymentsList_By_optDateInterval(@Param("orgId") String orgId,
			@Param("optDateStart") String optDateStart, @Param("optDateEnd") String optDateEnd,
			@Param("phoneNum") String phoneNum, Sort sort);
}
