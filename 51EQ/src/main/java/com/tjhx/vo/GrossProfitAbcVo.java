package com.tjhx.vo;

import java.util.List;

import com.google.common.collect.Lists;
import com.tjhx.entity.info.SalesDayTotalGoods;

public class GrossProfitAbcVo {
	private List<SalesDayTotalGoods> listA = Lists.newArrayList();
	private List<SalesDayTotalGoods> listB = Lists.newArrayList();
	private List<SalesDayTotalGoods> listC = Lists.newArrayList();

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
}
