package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.order.ReqBill;

public interface SalesWeekTotalGoodsMyBatisDao {

	/**
	 * 清除近1周销售合计情况
	 */
	public void delSalesWeekTotalGoodsInfo_1();

	/**
	 * 清除近2周销售合计情况
	 */
	public void delSalesWeekTotalGoodsInfo_2();

	/**
	 * 清除近3周销售合计情况
	 */
	public void delSalesWeekTotalGoodsInfo_3();

	/**
	 * 清除近4周销售合计情况
	 */
	public void delSalesWeekTotalGoodsInfo_4();

	/**
	 * 取得指定门店近四周销售数据及库存情况-按销量排序
	 * 
	 * @param orgId 门店编号
	 * @param barcode 货号/条码
	 * @return
	 */
	public List<ReqBill> getSalesWeekGoodsTotalList_ByOrg_OrderQty(ReqBill reqBill);

	/**
	 * 取得指定门店近四周销售数据及库存情况-按销售额排序
	 * 
	 * @param orgId 门店编号
	 * @param barcode 货号/条码
	 * @return
	 */
	public List<ReqBill> getSalesWeekGoodsTotalList_ByOrg_OrderAmt(ReqBill reqBill);

	/**
	 * 取得指定条码各门店近四周销售数据及库存情况
	 * 
	 * @param barcode 商品条码
	 * @return
	 */
	public List<ReqBill> getSalesWeekGoodsTotalList_ByBarcode(String barcode);
}
