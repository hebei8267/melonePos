package com.tjhx.service.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.affair.BorrowItemRunJpaDao;
import com.tjhx.entity.affair.BorrowItem;
import com.tjhx.entity.affair.BorrowItemRun;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class BorrowItemRunManager {
	@Resource
	private BorrowItemRunJpaDao borrowItemRunJpaDao;
	@Resource
	private BorrowItemManager borrowItemManager;

	/**
	 * 根据物件编号取得借还物件流水信息
	 * 
	 * @param itemNo 借还物件编号
	 * @return 借还物件流水信息
	 */
	public List<BorrowItemRun> getBorrowItemRunListByItemNo(String itemNo) {
		return (List<BorrowItemRun>) borrowItemRunJpaDao.findByItemNo(itemNo);
	}

	/**
	 * 根据编号取得借还物件流水信息
	 * 
	 * @param uuid 借还物件流水编号
	 * @return 借还物件流水信息
	 */
	public BorrowItemRun getBorrowItemRunByUuid(Integer uuid) {
		return borrowItemRunJpaDao.findOne(uuid);
	}

	/**
	 * 删除借还物件流水信息
	 * 
	 * @param uuid 借还物件流水编号
	 */
	@Transactional(readOnly = false)
	public void delBorrowItemRunByUuid(Integer uuid) {
		BorrowItemRun _run = borrowItemRunJpaDao.findOne(uuid);
		borrowItemRunJpaDao.delete(_run);

		List<BorrowItemRun> _list = getBorrowItemRunListByItemNo(_run.getItemNo());
		if (null != _list && _list.size() > 0) {
			BorrowItemRun _tmp = _list.get(0);

			if (StringUtils.isBlank(_tmp.getReturnAttn())) {
				lendItem(_run.getItemNo());
			} else {
				returnItem(_run.getItemNo());
			}
		}
	}

	/**
	 * 更新物件状态信息（借出）
	 */
	private void lendItem(String itemNo) {
		BorrowItem _borrowItem = new BorrowItem();
		_borrowItem.setItemNo(itemNo);
		// 物件状态1-在库0-借出
		_borrowItem.setStatus("0");
		borrowItemManager.updateBorrowItemStatus(_borrowItem);
	}

	/**
	 * 更新物件状态信息（还入）
	 * 
	 * @param itemNo
	 */
	private void returnItem(String itemNo) {

		BorrowItem _borrowItem = new BorrowItem();
		_borrowItem.setItemNo(itemNo);
		// 物件状态1-在库0-借出
		_borrowItem.setStatus("1");
		borrowItemManager.updateBorrowItemStatus(_borrowItem);
	}

	/**
	 * 添加新借还物件流水信息
	 * 
	 * @param borrowItemRun 借还物件流水信息
	 */
	@Transactional(readOnly = false)
	public void addNewBorrowItemRun(BorrowItemRun borrowItemRun) {
		borrowItemRunJpaDao.save(borrowItemRun);

		// 更新物件状态信息（借出）
		lendItem(borrowItemRun.getItemNo());

	}

	/**
	 * 更新借还物件流水信息
	 * 
	 * @param borrowItemRun 借还物件流水信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateBorrowItemRun(BorrowItemRun borrowItemRun) throws IllegalAccessException, InvocationTargetException {

		BorrowItemRun _dbBorrowItemRun = borrowItemRunJpaDao.findOne(borrowItemRun.getUuid());
		if (null == _dbBorrowItemRun) {
			// 借还物件流水信息不存在!
			throw new ServiceException("ERR_MSG_BORROW_ITEM_RUN_002");
		}

		_dbBorrowItemRun.setActReturnDate(borrowItemRun.getActReturnDate());
		_dbBorrowItemRun.setReturnPeople(borrowItemRun.getReturnPeople());
		_dbBorrowItemRun.setReturnAttn(borrowItemRun.getReturnAttn());

		borrowItemRunJpaDao.save(_dbBorrowItemRun);

		// 更新物件状态信息（还入）
		returnItem(borrowItemRun.getItemNo());
	}
}