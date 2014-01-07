package com.tjhx.dao.member;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.member.Role;

public interface RoleJpaDao extends CrudRepository<Role, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	public Role findByName(String name);
}
