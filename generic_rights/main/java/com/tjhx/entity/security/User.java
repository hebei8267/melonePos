package com.tjhx.entity.security;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 用户
 */
@Entity
@Table(name = "T_USER")
public class User extends IdEntity {
	// 编号
	private String code;
	// 姓名
	private String name;
	// 登录帐号
	private String loginName;
	// 登录密码
	private String passWord;
	// 性别
	private char sex;
	// 出生日期
	private String birthDay;
	// 手机号码
	private String phoneNum;
	// 固定电话
	private String telNum;
	// QQ号码
	private String qqNum;
	// 电子邮件
	private String emailAdd;
	// 有效性
	private byte valid;
	// 备注
	private String remark;

}
