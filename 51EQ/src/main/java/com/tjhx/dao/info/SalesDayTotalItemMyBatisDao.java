package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.SalesDayTotalItem;

public interface SalesDayTotalItemMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesDayTotalInfo(String optDate);

	/**
	 * 取得合计实销金额（指定时间区间/机构）
	 * 
	 * @param salesDayTotal
	 * @return
	 */
	public List<SalesDayTotalItem> getSumSaleRamtList(SalesDayTotalItem salesDayTotal);

	/**
	 * 取得合计实销数量（指定时间区间/机构）
	 * 
	 * @param salesDayTotal
	 * @return
	 */
	public List<SalesDayTotalItem> getSumSaleRqtyList(SalesDayTotalItem salesDayTotal);

	/**
	 * 取得合计实销数量（指定年/月/机构）
	 * 
	 * @param salesDayTotal
	 * @return
	 */
	public List<SalesDayTotalItem> getSumSalesMonthTotalList(SalesDayTotalItem salesDayTotal);

	/**
	 * 取得合计信息（金额/数量/均价）（指定时间区间/机构）
	 * 
	 * @param salesDayTotal
	 * @return
	 */
	public List<SalesDayTotalItem> getSumSaleInfoList(SalesDayTotalItem salesDayTotal);
}
