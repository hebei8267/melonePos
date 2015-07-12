package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

@Entity
@Table(name = "T_WEEK_SALES_TOTAL_GOODS")
public class WeekSalesTotalGoods extends IdEntity {

	private static final long serialVersionUID = 1611229299063713763L;
	/** 机构编号 */
	private String orgId;
	/** 短条码 */
	private String itemSubno;
	/** 销售数量 */
	private BigDecimal posQty;
	/** 库存数量 */
	private BigDecimal stockQty;

	/**
	 * 取得机构编号
	 * 
	 * @return 机构编号
	 */
	@NaturalId
	@Column(name = "ORG_ID", nullable = false, length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置机构编号
	 * 
	 * @param orgId 机构编号
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 取得短条码
	 * 
	 * @return 短条码
	 */
	@NaturalId
	@Column(length = 16)
	public String getItemSubno() {
		return itemSubno;
	}

	/**
	 * 设置短条码
	 * 
	 * @param itemSubno 短条码
	 */
	public void setItemSubno(String itemSubno) {
		this.itemSubno = itemSubno;
	}

	/**
	 * 取得销售数量
	 * 
	 * @return 销售数量
	 */
	public BigDecimal getPosQty() {
		return posQty;
	}

	/**
	 * 设置销售数量
	 * 
	 * @param posQty 销售数量
	 */
	public void setPosQty(BigDecimal posQty) {
		this.posQty = posQty;
	}
	/**
	 * 取得库存数量
	 * 
	 * @return stockQty 库存数量
	 */
	public BigDecimal getStockQty() {
		return stockQty;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param stockQty 库存数量
	 */
	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}
}
