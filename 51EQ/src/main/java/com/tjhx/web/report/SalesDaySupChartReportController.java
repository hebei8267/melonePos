package com.tjhx.web.report;

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
import com.tjhx.entity.info.SalesDayTotalSup;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.info.SalesDayTotalSupManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesDaySupChartReport")
public class SalesDaySupChartReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesDayTotalSupManager salesDayTotalSupManager;

	@RequestMapping(value = "bar_init")
	public String salesDayChartReport1_Action(Model model) {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "report/salesDaySupChartReport_bar";
	}

	@RequestMapping(value = "bar_search")
	public String salesDayChartReportSearch1_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
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
	public String salesDayChartReportSearch2_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		String optDateStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("optDateShow_start", optDateStart);
		model.addAttribute("optDateShow_end", optDateEnd);

		String _startDate = DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd");
		String _endDate = DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd");

		List<String> _jsonStrList = new ArrayList<String>();
		List<String> _orgNameList = new ArrayList<String>();
		// 合计
		_jsonStrList.add(getSumSaleRamtJsonStr(_startDate, _endDate));
		_orgNameList.add("合计");

		List<Organization> _orgList = orgManager.getSubOrganization();
		for (Organization org : _orgList) {
			// 各门店
			_jsonStrList.add(getSumSaleRamtJsonStr(_startDate, _endDate, org.getId()));
			_orgNameList.add(org.getName());
		}

		model.addAttribute("saleRamtJsonList", _jsonStrList);
		model.addAttribute("orgNameList", _orgNameList);

		return "report/salesDaySupChartReport_pie";
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
}
