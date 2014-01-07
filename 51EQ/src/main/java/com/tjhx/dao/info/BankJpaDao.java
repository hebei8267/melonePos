package com.tjhx.dao.info;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.Bank;

public interface BankJpaDao extends CrudRepository<Bank, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	public Bank findByBankId(String bankId);
	
	public Bank findByName(String name);
}
