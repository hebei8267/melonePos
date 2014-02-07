package com.tjhx.dao.info;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.info.SalesDayTotal;

public interface SalesDayTotalJpaDao extends CrudRepository<SalesDayTotal, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@Query("select s from SalesDayTotal s where s.orgId = :orgId and s.optDateY = :optDateY and s.optDateM = :optDateM order by optDateM")
	public Iterable<SalesDayTotal> findOneByOrgIdAndOptDateYM(@Param("orgId") String orgId,
			@Param("optDateY") String optDateY, @Param("optDateM") String optDateM);

	@Query("select s from SalesDayTotal s where s.optDate = :optDate order by posAmtByNow desc")
	public Iterable<SalesDayTotal> findByOptDate(@Param("optDate") String optDate);
}
