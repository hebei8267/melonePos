package com.tjhx.dao.affair;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.RunInspect;

public interface RunInspectJpaDao extends CrudRepository<RunInspect, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@Query("select run from RunInspect run where run.trsId = :trsId")
	public RunInspect findByTrsId(@Param("trsId") String trsId);

	/**
	 * @param optDateStart
	 * @param optDateEnd
	 */
	@Query("select run from RunInspect run where run.optDate >= :optDateStart and run.optDate <= :optDateEnd")
	public List<RunInspect> getRunInspectList(@Param("optDateStart") String optDateStart, @Param("optDateEnd") String optDateEnd);

	/**
	 * @param orgId
	 * @param optDateStart
	 * @param optDateEnd
	 */
	@Query("select run from RunInspect run where run.orgId >= :orgId and run.optDate >= :optDateStart and run.optDate <= :optDateEnd")
	public List<RunInspect> getRunInspectList(@Param("orgId") String orgId, @Param("optDateStart") String optDateStart,
			@Param("optDateEnd") String optDateEnd);

}
