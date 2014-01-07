package com.tjhx.web.report;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/dailyReport")
public class DailyReportController extends BaseController {
	@Resource
	private CashDailyManager cashDailyManager;

	@RequestMapping(value = { "list", "" })
	public String dailyReportList_Action(Model model) throws ServletRequestBindingException, ParseException {

		List<CashDaily> _cashDailyList = cashDailyManager.getCashDailyListByAllOrg(DateUtils.getNextDateFormatDate(
				DateUtils.getCurFormatDate("yyyy-MM-dd"), -1, "yyyy-MM-dd"));
		model.addAttribute("cashDailyList", _cashDailyList);
		
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		model.addAttribute("DEFAULT_RETAINED_AMT", sysConfig.getDefaultRetainedAmt());

		return "report/dailyListReport";
	}

	@RequestMapping(value = "search")
	public String dailyReportSearch_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException, ParseException {
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");
		model.addAttribute("optDateShow", optDateShow);

		List<CashDaily> _cashDailyList = cashDailyManager.getCashDailyListByAllOrg(optDateShow);
		model.addAttribute("cashDailyList", _cashDailyList);
		
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		model.addAttribute("DEFAULT_RETAINED_AMT", sysConfig.getDefaultRetainedAmt());

		return "report/dailyListReport";
	}
}
