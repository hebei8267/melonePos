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
 * 门店巡查报告(运营)-检查项
 */
@Entity
@Table(name = "T_RUN_INSPECT_ITEM")
public class RunInspectItem extends IdEntity {

	private static final long serialVersionUID = 4950451115450336067L;
	/** 类型编号 */
	private String typeNo;
	/** 编号 */
	private String id;
	/** 分数 */
	private int score;
	/** 描述 */
	private String descTxt;

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
	 * 获取分数
	 * 
	 * @return score 分数
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 设置分数
	 * 
	 * @param score 分数
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 获取描述
	 * 
	 * @return descTxt 描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置描述
	 * 
	 * @param descTxt 描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}
}
