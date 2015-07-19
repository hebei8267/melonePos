package com.tjhx.web.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
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

	@RequestMapping(value = {"init"})
	public String init_Action(Model model) throws ServletRequestBindingException {

		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		model.addAttribute("orderMode", "qty");

		return "report/salesWeekGoodsTotalReport";
	}

	@RequestMapping(value = {"search"})
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String barcode = ServletRequestUtils.getStringParameter(request, "barcode");
		String orderMode = ServletRequestUtils.getStringParameter(request, "orderMode");
		model.addAttribute("orgId", orgId);
		model.addAttribute("barcode", barcode);
		model.addAttribute("orderMode", orderMode);

		List<ReqBill> salesWeekGoodsList = null;
		if (orderMode.equals("qty")) {// 按销量排序
			salesWeekGoodsList = salesWeekTotalGoodsManager.getSalesWeekGoodsTotalList_ByOrg_OrderQty(orgId, barcode);
		} else {// 按销售额排序
			salesWeekGoodsList = salesWeekTotalGoodsManager.getSalesWeekGoodsTotalList_ByOrg_OrderAmt(orgId, barcode);
		}
		model.addAttribute("salesWeekGoodsList", salesWeekGoodsList);
		return "report/salesWeekGoodsTotalReport";
	}

	@RequestMapping(value = "contrast/{barcode}")
	public String contrast_Action(@PathVariable("barcode") String barcode, Model model) {
		List<List<ReqBill>> list = Lists.newArrayList();
		String[] barcodes = barcode.split(",");

		if (ArrayUtils.isNotEmpty(barcodes)) {
			for (int i = 0; i < barcodes.length; i++) {
				List<ReqBill> salesWeekGoodsList = salesWeekTotalGoodsManager
						.getSalesWeekGoodsTotalList_ByBarcode(barcodes[i]);

				list.add(salesWeekGoodsList);
			}

		}

		model.addAttribute("list", list);

		return "report/salesWeekGoodsTotalContrast";
	}
}
