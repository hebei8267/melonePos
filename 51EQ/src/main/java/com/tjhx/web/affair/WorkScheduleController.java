package com.tjhx.web.affair;

import java.text.ParseException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.entity.affair.WorkSchedule_List_Show;
import com.tjhx.entity.affair.WorkType;
import com.tjhx.entity.member.Employee;
import com.tjhx.globals.Constants;
import com.tjhx.service.affair.WorkScheduleManager;
import com.tjhx.service.affair.WorkTypeManager;
import com.tjhx.service.member.EmployeeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/workSchedule")
public class WorkScheduleController extends BaseController {
	@Resource
	private EmployeeManager empManager;
	@Resource
	private WorkScheduleManager workScheduleManager;
	@Resource
	private WorkTypeManager workTypeManager;

	/**
	 * @param model
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list" })
	public String workScheduleList_Action(Model model, HttpSession session) throws ParseException {
		String orgId = getUserInfo(session).getOrganization().getId();

		List<Employee> _empList = empManager.getEmployeeListByOrgId(orgId);
		List<Employee> _tmpEmpList = empManager.getTmpEmployeeByOrgId_WorkFlg(orgId);
		// 取得本机构正式员工和兼职员工
		_empList.addAll(_tmpEmpList);
		model.addAttribute("empList", _empList);

		List<WorkSchedule_List_Show> wsList = workScheduleManager.getWorkScheduleList(orgId, _empList);
		model.addAttribute("wsList", wsList);

		initWorkTypeList(orgId, model);

		return "affair/workScheduleList";
	}

	/**
	 * 取得指定机构的上班类型信息列表(开启状态)
	 * 
	 * @param orgId 机构编号
	 */
	private void initWorkTypeList(String orgId, Model model) {
		List<WorkType> _wtList = workTypeManager.getValidWorkTypeByOrgId(orgId);
		if (null == _wtList || _wtList.size() == 0) {
			return;
		}

		Map<String, String> wtList = new LinkedHashMap<String, String>();

		wtList.put("", "");
		for (WorkType wt : _wtList) {
			wtList.put(wt.getUuid().toString(), wt.getName());
		}
		model.addAttribute("wtList", wtList);

		Map<String, String> _wt_data = new LinkedHashMap<String, String>();
		for (WorkType wt : _wtList) {
			if (StringUtils.isNotBlank(wt.getStartDate())) {
				_wt_data.put(wt.getUuid().toString(), wt.getWorkDate());
			}
		}
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("_wt_data_set", mapper.toJson(_wt_data));

	}

	/**
	 * 保存/更新 排班信息
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "save")
	public String saveWorkScheduleList_Action(HttpServletRequest request, HttpSession session, Model model)
			throws ServletRequestBindingException {
		String scheduleDate[] = ServletRequestUtils.getStringParameters(request, "scheduleDate");
		String empCode[] = ServletRequestUtils.getStringParameters(request, "empCode");
		String scheduleTimeSelect[] = ServletRequestUtils.getStringParameters(request, "scheduleTimeSelect");
		String wtDataString = ServletRequestUtils.getStringParameter(request, "_wt_data_set");

		Map<String, String> wtDataMap = null;
		if (StringUtils.isNotBlank(wtDataString)) {
			JsonMapper mapper = new JsonMapper();
			wtDataMap = mapper.fromJson(wtDataString, HashMap.class);
		}

		workScheduleManager.updateWorkScheduleList(scheduleDate, empCode, scheduleTimeSelect, wtDataMap,
				getUserInfo(session).getOrganization().getBwId());

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/workSchedule/list";
	}

	// --------------------------------------------历史排班表---------------------------------------------------

	@RequestMapping(value = "historyList/{date}")
	public String workScheduleList_Date_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {

		String orgId = getUserInfo(session).getOrganization().getId();

		List<Employee> _empList = empManager.getEmployeeListByOrgId(orgId);
		List<Employee> _tmpEmpList = empManager.getTmpEmployeeByOrgId_WorkFlg(orgId);
		// 取得本机构正式员工和兼职员工
		_empList.addAll(_tmpEmpList);
		model.addAttribute("empList", _empList);

		List<WorkSchedule_List_Show> wsList = workScheduleManager.getWorkScheduleList(orgId, _empList, date);
		model.addAttribute("wsList", wsList);

		return "affair/workScheduleHistoryList";
	}

}
