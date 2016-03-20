package com.tjhx.service.info;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.AccountFlowJpaDao;
import com.tjhx.dao.info.AccountFlowSplitJpaDao;
import com.tjhx.dao.info.AccountFlowSplitMyBatisDao;
import com.tjhx.dao.info.AccountSubjectJpaDao;
import com.tjhx.entity.info.AccountFlow;
import com.tjhx.entity.info.AccountFlowSplit;
import com.tjhx.entity.info.AccountSubject;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.ServiceException;
import com.tjhx.vo.AccountFlowSplitVo;

@Service
@Transactional(readOnly = true)
public class AccountFlowManager {
	@Resource
	private AccountFlowJpaDao accountFlowJpaDao;
	@Resource
	private AccountFlowSplitJpaDao accountFlowSplitJpaDao;
	@Resource
	private AccountFlowSplitMyBatisDao accountFlowSplitMyBatisDao;
	@Resource
	private AccountSubjectJpaDao accountSubjectJpaDao;
	@Resource
	private AccountSubjectManager accountSubjectManager;

	private final static String XML_CONFIG_READ_ACCOUNT_FLOW = "/excel/AccountFlow_CFG.xml";
	private final static String XML_CONFIG_ACCOUNT_FLOW = "/excel/Account_Flow_Template.xls";
	private final static String XML_CONFIG_ACCOUNT_FLOW_DETAIL = "/excel/Account_Flow_Detail_Template.xls";

	/**
	 * 读取记账文件内容
	 * 
	 * @param filePath
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@Transactional(readOnly = false)
	public boolean loadAccountFlowFile(String filePath) throws IOException, SAXException, InvalidFormatException, NumberFormatException,
			ParseException {
		InputStream inputXML = new BufferedInputStream(AccountFlowManager.class.getResourceAsStream(XML_CONFIG_READ_ACCOUNT_FLOW));

		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);

		InputStream inputXLS = new BufferedInputStream(new FileInputStream(filePath));

		List<AccountFlow> accountFlowList = Lists.newArrayList();
		Map<String, List<AccountFlow>> map = Maps.newHashMap();

		map.put("accountFlowList", accountFlowList);
		XLSReadStatus readStatus = mainReader.read(inputXLS, map);

		if (Boolean.TRUE.equals(readStatus.isStatusOK())) {

			saveAccountFlow(accountFlowList);

			calBalanceAmt();
			return true;
		}

		return false;

	}

	/**
	 * 计算会计余额
	 */
	@Transactional(readOnly = false)
	private void calBalanceAmt() {

		List<AccountFlow> _list = accountFlowJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "optDate"), new Sort.Order(
				Sort.Direction.ASC, "uuid")));

		BigDecimal _tmpBalanceAmt = new BigDecimal("0");
		for (int i = 0; i < _list.size(); i++) {
			AccountFlow accountFlow = _list.get(i);

			_tmpBalanceAmt = _tmpBalanceAmt.add(accountFlow.getInAmt()).subtract(accountFlow.getOutAmt());
			accountFlow.setBalanceAmt(_tmpBalanceAmt);
			accountFlowJpaDao.save(accountFlow);

		}

	}

	/**
	 * 保存记账文件内容
	 * 
	 * @param accountFlowList
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	private void saveAccountFlow(List<AccountFlow> accountFlowList) throws NumberFormatException, ParseException {
		System.out.println(accountFlowList.size());
		for (AccountFlow accountFlow : accountFlowList) {

			if (null != accountFlow.getOptDate()) {
				if ("1".equals(accountFlow.getOptDate())) {
					accountFlow.setOptDate("19000101");
				} else {
					accountFlow.setOptDate(DateUtils.transDateFormat(
							DateUtils.getNextDateFormatDate("1900/01/01", Integer.parseInt(accountFlow.getOptDate()) - 2, "yyyy/MM/dd"),
							"yyyy/MM/dd", "yyyyMMdd"));
				}
			}

			if (null == accountFlow.getInAmt()) {
				accountFlow.setInAmt(new BigDecimal("0"));
			}
			if (null == accountFlow.getOutAmt()) {
				accountFlow.setOutAmt(new BigDecimal("0"));
			}
			accountFlowJpaDao.save(accountFlow);
		}
	}

	public String createDownLoadFile(String optDateShowStart, String optDateShowEnd) throws ParsePropertyException, InvalidFormatException,
			IOException {
		if (StringUtils.isBlank(optDateShowStart) && StringUtils.isBlank(optDateShowEnd)) {
			return null;
		}
		String _optDateStart = DateUtils.transDateFormat(optDateShowStart, "yyyy-MM-dd", "yyyyMMdd");
		String _optDateShowEnd = DateUtils.transDateFormat(optDateShowEnd, "yyyy-MM-dd", "yyyyMMdd");

		List<AccountFlow> _list = accountFlowJpaDao.findByOptDate(_optDateStart, _optDateShowEnd, new Sort(new Sort.Order(
				Sort.Direction.ASC, "optDate"), new Sort.Order(Sort.Direction.ASC, "uuid")));

		for (AccountFlow accountFlow : _list) {
			accountFlow.setOptDate(DateUtils.transDateFormat(accountFlow.getOptDate(), "yyyyMMdd", "yyyy-MM-dd"));
		}
		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountFlowList", _list);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_ACCOUNT_FLOW, map, tmpFilePath);

		return tmpFileName;
	}

	/**
	 * 取得记账信息对象列表
	 * 
	 * @param optDate
	 * 
	 * @return
	 */
	public List<AccountFlow> getAccountFlowList(String optDateShowStart, String optDateShowEnd) {

		if (StringUtils.isNotBlank(optDateShowStart) && StringUtils.isNotBlank(optDateShowEnd)) {
			String _optDateStart = DateUtils.transDateFormat(optDateShowStart, "yyyy-MM-dd", "yyyyMMdd");
			String _optDateShowEnd = DateUtils.transDateFormat(optDateShowEnd, "yyyy-MM-dd", "yyyyMMdd");

			return (List<AccountFlow>) accountFlowJpaDao.findByOptDate(_optDateStart, _optDateShowEnd, new Sort(new Sort.Order(
					Sort.Direction.DESC, "optDate"), new Sort.Order(Sort.Direction.DESC, "uuid")));
		} else {
			return (List<AccountFlow>) accountFlowJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "optDate"), new Sort.Order(
					Sort.Direction.DESC, "uuid")));
		}

	}

	/**
	 * 删除记账信息
	 * 
	 * @param uuid
	 */
	@Transactional(readOnly = false)
	public void delAccountFlowByUuid(Integer uuid) {
		AccountFlow _accountFlow = accountFlowJpaDao.findOne(uuid);
		// TODO 会计记账分拆项目

		accountFlowJpaDao.delete(_accountFlow);

		// 计算会计余额
		calBalanceAmt();
	}

	/**
	 * 取得记账信息对象
	 * 
	 * @param id 记账对象ID
	 * @return
	 */
	public AccountFlow getAccountFlowByUuid(Integer id) {
		return accountFlowJpaDao.findOne(id);
	}

	/**
	 * 新增/修改 记账信息
	 * 
	 * @param accountFlow
	 */
	@Transactional(readOnly = false)
	public Boolean saveAccountFlow(AccountFlow accountFlow) {
		if (null == accountFlow) {
			return false;
		}

		if (null == accountFlow.getUuid()) {
			// 添加记账信息
			return addAccountFlow(accountFlow);
		} else {
			// 编辑记账信息
			return editAccountFlow(accountFlow);
		}

	}

	/**
	 * 编辑记账信息
	 * 
	 * @param accountFlow
	 * @return
	 */
	@Transactional(readOnly = false)
	private Boolean editAccountFlow(AccountFlow accountFlow) {
		AccountFlow dbAccountFlow = accountFlowJpaDao.findOne(accountFlow.getUuid());

		if (null == dbAccountFlow) {
			throw new ServiceException("ERR_MSG_ACCOUNT_FLOW_001");
		}
		// 日期
		dbAccountFlow.setOptDate(DateUtils.transDateFormat(accountFlow.getOptDate(), "yyyy-MM-dd", "yyyyMMdd"));
		// 余额
		dbAccountFlow.setBalanceAmt(accountFlow.getBalanceAmt());
		// 拨入来源
		dbAccountFlow.setInAmtDesc(accountFlow.getInAmtDesc());
		// 拨入金额
		dbAccountFlow.setInAmt(accountFlow.getInAmt());
		// 支出金额
		dbAccountFlow.setOutAmt(accountFlow.getOutAmt());
		// 支出大类
		dbAccountFlow.setOutAmtLClass(accountFlow.getOutAmtLClass());
		// 支出细类
		dbAccountFlow.setOutAmtSClass(accountFlow.getOutAmtSClass());
		// 备注
		dbAccountFlow.setDescTxt(accountFlow.getDescTxt());

		accountFlowJpaDao.save(dbAccountFlow);

		// 计算会计余额
		calBalanceAmt();

		return true;
	}

	/**
	 * 添加记账信息
	 * 
	 * @param accountFlow
	 * @return
	 */
	@Transactional(readOnly = false)
	private Boolean addAccountFlow(AccountFlow accountFlow) {
		accountFlow.setOptDate(DateUtils.transDateFormat(accountFlow.getOptDate(), "yyyy-MM-dd", "yyyyMMdd"));
		accountFlowJpaDao.save(accountFlow);

		// 计算会计余额
		calBalanceAmt();

		return true;
	}

	/**
	 * 取得资金记账流水支出明细切分信息列表（根据记账信息UUID）
	 * 
	 * @param id 记账信息UUID
	 * @return
	 */
	public List<AccountFlowSplit> getAccountFlowSplitByFlowUuid(Integer id) {
		List<AccountFlowSplit> _list = accountFlowSplitMyBatisDao.getAccountFlowSplitByFlowUuid(id);

		int _index = 10 - _list.size();

		for (int i = 0; i < _index; i++) {
			AccountFlowSplit split = new AccountFlowSplit();
			split.setAmt(null);
			_list.add(split);
		}

		return _list;
	}

	@Transactional(readOnly = false)
	public void saveAccountFlowSplit(int accountFlowUuid, List<AccountFlowSplit> _list) {

		AccountFlow accountFlow = accountFlowJpaDao.findOne(accountFlowUuid);
		if (null == accountFlow) {
			return;
		}

		accountFlowSplitMyBatisDao.delAccountFlowSplitByFlowUuid(accountFlowUuid);

		for (int i = 0; i < _list.size(); i++) {
			AccountFlowSplit accountFlowSplit = _list.get(i);

			accountFlowSplit.setAccountFlow(accountFlow);

			AccountSubject sub = accountSubjectJpaDao.findBySubId(accountFlowSplit.getSubId());
			accountFlowSplit.setAccountSub(sub);

			accountFlowSplit.setIndex(i);

			accountFlowSplitJpaDao.save(accountFlowSplit);
		}

		accountFlow.setLockFlg(true);
		accountFlowJpaDao.save(accountFlow);
	}

	/**
	 * 取得记账科目节点全路径名称
	 * 
	 * @param subMap
	 * @param accountFlowSplit
	 * @return
	 */
	private String getAccountSubjectName(Map<String, AccountSubject> subMap, AccountFlowSplit accountFlowSplit) {
		AccountSubject sub = subMap.get(accountFlowSplit.getSubId());

		if (null == sub) {
			sub = accountSubjectManager.getAccountSubjectStructTreeFromSubNode(accountFlowSplit.getSubId());

			subMap.put(accountFlowSplit.getSubId(), sub);
		}

		List<String> _nameList = Lists.newArrayList();
		_getAccountSubjectName(_nameList, sub);

		StringBuffer _sb = new StringBuffer();
		for (int i = _nameList.size(); i > 0; i--) {
			_sb.append(_nameList.get(i - 1) + "/");
		}

		return _sb.toString().substring(0, _sb.length() - 1);
	}

	private void _getAccountSubjectName(List<String> _nameList, AccountSubject sub) {
		if (2 == sub.getSubId().length()) {
			return;
		}

		if (4 == sub.getSubId().length()) {
			_nameList.add(sub.getSubName());
		} else {
			_nameList.add(sub.getSubName());
		}
		_getAccountSubjectName(_nameList, sub.getParentSub());
	}

	public String createDownLoadFile_Detail(String optDateShowStart, String optDateShowEnd) throws IllegalAccessException,
			InvocationTargetException, ParsePropertyException, InvalidFormatException, IOException {
		if (StringUtils.isBlank(optDateShowStart) && StringUtils.isBlank(optDateShowEnd)) {
			return null;
		}
		String _optDateStart = DateUtils.transDateFormat(optDateShowStart, "yyyy-MM-dd", "yyyyMMdd");
		String _optDateShowEnd = DateUtils.transDateFormat(optDateShowEnd, "yyyy-MM-dd", "yyyyMMdd");

		List<AccountFlow> _list = accountFlowJpaDao.findByOptDate(_optDateStart, _optDateShowEnd, new Sort(new Sort.Order(
				Sort.Direction.ASC, "optDate"), new Sort.Order(Sort.Direction.ASC, "uuid")));

		List<AccountFlowSplitVo> _voList = Lists.newArrayList();

		Map<String, AccountSubject> _subMap = Maps.newHashMap();

		for (AccountFlow accountFlow : _list) {
			accountFlow.setOptDate(DateUtils.transDateFormat(accountFlow.getOptDate(), "yyyyMMdd", "yyyy-MM-dd"));

			List<AccountFlowSplit> _splitList = accountFlowSplitMyBatisDao.getAccountFlowSplitByFlowUuid(accountFlow.getUuid());

			for (AccountFlowSplit accountFlowSplit : _splitList) {
				AccountFlowSplitVo vo = new AccountFlowSplitVo();
				vo.setAccountFlowUuid(accountFlow.getUuid());
				BeanUtils.copyProperties(vo, accountFlow);

				accountFlowSplit.setSubName(getAccountSubjectName(_subMap, accountFlowSplit));

				BeanUtils.copyProperties(vo, accountFlowSplit);

				_voList.add(vo);
			}
		}
		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountFlowSplitVoList", _voList);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_ACCOUNT_FLOW_DETAIL, map, tmpFilePath);

		return tmpFileName;

	}

}
