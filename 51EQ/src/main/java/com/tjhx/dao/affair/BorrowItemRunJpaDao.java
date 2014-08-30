package com.tjhx.dao.affair;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.affair.BorrowItemRun;

public interface BorrowItemRunJpaDao extends CrudRepository<BorrowItemRun, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
