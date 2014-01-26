package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 特殊标记-货品供应商 流水信息
 * 
 * <pre>
 * 特殊标记-货品供应商 流水类型
 * 1. 赊购挂账 （逻辑型） 
 * 2. 对账通知 （日期型） 
 * 3. 对账完成 （日期与数字） 
 * 4. 结算付款 （日期与数字与文本备注） 
 * 5. 退货申请 （日期与数字） 
 * 6. 退货确认 （日期与数字）
 * </pre>
 */
@Entity
@Table(name = "T_SUPPLIER_SIGN_RUN")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SupplierSignRun extends IdEntity {

	private static final long serialVersionUID = 2055141709136700983L;

	/** 供应商编号-百威 */
	private String supplierBwId;

	/** 日期-年 */
	private String optDateY;

	/** 日期-月 */
	private String optDateM;

	/** 流水类型 */
	private String runType;

	// ==========赊购挂账==========
	/** 赊购挂账标记 1-赊购挂账 */
	private String loanFlg;
	// ==========对账通知==========
	/** 对账通知时间 */
	private String checkNoticeDate;
	/** 对账通知方式 1-电话 2-网络 3-捎信 */
	private String noticeMode;
	// ==========对账完成==========
	/** 对账完成时间 */
	private String checkDate;
	/** 对账完成金额 */
	private BigDecimal checkAmt = new BigDecimal("0");
	// ==========结算付款==========
	/** 结算付款时间 */
	private String paymentDate;
	/** 结算付款金额 */
	private BigDecimal paymentAmt = new BigDecimal("0");
	// ==========退货申请==========
	/** 退货申请时间 */
	private String appDate;
	/** 退货申请金额 */
	private BigDecimal appAmt = new BigDecimal("0");
	// ==========退货确认==========
	/** 退货确认时间 */
	private String confirmDate;
	/** 退货确认金额 */
	private BigDecimal confirmAmt = new BigDecimal("0");

	/** 备注 */
	private String descTxt;

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
	 * 取得日期-年
	 * 
	 * @return optDateY 日期-年
	 */
	@NaturalId
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
	@NaturalId
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
	 * 取得流水类型
	 * 
	 * @return runType 流水类型
	 */
	@NaturalId
	@Column(name = "RUN_TYPE", length = 1)
	public String getRunType() {
		return runType;
	}

	/**
	 * 设置流水类型
	 * 
	 * @param runType 流水类型
	 */
	public void setRunType(String runType) {
		this.runType = runType;
	}

	/**
	 * 取得赊购挂账标记
	 * 
	 * @return loanFlg 赊购挂账标记
	 */
	@Column(name = "LOAN_FLG", length = 1)
	public String getLoanFlg() {
		return loanFlg;
	}

	/**
	 * 设置赊购挂账标记
	 * 
	 * @param loanFlg 赊购挂账标记
	 */
	public void setLoanFlg(String loanFlg) {
		this.loanFlg = loanFlg;
	}

	/**
	 * 取得对账通知时间
	 * 
	 * @return checkNoticeDate 对账通知时间
	 */
	@Column(length = 8)
	public String getCheckNoticeDate() {
		return checkNoticeDate;
	}

	/**
	 * 设置对账通知时间
	 * 
	 * @param checkNoticeDate 对账通知时间
	 */
	public void setCheckNoticeDate(String checkNoticeDate) {
		this.checkNoticeDate = checkNoticeDate;
	}

	/**
	 * 取得对账通知方式1-电话2-网络3-捎信
	 * 
	 * @return noticeMode 对账通知方式1-电话2-网络3-捎信
	 */
	@Column(length = 1)
	public String getNoticeMode() {
		return noticeMode;
	}

	/**
	 * 设置对账通知方式1-电话2-网络3-捎信
	 * 
	 * @param noticeMode 对账通知方式1-电话2-网络3-捎信
	 */
	public void setNoticeMode(String noticeMode) {
		this.noticeMode = noticeMode;
	}

	/**
	 * 取得对账完成时间
	 * 
	 * @return checkDate 对账完成时间
	 */
	@Column(length = 8)
	public String getCheckDate() {
		return checkDate;
	}

	/**
	 * 设置对账完成时间
	 * 
	 * @param checkDate 对账完成时间
	 */
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * 取得对账完成金额
	 * 
	 * @return checkAmt 对账完成金额
	 */
	public BigDecimal getCheckAmt() {
		return checkAmt;
	}

	/**
	 * 设置对账完成金额
	 * 
	 * @param checkAmt 对账完成金额
	 */
	public void setCheckAmt(BigDecimal checkAmt) {
		this.checkAmt = checkAmt;
	}

	/**
	 * 取得结算付款时间
	 * 
	 * @return paymentDate 结算付款时间
	 */
	@Column(length = 8)
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * 设置结算付款时间
	 * 
	 * @param paymentDate 结算付款时间
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * 取得结算付款金额
	 * 
	 * @return paymentAmt 结算付款金额
	 */
	public BigDecimal getPaymentAmt() {
		return paymentAmt;
	}

	/**
	 * 设置结算付款金额
	 * 
	 * @param paymentAmt 结算付款金额
	 */
	public void setPaymentAmt(BigDecimal paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	/**
	 * 取得退货申请时间
	 * 
	 * @return appDate 退货申请时间
	 */
	@Column(length = 8)
	public String getAppDate() {
		return appDate;
	}

	/**
	 * 设置退货申请时间
	 * 
	 * @param appDate 退货申请时间
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	/**
	 * 取得退货申请金额
	 * 
	 * @return appAmt 退货申请金额
	 */
	public BigDecimal getAppAmt() {
		return appAmt;
	}

	/**
	 * 设置退货申请金额
	 * 
	 * @param appAmt 退货申请金额
	 */
	public void setAppAmt(BigDecimal appAmt) {
		this.appAmt = appAmt;
	}

	/**
	 * 取得退货确认时间
	 * 
	 * @return confirmDate 退货确认时间
	 */
	@Column(length = 8)
	public String getConfirmDate() {
		return confirmDate;
	}

	/**
	 * 设置退货确认时间
	 * 
	 * @param confirmDate 退货确认时间
	 */
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	/**
	 * 取得退货确认金额
	 * 
	 * @return confirmAmt 退货确认金额
	 */
	public BigDecimal getConfirmAmt() {
		return confirmAmt;
	}

	/**
	 * 设置退货确认金额
	 * 
	 * @param confirmAmt 退货确认金额
	 */
	public void setConfirmAmt(BigDecimal confirmAmt) {
		this.confirmAmt = confirmAmt;
	}

	/**
	 * 取得备注
	 * 
	 * @return descTxt 备注
	 */
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

}
