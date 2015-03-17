/**
 * 
 */
package com.tjhx.web.info;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.entity.info.BudgetSubject;
import com.tjhx.service.info.BudgetSubjectManager;
import com.tjhx.vo.tree.info.BudgetSubjectTreeNode;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/budgetSubject")
public class BudgetSubjectController extends BaseController {
	@Resource
	private BudgetSubjectManager budgetSubjectManager;

	/**
	 * 预算科目页面初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) {
		BudgetSubject rootSub = budgetSubjectManager.getBudgetSubjectStructTree();

		if (null != rootSub) {
			BudgetSubjectTreeNode rootNode = formatBudgetSubject(rootSub);

			JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
			String rootNodeJson = jsonMapper.toJson(rootNode);

			model.addAttribute("rootNodeJson", rootNodeJson);
		}

		return "info/budgetSubject";
	}

	@RequestMapping(value = "subAdd")
	public String subAdd_Action(Model model, HttpServletRequest request) {
		List<BudgetSubject> subList = budgetSubjectManager.getBudgetSubjectList();
		model.addAttribute("subList", subList);

		return "info/budgetSubjectAdd";
	}

	/**
	 * 转换BudgetSubject->BudgetSubjectTreeNode
	 * 
	 * @param sub
	 * @return
	 */
	private BudgetSubjectTreeNode formatBudgetSubject(BudgetSubject sub) {
		BudgetSubjectTreeNode node = new BudgetSubjectTreeNode(sub);

		if (null != sub.getChildrenSubList()) {

			for (BudgetSubject _sub : sub.getChildrenSubList()) {
				node.addChildren(formatBudgetSubject(_sub));
			}
		}
		return node;
	}

	/**
	 * 保存预算科目
	 * 
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public String save_Action(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		BudgetSubject sub = new BudgetSubject();
		BeanUtils.populate(sub, request.getParameterMap());

		// 保存预算科目
		budgetSubjectManager.saveBudgetSubject(sub);

		return "true";
	}
}
