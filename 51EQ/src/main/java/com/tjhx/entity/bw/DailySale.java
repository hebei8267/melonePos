package com.tjhx.entity.bw;

import java.math.BigDecimal;

public class DailySale {

	/** 日期 */
	private String operDate;
	/** 机构编号 */
	private String orgBranchNo;
	/** 百威系统销售额 */
	private BigDecimal bwSaleAmt = new BigDecimal("0");

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

}
