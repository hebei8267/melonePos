/**
 * 
 */
package com.tjhx.entity.affair;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 备用金申请/审批
 */
@Entity
@Table(name = "T_PETTY_CASH_APP")
public class PettyCashApp extends IdEntity {
	private static final long serialVersionUID = -5913846686871130443L;
	/** 备用金申请编号 */
	private String appNo;
	/** 申请人 */
	private Integer appPer;
	/** 申请人所属预算机构 */
	private Integer appOrg;
	/** 申请时间 */
	private String appDate;
	/** 付款期限 */
	private String paymentPeriod;
	/** 请款金额 */
	private BigDecimal amount;
	/** 请款事由 */
	private String reason;
	/** 付款方式 00-现金 01-转帐 */
	private String paymentMode;
	/** 付款方式说明 */
	private String paymentExplain;
	/** 申请/审批状态 00-申请 01-1审 02-2审 03-3审 99-归档 */
	private String appState;
	/** 申请/审批级别 01-1审 02-2审 03-3审 */
	private String appLevel;
	/** 审批人1 */
	private Integer approvalPer1;
	/** 审批人1批注 */
	private String approvalPerComment1;
	/** 审批人2 */
	private Integer approvalPer2;
	/** 审批人2批注 */
	private String approvalPerComment2;
	/** 审批人3 */
	private Integer approvalPer3;
	/** 审批人3批注 */
	private String approvalPerComment3;
	/** 审批金额 */
	private BigDecimal confirmAmount;

	private String appPerName;
	private String appOrgName;
	private String approvalPer1Name;
	private String approvalPer2Name;
	private String approvalPer3Name;

	/**
	 * 获取备用金申请编号
	 * 
	 * @return appNo
	 */
	@NaturalId
	@Column(length = 32)
	public String getAppNo() {
		return appNo;
	}

	/**
	 * 设置备用金申请编号
	 * 
	 * @param appNo 备用金申请编号
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	/**
	 * 获取申请人
	 * 
	 * @return appPer
	 */
	public Integer getAppPer() {
		return appPer;
	}

	/**
	 * 设置申请人
	 * 
	 * @param appPer 申请人
	 */
	public void setAppPer(Integer appPer) {
		this.appPer = appPer;
	}

	/**
	 * 获取申请人所属预算机构
	 * 
	 * @return appOrg
	 */
	public Integer getAppOrg() {
		return appOrg;
	}

	/**
	 * 设置申请人所属预算机构
	 * 
	 * @param appOrg 申请人所属预算机构
	 */
	public void setAppOrg(Integer appOrg) {
		this.appOrg = appOrg;
	}

	/**
	 * 获取申请时间
	 * 
	 * @return appDate
	 */
	@Column(length = 8)
	public String getAppDate() {
		return appDate;
	}

	/**
	 * 设置申请时间
	 * 
	 * @param appDate 申请时间
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	/**
	 * 获取付款期限
	 * 
	 * @return paymentPeriod
	 */
	@Column(length = 8)
	public String getPaymentPeriod() {
		return paymentPeriod;
	}

	/**
	 * 设置付款期限
	 * 
	 * @param paymentPeriod 付款期限
	 */
	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	/**
	 * 获取请款金额
	 * 
	 * @return amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置请款金额
	 * 
	 * @param amount 请款金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取请款事由
	 * 
	 * @return reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * 设置请款事由
	 * 
	 * @param reason 请款事由
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * 获取付款方式00-现金01-转帐
	 * 
	 * @return paymentMode
	 */
	@Column(length = 2)
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * 设置付款方式00-现金01-转帐
	 * 
	 * @param paymentMode 付款方式00-现金01-转帐
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * 获取付款方式说明
	 * 
	 * @return paymentExplain
	 */
	public String getPaymentExplain() {
		return paymentExplain;
	}

	/**
	 * 设置付款方式说明
	 * 
	 * @param paymentExplain 付款方式说明
	 */
	public void setPaymentExplain(String paymentExplain) {
		this.paymentExplain = paymentExplain;
	}

	/**
	 * 获取申请审批状态00-申请01-1审02-2审03-3审99-归档
	 * 
	 * @return appState
	 */
	@Column(length = 2)
	public String getAppState() {
		return appState;
	}

	/**
	 * 设置申请审批状态00-申请01-1审02-2审03-3审99-归档
	 * 
	 * @param appState 申请审批状态00-申请01-1审02-2审03-3审99-归档
	 */
	public void setAppState(String appState) {
		this.appState = appState;
	}

	/**
	 * 获取申请审批级别01-1审02-2审03-3审
	 * 
	 * @return appLevel
	 */
	public String getAppLevel() {
		return appLevel;
	}

	/**
	 * 设置申请审批级别01-1审02-2审03-3审
	 * 
	 * @param appLevel 申请审批级别01-1审02-2审03-3审
	 */
	public void setAppLevel(String appLevel) {
		this.appLevel = appLevel;
	}

	/**
	 * 获取审批人1
	 * 
	 * @return approvalPer1
	 */
	public Integer getApprovalPer1() {
		return approvalPer1;
	}

	/**
	 * 设置审批人1
	 * 
	 * @param approvalPer1 审批人1
	 */
	public void setApprovalPer1(Integer approvalPer1) {
		this.approvalPer1 = approvalPer1;
	}

	/**
	 * 获取审批人1批注
	 * 
	 * @return approvalPerComment1
	 */
	public String getApprovalPerComment1() {
		return approvalPerComment1;
	}

	/**
	 * 设置审批人1批注
	 * 
	 * @param approvalPerComment1 审批人1批注
	 */
	public void setApprovalPerComment1(String approvalPerComment1) {
		this.approvalPerComment1 = approvalPerComment1;
	}

	/**
	 * 获取审批人2
	 * 
	 * @return approvalPer2
	 */
	public Integer getApprovalPer2() {
		return approvalPer2;
	}

	/**
	 * 设置审批人2
	 * 
	 * @param approvalPer2 审批人2
	 */
	public void setApprovalPer2(Integer approvalPer2) {
		this.approvalPer2 = approvalPer2;
	}

	/**
	 * 获取审批人2批注
	 * 
	 * @return approvalPerComment2
	 */
	public String getApprovalPerComment2() {
		return approvalPerComment2;
	}

	/**
	 * 设置审批人2批注
	 * 
	 * @param approvalPerComment2 审批人2批注
	 */
	public void setApprovalPerComment2(String approvalPerComment2) {
		this.approvalPerComment2 = approvalPerComment2;
	}

	/**
	 * 获取审批人3
	 * 
	 * @return approvalPer3
	 */
	public Integer getApprovalPer3() {
		return approvalPer3;
	}

	/**
	 * 设置审批人3
	 * 
	 * @param approvalPer3 审批人3
	 */
	public void setApprovalPer3(Integer approvalPer3) {
		this.approvalPer3 = approvalPer3;
	}

	/**
	 * 获取审批人3批注
	 * 
	 * @return approvalPer3Comment
	 */
	public String getApprovalPerComment3() {
		return approvalPerComment3;
	}

	/**
	 * 设置审批人3批注
	 * 
	 * @param approvalPerComment3 审批人3批注
	 */
	public void setApprovalPerComment3(String approvalPerComment3) {
		this.approvalPerComment3 = approvalPerComment3;
	}

	/**
	 * 获取审批金额
	 * 
	 * @return confirmAmount
	 */
	public BigDecimal getConfirmAmount() {
		return confirmAmount;
	}

	/**
	 * 设置审批金额
	 * 
	 * @param confirmAmount 审批金额
	 */
	public void setConfirmAmount(BigDecimal confirmAmount) {
		this.confirmAmount = confirmAmount;
	}

	/**
	 * 获取appPerName
	 * 
	 * @return appPerName
	 */
	@Transient
	public String getAppPerName() {
		return appPerName;
	}

	/**
	 * 设置appPerName
	 * 
	 * @param appPerName appPerName
	 */
	public void setAppPerName(String appPerName) {
		this.appPerName = appPerName;
	}

	/**
	 * 获取appOrgName
	 * 
	 * @return appOrgName
	 */
	@Transient
	public String getAppOrgName() {
		return appOrgName;
	}

	/**
	 * 设置appOrgName
	 * 
	 * @param appOrgName appOrgName
	 */
	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}

	/**
	 * 获取approvalPer1Name
	 * 
	 * @return approvalPer1Name
	 */
	@Transient
	public String getApprovalPer1Name() {
		return approvalPer1Name;
	}

	/**
	 * 设置approvalPer1Name
	 * 
	 * @param approvalPer1Name approvalPer1Name
	 */
	public void setApprovalPer1Name(String approvalPer1Name) {
		this.approvalPer1Name = approvalPer1Name;
	}

	/**
	 * 获取approvalPer2Name
	 * 
	 * @return approvalPer2Name
	 */
	@Transient
	public String getApprovalPer2Name() {
		return approvalPer2Name;
	}

	/**
	 * 设置approvalPer2Name
	 * 
	 * @param approvalPer2Name approvalPer2Name
	 */
	public void setApprovalPer2Name(String approvalPer2Name) {
		this.approvalPer2Name = approvalPer2Name;
	}

	/**
	 * 获取approvalPer3Name
	 * 
	 * @return approvalPer3Name
	 */
	@Transient
	public String getApprovalPer3Name() {
		return approvalPer3Name;
	}

	/**
	 * 设置approvalPer3Name
	 * 
	 * @param approvalPer3Name approvalPer3Name
	 */
	public void setApprovalPer3Name(String approvalPer3Name) {
		this.approvalPer3Name = approvalPer3Name;
	}

}
