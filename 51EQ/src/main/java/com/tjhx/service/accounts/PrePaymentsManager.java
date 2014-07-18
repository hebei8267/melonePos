package com.tjhx.service.accounts;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.PrePaymentsJpaDao;
import com.tjhx.dao.accounts.PrePaymentsMyBatisDao;
import com.tjhx.entity.accounts.PrePayments;

@Service
@Transactional(readOnly = true)
public class PrePaymentsManager {
	@Resource
	private PrePaymentsJpaDao prePaymentsJpaDao;
	@Resource
	private PrePaymentsMyBatisDao prePaymentsMyBatisDao;

	/**
	 * 取得顾客/会员预付款（充值、消费）信息---总部/门店
	 * 
	 * @param orgId 机构编号
	 * @param optDateShowStart 业务日期-开始YYYYMMDD
	 * @param optDateShowEnd 业务日期-结束YYYYMMDD
	 * @param phoneNum (顾客)电话号码
	 * @return
	 */
	public List<PrePayments> getPrePaymentsList(String orgId, String optDateShowStart, String optDateShowEnd, String phoneNum) {
		PrePayments param = new PrePayments();
		param.setOrgId(orgId);
		param.setOptDateShow_start(optDateShowStart);
		param.setOptDateShow_end(optDateShowEnd);

		if (StringUtils.isBlank(phoneNum)) {
			return prePaymentsMyBatisDao.getPrePaymentsList(param);
		} else {
			param.setPhoneNum("%" + phoneNum + "%");

			return prePaymentsMyBatisDao.getPrePaymentsList(param);
		}
	}

	/**
	 * 根据编号取得顾客/会员预付款（充值、消费）信息
	 * 
	 * @param uuid 顾客/会员预付款（充值、消费）信息编号
	 * @return 顾客/会员预付款（充值、消费）信息
	 */
	public PrePayments getPrePaymentsByUuid(Integer uuid) {
		return prePaymentsJpaDao.findOne(uuid);
	}

	/**
	 * 删除顾客/会员预付款（充值、消费）信息
	 * 
	 * @param uuid 顾客/会员预付款（充值、消费）信息编号
	 */
	@Transactional(readOnly = false)
	public void delPrePaymentsByUuid(Integer uuid) {
		prePaymentsJpaDao.delete(uuid);
	}

	/**
	 * 添加新顾客/会员预付款（充值、消费）信息
	 * 
	 * @param prePayments 顾客/会员预付款（充值、消费）信息
	 */
	@Transactional(readOnly = false)
	public void addNewPrePayments(PrePayments prePayments) {
		// 日期
		prePayments.setOptDate(DateUtils.transDateFormat(prePayments.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd"));
		// 日期-年
		prePayments.setOptDateY(DateUtils.transDateFormat(prePayments.getOptDateShow(), "yyyy-MM-dd", "yyyy"));
		// 日期-月
		prePayments.setOptDateM(DateUtils.transDateFormat(prePayments.getOptDateShow(), "yyyy-MM-dd", "MM"));

		prePaymentsJpaDao.save(prePayments);
	}

	/**
	 * 取得顾客/会员预付款（现金充值）合计信息
	 * 
	 * @param orgId 机构信息
	 * @param optDate 业务日期
	 * @param jobType 班次类型
	 * @return
	 */
	public BigDecimal getOrgPrePaymentsInfo_By_Cash(String orgId, String optDate, Integer jobType) {
		return prePaymentsJpaDao.getOrgPrePaymentsInfo_By_Cash(orgId, optDate, jobType);
	}

	/**
	 * 取得顾客/会员预付款（现金充值）合计信息
	 * 
	 * @param orgId 机构信息
	 * @param optDate 业务日期
	 * @return
	 */
	public BigDecimal getOrgPrePaymentsInfo_By_Cash(String orgId, String optDate) {
		return prePaymentsJpaDao.getOrgPrePaymentsInfo_By_Cash(orgId, optDate);
	}

	/**
	 * 取得顾客/会员预付款（刷卡充值）合计信息
	 * 
	 * @param orgId 机构信息
	 * @param optDate 业务日期
	 * @param jobType 班次类型
	 * @return
	 */
	public BigDecimal getOrgPrePaymentsInfo_By_Card(String orgId, String optDate, Integer jobType) {
		return prePaymentsJpaDao.getOrgPrePaymentsInfo_By_Card(orgId, optDate, jobType);
	}

	/**
	 * 取得顾客/会员预付款（刷卡充值）合计信息
	 * 
	 * @param orgId 机构信息
	 * @param optDate 业务日期
	 * @return
	 */
	public BigDecimal getOrgPrePaymentsInfo_By_Card(String orgId, String optDate) {
		return prePaymentsJpaDao.getOrgPrePaymentsInfo_By_Card(orgId, optDate);
	}
}