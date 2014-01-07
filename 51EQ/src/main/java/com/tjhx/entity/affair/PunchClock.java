package com.tjhx.entity.affair;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.IdEntity;

@Entity
@Table(name = "T_PUNCH_CLOCK")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PunchClock extends IdEntity {

	private static final long serialVersionUID = 8758497876481381832L;
	/** 用户编号 */
	private Integer zkid;
	/** 打卡时间-开始 */
	private Date startClockTime;
	/** 打卡时间-结束 */
	private Date endClockTime;
	/** 打卡时间-年 */
	private String clockTimeY;
	/** 打卡时间-月 */
	private String clockTimeM;
	/** 打卡时间-日 */
	private String clockTimeD;
	/** 打卡机编号 */
	private String sn;
	// #######################################################
	/** 机构编号 */
	private String orgId;
	/** 机构名称 */
	private String orgName;
	/** 员工编号 */
	private int empUuid;

	public PunchClock() {

	}

	public PunchClock(String clockTimeYMD) {
		clockTimeY = DateUtils.transDateFormat(clockTimeYMD, "yyyy-MM-dd", "yyyy");
		clockTimeM = DateUtils.transDateFormat(clockTimeYMD, "yyyy-MM-dd", "MM");
		clockTimeD = DateUtils.transDateFormat(clockTimeYMD, "yyyy-MM-dd", "dd");
	}

	/**
	 * 取得用户编号
	 * 
	 * @return zkid 用户编号
	 */
	public Integer getZkid() {
		return zkid;
	}

	/**
	 * 设置用户编号
	 * 
	 * @param zkid 用户编号
	 */
	public void setZkid(Integer zkid) {
		this.zkid = zkid;
	}

	/**
	 * 取得打卡时间-开始
	 * 
	 * @return startClockTime 打卡时间-开始
	 */
	public Date getStartClockTime() {
		return startClockTime;
	}

	/**
	 * 设置打卡时间-开始
	 * 
	 * @param startClockTime 打卡时间-开始
	 */
	public void setStartClockTime(Date startClockTime) {
		this.startClockTime = startClockTime;
	}

	/**
	 * 取得打卡时间-结束
	 * 
	 * @return endClockTime 打卡时间-结束
	 */
	public Date getEndClockTime() {
		return endClockTime;
	}

	/**
	 * 设置打卡时间-结束
	 * 
	 * @param endClockTime 打卡时间-结束
	 */
	public void setEndClockTime(Date endClockTime) {
		this.endClockTime = endClockTime;
	}

	/**
	 * 取得打卡时间-年
	 * 
	 * @return clockTimeY 打卡时间-年
	 */
	@Column(name = "CLOCK_TIME_Y", length = 4)
	public String getClockTimeY() {
		return clockTimeY;
	}

	/**
	 * 设置打卡时间-年
	 * 
	 * @param clockTimeY 打卡时间-年
	 */
	public void setClockTimeY(String clockTimeY) {
		this.clockTimeY = clockTimeY;
	}

	/**
	 * 取得打卡时间-月
	 * 
	 * @return clockTimeM 打卡时间-月
	 */
	@Column(name = "CLOCK_TIME_M", length = 2)
	public String getClockTimeM() {
		return clockTimeM;
	}

	/**
	 * 设置打卡时间-月
	 * 
	 * @param clockTimeM 打卡时间-月
	 */
	public void setClockTimeM(String clockTimeM) {
		this.clockTimeM = clockTimeM;
	}

	/**
	 * 取得打卡时间-日
	 * 
	 * @return clockTimeD 打卡时间-日
	 */
	@Column(name = "CLOCK_TIME_D", length = 2)
	public String getClockTimeD() {
		return clockTimeD;
	}

	/**
	 * 设置打卡时间-日
	 * 
	 * @param clockTimeD 打卡时间-日
	 */
	public void setClockTimeD(String clockTimeD) {
		this.clockTimeD = clockTimeD;
	}

	/**
	 * 取得打卡机编号
	 * 
	 * @return sn 打卡机编号
	 */
	@Column(length = 16)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置打卡机编号
	 * 
	 * @param sn 打卡机编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	// #######################################################
	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@Transient
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

	/**
	 * 取得员工编号
	 * 
	 * @return empUuid 员工编号
	 */
	@Transient
	public int getEmpUuid() {
		return empUuid;
	}

	/**
	 * 设置员工编号
	 * 
	 * @param empUuid 员工编号
	 */
	public void setEmpUuid(int empUuid) {
		this.empUuid = empUuid;
	}

}
