/**
 * 
 */
package com.tjhx.entity.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

@Entity
@Table(name = "T_EMPLOYEE_2")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee2 extends IdEntity {

	private static final long serialVersionUID = 6297453810049919075L;
	/** 员工姓名 */
	private String name;
	/** 员工性别 1-男 2-女 */
	private String sex;
	/** 身份证号码 */
	private String idCardNo;
	/** 婚姻状况1-已婚 0-未婚 */
	private String maritalStatus;
	/** 学历1-小学 2-初中 3-高中 4-大学 */
	private String education;
	/** 参加工作时间 */
	private String startWorkTime;
	/** 专业 */
	private String professional;
	/** 户口所在地 */
	private String accountLocal;
	/** 常住地址 */
	private String address;
	/** 联系电话 */
	private String phone;
	/** 紧急联系人 */
	private String emergencyContact;
	/** 紧急联系电话 */
	private String emergencyPhone;
	/** 基本情况备注 */
	private String basicInfoDescTxt;
	/** 所属部门 */
	private String orgId;
	/** 职务 */
	private String pos;
	/** 入职时间 */
	private String entryTime;
	/** 转正时间 */
	private String posTime;
	/** 合同到期时间 */
	private String contractExpTime;
	/** 续签时间 */
	private String renewTime;
	/** 聘用形式 1-正式 2-兼职 */
	private String employForm;
	/** 机构调动记录 */
	private String orgTransRecord;
	/** 职位调整记录 */
	private String posAdjustRecord;

	/**
	 * 获取员工姓名
	 * 
	 * @return name 员工姓名
	 */
	@Column(length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置员工姓名
	 * 
	 * @param name 员工姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取员工性别1-男2-女
	 * 
	 * @return sex 员工性别1-男2-女
	 */
	@Column(length = 1)
	public String getSex() {
		return sex;
	}

	/**
	 * 设置员工性别1-男2-女
	 * 
	 * @param sex 员工性别1-男2-女
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取身份证号码
	 * 
	 * @return idCardNo 身份证号码
	 */
	@Column(length = 18)
	public String getIdCardNo() {
		return idCardNo;
	}

	/**
	 * 设置身份证号码
	 * 
	 * @param idCardNo 身份证号码
	 */
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	/**
	 * 获取婚姻状况1-已婚0-未婚
	 * 
	 * @return maritalStatus 婚姻状况1-已婚0-未婚
	 */
	@Column(length = 1)
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * 设置婚姻状况1-已婚0-未婚
	 * 
	 * @param maritalStatus 婚姻状况1-已婚0-未婚
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * 获取学历1-小学2-初中3-高中4-大学
	 * 
	 * @return education 学历1-小学2-初中3-高中4-大学
	 */
	@Column(length = 1)
	public String getEducation() {
		return education;
	}

	/**
	 * 设置学历1-小学2-初中3-高中4-大学
	 * 
	 * @param education 学历1-小学2-初中3-高中4-大学
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * 获取参加工作时间
	 * 
	 * @return startWorkTime 参加工作时间
	 */
	@Column(length = 6)
	public String getStartWorkTime() {
		return startWorkTime;
	}

	/**
	 * 设置参加工作时间
	 * 
	 * @param startWorkTime 参加工作时间
	 */
	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	/**
	 * 获取专业
	 * 
	 * @return professional 专业
	 */
	@Column(length = 32)
	public String getProfessional() {
		return professional;
	}

	/**
	 * 设置专业
	 * 
	 * @param professional 专业
	 */
	public void setProfessional(String professional) {
		this.professional = professional;
	}

	/**
	 * 获取户口所在地
	 * 
	 * @return accountLocal 户口所在地
	 */
	@Column(length = 64)
	public String getAccountLocal() {
		return accountLocal;
	}

	/**
	 * 设置户口所在地
	 * 
	 * @param accountLocal 户口所在地
	 */
	public void setAccountLocal(String accountLocal) {
		this.accountLocal = accountLocal;
	}

	/**
	 * 获取常住地址
	 * 
	 * @return address 常住地址
	 */
	@Column(length = 64)
	public String getAddress() {
		return address;
	}

	/**
	 * 设置常住地址
	 * 
	 * @param address 常住地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return phone 联系电话
	 */
	@Column(length = 16)
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param phone 联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取紧急联系人
	 * 
	 * @return emergencyContact 紧急联系人
	 */
	@Column(length = 32)
	public String getEmergencyContact() {
		return emergencyContact;
	}

	/**
	 * 设置紧急联系人
	 * 
	 * @param emergencyContact 紧急联系人
	 */
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	/**
	 * 获取紧急联系电话
	 * 
	 * @return emergencyPhone 紧急联系电话
	 */
	@Column(length = 16)
	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	/**
	 * 设置紧急联系电话
	 * 
	 * @param emergencyPhone 紧急联系电话
	 */
	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	/**
	 * 获取基本情况备注
	 * 
	 * @return basicInfoDescTxt 基本情况备注
	 */
	@Column(length = 1024)
	public String getBasicInfoDescTxt() {
		return basicInfoDescTxt;
	}

	/**
	 * 设置基本情况备注
	 * 
	 * @param basicInfoDescTxt 基本情况备注
	 */
	public void setBasicInfoDescTxt(String basicInfoDescTxt) {
		this.basicInfoDescTxt = basicInfoDescTxt;
	}

	/**
	 * 获取所属部门
	 * 
	 * @return orgId 所属部门
	 */
	@Column(length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置所属部门
	 * 
	 * @param orgId 所属部门
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取职务
	 * 
	 * @return pos 职务
	 */
	@Column(length = 16)
	public String getPos() {
		return pos;
	}

	/**
	 * 设置职务
	 * 
	 * @param pos 职务
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}

	/**
	 * 获取入职时间
	 * 
	 * @return entryTime 入职时间
	 */
	@Column(length = 8)
	public String getEntryTime() {
		return entryTime;
	}

	/**
	 * 设置入职时间
	 * 
	 * @param entryTime 入职时间
	 */
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * 获取转正时间
	 * 
	 * @return posTime 转正时间
	 */
	@Column(length = 8)
	public String getPosTime() {
		return posTime;
	}

	/**
	 * 设置转正时间
	 * 
	 * @param posTime 转正时间
	 */
	public void setPosTime(String posTime) {
		this.posTime = posTime;
	}

	/**
	 * 获取合同到期时间
	 * 
	 * @return contractExpTime 合同到期时间
	 */
	@Column(length = 8)
	public String getContractExpTime() {
		return contractExpTime;
	}

	/**
	 * 设置合同到期时间
	 * 
	 * @param contractExpTime 合同到期时间
	 */
	public void setContractExpTime(String contractExpTime) {
		this.contractExpTime = contractExpTime;
	}

	/**
	 * 获取续签时间
	 * 
	 * @return renewTime 续签时间
	 */
	@Column(length = 8)
	public String getRenewTime() {
		return renewTime;
	}

	/**
	 * 设置续签时间
	 * 
	 * @param renewTime 续签时间
	 */
	public void setRenewTime(String renewTime) {
		this.renewTime = renewTime;
	}

	/**
	 * 获取聘用形式1-正式2-兼职
	 * 
	 * @return employForm 聘用形式1-正式2-兼职
	 */
	@Column(length = 1)
	public String getEmployForm() {
		return employForm;
	}

	/**
	 * 设置聘用形式1-正式2-兼职
	 * 
	 * @param employForm 聘用形式1-正式2-兼职
	 */
	public void setEmployForm(String employForm) {
		this.employForm = employForm;
	}

	/**
	 * 获取机构调动记录
	 * 
	 * @return orgTransRecord 机构调动记录
	 */
	@Column(length = 1024)
	public String getOrgTransRecord() {
		return orgTransRecord;
	}

	/**
	 * 设置机构调动记录
	 * 
	 * @param orgTransRecord 机构调动记录
	 */
	public void setOrgTransRecord(String orgTransRecord) {
		this.orgTransRecord = orgTransRecord;
	}

	/**
	 * 获取职位调整记录
	 * 
	 * @return posAdjustRecord 职位调整记录
	 */
	@Column(length = 1024)
	public String getPosAdjustRecord() {
		return posAdjustRecord;
	}

	/**
	 * 设置职位调整记录
	 * 
	 * @param posAdjustRecord 职位调整记录
	 */
	public void setPosAdjustRecord(String posAdjustRecord) {
		this.posAdjustRecord = posAdjustRecord;
	}

}
