package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.AccountFlowSplit;

public interface AccountFlowSplitJpaDao extends CrudRepository<AccountFlowSplit, Integer> {

}
