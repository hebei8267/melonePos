package com.tjhx.entity.affair;

import java.util.ArrayList;
import java.util.List;

public class WorkSchedule_List_Show {

	/** 排班日期 */
	private String scheduleDate;
	/** 排班日期-对应的星期 */
	private String week;
	/** 排班表 */
	private List<WorkSchedule_Show> scheduleList = new ArrayList<WorkSchedule_Show>();
	/** 可编辑标记 */
	private boolean editFlg;

	/**
	 * 取得排班日期
	 * 
	 * @return scheduleDate 排班日期
	 */
	public String getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * 设置排班日期
	 * 
	 * @param scheduleDate 排班日期
	 */
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * 取得排班日期-对应的星期
	 * 
	 * @return week 排班日期-对应的星期
	 */
	public String getWeek() {
		return week;
	}

	/**
	 * 设置排班日期-对应的星期
	 * 
	 * @param week 排班日期-对应的星期
	 */
	public void setWeek(String week) {
		this.week = week;
	}

	/**
	 * 取得排班表
	 * 
	 * @return scheduleList 排班表
	 */
	public List<WorkSchedule_Show> getScheduleList() {
		return scheduleList;
	}

	/**
	 * 设置排班表
	 * 
	 * @param scheduleList 排班表
	 */
	public void setScheduleList(List<WorkSchedule_Show> scheduleList) {
		this.scheduleList = scheduleList;
	}

	/**
	 * 取得可编辑标记
	 * 
	 * @return editFlg 可编辑标记
	 */
	public boolean isEditFlg() {
		return editFlg;
	}

	/**
	 * 设置可编辑标记
	 * 
	 * @param editFlg 可编辑标记
	 */
	public void setEditFlg(boolean editFlg) {
		this.editFlg = editFlg;
	}

}
