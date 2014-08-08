package com.tjhx.service.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.affair.FreightApplicationJpaDao;
import com.tjhx.dao.affair.FreightApplicationMyBatisDao;
import com.tjhx.entity.affair.FreightApplication;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class FreightApplicationManager {
	@Resource
	private FreightApplicationJpaDao freightApplicationJpaDao;
	@Resource
	private FreightApplicationMyBatisDao freightApplicationMyBatisDao;

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param appOrgId
	 * @param targetOrgId
	 * @param status
	 * @return
	 */
	public List<FreightApplication> getAllFreightApplication(String appOrgId, String targetOrgId, String status) {
		FreightApplication param = new FreightApplication();

		param.setAppOrgId(appOrgId);
		param.setTargetOrgId(targetOrgId);

		if (StringUtils.isNotBlank(status)) {
			param.setStatus(status);
		}
		return (List<FreightApplication>) freightApplicationMyBatisDao.getFreightApplicationList(param);
	}

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param appOrgId
	 * @param targetOrgId
	 * @param status
	 * @return
	 */
	public List<FreightApplication> getFreightApplicationList_Manager(String appOrgId, String targetOrgId, String status) {
		FreightApplication param = new FreightApplication();
		if (StringUtils.isNotBlank(appOrgId)) {
			param.setAppOrgId(appOrgId);
		}
		if (StringUtils.isNotBlank(targetOrgId)) {
			param.setTargetOrgId(targetOrgId);
		}
		if (StringUtils.isNotBlank(status)) {
			param.setStatus(status);
		}
		return (List<FreightApplication>) freightApplicationMyBatisDao.getFreightApplicationList_Manager(param);
	}

	/**
	 * 根据编号取得货运申请信息
	 * 
	 * @param uuid 货运申请编号
	 * @return 货运申请信息
	 */
	public FreightApplication getFreightApplicationByUuid(Integer uuid) {
		return freightApplicationJpaDao.findOne(uuid);
	}

	/**
	 * 删除货运申请信息
	 * 
	 * @param uuid 货运申请编号
	 */
	@Transactional(readOnly = false)
	public void delFreightApplicationByUuid(Integer uuid) {
		freightApplicationJpaDao.delete(uuid);
	}

	/**
	 * 添加新货运申请信息
	 * 
	 * @param 货运申请 货运申请信息
	 */
	@Transactional(readOnly = false)
	public void addNewFreightApplication(FreightApplication freightApplication) {

		FreightApplication _dbFreightApplication = freightApplicationJpaDao.findByAppDateAndAppOrgIdAndTargetOrgId(
				freightApplication.getAppDate(), freightApplication.getAppOrgId(), freightApplication.getTargetOrgId());
		// 该货运申请已存在!
		if (null != _dbFreightApplication) {
			throw new ServiceException("ERR_MSG_FREIGHT_APP_001");
		}

		freightApplicationJpaDao.save(freightApplication);
	}

	/**
	 * 更新货运申请信息
	 * 
	 * @param 货运申请 货运申请信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateFreightApplication(FreightApplication freightApplication) throws IllegalAccessException,
			InvocationTargetException {

		FreightApplication _dbFreightApplication = freightApplicationJpaDao.findOne(freightApplication.getUuid());
		if (null == _dbFreightApplication) {
			// 货运申请不存在!
			throw new ServiceException("ERR_MSG_FREIGHT_APP_002");
		}

		// 申请日期
		_dbFreightApplication.setAppDate(freightApplication.getAppDate());
		// 申请机构
		_dbFreightApplication.setAppOrgId(freightApplication.getAppOrgId());
		// 申请人
		_dbFreightApplication.setApplicant(freightApplication.getApplicant());
		// 是否打包（1-已打包/0-未打包）
		_dbFreightApplication.setPackFlg(freightApplication.getPackFlg());
		// 打包件数（箱和袋）
		_dbFreightApplication.setPackNum(freightApplication.getPackNum());
		// 打包件数（1-箱/2-袋）
		_dbFreightApplication.setPackUnit(freightApplication.getPackUnit());
		// 调货目的机构
		_dbFreightApplication.setTargetOrgId(freightApplication.getTargetOrgId());
		// 调货类别（1-普通/2-限时）
		_dbFreightApplication.setFreightType(freightApplication.getFreightType());
		// 限时日期
		_dbFreightApplication.setLimitedDate(freightApplication.getLimitedDate());
		// 审批人
		_dbFreightApplication.setApprover(freightApplication.getApprover());
		// 货运申请状态-00申请 01已审批 02已送达
		_dbFreightApplication.setStatus(freightApplication.getStatus());
		// ----------------------------------------------------------------------------
		freightApplicationJpaDao.save(_dbFreightApplication);
	}
}