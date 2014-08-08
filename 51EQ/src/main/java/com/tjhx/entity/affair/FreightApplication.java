/**
 * 
 */
package com.tjhx.entity.affair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 货运申请
 */
@Entity
@Table(name = "T_FREIGHT_APPLICATION")
public class FreightApplication extends IdEntity {

	private static final long serialVersionUID = -4443650985744491393L;
	/** 申请日期 */
	private String appDate;
	/** 申请机构 */
	private String appOrgId;
	/** 申请人 */
	private String applicant;
	/** 是否打包（1-已打包/0-未打包） */
	private String packFlg = "0";
	/** 打包件数（箱和袋） */
	private Integer packNum;
	/** 打包件数（1-箱/2-袋） */
	private String packUnit;
	/** 调货目的机构 */
	private String targetOrgId;
	/** 调货类别（1-普通/2-限时） */
	private String freightType;
	/** 限时日期 */
	private String limitedDate;
	/** 审批人 */
	private String approver;
	/** 预计收货时间-运输 */
	private String expReceiptDate;
	/** 实际收货时间-运输 */
	private String actReceiptDate;
	/** 预计送货时间-运输 */
	private String expdeliveryDate;
	/** 实际送达时间-运输 */
	private String actDeliveryDate;
	/** 货运申请状态-00申请 01已审批 02已送达 */
	private String status;

	/**
	 * 获取申请日期
	 * 
	 * @return appDate 申请日期
	 */
	@NaturalId
	@Column(length = 8)
	public String getAppDate() {
		return appDate;
	}

	/**
	 * 设置申请日期
	 * 
	 * @param appDate 申请日期
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	/**
	 * 获取申请机构
	 * 
	 * @return appOrgId 申请机构
	 */
	@NaturalId
	@Column(length = 32)
	public String getAppOrgId() {
		return appOrgId;
	}

	/**
	 * 设置申请机构
	 * 
	 * @param appOrgId 申请机构
	 */
	public void setAppOrgId(String appOrgId) {
		this.appOrgId = appOrgId;
	}

	/**
	 * 获取申请人
	 * 
	 * @return applicant 申请人
	 */
	@Column(length = 32)
	public String getApplicant() {
		return applicant;
	}

	/**
	 * 设置申请人
	 * 
	 * @param applicant 申请人
	 */
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	/**
	 * 获取是否打包（1-已打包0-未打包）
	 * 
	 * @return packFlg 是否打包（1-已打包0-未打包）
	 */
	@Column(length = 1)
	public String getPackFlg() {
		return packFlg;
	}

	/**
	 * 设置是否打包（1-已打包0-未打包）
	 * 
	 * @param packFlg 是否打包（1-已打包0-未打包）
	 */
	public void setPackFlg(String packFlg) {
		this.packFlg = packFlg;
	}

	/**
	 * 获取打包件数（箱和袋）
	 * 
	 * @return packNum 打包件数（箱和袋）
	 */
	public Integer getPackNum() {
		return packNum;
	}

	/**
	 * 设置打包件数（箱和袋）
	 * 
	 * @param packNum 打包件数（箱和袋）
	 */
	public void setPackNum(Integer packNum) {
		this.packNum = packNum;
	}

	/**
	 * 获取打包件数（1-箱/2-袋）
	 * 
	 * @return packUnit 打包件数（1-箱/2-袋）
	 */
	@Column(length = 1)
	public String getPackUnit() {
		return packUnit;
	}

	/**
	 * 设置打包件数（1-箱/2-袋）
	 * 
	 * @param packUnit 打包件数（1-箱/2-袋）
	 */
	public void setPackUnit(String packUnit) {
		this.packUnit = packUnit;
	}

	/**
	 * 获取调货目的机构
	 * 
	 * @return targetOrgId 调货目的机构
	 */
	@NaturalId
	@Column(length = 32)
	public String getTargetOrgId() {
		return targetOrgId;
	}

	/**
	 * 设置调货目的机构
	 * 
	 * @param targetOrgId 调货目的机构
	 */
	public void setTargetOrgId(String targetOrgId) {
		this.targetOrgId = targetOrgId;
	}

	/**
	 * 获取调货类别（1-普通2-限时）
	 * 
	 * @return freightType 调货类别（1-普通2-限时）
	 */
	@Column(length = 1)
	public String getFreightType() {
		return freightType;
	}

	/**
	 * 设置调货类别（1-普通2-限时）
	 * 
	 * @param freightType 调货类别（1-普通2-限时）
	 */
	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}

	/**
	 * 获取限时日期
	 * 
	 * @return limitedDate 限时日期
	 */
	@Column(length = 8)
	public String getLimitedDate() {
		return limitedDate;
	}

	/**
	 * 设置限时日期
	 * 
	 * @param limitedDate 限时日期
	 */
	public void setLimitedDate(String limitedDate) {
		this.limitedDate = limitedDate;
	}

	/**
	 * 获取审批人
	 * 
	 * @return approver 审批人
	 */
	@Column(length = 32)
	public String getApprover() {
		return approver;
	}

	/**
	 * 设置审批人
	 * 
	 * @param approver 审批人
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}

	/**
	 * 获取预计收货时间-运输
	 * 
	 * @return expReceiptDate 预计收货时间-运输
	 */
	@Column(length = 8)
	public String getExpReceiptDate() {
		return expReceiptDate;
	}

	/**
	 * 获取实际收货时间-运输
	 * 
	 * @return actReceiptDate 实际收货时间-运输
	 */
	@Column(length = 8)
	public String getActReceiptDate() {
		return actReceiptDate;
	}

	/**
	 * 设置实际收货时间-运输
	 * 
	 * @param actReceiptDate 实际收货时间-运输
	 */
	public void setActReceiptDate(String actReceiptDate) {
		this.actReceiptDate = actReceiptDate;
	}

	/**
	 * 设置预计收货时间-运输
	 * 
	 * @param expReceiptDate 预计收货时间-运输
	 */
	public void setExpReceiptDate(String expReceiptDate) {
		this.expReceiptDate = expReceiptDate;
	}

	/**
	 * 获取预计送货时间-运输
	 * 
	 * @return expdeliveryDate 预计送货时间-运输
	 */
	@Column(length = 8)
	public String getExpdeliveryDate() {
		return expdeliveryDate;
	}

	/**
	 * 设置预计送货时间-运输
	 * 
	 * @param expdeliveryDate 预计送货时间-运输
	 */
	public void setExpdeliveryDate(String expdeliveryDate) {
		this.expdeliveryDate = expdeliveryDate;
	}

	/**
	 * 获取实际送达时间-运输
	 * 
	 * @return actDeliveryDate 实际送达时间-运输
	 */
	@Column(length = 8)
	public String getActDeliveryDate() {
		return actDeliveryDate;
	}

	/**
	 * 设置实际送达时间-运输
	 * 
	 * @param actDeliveryDate 实际送达时间-运输
	 */
	public void setActDeliveryDate(String actDeliveryDate) {
		this.actDeliveryDate = actDeliveryDate;
	}

	/**
	 * 获取货运申请状态-00申请01已审批02已送达
	 * 
	 * @return status 货运申请状态-00申请01已审批02已送达
	 */
	@Column(length = 2)
	public String getStatus() {
		return status;
	}

	/**
	 * 设置货运申请状态-00申请01已审批02已送达
	 * 
	 * @param status 货运申请状态-00申请01已审批02已送达
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
