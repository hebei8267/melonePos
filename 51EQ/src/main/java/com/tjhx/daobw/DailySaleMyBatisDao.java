package com.tjhx.daobw;

import java.util.List;

import com.tjhx.entity.bw.DailySale;

public interface DailySaleMyBatisDao {

	public List<DailySale> getDailySaleList(String operDate);

}
