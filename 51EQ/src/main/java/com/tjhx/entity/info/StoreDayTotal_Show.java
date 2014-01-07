package com.tjhx.entity.info;

import java.math.BigDecimal;

public class StoreDayTotal_Show {

	/** 机构编号 */
	private String orgId;
	/** 机构名称 */
	private String orgName;
	/** 日期 */
	private String optDate;

	/** 库存数量 */
	private BigDecimal stockTotalQty;
	/** 库存金额 */
	private BigDecimal stockTotalAmt;
	/** 售价金额 */
	private BigDecimal itemSaleTotalAmt;

	/** 负库存数量 */
	private BigDecimal stockTotalQty_Minus;
	/** 负库存金额 */
	private BigDecimal stockTotalAmt_Minus;
	/** 负售价金额 */
	private BigDecimal itemSaleTotalAmt_Minus;

	public void initAmt() {
		// 库存数量
		this.stockTotalQty = new BigDecimal(0);
		// 库存金额
		this.stockTotalAmt = new BigDecimal(0);
		// 售价金额
		this.itemSaleTotalAmt = new BigDecimal(0);

		// 负库存数量
		this.stockTotalQty_Minus = new BigDecimal(0);
		// 负库存金额
		this.stockTotalAmt_Minus = new BigDecimal(0);
		// 负售价金额
		this.itemSaleTotalAmt_Minus = new BigDecimal(0);
	}

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
	 * 取得库存数量
	 * 
	 * @return stockTotalQty 库存数量
	 */
	public BigDecimal getStockTotalQty() {
		return stockTotalQty;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param stockTotalQty 库存数量
	 */
	public void setStockTotalQty(BigDecimal stockTotalQty) {
		this.stockTotalQty = stockTotalQty;
	}

	/**
	 * 取得库存金额
	 * 
	 * @return stockTotalAmt 库存金额
	 */
	public BigDecimal getStockTotalAmt() {
		return stockTotalAmt;
	}

	/**
	 * 设置库存金额
	 * 
	 * @param stockTotalAmt 库存金额
	 */
	public void setStockTotalAmt(BigDecimal stockTotalAmt) {
		this.stockTotalAmt = stockTotalAmt;
	}

	/**
	 * 取得售价金额
	 * 
	 * @return itemSaleTotalAmt 售价金额
	 */
	public BigDecimal getItemSaleTotalAmt() {
		return itemSaleTotalAmt;
	}

	/**
	 * 设置售价金额
	 * 
	 * @param itemSaleTotalAmt 售价金额
	 */
	public void setItemSaleTotalAmt(BigDecimal itemSaleTotalAmt) {
		this.itemSaleTotalAmt = itemSaleTotalAmt;
	}

	/**
	 * 取得负库存数量
	 * 
	 * @return stockTotalQty_Minus 负库存数量
	 */
	public BigDecimal getStockTotalQty_Minus() {
		return stockTotalQty_Minus;
	}

	/**
	 * 设置负库存数量
	 * 
	 * @param stockTotalQty_Minus 负库存数量
	 */
	public void setStockTotalQty_Minus(BigDecimal stockTotalQty_Minus) {
		this.stockTotalQty_Minus = stockTotalQty_Minus;
	}

	/**
	 * 取得负库存金额
	 * 
	 * @return stockTotalAmt_Minus 负库存金额
	 */
	public BigDecimal getStockTotalAmt_Minus() {
		return stockTotalAmt_Minus;
	}

	/**
	 * 设置负库存金额
	 * 
	 * @param stockTotalAmt_Minus 负库存金额
	 */
	public void setStockTotalAmt_Minus(BigDecimal stockTotalAmt_Minus) {
		this.stockTotalAmt_Minus = stockTotalAmt_Minus;
	}

	/**
	 * 取得负售价金额
	 * 
	 * @return itemSaleTotalAmt_Minus 负售价金额
	 */
	public BigDecimal getItemSaleTotalAmt_Minus() {
		return itemSaleTotalAmt_Minus;
	}

	/**
	 * 设置负售价金额
	 * 
	 * @param itemSaleTotalAmt_Minus 负售价金额
	 */
	public void setItemSaleTotalAmt_Minus(BigDecimal itemSaleTotalAmt_Minus) {
		this.itemSaleTotalAmt_Minus = itemSaleTotalAmt_Minus;
	}

	public void copyStoreDayTotalInfo_chart(StoreDayTotal storeDayTotal) {
		// 机构编号
		this.orgId = storeDayTotal.getOrgId();
		// 机构名称
		this.orgName = storeDayTotal.getOrgName();
		// 日期
		this.optDate = storeDayTotal.getOptDate();

		// 库存标记 0-正库存 1-负库存
		if ("0".equals(storeDayTotal.getStoreFlg())) {
			// 库存数量
			this.stockTotalQty = storeDayTotal.getStockTotalQty();
			// 库存金额
			this.stockTotalAmt = storeDayTotal.getStockTotalAmt();
			// 售价金额
			this.itemSaleTotalAmt = storeDayTotal.getItemSaleTotalAmt();
		} else {

			// 负库存数量
			this.stockTotalQty_Minus = storeDayTotal.getStockTotalQty().multiply(new BigDecimal(-1));
			// 负库存金额
			this.stockTotalAmt_Minus = storeDayTotal.getStockTotalAmt().multiply(new BigDecimal(-1));
			// 负售价金额
			this.itemSaleTotalAmt_Minus = storeDayTotal.getItemSaleTotalAmt().multiply(new BigDecimal(-1));
		}
	}

	public void copyStoreDayTotalInfo_list(StoreDayTotal storeDayTotal) {
		// 机构编号
		this.orgId = storeDayTotal.getOrgId();
		// 机构名称
		this.orgName = storeDayTotal.getOrgName();
		// 日期
		this.optDate = storeDayTotal.getOptDate();

		// 库存标记 0-正库存 1-负库存
		if ("0".equals(storeDayTotal.getStoreFlg())) {
			// 库存数量
			this.stockTotalQty = storeDayTotal.getStockTotalQty();
			// 库存金额
			this.stockTotalAmt = storeDayTotal.getStockTotalAmt();
			// 售价金额
			this.itemSaleTotalAmt = storeDayTotal.getItemSaleTotalAmt();
		} else {

			// 负库存数量
			this.stockTotalQty_Minus = storeDayTotal.getStockTotalQty();
			// 负库存金额
			this.stockTotalAmt_Minus = storeDayTotal.getStockTotalAmt();
			// 负售价金额
			this.itemSaleTotalAmt_Minus = storeDayTotal.getItemSaleTotalAmt();
		}
	}

}
