package com.tjhx.vo;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.tjhx.entity.info.SalesDayTotalGoods;

public class GrossProfitAbcVo {
	private List<SalesDayTotalGoods> listA = Lists.newArrayList();
	private List<SalesDayTotalGoods> listB = Lists.newArrayList();
	private List<SalesDayTotalGoods> listC = Lists.newArrayList();

	private BigDecimal total = new BigDecimal("0");
	private BigDecimal totalA = new BigDecimal("0");
	private BigDecimal totalB = new BigDecimal("0");
	private BigDecimal totalC = new BigDecimal("0");

	/**
	 * 取得listA
	 * 
	 * @return listA
	 */
	public List<SalesDayTotalGoods> getListA() {
		return listA;
	}

	/**
	 * 设置listA
	 * 
	 * @param listA listA
	 */
	public void setListA(List<SalesDayTotalGoods> listA) {
		this.listA = listA;
	}

	/**
	 * 取得listB
	 * 
	 * @return listB
	 */
	public List<SalesDayTotalGoods> getListB() {
		return listB;
	}

	/**
	 * 设置listB
	 * 
	 * @param listB listB
	 */
	public void setListB(List<SalesDayTotalGoods> listB) {
		this.listB = listB;
	}

	/**
	 * 取得listC
	 * 
	 * @return listC
	 */
	public List<SalesDayTotalGoods> getListC() {
		return listC;
	}

	/**
	 * 设置listC
	 * 
	 * @param listC listC
	 */
	public void setListC(List<SalesDayTotalGoods> listC) {
		this.listC = listC;
	}

	/**
	 * 取得total
	 * 
	 * @return total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * 设置total
	 * 
	 * @param total total
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * 取得totalA
	 * 
	 * @return totalA
	 */
	public BigDecimal getTotalA() {
		return totalA;
	}

	/**
	 * 设置totalA
	 * 
	 * @param totalA totalA
	 */
	public void setTotalA(BigDecimal totalA) {
		this.totalA = totalA;
	}

	/**
	 * 取得totalB
	 * 
	 * @return totalB
	 */
	public BigDecimal getTotalB() {
		return totalB;
	}

	/**
	 * 设置totalB
	 * 
	 * @param totalB totalB
	 */
	public void setTotalB(BigDecimal totalB) {
		this.totalB = totalB;
	}

	/**
	 * 取得totalC
	 * 
	 * @return totalC
	 */
	public BigDecimal getTotalC() {
		return totalC;
	}

	/**
	 * 设置totalC
	 * 
	 * @param totalC totalC
	 */
	public void setTotalC(BigDecimal totalC) {
		this.totalC = totalC;
	}
}
