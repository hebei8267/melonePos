package com.tjhx.web.accounts;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.globals.Constants;
import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

/**
 * 销售反日结
 */
@Controller
@RequestMapping(value = "/cashCounterDaily")
public class CashCounterDailyController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private CashDailyManager cashDailyManager;

	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		return "accounts/cashCounterDaily";
	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);

		CashDaily cashDaily = cashDailyManager.getLastCashDailyInfoByOrg(orgId);
		model.addAttribute("cashDaily", cashDaily);

		return "accounts/cashCounterDaily";
	}

	/**
	 * 销售流水反日结
	 * 
	 * @param date
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "confirm")
	public String cashCounterDailyConfirm_Action(HttpServletRequest request, Model model)
			throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDate = ServletRequestUtils.getStringParameter(request, "optDate");

		cashDailyManager.counterDaily(orgId, optDate);

		// 反日结操作完成-提示信息
		addInfoMsg(model, "TIP_MSG_CASH_COUNTER_DAILY_001");

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cashCounterDaily/init";
	}
}
