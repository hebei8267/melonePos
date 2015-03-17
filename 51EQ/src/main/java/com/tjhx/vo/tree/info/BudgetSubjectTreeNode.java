package com.tjhx.vo.tree.info;

import com.tjhx.entity.info.BudgetSubject;
import com.tjhx.vo.tree.BaseTreeNode;

public class BudgetSubjectTreeNode extends BaseTreeNode {
	/** 预算科目父节点Uuid */
	private Integer parentSubUuid;
	/** 预算科目父节点名称 */
	private String parentSubName;
	/** 排序 */
	private int sortIndex;

	public BudgetSubjectTreeNode() {

	}

	public BudgetSubjectTreeNode(BudgetSubject sub) {
		this.id = sub.getUuid().toString();
		this.text = sub.getSubName();

		// 预算科目父节点Uuid
		this.parentSubUuid = sub.getParentSubUuid();
		// 预算科目父节点名称
		this.parentSubName = sub.getParentSubName();
		// 排序
		this.sortIndex = sub.getSortIndex();

	}

	/**
	 * 获取预算科目父节点Uuid
	 * 
	 * @return parentSubUuid
	 */
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
	 * 获取排序
	 * 
	 * @return sortIndex
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

}
