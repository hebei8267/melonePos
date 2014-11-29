package com.tjhx.daobw;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.bw.Store;

public interface StoreMyBatisDao {

	public List<Store> getStoreInfoList(Map<String,String> param);

}
