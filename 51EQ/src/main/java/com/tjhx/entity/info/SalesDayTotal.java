package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 每日各店销售汇总
 */
@Entity
@Table(name = "T_SALES_DAY_TOTAL")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesDayTotal extends IdEntity {

	private static final long serialVersionUID = 911410165224218561L;
	/** 日期 */
	private String optDate;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 机构编号 */
	private String orgId;
	/** 机构资金编号 */
	private String branchNo;
	/** 当日销售金额 */
	private BigDecimal posAmt;
	/** 截止现在销售金额 */
	private BigDecimal posAmtByNow;
	/** 截止天数 */
	private int nowDays;
	/** 本月天数 */
	private int monthDays;
	/** 预计本月销售 */
	private BigDecimal expMonthPosAmt;
	/** 去年同期销售 */
	private BigDecimal preYearMonthPosAmt;
	/** 销售增长额 */
	private BigDecimal posAmtIncrease;
	/** 销售增长率 */
	private BigDecimal posAmtRate;
	/** 日需销售金额 */
	private BigDecimal dayNeededPosAmt;
	/** 排名 */
	private int ranking;
	/** 月销售目标额 */
	private BigDecimal saleTargetAmt = new BigDecimal(0);
	// ========================================================
	/** 颜色 */
	private String htmlColor;

	public SalesDayTotal() {

	}

	public SalesDayTotal(String optDate, String orgId) {
		this.optDate = optDate;
		this.orgId = orgId;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDate 日期
	 */
	@NaturalId
	@Column(length = 8, nullable = false)
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
	 * 取得日期-年
	 * 
	 * @return optDateY 日期-年
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
	 * 取得日期-月
	 * 
	 * @return optDateM 日期-月
	 */
	@Column(name = "OPT_DATE_M", length = 2)
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置日期-月
	 * 
	 * @param optDateM 日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
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
	 * 取得机构资金编号
	 * 
	 * @return branchNo 机构资金编号
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
	 * 取得当日销售金额
	 * 
	 * @return posAmt 当日销售金额
	 */
	public BigDecimal getPosAmt() {
		return posAmt;
	}

	/**
	 * 设置当日销售金额
	 * 
	 * @param posAmt 当日销售金额
	 */
	public void setPosAmt(BigDecimal posAmt) {
		this.posAmt = posAmt;
	}

	/**
	 * 取得截止现在销售金额
	 * 
	 * @return posAmtByNow 截止现在销售金额
	 */
	public BigDecimal getPosAmtByNow() {
		return posAmtByNow;
	}

	/**
	 * 设置截止现在销售金额
	 * 
	 * @param posAmtByNow 截止现在销售金额
	 */
	public void setPosAmtByNow(BigDecimal posAmtByNow) {
		this.posAmtByNow = posAmtByNow;
	}

	/**
	 * 取得截止天数
	 * 
	 * @return nowDays 截止天数
	 */
	public int getNowDays() {
		return nowDays;
	}

	/**
	 * 设置截止天数
	 * 
	 * @param nowDays 截止天数
	 */
	public void setNowDays(int nowDays) {
		this.nowDays = nowDays;
	}

	/**
	 * 取得本月天数
	 * 
	 * @return monthDays 本月天数
	 */
	public int getMonthDays() {
		return monthDays;
	}

	/**
	 * 设置本月天数
	 * 
	 * @param monthDays 本月天数
	 */
	public void setMonthDays(int monthDays) {
		this.monthDays = monthDays;
	}

	/**
	 * 取得预计本月销售
	 * 
	 * @return expMonthPosAmt 预计本月销售
	 */
	public BigDecimal getExpMonthPosAmt() {
		return expMonthPosAmt;
	}

	/**
	 * 设置预计本月销售
	 * 
	 * @param expMonthPosAmt 预计本月销售
	 */
	public void setExpMonthPosAmt(BigDecimal expMonthPosAmt) {
		this.expMonthPosAmt = expMonthPosAmt;
	}

	/**
	 * 取得去年同期销售
	 * 
	 * @return preYearMonthPosAmt 去年同期销售
	 */
	public BigDecimal getPreYearMonthPosAmt() {
		return preYearMonthPosAmt;
	}

	/**
	 * 设置去年同期销售
	 * 
	 * @param preYearMonthPosAmt 去年同期销售
	 */
	public void setPreYearMonthPosAmt(BigDecimal preYearMonthPosAmt) {
		this.preYearMonthPosAmt = preYearMonthPosAmt;
	}

	/**
	 * 取得销售增长额
	 * 
	 * @return posAmtIncrease 销售增长额
	 */
	public BigDecimal getPosAmtIncrease() {
		return posAmtIncrease;
	}

	/**
	 * 设置销售增长额
	 * 
	 * @param posAmtIncrease 销售增长额
	 */
	public void setPosAmtIncrease(BigDecimal posAmtIncrease) {
		this.posAmtIncrease = posAmtIncrease;
	}

	/**
	 * 取得销售增长率
	 * 
	 * @return posAmtRate 销售增长率
	 */
	public BigDecimal getPosAmtRate() {
		return posAmtRate;
	}

	/**
	 * 设置销售增长率
	 * 
	 * @param posAmtRate 销售增长率
	 */
	public void setPosAmtRate(BigDecimal posAmtRate) {
		this.posAmtRate = posAmtRate;
	}

	/**
	 * 取得日需销售金额
	 * 
	 * @return dayNeededPosAmt 日需销售金额
	 */
	public BigDecimal getDayNeededPosAmt() {
		return dayNeededPosAmt;
	}

	/**
	 * 设置日需销售金额
	 * 
	 * @param dayNeededPosAmt 日需销售金额
	 */
	public void setDayNeededPosAmt(BigDecimal dayNeededPosAmt) {
		this.dayNeededPosAmt = dayNeededPosAmt;
	}

	/**
	 * 取得排名
	 * 
	 * @return ranking 排名
	 */
	public int getRanking() {
		return ranking;
	}

	/**
	 * 设置排名
	 * 
	 * @param ranking 排名
	 */
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	/**
	 * 获取月销售目标额
	 * 
	 * @return saleTargetAmt 月销售目标额
	 */
	public BigDecimal getSaleTargetAmt() {
		return saleTargetAmt;
	}

	/**
	 * 设置月销售目标额
	 * 
	 * @param saleTargetAmt 月销售目标额
	 */
	public void setSaleTargetAmt(BigDecimal saleTargetAmt) {
		this.saleTargetAmt = saleTargetAmt;
	}

	/**
	 * 取得颜色
	 * 
	 * @return htmlColor 颜色
	 */
	@Transient
	public String getHtmlColor() {
		return htmlColor;
	}

	/**
	 * 设置颜色
	 * 
	 * @param htmlColor 颜色
	 */
	public void setHtmlColor(String htmlColor) {
		this.htmlColor = htmlColor;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SalesDayTotal)) {
			return false;
		}
		SalesDayTotal rhs = (SalesDayTotal) obj;
		return new EqualsBuilder().append(this.getOptDate(), rhs.getOptDate()).append(this.getOrgId(), rhs.getOrgId()).isEquals();
	}

}
