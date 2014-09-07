package com.tjhx.dao.affair;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.BorrowItemRun;

public interface BorrowItemRunJpaDao extends CrudRepository<BorrowItemRun, Integer> {

	@Query("select b from BorrowItemRun b where b.itemNo = :itemNo order by b.createDate desc")
	public Iterable<BorrowItemRun> findByItemNo(@Param("itemNo") String itemNo);
}
