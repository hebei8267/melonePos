/**
 * 
 */
package com.tjhx.entity.affair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 门店巡查报告(运营)-明细信息
 */
@Entity
@Table(name = "T_RUN_INSPECT_DETAIL")
public class RunInspectDetail extends IdEntity {

	private static final long serialVersionUID = 4645090980003554833L;
	/** 门店巡查报告(运营)编号 */
	private String trsId;
	/** 类型编号 */
	private String typeNo;
	/** 编号 */
	private String id;
	/** 选择标记 false-未选 true-已选 */
	private boolean selectFlg;

	/**
	 * 获取门店巡查报告(运营)编号
	 * 
	 * @return trsId 门店巡查报告(运营)编号
	 */
	@NaturalId
	@Column(length = 16)
	public String getTrsId() {
		return trsId;
	}

	/**
	 * 设置门店巡查报告(运营)编号
	 * 
	 * @param trsId 门店巡查报告(运营)编号
	 */
	public void setTrsId(String trsId) {
		this.trsId = trsId;
	}

	/**
	 * 获取类型编号
	 * 
	 * @return typeNo 类型编号
	 */
	@NaturalId
	@Column(length = 2)
	public String getTypeNo() {
		return typeNo;
	}

	/**
	 * 设置类型编号
	 * 
	 * @param typeNo 类型编号
	 */
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	/**
	 * 获取编号
	 * 
	 * @return id 编号
	 */
	@NaturalId
	@Column(length = 8)
	public String getId() {
		return id;
	}

	/**
	 * 设置编号
	 * 
	 * @param id 编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取选择标记false-未选true-已选
	 * 
	 * @return selectFlg 选择标记false-未选true-已选
	 */
	public boolean getSelectFlg() {
		return selectFlg;
	}

	/**
	 * 设置选择标记false-未选true-已选
	 * 
	 * @param selectFlg 选择标记false-未选true-已选
	 */
	public void setSelectFlg(boolean selectFlg) {
		this.selectFlg = selectFlg;
	}

}
