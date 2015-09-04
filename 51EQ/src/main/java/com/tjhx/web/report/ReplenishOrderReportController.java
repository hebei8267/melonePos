package com.tjhx.web.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.entity.order.ReplenishOrder;
import com.tjhx.service.order.ReplenishOrderManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
/**
 * 补货单分析
 */
@Controller
@RequestMapping(value = "/replenishOrderReport")
public class ReplenishOrderReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private ReplenishOrderManager replenishOrderManager;

	@RequestMapping(value = {""})
	public String replenishOrderReport_Action(Model model) throws ServletRequestBindingException {

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "report/replenishOrderReport";
	}

	@RequestMapping(value = {"search"})
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		List<ReplenishOrder> odList = replenishOrderManager.getReplenishOrderReport(orgId);

		for (ReplenishOrder rOrder : odList) {
			getOrgReplenishOrderRanking(rOrder);
		}

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("orgId", orgId);
		model.addAttribute("odList", mapper.toJson(odList));

		return "report/replenishOrderReport";
	}

	private List<ReplenishOrder> getOrgReplenishOrderRanking(ReplenishOrder rOrder) {
		List<ReplenishOrder> odList = replenishOrderManager.getOrgReplenishOrderRanking(rOrder.getReceiveDateYM());

		rOrder.setOrgReplenishOrderList(odList);

		return odList;
	}
}
