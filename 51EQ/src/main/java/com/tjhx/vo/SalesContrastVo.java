/**
 * 
 */
package com.tjhx.vo;

import java.math.BigDecimal;

/** */
public class SalesContrastVo {
	/** 机构名称 */
	private String orgName;
	/** 机构编号 */
	private String orgId;
	/** 类型编号 */
	private String itemClsNo;
	/** 类型名称 */
	private String itemName;

	/** 实销数量1 */
	private BigDecimal saleRqty1;
	/** 实销金额1 */
	private BigDecimal saleRamt1;
	/** 实销均价1 */
	private BigDecimal salePrice1;

	/** 实销数量2 */
	private BigDecimal saleRqty2;
	/** 实销金额2 */
	private BigDecimal saleRamt2;
	/** 实销均价2 */
	private BigDecimal salePrice2;
	/** 销售额增长/下降率 */
	private BigDecimal salesContrast;

	/**
	 * 获取机构名称
	 * 
	 * @return orgName 机构名称
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置机构名称
	 * 
	 * @param orgName 机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取机构编号
	 * 
	 * @return orgId 机构编号
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置机构编号
	 * 
	 * @param orgId 机构编号
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取类型编号
	 * 
	 * @return itemClsNo 类型编号
	 */
	public String getItemClsNo() {
		return itemClsNo;
	}

	/**
	 * 设置类型编号
	 * 
	 * @param itemClsNo 类型编号
	 */
	public void setItemClsNo(String itemClsNo) {
		this.itemClsNo = itemClsNo;
	}

	/**
	 * 获取类型名称
	 * 
	 * @return itemName 类型名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置类型名称
	 * 
	 * @param itemName 类型名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取实销数量1
	 * 
	 * @return saleRqty1 实销数量1
	 */
	public BigDecimal getSaleRqty1() {
		return saleRqty1;
	}

	/**
	 * 设置实销数量1
	 * 
	 * @param saleRqty1 实销数量1
	 */
	public void setSaleRqty1(BigDecimal saleRqty1) {
		this.saleRqty1 = saleRqty1;
	}

	/**
	 * 获取实销金额1
	 * 
	 * @return saleRamt1 实销金额1
	 */
	public BigDecimal getSaleRamt1() {
		return saleRamt1;
	}

	/**
	 * 设置实销金额1
	 * 
	 * @param saleRamt1 实销金额1
	 */
	public void setSaleRamt1(BigDecimal saleRamt1) {
		this.saleRamt1 = saleRamt1;
	}

	/**
	 * 获取实销均价1
	 * 
	 * @return salePrice1 实销均价1
	 */
	public BigDecimal getSalePrice1() {
		return salePrice1;
	}

	/**
	 * 设置实销均价1
	 * 
	 * @param salePrice1 实销均价1
	 */
	public void setSalePrice1(BigDecimal salePrice1) {
		this.salePrice1 = salePrice1;
	}

	/**
	 * 获取实销数量2
	 * 
	 * @return saleRqty2 实销数量2
	 */
	public BigDecimal getSaleRqty2() {
		return saleRqty2;
	}

	/**
	 * 设置实销数量2
	 * 
	 * @param saleRqty2 实销数量2
	 */
	public void setSaleRqty2(BigDecimal saleRqty2) {
		this.saleRqty2 = saleRqty2;
	}

	/**
	 * 获取实销金额2
	 * 
	 * @return saleRamt2 实销金额2
	 */
	public BigDecimal getSaleRamt2() {
		return saleRamt2;
	}

	/**
	 * 设置实销金额2
	 * 
	 * @param saleRamt2 实销金额2
	 */
	public void setSaleRamt2(BigDecimal saleRamt2) {
		this.saleRamt2 = saleRamt2;
	}

	/**
	 * 获取实销均价2
	 * 
	 * @return salePrice2 实销均价2
	 */
	public BigDecimal getSalePrice2() {
		return salePrice2;
	}

	/**
	 * 设置实销均价2
	 * 
	 * @param salePrice2 实销均价2
	 */
	public void setSalePrice2(BigDecimal salePrice2) {
		this.salePrice2 = salePrice2;
	}

	/**
	 * 获取销售额增长/下降率
	 * 
	 * @return salesContrast 销售额增长/下降率
	 */
	public BigDecimal getSalesContrast() {
		return salesContrast;
	}

	/**
	 * 设置销售额增长/下降率
	 * 
	 * @param salesContrast 销售额增长/下降率
	 */
	public void setSalesContrast(BigDecimal salesContrast) {
		this.salesContrast = salesContrast;
	}
}
