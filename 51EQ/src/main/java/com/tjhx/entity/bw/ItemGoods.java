package com.tjhx.entity.bw;

import java.math.BigDecimal;

/**
 * 商品信息
 */
public class ItemGoods {
	/** 短条码 */
	private String itemSubno;
	/** 长条码 */
	private String itemBarcode;
	/** 商品售价 */
	private BigDecimal itemPrice;
	/** 商品成本价 */
	private BigDecimal costPrice;
	/** 商品名称 */
	private String itemName;
	/** 商品名称-拼音缩写 */
	private String itemSubname;
	/** 商品种类编号 */
	private String itemClsno;
	/** 供应商编号 */
	private String supcustNo;

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
	 * 取得商品名称-拼音缩写
	 * 
	 * @return itemSubname 商品名称-拼音缩写
	 */
	public String getItemSubname() {
		return itemSubname;
	}

	/**
	 * 设置商品名称-拼音缩写
	 * 
	 * @param itemSubname 商品名称-拼音缩写
	 */
	public void setItemSubname(String itemSubname) {
		this.itemSubname = itemSubname;
	}

	/**
	 * 取得商品种类编号
	 * 
	 * @return itemClsno 商品种类编号
	 */
	public String getItemClsno() {
		return itemClsno;
	}

	/**
	 * 设置商品种类编号
	 * 
	 * @param itemClsno 商品种类编号
	 */
	public void setItemClsno(String itemClsno) {
		this.itemClsno = itemClsno;
	}

	/**
	 * 取得供应商编号
	 * 
	 * @return supcustNo 供应商编号
	 */
	public String getSupcustNo() {
		return supcustNo;
	}

	/**
	 * 设置供应商编号
	 * 
	 * @param supcustNo 供应商编号
	 */
	public void setSupcustNo(String supcustNo) {
		this.supcustNo = supcustNo;
	}

	/**
	 * 取得商品售价
	 * 
	 * @return 商品售价
	 */
	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	/**
	 * 设置商品售价
	 * 
	 * @param itemPrice 商品售价
	 */
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * 取得商品成本价
	 * 
	 * @return 商品成本价
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}

	/**
	 * 设置商品成本价
	 * 
	 * @param costPrice 商品成本价
	 */
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

}
