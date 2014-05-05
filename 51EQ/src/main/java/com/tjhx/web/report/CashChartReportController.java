package com.tjhx.web.report;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cashChartReport")
public class CashChartReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private CashDailyManager cashDailyManager;

	@RequestMapping(value = { "list", "" })
	public String cashChartReportList_Action(Model model) throws ServletRequestBindingException {

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "report/cashChartReport";
	}

	@RequestMapping(value = "search")
	public String cashChartReportSearch_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException,
			ParseException {
		CashDaily _cashDaily = new CashDaily();
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");

		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow", optDateShow);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		if (StringUtils.isNotBlank(orgId)) {
			_cashDaily.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(optDateShow)) {
			// 日期-年
			_cashDaily.setOptDateY(DateUtils.transDateFormat(optDateShow, "yyyy-MM", "yyyy"));
			// 日期-月
			_cashDaily.setOptDateM(DateUtils.transDateFormat(optDateShow, "yyyy-MM", "MM"));
		}

		if (StringUtils.isNotBlank(orgId)) {// 单个机构
			List<CashDaily> _cashDailyList = cashDailyManager.searchChartReportListByOrg(_cashDaily);
			formatData(_cashDailyList);
			JsonMapper mapper = new JsonMapper();
			model.addAttribute("data_set", mapper.toJson(_cashDailyList));

			model.addAttribute("orgName", orgId.substring(3));

			model.addAttribute("total", calTotal(_cashDailyList));
		} else {// 所有机构
			List<CashDaily> _cashDailyList = cashDailyManager.searchChartReportList(_cashDaily);
			formatData(_cashDailyList);
			JsonMapper mapper = new JsonMapper();
			model.addAttribute("data_set", mapper.toJson(_cashDailyList));

			model.addAttribute("orgName", "合计");

			model.addAttribute("total", calTotal(_cashDailyList));

			// 取得所有子机构销售信息Json列表
			getAllSubOrgCashDailyList(model, optDateShow);
		}

		model.addAttribute("showFlg", true);

		return "report/cashChartReport";
	}

	/**
	 * 销售额合计计算
	 * 
	 * @param _cashDailyList
	 * @return
	 */
	private BigDecimal calTotal(List<CashDaily> _cashDailyList) {
		BigDecimal _res = new BigDecimal(0);
		for (CashDaily cashDaily : _cashDailyList) {
			_res = _res.add(cashDaily.getSaleAmt());
		}

		return _res;
	}

	/**
	 * 取得所有子机构销售信息Json列表
	 * 
	 * @param model
	 * @param optDateShow
	 * @return
	 * @throws ParseException
	 */
	private void getAllSubOrgCashDailyList(Model model, String optDateShow) throws ParseException {
		List<Organization> _orgList = orgManager.getSubOrganization();

		List<String> _allSubOrgCashDailyList = new ArrayList<String>();
		List<String> _orgNameList = new ArrayList<String>();
		List<BigDecimal> _totalList = new ArrayList<BigDecimal>();
		for (Organization org : _orgList) {
			CashDaily _cashDaily = new CashDaily();
			_cashDaily.setOrgId(org.getId());
			// 日期-年
			_cashDaily.setOptDateY(DateUtils.transDateFormat(optDateShow, "yyyy-MM", "yyyy"));
			// 日期-月
			_cashDaily.setOptDateM(DateUtils.transDateFormat(optDateShow, "yyyy-MM", "MM"));

			List<CashDaily> _cashDailyList = cashDailyManager.searchChartReportListByOrg(_cashDaily);
			formatData(_cashDailyList);
			JsonMapper mapper = new JsonMapper();
			_allSubOrgCashDailyList.add(mapper.toJson(_cashDailyList));
			_orgNameList.add(org.getName());

			_totalList.add(calTotal(_cashDailyList));
		}
		model.addAttribute("allSubOrgCashDailyList", _allSubOrgCashDailyList);
		model.addAttribute("orgNameList", _orgNameList);
		model.addAttribute("totalList", _totalList);
		model.addAttribute("subOrgShowFlg", true);
	}

	private void formatData(List<CashDaily> cashDailyList) throws ParseException {
		for (CashDaily cashDaily : cashDailyList) {
			cashDaily.setOptDate(cashDaily.getOptDate().substring(6) + " "
					+ DateUtils.getWeekOfDate(cashDaily.getOptDate(), "yyyyMMdd"));
		}
	}
}
