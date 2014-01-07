package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 每日各店库存合计(将每日各店库存明细规整为两条记录)
 */
@Entity
@Table(name = "T_STORE_DAY_TOTAL")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StoreDayTotal extends IdEntity {

	private static final long serialVersionUID = 9047401151413675951L;
	/** 机构编号 */
	private String orgId;
	/** 机构名称 */
	private String orgName;
	/** 机构资金-百威 */
	private String bwBranchNo;
	/** 日期 */
	private String optDate;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 库存标记 0-正库存 1-负库存 */
	private String storeFlg;
	/** 库存数量 */
	private BigDecimal stockTotalQty;
	/** 库存金额 */
	private BigDecimal stockTotalAmt;
	/** 售价金额 */
	private BigDecimal itemSaleTotalAmt;

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
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
	 * 取得机构名称
	 * 
	 * @return orgName 机构名称
	 */
	@Transient
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置机构名称
	 * 
	 * @param orgName 机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 取得机构资金-百威
	 * 
	 * @return bwBranchNo 机构资金-百威
	 */
	@Column(length = 8)
	public String getBwBranchNo() {
		return bwBranchNo;
	}

	/**
	 * 设置机构资金-百威
	 * 
	 * @param bwBranchNo 机构资金-百威
	 */
	public void setBwBranchNo(String bwBranchNo) {
		this.bwBranchNo = bwBranchNo;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDate 日期
	 */
	@NaturalId
	@Column(length = 8, nullable = false)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDate 日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得日期-年
	 * 
	 * @return optDateY 日期-年
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置日期-年
	 * 
	 * @param optDateY 日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得日期-月
	 * 
	 * @return optDateM 日期-月
	 */
	@Column(name = "OPT_DATE_M", length = 2)
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置日期-月
	 * 
	 * @param optDateM 日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得库存标记0-正库存1-负库存
	 * 
	 * @return storeFlg 库存标记0-正库存1-负库存
	 */
	@NaturalId
	@Column(length = 1, nullable = false)
	public String getStoreFlg() {
		return storeFlg;
	}

	/**
	 * 设置库存标记0-正库存1-负库存
	 * 
	 * @param storeFlg 库存标记0-正库存1-负库存
	 */
	public void setStoreFlg(String storeFlg) {
		this.storeFlg = storeFlg;
	}

	/**
	 * 取得库存数量
	 * 
	 * @return stockTotalQty 库存数量
	 */
	public BigDecimal getStockTotalQty() {
		return stockTotalQty;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param stockTotalQty 库存数量
	 */
	public void setStockTotalQty(BigDecimal stockTotalQty) {
		this.stockTotalQty = stockTotalQty;
	}

	/**
	 * 取得库存金额
	 * 
	 * @return stockTotalAmt 库存金额
	 */
	public BigDecimal getStockTotalAmt() {
		return stockTotalAmt;
	}

	/**
	 * 设置库存金额
	 * 
	 * @param stockTotalAmt 库存金额
	 */
	public void setStockTotalAmt(BigDecimal stockTotalAmt) {
		this.stockTotalAmt = stockTotalAmt;
	}

	/**
	 * 取得售价金额
	 * 
	 * @return itemSaleTotalAmt 售价金额
	 */
	public BigDecimal getItemSaleTotalAmt() {
		return itemSaleTotalAmt;
	}

	/**
	 * 设置售价金额
	 * 
	 * @param itemSaleTotalAmt 售价金额
	 */
	public void setItemSaleTotalAmt(BigDecimal itemSaleTotalAmt) {
		this.itemSaleTotalAmt = itemSaleTotalAmt;
	}

	public boolean myEquals(Object obj) {
		if (!(obj instanceof StoreDayTotal)) {
			return false;
		}
		StoreDayTotal rhs = (StoreDayTotal) obj;
		return new EqualsBuilder().append(this.getOrgId(), rhs.getOrgId()).append(this.getOptDate(), rhs.getOptDate())
				.isEquals();
	}
}
