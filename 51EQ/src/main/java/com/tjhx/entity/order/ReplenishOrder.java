package com.tjhx.entity.order;

import java.math.BigDecimal;
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
	/** 补货单状态 01-编辑中 02-收货中 03-收货完成 99-已完成 */
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
	/** 收货日期YM */
	private String receiveDateYM;
	/** 收货次数 */
	private Integer receiveNum;
	/** 收货错填率 */
	private Float errorRate;

	private List<ReplenishOrder> orgReplenishOrderList;

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
	 * 获取补货单状态 01-编辑中 02-收货中 03-收货完成 99-已完成
	 * 
	 * @return orderState
	 */
	@Column(length = 2)
	public String getOrderState() {
		return orderState;
	}

	/**
	 * 设置补货单状态 01-编辑中 02-收货中 03-收货完成 99-已完成
	 * 
	 * @param orderState 补货单状态 01-编辑中 02-收货中 03-收货完成 99-已完成
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

	/**
	 * 取得收货日期YM
	 * 
	 * @return 收货日期YM
	 */
	@Transient
	public String getReceiveDateYM() {
		return receiveDateYM;
	}

	/**
	 * 设置收货日期YM
	 * 
	 * @param receiveDateYM 收货日期YM
	 */
	public void setReceiveDateYM(String receiveDateYM) {
		this.receiveDateYM = receiveDateYM;
	}

	/**
	 * 取得
	 * 
	 * @return receiveNum
	 */
	@Transient
	public Integer getReceiveNum() {
		return receiveNum;
	}

	/**
	 * 设置
	 * 
	 * @param receiveNum receiveNum
	 */
	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}

	/**
	 * 取得
	 * 
	 * @return errorRate
	 */
	@Transient
	public Float getErrorRate() {
		if (null == receiveNum || 0 == receiveNum) {
			return 0f;
		}

		errorRate = new Float(errorNum) / receiveNum;
		BigDecimal b = new BigDecimal(errorRate);
		float errorRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return errorRate;
	}

	/**
	 * 设置
	 * 
	 * @param errorRate errorRate
	 */
	public void setErrorRate(Float errorRate) {
		this.errorRate = errorRate;
	}

	/**
	 * 取得
	 * 
	 * @return orgReplenishOrderList
	 */
	@Transient
	public List<ReplenishOrder> getOrgReplenishOrderList() {
		return orgReplenishOrderList;
	}

	/**
	 * 设置
	 * 
	 * @param orgReplenishOrderList orgReplenishOrderList
	 */
	public void setOrgReplenishOrderList(List<ReplenishOrder> orgReplenishOrderList) {
		this.orgReplenishOrderList = orgReplenishOrderList;
	}

}
