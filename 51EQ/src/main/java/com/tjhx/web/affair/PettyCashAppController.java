/**
 * 
 */
package com.tjhx.web.affair;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.PettyCashApp;
import com.tjhx.entity.info.BudgetSubject;
import com.tjhx.globals.Constants;
import com.tjhx.service.affair.PettyCashAppManager;
import com.tjhx.service.info.BudgetSubjectManager;
import com.tjhx.web.BaseController;

/**
 * 备用金申请/审批
 */
@Controller
@RequestMapping(value = "/pettyCashApp")
public class PettyCashAppController extends BaseController {
	@Resource
	private PettyCashAppManager pettyCashAppManager;
	@Resource
	private BudgetSubjectManager budgetSubjectManager;

	/**
	 * 备用金申请/审批--列表页面初期化
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "list" })
	public String pettyCashApp_list_Action(Model model, HttpSession session) {

		return "affair/pettyCashAppList";
	}

	/**
	 * 删除门店巡查报告
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delPettyCashApp_Action(@RequestParam("uuids") String ids, Model model) {

		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			pettyCashAppManager.delPettyCashAppByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCashApp/list";
	}

	/**
	 * 备用金申请/审批--查询列表
	 * 
	 * @param model
	 * @param session
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = { "search" })
	public String pettyCashApp_search_Action(Model model, HttpServletRequest request, HttpSession session)
			throws ServletRequestBindingException {
		boolean managerFlg = false;
		int roleUuid = getUserInfo(session).getRole().getUuid();
		// 1-系统管理员 2-总部人员-会计 5-总部人员-Boss
		if (1 == roleUuid || 2 == roleUuid || 5 == roleUuid) {
			managerFlg = true;
		}
		if (2 == roleUuid) {// 1审--总部人员
			model.addAttribute("appFlg1", true);
		}
		// TODO appFlg2
		if (5 == roleUuid) {// 3审--总部人员-Boss
			model.addAttribute("appFlg3", true);
		}
		// TODO fileFlg

		String appNo = ServletRequestUtils.getStringParameter(request, "appNo");
		String optDateShowStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShowEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String appPerName = ServletRequestUtils.getStringParameter(request, "appPerName");
		model.addAttribute("appNo", appNo);
		model.addAttribute("optDateShow_start", optDateShowStart);
		model.addAttribute("optDateShow_end", optDateShowEnd);
		model.addAttribute("appPerName", appPerName);
		model.addAttribute("managerFlg", managerFlg);

		List<PettyCashApp> appList = pettyCashAppManager.getPettyCashAppList(appNo,
				DateUtils.transDateFormat(optDateShowStart, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDateShowEnd, "yyyy-MM-dd", "yyyyMMdd"), appPerName, getUserInfo(session).getUuid(),
				managerFlg);

		model.addAttribute("appList", appList);

		return "affair/pettyCashAppList";
	}

	/**
	 * 备用金申请/审批--新增（申请）
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "new")
	public String pettyCashApp_new_Action(Model model, HttpSession session) throws ParseException {
		PettyCashApp pettyCashApp = new PettyCashApp();

		pettyCashApp.setAppDate(DateUtils.getCurFormatDate("yyyy-MM-dd"));

		model.addAttribute("pettyCashApp", pettyCashApp);

		return "affair/pettyCashAppForm";
	}

	/**
	 * 备用金申请/审批--编辑/审批
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "edit/{id}")
	public String pettyCashApp_edit_Action(@PathVariable("id") Integer id, Model model, HttpSession session)
			throws ParseException {
		PettyCashApp pettyCashApp = pettyCashAppManager.getPettyCashAppByUuid(id);

		// 申请时间
		pettyCashApp.setAppDate(DateUtils.transDateFormat(pettyCashApp.getAppDate(), "yyyyMMdd", "yyyy-MM-dd"));
		// 付款期限
		pettyCashApp.setPaymentPeriod(DateUtils.transDateFormat(pettyCashApp.getPaymentPeriod(), "yyyyMMdd", "yyyy-MM-dd"));

		model.addAttribute("pettyCashApp", pettyCashApp);

		return "affair/pettyCashAppForm";
	}

	/**
	 * 备用金申请/审批--查看
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "view/{id}")
	public String pettyCashApp_view_Action(@PathVariable("id") Integer id, Model model, HttpSession session)
			throws ParseException {
		PettyCashApp pettyCashApp = pettyCashAppManager.getPettyCashAppByUuid(id);

		// 申请时间
		pettyCashApp.setAppDate(DateUtils.transDateFormat(pettyCashApp.getAppDate(), "yyyyMMdd", "yyyy-MM-dd"));
		// 付款期限
		pettyCashApp.setPaymentPeriod(DateUtils.transDateFormat(pettyCashApp.getPaymentPeriod(), "yyyyMMdd", "yyyy-MM-dd"));

		model.addAttribute("pettyCashApp", pettyCashApp);

		return "affair/pettyCashAppView";
	}

	/**
	 * 备用金申请/审批--保存
	 * 
	 * @param pettyCashApp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save")
	public String pettyCashApp_save_Action(@ModelAttribute("pettyCashApp") PettyCashApp pettyCashApp, Model model) {

		pettyCashAppManager.savePettyCashApp(pettyCashApp, false);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCashApp/list";
	}

	/**
	 * 备用金申请/审批--归档保存
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "fileSave")
	public String pettyCashApp_fileSave_Action(@ModelAttribute("pettyCashApp") PettyCashApp pettyCashApp, Model model)
			throws ParseException {
		pettyCashAppManager.fileSavePettyCashApp(pettyCashApp);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCashApp/list";
	}

	private void initBudgetSubjectList(Model model, Integer parentUuid) {

		// 取得一级科目（机构信息）
		List<BudgetSubject> _subList = budgetSubjectManager.getBudgetSubjectList(parentUuid);
		Map<String, String> subList = new LinkedHashMap<String, String>();
		subList.put("", "");
		for (BudgetSubject _sub : _subList) {
			subList.put(_sub.getUuid().toString(), _sub.getSubName());
		}

		model.addAttribute("subList", subList);
	}

	/**
	 * 备用金申请/审批--归档编辑
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "fileEdit/{id}")
	public String pettyCashApp_fileEdit_Action(@PathVariable("id") Integer id, Model model, HttpSession session)
			throws ParseException {
		PettyCashApp pettyCashApp = pettyCashAppManager.getPettyCashAppByUuid(id);

		// 申请时间
		pettyCashApp.setAppDate(DateUtils.transDateFormat(pettyCashApp.getAppDate(), "yyyyMMdd", "yyyy-MM-dd"));
		// 付款期限
		pettyCashApp.setPaymentPeriod(DateUtils.transDateFormat(pettyCashApp.getPaymentPeriod(), "yyyyMMdd", "yyyy-MM-dd"));

		initBudgetSubjectList(model, pettyCashApp.getAppOrg());

		model.addAttribute("pettyCashApp", pettyCashApp);

		return "affair/pettyCashAppFileEditForm";
	}

	/**
	 * 备用金申请/审批--提交
	 * 
	 * @param pettyCashApp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "confirm")
	public String pettyCashApp_confirm_Action(@ModelAttribute("pettyCashApp") PettyCashApp pettyCashApp, Model model) {
		pettyCashAppManager.savePettyCashApp(pettyCashApp, true);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCashApp/list";
	}
}
