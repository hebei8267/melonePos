/**
 * 
 */
package com.tjhx.schedule;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.GoodsManager;
import com.tjhx.service.info.SalesDayTotalGoodsManager;
import com.tjhx.service.info.SalesDayTotalItemManager;
import com.tjhx.service.info.SalesDayTotalSupManager;
import com.tjhx.service.info.SalesMonthTotalItemManager;
import com.tjhx.service.info.SalesWeekTotalGoodsManager;
import com.tjhx.service.info.StoreDetailManager;
import com.tjhx.service.info.SupplierManager;

/**
 * @author he_bei
 * 
 */
public class SaleInfoJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(OrgStoreJob.class);
	@Autowired
	private StoreDetailManager storeDetailManager;
	@Autowired
	private GoodsManager goodsManager;
	@Autowired
	private SalesDayTotalItemManager salesDayTotalItemManager;
	@Autowired
	private SalesMonthTotalItemManager salesMonthTotalItemManager;
	@Autowired
	private SalesDayTotalSupManager salesDayTotalSupManager;
	@Autowired
	private SalesDayTotalGoodsManager salesDayTotalGoodsManager;
	@Autowired
	private SalesWeekTotalGoodsManager salesWeekTotalGoodsManager;
	@Resource
	private SupplierManager supplierManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tjhx.schedule.IJob#execute()
	 */
	@Override
	public void execute() throws Exception {
		// 同步百威数据库供应商信息
		logger.info("同步百威数据库供应商信息 Begin");
		supplierManager.bwDataSyn();
		logger.info("同步百威数据库供应商信息 End");

		logger.info("取得百威系统商品信息 Begin");
		// 取得百威系统各门店日销售信息
		goodsManager.bwDataSyn();
		logger.info("取得百威系统商品信息 End");

		logger.info("清理t_store_detail Begin");
		// 清理t_store_detail(数据量过大,其数据已汇总到t_store_day_total)
		// storeDetailManager.initTable();
		logger.info("清理t_store_detail End");
		// ##########################################################
		logger.info("取得门店库存信息 Begin");
		// 取得门店库存信息
		storeDetailManager.getOrgStoreDetail();
		logger.info("取得门店库存信息 End");

		// ##########################################################
		logger.info("计算门店库存合计信息 Begin");
		// 计算门店库存合计信息
		storeDetailManager.calOrgStoreDayTotal();
		logger.info("计算门店库存合计信息 End");

		logger.info("取得百威系统各门店日销售信息(按商品分类) Begin");
		// 取得百威系统各门店日销售信息
		salesDayTotalItemManager.getOrgSalesDayTotal();
		logger.info("取得百威系统各门店日销售信息(按商品分类) End");

		// #################################################
		logger.info("门店月销售计算(按商品分类) Begin");
		// 门店月销售计算
		salesMonthTotalItemManager.calOrgSalesMonthTotal();
		logger.info("门店月销售计算(按商品分类) End");

		logger.info("取得百威系统各门店日销售信息(按供应商) Begin");
		// 取得百威系统各门店日销售信息(按供应商)
		salesDayTotalSupManager.getOrgSalesDayTotal();
		logger.info("取得百威系统各门店日销售信息(按供应商) End");

		logger.info("取得百威系统各门店日销售信息(按商品) Begin");
		// 取得百威系统各门店日销售信息
		salesDayTotalGoodsManager.getOrgSalesDayTotal();
		logger.info("取得百威系统各门店日销售信息(按商品) End");

		// =================================================================
		// 以下方法依赖上面的数据
		// =================================================================

		logger.info("计算近一周销售商品合计 Begin");
		salesWeekTotalGoodsManager.calSalesWeekTotalGoodsInfo_1();
		logger.info("计算近一周销售商品合计 End");

		// -------------------------------------------
		logger.info("计算近两周销售商品合计 Begin");
		salesWeekTotalGoodsManager.calSalesWeekTotalGoodsInfo_2();
		logger.info("计算近两周销售商品合计 End");

		// -------------------------------------------
		logger.info("计算近三周销售商品合计 Begin");
		salesWeekTotalGoodsManager.calSalesWeekTotalGoodsInfo_3();
		logger.info("计算近三周销售商品合计 End");

		// -------------------------------------------
		logger.info("计算近四周销售商品合计 Begin");
		salesWeekTotalGoodsManager.calSalesWeekTotalGoodsInfo_4();
		logger.info("计算近四周销售商品合计 End");
	}

}
