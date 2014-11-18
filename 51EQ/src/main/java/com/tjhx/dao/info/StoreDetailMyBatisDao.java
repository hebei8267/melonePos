package com.tjhx.dao.info;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.info.StoreDetail;

public interface StoreDetailMyBatisDao {

	public List<StoreDetail> getDayTotalList(StoreDetail storeDetail);

	public void createTable();

	public void dropTable();

	/**
	 * 销售合计库存对比列表
	 */
	public List<StoreDetail> getContrastStoreList(Map<String, String> paramMap);
}
