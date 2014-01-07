package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 每日各店销售汇总(按供应商)
 */
@Entity
@Table(name = "T_SALES_DAY_TOTAL_SUP")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesDayTotalSup extends IdEntity {

	private static final long serialVersionUID = 4165126758890224831L;
	/** 日期 */
	private String optDate;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 机构编号 */
	private String orgId;
	/** 供应商编号-百威 */
	private String supplierBwId;
	/** 机构资金编号 */
	private String branchNo;
	/** 前台销售数量 */
	private BigDecimal posQty;
	/** 前台销售金额 */
	private BigDecimal posAmt;
	/** 批发销售数量 */
	private BigDecimal pfQty;
	/** 批发销售金额 */
	private BigDecimal pfAmt;
	/** 合计销售数量 */
	private BigDecimal saleQty;
	/** 合计销售金额 */
	private BigDecimal saleAmt;
	/** 区域编码 */
	private String regionCode;

	/** 日期-开始时间 */
	private String optDateStart;
	/** 日期-结束时间 */
	private String optDateEnd;
	/** 供应商名称-百威 */
	private String supplierName;

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
	 * 取得供应商编号-百威
	 * 
	 * @return supplierBwId 供应商编号-百威
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
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
	 * 取得前台销售数量
	 * 
	 * @return posQty 前台销售数量
	 */
	public BigDecimal getPosQty() {
		return posQty;
	}

	/**
	 * 设置前台销售数量
	 * 
	 * @param posQty 前台销售数量
	 */
	public void setPosQty(BigDecimal posQty) {
		this.posQty = posQty;
	}

	/**
	 * 取得前台销售金额
	 * 
	 * @return posAmt 前台销售金额
	 */
	public BigDecimal getPosAmt() {
		return posAmt;
	}

	/**
	 * 设置前台销售金额
	 * 
	 * @param posAmt 前台销售金额
	 */
	public void setPosAmt(BigDecimal posAmt) {
		this.posAmt = posAmt;
	}

	/**
	 * 取得批发销售数量
	 * 
	 * @return pfQty 批发销售数量
	 */
	public BigDecimal getPfQty() {
		return pfQty;
	}

	/**
	 * 设置批发销售数量
	 * 
	 * @param pfQty 批发销售数量
	 */
	public void setPfQty(BigDecimal pfQty) {
		this.pfQty = pfQty;
	}

	/**
	 * 取得批发销售金额
	 * 
	 * @return pfAmt 批发销售金额
	 */
	public BigDecimal getPfAmt() {
		return pfAmt;
	}

	/**
	 * 设置批发销售金额
	 * 
	 * @param pfAmt 批发销售金额
	 */
	public void setPfAmt(BigDecimal pfAmt) {
		this.pfAmt = pfAmt;
	}

	/**
	 * 取得合计销售数量
	 * 
	 * @return saleQty 合计销售数量
	 */
	public BigDecimal getSaleQty() {
		return saleQty;
	}

	/**
	 * 设置合计销售数量
	 * 
	 * @param saleQty 合计销售数量
	 */
	public void setSaleQty(BigDecimal saleQty) {
		this.saleQty = saleQty;
	}

	/**
	 * 取得合计销售金额
	 * 
	 * @return saleAmt 合计销售金额
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置合计销售金额
	 * 
	 * @param saleAmt 合计销售金额
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得区域编码
	 * 
	 * @return regionCode 区域编码
	 */
	@Column(length = 16)
	public String getRegionCode() {
		return regionCode;
	}

	/**
	 * 设置区域编码
	 * 
	 * @param regionCode 区域编码
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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
	 * 取得供应商名称-百威
	 * 
	 * @return supplierName 供应商名称-百威
	 */
	@Transient
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 设置供应商名称-百威
	 * 
	 * @param supplierName 供应商名称-百威
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
