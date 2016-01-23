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
//	/** 实销金额 */
//	private BigDecimal saleRamt01 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt02 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt03 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt04 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt05 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt06 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt07 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt08 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt09 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt10 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt11 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt12 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt13 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt14 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt15 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt16 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt17 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt18 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt19 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt20 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt21 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt22 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt23 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt24 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt25 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt26 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt27 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt28 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt29 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt30 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt31 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt32 = new BigDecimal(0);
//	/** 实销金额 */
//	private BigDecimal saleRamt33 = new BigDecimal(0);
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

//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt01() {
//		return saleRamt01;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt01
//	 */
//	public void setSaleRamt01(BigDecimal saleRamt01) {
//		this.saleRamt01 = saleRamt01;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt02() {
//		return saleRamt02;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt02
//	 */
//	public void setSaleRamt02(BigDecimal saleRamt02) {
//		this.saleRamt02 = saleRamt02;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt03() {
//		return saleRamt03;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt03
//	 */
//	public void setSaleRamt03(BigDecimal saleRamt03) {
//		this.saleRamt03 = saleRamt03;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt04() {
//		return saleRamt04;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt04
//	 */
//	public void setSaleRamt04(BigDecimal saleRamt04) {
//		this.saleRamt04 = saleRamt04;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt05() {
//		return saleRamt05;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt05
//	 */
//	public void setSaleRamt05(BigDecimal saleRamt05) {
//		this.saleRamt05 = saleRamt05;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt06() {
//		return saleRamt06;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt06
//	 */
//	public void setSaleRamt06(BigDecimal saleRamt06) {
//		this.saleRamt06 = saleRamt06;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt07() {
//		return saleRamt07;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt07
//	 */
//	public void setSaleRamt07(BigDecimal saleRamt07) {
//		this.saleRamt07 = saleRamt07;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt08() {
//		return saleRamt08;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt08
//	 */
//	public void setSaleRamt08(BigDecimal saleRamt08) {
//		this.saleRamt08 = saleRamt08;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt09() {
//		return saleRamt09;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt09
//	 */
//	public void setSaleRamt09(BigDecimal saleRamt09) {
//		this.saleRamt09 = saleRamt09;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt10() {
//		return saleRamt10;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt10
//	 */
//	public void setSaleRamt10(BigDecimal saleRamt10) {
//		this.saleRamt10 = saleRamt10;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt11() {
//		return saleRamt11;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt11
//	 */
//	public void setSaleRamt11(BigDecimal saleRamt11) {
//		this.saleRamt11 = saleRamt11;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt12() {
//		return saleRamt12;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt12
//	 */
//	public void setSaleRamt12(BigDecimal saleRamt12) {
//		this.saleRamt12 = saleRamt12;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt13() {
//		return saleRamt13;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt13
//	 */
//	public void setSaleRamt13(BigDecimal saleRamt13) {
//		this.saleRamt13 = saleRamt13;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt14() {
//		return saleRamt14;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt14
//	 */
//	public void setSaleRamt14(BigDecimal saleRamt14) {
//		this.saleRamt14 = saleRamt14;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt15() {
//		return saleRamt15;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt15
//	 */
//	public void setSaleRamt15(BigDecimal saleRamt15) {
//		this.saleRamt15 = saleRamt15;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt16() {
//		return saleRamt16;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt16
//	 */
//	public void setSaleRamt16(BigDecimal saleRamt16) {
//		this.saleRamt16 = saleRamt16;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt17() {
//		return saleRamt17;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt17
//	 */
//	public void setSaleRamt17(BigDecimal saleRamt17) {
//		this.saleRamt17 = saleRamt17;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt18() {
//		return saleRamt18;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt18
//	 */
//	public void setSaleRamt18(BigDecimal saleRamt18) {
//		this.saleRamt18 = saleRamt18;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt19() {
//		return saleRamt19;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt19
//	 */
//	public void setSaleRamt19(BigDecimal saleRamt19) {
//		this.saleRamt19 = saleRamt19;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt20() {
//		return saleRamt20;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt20
//	 */
//	public void setSaleRamt20(BigDecimal saleRamt20) {
//		this.saleRamt20 = saleRamt20;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt21() {
//		return saleRamt21;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt21
//	 */
//	public void setSaleRamt21(BigDecimal saleRamt21) {
//		this.saleRamt21 = saleRamt21;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt22() {
//		return saleRamt22;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt22
//	 */
//	public void setSaleRamt22(BigDecimal saleRamt22) {
//		this.saleRamt22 = saleRamt22;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt23() {
//		return saleRamt23;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt23
//	 */
//	public void setSaleRamt23(BigDecimal saleRamt23) {
//		this.saleRamt23 = saleRamt23;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt24() {
//		return saleRamt24;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt24
//	 */
//	public void setSaleRamt24(BigDecimal saleRamt24) {
//		this.saleRamt24 = saleRamt24;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt25() {
//		return saleRamt25;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt25
//	 */
//	public void setSaleRamt25(BigDecimal saleRamt25) {
//		this.saleRamt25 = saleRamt25;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt26() {
//		return saleRamt26;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt26
//	 */
//	public void setSaleRamt26(BigDecimal saleRamt26) {
//		this.saleRamt26 = saleRamt26;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt27() {
//		return saleRamt27;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt27
//	 */
//	public void setSaleRamt27(BigDecimal saleRamt27) {
//		this.saleRamt27 = saleRamt27;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt28() {
//		return saleRamt28;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt28
//	 */
//	public void setSaleRamt28(BigDecimal saleRamt28) {
//		this.saleRamt28 = saleRamt28;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt29() {
//		return saleRamt29;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt29
//	 */
//	public void setSaleRamt29(BigDecimal saleRamt29) {
//		this.saleRamt29 = saleRamt29;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt30() {
//		return saleRamt30;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt30
//	 */
//	public void setSaleRamt30(BigDecimal saleRamt30) {
//		this.saleRamt30 = saleRamt30;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt31() {
//		return saleRamt31;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt31
//	 */
//	public void setSaleRamt31(BigDecimal saleRamt31) {
//		this.saleRamt31 = saleRamt31;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt32() {
//		return saleRamt32;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt32
//	 */
//	public void setSaleRamt32(BigDecimal saleRamt32) {
//		this.saleRamt32 = saleRamt32;
//	}
//	/**
//	 * 取得
//	 * 
//	 * @return 实销金额
//	 */
//	public BigDecimal getSaleRamt33() {
//		return saleRamt33;
//	}
//	/**
//	 * 设置
//	 * 
//	 * @param 实销金额 saleRamt33
//	 */
//	public void setSaleRamt33(BigDecimal saleRamt33) {
//		this.saleRamt33 = saleRamt33;
//	}

}
