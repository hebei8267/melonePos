package com.tjhx.dao.struct;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.struct.Organization;

public interface OrganizationJpaDao extends CrudRepository<Organization, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得机构信息
	 * 
	 * @return 机构信息
	 */
	public Organization findByBwId(String bwId);
}
