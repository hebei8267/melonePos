package com.tjhx.web.info;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.OrgContractPayRun;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.info.OrgContractPayRunManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/orgContractPayRun")
public class OrgContractPayRunController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private OrgContractPayRunManager orgContractPayRunManager;

	/**
	 * 新增机构合同缴费信息初始化
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "new")
	public String initOrgContractPayRun_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		OrgContractPayRun _orgContractPayRun = new OrgContractPayRun();
		_orgContractPayRun.setPayAmt(null);
		model.addAttribute("orgContractPayRun", _orgContractPayRun);

		ReportUtils.initOrgList_Null_Root(orgManager, model);

		String selectOrgId = ServletRequestUtils.getStringParameter(request, "selectOrgId");
		model.addAttribute("selectOrgId", selectOrgId);

		return "info/orgContractPayRunForm";
	}

	/**
	 * 新增/修改 机构合同缴费信息
	 * 
	 * @throws ServletRequestBindingException
	 * 
	 */
	@RequestMapping(value = "save")
	public String saveOrgContractPayRun_Action(@ModelAttribute("orgContractPayRun") OrgContractPayRun orgContractPayRun, Model model,
			HttpServletRequest request) throws ServletRequestBindingException {
		orgContractPayRun.setPayDate(DateUtils.transDateFormat(orgContractPayRun.getPayDate(), "yyyy-MM-dd", "yyyyMMdd"));

		String selectOrgId = ServletRequestUtils.getStringParameter(request, "selectOrgId");

		if (null == orgContractPayRun.getUuid()) {// 新增操作

			orgContractPayRunManager.addOrgContractPayRun(orgContractPayRun);

		} else {// 修改操作
			try {
				orgContractPayRunManager.updateOrgContractPayRun(orgContractPayRun);
			} catch (ServiceException ex) {
				orgContractPayRun.setPayDate(DateUtils.transDateFormat(orgContractPayRun.getPayDate(), "yyyyMMdd", "yyyy-MM-dd"));

				ReportUtils.initOrgList_Null_Root(orgManager, model);

				model.addAttribute("selectOrgId", selectOrgId);

				return "info/orgContractPayRunForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/orgContract/init?selectOrgId=" + selectOrgId;
	}

	/**
	 * 编辑机构合同缴费信息
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "edit/{id}")
	public String editOrgContractPayRun_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		OrgContractPayRun _orgContractPayRun = orgContractPayRunManager.getOrgContractPayRunByUuid(id);

		if (null == _orgContractPayRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/orgContract/init";
		} else {
			_orgContractPayRun.setPayDate(DateUtils.transDateFormat(_orgContractPayRun.getPayDate(), "yyyyMMdd", "yyyy-MM-dd"));

			model.addAttribute("orgContractPayRun", _orgContractPayRun);

			ReportUtils.initOrgList_Null_Root(orgManager, model);

			model.addAttribute("selectOrgId", _orgContractPayRun.getOrgId());
		}
		return "info/orgContractPayRunForm";
	}

	/**
	 * 删除机构合同缴费信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "del")
	public String delOrgContractPayRun_Action(@RequestParam("uuids2") String ids, Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			orgContractPayRunManager.delOrgContractPayRunByUuid(Integer.parseInt(idArray[i]));
		}

		String selectOrgId = ServletRequestUtils.getStringParameter(request, "selectOrgId");
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/orgContract/init/?selectOrgId=" + selectOrgId;
	}
}
