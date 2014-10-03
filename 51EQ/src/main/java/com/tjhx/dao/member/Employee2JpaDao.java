package com.tjhx.dao.member;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.member.Employee2;

public interface Employee2JpaDao extends CrudRepository<Employee2, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
