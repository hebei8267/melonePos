package com.tjhx.web.info;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.info.Region;
import com.tjhx.entity.info.Supplier;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.info.RegionManager;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/supplier")
public class SupplierController extends BaseController {
	@Resource
	private SupplierManager supplierManager;
	@Resource
	private RegionManager regionManager;

	/**
	 * 取得Supplier信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String supplierList_Action(Model model, HttpServletRequest request) {
		List<Supplier> supplierList = supplierManager.getAllSupplierOfSignBill();

		model.addAttribute("supplierList", supplierList);

		return "info/supplierList";
	}

	/**
	 * 编辑Supplier信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editSupplier_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		Supplier supplier = supplierManager.getSupplierByUuid(id);
		if (null == supplier) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplier/list";
		} else {
			model.addAttribute("supplier", supplier);

			initPayTypeList(model);
			initRegionList(model);

			return "info/supplierForm";
		}

	}

	/**
	 * 删除Supplier信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delSupplier_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			supplierManager.delSupplierByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplier/list";
	}

	/**
	 * 新增Supplier初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initSupplier_Action(Model model, HttpServletRequest request) {

		Supplier supplier = new Supplier();
		model.addAttribute("supplier", supplier);

		initPayTypeList(model);
		initRegionList(model);

		return "info/supplierForm";
	}

	private void initPayTypeList(Model model) {
		Map<String, String> payTypeList = new LinkedHashMap<String, String>();
		payTypeList.put("", "");
		payTypeList.put("1", "现款商户");
		payTypeList.put("2", "月结商户");
		payTypeList.put("4", "不定");

		model.addAttribute("payTypeList", payTypeList);
	}

	private void initRegionList(Model model) {
		List<Region> _regionList = regionManager.getAllRegion();

		Map<String, String> regionList = new LinkedHashMap<String, String>();
		regionList.put("", "");
		for (Region _region : _regionList) {
			regionList.put(_region.getCode(), _region.getName());
		}

		model.addAttribute("regionList", regionList);

	}

	/**
	 * 新增/修改 Supplier信息
	 * 
	 * @param supplier
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveSupplier_Action(@ModelAttribute("supplier") Supplier supplier, Model model,
			HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {

		if (null == supplier.getUuid()) {// 新增操作
			try {
				supplierManager.addNewSupplier(supplier);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initPayTypeList(model);
				initRegionList(model);

				return "info/supplierForm";
			}
		} else {// 修改操作
			try {
				supplierManager.updateSupplier(supplier);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initPayTypeList(model);
				initRegionList(model);

				return "info/supplierForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplier/list";
	}

	/**
	 * 同步百威供应商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "bwDataSynBtn")
	public String bwDataSyn_Action(Model model) {
		// 同步百威数据库供应商信息
		supplierManager.bwDataSyn();

		addInfoMsg(model, "TIP_MSG_SUPPLIER_001");

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplier/list";
	}

	/**
	 * 同步挂账供应商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "signBillSupDataSynBtn")
	public String signBillSupDataSyn_Action(@RequestParam("supplierBwIds") String supplierBwIds, Model model) {
		String[] supIdArray = supplierBwIds.split(",");
		// 同步挂账供应商信息
		supplierManager.signBillSupDataSyn(supIdArray);

		addInfoMsg(model, "TIP_MSG_SUPPLIER_002");

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplier/list";
	}
}