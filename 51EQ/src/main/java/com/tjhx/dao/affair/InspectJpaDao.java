package com.tjhx.dao.affair;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.Inspect;

public interface InspectJpaDao extends CrudRepository<Inspect, Integer> {
	@Query("select i from Inspect i where i.trsId = :trsId")
	public Inspect findByTrsId(@Param("trsId") String trsId);
}
