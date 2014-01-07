package com.tjhx.entity.affair;

import java.util.ArrayList;
import java.util.List;

public class PunchClock_List_Show {

	private List<PunchClock_Show> punchClockList = new ArrayList<PunchClock_Show>();
	/** 打卡时间-年月日 */
	private String clockTime;

	public PunchClock_List_Show(String clockTime) {
		this.clockTime = clockTime;
	}

	/**
	 * 取得punchClockList
	 * 
	 * @return punchClockList punchClockList
	 */
	public List<PunchClock_Show> getPunchClockList() {
		return punchClockList;
	}

	/**
	 * 设置punchClockList
	 * 
	 * @param punchClockList punchClockList
	 */
	public void setPunchClockList(List<PunchClock_Show> punchClockList) {
		this.punchClockList = punchClockList;
	}

	/**
	 * 取得打卡时间-年月日
	 * 
	 * @return clockTime 打卡时间-年月日
	 */
	public String getClockTime() {
		return clockTime;
	}

	/**
	 * 设置打卡时间-年月日
	 * 
	 * @param clockTime 打卡时间-年月日
	 */
	public void setClockTime(String clockTime) {
		this.clockTime = clockTime;
	}

	public void addPunchClockShowObj(PunchClock_Show punchClock_Show) {
		punchClockList.add(punchClock_Show);
	}

}
