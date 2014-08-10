package com.tjhx.web.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.FreightApply;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.FreightApplyManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/freight")
public class FreightApplyController extends BaseController {
	@Resource
	private FreightApplyManager freightApplyManager;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String freightApplyList_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_All_Null(orgManager, model);
		// 初始化调货单状态下拉列表
		initStatusList(model);

		return "affair/freightApplyList";
	}

	/**
	 * 初始化调货单状态下拉列表
	 * 
	 * @param model
	 */
	private void initStatusList(Model model) {
		Map<String, String> statusList = new LinkedHashMap<String, String>();

		statusList.put("", "");
		statusList.put("00", "申请");
		statusList.put("01", "已审批");
		statusList.put("02", "已送达");

		model.addAttribute("statusList", statusList);
	}

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String appOrgId = ServletRequestUtils.getStringParameter(request, "appOrgId");
		String targetOrgId = ServletRequestUtils.getStringParameter(request, "targetOrgId");
		String status = ServletRequestUtils.getStringParameter(request, "status");
		model.addAttribute("appOrgId", appOrgId);
		model.addAttribute("targetOrgId", targetOrgId);
		model.addAttribute("status", status);

		ReportUtils.initOrgList_All_Null(orgManager, model);
		// 初始化调货单状态下拉列表
		initStatusList(model);

		List<FreightApply> _list = freightApplyManager.getFreightApplyList(appOrgId, targetOrgId, status);
		model.addAttribute("freightAppList", _list);

		return "affair/freightApplyList";
	}

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "managerSearch")
	public String managerSearch_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String appOrgId = ServletRequestUtils.getStringParameter(request, "appOrgId");
		String targetOrgId = ServletRequestUtils.getStringParameter(request, "targetOrgId");
		String status = ServletRequestUtils.getStringParameter(request, "status");
		model.addAttribute("appOrgId", appOrgId);
		model.addAttribute("targetOrgId", targetOrgId);
		model.addAttribute("status", status);

		ReportUtils.initOrgList_All_Null(orgManager, model);
		// 初始化调货单状态下拉列表
		initStatusList(model);

		List<FreightApply> _list = freightApplyManager.getFreightApplyList_Manager(appOrgId, targetOrgId, status);
		model.addAttribute("freightAppList", _list);

		return "affair/freightApplyList";
	}

	/**
	 * 删除货运申请信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delFreightApp_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			freightApplyManager.delFreightApplyByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/freight/list";
	}

	/**
	 * 编辑/审批货运申请信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editFreightApp_Action(@PathVariable("id") Integer id, Model model) {
		FreightApply freightApp = freightApplyManager.getFreightApplyByUuid(id);
		if (null == freightApp) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/freight/list";
		} else {
			model.addAttribute("freightApp", freightApp);

			ReportUtils.initOrgList_Null_Root(orgManager, model);
			// 初始化调货单状态下拉列表
			initStatusList(model);

			return "affair/freightApplyForm";
		}

	}

	/**
	 * 新增货运申请信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initFreightApp_Action(Model model) {
		FreightApply freightApp = new FreightApply();
		freightApp.setBoxNum(null);
		freightApp.setBagNum(null);
		model.addAttribute("freightApp", freightApp);

		ReportUtils.initOrgList_Null_Root(orgManager, model);
		// 初始化调货单状态下拉列表
		initStatusList(model);

		return "affair/freightApplyForm";
	}

	/**
	 * 新增/修改 货运申请信息
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveFreightApp_Action(@ModelAttribute("freightApp") FreightApply freightApp, Model model)
			throws IllegalAccessException, InvocationTargetException {
		// 限时日期
		if (StringUtils.isNotBlank(freightApp.getLimitedDate())) {
			freightApp.setLimitedDate(DateUtils.transDateFormat(freightApp.getLimitedDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}

		if (null == freightApp.getUuid()) {// 新增操作
			try {
				// 申请日期
				if (StringUtils.isNotBlank(freightApp.getAppDate())) {
					freightApp.setAppDate(DateUtils.transDateFormat(freightApp.getAppDate(), "yyyy-MM-dd", "yyyyMMdd"));
				}

				freightApplyManager.addNewFreightApply(freightApp);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				return "affair/freightApplyForm";
			}
		} else {// 修改操作
			try {
				freightApplyManager.updateFreightApply(freightApp);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				return "affair/freightApplyForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/freight/list";
	}

	// ==========================================================================
	// 货运-运输
	// ==========================================================================
	@RequestMapping(value = "view")
	public String viewFreightApp_Action(Model model) {
		// 已申请（货运信息）数量
		int applyCount = freightApplyManager.getApplyCount();
		// 已审批（货运信息）数量
		int approvalCount = freightApplyManager.getApprovalCount();
		// 预收货（货运信息）数量
		int expReceiptCount = freightApplyManager.getExpReceiptCount();
		// 已打包（货运信息）数量
		int packedCount = freightApplyManager.getPackedCount();
		// 已收货（货运信息）数量
		int actReceiptCount = freightApplyManager.getActReceiptCount();
		// 预送货（货运信息）数量
		int expDeliveryCount = freightApplyManager.getExpDeliveryCount();
		// 已送货（货运信息）数量
		int actDeliveryDate = freightApplyManager.getActDeliveryDate();

		model.addAttribute("applyCount", applyCount);
		model.addAttribute("approvalCount", approvalCount);
		model.addAttribute("expReceiptCount", expReceiptCount);
		model.addAttribute("packedCount", packedCount);
		model.addAttribute("actReceiptCount", actReceiptCount);
		model.addAttribute("expDeliveryCount", expDeliveryCount);
		model.addAttribute("actDeliveryDate", actDeliveryDate);

		return "affair/freightApplyView";
	}

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewList")
	public String freightApplyViewList_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_All_Null(orgManager, model);
		// 初始化调货单状态下拉列表
		initStatusList(model);
		
		model.addAttribute("status", "01");

		return "affair/freightApplyViewList";
	}

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "viewManagerSearch")
	public String viewManagerSearch_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		managerSearch_Action(model, request);

		return "affair/freightApplyViewList";
	}

	/**
	 * 编辑/审批货运申请信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewEdit/{id}/{editFlg}")
	public String viewEditFreightApp_Action(@PathVariable("id") Integer id, @PathVariable("editFlg") Integer editFlg, Model model) {
		FreightApply freightApp = freightApplyManager.getFreightApplyByUuid(id);
		if (null == freightApp) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/freight/viewList";
		} else {
			freightApp.setEditFlg(editFlg);
			model.addAttribute("freightApp", freightApp);

			ReportUtils.initOrgList_Null_Root(orgManager, model);
			// 初始化调货单状态下拉列表
			initStatusList(model);

			return "affair/freightApplyViewForm";
		}

	}

	/**
	 * 修改 货运申请信息
	 * 
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "viewSave")
	public String viewSaveFreightApp_Action(@ModelAttribute("freightApp") FreightApply freightApp, Model model,
			HttpServletRequest request) throws ServletRequestBindingException {

		try {

			// 实际收货
			if (null != freightApp.getEditFlg() && freightApp.getEditFlg() == 2) {
				String actReceiptDateChkFlg = ServletRequestUtils.getStringParameter(request, "actReceiptDateChkFlg");
				if (null != actReceiptDateChkFlg && actReceiptDateChkFlg.equals("1")) {
					freightApp.setActReceiptDate(DateUtils.getCurFormatDate("yyyy-MM-dd HH:mm"));
				} else {
					freightApp.setActReceiptDate(null);
				}
			}

			// 实际送货
			if (null != freightApp.getEditFlg() && freightApp.getEditFlg() == 4) {
				String actDeliveryDateChkFlg = ServletRequestUtils.getStringParameter(request, "actDeliveryDateChkFlg");
				if (null != actDeliveryDateChkFlg && actDeliveryDateChkFlg.equals("1")) {
					freightApp.setActDeliveryDate(DateUtils.getCurFormatDate("yyyy-MM-dd HH:mm"));
				} else {
					freightApp.setActDeliveryDate(null);
				}
			}

			freightApplyManager.updateFreightApply_view(freightApp);
		} catch (ServiceException ex) {
			// 添加错误消息
			addInfoMsg(model, ex.getMessage());

			return "affair/freightApplyViewForm";
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/freight/viewList";
	}
}
