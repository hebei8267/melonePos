package com.tjhx.web.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
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

		Map<String, List<ReplenishOrder>> odMap = replenishOrderManager.getReplenishOrderReport();

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("odMap", mapper.toJson(odMap));

		return "report/replenishOrderReport";
	}
}
