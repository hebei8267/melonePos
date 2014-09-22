/**
 * 
 */
package com.tjhx.entity.affair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 门店巡查报告(运营)
 */
@Entity
@Table(name = "T_RUN_INSPECT")
public class RunInspect extends IdEntity {

	private static final long serialVersionUID = -8034259767801686310L;
	/** 门店巡查报告(运营)编号 */
	private String trsId;
	/** 店铺名称 */
	private String orgId;
	/** 当班负责人 */
	private String dutyPerson;
	/** 评核日期 */
	private String optDateY;
	/** 评核日期 */
	private String optDateM;
	/** 评核日期 */
	private String optDate;
	/** 评核员 */
	private String assessors;
	/** 收银台礼仪-得分 */
	private int score1;
	/** 卖场巡检-得分 */
	private int score2;
	/** 意见或建议-收银台礼仪 */
	private String comments;
	/** 意见或建议-卖场巡检 */
	private String comments2;
	/** 店铺反馈问题及跟进 */
	private String feedback;
	/** 货品问题的发现及跟进 */
	private String goodsIssue;
	/** 现场违纪违规及处罚情况 */
	private String penaltyCase;
	/** 培训统计 */
	private String trainingStatistics;
	/** 库存统计 */
	private String inventoryStatistics;
	// --------------------------------------
	/** 评核日期 */
	private String optDateShow;

	/**
	 * 获取门店巡查报告(运营)编号
	 * 
	 * @return trsId 门店巡查报告(运营)编号
	 */
	@NaturalId
	@Column(length = 16)
	public String getTrsId() {
		return trsId;
	}

	/**
	 * 设置门店巡查报告(运营)编号
	 * 
	 * @param trsId 门店巡查报告(运营)编号
	 */
	public void setTrsId(String trsId) {
		this.trsId = trsId;
	}

	/**
	 * 获取店铺名称
	 * 
	 * @return orgId 店铺名称
	 */
	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置店铺名称
	 * 
	 * @param orgId 店铺名称
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取当班负责人
	 * 
	 * @return dutyPerson 当班负责人
	 */
	@Column(length = 32)
	public String getDutyPerson() {
		return dutyPerson;
	}

	/**
	 * 设置当班负责人
	 * 
	 * @param dutyPerson 当班负责人
	 */
	public void setDutyPerson(String dutyPerson) {
		this.dutyPerson = dutyPerson;
	}

	/**
	 * 获取评核日期
	 * 
	 * @return optDateY 评核日期
	 */
	@Column(length = 4, name = "OPT_DATE_Y")
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置评核日期
	 * 
	 * @param optDateY 评核日期
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 获取评核日期
	 * 
	 * @return optDateM 评核日期
	 */
	@Column(length = 2, name = "OPT_DATE_M")
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置评核日期
	 * 
	 * @param optDateM 评核日期
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 获取评核日期
	 * 
	 * @return optDate 评核日期
	 */
	@Column(length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置评核日期
	 * 
	 * @param optDate 评核日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 获取评核员
	 * 
	 * @return assessors 评核员
	 */
	@Column(length = 32)
	public String getAssessors() {
		return assessors;
	}

	/**
	 * 设置评核员
	 * 
	 * @param assessors 评核员
	 */
	public void setAssessors(String assessors) {
		this.assessors = assessors;
	}

	/**
	 * 获取收银台礼仪-得分
	 * 
	 * @return score1 收银台礼仪-得分
	 */
	public int getScore1() {
		return score1;
	}

	/**
	 * 设置收银台礼仪-得分
	 * 
	 * @param score1 收银台礼仪-得分
	 */
	public void setScore1(int score1) {
		this.score1 = score1;
	}

	/**
	 * 获取卖场巡检-得分
	 * 
	 * @return score2 卖场巡检-得分
	 */
	public int getScore2() {
		return score2;
	}

	/**
	 * 设置卖场巡检-得分
	 * 
	 * @param score2 卖场巡检-得分
	 */
	public void setScore2(int score2) {
		this.score2 = score2;
	}

	/**
	 * 获取意见或建议-收银台礼仪
	 * 
	 * @return comments 意见或建议-收银台礼仪
	 */
	@Column(length = 1024)
	public String getComments() {
		return comments;
	}

	/**
	 * 设置意见或建议-收银台礼仪
	 * 
	 * @param comments 意见或建议-收银台礼仪
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * 获取意见或建议-卖场巡检
	 * 
	 * @return comments2 意见或建议-卖场巡检
	 */
	@Column(length = 1024)
	public String getComments2() {
		return comments2;
	}

	/**
	 * 设置意见或建议-卖场巡检
	 * 
	 * @param comments2 意见或建议-卖场巡检
	 */
	public void setComments2(String comments2) {
		this.comments2 = comments2;
	}

	/**
	 * 获取店铺反馈问题及跟进
	 * 
	 * @return feedback 店铺反馈问题及跟进
	 */
	@Column(length = 1024)
	public String getFeedback() {
		return feedback;
	}

	/**
	 * 设置店铺反馈问题及跟进
	 * 
	 * @param feedback 店铺反馈问题及跟进
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 * 获取货品问题的发现及跟进
	 * 
	 * @return goodsIssue 货品问题的发现及跟进
	 */
	@Column(length = 1024)
	public String getGoodsIssue() {
		return goodsIssue;
	}

	/**
	 * 设置货品问题的发现及跟进
	 * 
	 * @param goodsIssue 货品问题的发现及跟进
	 */
	public void setGoodsIssue(String goodsIssue) {
		this.goodsIssue = goodsIssue;
	}

	/**
	 * 获取现场违纪违规及处罚情况
	 * 
	 * @return penaltyCase 现场违纪违规及处罚情况
	 */
	@Column(length = 1024)
	public String getPenaltyCase() {
		return penaltyCase;
	}

	/**
	 * 设置现场违纪违规及处罚情况
	 * 
	 * @param penaltyCase 现场违纪违规及处罚情况
	 */
	public void setPenaltyCase(String penaltyCase) {
		this.penaltyCase = penaltyCase;
	}

	/**
	 * 获取培训统计
	 * 
	 * @return trainingStatistics 培训统计
	 */
	@Column(length = 1024)
	public String getTrainingStatistics() {
		return trainingStatistics;
	}

	/**
	 * 设置培训统计
	 * 
	 * @param trainingStatistics 培训统计
	 */
	public void setTrainingStatistics(String trainingStatistics) {
		this.trainingStatistics = trainingStatistics;
	}

	/**
	 * 获取库存统计
	 * 
	 * @return inventoryStatistics 库存统计
	 */
	@Column(length = 1024)
	public String getInventoryStatistics() {
		return inventoryStatistics;
	}

	/**
	 * 设置库存统计
	 * 
	 * @param inventoryStatistics 库存统计
	 */
	public void setInventoryStatistics(String inventoryStatistics) {
		this.inventoryStatistics = inventoryStatistics;
	}

	// --------------------------------------------------------
	/**
	 * 获取评核日期
	 * 
	 * @return optDateShow 评核日期
	 */
	@Transient
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置评核日期
	 * 
	 * @param optDateShow 评核日期
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
	}
}
