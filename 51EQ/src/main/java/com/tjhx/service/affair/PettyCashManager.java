package com.tjhx.service.affair;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.InspectJpaDao;
import com.tjhx.dao.affair.PettyCashJpaDao;
import com.tjhx.dao.affair.PettyCashMyBatisDao;
import com.tjhx.entity.affair.Inspect;
import com.tjhx.entity.affair.PettyCash;
import com.tjhx.entity.member.User;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class PettyCashManager {
	@Resource
	private PettyCashJpaDao pettyCashJpaDao;
	@Resource
	private PettyCashMyBatisDao pettyCashMyBatisDao;
	@Resource
	private InspectJpaDao inspectJpaDao;

	private final static String XML_CONFIG_PETTY_CASH = "/excel/Petty_Cash_Template.xls";

	/**
	 * 取得 门店备用金
	 * 
	 * @param uuid
	 * @return
	 */
	public PettyCash getPettyCashByOptUid(String optUid) {
		return pettyCashJpaDao.findByOptUid(optUid);
	}

	/**
	 * 新增 门店备用金（支出类型）
	 * 
	 * @param pettyCash
	 * @param userInfo
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void addNewPettyCash_Pay(PettyCash pettyCash, User user) throws ParseException {

		checkNewPettyCash_Pay(pettyCash);

		String _date = DateUtils.transDateFormat(pettyCash.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		// 业务日期
		pettyCash.setOptDate(_date);
		// 业务日期-年
		pettyCash.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 业务日期-月
		pettyCash.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 业务日期-对应的星期
		pettyCash.setWeek(DateUtils.getWeekOfDate(_date, "yyyyMMdd"));
		// 操作金额-支出为负数，拨入为正数
		pettyCash.setOptAmt(pettyCash.getOptAmtShow().multiply(new BigDecimal(-1)));
		// 机构编号
		pettyCash.setOrgId(user.getOrganization().getId());

		// ---------------------------------------------------------------------------------
		// 默认审查通过
		// ---------------------------------------------------------------------------------
		// 审查标记-账证对应
		pettyCash.setExamineFlg1(1);
		// 审查标记-大小写完整规范
		pettyCash.setExamineFlg2(1);
		// 审查标记-摘要清晰
		pettyCash.setExamineFlg3(1);
		// 审查标记-附件或监督完整
		pettyCash.setExamineFlg4(1);
		// 审查标记-记录序时
		pettyCash.setExamineFlg5(1);
		// 审查标记-UID正确
		pettyCash.setExamineFlg6(1);
		// 审查标记-装订正确
		pettyCash.setExamineFlg7(1);

		pettyCashJpaDao.save(pettyCash);

		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());

	}

	/**
	 * 检查门店备用金信息是否存在
	 * 
	 * @param pettyCash
	 */
	private void checkNewPettyCash_Pay(PettyCash pettyCash) {
		PettyCash _dbPettyCash = getPettyCashByOptUid(pettyCash.getOptUid());
		// 该门店备用金信息已存在!
		if (null != _dbPettyCash) {
			throw new ServiceException("ERR_MSG_PETTY_CASH_001");
		}
	}

	/**
	 * 更新 门店备用金（支出类型）
	 * 
	 * @param pettyCash
	 * @param userInfo
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void updateNewPettyCash_Pay(PettyCash pettyCash, User user) throws ParseException {
		PettyCash _dbPettyCash = pettyCashJpaDao.findOne(pettyCash.getUuid());
		// 该门店备用金信息不存在
		if (null == _dbPettyCash) {
			throw new ServiceException("ERR_MSG_PETTY_CASH_002");
		}

		// 业务日期
		String _date = DateUtils.transDateFormat(pettyCash.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		_dbPettyCash.setOptDate(_date);
		// 业务日期-显示
		_dbPettyCash.setOptDateShow(pettyCash.getOptDateShow());
		// 业务日期-年
		_dbPettyCash.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 业务日期-月
		_dbPettyCash.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 业务日期-对应的星期
		_dbPettyCash.setWeek(DateUtils.getWeekOfDate(_date, "yyyyMMdd"));
		// 操作金额-支出为负数，拨入为正数
		_dbPettyCash.setOptAmt(pettyCash.getOptAmtShow().multiply(new BigDecimal(-1)));
		// 操作金额-全部为正数
		_dbPettyCash.setOptAmtShow(pettyCash.getOptAmtShow());
		// 支出类型
		_dbPettyCash.setExpType(pettyCash.getExpType());
		// 支出事项
		_dbPettyCash.setExpReason(pettyCash.getExpReason());
		// 备注
		_dbPettyCash.setDescTxt(pettyCash.getDescTxt());

		pettyCashJpaDao.save(_dbPettyCash);

		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());
	}

	/**
	 * 取得门店备用金信息列表
	 * 
	 * @param orgId 机构编号
	 * @return
	 */
	public List<PettyCash> getPettyCashListByOrgId(String orgId) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		// 门店备用金可查看天数
		int editDays = sysConfig.getPettyCashEditDays();
		int viewDays = sysConfig.getPettyCashViewDays() * -1;
		String beforeDate = DateUtils.getNextDateFormatDate(viewDays, "yyyyMMdd");

		List<PettyCash> _list = pettyCashJpaDao.findByOrgId(orgId, beforeDate, new Sort(new Sort.Order(Sort.Direction.DESC,
				"optDate"), new Sort.Order(Sort.Direction.DESC, "uuid")));

		setEditFlg_PettyCashList(_list, editDays);

		return _list;
	}

	/**
	 * 设置备用金的可编辑标记
	 * 
	 * @param pettyCashList
	 * @param editDays
	 */
	private void setEditFlg_PettyCashList(List<PettyCash> pettyCashList, int editDays) {
		for (PettyCash pettyCash : pettyCashList) {
			if (Boolean.FALSE.equals(pettyCash.getCarryOverFlg())) {
				long spanDay = DateUtils.getDateSpanDay(pettyCash.getCreateDate(), DateUtils.getCurrentDate());
				if (spanDay <= editDays) {
					pettyCash.setEditFlg(true);
				}
			}
		}
	}

	/**
	 * 删除门店备用金信息
	 * 
	 * @param parseInt
	 */
	@Transactional(readOnly = false)
	public void delPettyCashByUuid(String[] idArray, User user) {
		for (int i = 0; i < idArray.length; i++) {
			pettyCashJpaDao.delete(Integer.parseInt(idArray[i]));
		}
		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());
	}

	/**
	 * 取得门店备用金信息
	 * 
	 * @param uuid
	 * @return
	 */
	public PettyCash getPettyCashByUuid(Integer uuid) {
		return pettyCashJpaDao.findOne(uuid);
	}

	/**
	 * 新增 门店备用金（拨入类型）
	 * 
	 * @param pettyCash
	 * @param userInfo
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void addNewPettyCash_Income(PettyCash pettyCash, User user) throws ParseException {
		// 业务日期
		String _date = DateUtils.transDateFormat(pettyCash.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		// 业务日期
		pettyCash.setOptDate(_date);
		// 业务日期-年
		pettyCash.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 业务日期-月
		pettyCash.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 业务日期-对应的星期
		pettyCash.setWeek(DateUtils.getWeekOfDate(_date, "yyyyMMdd"));
		// 操作金额-支出为负数，拨入为正数
		pettyCash.setOptAmt(pettyCash.getOptAmtShow());
		// 机构编号
		pettyCash.setOrgId(user.getOrganization().getId());

		pettyCashJpaDao.save(pettyCash);

		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());
	}

	/**
	 * 更新 门店备用金（拨入类型）
	 * 
	 * @param pettyCash
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void updateNewPettyCash_Income(PettyCash pettyCash, User user) throws ParseException {
		PettyCash _dbPettyCash = pettyCashJpaDao.findOne(pettyCash.getUuid());
		// 该门店备用金信息不存在
		if (null == _dbPettyCash) {
			throw new ServiceException("ERR_MSG_PETTY_CASH_002");
		}

		// 业务日期
		String _date = DateUtils.transDateFormat(pettyCash.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		_dbPettyCash.setOptDate(_date);
		// 业务日期-显示
		_dbPettyCash.setOptDateShow(pettyCash.getOptDateShow());
		// 业务日期-年
		_dbPettyCash.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 业务日期-月
		_dbPettyCash.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 业务日期-对应的星期
		_dbPettyCash.setWeek(DateUtils.getWeekOfDate(_date, "yyyyMMdd"));
		// 操作金额-支出为负数，拨入为正数
		_dbPettyCash.setOptAmt(pettyCash.getOptAmtShow());
		// 操作金额-全部为正数
		_dbPettyCash.setOptAmtShow(pettyCash.getOptAmtShow());

		// 拨入来源 1-正常拨入 2-非常拨入 4-会计调帐
		_dbPettyCash.setIncomeSource(pettyCash.getIncomeSource());

		// 备注
		_dbPettyCash.setDescTxt(pettyCash.getDescTxt());

		pettyCashJpaDao.save(_dbPettyCash);

		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());

	}

	/**
	 * 计算备用金余额
	 */
	@Transactional(readOnly = false)
	private void calBalanceAmt(String orgId) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		// 门店备用金重计算天数
		int calDays = sysConfig.getPettyCashCalculateDays();
		String beforeDate = DateUtils.getNextDateFormatDate(calDays * -1, "yyyyMMdd");

		List<PettyCash> _list = pettyCashJpaDao.findByOrgId(orgId, beforeDate, new Sort(new Sort.Order(Sort.Direction.ASC,
				"optDate"), new Sort.Order(Sort.Direction.ASC, "uuid")));

		BigDecimal _tmpBalanceAmt = null;
		for (int i = 0; i < _list.size(); i++) {
			PettyCash pettyCash = _list.get(i);
			if (i == 0) {
				_tmpBalanceAmt = pettyCash.getBalanceAmt();
				continue;// 第一条记录不计算备用金余额
			}
			_tmpBalanceAmt = _tmpBalanceAmt.add(pettyCash.getOptAmt());
			pettyCash.setBalanceAmt(_tmpBalanceAmt);
			pettyCashJpaDao.save(pettyCash);

		}
	}

	// -------------------------------------------------------------------------
	// -------------------------备用金信息结转-----------------------------------
	// -------------------------------------------------------------------------
	/**
	 * 根据查询条件 取得指定门店备用金余额（指定时间段内）
	 * 
	 * @param orgId 门店编号
	 * @param optDateStart 开始时间(yyyyMMdd)
	 * @param optDateEnd 结束时间(yyyyMMdd)
	 * @return
	 */
	public List<PettyCash> searchPettyCashList(String orgId, String optDateStart, String optDateEnd) {
		List<PettyCash> _list = pettyCashJpaDao.findByOrgIdAndOptDateInterval(orgId, optDateStart, optDateEnd, new Sort(
				new Sort.Order(Sort.Direction.ASC, "optDate"), new Sort.Order(Sort.Direction.ASC, "uuid")));
		return _list;
	}

	/**
	 * 取得指定门店未结转的备用金信息
	 * 
	 * @param orgId 门店编号
	 * @return
	 */
	public List<PettyCash> searchPettyCashList_noCarryOver(String orgId) {
		List<PettyCash> _list = pettyCashJpaDao.findByOrgIdAndCarryOverFlg(orgId, new Sort(new Sort.Order(Sort.Direction.DESC,
				"optDate"), new Sort.Order(Sort.Direction.DESC, "uuid")));
		return _list;
	}

	/**
	 * 备用金信息结转
	 * 
	 * @param orgId 门店编号
	 * @param pettyCashUuid 备用金UUID
	 * @param inspectTrsId 巡查报告流水号
	 */
	@Transactional(readOnly = false)
	public void pettyCashCarryOver(String orgId, Integer pettyCashUuid, String inspectTrsId) {

		Inspect _dbInspect = inspectJpaDao.findByTrsId(inspectTrsId);
		if (null == _dbInspect) {
			// 门店巡查报告不存在!
			throw new ServiceException("ERR_MSG_INSPECT_002");
		}

		List<PettyCash> _list = pettyCashJpaDao.findByOrgIdAndCarryOverFlg(orgId, new Sort(new Sort.Order(Sort.Direction.DESC,
				"optDate"), new Sort.Order(Sort.Direction.DESC, "uuid")));

		boolean carryOverFlg = false;
		for (PettyCash pettyCash : _list) {
			if (carryOverFlg || pettyCash.getUuid().equals(pettyCashUuid)) {
				carryOverFlg = true;
				pettyCash.setCarryOverFlg(true);
				pettyCash.setInspectTrsId(inspectTrsId);
				pettyCashJpaDao.save(pettyCash);
			}
		}
	}

	/**
	 * 备用金审核
	 * 
	 * @param uuids
	 * @param examineFlgs1
	 * @param examineFlgs2
	 * @param examineFlgs3
	 * @param examineFlgs4
	 * @param examineFlgs5
	 * @param examineFlgs6
	 * @param examineFlgs7
	 */
	@Transactional(readOnly = false)
	public void auditPettyCash(int[] uuids, int[] examineFlgs1, int[] examineFlgs2, int[] examineFlgs3, int[] examineFlgs4,
			int[] examineFlgs5, int[] examineFlgs6, int[] examineFlgs7) {
		for (int i = 0; i < uuids.length; i++) {
			int uuid = uuids[i];
			PettyCash _pettyCash = pettyCashJpaDao.findOne(uuid);

			// 审查标记-账证对应
			_pettyCash.setExamineFlg1(examineFlgs1[i]);
			// 审查标记-大小写完整规范
			_pettyCash.setExamineFlg2(examineFlgs2[i]);
			// 审查标记-摘要清晰
			_pettyCash.setExamineFlg3(examineFlgs3[i]);
			// 审查标记-附件或监督完整
			_pettyCash.setExamineFlg4(examineFlgs4[i]);
			// 审查标记-记录序时
			_pettyCash.setExamineFlg5(examineFlgs5[i]);
			// 审查标记-UID正确
			_pettyCash.setExamineFlg6(examineFlgs6[i]);
			// 审查标记-装订正确
			_pettyCash.setExamineFlg7(examineFlgs7[i]);

			pettyCashJpaDao.save(_pettyCash);
		}

	}

	/**
	 * 创建备用金信息文件
	 * 
	 * @param orgId
	 * @param optDate_start
	 * @param optDate_end
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public String createPettyCashFile(String orgId, String optDate_start, String optDate_end) throws ParsePropertyException,
			InvalidFormatException, IOException {
		List<PettyCash> _list = pettyCashJpaDao.findByOrgIdAndOptDateInterval(orgId, optDate_start, optDate_end, new Sort(
				new Sort.Order(Sort.Direction.ASC, "optDate"), new Sort.Order(Sort.Direction.ASC, "uuid")));

		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pettyCashList", _list);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_PETTY_CASH, map, tmpFilePath);

		return tmpFileName;
	}

	/**
	 * 取得门店备用金月度分析信息
	 * 
	 * @param orgId
	 * @param optDateStart 开始时间(yyyyMMdd)
	 * @param optDateEnd 结束时间(yyyyMMdd)
	 * @return
	 */
	public List<PettyCash> getPettyCashAnalyticsInfo(String orgId, String optDateStart, String optDateEnd) {
		PettyCash param = new PettyCash();
		param.setOrgId(orgId);
		param.setOptDateStart(optDateStart);
		param.setOptDateEnd(optDateEnd);

		List<PettyCash> _list = pettyCashMyBatisDao.getPettyCashAnalyticsInfo(param);
		return _list;
	}
}
