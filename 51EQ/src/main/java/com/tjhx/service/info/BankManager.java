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

import com.tjhx.dao.info.BankJpaDao;
import com.tjhx.entity.info.Bank;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class BankManager {
	private static Logger logger = LoggerFactory.getLogger(BankManager.class);
	@Resource
	private BankJpaDao bankJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;

	/**
	 * 取得所有银行信息
	 * 
	 * @return 银行信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Bank> getAllBank() {
		List<Bank> _bankList = spyMemcachedClient.get(MemcachedObjectType.BANK_LIST.getObjKey());

		if (null == _bankList) {
			// 从数据库中取出全量银行信息(List格式)
			_bankList = (List<Bank>) bankJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
			// 将银行信息List保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.BANK_LIST.getObjKey(),
					MemcachedObjectType.BANK_LIST.getExpiredTime(), _bankList);

			logger.debug("银行信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出银行信息");
		}

		return _bankList;

	}

	/**
	 * 取得银行
	 * 
	 * @param bankId
	 * @return
	 */
	public Bank getBank(String bankId) {
		return bankJpaDao.findByBankId(bankId);
	}

	/**
	 * 取得银行
	 * 
	 * @param bankName
	 * @return
	 */
	public Bank getBankByName(String bankName) {
		return bankJpaDao.findByName(bankName);
	}

	/**
	 * 根据编号取得银行信息
	 * 
	 * @param uuid 银行编号
	 * @return 银行信息
	 */
	public Bank getBankByUuid(Integer uuid) {
		return bankJpaDao.findOne(uuid);
	}

	/**
	 * 删除银行信息
	 * 
	 * @param uuid 银行编号
	 */
	@Transactional(readOnly = false)
	public void delBankByUuid(Integer uuid) {
		bankJpaDao.delete(uuid);
	}

	/**
	 * 添加新银行信息
	 * 
	 * @param bank 银行信息
	 */
	@Transactional(readOnly = false)
	public void addNewBank(Bank bank) {

		Bank _dbBank = bankJpaDao.findByBankId(bank.getBankId());
		// 该银行已存在!
		if (null != _dbBank) {
			throw new ServiceException("ERR_MSG_BANK_001");
		}
		bankJpaDao.save(bank);
	}

	/**
	 * 更新银行信息
	 * 
	 * @param bank 银行信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateBank(Bank bank) throws IllegalAccessException, InvocationTargetException {

		Bank _dbBank = bankJpaDao.findOne(bank.getUuid());
		if (null == _dbBank) {
			// 银行不存在!
			throw new ServiceException("ERR_MSG_BANK_002");
		}

		_dbBank.setName(bank.getName());

		bankJpaDao.save(_dbBank);
	}
}