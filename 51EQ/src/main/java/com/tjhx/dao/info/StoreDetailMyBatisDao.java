package com.tjhx.dao.info;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.info.StoreDetail;

public interface StoreDetailMyBatisDao {

	public List<StoreDetail> getDayTotalList(StoreDetail storeDetail);

	public void createTable();

	public void dropTable();

	void delStoreDetail(String optDate);

	/**
	 * 销售合计库存对比列表(商品类型)
	 */
	public List<StoreDetail> getItemContrastStoreList(Map<String, String> paramMap);

	/**
	 * 销售合计库存对比列表(货商)
	 * 
	 * @param param
	 * @return
	 */
	public List<StoreDetail> getSupplierContrastStoreList(Map<String, String> param);
	
	public List<StoreDetail> getStoreListGroupBySubno(Map<String, String> param);

	/**
	 * 删除库存明细信息,仅保留近90天数据
	 * 
	 * @param optDate
	 * @return
	 */
	public void delStoreDetail_GreaterThan90Day(String optDate);
}
