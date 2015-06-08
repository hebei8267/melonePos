package com.tjhx.web.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.StoreDayTotal;
import com.tjhx.entity.info.StoreDayTotal_Show;
import com.tjhx.service.affair.StoreChartReportManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/storeChartReport")
public class StoreChartReportController extends BaseController {
	@Resource
	private StoreChartReportManager storeChartReportManager;
	@Resource
	private OrganizationManager orgManager;

	@RequestMapping(value = { "chart", "" })
	public String storeChartReport_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		List<StoreDayTotal> _dbStoreDayTotalList = storeChartReportManager.getStoreTotalListGroupByDay(null);
		model.addAttribute("data_set", formatStoreDayTotalInfo_chart(_dbStoreDayTotalList));

		return "report/storeChartReport";
	}

	@RequestMapping(value = { "search" })
	public String storeChartReport_search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);

		List<StoreDayTotal> _dbStoreDayTotalList = storeChartReportManager.getStoreTotalListGroupByDay(orgId);
		model.addAttribute("data_set", formatStoreDayTotalInfo_chart(_dbStoreDayTotalList));

		return "report/storeChartReport";
	}

	@RequestMapping(value = { "list" })
	public String storeListReport_Action(Model model) throws ServletRequestBindingException {
		String maxOptDate = storeChartReportManager.getMaxOptDate();

		if (StringUtils.isNotBlank(maxOptDate)) {
			model.addAttribute("maxOptDate", DateUtils.transDateFormat(maxOptDate, "yyyyMMdd", "yyyy-MM-dd"));
			List<StoreDayTotal> _dbStoreDayTotalList = storeChartReportManager.getStoreDayTotalList(maxOptDate);
			List<StoreDayTotal_Show> _list = formatStoreDayTotalInfo_list(_dbStoreDayTotalList);
			model.addAttribute("storeDayTotalList", _list);
			calTotal_StoreDayTotal(model, _list);
		}

		return "report/storeChartReport_dateList";
	}

	private List<StoreDayTotal_Show> formatStoreDayTotalInfo_list(List<StoreDayTotal> _dbStoreDayTotal) {
		List<StoreDayTotal_Show> _list = new ArrayList<StoreDayTotal_Show>();

		StoreDayTotal tmpStoreDayTotal = null;
		for (StoreDayTotal storeDayTotal : _dbStoreDayTotal) {

			if (null != tmpStoreDayTotal && tmpStoreDayTotal.myEquals(storeDayTotal)) {

				StoreDayTotal_Show _showObj = _list.get(_list.size() - 1);

				_showObj.copyStoreDayTotalInfo_list(storeDayTotal);

			} else {
				StoreDayTotal_Show _showObj = new StoreDayTotal_Show();
				_showObj.copyStoreDayTotalInfo_list(storeDayTotal);

				_list.add(_showObj);
			}

			tmpStoreDayTotal = storeDayTotal;
		}

		return _list;
	}

	private String formatStoreDayTotalInfo_chart(List<StoreDayTotal> _dbStoreDayTotal) {
		List<StoreDayTotal_Show> _list = new ArrayList<StoreDayTotal_Show>();

		StoreDayTotal tmpStoreDayTotal = null;
		for (StoreDayTotal storeDayTotal : _dbStoreDayTotal) {

			if (null != tmpStoreDayTotal && tmpStoreDayTotal.myEquals(storeDayTotal)) {

				StoreDayTotal_Show _showObj = _list.get(_list.size() - 1);

				_showObj.copyStoreDayTotalInfo_chart(storeDayTotal);

			} else {
				StoreDayTotal_Show _showObj = new StoreDayTotal_Show();
				_showObj.copyStoreDayTotalInfo_chart(storeDayTotal);

				_list.add(_showObj);
			}

			tmpStoreDayTotal = storeDayTotal;
		}

		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_list);
	}

	private void calTotal_StoreDayTotal(Model model, List<StoreDayTotal_Show> _list) {
		StoreDayTotal_Show _total = new StoreDayTotal_Show();
		_total.initAmt();
		for (StoreDayTotal_Show storeDayTotal_Show : _list) {

			// 库存数量
			if (null != storeDayTotal_Show.getStockTotalQty()) {
				_total.setStockTotalQty(_total.getStockTotalQty().add(storeDayTotal_Show.getStockTotalQty()));
			}

			// 库存金额
			if (null != storeDayTotal_Show.getStockTotalAmt()) {
				_total.setStockTotalAmt(_total.getStockTotalAmt().add(storeDayTotal_Show.getStockTotalAmt()));
			}
			// 售价金额
			if (null != storeDayTotal_Show.getItemSaleTotalAmt()) {
				_total.setItemSaleTotalAmt(_total.getItemSaleTotalAmt().add(storeDayTotal_Show.getItemSaleTotalAmt()));
			}

			// 负库存数量
			if (null != storeDayTotal_Show.getStockTotalQty_Minus()) {
				_total.setStockTotalQty_Minus(_total.getStockTotalQty_Minus().add(storeDayTotal_Show.getStockTotalQty_Minus()));
			}
			// 负库存金额
			if (null != storeDayTotal_Show.getStockTotalAmt_Minus()) {
				_total.setStockTotalAmt_Minus(_total.getStockTotalAmt_Minus().add(storeDayTotal_Show.getStockTotalAmt_Minus()));
			}
			// 负售价金额
			if (null != storeDayTotal_Show.getItemSaleTotalAmt_Minus()) {
				_total.setItemSaleTotalAmt_Minus(_total.getItemSaleTotalAmt_Minus().add(
						storeDayTotal_Show.getItemSaleTotalAmt_Minus()));
			}

		}

		model.addAttribute("totalStore", _total);
	}

}
