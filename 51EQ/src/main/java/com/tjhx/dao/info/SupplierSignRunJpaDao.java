package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.info.SupplierSignRun;

public interface SupplierSignRunJpaDao extends CrudRepository<SupplierSignRun, Integer> {

	@Query("select r from SupplierSignRun r where r.supplierBwId = :supplierBwId and r.optDateY = :optDateY and r.optDateM = :optDateM and r.runType = :runType")
	public SupplierSignRun findSupplierSignRunByNaturalId(@Param("supplierBwId") String supplierBwId,
			@Param("optDateY") String optDateY, @Param("optDateM") String optDateM, @Param("runType") String runType);

	/**
	 * 取得数据库中保存对象（特殊标记-货品供应商 流水信息）
	 * 
	 * @param optDate
	 * @return
	 */
	@Query("select r from SupplierSignRun r where r.optDateY = :optDateY")
	public List<SupplierSignRun> findAllByOptYear(@Param("optDateY") String optDate);
}
