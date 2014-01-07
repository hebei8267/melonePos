package com.tjhx.entity.affair;

public class WorkSchedule_Show {
	/** 排班日期 */
	private String scheduleDate;
	/** 员工编号 */
	private String empCode;
	/** 上班类型名称 */
	private String workTypeName;
	/** 上班类型Uuid */
	private Integer workTypeUuid;
	/** 工作时间 HH:mm - HH:mm */
	private String workTime;

	/**
	 * 取得上班类型名称
	 * 
	 * @return workTypeName 上班类型名称
	 */
	public String getWorkTypeName() {
		return workTypeName;
	}

	/**
	 * 设置上班类型名称
	 * 
	 * @param workTypeName 上班类型名称
	 */
	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	/**
	 * 取得员工编号
	 * 
	 * @return empCode 员工编号
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * 设置员工编号
	 * 
	 * @param empCode 员工编号
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

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
	 * 取得上班类型Uuid
	 * 
	 * @return workTypeUuid 上班类型Uuid
	 */
	public Integer getWorkTypeUuid() {
		return workTypeUuid;
	}

	/**
	 * 设置上班类型Uuid
	 * 
	 * @param workTypeUuid 上班类型Uuid
	 */
	public void setWorkTypeUuid(Integer workTypeUuid) {
		this.workTypeUuid = workTypeUuid;
	}

	/**
	 * 取得工作时间HH:mm-HH:mm
	 * 
	 * @return workTime 工作时间HH:mm-HH:mm
	 */
	public String getWorkTime() {
		return workTime;
	}

	/**
	 * 设置工作时间HH:mm-HH:mm
	 * 
	 * @param workTime 工作时间HH:mm-HH:mm
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

}
