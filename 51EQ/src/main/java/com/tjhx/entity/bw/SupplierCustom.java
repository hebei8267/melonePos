package com.tjhx.entity.bw;

import com.tjhx.entity.info.Supplier;

/**
 * 货品供应商
 */
public class SupplierCustom {
	/** 供应商编号 */
	private String supcustNo;
	/** 供应商名称 */
	private String supName;
	/** 供应商所属区域 */
	private String regionNo;
	/** 显示标记 0-不显示 1-显示 */
	private String displayFlag;

	/**
	 * 取得供应商编号
	 * 
	 * @return supcustNo 供应商编号
	 */
	public String getSupcustNo() {
		return supcustNo;
	}

	/**
	 * 设置供应商编号
	 * 
	 * @param supcustNo 供应商编号
	 */
	public void setSupcustNo(String supcustNo) {
		this.supcustNo = supcustNo;
	}

	/**
	 * 取得供应商名称
	 * 
	 * @return supName 供应商名称
	 */
	public String getSupName() {
		return supName;
	}

	/**
	 * 设置供应商名称
	 * 
	 * @param supName 供应商名称
	 */
	public void setSupName(String supName) {
		this.supName = supName;
	}

	/**
	 * 取得供应商所属区域
	 * 
	 * @return regionNo 供应商所属区域
	 */
	public String getRegionNo() {
		return regionNo;
	}

	/**
	 * 设置供应商所属区域
	 * 
	 * @param regionNo 供应商所属区域
	 */
	public void setRegionNo(String regionNo) {
		this.regionNo = regionNo;
	}

	/**
	 * 取得显示标记0-不显示1-显示
	 * 
	 * @return displayFlag 显示标记0-不显示1-显示
	 */
	public String getDisplayFlag() {
		return displayFlag;
	}

	/**
	 * 设置显示标记0-不显示1-显示
	 * 
	 * @param displayFlag 显示标记0-不显示1-显示
	 */
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Supplier))
			return false;

		return this.getSupcustNo().equals(((Supplier) obj).getSupplierBwId());
	}
}
