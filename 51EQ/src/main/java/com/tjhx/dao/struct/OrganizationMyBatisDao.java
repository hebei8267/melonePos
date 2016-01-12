package com.tjhx.dao.struct;

import java.util.List;

import com.tjhx.entity.struct.Organization;

public interface OrganizationMyBatisDao {

	/**
	 * 取得机构信息列表
	 * 
	 * @return
	 */
	public List<Organization> getAllOrgList();

}
