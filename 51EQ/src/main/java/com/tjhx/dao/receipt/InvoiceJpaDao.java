package com.tjhx.dao.receipt;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.receipt.Invoice;

public interface InvoiceJpaDao extends CrudRepository<Invoice, Integer> {

	@Query("select i from Invoice i where i.orgId = :orgId and i.appDateY = :appDateY and i.appDateM = :appDateM")
	List<Invoice> findByOrgId_AppDateY_AppDateM(@Param("orgId") String orgId, @Param("appDateY") String appDateY,
			@Param("appDateM") String appDateM, Sort sort);

}
