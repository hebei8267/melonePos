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
