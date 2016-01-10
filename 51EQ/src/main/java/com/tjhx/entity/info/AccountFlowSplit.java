package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;

/**
 * 资金记账流水支出明细切分
 */
@Entity
@Table(name = "T_ACCOUNT_FLOW_SPLIT")
public class AccountFlowSplit extends IdEntity {

	private static final long serialVersionUID = -7253432114356840474L;
	/** 资金记账流水 */
	private AccountFlow accountFlow;
	/** 资金记账流水 */
	private Integer accountFlowUuid;
	/** 记账科目 */
	private AccountSubject accountSub;
	/** 记账科目编号 */
	private String subId;
	/** 记账科目名称 */
	private String subName;
	/** 记账金额 */
	private BigDecimal amt = new BigDecimal("0");
	/** Index */
	private Integer index;

	public AccountFlowSplit() {
	}

	public AccountFlowSplit(String subId, BigDecimal amt) {
		this.subId = subId;
		this.amt = amt;
	}

	/**
	 * 取得资金记账流水
	 * 
	 * @return 资金记账流水
	 */
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_FLOW_H_ID")
	public AccountFlow getAccountFlow() {
		return accountFlow;
	}

	/**
	 * 设置资金记账流水
	 * 
	 * @param accountFlow 资金记账流水
	 */
	public void setAccountFlow(AccountFlow accountFlow) {
		this.accountFlow = accountFlow;
	}

	/**
	 * @return the accountFlowUuid
	 */
	@Transient
	public Integer getAccountFlowUuid() {
		return accountFlowUuid;
	}

	/**
	 * @param accountFlowUuid the accountFlowUuid to set
	 */
	public void setAccountFlowUuid(Integer accountFlowUuid) {
		this.accountFlowUuid = accountFlowUuid;
	}

	/**
	 * 取得记账科目
	 * 
	 * @return 记账科目
	 */
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_SUB_H_ID")
	public AccountSubject getAccountSub() {
		return accountSub;
	}

	/**
	 * 设置记账科目
	 * 
	 * @param accountSub 记账科目
	 */
	public void setAccountSub(AccountSubject accountSub) {
		this.accountSub = accountSub;
	}

	/**
	 * 取得记账科目编号
	 * 
	 * @return 记账科目编号
	 */
	@Column(nullable = false, length = 32)
	public String getSubId() {
		return subId;
	}

	/**
	 * 设置记账科目编号
	 * 
	 * @param subId 记账科目编号
	 */
	public void setSubId(String subId) {
		this.subId = subId;
	}

	/**
	 * 取得记账科目名称
	 * 
	 * @return 记账科目名称
	 */
	@Transient
	public String getSubName() {
		return subName;
	}

	/**
	 * 设置记账科目名称
	 * 
	 * @param subName 记账科目名称
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}

	/**
	 * 取得记账金额
	 * 
	 * @return 记账金额
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * 设置记账金额
	 * 
	 * @param amt 记账金额
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * 取得Index
	 * 
	 * @return index Index
	 */
	@Basic
	@Column(name = "_INDEX")
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置Index
	 * 
	 * @param index Index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
}
