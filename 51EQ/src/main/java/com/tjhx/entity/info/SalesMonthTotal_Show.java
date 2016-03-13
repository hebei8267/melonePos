package com.tjhx.entity.info;

import java.math.BigDecimal;

public class SalesMonthTotal_Show {

	/** 机构编号 */
	private String orgId;
	/** 机构数量 */
	private Integer orgCnt;
	/** 机构名称 */
	private String orgName;
	/** 月份 */
	private String optDateM;
	/** 日期 */
	private String optDateYM1;
	/** 日期 */
	private String optDateYM2;
	/** 日期 */
	private String optDateYM3;
	/** 日期 */
	private String optDateYM4;
	/** 实销金额 */
	private BigDecimal saleRamt1 = new BigDecimal(0);
	/** 实销金额 */
	private BigDecimal saleRamt2 = new BigDecimal(0);
	/** 实销金额 */
	private BigDecimal saleRamt3 = new BigDecimal(0);
	/** 实销金额 */
	private BigDecimal saleRamt4 = new BigDecimal(0);

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
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
	 * 取得机构数量
	 * 
	 * @return 机构数量
	 */
	public Integer getOrgCnt() {
		return orgCnt;
	}

	/**
	 * 设置机构数量
	 * 
	 * @param orgCnt 机构数量
	 */
	public void setOrgCnt(Integer orgCnt) {
		this.orgCnt = orgCnt;
	}

	/**
	 * 取得机构名称
	 * 
	 * @return orgName 机构名称
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置机构名称
	 * 
	 * @param orgName 机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 取得月份
	 * 
	 * @return optDateM 月份
	 */
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置月份
	 * 
	 * @param optDateM 月份
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDateYM1 日期
	 */
	public String getOptDateYM1() {
		return optDateYM1;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDateYM1 日期
	 */
	public void setOptDateYM1(String optDateYM1) {
		this.optDateYM1 = optDateYM1;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDateYM2 日期
	 */
	public String getOptDateYM2() {
		return optDateYM2;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDateYM2 日期
	 */
	public void setOptDateYM2(String optDateYM2) {
		this.optDateYM2 = optDateYM2;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDateYM3 日期
	 */
	public String getOptDateYM3() {
		return optDateYM3;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDateYM3 日期
	 */
	public void setOptDateYM3(String optDateYM3) {
		this.optDateYM3 = optDateYM3;
	}

	/**
	 * 取得实销金额
	 * 
	 * @return saleRamt1 实销金额
	 */
	public BigDecimal getSaleRamt1() {
		return saleRamt1;
	}

	/**
	 * 设置实销金额
	 * 
	 * @param saleRamt1 实销金额
	 */
	public void setSaleRamt1(BigDecimal saleRamt1) {
		this.saleRamt1 = saleRamt1;
	}

	/**
	 * 取得实销金额
	 * 
	 * @return saleRamt2 实销金额
	 */
	public BigDecimal getSaleRamt2() {
		return saleRamt2;
	}

	/**
	 * 设置实销金额
	 * 
	 * @param saleRamt2 实销金额
	 */
	public void setSaleRamt2(BigDecimal saleRamt2) {
		this.saleRamt2 = saleRamt2;
	}

	/**
	 * 取得实销金额
	 * 
	 * @return saleRamt3 实销金额
	 */
	public BigDecimal getSaleRamt3() {
		return saleRamt3;
	}

	/**
	 * 设置实销金额
	 * 
	 * @param saleRamt3 实销金额
	 */
	public void setSaleRamt3(BigDecimal saleRamt3) {
		this.saleRamt3 = saleRamt3;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDateYM4 日期
	 */
	public String getOptDateYM4() {
		return optDateYM4;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDateYM4 日期
	 */
	public void setOptDateYM4(String optDateYM4) {
		this.optDateYM4 = optDateYM4;
	}

	/**
	 * 取得实销金额
	 * 
	 * @return saleRamt4 实销金额
	 */
	public BigDecimal getSaleRamt4() {
		return saleRamt4;
	}

	/**
	 * 设置实销金额
	 * 
	 * @param saleRamt4 实销金额
	 */
	public void setSaleRamt4(BigDecimal saleRamt4) {
		this.saleRamt4 = saleRamt4;
	}

	public int myEquals(Object obj) {

		if (!(obj instanceof SalesMonthTotalItem))
			return 0;

		SalesMonthTotalItem rhs = (SalesMonthTotalItem) obj;

		if (!this.getOrgId().equals(rhs.getOrgId()))
			return 0;

		if (this.getOptDateYM1().equals(rhs.getOptDateYM()))
			return 1;

		if (this.getOptDateYM2().equals(rhs.getOptDateYM()))
			return 2;

		if (this.getOptDateYM3().equals(rhs.getOptDateYM()))
			return 3;

		if (this.getOptDateYM4().equals(rhs.getOptDateYM()))
			return 4;

		return 0;
	}

	public void copyData(SalesMonthTotalItem _salesMonthTotalItem, int equalsRes) {
		if (1 == equalsRes) {
			saleRamt1 = _salesMonthTotalItem.getSaleRamt();
		}

		if (2 == equalsRes) {
			saleRamt2 = _salesMonthTotalItem.getSaleRamt();
		}

		if (3 == equalsRes) {
			saleRamt3 = _salesMonthTotalItem.getSaleRamt();
		}

		if (4 == equalsRes) {
			saleRamt4 = _salesMonthTotalItem.getSaleRamt();
		}

		orgCnt = _salesMonthTotalItem.getOrgCnt();
	}
}
