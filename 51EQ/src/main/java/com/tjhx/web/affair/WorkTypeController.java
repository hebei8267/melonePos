package com.tjhx.web.affair;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.affair.WorkType;
import com.tjhx.globals.Constants;
import com.tjhx.service.affair.WorkTypeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/workType")
public class WorkTypeController extends BaseController {
	@Resource
	private WorkTypeManager workTypeManager;

	@RequestMapping(value = { "list" })
	public String workTypeList_Action(Model model, HttpSession session) {
		List<WorkType> workTypeList = workTypeManager
				.getWorkTypeByOrgId(getUserInfo(session).getOrganization().getId());

		formatWorkTypeList(workTypeList);

		model.addAttribute("workTypeList", workTypeList);

		initDateList(model);

		return "affair/workTypeList";
	}

	private void formatWorkTypeList(List<WorkType> workTypeList) {
		for (WorkType workType : workTypeList) {
			if (StringUtils.isNotBlank(workType.getStartDate())) {
				workType.setStartDateHr(workType.getStartDate().substring(0, 2));
				workType.setStartDateMinute(workType.getStartDate().substring(2, 4));
			}

			if (StringUtils.isNotBlank(workType.getEndDate())) {
				workType.setEndDateHr(workType.getEndDate().substring(0, 2));
				workType.setEndDateMinute(workType.getEndDate().substring(2, 4));
			}
		}
	}

	private void initDateList(Model model) {
		Map<String, String> hrList = new LinkedHashMap<String, String>();

		hrList.put("", "");
		for (int i = 0; i < 24; i++) {
			hrList.put(String.format("%02d", i), String.format("%02d", i));
		}
		model.addAttribute("hrList", hrList);

		Map<String, String> minuteList = new LinkedHashMap<String, String>();
		minuteList.put("", "");
		minuteList.put(String.format("%02d", 0), String.format("%02d", 0));
		minuteList.put(String.format("%02d", 15), String.format("%02d", 15));
		minuteList.put(String.format("%02d", 30), String.format("%02d", 30));
		minuteList.put(String.format("%02d", 45), String.format("%02d", 45));

		model.addAttribute("minuteList", minuteList);
	}

	@RequestMapping(value = "save")
	public String saveWorkTypeList_Action(HttpServletRequest request, HttpSession session, Model model)
			throws ServletRequestBindingException {
		String workTypeUuids[] = ServletRequestUtils.getStringParameters(request, "uuid");

		String names[] = new String[8];
		for (int i = 1; i < 9; i++) {
			names[i - 1] = ServletRequestUtils.getStringParameter(request, "name" + i);
		}
		String useFlgs[] = new String[8];
		for (int i = 1; i < 9; i++) {
			useFlgs[i - 1] = ServletRequestUtils.getStringParameter(request, "useFlg" + i);
		}
		String startDate[] = new String[8];
		for (int i = 1; i < 9; i++) {
			startDate[i - 1] = ServletRequestUtils.getStringParameter(request, "startDate" + i);
		}
		String endDate[] = new String[8];
		for (int i = 1; i < 9; i++) {
			endDate[i - 1] = ServletRequestUtils.getStringParameter(request, "endDate" + i);
		}

		List<WorkType> workTypeList = formatWorkTypeObj(workTypeUuids, names, useFlgs, startDate, endDate);

		workTypeManager.updateWorkTypeList(getUserInfo(session).getOrganization().getId(), workTypeList);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/workType/list";
	}

	private List<WorkType> formatWorkTypeObj(String workTypeUuids[], String names[], String useFlgs[],
			String startDate[], String endDate[]) {
		List<WorkType> workTypeList = new ArrayList<WorkType>();

		for (int i = 0; i < workTypeUuids.length; i++) {
			WorkType wt = new WorkType();

			wt.setUuid(Integer.valueOf(workTypeUuids[i]));
			wt.setName(names[i]);
			wt.setUseFlg(useFlgs[i]);
			wt.setStartDate(startDate[i]);
			wt.setEndDate(endDate[i]);

			wt.set_startDate(formatDate(startDate[i]));
			wt.set_endDate(formatDate(endDate[i]));
			wt.setWorkDate(wt.get_startDate() + " - " + wt.get_endDate());

			workTypeList.add(wt);
		}

		return workTypeList;
	}

	private String formatDate(String date) {
		if (StringUtils.isNotBlank(date)) {
			String dateHr = date.substring(0, 2);
			String dateMin = date.substring(2, 4);
			return String.format(String.format("%02d:%02d", Integer.valueOf(dateHr), Integer.valueOf(dateMin)));
		} else {
			return "";
		}

	}
}
