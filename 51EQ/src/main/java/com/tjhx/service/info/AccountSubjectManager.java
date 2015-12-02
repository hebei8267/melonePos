package com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tjhx.dao.info.AccountSubjectJpaDao;
import com.tjhx.entity.info.AccountSubject;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class AccountSubjectManager {

	@Resource
	private AccountSubjectJpaDao accountSubjectJpaDao;

	/**
	 * 取得记账科目结构树
	 * 
	 * @return
	 */
	public AccountSubject getAccountSubjectStructTree() {
		AccountSubject rootSub = accountSubjectJpaDao.findOne(1);

		return initLazyObj(rootSub);
	}

	/**
	 * 初始化延时加载对象
	 * 
	 * @param budgetSubject
	 */
	private AccountSubject initLazyObj(AccountSubject accountSubject) {
		AccountSubject _reSub = null;
		if (null != accountSubject && !Constants.DEL_FLAG.equals(accountSubject.getDelFlg())) {
			_reSub = new AccountSubject();

			// 对象唯一标识
			_reSub.setUuid(accountSubject.getUuid());
			// 预算科目父节点Uuid
			_reSub.setParentSubUuid(accountSubject.getParentSub() == null ? null : accountSubject.getParentSub()
					.getUuid());
			// 预算科目父节点名称
			_reSub.setParentSubName(accountSubject.getParentSub() == null ? null : accountSubject.getParentSub()
					.getSubName());
			// 机构名称
			_reSub.setSubName(accountSubject.getSubName());
			// 排序
			_reSub.setSortIndex(accountSubject.getSortIndex());

			if (null != accountSubject.getChildrenSubList() && accountSubject.getChildrenSubList().size() > 0) {

				for (AccountSubject sub : accountSubject.getChildrenSubList()) {
					_reSub.addChildrenSub(initLazyObj(sub));
				}
			}

		}
		return _reSub;
	}

	/**
	 * 取得记账科目结构列表
	 * 
	 * @return
	 */
	public List<AccountSubject> getAccountSubjectList() {
		AccountSubject rootSub = accountSubjectJpaDao.findOne(1);

		rootSub = initLazyObj(rootSub);

		List<AccountSubject> subList = Lists.newArrayList();
		initSubList(rootSub, subList);

		return subList;
	}

	/**
	 * AccountSubject--->AccountSubjectList
	 * 
	 * @param sub
	 * @param subList
	 */
	private void initSubList(AccountSubject sub, List<AccountSubject> subList) {
		subList.add(sub);

		if (null != sub.getChildrenSubList() && sub.getChildrenSubList().size() > 0) {

			for (AccountSubject _sub : sub.getChildrenSubList()) {
				initSubList(_sub, subList);
			}
		}
	}

	/**
	 * 保存记账科目
	 * 
	 * @param sub
	 */
	@Transactional(readOnly = false)
	public Boolean saveAccountSubject(AccountSubject sub) {
		if (null == sub) {
			return false;
		}

		if (null == sub.getUuid()) {
			// 添加记账科目信息
			return addAccountSubject(sub);
		} else {
			// 编辑记账科目信息
			return editAccountSubject(sub);
		}
	}

	/**
	 * 添加记账科目信息
	 * 
	 * @param sub
	 * @return
	 */
	@Transactional(readOnly = false)
	private Boolean addAccountSubject(AccountSubject sub) {

		AccountSubject parentSub = accountSubjectJpaDao.findOne(sub.getParentSubUuid());
		if (null == parentSub) {
			return false;
		}

		sub.setSubId(parentSub.getSubId() + String.format("%02d", parentSub.getChildrenSubList().size() + 1));
		// 父节点
		sub.setParentSub(parentSub);
		parentSub.addChildrenSub(sub);

		sub.setLevel(parentSub.getLevel() + 1);

		accountSubjectJpaDao.save(sub);

		return true;
	}
	/**
	 * 编辑记账科目信息
	 * 
	 * @param sub
	 * @return
	 */
	@Transactional(readOnly = false)
	private Boolean editAccountSubject(AccountSubject sub) {
		AccountSubject dbSub = accountSubjectJpaDao.findOne(sub.getUuid());

		if (null == dbSub) {
			throw new ServiceException("ERR_MSG_BUDGET_SUBJECT_001");
		}
		// 预算科目名称
		dbSub.setSubName(sub.getSubName());
		// 排序
		dbSub.setSortIndex(sub.getSortIndex());

		accountSubjectJpaDao.save(dbSub);

		return true;
	}

	/**
	 * 删除记账科目信息
	 * 
	 * @param uuid
	 */
	@Transactional(readOnly = false)
	public void delAccountSubject(String uuid) {
		AccountSubject dbSub = accountSubjectJpaDao.findOne(Integer.valueOf(uuid));

		if (null != dbSub) {
			dbSub.setDelFlg(Constants.DEL_FLAG);

			accountSubjectJpaDao.save(dbSub);
		}
	}
}
