package com.tjhx.dao.info;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.Goods;

public interface GoodsJpaDao extends CrudRepository<Goods, Integer> {

	/**
	 * 根据短条码查找商品信息
	 * 
	 * @param itemSubno
	 * @return
	 */
	public Goods findBySubno(String itemSubno);

	@Query("select count(*) from Goods")
	public Long getGoodsCount();

}
