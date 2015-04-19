package com.tjhx.web.member;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.info.BudgetSubject;
import com.tjhx.entity.member.Role;
import com.tjhx.entity.member.User;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.info.BudgetSubjectManager;
import com.tjhx.service.member.RoleManager;
import com.tjhx.service.member.UserManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	@Resource
	private UserManager userManager;
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private RoleManager roleManager;
	@Resource
	private BudgetSubjectManager budgetSubjectManager;

	/**
	 * 取得User信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String userList_Action(Model model) {
		List<User> userList = userManager.getAllUser();

		model.addAttribute("userList", userList);

		return "member/userList";
	}

	/**
	 * 编辑User信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editUser_Action(@PathVariable("id") Integer id, Model model) {

		User user = userManager.getUserByUuid(id);
		if (null == user) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/user/list";
		} else {
			model.addAttribute("user", user);

			initOrgList(model);
			initRoleList(model);
			initBudgetSubjectList(model);

			return "member/userForm";
		}

	}

	/**
	 * 删除User信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delUser_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			userManager.delUserByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/user/list";
	}

	/**
	 * 新增User初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initUser_Action(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		initOrgList(model);
		initRoleList(model);
		initBudgetSubjectList(model);

		return "member/userForm";
	}

	private void initOrgList(Model model) {
		List<Organization> _orgList = orgManager.getAllOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();
		orgList.put("", "");
		for (Organization _org : _orgList) {
			orgList.put(_org.getUuid().toString(), _org.getName());
		}

		model.addAttribute("orgList", orgList);
	}

	private void initBudgetSubjectList(Model model) {

		// 取得一级科目（机构信息）
		List<BudgetSubject> _subList = budgetSubjectManager.findLevelOneBudgetSubjectList();
		Map<String, String> subList = new LinkedHashMap<String, String>();
		subList.put("", "");
		for (BudgetSubject _sub : _subList) {
			subList.put(_sub.getUuid().toString(), _sub.getSubName());
		}

		model.addAttribute("subList", subList);
	}

	private void initRoleList(Model model) {
		List<Role> _roleList = roleManager.getAllRole();

		Map<String, String> roleList = new LinkedHashMap<String, String>();
		roleList.put("", "");
		for (Role _role : _roleList) {
			roleList.put(_role.getUuid().toString(), _role.getName());
		}

		model.addAttribute("roleList", roleList);
	}

	/**
	 * 新增/修改 User信息
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveUser_Action(@ModelAttribute("user") User user, Model model) throws IllegalAccessException,
			InvocationTargetException {

		if (null == user.getUuid()) {
			// 新增操作
			try {
				userManager.addNewUser(user);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initOrgList(model);
				initRoleList(model);
				initBudgetSubjectList(model);
				return "member/userForm";
			}
		} else {// 修改操作
			try {
				userManager.updateUser(user);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initOrgList(model);
				initRoleList(model);
				initBudgetSubjectList(model);
				return "member/userForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/user/list";
	}
}