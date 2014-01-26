package com.tjhx.service.info;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.info.SupplierSignMyBatisDao;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.info.SupplierSignRun_Show;

@Service
@Transactional(readOnly = true)
public class SupplierSignRunManager {
	@Resource
	private SupplierSignMyBatisDao supplierSignMyBatisDao;

	/**
	 * 取得特殊标记-货品供应商信息列表
	 * 
	 * @return
	 */
	public List<SupplierSignRun_Show> getSupplierSignRunList(String optYear) {
		List<Supplier> _supList = supplierSignMyBatisDao.getSupplierList(optYear);

		List<SupplierSignRun_Show> _supSignRunList = initSupplierSignRunList(_supList, optYear);

		return _supSignRunList;
	}

	/**
	 * 初始化特殊标记-货品供应商 流水信息（显示）
	 * 
	 * @param _supList
	 * @return
	 */
	private List<SupplierSignRun_Show> initSupplierSignRunList(List<Supplier> _supList, String optYear) {
		List<SupplierSignRun_Show> _list = new ArrayList<SupplierSignRun_Show>();

		for (Supplier supplier : _supList) {

			SupplierSignRun_Show run = new SupplierSignRun_Show(supplier.getSupplierBwId(), supplier.getName(), optYear);
			// TODO ?????????????????????
			_list.add(run);
		}

		return _list;
	}
}
