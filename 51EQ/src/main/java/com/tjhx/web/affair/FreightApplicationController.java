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
import com.tjhx.entity.affair.FreightApplication;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.FreightApplicationManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/freight")
public class FreightApplicationController extends BaseController {
	@Resource
	private FreightApplicationManager freightApplicationManager;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String freightApplicationList_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_All_Null(orgManager, model);
		// 初始化调货单状态下拉列表
		initStatusList(model);

		return "affair/freightApplicationList";
	}

	/**
	 * 初始化调货单状态下拉列表
	 * 
	 * @param model
	 */
	private void initStatusList(Model model) {
		Map<String, String> statusList = new LinkedHashMap<String, String>();

		statusList.put("", "全状态");
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

		List<FreightApplication> _list = freightApplicationManager.getAllFreightApplication(appOrgId, targetOrgId, status);
		model.addAttribute("freightAppList", _list);

		return "affair/freightApplicationList";
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

		List<FreightApplication> _list = freightApplicationManager.getFreightApplicationList_Manager(appOrgId, targetOrgId,
				status);
		model.addAttribute("freightAppList", _list);

		return "affair/freightApplicationList";
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
			freightApplicationManager.delFreightApplicationByUuid(Integer.parseInt(idArray[i]));
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
		FreightApplication freightApp = freightApplicationManager.getFreightApplicationByUuid(id);
		if (null == freightApp) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/freight/list";
		} else {
			model.addAttribute("freightApp", freightApp);

			ReportUtils.initOrgList_Null_Root(orgManager, model);
			// 初始化调货单状态下拉列表
			initStatusList(model);

			return "affair/freightApplicationForm";
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
		FreightApplication freightApp = new FreightApplication();
		freightApp.setPackNum(null);
		model.addAttribute("freightApp", freightApp);

		ReportUtils.initOrgList_Null_Root(orgManager, model);
		// 初始化调货单状态下拉列表
		initStatusList(model);

		return "affair/freightApplicationForm";
	}

	/**
	 * 新增/修改 货运申请信息
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveFreightApp_Action(@ModelAttribute("freightApp") FreightApplication freightApp, Model model)
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

				freightApplicationManager.addNewFreightApplication(freightApp);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				return "affair/freightApplicationForm";
			}
		} else {// 修改操作
			try {
				freightApplicationManager.updateFreightApplication(freightApp);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				return "affair/freightApplicationForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/freight/list";
	}
}
