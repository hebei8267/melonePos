package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.SalesDayTotalGoods;

public interface SalesDayTotalGoodsMyBatisDao {
	/**
	 * 删除指定日期的所有门店销售信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesDayTotalInfo(String optDate);

	/**
	 * 取得各店指定时间区间内的销售信息（按商品）
	 * 
	 * @param salesDayTotalGoods
	 * @return
	 */
	public List<SalesDayTotalGoods> getSumSaleInfoList(SalesDayTotalGoods salesDayTotalGoods);

	/**
	 * 取得各店指定时间区间内(周)的销售信息（按商品）
	 * 
	 * @param salesDayTotalGoods
	 * @return
	 */
	public List<SalesDayTotalGoods> getSumSaleInfoList_Week(SalesDayTotalGoods salesDayTotalGoods);

	/**
	 * 取得指定店指定时间区间内销售信息排名(按类别)
	 * 
	 * @param salesDayTotalGoods
	 * @return
	 */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderQty(SalesDayTotalGoods salesDayTotalGoods);
}
