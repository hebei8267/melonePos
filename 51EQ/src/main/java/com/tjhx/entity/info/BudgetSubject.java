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

import com.google.common.collect.Lists;
import com.tjhx.entity.IdEntity;

/**
 * 预算科目
 */
@Entity
@Table(name = "T_BUDGET_SUBJECT")
public class BudgetSubject extends IdEntity {

	private static final long serialVersionUID = 6623863688516776919L;
	/** 预算科目父节点 */
	private BudgetSubject parentSub;
	/** 预算科目父节点Uuid */
	private Integer parentSubUuid;
	/** 预算科目父节点名称 */
	private String parentSubName;
	/** 子预算科目节点 */
	private List<BudgetSubject> childrenSubList = Lists.newArrayList();
	/** 预算科目名称 */
	private String subName;
	/** 级别 */
	private int level;
	/** 排序 */
	private int sortIndex;
	/** 删除标识 0有效 1已删除 */
	private String delFlg = "0";

	public BudgetSubject() {

	}

	/**
	 * 获取预算科目父节点
	 * 
	 * @return parentSub
	 */
	@ManyToOne
	@JoinColumn(name = "PARENT_UUID")
	public BudgetSubject getParentSub() {
		return parentSub;
	}

	/**
	 * 设置预算科目父节点
	 * 
	 * @param parentSub 预算科目父节点
	 */
	public void setParentSub(BudgetSubject parentSub) {
		this.parentSub = parentSub;
	}

	/**
	 * 获取预算科目父节点Uuid
	 * 
	 * @return parentSubUuid
	 */
	@Transient
	public Integer getParentSubUuid() {
		return parentSubUuid;
	}

	/**
	 * 设置预算科目父节点Uuid
	 * 
	 * @param parentSubUuid 预算科目父节点Uuid
	 */
	public void setParentSubUuid(Integer parentSubUuid) {
		this.parentSubUuid = parentSubUuid;
	}

	/**
	 * 获取预算科目父节点名称
	 * 
	 * @return parentSubName
	 */
	@Transient
	public String getParentSubName() {
		return parentSubName;
	}

	/**
	 * 设置预算科目父节点名称
	 * 
	 * @param parentSubName 预算科目父节点名称
	 */
	public void setParentSubName(String parentSubName) {
		this.parentSubName = parentSubName;
	}

	/**
	 * 获取子预算科目节点
	 * 
	 * @return childrenSubList
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentSub")
	@OrderBy("sortIndex")
	public List<BudgetSubject> getChildrenSubList() {
		return childrenSubList;
	}

	/**
	 * 设置子预算科目节点
	 * 
	 * @param childrenSubList 子预算科目节点
	 */
	public void setChildrenSubList(List<BudgetSubject> childrenSubList) {
		this.childrenSubList = childrenSubList;
	}

	/**
	 * 获取预算科目名称
	 * 
	 * @return subName
	 */
	@Column(length = 32)
	public String getSubName() {
		return subName;
	}

	/**
	 * 设置预算科目名称
	 * 
	 * @param subName 预算科目名称
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}

	/**
	 * 获取级别
	 * 
	 * @return level
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
	 * 获取排序
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
	 * 获取删除标识 0有效 1已删除
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
	 * 添加子预算科目节点
	 * 
	 * @param childrenSub 子预算科目节点
	 */
	public void addChildrenSub(BudgetSubject childrenSub) {
		if (null == childrenSub) {
			return;
		}

		childrenSub.setParentSub(this);

		if (!getChildrenSubList().contains(childrenSub)) {
			getChildrenSubList().add(childrenSub);
		}
	}
}
