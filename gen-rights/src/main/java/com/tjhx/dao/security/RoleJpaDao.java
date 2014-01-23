package com.tjhx.dao.security;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.security.Role;

/**
 * 角色JpaDao
 */
public interface RoleJpaDao extends CrudRepository<Role, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
