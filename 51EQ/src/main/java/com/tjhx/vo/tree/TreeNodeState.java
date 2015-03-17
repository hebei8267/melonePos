package com.tjhx.vo.tree;

public class TreeNodeState {

	private boolean opened; // is the node open
	private boolean disabled; // is the node disabled
	private boolean selected;// is the node selected

	public TreeNodeState() {
	}

	public TreeNodeState(boolean selected) {
		this.selected = selected;
	}

	/**
	 * 获取opened
	 * 
	 * @return opened
	 */
	public boolean isOpened() {
		return opened;
	}

	/**
	 * 设置opened
	 * 
	 * @param opened opened
	 */
	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	/**
	 * 获取disabled
	 * 
	 * @return disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * 设置disabled
	 * 
	 * @param disabled disabled
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * 获取selected
	 * 
	 * @return selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * 设置selected
	 * 
	 * @param selected selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
