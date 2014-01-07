package com.tjhx.entity.bw;

/**
 * 商品信息
 */
public class ItemGoods {
	/** 短条码 */
	private String itemSubno;
	/** 长条码 */
	private String itemBarcode;
	/** 商品名称 */
	private String itemName;
	/** 商品名称-拼音缩写 */
	private String itemSubname;
	/** 商品种类编号 */
	private String itemClsno;

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

}
