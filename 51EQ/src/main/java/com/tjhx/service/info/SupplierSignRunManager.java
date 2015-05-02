package com.tjhx.service.info;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.dao.info.SupplierSignMyBatisDao;
import com.tjhx.dao.info.SupplierSignRunJpaDao;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.info.SupplierSignRun;
import com.tjhx.entity.info.SupplierSignRun_Show;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class SupplierSignRunManager {
	@Resource
	private SupplierSignMyBatisDao supplierSignMyBatisDao;
	@Resource
	private SupplierSignRunJpaDao supplierSignRunJpaDao;

	@Resource
	private SupplierManager supplierManager;

	public List<Supplier> getSupplierList() {
		return supplierSignMyBatisDao.getSupplierList();
	}

	/**
	 * 取得特殊标记-货品供应商信息列表
	 * 
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public List<SupplierSignRun_Show> getSupplierSignRunList(String optYear, String supplierBwId) throws IllegalAccessException,
			InvocationTargetException {
		List<Supplier> _supList = null;

		if (StringUtils.isNotBlank(supplierBwId)) {
			_supList = new ArrayList<Supplier>();
			_supList.add(supplierManager.getSupplierByBwId(supplierBwId));
		} else {
			_supList = supplierSignMyBatisDao.getSupplierListByYear(optYear);
		}

		List<SupplierSignRun_Show> _supSignRunList = initSupplierSignRunList(_supList, optYear);

		return _supSignRunList;
	}

	private void copyProperties(SupplierSignRun destObj, List<SupplierSignRun> origObjList) throws IllegalAccessException,
			InvocationTargetException {

		int _i = origObjList.indexOf(destObj);

		if (-1 != _i) {
			BeanUtils.copyProperties(destObj, origObjList.get(_i));
		}
	}

	/**
	 * 初始化特殊标记-货品供应商 流水信息（显示）
	 * 
	 * @param _supList
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private List<SupplierSignRun_Show> initSupplierSignRunList(List<Supplier> _supList, String optYear)
			throws IllegalAccessException, InvocationTargetException {
		List<SupplierSignRun_Show> _list = new ArrayList<SupplierSignRun_Show>();

		// 初始化所有显示对象（特殊标记-货品供应商 流水信息）
		for (Supplier supplier : _supList) {
			SupplierSignRun_Show run = new SupplierSignRun_Show(supplier.getSupplierBwId(), supplier.getName(), optYear);
			_list.add(run);
		}

		// 取得数据库中保存对象（特殊标记-货品供应商 流水信息）
		List<SupplierSignRun> _dblist = supplierSignRunJpaDao.findAllByOptYear(optYear);

		for (SupplierSignRun_Show _showObj : _list) {
			for (SupplierSignRun loanObj : _showObj.getLoanList()) {
				copyProperties(loanObj, _dblist);
			}
			for (SupplierSignRun noticeObj : _showObj.getNoticeList()) {
				copyProperties(noticeObj, _dblist);
			}
			for (SupplierSignRun checkObj : _showObj.getCheckSuccessList()) {
				copyProperties(checkObj, _dblist);
			}
			for (SupplierSignRun payObj : _showObj.getPayList()) {
				copyProperties(payObj, _dblist);
			}
			for (SupplierSignRun appObj : _showObj.getAppList()) {
				copyProperties(appObj, _dblist);
			}
			for (SupplierSignRun ConfirmObj : _showObj.getConfirmList()) {
				copyProperties(ConfirmObj, _dblist);
			}

			// 计算最终结算付款信息
			_showObj.calLastSupplierSignRun();
		}

		return _list;
	}

	/**
	 * 根据业主主键取得特殊标记-货品供应商 流水信息
	 * 
	 * @return
	 */
	public SupplierSignRun findSupplierSignRunByNaturalId(String supplierBwId, String optDateY, String optDateM, String runType) {
		return supplierSignRunJpaDao.findSupplierSignRunByNaturalId(supplierBwId, optDateY, optDateM, runType);
	}

	/**
	 * 保存特殊标记-货品供应商 流水信息
	 * 
	 * <pre>
	 * 特殊标记-货品供应商 流水类型
	 * 1. 赊购挂账
	 * 2. 对账通知
	 * 3. 对账完成
	 * 4. 结算付款
	 * 5. 退货申请
	 * 6. 退货确认
	 * </pre>
	 * 
	 * @param run
	 */
	@Transactional(readOnly = false)
	public void saveRunInfo(SupplierSignRun run) {
		SupplierSignRun _dbRun = findSupplierSignRunByNaturalId(run.getSupplierBwId(), run.getOptDateY(), run.getOptDateM(),
				run.getRunType());

		if (null == _dbRun) {// 新增
			if ("1".equals(run.getRunType())) {// 赊购挂账
				run.setLoanFlg("1");
			}
			supplierSignRunJpaDao.save(run);
		} else {// 更新
			if ("2".equals(run.getRunType())) {// 对账通知
				_dbRun.setCheckNoticeDate(run.getCheckNoticeDate());
				_dbRun.setNoticeMode(run.getNoticeMode());
			} else if ("3".equals(run.getRunType())) {// 对账完成
				_dbRun.setCheckDate(run.getCheckDate());
				_dbRun.setCheckAmt(run.getCheckAmt());
			} else if ("4".equals(run.getRunType())) {// 结算付款
				_dbRun.setPaymentDate(run.getPaymentDate());
				_dbRun.setPaymentAmt(run.getPaymentAmt());
			} else if ("5".equals(run.getRunType())) {// 退货申请
				_dbRun.setAppDate(run.getAppDate());
				_dbRun.setAppAmt(run.getAppAmt());
			} else if ("6".equals(run.getRunType())) {// 退货确认
				_dbRun.setConfirmDate(run.getConfirmDate());
				_dbRun.setConfirmAmt(run.getConfirmAmt());
			}

			_dbRun.setDescTxt(run.getDescTxt());
			supplierSignRunJpaDao.save(_dbRun);
		}

	}

	/**
	 * 删除特殊标记-货品供应商 流水信息
	 * 
	 * @param run
	 */
	@Transactional(readOnly = false)
	public void delRunInfo(SupplierSignRun run) {
		SupplierSignRun _dbRun = findSupplierSignRunByNaturalId(run.getSupplierBwId(), run.getOptDateY(), run.getOptDateM(),
				run.getRunType());
		if (null != _dbRun) {
			supplierSignRunJpaDao.delete(_dbRun);
		}
	}

	/**
	 * 文件导出
	 * 
	 * @param optDateY
	 * @param supplierBwId
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws ParsePropertyException 
	 */
	public String createSupplierSignRunFile(String optDateY, String supplierBwId) throws IllegalAccessException,
			InvocationTargetException, ParsePropertyException, InvalidFormatException, IOException {
		List<SupplierSignRun_Show> _supSignRunList = getSupplierSignRunList(optDateY, supplierBwId);

		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("optDateY", optDateY);
		map.put("supSignRunList", _supSignRunList);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_SUPPLIER_SIGN_RUN, map, tmpFilePath);

		return tmpFileName;
	}

	private final static String XML_CONFIG_SUPPLIER_SIGN_RUN = "/excel/Supplier_Sign_Run_Template.xls";
}
