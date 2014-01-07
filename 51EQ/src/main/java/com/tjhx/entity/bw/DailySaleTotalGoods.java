package com.tjhx.entity.bw;

import java.math.BigDecimal;

/**
 * 各店每日销售合计（按商品）
 */
public class DailySaleTotalGoods {

	/** 日期 */
	private String optDate;
	/** 机构资金编号 */
	private String branchNo;
	/** 商品名称 */
	private String itemName;
	/** 短条码 */
	private String itemSubno;
	/** 长条码 */
	private String itemBarcode;
	/** 销售数量 */
	private BigDecimal posQty;
	/** 销售金额 */
	private BigDecimal posAmt;

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
	 * 取得商品名称
	 * 
	 * @return itemName 商品名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param itemName 商品名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 取得短条码
	 * 
	 * @return itemSubno 短条码
	 */
	public String getItemSubno() {
		return itemSubno;
	}

	/**
	 * 设置短条码
	 * 
	 * @param itemSubno 短条码
	 */
	public void setItemSubno(String itemSubno) {
		this.itemSubno = itemSubno;
	}

	/**
	 * 取得长条码
	 * 
	 * @return itemBarcode 长条码
	 */
	public String getItemBarcode() {
		return itemBarcode;
	}

	/**
	 * 设置长条码
	 * 
	 * @param itemBarcode 长条码
	 */
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}

	/**
	 * 取得销售数量
	 * 
	 * @return posQty 销售数量
	 */
	public BigDecimal getPosQty() {
		return posQty;
	}

	/**
	 * 设置销售数量
	 * 
	 * @param posQty 销售数量
	 */
	public void setPosQty(BigDecimal posQty) {
		this.posQty = posQty;
	}

	/**
	 * 取得销售金额
	 * 
	 * @return posAmt 销售金额
	 */
	public BigDecimal getPosAmt() {
		return posAmt;
	}

	/**
	 * 设置销售金额
	 * 
	 * @param posAmt 销售金额
	 */
	public void setPosAmt(BigDecimal posAmt) {
		this.posAmt = posAmt;
	}

}
