package com.tjhx.web.accounts;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.tjhx.entity.info.Supplier;
import com.tjhx.service.info.SupplierManager;

public class StoreRunUtils {

	/**
	 * 初始化入库类型
	 * 
	 * @param model
	 */
	protected static void initStoreTypeList(Model model) {
		Map<String, String> storeTypeList = new LinkedHashMap<String, String>();
		storeTypeList.put("", "");
		storeTypeList.put("A", "挂账采购");
		storeTypeList.put("B", "现结采购");
		storeTypeList.put("C", "货商补欠");
		model.addAttribute("storeTypeList", storeTypeList);
	}

	/**
	 * 初始化供应商列表
	 * 
	 * @param model
	 */
	protected static void initSupplierList(Model model, SupplierManager supplierManager) {

		List<Supplier> _supplierList = supplierManager.getSupplierList();

		Map<String, String> supplier = new LinkedHashMap<String, String>();
		supplier.put("", "");

		for (Supplier _supplier : _supplierList) {
			supplier.put(_supplier.getSupplierBwId(), _supplier.getName());
		}
		model.addAttribute("supplier", supplier);
	}

}
