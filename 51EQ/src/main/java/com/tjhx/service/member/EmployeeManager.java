package com.tjhx.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.member.EmployeeJpaDao;
import com.tjhx.dao.member.EmployeeMyBatisDao;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.daozk.UserInfoMyBatisDao;
import com.tjhx.entity.member.Employee;
import com.tjhx.entity.struct.Organization;
import com.tjhx.entity.zknet.UserInfo;

@Service
@Transactional(readOnly = true)
public class EmployeeManager {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

	@Resource
	private EmployeeJpaDao empJpaDao;
	@Resource
	private OrganizationJpaDao orgJpaDao;
	@Resource
	private EmployeeMyBatisDao empMyBatisDao;
	@Resource
	private UserInfoMyBatisDao userInfoMyBatisDao;

	/**
	 * 取得本机构的兼职人员信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Employee> getTmpEmployeeByOrgId(String orgId) {
		List<Employee> empList = empMyBatisDao.getTmpEmployeeListByOrgId(orgId);

		return empList;
	}

	/**
	 * 取得本机构的兼职人员信息(启用状态)
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Employee> getTmpEmployeeByOrgId_WorkFlg(String orgId) {
		List<Employee> empList = empMyBatisDao.getTmpEmployeeByOrgId_WorkFlg(orgId);

		return empList;
	}

	@Transactional(readOnly = false)
	public void updateTmpEmployeeList(String orgId, List<Employee> empList) {
		List<Employee> _dbEmpList = empJpaDao.getTmpEmployeeListByOrgId(orgId);

		for (Employee dbEmployee : _dbEmpList) {
			Employee emp = myEquals(dbEmployee, empList);
			if (null != emp) {
				dbEmployee.setName(emp.getName());
				dbEmployee.setWorkFlg(emp.getWorkFlg());

				empJpaDao.save(dbEmployee);
			}

		}
	}

	private Employee myEquals(Employee dbEmployee, List<Employee> empList) {
		for (Employee employee : empList) {
			if (dbEmployee.getUuid().equals(employee.getUuid())) {
				return employee;
			}
		}
		return null;
	}

	public List<Employee> getEmployeeByOrgId(String orgId) {
		if (StringUtils.isBlank(orgId)) {
			return empMyBatisDao.getEmployeeList();
		} else {
			return empMyBatisDao.getEmployeeListByOrgId(orgId);
		}
	}

	/**
	 * 同步中控打卡数据库数据
	 */
	@Transactional(readOnly = false)
	public void zkDataSyn() {
		// 删除所有正式员工信息
		empMyBatisDao.deleteEmployeeByEmpType("1");

		List<UserInfo> userList = userInfoMyBatisDao.getUserInfoList();
		for (UserInfo userInfo : userList) {
			Employee emp = new Employee();

			emp.setZkid(userInfo.getUserid());
			emp.setCode(userInfo.getBadgenumber());
			emp.setName(userInfo.getName());
			emp.setZkOrgId(userInfo.getDefaultdeptid());
			emp.setEmpType("1");
			emp.setWorkFlg("1");

			empJpaDao.save(emp);

		}

	}

	public List<Employee> getEmployeeListByOrgId(String orgId) {
		return empMyBatisDao.getEmployeeListByOrgId(orgId);
	}

	/**
	 * 初始化新机构的关联兼职人员信息
	 * 
	 * @param orgUuid
	 */
	public void initNewOrgTmpEmployee(Integer orgUuid) {
		Organization org = orgJpaDao.findOne(orgUuid);
		if (null == org) {
			return;
		}
		for (int i = 1; i < 11; i++) {
			Employee emp = new Employee();

			emp.setName("兼职人员" + i);
			// 2-兼职
			emp.setEmpType("2");
			// 0-离职
			emp.setWorkFlg("0");
			/** 用户关联机构 */
			emp.setOrganization(org);

			emp.setCode(org.getId() + "TMP" + String.format("%02d", i));

			empJpaDao.save(emp);

		}
	}

	/**
	 * 清除机构的关联兼职人员信息
	 * 
	 * @param orgUuid
	 */
	public void cleanOrgTmpEmployee(Integer orgUuid) {
		Organization org = orgJpaDao.findOne(orgUuid);
		if (null == org) {
			return;
		}
		// 删除原有机构的关联兼职人员信息
		List<Employee> empList = empJpaDao.getTmpEmployeeListByOrgId(org.getId());
		if (null != empList && empList.size() > 0) {
			empJpaDao.delete(empList);
		}
	}
}
