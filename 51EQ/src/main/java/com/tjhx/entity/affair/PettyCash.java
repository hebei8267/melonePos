package com.tjhx.entity.affair;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;

/**
 * 门店备用金
 */
@Entity
@Table(name = "T_PETTY_CASH")
public class PettyCash extends IdEntity {

	private static final long serialVersionUID = -7793146587669072960L;
	/** 巡查报告流水号 */
	private String inspectTrsId;
	/** 业务编号 */
	private String optUid;
	/** 业务日期 */
	private String optDate;
	/** 业务日期-显示 */
	private String optDateShow;
	/** 业务日期-年 */
	private String optDateY;
	/** 业务日期-月 */
	private String optDateM;
	/** 业务日期-对应的星期 */
	private String week;
	/** 操作金额-支出为负数，拨入为正数 */
	private BigDecimal optAmt = new BigDecimal("0");
	/** 操作金额-全部为正数 */
	private BigDecimal optAmtShow = new BigDecimal("0");
	/** 备用金余额（可能为负） */
	private BigDecimal balanceAmt = new BigDecimal("0");
	/** 操作类型 0-支出 1-拨入 */
	private int optType;
	/** 拨入来源 1-正常拨入 2-非常拨入 4-会计调帐 */
	private String incomeSource;
	/** 支出类型 01-房租(门店) 02-电费 03-水费 04-税费 05-工资 06-网络/通讯费 07-房租(宿舍) 99-其他 */
	private String expType;
	/** 支出事项 */
	private String expReason;
	/** 备注 */
	private String descTxt;
	/** 机构编号 */
	private String orgId;
	/** 结转标记 */
	private boolean carryOverFlg;
	/** 审查标记-账证对应 1-正确 0-错误 */
	private Integer examineFlg1;
	/** 审查标记-大小写完整规范 1-正确 0-错误 */
	private Integer examineFlg2;
	/** 审查标记-摘要清晰 1-正确 0-错误 */
	private Integer examineFlg3;
	/** 审查标记-附件或监督完整 1-正确 0-错误 */
	private Integer examineFlg4;
	/** 审查标记-记录序时 1-正确 0-错误 */
	private Integer examineFlg5;
	/** 审查标记-UID正确 1-正确 0-错误 */
	private Integer examineFlg6;
	/** 审查标记-装订正确 1-正确 0-错误 */
	private Integer examineFlg7;
	// ############################################################################################
	/** 用户关联机构名称 */
	private String orgName;
	/** 门店备用金可编辑日期 */
	private String editDate;
	/** 可编辑标记 */
	private boolean editFlg;
	/** 业务日期 */
	private String createDateStr;
	/** 业务日期 */
	private String optDateStart;
	/** 业务日期 */
	private String optDateEnd;
	/** 机构-销售合计 */
	private BigDecimal totalSaleRamt = new BigDecimal(0);
	/** 机构-支出占销售合计百分比 */
	private String rate;

	/**
	 * 取得巡查报告流水号
	 * 
	 * @return inspectTrsId 巡查报告流水号
	 */
	@Column(length = 16)
	public String getInspectTrsId() {
		return inspectTrsId;
	}

	/**
	 * 设置巡查报告流水号
	 * 
	 * @param inspectTrsId 巡查报告流水号
	 */
	public void setInspectTrsId(String inspectTrsId) {
		this.inspectTrsId = inspectTrsId;
	}

	/**
	 * 取得业务编号
	 * 
	 * @return optUid 业务编号
	 */
	@Column(length = 16)
	public String getOptUid() {
		return optUid;
	}

	/**
	 * 设置业务编号
	 * 
	 * @param optUid 业务编号
	 */
	public void setOptUid(String optUid) {
		this.optUid = optUid;
	}

	/**
	 * 取得业务日期
	 * 
	 * @return optDate 业务日期
	 */
	@Column(length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置业务日期
	 * 
	 * @param optDate 业务日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得业务日期-显示
	 * 
	 * @return optDateShow 业务日期-显示
	 */
	@Column(length = 10)
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置业务日期-显示
	 * 
	 * @param optDateShow 业务日期-显示
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
	}

	/**
	 * 取得业务日期-年
	 * 
	 * @return optDateY 业务日期-年
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置业务日期-年
	 * 
	 * @param optDateY 业务日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得业务日期-月
	 * 
	 * @return optDateM 业务日期-月
	 */
	@Column(name = "OPT_DATE_M", length = 2)
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置业务日期-月
	 * 
	 * @param optDateM 业务日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得业务日期-对应的星期
	 * 
	 * @return week 业务日期-对应的星期
	 */
	@Column(length = 1)
	public String getWeek() {
		return week;
	}

	/**
	 * 设置业务日期-对应的星期
	 * 
	 * @param week 业务日期-对应的星期
	 */
	public void setWeek(String week) {
		this.week = week;
	}

	/**
	 * 取得操作金额-支出为负数，拨入为正数
	 * 
	 * @return optAmt 操作金额-支出为负数，拨入为正数
	 */
	public BigDecimal getOptAmt() {
		return optAmt;
	}

	/**
	 * 设置操作金额-支出为负数，拨入为正数
	 * 
	 * @param optAmt 操作金额-支出为负数，拨入为正数
	 */
	public void setOptAmt(BigDecimal optAmt) {
		this.optAmt = optAmt;
	}

	/**
	 * 取得操作金额-全部为正数
	 * 
	 * @return optAmtShow 操作金额-全部为正数
	 */
	public BigDecimal getOptAmtShow() {
		return optAmtShow;
	}

	/**
	 * 设置操作金额-全部为正数
	 * 
	 * @param optAmtShow 操作金额-全部为正数
	 */
	public void setOptAmtShow(BigDecimal optAmtShow) {
		this.optAmtShow = optAmtShow;
	}

	/**
	 * 取得备用金余额（可能为负）
	 * 
	 * @return balanceAmt 备用金余额（可能为负）
	 */
	public BigDecimal getBalanceAmt() {
		return balanceAmt;
	}

	/**
	 * 设置备用金余额（可能为负）
	 * 
	 * @param balanceAmt 备用金余额（可能为负）
	 */
	public void setBalanceAmt(BigDecimal balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	/**
	 * 取得操作类型0-支出1-拨入
	 * 
	 * @return optType 操作类型0-支出1-拨入
	 */
	public int getOptType() {
		return optType;
	}

	/**
	 * 设置操作类型0-支出1-拨入
	 * 
	 * @param optType 操作类型0-支出1-拨入
	 */
	public void setOptType(int optType) {
		this.optType = optType;
	}

	/**
	 * 取得拨入来源1-正常拨入2-非常拨入4-会计调帐
	 * 
	 * @return incomeSource 拨入来源1-正常拨入2-非常拨入4-会计调帐
	 */
	@Column(length = 1)
	public String getIncomeSource() {
		return incomeSource;
	}

	/**
	 * 设置拨入来源1-正常拨入2-非常拨入4-会计调帐
	 * 
	 * @param incomeSource 拨入来源1-正常拨入2-非常拨入4-会计调帐
	 */
	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}

	/**
	 * 取得支出类型 01-房租(门店) 02-电费 03-水费 04-税费 05-工资 06-网络/通讯费 07-房租(宿舍) 99-其他
	 * 
	 * @return expType 支出类型
	 */
	@Column(length = 2)
	public String getExpType() {
		return expType;
	}

	/**
	 * 设置支出类型 01-房租(门店) 02-电费 03-水费 04-税费 05-工资 06-网络/通讯费 07-房租(宿舍) 99-其他
	 * 
	 * @param expReason 支出类型
	 */
	public void setExpType(String expType) {
		this.expType = expType;
	}

	/**
	 * 取得支出事项
	 * 
	 * @return expReason 支出事项
	 */
	public String getExpReason() {
		return expReason;
	}

	/**
	 * 设置支出事项
	 * 
	 * @param expReason 支出事项
	 */
	public void setExpReason(String expReason) {
		this.expReason = expReason;
	}

	/**
	 * 取得备注
	 * 
	 * @return descTxt 备注
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置备注
	 * 
	 * @param descTxt 备注
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@Column(length = 32)
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
	 * 取得结转标记
	 * 
	 * @return carryOverFlg 结转标记
	 */
	public boolean getCarryOverFlg() {
		return carryOverFlg;
	}

	/**
	 * 设置结转标记
	 * 
	 * @param carryOverFlg 结转标记
	 */
	public void setCarryOverFlg(boolean carryOverFlg) {
		this.carryOverFlg = carryOverFlg;
	}

	/**
	 * 取得审查标记-账证对应1-正确 0-错误
	 * 
	 * @return examineFlg1 审查标记-账证对应1-正确 0-错误
	 */
	public Integer getExamineFlg1() {
		return examineFlg1;
	}

	/**
	 * 设置审查标记-账证对应1-正确 0-错误
	 * 
	 * @param examineFlg1 审查标记-账证对应1-正确 0-错误
	 */
	public void setExamineFlg1(Integer examineFlg1) {
		this.examineFlg1 = examineFlg1;
	}

	/**
	 * 取得审查标记-大小写完整规范1-正确 0-错误
	 * 
	 * @return examineFlg2 审查标记-大小写完整规范1-正确 0-错误
	 */
	public Integer getExamineFlg2() {
		return examineFlg2;
	}

	/**
	 * 设置审查标记-大小写完整规范1-正确 0-错误
	 * 
	 * @param examineFlg2 审查标记-大小写完整规范1-正确 0-错误
	 */
	public void setExamineFlg2(Integer examineFlg2) {
		this.examineFlg2 = examineFlg2;
	}

	/**
	 * 取得审查标记-摘要清晰1-正确 0-错误
	 * 
	 * @return examineFlg3 审查标记-摘要清晰1-正确 0-错误
	 */
	public Integer getExamineFlg3() {
		return examineFlg3;
	}

	/**
	 * 设置审查标记-摘要清晰1-正确 0-错误
	 * 
	 * @param examineFlg3 审查标记-摘要清晰1-正确 0-错误
	 */
	public void setExamineFlg3(Integer examineFlg3) {
		this.examineFlg3 = examineFlg3;
	}

	/**
	 * 取得审查标记-附件或监督完整1-正确 0-错误
	 * 
	 * @return examineFlg4 审查标记-附件或监督完整1-正确 0-错误
	 */
	public Integer getExamineFlg4() {
		return examineFlg4;
	}

	/**
	 * 设置审查标记-附件或监督完整1-正确 0-错误
	 * 
	 * @param examineFlg4 审查标记-附件或监督完整1-正确 0-错误
	 */
	public void setExamineFlg4(Integer examineFlg4) {
		this.examineFlg4 = examineFlg4;
	}

	/**
	 * 取得审查标记-记录序时1-正确 0-错误
	 * 
	 * @return examineFlg5 审查标记-记录序时1-正确 0-错误
	 */
	public Integer getExamineFlg5() {
		return examineFlg5;
	}

	/**
	 * 设置审查标记-记录序时1-正确 0-错误
	 * 
	 * @param examineFlg5 审查标记-记录序时1-正确 0-错误
	 */
	public void setExamineFlg5(Integer examineFlg5) {
		this.examineFlg5 = examineFlg5;
	}

	/**
	 * 取得审查标记-UID正确1-正确 0-错误
	 * 
	 * @return examineFlg6 审查标记-UID正确1-正确 0-错误
	 */
	public Integer getExamineFlg6() {
		return examineFlg6;
	}

	/**
	 * 设置审查标记-UID正确1-正确 0-错误
	 * 
	 * @param examineFlg6 审查标记-UID正确1-正确 0-错误
	 */
	public void setExamineFlg6(Integer examineFlg6) {
		this.examineFlg6 = examineFlg6;
	}

	/**
	 * 取得审查标记-装订正确1-正确 0-错误
	 * 
	 * @return examineFlg7 审查标记-装订正确1-正确 0-错误
	 */
	public Integer getExamineFlg7() {
		return examineFlg7;
	}

	/**
	 * 设置审查标记-装订正确1-正确 0-错误
	 * 
	 * @param examineFlg7 审查标记-装订正确1-正确 0-错误
	 */
	public void setExamineFlg7(Integer examineFlg7) {
		this.examineFlg7 = examineFlg7;
	}

	// ############################################################################################
	/**
	 * 取得用户关联机构名称
	 * 
	 * @return orgName 用户关联机构名称
	 */
	@Transient
	public String getOrgName() {
		if (null == orgName && null != orgId) {
			orgName = orgId.substring(3, 6);
		}
		return orgName;
	}

	/**
	 * 设置用户关联机构名称
	 * 
	 * @param orgName 用户关联机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 取得门店备用金可编辑日期
	 * 
	 * @return editDate 门店备用金可编辑日期
	 */
	@Transient
	public String getEditDate() {
		return editDate;
	}

	/**
	 * 设置门店备用金可编辑日期
	 * 
	 * @param editDate 门店备用金可编辑日期
	 */
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	/**
	 * 取得可编辑标记
	 * 
	 * @return editFlg 可编辑标记
	 */
	@Transient
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
	 * 取得业务日期
	 * 
	 * @return createDateStr 业务日期
	 */
	@Transient
	public String getCreateDateStr() {
		return createDateStr;
	}

	/**
	 * 设置业务日期
	 * 
	 * @param createDateStr 业务日期
	 */
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	/**
	 * 取得业务日期
	 * 
	 * @return optDateStart 业务日期
	 */
	@Transient
	public String getOptDateStart() {
		return optDateStart;
	}

	/**
	 * 设置业务日期
	 * 
	 * @param optDateStart 业务日期
	 */
	public void setOptDateStart(String optDateStart) {
		this.optDateStart = optDateStart;
	}

	/**
	 * 取得业务日期
	 * 
	 * @return optDateEnd 业务日期
	 */
	@Transient
	public String getOptDateEnd() {
		return optDateEnd;
	}

	/**
	 * 设置业务日期
	 * 
	 * @param optDateEnd 业务日期
	 */
	public void setOptDateEnd(String optDateEnd) {
		this.optDateEnd = optDateEnd;
	}

	/**
	 * 取得 当月机构销售合计
	 * 
	 * @return totalSaleRamt 当月机构销售合计
	 */
	@Transient
	public BigDecimal getTotalSaleRamt() {
		return totalSaleRamt;
	}

	/**
	 * 设置 当月机构销售合计
	 * 
	 * @param totalSaleRamt 当月机构销售合计
	 */
	public void setTotalSaleRamt(BigDecimal totalSaleRamt) {
		this.totalSaleRamt = totalSaleRamt;
	}

	/**
	 * 取得 机构-支出占销售合计百分比
	 * 
	 * @return rate 机构-支出占销售合计百分比
	 */
	@Transient
	public String getRate() {
		return rate;
	}

	/**
	 * 设置 机构-支出占销售合计百分比
	 * 
	 * @param rate 机构-支出占销售合计百分比
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}
}
