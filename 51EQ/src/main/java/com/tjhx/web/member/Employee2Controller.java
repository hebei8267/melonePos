package com.tjhx.web.member;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.member.Employee2;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.member.Employee2Manager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/employee2")
public class Employee2Controller extends BaseController {
	@Resource
	private Employee2Manager employee2Manager;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 取得员工信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String employee2List_Action(Model model, HttpServletRequest request) {

		return "member/employee2List";
	}

	/**
	 * 取得员工信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String name = ServletRequestUtils.getStringParameter(request, "name");
		String idCardNo = ServletRequestUtils.getStringParameter(request, "idCardNo");
		String phone = ServletRequestUtils.getStringParameter(request, "phone");
		model.addAttribute("name", name);
		model.addAttribute("idCardNo", idCardNo);
		model.addAttribute("phone", phone);

		Employee2 param = new Employee2();
		if (StringUtils.isNotBlank(name)) {
			param.setName("%" + name + "%");
		}
		if (StringUtils.isNotBlank(idCardNo)) {
			param.setIdCardNo("%" + idCardNo + "%");
		}
		if (StringUtils.isNotBlank(phone)) {
			param.setPhone("%" + phone + "%");
		}
		List<Employee2> _list = employee2Manager.getAllEmployee2(param);
		model.addAttribute("employeeList", _list);

		return "member/employee2List";
	}
	@RequestMapping(value = "view/{id}")
	public String viewEmployee2_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
		editEmployee2_Action(id, model, request);
		
		return "member/employee2ViewForm";
	}
	/**
	 * 编辑Employee2信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editEmployee2_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		Employee2 employee2 = employee2Manager.getEmployee2ByUuid(id);
		if (null == employee2) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/employee2/list";
		} else {
			ReportUtils.initOrgList_Null_Root(orgManager, model);

			if (StringUtils.isNotBlank(employee2.getStartWorkTime())) {
				employee2.setStartWorkTime(DateUtils.transDateFormat(employee2.getStartWorkTime(), "yyyyMM", "yyyy-MM"));
			}
			if (StringUtils.isNotBlank(employee2.getEntryTime())) {
				employee2.setEntryTime(DateUtils.transDateFormat(employee2.getEntryTime(), "yyyyMMdd", "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(employee2.getPosTime())) {
				employee2.setPosTime(DateUtils.transDateFormat(employee2.getPosTime(), "yyyyMMdd", "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(employee2.getContractExpTime())) {
				employee2.setContractExpTime(DateUtils.transDateFormat(employee2.getContractExpTime(), "yyyyMMdd", "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(employee2.getRenewTime())) {
				employee2.setRenewTime(DateUtils.transDateFormat(employee2.getRenewTime(), "yyyyMMdd", "yyyy-MM-dd"));
			}

			model.addAttribute("employee2", employee2);
			return "member/employee2Form";
		}

	}

	/**
	 * 删除Employee2信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delEmployee2_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			employee2Manager.delEmployee2ByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/employee2/list";
	}

	/**
	 * 新增Employee2初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initEmployee2_Action(Model model, HttpServletRequest request) {

		Employee2 employee2 = new Employee2();
		model.addAttribute("employee2", employee2);

		ReportUtils.initOrgList_Null_Root(orgManager, model);

		return "member/employee2Form";
	}

	/**
	 * 新增/修改 Employee2信息
	 * 
	 * @param employee2
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "save")
	public String saveEmployee2_Action(@ModelAttribute("employee2") Employee2 employee2,
			@RequestParam("photoFile") MultipartFile photoFile, Model model, HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, IllegalStateException, IOException {

		employee2.setImgFile(photoFile);

		if (StringUtils.isNotBlank(employee2.getStartWorkTime())) {
			employee2.setStartWorkTime(DateUtils.transDateFormat(employee2.getStartWorkTime(), "yyyy-MM", "yyyyMM"));
		}
		if (StringUtils.isNotBlank(employee2.getEntryTime())) {
			employee2.setEntryTime(DateUtils.transDateFormat(employee2.getEntryTime(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (StringUtils.isNotBlank(employee2.getPosTime())) {
			employee2.setPosTime(DateUtils.transDateFormat(employee2.getPosTime(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (StringUtils.isNotBlank(employee2.getContractExpTime())) {
			employee2.setContractExpTime(DateUtils.transDateFormat(employee2.getContractExpTime(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (StringUtils.isNotBlank(employee2.getRenewTime())) {
			employee2.setRenewTime(DateUtils.transDateFormat(employee2.getRenewTime(), "yyyy-MM-dd", "yyyyMMdd"));
		}

		if (null == employee2.getUuid()) {// 新增操作
			employee2Manager.addNewEmployee2(employee2);
		} else {// 修改操作
			try {
				employee2Manager.updateEmployee2(employee2);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/employee2/list";
	}
}