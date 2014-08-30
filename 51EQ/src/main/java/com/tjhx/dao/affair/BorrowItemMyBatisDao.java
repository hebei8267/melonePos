package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.BorrowItem;


public interface BorrowItemMyBatisDao {

	public List<BorrowItem> getBorrowItemList(BorrowItem borrowItem);

}
