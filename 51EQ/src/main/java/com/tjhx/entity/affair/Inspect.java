package com.tjhx.entity.affair;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 门店巡查报告
 */
@Entity
@Table(name = "T_INSPECT")
public class Inspect extends IdEntity {

	private static final long serialVersionUID = -9175140279165244043L;
	/** 巡查机构编号 */
	private String orgId;
	/** 巡查日期 */
	private String optDate;
	/** 巡查日期-显示 */
	private String optDateShow;
	/** 巡查日期-年 */
	private String optDateY;
	/** 巡查日期-月 */
	private String optDateM;
	/** 报告流水号 */
	private String trsId;
	/** 巡查时间 */
	private String optTime;
	/** 班次(1-A早班、2-B晚班) */
	private Integer jobType;
	/** 当班负责人 */
	private String dutyPer;

	/** 班前余额-A */
	private BigDecimal initAmt = new BigDecimal("0");
	/** 实际销售现金-B */
	private BigDecimal cashAmt = new BigDecimal("0");
	/** 存款金额-C */
	private BigDecimal depositAmt = new BigDecimal("0");
	/** 销售现金-D */
	private BigDecimal saleCashAmt = new BigDecimal("0");
	/** 现金小计(A+B+C) */
	private BigDecimal cashSubtotal = new BigDecimal("0");
	/** 现金小计盈亏(A+B+C-D) */
	private BigDecimal adjustAmt = new BigDecimal("0");
	/** 结论 1-完全相等、2基本相等、4不符，调查原因 */
	private Integer cashConclusion;
	/** 结论 1备注 */
	private String cashConclusionTxt;

	/** 备用金日记账余额-E */
	private BigDecimal imprestCalance = new BigDecimal("0");
	/** 已作报销支出尚未记账的支出金额-F */
	private BigDecimal expImprestAmt = new BigDecimal("0");
	/** 实际清点的备用金额-G */
	private BigDecimal clearImprestAmt = new BigDecimal("0");
	/** 备用金小计1(E-F) */
	private BigDecimal imprestSubtotal1 = new BigDecimal("0");
	/** 备用金小计2(E-F-G) */
	private BigDecimal imprestSubtotal2 = new BigDecimal("0");
	/** 结论 1-完全相等、2基本相等、4不符，调查原因 */
	private Integer imprestConclusion;
	/** 结论 2备注 */
	private String imprestConclusionTxt;

	/** 巡查人 */
	private String inspectPer;

	// ############################################################################################

	/** 日期-开始时间 */
	private String optDateStart;
	/** 日期-结束时间 */
	private String optDateEnd;

	/**
	 * 取得巡查机构编号
	 * 
	 * @return orgId 巡查机构编号
	 */
	@Column(length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置巡查机构编号
	 * 
	 * @param orgId 巡查机构编号
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 取得巡查日期
	 * 
	 * @return optDate 巡查日期
	 */
	@Column(length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置巡查日期
	 * 
	 * @param optDate 巡查日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得巡查日期-显示
	 * 
	 * @return optDateShow 巡查日期-显示
	 */
	@Column(length = 10)
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置巡查日期-显示
	 * 
	 * @param optDateShow 巡查日期-显示
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
	}

	/**
	 * 取得巡查日期-年
	 * 
	 * @return optDateY 巡查日期-年
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置巡查日期-年
	 * 
	 * @param optDateY 巡查日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得巡查日期-月
	 * 
	 * @return optDateM 巡查日期-月
	 */
	@Column(name = "OPT_DATE_M", length = 2)
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置巡查日期-月
	 * 
	 * @param optDateM 巡查日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得报告流水号
	 * 
	 * @return trsId 报告流水号
	 */
	@NaturalId
	@Column(length = 16, nullable = false)
	public String getTrsId() {
		return trsId;
	}

	/**
	 * 设置报告流水号
	 * 
	 * @param trsId 报告流水号
	 */
	public void setTrsId(String trsId) {
		this.trsId = trsId;
	}

	/**
	 * 取得巡查时间
	 * 
	 * @return optTime 巡查时间
	 */
	@Column(length = 8)
	public String getOptTime() {
		return optTime;
	}

	/**
	 * 设置巡查时间
	 * 
	 * @param optTime 巡查时间
	 */
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	/**
	 * 取得班次(1-A早班、2-B晚班)
	 * 
	 * @return jobType 班次(1-A早班、2-B晚班)
	 */
	public Integer getJobType() {
		return jobType;
	}

	/**
	 * 设置班次(1-A早班、2-B晚班)
	 * 
	 * @param jobType 班次(1-A早班、2-B晚班)
	 */
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	/**
	 * 取得当班负责人
	 * 
	 * @return dutyPer 当班负责人
	 */
	@Column(length = 16)
	public String getDutyPer() {
		return dutyPer;
	}

	/**
	 * 设置当班负责人
	 * 
	 * @param dutyPer 当班负责人
	 */
	public void setDutyPer(String dutyPer) {
		this.dutyPer = dutyPer;
	}

	/**
	 * 取得班前余额-A
	 * 
	 * @return initAmt 班前余额-A
	 */
	public BigDecimal getInitAmt() {
		return initAmt;
	}

	/**
	 * 设置班前余额-A
	 * 
	 * @param initAmt 班前余额-A
	 */
	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	/**
	 * 取得实际销售现金-B
	 * 
	 * @return cashAmt 实际销售现金-B
	 */
	public BigDecimal getCashAmt() {
		return cashAmt;
	}

	/**
	 * 设置实际销售现金-B
	 * 
	 * @param cashAmt 实际销售现金-B
	 */
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}

	/**
	 * 取得存款金额-C
	 * 
	 * @return depositAmt 存款金额-C
	 */
	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	/**
	 * 设置存款金额-C
	 * 
	 * @param depositAmt 存款金额-C
	 */
	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	/**
	 * 取得销售现金-D
	 * 
	 * @return saleCashAmt 销售现金-D
	 */
	public BigDecimal getSaleCashAmt() {
		return saleCashAmt;
	}

	/**
	 * 设置销售现金-D
	 * 
	 * @param saleCashAmt 销售现金-D
	 */
	public void setSaleCashAmt(BigDecimal saleCashAmt) {
		this.saleCashAmt = saleCashAmt;
	}

	/**
	 * 取得现金小计(A+B+C)
	 * 
	 * @return cashSubtotal 现金小计(A+B+C)
	 */
	public BigDecimal getCashSubtotal() {
		return cashSubtotal;
	}

	/**
	 * 设置现金小计(A+B+C)
	 * 
	 * @param cashSubtotal 现金小计(A+B+C)
	 */
	public void setCashSubtotal(BigDecimal cashSubtotal) {
		this.cashSubtotal = cashSubtotal;
	}

	/**
	 * 取得现金小计盈亏(A+B+C-D)
	 * 
	 * @return adjustAmt 现金小计盈亏(A+B+C-D)
	 */
	public BigDecimal getAdjustAmt() {
		return adjustAmt;
	}

	/**
	 * 设置现金小计盈亏(A+B+C-D)
	 * 
	 * @param adjustAmt 现金小计盈亏(A+B+C-D)
	 */
	public void setAdjustAmt(BigDecimal adjustAmt) {
		this.adjustAmt = adjustAmt;
	}

	/**
	 * 取得结论1-完全相等、2基本相等、4不符，调查原因
	 * 
	 * @return cashConclusion 结论1-完全相等、2基本相等、4不符，调查原因
	 */
	public Integer getCashConclusion() {
		return cashConclusion;
	}

	/**
	 * 设置结论1-完全相等、2基本相等、4不符，调查原因
	 * 
	 * @param cashConclusion 结论1-完全相等、2基本相等、4不符，调查原因
	 */
	public void setCashConclusion(Integer cashConclusion) {
		this.cashConclusion = cashConclusion;
	}

	/**
	 * 取得结论1备注
	 * 
	 * @return cashConclusionTxt 结论1备注
	 */
	public String getCashConclusionTxt() {
		return cashConclusionTxt;
	}

	/**
	 * 设置结论1备注
	 * 
	 * @param cashConclusionTxt 结论1备注
	 */
	public void setCashConclusionTxt(String cashConclusionTxt) {
		this.cashConclusionTxt = cashConclusionTxt;
	}

	/**
	 * 取得备用金日记账余额-E
	 * 
	 * @return imprestCalance 备用金日记账余额-E
	 */
	public BigDecimal getImprestCalance() {
		return imprestCalance;
	}

	/**
	 * 设置备用金日记账余额-E
	 * 
	 * @param imprestCalance 备用金日记账余额-E
	 */
	public void setImprestCalance(BigDecimal imprestCalance) {
		this.imprestCalance = imprestCalance;
	}

	/**
	 * 取得已作报销支出尚未记账的支出金额-F
	 * 
	 * @return expImprestAmt 已作报销支出尚未记账的支出金额-F
	 */
	public BigDecimal getExpImprestAmt() {
		return expImprestAmt;
	}

	/**
	 * 设置已作报销支出尚未记账的支出金额-F
	 * 
	 * @param expImprestAmt 已作报销支出尚未记账的支出金额-F
	 */
	public void setExpImprestAmt(BigDecimal expImprestAmt) {
		this.expImprestAmt = expImprestAmt;
	}

	/**
	 * 取得实际清点的备用金额-G
	 * 
	 * @return clearImprestAmt 实际清点的备用金额-G
	 */
	public BigDecimal getClearImprestAmt() {
		return clearImprestAmt;
	}

	/**
	 * 设置实际清点的备用金额-G
	 * 
	 * @param clearImprestAmt 实际清点的备用金额-G
	 */
	public void setClearImprestAmt(BigDecimal clearImprestAmt) {
		this.clearImprestAmt = clearImprestAmt;
	}

	/**
	 * 取得备用金小计1(E-F)
	 * 
	 * @return imprestSubtotal1 备用金小计1(E-F)
	 */
	public BigDecimal getImprestSubtotal1() {
		return imprestSubtotal1;
	}

	/**
	 * 设置备用金小计1(E-F)
	 * 
	 * @param imprestSubtotal1 备用金小计1(E-F)
	 */
	public void setImprestSubtotal1(BigDecimal imprestSubtotal1) {
		this.imprestSubtotal1 = imprestSubtotal1;
	}

	/**
	 * 取得备用金小计2(E-F-G)
	 * 
	 * @return imprestSubtotal2 备用金小计2(E-F-G)
	 */
	public BigDecimal getImprestSubtotal2() {
		return imprestSubtotal2;
	}

	/**
	 * 设置备用金小计2(E-F-G)
	 * 
	 * @param imprestSubtotal2 备用金小计2(E-F-G)
	 */
	public void setImprestSubtotal2(BigDecimal imprestSubtotal2) {
		this.imprestSubtotal2 = imprestSubtotal2;
	}

	/**
	 * 取得结论2-完全相等、2基本相等、4不符，调查原因
	 * 
	 * @return imprestConclusion 结论2-完全相等、2基本相等、4不符，调查原因
	 */
	public Integer getImprestConclusion() {
		return imprestConclusion;
	}

	/**
	 * 设置结论2-完全相等、2基本相等、4不符，调查原因
	 * 
	 * @param imprestConclusion 结论2-完全相等、2基本相等、4不符，调查原因
	 */
	public void setImprestConclusion(Integer imprestConclusion) {
		this.imprestConclusion = imprestConclusion;
	}

	/**
	 * 取得结论2备注
	 * 
	 * @return imprestConclusionTxt 结论2备注
	 */
	public String getImprestConclusionTxt() {
		return imprestConclusionTxt;
	}

	/**
	 * 设置结论2备注
	 * 
	 * @param imprestConclusionTxt 结论2备注
	 */
	public void setImprestConclusionTxt(String imprestConclusionTxt) {
		this.imprestConclusionTxt = imprestConclusionTxt;
	}

	/**
	 * 取得巡查人
	 * 
	 * @return inspectPer 巡查人
	 */
	@Column(length = 16)
	public String getInspectPer() {
		return inspectPer;
	}

	/**
	 * 设置巡查人
	 * 
	 * @param inspectPer 巡查人
	 */
	public void setInspectPer(String inspectPer) {
		this.inspectPer = inspectPer;
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
}
