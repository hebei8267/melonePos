package com.tjhx.dao.accounts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.MonthSaleTarget;

public interface MonthSaleTargetJpaDao extends CrudRepository<MonthSaleTarget, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@SuppressWarnings("rawtypes")
	@Query("select m from MonthSaleTarget m where m.orgId = :orgId and m.optDateY = :optDateY")
	public Iterable findByOrgIdAndOptDateY(@Param("orgId") String orgId, @Param("optDateY") String optDateY, Sort sort);

	@SuppressWarnings("rawtypes")
	@Query("select m from MonthSaleTarget m where m.optDateY = :optDateY")
	public Iterable findByOptDateY(@Param("optDateY") String optDateY, Sort sort);

	@Query("select m from MonthSaleTarget m where m.optDateY = :optDateY and m.optDateM = :optDateM")
	public List<MonthSaleTarget> findByOptDateYM(@Param("optDateY") String optDateY, @Param("optDateM") String optDateM);
}
