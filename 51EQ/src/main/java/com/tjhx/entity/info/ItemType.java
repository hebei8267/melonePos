package com.tjhx.entity.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 商品种类
 */
@Entity
@Table(name = "T_ITEM_TYPE")
public class ItemType extends IdEntity {

	private static final long serialVersionUID = -4579594224153047525L;
	/** 种类编号 */
	private String itemNo;
	/** 种类名称 */
	private String itemShortName;
	/** 种类名称 */
	private String itemName;

	/**
	 * 取得种类编号
	 * 
	 * @return itemNo 种类编号
	 */
	@NaturalId
	@Column(length = 16)
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * 设置种类编号
	 * 
	 * @param itemNo 种类编号
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	/**
	 * 取得种类名称
	 * 
	 * @return itemShortName 种类名称
	 */
	@Column(length = 32)
	public String getItemShortName() {
		return itemShortName;
	}

	/**
	 * 设置种类名称
	 * 
	 * @param itemShortName 种类名称
	 */
	public void setItemShortName(String itemShortName) {
		this.itemShortName = itemShortName;
	}

	/**
	 * 取得种类名称
	 * 
	 * @return itemName 种类名称
	 */
	@Column(length = 128)
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置种类名称
	 * 
	 * @param itemName 种类名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
