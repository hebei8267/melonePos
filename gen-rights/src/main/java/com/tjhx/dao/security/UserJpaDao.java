package com.tjhx.dao.security;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.security.User;

/**
 * 用户JpaDao
 */
public interface UserJpaDao extends CrudRepository<User, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
