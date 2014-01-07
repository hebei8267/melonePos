package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.PunchClock;

public interface PunchClockMyBatisDao {

	public void delPunchClockInfo(PunchClock punchClock);

	public List<PunchClock> getPunchClockList(PunchClock punchClock);
}
