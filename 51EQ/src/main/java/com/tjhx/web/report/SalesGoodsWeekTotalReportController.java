package com.tjhx.web.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.entity.order.ReqBill;
import com.tjhx.service.info.SalesWeekTotalGoodsManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesWeekGoodsTotalReport")
public class SalesGoodsWeekTotalReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesWeekTotalGoodsManager salesWeekTotalGoodsManager;

	@RequestMapping(value = { "init" })
	public String init_Action(Model model) throws ServletRequestBindingException {

		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		return "report/salesWeekGoodsTotalReport";
	}

	@RequestMapping(value = { "search" })
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String barcode = ServletRequestUtils.getStringParameter(request, "barcode");
		model.addAttribute("orgId", orgId);
		model.addAttribute("barcode", barcode);

		List<ReqBill> salesWeekGoodsList = salesWeekTotalGoodsManager.getSalesWeekGoodsTotalList_ByOrg(orgId,barcode);
		model.addAttribute("salesWeekGoodsList", salesWeekGoodsList);
		return "report/salesWeekGoodsTotalReport";
	}

	@RequestMapping(value = "contrast/{barcode}")
	public String contrast_Action(@PathVariable("barcode") String barcode, Model model) {
		List<ReqBill> salesWeekGoodsList = salesWeekTotalGoodsManager.getSalesWeekGoodsTotalList_ByBarcode(barcode);
		model.addAttribute("salesWeekGoodsList", salesWeekGoodsList);

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("salesWeekGoodsListJson", mapper.toJson(salesWeekGoodsList));
		return "report/salesWeekGoodsTotalContrast";
	}
}
