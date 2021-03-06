package com.tjhx.web.report;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.SalesDayTotalGoods;
import com.tjhx.entity.info.SalesDayTotalSup;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.info.SalesDayTotalGoodsManager;
import com.tjhx.service.info.SalesDayTotalSupManager;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesDaySupChartReport")
public class SalesDaySupChartReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesDayTotalSupManager salesDayTotalSupManager;
	@Resource
	private SalesDayTotalGoodsManager salesDayTotalGoodsManager;
	@Resource
	private SupplierManager supplierManager;

	@RequestMapping(value = "bar_init")
	public String salesDayChartReport1_Action(Model model) {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "report/salesDaySupChartReport_bar";
	}

	@RequestMapping(value = "bar_search")
	public String salesDayChartReportSearch1_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String optDateStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow_start", optDateStart);
		model.addAttribute("optDateShow_end", optDateEnd);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		SalesDayTotalSup param = new SalesDayTotalSup();
		param.setOrgId(orgId);
		param.setOptDateStart(DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd"));
		param.setOptDateEnd(DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd"));

		// 取得合计实销金额（指定时间区间/机构）-按供应商
		List<SalesDayTotalSup> _sumSaleRamtList = salesDayTotalSupManager.getSumSaleRamtList(param);
		// 取得合计实销数量（指定时间区间/机构）-按供应商
		List<SalesDayTotalSup> _sumSaleRqtyList = salesDayTotalSupManager.getSumSaleRqtyList(param);

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("sumSaleRamtList", mapper.toJson(_sumSaleRamtList));
		model.addAttribute("sumSaleRqtyList", mapper.toJson(_sumSaleRqtyList));

		model.addAttribute("showFlg", true);

		return "report/salesDaySupChartReport_bar";
	}

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	@RequestMapping(value = "pie_init")
	public String salesDayChartReport2_Action(Model model) {
		return "report/salesDaySupChartReport_pie";
	}

	@RequestMapping(value = "pie_search")
	public String salesDayChartReportSearch2_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String optDateStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("optDateShow_start", optDateStart);
		model.addAttribute("optDateShow_end", optDateEnd);

		String _startDate = DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd");
		String _endDate = DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd");

		List<String> _saleRamtJsonList = new ArrayList<String>();
		List<String> _saleRqtyJsonList = new ArrayList<String>();
		List<String> _orgNameList = new ArrayList<String>();
		List<String> _orgIdList = new ArrayList<String>();
		// 合计
		_saleRamtJsonList.add(getSumSaleRamtJsonStr(_startDate, _endDate));
		_saleRqtyJsonList.add(getSumSaleRqtyJsonStr(_startDate, _endDate));
		_orgNameList.add("合计");
		_orgIdList.add(Constants.ROOT_ORG_ID);

		List<Organization> _orgList = orgManager.getOpenSubOrganization();
		for (Organization org : _orgList) {
			// 各门店
			_saleRamtJsonList.add(getSumSaleRamtJsonStr(_startDate, _endDate, org.getId()));
			_saleRqtyJsonList.add(getSumSaleRqtyJsonStr(_startDate, _endDate, org.getId()));
			_orgNameList.add(org.getName());
			_orgIdList.add(org.getId());
		}

		model.addAttribute("saleRamtJsonList", _saleRamtJsonList);
		model.addAttribute("saleRqtyJsonList", _saleRqtyJsonList);
		model.addAttribute("orgNameList", _orgNameList);
		model.addAttribute("orgIdList", _orgIdList);

		return "report/salesDaySupChartReport_pie";
	}

	/**
	 * 供应商销售信息排名（按机构）
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "pie_detail_list")
	public String salesDayChartReportDetailList_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String optDateStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		if (null != orgId && orgId.length() != 6) {
			orgId = null;
		}

		String _startDate = DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd");
		String _endDate = DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd");

		List<SalesDayTotalSup> _sumSaleRamtList = getSumSaleRamtList(_startDate, _endDate, orgId);

		model.addAttribute("optDateStart", optDateStart);
		model.addAttribute("optDateEnd", optDateEnd);
		model.addAttribute("orgId", orgId);
		model.addAttribute("orgName", orgId == null ? null : orgId.substring(3));
		model.addAttribute("sumSaleRamtList", _sumSaleRamtList);

		return "report/salesDaySupChartReport_pie_detail_list";
	}

	/**
	 * 取得指定机构和时间区间的销售合计信息(按销售额排序)
	 * 
	 * @param startDate
	 * @param endDate
	 * @param orgId
	 * @return
	 */
	private List<SalesDayTotalSup> getSumSaleRamtList(String startDate, String endDate, String orgId) {
		SalesDayTotalSup param = new SalesDayTotalSup();
		param.setOptDateStart(startDate);
		param.setOptDateEnd(endDate);
		param.setOrgId(orgId);

		// 取得合计实销金额（指定时间区间/机构）
		List<SalesDayTotalSup> _sumSaleRamtList = salesDayTotalSupManager.getSumSaleRamtList(param);
		return _sumSaleRamtList;
	}

	private String getSumSaleRamtJsonStr(String startDate, String endDate) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int num = sysConfig.getSalesDaySupTotalShowNum();

		SalesDayTotalSup param = new SalesDayTotalSup();
		param.setOptDateStart(startDate);
		param.setOptDateEnd(endDate);
		// 取得合计实销金额（指定时间区间/机构）-按供应商）
		List<SalesDayTotalSup> _sumSaleRamtList = salesDayTotalSupManager.getSumSaleRamtList(param);
		if (null != _sumSaleRamtList && _sumSaleRamtList.size() > num) {
			_sumSaleRamtList = _sumSaleRamtList.subList(0, num);
		}
		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_sumSaleRamtList);
	}

	private String getSumSaleRqtyJsonStr(String startDate, String endDate) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int num = sysConfig.getSalesDaySupTotalShowNum();

		SalesDayTotalSup param = new SalesDayTotalSup();
		param.setOptDateStart(startDate);
		param.setOptDateEnd(endDate);
		// 取得合计实销金额（指定时间区间/机构）-按供应商）
		List<SalesDayTotalSup> _sumSaleRqtyList = salesDayTotalSupManager.getSumSaleRqtyList(param);
		if (null != _sumSaleRqtyList && _sumSaleRqtyList.size() > num) {
			_sumSaleRqtyList = _sumSaleRqtyList.subList(0, num);
		}
		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_sumSaleRqtyList);
	}

	private String getSumSaleRamtJsonStr(String startDate, String endDate, String orgId) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int num = sysConfig.getSalesDaySupTotalShowNum();

		SalesDayTotalSup param = new SalesDayTotalSup();
		param.setOptDateStart(startDate);
		param.setOptDateEnd(endDate);
		param.setOrgId(orgId);
		// 取得合计实销金额（指定时间区间/机构）-按供应商
		List<SalesDayTotalSup> _sumSaleRamtList = salesDayTotalSupManager.getSumSaleRamtList(param);
		if (null != _sumSaleRamtList && _sumSaleRamtList.size() > num) {
			_sumSaleRamtList = _sumSaleRamtList.subList(0, num);
		}
		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_sumSaleRamtList);
	}

	private String getSumSaleRqtyJsonStr(String startDate, String endDate, String orgId) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int num = sysConfig.getSalesDaySupTotalShowNum();

		SalesDayTotalSup param = new SalesDayTotalSup();
		param.setOptDateStart(startDate);
		param.setOptDateEnd(endDate);
		param.setOrgId(orgId);
		// 取得合计实销金额（指定时间区间/机构）-按供应商
		List<SalesDayTotalSup> _sumSaleRqtyList = salesDayTotalSupManager.getSumSaleRqtyList(param);
		if (null != _sumSaleRqtyList && _sumSaleRqtyList.size() > num) {
			_sumSaleRqtyList = _sumSaleRqtyList.subList(0, num);
		}
		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_sumSaleRqtyList);
	}

	/**
	 * 某机构某供应商商品销售排名一览
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws ParseException
	 */
	@RequestMapping(value = "org_goods_list")
	public String orgGoodsList_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException, ParseException {
		String optDateStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String supplierBwId = ServletRequestUtils.getStringParameter(request, "supplierBwId");
		if (null != orgId && orgId.length() != 6) {
			orgId = null;
		}

		String _startDate = DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd");
		String _endDate = DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd");

		List<SalesDayTotalGoods> _topList = salesDayTotalGoodsManager.getSalesItemRankInfoList_OrderAmt_Top_Supplier(_startDate, _endDate,
				orgId, supplierBwId);
		List<SalesDayTotalGoods> _downList = salesDayTotalGoodsManager.getSalesItemRankInfoList_OrderAmt_Down_Supplier(_startDate,
				_endDate, orgId, supplierBwId);
		Supplier supplier = supplierManager.getSupplierByBwId(supplierBwId);

		model.addAttribute("supplier", supplier);
		model.addAttribute("optDateStart", optDateStart);
		model.addAttribute("optDateEnd", optDateEnd);
		model.addAttribute("orgId", orgId);
		model.addAttribute("orgName", orgId == null ? null : orgId.substring(3));

		model.addAttribute("topList", _topList);
		model.addAttribute("downList", _downList);

		return "report/salesDaySupChartReport_goods_list";
	}
}
