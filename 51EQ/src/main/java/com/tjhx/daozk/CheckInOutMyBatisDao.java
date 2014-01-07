package com.tjhx.daozk;

import java.util.List;

import com.tjhx.entity.zknet.CheckInOut;

public interface CheckInOutMyBatisDao {

	public List<CheckInOut> getCheckInOutList(CheckInOut checkInOut);
}
