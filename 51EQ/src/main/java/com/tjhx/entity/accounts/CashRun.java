package com.tjhx.entity.accounts;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 销售流水
 */
@Entity
@Table(name = "T_CASH_RUN")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CashRun extends IdEntity {

	private static final long serialVersionUID = -2492530785174886747L;

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
	/** 上班类型(1早班、2晚班、4全天班) */
	private Integer jobType;
	/** 班前余额 */
	private BigDecimal initAmt = new BigDecimal("0");
	/** 当前销售-合计（销售现金+刷卡金额 ） */
	private BigDecimal saleAmt = new BigDecimal("0");
	/** 销售现金-交班时 */
	private BigDecimal saleCashAmt = new BigDecimal("0");
	/** 账面应有现金 */
	private BigDecimal carryingCashAmt = new BigDecimal("0");
	/** 实际销售现金-交班时 */
	private BigDecimal cashAmt = new BigDecimal("0");
	/** 刷卡金额-单据统计 */
	private BigDecimal cardAmt = new BigDecimal("0");
	/** 刷卡金额-电脑统计 */
	private BigDecimal cardAmtBw = new BigDecimal("0");
	/** 刷卡-凭证号 */
	private String cardCertNo;
	/** 刷卡笔数 */
	private Integer cardNum = 0;
	/** 存款金额 */
	private BigDecimal depositAmt = new BigDecimal("0");
	/** 存款人 */
	private String depositor;
	/** 卡号（选择） */
	private String bankCardNo;
	/** 留存金额-交班时 */
	private BigDecimal retainedAmt = new BigDecimal("0");
	/** 备注 */
	private String descTxt;
	/** 日结标记 */
	private Boolean dailyFlg = false;
	/** 现金盈亏（调节） */
	private BigDecimal adjustAmt = new BigDecimal("0");
	/** 汇报金额 */
	private BigDecimal reportAmt = new BigDecimal("0");
	/** 回单对账标记 1-已对账 0-未对账 */
	private Integer bankCheckFlg = 0;
	/** 代金卷编号 */
	private String[] couponNo = new String[5];
	/** 代金卷面值 */
	private BigDecimal[] couponValue = new BigDecimal[5];
	/** 代金卷面值 */
	private BigDecimal totalCouponValue = new BigDecimal("0");
	/** 代金卷实际值 */
	private BigDecimal totalCouponCashValue = new BigDecimal("0");

	/** 金卡销售金额 */
	private BigDecimal goldCardAmt = new BigDecimal("0");
	/** 返利金额 */
	private BigDecimal rebateAmt = new BigDecimal("0");
	/** 预付款(收现) */
	private BigDecimal prePayCashAmt = new BigDecimal("0");
	/** 预付款(刷卡) */
	private BigDecimal prePayCardAmt = new BigDecimal("0");
	// ############################################################################################
	/** 机构名称 */
	private String orgName;
	/** 记录人 */
	private String userName;
	/** 日期-开始时间 */
	private String optDateStart;
	/** 日期-结束时间 */
	private String optDateEnd;

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
	 * 取得上班类型(1早班、2晚班、4全天班)
	 * 
	 * @return jobType 上班类型(1早班、2晚班、4全天班)
	 */
	@NaturalId
	public Integer getJobType() {
		return jobType;
	}

	/**
	 * 设置上班类型(1早班、2晚班、4全天班)
	 * 
	 * @param jobType 上班类型(1早班、2晚班、4全天班)
	 */
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	/**
	 * 取得班前余额
	 * 
	 * @return initAmt 班前余额
	 */
	public BigDecimal getInitAmt() {
		return initAmt;
	}

	/**
	 * 设置班前余额
	 * 
	 * @param initAmt 班前余额
	 */
	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	/**
	 * 取得当前销售-合计（销售现金+刷卡金额 ）
	 * 
	 * @return saleAmt 当前销售-合计（销售现金+刷卡金额 ）
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置当前销售
	 * 
	 * @param saleAmt 当前销售
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得实际销售现金-交班时
	 * 
	 * @return cashAmt 实际销售现金-交班时
	 */
	public BigDecimal getCashAmt() {
		return cashAmt;
	}

	/**
	 * 设置实际销售现金-交班时
	 * 
	 * @param cashAmt 实际销售现金-交班时
	 */
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}

	/**
	 * 取得刷卡金额-单据统计
	 * 
	 * @return cardAmt 刷卡金额-单据统计
	 */
	public BigDecimal getCardAmt() {
		return cardAmt;
	}

	/**
	 * 设置刷卡金额-单据统计
	 * 
	 * @param cardAmt 刷卡金额-单据统计
	 */
	public void setCardAmt(BigDecimal cardAmt) {
		this.cardAmt = cardAmt;
	}

	/**
	 * 取得刷卡金额-电脑统计
	 * 
	 * @return cardAmtBw 刷卡金额-电脑统计
	 */
	public BigDecimal getCardAmtBw() {
		return cardAmtBw;
	}

	/**
	 * 设置刷卡金额-电脑统计
	 * 
	 * @param cardAmtBw 刷卡金额-电脑统计
	 */
	public void setCardAmtBw(BigDecimal cardAmtBw) {
		this.cardAmtBw = cardAmtBw;
	}

	/**
	 * 取得刷卡笔数
	 * 
	 * @return cardNum 刷卡笔数
	 */
	public Integer getCardNum() {
		return cardNum;
	}

	/**
	 * 设置刷卡笔数
	 * 
	 * @param cardNum 刷卡笔数
	 */
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * 取得存款金额
	 * 
	 * @return depositAmt 存款金额
	 */
	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	/**
	 * 设置存款金额
	 * 
	 * @param depositAmt 存款金额
	 */
	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	/**
	 * 取得存款人
	 * 
	 * @return depositor 存款人
	 */
	@Column(length = 16)
	public String getDepositor() {
		return depositor;
	}

	/**
	 * 设置存款人
	 * 
	 * @param depositor 存款人
	 */
	public void setDepositor(String depositor) {
		this.depositor = depositor;
	}

	/**
	 * 取得卡号（选择）
	 * 
	 * @return bankCardNo 卡号（选择）
	 */
	@Column(length = 32)
	public String getBankCardNo() {
		return bankCardNo;
	}

	/**
	 * 设置卡号（选择）
	 * 
	 * @param bankCardNo 卡号（选择）
	 */
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	/**
	 * 取得留存金额-交班时
	 * 
	 * @return retainedAmt 留存金额-交班时
	 */
	public BigDecimal getRetainedAmt() {
		return retainedAmt;
	}

	/**
	 * 设置留存金额-交班时
	 * 
	 * @param retainedAmt 留存金额-交班时
	 */
	public void setRetainedAmt(BigDecimal retainedAmt) {
		this.retainedAmt = retainedAmt;
	}

	/**
	 * 取得备注
	 * 
	 * @return descTxt 备注
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置备注
	 * 
	 * @param descTxt 备注
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得日结标记
	 * 
	 * @return dailyFlg 日结标记
	 */
	public Boolean getDailyFlg() {
		return dailyFlg;
	}

	/**
	 * 设置日结标记
	 * 
	 * @param dailyFlg 日结标记
	 */
	public void setDailyFlg(Boolean dailyFlg) {
		this.dailyFlg = dailyFlg;
	}

	/**
	 * 取得刷卡-凭证号
	 * 
	 * @return cardCertNo 刷卡-凭证号
	 */
	@Column(length = 32)
	public String getCardCertNo() {
		return cardCertNo;
	}

	/**
	 * 设置刷卡-凭证号
	 * 
	 * @param cardCertNo 刷卡-凭证号
	 */
	public void setCardCertNo(String cardCertNo) {
		this.cardCertNo = cardCertNo;
	}

	/**
	 * 取得回单对账标记1-已对账0-未对账
	 * 
	 * @return bankCheckFlg 回单对账标记1-已对账0-未对账
	 */
	public Integer getBankCheckFlg() {
		return bankCheckFlg;
	}

	/**
	 * 设置回单对账标记1-已对账0-未对账
	 * 
	 * @param bankCheckFlg 回单对账标记1-已对账0-未对账
	 */
	public void setBankCheckFlg(Integer bankCheckFlg) {
		this.bankCheckFlg = bankCheckFlg;
	}

	/**
	 * 获取代金卷编号
	 * 
	 * @return couponNo 代金卷编号
	 */
	@Transient
	public String[] getCouponNo() {
		return couponNo;
	}

	/**
	 * 设置代金卷编号
	 * 
	 * @param couponNo 代金卷编号
	 */
	public void setCouponNo(String[] couponNo) {
		this.couponNo = couponNo;
	}

	/**
	 * 获取代金卷面值
	 * 
	 * @return couponValue 代金卷面值
	 */
	@Transient
	public BigDecimal[] getCouponValue() {
		return couponValue;
	}

	/**
	 * 设置代金卷面值
	 * 
	 * @param couponValue 代金卷面值
	 */
	public void setCouponValue(BigDecimal[] couponValue) {
		this.couponValue = couponValue;
	}

	/**
	 * 获取代金卷面值
	 * 
	 * @return totalCouponValue 代金卷面值
	 */
	@Column(name = "COUPON_VALUE")
	public BigDecimal getTotalCouponValue() {
		return totalCouponValue;
	}

	/**
	 * 设置代金卷面值
	 * 
	 * @param totalCouponValue 代金卷面值
	 */
	public void setTotalCouponValue(BigDecimal totalCouponValue) {
		this.totalCouponValue = totalCouponValue;
	}

	/**
	 * 获取代金卷实际值
	 * 
	 * @return totalCouponCashValue 代金卷实际值
	 */
	@Column(name = "COUPON_CASH_VALUE")
	public BigDecimal getTotalCouponCashValue() {
		return totalCouponCashValue;
	}

	/**
	 * 设置代金卷实际值
	 * 
	 * @param totalCouponCashValue 代金卷实际值
	 */
	public void setTotalCouponCashValue(BigDecimal totalCouponCashValue) {
		this.totalCouponCashValue = totalCouponCashValue;
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
	 * 取得销售现金-交班时
	 * 
	 * @return saleCashAmt 销售现金-交班时
	 */
	public BigDecimal getSaleCashAmt() {
		return saleCashAmt;
	}

	/**
	 * 设置销售现金-交班时
	 * 
	 * @param saleCashAmt 销售现金-交班时
	 */
	public void setSaleCashAmt(BigDecimal saleCashAmt) {
		this.saleCashAmt = saleCashAmt;
	}

	/**
	 * 取得账面应有现金
	 * 
	 * @return carryingCashAmt 账面应有现金
	 */
	public BigDecimal getCarryingCashAmt() {
		return carryingCashAmt;
	}

	/**
	 * 设置账面应有现金
	 * 
	 * @param carryingCashAmt 账面应有现金
	 */
	public void setCarryingCashAmt(BigDecimal carryingCashAmt) {
		this.carryingCashAmt = carryingCashAmt;
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
	 * 取得记录人
	 * 
	 * @return userName 记录人
	 */
	@Transient
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置记录人
	 * 
	 * @param userName 记录人
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * 获取预付款(收现)
	 * 
	 * @return prePayCashAmt 预付款(收现)
	 */
	public BigDecimal getPrePayCashAmt() {
		return prePayCashAmt;
	}

	/**
	 * 设置预付款(收现)
	 * 
	 * @param prePayCashAmt 预付款(收现)
	 */
	public void setPrePayCashAmt(BigDecimal prePayCashAmt) {
		this.prePayCashAmt = prePayCashAmt;
	}

	/**
	 * 获取预付款(刷卡)
	 * 
	 * @return prePayCardAmt 预付款(刷卡)
	 */
	public BigDecimal getPrePayCardAmt() {
		return prePayCardAmt;
	}

	/**
	 * 设置预付款(刷卡)
	 * 
	 * @param prePayCardAmt 预付款(刷卡)
	 */
	public void setPrePayCardAmt(BigDecimal prePayCardAmt) {
		this.prePayCardAmt = prePayCardAmt;
	}

	/**
	 * 获取金卡销售金额
	 * 
	 * @return goldCardAmt 金卡销售金额
	 */
	public BigDecimal getGoldCardAmt() {
		return goldCardAmt;
	}

	/**
	 * 设置金卡销售金额
	 * 
	 * @param goldCardAmt 金卡销售金额
	 */
	public void setGoldCardAmt(BigDecimal goldCardAmt) {
		this.goldCardAmt = goldCardAmt;
	}

	/**
	 * 获取返利金额
	 * 
	 * @return rebateAmt 返利金额
	 */
	public BigDecimal getRebateAmt() {
		return rebateAmt;
	}

	/**
	 * 设置返利金额
	 * 
	 * @param rebateAmt 返利金额
	 */
	public void setRebateAmt(BigDecimal rebateAmt) {
		this.rebateAmt = rebateAmt;
	}
}
