package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 每日各店销售汇总(按类别)
 */
@Entity
@Table(name = "T_SALES_DAY_TOTAL_ITEM")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesDayTotalItem extends IdEntity {

	private static final long serialVersionUID = -1760921041803697302L;
	/** 机构编号 */
	private String orgId;
	/** 机构资金编号 */
	private String branchNo;
	/** 日期 */
	private String optDate;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 类别编号 */
	private String itemClsNo;
	/** 销售数量 */
	private BigDecimal saleQty;
	/** 销售金额 */
	private BigDecimal saleAmt;
	/** 退货数量 */
	private BigDecimal retQty;
	/** 退货金额 */
	private BigDecimal retAmt;
	/** 赠送数量 */
	private BigDecimal giveQty;
	/** 实销数量 */
	private BigDecimal saleRqty;
	/** 实销金额 */
	private BigDecimal saleRamt;
	/** 实销价格 */
	private BigDecimal salePrice;
	// -----------------------------------------------------------
	/** 种类名称 */
	private String itemShortName;
	/** 种类名称 */
	private String itemName;
	/** 日期-开始时间 */
	private String optDateStart;
	/** 日期-结束时间 */
	private String optDateEnd;
	/** 用户关联机构名称 */
	private String orgName;

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
	 * 取得机构资金编号
	 * 
	 * @return branchNo 机构资金编号
	 */
	@Column(length = 8)
	public String getBranchNo() {
		return branchNo;
	}

	/**
	 * 设置机构资金编号
	 * 
	 * @param branchNo 机构资金编号
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
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
	 * 取得类别编号
	 * 
	 * @return itemClsNo 类别编号
	 */
	@NaturalId
	@Column(length = 16)
	public String getItemClsNo() {
		return itemClsNo;
	}

	/**
	 * 设置类别编号
	 * 
	 * @param itemClsNo 类别编号
	 */
	public void setItemClsNo(String itemClsNo) {
		this.itemClsNo = itemClsNo;
	}

	/**
	 * 取得销售数量
	 * 
	 * @return saleQty 销售数量
	 */
	public BigDecimal getSaleQty() {
		return saleQty;
	}

	/**
	 * 设置销售数量
	 * 
	 * @param saleQty 销售数量
	 */
	public void setSaleQty(BigDecimal saleQty) {
		this.saleQty = saleQty;
	}

	/**
	 * 取得销售金额
	 * 
	 * @return saleAmt 销售金额
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置销售金额
	 * 
	 * @param saleAmt 销售金额
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得退货数量
	 * 
	 * @return retQty 退货数量
	 */
	public BigDecimal getRetQty() {
		return retQty;
	}

	/**
	 * 设置退货数量
	 * 
	 * @param retQty 退货数量
	 */
	public void setRetQty(BigDecimal retQty) {
		this.retQty = retQty;
	}

	/**
	 * 取得退货金额
	 * 
	 * @return retAmt 退货金额
	 */
	public BigDecimal getRetAmt() {
		return retAmt;
	}

	/**
	 * 设置退货金额
	 * 
	 * @param retAmt 退货金额
	 */
	public void setRetAmt(BigDecimal retAmt) {
		this.retAmt = retAmt;
	}

	/**
	 * 取得赠送数量
	 * 
	 * @return giveQty 赠送数量
	 */
	public BigDecimal getGiveQty() {
		return giveQty;
	}

	/**
	 * 设置赠送数量
	 * 
	 * @param giveQty 赠送数量
	 */
	public void setGiveQty(BigDecimal giveQty) {
		this.giveQty = giveQty;
	}

	/**
	 * 取得实销数量
	 * 
	 * @return saleRqty 实销数量
	 */
	public BigDecimal getSaleRqty() {
		return saleRqty;
	}

	/**
	 * 设置实销数量
	 * 
	 * @param saleRqty 实销数量
	 */
	public void setSaleRqty(BigDecimal saleRqty) {
		this.saleRqty = saleRqty;
	}

	/**
	 * 取得实销金额
	 * 
	 * @return saleRamt 实销金额
	 */
	public BigDecimal getSaleRamt() {
		return saleRamt;
	}

	/**
	 * 设置实销金额
	 * 
	 * @param saleRamt 实销金额
	 */
	public void setSaleRamt(BigDecimal saleRamt) {
		this.saleRamt = saleRamt;
	}

	/**
	 * 取得实销价格
	 * 
	 * @return salePrice 实销价格
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * 设置实销价格
	 * 
	 * @param salePrice 实销价格
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	// -----------------------------------------------------------
	/**
	 * 取得种类名称
	 * 
	 * @return itemShortName 种类名称
	 */
	@Transient
	public String getItemShortName() {
		return itemShortName;
	}

	/**
	 * 设置种类名称
	 * 
	 * @param itemShortName 种类名称
	 */
	public void setItemShortName(String itemShortName) {
		this.itemShortName = itemShortName;
	}

	/**
	 * 取得种类名称
	 * 
	 * @return itemName 种类名称
	 */
	@Transient
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置种类名称
	 * 
	 * @param itemName 种类名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 取得日期-开始时间
	 * 
	 * @return optDateStart 日期-开始时间
	 */
	@Transient
	public String getOptDateStart() {
		return optDateStart;
	}

	/**
	 * 设置日期-开始时间
	 * 
	 * @param optDateStart 日期-开始时间
	 */
	public void setOptDateStart(String optDateStart) {
		this.optDateStart = optDateStart;
	}

	/**
	 * 取得日期-结束时间
	 * 
	 * @return optDateEnd 日期-结束时间
	 */
	@Transient
	public String getOptDateEnd() {
		return optDateEnd;
	}

	/**
	 * 设置日期-结束时间
	 * 
	 * @param optDateEnd 日期-结束时间
	 */
	public void setOptDateEnd(String optDateEnd) {
		this.optDateEnd = optDateEnd;
	}

	/**
	 * 取得用户关联机构名称
	 * 
	 * @return orgName 用户关联机构名称
	 */
	@Transient
	public String getOrgName() {
		if (null == orgName && null != orgId) {
			orgName = orgId.substring(3, 6);
		}
		return orgName;
	}

	/**
	 * 设置用户关联机构名称
	 * 
	 * @param orgName 用户关联机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
