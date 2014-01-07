package com.tjhx.entity.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 银行信息
 */
@Entity
@Table(name = "T_BANK")
public class Bank extends IdEntity {

	private static final long serialVersionUID = -342952727806019019L;
	/** 银行编号 */
	private String bankId;
	/** 银行名称 */
	private String name;

	/**
	 * 取得银行编号
	 * 
	 * @return bankId 银行编号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
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
	 * 取得银行名称
	 * 
	 * @return name 银行名称
	 */
	@Column(length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置银行名称
	 * 
	 * @param name 银行名称
	 */
	public void setName(String name) {
		this.name = name;
	}

}
