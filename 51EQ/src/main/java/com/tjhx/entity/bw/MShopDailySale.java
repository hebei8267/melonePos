package com.tjhx.entity.bw;

import java.math.BigDecimal;

public class MShopDailySale {
	/** 店铺ID */
	private String orgId;
	/** POS机编号 */
	private String posNo;
	/** 销售单号 */
	private String flowNo;
	/** 交易类型 */
	private String operType;
	/** 机构编号 */
	private String branchNo;
	/** 商品销售金额 */
	private BigDecimal salePrice = new BigDecimal("0");
	/** 商品销售数量 */
	private BigDecimal saleQnty = new BigDecimal("0");
	/** 日期 */
	private String operDate;
	/** 商品条码 */
	private String itemBarcode;

	/**
	 * 取得flowNo
	 * 
	 * @return flowNo
	 */
	public String getFlowNo() {
		return flowNo;
	}

	/**
	 * 设置flowNo
	 * 
	 * @param flowNo flowNo
	 */
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	/**
	 * 取得branchNo
	 * 
	 * @return branchNo
	 */
	public String getBranchNo() {
		return branchNo;
	}

	/**
	 * 设置branchNo
	 * 
	 * @param branchNo branchNo
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	/**
	 * 取得salePrice
	 * 
	 * @return salePrice
	 */
	public BigDecimal getSalePrice() {
		if (null != salePrice) {
			return salePrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return salePrice;
	}

	/**
	 * 设置salePrice
	 * 
	 * @param salePrice salePrice
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * 取得saleQnty
	 * 
	 * @return saleQnty
	 */
	public int getSaleQnty() {
		return saleQnty.intValue();
	}

	/**
	 * 设置saleQnty
	 * 
	 * @param saleQnty saleQnty
	 */
	public void setSaleQnty(BigDecimal saleQnty) {
		this.saleQnty = saleQnty;
	}

	/**
	 * 取得operDate
	 * 
	 * @return operDate
	 */
	public String getOperDate() {
		return operDate;
	}

	/**
	 * 设置operDate
	 * 
	 * @param operDate operDate
	 */
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	/**
	 * 取得itemBarcode
	 * 
	 * @return itemBarcode
	 */
	public String getItemBarcode() {
		return itemBarcode;
	}

	/**
	 * 设置itemBarcode
	 * 
	 * @param itemBarcode itemBarcode
	 */
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}

	/**
	 * 取得orgId
	 * 
	 * @return orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置orgId
	 * 
	 * @param orgId orgId
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 取得posNo
	 * 
	 * @return posNo
	 */
	public String getPosNo() {
		return posNo;
	}

	/**
	 * 设置posNo
	 * 
	 * @param posNo posNo
	 */
	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}

	/**
	 * 取得operType
	 * 
	 * @return operType
	 */
	public String getOperType() {
		return operType;
	}

	/**
	 * 设置operType
	 * 
	 * @param operType operType
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}

}
