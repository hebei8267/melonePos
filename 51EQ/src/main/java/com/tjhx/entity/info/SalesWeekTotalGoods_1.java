package com.tjhx.entity.info;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 各店销售汇总(按商品)
 */
@Entity
@Table(name = "T_SALES_WEEK_TOTAL_GOODS_1")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesWeekTotalGoods_1 extends IdEntity {

	private static final long serialVersionUID = -4957907795089058078L;
	/** 机构编号 */
	private String orgId;
	/** 机构资金编号 */
	private String branchNo;
	/** 统计日期 */
	private String optDate;
	/** 短条码 */
	private String itemSubno;
	/** 长条码 */
	private String itemBarcode;
	/** 销售数量-近1周 */
	private BigDecimal posQty;

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
	 * 取得统计日期
	 * 
	 * @return optDate 统计日期
	 */
	@Column(length = 8, nullable = false)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置统计日期
	 * 
	 * @param optDate 统计日期
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得短条码
	 * 
	 * @return itemSubno 短条码
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
	 * 取得长条码
	 * 
	 * @return itemBarcode 长条码
	 */
	@Column(length = 16)
	public String getItemBarcode() {
		return itemBarcode;
	}

	/**
	 * 设置长条码
	 * 
	 * @param itemBarcode 长条码
	 */
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}

	/**
	 * 取得销售数量-近1周
	 * 
	 * @return posQty 销售数量-近1周
	 */
	public BigDecimal getPosQty() {
		return posQty;
	}

	/**
	 * 设置销售数量-近1周
	 * 
	 * @param posQty 销售数量-近1周
	 */
	public void setPosQty(BigDecimal posQty) {
		this.posQty = posQty;
	}

}
