package com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.info.ItemTypeJpaDao;
import com.tjhx.daobw.ItemTypeClsMyBatisDao;
import com.tjhx.entity.bw.ItemTypeCls;
import com.tjhx.entity.info.ItemType;
import com.tjhx.globals.MemcachedObjectType;

@Service
@Transactional(readOnly = true)
public class ItemTypeManager {
	private static Logger logger = LoggerFactory.getLogger(ItemTypeManager.class);
	@Resource
	private SpyMemcachedClient spyMemcachedClient;
	@Resource
	private ItemTypeJpaDao itemTypeJpaDao;
	@Resource
	private ItemTypeClsMyBatisDao itemTypeClsMyBatisDao;

	/**
	 * 取得商品种类信息
	 * 
	 * @param itemNo 商品编号种类
	 * @return
	 */
	public ItemType getByItemNo(String itemNo) {
		List<ItemType> _itemTypeList = getAllItemType();

		for (ItemType itemType : _itemTypeList) {
			if (itemType.getItemNo().equals(itemNo)) {
				return itemType;
			}
		}

		return null;
	}

	/**
	 * 取得所有商品种类信息
	 * 
	 * @return 商品种类信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<ItemType> getAllItemType() {
		List<ItemType> _itemTypeList = spyMemcachedClient.get(MemcachedObjectType.ITEM_TYPE_LIST.getObjKey());

		if (null == _itemTypeList) {
			// 从数据库中取出商品种类信息(List格式)
			_itemTypeList = (List<ItemType>) itemTypeJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "itemNo")));
			// 将商品种类信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.ITEM_TYPE_LIST.getObjKey(),
					MemcachedObjectType.ITEM_TYPE_LIST.getExpiredTime(), _itemTypeList);

			logger.debug("商品种类信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出商品种类信息");
		}
		return _itemTypeList;
	}

	/**
	 * 同步百威商品种类信息
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public void bwDataSyn() {
		List<ItemTypeCls> _bwList = itemTypeClsMyBatisDao.getItemTypeClsList();
		List<ItemType> _itemTypeList = (List<ItemType>) itemTypeJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC,
				"itemNo")));

		for (ItemTypeCls itemTypeCls : _bwList) {
			// 见ItemTypeCls.java的equals实现
			if (_itemTypeList.contains(itemTypeCls)) {
				// 更新商品种类信息
				updateItemTypeInfo(itemTypeCls);
			} else {
				// 新增商品种类信息
				addItemTypeInfo(itemTypeCls);
			}
		}
		spyMemcachedClient.delete(MemcachedObjectType.ITEM_TYPE_LIST.getObjKey());
	}

	/**
	 * 新增商品种类信息
	 * 
	 * @param itemTypeCls
	 */
	@Transactional(readOnly = false)
	private void addItemTypeInfo(ItemTypeCls itemTypeCls) {
		ItemType _itemType = new ItemType();
		// 种类编号
		_itemType.setItemNo(itemTypeCls.getItemClsno().trim());
		// 种类名称
		_itemType.setItemShortName(getItemShortName(itemTypeCls.getItemClsname().trim()));
		// 种类名称
		_itemType.setItemName(itemTypeCls.getItemClsname().trim());

		itemTypeJpaDao.save(_itemType);
	}

	/**
	 * 更新商品种类信息
	 * 
	 * @param itemTypeCls
	 */
	@Transactional(readOnly = false)
	private void updateItemTypeInfo(ItemTypeCls itemTypeCls) {
		ItemType _itemType = itemTypeJpaDao.findByItemNo(itemTypeCls.getItemClsno());
		// 种类编号
		_itemType.setItemNo(itemTypeCls.getItemClsno().trim());
		// 种类名称
		_itemType.setItemShortName(getItemShortName(itemTypeCls.getItemClsname().trim()));
		// 种类名称
		_itemType.setItemName(itemTypeCls.getItemClsname().trim());

		itemTypeJpaDao.save(_itemType);
	}

	private String getItemShortName(String itemName) {
		if (null == itemName) {
			return null;
		}
		if (!itemName.contains("（")) {
			return itemName.trim();
		}
		int _index = itemName.indexOf("（");
		String _shortName = itemName.substring(0, _index);
		return _shortName.trim();
	}
}
