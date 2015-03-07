/**
 * 
 */
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

import com.tjhx.common.utils.DateUtils;
import com.tjhx.service.info.SalesOrdersDayTotalManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.OrgSalesQuota;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesOrdersDayTotalContrast")
public class SalesOrdersDayTotalContrastController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesOrdersDayTotalManager salesOrdersDayTotalManager;

	@RequestMapping(value = "init")
	public String init_Action(Model model) {
		// 初始化页面下拉菜单控件
		initPageControls(model);

		return "report/salesOrdersDayTotalContrast";
	}

	/**
	 * 初始化页面下拉菜单控件
	 * 
	 * @param model
	 */
	private void initPageControls(Model model) {
		// 初始化机构下拉菜单
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException, ParseException {
		String optDate2_start = ServletRequestUtils.getStringParameter(request, "optDate2_start");
		String optDate2_end = ServletRequestUtils.getStringParameter(request, "optDate2_end");
		String optDate1_end = ServletRequestUtils.getStringParameter(request, "optDate1_end");
		String optDate1_start = ServletRequestUtils.getStringParameter(request, "optDate1_start");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String orderMode = ServletRequestUtils.getStringParameter(request, "orderMode");

		model.addAttribute("optDate2_start", optDate2_start);
		model.addAttribute("optDate2_end", optDate2_end);
		model.addAttribute("optDate1_end", optDate1_end);
		model.addAttribute("optDate1_start", optDate1_start);
		model.addAttribute("orgId", orgId);
		model.addAttribute("orderMode", orderMode);

		// 初始化页面下拉菜单控件
		initPageControls(model);

		List<OrgSalesQuota> _quotaList = salesOrdersDayTotalManager.getContrastList(
				DateUtils.transDateFormat(optDate1_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate1_end, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_end, "yyyy-MM-dd", "yyyyMMdd"), orgId, orderMode);

		model.addAttribute("quotaList", _quotaList);

		return "report/salesOrdersDayTotalContrast";
	}
}
