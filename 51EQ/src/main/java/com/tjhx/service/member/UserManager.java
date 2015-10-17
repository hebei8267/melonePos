package com.tjhx.service.member;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.common.utils.Encrypter;
import com.tjhx.dao.info.BudgetSubjectJpaDao;
import com.tjhx.dao.member.RoleJpaDao;
import com.tjhx.dao.member.UserJpaDao;
import com.tjhx.dao.member.UserMyBatisDao;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.info.BudgetSubject;
import com.tjhx.entity.member.Role;
import com.tjhx.entity.member.User;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class UserManager {
	private static Logger logger = LoggerFactory.getLogger(UserManager.class);

	@Resource
	private UserJpaDao userJpaDao;
	@Resource
	private UserMyBatisDao userMyBatisDao;
	@Resource
	private RoleJpaDao roleJpaDao;
	@Resource
	private OrganizationJpaDao orgJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;
	@Resource
	private BudgetSubjectJpaDao budgetSubjectJpaDao;

	/**
	 * 取得所有用户信息
	 * 
	 * @return 用户信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		return (List<User>) userJpaDao.findAll(new Sort(new Sort.Order("organization"), new Sort.Order(
				Sort.Direction.ASC, "loginName")));
	}

	/**
	 * 取得所有用户信息
	 * 
	 * @return 用户信息列表
	 */
	public List<User> getAllUserByCache() {
		List<User> _userList = spyMemcachedClient.get(MemcachedObjectType.USER_LIST.getObjKey());

		if (null == _userList) {
			// 从数据库中取出全量机构信息(List格式)
			_userList = userMyBatisDao.getAllUserList();
			// 将机构信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.USER_LIST.getObjKey(),
					MemcachedObjectType.USER_LIST.getExpiredTime(), _userList);

			logger.debug("人员信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出人员信息");
		}
		return _userList;

	}

	/**
	 * 取得指定机构的用户信息列表
	 * 
	 * @param orgUuid
	 * @return
	 */
	public List<User> getSelfOrgUserByCache(int orgUuid) {
		List<User> _userList = getAllUserByCache();

		List<User> _reList = new ArrayList<User>();
		for (User user : _userList) {
			if (user.getOrgUuid().equals(String.valueOf(orgUuid))) {
				_reList.add(user);
			}
		}
		return _reList;
	}

	/**
	 * 根据用户名取得用户信息
	 * 
	 * @param loginName 用户名
	 * @return
	 */
	public User getUserByLoginNameInCache(String loginName) {
		List<User> _userList = getAllUserByCache();

		for (User user : _userList) {
			if (user.getLoginName().equals(loginName)) {
				return user;
			}
		}

		return null;
	}

	/**
	 * 根据编号取得用户信息
	 * 
	 * @param uuid 用户编号
	 * @return 用户信息
	 */
	public User getUserByUuid(Integer uuid) {
		return userJpaDao.findOne(uuid);
	}

	/**
	 * 删除用户信息
	 * 
	 * @param uuid 用户编号
	 */
	@Transactional(readOnly = false)
	public void delUserByUuid(Integer uuid) {
		// 消除用户信息缓存
		spyMemcachedClient.delete(MemcachedObjectType.USER_LIST.getObjKey());

		userJpaDao.delete(uuid);
	}

	/**
	 * 添加新用户信息
	 * 
	 * @param user 用户信息
	 */
	@Transactional(readOnly = false)
	public void addNewUser(User user) {

		User _dbUser = findByLoginName(user.getLoginName());
		// 该用户已存在!
		if (null != _dbUser) {
			throw new ServiceException("ERR_MSG_USER_001");
		}

		// 密码加密
		user.setPassWord(Encrypter.encrypt(Constants.DEFAULT_PWD));

		Organization org = orgJpaDao.findOne(Integer.parseInt(user.getOrgUuid()));
		user.setOrganization(org);
		Role role = roleJpaDao.findOne(Integer.parseInt(user.getRoleUuid()));
		user.setRole(role);

		if (null != user.getSubUuid()) {
			// 预算科目
			BudgetSubject sub = budgetSubjectJpaDao.findOne(Integer.parseInt(user.getSubUuid()));
			user.setBudgetSubject(sub);
		}

		// 消除用户信息缓存
		spyMemcachedClient.delete(MemcachedObjectType.USER_LIST.getObjKey());

		userJpaDao.save(user);
	}

	/**
	 * 取得用户信息
	 * 
	 * @param loginName 登录名称
	 * @return 用户信息
	 */
	public User findByLoginName(String loginName) {
		return userJpaDao.findByLoginName(loginName);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param userUuid
	 * @param oldPassWord
	 * @param newPassWord
	 */
	@Transactional(readOnly = false)
	public User modUserPwd(Integer userUuid, String oldPassWord, String newPassWord) {
		User _dbUser = userJpaDao.findOne(userUuid);
		if (null == _dbUser) {
			// 用户不存在!
			throw new ServiceException("ERR_MSG_USER_002");
		}

		if (!Encrypter.decrypt(_dbUser.getPassWord()).equals(oldPassWord)) {
			// 原始密码不正确!
			throw new ServiceException("ERR_MSG_USER_003");
		}

		_dbUser.setPassWord(Encrypter.encrypt(newPassWord));

		_dbUser.setFirstLoginFlg(false);

		userJpaDao.save(_dbUser);

		return _dbUser;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user 用户信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateUser(User user) throws IllegalAccessException, InvocationTargetException {

		User _dbUser = userJpaDao.findOne(user.getUuid());
		if (null == _dbUser) {
			// 用户不存在!
			throw new ServiceException("ERR_MSG_USER_002");
		}
		// 用户名称
		_dbUser.setName(user.getName());
		_dbUser.setDescTxt(user.getDescTxt());

		if (user.isInitPwdFlg()) {// 初始化密码-默认值
			_dbUser.setPassWord(Encrypter.encrypt(Constants.DEFAULT_PWD));
		}

		// 所属机构
		Organization org = orgJpaDao.findOne(Integer.parseInt(user.getOrgUuid()));
		_dbUser.setOrganization(org);
		// 用户角色
		Role role = roleJpaDao.findOne(Integer.parseInt(user.getRoleUuid()));
		_dbUser.setRole(role);
		// 账户是否有效
		_dbUser.setValid(user.isValid());
		// 督导员标记
		_dbUser.setMngFlg(user.isMngFlg());
		// 预算科目
		BudgetSubject sub = budgetSubjectJpaDao.findOne(Integer.parseInt(user.getSubUuid()));
		_dbUser.setBudgetSubject(sub);

		// 消除用户信息缓存
		spyMemcachedClient.delete(MemcachedObjectType.USER_LIST.getObjKey());

		userJpaDao.save(_dbUser);
	}

	/**
	 * 取得督导员信息列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getMngUser() {
		return (List<User>) userJpaDao.getMngUser(new Sort(new Sort.Order(Sort.Direction.ASC, "loginName")));
	}
}