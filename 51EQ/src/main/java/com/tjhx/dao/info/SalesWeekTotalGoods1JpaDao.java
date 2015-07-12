package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.SalesWeekTotalGoods_1;

public interface SalesWeekTotalGoods1JpaDao extends CrudRepository<SalesWeekTotalGoods_1, Integer> {
	@Query("select g from SalesWeekTotalGoods_1 g where g.orgId = ?1")
	public List<SalesWeekTotalGoods_1> findByOrgId(String orgId);
}
