package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.AccountFlow;

public interface AccountFlowJpaDao extends CrudRepository<AccountFlow, Integer> {
	@Query("select a from AccountFlow a")
	public List<AccountFlow> findAll(Sort sort);

	@Query("select a from AccountFlow a where a.optDate >= ?1 and a.optDate <= ?2")
	public List<AccountFlow> findByOptDate(String optDateStart, String optDateEnd, Sort sort);
}
