package com.tjhx.entity.info;

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
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.google.common.collect.Lists;
import com.tjhx.entity.IdEntity;

/**
 * 记账科目
 */
@Entity
@Table(name = "T_ACCOUNT_SUBJECT")
public class AccountSubject extends IdEntity {

	private static final long serialVersionUID = 7547035268785708331L;

	/** 科目编号 */
	private String subId;
	/** 记账科目父节点 */
	private AccountSubject parentSub;
	/** 记账科目父节点Uuid */
	private Integer parentSubUuid;
	/** 记账科目父节点名称 */
	private String parentSubName;
	/** 子记账科目节点 */
	private List<AccountSubject> childrenSubList = Lists.newArrayList();
	/** 记账科目名称 */
	private String subName;
	/** 级别 */
	private int level;
	/** 排序 */
	private int sortIndex;
	/** 删除标识 0有效 1已删除 */
	private String delFlg = "0";

	public AccountSubject() {

	}

	/**
	 * 取得科目编号
	 * 
	 * @return 科目编号
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getSubId() {
		return subId;
	}

	/**
	 * 设置科目编号
	 * 
	 * @param subId 科目编号
	 */
	public void setSubId(String subId) {
		this.subId = subId;
	}

	/**
	 * 取得记账科目父节点
	 * 
	 * @return 记账科目父节点
	 */
	@ManyToOne
	@JoinColumn(name = "PARENT_UUID")
	public AccountSubject getParentSub() {
		return parentSub;
	}

	/**
	 * 设置记账科目父节点
	 * 
	 * @param parentSub 记账科目父节点
	 */
	public void setParentSub(AccountSubject parentSub) {
		this.parentSub = parentSub;
	}

	/**
	 * 取得记账科目父节点Uuid
	 * 
	 * @return 记账科目父节点Uuid
	 */
	@Transient
	public Integer getParentSubUuid() {
		return parentSubUuid;
	}

	/**
	 * 设置记账科目父节点Uuid
	 * 
	 * @param parentSubUuid 记账科目父节点Uuid
	 */
	public void setParentSubUuid(Integer parentSubUuid) {
		this.parentSubUuid = parentSubUuid;
	}

	/**
	 * 取得记账科目父节点名称
	 * 
	 * @return 记账科目父节点名称
	 */
	@Transient
	public String getParentSubName() {
		return parentSubName;
	}

	/**
	 * 设置记账科目父节点名称
	 * 
	 * @param parentSubName 记账科目父节点名称
	 */
	public void setParentSubName(String parentSubName) {
		this.parentSubName = parentSubName;
	}

	/**
	 * 取得子记账科目节点
	 * 
	 * @return 子记账科目节点
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentSub")
	@OrderBy("sortIndex")
	public List<AccountSubject> getChildrenSubList() {
		return childrenSubList;
	}

	/**
	 * 设置子记账科目节点
	 * 
	 * @param childrenSubList 子记账科目节点
	 */
	public void setChildrenSubList(List<AccountSubject> childrenSubList) {
		this.childrenSubList = childrenSubList;
	}

	/**
	 * 取得记账科目名称
	 * 
	 * @return 记账科目名称
	 */
	@Column(length = 32)
	public String getSubName() {
		return subName;
	}

	/**
	 * 设置记账科目名称
	 * 
	 * @param subName 记账科目名称
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}

	/**
	 * 取得级别
	 * 
	 * @return 级别
	 */
	@Column(name = "_level")
	public int getLevel() {
		return level;
	}

	/**
	 * 设置级别
	 * 
	 * @param level 级别
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * 取得排序
	 * 
	 * @return 排序
	 */
	public int getSortIndex() {
		return sortIndex;
	}

	/**
	 * 设置排序
	 * 
	 * @param sortIndex 排序
	 */
	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	/**
	 * 取得删除标识 0有效 1已删除
	 * 
	 * @return 删除标识 0有效 1已删除
	 */
	@Column(length = 1)
	public String getDelFlg() {
		return delFlg;
	}

	/**
	 * 设置删除标识 0有效 1已删除
	 * 
	 * @param delFlg 删除标识 0有效 1已删除
	 */
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * 添加子记账科目节点
	 * 
	 * @param childrenSub 子记账科目节点
	 */
	public void addChildrenSub(AccountSubject childrenSub) {
		if (null == childrenSub) {
			return;
		}

		childrenSub.setParentSub(this);

		if (!getChildrenSubList().contains(childrenSub)) {
			getChildrenSubList().add(childrenSub);
		}
	}

}
