package com.tjhx.dao.affair;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.WorkSchedule;

/**
 * 排班表JpaDao
 */
public interface WorkScheduleJpaDao extends CrudRepository<WorkSchedule, Integer> {
	@Query("select ws from WorkSchedule ws where ws.empCode = :empCode and ws.scheduleDate = :scheduleDate")
	public WorkSchedule findByEmpCodeAndscheduleDate(@Param("empCode") String empCode,
			@Param("scheduleDate") String scheduleDate);
}
