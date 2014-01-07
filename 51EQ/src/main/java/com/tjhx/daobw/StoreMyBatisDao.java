package com.tjhx.daobw;

import java.util.List;

import com.tjhx.entity.bw.Store;

public interface StoreMyBatisDao {

	public List<Store> getStoreInfoList(String bwBranchNo);

}
