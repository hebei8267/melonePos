package com.tjhx.dao.accounts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.CashDaily;

public interface CashDailyJpaDao extends CrudRepository<CashDaily, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@Query("select c from CashDaily c where c.orgId = :orgId and c.optDate = :optDate")
	public CashDaily findByOrgId_OptDate(@Param("orgId") String orgId, @Param("optDate") String optDate);

	@Query("select c from CashDaily c where c.orgId = :orgId and c.optDateY = :optDateY and c.optDateM = :optDateM")
	public List<CashDaily> findByOrgId_OptDateY_OptDateM(@Param("orgId") String orgId, @Param("optDateY") String optDateY,
			@Param("optDateM") String optDateM, Sort sort);

	@Query("select count(c) from CashDaily c where c.orgId = :orgId and c.optDate >= :optDate")
	public Long checkCashDailyOptDate(@Param("orgId") String orgId, @Param("optDate") String optDate);

	@Query("select count(c) from CashDaily c where c.optDate = :optDate")
	public Long getCashDailyCount(@Param("optDate") String optDate);

	@Query("select c from CashDaily c where c.optDate = :optDate")
	public List<CashDaily> findByOptDate(@Param("optDate") String optDate);
}
