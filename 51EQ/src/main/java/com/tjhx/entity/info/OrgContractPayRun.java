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
@Table(name = "T_ORG_CONTRACT_PAY_RUN")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgContractPayRun extends IdEntity {

	private static final long serialVersionUID = 2447632115185842723L;
	/** 机构编号 */
	private String orgId;
	/** 缴交时间安排 */
	private String payDate;
	/** 缴交金额 */
	private BigDecimal payAmt = new BigDecimal("0");
	/** 是否缴款 0-未缴费 1-已缴费 */
	private String payFlg;

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
	 * 取得缴交时间安排
	 * 
	 * @return 缴交时间安排
	 */
	@Column(length = 8)
	public String getPayDate() {
		return payDate;
	}

	/**
	 * 设置缴交时间安排
	 * 
	 * @param payDate 缴交时间安排
	 */
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	/**
	 * 取得缴交金额
	 * 
	 * @return 缴交金额
	 */
	public BigDecimal getPayAmt() {
		return payAmt;
	}

	/**
	 * 设置缴交金额
	 * 
	 * @param payAmt 缴交金额
	 */
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	/**
	 * 取得是否缴款 0-未缴费 1-已缴费
	 * 
	 * @return 是否缴款 0-未缴费 1-已缴费
	 */
	@Column(length = 1)
	public String getPayFlg() {
		return payFlg;
	}

	/**
	 * 设置是否缴款 0-未缴费 1-已缴费
	 * 
	 * @param payFlg 是否缴款 0-未缴费 1-已缴费
	 */
	public void setPayFlg(String payFlg) {
		this.payFlg = payFlg;
	}

}
