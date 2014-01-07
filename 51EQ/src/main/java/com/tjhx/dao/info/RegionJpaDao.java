package com.tjhx.dao.info;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.Region;

public interface RegionJpaDao extends CrudRepository<Region, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	public Region findByCode(String regionCode);

	public Region findByName(String regionName);
}
