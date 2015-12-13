package com.tjhx.web.info;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	 * 会计记账编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "split")
	public String splitAccountFlow_Action(@RequestParam("uuid") String id, Model model) {
		return null;
	}
}
