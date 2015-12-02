package com.tjhx.web.info;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.entity.info.AccountSubject;
import com.tjhx.globals.Constants;
import com.tjhx.service.info.AccountSubjectManager;
import com.tjhx.vo.tree.info.AccountSubjectTreeNode;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/accountSubject")
public class AccountSubjectController extends BaseController {
	@Resource
	private AccountSubjectManager accountSubjectManager;

	/**
	 * 记账科目页面初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) {
		AccountSubject rootSub = accountSubjectManager.getAccountSubjectStructTree();

		if (null != rootSub) {
			AccountSubjectTreeNode rootNode = formatAccountSubject(rootSub);

			JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
			String rootNodeJson = jsonMapper.toJson(rootNode);

			model.addAttribute("rootNodeJson", rootNodeJson);
		}

		return "info/accountSubject";
	}

	/**
	 * 转换AccountSubject->AccountSubjectTreeNode
	 * 
	 * @param sub
	 * @return
	 */
	private AccountSubjectTreeNode formatAccountSubject(AccountSubject sub) {
		AccountSubjectTreeNode node = new AccountSubjectTreeNode(sub);

		if (null != sub.getChildrenSubList()) {

			for (AccountSubject _sub : sub.getChildrenSubList()) {
				node.addChildren(formatAccountSubject(_sub));
			}
		}
		return node;
	}

	@RequestMapping(value = "subAdd")
	public String subAdd_Action(Model model, HttpServletRequest request) {
		List<AccountSubject> subList = accountSubjectManager.getAccountSubjectList();
		model.addAttribute("subList", subList);

		return "info/accountSubjectAdd";
	}

	/**
	 * 保存记账科目
	 * 
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public String save_Action(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		AccountSubject sub = new AccountSubject();
		BeanUtils.populate(sub, request.getParameterMap());

		// 保存记账科目
		accountSubjectManager.saveAccountSubject(sub);

		return "true";
	}

	/**
	 * 删除记账科目信息
	 * 
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "del")
	public String del_Action(HttpServletRequest request) throws ServletRequestBindingException {
		String uuid = ServletRequestUtils.getStringParameter(request, "uuid");

		if (StringUtils.isNotBlank(uuid)) {
			// 删除记账科目信息
			accountSubjectManager.delAccountSubject(uuid);
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountSubject/init";
	}
}
