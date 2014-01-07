package com.tjhx.dao.affair;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.WorkType;

public interface WorkTypeJpaDao extends CrudRepository<WorkType, Integer> {
	@Query("select w from WorkType w, Organization org where w.organization = org and org.id = :orgId")
	public List<WorkType> getWorkTypeListByOrgId(@Param("orgId") String orgId);

	@Query("select w from WorkType w, Organization org where w.organization = org and org.id = :orgId and w.useFlg = '1'")
	public List<WorkType> getValidWorkTypeByOrgId(@Param("orgId") String orgId);
}
