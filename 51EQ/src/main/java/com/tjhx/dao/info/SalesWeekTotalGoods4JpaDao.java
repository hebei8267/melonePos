package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.SalesWeekTotalGoods_4;

public interface SalesWeekTotalGoods4JpaDao extends CrudRepository<SalesWeekTotalGoods_4, Integer> {
	@Query("select g from SalesWeekTotalGoods_4 g where g.orgId = ?1")
	public List<SalesWeekTotalGoods_4> findByOrgId(String orgId);
}
