package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.StoreDetail;

public interface StoreDetailJpaDao extends CrudRepository<StoreDetail, Integer> {
	@Query("select s from StoreDetail s where s.orgId = ?1 and s.optDate= ?2")
	public List<StoreDetail> findByOrgIdAndOptDate(String orgId, String optDate);
}
