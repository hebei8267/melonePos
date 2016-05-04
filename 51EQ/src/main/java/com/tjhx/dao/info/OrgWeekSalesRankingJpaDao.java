package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.info.OrgWeekSalesRanking;

public interface OrgWeekSalesRankingJpaDao extends CrudRepository<OrgWeekSalesRanking, Integer> {
	@Query("select count(o) from OrgWeekSalesRanking o where o.optDateY=:optDateY and o.optDateW=:optDateW")
	public Long getRankingCount(@Param("optDateY") Integer optDateY, @Param("optDateW") Integer optDateW);

	@Query("select max(o.optDateW) from OrgWeekSalesRanking o where o.optDateY=:optDateY")
	public Integer getMaxOptDateW(@Param("optDateY") Integer optDateY);

	@Query("select o from OrgWeekSalesRanking o where o.optDateY=:optDateY and o.optDateW=:optDateW order by ranking")
	public List<OrgWeekSalesRanking> getOrgWeekSalesRankingList(@Param("optDateY") Integer optDateY, @Param("optDateW") Integer optDateW);
}
