package com.tjhx.web.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.tjhx.entity.affair.Inspect;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.InspectManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/inspect")
public class InspectController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private InspectManager inspectManager;

	@RequestMapping(value = "search")
	public String inspectSearch_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {

		Inspect _inspect = new Inspect();
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		if (StringUtils.isNotBlank(orgId)) {
			_inspect.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(optDateShow_start)) {
			_inspect.setOptDateStart(DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (StringUtils.isNotBlank(optDateShow_end)) {
			_inspect.setOptDateEnd(DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"));
		}

		List<Inspect> _inspectList = inspectManager.searchInspectList(_inspect);
		model.addAttribute("inspectList", _inspectList);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "affair/inspectList";
	}

	/**
	 * 取得门店巡查报告信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String inspectList_Action(Model model) {
		Inspect _inspect = new Inspect();
		_inspect.setOptDateStart(DateUtils.getCurrentDateShortStr());
		_inspect.setOptDateEnd(DateUtils.getCurrentDateShortStr());

		List<Inspect> _inspectList = inspectManager.searchInspectList(_inspect);
		model.addAttribute("inspectList", _inspectList);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "affair/inspectList";
	}

	public void initJobTypeList(Model model) {
		Map<String, String> jobTypeList = new LinkedHashMap<String, String>();

		jobTypeList.put("", "");
		jobTypeList.put("1", "A 早班");
		jobTypeList.put("2", "B 晚班");

		model.addAttribute("jobTypeList", jobTypeList);
	}

	/**
	 * 初始化结论下拉列表
	 * 
	 * @param model
	 */
	public void initConclusionList(Model model) {

		Map<String, String> conclusionList = new LinkedHashMap<String, String>();

		conclusionList.put("", "");
		conclusionList.put("1", "完全相等");
		conclusionList.put("2", "基本相等");
		conclusionList.put("4", "不符，调查原因");

		model.addAttribute("conclusionList", conclusionList);
	}

	/**
	 * 新增门店巡查报告
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initInspect_Action(Model model) {
		Inspect inspect = new Inspect();
		inspect.setInitAmt(null);
		inspect.setCashAmt(null);
		inspect.setDepositAmt(null);
		inspect.setSaleCashAmt(null);
		inspect.setImprestCalance(null);
		inspect.setExpImprestAmt(null);
		inspect.setClearImprestAmt(null);

		model.addAttribute("inspect", inspect);

		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		initConclusionList(model);
		initJobTypeList(model);

		return "affair/inspectForm";
	}

	/**
	 * 编辑门店巡查报告
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editInspect_Action(@PathVariable("id") Integer id, Model model) {
		Inspect inspect = inspectManager.getInspectByUuid(id);
		if (null == inspect) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/inspect/list";
		} else {
			model.addAttribute("inspect", inspect);

			ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

			initConclusionList(model);
			initJobTypeList(model);

			return "affair/inspectForm";
		}

	}

	/**
	 * 删除门店巡查报告
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delInspect_Action(@RequestParam("uuids") String ids, Model model) {

		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			inspectManager.delInspectByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/inspect/list";
	}

	/**
	 * 新增/修改 门店巡查报告
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveInspect_Action(@ModelAttribute("inspect") Inspect inspect, Model model, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {

		if (null == inspect.getUuid()) {// 新增操作
			try {
				inspectManager.addNewInspect(inspect, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

				initConclusionList(model);
				initJobTypeList(model);

				return "affair/inspectForm";
			}
		} else {// 修改操作
			try {
				inspectManager.updateInspect(inspect, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

				initConclusionList(model);
				initJobTypeList(model);

				return "affair/inspectForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/inspect/list";
	}
}
