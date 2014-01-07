package com.tjhx.dao.accounts;

import java.util.List;

import com.tjhx.entity.accounts.StoreRun;

public interface StoreRunMyBatisDao {

	public List<StoreRun> getStoreRunList(StoreRun storeRun);

}
