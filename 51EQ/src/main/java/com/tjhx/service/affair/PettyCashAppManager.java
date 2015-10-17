/**
 * 
 */
package com.tjhx.service.affair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.PettyCashAppJpaDao;
import com.tjhx.dao.affair.PettyCashAppMyBatisDao;
import com.tjhx.entity.affair.PettyCashApp;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class PettyCashAppManager {
	@Resource
	private PettyCashAppJpaDao pettyCashAppJpaDao;
	@Resource
	private PettyCashAppMyBatisDao pettyCashAppMyBatisDao;

	public List<PettyCashApp> getPettyCashAppList(String appNo, String optDateShowStart, String optDateShowEnd,
			String appPerName, Integer appPer, boolean managerFlg) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("appNo", appNo);
		param.put("optDateShowStart", optDateShowStart);
		param.put("optDateShowEnd", optDateShowEnd);

		if (StringUtils.isNotBlank(appPerName)) {
			appPerName = "%" + appPerName + "%";
			param.put("appPerName", appPerName);
		}

		param.put("appPer", appPer);
		param.put("managerFlg", managerFlg);

		return pettyCashAppMyBatisDao.getPettyCashAppList(param);
	}

	/**
	 * @param pettyCashApp
	 */
	@Transactional(readOnly = false)
	public void savePettyCashApp(PettyCashApp pettyCashApp, boolean confirmFlg, boolean refuseFlg) {
		// 申请时间
		pettyCashApp.setAppDate(DateUtils.transDateFormat(pettyCashApp.getAppDate(), "yyyy-MM-dd", "yyyyMMdd"));
		// 付款期限
		pettyCashApp.setPaymentPeriod(DateUtils.transDateFormat(pettyCashApp.getPaymentPeriod(), "yyyy-MM-dd", "yyyyMMdd"));

		if (null == pettyCashApp.getUuid()) {// 新增操作
			addPettyCashApp(pettyCashApp, confirmFlg);
		} else {// 修改操作
			editPettyCashApp(pettyCashApp, confirmFlg, refuseFlg);
		}

	}

	/**
	 * @param pettyCashApp
	 */
	@Transactional(readOnly = false)
	private void editPettyCashApp(PettyCashApp pettyCashApp, boolean confirmFlg, boolean refuseFlg) {
		PettyCashApp _dbPettyCashApp = pettyCashAppJpaDao.findOne(pettyCashApp.getUuid());
		if (null == _dbPettyCashApp) {
			return;
		}

		// 申请审批状态00-申请
		if ("00".equals(_dbPettyCashApp.getAppState())) {
			// 付款期限
			_dbPettyCashApp.setPaymentPeriod(pettyCashApp.getPaymentPeriod());
			// 请款金额
			_dbPettyCashApp.setAmount(pettyCashApp.getAmount());
			// 请款事由
			_dbPettyCashApp.setReason(pettyCashApp.getReason());
			// 付款方式
			_dbPettyCashApp.setPaymentMode(pettyCashApp.getPaymentMode());
			// 付款方式描述
			_dbPettyCashApp.setPaymentExplain(pettyCashApp.getPaymentExplain());
		}

		// 申请审批状态01-1审
		if ("01".equals(_dbPettyCashApp.getAppState())) {
			// 审批人1
			_dbPettyCashApp.setApprovalPer1(pettyCashApp.getApprovalPer1());
			// 审批人1批注
			_dbPettyCashApp.setApprovalPerComment1(pettyCashApp.getApprovalPerComment1());
			// 审批金额
			_dbPettyCashApp.setConfirmAmount(pettyCashApp.getConfirmAmount());
		}
		// 申请审批状态02-2审
		if ("02".equals(_dbPettyCashApp.getAppState())) {
			// 审批人2
			_dbPettyCashApp.setApprovalPer2(pettyCashApp.getApprovalPer2());
			// 审批人2批注
			_dbPettyCashApp.setApprovalPerComment2(pettyCashApp.getApprovalPerComment2());
			// 审批金额
			_dbPettyCashApp.setConfirmAmount(pettyCashApp.getConfirmAmount());
		}
		// 申请审批状态03-3审
		if ("03".equals(_dbPettyCashApp.getAppState())) {
			// 审批人3
			_dbPettyCashApp.setApprovalPer3(pettyCashApp.getApprovalPer3());
			// 审批人3批注
			_dbPettyCashApp.setApprovalPerComment3(pettyCashApp.getApprovalPerComment3());
			// 审批金额
			_dbPettyCashApp.setConfirmAmount(pettyCashApp.getConfirmAmount());
		}

		commonProcess(_dbPettyCashApp, confirmFlg);

		// 拒绝标记
		if (refuseFlg) {
			_dbPettyCashApp.setRefuseFlg(refuseFlg);
		}

		pettyCashAppJpaDao.save(_dbPettyCashApp);
	}

	/**
	 * 新建备用金申请
	 * 
	 * @param pettyCashApp
	 */
	@Transactional(readOnly = false)
	private void addPettyCashApp(PettyCashApp pettyCashApp, boolean confirmFlg) {
		int appCount = pettyCashAppMyBatisDao.getPettyCashAppCount(DateUtils.getCurFormatDate("yyyyMMdd"));
		// 备用金申请编号
		pettyCashApp.setAppNo(DateUtils.getCurFormatDate("yyyyMMdd") + "-" + String.format("%03d", appCount + 1));
		// 申请审批状态00-申请01-1审02-2审03-3审99-归档
		pettyCashApp.setAppState("00");

		commonProcess(pettyCashApp, confirmFlg);

		pettyCashAppJpaDao.save(pettyCashApp);

	}

	private void commonProcess(PettyCashApp pettyCashApp, boolean confirmFlg) {
		if (pettyCashApp.getAmount().doubleValue() <= 5000) {
			// 申请审批级别01-1审
			pettyCashApp.setAppLevel("01");
		} else if (pettyCashApp.getAmount().doubleValue() <= 20000) {
			// 申请审批级别 02-2审
			pettyCashApp.setAppLevel("02");
		} else {
			// 申请审批级别 03-3审
			pettyCashApp.setAppLevel("03");
		}

		if (confirmFlg) {
			int appState = Integer.valueOf(pettyCashApp.getAppState()) + 1;

			if (appState > Integer.valueOf(pettyCashApp.getAppLevel())) {
				appState = 99;
			}
			pettyCashApp.setAppState(String.format("%02d", appState));
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public PettyCashApp getPettyCashAppByUuid(Integer id) {
		return pettyCashAppMyBatisDao.findPettyCashAppByUuid(id);
	}

	/**
	 * 备用金申请/审批--归档保存
	 * 
	 * @param pettyCashApp
	 * @param b
	 */
	@Transactional(readOnly = false)
	public void fileSavePettyCashApp(PettyCashApp pettyCashApp) {
		PettyCashApp _dbPettyCashApp = pettyCashAppJpaDao.findOne(pettyCashApp.getUuid());
		if (null == _dbPettyCashApp) {
			return;
		}

		// 申请审批状态99-归档
		if ("99".equals(_dbPettyCashApp.getAppState())) {
			_dbPettyCashApp.setAppOrg(pettyCashApp.getAppOrg());
		}

		pettyCashAppJpaDao.save(_dbPettyCashApp);

	}

	/**
	 * @param parseInt
	 */
	@Transactional(readOnly = false)
	public void delPettyCashAppByUuid(int uuid) {
		pettyCashAppJpaDao.delete(uuid);
	}

	/**
	 * @param appNo
	 * @param transDateFormat
	 * @param transDateFormat2
	 * @param appPerName
	 * @param uuid
	 * @param managerFlg
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public String createPettyCashAppFile(String appNo, String optDateShowStart, String optDateShowEnd, String appPerName,
			Integer appPer, boolean managerFlg) throws ParsePropertyException, InvalidFormatException, IOException {
		List<PettyCashApp> list = getPettyCashAppList(appNo, optDateShowStart, optDateShowEnd, appPerName, appPer, managerFlg);

		List<PettyCashApp> _list = Lists.newArrayList();
		for (PettyCashApp pettyCashApp : list) {
			if ("99".equals(pettyCashApp.getAppState())) {
				_list.add(pettyCashApp);
			}
		}
		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pettyCashAppList", _list);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xlsx";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_PETTY_CASH_APP, map, tmpFilePath);

		return tmpFileName;
	}

	private final static String XML_CONFIG_PETTY_CASH_APP = "/excel/Petty_Cash_App_Template.xlsx";
}
