package com.tjhx.web.struct;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.member.User;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.member.UserManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private UserManager userManager;

	@RequestMapping(value = { "list", "" })
	public String orgList_Action(Model model, HttpSession session) throws ParseException {
		List<Organization> orgList = orgManager.getAllOrganization();

		model.addAttribute("orgList", orgList);

		return "struct/orgList";
	}

	/**
	 * 初始化督导员列表
	 * 
	 * @param model
	 */
	private void initMngPerList(Model model) {
		// 取得督导员信息列表
		List<User> mngList = userManager.getMngUser();

		Map<String, String> _mngUserList = new LinkedHashMap<String, String>();
		_mngUserList.put("", "");

		for (User user : mngList) {
			_mngUserList.put(user.getLoginName(), user.getName());
		}
		model.addAttribute("mngUserList", _mngUserList);
	}

	private void initBrandList(Model model) {

		Map<String, String> _brandList = new LinkedHashMap<String, String>();
		_brandList.put("", "");
		_brandList.put("EQ+", "EQ+");
		_brandList.put("Infancy", "Infancy");

		model.addAttribute("brandList", _brandList);
	}

	/**
	 * 新增Organization初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initOrganization_Action(Model model) {

		Organization org = new Organization();
		model.addAttribute("org", org);

		Organization rootOrg = orgManager.getOrganizationByUuid(1);
		model.addAttribute("rootOrg", rootOrg);

		initBrandList(model);
		// 初始化督导员列表
		initMngPerList(model);

		return "struct/orgForm";
	}

	/**
	 * 删除Organization信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delOrganization_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			orgManager.delOrganizationByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/organization/list";
	}

	/**
	 * 编辑Organization信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editOrganization_Action(@PathVariable("id") Integer id, Model model) {

		Organization org = orgManager.getOrganizationByUuid(id);
		if (null == org) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/organization/list";
		} else {
			model.addAttribute("org", org);

			if (org.getUuid() != 1) {// 非顶层机构
				Organization rootOrg = orgManager.getOrganizationByUuid(1);
				model.addAttribute("rootOrg", rootOrg);
			}

			initBrandList(model);
			// 初始化督导员列表
			initMngPerList(model);

			return "struct/orgForm";
		}

	}

	/**
	 * 新增/修改 Organization信息
	 * 
	 * @param organization
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveOrganization_Action(@ModelAttribute("org") Organization org, Model model)
			throws IllegalAccessException, InvocationTargetException {

		if (null == org.getUuid()) {// 新增操作
			try {
				orgManager.addNewOrganization(org);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initBrandList(model);
				// 初始化督导员列表
				initMngPerList(model);

				return "struct/orgForm";
			}
		} else {// 修改操作
			try {
				orgManager.updateOrganization(org);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/organization/list";
	}
}
