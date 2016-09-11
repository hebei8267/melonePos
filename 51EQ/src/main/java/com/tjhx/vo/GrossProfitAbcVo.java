package com.tjhx.vo;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.tjhx.entity.info.SalesDayTotalGoods;

public class GrossProfitAbcVo {
	private List<SalesDayTotalGoods> listA = Lists.newArrayList();
	private List<SalesDayTotalGoods> listB = Lists.newArrayList();
	private List<SalesDayTotalGoods> listC = Lists.newArrayList();
	private List<SalesDayTotalGoods> listD = Lists.newArrayList();

	private BigDecimal totalQty = new BigDecimal("0");
	private BigDecimal totalAQty = new BigDecimal("0");
	private BigDecimal totalBQty = new BigDecimal("0");
	private BigDecimal totalCQty = new BigDecimal("0");

	private BigDecimal totalAmt = new BigDecimal("0");
	private BigDecimal totalAAmt = new BigDecimal("0");
	private BigDecimal totalBAmt = new BigDecimal("0");
	private BigDecimal totalCAmt = new BigDecimal("0");

	private BigDecimal totalAStockAmt = new BigDecimal("0");
	private BigDecimal totalBStockAmt = new BigDecimal("0");
	private BigDecimal totalCStockAmt = new BigDecimal("0");
	private BigDecimal totalDStockAmt = new BigDecimal("0");

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
	 * 取得totalQty
	 * 
	 * @return totalQty
	 */
	public BigDecimal getTotalQty() {
		return totalQty;
	}

	/**
	 * 设置totalQty
	 * 
	 * @param totalQty totalQty
	 */
	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}

	/**
	 * 取得totalAQty
	 * 
	 * @return totalAQty
	 */
	public BigDecimal getTotalAQty() {
		return totalAQty;
	}

	/**
	 * 设置totalAQty
	 * 
	 * @param totalAQty totalAQty
	 */
	public void setTotalAQty(BigDecimal totalAQty) {
		this.totalAQty = totalAQty;
	}

	/**
	 * 取得totalBQty
	 * 
	 * @return totalBQty
	 */
	public BigDecimal getTotalBQty() {
		return totalBQty;
	}

	/**
	 * 设置totalBQty
	 * 
	 * @param totalBQty totalBQty
	 */
	public void setTotalBQty(BigDecimal totalBQty) {
		this.totalBQty = totalBQty;
	}

	/**
	 * 取得totalCQty
	 * 
	 * @return totalCQty
	 */
	public BigDecimal getTotalCQty() {
		return totalCQty;
	}

	/**
	 * 设置totalCQty
	 * 
	 * @param totalCQty totalCQty
	 */
	public void setTotalCQty(BigDecimal totalCQty) {
		this.totalCQty = totalCQty;
	}

	/**
	 * 取得totalAmt
	 * 
	 * @return totalAmt
	 */
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	/**
	 * 设置totalAmt
	 * 
	 * @param totalAmt totalAmt
	 */
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	/**
	 * 取得totalAAmt
	 * 
	 * @return totalAAmt
	 */
	public BigDecimal getTotalAAmt() {
		return totalAAmt;
	}

	/**
	 * 设置totalAAmt
	 * 
	 * @param totalAAmt totalAAmt
	 */
	public void setTotalAAmt(BigDecimal totalAAmt) {
		this.totalAAmt = totalAAmt;
	}

	/**
	 * 取得totalBAmt
	 * 
	 * @return totalBAmt
	 */
	public BigDecimal getTotalBAmt() {
		return totalBAmt;
	}

	/**
	 * 设置totalBAmt
	 * 
	 * @param totalBAmt totalBAmt
	 */
	public void setTotalBAmt(BigDecimal totalBAmt) {
		this.totalBAmt = totalBAmt;
	}

	/**
	 * 取得totalCAmt
	 * 
	 * @return totalCAmt
	 */
	public BigDecimal getTotalCAmt() {
		return totalCAmt;
	}

	/**
	 * 设置totalCAmt
	 * 
	 * @param totalCAmt totalCAmt
	 */
	public void setTotalCAmt(BigDecimal totalCAmt) {
		this.totalCAmt = totalCAmt;
	}

	/**
	 * 取得totalAStockAmt
	 * 
	 * @return totalAStockAmt
	 */
	public BigDecimal getTotalAStockAmt() {
		return totalAStockAmt;
	}

	/**
	 * 设置totalAStockAmt
	 * 
	 * @param totalAStockAmt totalAStockAmt
	 */
	public void setTotalAStockAmt(BigDecimal totalAStockAmt) {
		this.totalAStockAmt = totalAStockAmt;
	}

	/**
	 * 取得totalBStockAmt
	 * 
	 * @return totalBStockAmt
	 */
	public BigDecimal getTotalBStockAmt() {
		return totalBStockAmt;
	}

	/**
	 * 设置totalBStockAmt
	 * 
	 * @param totalBStockAmt totalBStockAmt
	 */
	public void setTotalBStockAmt(BigDecimal totalBStockAmt) {
		this.totalBStockAmt = totalBStockAmt;
	}

	/**
	 * 取得totalCStockAmt
	 * 
	 * @return totalCStockAmt
	 */
	public BigDecimal getTotalCStockAmt() {
		return totalCStockAmt;
	}

	/**
	 * 设置totalCStockAmt
	 * 
	 * @param totalCStockAmt totalCStockAmt
	 */
	public void setTotalCStockAmt(BigDecimal totalCStockAmt) {
		this.totalCStockAmt = totalCStockAmt;
	}

	/**
	 * 取得listD
	 * 
	 * @return listD
	 */
	public List<SalesDayTotalGoods> getListD() {
		return listD;
	}

	/**
	 * 设置listD
	 * 
	 * @param listD listD
	 */
	public void setListD(List<SalesDayTotalGoods> listD) {
		this.listD = listD;
	}

	/**
	 * 取得totalDStockAmt
	 * 
	 * @return totalDStockAmt
	 */
	public BigDecimal getTotalDStockAmt() {
		return totalDStockAmt;
	}

	/**
	 * 设置totalDStockAmt
	 * 
	 * @param totalDStockAmt totalDStockAmt
	 */
	public void setTotalDStockAmt(BigDecimal totalDStockAmt) {
		this.totalDStockAmt = totalDStockAmt;
	}

}
