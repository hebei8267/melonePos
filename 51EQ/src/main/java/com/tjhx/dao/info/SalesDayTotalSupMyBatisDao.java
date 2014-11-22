package com.tjhx.dao.info;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.info.SalesDayTotalSup;

public interface SalesDayTotalSupMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售信息(按供应商分类)
	 * 
	 * @param optDate 日期
	 */
	public void delSalesDayTotalInfo(String optDate);

	/**
	 * 取得合计实销金额（指定时间区间/机构）-按供应商
	 * 
	 * @param salesDayTotalSup
	 * @return
	 */
	public List<SalesDayTotalSup> getSumSaleRamtList(SalesDayTotalSup salesDayTotalSup);

	/**
	 * 取得合计实销数量（指定时间区间/机构）-按供应商
	 * 
	 * @param salesDayTotalSup
	 * @return
	 */
	public List<SalesDayTotalSup> getSumSaleRqtyList(SalesDayTotalSup salesDayTotalSup);

	/**
	 * 销售合计对比列表
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesDayTotalSup> getContrastList(Map<String, String> param);
}
