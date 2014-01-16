package com.tjhx.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author
 */
// JPA Entity基类的标识
@MappedSuperclass
@EntityListeners({ IdListener.class })
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = -4108066747601937361L;

	/** 对象唯一标识 */
	@Id
	@Column(name = "UUID", nullable = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer uuid;

	/** 对象创建时间（Timestamp） */
	@Basic
	@Column(name = "CREATE_DATE", nullable = false, updatable = false)
	protected Date createDate;

	/** Create_User_ID */
	@Basic
	@Column(name = "CREATE_USER_ID", nullable = false, length = 32, updatable = false)
	protected String createUserId;

	/** 对象更新时间（Timestamp） */
	@Basic
	@Column(name = "UPDATE_USER_ID", nullable = false, length = 32)
	protected Date updateDate;

	/** Update_User_ID */
	@Basic
	@Column(name = "UPDATE_DATE", nullable = false)
	protected String updateUserId;

	/** Hibernate_Version */
	@Version
	@Column(name = "VERSION", nullable = false)
	protected Integer version;

	/**
	 * 取得对象唯一标识
	 * 
	 * @return uuid 对象唯一标识
	 */
	public Integer getUuid() {
		return uuid;
	}

	/**
	 * 设置对象唯一标识
	 * 
	 * @param uuid 对象唯一标识
	 */
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	/**
	 * 取得对象创建时间（Timestamp）
	 * 
	 * @return createDate 对象创建时间（Timestamp）
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置对象创建时间（Timestamp）
	 * 
	 * @param createDate 对象创建时间（Timestamp）
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 取得Create_User_ID
	 * 
	 * @return createUserId Create_User_ID
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置Create_User_ID
	 * 
	 * @param createUserId Create_User_ID
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 取得对象更新时间（Timestamp）
	 * 
	 * @return updateDate 对象更新时间（Timestamp）
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置对象更新时间（Timestamp）
	 * 
	 * @param updateDate 对象更新时间（Timestamp）
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 取得Update_User_ID
	 * 
	 * @return updateUserId Update_User_ID
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置Update_User_ID
	 * 
	 * @param updateUserId Update_User_ID
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * 取得Hibernate_Version
	 * 
	 * @return version Hibernate_Version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * 设置Hibernate_Version
	 * 
	 * @param version Hibernate_Version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

}
