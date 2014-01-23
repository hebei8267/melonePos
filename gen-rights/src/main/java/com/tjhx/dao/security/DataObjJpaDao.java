package com.tjhx.dao.security;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.security.DataObj;

/**
 * 数据对象资源JpaDao
 */
public interface DataObjJpaDao extends CrudRepository<DataObj, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
