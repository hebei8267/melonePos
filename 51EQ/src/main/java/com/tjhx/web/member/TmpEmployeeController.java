package com.tjhx.web.member;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.member.Employee;
import com.tjhx.globals.Constants;
import com.tjhx.service.member.EmployeeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/tmpEmployee")
public class TmpEmployeeController extends BaseController {
	@Resource
	private EmployeeManager empManager;

	@RequestMapping(value = { "list" })
	public String tmpEmployeeList_Action(Model model, HttpSession session) {
		List<Employee> empList = empManager.getTmpEmployeeByOrgId(getUserInfo(session).getOrganization().getId());

		model.addAttribute("empList", empList);

		return "member/tmpEmployeeList";
	}

	@RequestMapping(value = "save")
	public String saveTmpEmployeeList_Action(HttpServletRequest request, HttpSession session, Model model)
			throws ServletRequestBindingException {
		String empUuids[] = ServletRequestUtils.getStringParameters(request, "uuid");

		String names[] = new String[10];
		for (int i = 1; i < 11; i++) {
			names[i - 1] = ServletRequestUtils.getStringParameter(request, "name" + i);
		}

		String workFlgs[] = new String[10];
		for (int i = 1; i < 11; i++) {
			workFlgs[i - 1] = ServletRequestUtils.getStringParameter(request, "workFlg[" + i + "]");
		}

		List<Employee> empList = formatEmployeeObj(empUuids, names, workFlgs);

		empManager.updateTmpEmployeeList(getUserInfo(session).getOrganization().getId(), empList);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/tmpEmployee/list";
	}

	private List<Employee> formatEmployeeObj(String empUuids[], String names[], String workFlgs[]) {
		List<Employee> empList = new ArrayList<Employee>();

		for (int i = 0; i < empUuids.length; i++) {
			Employee emp = new Employee();

			emp.setUuid(Integer.valueOf(empUuids[i]));
			emp.setName(names[i]);
			emp.setWorkFlg(workFlgs[i]);

			empList.add(emp);
		}

		return empList;
	}

}
