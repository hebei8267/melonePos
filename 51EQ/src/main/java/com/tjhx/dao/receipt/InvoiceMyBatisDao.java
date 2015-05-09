package com.tjhx.dao.receipt;

import java.util.List;

import com.tjhx.entity.receipt.Invoice;

public interface InvoiceMyBatisDao {

	/**
	 * 根据参数取得发票信息
	 * 
	 * @return
	 */
	public List<Invoice> getInvoiceDrawList(Invoice invoice);

}
