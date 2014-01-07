package com.tjhx.entity.bw;

import java.math.BigDecimal;

/**
 * 各店每日销售合计（按供应商）
 */
public class DailySaleTotalSup {
	/** 日期 */
	private String optDateStart;
	/** 日期 */
	private String optDateEnd;
	/** 机构资金编号 */
	private String branchNo;
	/** 前台销售数量 */
	private BigDecimal posQty;
	/** 前台销售金额 */
	private BigDecimal posAmt;
	/** 批发销售数量 */
	private BigDecimal pfQty;
	/** 批发销售金额 */
	private BigDecimal pfAmt;
	/** 合计销售数量 */
	private BigDecimal saleQty;
	/** 合计销售金额 */
	private BigDecimal saleAmt;
	/** 货商编码 */
	private String supcustNo;
	/** 区域编码 */
	private String regionNo;

	/**
	 * 取得日期
	 * 
	 * @return optDateStart 日期
	 */
	public String getOptDateStart() {
		return optDateStart;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDateStart 日期
	 */
	public void setOptDateStart(String optDateStart) {
		this.optDateStart = optDateStart;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDateEnd 日期
	 */
	public String getOptDateEnd() {
		return optDateEnd;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDateEnd 日期
	 */
	public void setOptDateEnd(String optDateEnd) {
		this.optDateEnd = optDateEnd;
	}

	/**
	 * 取得机构资金编号
	 * 
	 * @return branchNo 机构资金编号
	 */
	public String getBranchNo() {
		return branchNo;
	}

	/**
	 * 设置机构资金编号
	 * 
	 * @param branchNo 机构资金编号
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	/**
	 * 取得前台销售数量
	 * 
	 * @return posQty 前台销售数量
	 */
	public BigDecimal getPosQty() {
		return posQty;
	}

	/**
	 * 设置前台销售数量
	 * 
	 * @param posQty 前台销售数量
	 */
	public void setPosQty(BigDecimal posQty) {
		this.posQty = posQty;
	}

	/**
	 * 取得前台销售金额
	 * 
	 * @return posAmt 前台销售金额
	 */
	public BigDecimal getPosAmt() {
		return posAmt;
	}

	/**
	 * 设置前台销售金额
	 * 
	 * @param posAmt 前台销售金额
	 */
	public void setPosAmt(BigDecimal posAmt) {
		this.posAmt = posAmt;
	}

	/**
	 * 取得批发销售数量
	 * 
	 * @return pfQty 批发销售数量
	 */
	public BigDecimal getPfQty() {
		return pfQty;
	}

	/**
	 * 设置批发销售数量
	 * 
	 * @param pfQty 批发销售数量
	 */
	public void setPfQty(BigDecimal pfQty) {
		this.pfQty = pfQty;
	}

	/**
	 * 取得批发销售金额
	 * 
	 * @return pfAmt 批发销售金额
	 */
	public BigDecimal getPfAmt() {
		return pfAmt;
	}

	/**
	 * 设置批发销售金额
	 * 
	 * @param pfAmt 批发销售金额
	 */
	public void setPfAmt(BigDecimal pfAmt) {
		this.pfAmt = pfAmt;
	}

	/**
	 * 取得合计销售数量
	 * 
	 * @return saleQty 合计销售数量
	 */
	public BigDecimal getSaleQty() {
		return saleQty;
	}

	/**
	 * 设置合计销售数量
	 * 
	 * @param saleQty 合计销售数量
	 */
	public void setSaleQty(BigDecimal saleQty) {
		this.saleQty = saleQty;
	}

	/**
	 * 取得合计销售金额
	 * 
	 * @return saleAmt 合计销售金额
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置合计销售金额
	 * 
	 * @param saleAmt 合计销售金额
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得货商编码
	 * 
	 * @return supcustNo 货商编码
	 */
	public String getSupcustNo() {
		return supcustNo;
	}

	/**
	 * 设置货商编码
	 * 
	 * @param supcustNo 货商编码
	 */
	public void setSupcustNo(String supcustNo) {
		this.supcustNo = supcustNo;
	}

	/**
	 * 取得区域编码
	 * 
	 * @return regionNo 区域编码
	 */
	public String getRegionNo() {
		return regionNo;
	}

	/**
	 * 设置区域编码
	 * 
	 * @param regionNo 区域编码
	 */
	public void setRegionNo(String regionNo) {
		this.regionNo = regionNo;
	}

}
