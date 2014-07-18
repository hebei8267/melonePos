package com.tjhx.dao.accounts;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.PrePayments;

public interface PrePaymentsJpaDao extends CrudRepository<PrePayments, Integer> {

	/**
	 * 取得顾客/会员预付款（现金充值）合计信息
	 * 
	 * @param orgId 机构信息
	 * @param optDate 业务日期
	 * @param jobType 班次类型
	 * @return
	 */
	@Query("select sum(p.amt) from PrePayments p where p.orgId = :orgId and p.optDate = :optDate and p.inOutFlg = 1 and p.rechargeWay = 1 and p.jobType = :jobType")
	public BigDecimal getOrgPrePaymentsInfo_By_Cash(@Param("orgId") String orgId, @Param("optDate") String optDate,
			@Param("jobType") Integer jobType);

	/**
	 * 取得顾客/会员预付款（现金充值）合计信息
	 * 
	 * @param orgId 机构信息
	 * @param optDate 业务日期
	 * @return
	 */
	@Query("select sum(p.amt) from PrePayments p where p.orgId = :orgId and p.optDate = :optDate and p.inOutFlg = 1 and p.rechargeWay = 1")
	public BigDecimal getOrgPrePaymentsInfo_By_Cash(@Param("orgId") String orgId, @Param("optDate") String optDate);

	/**
	 * 取得顾客/会员预付款（刷卡充值）合计信息
	 * 
	 * @param orgId 机构信息
	 * @param optDate 业务日期
	 * @param jobType 班次类型
	 * @return
	 */
	@Query("select sum(p.amt) from PrePayments p where p.orgId = :orgId and p.optDate = :optDate and p.inOutFlg = 1 and p.rechargeWay = 2 and p.jobType = :jobType")
	public BigDecimal getOrgPrePaymentsInfo_By_Card(@Param("orgId") String orgId, @Param("optDate") String optDate,
			@Param("jobType") Integer jobType);

	/**
	 * 取得顾客/会员预付款（刷卡充值）合计信息
	 * 
	 * @param orgId 机构信息
	 * @param optDate 业务日期
	 * @param jobType 班次类型
	 * @return
	 */
	@Query("select sum(p.amt) from PrePayments p where p.orgId = :orgId and p.optDate = :optDate and p.inOutFlg = 1 and p.rechargeWay = 2")
	public BigDecimal getOrgPrePaymentsInfo_By_Card(@Param("orgId") String orgId, @Param("optDate") String optDate);

}
