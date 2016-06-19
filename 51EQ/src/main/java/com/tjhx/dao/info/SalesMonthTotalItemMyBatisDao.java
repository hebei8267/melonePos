package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.SalesMonthTotalItem;

public interface SalesMonthTotalItemMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesMonthTotalInfo(String optYMDate);

	/**
	 * 取得指定门店销售信息（按月份区分）
	 * 
	 * @param orgId 门店编号
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalMonthList(String orgId);

	/**
	 * 取得指定门店指定年份合计销售信息
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalList_ByOrgAndYear(SalesMonthTotalItem param);

	/**
	 * 取得指定门店指定年份合计销售信息
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalList_ByOrgAndYear_SJ(SalesMonthTotalItem param);

	/**
	 * 取得全部门店指定年份合计销售信息
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalList_ByYear(SalesMonthTotalItem param);

	/**
	 * 取得指定门店/月份合计销售信息
	 * 
	 * @param param
	 * @return
	 */
	public SalesMonthTotalItem getSalesTotal_ByOrgAndYearMonth(SalesMonthTotalItem param);

	/**
	 * 取得指定门店/月份合计销售信息(区间)
	 * 
	 * @param param
	 * @return
	 */
	public SalesMonthTotalItem getSalesTotal_ByOrgAndYearMonthInterval(SalesMonthTotalItem param);

	/**
	 * 月销售信息对比(图形)[按品牌]－不含13D
	 */
	public List<SalesMonthTotalItem> getSalesTotalMonthListByBrand();

	/**
	 * 月销售信息对比(图形)[按品牌]－含13D
	 */
	public List<SalesMonthTotalItem> getSalesTotalMonthListByBrand_13D();

	/**
	 * 月销售信息对比(图形)[按督导]
	 */
	public List<SalesMonthTotalItem> getSalesTotalMonthListByMngUser();
}
