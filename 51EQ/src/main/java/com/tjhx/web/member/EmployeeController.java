package com.tjhx.web.member;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.member.Employee;
import com.tjhx.service.member.EmployeeManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController {

	@Resource
	private EmployeeManager empManager;
	@Resource
	private OrganizationManager orgManager;

	@RequestMapping(value = { "" })
	public String initEmployeeList_Action(Model model) {
		ReportUtils.initOrgList_All_Null(orgManager, model);

		return "member/employeeList";
	}

	@RequestMapping(value = "search")
	public String employeeListSearch_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);

		List<Employee> empList = empManager.getEmployeeByOrgId(orgId);
		model.addAttribute("empList", empList);

		ReportUtils.initOrgList_All_Null(orgManager, model);

		return "member/employeeList";
	}

	@RequestMapping(value = "zkDataSyn")
	public String zkDataSyn_Action(Model model) {
		ReportUtils.initOrgList_All_Null(orgManager, model);
		// 同步中控打卡数据库数据
		empManager.zkDataSyn();

		addInfoMsg(model, "TIP_MSG_EMPLOYEE_001");

		return "member/employeeList";
	}
}
