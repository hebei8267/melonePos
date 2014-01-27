package com.tjhx.entity.order;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 门店要货单
 */
@Entity
@Table(name = "T_REQ_BILL_TMP")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReqBill extends IdEntity {

	private static final long serialVersionUID = 5023594671871878136L;
	/** 处理批次号 */
	private String batchId;
	/** 行号 */
	private Integer index;
	/** 机构编号 */
	private String orgId;
	/** 货号 */
	private String productNo;
	/** 条码 */
	private String barcode;
	/** 商品名称 */
	private String productName;
	/** 库存数量 */
	private Integer inventoryNum;
	/** 申请数量 */
	private Integer appNum;
	/** 参考进价 */
	private BigDecimal refPrice;
	/** 主供应商 */
	private String supplierName;
	/** 备注 */
	private String remarks;
	// -----------------------------------------------------
	/** 机构编号列表 */
	private List<String> orgIdList;
	/** 销售数量-合计 */
	private BigDecimal posQtyTotal;
	/** 销售数量-近1周 */
	private BigDecimal posQty1;
	/** 销售数量-近2周 */
	private BigDecimal posQty2;
	/** 销售数量-近3周 */
	private BigDecimal posQty3;
	/** 销售数量-近4周 */
	private BigDecimal posQty4;
	/** 库存数量 */
	private BigDecimal stockQty;
	/** 建议采购数量-低（平均值×1） */
	private BigDecimal lowPurchase;
	/** 建议采购数量-高（平均值×2） */
	private BigDecimal highPurchase;

	/** 销售金额-合计 */
	private BigDecimal posAmtTotal;
	/** 销售金额-近1周 */
	private BigDecimal posAmt1;
	/** 销售金额-近2周 */
	private BigDecimal posAmt2;
	/** 销售金额-近3周 */
	private BigDecimal posAmt3;
	/** 销售金额-近4周 */
	private BigDecimal posAmt4;

	/**
	 * 取得处理批次号
	 * 
	 * @return batchId 处理批次号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getBatchId() {
		return batchId;
	}

	/**
	 * 设置处理批次号
	 * 
	 * @param batchId 处理批次号
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * 取得行号
	 * 
	 * @return index 行号
	 */
	@NaturalId
	@Column(nullable = false, name = "_INDEX")
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置行号
	 * 
	 * @param index 行号
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
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
	 * 取得货号
	 * 
	 * @return productNo 货号
	 */
	@Column(length = 16)
	public String getProductNo() {
		return productNo;
	}

	/**
	 * 设置货号
	 * 
	 * @param productNo 货号
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	/**
	 * 取得条码
	 * 
	 * @return barcode 条码
	 */
	@Column(length = 16)
	public String getBarcode() {
		return barcode;
	}

	/**
	 * 设置条码
	 * 
	 * @param barcode 条码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * 取得商品名称
	 * 
	 * @return productName 商品名称
	 */
	@Column(length = 64)
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param productName 商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 取得库存数量
	 * 
	 * @return inventoryNum 库存数量
	 */
	public Integer getInventoryNum() {
		return inventoryNum;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param inventoryNum 库存数量
	 */
	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	/**
	 * 取得申请数量
	 * 
	 * @return appNum 申请数量
	 */
	public Integer getAppNum() {
		return appNum;
	}

	/**
	 * 设置申请数量
	 * 
	 * @param appNum 申请数量
	 */
	public void setAppNum(Integer appNum) {
		this.appNum = appNum;
	}

	/**
	 * 取得参考进价
	 * 
	 * @return refPrice 参考进价
	 */
	public BigDecimal getRefPrice() {
		return refPrice;
	}

	/**
	 * 设置参考进价
	 * 
	 * @param refPrice 参考进价
	 */
	public void setRefPrice(BigDecimal refPrice) {
		this.refPrice = refPrice;
	}

	/**
	 * 取得主供应商
	 * 
	 * @return supplierName 主供应商
	 */
	@Column(length = 32)
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 设置主供应商
	 * 
	 * @param supplierName 主供应商
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 取得备注
	 * 
	 * @return remarks 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 设置备注
	 * 
	 * @param remarks 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 取得机构编号列表
	 * 
	 * @return orgIdList 机构编号列表
	 */
	@Transient
	public List<String> getOrgIdList() {
		return orgIdList;
	}

	/**
	 * 设置机构编号列表
	 * 
	 * @param orgIdList 机构编号列表
	 */
	public void setOrgIdList(List<String> orgIdList) {
		this.orgIdList = orgIdList;
	}

	/**
	 * 取得销售数量-合计
	 * 
	 * @return posQtyTotal 销售数量-合计
	 */
	public BigDecimal getPosQtyTotal() {
		return posQtyTotal;
	}

	/**
	 * 设置销售数量-合计
	 * 
	 * @param posQtyTotal 销售数量-合计
	 */
	public void setPosQtyTotal(BigDecimal posQtyTotal) {
		this.posQtyTotal = posQtyTotal;
	}

	/**
	 * 取得销售数量-近1周
	 * 
	 * @return posQty1 销售数量-近1周
	 */
	@Transient
	public BigDecimal getPosQty1() {
		if (null == posQty1) {
			return new BigDecimal(0);
		}
		return posQty1;
	}

	/**
	 * 设置销售数量-近1周
	 * 
	 * @param posQty1 销售数量-近1周
	 */
	public void setPosQty1(BigDecimal posQty1) {
		this.posQty1 = posQty1;
	}

	/**
	 * 取得销售数量-近2周
	 * 
	 * @return posQty2 销售数量-近2周
	 */
	@Transient
	public BigDecimal getPosQty2() {
		if (null == posQty2) {
			return new BigDecimal(0);
		}
		return posQty2;
	}

	/**
	 * 设置销售数量-近2周
	 * 
	 * @param posQty2 销售数量-近2周
	 */
	public void setPosQty2(BigDecimal posQty2) {
		this.posQty2 = posQty2;
	}

	/**
	 * 取得销售数量-近3周
	 * 
	 * @return posQty3 销售数量-近3周
	 */
	@Transient
	public BigDecimal getPosQty3() {
		if (null == posQty3) {
			return new BigDecimal(0);
		}
		return posQty3;
	}

	/**
	 * 设置销售数量-近3周
	 * 
	 * @param posQty3 销售数量-近3周
	 */
	public void setPosQty3(BigDecimal posQty3) {
		this.posQty3 = posQty3;
	}

	/**
	 * 取得销售数量-近4周
	 * 
	 * @return posQty4 销售数量-近4周
	 */
	@Transient
	public BigDecimal getPosQty4() {
		if (null == posQty4) {
			return new BigDecimal(0);
		}
		return posQty4;
	}

	/**
	 * 设置销售数量-近4周
	 * 
	 * @param posQty4 销售数量-近4周
	 */
	public void setPosQty4(BigDecimal posQty4) {
		this.posQty4 = posQty4;
	}

	/**
	 * 取得库存数量
	 * 
	 * @return stockQty 库存数量
	 */
	@Transient
	public BigDecimal getStockQty() {
		if (null == stockQty) {
			return new BigDecimal(0);
		}
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
	 * 取得建议采购数量-低（平均值×1）
	 * 
	 * @return lowPurchase 建议采购数量-低（平均值×1）
	 */
	@Transient
	public BigDecimal getLowPurchase() {
		if (null == lowPurchase) {
			return new BigDecimal(0);
		}
		return lowPurchase;
	}

	/**
	 * 设置建议采购数量-低（平均值×1）
	 * 
	 * @param lowPurchase 建议采购数量-低（平均值×1）
	 */
	public void setLowPurchase(BigDecimal lowPurchase) {
		this.lowPurchase = lowPurchase;
	}

	/**
	 * 取得建议采购数量-高（平均值×2）
	 * 
	 * @return highPurchase 建议采购数量-高（平均值×2）
	 */
	@Transient
	public BigDecimal getHighPurchase() {
		if (null == highPurchase) {
			return new BigDecimal(0);
		}
		return highPurchase;
	}

	/**
	 * 设置建议采购数量-高（平均值×2）
	 * 
	 * @param highPurchase 建议采购数量-高（平均值×2）
	 */
	public void setHighPurchase(BigDecimal highPurchase) {
		this.highPurchase = highPurchase;
	}

	/**
	 * 取得销售金额-合计
	 * 
	 * @return posAmtTotal 销售金额-合计
	 */
	@Transient
	public BigDecimal getPosAmtTotal() {
		return posAmtTotal;
	}

	/**
	 * 设置销售金额-合计
	 * 
	 * @param posAmtTotal 销售金额-合计
	 */
	public void setPosAmtTotal(BigDecimal posAmtTotal) {
		this.posAmtTotal = posAmtTotal;
	}

	/**
	 * 取得销售金额-近1周
	 * 
	 * @return posAmt1 销售金额-近1周
	 */
	@Transient
	public BigDecimal getPosAmt1() {
		return posAmt1;
	}

	/**
	 * 设置销售金额-近1周
	 * 
	 * @param posAmt1 销售金额-近1周
	 */
	public void setPosAmt1(BigDecimal posAmt1) {
		this.posAmt1 = posAmt1;
	}

	/**
	 * 取得销售金额-近2周
	 * 
	 * @return posAmt2 销售金额-近2周
	 */
	@Transient
	public BigDecimal getPosAmt2() {
		return posAmt2;
	}

	/**
	 * 设置销售金额-近2周
	 * 
	 * @param posAmt2 销售金额-近2周
	 */
	public void setPosAmt2(BigDecimal posAmt2) {
		this.posAmt2 = posAmt2;
	}

	/**
	 * 取得销售金额-近3周
	 * 
	 * @return posAmt3 销售金额-近3周
	 */
	@Transient
	public BigDecimal getPosAmt3() {
		return posAmt3;
	}

	/**
	 * 设置销售金额-近3周
	 * 
	 * @param posAmt3 销售金额-近3周
	 */
	public void setPosAmt3(BigDecimal posAmt3) {
		this.posAmt3 = posAmt3;
	}

	/**
	 * 取得销售金额-近4周
	 * 
	 * @return posAmt4 销售金额-近4周
	 */
	@Transient
	public BigDecimal getPosAmt4() {
		return posAmt4;
	}

	/**
	 * 设置销售金额-近4周
	 * 
	 * @param posAmt4 销售金额-近4周
	 */
	public void setPosAmt4(BigDecimal posAmt4) {
		this.posAmt4 = posAmt4;
	}

}
