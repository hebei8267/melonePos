package com.tjhx.dao.security;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.security.Menu;

/**
 * 菜单资源JpaDao
 */
public interface MenuJpaDao extends CrudRepository<Menu, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
