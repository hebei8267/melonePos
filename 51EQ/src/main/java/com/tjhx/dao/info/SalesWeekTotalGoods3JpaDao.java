package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.SalesWeekTotalGoods_3;

public interface SalesWeekTotalGoods3JpaDao extends CrudRepository<SalesWeekTotalGoods_3, Integer> {
	@Query("select g from SalesWeekTotalGoods_3 g where g.orgId = ?1")
	public List<SalesWeekTotalGoods_3> findByOrgId(String orgId);
}
