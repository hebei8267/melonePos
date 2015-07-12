package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.SalesWeekTotalGoods_2;

public interface SalesWeekTotalGoods2JpaDao extends CrudRepository<SalesWeekTotalGoods_2, Integer> {
	@Query("select g from SalesWeekTotalGoods_2 g where g.orgId = ?1")
	public List<SalesWeekTotalGoods_2> findByOrgId(String orgId);
}
