package com.tjhx.entity.struct;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 机构
 */
@Entity
@Table(name = "T_ORGANIZATION")
public class Organization extends IdEntity {

	private static final long serialVersionUID = 5559157391146119369L;

	/** 机构编号 */
	private String id;
	/** 机构编号-百威 */
	private String bwId;
	/** 机构资金-百威 */
	private String bwBranchNo;
	/** 机构编号-中控打卡机 */
	private Integer zkId;
	/** 机构名称 */
	private String name;
	/** Index */
	private Integer index;
	/** 子机构List */
	private List<Organization> subOrgList = new ArrayList<Organization>();

	/** 父机构 */
	private Organization parentOrg;

	/** 机构地址 */
	private String orgAdd;
	/** 机构地址简称 */
	private String orgAddShort;

	/** 督导员 */
	private String mngUserId;
	private String mngUserName;
	/** 门店品牌 */
	private String brand;
	/** 闭店标记 */
	private boolean closedFlg;

	public Organization() {

	}

	public Organization(String id) {
		this.id = id;
	}

	/**
	 * 取得机构编号
	 * 
	 * @return id 机构编号
	 */
	@NaturalId
	@Column(name = "ORG_ID", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	/**
	 * 设置机构编号
	 * 
	 * @param id 机构编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得机构编号-百威
	 * 
	 * @return bwId 机构编号-百威
	 */
	@Column(name = "ORG_BW_ID", nullable = false, length = 32)
	public String getBwId() {
		return bwId;
	}

	/**
	 * 设置机构资金编号-百威
	 * 
	 * @param bwId 机构资金编号-百威
	 */
	public void setBwId(String bwId) {
		this.bwId = bwId;
	}

	/**
	 * 取得机构资金编号-百威
	 * 
	 * @return bwBranchNo 机构资金编号-百威
	 */
	@Column(length = 8)
	public String getBwBranchNo() {
		return bwBranchNo;
	}

	/**
	 * 设置机构编号-百威
	 * 
	 * @param bwBranchNo 机构编号-百威
	 */
	public void setBwBranchNo(String bwBranchNo) {
		this.bwBranchNo = bwBranchNo;
	}

	/**
	 * 取得机构名称
	 * 
	 * @return name 机构名称
	 */
	@Basic
	@Column(name = "ORG_NAME", nullable = false, length = 32, unique = true)
	public String getName() {
		return name;
	}

	/**
	 * 设置机构名称
	 * 
	 * @param name 机构名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得Index
	 * 
	 * @return index Index
	 */
	@Basic
	@Column(name = "_INDEX")
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置Index
	 * 
	 * @param index Index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * 取得子机构List
	 * 
	 * @return subOrgList 子机构List
	 */
	@OneToMany(mappedBy = "parentOrg", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexColumn(name = "_INDEX", base = 1)
	public List<Organization> getSubOrgList() {
		return subOrgList;
	}

	/**
	 * 设置子机构List
	 * 
	 * @param subOrgList 子机构List
	 */
	public void setSubOrgList(List<Organization> subOrgList) {
		this.subOrgList = subOrgList;
	}

	/**
	 * 添加子机构
	 * 
	 * @param org 子机构
	 */
	public void addSubOrg(Organization org) {
		int index = 1;
		for (Organization _organization : this.subOrgList) {
			if (_organization.getIndex() != null) {
				_organization.setIndex(index);
				index++;
			}
		}

		org.setIndex(index);
		this.subOrgList.add(org);
	}

	/**
	 * 取得父机构
	 * 
	 * @return parentOrg 父机构
	 */
	@ManyToOne
	@JoinColumn(name = "PARENT_ORG_H_ID")
	public Organization getParentOrg() {
		return parentOrg;
	}

	/**
	 * 设置父机构
	 * 
	 * @param parentOrg 父机构
	 */
	public void setParentOrg(Organization parentOrg) {
		this.parentOrg = parentOrg;
	}

	/**
	 * 取得机构编号-中控打卡机
	 * 
	 * @return zkId 机构编号-中控打卡机
	 */
	public Integer getZkId() {
		return zkId;
	}

	/**
	 * 设置机构编号-中控打卡机
	 * 
	 * @param zkId 机构编号-中控打卡机
	 */
	public void setZkId(Integer zkId) {
		this.zkId = zkId;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Organization)) {
			return false;
		}
		Organization rhs = (Organization) obj;
		return new EqualsBuilder().append(this.getId(), rhs.getId()).isEquals();
	}

	/**
	 * 取得机构地址
	 * 
	 * @return 机构地址
	 */
	@Column(length = 64)
	public String getOrgAdd() {
		return orgAdd;
	}

	/**
	 * 设置机构地址
	 * 
	 * @param orgAdd 机构地址
	 */
	public void setOrgAdd(String orgAdd) {
		this.orgAdd = orgAdd;
	}

	/**
	 * 取得机构地址简称
	 * 
	 * @return 机构地址简称
	 */
	@Column(length = 32)
	public String getOrgAddShort() {
		return orgAddShort;
	}

	/**
	 * 设置机构地址简称
	 * 
	 * @param orgAddShort 机构地址简称
	 */
	public void setOrgAddShort(String orgAddShort) {
		this.orgAddShort = orgAddShort;
	}

	/**
	 * 取得督导员
	 * 
	 * @return 督导员
	 */
	@Column(length = 32)
	public String getMngUserId() {
		return mngUserId;
	}

	/**
	 * 设置督导员
	 * 
	 * @param mngUserId 督导员
	 */
	public void setMngUserId(String mngUserId) {
		this.mngUserId = mngUserId;
	}

	/**
	 * @return the mngUserName
	 */
	@Transient
	public String getMngUserName() {
		return mngUserName;
	}

	/**
	 * @param mngUserName the mngUserName to set
	 */
	public void setMngUserName(String mngUserName) {
		this.mngUserName = mngUserName;
	}

	/**
	 * 取得门店品牌
	 * 
	 * @return 门店品牌
	 */
	@Column(length = 32)
	public String getBrand() {
		return brand;
	}

	/**
	 * 设置门店品牌
	 * 
	 * @param brand 门店品牌
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * 取得闭店标记
	 * 
	 * @return 闭店标记
	 */
	public boolean isClosedFlg() {
		return closedFlg;
	}

	/**
	 * 设置闭店标记
	 * 
	 * @param closedFlg 闭店标记
	 */
	public void setClosedFlg(boolean closedFlg) {
		this.closedFlg = closedFlg;
	}
}
