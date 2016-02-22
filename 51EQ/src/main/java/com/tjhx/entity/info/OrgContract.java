package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 机构合同
 */
@Entity
@Table(name = "T_ORG_CONTRACT")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgContract extends IdEntity {

	private static final long serialVersionUID = 2447632115185842723L;
	/** 机构编号 */
	private String orgId;
	/** 合同期限－开始时间 */
	private String startDate;
	/** 合同期限－结束时间 */
	private String endDate;
	/** 缴交频率 1-按季度 2-按月份 */
	private String payFrequent;
	/** 缴交金额（估计） */
	private BigDecimal computePayAmt = new BigDecimal("0");
	/** 优惠条款 */
	private String terms;

	/**
	 * 取得机构编号
	 * 
	 * @return 机构编号
	 */
	@NaturalId
	@Column(length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置机构编号
	 * 
	 * @param orgId 机构编号
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 取得合同期限－开始时间
	 * 
	 * @return 合同期限－开始时间
	 */
	@NaturalId
	@Column(length = 8)
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 设置合同期限－开始时间
	 * 
	 * @param startDate 合同期限－开始时间
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 取得合同期限－结束时间
	 * 
	 * @return 合同期限－结束时间
	 */
	@Column(length = 8)
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 设置合同期限－结束时间
	 * 
	 * @param endDate 合同期限－结束时间
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 取得缴交频率 1-按季度 2-按月份
	 * 
	 * @return 缴交频率
	 */
	@Column(length = 1)
	public String getPayFrequent() {
		return payFrequent;
	}

	/**
	 * 设置缴交频率 1-按季度 2-按月份
	 * 
	 * @param payFrequent 缴交频率
	 */
	public void setPayFrequent(String payFrequent) {
		this.payFrequent = payFrequent;
	}

	/**
	 * 取得缴交金额（估计）
	 * 
	 * @return 缴交金额（估计）
	 */
	public BigDecimal getComputePayAmt() {
		return computePayAmt;
	}

	/**
	 * 设置缴交金额（估计）
	 * 
	 * @param computePayAmt 缴交金额（估计）
	 */
	public void setComputePayAmt(BigDecimal computePayAmt) {
		this.computePayAmt = computePayAmt;
	}

	/**
	 * 取得优惠条款
	 * 
	 * @return 优惠条款
	 */
	public String getTerms() {
		return terms;
	}

	/**
	 * 设置优惠条款
	 * 
	 * @param terms 优惠条款
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}

}
