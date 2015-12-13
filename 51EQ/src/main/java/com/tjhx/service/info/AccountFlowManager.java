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
import com.tjhx.entity.info.AccountFlow;

@Service
@Transactional(readOnly = true)
public class AccountFlowManager {
	@Resource
	private AccountFlowJpaDao accountFlowJpaDao;
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
		return (List<AccountFlow>) accountFlowJpaDao.findAll();
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

	}
}
