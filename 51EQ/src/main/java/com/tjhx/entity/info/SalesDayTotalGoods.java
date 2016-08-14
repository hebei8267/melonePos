package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 每日各店销售汇总(按商品)
 */
@Entity
@Table(name = "T_SALES_DAY_TOTAL_GOODS")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesDayTotalGoods extends IdEntity {

	private static final long serialVersionUID = -6412367714813044112L;

	/** 机构编号 */
	private String orgId;
	/** 机构资金编号 */
	private String branchNo;
	/** 日期 */
	private String optDate;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 短条码 */
	private String itemSubno;
	/** 长条码 */
	private String itemBarcode;
	/** 销售数量 */
	private BigDecimal posQty = new BigDecimal("0");
	/** 销售金额 */
	private BigDecimal posAmt = new BigDecimal("0");
	// ----------------------------------------------------------
	/** 日期-开始时间 */
	private String optDateStart;
	/** 日期-结束时间 */
	private String optDateEnd;
	/** 时间间隔（单位：天） */
	private long spanDay;
	/** 日均销量 */
	private BigDecimal averageDailySales = new BigDecimal("0");
	/** 机构名称 */
	private String orgName;
	/** 商品名字 */
	private String goodsName;
	/** 库存数量 */
	private BigDecimal stockQty = new BigDecimal("0");
	/** 库存金额 */
	private BigDecimal stockAmt = new BigDecimal("0");
	/** 销售金额 */
	private BigDecimal saleAmt = new BigDecimal("0");
	/** 种类编号 */
	private String itemNo;
	/** 供应商名称 */
	private String supName;
	/** 供应商编号 */
	private String supplierBwId;
	private String orderMode;

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
	 * 取得机构资金编号
	 * 
	 * @return branchNo 机构资金编号
	 */
	@Column(length = 8)
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
	 * 取得短条码
	 * 
	 * @return itemSubno 短条码
	 */
	@NaturalId
	@Column(length = 16)
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
	@Column(length = 16)
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
		if (null == posQty) {
			return new BigDecimal(0);
		}
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
		if (null == posAmt) {
			return new BigDecimal(0);
		}
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

	/**
	 * 取得日期-开始时间
	 * 
	 * @return optDateStart 日期-开始时间
	 */
	@Transient
	public String getOptDateStart() {
		return optDateStart;
	}

	/**
	 * 设置日期-开始时间
	 * 
	 * @param optDateStart 日期-开始时间
	 */
	public void setOptDateStart(String optDateStart) {
		this.optDateStart = optDateStart;
	}

	/**
	 * 取得日期-结束时间
	 * 
	 * @return optDateEnd 日期-结束时间
	 */
	@Transient
	public String getOptDateEnd() {
		return optDateEnd;
	}

	/**
	 * 设置日期-结束时间
	 * 
	 * @param optDateEnd 日期-结束时间
	 */
	public void setOptDateEnd(String optDateEnd) {
		this.optDateEnd = optDateEnd;
	}

	/**
	 * 取得 时间间隔（单位：天）
	 * 
	 * @return 时间间隔（单位：天）
	 */
	@Transient
	public long getSpanDay() {
		return spanDay;
	}

	/**
	 * 设置 时间间隔（单位：天）
	 * 
	 * @param spanDay 时间间隔（单位：天）
	 */
	public void setSpanDay(long spanDay) {
		this.spanDay = spanDay;
	}

	/**
	 * 取得日均销量
	 * 
	 * @return averageDailySales 日均销量
	 */
	@Transient
	public BigDecimal getAverageDailySales() {

		// return posQty.divide(new BigDecimal(spanDay), 1);

		return averageDailySales;
	}

	/**
	 * 设置日均销量
	 * 
	 * @param averageDailySales 日均销量
	 */
	public void setAverageDailySales(BigDecimal averageDailySales) {
		this.averageDailySales = averageDailySales;
	}

	/**
	 * 取得机构名称
	 * 
	 * @return orgName 机构名称
	 */
	@Transient
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
	 * 取得商品名字
	 * 
	 * @return goodsName 商品名字
	 */
	@Transient
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 设置商品名字
	 * 
	 * @param goodsName 商品名字
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * 取得库存数量
	 * 
	 * @return stockQty 库存数量
	 */
	@Transient
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
	@Transient
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
	 * 取得销售金额
	 * 
	 * @return saleAmt 销售金额
	 */
	@Transient
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置销售金额
	 * 
	 * @param saleAmt 销售金额
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得种类编号
	 * 
	 * @return itemNo 种类编号
	 */
	@Transient
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * 设置种类编号
	 * 
	 * @param itemNo 种类编号
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	/**
	 * 取得供应商名称
	 * 
	 * @return supName 供应商名称
	 */
	@Transient
	public String getSupName() {
		return supName;
	}

	/**
	 * 设置供应商名称
	 * 
	 * @param supName 供应商名称
	 */
	public void setSupName(String supName) {
		this.supName = supName;
	}

	/**
	 * 取得供应商编号
	 * 
	 * @return 供应商编号
	 */
	@Transient
	public String getSupplierBwId() {
		return supplierBwId;
	}

	/**
	 * 设置供应商编号
	 * 
	 * @param supplierBwId 供应商编号
	 */
	public void setSupplierBwId(String supplierBwId) {
		this.supplierBwId = supplierBwId;
	}

	/**
	 * 获取orderMode
	 * 
	 * @return orderMode orderMode
	 */
	@Transient
	public String getOrderMode() {
		return orderMode;
	}

	/**
	 * 设置orderMode
	 * 
	 * @param orderMode orderMode
	 */
	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}

}
