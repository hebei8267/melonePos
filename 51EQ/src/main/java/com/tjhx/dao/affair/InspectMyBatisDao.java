package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.Inspect;

public interface InspectMyBatisDao {
	/**
	 * 根据参数取得门店巡查报告信息
	 * 
	 * @return
	 */
	public List<Inspect> getInspectList(Inspect inspect);

}
