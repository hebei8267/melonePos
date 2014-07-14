package com.tjhx.service.accounts;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.CashRunCouponJpaDao;
import com.tjhx.dao.accounts.CashRunCouponMyBatisDao;
import com.tjhx.entity.accounts.CashRunCoupon;
import com.tjhx.entity.order.Coupon;
import com.tjhx.service.order.CouponManager;

@Service
@Transactional(readOnly = true)
public class CashRunCouponManager {
	@Resource
	private CouponManager couponManager;
	@Resource
	private CashRunCouponJpaDao cashRunCouponJpaDao;
	@Resource
	private CashRunCouponMyBatisDao cashRunCouponMyBatisDao;

	/**
	 * 删除销售流水-代金卷明细信息
	 * 
	 * @param uuid 销售流水-代金卷明细编号
	 */
	@Transactional(readOnly = false)
	public void delCashRunCoupon(String orgId, String optDate, Integer jobType) {
		CashRunCoupon param = new CashRunCoupon();
		param.setOrgId(orgId);
		param.setOptDate(optDate);
		param.setJobType(jobType);
		cashRunCouponMyBatisDao.delCashRunCouponInfo(param);
	}

	/**
	 * 添加新销售流水-代金卷明细信息(先作删除操作)
	 * 
	 * @param orgId
	 * @param optDate
	 * @param jobType
	 * @param couponNo
	 * @param couponValue
	 * @return 代金卷实际值
	 */
	@Transactional(readOnly = false)
	public BigDecimal saveCashRunCoupon(String orgId, String optDate, Integer jobType, String couponNo, BigDecimal couponValue) {

		CashRunCoupon _cashRunCoupon = new CashRunCoupon();
		// 机构编号
		_cashRunCoupon.setOrgId(orgId);
		// 日期
		_cashRunCoupon.setOptDate(optDate);
		// 日期-年
		_cashRunCoupon.setOptDateY(DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy"));
		// 日期-月
		_cashRunCoupon.setOptDateM(DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM"));
		// 上班类型(1早班、2晚班、4全天班)
		_cashRunCoupon.setJobType(jobType);
		// 代金卷编号
		_cashRunCoupon.setCouponNo(couponNo);
		// 代金卷面值
		_cashRunCoupon.setCouponValue(couponValue);
		// 代金卷实际值
		_cashRunCoupon.setCouponCashValue(calCouponCashValue(couponNo, couponValue));

		cashRunCouponJpaDao.save(_cashRunCoupon);

		return _cashRunCoupon.getCouponCashValue();
	}

	/**
	 * 计算代金卷实际值
	 * 
	 * @param cashRun
	 * @return
	 */
	private BigDecimal calCouponCashValue(String couponNo, BigDecimal couponValue) {
		if (StringUtils.isNotBlank(couponNo)) {
			Coupon coupon = couponManager.getOneByCouponNo(couponNo);

			return coupon.getRate().multiply(couponValue);
		}

		return new BigDecimal("0");
	}

	/**
	 * 取得销售流水-代金卷明细列表
	 * 
	 * @param orgId
	 * @param optDate
	 * @param jobType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CashRunCoupon> getCashRunCouponList(String orgId, String optDate, Integer jobType) {
		return (List<CashRunCoupon>) cashRunCouponJpaDao.getCashRunCouponList(orgId, optDate, jobType);
	}
}