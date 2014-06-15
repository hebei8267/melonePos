package com.tjhx.dao.affair;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.RunInspectDetail;

public interface RunInspectDetailJpaDao extends CrudRepository<RunInspectDetail, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@Query("select run from RunInspectDetail run where run.trsId = :trsId")
	public List<RunInspectDetail> getRunInspectDetailList(@Param("trsId") String trsId);
}
