/**
 * 
 */
package com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tjhx.dao.info.BudgetSubjectJpaDao;
import com.tjhx.entity.info.BudgetSubject;
import com.tjhx.globals.Constants;

/**
 * 
 */
@Service
@Transactional(readOnly = true)
public class BudgetSubjectManager {
	@Resource
	private BudgetSubjectJpaDao budgetSubjectJpaDao;

	/**
	 * 取得预算科目结构树
	 * 
	 * @return
	 */
	public BudgetSubject getBudgetSubjectStructTree() {
		BudgetSubject rootSub = budgetSubjectJpaDao.findOne(1);

		return initLazyObj(rootSub);
	}

	/**
	 * 初始化延时加载对象
	 * 
	 * @param budgetSubject
	 */
	private BudgetSubject initLazyObj(BudgetSubject budgetSubject) {
		BudgetSubject _reSub = null;
		if (null != budgetSubject && !Constants.DEL_FLAG.equals(budgetSubject.getDelFlg())) {
			_reSub = new BudgetSubject();

			// 对象唯一标识
			_reSub.setUuid(budgetSubject.getUuid());
			// 预算科目父节点Uuid
			_reSub.setParentSubUuid(budgetSubject.getParentSub() == null ? null : budgetSubject.getParentSub().getUuid());
			// 预算科目父节点名称
			_reSub.setParentSubName(budgetSubject.getParentSub() == null ? null : budgetSubject.getParentSub().getSubName());
			// 机构名称
			_reSub.setSubName(budgetSubject.getSubName());
			// 排序
			_reSub.setSortIndex(budgetSubject.getSortIndex());

			if (null != budgetSubject.getChildrenSubList() && budgetSubject.getChildrenSubList().size() > 0) {

				for (BudgetSubject sub : budgetSubject.getChildrenSubList()) {
					_reSub.addChildrenSub(initLazyObj(sub));
				}
			}

		}
		return _reSub;
	}

	/**
	 * 取得预算科目结构列表
	 * 
	 * @return
	 */
	public List<BudgetSubject> getBudgetSubjectList() {
		BudgetSubject rootSub = budgetSubjectJpaDao.findOne(1);

		rootSub = initLazyObj(rootSub);

		List<BudgetSubject> subList = Lists.newArrayList();
		initSubList(rootSub, subList);

		return subList;
	}

	/**
	 * BudgetSubject--->BudgetSubjectList
	 * 
	 * @param sub
	 * @param subList
	 */
	private void initSubList(BudgetSubject sub, List<BudgetSubject> subList) {
		subList.add(sub);

		if (null != sub.getChildrenSubList() && sub.getChildrenSubList().size() > 0) {

			for (BudgetSubject _sub : sub.getChildrenSubList()) {
				initSubList(_sub, subList);
			}
		}
	}

	/**
	 * 保存预算科目
	 * 
	 * @param sub
	 */
	public void saveBudgetSubject(BudgetSubject sub) {
		// TODO Auto-generated method stub

	}
}
