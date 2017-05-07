package com.tjhx.entity.info;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 银行卡信息
 */
@Entity
@Table(name = "T_BANK_CARD")
public class BankCard extends IdEntity {

	private static final long serialVersionUID = 1270130153158385933L;

	/** 机构编号 */
	private String orgId;
	/** 银行卡号码 */
	private String bankCardNo;
	/** 银行 */
	private Bank bank;
	/** 银行编号 */
	private String bankId;
	/** 开户人名称 */
	private String accountName;

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
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
	 * 取得银行卡号码
	 * 
	 * @return bankCardNo 银行卡号码
	 */
	// @NaturalId
	@Column(nullable = false, length = 32)
	public String getBankCardNo() {
		return bankCardNo;
	}

	/**
	 * 设置银行卡号码
	 * 
	 * @param bankCardNo 银行卡号码
	 */
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	/**
	 * 取得银行
	 * 
	 * @return bank 银行
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "BANK_UUID")
	public Bank getBank() {
		return bank;
	}

	/**
	 * 设置银行
	 * 
	 * @param bank 银行
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
		if (null != bank) {
			this.bankId = bank.getBankId();
		}
	}

	/**
	 * 取得银行编号
	 * 
	 * @return bankId 银行编号
	 */
	@Column(length = 16)
	public String getBankId() {
		return bankId;
	}

	/**
	 * 设置银行编号
	 * 
	 * @param bankId 银行编号
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	/**
	 * 取得accountName
	 * 
	 * @return accountName
	 */
	@Column(length = 32)
	public String getAccountName() {
		return accountName;
	}

	/**
	 * 设置accountName
	 * 
	 * @param accountName accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
