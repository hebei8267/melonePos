package com.tjhx.entity.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 功能资源
 */
@Entity
@Table(name = "T_FUNCTION")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Function extends IdEntity {

	private static final long serialVersionUID = -2795204525062296701L;

	/** 功能显示名称 */
	private String displayName;
	/** 功能URL */
	private String funUrl;
	/** 功能描述 */
	private String descTxt;
	/** 功能标记 0-菜单功能 */
	private String funFlg;

	/**
	 * 取得功能显示名称
	 * 
	 * @return 功能显示名称
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 设置功能显示名称
	 * 
	 * @param displayName 功能显示名称
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 取得功能URL
	 * 
	 * @return 功能URL
	 */
	public String getFunUrl() {
		return funUrl;
	}

	/**
	 * 设置功能URL
	 * 
	 * @param funUrl 功能URL
	 */
	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	/**
	 * 取得功能描述
	 * 
	 * @return 功能描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置功能描述
	 * 
	 * @param descTxt 功能描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得功能标记 0-菜单功能
	 * 
	 * @return 功能标记 0-菜单功能
	 */
	@Column(length = 1)
	public String getFunFlg() {
		return funFlg;
	}

	/**
	 * 设置功能标记 0-菜单功能
	 * 
	 * @param funFlg 功能标记 0-菜单功能
	 */
	public void setFunFlg(String funFlg) {
		this.funFlg = funFlg;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Function)) {
			return false;
		}
		Function rhs = (Function) obj;
		return new EqualsBuilder().append(this.getUuid(), rhs.getUuid()).isEquals();
	}
}
