package com.tjhx.entity.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.struct.Organization;

/**
 * 用户
 */
@Entity
@Table(name = "T_USER")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {

	private static final long serialVersionUID = -6992234020981066235L;

	/** 登录名称 */
	private String loginName;
	/** 登录密码 */
	private String passWord;
	/** 用户名称-汉字 */
	private String name;
	/** 用户详细描述 */
	private String descTxt;
	/** 第一次登录标记 */
	private Boolean firstLoginFlg = true;
	/** 用户关联角色 */
	private Role role;
	/** 用户关联机构 */
	private Organization organization;
	/** 上传用户相片名称 */
	private String photoName;
	/** 账户是否有效 */
	private boolean valid = true;
	// ############################################################################################
	/** 用户关联机构名称 */
	private String orgName;
	/** 角色编号 */
	private String roleUuid;
	/** 机构编号 */
	private String orgUuid;
	/** 初始化默认密码标记 */
	private boolean initPwdFlg;

	/**
	 * 取得登录名称
	 * 
	 * @return 登录名称
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置登录名称
	 * 
	 * @param loginName 登录名称
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 取得登录密码
	 * 
	 * @return 登录密码
	 */
	@Column(nullable = false, length = 32)
	public String getPassWord() {
		return passWord;
	}

	/**
	 * 设置登录密码
	 * 
	 * @param passWord 登录密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * 取得用户名称-汉字
	 * 
	 * @return 用户名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置用户名称-汉字
	 * 
	 * @param name 用户名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得用户详细描述
	 * 
	 * @return 用户详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置用户详细描述
	 * 
	 * @param descTxt 用户详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得第一次登录标记
	 * 
	 * @return 第一次登录标记
	 */
	@Column(length = 1)
	public Boolean getFirstLoginFlg() {
		return firstLoginFlg;
	}

	/**
	 * 设置第一次登录标记
	 * 
	 * @param firstLoginFlg 第一次登录标记
	 */
	public void setFirstLoginFlg(Boolean firstLoginFlg) {
		this.firstLoginFlg = firstLoginFlg;
	}

	/**
	 * 取得用户关联角色
	 * 
	 * @return 用户关联角色
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_UUID")
	public Role getRole() {
		return role;
	}

	/**
	 * 设置用户关联角色
	 * 
	 * @param role 用户关联角色
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * 取得上传用户相片名称
	 * 
	 * @return 上传用户相片名称
	 */
	@Column(length = 32)
	public String getPhotoName() {
		return photoName;
	}

	/**
	 * 设置上传用户相片名称
	 * 
	 * @param photoName 上传用户相片名称
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
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
	 * 取得账户是否有效
	 * 
	 * @return valid 账户是否有效
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * 设置账户是否有效
	 * 
	 * @param valid 账户是否有效
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	// ############################################################################################
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

	/**
	 * 取得角色编号
	 * 
	 * @return roleUuid 角色编号
	 */
	@Transient
	public String getRoleUuid() {
		if (null != role) {
			roleUuid = role.getUuid().toString();
		}
		return roleUuid;
	}

	/**
	 * 设置角色编号
	 * 
	 * @param roleUuid 角色编号
	 */
	public void setRoleUuid(String roleUuid) {
		this.roleUuid = roleUuid;
	}

	/**
	 * 取得机构编号
	 * 
	 * @return orgUuid 机构编号
	 */
	@Transient
	public String getOrgUuid() {
		if (null != organization) {
			orgUuid = organization.getUuid().toString();
		}
		return orgUuid;
	}

	/**
	 * 设置机构编号
	 * 
	 * @param orgUuid 机构编号
	 */
	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	/**
	 * 取得初始化默认密码标记
	 * 
	 * @return initPwdFlg 初始化默认密码标记
	 */
	@Transient
	public boolean isInitPwdFlg() {
		return initPwdFlg;
	}

	/**
	 * 设置初始化默认密码标记
	 * 
	 * @param initPwdFlg 初始化默认密码标记
	 */
	public void setInitPwdFlg(boolean initPwdFlg) {
		this.initPwdFlg = initPwdFlg;
	}

}