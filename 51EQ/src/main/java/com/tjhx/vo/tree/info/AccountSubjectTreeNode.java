package com.tjhx.vo.tree.info;

import com.tjhx.entity.info.AccountSubject;
import com.tjhx.vo.tree.BaseTreeNode;

public class AccountSubjectTreeNode extends BaseTreeNode {
	/** 记账科目父节点Uuid */
	private Integer parentSubUuid;
	/** 记账科目父节点名称 */
	private String parentSubName;
	/** 排序 */
	private int sortIndex;

	public AccountSubjectTreeNode() {

	}

	public AccountSubjectTreeNode(AccountSubject sub) {
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
	 * 取得
	 * 
	 * @return parentSubUuid
	 */
	public Integer getParentSubUuid() {
		return parentSubUuid;
	}

	/**
	 * 设置
	 * 
	 * @param parentSubUuid parentSubUuid
	 */
	public void setParentSubUuid(Integer parentSubUuid) {
		this.parentSubUuid = parentSubUuid;
	}

	/**
	 * 取得
	 * 
	 * @return parentSubName
	 */
	public String getParentSubName() {
		return parentSubName;
	}

	/**
	 * 设置
	 * 
	 * @param parentSubName parentSubName
	 */
	public void setParentSubName(String parentSubName) {
		this.parentSubName = parentSubName;
	}

	/**
	 * 取得
	 * 
	 * @return sortIndex
	 */
	public int getSortIndex() {
		return sortIndex;
	}

	/**
	 * 设置
	 * 
	 * @param sortIndex sortIndex
	 */
	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}
}
