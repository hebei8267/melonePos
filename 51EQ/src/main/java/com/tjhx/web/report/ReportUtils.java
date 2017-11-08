package com.tjhx.web.report;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
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
		List<Organization> _orgList = orgManager.getAllOpenOrganization();

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
		List<Organization> _orgList = orgManager.getAllOpenOrganization();

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
		List<Organization> _orgList = orgManager.getAllOpenOrganization();

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
	 * 取得所有机构信息-不含总部-全机构-含机构分组
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initOrgList_All_NonRoot_Group(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOpenOrganization();

		List<Organization> _eqOrgList = Lists.newArrayList();
		List<Organization> _infOrgList = Lists.newArrayList();
		for (Organization _org : _orgList) {
			if (Constants.ROOT_ORG_ID.equals(_org.getId())) {
				continue;
			}
			if ("EQ+".equals(_org.getBrand())) {
				_eqOrgList.add(_org);
			} else if ("Infancy".equals(_org.getBrand())) {
				_infOrgList.add(_org);
			}
		}

		Map<String, String> orgList = new LinkedHashMap<String, String>();
		orgList.put("", "全机构");

		orgList.put("EQ+", "EQ+");
		for (Organization _org : _eqOrgList) {
			orgList.put(_org.getId(), _org.getName());
		}

		orgList.put("Infancy", "Infancy");
		for (Organization _org : _infOrgList) {
			orgList.put(_org.getId(), _org.getName());
		}
		model.addAttribute("orgList", orgList);
	}

	/**
	 * SQL专用(引号和逗号分割)
	 * 
	 * @param orgManager
	 * @param orgBrand
	 * @return
	 */
	public static String getOrgIdByGroup(OrganizationManager orgManager, String orgBrand) {
		if (StringUtils.isBlank(orgBrand)) {
			return null;
		}
		List<Organization> _orgList = orgManager.getAllOpenOrganization();
		List<String> _orgIdList = Lists.newArrayList();
		for (Organization _org : _orgList) {

			if (orgBrand.equals(_org.getBrand())) {
				_orgIdList.add("'" + _org.getId() + "'");
			}
		}
		return Joiner.on(",").join(_orgIdList);
	}

	/**
	 * 取得所有机构信息-含总部-全机构
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initOrgList_All_Null(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOpenOrganization();

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
		List<Organization> _orgList = orgManager.getAllOpenOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();

		orgList.put("", "");
		for (Organization _org : _orgList) {
			if (!Constants.ROOT_ORG_ID.equals(_org.getId())) {

				orgList.put(_org.getId(), _org.getName());
			}
		}

		model.addAttribute("orgList", orgList);
	}

	/**
	 * 取得所有机构信息-不含总部-不含空白
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initOrgList_NoNRoot(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOpenOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();

		for (Organization _org : _orgList) {
			if (!Constants.ROOT_ORG_ID.equals(_org.getId())) {

				orgList.put(_org.getId(), _org.getName());
			}
		}

		model.addAttribute("orgList", orgList);
	}

	/**
	 * 取得所有机构信息(分品牌)-不含总部-不含空白
	 * 
	 * @param orgManager
	 * @param model
	 */
	public static void initBrandOrgList_NoNRoot(OrganizationManager orgManager, Model model) {
		List<Organization> _orgList = orgManager.getAllOpenOrganization();

		Map<String, String> eqOrgList = new LinkedHashMap<String, String>();
		Map<String, String> infOrgList = new LinkedHashMap<String, String>();
		Map<String, String> amOrgList = new LinkedHashMap<String, String>();

		for (Organization _org : _orgList) {
			if (!Constants.ROOT_ORG_ID.equals(_org.getId()) && !_org.isClosedFlg() && "EQ+".equals(_org.getBrand())) {
				eqOrgList.put(_org.getId(), _org.getName());
			} else if (!Constants.ROOT_ORG_ID.equals(_org.getId()) && !_org.isClosedFlg() && "Infancy".equals(_org.getBrand())) {
				infOrgList.put(_org.getId(), _org.getName());
			} else if (!Constants.ROOT_ORG_ID.equals(_org.getId()) && !_org.isClosedFlg() && "AmpleLife".equals(_org.getBrand())) {
				amOrgList.put(_org.getId(), _org.getName());
			}
		}

		model.addAttribute("eqOrgList", eqOrgList);
		model.addAttribute("infOrgList", infOrgList);
		model.addAttribute("amOrgList", amOrgList);
	}
}
