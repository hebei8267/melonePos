package com.tjhx.entity.order;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 补货单
 */
@Entity
@Table(name = "T_REPLENISH_ORDER")
@DynamicInsert
@DynamicUpdate
@XmlRootElement(name = "replenishOrder")
public class ReplenishOrder extends IdEntity {

	private static final long serialVersionUID = 6690525867989535532L;

	/** 补货单生成批次号 */
	private String orderBatchId;
	/** 补货单编号 */
	private String orderNo;
	/** 补货机构代码 */
	private String replenishOrgId;
	/** 补货单状态 01-编辑中 02-收货中 99-已确认 */
	private String orderState;
	/** 发货日期 */
	private String sendDate;
	/** 收货日期 */
	private String receiveDate;
	/** 描述 */
	private String description;
	/** 补货单明细列表 */
	private List<ReplenishOrderDetail> detailList;
	/** 错填次数 */
	private int errorNum;

	/**
	 * 获取补货单生成批次号
	 * 
	 * @return orderBatchId
	 */
	@Column(nullable = false, length = 16)
	public String getOrderBatchId() {
		return orderBatchId;
	}

	/**
	 * 设置补货单生成批次号
	 * 
	 * @param orderBatchId 补货单生成批次号
	 */
	public void setOrderBatchId(String orderBatchId) {
		this.orderBatchId = orderBatchId;
	}

	/**
	 * 获取补货单编号
	 * 
	 * @return 补货单编号
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置补货单编号
	 * 
	 * @param orderNo 补货单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取补货机构代码
	 * 
	 * @return replenishOrgId
	 */
	@Column(length = 32)
	public String getReplenishOrgId() {
		return replenishOrgId;
	}

	/**
	 * 设置补货机构代码
	 * 
	 * @param replenishOrgId 补货机构代码
	 */
	public void setReplenishOrgId(String replenishOrgId) {
		this.replenishOrgId = replenishOrgId;
	}

	/**
	 * 获取补货单状态01-编辑中02-已发货03-收货中99-已确认
	 * 
	 * @return orderState
	 */
	@Column(length = 2)
	public String getOrderState() {
		return orderState;
	}

	/**
	 * 设置补货单状态01-编辑中02-收货中99-已确认
	 * 
	 * @param orderState 补货单状态01-编辑中02-收货中99-已确认
	 */
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	/**
	 * 获取发货日期
	 * 
	 * @return sendDate
	 */
	@Column(length = 8)
	public String getSendDate() {
		return sendDate;
	}

	/**
	 * 设置发货日期
	 * 
	 * @param sendDate 发货日期
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * 获取收货日期
	 * 
	 * @return receiveDate
	 */
	@Column(length = 8)
	public String getReceiveDate() {
		return receiveDate;
	}

	/**
	 * 设置收货日期
	 * 
	 * @param receiveDate 收货日期
	 */
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * 获取详细描述
	 * 
	 * @return 详细描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置详细描述
	 * 
	 * @param description 详细描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取补货单明细列表
	 * 
	 * @return 补货单明细列表
	 */
	@Transient
	public List<ReplenishOrderDetail> getDetailList() {
		return detailList;
	}

	/**
	 * 设置补货单明细列表
	 * 
	 * @param detailList 补货单明细列表
	 */
	public void setDetailList(List<ReplenishOrderDetail> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 获取错填次数
	 * 
	 * @return errorNum
	 */
	public int getErrorNum() {
		return errorNum;
	}

	/**
	 * 设置错填次数
	 * 
	 * @param errorNum 错填次数
	 */
	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}
}