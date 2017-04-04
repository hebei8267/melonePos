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

import com.google.common.collect.Lists;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.StoreDayTotal;
import com.tjhx.entity.info.StoreDayTotal_Show;
import com.tjhx.entity.struct.Organization;
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

		List<List<StoreDayTotal>> _dbStoreDayTotalList = storeChartReportManager.getStoreTotalListGroupByDay(null);
		model.addAttribute("dataList", formatStoreDayTotalInfo_chart(_dbStoreDayTotalList));

		// 取得机构名称
		getOrgNameList(model, null);

		return "report/storeChartReport";
	}

	/**
	 * 取得机构名称
	 * 
	 * @param model
	 * @param orgId
	 */
	private void getOrgNameList(Model model, String orgId) {
		List<String> _nameList = Lists.newLinkedList();

		if (StringUtils.isBlank(orgId)) {// 全机构
			List<Organization> orgList = orgManager.getOpenSubOrganization();

			_nameList.add("全机构");

			for (Organization org : orgList) {
				_nameList.add(org.getName());
			}
		} else {
			_nameList.add(orgId);
		}

		model.addAttribute("orgNameList", _nameList);
	}

	@RequestMapping(value = { "search" })
	public String storeChartReport_search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);

		List<List<StoreDayTotal>> _dbStoreDayTotalList = storeChartReportManager.getStoreTotalListGroupByDay(orgId);
		model.addAttribute("dataList", formatStoreDayTotalInfo_chart(_dbStoreDayTotalList));

		// 取得机构名称
		getOrgNameList(model, orgId);

		return "report/storeChartReport";
	}

	@RequestMapping(value = { "list" })
	public String storeListReport_Action(Model model) throws ServletRequestBindingException {
		String maxOptDate = storeChartReportManager.getMaxOptDate();
		List<Organization> orgList = orgManager.getOpenSubOrganization();

		if (StringUtils.isNotBlank(maxOptDate)) {
			model.addAttribute("maxOptDate", DateUtils.transDateFormat(maxOptDate, "yyyyMMdd", "yyyy-MM-dd"));
			List<StoreDayTotal> _dbStoreDayTotalList = storeChartReportManager.getStoreDayTotalList(maxOptDate);

			List<StoreDayTotal> _storeDayTotalList_EQ = Lists.newArrayList();
			List<StoreDayTotal> _storeDayTotalList_Infancy = Lists.newArrayList();

			for (StoreDayTotal _storeDayTotal : _dbStoreDayTotalList) {
				String _brand = getOrgBrand(orgList, _storeDayTotal.getOrgId());

				if ("EQ+".equals(_brand)) {
					_storeDayTotalList_EQ.add(_storeDayTotal);
				} else if ("Infancy".equals(_brand)) {
					_storeDayTotalList_Infancy.add(_storeDayTotal);
				}
			}

			List<StoreDayTotal_Show> _list = Lists.newArrayList();
			List<StoreDayTotal_Show> _list_tmp = formatStoreDayTotalInfo_list(_dbStoreDayTotalList);
			_list.add(calTotal_StoreDayTotal(_list_tmp, "合计"));

			List<StoreDayTotal_Show> _list_EQ = formatStoreDayTotalInfo_list(_storeDayTotalList_EQ);
			_list.add(calTotal_StoreDayTotal(_list_EQ, "EQ+"));
			_list.addAll(_list_EQ);

			List<StoreDayTotal_Show> _list_In = formatStoreDayTotalInfo_list(_storeDayTotalList_Infancy);
			_list.add(calTotal_StoreDayTotal(_list_In, "Infancy"));
			_list.addAll(_list_In);

			model.addAttribute("storeDayTotalList", _list);

		}

		return "report/storeChartReport_dateList";
	}

	private String getOrgBrand(List<Organization> orgList, String orgId) {
		for (Organization org : orgList) {
			if (org.getId().equals(orgId)) {
				return org.getBrand();
			}
		}
		return null;
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

	private List<String> formatStoreDayTotalInfo_chart(List<List<StoreDayTotal>> _dbStoreDayTotalList) {
		List<String> _relist = Lists.newArrayList();
		for (List<StoreDayTotal> _dbStoreDayTotal : _dbStoreDayTotalList) {
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
			_relist.add(mapper.toJson(_list));
		}
		return _relist;
	}

	private StoreDayTotal_Show calTotal_StoreDayTotal(List<StoreDayTotal_Show> _list, String orgName) {
		StoreDayTotal_Show _total = new StoreDayTotal_Show();

		_total.initAmt();
		_total.setOrgName(orgName);

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
				_total.setItemSaleTotalAmt_Minus(_total.getItemSaleTotalAmt_Minus().add(storeDayTotal_Show.getItemSaleTotalAmt_Minus()));
			}

		}
		return _total;

	}

}
