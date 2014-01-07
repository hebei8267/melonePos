package com.tjhx.dao.member;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.member.Permission;

public interface PermissionJpaDao extends CrudRepository<Permission, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
