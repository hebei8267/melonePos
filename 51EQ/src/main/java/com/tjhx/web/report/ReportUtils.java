package com.tjhx.web.report;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.struct.OrganizationManager;

public class ReportUtils {
	/**
	 * 取得所有机构信息-含总部-含空白
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initOrgList_Null_Root(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();
		orgList.put("", "");
		for (Organization _org : _orgList) {

			orgList.put(_org.getId(), _org.getName());

		}

		model.addAttribute("orgList", orgList);
	}

	/**
	 * 取得所有机构信息-含总部-不含空白
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initOrgList_NonNull_Root(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();

		for (Organization _org : _orgList) {

			orgList.put(_org.getId(), _org.getName());

		}

		model.addAttribute("orgList", orgList);
	}

	/**
	 * 取得所有机构信息-不含总部-全机构
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initOrgList_All_NonRoot(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();

		orgList.put("", "全机构");
		for (Organization _org : _orgList) {
			if (!Constants.ROOT_ORG_ID.equals(_org.getId())) {
				orgList.put(_org.getId(), _org.getName());
			}
		}

		model.addAttribute("orgList", orgList);
	}

	/**
	 * 取得所有机构信息-含总部-全机构
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initOrgList_All_Null(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();

		orgList.put("", "全机构");
		for (Organization _org : _orgList) {

			orgList.put(_org.getId(), _org.getName());

		}

		model.addAttribute("orgList", orgList);
	}

	/**
	 * 取得所有机构信息-不含总部-含空白
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initOrgList_Null_NoNRoot(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();

		orgList.put("", "");
		for (Organization _org : _orgList) {
			if (!Constants.ROOT_ORG_ID.equals(_org.getId())) {

				orgList.put(_org.getId(), _org.getName());
			}
		}

		model.addAttribute("orgList", orgList);
	}
}
