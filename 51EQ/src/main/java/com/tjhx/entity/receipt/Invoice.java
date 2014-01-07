package com.tjhx.entity.receipt;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;

/**
 * 发票
 */
@Entity
@Table(name = "T_INVOICE")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Invoice extends IdEntity {

	private static final long serialVersionUID = 1253775237657461343L;

	/** 申请机构编号 */
	private String orgId;
	/** 申请人名称 */
	private String appName;
	/** 申请日期-显示 */
	private String appDateShow;
	/** 申请日期-年 */
	private String appDateY;
	/** 申请日期-月 */
	private String appDateM;
	/** 申请日期 */
	private String appDate;
	/** 发票种类 1-机打 2-手写 4-其他 */
	private String invoiceType;
	/** 发票台头 */
	private String title;
	/** 发票内容 */
	private String content;
	/** 发票金额 */
	private BigDecimal amt = new BigDecimal("0");
	/** 送达日期 */
	private String serviceDateShow;
	/** 是否邮寄客户 1-需要 0-不需要 */
	private String needPost = "0";
	/** 客户姓名 */
	private String customerName;
	/** 客服电话 */
	private String customerTel;
	/** 客户地址 */
	private String customerAdd;
	/** 发票号码 */
	private String invoiceNum;
	/** 发票来源 */
	private String invoiceSrc;
	/** 备注 */
	private String descTxt;
	/** 发票状态 1-申请 2-已处理 */
	private String invoiceStatus;
	// ############################################################################################
	/** 机构名称 */
	private String orgName;

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@Column(name = "ORG_ID", length = 32)
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
	 * 取得申请人名称
	 * 
	 * @return appName 申请人名称
	 */
	@Column(nullable = false, length = 32)
	public String getAppName() {
		return appName;
	}

	/**
	 * 设置申请人名称
	 * 
	 * @param appName 申请人名称
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * 取得申请日期-显示
	 * 
	 * @return appDateShow 申请日期-显示
	 */
	@Column(length = 10)
	public String getAppDateShow() {
		return appDateShow;
	}

	/**
	 * 设置申请日期-显示
	 * 
	 * @param appDateShow 申请日期-显示
	 */
	public void setAppDateShow(String appDateShow) {
		this.appDateShow = appDateShow;
	}

	/**
	 * 取得申请日期-年
	 * 
	 * @return appDateY 申请日期-年
	 */
	@Column(name = "APP_DATE_Y", length = 4)
	public String getAppDateY() {
		return appDateY;
	}

	/**
	 * 设置申请日期-年
	 * 
	 * @param appDateY 申请日期-年
	 */
	public void setAppDateY(String appDateY) {
		this.appDateY = appDateY;
	}

	/**
	 * 取得申请日期-月
	 * 
	 * @return appDateM 申请日期-月
	 */
	@Column(name = "APP_DATE_M", length = 2)
	public String getAppDateM() {
		return appDateM;
	}

	/**
	 * 设置申请日期-月
	 * 
	 * @param appDateM 申请日期-月
	 */
	public void setAppDateM(String appDateM) {
		this.appDateM = appDateM;
	}

	/**
	 * 取得申请日期
	 * 
	 * @return appDate 申请日期
	 */
	@Column(length = 8)
	public String getAppDate() {
		return appDate;
	}

	/**
	 * 设置申请日期
	 * 
	 * @param appDate 申请日期
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	/**
	 * 取得发票种类1-机打2-手写3-其他
	 * 
	 * @return invoiceType 发票种类1-机打2-手写3-其他
	 */
	@Column(length = 1)
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * 设置发票种类1-机打2-手写3-其他
	 * 
	 * @param invoiceType 发票种类1-机打2-手写3-其他
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * 取得发票台头
	 * 
	 * @return title 发票台头
	 */
	@Column(length = 64)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置发票台头
	 * 
	 * @param title 发票台头
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 取得发票内容
	 * 
	 * @return content 发票内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置发票内容
	 * 
	 * @param content 发票内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 取得发票金额
	 * 
	 * @return amt 发票金额
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * 设置发票金额
	 * 
	 * @param amt 发票金额
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * 取得送达日期
	 * 
	 * @return serviceDateShow 送达日期
	 */
	@Column(length = 10)
	public String getServiceDateShow() {
		return serviceDateShow;
	}

	/**
	 * 设置送达日期
	 * 
	 * @param serviceDateShow 送达日期
	 */
	public void setServiceDateShow(String serviceDateShow) {
		this.serviceDateShow = serviceDateShow;
	}

	/**
	 * 取得是否邮寄客户1-需要0-不需要
	 * 
	 * @return needPost 是否邮寄客户1-需要0-不需要
	 */
	@Column(length = 1)
	public String getNeedPost() {
		return needPost;
	}

	/**
	 * 设置是否邮寄客户1-需要0-不需要
	 * 
	 * @param needPost 是否邮寄客户1-需要0-不需要
	 */
	public void setNeedPost(String needPost) {
		this.needPost = needPost;
	}

	/**
	 * 取得客户姓名
	 * 
	 * @return customerName 客户姓名
	 */
	@Column(length = 64)
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 设置客户姓名
	 * 
	 * @param customerName 客户姓名
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 取得客服电话
	 * 
	 * @return customerTel 客服电话
	 */
	@Column(length = 32)
	public String getCustomerTel() {
		return customerTel;
	}

	/**
	 * 设置客服电话
	 * 
	 * @param customerTel 客服电话
	 */
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	/**
	 * 取得客户地址
	 * 
	 * @return customerAdd 客户地址
	 */
	@Column(length = 64)
	public String getCustomerAdd() {
		return customerAdd;
	}

	/**
	 * 设置客户地址
	 * 
	 * @param customerAdd 客户地址
	 */
	public void setCustomerAdd(String customerAdd) {
		this.customerAdd = customerAdd;
	}

	/**
	 * 取得发票号码
	 * 
	 * @return invoiceNum 发票号码
	 */
	@Column(length = 32)
	public String getInvoiceNum() {
		return invoiceNum;
	}

	/**
	 * 设置发票号码
	 * 
	 * @param invoiceNum 发票号码
	 */
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	/**
	 * 取得发票来源
	 * 
	 * @return invoiceSrc 发票来源
	 */
	public String getInvoiceSrc() {
		return invoiceSrc;
	}

	/**
	 * 设置发票来源
	 * 
	 * @param invoiceSrc 发票来源
	 */
	public void setInvoiceSrc(String invoiceSrc) {
		this.invoiceSrc = invoiceSrc;
	}

	/**
	 * 取得备注
	 * 
	 * @return descTxt 备注
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置备注
	 * 
	 * @param descTxt 备注
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得发票状态1-申请2-已处理
	 * 
	 * @return invoiceStatus 发票状态1-申请2-已处理
	 */
	@Column(length = 1)
	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	/**
	 * 设置发票状态1-申请2-已处理
	 * 
	 * @param invoiceStatus 发票状态1-申请2-已处理
	 */
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
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
}
