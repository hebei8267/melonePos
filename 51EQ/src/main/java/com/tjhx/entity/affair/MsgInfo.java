package com.tjhx.entity.affair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;

/**
 * 交接班/总部命令下发 记录
 */
@Entity
@Table(name = "T_MSG_INFO")
public class MsgInfo extends IdEntity {

	private static final long serialVersionUID = -5755794523836653485L;
	/** 消息批次号 */
	private String msgBatchId;
	/** 日期 */
	private String optDate;
	/** 日期-显示 */
	private String optDateShow;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 星期 */
	private String week;
	/** 发送人 */
	private String sendUserLoginName;
	/** 接收人 */
	private String acceptUserLoginName;
	/** 发送人名字集合 */
	private String sendNameSet;
	/** 接收人名字集合 */
	private String acceptNameSet;
	/** 主题 */
	private String msgSubject;
	/** 消息内容 */
	private String msgContent;
	/** 消息类型 1-发送 2-接受 */
	private String msgType;
	/** 阅读标记 0-未读 1-已读 */
	private String readFlg = "0";
	// ------------------------------------------------------------------------------
	/** 发送人-名字 */
	private String sendUserName;
	/** 接收人-名字 */
	private String acceptUserName;
	/** 全部机构编号集合 */
	private String[] allOrgIds;
	/** 接收机构编号 */
	private String[] acceptOrgIds;
	/** 全部人员编号集合 */
	private String[] allUserIds;
	/** 接收人员编号 */
	private String[] acceptUserIds;
	/** 接收类型 1-机构 2-人员 */
	private String acceptType;
	private String optDateStart;
	private String optDateEnd;

	/**
	 * 取得消息内容
	 * 
	 * @return msgBatchId 消息内容
	 */
	@Column(length = 25)
	public String getMsgBatchId() {
		return msgBatchId;
	}

	/**
	 * 设置消息内容
	 * 
	 * @param msgBatchId 消息内容
	 */
	public void setMsgBatchId(String msgBatchId) {
		this.msgBatchId = msgBatchId;
	}

	/**
	 * 取得日期
	 * 
	 * @return optDate 日期
	 */
	@Column(length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDate 日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得日期-显示
	 * 
	 * @return optDateShow 日期-显示
	 */
	@Column(length = 10)
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置日期-显示
	 * 
	 * @param optDateShow 日期-显示
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
	}

	/**
	 * 取得日期-年
	 * 
	 * @return optDateY 日期-年
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置日期-年
	 * 
	 * @param optDateY 日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得日期-月
	 * 
	 * @return optDateM 日期-月
	 */
	@Column(name = "OPT_DATE_M", length = 2)
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置日期-月
	 * 
	 * @param optDateM 日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得星期
	 * 
	 * @return week 星期
	 */
	@Column(length = 1)
	public String getWeek() {
		return week;
	}

	/**
	 * 设置星期
	 * 
	 * @param week 星期
	 */
	@Column(length = 1)
	public void setWeek(String week) {
		this.week = week;
	}

	/**
	 * 取得发送人
	 * 
	 * @return sendUserLoginName 发送人
	 */
	@Column(length = 32)
	public String getSendUserLoginName() {
		return sendUserLoginName;
	}

	/**
	 * 设置发送人
	 * 
	 * @param sendUserLoginName 发送人
	 */
	public void setSendUserLoginName(String sendUserLoginName) {
		this.sendUserLoginName = sendUserLoginName;
	}

	/**
	 * 取得接收人
	 * 
	 * @return acceptUserLoginName 接收人
	 */
	@Column(length = 32)
	public String getAcceptUserLoginName() {
		return acceptUserLoginName;
	}

	/**
	 * 设置接收人
	 * 
	 * @param acceptUserLoginName 接收人
	 */
	public void setAcceptUserLoginName(String acceptUserLoginName) {
		this.acceptUserLoginName = acceptUserLoginName;
	}

	/**
	 * 取得发送人名字集合
	 * 
	 * @return sendNameSet 发送人名字集合
	 */
	@Column(length = 512)
	public String getSendNameSet() {
		return sendNameSet;
	}

	/**
	 * 设置发送人名字集合
	 * 
	 * @param sendNameSet 发送人名字集合
	 */
	public void setSendNameSet(String sendNameSet) {
		this.sendNameSet = sendNameSet;
	}

	/**
	 * 取得接收人名字集合
	 * 
	 * @return acceptNameSet 接收人名字集合
	 */
	@Column(length = 512)
	public String getAcceptNameSet() {
		return acceptNameSet;
	}

	/**
	 * 设置接收人名字集合
	 * 
	 * @param acceptNameSet 接收人名字集合
	 */
	public void setAcceptNameSet(String acceptNameSet) {
		this.acceptNameSet = acceptNameSet;
	}

	/**
	 * 取得主题
	 * 
	 * @return msgSubject 主题
	 */
	@Column(length = 128)
	public String getMsgSubject() {
		return msgSubject;
	}

	/**
	 * 设置主题
	 * 
	 * @param msgSubject 主题
	 */
	public void setMsgSubject(String msgSubject) {
		this.msgSubject = msgSubject;
	}

	/**
	 * 取得消息内容
	 * 
	 * @return msgContent 消息内容
	 */
	@Column(length = 1024)
	public String getMsgContent() {
		return msgContent;
	}

	/**
	 * 设置消息内容
	 * 
	 * @param msgContent 消息内容
	 */
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	/**
	 * 取得消息类型1-发送2-接受
	 * 
	 * @return msgType 消息类型1-发送2-接受
	 */
	@Column(length = 1)
	public String getMsgType() {
		return msgType;
	}

	/**
	 * 设置消息类型1-发送2-接受
	 * 
	 * @param msgType 消息类型1-发送2-接受
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * 取得阅读标记0-未读1-已读
	 * 
	 * @return readFlg 阅读标记0-未读1-已读
	 */
	@Column(length = 1)
	public String getReadFlg() {
		return readFlg;
	}

	/**
	 * 设置阅读标记0-未读1-已读
	 * 
	 * @param readFlg 阅读标记0-未读1-已读
	 */
	public void setReadFlg(String readFlg) {
		this.readFlg = readFlg;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 取得发送人-名字
	 * 
	 * @return sendUserName 发送人-名字
	 */
	@Transient
	public String getSendUserName() {
		return sendUserName;
	}

	/**
	 * 设置发送人-名字
	 * 
	 * @param sendUserName 发送人-名字
	 */
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	/**
	 * 取得接收人-名字
	 * 
	 * @return acceptUserName 接收人-名字
	 */
	@Transient
	public String getAcceptUserName() {
		return acceptUserName;
	}

	/**
	 * 设置接收人-名字
	 * 
	 * @param acceptUserName 接收人-名字
	 */
	public void setAcceptUserName(String acceptUserName) {
		this.acceptUserName = acceptUserName;
	}

	/**
	 * 取得全部机构编号集合
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
	 * 取得接收机构编号
	 * 
	 * @return acceptOrgIds 接收机构编号
	 */
	@Transient
	public String[] getAcceptOrgIds() {
		return acceptOrgIds;
	}

	/**
	 * 设置接收机构编号
	 * 
	 * @param acceptOrgIds 接收机构编号
	 */
	public void setAcceptOrgIds(String[] acceptOrgIds) {
		this.acceptOrgIds = acceptOrgIds;
	}

	/**
	 * 取得全部人员编号集合
	 * 
	 * @return allUserIds 全部人员编号集合
	 */
	@Transient
	public String[] getAllUserIds() {
		return allUserIds;
	}

	/**
	 * 设置全部人员编号集合
	 * 
	 * @param allUserIds 全部人员编号集合
	 */
	public void setAllUserIds(String[] allUserIds) {
		this.allUserIds = allUserIds;
	}

	/**
	 * 取得接收人员编号
	 * 
	 * @return acceptUserIds 接收人员编号
	 */
	@Transient
	public String[] getAcceptUserIds() {
		return acceptUserIds;
	}

	/**
	 * 设置接收人员编号
	 * 
	 * @param acceptUserIds 接收人员编号
	 */
	public void setAcceptUserIds(String[] acceptUserIds) {
		this.acceptUserIds = acceptUserIds;
	}

	/**
	 * 取得接收类型1-机构2-人员
	 * 
	 * @return acceptType 接收类型1-机构2-人员
	 */
	@Transient
	public String getAcceptType() {
		return acceptType;
	}

	/**
	 * 设置接收类型1-机构2-人员
	 * 
	 * @param acceptType 接收类型1-机构2-人员
	 */
	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}

	/**
	 * 取得optDateStart
	 * 
	 * @return optDateStart
	 */
	@Transient
	public String getOptDateStart() {
		return optDateStart;
	}

	/**
	 * 设置optDateStart
	 * 
	 * @param optDateStart
	 */
	public void setOptDateStart(String optDateStart) {
		this.optDateStart = optDateStart;
	}

	/**
	 * 取得optDateEnd
	 * 
	 * @return optDateEnd
	 */
	@Transient
	public String getOptDateEnd() {
		return optDateEnd;
	}

	/**
	 * 设置optDateEnd
	 * 
	 * @param optDateEnd
	 */
	public void setOptDateEnd(String optDateEnd) {
		this.optDateEnd = optDateEnd;
	}
}
