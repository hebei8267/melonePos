/**
 * 
 */
package com.tjhx.service.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.order.CouponJpaDao;
import com.tjhx.dao.order.CouponMyBatisDao;
import com.tjhx.entity.order.Coupon;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

/**
 * 代金卷服务类
 */
@Service
@Transactional(readOnly = true)
public class CouponManager {
	@Resource
	private SpyMemcachedClient spyMemcachedClient;
	@Resource
	private CouponJpaDao couponJpaDao;
	@Resource
	private CouponMyBatisDao couponMyBatisDao;

	/**
	 * 删除代金卷
	 * 
	 * @param uuid 代金卷uuid
	 */
	@Transactional(readOnly = false)
	public void delCouponByUuid(int uuid) {
		// 消除代金卷信息缓存
		spyMemcachedClient.delete(MemcachedObjectType.COUPON_TYPE_LIST.getObjKey());

		Coupon _coupon = getCouponByUuid(uuid);

		// 删除指定代金卷信息
		delCouponInfo(_coupon.getCouponNo());
	}

	/**
	 * 取得代金卷信息列表
	 * 
	 * @return 代金卷信息列表
	 */
	public List<Coupon> getAllCouponList() {
		@SuppressWarnings("unchecked")
		List<Coupon> _list = (List<Coupon>) couponJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "couponNo"),
				new Sort.Order(Sort.Direction.ASC, "orgId")));

		List<Coupon> _relist = new ArrayList<Coupon>();

		String _tmpCouponNo = null;
		Coupon _tmpCoupon = null;
		for (Coupon _coupon : _list) {
			if (!_coupon.getCouponNo().equals(_tmpCouponNo)) {
				_tmpCouponNo = _coupon.getCouponNo();

				_tmpCoupon = new Coupon();

				_tmpCoupon.setUuid(_coupon.getUuid());
				// 代金卷编号
				_tmpCoupon.setCouponNo(_coupon.getCouponNo());
				// 名称
				_tmpCoupon.setName(_coupon.getName());
				// 汇率
				_tmpCoupon.setRate(_coupon.getRate());
				// 日销售合计是否归集
				_tmpCoupon.setSubTotalFlg(_coupon.getSubTotalFlg());
				// 删除标记
				_tmpCoupon.setDelFlg(_coupon.getDelFlg());

				_relist.add(_tmpCoupon);
			}

			if (StringUtils.isBlank(_tmpCoupon.getOrgId())) {
				_tmpCoupon.setOrgId(_coupon.getOrgId().substring(3));
			} else {
				_tmpCoupon.setOrgId(_tmpCoupon.getOrgId() + "," + _coupon.getOrgId().substring(3));
			}

		}
		return _relist;
	}

	/**
	 * 根据编号取得代金卷信息
	 * 
	 * @param uuid 代金卷编号
	 * @return 代金卷信息
	 */
	public Coupon getCouponByUuid(Integer uuid) {
		return couponJpaDao.findOne(uuid);
	}

	/**
	 * 取得代金卷信息
	 * 
	 * @param couponNo 代金卷编号
	 * @return 代金卷信息
	 */
	public List<Coupon> findByCouponNo(String couponNo) {
		return couponJpaDao.findByCouponNo(couponNo, new Sort(new Sort.Order(Sort.Direction.ASC, "orgId")));
	}

	/**
	 * 添加新代金卷信息
	 * 
	 * @param coupon 代金卷信息
	 */
	@Transactional(readOnly = false)
	public void addNewCoupon(Coupon coupon) {
		// 消除代金卷信息缓存
		spyMemcachedClient.delete(MemcachedObjectType.COUPON_TYPE_LIST.getObjKey());

		List<Coupon> _dbCouponList = findByCouponNo(coupon.getCouponNo());
		// 该代金卷已存在!
		if (null != _dbCouponList && _dbCouponList.size() > 0) {
			throw new ServiceException("ERR_MSG_COUPON_001");
		}

		String[] appOrgIds = coupon.getAppOrgIds();
		for (String appOrgId : appOrgIds) {
			Coupon _coupon = new Coupon();

			// 代金卷编号
			_coupon.setCouponNo(coupon.getCouponNo());
			// 名称
			_coupon.setName(coupon.getName());
			// 汇率
			_coupon.setRate(coupon.getRate());
			// 适用机构编号
			_coupon.setOrgId(appOrgId);
			// 日销售合计是否归集
			_coupon.setSubTotalFlg(coupon.getSubTotalFlg());
			// 删除标记
			_coupon.setDelFlg(coupon.getDelFlg());
			// 备注
			_coupon.setDescTxt(coupon.getDescTxt());

			couponJpaDao.save(_coupon);
		}

	}

	/**
	 * 更新代金卷信息
	 * 
	 * @param coupon 代金卷信息
	 */
	@Transactional(readOnly = false)
	public void updateCoupon(Coupon coupon) {
		// 消除代金卷信息缓存
		spyMemcachedClient.delete(MemcachedObjectType.COUPON_TYPE_LIST.getObjKey());

		List<Coupon> _dbCouponList = findByCouponNo(coupon.getCouponNo());
		// 该代金卷不存在!
		if (null == _dbCouponList || _dbCouponList.size() == 0) {
			throw new ServiceException("ERR_MSG_COUPON_002");
		}

		// 删除指定代金卷信息
		delCouponInfo(coupon.getCouponNo());

		String[] appOrgIds = coupon.getAppOrgIds();
		for (String appOrgId : appOrgIds) {
			Coupon _coupon = new Coupon();

			// 代金卷编号
			_coupon.setCouponNo(coupon.getCouponNo());
			// 名称
			_coupon.setName(coupon.getName());
			// 汇率
			_coupon.setRate(coupon.getRate());
			// 适用机构编号
			_coupon.setOrgId(appOrgId);
			// 日销售合计是否归集
			_coupon.setSubTotalFlg(coupon.getSubTotalFlg());
			// 删除标记
			_coupon.setDelFlg(coupon.getDelFlg());
			// 备注
			_coupon.setDescTxt(coupon.getDescTxt());

			couponJpaDao.save(_coupon);
		}
	}

	/**
	 * 删除指定代金卷信息
	 * 
	 * @param couponNo
	 */
	@Transactional(readOnly = false)
	public void delCouponInfo(String couponNo) {
		// 删除指定代金卷信息
		couponMyBatisDao.delCouponInfo(couponNo);
	}
}
