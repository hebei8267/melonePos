package com.tjhx.dao.info;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.AccountFlowSplit;

public interface AccountFlowSplitJpaDao extends
		CrudRepository<AccountFlowSplit, Integer> {

}
