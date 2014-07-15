package com.tjhx.service.accounts;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.StoreRunJpaDao;
import com.tjhx.dao.accounts.StoreRunMyBatisDao;
import com.tjhx.dao.info.SupplierJpaDao;
import com.tjhx.entity.accounts.StoreRun;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.member.User;
import com.tjhx.service.ServiceException;
import com.tjhx.service.info.SupplierManager;

@Service
@Transactional(readOnly = true)
public class StoreRunManager {
	@Resource
	private StoreRunJpaDao storeRunJpaDao;
	@Resource
	private StoreRunMyBatisDao storeRunMyBatisDao;
	@Resource
	private SupplierJpaDao supplierJpaDao;
	@Resource
	private SupplierManager supplierManager;

	/**
	 * 取得所有货物入库流水信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyyMMdd)
	 * @return 货物入库流水信息列表
	 * @throws ParseException
	 */
	public List<StoreRun> getAllStoreRunByOrgId_1(String orgId, String currentDate) throws ParseException {
		String recordDateY = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "yyyy");

		String recordDateM = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "MM");

		return getAllStoreRunByOrgId(orgId, recordDateY, recordDateM);
	}

	/**
	 * 取得所有货物入库流水信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyy-MM)
	 * @return 货物入库流水信息列表
	 * @throws ParseException
	 */
	public List<StoreRun> getAllStoreRunByOrgId_2(String orgId, String currentDate) throws ParseException {
		String recordDateY = DateUtils.transDateFormat(currentDate, "yyyy-MM", "yyyy");

		String recordDateM = DateUtils.transDateFormat(currentDate, "yyyy-MM", "MM");

		return getAllStoreRunByOrgId(orgId, recordDateY, recordDateM);
	}

	/**
	 * 合计计算
	 * 
	 * @param cardRunList
	 * @return
	 */
	public StoreRun calTotal(List<StoreRun> storeRunList) {
		StoreRun _storeRun = new StoreRun();
		for (StoreRun storeRun : storeRunList) {
			// 开单金额
			_storeRun.setRecordAmt(_storeRun.getRecordAmt().add(storeRun.getRecordAmt()));
			// 入库金额
			_storeRun.setOptAmt(_storeRun.getOptAmt().add(storeRun.getOptAmt()));
		}
		return _storeRun;
	}

	@SuppressWarnings("unchecked")
	private List<StoreRun> getAllStoreRunByOrgId(String orgId, String recordDateY, String recordDateM)
			throws ParseException {

		List<StoreRun> _list = (List<StoreRun>) storeRunJpaDao.findByOrgId_RecordDateY_RecordDateM(orgId, recordDateY,
				recordDateM, new Sort(new Sort.Order(Sort.Direction.DESC, "recordDate")));

		// 取得供应商信息(Map格式)
		Map<String, Supplier> _supplierMap = getSupplierInfo();

		for (StoreRun storeRun : _list) {
			storeRun.autoSetEditFlg();
			storeRun.setSupplierName(_supplierMap.get(storeRun.getSupplierBwId()).getName());
		}

		return _list;
	}

	/**
	 * 取得供应商信息(Map格式)
	 * 
	 * @return 供应商信息
	 */
	private Map<String, Supplier> getSupplierInfo() {
		List<Supplier> _supplierList = supplierManager.getSupplierList();

		Map<String, Supplier> _supplierMap = new HashMap<String, Supplier>();
		for (Supplier _supplier : _supplierList) {
			_supplierMap.put(_supplier.getSupplierBwId(), _supplier);
		}

		return _supplierMap;
	}

	/**
	 * 根据编号取得货物入库流水信息
	 * 
	 * @param uuid 货物入库流水编号
	 * @return 货物入库流水信息
	 */
	public StoreRun getStoreRunByUuid(Integer uuid) {
		StoreRun storeRun = storeRunJpaDao.findOne(uuid);

		// 取得供应商信息(Map格式)
		Map<String, Supplier> _supplierMap = getSupplierInfo();
		storeRun.setSupplierName(_supplierMap.get(storeRun.getSupplierBwId()).getName());
		return storeRun;
	}

	/**
	 * 删除货物入库流水信息
	 * 
	 * @param uuid 货物入库流水编号
	 */
	@Transactional(readOnly = false)
	public void delStoreRunByUuid(Integer uuid) {
		storeRunJpaDao.delete(uuid);
	}

	/**
	 * 添加新货物入库流水信息
	 * 
	 * @param storeRun 货物入库流水信息
	 */
	@Transactional(readOnly = false)
	public void addNewStoreRun(StoreRun storeRun, User user) {

		StoreRun _dbStoreRun = storeRunJpaDao.findByOrgIdAndRecordNo(user.getOrganization().getId(),
				storeRun.getRecordNo());
		// 该货物入库流水已存在!
		if (null != _dbStoreRun) {
			throw new ServiceException("ERR_MSG_STORE_RUN_001");
		}

		// 机构编号
		storeRun.setOrgId(user.getOrganization().getId());
		// 供应商
		Supplier supplier = supplierJpaDao.findBySupplierBwId(storeRun.getSupplierBwId());
		storeRun.setSupplier(supplier);
		// 开单日期
		String recordDate = DateUtils.transDateFormat(storeRun.getRecordDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		storeRun.setRecordDate(recordDate);
		// 开单日期-年
		storeRun.setRecordDateY(DateUtils.transDateFormat(recordDate, "yyyyMMdd", "yyyy"));
		// 开单日期-月
		storeRun.setRecordDateM(DateUtils.transDateFormat(recordDate, "yyyyMMdd", "MM"));
		// 统筹日期
		String planDate = DateUtils.transDateFormat(storeRun.getPlanDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		storeRun.setPlanDate(planDate);
		// 统筹日期-月
		storeRun.setPlanDateY(DateUtils.transDateFormat(planDate, "yyyyMMdd", "yyyy"));
		// 统筹日期-年
		storeRun.setPlanDateM(DateUtils.transDateFormat(planDate, "yyyyMMdd", "MM"));
		// 入库日期
		String intoDate = DateUtils.transDateFormat(storeRun.getIntoDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		storeRun.setIntoDate(intoDate);
		// 入库日期-年
		storeRun.setIntoDateY(DateUtils.transDateFormat(intoDate, "yyyyMMdd", "yyyy"));
		// 入库日期-月
		storeRun.setIntoDateM(DateUtils.transDateFormat(intoDate, "yyyyMMdd", "MM"));

		storeRunJpaDao.save(storeRun);
	}

	/**
	 * 更新货物入库流水信息
	 * 
	 * @param storeRun 货物入库流水信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateStoreRun(StoreRun storeRun, User user) throws IllegalAccessException, InvocationTargetException {

		StoreRun _dbStoreRun = storeRunJpaDao.findOne(storeRun.getUuid());
		if (null == _dbStoreRun) {
			// 货物入库流水不存在!
			throw new ServiceException("ERR_MSG_STORE_RUN_002");
		}

		StoreRun _tmp_dbStoreRun = storeRunJpaDao.findByOrgIdAndRecordNo(user.getOrganization().getId(),
				storeRun.getRecordNo());
		// 该货物入库流水已存在!
		if (null != _tmp_dbStoreRun && !_tmp_dbStoreRun.getUuid().equals(_dbStoreRun.getUuid())) {
			throw new ServiceException("ERR_MSG_STORE_RUN_001");
		}

		// 供应商
		Supplier supplier = supplierJpaDao.findBySupplierBwId(storeRun.getSupplierBwId());
		_dbStoreRun.setSupplier(supplier);
		// 供应商编号-百威
		_dbStoreRun.setSupplierBwId(storeRun.getSupplierBwId());
		// 开单日期
		String recordDate = DateUtils.transDateFormat(storeRun.getRecordDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		_dbStoreRun.setRecordDate(recordDate);
		// 开单日期-显示
		_dbStoreRun.setRecordDateShow(storeRun.getRecordDateShow());
		// 开单日期-年
		_dbStoreRun.setRecordDateY(DateUtils.transDateFormat(recordDate, "yyyyMMdd", "yyyy"));
		// 开单日期-月
		_dbStoreRun.setRecordDateM(DateUtils.transDateFormat(recordDate, "yyyyMMdd", "MM"));
		// 统筹日期
		String planDate = DateUtils.transDateFormat(storeRun.getPlanDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		_dbStoreRun.setPlanDate(planDate);
		// 开单日期-显示
		_dbStoreRun.setPlanDateShow(storeRun.getPlanDateShow());
		// 统筹日期-月
		_dbStoreRun.setPlanDateY(DateUtils.transDateFormat(planDate, "yyyyMMdd", "yyyy"));
		// 统筹日期-年
		_dbStoreRun.setPlanDateM(DateUtils.transDateFormat(planDate, "yyyyMMdd", "MM"));
		// 入库类型
		_dbStoreRun.setStoreType(storeRun.getStoreType());
		// 入库日期
		String intoDate = DateUtils.transDateFormat(storeRun.getIntoDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		_dbStoreRun.setIntoDate(intoDate);
		// 入库日期-显示
		_dbStoreRun.setIntoDateShow(storeRun.getIntoDateShow());
		// 入库日期-年
		_dbStoreRun.setIntoDateY(DateUtils.transDateFormat(intoDate, "yyyyMMdd", "yyyy"));
		// 入库日期-月
		_dbStoreRun.setIntoDateM(DateUtils.transDateFormat(intoDate, "yyyyMMdd", "MM"));
		// 开单金额
		_dbStoreRun.setRecordAmt(storeRun.getRecordAmt());
		// 入库金额
		_dbStoreRun.setOptAmt(storeRun.getOptAmt());
		// 入库人名称
		_dbStoreRun.setOptPerName(storeRun.getOptPerName());
		// 备注
		_dbStoreRun.setDescTxt(storeRun.getDescTxt());

		storeRunJpaDao.save(_dbStoreRun);
	}

	/**
	 * 货物入库流水信息-审核确认
	 * 
	 * @param storeRun
	 */
	@Transactional(readOnly = false)
	public void auditConfirmStoreRun(Integer storeRunUuid) {
		StoreRun _dbStoreRun = storeRunJpaDao.findOne(storeRunUuid);
		if (null == _dbStoreRun) {
			// 货物入库流水不存在!
			throw new ServiceException("ERR_MSG_STORE_RUN_002");
		}

		_dbStoreRun.setAuditFlg(true);

		storeRunJpaDao.save(_dbStoreRun);
	}

	/**
	 * 取得货物入库流水信息列表
	 * 
	 * @param storeRun
	 * @return
	 */
	public List<StoreRun> searchReportList(StoreRun storeRun) {
		return storeRunMyBatisDao.getStoreRunList(storeRun);
	}
}