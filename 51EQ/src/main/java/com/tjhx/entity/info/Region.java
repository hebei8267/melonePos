package com.tjhx.entity.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 区域
 */
@Entity
@Table(name = "T_REGION")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Region extends IdEntity {

	private static final long serialVersionUID = -3761908507234693948L;
	/** 编号 */
	private String code;
	/** 区域 */
	private String name;

	/**
	 * 取得编号
	 * 
	 * @return code 编号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getCode() {
		return code;
	}

	/**
	 * 设置编号
	 * 
	 * @param code 编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 取得区域
	 * 
	 * @return name 区域
	 */
	@Column(length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置区域
	 * 
	 * @param name 区域
	 */
	public void setName(String name) {
		this.name = name;
	}

}
