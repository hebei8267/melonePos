/**
 * 
 */
package com.tjhx.entity.affair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	private String packFlg;
	/** 打包件数（箱和袋） */
	private int packNum;
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
	/** 是否已知-运输 （1-已知/0-未知） */
	private String knownFlg;
	/** 预计收货时间-运输 */
	private String expReceiptDate;
	/** 预计送货时间-运输 */
	private String expdeliveryDate;
	/** 实际送达时间-运输 */
	private String actDeliveryDate;

	/**
	 * 获取申请日期
	 * 
	 * @return appDate 申请日期
	 */
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
	public int getPackNum() {
		return packNum;
	}

	/**
	 * 设置打包件数（箱和袋）
	 * 
	 * @param packNum 打包件数（箱和袋）
	 */
	public void setPackNum(int packNum) {
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
	 * 获取是否已知-运输（1-已知0-未知）
	 * 
	 * @return knownFlg 是否已知-运输（1-已知0-未知）
	 */
	@Column(length = 1)
	public String getKnownFlg() {
		return knownFlg;
	}

	/**
	 * 设置是否已知-运输（1-已知0-未知）
	 * 
	 * @param knownFlg 是否已知-运输（1-已知0-未知）
	 */
	public void setKnownFlg(String knownFlg) {
		this.knownFlg = knownFlg;
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

}
