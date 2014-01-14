package com.tjhx.web.accounts;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.service.accounts.CashRunManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

/**
 * 存款信息核对
 */
@Controller
@RequestMapping(value = "/bankCheck")
public class BankCheckController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private CashRunManager cashRunManager;

	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		return "accounts/bankCheck";
	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		CashRun _param = new CashRun();
		_param.setOptDateStart(DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"));
		_param.setOptDateEnd(DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"));
		_param.setOrgId(orgId);
		List<CashRun> _cashRunList = cashRunManager.getBankCheckList(_param);
		model.addAttribute("cashRunList", _cashRunList);

		return "accounts/bankCheck";
	}

	@RequestMapping(value = "audit")
	public String audit_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		int[] uuids = ServletRequestUtils.getIntParameters(request, "uuid");
		int[] bankCheckFlg = ServletRequestUtils.getIntParameters(request, "bankCheckFlg");

		// 存款信息审核
		cashRunManager.auditBankCheckFlg(uuids, bankCheckFlg);
		return search_Action(model, request);
	}
}
