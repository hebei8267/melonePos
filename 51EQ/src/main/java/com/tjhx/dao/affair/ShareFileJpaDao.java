package com.tjhx.dao.affair;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.affair.ShareFile;

public interface ShareFileJpaDao extends CrudRepository<ShareFile, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
