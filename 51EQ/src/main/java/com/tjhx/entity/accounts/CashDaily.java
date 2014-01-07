package com.tjhx.entity.accounts;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 销售流水日结
 */
@Entity
@Table(name = "T_CASH_DAILY")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CashDaily extends IdEntity {

	private static final long serialVersionUID = 6877509722983193093L;
	/** 机构编号 */
	private String orgId;
	/** 日期 */
	private String optDate;
	/** 日期-显示 */
	private String optDateShow;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 昨日余额 */
	private BigDecimal initAmt = new BigDecimal("0");
	/** 当日销售（合计） */
	private BigDecimal saleAmt = new BigDecimal("0");
	/** 刷卡金额-单据统计-当日（合计） */
	private BigDecimal cardAmt = new BigDecimal("0");
	/** 刷卡金额-电脑统计-当日（合计） */
	private BigDecimal cardAmtBw = new BigDecimal("0");
	/** 销售现金-当日（合计） */
	private BigDecimal saleCashAmt = new BigDecimal("0");
	/** 刷卡笔数-当日（合计） */
	private Integer cardNum = 0;
	/** 存款金额-当日（合计） */
	private BigDecimal depositAmt = new BigDecimal("0");
	/** 留存金额-当日 */
	private BigDecimal retainedAmt = new BigDecimal("0");
	/** 现金盈亏（调节） */
	private BigDecimal adjustAmt = new BigDecimal("0");
	/** 百威系统销售额 */
	private BigDecimal bwSaleAmt = new BigDecimal("0");
	/** 百威系统机构编号 */
	private String orgBranchNo;
	/** 汇报金额 */
	private BigDecimal reportAmt = new BigDecimal("0");
	// ############################################################################################
	/** 机构名称 */
	private String orgName;
	/** 日期-开始时间 */
	private String optDateStart;
	/** 日期-结束时间 */
	private String optDateEnd;
	/** 行号 */
	private Integer index;

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@NaturalId
	@Column(length = 32)
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
	 * 取得日期
	 * 
	 * @return optDate 日期
	 */
	@NaturalId
	@Column(length = 8)
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
	 * 取得日期-显示
	 * 
	 * @return optDateShow 日期-显示
	 */
	@Column(length = 10)
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置日期-显示
	 * 
	 * @param optDateShow 日期-显示
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
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
	 * 取得昨日余额
	 * 
	 * @return initAmt 昨日余额
	 */
	public BigDecimal getInitAmt() {
		return initAmt;
	}

	/**
	 * 设置昨日余额
	 * 
	 * @param initAmt 昨日余额
	 */
	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	/**
	 * 取得当日销售（合计）
	 * 
	 * @return saleAmt 当日销售（合计）
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置当日销售（合计）
	 * 
	 * @param saleAmt 当日销售（合计）
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得刷卡金额-单据统计-当日（合计）
	 * 
	 * @return cardAmt 刷卡金额-单据统计-当日（合计）
	 */
	public BigDecimal getCardAmt() {
		return cardAmt;
	}

	/**
	 * 设置刷卡金额-单据统计-当日（合计）
	 * 
	 * @param cardAmt 刷卡金额-单据统计-当日（合计）
	 */
	public void setCardAmt(BigDecimal cardAmt) {
		this.cardAmt = cardAmt;
	}

	/**
	 * 取得刷卡金额-电脑统计-当日（合计）
	 * 
	 * @return cardAmtBw 刷卡金额-电脑统计-当日（合计）
	 */
	public BigDecimal getCardAmtBw() {
		return cardAmtBw;
	}

	/**
	 * 设置刷卡金额-电脑统计-当日（合计）
	 * 
	 * @param cardAmtBw 刷卡金额-电脑统计-当日（合计）
	 */
	public void setCardAmtBw(BigDecimal cardAmtBw) {
		this.cardAmtBw = cardAmtBw;
	}

	/**
	 * 取得刷卡笔数-当日（合计）
	 * 
	 * @return cardNum 刷卡笔数-当日（合计）
	 */
	public Integer getCardNum() {
		return cardNum;
	}

	/**
	 * 设置刷卡笔数-当日（合计）
	 * 
	 * @param cardNum 刷卡笔数-当日（合计）
	 */
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * 取得存款金额-当日（合计）
	 * 
	 * @return depositAmt 存款金额-当日（合计）
	 */
	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	/**
	 * 设置存款金额-当日（合计）
	 * 
	 * @param depositAmt 存款金额-当日（合计）
	 */
	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	/**
	 * 取得留存金额-当日
	 * 
	 * @return retainedAmt 留存金额-当日
	 */
	public BigDecimal getRetainedAmt() {
		return retainedAmt;
	}

	/**
	 * 设置留存金额-当日
	 * 
	 * @param retainedAmt 留存金额-当日
	 */
	public void setRetainedAmt(BigDecimal retainedAmt) {
		this.retainedAmt = retainedAmt;
	}

	/**
	 * 取得汇报金额
	 * 
	 * @return reportAmt 汇报金额
	 */
	public BigDecimal getReportAmt() {
		return reportAmt;
	}

	/**
	 * 设置汇报金额
	 * 
	 * @param reportAmt 汇报金额
	 */
	public void setReportAmt(BigDecimal reportAmt) {
		this.reportAmt = reportAmt;
	}

	/**
	 * 取得机构名称
	 * 
	 * @return orgName 机构名称
	 */
	@Transient
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
	 * 取得现金盈亏（调节）
	 * 
	 * @return adjustAmt 现金盈亏（调节）
	 */
	public BigDecimal getAdjustAmt() {
		return adjustAmt;
	}

	/**
	 * 设置现金盈亏（调节）
	 * 
	 * @param adjustAmt 现金盈亏（调节）
	 */
	public void setAdjustAmt(BigDecimal adjustAmt) {
		this.adjustAmt = adjustAmt;
	}

	/**
	 * 取得销售现金-当日（合计）
	 * 
	 * @return saleCashAmt 销售现金-当日（合计）
	 */
	public BigDecimal getSaleCashAmt() {
		return saleCashAmt;
	}

	/**
	 * 设置销售现金-当日（合计）
	 * 
	 * @param saleCashAmt 销售现金-当日（合计）
	 */
	public void setSaleCashAmt(BigDecimal saleCashAmt) {
		this.saleCashAmt = saleCashAmt;
	}

	/**
	 * 取得日期-开始时间
	 * 
	 * @return optDateStart 日期-开始时间
	 */
	@Transient
	public String getOptDateStart() {
		return optDateStart;
	}

	/**
	 * 设置日期-开始时间
	 * 
	 * @param optDateStart 日期-开始时间
	 */
	public void setOptDateStart(String optDateStart) {
		this.optDateStart = optDateStart;
	}

	/**
	 * 取得日期-结束时间
	 * 
	 * @return optDateEnd 日期-结束时间
	 */
	@Transient
	public String getOptDateEnd() {
		return optDateEnd;
	}

	/**
	 * 设置日期-结束时间
	 * 
	 * @param optDateEnd 日期-结束时间
	 */
	public void setOptDateEnd(String optDateEnd) {
		this.optDateEnd = optDateEnd;
	}

	/**
	 * 取得百威系统销售额
	 * 
	 * @return bwSaleAmt 百威系统销售额
	 */
	public BigDecimal getBwSaleAmt() {
		return bwSaleAmt;
	}

	/**
	 * 设置百威系统销售额
	 * 
	 * @param bwSaleAmt 百威系统销售额
	 */
	public void setBwSaleAmt(BigDecimal bwSaleAmt) {
		this.bwSaleAmt = bwSaleAmt;
	}

	/**
	 * 取得百威系统机构编号
	 * 
	 * @return orgBranchNo 百威系统机构编号
	 */
	@Column(length = 6)
	public String getOrgBranchNo() {
		return orgBranchNo;
	}

	/**
	 * 设置百威系统机构编号
	 * 
	 * @param orgBranchNo 百威系统机构编号
	 */
	public void setOrgBranchNo(String orgBranchNo) {
		this.orgBranchNo = orgBranchNo;
	}

	/**
	 * 取得行号
	 * 
	 * @return index 行号
	 */
	@Transient
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置行号
	 * 
	 * @param index 行号
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

}
