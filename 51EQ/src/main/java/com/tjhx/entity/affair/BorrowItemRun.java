/**
 * 
 */
package com.tjhx.entity.affair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 借还物件流水
 */
@Entity
@Table(name = "T_BORROW_ITEM_RUN")
public class BorrowItemRun extends IdEntity {

	private static final long serialVersionUID = 3560980414142444877L;
	/** 物件编号 */
	private String itemNo;
	/** 借用人 */
	private String borrower;
	/** 借用日期 */
	private String borrowDate;
	/** 借用-经办人 */
	private String borrowAttn;
	/** 用途 */
	private String useDesc;
	/** 预计归还日期 */
	private String expReturnDate;
	/** 实际归还日期 */
	private String actReturnDate;
	/** 归还人 */
	private String returnPeople;
	/** 归还-经办人 */
	private String returnAttn;

	/**
	 * 获取物件编号
	 * 
	 * @return itemNo 物件编号
	 */
	@Column(length = 8)
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * 设置物件编号
	 * 
	 * @param itemNo 物件编号
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	/**
	 * 获取借用人
	 * 
	 * @return borrower 借用人
	 */
	@Column(length = 32)
	public String getBorrower() {
		return borrower;
	}

	/**
	 * 设置借用人
	 * 
	 * @param borrower 借用人
	 */
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	/**
	 * 获取借用日期
	 * 
	 * @return borrowDate 借用日期
	 */
	@Column(length = 8)
	public String getBorrowDate() {
		return borrowDate;
	}

	/**
	 * 设置借用日期
	 * 
	 * @param borrowDate 借用日期
	 */
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	/**
	 * 获取借用-经办人
	 * 
	 * @return borrowAttn 借用-经办人
	 */
	@Column(length = 32)
	public String getBorrowAttn() {
		return borrowAttn;
	}

	/**
	 * 设置借用-经办人
	 * 
	 * @param borrowAttn 借用-经办人
	 */
	public void setBorrowAttn(String borrowAttn) {
		this.borrowAttn = borrowAttn;
	}

	/**
	 * 获取用途
	 * 
	 * @return useDesc 用途
	 */
	public String getUseDesc() {
		return useDesc;
	}

	/**
	 * 设置用途
	 * 
	 * @param useDesc 用途
	 */
	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}

	/**
	 * 获取预计归还日期
	 * 
	 * @return expReturnDate 预计归还日期
	 */
	@Column(length = 8)
	public String getExpReturnDate() {
		return expReturnDate;
	}

	/**
	 * 设置预计归还日期
	 * 
	 * @param expReturnDate 预计归还日期
	 */
	public void setExpReturnDate(String expReturnDate) {
		this.expReturnDate = expReturnDate;
	}

	/**
	 * 获取实际归还日期
	 * 
	 * @return actReturnDate 实际归还日期
	 */
	@Column(length = 8)
	public String getActReturnDate() {
		return actReturnDate;
	}

	/**
	 * 设置实际归还日期
	 * 
	 * @param actReturnDate 实际归还日期
	 */
	public void setActReturnDate(String actReturnDate) {
		this.actReturnDate = actReturnDate;
	}

	/**
	 * 获取归还人
	 * 
	 * @return returnPeople 归还人
	 */
	@Column(length = 32)
	public String getReturnPeople() {
		return returnPeople;
	}

	/**
	 * 设置归还人
	 * 
	 * @param returnPeople 归还人
	 */
	public void setReturnPeople(String returnPeople) {
		this.returnPeople = returnPeople;
	}

	/**
	 * 获取归还-经办人
	 * 
	 * @return returnAttn 归还-经办人
	 */
	@Column(length = 32)
	public String getReturnAttn() {
		return returnAttn;
	}

	/**
	 * 设置归还-经办人
	 * 
	 * @param returnAttn 归还-经办人
	 */
	public void setReturnAttn(String returnAttn) {
		this.returnAttn = returnAttn;
	}
}
