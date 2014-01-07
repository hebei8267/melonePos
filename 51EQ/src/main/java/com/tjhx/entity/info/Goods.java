package com.tjhx.entity.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 商品
 */
@Entity
@Table(name = "T_GOODS")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Goods extends IdEntity {

	private static final long serialVersionUID = 8277828799640545113L;
	/** 短条码 */
	private String subno;
	/** 长条码 */
	private String barcode;
	/** 商品名称 */
	private String name;
	/** 商品名称-拼音缩写 */
	private String pyName;
	/** 商品种类编号 */
	private String itemNo;

	/**
	 * 取得短条码
	 * 
	 * @return subno 短条码
	 */
	@NaturalId
	@Column(length = 16)
	public String getSubno() {
		return subno;
	}

	/**
	 * 设置短条码
	 * 
	 * @param subno 短条码
	 */
	public void setSubno(String subno) {
		this.subno = subno;
	}

	/**
	 * 取得长条码
	 * 
	 * @return barcode 长条码
	 */
	@Column(length = 16)
	public String getBarcode() {
		return barcode;
	}

	/**
	 * 设置长条码
	 * 
	 * @param barcode 长条码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * 取得商品名称
	 * 
	 * @return name 商品名称
	 */
	@Column(length = 64)
	public String getName() {
		return name;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param name 商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得商品名称-拼音缩写
	 * 
	 * @return pyName 商品名称-拼音缩写
	 */
	@Column(length = 32)
	public String getPyName() {
		return pyName;
	}

	/**
	 * 设置商品名称-拼音缩写
	 * 
	 * @param pyName 商品名称-拼音缩写
	 */
	public void setPyName(String pyName) {
		this.pyName = pyName;
	}

	/**
	 * 取得商品种类编号
	 * 
	 * @return itemNo 商品种类编号
	 */
	@Column(length = 8)
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * 设置商品种类编号
	 * 
	 * @param itemNo 商品种类编号
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

}
