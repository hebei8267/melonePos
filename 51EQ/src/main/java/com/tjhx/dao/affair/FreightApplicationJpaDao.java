package com.tjhx.dao.affair;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.affair.FreightApplication;

public interface FreightApplicationJpaDao extends CrudRepository<FreightApplication, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得货运申请信息
	 * 
	 * @param appDate 申请日期
	 * @param appOrgId 申请机构
	 * @param targetOrgId 调货目的机构
	 * @return
	 */
	public FreightApplication findByAppDateAndAppOrgIdAndTargetOrgId(String appDate, String appOrgId, String targetOrgId);
}
