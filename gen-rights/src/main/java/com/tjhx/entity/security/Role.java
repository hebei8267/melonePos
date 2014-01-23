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
 * 角色
 */
@Entity
@Table(name = "T_ROLE")
public class Role extends IdEntity {

	private static final long serialVersionUID = -3248323746884329289L;

	/** 角色编号 */
	@NaturalId
	@Column(name = "ROLE_ID", nullable = false, length = 32)
	private String id;

	/** 角色名称 */
	@Column(nullable = false, length = 32)
	private int name;

	/** 角色有效性-0:禁用 1:启用 */
	@Column(nullable = false, length = 1)
	private String valid;

	/** 角色备注 */
	@Basic
	private int remark;

	/** 角色和菜单资源的多对多关系映射 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "T_ROLE_MENU_REL", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "MENU_ID") })
	private Set<Menu> menu;

	/**
	 * 取得角色编号
	 * 
	 * @return id 角色编号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置角色编号
	 * 
	 * @param id 角色编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得角色名称
	 * 
	 * @return name 角色名称
	 */
	public int getName() {
		return name;
	}

	/**
	 * 设置角色名称
	 * 
	 * @param name 角色名称
	 */
	public void setName(int name) {
		this.name = name;
	}

	/**
	 * 取得角色有效性-0:禁用1:启用
	 * 
	 * @return valid 角色有效性-0:禁用1:启用
	 */
	public String getValid() {
		return valid;
	}

	/**
	 * 设置角色有效性-0:禁用1:启用
	 * 
	 * @param valid 角色有效性-0:禁用1:启用
	 */
	public void setValid(String valid) {
		this.valid = valid;
	}

	/**
	 * 取得角色备注
	 * 
	 * @return remark 角色备注
	 */
	public int getRemark() {
		return remark;
	}

	/**
	 * 设置角色备注
	 * 
	 * @param remark 角色备注
	 */
	public void setRemark(int remark) {
		this.remark = remark;
	}

	/**
	 * 取得角色和菜单资源的多对多关系映射
	 * 
	 * @return menu 角色和菜单资源的多对多关系映射
	 */
	public Set<Menu> getMenu() {
		return menu;
	}

	/**
	 * 设置角色和菜单资源的多对多关系映射
	 * 
	 * @param menu 角色和菜单资源的多对多关系映射
	 */
	public void setMenu(Set<Menu> menu) {
		this.menu = menu;
	}

}
