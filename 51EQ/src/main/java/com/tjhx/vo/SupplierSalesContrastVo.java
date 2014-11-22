/**
 * 
 */
package com.tjhx.vo;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.tjhx.entity.info.SalesDayTotalSup;
import com.tjhx.entity.info.StoreDetail;

public class SupplierSalesContrastVo {
	/** 机构名称 */
	private String orgName;
	/** 机构编号 */
	private String orgId;
	/** 货商编号 */
	private String supplierNo;
	/** 货商名称 */
	private String supplierName;

	/** 实销数量1 */
	private BigDecimal saleRqty1 = new BigDecimal("0");
	/** 实销金额1 */
	private BigDecimal saleRamt1 = new BigDecimal("0");
	/** 实销均价1 */
	private BigDecimal salePrice1 = new BigDecimal("0");

	/** 实销数量2 */
	private BigDecimal saleRqty2 = new BigDecimal("0");
	/** 实销金额2 */
	private BigDecimal saleRamt2 = new BigDecimal("0");
	/** 实销均价2 */
	private BigDecimal salePrice2 = new BigDecimal("0");
	/** 销售额增长/下降率 */
	private BigDecimal salesContrast = new BigDecimal("0");

	/** 库存数量1 */
	private BigDecimal stockTotalQty1 = new BigDecimal("0");
	/** 库存数量2 */
	private BigDecimal stockTotalQty2 = new BigDecimal("0");

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
	 * 获取货商编号
	 * 
	 * @return supplierNo 货商编号
	 */
	public String getSupplierNo() {
		return supplierNo;
	}

	/**
	 * 设置货商编号
	 * 
	 * @param supplierNo 货商编号
	 */
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	/**
	 * 获取货商名称
	 * 
	 * @return supplierName 货商名称
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 设置货商名称
	 * 
	 * @param supplierName 货商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
	 * 获取销售额增长下降率
	 * 
	 * @return salesContrast 销售额增长下降率
	 */
	public BigDecimal getSalesContrast() {
		return salesContrast;
	}

	/**
	 * 设置销售额增长下降率
	 * 
	 * @param salesContrast 销售额增长下降率
	 */
	public void setSalesContrast(BigDecimal salesContrast) {
		this.salesContrast = salesContrast;
	}

	/**
	 * 获取库存数量1
	 * 
	 * @return stockTotalQty1 库存数量1
	 */
	public BigDecimal getStockTotalQty1() {
		return stockTotalQty1;
	}

	/**
	 * 设置库存数量1
	 * 
	 * @param stockTotalQty1 库存数量1
	 */
	public void setStockTotalQty1(BigDecimal stockTotalQty1) {
		this.stockTotalQty1 = stockTotalQty1;
	}

	/**
	 * 获取库存数量2
	 * 
	 * @return stockTotalQty2 库存数量2
	 */
	public BigDecimal getStockTotalQty2() {
		return stockTotalQty2;
	}

	/**
	 * 设置库存数量2
	 * 
	 * @param stockTotalQty2 库存数量2
	 */
	public void setStockTotalQty2(BigDecimal stockTotalQty2) {
		this.stockTotalQty2 = stockTotalQty2;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SalesDayTotalSup || obj instanceof StoreDetail || obj instanceof SupplierSalesContrastVo)) {
			return false;
		}

		if (obj instanceof SalesDayTotalSup) {
			SalesDayTotalSup rhs = (SalesDayTotalSup) obj;
			return new EqualsBuilder().append(this.getOrgId(), rhs.getOrgId())
					.append(this.getSupplierNo(), rhs.getSupplierBwId()).isEquals();
		} else if (obj instanceof StoreDetail) {
			StoreDetail rhs = (StoreDetail) obj;
			return new EqualsBuilder().append(this.getOrgId(), rhs.getOrgId())
					.append(this.getSupplierNo(), rhs.getSupplierBwId()).isEquals();
		} else {
			SupplierSalesContrastVo rhs = (SupplierSalesContrastVo) obj;
			return new EqualsBuilder().append(this.getSupplierNo(), rhs.getSupplierNo()).isEquals();
		}

	}
}
