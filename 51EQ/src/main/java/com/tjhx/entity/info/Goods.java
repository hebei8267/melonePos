package com.tjhx.entity.info;

import java.math.BigDecimal;

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
	/** 供应商编号-百威 */
	private String supplierBwId;
	/** 商品售价 */
	private BigDecimal salePrice;
	/** 商品成本价 */
	private BigDecimal costPrice;

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

	/**
	 * 取得供应商编号-百威
	 * 
	 * @return supplierBwId 供应商编号-百威
	 */
	@Column(length = 16)
	public String getSupplierBwId() {
		return supplierBwId;
	}

	/**
	 * 设置供应商编号-百威
	 * 
	 * @param supplierBwId 供应商编号-百威
	 */
	public void setSupplierBwId(String supplierBwId) {
		this.supplierBwId = supplierBwId;
	}

	/**
	 * 取得商品售价
	 * 
	 * @return 商品售价
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * 设置商品售价
	 * 
	 * @param salePrice 商品售价
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
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
