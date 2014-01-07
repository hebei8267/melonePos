package com.tjhx.entity.zknet;

import java.util.Date;

/**
 * @author cn.com.free.framework.generator
 */
/** ZK-Checkinout对应实体类 */
public class CheckInOut {
	/** 用户编号 */
	private Integer userid;
	/** 打卡时间 */
	private Date checktime;
	/** 打卡机编号 */
	private String sn;

	// ############################################################################################

	/** 日期-开始时间 */
	private String optDateStart;
	/** 日期-结束时间 */
	private String optDateEnd;

	/**
	 * 取得用户编号
	 * 
	 * @return userid 用户编号
	 */
	public Integer getUserid() {
		return userid;
	}

	/**
	 * 设置用户编号
	 * 
	 * @param userid 用户编号
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	/**
	 * 取得打卡时间
	 * 
	 * @return checktime 打卡时间
	 */
	public Date getChecktime() {
		return checktime;
	}

	/**
	 * 设置打卡时间
	 * 
	 * @param checktime 打卡时间
	 */
	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	/**
	 * 取得打卡机编号
	 * 
	 * @return sn 打卡机编号
	 */
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

	/**
	 * 取得###########
	 * 
	 * @return optDateStart ###########
	 */
	public String getOptDateStart() {
		return optDateStart;
	}

	/**
	 * 设置###########
	 * 
	 * @param optDateStart ###########
	 */
	public void setOptDateStart(String optDateStart) {
		this.optDateStart = optDateStart;
	}

	/**
	 * 取得日期-结束时间
	 * 
	 * @return optDateEnd 日期-结束时间
	 */
	public String getOptDateEnd() {
		return optDateEnd;
	}

	/**
	 * 设置日期-结束时间
	 * 
	 * @param optDateEnd 日期-结束时间
	 */
	public void setOptDateEnd(String optDateEnd) {
		this.optDateEnd = optDateEnd;
	}

}