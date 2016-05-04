package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	// ==================================================================================
	/** 年份 */
	private Integer optDateY_Last;
	/** 星期(周) */
	private Integer optDateW_Last;
	/** 周区间 */
	private String optDateWSection_Last;
	/** 销售金额 */
	private BigDecimal saleCashAmt_Last = new BigDecimal("0");
	/** 排名 */
	private Integer ranking_Last;
	/** 等级 */
	private String level_Last;
	/** 等级排名 */
	private Integer rankingLevel_Last;
	/** 间隙 */
	private Integer gap;

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

	/**
	 * 取得optDateY_Last
	 * 
	 * @return optDateY_Last
	 */
	@Transient
	public Integer getOptDateY_Last() {
		return optDateY_Last;
	}

	/**
	 * 设置optDateY_Last
	 * 
	 * @param optDateY_Last optDateY_Last
	 */
	public void setOptDateY_Last(Integer optDateY_Last) {
		this.optDateY_Last = optDateY_Last;
	}

	/**
	 * 取得optDateW_Last
	 * 
	 * @return optDateW_Last
	 */
	@Transient
	public Integer getOptDateW_Last() {
		return optDateW_Last;
	}

	/**
	 * 设置optDateW_Last
	 * 
	 * @param optDateW_Last optDateW_Last
	 */
	public void setOptDateW_Last(Integer optDateW_Last) {
		this.optDateW_Last = optDateW_Last;
	}

	/**
	 * 取得optDateWSection_Last
	 * 
	 * @return optDateWSection_Last
	 */
	@Transient
	public String getOptDateWSection_Last() {
		return optDateWSection_Last;
	}

	/**
	 * 设置optDateWSection_Last
	 * 
	 * @param optDateWSection_Last optDateWSection_Last
	 */
	public void setOptDateWSection_Last(String optDateWSection_Last) {
		this.optDateWSection_Last = optDateWSection_Last;
	}

	/**
	 * 取得saleCashAmt_Last
	 * 
	 * @return saleCashAmt_Last
	 */
	@Transient
	public BigDecimal getSaleCashAmt_Last() {
		return saleCashAmt_Last;
	}

	/**
	 * 设置saleCashAmt_Last
	 * 
	 * @param saleCashAmt_Last saleCashAmt_Last
	 */
	public void setSaleCashAmt_Last(BigDecimal saleCashAmt_Last) {
		this.saleCashAmt_Last = saleCashAmt_Last;
	}

	/**
	 * 取得ranking_Last
	 * 
	 * @return ranking_Last
	 */
	@Transient
	public Integer getRanking_Last() {
		return ranking_Last;
	}

	/**
	 * 设置ranking_Last
	 * 
	 * @param ranking_Last ranking_Last
	 */
	public void setRanking_Last(Integer ranking_Last) {
		this.ranking_Last = ranking_Last;
	}

	/**
	 * 取得level_Last
	 * 
	 * @return level_Last
	 */
	@Transient
	public String getLevel_Last() {
		return level_Last;
	}

	/**
	 * 设置level_Last
	 * 
	 * @param level_Last level_Last
	 */
	public void setLevel_Last(String level_Last) {
		this.level_Last = level_Last;
	}

	/**
	 * 取得rankingLevel_Last
	 * 
	 * @return rankingLevel_Last
	 */
	@Transient
	public Integer getRankingLevel_Last() {
		return rankingLevel_Last;
	}

	/**
	 * 设置rankingLevel_Last
	 * 
	 * @param rankingLevel_Last rankingLevel_Last
	 */
	public void setRankingLevel_Last(Integer rankingLevel_Last) {
		this.rankingLevel_Last = rankingLevel_Last;
	}

	/**
	 * 取得gap
	 * 
	 * @return gap
	 */
	@Transient
	public Integer getGap() {
		if (null != ranking && null != ranking_Last) {
			return ranking_Last - ranking;
		}
		return gap;
	}

	/**
	 * 设置gap
	 * 
	 * @param gap gap
	 */
	public void setGap(Integer gap) {
		this.gap = gap;
	}

}
