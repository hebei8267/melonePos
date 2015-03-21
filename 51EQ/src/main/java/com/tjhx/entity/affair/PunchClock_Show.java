package com.tjhx.entity.affair;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.tjhx.common.utils.DateUtils;

public class PunchClock_Show {
	/** 打卡时间-年 */
	private String clockTimeY;
	/** 打卡时间-月 */
	private String clockTimeM;
	/** 打卡时间-日 */
	private String clockTimeD;
	/** 打卡时间-开始 */
	private String startClockTime;
	/** 打卡时间-结束 */
	private String endClockTime;
	/** 打卡时间-开始 */
	private Date _startClockTime;
	/** 打卡时间-结束 */
	private Date _endClockTime;
	/** 排班日期 YYYYMMDD */
	private String scheduleDate;
	/** 排班上班时间 HH:mm */
	private String startScheduleTime;
	/** 排班下班时间 HH:mm */
	private String endScheduleTime;
	/** 员工编号 */
	private int empUuid;
	/** 员工名称 */
	private String empName;
	/** 考勤状态 99-非预期加班 0-正常 1-迟到 2-早退 3-工作时长不满 */
	private int punchNormalState;
	/** 工作时长 */
	private double workHour;

	public PunchClock_Show() {

	}

	public PunchClock_Show(String clockTimeY, String clockTimeM, String clockTimeD, int empUuid, String empName) {
		this.clockTimeY = clockTimeY;
		this.clockTimeM = clockTimeM;
		this.clockTimeD = clockTimeD;
		this.empUuid = empUuid;
		this.empName = empName;
	}

	/**
	 * 取得打卡时间-年
	 * 
	 * @return clockTimeY 打卡时间-年
	 */
	public String getClockTimeY() {
		return clockTimeY;
	}

	/**
	 * 设置打卡时间-年
	 * 
	 * @param clockTimeY 打卡时间-年
	 */
	public void setClockTimeY(String clockTimeY) {
		this.clockTimeY = clockTimeY;
	}

	/**
	 * 取得打卡时间-月
	 * 
	 * @return clockTimeM 打卡时间-月
	 */
	public String getClockTimeM() {
		return clockTimeM;
	}

	/**
	 * 设置打卡时间-月
	 * 
	 * @param clockTimeM 打卡时间-月
	 */
	public void setClockTimeM(String clockTimeM) {
		this.clockTimeM = clockTimeM;
	}

	/**
	 * 取得打卡时间-日
	 * 
	 * @return clockTimeD 打卡时间-日
	 */
	public String getClockTimeD() {
		return clockTimeD;
	}

	/**
	 * 设置打卡时间-日
	 * 
	 * @param clockTimeD 打卡时间-日
	 */
	public void setClockTimeD(String clockTimeD) {
		this.clockTimeD = clockTimeD;
	}

	/**
	 * 取得打卡时间-开始
	 * 
	 * @return startClockTime 打卡时间-开始
	 */
	public String getStartClockTime() {
		return startClockTime;
	}

	/**
	 * 设置打卡时间-开始
	 * 
	 * @param startClockTime 打卡时间-开始
	 */
	public void setStartClockTime(String startClockTime) {
		this.startClockTime = startClockTime;
	}

	/**
	 * 取得打卡时间-结束
	 * 
	 * @return endClockTime 打卡时间-结束
	 */
	public String getEndClockTime() {
		return endClockTime;
	}

	/**
	 * 设置打卡时间-结束
	 * 
	 * @param endClockTime 打卡时间-结束
	 */
	public void setEndClockTime(String endClockTime) {
		this.endClockTime = endClockTime;
	}

	/**
	 * 取得员工编号
	 * 
	 * @return empUuid 员工编号
	 */
	public int getEmpUuid() {
		return empUuid;
	}

	/**
	 * 设置员工编号
	 * 
	 * @param empUuid 员工编号
	 */
	public void setEmpUuid(int empUuid) {
		this.empUuid = empUuid;
	}

	/**
	 * 取得排班日期YYYYMMDD
	 * 
	 * @return scheduleDate 排班日期YYYYMMDD
	 */
	public String getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * 设置排班日期YYYYMMDD
	 * 
	 * @param scheduleDate 排班日期YYYYMMDD
	 */
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * 取得排班上班时间HH:mm
	 * 
	 * @return startScheduleTime 排班上班时间HH:mm
	 */
	public String getStartScheduleTime() {
		return startScheduleTime;
	}

	/**
	 * 设置排班上班时间HH:mm
	 * 
	 * @param startScheduleTime 排班上班时间HH:mm
	 */
	public void setStartScheduleTime(String startScheduleTime) {
		this.startScheduleTime = startScheduleTime;
	}

	/**
	 * 取得排班下班时间HH:mm
	 * 
	 * @return endScheduleTime 排班下班时间HH:mm
	 */
	public String getEndScheduleTime() {
		return endScheduleTime;
	}

	/**
	 * 设置排班下班时间HH:mm
	 * 
	 * @param endScheduleTime 排班下班时间HH:mm
	 */
	public void setEndScheduleTime(String endScheduleTime) {
		this.endScheduleTime = endScheduleTime;
	}

	/**
	 * 取得考勤状态99-非预期加班 0-正常 1-迟到 2-早退
	 * 
	 * @return punchNormalState 考勤状态99-非预期加班0-正常1-迟到2-早退
	 */
	public int getPunchNormalState() {
		return punchNormalState;
	}

	/**
	 * 设置考勤状态99-非预期加班 0-正常 1-迟到 2-早退 3-工作时长不满
	 * 
	 * @param punchNormalState 考勤状态99-非预期加班 0-正常 1-迟到 2-早退 3-工作时长不满
	 */
	public void setPunchNormalState(int punchNormalState) {
		this.punchNormalState = punchNormalState;
	}

	/**
	 * 取得工作时长
	 * 
	 * @return 工作时长
	 */
	public double getWorkHour() {
		return workHour;
	}

	/**
	 * 设置工作时长
	 * 
	 * @param workHour 工作时长
	 */
	public void setWorkHour(double workHour) {
		this.workHour = workHour;
	}

	/**
	 * 获取员工名称
	 * 
	 * @return empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * 设置员工名称
	 * 
	 * @param empName 员工名称
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void copy(PunchClock clock) {
		this._startClockTime = clock.getStartClockTime();
		this._endClockTime = clock.getEndClockTime();

		this.startClockTime = DateUtils.transDateFormat(clock.getStartClockTime(), "MM/dd HH:mm:ss");
		this.endClockTime = DateUtils.transDateFormat(clock.getEndClockTime(), "MM/dd HH:mm:ss");
		// 设置工作时长
		if (null != clock.getStartClockTime() && null != clock.getEndClockTime()) {
			setWorkHour(DateUtils.getDateSpanHour(clock.getStartClockTime(), clock.getEndClockTime()));
		}
	}

	public void copy(WorkSchedule workSchedule) {
		this.startScheduleTime = workSchedule.getStartTime();
		this.endScheduleTime = workSchedule.getEndTime();
		this.scheduleDate = workSchedule.getScheduleDate();
	}

	public void timeValidate() throws ParseException {
		// 99-非预期加班 0-正常 1-迟到 2-早退 3-工作时长不满

		String curDate = DateUtils.getCurrentDateShortStr();
		String clockTime = clockTimeY + clockTimeM + clockTimeD;
		if (Integer.valueOf(curDate) < Integer.valueOf(clockTime)) {// 未来日不做计算
			punchNormalState = 0;// 正常
		} else if (StringUtils.isBlank(startScheduleTime) || StringUtils.isBlank(endScheduleTime)) {
			punchNormalState = 0;// 正常

			if (StringUtils.isNotBlank(startClockTime) || StringUtils.isNotBlank(endClockTime)) {

				if (_startClockTime != null && _endClockTime != null
						&& DateUtils.getDateSpanHour(_startClockTime, _endClockTime) < 7) {
					// 工作时长不满
					punchNormalState = 3;
				} else {
					// 非预期加班
					punchNormalState = 99;
				}
			}
		} else {
			// 是否迟到
			if (StringUtils.isBlank(startClockTime)) {
				punchNormalState = 1;// 迟到
			} else {
				long timeBetween1 = DateUtils.timeBetween(startClockTime, "MM/dd HH:mm:ss", scheduleDate.substring(4, 8) + " "
						+ startScheduleTime, "MMdd HH:mm");
				if (timeBetween1 < 0) {
					punchNormalState = 1;// 迟到
				}
			}

			// 是否早退
			if (StringUtils.isBlank(endClockTime)) {
				punchNormalState = 2;// 早退
			} else {
				long timeBetween2 = DateUtils.timeBetween(scheduleDate.substring(4, 8) + " " + endScheduleTime, "MMdd HH:mm",
						endClockTime, "MM/dd HH:mm:ss");
				if (timeBetween2 < 0) {
					punchNormalState = 2;// 早退
				}
			}

			// 工作时长不满
			if (_startClockTime != null && _endClockTime != null && DateUtils.getDateSpanHour(_startClockTime, _endClockTime) < 7) {
				// 工作时长不满
				punchNormalState = 3;
			}
		}
	}
}
