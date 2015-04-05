package com.tjhx.entity.order;

import javax.persistence.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 补货单明细
 */
@Entity
@Table(name = "T_REPLENISH_ORDER_DETAIL")
@DynamicInsert
@DynamicUpdate
@XmlRootElement(name = "replenishOrderDetail")
public class ReplenishOrderDetail extends IdEntity {

	private static final long serialVersionUID = -3072737416581684692L;

	/** 补货单生成批次号 */
	private String orderBatchId;
	/** 补货单编号 */
	private String orderNo;
	/** 商品条码 */
	private String productBarcode;
	/** 补货(发货)数量 */
	private int replenishNum;
	/** 收货数量 */
	private int receiptNum;

	// =========================================================
	/** 商品名称 */
	private String goodsName;
	/** 商品类型 */
	private String goodsItemNo;
	/** 商品货商名称 */
	private String supplierName;

	/**
	 * 获取补货单生成批次号
	 * 
	 * @return orderBatchId
	 */
	@Column(nullable = false, length = 16)
	public String getOrderBatchId() {
		return orderBatchId;
	}

	/**
	 * 设置补货单生成批次号
	 * 
	 * @param orderBatchId 补货单生成批次号
	 */
	public void setOrderBatchId(String orderBatchId) {
		this.orderBatchId = orderBatchId;
	}

	/**
	 * 获取补货单编号
	 * 
	 * @return 补货单编号
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置补货单编号
	 * 
	 * @param orderNo 补货单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取商品条码
	 * 
	 * @return 商品条码
	 */
	@NaturalId
	@Column(length = 16, nullable = false)
	public String getProductBarcode() {
		return productBarcode;
	}

	/**
	 * 设置商品条码
	 * 
	 * @param productBarcode 商品条码
	 */
	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	/**
	 * 获取补货数量
	 * 
	 * @return 补货数量
	 */
	public int getReplenishNum() {
		return replenishNum;
	}

	/**
	 * 设置补货数量
	 * 
	 * @param replenishNum 补货数量
	 */
	public void setReplenishNum(int replenishNum) {
		this.replenishNum = replenishNum;
	}

	/**
	 * 获取收货数量
	 * 
	 * @return receiptNum
	 */
	public int getReceiptNum() {
		return receiptNum;
	}

	/**
	 * 设置收货数量
	 * 
	 * @param receiptNum 收货数量
	 */
	public void setReceiptNum(int receiptNum) {
		this.receiptNum = receiptNum;
	}

	// =========================================================

	/**
	 * 获取商品名称
	 * 
	 * @return goodsName
	 */
	@javax.persistence.Transient
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param goodsName 商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * 获取商品类型
	 * 
	 * @return goodsItemNo
	 */
	@Transient
	public String getGoodsItemNo() {
		return goodsItemNo;
	}

	/**
	 * 设置商品类型
	 * 
	 * @param goodsItemNo 商品类型
	 */
	public void setGoodsItemNo(String goodsItemNo) {
		this.goodsItemNo = goodsItemNo;
	}

	/**
	 * 获取商品货商名称
	 * 
	 * @return supplierName
	 */
	@Transient
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 设置商品货商名称
	 * 
	 * @param supplierName 商品货商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
