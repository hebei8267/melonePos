package com.tjhx.dao.affair;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.affair.BorrowItem;

public interface BorrowItemJpaDao extends CrudRepository<BorrowItem, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * @param itemNo
	 * @return
	 */
	public BorrowItem findByItemNo(String itemNo);
}
