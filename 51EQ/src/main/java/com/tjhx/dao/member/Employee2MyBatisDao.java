package com.tjhx.dao.member;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.member.Employee2;

public interface Employee2MyBatisDao {

	/**
	 * @param name
	 * @param idCardNo
	 * @param phone
	 * @param orgId
	 * @return
	 */
	public List<Employee2> getEmployee2List(Map<String, String> param);

}
