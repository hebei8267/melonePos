package com.tjhx.entity.info;

import java.math.BigDecimal;
import java.util.Map;

import com.google.common.collect.Maps;

public class SalesMonthTotal_Org_Show {
	/** 月份 */
	private String optDateYM;
	/** 实销金额 */
	private BigDecimal saleTotalRamt = new BigDecimal(0);
	/** 机构数量 */
	private int orgCount = 0;
	/** 机构实销金额 */
	Map<String, BigDecimal> saleRamtMap = Maps.newHashMap();

	/**
	 * 取得
	 * 
	 * @return 月份
	 */
	public String getOptDateYM() {
		return optDateYM;
	}

	/**
	 * 设置
	 * 
	 * @param 月份 optDateYM
	 */
	public void setOptDateYM(String optDateYM) {
		this.optDateYM = optDateYM;
	}

	/**
	 * 取得
	 * 
	 * @return 实销金额
	 */
	public BigDecimal getSaleTotalRamt() {
		return saleTotalRamt;
	}

	/**
	 * 设置
	 * 
	 * @param 实销金额 saleTotalRamt
	 */
	public void setSaleTotalRamt(BigDecimal saleTotalRamt) {
		this.saleTotalRamt = saleTotalRamt;
	}

	/**
	 * 取得
	 * 
	 * @return 机构数量
	 */
	public int getOrgCount() {
		return orgCount;
	}

	/**
	 * 设置
	 * 
	 * @param 机构数量 orgCount
	 */
	public void setOrgCount(int orgCount) {
		this.orgCount = orgCount;
	}

	/**
	 * 取得
	 * 
	 * @return 机构实销金额
	 */
	public Map<String, BigDecimal> getSaleRamtMap() {
		return saleRamtMap;
	}

	/**
	 * 设置
	 * 
	 * @param 机构实销金额 saleRamtMap
	 */
	public void setSaleRamtMap(Map<String, BigDecimal> saleRamtMap) {
		this.saleRamtMap = saleRamtMap;
	}

	public void setSaleRamt(String orgId, BigDecimal saleRamt) {
		if (null != orgId && orgId.length() == 6) {
			this.saleRamtMap.put("saleRamt" + orgId.substring(3, 5), saleRamt);
		}
	}

}
