package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.Supplier;

public interface SupplierSignMyBatisDao {

	/**
	 * 删除所有挂账供应商信息
	 */
	public void delAllSignSupplierInfo();

	/**
	 * 取得所有货品供应商(含特殊标记供应商)信息
	 * 
	 * @return
	 */
	public List<Supplier> getAllSupplierInfo_Sign();

	/**
	 * 取得货品供应商列表
	 * 
	 * @param supplierSign
	 * @return
	 */
	public List<Supplier> getSupplierList(String optYear);
}
