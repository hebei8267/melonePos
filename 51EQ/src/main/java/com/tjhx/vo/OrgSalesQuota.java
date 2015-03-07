/**
 * 
 */
package com.tjhx.vo;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.tjhx.entity.info.SalesOrdersDayTotal;

/**
 * 门店销售指标
 */
public class OrgSalesQuota {
	/** 机构名称 */
	private String orgName;
	/** 机构编号 */
	private String orgId;
	/** 排名（客单数） */
	private Double qtyTrend;
	/** 排名（客单价） */
	private Double amtTrend;

	/** 销售金额1 */
	private BigDecimal saleAmt1 = new BigDecimal("0");
	/** 销售单数量1 */
	private Integer ordersNum1 = new Integer("0");
	/** 销售单均价1 */
	private BigDecimal ordersAvgPrice1 = new BigDecimal("0");

	/** 销售金额2 */
	private BigDecimal saleAmt2 = new BigDecimal("0");
	/** 销售单数量2 */
	private Integer ordersNum2 = new Integer("0");
	/** 销售单均价2 */
	private BigDecimal ordersAvgPrice2 = new BigDecimal("0");

	/**
	 * 获取机构名称
	 * 
	 * @return orgName
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
	 * @return orgId
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
	 * 获取排名（客单数）
	 * 
	 * @return qtyTrend
	 */
	public Double getQtyTrend() {

		if (0 != ordersNum1) {
			int _ordersNum = ordersNum2 - ordersNum1;
			qtyTrend = new BigDecimal(_ordersNum).divide(new BigDecimal(ordersNum1), 4, BigDecimal.ROUND_UP).setScale(4)
					.doubleValue() * 100;
		}
		return qtyTrend;
	}

	/**
	 * 设置排名（客单数）
	 * 
	 * @param qtyTrend 排名（客单数）
	 */
	public void setQtyTrend(Double qtyTrend) {
		this.qtyTrend = qtyTrend;
	}

	/**
	 * 获取排名（客单价）
	 * 
	 * @return amtTrend
	 */
	public Double getAmtTrend() {
		if (saleAmt1.compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal _saleAmt = saleAmt2.subtract(saleAmt1);
			amtTrend = _saleAmt.divide(saleAmt1, 4, BigDecimal.ROUND_UP).setScale(4).doubleValue() * 100;
		}
		return amtTrend;
	}

	/**
	 * 设置排名（客单价）
	 * 
	 * @param amtTrend 排名（客单价）
	 */
	public void setAmtTrend(Double amtTrend) {
		this.amtTrend = amtTrend;
	}

	/**
	 * 获取销售金额1
	 * 
	 * @return saleAmt1
	 */
	public BigDecimal getSaleAmt1() {
		return saleAmt1;
	}

	/**
	 * 设置销售金额1
	 * 
	 * @param saleAmt1 销售金额1
	 */
	public void setSaleAmt1(BigDecimal saleAmt1) {
		this.saleAmt1 = saleAmt1;
	}

	/**
	 * 获取销售单数量1
	 * 
	 * @return ordersNum1
	 */
	public Integer getOrdersNum1() {
		return ordersNum1;
	}

	/**
	 * 设置销售单数量1
	 * 
	 * @param ordersNum1 销售单数量1
	 */
	public void setOrdersNum1(Integer ordersNum1) {
		this.ordersNum1 = ordersNum1;
	}

	/**
	 * 获取销售单均价1
	 * 
	 * @return ordersAvgPrice1
	 */
	public BigDecimal getOrdersAvgPrice1() {
		return ordersAvgPrice1;
	}

	/**
	 * 设置销售单均价1
	 * 
	 * @param ordersAvgPrice1 销售单均价1
	 */
	public void setOrdersAvgPrice1(BigDecimal ordersAvgPrice1) {
		this.ordersAvgPrice1 = ordersAvgPrice1;
	}

	/**
	 * 获取销售金额2
	 * 
	 * @return saleAmt2
	 */
	public BigDecimal getSaleAmt2() {
		return saleAmt2;
	}

	/**
	 * 设置销售金额2
	 * 
	 * @param saleAmt2 销售金额2
	 */
	public void setSaleAmt2(BigDecimal saleAmt2) {
		this.saleAmt2 = saleAmt2;
	}

	/**
	 * 获取销售单数量2
	 * 
	 * @return ordersNum2
	 */
	public Integer getOrdersNum2() {
		return ordersNum2;
	}

	/**
	 * 设置销售单数量2
	 * 
	 * @param ordersNum2 销售单数量2
	 */
	public void setOrdersNum2(Integer ordersNum2) {
		this.ordersNum2 = ordersNum2;
	}

	/**
	 * 获取销售单均价2
	 * 
	 * @return ordersAvgPrice2
	 */
	public BigDecimal getOrdersAvgPrice2() {
		return ordersAvgPrice2;
	}

	/**
	 * 设置销售单均价2
	 * 
	 * @param ordersAvgPrice2 销售单均价2
	 */
	public void setOrdersAvgPrice2(BigDecimal ordersAvgPrice2) {
		this.ordersAvgPrice2 = ordersAvgPrice2;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SalesOrdersDayTotal)) {
			return false;
		}

		SalesOrdersDayTotal rhs = (SalesOrdersDayTotal) obj;
		return new EqualsBuilder().append(this.getOrgId(), rhs.getOrgId()).isEquals();

	}
}
