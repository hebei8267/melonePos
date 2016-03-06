package com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.info.OrgContractPayRunJpaDao;
import com.tjhx.entity.info.OrgContractPayRun;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class OrgContractPayRunManager {
	@Resource
	private OrgContractPayRunJpaDao orgContractPayRunJpaDao;

	/**
	 * 删除机构合同缴费信息
	 * 
	 * @param parseInt
	 */
	@Transactional(readOnly = false)
	public void delOrgContractPayRunByUuid(int uuid) {
		OrgContractPayRun _orgContractPayRun = orgContractPayRunJpaDao.findOne(uuid);

		orgContractPayRunJpaDao.delete(_orgContractPayRun);

	}

	/**
	 * 取得机构合同缴费信息
	 * 
	 * @return
	 */
	public List<OrgContractPayRun> getOrgContractPayRunListByOrgId(String orgId) {
		return orgContractPayRunJpaDao.findByOrgId(orgId, new Sort(new Sort.Order(Sort.Direction.DESC, "payDate")));
	}

	/**
	 * 新增机构合同缴费信息
	 * 
	 * @param orgContract
	 */
	@Transactional(readOnly = false)
	public void addOrgContractPayRun(OrgContractPayRun orgContractPayRun) {
		orgContractPayRunJpaDao.save(orgContractPayRun);
	}

	/**
	 * 更新机构合同缴费信息
	 * 
	 * @param orgContract
	 */
	@Transactional(readOnly = false)
	public void updateOrgContractPayRun(OrgContractPayRun orgContractPayRun) {
		OrgContractPayRun _dbOrgContractPayRun = orgContractPayRunJpaDao.findOne(orgContractPayRun.getUuid());

		if (null == _dbOrgContractPayRun) {// 不存在的机构合同缴费信息
			throw new ServiceException("ERR_MSG_ORG_CONTRACT_PAY_RUN_001");
		}

		// 缴交时间安排
		_dbOrgContractPayRun.setPayDate(orgContractPayRun.getPayDate());
		// 缴交金额
		_dbOrgContractPayRun.setPayAmt(orgContractPayRun.getPayAmt());
		// 是否缴款 0-未缴费 1-已缴费
		_dbOrgContractPayRun.setPayFlg(orgContractPayRun.getPayFlg());

		orgContractPayRunJpaDao.save(_dbOrgContractPayRun);
	}

	/**
	 * 取得机构合同缴费信息
	 * 
	 * @param id
	 * @return
	 */
	public OrgContractPayRun getOrgContractPayRunByUuid(Integer id) {
		return orgContractPayRunJpaDao.findOne(id);
	}

}
