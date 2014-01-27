package com.tjhx.dao.info;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.info.SupplierSignRun;

public interface SupplierSignRunJpaDao extends CrudRepository<SupplierSignRun, Integer> {

	@Query("select r from SupplierSignRun r where r.supplierBwId = :supplierBwId and r.optDateY = :optDateY and r.optDateM = :optDateM and r.runType = :runType")
	public SupplierSignRun findSupplierSignRunByNaturalId(@Param("supplierBwId") String supplierBwId,
			@Param("optDateY") String optDateY, @Param("optDateM") String optDateM, @Param("runType") String runType);
}
