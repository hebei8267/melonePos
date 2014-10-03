package com.tjhx.service.member;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.member.Employee2JpaDao;
import com.tjhx.dao.member.Employee2MyBatisDao;
import com.tjhx.entity.member.Employee2;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class Employee2Manager {
	@Resource
	private Employee2JpaDao employee2JpaDao;
	@Resource
	private Employee2MyBatisDao employee2MyBatisDao;

	/**
	 * 取得所有Employee2信息
	 * 
	 * @return Employee2信息列表
	 */
	public List<Employee2> getAllEmployee2(Employee2 employee2) {
		return employee2MyBatisDao.getEmployee2List(employee2);
	}

	/**
	 * 根据编号取得Employee2信息
	 * 
	 * @param uuid Employee2编号
	 * @return Employee2信息
	 */
	public Employee2 getEmployee2ByUuid(Integer uuid) {
		return employee2JpaDao.findOne(uuid);
	}

	/**
	 * 删除Employee2信息
	 * 
	 * @param uuid Employee2编号
	 */
	@Transactional(readOnly = false)
	public void delEmployee2ByUuid(Integer uuid) {
		employee2JpaDao.delete(uuid);
	}

	/**
	 * 添加新Employee2信息
	 * 
	 * @param employee2 Employee2信息
	 */
	@Transactional(readOnly = false)
	public void addNewEmployee2(Employee2 employee2) {
		employee2JpaDao.save(employee2);
	}

	/**
	 * 更新Employee2信息
	 * 
	 * @param employee2 Employee2信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateEmployee2(Employee2 employee2) throws IllegalAccessException, InvocationTargetException {

		Employee2 _dbEmployee2 = employee2JpaDao.findOne(employee2.getUuid());
		if (null == _dbEmployee2) {
			throw new ServiceException("ERR_MSG_EMPLOYEE2_002");
		}
		// 员工姓名
		_dbEmployee2.setName(employee2.getName());
		// 员工性别 1-男 2-女
		_dbEmployee2.setSex(employee2.getSex());
		// 身份证号码
		_dbEmployee2.setIdCardNo(employee2.getIdCardNo());
		// 婚姻状况1-已婚 0-未婚
		_dbEmployee2.setMaritalStatus(employee2.getMaritalStatus());
		// 学历1-小学 2-初中 3-高中 4-大学
		_dbEmployee2.setEducation(employee2.getEducation());
		// 参加工作时间
		_dbEmployee2.setStartWorkTime(employee2.getStartWorkTime());
		// 专业
		_dbEmployee2.setProfessional(employee2.getProfessional());
		// 户口所在地
		_dbEmployee2.setAccountLocal(employee2.getAccountLocal());
		// 常住地址
		_dbEmployee2.setAddress(employee2.getAddress());
		// 联系电话
		_dbEmployee2.setPhone(employee2.getPhone());
		// 紧急联系人
		_dbEmployee2.setEmergencyContact(employee2.getEmergencyContact());
		// 紧急联系电话
		_dbEmployee2.setEmergencyPhone(employee2.getEmergencyPhone());
		// 基本情况备注
		_dbEmployee2.setBasicInfoDescTxt(employee2.getBasicInfoDescTxt());
		// 所属部门
		_dbEmployee2.setOrgId(employee2.getOrgId());
		// 职务
		_dbEmployee2.setPos(employee2.getPos());
		// 入职时间
		_dbEmployee2.setEntryTime(employee2.getEntryTime());
		// 转正时间
		_dbEmployee2.setPosTime(employee2.getPosTime());
		// 合同到期时间
		_dbEmployee2.setContractExpTime(employee2.getContractExpTime());
		// 续签时间
		_dbEmployee2.setRenewTime(employee2.getRenewTime());
		// 聘用形式 1-正式 2-兼职
		_dbEmployee2.setEmployForm(employee2.getEmployForm());
		// 机构调动记录
		_dbEmployee2.setOrgTransRecord(employee2.getOrgTransRecord());
		// 职位调整记录
		_dbEmployee2.setPosAdjustRecord(employee2.getPosAdjustRecord());

		employee2JpaDao.save(_dbEmployee2);
	}
}