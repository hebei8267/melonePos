package com.tjhx.entity.security;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 菜单资源
 */
@Entity
@Table(name = "T_MENU")
public class Menu extends IdEntity {

	private static final long serialVersionUID = 5606903883151342615L;

	/** 父菜单节点 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_MENU_H_ID")
	private Menu parentMenu;

	/** 父菜单编号 */
	@Column(name = "PARENT_MENU_ID", length = 32)
	private String parentId;

	/** 菜单编号 */
	@NaturalId
	@Column(name = "MENU_ID", nullable = false, length = 32)
	private String id;

	/** 菜单名称 */
	@Column(nullable = false, length = 32)
	private String name;

	/** 菜单排序序号 */
	@Column(name = "_INDEX")
	private int index;

	/** 备注 */
	private String remark;

	/** 子菜单节点集合 */
	@OneToMany(mappedBy = "parentMenu", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OrderBy("index")
	private List<Menu> subMenus;

	/** 导航地址 */
	@Column(length = 64)
	private String navLink;

	/** 图标 */
	@Column(length = 32)
	private String icon;

	/**
	 * 取得父菜单节点
	 * 
	 * @return parentMenu 父菜单节点
	 */
	public Menu getParentMenu() {
		return parentMenu;
	}

	/**
	 * 设置父菜单节点
	 * 
	 * @param parentMenu 父菜单节点
	 */
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	/**
	 * 取得父菜单编号
	 * 
	 * @return parentId 父菜单编号
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父菜单编号
	 * 
	 * @param parentId 父菜单编号
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 取得菜单编号
	 * 
	 * @return id 菜单编号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置菜单编号
	 * 
	 * @param id 菜单编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得菜单名称
	 * 
	 * @return name 菜单名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置菜单名称
	 * 
	 * @param name 菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得菜单排序序号
	 * 
	 * @return index 菜单排序序号
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 设置菜单排序序号
	 * 
	 * @param index 菜单排序序号
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 取得备注
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 取得子菜单节点集合
	 * 
	 * @return subMenus 子菜单节点集合
	 */
	public List<Menu> getSubMenus() {
		return subMenus;
	}

	/**
	 * 设置子菜单节点集合
	 * 
	 * @param subMenus 子菜单节点集合
	 */
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	/**
	 * 取得导航地址
	 * 
	 * @return navLink 导航地址
	 */
	public String getNavLink() {
		return navLink;
	}

	/**
	 * 设置导航地址
	 * 
	 * @param navLink 导航地址
	 */
	public void setNavLink(String navLink) {
		this.navLink = navLink;
	}

	/**
	 * 取得图标
	 * 
	 * @return icon 图标
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置图标
	 * 
	 * @param icon 图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
