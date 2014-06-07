package com.tjhx.dao.affair;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.affair.RunInspectItem;

public interface RunInspectItemJpaDao extends CrudRepository<RunInspectItem, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
