package com.tjhx.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

/**
 * 督导统计信息
 */
public class MngUserStatisticsTotal {
	/** 统计日期 */
	private String optDate;
	/** 当月任务额 */
	private BigDecimal monthTaskAmt = new BigDecimal("0");
	/** 当前完成额 */
	private BigDecimal monthCompleteAmt = new BigDecimal("0");
	/** 预计本月销售 */
	private BigDecimal expMonthAmt = new BigDecimal("0");
	/** 统计店铺数量 */
	private int orgCnt;
	/** 任务完成店数 */
	private int orgCompleteCnt;
	/** 督导统计信息一览（机构） */
	private Map<String, List<MngUserStatisticsOrgDetail>> mngOrgMap;
	/** 督导统计信息一览 */
	private List<MngUserStatisticsDetail> sDetailList;

	/**
	 * 取得统计日期
	 * 
	 * @return 统计日期
	 */
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置统计日期
	 * 
	 * @param optDate 统计日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得当月任务额
	 * 
	 * @return 当月任务额
	 */
	public BigDecimal getMonthTaskAmt() {
		return monthTaskAmt;
	}

	/**
	 * 设置当月任务额
	 * 
	 * @param monthTaskAmt 当月任务额
	 */
	public void setMonthTaskAmt(BigDecimal monthTaskAmt) {
		this.monthTaskAmt = monthTaskAmt;
	}

	/**
	 * 取得当前完成额
	 * 
	 * @return 当前完成额
	 */
	public BigDecimal getMonthCompleteAmt() {
		return monthCompleteAmt;
	}

	/**
	 * 设置当前完成额
	 * 
	 * @param monthCompleteAmt 当前完成额
	 */
	public void setMonthCompleteAmt(BigDecimal monthCompleteAmt) {
		this.monthCompleteAmt = monthCompleteAmt;
	}

	/**
	 * 取得预计本月销售
	 * 
	 * @return 预计本月销售
	 */
	public BigDecimal getExpMonthAmt() {
		return expMonthAmt;
	}

	/**
	 * 设置预计本月销售
	 * 
	 * @param expMonthAmt 预计本月销售
	 */
	public void setExpMonthAmt(BigDecimal expMonthAmt) {
		this.expMonthAmt = expMonthAmt;
	}

	/**
	 * 取得统计店铺数量
	 * 
	 * @return 统计店铺数量
	 */
	public int getOrgCnt() {
		return orgCnt;
	}

	/**
	 * 设置统计店铺数量
	 * 
	 * @param orgCnt 统计店铺数量
	 */
	public void setOrgCnt(int orgCnt) {
		this.orgCnt = orgCnt;
	}

	/**
	 * 取得任务完成店数
	 * 
	 * @return 任务完成店数
	 */
	public int getOrgCompleteCnt() {
		return orgCompleteCnt;
	}

	/**
	 * 设置任务完成店数
	 * 
	 * @param orgCompleteCnt 任务完成店数
	 */
	public void setOrgCompleteCnt(int orgCompleteCnt) {
		this.orgCompleteCnt = orgCompleteCnt;
	}

	/**
	 * 取得督导统计信息一览（机构）
	 * 
	 * @return 督导统计信息一览（机构）
	 */
	public Map<String, List<MngUserStatisticsOrgDetail>> getMngOrgMap() {
		return mngOrgMap;
	}

	/**
	 * 设置督导统计信息一览（机构）
	 * 
	 * @param mngOrgMap 督导统计信息一览（机构）
	 */
	public void setMngOrgMap(Map<String, List<MngUserStatisticsOrgDetail>> mngOrgMap) {
		this.mngOrgMap = mngOrgMap;
	}

	/**
	 * 取得督导统计信息一览
	 * 
	 * @return 督导统计信息一览
	 */
	public List<MngUserStatisticsDetail> getsDetailList() {
		return sDetailList;
	}

	/**
	 * 设置督导统计信息一览
	 * 
	 * @param sDetailList 督导统计信息一览
	 */
	public void setsDetailList(List<MngUserStatisticsDetail> sDetailList) {
		this.sDetailList = sDetailList;
	}

	/**
	 * 设置督导统计信息一览
	 * 
	 * @param mngMap
	 * @param totalMngUserStatisticsDetail
	 */
	public void setsDetailList(Map<String, MngUserStatisticsDetail> mngMap, MngUserStatisticsDetail totalMngUserStatisticsDetail) {
		this.sDetailList = Lists.newArrayList();

		for (Entry<String, MngUserStatisticsDetail> entry : mngMap.entrySet()) {
			this.sDetailList.add(entry.getValue());
		}
		this.sDetailList.add(totalMngUserStatisticsDetail);
	}
}
