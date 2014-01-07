package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.WorkSchedule;

public interface WorkScheduleMyBatisDao {

	/**
	 * 取得指定日期开始的所有排班信息
	 * 
	 * @param workScheduleDate
	 * @return
	 */
	public List<WorkSchedule> getWorkScheduleListByDate(WorkSchedule workSchedule);

	/**
	 * 取得指定月份的排班信息
	 * 
	 * @param workScheduleDateYM
	 * @return
	 */
	public List<WorkSchedule> getWorkScheduleListByDateYM(WorkSchedule workSchedule);
}
