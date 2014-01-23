package com.tjhx.entity.security;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 用户
 */
@Entity
@Table(name = "T_USER")
public class User extends IdEntity {

	private static final long serialVersionUID = -6505067803859982545L;

	/** 用户编号 */
	@Column(name = "USER_ID", nullable = false, unique = true, length = 32)
	private String id;

	/** 用户姓名 */
	@Column(nullable = false, length = 32)
	private String name;

	/** 用户登录帐号 */
	@NaturalId
	@Column(nullable = false, length = 32)
	private String loginName;

	/** 用户登录密码 */
	@Column(nullable = false, length = 32)
	private String passWord;

	/** 用户性别 */
	@Column(length = 1)
	private String sex;

	/** 用户出生日期 */
	@Column(length = 8)
	private String birthDay;

	/** 用户手机号码 */
	@Column(length = 16)
	private String phoneNum;

	/** 用户固定电话 */
	@Column(length = 16)
	private String telNum;

	/** 用户QQ号码 */
	@Column(length = 16)
	private String qqNum;

	/** 用户电子邮件 */
	@Column(length = 32)
	private String emailAdd;

	/** 用户有效性-0:禁用 1:启用 */
	@Column(nullable = false, length = 1)
	private String valid;

	/** 用户备注 */
	@Basic
	private String remark;

	/** 用户和角色的关系 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "T_USER_ROLE_REL", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;

	/**
	 * 取得用户编号
	 * 
	 * @return id 用户编号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置用户编号
	 * 
	 * @param id 用户编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得用户姓名
	 * 
	 * @return name 用户姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置用户姓名
	 * 
	 * @param name 用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得用户登录帐号
	 * 
	 * @return loginName 用户登录帐号
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置用户登录帐号
	 * 
	 * @param loginName 用户登录帐号
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 取得用户登录密码
	 * 
	 * @return passWord 用户登录密码
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * 设置用户登录密码
	 * 
	 * @param passWord 用户登录密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * 取得用户性别
	 * 
	 * @return sex 用户性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置用户性别
	 * 
	 * @param sex 用户性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 取得用户出生日期
	 * 
	 * @return birthDay 用户出生日期
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * 设置用户出生日期
	 * 
	 * @param birthDay 用户出生日期
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * 取得用户手机号码
	 * 
	 * @return phoneNum 用户手机号码
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * 设置用户手机号码
	 * 
	 * @param phoneNum 用户手机号码
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * 取得用户固定电话
	 * 
	 * @return telNum 用户固定电话
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * 设置用户固定电话
	 * 
	 * @param telNum 用户固定电话
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * 取得用户QQ号码
	 * 
	 * @return qqNum 用户QQ号码
	 */
	public String getQqNum() {
		return qqNum;
	}

	/**
	 * 设置用户QQ号码
	 * 
	 * @param qqNum 用户QQ号码
	 */
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	/**
	 * 取得用户电子邮件
	 * 
	 * @return emailAdd 用户电子邮件
	 */
	public String getEmailAdd() {
		return emailAdd;
	}

	/**
	 * 设置用户电子邮件
	 * 
	 * @param emailAdd 用户电子邮件
	 */
	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	/**
	 * 取得用户有效性-0:禁用1:启用
	 * 
	 * @return valid 用户有效性-0:禁用1:启用
	 */
	public String getValid() {
		return valid;
	}

	/**
	 * 设置用户有效性-0:禁用1:启用
	 * 
	 * @param valid 用户有效性-0:禁用1:启用
	 */
	public void setValid(String valid) {
		this.valid = valid;
	}

	/**
	 * 取得用户备注
	 * 
	 * @return remark 用户备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置用户备注
	 * 
	 * @param remark 用户备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 取得用户和角色的关系
	 * 
	 * @return roles 用户和角色的关系
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * 设置用户和角色的关系
	 * 
	 * @param roles 用户和角色的关系
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
