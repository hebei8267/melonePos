/**
 * 
 */
package com.tjhx.web.order;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.order.ReplenishOrder;
import com.tjhx.service.order.ReplenishOrderManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

/**
 * 补货单
 */
@Controller
@RequestMapping(value = "/replenishOrder")
public class ReplenishOrderController extends BaseController {
	@Resource
	private ReplenishOrderManager replenishOrderManager;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 门店--补货单管理
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "list")
	public String list_Action(Model model) {

		return "order/replenishOrderList";
	}

	/**
	 * 门店--补货单编辑
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{orderNo}")
	public String edit_Action(@PathVariable("orderNo") String orderNo, Model model) {

		return "order/replenishOrderEdit";
	}

	/**
	 * 门店--补货单查看
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{orderNo}")
	public String view_Action(@PathVariable("orderNo") String orderNo, Model model) {

		return "order/replenishOrderView";
	}

	// ======================================================================
	// 总部
	// ======================================================================
	/**
	 * 补货单状态-下拉菜单
	 * 
	 * @param orgManager
	 * @param model
	 */
	private void initOrderState(Model model) {

		Map<String, String> stateList = new LinkedHashMap<String, String>();

		stateList.put("", "");
		stateList.put("01", "编辑中");
		stateList.put("02", "已发货");
		stateList.put("03", "收货中");
		stateList.put("99", "已完结");

		model.addAttribute("stateList", stateList);
	}

	/**
	 * 总部--补货单管理(查询)
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws ParseException
	 */
	@RequestMapping(value = "manageSearch")
	public String manageSearch_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);
		// 补货单状态-下拉菜单
		initOrderState(model);

		String orderNo = ServletRequestUtils.getStringParameter(request, "orderNo");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String orderState = ServletRequestUtils.getStringParameter(request, "orderState");
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("orgId", orgId);
		model.addAttribute("orderState", orderState);

		List<ReplenishOrder> list = replenishOrderManager.getReplenishOrderList(orderNo, orgId, orderState);
		model.addAttribute("replenishOrderList", list);

		return "order/replenishOrderManageList";
	}

	/**
	 * 总部--补货单管理
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "manageList")
	public String manageList_Action(Model model) {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);
		// 补货单状态-下拉菜单
		initOrderState(model);

		// 01-编辑中 02-已发货 03-收货中 99-已确认
		List<ReplenishOrder> list = replenishOrderManager.getReplenishOrderList(null, null, "02");
		model.addAttribute("replenishOrderList", list);

		return "order/replenishOrderManageList";
	}

	/**
	 * 总部--补货单编辑
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "manageEdit/{orderNo}")
	public String manageEdit_Action(@PathVariable("orderNo") String orderNo, Model model) {

		return "order/replenishOrderManageEdit";
	}

	/**
	 * 总部--补货单查看
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "manageView/{orderNo}")
	public String manageView_Action(@PathVariable("orderNo") String orderNo, Model model) {

		return "order/replenishOrderManageView";
	}
}
