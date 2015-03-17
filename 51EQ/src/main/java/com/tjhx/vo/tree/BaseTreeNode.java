package com.tjhx.vo.tree;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 树节点
 */
public abstract class BaseTreeNode {

	/** 对象唯一标识 */
	protected Integer guuid;
	/** 节点ID */
	protected String id;
	/** 节点显示内容 */
	protected String text;
	/** 节点显示图标 */
	protected String icon;
	/** 子节点 */
	protected List<BaseTreeNode> children = Lists.newArrayList();
	/** 节点状态 */
	protected TreeNodeState state;

	/**
	 * 获取对象唯一标识
	 * 
	 * @return 对象唯一标识
	 */
	public Integer getGuuid() {
		return guuid;
	}

	/**
	 * 设置对象唯一标识
	 * 
	 * @param guuid 对象唯一标识
	 */
	public void setGuuid(Integer guuid) {
		this.guuid = guuid;
	}

	/**
	 * 获取节点ID
	 * 
	 * @return 节点ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置节点ID
	 * 
	 * @param id 节点ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取节点显示内容
	 * 
	 * @return 节点显示内容
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置节点显示内容
	 * 
	 * @param text 节点显示内容
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 获取节点显示图标
	 * 
	 * @return 节点显示图标
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置节点显示图标
	 * 
	 * @param icon 节点显示图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取子节点
	 * 
	 * @return 子节点
	 */
	public List<BaseTreeNode> getChildren() {
		return children;
	}

	/**
	 * 设置子节点
	 * 
	 * @param children 子节点
	 */
	public void setChildren(List<BaseTreeNode> children) {
		this.children = children;
	}

	/**
	 * 添加子节点
	 * 
	 * @param children 子节点
	 */
	public void addChildren(BaseTreeNode children) {
		if (null == children) {
			return;
		}

		this.children.add(children);
	}

	/**
	 * 获取节点状态
	 * 
	 * @return 节点状态
	 */
	public TreeNodeState getState() {
		return state;
	}

	/**
	 * 设置节点状态
	 * 
	 * @param state 节点状态
	 */
	public void setState(TreeNodeState state) {
		this.state = state;
	}
}
