package com.tjhx.dao.info;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.Supplier;

public interface SupplierJpaDao extends CrudRepository<Supplier, Integer> {

	/**
	 * 取得所有货品供应商(不含已删除供应商)
	 * 
	 * @param sort
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Query("select s from Supplier s where s.delFlg = '0'")
	public Iterable findSupplierList(Sort sort);

	/**
	 * 取得所有货品供应商
	 * 
	 * @param sort
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Query("select s from Supplier s")
	public Iterable findAllSupplierList(Sort sort);

	/**
	 * 取得货品供应商信息
	 * 
	 * @param name 名称
	 * @return 货品供应商信息
	 */
	public Supplier findByName(String name);

	/**
	 * 取得货品供应商信息
	 * 
	 * @param BwId 供应商编号-百威
	 * @return 货品供应商信息
	 */
	public Supplier findBySupplierBwId(String BwId);
}
