/**
 * 
 */
package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 销售单日汇总
 */
@Entity
@Table(name = "T_SALES_ORDERS_DAY_TOTAL")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesOrdersDayTotal extends IdEntity {

	private static final long serialVersionUID = 4882238833982596953L;
	/** 机构编号 */
	private String orgId;
	/** 机构资金编号 */
	private String branchNo;
	/** 日期-年月 */
	private String optDateYM;
	/** 日期-年 */
	private String optDateY;
	/** 日期 */
	private String optDate;
	/** 销售金额 */
	private BigDecimal saleAmt = new BigDecimal("0");
	/** 销售单数量 */
	private Integer ordersNum = new Integer("0");
	/** 销售单均价 */
	private BigDecimal ordersAvgPrice = new BigDecimal("0");

	/**
	 * 获取机构编号
	 * 
	 * @return orgId
	 */
	@NaturalId
	@Column(name = "ORG_ID", nullable = false, length = 32)
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
	 * 获取机构资金编号
	 * 
	 * @return branchNo
	 */
	@Column(length = 8)
	public String getBranchNo() {
		return branchNo;
	}

	/**
	 * 设置机构资金编号
	 * 
	 * @param branchNo 机构资金编号
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	/**
	 * 获取日期-年月
	 * 
	 * @return optDateYM
	 */
	@Column(name = "OPT_DATE_Y_M", length = 6)
	public String getOptDateYM() {
		return optDateYM;
	}

	/**
	 * 设置日期-年月
	 * 
	 * @param optDateYM 日期-年月
	 */
	public void setOptDateYM(String optDateYM) {
		this.optDateYM = optDateYM;
	}

	/**
	 * 获取日期-年
	 * 
	 * @return optDateY
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置日期-年
	 * 
	 * @param optDateY 日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 获取日期
	 * 
	 * @return optDate
	 */
	@NaturalId
	@Column(name = "OPT_DATE", length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDate 日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 获取销售金额
	 * 
	 * @return saleAmt
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置销售金额
	 * 
	 * @param saleAmt 销售金额
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 获取销售单数量
	 * 
	 * @return ordersNum
	 */
	public Integer getOrdersNum() {
		return ordersNum;
	}

	/**
	 * 设置销售单数量
	 * 
	 * @param ordersNum 销售单数量
	 */
	public void setOrdersNum(Integer ordersNum) {
		this.ordersNum = ordersNum;
	}

	/**
	 * 获取销售单均价
	 * 
	 * @return ordersAvgPrice
	 */
	public BigDecimal getOrdersAvgPrice() {
		return ordersAvgPrice;
	}

	/**
	 * 设置销售单均价
	 * 
	 * @param ordersAvgPrice 销售单均价
	 */
	public void setOrdersAvgPrice(BigDecimal ordersAvgPrice) {
		this.ordersAvgPrice = ordersAvgPrice;
	}

}
