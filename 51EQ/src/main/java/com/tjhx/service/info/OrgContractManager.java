package com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.OrgContractJpaDao;
import com.tjhx.entity.info.OrgContract;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class OrgContractManager {
	@Resource
	private OrgContractJpaDao orgContractJpaDao;

	/**
	 * 取得机构合同信息（含失效期三个月以内）
	 * 
	 * @return
	 */
	public List<OrgContract> getOrgContractList() {
		String validateData = DateUtils.getNextDateFormatDate(-90, "yyyyMMdd");
		return orgContractJpaDao.getOrgContractList(validateData);
	}

	/**
	 * 新增机构合同信息
	 * 
	 * @param orgContract
	 */
	@Transactional(readOnly = false)
	public void addOrgContract(OrgContract orgContract) {
		OrgContract _dbOrgContract = orgContractJpaDao.getOrgContractByOrgIdAndStartDate(orgContract.getOrgId(),
				orgContract.getStartDate());

		if (null != _dbOrgContract) {// 重复机构合同信息
			throw new ServiceException("ERR_MSG_ORG_CONTRACT_001");
		}
		orgContractJpaDao.save(orgContract);
	}

	/**
	 * 更新机构合同信息
	 * 
	 * @param orgContract
	 */
	@Transactional(readOnly = false)
	public void updateOrgContract(OrgContract orgContract) {
		OrgContract _dbOrgContract = orgContractJpaDao.getOrgContractByOrgIdAndStartDate(orgContract.getOrgId(),
				orgContract.getStartDate());

		if (null == _dbOrgContract) {// 不存在的机构合同信息
			throw new ServiceException("ERR_MSG_ORG_CONTRACT_002");
		}

		// 合同期限－结束时间
		_dbOrgContract.setEndDate(orgContract.getEndDate());
		// 缴交频率 1-按季度 2-按月份
		_dbOrgContract.setPayFrequent(orgContract.getPayFrequent());
		// 缴交金额（估计）
		_dbOrgContract.setComputePayAmt(orgContract.getComputePayAmt());
		// 优惠条款
		_dbOrgContract.setTerms(orgContract.getTerms());

		orgContractJpaDao.save(_dbOrgContract);
	}

	/**
	 * 编辑机构合同信息
	 * 
	 * @param id
	 * @return
	 */
	public OrgContract getOrgContractByUuid(Integer id) {
		return orgContractJpaDao.findOne(id);
	}

	/**
	 * 删除机构合同信息
	 * 
	 * @param parseInt
	 */
	@Transactional(readOnly = false)
	public void delOrgContractByUuid(Integer uuid) {
		OrgContract _orgContract = orgContractJpaDao.findOne(uuid);

		orgContractJpaDao.delete(_orgContract);

	}
}
