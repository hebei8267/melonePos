package com.tjhx.dao.info;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.BankCard;

public interface BankCardJpaDao extends CrudRepository<BankCard, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	public BankCard findByBankCardNo(String bankCardNo);

	@SuppressWarnings("rawtypes")
	@Query("select b from BankCard b where b.orgId = ?1") 
	public Iterable findByOrgId(String orgId, Sort sort);
}
