package com.tjhx.service.info;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.AccountFlowJpaDao;
import com.tjhx.dao.info.AccountFlowSplitJpaDao;
import com.tjhx.dao.info.AccountFlowSplitMyBatisDao;
import com.tjhx.entity.info.AccountFlow;
import com.tjhx.entity.info.AccountFlowSplit;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class AccountFlowManager {
	@Resource
	private AccountFlowJpaDao accountFlowJpaDao;

	@Resource
	private AccountFlowSplitJpaDao accountFlowSplitJpaDao;

	@Resource
	private AccountFlowSplitMyBatisDao accountFlowSplitMyBatisDao;

	private final static String XML_CONFIG_ACCOUNT_FLOW = "/excel/AccountFlow_CFG.xml";

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
	public boolean loadAccountFlowFile(String filePath) throws IOException, SAXException, InvalidFormatException,
			NumberFormatException, ParseException {
		InputStream inputXML = new BufferedInputStream(
				AccountFlowManager.class.getResourceAsStream(XML_CONFIG_ACCOUNT_FLOW));

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

		List<AccountFlow> _list = accountFlowJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "optDate"),
				new Sort.Order(Sort.Direction.ASC, "uuid")));

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
					accountFlow.setOptDate(DateUtils.transDateFormat(DateUtils.getNextDateFormatDate("1900/01/01",
							Integer.parseInt(accountFlow.getOptDate()) - 2, "yyyy/MM/dd"), "yyyy/MM/dd", "yyyyMMdd"));
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

	/**
	 * 取得记账信息对象列表
	 * 
	 * @return
	 */
	public List<AccountFlow> getAccountFlowList() {
		return (List<AccountFlow>) accountFlowJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "optDate"),
				new Sort.Order(Sort.Direction.DESC, "uuid")));
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
			_list.add(new AccountFlowSplit());
		}

		return _list;
	}
}
