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
		String optDate_start = ServletRequestUtils.getStringParameter(request, "optDate_start");
		String optDate_end = ServletRequestUtils.getStringParameter(request, "optDate_end");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String orderMode = ServletRequestUtils.getStringParameter(request, "orderMode");

		model.addAttribute("optDate_start", optDate_start);
		model.addAttribute("optDate_end", optDate_end);
		model.addAttribute("orgId", orgId);
		model.addAttribute("orderMode", orderMode);

		// 初始化页面下拉菜单控件
		initPageControls(model);

		String currentStartOptDate = DateUtils.transDateFormat(optDate_start, "yyyy-MM-dd", "yyyyMMdd");
		String currentEndOptDate = DateUtils.transDateFormat(optDate_end, "yyyy-MM-dd", "yyyyMMdd");
		Long _span = DateUtils.getDateSpanDay(currentStartOptDate, currentEndOptDate);
		String preEndOptDate = DateUtils.getNextDateFormatDate(currentStartOptDate, -1, "yyyyMMdd");
		String preStartOptDate = DateUtils.getNextDateFormatDate(preEndOptDate, (int) -_span, "yyyyMMdd");

		List<OrgSalesQuota> _quotaList = salesOrdersDayTotalManager.getContrastList(currentStartOptDate, currentEndOptDate,
				preStartOptDate, preEndOptDate, orgId, orderMode);

		model.addAttribute("quotaList", _quotaList);

		return "report/salesOrdersDayTotalContrast";
	}
}
