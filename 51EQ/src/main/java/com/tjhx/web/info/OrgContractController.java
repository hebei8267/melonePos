package com.tjhx.web.info;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.OrgContract;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.info.OrgContractManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/orgContract")
public class OrgContractController extends BaseController {
	@Resource
	private OrgContractManager orgContractManager;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 取得机构合同信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String initList_Action(Model model, HttpServletRequest request) {
		List<OrgContract> _list = orgContractManager.getOrgContractList();

		model.addAttribute("orgContractList", _list);

		return "info/orgContractList";
	}

	/**
	 * 新增机构合同信息初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initOrgContract_Action(Model model) {
		OrgContract _orgContract = new OrgContract();
		_orgContract.setComputePayAmt(null);
		model.addAttribute("orgContract", _orgContract);

		ReportUtils.initOrgList_Null_Root(orgManager, model);

		// 初始化缴交频率
		initPayFrequentList(model);

		return "info/orgContractForm";
	}

	/**
	 * 初始化缴交频率
	 * 
	 * @param model
	 */
	private void initPayFrequentList(Model model) {

		Map<String, String> _payFrequentList = new LinkedHashMap<String, String>();
		_payFrequentList.put("", "");
		_payFrequentList.put("1", "按季度");
		_payFrequentList.put("2", "按月份");

		model.addAttribute("payFrequentList", _payFrequentList);
	}

	/**
	 * 新增/修改 机构合同信息
	 * 
	 */
	@RequestMapping(value = "save")
	public String saveOrgContract_Action(@ModelAttribute("orgContract") OrgContract orgContract, Model model)
			throws IllegalAccessException, InvocationTargetException {
		orgContract.setStartDate(DateUtils.transDateFormat(orgContract.getStartDate(), "yyyy-MM-dd", "yyyyMMdd"));
		orgContract.setEndDate(DateUtils.transDateFormat(orgContract.getEndDate(), "yyyy-MM-dd", "yyyyMMdd"));

		if (null == orgContract.getUuid()) {// 新增操作
			try {
				orgContractManager.addOrgContract(orgContract);
			} catch (ServiceException ex) {
				orgContract.setStartDate(DateUtils.transDateFormat(orgContract.getStartDate(), "yyyyMMdd", "yyyy-MM-dd"));
				orgContract.setEndDate(DateUtils.transDateFormat(orgContract.getEndDate(), "yyyyMMdd", "yyyy-MM-dd"));

				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				ReportUtils.initOrgList_Null_Root(orgManager, model);

				// 初始化缴交频率
				initPayFrequentList(model);

				return "info/orgContractForm";
			}
		} else {// 修改操作
			try {
				orgContractManager.updateOrgContract(orgContract);
			} catch (ServiceException ex) {
				orgContract.setStartDate(DateUtils.transDateFormat(orgContract.getStartDate(), "yyyyMMdd", "yyyy-MM-dd"));
				orgContract.setEndDate(DateUtils.transDateFormat(orgContract.getEndDate(), "yyyyMMdd", "yyyy-MM-dd"));

				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				ReportUtils.initOrgList_Null_Root(orgManager, model);

				// 初始化缴交频率
				initPayFrequentList(model);

				return "info/orgContractForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/orgContract/init";
	}

	/**
	 * 编辑机构合同信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editOrgContract_Action(@PathVariable("id") Integer id, Model model) {

		OrgContract _orgContract = orgContractManager.getOrgContractByUuid(id);
		if (null == _orgContract) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/orgContract/init";
		} else {
			_orgContract.setStartDate(DateUtils.transDateFormat(_orgContract.getStartDate(), "yyyyMMdd", "yyyy-MM-dd"));
			_orgContract.setEndDate(DateUtils.transDateFormat(_orgContract.getEndDate(), "yyyyMMdd", "yyyy-MM-dd"));
			
			model.addAttribute("orgContract", _orgContract);

			ReportUtils.initOrgList_Null_Root(orgManager, model);

			// 初始化缴交频率
			initPayFrequentList(model);

			return "info/orgContractForm";
		}

	}

	/**
	 * 删除机构合同信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delOrgContract_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			orgContractManager.delOrgContractByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/orgContract/init";
	}

}
