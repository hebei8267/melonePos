package com.tjhx.web.info;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.AccountFlow;
import com.tjhx.globals.Constants;
import com.tjhx.service.info.AccountFlowManager;
import com.tjhx.web.BaseController;
@Controller
@RequestMapping(value = "/accountFlow")
public class AccountFlowController extends BaseController {
	@Resource
	private AccountFlowManager accountFlowManager;

	/**
	 * 记账页面初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list_Action(Model model) {
		List<AccountFlow> _list = accountFlowManager.getAccountFlowList();

		model.addAttribute("accountFlowList", _list);

		return "info/accountFlowList";
	}

	/**
	 * 删除记账信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delAccountFlow_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			accountFlowManager.delAccountFlowByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountFlow/list";
	}
	/**
	 * 记账初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initAccountFlow_Action(Model model) {

		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setInAmt(null);
		accountFlow.setOutAmt(null);
		model.addAttribute("accountFlow", accountFlow);

		return "info/accountFlowForm";
	}

	/**
	 * 编辑记账信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{uuid}")
	public String editAccountFlow_Action(@PathVariable("uuid") Integer id, Model model) {

		AccountFlow accountFlow = accountFlowManager.getAccountFlowByUuid(id);
		if (null == accountFlow) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountFlow/list";
		} else {
			accountFlow.setOptDate(DateUtils.transDateFormat(accountFlow.getOptDate(), "yyyyMMdd", "yyyy-MM-dd"));
			model.addAttribute("accountFlow", accountFlow);

			return "info/accountFlowForm";
		}

	}

	/**
	 * 新增/修改 记账信息
	 * 
	 * @param role
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveAccountFlow_Action(HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		AccountFlow accountFlow = new AccountFlow();
		BeanUtils.populate(accountFlow, request.getParameterMap());

		if (null != accountFlow.getUuid() && 0 == accountFlow.getUuid()) {
			accountFlow.setUuid(null);
		}
		// 保存操作
		accountFlowManager.saveAccountFlow(accountFlow);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountFlow/list";
	}
	// =================================================================================
	/**
	 * 会计记账编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "split")
	public String splitAccountFlow_Action(@RequestParam("uuid") String id, Model model) {
		return "info/accountFlowSplit";
	}
}
