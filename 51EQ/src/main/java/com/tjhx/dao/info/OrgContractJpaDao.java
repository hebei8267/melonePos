package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.OrgContract;

/**
 * 机构合同
 */
public interface OrgContractJpaDao extends CrudRepository<OrgContract, Integer> {

	/**
	 * 取得机构合同信息（含失效期三个月以内）
	 * 
	 * @param validateData
	 * @return
	 */
	@Query("select oc from OrgContract oc where oc.endDate >= ?1 order by oc.orgId , oc.startDate desc")
	List<OrgContract> getOrgContractList(String validateData);

	@Query("select oc from OrgContract oc where oc.orgId = ?1 AND oc.startDate = ?2")
	OrgContract getOrgContractByOrgIdAndStartDate(String orgId, String startDate);

}
