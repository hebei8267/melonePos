package com.tjhx.dao.info;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.info.OrgWeekSalesRanking;

public interface OrgWeekSalesRankingJpaDao extends CrudRepository<OrgWeekSalesRanking, Integer> {
	@Query("select count(o) from OrgWeekSalesRanking o where o.optDateY=:optDateY and o.optDateW=:optDateW")
	public Long getRankingCount(@Param("optDateY") Integer optDateY, @Param("optDateW") Integer optDateW);
}
