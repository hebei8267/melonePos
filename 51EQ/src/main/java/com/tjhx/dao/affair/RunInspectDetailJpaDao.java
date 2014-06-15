package com.tjhx.dao.affair;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.affair.RunInspectDetail;

public interface RunInspectDetailJpaDao extends CrudRepository<RunInspectDetail, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
