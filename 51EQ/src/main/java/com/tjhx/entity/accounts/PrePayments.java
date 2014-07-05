/**
 * 
 */
package com.tjhx.entity.accounts;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;

/**
 * 顾客/会员预付款（充值、消费）信息
 */
@Entity
@Table(name = "T_PRE_PAYMENTS")
public class PrePayments extends IdEntity {

	private static final long serialVersionUID = -8544832740813796688L;
	/** 电话号码 */
	private String phoneNum;
	/** 用户名称-汉字 */
	private String name;
	/** 使用方式(1充值、2消费) */
	private Integer inOutFlg;
	/** 金额 */
	private BigDecimal amt = new BigDecimal("0");
	/** 上班类型(1早班、2晚班、4全天班) */
	private Integer jobType;
	/** 充值方式(1现金、2刷卡) */
	private Integer rechargeWay;
	/** 机构编号 */
	private String orgId;
	/** 日期 */
	private String optDate;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 日期 */
	private String optDateShow;
	/** 日期-开始 */
	private String optDateShow_start;
	/** 日期-结束 */
	private String optDateShow_end;

	/**
	 * 获取电话号码
	 * 
	 * @return phoneNum 电话号码
	 */
	@Column(nullable = false, length = 16)
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * 设置电话号码
	 * 
	 * @param phoneNum 电话号码
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * 获取用户名称-汉字
	 * 
	 * @return name 用户名称-汉字
	 */
	@Column(length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置用户名称-汉字
	 * 
	 * @param name 用户名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取使用方式(1充值、2消费)
	 * 
	 * @return inOutFlg 使用方式(1充值、2消费)
	 */
	@Column(nullable = false)
	public Integer getInOutFlg() {
		return inOutFlg;
	}

	/**
	 * 设置使用方式(1充值、2消费)
	 * 
	 * @param inOutFlg 使用方式(1充值、2消费)
	 */
	public void setInOutFlg(Integer inOutFlg) {
		this.inOutFlg = inOutFlg;
	}

	/**
	 * 获取充值金额
	 * 
	 * @return amt 充值金额
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * 设置充值金额
	 * 
	 * @param amt 充值金额
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * 获取上班类型(1早班、2晚班、4全天班)
	 * 
	 * @return jobType 上班类型(1早班、2晚班、4全天班)
	 */
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
	 * 获取充值方式(1现金、2刷卡)
	 * 
	 * @return rechargeWay 充值方式(1现金、2刷卡)
	 */
	public Integer getRechargeWay() {
		return rechargeWay;
	}

	/**
	 * 设置充值方式(1现金、2刷卡)
	 * 
	 * @param rechargeWay 充值方式(1现金、2刷卡)
	 */
	public void setRechargeWay(Integer rechargeWay) {
		this.rechargeWay = rechargeWay;
	}

	/**
	 * 获取机构编号
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
	 * 获取日期
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
	 * 获取日期-年
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
	 * 获取日期-月
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
	@Column(name = "OPT_DATE_M", length = 2)
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 获取日期
	 * 
	 * @return optDateShow 日期
	 */
	@Transient
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDateShow 日期
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
	}

	/**
	 * 获取日期-开始
	 * 
	 * @return optDateShow_start 日期-开始
	 */
	@Transient
	public String getOptDateShow_start() {
		return optDateShow_start;
	}

	/**
	 * 设置日期-开始
	 * 
	 * @param optDateShow_start 日期-开始
	 */
	public void setOptDateShow_start(String optDateShow_start) {
		this.optDateShow_start = optDateShow_start;
	}

	/**
	 * 获取日期-结束
	 * 
	 * @return optDateShow_end 日期-结束
	 */
	@Transient
	public String getOptDateShow_end() {
		return optDateShow_end;
	}

	/**
	 * 设置日期-结束
	 * 
	 * @param optDateShow_end 日期-结束
	 */
	public void setOptDateShow_end(String optDateShow_end) {
		this.optDateShow_end = optDateShow_end;
	}

}
