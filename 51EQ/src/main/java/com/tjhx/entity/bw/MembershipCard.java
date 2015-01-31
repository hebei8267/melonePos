/**
 * 
 */
package com.tjhx.entity.bw;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * 金卡信息
 */
public class MembershipCard {
	/** 金卡发行次数 */
	private int _issueCnt = 0;
	/** 金卡发行次数-1 */
	private int issueCnt1 = 0;
	/** 金卡发行次数-2 */
	private int issueCnt2 = 0;

	/** 金卡返利 */
	private BigDecimal _retAmt = new BigDecimal("0");
	/** 金卡返利-1 */
	private BigDecimal retAmt1 = new BigDecimal("0");
	/** 金卡返利-2 */
	private BigDecimal retAmt2 = new BigDecimal("0");

	/** 金卡充值 */
	private BigDecimal _rechargeAmt = new BigDecimal("0");
	/** 金卡充值-1 */
	private BigDecimal rechargeAmt1 = new BigDecimal("0");
	/** 金卡充值-2 */
	private BigDecimal rechargeAmt2 = new BigDecimal("0");

	/** 金卡消费次数 */
	private int _consumeCnt = 0;
	/** 金卡消费次数-1 */
	private int consumeCnt1 = 0;
	/** 金卡消费次数-2 */
	private int consumeCnt2 = 0;

	/** 消费次数 */
	private int _totalConsumeCnt = 0;
	/** 消费次数-1 */
	private int totalConsumeCnt1 = 0;
	/** 消费次数-2 */
	private int totalConsumeCnt2 = 0;

	/** 金卡消费金额 */
	private BigDecimal _saleAmt = new BigDecimal("0");
	/** 金卡消费金额 */
	private BigDecimal saleAmt1 = new BigDecimal("0");
	/** 金卡消费金额 */
	private BigDecimal saleAmt2 = new BigDecimal("0");

	/** 消费金额 */
	private BigDecimal _totalSaleAmt = new BigDecimal("0");
	/** 消费金额-1 */
	private BigDecimal totalSaleAmt1 = new BigDecimal("0");
	/** 消费金额-2 */
	private BigDecimal totalSaleAmt2 = new BigDecimal("0");

	/** 机构编号 */
	private String orgId;
	/** 机构资金编号-百威 */
	private String bwBranchNo;
	/** 机构名称 */
	private String orgName;

	/**
	 * 获取金卡发行次数
	 * 
	 * @return _issueCnt
	 */
	public int get_issueCnt() {
		return _issueCnt;
	}

	/**
	 * 设置金卡发行次数
	 * 
	 * @param _issueCnt 金卡发行次数
	 */
	public void set_issueCnt(int _issueCnt) {
		this._issueCnt = _issueCnt;
	}

	/**
	 * 获取金卡发行次数-1
	 * 
	 * @return issueCnt1
	 */
	public int getIssueCnt1() {
		return issueCnt1;
	}

	/**
	 * 设置金卡发行次数-1
	 * 
	 * @param issueCnt1 金卡发行次数-1
	 */
	public void setIssueCnt1(int issueCnt1) {
		this.issueCnt1 = issueCnt1;
	}

	/**
	 * 获取金卡发行次数-2
	 * 
	 * @return issueCnt2
	 */
	public int getIssueCnt2() {
		return issueCnt2;
	}

	/**
	 * 设置金卡发行次数-2
	 * 
	 * @param issueCnt2 金卡发行次数-2
	 */
	public void setIssueCnt2(int issueCnt2) {
		this.issueCnt2 = issueCnt2;
	}

	/**
	 * 获取金卡返利
	 * 
	 * @return _retAmt
	 */
	public BigDecimal get_retAmt() {
		return _retAmt;
	}

	/**
	 * 设置金卡返利
	 * 
	 * @param _retAmt 金卡返利
	 */
	public void set_retAmt(BigDecimal _retAmt) {
		this._retAmt = _retAmt;
	}

	/**
	 * 获取金卡返利-1
	 * 
	 * @return retAmt1
	 */
	public String getRetAmt1() {
		if (retAmt1.compareTo(BigDecimal.ZERO) == 1) {
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(retAmt1);
		}
		return retAmt1.toString();
	}

	/**
	 * 设置金卡返利-1
	 * 
	 * @param retAmt1 金卡返利-1
	 */
	public void setRetAmt1(BigDecimal retAmt1) {
		this.retAmt1 = retAmt1;
	}

	/**
	 * 获取金卡返利-2
	 * 
	 * @return retAmt2
	 */
	public String getRetAmt2() {
		if (retAmt2.compareTo(BigDecimal.ZERO) == 1) {
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(retAmt2);
		}
		return retAmt2.toString();
	}

	/**
	 * 设置金卡返利-2
	 * 
	 * @param retAmt2 金卡返利-2
	 */
	public void setRetAmt2(BigDecimal retAmt2) {
		this.retAmt2 = retAmt2;
	}

	/**
	 * 获取金卡充值
	 * 
	 * @return _rechargeAmt
	 */
	public BigDecimal get_rechargeAmt() {
		return _rechargeAmt;
	}

	/**
	 * 设置金卡充值
	 * 
	 * @param _rechargeAmt 金卡充值
	 */
	public void set_rechargeAmt(BigDecimal _rechargeAmt) {
		this._rechargeAmt = _rechargeAmt;
	}

	/**
	 * 获取金卡充值-1
	 * 
	 * @return rechargeAmt1
	 */
	public String getRechargeAmt1() {
		if (rechargeAmt1.compareTo(BigDecimal.ZERO) == 1) {
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(rechargeAmt1);
		}
		return rechargeAmt1.toString();
	}

	/**
	 * 设置金卡充值-1
	 * 
	 * @param rechargeAmt1 金卡充值-1
	 */
	public void setRechargeAmt1(BigDecimal rechargeAmt1) {
		this.rechargeAmt1 = rechargeAmt1;
	}

	/**
	 * 获取金卡充值-2
	 * 
	 * @return rechargeAmt2
	 */
	public String getRechargeAmt2() {
		if (rechargeAmt2.compareTo(BigDecimal.ZERO) == 1) {
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(rechargeAmt2);
		}
		return rechargeAmt2.toString();
	}

	/**
	 * 设置金卡充值-2
	 * 
	 * @param rechargeAmt2 金卡充值-2
	 */
	public void setRechargeAmt2(BigDecimal rechargeAmt2) {
		this.rechargeAmt2 = rechargeAmt2;
	}

	/**
	 * 获取金卡消费次数
	 * 
	 * @return _consumeCnt
	 */
	public int get_consumeCnt() {
		return _consumeCnt;
	}

	/**
	 * 设置金卡消费次数
	 * 
	 * @param _consumeCnt 金卡消费次数
	 */
	public void set_consumeCnt(int _consumeCnt) {
		this._consumeCnt = _consumeCnt;
	}

	/**
	 * 获取金卡消费次数-1
	 * 
	 * @return consumeCnt1
	 */
	public int getConsumeCnt1() {
		return consumeCnt1;
	}

	/**
	 * 设置金卡消费次数-1
	 * 
	 * @param consumeCnt1 金卡消费次数-1
	 */
	public void setConsumeCnt1(int consumeCnt1) {
		this.consumeCnt1 = consumeCnt1;
	}

	/**
	 * 获取金卡消费次数-2
	 * 
	 * @return consumeCnt2
	 */
	public int getConsumeCnt2() {
		return consumeCnt2;
	}

	/**
	 * 设置金卡消费次数-2
	 * 
	 * @param consumeCnt2 金卡消费次数-2
	 */
	public void setConsumeCnt2(int consumeCnt2) {
		this.consumeCnt2 = consumeCnt2;
	}

	/**
	 * 获取消费次数
	 * 
	 * @return _totalConsumeCnt
	 */
	public int get_totalConsumeCnt() {
		return _totalConsumeCnt;
	}

	/**
	 * 设置消费次数
	 * 
	 * @param _totalConsumeCnt 消费次数
	 */
	public void set_totalConsumeCnt(int _totalConsumeCnt) {
		this._totalConsumeCnt = _totalConsumeCnt;
	}

	/**
	 * 获取消费次数-1
	 * 
	 * @return totalConsumeCnt1
	 */
	public int getTotalConsumeCnt1() {
		return totalConsumeCnt1;
	}

	/**
	 * 设置消费次数-1
	 * 
	 * @param totalConsumeCnt1 消费次数-1
	 */
	public void setTotalConsumeCnt1(int totalConsumeCnt1) {
		this.totalConsumeCnt1 = totalConsumeCnt1;
	}

	/**
	 * 获取消费次数-2
	 * 
	 * @return totalConsumeCnt2
	 */
	public int getTotalConsumeCnt2() {
		return totalConsumeCnt2;
	}

	/**
	 * 设置消费次数-2
	 * 
	 * @param totalConsumeCnt2 消费次数-2
	 */
	public void setTotalConsumeCnt2(int totalConsumeCnt2) {
		this.totalConsumeCnt2 = totalConsumeCnt2;
	}

	/**
	 * 获取金卡消费金额
	 * 
	 * @return _saleAmt
	 */
	public BigDecimal get_saleAmt() {
		return _saleAmt;
	}

	/**
	 * 设置金卡消费金额
	 * 
	 * @param _saleAmt 金卡消费金额
	 */
	public void set_saleAmt(BigDecimal _saleAmt) {
		this._saleAmt = _saleAmt;
	}

	/**
	 * 获取金卡消费金额
	 * 
	 * @return saleAmt1
	 */
	public String getSaleAmt1() {
		if (saleAmt1.compareTo(BigDecimal.ZERO) == 1) {
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(saleAmt1);
		}
		return saleAmt1.toString();
	}

	/**
	 * 设置金卡消费金额
	 * 
	 * @param saleAmt1 金卡消费金额
	 */
	public void setSaleAmt1(BigDecimal saleAmt1) {
		this.saleAmt1 = saleAmt1;
	}

	/**
	 * 获取金卡消费金额
	 * 
	 * @return saleAmt2
	 */
	public String getSaleAmt2() {
		if (saleAmt2.compareTo(BigDecimal.ZERO) == 1) {
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(saleAmt2);
		}
		return saleAmt2.toString();
	}

	/**
	 * 设置金卡消费金额
	 * 
	 * @param saleAmt2 金卡消费金额
	 */
	public void setSaleAmt2(BigDecimal saleAmt2) {
		this.saleAmt2 = saleAmt2;
	}

	/**
	 * 获取消费金额
	 * 
	 * @return _totalSaleAmt
	 */
	public BigDecimal get_totalSaleAmt() {
		return _totalSaleAmt;
	}

	/**
	 * 设置消费金额
	 * 
	 * @param _totalSaleAmt 消费金额
	 */
	public void set_totalSaleAmt(BigDecimal _totalSaleAmt) {
		this._totalSaleAmt = _totalSaleAmt;
	}

	/**
	 * 获取消费金额-1
	 * 
	 * @return totalSaleAmt1
	 */
	public String getTotalSaleAmt1() {
		if (totalSaleAmt1.compareTo(BigDecimal.ZERO) == 1) {
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(totalSaleAmt1);
		}
		return totalSaleAmt1.toString();
	}

	/**
	 * 设置消费金额-1
	 * 
	 * @param totalSaleAmt1 消费金额-1
	 */
	public void setTotalSaleAmt1(BigDecimal totalSaleAmt1) {
		this.totalSaleAmt1 = totalSaleAmt1;
	}

	/**
	 * 获取消费金额-2
	 * 
	 * @return totalSaleAmt2
	 */
	public String getTotalSaleAmt2() {
		if (totalSaleAmt2.compareTo(BigDecimal.ZERO) == 1) {
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(totalSaleAmt2);
		}
		return totalSaleAmt2.toString();
	}

	/**
	 * 设置消费金额-2
	 * 
	 * @param totalSaleAmt2 消费金额-2
	 */
	public void setTotalSaleAmt2(BigDecimal totalSaleAmt2) {
		this.totalSaleAmt2 = totalSaleAmt2;
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
	 * 获取机构资金编号-百威
	 * 
	 * @return bwBranchNo
	 */
	public String getBwBranchNo() {
		return bwBranchNo;
	}

	/**
	 * 设置机构资金编号-百威
	 * 
	 * @param bwBranchNo 机构资金编号-百威
	 */
	public void setBwBranchNo(String bwBranchNo) {
		this.bwBranchNo = bwBranchNo;
	}

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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MembershipCard)) {
			return false;
		}

		MembershipCard rhs = (MembershipCard) obj;
		return new EqualsBuilder().append(this.getBwBranchNo(), rhs.getBwBranchNo()).isEquals();

	}
}
