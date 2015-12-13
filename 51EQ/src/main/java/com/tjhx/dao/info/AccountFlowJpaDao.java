package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.AccountFlow;

public interface AccountFlowJpaDao extends CrudRepository<AccountFlow, Integer> {
	@Query("select a from AccountFlow a")
	public List<AccountFlow> findAll(Sort sort);
}
