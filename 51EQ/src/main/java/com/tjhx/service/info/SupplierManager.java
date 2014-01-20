package com.tjhx.service.info;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.info.RegionJpaDao;
import com.tjhx.dao.info.SupplierJpaDao;
import com.tjhx.dao.info.SupplierSignBillJpaDao;
import com.tjhx.dao.info.SupplierSignBillMyBatisDao;
import com.tjhx.daobw.SupplierCustomMyBatisDao;
import com.tjhx.entity.bw.SupplierCustom;
import com.tjhx.entity.info.Region;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.info.SupplierSignBill;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class SupplierManager {
	private static Logger logger = LoggerFactory.getLogger(SupplierManager.class);
	@Resource
	private SupplierJpaDao supplierJpaDao;
	@Resource
	private RegionJpaDao regionJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;
	@Resource
	private SupplierCustomMyBatisDao supplierCustomMyBatisDao;
	@Resource
	private SupplierSignBillMyBatisDao supplierSignBillMyBatisDao;
	@Resource
	private SupplierSignBillJpaDao supplierSignBillJpaDao;

	/**
	 * 取得所有货品供应商信息
	 * 
	 * @return 货品供应商信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Supplier> getAllSupplier() {
		List<Supplier> _supplierList = spyMemcachedClient.get(MemcachedObjectType.SUPPLIER_LIST.getObjKey());

		if (null == _supplierList) {
			// 从数据库中取出全量供应商信息(List格式)
			_supplierList = (List<Supplier>) supplierJpaDao.findSupplierList(new Sort(new Sort.Order(
					Sort.Direction.ASC, "name")));
			// 将供应商信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.SUPPLIER_LIST.getObjKey(),
					MemcachedObjectType.SUPPLIER_LIST.getExpiredTime(), _supplierList);

			logger.debug("供应商信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出供应商信息");
		}
		return _supplierList;
	}

	/**
	 * 取得所有货品供应商信息
	 * 
	 * @return 货品供应商信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Supplier> getAllSupplier_DB() {

		return (List<Supplier>) supplierJpaDao.findSupplierList(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得货品供应商信息
	 * 
	 * @param uuid 货品供应商编号
	 * @return 货品供应商信息
	 */
	public Supplier getSupplierByUuid(Integer uuid) {
		return supplierJpaDao.findOne(uuid);
	}

	/**
	 * 取得货品供应商信息
	 * 
	 * @param name 名称
	 * @return 货品供应商信息
	 */
	public Supplier findByName(String name) {
		return supplierJpaDao.findByName(name);
	}

	/**
	 * 删除货品供应商信息
	 * 
	 * @param uuid 货品供应商编号
	 */
	@Transactional(readOnly = false)
	public void delSupplierByUuid(Integer uuid) {
		supplierJpaDao.delete(uuid);

		spyMemcachedClient.delete(MemcachedObjectType.SUPPLIER_LIST.getObjKey());
	}

	/**
	 * 添加新货品供应商信息
	 * 
	 * @param supplier 货品供应商信息
	 */
	@Transactional(readOnly = false)
	public void addNewSupplier(Supplier supplier) {
		Supplier _dbSupplier = findByName(supplier.getName());
		// 该货品供应商已存在!
		if (null != _dbSupplier) {
			throw new ServiceException("ERR_MSG_SUP_001");
		}

		Region region = regionJpaDao.findByCode(supplier.getRegionCode());
		supplier.setRegion(region);

		supplierJpaDao.save(supplier);

		spyMemcachedClient.delete(MemcachedObjectType.SUPPLIER_LIST.getObjKey());
	}

	/**
	 * 更新货品供应商信息
	 * 
	 * @param supplier 货品供应商信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateSupplier(Supplier supplier) throws IllegalAccessException, InvocationTargetException {

		Supplier _dbSupplier = supplierJpaDao.findOne(supplier.getUuid());
		if (null == _dbSupplier) {
			// 货品供应商不存在!
			throw new ServiceException("ERR_MSG_USER_002");
		}
		// 供应商名称
		_dbSupplier.setName(supplier.getName());
		// 付款方式
		_dbSupplier.setPayType(supplier.getPayType());
		// 所在区域
		Region region = regionJpaDao.findByCode(supplier.getRegionCode());
		_dbSupplier.setRegion(region);

		supplierJpaDao.save(_dbSupplier);

		spyMemcachedClient.delete(MemcachedObjectType.SUPPLIER_LIST.getObjKey());
	}

	/**
	 * 同步百威供应商信息
	 */
	@Transactional(readOnly = false)
	public void bwDataSyn() {
		List<SupplierCustom> _bwList = supplierCustomMyBatisDao.getSupplierCustomList();
		List<Supplier> _supplierList = (List<Supplier>) supplierJpaDao.findAll();

		for (SupplierCustom supplierCustom : _bwList) {
			// 见SupplierCustom.java的equals实现
			if (_supplierList.contains(supplierCustom)) {
				// 更新供应商信息
				updateSupplierInfo(supplierCustom);
			} else {
				// 新增供应商信息
				addSupplierInfo(supplierCustom);
			}
		}
		spyMemcachedClient.delete(MemcachedObjectType.SUPPLIER_LIST.getObjKey());
	}

	/**
	 * 新增供应商信息
	 * 
	 * @param supplierCustom
	 */
	@Transactional(readOnly = false)
	private void addSupplierInfo(SupplierCustom supplierCustom) {
		Supplier _supplier = new Supplier();
		// 供应商编号-百威
		_supplier.setSupplierBwId(supplierCustom.getSupcustNo());
		// 供应商名称
		_supplier.setName(supplierCustom.getSupName());
		// 所在区域
		Region region = regionJpaDao.findByCode(supplierCustom.getRegionNo());
		_supplier.setRegion(region);
		// 显示标记 0-正常 1-删除
		_supplier.setDelFlg(getDelFlg(supplierCustom.getDisplayFlag()));

		supplierJpaDao.save(_supplier);

	}

	/**
	 * 更新供应商信息
	 * 
	 * @param supplierCustom
	 */
	@Transactional(readOnly = false)
	private void updateSupplierInfo(SupplierCustom supplierCustom) {
		Supplier _supplier = supplierJpaDao.findBySupplierBwId(supplierCustom.getSupcustNo());
		// 供应商名称
		_supplier.setName(supplierCustom.getSupName());
		// 所在区域
		Region region = regionJpaDao.findByCode(supplierCustom.getRegionNo());
		_supplier.setRegion(region);
		// 显示标记 0-正常 1-删除
		_supplier.setDelFlg(getDelFlg(supplierCustom.getDisplayFlag()));

		supplierJpaDao.save(_supplier);
	}

	private String getDelFlg(String displayFlag) {
		// 显示标记0-不显示1-显示
		if ("0".equals(displayFlag)) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 同步挂账供应商信息
	 */
	public void signBillSupDataSyn(String[] supIdArray) {
		// 删除所有挂账供应商信息
		supplierSignBillMyBatisDao.delAllSignBillSupplierInfo();

		for (int i = 0; i < supIdArray.length; i++) {
			Supplier _supplier = supplierJpaDao.findBySupplierBwId(supIdArray[i]);

			SupplierSignBill _supplierSignBill = new SupplierSignBill();
			// 供应商编号-百威
			_supplierSignBill.setSupplierBwId(_supplier.getSupplierBwId());
			// 供应商名称
			_supplierSignBill.setName(_supplier.getName());

			supplierSignBillJpaDao.save(_supplierSignBill);
		}
	}

	// /**
	// * 自定义比较方法
	// *
	// * @param supplierCustom
	// * @param supplier
	// * @return
	// */
	// private boolean myEquals(SupplierCustom supplierCustom, Supplier
	// supplier) {
	// if (supplierCustom.getSupcustNo().equals(supplier.getSupplierBwId())) {
	// return true;
	// }
	//
	// return false;
	// }
}