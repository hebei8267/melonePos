package com.tjhx.dao.struct;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.struct.Organization;

public interface OrganizationJpaDao extends CrudRepository<Organization, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得机构信息
	 * 
	 * @return 机构信息
	 */
	public Organization findByBwId(String bwId);

	/**
	 * 取得机构开店数量
	 * 
	 * @return
	 */
	@Query("select count(o) from Organization o where o.closedFlg = 0")
	public Long getOpenOrganizationCount();
}
