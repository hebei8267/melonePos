package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 资金记账流水
 */
@Entity
@Table(name = "T_ACCOUNT_FLOW")
public class AccountFlow extends IdEntity {

	private static final long serialVersionUID = 4934637793138775985L;
	/** 日期 */
	private String optDate;
	/** 业务编号 */
	private String bizCode;
	/** 余额 */
	private BigDecimal balanceAmt = new BigDecimal("0");
	/** 拨入来源 */
	private String inAmtDesc;
	/** 拨入金额 */
	private BigDecimal inAmt = new BigDecimal("0");
	/** 支出金额 */
	private BigDecimal outAmt = new BigDecimal("0");
	/** 支出大类 */
	private String outAmtLClass;
	/** 支出细类 */
	private String outAmtSClass;
	/** 备注 */
	private String descTxt;
	/** 锁定标记 */
	private Boolean lockFlg = false;

	/**
	 * 取得日期
	 * 
	 * @return optDate 日期
	 */
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
	 * 取得bizCode
	 * 
	 * @return bizCode
	 */
	@Column(length = 64)
	public String getBizCode() {
		return bizCode;
	}

	/**
	 * 设置bizCode
	 * 
	 * @param bizCode bizCode
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	/**
	 * 取得余额
	 * 
	 * @return balanceAmt 余额
	 */
	public BigDecimal getBalanceAmt() {
		return balanceAmt;
	}

	/**
	 * 设置余额
	 * 
	 * @param balanceAmt 余额
	 */
	public void setBalanceAmt(BigDecimal balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	/**
	 * 取得拨入来源
	 * 
	 * @return inAmtDesc 拨入来源
	 */
	public String getInAmtDesc() {
		return inAmtDesc;
	}

	/**
	 * 设置拨入来源
	 * 
	 * @param inAmtDesc 拨入来源
	 */
	public void setInAmtDesc(String inAmtDesc) {
		this.inAmtDesc = inAmtDesc;
	}

	/**
	 * 取得拨入金额
	 * 
	 * @return inAmt 拨入金额
	 */
	public BigDecimal getInAmt() {
		return inAmt;
	}

	/**
	 * 设置拨入金额
	 * 
	 * @param inAmt 拨入金额
	 */
	public void setInAmt(BigDecimal inAmt) {
		this.inAmt = inAmt;
	}

	/**
	 * 取得支出金额
	 * 
	 * @return outAmt 支出金额
	 */
	public BigDecimal getOutAmt() {
		return outAmt;
	}

	/**
	 * 设置支出金额
	 * 
	 * @param outAmt 支出金额
	 */
	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	/**
	 * 取得支出大类
	 * 
	 * @return outAmtLClass 支出大类
	 */
	public String getOutAmtLClass() {
		return outAmtLClass;
	}

	/**
	 * 设置支出大类
	 * 
	 * @param outAmtLClass 支出大类
	 */
	public void setOutAmtLClass(String outAmtLClass) {
		this.outAmtLClass = outAmtLClass;
	}

	/**
	 * 取得支出细类
	 * 
	 * @return outAmtSClass 支出细类
	 */
	public String getOutAmtSClass() {
		return outAmtSClass;
	}

	/**
	 * 设置支出细类
	 * 
	 * @param outAmtSClass 支出细类
	 */
	public void setOutAmtSClass(String outAmtSClass) {
		this.outAmtSClass = outAmtSClass;
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
	@Column(length = 1024)
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得锁定标记
	 * 
	 * @return 锁定标记
	 */
	public Boolean getLockFlg() {
		return lockFlg;
	}

	/**
	 * 设置锁定标记
	 * 
	 * @param lockFlg 锁定标记
	 */
	public void setLockFlg(Boolean lockFlg) {
		this.lockFlg = lockFlg;
	}

}
