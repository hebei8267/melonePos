package com.tjhx.dao.member;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.member.Function;

public interface FunctionJpaDao extends CrudRepository<Function, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
