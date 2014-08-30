package com.tjhx.service.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.affair.BorrowItemJpaDao;
import com.tjhx.dao.affair.BorrowItemMyBatisDao;
import com.tjhx.entity.affair.BorrowItem;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class BorrowItemManager {
	@Resource
	private BorrowItemJpaDao borrowItemJpaDao;
	@Resource
	private BorrowItemMyBatisDao borrowItemMyBatisDao;

	/**
	 * 取得借还物件信息列表
	 * 
	 * @param itemName
	 * @param itemType
	 * @param orgId
	 * @return
	 */
	public List<BorrowItem> getBorrowItemList(String itemName, String itemType, String orgId) {
		BorrowItem param = new BorrowItem();
		if (StringUtils.isNotBlank(itemName)) {
			param.setItemName("%" + itemName + "%");
		}
		if (StringUtils.isNotBlank(itemType)) {
			param.setItemType(itemType);
		}
		if (StringUtils.isNotBlank(orgId)) {
			param.setOrgId(orgId);
		}
		return borrowItemMyBatisDao.getBorrowItemList(param);
	}

	/**
	 * 根据编号取得借还物件信息
	 * 
	 * @param uuid 物件编号
	 * @return 借还物件信息
	 */
	public BorrowItem getBorrowItemByUuid(Integer uuid) {
		return borrowItemJpaDao.findOne(uuid);
	}

	/**
	 * 删除借还物件信息
	 * 
	 * @param uuid 物件编号
	 */
	@Transactional(readOnly = false)
	public void delBorrowItemByUuid(Integer uuid) {
		borrowItemJpaDao.delete(uuid);
	}

	/**
	 * 添加新物件信息
	 * 
	 * @param borrowItem BorrowItem信息
	 */
	@Transactional(readOnly = false)
	public void addNewBorrowItem(BorrowItem borrowItem) {

		BorrowItem _dbBorrowItem = borrowItemJpaDao.findByItemNo(borrowItem.getItemNo());
		// 该物件已存在!
		if (null != _dbBorrowItem) {
			throw new ServiceException("ERR_MSG_BORROW_ITEM_001");
		}

		// 设置物件状态1-在库
		borrowItem.setStatus("1");
		borrowItemJpaDao.save(borrowItem);
	}

	/**
	 * 更新物件信息
	 * 
	 * @param borrowItem 物件信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateBorrowItem(BorrowItem borrowItem) throws IllegalAccessException, InvocationTargetException {

		BorrowItem _dbBorrowItem = borrowItemJpaDao.findOne(borrowItem.getUuid());
		if (null == _dbBorrowItem) {
			// 物件不存在!
			throw new ServiceException("ERR_MSG_BORROW_ITEM_002");
		}
		// 物件名称
		_dbBorrowItem.setItemName(borrowItem.getItemName());
		// 物件类型
		_dbBorrowItem.setItemType(borrowItem.getItemType());
		// 所属机构
		_dbBorrowItem.setOrgId(borrowItem.getOrgId());
		// 接收时间
		_dbBorrowItem.setOptDate(borrowItem.getOptDate());
		// 备注
		_dbBorrowItem.setDescTxt(borrowItem.getDescTxt());

		borrowItemJpaDao.save(_dbBorrowItem);
	}
}