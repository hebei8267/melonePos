package com.tjhx.entity.security;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 数据对象资源
 */
@Entity
@Table(name = "T_DATA_OBJ")
public class DataObj extends IdEntity {

	private static final long serialVersionUID = 907598015779865964L;
	/** 父数据对象节点 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_DATA_OBJ_H_ID")
	private DataObj parentDataObj;

	/** 父数据对象编号 */
	@Column(name = "PARENT_DATA_OBJ_ID", length = 32)
	private String parentId;

	/** 数据对象编号 */
	@NaturalId
	@Column(name = "DATA_OBJ_ID", nullable = false, length = 32)
	private String id;

	/** 数据对象名称 */
	@Column(nullable = false, length = 32)
	private String name;

	/** 数据对象排序序号 */
	@Column(name = "_INDEX")
	private int index;

	/** 备注 */
	private String remark;

	/** 子数据对象节点集合 */
	@OneToMany(mappedBy = "parentDataObj", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OrderBy("index")
	private List<DataObj> subDataObjs;

	/**
	 * 取得父数据对象节点
	 * 
	 * @return parentDataObj 父数据对象节点
	 */
	public DataObj getParentDataObj() {
		return parentDataObj;
	}

	/**
	 * 设置父数据对象节点
	 * 
	 * @param parentDataObj 父数据对象节点
	 */
	public void setParentDataObj(DataObj parentDataObj) {
		this.parentDataObj = parentDataObj;
	}

	/**
	 * 取得父数据对象编号
	 * 
	 * @return parentId 父数据对象编号
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父数据对象编号
	 * 
	 * @param parentId 父数据对象编号
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 取得数据对象编号
	 * 
	 * @return id 数据对象编号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置数据对象编号
	 * 
	 * @param id 数据对象编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得数据对象名称
	 * 
	 * @return name 数据对象名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置数据对象名称
	 * 
	 * @param name 数据对象名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得数据对象排序序号
	 * 
	 * @return index 数据对象排序序号
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 设置数据对象排序序号
	 * 
	 * @param index 数据对象排序序号
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 取得备注
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 取得子数据对象节点集合
	 * 
	 * @return subDataObjs 子数据对象节点集合
	 */
	public List<DataObj> getSubDataObjs() {
		return subDataObjs;
	}

	/**
	 * 设置子数据对象节点集合
	 * 
	 * @param subDataObjs 子数据对象节点集合
	 */
	public void setSubDataObjs(List<DataObj> subDataObjs) {
		this.subDataObjs = subDataObjs;
	}

}
