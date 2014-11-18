package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 每日各店库存明细
 */
@Entity
@Table(name = "T_STORE_DETAIL")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StoreDetail extends IdEntity {

	private static final long serialVersionUID = -1250637417448286070L;
	/** 机构编号 */
	private String orgId;
	/** 机构资金-百威 */
	private String bwBranchNo;
	/** 日期 */
	private String optDate;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 库存标记 0-正库存 1-负库存 */
	private String storeFlg;
	/** Index */
	private Integer index;
	// --------------------------------------------------------------
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
	// --------------------------------------------------------------
	/** 库存数量-合计 */
	private BigDecimal stockTotalQty;
	/** 库存金额-合计 */
	private BigDecimal stockTotalAmt;
	/** 售价金额-合计 */
	private BigDecimal itemSaleTotalAmt;
	/** 类别编号 */
	private String itemClsNo;

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@NaturalId
	@Column(name = "ORG_ID", nullable = false, length = 32)
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
	 * 取得机构资金-百威
	 * 
	 * @return bwBranchNo 机构资金-百威
	 */
	@Column(length = 8)
	public String getBwBranchNo() {
		return bwBranchNo;
	}

	/**
	 * 设置机构资金-百威
	 * 
	 * @param bwBranchNo 机构资金-百威
	 */
	public void setBwBranchNo(String bwBranchNo) {
		this.bwBranchNo = bwBranchNo;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDate 日期
	 */
	@NaturalId
	@Column(length = 8, nullable = false)
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
	 * 取得日期-年
	 * 
	 * @return optDateY 日期-年
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置日期-年
	 * 
	 * @param optDateY 日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得日期-月
	 * 
	 * @return optDateM 日期-月
	 */
	@Column(name = "OPT_DATE_M", length = 2)
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置日期-月
	 * 
	 * @param optDateM 日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得库存标记0-正库存1-负库存
	 * 
	 * @return storeFlg 库存标记0-正库存1-负库存
	 */
	@Column(length = 1)
	public String getStoreFlg() {
		return storeFlg;
	}

	/**
	 * 设置库存标记0-正库存1-负库存
	 * 
	 * @param storeFlg 库存标记0-正库存1-负库存
	 */
	public void setStoreFlg(String storeFlg) {
		this.storeFlg = storeFlg;
	}

	/**
	 * 取得Index
	 * 
	 * @return index Index
	 */
	@NaturalId
	@Column(name = "_INDEX", nullable = false)
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置Index
	 * 
	 * @param index Index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	// --------------------------------------------------------------
	/**
	 * 取得货号
	 * 
	 * @return itemSubno 货号
	 */
	@Column(length = 16)
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
	@Column(length = 16)
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
	@Column(length = 128)
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

	/**
	 * 取得库存数量-合计
	 * 
	 * @return stockTotalQty 库存数量-合计
	 */
	@Transient
	public BigDecimal getStockTotalQty() {
		return stockTotalQty;
	}

	/**
	 * 设置库存数量-合计
	 * 
	 * @param stockTotalQty 库存数量-合计
	 */
	public void setStockTotalQty(BigDecimal stockTotalQty) {
		this.stockTotalQty = stockTotalQty;
	}

	/**
	 * 取得库存金额-合计
	 * 
	 * @return stockTotalAmt 库存金额-合计
	 */
	@Transient
	public BigDecimal getStockTotalAmt() {
		return stockTotalAmt;
	}

	/**
	 * 设置库存金额-合计
	 * 
	 * @param stockTotalAmt 库存金额-合计
	 */
	public void setStockTotalAmt(BigDecimal stockTotalAmt) {
		this.stockTotalAmt = stockTotalAmt;
	}

	/**
	 * 取得售价金额-合计
	 * 
	 * @return itemSaleTotalAmt 售价金额-合计
	 */
	@Transient
	public BigDecimal getItemSaleTotalAmt() {
		return itemSaleTotalAmt;
	}

	/**
	 * 设置售价金额-合计
	 * 
	 * @param itemSaleTotalAmt 售价金额-合计
	 */
	public void setItemSaleTotalAmt(BigDecimal itemSaleTotalAmt) {
		this.itemSaleTotalAmt = itemSaleTotalAmt;
	}

	/**
	 * 获取类别编号
	 * 
	 * @return itemClsNo 类别编号
	 */
	@Transient
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

}
