package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.SupplierSignRun;

public interface SupplierSignRunMyBatisDao {

	/**
	 * 根据货商编号和年份取得流水信息
	 * 
	 * @param supplierSignRun
	 * @return
	 */
	public List<SupplierSignRun> getSupplierSignRunList(SupplierSignRun supplierSignRun);
}
