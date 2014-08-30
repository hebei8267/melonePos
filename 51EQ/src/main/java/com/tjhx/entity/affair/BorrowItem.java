/**
 * 
 */
package com.tjhx.entity.affair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 借还物件
 */
@Entity
@Table(name = "T_BORROW_ITEM")
public class BorrowItem extends IdEntity {

	private static final long serialVersionUID = -4397753771579988667L;
	/** 物件编号 */
	private String itemNo;
	/** 物件名称 */
	private String itemName;
	/** 物件类型 01-证书、02-执照、03-公章、04-押金收据、99-其他 */
	private String itemType;
	/** 所属机构 */
	private String orgId;
	/** 接收时间 */
	private String optDate;
	/** 物件状态 1-在库 0-借出 */
	private String status;
	/** 备注 */
	private String descTxt;

	/**
	 * 获取物件编号
	 * 
	 * @return itemNo 物件编号
	 */
	@NaturalId
	@Column(length = 8)
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * 设置物件编号
	 * 
	 * @param itemNo 物件编号
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	/**
	 * 获取物件名称
	 * 
	 * @return itemName 物件名称
	 */
	@Column(length = 32)
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置物件名称
	 * 
	 * @param itemName 物件名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取物件类型01-证书、02-执照、03-公章、04-押金收据、99-其他
	 * 
	 * @return itemType 物件类型01-证书、02-执照、03-公章、04-押金收据、99-其他
	 */
	@Column(length = 2)
	public String getItemType() {
		return itemType;
	}

	/**
	 * 设置物件类型01-证书、02-执照、03-公章、04-押金收据、99-其他
	 * 
	 * @param itemType 物件类型01-证书、02-执照、03-公章、04-押金收据、99-其他
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * 获取所属机构
	 * 
	 * @return orgId 所属机构
	 */
	@Column(length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置所属机构
	 * 
	 * @param orgId 所属机构
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取接收时间
	 * 
	 * @return optDate 接收时间
	 */
	@Column(length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置接收时间
	 * 
	 * @param optDate 接收时间
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 获取物件状态1-在库0-借出
	 * 
	 * @return status 物件状态1-在库0-借出
	 */
	@Column(length = 1)
	public String getStatus() {
		return status;
	}

	/**
	 * 设置物件状态1-在库0-借出
	 * 
	 * @param status 物件状态1-在库0-借出
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取备注
	 * 
	 * @return descTxt 备注
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置备注
	 * 
	 * @param descTxt 备注
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}
}
