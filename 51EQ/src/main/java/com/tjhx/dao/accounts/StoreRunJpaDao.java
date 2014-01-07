package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.StoreRun;

public interface StoreRunJpaDao extends CrudRepository<StoreRun, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@SuppressWarnings("rawtypes")
	@Query("select s from StoreRun s where s.orgId = :orgId and s.recordDateY = :recordDateY and s.recordDateM = :recordDateM")
	public Iterable findByOrgId_RecordDateY_RecordDateM(@Param("orgId") String orgId,
			@Param("recordDateY") String recordDateY, @Param("recordDateM") String recordDateM, Sort sort);

	public StoreRun findByOrgIdAndRecordNo(String orgId, String recordNo);
}
