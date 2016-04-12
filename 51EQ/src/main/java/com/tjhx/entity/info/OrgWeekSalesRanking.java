package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 机构周销售排名
 */
@Entity
@Table(name = "T_ORG_WEEK_SALES_RANKING")
public class OrgWeekSalesRanking extends IdEntity {

	private static final long serialVersionUID = -267479266385730711L;
	/** 年份 */
	private Integer optDateY;
	/** 星期(周) */
	private Integer optDateW;
	/** 周区间 */
	private String optDateWSection;
	/** 门店编号 */
	private String orgId;
	/** 销售金额 */
	private BigDecimal saleCashAmt = new BigDecimal("0");
	/** 排名 */
	private Integer ranking;
	/** 等级 */
	private String level;
	/** 等级排名 */
	private Integer rankingLevel;

	/**
	 * 取得年份
	 * 
	 * @return 年份
	 */
	@NaturalId
	@Column(name = "OPT_DATE_Y")
	public Integer getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置年份
	 * 
	 * @param optDateY 年份
	 */
	public void setOptDateY(Integer optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得星期(周)
	 * 
	 * @return 星期(周)
	 */
	@NaturalId
	@Column(name = "OPT_DATE_W")
	public Integer getOptDateW() {
		return optDateW;
	}

	/**
	 * 设置星期(周)
	 * 
	 * @param optDateW 星期(周)
	 */
	public void setOptDateW(Integer optDateW) {
		this.optDateW = optDateW;
	}

	/**
	 * 取得周区间
	 * 
	 * @return 周区间
	 */
	@Column(name = "OPT_DATE_W_SECTION", length = 17)
	public String getOptDateWSection() {
		return optDateWSection;
	}

	/**
	 * 设置周区间
	 * 
	 * @param optDateWSection 周区间
	 */
	public void setOptDateWSection(String optDateWSection) {
		this.optDateWSection = optDateWSection;
	}

	/**
	 * 取得门店编号
	 * 
	 * @return 门店编号
	 */
	@NaturalId
	@Column(length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置门店编号
	 * 
	 * @param orgId 门店编号
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 取得销售金额
	 * 
	 * @return 销售金额
	 */
	public BigDecimal getSaleCashAmt() {
		return saleCashAmt;
	}

	/**
	 * 设置销售金额
	 * 
	 * @param saleCashAmt 销售金额
	 */
	public void setSaleCashAmt(BigDecimal saleCashAmt) {
		this.saleCashAmt = saleCashAmt;
	}

	/**
	 * 取得排名
	 * 
	 * @return 排名
	 */
	public Integer getRanking() {
		return ranking;
	}

	/**
	 * 设置排名
	 * 
	 * @param ranking 排名
	 */
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	/**
	 * 取得等级
	 * 
	 * @return 等级
	 */
	@Column(name = "LVL", length = 1)
	public String getLevel() {
		return level;
	}

	/**
	 * 设置等级
	 * 
	 * @param level 等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 取得等级排名
	 * 
	 * @return 等级排名
	 */
	public Integer getRankingLevel() {
		return rankingLevel;
	}

	/**
	 * 设置等级排名
	 * 
	 * @param rankingLevel 等级排名
	 */
	public void setRankingLevel(Integer rankingLevel) {
		this.rankingLevel = rankingLevel;
	}

}
