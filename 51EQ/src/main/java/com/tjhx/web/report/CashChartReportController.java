package com.tjhx.web.report;

import java.text.ParseException;
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

		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		return "report/cashChartReport";
	}

	@RequestMapping(value = "search")
	public String cashChartReportSearch_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException, ParseException {
		CashDaily _cashDaily = new CashDaily();
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");

		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow", optDateShow);

		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		if (StringUtils.isNotBlank(orgId)) {
			_cashDaily.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(optDateShow)) {
			// 日期-年
			_cashDaily.setOptDateY(DateUtils.transDateFormat(optDateShow, "yyyy-MM", "yyyy"));
			// 日期-月
			_cashDaily.setOptDateM(DateUtils.transDateFormat(optDateShow, "yyyy-MM", "MM"));
		}

		List<CashDaily> _cashDailyList = cashDailyManager.searchChartReportList(_cashDaily);

		formatData(_cashDailyList);

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("data_set", mapper.toJson(_cashDailyList));
		model.addAttribute("showFlg", true);

		return "report/cashChartReport";
	}

	private void formatData(List<CashDaily> cashDailyList) throws ParseException {
		for (CashDaily cashDaily : cashDailyList) {
			cashDaily.setOptDate(cashDaily.getOptDate().substring(6) + " "
					+ DateUtils.getWeekOfDate(cashDaily.getOptDate(), "yyyyMMdd"));
		}
	}
}
