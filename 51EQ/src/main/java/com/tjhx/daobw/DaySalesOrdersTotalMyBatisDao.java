package com.tjhx.daobw;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.info.SalesOrdersDayTotal;

public interface DaySalesOrdersTotalMyBatisDao {

	public List<SalesOrdersDayTotal> getSalesOrdersDayTotalList(Map<String, String> paramMap);

}
