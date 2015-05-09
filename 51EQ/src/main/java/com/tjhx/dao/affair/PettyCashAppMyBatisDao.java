package com.tjhx.dao.affair;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.affair.PettyCashApp;

public interface PettyCashAppMyBatisDao {

	/**
	 * @param appPer
	 * @return
	 */
	public List<PettyCashApp> getPettyCashAppList(Map<String, Object> param);

	/**
	 * @param id
	 * @return
	 */
	public PettyCashApp findPettyCashAppByUuid(Integer id);

	/**
	 * 取得当前申请单据数量
	 * 
	 * @param curFormatDate
	 * @return
	 */
	public int getPettyCashAppCount(String appDate);

}
