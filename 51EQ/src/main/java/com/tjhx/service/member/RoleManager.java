package com.tjhx.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.member.RoleJpaDao;
import com.tjhx.entity.member.Role;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class RoleManager {
	private static Logger logger = LoggerFactory.getLogger(RoleManager.class);

	@Resource
	private RoleJpaDao roleJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;

	/**
	 * 取得所有角色信息
	 * 
	 * @return 角色信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAllRole() {
		List<Role> _roleList = spyMemcachedClient.get(MemcachedObjectType.ROLE_LIST.getObjKey());

		if (null == _roleList) {
			// 从数据库中取出全量角色信息(List格式)
			_roleList = (List<Role>) roleJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
			// 将角色信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.ROLE_LIST.getObjKey(),
					MemcachedObjectType.ROLE_LIST.getExpiredTime(), _roleList);

			logger.debug("角色信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出角色信息");
		}
		return _roleList;
	}

	/**
	 * 根据编号取得角色信息
	 * 
	 * @param uuid 角色编号
	 * @return 角色信息
	 */
	public Role getRoleByUuid(Integer uuid) {
		return roleJpaDao.findOne(uuid);
	}

	/**
	 * 添加新角色信息
	 * 
	 * @param role 角色信息
	 */
	@Transactional(readOnly = false)
	public void addNewRole(Role role) {

		Role _dbRole = roleJpaDao.findByName(role.getName());
		// 该角色已存在!
		if (null != _dbRole) {
			throw new ServiceException("ERR_MSG_ROLE_001");
		}

		roleJpaDao.save(role);

		spyMemcachedClient.delete(MemcachedObjectType.ROLE_LIST.getObjKey());
	}
}