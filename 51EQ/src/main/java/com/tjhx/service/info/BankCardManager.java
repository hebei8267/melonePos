package com.tjhx.service.info;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.info.BankCardJpaDao;
import com.tjhx.entity.info.Bank;
import com.tjhx.entity.info.BankCard;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class BankCardManager {
	private static Logger logger = LoggerFactory.getLogger(BankCardManager.class);
	@Resource
	private BankCardJpaDao bankCardJpaDao;
	@Resource
	private BankManager bankManager;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;

	/**
	 * 取得所有银行卡信息
	 * 
	 * @return 银行卡信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<BankCard> getAllBankCard(String orgId) {

		Map<String, List<BankCard>> _bankCardMap = spyMemcachedClient.get(MemcachedObjectType.BANK_CARD_MAP.getObjKey());
		if (null == _bankCardMap) {
			_bankCardMap = new HashMap<String, List<BankCard>>();
		}

		List<BankCard> _bankCardList = _bankCardMap.get(orgId);

		if (null == _bankCardList) {
			// 从数据库中取出全量银行卡信息(List格式)
			_bankCardList = (List<BankCard>) bankCardJpaDao.findByOrgId(orgId, new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
			_bankCardMap.put(orgId, _bankCardList);

			// 将银行卡信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.BANK_CARD_MAP.getObjKey(), MemcachedObjectType.BANK_CARD_MAP.getExpiredTime(),
					_bankCardMap);

			logger.debug("银行卡信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出银行卡信息");
		}
		return _bankCardList;
	}

	/**
	 * 取得所有银行卡信息
	 * 
	 * @return 银行卡信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<BankCard> getAllBankCard() {
		List<BankCard> _bankCardList = (List<BankCard>) bankCardJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "orgId")));
		return _bankCardList;
	}

	/**
	 * 根据编号取得银行卡信息
	 * 
	 * @param uuid 银行卡编号
	 * @return 银行卡信息
	 */
	public BankCard getBankCardByUuid(Integer uuid) {
		return bankCardJpaDao.findOne(uuid);
	}

	/**
	 * 删除银行卡信息
	 * 
	 * @param uuid 银行卡编号
	 */
	@Transactional(readOnly = false)
	public void delBankCardByUuid(Integer uuid) {
		bankCardJpaDao.delete(uuid);

		spyMemcachedClient.delete(MemcachedObjectType.BANK_CARD_MAP.getObjKey());
	}

	/**
	 * 添加新银行卡信息
	 * 
	 * @param bankCard 银行卡信息
	 */
	@Transactional(readOnly = false)
	public void addNewBankCard(BankCard bankCard) {
		BankCard _dbBankCard = bankCardJpaDao.findByBankCardNo(bankCard.getBankCardNo());
		// 该银行卡已存在!
		if (null != _dbBankCard) {
			throw new ServiceException("ERR_MSG_BANK_CARD_001");
		}

		Bank bank = bankManager.getBank(bankCard.getBankId());
		bankCard.setBank(bank);

		bankCardJpaDao.save(bankCard);

		spyMemcachedClient.delete(MemcachedObjectType.BANK_CARD_MAP.getObjKey());
	}

	/**
	 * 更新银行卡信息
	 * 
	 * @param bankCard 银行卡信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateBankCard(BankCard bankCard) throws IllegalAccessException, InvocationTargetException {

		BankCard _dbBankCard = bankCardJpaDao.findOne(bankCard.getUuid());
		if (null == _dbBankCard) {
			// 银行卡不存在!
			throw new ServiceException("ERR_MSG_BANK_CARD_002");
		}

		BankCard _dbBankCard2 = bankCardJpaDao.findByBankCardNo(bankCard.getBankCardNo());
		if (null != _dbBankCard2 && _dbBankCard.getUuid() != _dbBankCard2.getUuid()) {
			throw new ServiceException("ERR_MSG_BANK_CARD_001");
		}

		_dbBankCard.setBankCardNo(bankCard.getBankCardNo());
		_dbBankCard.setOrgId(bankCard.getOrgId());
		_dbBankCard.setAccountName(bankCard.getAccountName());

		bankCardJpaDao.save(_dbBankCard);

		spyMemcachedClient.delete(MemcachedObjectType.BANK_CARD_MAP.getObjKey());
	}
}