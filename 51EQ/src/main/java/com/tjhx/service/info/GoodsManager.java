package com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.info.GoodsJpaDao;
import com.tjhx.dao.info.GoodsMyBatisDao;
import com.tjhx.daobw.ItemGoodsMyBatisDao;
import com.tjhx.entity.bw.ItemGoods;
import com.tjhx.entity.info.Goods;

@Service
@Transactional(readOnly = true)
public class GoodsManager {
	@Resource
	private ItemGoodsMyBatisDao itemGoodsMyBatisDao;
	@Resource
	private GoodsJpaDao goodsJpaDao;
	@Resource
	private GoodsMyBatisDao goodsMyBatisDao;

	/**
	 * 取得商品数量
	 * 
	 * @return
	 */
	public Long getGoodsCount() {
		return goodsJpaDao.getGoodsCount();
	}

	/**
	 * 同步百威商品信息
	 */
	@Transactional(readOnly = false)
	public void bwDataSyn() {
		goodsMyBatisDao.dropTable();
		goodsMyBatisDao.createTable();

		List<ItemGoods> _bwList = itemGoodsMyBatisDao.getItemGoodsList();

		for (ItemGoods _itemGoods : _bwList) {
			// 新增商品信息
			addGoodsInfo(_itemGoods);
		}
	}

	/**
	 * 新增商品信息
	 * 
	 * @param itemGoods
	 */
	@Transactional(readOnly = false)
	private void addGoodsInfo(ItemGoods _itemGoods) {
		Goods _goods = new Goods();

		// 短条码
		_goods.setSubno(_itemGoods.getItemSubno());
		// 长条码
		_goods.setBarcode(_itemGoods.getItemBarcode());
		// 商品名称
		_goods.setName(_itemGoods.getItemName());
		// 商品名称-拼音缩写
		_goods.setPyName(_itemGoods.getItemSubname());
		// 商品种类编号
		_goods.setItemNo(_itemGoods.getItemClsno());
		// 供应商编号
		_goods.setSupplierBwId(_itemGoods.getSupcustNo());

		goodsJpaDao.save(_goods);

	}

}
