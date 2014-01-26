package com.tjhx.entity.info;

import java.util.ArrayList;
import java.util.List;

/**
 * 特殊标记-货品供应商 流水信息（显示）
 */
public class SupplierSignRun_Show {

	/** 供应商编号-百威 */
	private String supplierBwId;
	/** 供应商名称 */
	private String supplierName;
	/** 赊购挂账 */
	private List<SupplierSignRun> loanList = new ArrayList<SupplierSignRun>();
	/** 对账通知 */
	private List<SupplierSignRun> noticeList = new ArrayList<SupplierSignRun>();
	/** 对账完成 */
	private List<SupplierSignRun> checkSuccessList = new ArrayList<SupplierSignRun>();
	/** 结算付款 */
	private List<SupplierSignRun> payList = new ArrayList<SupplierSignRun>();
	/** 退货申请 */
	private List<SupplierSignRun> appList = new ArrayList<SupplierSignRun>();
	/** 退货确认 */
	private List<SupplierSignRun> confirmList = new ArrayList<SupplierSignRun>();

	public SupplierSignRun_Show() {

	}

	public SupplierSignRun_Show(String supplierBwId, String supplierName, String optYear) {
		for (int i = 0; i < 12; i++) {
			this.supplierBwId = supplierBwId;
			this.supplierName = supplierName;
			this.loanList.add(new SupplierSignRun(supplierBwId, optYear, String.valueOf(i + 1)));
			this.noticeList.add(new SupplierSignRun(supplierBwId, optYear, String.valueOf(i + 1)));
			this.checkSuccessList.add(new SupplierSignRun(supplierBwId, optYear, String.valueOf(i + 1)));
			this.payList.add(new SupplierSignRun(supplierBwId, optYear, String.valueOf(i + 1)));
			this.appList.add(new SupplierSignRun(supplierBwId, optYear, String.valueOf(i + 1)));
			this.confirmList.add(new SupplierSignRun(supplierBwId, optYear, String.valueOf(i + 1)));
		}
	}

	/**
	 * 取得供应商编号-百威
	 * 
	 * @return supplierBwId 供应商编号-百威
	 */
	public String getSupplierBwId() {
		return supplierBwId;
	}

	/**
	 * 设置供应商编号-百威
	 * 
	 * @param supplierBwId 供应商编号-百威
	 */
	public void setSupplierBwId(String supplierBwId) {
		this.supplierBwId = supplierBwId;
	}

	/**
	 * 取得供应商名称
	 * 
	 * @return supplierName 供应商名称
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 设置供应商名称
	 * 
	 * @param supplierName 供应商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 取得赊购挂账
	 * 
	 * @return loanList 赊购挂账
	 */
	public List<SupplierSignRun> getLoanList() {
		return loanList;
	}

	/**
	 * 设置赊购挂账
	 * 
	 * @param loanList 赊购挂账
	 */
	public void setLoanList(List<SupplierSignRun> loanList) {
		this.loanList = loanList;
	}

	/**
	 * 取得对账通知
	 * 
	 * @return noticeList 对账通知
	 */
	public List<SupplierSignRun> getNoticeList() {
		return noticeList;
	}

	/**
	 * 设置对账通知
	 * 
	 * @param noticeList 对账通知
	 */
	public void setNoticeList(List<SupplierSignRun> noticeList) {
		this.noticeList = noticeList;
	}

	/**
	 * 取得对账完成
	 * 
	 * @return checkSuccessList 对账完成
	 */
	public List<SupplierSignRun> getCheckSuccessList() {
		return checkSuccessList;
	}

	/**
	 * 设置对账完成
	 * 
	 * @param checkSuccessList 对账完成
	 */
	public void setCheckSuccessList(List<SupplierSignRun> checkSuccessList) {
		this.checkSuccessList = checkSuccessList;
	}

	/**
	 * 取得结算付款
	 * 
	 * @return payList 结算付款
	 */
	public List<SupplierSignRun> getPayList() {
		return payList;
	}

	/**
	 * 设置结算付款
	 * 
	 * @param payList 结算付款
	 */
	public void setPayList(List<SupplierSignRun> payList) {
		this.payList = payList;
	}

	/**
	 * 取得退货申请
	 * 
	 * @return appList 退货申请
	 */
	public List<SupplierSignRun> getAppList() {
		return appList;
	}

	/**
	 * 设置退货申请
	 * 
	 * @param appList 退货申请
	 */
	public void setAppList(List<SupplierSignRun> appList) {
		this.appList = appList;
	}

	/**
	 * 取得退货确认
	 * 
	 * @return confirmList 退货确认
	 */
	public List<SupplierSignRun> getConfirmList() {
		return confirmList;
	}

	/**
	 * 设置退货确认
	 * 
	 * @param confirmList 退货确认
	 */
	public void setConfirmList(List<SupplierSignRun> confirmList) {
		this.confirmList = confirmList;
	}

}
