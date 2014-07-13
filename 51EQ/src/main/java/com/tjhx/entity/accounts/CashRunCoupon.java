/**
 * 
 */
package com.tjhx.entity.accounts;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 销售流水-代金卷明细
 */
@Entity
@Table(name = "T_CASH_RUN_COUPON")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CashRunCoupon extends IdEntity {

	private static final long serialVersionUID = -6355363056608344967L;
	/** 机构编号 */
	private String orgId;
	/** 日期 */
	private String optDate;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 上班类型(1早班、2晚班、4全天班) */
	private Integer jobType;
	/** 代金卷编号 */
	private String couponNo;
	/** 代金卷面值 */
	private BigDecimal couponValue = new BigDecimal("0");
	/** 代金卷实际值 */
	private BigDecimal couponCashValue = new BigDecimal("0");

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
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
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
	 * 获取代金卷编号
	 * 
	 * @return couponNo 代金卷编号
	 */
	@Column(length = 8)
	public String getCouponNo() {
		return couponNo;
	}

	/**
	 * 设置代金卷编号
	 * 
	 * @param couponNo 代金卷编号
	 */
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
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

}
