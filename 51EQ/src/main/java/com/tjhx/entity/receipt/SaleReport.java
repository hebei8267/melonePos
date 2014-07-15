package com.tjhx.entity.receipt;

import java.math.BigDecimal;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashRun;

public class SaleReport {
	/** 行号 */
	private Integer index;
	/** 机构名称 */
	private String orgName;
	/** 日期-显示 */
	private String optDateShow;
	/** 上班类型(1早班、2晚班、4全天班) */
	private String jobType;
	/** 刷卡金额-单据统计 */
	private BigDecimal cardAmt = new BigDecimal("0");
	/** 刷卡金额-电脑统计 */
	private BigDecimal cardAmtBw = new BigDecimal("0");
	/** 刷卡-凭证号 */
	private String cardCertNo;
	/** 刷卡笔数 */
	private Integer cardNum = 0;
	/** 记录人 */
	private String userName;
	/** 备注 */
	private String descTxt;
	/** 星期数 */
	private String weekOfDate;

	/** 班前余额 */
	private BigDecimal initAmt = new BigDecimal("0");
	/** 销售现金-交班时 */
	private BigDecimal saleCashAmt = new BigDecimal("0");
	/** 账面应有现金 */
	private BigDecimal carryingCashAmt = new BigDecimal("0");
	/** 实点现金-交班时 */
	private BigDecimal cashAmt = new BigDecimal("0");
	/** 现金盈亏（调节） */
	private BigDecimal adjustAmt = new BigDecimal("0");
	/** 存款人 */
	private String depositor;
	/** 存款金额 */
	private BigDecimal depositAmt = new BigDecimal("0");
	/** 留存金额-交班时 */
	private BigDecimal retainedAmt = new BigDecimal("0");
	/** 当班销售-合计（销售现金+刷卡金额 ） */
	private BigDecimal saleAmt = new BigDecimal("0");
	/** 代金卷面值 */
	private BigDecimal couponValue = new BigDecimal("0");
	/** 代金卷实际值 */
	private BigDecimal couponCashValue = new BigDecimal("0");
	/** 预付款(收现) */
	private BigDecimal prePayCashAmt = new BigDecimal("0");
	/** 预付款(刷卡) */
	private BigDecimal prePayCardAmt = new BigDecimal("0");

	/**
	 * 取得行号
	 * 
	 * @return index 行号
	 */
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

	/**
	 * 取得机构名称
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
	 * 取得日期-显示
	 * 
	 * @return optDateShow 日期-显示
	 */
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
	 * 取得上班类型(1早班、2晚班、4全天班)
	 * 
	 * @return jobType 上班类型(1早班、2晚班、4全天班)
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * 设置上班类型(1早班、2晚班、4全天班)
	 * 
	 * @param jobType 上班类型(1早班、2晚班、4全天班)
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
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
	 * 取得刷卡-凭证号
	 * 
	 * @return cardCertNo 刷卡-凭证号
	 */
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
	 * 取得记录人
	 * 
	 * @return userName 记录人
	 */
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
	 * 取得星期数
	 * 
	 * @return weekOfDate 星期数
	 */
	public String getWeekOfDate() {
		return weekOfDate;
	}

	/**
	 * 设置星期数
	 * 
	 * @param weekOfDate 星期数
	 */
	public void setWeekOfDate(String weekOfDate) {
		this.weekOfDate = weekOfDate;
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
	 * 取得实点现金-交班时
	 * 
	 * @return cashAmt 实点现金-交班时
	 */
	public BigDecimal getCashAmt() {
		return cashAmt;
	}

	/**
	 * 设置实点现金-交班时
	 * 
	 * @param cashAmt 实点现金-交班时
	 */
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
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
	 * 取得存款人
	 * 
	 * @return depositor 存款人
	 */
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
	 * 取得当班销售-合计（销售现金+刷卡金额）
	 * 
	 * @return saleAmt 当班销售-合计（销售现金+刷卡金额）
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置当班销售-合计（销售现金+刷卡金额）
	 * 
	 * @param saleAmt 当班销售-合计（销售现金+刷卡金额）
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 获取代金卷面值
	 * 
	 * @return couponValue 代金卷面值
	 */
	public BigDecimal getCouponValue() {
		return couponValue;
	}

	/**
	 * 设置代金卷面值
	 * 
	 * @param couponValue 代金卷面值
	 */
	public void setCouponValue(BigDecimal couponValue) {
		this.couponValue = couponValue;
	}

	/**
	 * 获取代金卷实际值
	 * 
	 * @return couponCashValue 代金卷实际值
	 */
	public BigDecimal getCouponCashValue() {
		return couponCashValue;
	}

	/**
	 * 设置代金卷实际值
	 * 
	 * @param couponCashValue 代金卷实际值
	 */
	public void setCouponCashValue(BigDecimal couponCashValue) {
		this.couponCashValue = couponCashValue;
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

	public void addCashRunInfo_Cash(CashRun _cashRun) throws ParseException {
		// 机构名称
		this.orgName = _cashRun.getOrgName();
		// 日期-显示
		this.optDateShow = _cashRun.getOptDateShow();
		// 星期数
		this.weekOfDate = DateUtils.getWeekOfDate(optDateShow, "yyyy-MM-dd");
		// 上班类型(1早班、2晚班、4全天班)
		if (null != _cashRun.getJobType()) {
			if (1 == _cashRun.getJobType()) {
				this.jobType = "早班";
			}
			if (2 == _cashRun.getJobType()) {
				this.jobType = "晚班";
			}
			if (4 == _cashRun.getJobType()) {
				this.jobType = "全天班";
			}
		}
		// 交班签名
		this.userName = _cashRun.getUserName();
		// 头天现金
		this.initAmt = _cashRun.getInitAmt();
		// 销售收现
		this.saleCashAmt = _cashRun.getSaleCashAmt();
		// 账面现金 (应有金额)
		this.carryingCashAmt = _cashRun.getCarryingCashAmt();
		// 实点现金
		this.cashAmt = _cashRun.getCashAmt();
		// 现金盈亏
		this.adjustAmt = _cashRun.getAdjustAmt();
		// 刷卡金额
		this.cardAmt = _cashRun.getCardAmt();
		// 存款人
		this.depositor = _cashRun.getDepositor();
		// 存款金额
		this.depositAmt = _cashRun.getDepositAmt();
		// 留存现金
		this.retainedAmt = _cashRun.getRetainedAmt();
		// 当班销售-合计
		this.saleAmt = _cashRun.getSaleAmt();
		// 备注
		this.descTxt = _cashRun.getDescTxt();

		// 代金卷面值
		this.couponValue = _cashRun.getTotalCouponValue();
		// 代金卷实际值
		this.couponCashValue = _cashRun.getTotalCouponCashValue();

		// 预付款(收现)
		this.prePayCashAmt = _cashRun.getPrePayCashAmt();
		// 预付款(刷卡)
		this.prePayCardAmt = _cashRun.getPrePayCardAmt();
	}

	public void addCashRunInfo_Card(CashRun _cashRun) throws ParseException {
		// 机构名称
		this.orgName = _cashRun.getOrgName();
		// 日期-显示
		this.optDateShow = _cashRun.getOptDateShow();
		// 星期数
		this.weekOfDate = DateUtils.getWeekOfDate(optDateShow, "yyyy-MM-dd");
		// 刷卡金额-单据统计
		this.cardAmt = this.cardAmt.add(_cashRun.getCardAmt());
		// 刷卡金额-电脑统计
		this.cardAmtBw = this.cardAmtBw.add(_cashRun.getCardAmtBw());
		// 刷卡-凭证号
		if (StringUtils.isNotBlank(this.cardCertNo)) {
			this.cardCertNo += " / " + _cashRun.getCardCertNo();
		} else {
			this.cardCertNo = _cashRun.getCardCertNo();
		}

		// 刷卡笔数
		this.cardNum = this.cardNum + _cashRun.getCardNum();

		// 记录人
		if (StringUtils.isNotBlank(this.userName)) {
			this.userName += " / " + _cashRun.getUserName();
		} else {
			this.userName = _cashRun.getUserName();
		}

		// 备注
		if (StringUtils.isNotBlank(this.descTxt)) {
			this.descTxt += " / " + _cashRun.getDescTxt();
		} else {
			this.descTxt = _cashRun.getDescTxt();
		}
	}
}
