/**
 * 
 */
package com.tjhx.entity.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 代金卷
 */
@Entity
@Table(name = "T_COUPON")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Coupon extends IdEntity {

	private static final long serialVersionUID = 7780844409833405481L;
	/** 代金卷编号 */
	private String couponNo;
	/** 名称 */
	private String name;
	/** 汇率 */
	private BigDecimal rate = new BigDecimal(0);
	/** 适用机构编号 */
	private String orgId;
	/** 日销售合计是否归集 */
	private boolean subTotalFlg;
	/** 删除标记 */
	private boolean delFlg;
	/** 备注 */
	private String descTxt;
	// =========================================================
	/** 全部机构编号集合 */
	private String[] allOrgIds;
	/** 接收机构编号 */
	private String[] appOrgIds;

	/**
	 * 获取代金卷编号
	 * 
	 * @return couponNo 代金卷编号
	 */
	@NaturalId
	@Column(nullable = false, length = 8)
	public String getCouponNo() {
		return couponNo;
	}

	/**
	 * 设置代金卷编号
	 * 
	 * @param couponNo 代金卷编号
	 */
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	/**
	 * 获取名称
	 * 
	 * @return name 名称
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取汇率
	 * 
	 * @return rate 汇率
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * 设置汇率
	 * 
	 * @param rate 汇率
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * 获取适用机构编号
	 * 
	 * @return orgId 适用机构编号
	 */
	@NaturalId
	@Column(length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置适用机构编号
	 * 
	 * @param orgId 适用机构编号
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取日销售合计是否归集
	 * 
	 * @return subTotalFlg 日销售合计是否归集
	 */
	public boolean getSubTotalFlg() {
		return subTotalFlg;
	}

	/**
	 * 设置日销售合计是否归集
	 * 
	 * @param subTotalFlg 日销售合计是否归集
	 */
	public void setSubTotalFlg(boolean subTotalFlg) {
		this.subTotalFlg = subTotalFlg;
	}

	/**
	 * 获取删除标记
	 * 
	 * @return delFlg 删除标记
	 */
	public boolean getDelFlg() {
		return delFlg;
	}

	/**
	 * 设置删除标记
	 * 
	 * @param delFlg 删除标记
	 */
	public void setDelFlg(boolean delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * 获取备注
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
	 * 获取全部机构编号集合
	 * 
	 * @return allOrgIds 全部机构编号集合
	 */
	@Transient
	public String[] getAllOrgIds() {
		return allOrgIds;
	}

	/**
	 * 设置全部机构编号集合
	 * 
	 * @param allOrgIds 全部机构编号集合
	 */
	public void setAllOrgIds(String[] allOrgIds) {
		this.allOrgIds = allOrgIds;
	}

	/**
	 * 获取接收机构编号
	 * 
	 * @return appOrgIds 接收机构编号
	 */
	@Transient
	public String[] getAppOrgIds() {
		return appOrgIds;
	}

	/**
	 * 设置接收机构编号
	 * 
	 * @param appOrgIds 接收机构编号
	 */
	public void setAppOrgIds(String[] appOrgIds) {
		this.appOrgIds = appOrgIds;
	}

}
