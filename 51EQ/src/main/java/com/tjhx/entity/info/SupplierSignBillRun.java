package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 货品供应商挂账流水信息
 */
@Entity
@Table(name = "T_SUPPLIER_SIGN_BILL_RUN")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SupplierSignBillRun extends IdEntity {


	private static final long serialVersionUID = 2055141709136700983L;

	/** 供应商编号-百威 */
	private String supplierBwId;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	
	/**挂账 */
	private BigDecimal gAmt = new BigDecimal("0");
	/**对账 */
	private BigDecimal dAmt = new BigDecimal("0");
	/**结账 */
	private BigDecimal jAmt = new BigDecimal("0");
}
