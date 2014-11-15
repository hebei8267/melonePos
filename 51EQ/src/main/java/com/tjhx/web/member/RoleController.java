package com.tjhx.web.member;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.member.Role;
import com.tjhx.globals.Constants;
import com.tjhx.service.member.FunctionManager;
import com.tjhx.service.member.RoleManager;
import com.tjhx.web.BaseController;

/**
 * 角色管理
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
	@Resource
	private RoleManager roleManager;
	@Resource
	private FunctionManager functionManager;

	/**
	 * 取得角色信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String roleList_Action(Model model) {
		List<Role> roleList = roleManager.getAllRole();

		model.addAttribute("roleList", roleList);

		return "member/roleList";
	}

	/**
	 * 编辑角色信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editRole_Action(@PathVariable("id") Integer id, Model model) {

		Role role = roleManager.getRoleByUuid(id);
		if (null == role) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/role/list";
		} else {
			role.initPermIdList(functionManager.getAllFunction());

			model.addAttribute("role", role);

			return "member/roleForm";
		}

	}

	/**
	 * 新增角色初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initRole_Action(Model model) {

		Role role = new Role();
		model.addAttribute("role", role);

		return "member/roleForm";
	}

	/**
	 * 新增/修改 角色信息
	 * 
	 * @param role
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveUser_Action(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		Role role = new Role();
		int[] perms = ServletRequestUtils.getIntParameters(request, "perm");
		BeanUtils.populate(role, request.getParameterMap());

		if (null == role.getUuid() || 0 == role.getUuid()) {
			role.setUuid(null);
			// 新增操作
			roleManager.addNewRole(role, perms);
		} else {// 修改操作
			roleManager.updateRole(role, perms);
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/role/list";
	}
}
