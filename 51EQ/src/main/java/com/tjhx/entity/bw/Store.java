package com.tjhx.entity.bw;

import java.math.BigDecimal;

/**
 * 库存信息
 */
public class Store {
	/** 货号 */
	private String itemSubno;
	/** 条形码 */
	private String itemBarcode;
	/** 商品名称 */
	private String itemName;
	/** 库存数量 */
	private BigDecimal stockQty;
	/** 库存金额 */
	private BigDecimal stockAmt;
	/** 售价金额 */
	private BigDecimal itemSaleAmt;

	/**
	 * 取得货号
	 * 
	 * @return itemSubno 货号
	 */
	public String getItemSubno() {
		return itemSubno;
	}

	/**
	 * 设置货号
	 * 
	 * @param itemSubno 货号
	 */
	public void setItemSubno(String itemSubno) {
		this.itemSubno = itemSubno;
	}

	/**
	 * 取得条形码
	 * 
	 * @return itemBarcode 条形码
	 */
	public String getItemBarcode() {
		return itemBarcode;
	}

	/**
	 * 设置条形码
	 * 
	 * @param itemBarcode 条形码
	 */
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
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
	 * 取得库存数量
	 * 
	 * @return stockQty 库存数量
	 */
	public BigDecimal getStockQty() {
		return stockQty;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param stockQty 库存数量
	 */
	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}

	/**
	 * 取得库存金额
	 * 
	 * @return stockAmt 库存金额
	 */
	public BigDecimal getStockAmt() {
		return stockAmt;
	}

	/**
	 * 设置库存金额
	 * 
	 * @param stockAmt 库存金额
	 */
	public void setStockAmt(BigDecimal stockAmt) {
		this.stockAmt = stockAmt;
	}

	/**
	 * 取得售价金额
	 * 
	 * @return itemSaleAmt 售价金额
	 */
	public BigDecimal getItemSaleAmt() {
		return itemSaleAmt;
	}

	/**
	 * 设置售价金额
	 * 
	 * @param itemSaleAmt 售价金额
	 */
	public void setItemSaleAmt(BigDecimal itemSaleAmt) {
		this.itemSaleAmt = itemSaleAmt;
	}
}
