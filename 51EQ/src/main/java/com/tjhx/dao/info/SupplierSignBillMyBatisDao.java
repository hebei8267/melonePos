package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.Supplier;

public interface SupplierSignBillMyBatisDao {

	/**
	 * 删除所有挂账供应商信息
	 */
	public void delAllSignBillSupplierInfo();

	/**
	 * 取得所有货品供应商(含挂账)信息
	 * 
	 * @return
	 */
	public List<Supplier> getAllSupplierINfo_SignBill();

}
