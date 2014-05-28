package com.tjhx.entity.accounts;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 月销售目标
 */
@Entity
@Table(name = "T_MONTH_SALE_TARGET")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MonthSaleTarget extends IdEntity {

	private static final long serialVersionUID = 2515931147092043043L;

	/** 机构编号 */
	private String orgId;
	/** 年份 */
	private String optDateY;
	/** 月份 */
	private String optDateM;
	/** 月销售目标额 */
	private BigDecimal saleTargetAmt = new BigDecimal(0);

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
	 * 取得日期-年
	 * 
	 * @return optDateY 日期-年
	 */
	@NaturalId
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
	@NaturalId
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
	 * 获取月销售目标额
	 * 
	 * @return saleTargetAmt 月销售目标额
	 */
	public BigDecimal getSaleTargetAmt() {
		return saleTargetAmt;
	}

	/**
	 * 设置月销售目标额
	 * 
	 * @param saleTargetAmt 月销售目标额
	 */
	public void setSaleTargetAmt(BigDecimal saleTargetAmt) {
		this.saleTargetAmt = saleTargetAmt;
	}
}
