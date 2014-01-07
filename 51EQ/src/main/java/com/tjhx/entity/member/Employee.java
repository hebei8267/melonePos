package com.tjhx.entity.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.struct.Organization;

@Entity
@Table(name = "T_EMPLOYEE")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee extends IdEntity {

	private static final long serialVersionUID = 6188301640502063719L;

	/** 员工编号 */
	private Integer zkid;
	/** 员工编号-自定义 */
	private String code;
	/** 员工姓名 */
	private String name;
	/** 所属机构 */
	private Integer zkOrgId;
	/** 身份证编号 */
	private String cardId;
	/** 员工类别 1-正式 2-兼职 */
	private String empType;
	/** 在岗标记 1-在职 0-离职 */
	private String workFlg;
	/** 用户关联机构 */
	private Organization organization;
	// ---------------------------------------------
	/** 用户关联机构编号 */
	private Integer orgUuid;
	/** 用户关联机构名称 */
	private String orgName;

	/**
	 * 取得用户编号
	 * 
	 * @return zkid 用户编号
	 */
	public Integer getZkid() {
		return zkid;
	}

	/**
	 * 设置用户编号
	 * 
	 * @param zkid 用户编号
	 */
	public void setZkid(Integer zkid) {
		this.zkid = zkid;
	}

	/**
	 * 取得用户编号-自定义
	 * 
	 * @return code 用户编号-自定义
	 */
	@Column(length = 16)
	public String getCode() {
		return code;
	}

	/**
	 * 设置用户编号-自定义
	 * 
	 * @param code 用户编号-自定义
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 取得用户名
	 * 
	 * @return name 用户名
	 */
	@Column(length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置用户名
	 * 
	 * @param name 用户名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得所属机构
	 * 
	 * @return zkOrgId 所属机构
	 */
	public Integer getZkOrgId() {
		return zkOrgId;
	}

	/**
	 * 设置所属机构
	 * 
	 * @param zkOrgId 所属机构
	 */
	public void setZkOrgId(Integer zkOrgId) {
		this.zkOrgId = zkOrgId;
	}

	/**
	 * 取得身份证编号
	 * 
	 * @return cardId 身份证编号
	 */
	@Column(length = 18)
	public String getCardId() {
		return cardId;
	}

	/**
	 * 设置身份证编号
	 * 
	 * @param cardId 身份证编号
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * 取得员工类别1-正式2-兼职
	 * 
	 * @return empType 员工类别1-正式2-兼职
	 */
	@Column(length = 1)
	public String getEmpType() {
		return empType;
	}

	/**
	 * 设置员工类别1-正式2-兼职
	 * 
	 * @param empType 员工类别1-正式2-兼职
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}

	/**
	 * 取得在岗标记1-在职0-离职
	 * 
	 * @return workFlg 在岗标记1-在职0-离职
	 */
	@Column(length = 1)
	public String getWorkFlg() {
		return workFlg;
	}

	/**
	 * 设置在岗标记1-在职0-离职
	 * 
	 * @param workFlg 在岗标记1-在职0-离职
	 */
	public void setWorkFlg(String workFlg) {
		this.workFlg = workFlg;
	}

	/**
	 * 取得用户关联机构
	 * 
	 * @return organization 用户关联机构
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_UUID")
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * 设置用户关联机构
	 * 
	 * @param organization 用户关联机构
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * 取得用户关联机构编号
	 * 
	 * @return orgUuid 用户关联机构编号
	 */
	@Transient
	public Integer getOrgUuid() {
		return orgUuid;
	}

	/**
	 * 设置用户关联机构编号
	 * 
	 * @param orgUuid 用户关联机构编号
	 */
	public void setOrgUuid(Integer orgUuid) {
		this.orgUuid = orgUuid;
	}

	/**
	 * 取得用户关联机构名称
	 * 
	 * @return orgName 用户关联机构名称
	 */
	@Transient
	public String getOrgName() {
		if (null != organization) {
			return organization.getName();
		}
		return orgName;
	}

	/**
	 * 设置用户关联机构名称
	 * 
	 * @param orgName 用户关联机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
