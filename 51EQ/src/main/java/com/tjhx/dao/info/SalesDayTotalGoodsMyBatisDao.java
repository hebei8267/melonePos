package com.tjhx.dao.info;

import java.util.List;
import java.util.Map;

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
	 * 取得指定店指定时间区间内销售信息排名(按类别)--按销量排序
	 * 
	 * @param salesDayTotalGoods
	 * @return
	 */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderQty(SalesDayTotalGoods salesDayTotalGoods);

	/**
	 * 取得指定店指定时间区间内销售信息排名(按类别)--按销售额排序
	 * 
	 * @param salesDayTotalGoods
	 * @return
	 */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderAmt(SalesDayTotalGoods salesDayTotalGoods);

	/**
	 * 取得指定店指定时间区间内销售信息排名(按类别)--按销售额排序（前30名）
	 * 
	 * @param salesDayTotalGoods
	 * @return
	 */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderAmt_Top_Item(SalesDayTotalGoods salesDayTotalGoods);

	/**
	 * 取得指定店指定时间区间内销售信息排名(按类别)--按销售额排序（后30名）
	 * 
	 * @param salesDayTotalGoods
	 * @return
	 */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderAmt_Down_Item(SalesDayTotalGoods salesDayTotalGoods);

	/** 取得合计销售金额 */
	public SalesDayTotalGoods getTotalPosAmt(Map<String, String> param);

	public List<SalesDayTotalGoods> getTotalPosAmtInfo(Map<String, String> param);

	/** 取得合计销售数量 */
	public SalesDayTotalGoods getTotalPosQty(Map<String, String> param);

	public List<SalesDayTotalGoods> getTotalPosQtyInfo(Map<String, String> param);

	/** 取得指定店指定时间区间内销售信息排名(按供应商)--按销售额排序（前30名） */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderAmt_Top_Supplier(SalesDayTotalGoods param);

	/** 取得指定店指定时间区间内销售信息排名(按供应商)--按销售额排序（后30名） */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderAmt_Down_Supplier(SalesDayTotalGoods param);

	/** 取得销售门店信息（根据条码、销售时间段） */
	public List<String> getSaleOrgInfo(Map<String, String> param);

	/** 取得库存门店信息（根据条码） */
	public List<String> getStoreOrgInfo(String itemSubno);
}
