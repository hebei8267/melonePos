package com.tjhx.entity.bw;

import com.tjhx.entity.info.ItemType;

/**
 * 商品种类
 */
public class ItemTypeCls {
	/** 种类编号 */
	private String itemClsno;
	/** 种类名称 */
	private String itemClsname;

	/**
	 * 取得种类编号
	 * 
	 * @return itemClsno 种类编号
	 */
	public String getItemClsno() {
		return itemClsno;
	}

	/**
	 * 设置种类编号
	 * 
	 * @param itemClsno 种类编号
	 */
	public void setItemClsno(String itemClsno) {
		if (null != itemClsno) {
			this.itemClsno = itemClsno.trim();
		}

	}

	/**
	 * 取得种类名称
	 * 
	 * @return itemClsname 种类名称
	 */
	public String getItemClsname() {
		return itemClsname;
	}

	/**
	 * 设置种类名称
	 * 
	 * @param itemClsname 种类名称
	 */
	public void setItemClsname(String itemClsname) {
		this.itemClsname = itemClsname;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ItemType))
			return false;

		return this.getItemClsno().equals(((ItemType) obj).getItemNo());
	}
}
