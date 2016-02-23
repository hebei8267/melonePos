package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.OrgContractPayRun;

/**
 * 机构合同
 */

public interface OrgContractPayRunJpaDao extends CrudRepository<OrgContractPayRun, Integer> {
	public List<OrgContractPayRun> findAll(Sort sort);
}
