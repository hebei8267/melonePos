package com.tjhx.entity.zknet;

/**
 * @author cn.com.free.framework.generator
 */
/** ZK-UserInfo对应实体类 */
public class UserInfo {
	/** 用户编号 */
	private Integer userid;
	/** 用户编号-自定义 */
	private String badgenumber;
	/** 用户名 */
	private String name;
	/** 所属机构 */
	private Integer defaultdeptid;

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
	 * 取得用户编号-自定义
	 * 
	 * @return badgenumber 用户编号-自定义
	 */
	public String getBadgenumber() {
		return badgenumber;
	}

	/**
	 * 设置用户编号-自定义
	 * 
	 * @param badgenumber 用户编号-自定义
	 */
	public void setBadgenumber(String badgenumber) {
		this.badgenumber = badgenumber;
	}

	/**
	 * 取得用户名
	 * 
	 * @return name 用户名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置用户名
	 * 
	 * @param name 用户名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得所属机构
	 * 
	 * @return defaultdeptid 所属机构
	 */
	public Integer getDefaultdeptid() {
		return defaultdeptid;
	}

	/**
	 * 设置所属机构
	 * 
	 * @param defaultdeptid 所属机构
	 */
	public void setDefaultdeptid(Integer defaultdeptid) {
		this.defaultdeptid = defaultdeptid;
	}

}