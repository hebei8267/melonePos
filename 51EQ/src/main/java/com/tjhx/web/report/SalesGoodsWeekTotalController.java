package com.tjhx.web.report;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.service.info.SalesDayTotalGoodsManager;
import com.tjhx.service.info.SalesWeekTotalGoodsManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesWeekTotal")
public class SalesGoodsWeekTotalController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SalesGoodsWeekTotalController.class);

	@Autowired
	private SalesDayTotalGoodsManager salesDayTotalGoodsManager;
	@Autowired
	private SalesWeekTotalGoodsManager salesWeekTotalGoodsManager;

	@RequestMapping(value = { "reCalInit" })
	public String reCalInit_Action(Model model) {
		return "report/reCalGoodsWeekTotal";
	}

	@RequestMapping(value = { "reCal" })
	public String reCal_Action(Model model) throws ServletRequestBindingException, ParseException {
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

		
		addInfoMsg(model, "TIP_MSG_RECAL_SALES_FOODS_WEEK_TOTAL_001");
		
		return "report/reCalGoodsWeekTotal";
	}
}
