package com.tjhx.entity.accounts;

import java.math.BigDecimal;
import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.info.Supplier;

/**
 * 货物入库流水
 */
@Entity
@Table(name = "T_STORE_RUN")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StoreRun extends IdEntity {

	private static final long serialVersionUID = -5665981916597548958L;
	/** 机构编号 */
	private String orgId;
	/** 入库单号 */
	private String recordNo;
	/** 供应商 */
	private Supplier supplier;
	/** 供应商编号-百威 */
	private String supplierBwId;
	/** 供应商名称 */
	private String supplierName;
	/** 开单日期 */
	private String recordDate;
	/** 开单日期-显示 */
	private String recordDateShow;
	/** 开单日期-年 */
	private String recordDateY;
	/** 开单日期-月 */
	private String recordDateM;
	/** 入库日期 */
	private String intoDate;
	/** 入库日期-显示 */
	private String intoDateShow;
	/** 入库日期-年 */
	private String intoDateY;
	/** 入库日期-月 */
	private String intoDateM;
	/** 统筹日期 */
	private String planDate;
	/** 统筹日期-显示 */
	private String planDateShow;
	/** 统筹日期-年 */
	private String planDateY;
	/** 统筹日期-月 */
	private String planDateM;
	/** 入库类型 */
	private String storeType;
	/** 开单金额 */
	private BigDecimal recordAmt = new BigDecimal("0");
	/** 入库金额 */
	private BigDecimal optAmt = new BigDecimal("0");
	/** 入库人名称 */
	private String optPerName;
	/** 店长审核 */
	private boolean auditFlg = false;
	/** 备注 */
	private String descTxt;
	// ############################################################################################
	/** 可编辑标记 */
	private Boolean editFlg = false;
	/** 机构名称 */
	private String orgName;

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@NaturalId
	@Column(name = "ORG_ID", length = 32)
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
	 * 取得入库单号
	 * 
	 * @return recordNo 入库单号
	 */
	@NaturalId
	@Column(name = "RECORD_NO", length = 32)
	public String getRecordNo() {
		return recordNo;
	}

	/**
	 * 设置入库单号
	 * 
	 * @param recordNo 入库单号
	 */
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	/**
	 * 取得供应商
	 * 
	 * @return supplier 供应商
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_H_ID")
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * 设置供应商
	 * 
	 * @param supplier 供应商
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * 取得供应商编号-百威
	 * 
	 * @return supplierBwId 供应商编号-百威
	 */
	@Column(name = "SUPPLIER_BW_ID", length = 32)
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
	 * 取得开单日期
	 * 
	 * @return recordDate 开单日期
	 */
	@Column(name = "RECORD_DATE", length = 8)
	public String getRecordDate() {
		return recordDate;
	}

	/**
	 * 设置开单日期
	 * 
	 * @param recordDate 开单日期
	 */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	/**
	 * 取得开单日期-显示
	 * 
	 * @return recordDateShow 开单日期-显示
	 */
	@Column(name = "RECORD_DATE_SHOW", length = 10)
	public String getRecordDateShow() {
		return recordDateShow;
	}

	/**
	 * 设置开单日期-显示
	 * 
	 * @param recordDateShow 开单日期-显示
	 */
	public void setRecordDateShow(String recordDateShow) {
		this.recordDateShow = recordDateShow;
	}

	/**
	 * 取得开单日期-年
	 * 
	 * @return recordDateY 开单日期-年
	 */
	@Column(name = "RECORD_DATE_Y", length = 4)
	public String getRecordDateY() {
		return recordDateY;
	}

	/**
	 * 设置开单日期-年
	 * 
	 * @param recordDateY 开单日期-年
	 */
	public void setRecordDateY(String recordDateY) {
		this.recordDateY = recordDateY;
	}

	/**
	 * 取得开单日期-月
	 * 
	 * @return recordDateM 开单日期-月
	 */
	@Column(name = "RECORD_DATE_M", length = 2)
	public String getRecordDateM() {
		return recordDateM;
	}

	/**
	 * 设置开单日期-月
	 * 
	 * @param recordDateM 开单日期-月
	 */
	public void setRecordDateM(String recordDateM) {
		this.recordDateM = recordDateM;
	}

	/**
	 * 取得入库日期
	 * 
	 * @return intoDate 入库日期
	 */
	@Column(name = "INTO_DATE", length = 8)
	public String getIntoDate() {
		return intoDate;
	}

	/**
	 * 设置入库日期
	 * 
	 * @param intoDate 入库日期
	 */
	public void setIntoDate(String intoDate) {
		this.intoDate = intoDate;
	}

	/**
	 * 取得入库日期-显示
	 * 
	 * @return intoDateShow 入库日期-显示
	 */
	@Column(name = "INTO_DATE_SHOW", length = 10)
	public String getIntoDateShow() {
		return intoDateShow;
	}

	/**
	 * 设置入库日期-显示
	 * 
	 * @param intoDateShow 入库日期-显示
	 */
	public void setIntoDateShow(String intoDateShow) {
		this.intoDateShow = intoDateShow;
	}

	/**
	 * 取得入库日期-年
	 * 
	 * @return intoDateY 入库日期-年
	 */
	@Column(name = "INTO_DATE_Y", length = 4)
	public String getIntoDateY() {
		return intoDateY;
	}

	/**
	 * 设置入库日期-年
	 * 
	 * @param intoDateY 入库日期-年
	 */
	public void setIntoDateY(String intoDateY) {
		this.intoDateY = intoDateY;
	}

	/**
	 * 取得入库日期-月
	 * 
	 * @return intoDateM 入库日期-月
	 */
	@Column(name = "INTO_DATE_M", length = 2)
	public String getIntoDateM() {
		return intoDateM;
	}

	/**
	 * 设置入库日期-月
	 * 
	 * @param intoDateM 入库日期-月
	 */
	public void setIntoDateM(String intoDateM) {
		this.intoDateM = intoDateM;
	}

	/**
	 * 取得开单金额
	 * 
	 * @return recordAmt 开单金额
	 */
	@Column(name = "RECORD_AMT")
	public BigDecimal getRecordAmt() {
		return recordAmt;
	}

	/**
	 * 设置开单金额
	 * 
	 * @param recordAmt 开单金额
	 */
	public void setRecordAmt(BigDecimal recordAmt) {
		this.recordAmt = recordAmt;
	}

	/**
	 * 取得入库金额
	 * 
	 * @return optAmt 入库金额
	 */
	@Column(name = "OPT_AMT")
	public BigDecimal getOptAmt() {
		return optAmt;
	}

	/**
	 * 设置入库金额
	 * 
	 * @param optAmt 入库金额
	 */
	public void setOptAmt(BigDecimal optAmt) {
		this.optAmt = optAmt;
	}

	/**
	 * 取得入库人名称
	 * 
	 * @return optPerName 入库人名称
	 */
	@Column(name = "OPT_PER_NAME", length = 32)
	public String getOptPerName() {
		return optPerName;
	}

	/**
	 * 设置入库人名称
	 * 
	 * @param optPerName 入库人名称
	 */
	public void setOptPerName(String optPerName) {
		this.optPerName = optPerName;
	}

	/**
	 * 取得店长审核
	 * 
	 * @return auditFlg 店长审核
	 */
	@Column(name = "AUDIT_FLG")
	public boolean isAuditFlg() {
		return auditFlg;
	}

	/**
	 * 设置店长审核
	 * 
	 * @param auditFlg 店长审核
	 */
	public void setAuditFlg(boolean auditFlg) {
		this.auditFlg = auditFlg;
	}

	/**
	 * 取得备注
	 * 
	 * @return descTxt 备注
	 */
	@Column(name = "DESC_TXT")
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置备注
	 * 
	 * @param descTxt 备注
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得统筹日期
	 * 
	 * @return planDate 统筹日期
	 */
	@Column(length = 8)
	public String getPlanDate() {
		return planDate;
	}

	/**
	 * 设置统筹日期
	 * 
	 * @param planDate 统筹日期
	 */
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	/**
	 * 取得统筹日期-显示
	 * 
	 * @return planDateShow 统筹日期-显示
	 */
	@Column(length = 10)
	public String getPlanDateShow() {
		return planDateShow;
	}

	/**
	 * 设置统筹日期-显示
	 * 
	 * @param planDateShow 统筹日期-显示
	 */
	public void setPlanDateShow(String planDateShow) {
		this.planDateShow = planDateShow;
	}

	/**
	 * 取得统筹日期-年
	 * 
	 * @return planDateY 统筹日期-年
	 */
	@Column(name = "PLAN_DATE_Y", length = 4)
	public String getPlanDateY() {
		return planDateY;
	}

	/**
	 * 设置统筹日期-年
	 * 
	 * @param planDateY 统筹日期-年
	 */
	public void setPlanDateY(String planDateY) {
		this.planDateY = planDateY;
	}

	/**
	 * 取得统筹日期-月
	 * 
	 * @return planDateM 统筹日期-月
	 */
	@Column(name = "PLAN_DATE_M", length = 2)
	public String getPlanDateM() {
		return planDateM;
	}

	/**
	 * 设置统筹日期-月
	 * 
	 * @param planDateM 统筹日期-月
	 */
	public void setPlanDateM(String planDateM) {
		this.planDateM = planDateM;
	}

	/**
	 * 取得入库类型
	 * 
	 * @return storeType 入库类型
	 */
	@Column(length = 1)
	public String getStoreType() {
		return storeType;
	}

	/**
	 * 设置入库类型
	 * 
	 * @param storeType 入库类型
	 */
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	// ############################################################################################

	/**
	 * 取得可编辑标记
	 * 
	 * @return editFlg 可编辑标记
	 */
	@Transient
	public Boolean getEditFlg() {
		return editFlg;
	}

	/**
	 * 设置可编辑标记
	 * 
	 * @param editFlg 可编辑标记
	 */
	public void setEditFlg(Boolean editFlg) {
		this.editFlg = editFlg;
	}

	public void autoSetEditFlg() throws ParseException {
//		if (null == createDate) {
//			return;
//		}
//
//		Long _day = DateUtils.getDateSpanDay(createDate, DateUtils.getCurrentDate());
//		_day = Math.abs(_day);
//		if (_day <= Constants.EDITABLE_DAY && !auditFlg) {
		if (!auditFlg) {
			editFlg = true;
		}
	}

	/**
	 * 取得供应商名称
	 * 
	 * @return supplierName 供应商名称
	 */
	@Transient
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 设置供应商名称
	 * 
	 * @param supplierName 供应商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

}
