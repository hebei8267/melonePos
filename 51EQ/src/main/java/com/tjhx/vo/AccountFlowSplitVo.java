package com.tjhx.vo;

import java.math.BigDecimal;

public class AccountFlowSplitVo {
	private Integer accountFlowUuid;
	/** 日期 */
	private String optDate;
	/** 余额 */
	private BigDecimal balanceAmt = new BigDecimal("0");
	/** 拨入来源 */
	private String inAmtDesc;
	/** 拨入金额 */
	private BigDecimal inAmt = new BigDecimal("0");
	/** 支出金额 */
	private BigDecimal outAmt = new BigDecimal("0");
	/** 支出大类 */
	private String outAmtLClass;
	/** 支出细类 */
	private String outAmtSClass;
	/** 备注 */
	private String descTxt;

	// ===============================================================

	/** 记账科目编号 */
	private String subId;
	/** 记账科目名称 */
	private String subName;
	/** 科目代码 */
	private String subCode;
	/** 记账金额 */
	private BigDecimal amt = new BigDecimal("0");

	/**
	 * @return the accountFlowUuid
	 */
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
	 * @return 日期
	 */
	public String getOptDate() {
		return optDate;
	}

	/**
	 * @param optDate 日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * @return 余额
	 */
	public BigDecimal getBalanceAmt() {
		return balanceAmt;
	}

	/**
	 * @param balanceAmt 余额
	 */
	public void setBalanceAmt(BigDecimal balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	/**
	 * @return 拨入来源
	 */
	public String getInAmtDesc() {
		return inAmtDesc;
	}

	/**
	 * @param inAmtDesc 拨入来源
	 */
	public void setInAmtDesc(String inAmtDesc) {
		this.inAmtDesc = inAmtDesc;
	}

	/**
	 * @return 拨入金额
	 */
	public BigDecimal getInAmt() {
		return inAmt;
	}

	/**
	 * @param inAmt 拨入金额
	 */
	public void setInAmt(BigDecimal inAmt) {
		this.inAmt = inAmt;
	}

	/**
	 * @return 支出金额
	 */
	public BigDecimal getOutAmt() {
		return outAmt;
	}

	/**
	 * @param outAmt 支出金额
	 */
	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	/**
	 * @return 支出大类
	 */
	public String getOutAmtLClass() {
		return outAmtLClass;
	}

	/**
	 * @param outAmtLClass 支出大类
	 */
	public void setOutAmtLClass(String outAmtLClass) {
		this.outAmtLClass = outAmtLClass;
	}

	/**
	 * @return 支出细类
	 */
	public String getOutAmtSClass() {
		return outAmtSClass;
	}

	/**
	 * @param outAmtSClass 支出细类
	 */
	public void setOutAmtSClass(String outAmtSClass) {
		this.outAmtSClass = outAmtSClass;
	}

	/**
	 * @return 备注
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * @param descTxt 备注
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * @return 记账科目编号
	 */
	public String getSubId() {
		return subId;
	}

	/**
	 * @param subId 记账科目编号
	 */
	public void setSubId(String subId) {
		this.subId = subId;
	}

	/**
	 * @return 记账科目名称
	 */
	public String getSubName() {
		return subName;
	}

	/**
	 * @param subName 记账科目名称
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}

	/**
	 * 取得科目代码
	 * 
	 * @return 科目代码
	 */
	public String getSubCode() {
		return subCode;
	}

	/**
	 * 设置科目代码
	 * 
	 * @param subCode 科目代码
	 */
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	/**
	 * @return 记账金额
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * @param amt 记账金额
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
}
