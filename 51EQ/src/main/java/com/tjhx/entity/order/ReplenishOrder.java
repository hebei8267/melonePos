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
	/** 补货单状态 01-编辑中 02-已发货 03-收货中 99-已确认 */
	private String orderState;
	/** 点货日期 */
	private String generateDate;
	/** 描述 */
	private String description;
	/** 补货单明细列表 */
	private List<ReplenishOrderDetail> detailList;

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
	 * 设置补货单状态01-编辑中02-已发货03-收货中99-已确认
	 * 
	 * @param orderState 补货单状态01-编辑中02-已发货03-收货中99-已确认
	 */
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	/**
	 * 获取点货日期
	 * 
	 * @return generateDate
	 */
	@Column(length = 8)
	public String getGenerateDate() {
		return generateDate;
	}

	/**
	 * 设置点货日期
	 * 
	 * @param generateDate 点货日期
	 */
	public void setGenerateDate(String generateDate) {
		this.generateDate = generateDate;
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
}
