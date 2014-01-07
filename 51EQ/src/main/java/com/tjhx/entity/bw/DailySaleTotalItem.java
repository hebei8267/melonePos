package com.tjhx.entity.bw;

import java.math.BigDecimal;

/**
 * 各店每日销售合计（按大类别）
 */
public class DailySaleTotalItem {
	/** 日期 */
	private String optDate;
	/** 机构资金编号 */
	private String branchNo;
	/** 类别编号 */
	private String itemClsNo;
	/** 类别名称 */
	private String itemClsName;
	/** 销售数量 */
	private BigDecimal saleQty;
	/** 销售金额 */
	private BigDecimal saleAmt;
	/** 退货数量 */
	private BigDecimal retQty;
	/** 退货金额 */
	private BigDecimal retAmt;
	/** 赠送数量 */
	private BigDecimal giveQty;
	/** 实销数量 */
	private BigDecimal saleRqty;
	/** 实销金额 */
	private BigDecimal saleRamt;

	/**
	 * 取得日期
	 * 
	 * @return optDate 日期
	 */
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDate 日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
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
	 * 取得类别编号
	 * 
	 * @return itemClsNo 类别编号
	 */
	public String getItemClsNo() {
		return itemClsNo;
	}

	/**
	 * 设置类别编号
	 * 
	 * @param itemClsNo 类别编号
	 */
	public void setItemClsNo(String itemClsNo) {
		this.itemClsNo = itemClsNo;
	}

	/**
	 * 取得类别名称
	 * 
	 * @return itemClsName 类别名称
	 */
	public String getItemClsName() {
		return itemClsName;
	}

	/**
	 * 设置类别名称
	 * 
	 * @param itemClsName 类别名称
	 */
	public void setItemClsName(String itemClsName) {
		this.itemClsName = itemClsName;
	}

	/**
	 * 取得销售数量
	 * 
	 * @return saleQty 销售数量
	 */
	public BigDecimal getSaleQty() {
		return saleQty;
	}

	/**
	 * 设置销售数量
	 * 
	 * @param saleQty 销售数量
	 */
	public void setSaleQty(BigDecimal saleQty) {
		this.saleQty = saleQty;
	}

	/**
	 * 取得销售金额
	 * 
	 * @return saleAmt 销售金额
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置销售金额
	 * 
	 * @param saleAmt 销售金额
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得退货数量
	 * 
	 * @return retQty 退货数量
	 */
	public BigDecimal getRetQty() {
		return retQty;
	}

	/**
	 * 设置退货数量
	 * 
	 * @param retQty 退货数量
	 */
	public void setRetQty(BigDecimal retQty) {
		this.retQty = retQty;
	}

	/**
	 * 取得退货金额
	 * 
	 * @return retAmt 退货金额
	 */
	public BigDecimal getRetAmt() {
		return retAmt;
	}

	/**
	 * 设置退货金额
	 * 
	 * @param retAmt 退货金额
	 */
	public void setRetAmt(BigDecimal retAmt) {
		this.retAmt = retAmt;
	}

	/**
	 * 取得赠送数量
	 * 
	 * @return giveQty 赠送数量
	 */
	public BigDecimal getGiveQty() {
		return giveQty;
	}

	/**
	 * 设置赠送数量
	 * 
	 * @param giveQty 赠送数量
	 */
	public void setGiveQty(BigDecimal giveQty) {
		this.giveQty = giveQty;
	}

	/**
	 * 取得实销数量
	 * 
	 * @return saleRqty 实销数量
	 */
	public BigDecimal getSaleRqty() {
		return saleRqty;
	}

	/**
	 * 设置实销数量
	 * 
	 * @param saleRqty 实销数量
	 */
	public void setSaleRqty(BigDecimal saleRqty) {
		this.saleRqty = saleRqty;
	}

	/**
	 * 取得实销金额
	 * 
	 * @return saleRamt 实销金额
	 */
	public BigDecimal getSaleRamt() {
		return saleRamt;
	}

	/**
	 * 设置实销金额
	 * 
	 * @param saleRamt 实销金额
	 */
	public void setSaleRamt(BigDecimal saleRamt) {
		this.saleRamt = saleRamt;
	}

}
