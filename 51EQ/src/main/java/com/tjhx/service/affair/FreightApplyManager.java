package com.tjhx.service.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.FreightApplyJpaDao;
import com.tjhx.dao.affair.FreightApplyMyBatisDao;
import com.tjhx.entity.affair.FreightApply;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class FreightApplyManager {
	@Resource
	private FreightApplyJpaDao freightApplyJpaDao;
	@Resource
	private FreightApplyMyBatisDao freightApplyMyBatisDao;

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param appOrgId
	 * @param targetOrgId
	 * @param status
	 * @return
	 */
	public List<FreightApply> getFreightApplyList(String appOrgId, String targetOrgId, String status) {
		FreightApply param = new FreightApply();

		param.setAppOrgId(appOrgId);
		param.setTargetOrgId(targetOrgId);

		if (StringUtils.isNotBlank(status)) {
			param.setStatus(status);
		}
		return (List<FreightApply>) freightApplyMyBatisDao.getFreightApplyList(param);
	}

	/**
	 * 取得货运申请信息列表
	 * 
	 * @param appOrgId
	 * @param targetOrgId
	 * @param status
	 * @return
	 */
	public List<FreightApply> getFreightApplyList_Manager(String appOrgId, String targetOrgId, String status) {
		FreightApply param = new FreightApply();
		if (StringUtils.isNotBlank(appOrgId)) {
			param.setAppOrgId(appOrgId);
		}
		if (StringUtils.isNotBlank(targetOrgId)) {
			param.setTargetOrgId(targetOrgId);
		}
		if (StringUtils.isNotBlank(status)) {
			param.setStatus(status);
		}
		return (List<FreightApply>) freightApplyMyBatisDao.getFreightApplyList_Manager(param);
	}

	/**
	 * 根据编号取得货运申请信息
	 * 
	 * @param uuid 货运申请编号
	 * @return 货运申请信息
	 */
	public FreightApply getFreightApplyByUuid(Integer uuid) {
		return freightApplyJpaDao.findOne(uuid);
	}

	/**
	 * 删除货运申请信息
	 * 
	 * @param uuid 货运申请编号
	 */
	@Transactional(readOnly = false)
	public void delFreightApplyByUuid(Integer uuid) {
		freightApplyJpaDao.delete(uuid);
	}

	/**
	 * 添加新货运申请信息
	 * 
	 * @param 货运申请 货运申请信息
	 */
	@Transactional(readOnly = false)
	public void addNewFreightApply(FreightApply freightApplication) {

		FreightApply _dbFreightApplication = freightApplyJpaDao.findByAppDateAndAppOrgIdAndTargetOrgId(
				freightApplication.getAppDate(), freightApplication.getAppOrgId(), freightApplication.getTargetOrgId());
		// 该货运申请已存在!
		if (null != _dbFreightApplication) {
			throw new ServiceException("ERR_MSG_FREIGHT_APP_001");
		}

		freightApplyJpaDao.save(freightApplication);
	}

	/**
	 * 更新货运申请信息
	 * 
	 * @param 货运申请 货运申请信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateFreightApply(FreightApply freightApply) throws IllegalAccessException, InvocationTargetException {

		FreightApply _dbFreightApply = freightApplyJpaDao.findOne(freightApply.getUuid());
		if (null == _dbFreightApply) {
			// 货运申请不存在!
			throw new ServiceException("ERR_MSG_FREIGHT_APP_002");
		}

		// 申请日期
		_dbFreightApply.setAppDate(freightApply.getAppDate());
		// 申请机构
		_dbFreightApply.setAppOrgId(freightApply.getAppOrgId());
		// 申请人
		_dbFreightApply.setApplicant(freightApply.getApplicant());
		// 是否打包（1-已打包/0-未打包）
		_dbFreightApply.setPackFlg(freightApply.getPackFlg());
		// 打包件数（箱和袋）
		_dbFreightApply.setPackNum(freightApply.getPackNum());
		// 打包件数（1-箱/2-袋）
		_dbFreightApply.setPackUnit(freightApply.getPackUnit());
		// 调货目的机构
		_dbFreightApply.setTargetOrgId(freightApply.getTargetOrgId());
		// 调货类别（1-普通/2-限时）
		_dbFreightApply.setFreightType(freightApply.getFreightType());
		// 限时日期
		_dbFreightApply.setLimitedDate(freightApply.getLimitedDate());
		// 审批人
		_dbFreightApply.setApprover(freightApply.getApprover());
		// 货运申请状态-00申请 01已审批 02已送达
		_dbFreightApply.setStatus(freightApply.getStatus());

		freightApplyJpaDao.save(_dbFreightApply);
	}

	/**
	 * 更新货运申请信息
	 * 
	 * @param freightApp 货运申请 货运申请信息
	 */
	@Transactional(readOnly = false)
	public void updateFreightApply_view(FreightApply freightApp) {
		FreightApply _dbFreightApply = freightApplyJpaDao.findOne(freightApp.getUuid());
		if (null == _dbFreightApply) {
			// 货运申请不存在!
			throw new ServiceException("ERR_MSG_FREIGHT_APP_002");

		}

		// 预计收货
		if (null != freightApp.getEditFlg() && freightApp.getEditFlg() == 1) {
			_dbFreightApply.setExpReceiptDate(freightApp.getExpReceiptDate());
		}
		// 实际收货
		if (null != freightApp.getEditFlg() && freightApp.getEditFlg() == 2) {
			_dbFreightApply.setActReceiptDate(DateUtils.getCurFormatDate("yyyy-MM-dd hh:mm"));
		}
		// 预计送货
		if (null != freightApp.getEditFlg() && freightApp.getEditFlg() == 3) {
			_dbFreightApply.setExpDeliveryDate(freightApp.getExpDeliveryDate());
		}
		// 实际送货
		if (null != freightApp.getEditFlg() && freightApp.getEditFlg() == 4) {
			_dbFreightApply.setActDeliveryDate(DateUtils.getCurFormatDate("yyyy-MM-dd hh:mm"));
		}
		freightApplyJpaDao.save(_dbFreightApply);
	}

	/**
	 * 取得已申请未审批（货运信息）数量
	 * 
	 * @return
	 */
	public int getApplyNotApprovalCount() {
		return freightApplyMyBatisDao.getApplyNotApprovalCount();
	}

	/**
	 * 取得已审批未完成（货运信息）数量
	 * 
	 * @return
	 */
	public int getApprovalNotCompleteCount() {
		return freightApplyMyBatisDao.getApprovalNotCompleteCount();
	}

	/**
	 * 取得预计收货（货运信息）数量
	 * 
	 * @return
	 */
	public int getExpReceiptCount() {
		return freightApplyMyBatisDao.getExpReceiptCount();
	}

	/**
	 * 取得预计送货（货运信息）数量
	 * 
	 * @return
	 */
	public int getExpDeliveryCount() {
		return freightApplyMyBatisDao.getExpDeliveryCount();
	}

}