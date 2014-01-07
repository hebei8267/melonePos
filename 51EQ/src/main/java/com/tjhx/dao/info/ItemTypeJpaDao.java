package com.tjhx.dao.info;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.ItemType;

public interface ItemTypeJpaDao extends CrudRepository<ItemType, Integer> {
	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得商品种类信息
	 * 
	 * @param itemNo 商品种类编号
	 * @return 商品种类信息
	 */
	public ItemType findByItemNo(String itemNo);
}
