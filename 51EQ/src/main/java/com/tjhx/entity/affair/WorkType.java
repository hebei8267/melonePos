package com.tjhx.entity.affair;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.struct.Organization;

/**
 * 上班类型
 * 
 * @author he_bei
 * 
 */
@Entity
@Table(name = "T_WORK_TYPE")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkType extends IdEntity {

	private static final long serialVersionUID = 8717393085158252366L;
	/** 上班类型名称 */
	private String name;
	/** 启用标记 1-启用 0-停用 */
	private String useFlg;
	/** 可编辑标记 */
	private boolean editFlg = false;
	/** 上班时间 */
	private String startDate;
	/** 上班时间 HH:mm */
	private String _startDate;
	/** 下班时间 */
	private String endDate;
	/** 下班时间 HH:mm */
	private String _endDate;
	/** 工作时间 HH:mm - HH:mm */
	private String workDate;
	/** 用户关联机构 */
	private Organization organization;
	// ---------------------------------------------
	/** 用户关联机构编号 */
	private Integer orgUuid;

	private String startDateHr;
	private String startDateMinute;
	private String endDateHr;
	private String endDateMinute;

	/**
	 * 取得上班类型名称
	 * 
	 * @return name 上班类型名称
	 */
	@Column(length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置上班类型名称
	 * 
	 * @param name 上班类型名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得启用标记1-启用0-停用
	 * 
	 * @return useFlg 启用标记1-启用0-停用
	 */
	@Column(length = 1)
	public String getUseFlg() {
		return useFlg;
	}

	/**
	 * 设置启用标记1-启用0-停用
	 * 
	 * @param useFlg 启用标记1-启用0-停用
	 */
	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	/**
	 * 取得可编辑标记
	 * 
	 * @return editFlg 可编辑标记
	 */
	public boolean isEditFlg() {
		return editFlg;
	}

	/**
	 * 设置可编辑标记
	 * 
	 * @param editFlg 可编辑标记
	 */
	public void setEditFlg(boolean editFlg) {
		this.editFlg = editFlg;
	}

	/**
	 * 取得上班时间
	 * 
	 * @return startDate 上班时间
	 */
	@Column(length = 4)
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 设置上班时间
	 * 
	 * @param startDate 上班时间
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 取得下班时间
	 * 
	 * @return endDate 下班时间
	 */
	@Column(length = 4)
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 设置下班时间
	 * 
	 * @param endDate 下班时间
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 取得上班时间HH:mm
	 * 
	 * @return _startDate 上班时间HH:mm
	 */
	@Column(length = 5)
	public String get_startDate() {
		return _startDate;
	}

	/**
	 * 设置上班时间HH:mm
	 * 
	 * @param _startDate 上班时间HH:mm
	 */
	public void set_startDate(String _startDate) {
		this._startDate = _startDate;
	}

	/**
	 * 取得下班时间HH:mm
	 * 
	 * @return _endDate 下班时间HH:mm
	 */
	@Column(length = 5)
	public String get_endDate() {
		return _endDate;
	}

	/**
	 * 设置下班时间HH:mm
	 * 
	 * @param _endDate 下班时间HH:mm
	 */
	public void set_endDate(String _endDate) {
		this._endDate = _endDate;
	}

	/**
	 * 取得工作时间HH:mm-HH:mm
	 * 
	 * @return workDate 工作时间HH:mm-HH:mm
	 */
	@Column(length = 16)
	public String getWorkDate() {
		return workDate;
	}

	/**
	 * 设置工作时间HH:mm-HH:mm
	 * 
	 * @param workDate 工作时间HH:mm-HH:mm
	 */
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	/**
	 * 取得用户关联机构
	 * 
	 * @return organization 用户关联机构
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_UUID")
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * 设置用户关联机构
	 * 
	 * @param organization 用户关联机构
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * 取得用户关联机构编号
	 * 
	 * @return orgUuid 用户关联机构编号
	 */
	@Transient
	public Integer getOrgUuid() {
		return orgUuid;
	}

	/**
	 * 设置用户关联机构编号
	 * 
	 * @param orgUuid 用户关联机构编号
	 */
	public void setOrgUuid(Integer orgUuid) {
		this.orgUuid = orgUuid;
	}

	/**
	 * 取得startDateHr
	 * 
	 * @return startDateHr startDateHr
	 */
	@Transient
	public String getStartDateHr() {
		return startDateHr;
	}

	/**
	 * 设置startDateHr
	 * 
	 * @param startDateHr startDateHr
	 */
	public void setStartDateHr(String startDateHr) {
		this.startDateHr = startDateHr;
	}

	/**
	 * 取得startDateMinute
	 * 
	 * @return startDateMinute startDateMinute
	 */
	@Transient
	public String getStartDateMinute() {
		return startDateMinute;
	}

	/**
	 * 设置startDateMinute
	 * 
	 * @param startDateMinute startDateMinute
	 */
	public void setStartDateMinute(String startDateMinute) {
		this.startDateMinute = startDateMinute;
	}

	/**
	 * 取得endDateHr
	 * 
	 * @return endDateHr endDateHr
	 */
	@Transient
	public String getEndDateHr() {
		return endDateHr;
	}

	/**
	 * 设置endDateHr
	 * 
	 * @param endDateHr endDateHr
	 */
	public void setEndDateHr(String endDateHr) {
		this.endDateHr = endDateHr;
	}

	/**
	 * 取得endDateMinute
	 * 
	 * @return endDateMinute endDateMinute
	 */
	@Transient
	public String getEndDateMinute() {
		return endDateMinute;
	}

	/**
	 * 设置endDateMinute
	 * 
	 * @param endDateMinute endDateMinute
	 */
	public void setEndDateMinute(String endDateMinute) {
		this.endDateMinute = endDateMinute;
	}

}
