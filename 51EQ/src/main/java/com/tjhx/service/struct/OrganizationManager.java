package com.tjhx.service.struct;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.google.common.collect.Lists;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.dao.struct.OrganizationMyBatisDao;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.WorkTypeManager;
import com.tjhx.service.member.EmployeeManager;

@Service
@Transactional(readOnly = true)
public class OrganizationManager {
	private static Logger logger = LoggerFactory.getLogger(OrganizationManager.class);

	@Resource
	private OrganizationJpaDao orgJpaDao;
	@Resource
	private OrganizationMyBatisDao organizationMyBatisDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;
	@Resource
	private WorkTypeManager workTypeManager;
	@Resource
	private EmployeeManager employeeManager;

	/**
	 * 取得所有开店机构信息
	 * 
	 * @return 开店机构信息
	 */
	public List<Organization> getAllOpenOrganization() {
		List<Organization> _orgList = getAllOrganization();

		List<Organization> _reOrgList = Lists.newArrayList();

		for (Organization org : _orgList) {
			if (!org.isClosedFlg()) {
				_reOrgList.add(org);
			}
		}

		return _reOrgList;
	}

	/**
	 * 取得所有机构信息
	 * 
	 * @return 机构信息列表
	 */
	public List<Organization> getAllOrganization() {

		List<Organization> _orgList = spyMemcachedClient.get(MemcachedObjectType.ORG_LIST.getObjKey());

		if (null == _orgList) {
			// 从数据库中取出全量机构信息(List格式)
			_orgList = organizationMyBatisDao.getAllOrgList();
			// 将机构信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.ORG_LIST.getObjKey(), MemcachedObjectType.ORG_LIST.getExpiredTime(), _orgList);

			logger.debug("机构信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出机构信息");
		}
		return _orgList;
	}

	/**
	 * 根据资金编号(百威)取得机构信息
	 * 
	 * @param bwBranchNo
	 * @return
	 */
	public Organization getOrganizationByBwBranchNo(String bwBranchNo) {
		List<Organization> _orgList = getAllOrganization();
		for (Organization organization : _orgList) {
			if (null != organization.getBwBranchNo() && organization.getBwBranchNo().equals(bwBranchNo)) {
				return organization;
			}
		}

		return null;
	}

	/**
	 * 根据编号取得机构信息
	 * 
	 * @param uuid 机构编号
	 * @return
	 */
	public Organization getOrganizationByUuidInCache(Integer uuid) {
		List<Organization> _orgList = getAllOrganization();
		for (Organization organization : _orgList) {
			if (organization.getUuid().equals(uuid)) {
				return organization;
			}
		}

		return null;
	}

	/**
	 * 根据编号取得机构信息
	 * 
	 * @param orgId 机构编号
	 * @return
	 */
	public Organization getOrganizationByOrgIdInCache(String orgId) {
		List<Organization> _orgList = getAllOrganization();
		for (Organization organization : _orgList) {
			if (organization.getId().equals(orgId)) {
				return organization;
			}
		}

		return null;
	}

	/**
	 * 根据编号取得机构信息
	 * 
	 * @param uuid 机构编号
	 * @return 机构信息
	 */
	public Organization getOrganizationByUuid(Integer uuid) {
		return orgJpaDao.findOne(uuid);
	}

	/**
	 * 删除机构信息
	 * 
	 * @param uuid 机构编号
	 */
	@Transactional(readOnly = false)
	public void delOrganizationByUuid(Integer uuid) {
		// 清除机构的关联上班类型信息
		workTypeManager.cleanOrgWorkType(uuid);
		// 清除机构的关联兼职人员信息
		employeeManager.cleanOrgTmpEmployee(uuid);

		spyMemcachedClient.delete(MemcachedObjectType.ORG_LIST.getObjKey());

		orgJpaDao.delete(uuid);
	}

	/**
	 * 添加新机构信息
	 * 
	 * @param org 机构信息
	 */
	@Transactional(readOnly = false)
	public void addNewOrganization(Organization org) {
		Organization _dbOrganization = orgJpaDao.findByBwId(org.getBwId());
		// 该机构已存在!
		if (null != _dbOrganization) {
			throw new ServiceException("ERR_MSG_ORG_001");
		}

		if (Constants.ROOT_ORG_BW_ID.equals(org.getBwId())) {
			org.setId(Constants.ROOT_ORG_ID);
		} else {
			Organization rootOrg = orgJpaDao.findOne(1);
			org.setParentOrg(rootOrg);
			rootOrg.addSubOrg(org);
			org.setId(rootOrg.getId() + org.getBwId());

		}

		orgJpaDao.save(org);

		spyMemcachedClient.delete(MemcachedObjectType.ORG_LIST.getObjKey());

		// 初始化新机构的关联上班类型信息
		workTypeManager.initNewOrgWorkType(org.getUuid());
		// 初始化新机构的关联兼职人员信息
		employeeManager.initNewOrgTmpEmployee(org.getUuid());
	}

	/**
	 * 更新机构信息
	 * 
	 * @param org 机构信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateOrganization(Organization org) throws IllegalAccessException, InvocationTargetException {
		Organization _dbOrganization = orgJpaDao.findOne(org.getUuid());
		if (null == _dbOrganization) {
			// 机构不存在!
			throw new ServiceException("ERR_MSG_ORG_002");
		}

		_dbOrganization.setName(org.getName());
		_dbOrganization.setZkId(org.getZkId());
		_dbOrganization.setBwBranchNo(org.getBwBranchNo());
		_dbOrganization.setOrgAdd(org.getOrgAdd());
		_dbOrganization.setOrgAddShort(org.getOrgAddShort());
		_dbOrganization.setBrand(org.getBrand());
		_dbOrganization.setMngUserId(org.getMngUserId());
		_dbOrganization.setClosedFlg(org.isClosedFlg());

		orgJpaDao.save(_dbOrganization);

		spyMemcachedClient.delete(MemcachedObjectType.ORG_LIST.getObjKey());
	}

	/**
	 * 取得门店机构（不包含总部机构）
	 * 
	 * @return
	 */
	public List<Organization> getSubOrganization() {
		List<Organization> _orgList = getAllOrganization();

		_orgList.remove(new Organization(Constants.ROOT_ORG_ID));

		return _orgList;
	}
}