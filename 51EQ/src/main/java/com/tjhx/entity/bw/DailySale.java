package com.tjhx.entity.bw;

import java.math.BigDecimal;

public class DailySale {

	/** 日期 */
	private String operDate;
	/** 机构编号 */
	private String orgBranchNo;
	/** 百威系统销售额 */
	private BigDecimal bwSaleAmt = new BigDecimal("0");
	/** 金卡操作金额(合计) */
	private BigDecimal operMoney = new BigDecimal("0");
	/** 支付宝销售额 */
	private BigDecimal bwZfbSaleAmt = new BigDecimal("0");
	/** 微信销售额 */
	private BigDecimal bwWxSaleAmt = new BigDecimal("0");
	/** 金卡操作类型 */
	private String operType;

	/**
	 * 取得日期
	 * 
	 * @return operDate 日期
	 */
	public String getOperDate() {
		return operDate;
	}

	/**
	 * 设置日期
	 * 
	 * @param operDate 日期
	 */
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	/**
	 * 取得机构编号
	 * 
	 * @return orgBranchNo 机构编号
	 */
	public String getOrgBranchNo() {
		return orgBranchNo;
	}

	/**
	 * 设置机构编号
	 * 
	 * @param orgBranchNo 机构编号
	 */
	public void setOrgBranchNo(String orgBranchNo) {
		this.orgBranchNo = orgBranchNo;
	}

	/**
	 * 取得百威系统销售额
	 * 
	 * @return bwSaleAmt 百威系统销售额
	 */
	public BigDecimal getBwSaleAmt() {
		return bwSaleAmt;
	}

	/**
	 * 设置百威系统销售额
	 * 
	 * @param bwSaleAmt 百威系统销售额
	 */
	public void setBwSaleAmt(BigDecimal bwSaleAmt) {
		this.bwSaleAmt = bwSaleAmt;
	}

	/**
	 * 获取金卡操作金额(合计)
	 * 
	 * @return operMoney
	 */
	public BigDecimal getOperMoney() {
		return operMoney;
	}

	/**
	 * 设置金卡操作金额(合计)
	 * 
	 * @param operMoney 金卡操作金额(合计)
	 */
	public void setOperMoney(BigDecimal operMoney) {
		this.operMoney = operMoney;
	}

	/**
	 * 获取金卡操作类型
	 * 
	 * @return operType
	 */
	public String getOperType() {
		return operType;
	}

	/**
	 * 设置金卡操作类型
	 * 
	 * @param operType 金卡操作类型
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}

	/**
	 * 取得支付宝销售额
	 * 
	 * @return bwZfbSaleAmt 支付宝销售额
	 */
	public BigDecimal getBwZfbSaleAmt() {
		return bwZfbSaleAmt;
	}

	/**
	 * 设置支付宝销售额
	 * 
	 * @param bwZfbSaleAmt 支付宝销售额
	 */
	public void setBwZfbSaleAmt(BigDecimal bwZfbSaleAmt) {
		this.bwZfbSaleAmt = bwZfbSaleAmt;
	}

	/**
	 * 取得微信销售额
	 * 
	 * @return bwWxSaleAmt 微信销售额
	 */
	public BigDecimal getBwWxSaleAmt() {
		return bwWxSaleAmt;
	}

	/**
	 * 设置微信销售额
	 * 
	 * @param bwWxSaleAmt 微信销售额
	 */
	public void setBwWxSaleAmt(BigDecimal bwWxSaleAmt) {
		this.bwWxSaleAmt = bwWxSaleAmt;
	}
}
