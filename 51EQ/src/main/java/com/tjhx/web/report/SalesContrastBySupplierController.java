/**
 * 
 */
package com.tjhx.web.report;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.google.common.collect.Lists;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.Supplier;
import com.tjhx.service.info.SalesContrastBySupplierManager;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.Select2Vo;
import com.tjhx.vo.SupplierSalesContrastVo;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesContrastBySupplier")
public class SalesContrastBySupplierController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SupplierManager supplierManager;
	@Resource
	private SalesContrastBySupplierManager salesContrastBySupplierManager;

	@RequestMapping(value = "init")
	public String init_Action(Model model) {
		// 初始化页面下拉菜单控件
		initPageControls(model);

		return "report/salesContrastBySupplierReport";
	}

	/**
	 * 初始化页面下拉菜单控件
	 * 
	 * @param model
	 */
	private void initPageControls(Model model) {
		// 初始化机构下拉菜单
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);
		// 初始化类型下拉菜单
		List<Supplier> _supplierList = supplierManager.getAllSupplierList();
		List<Select2Vo> supplierList = Lists.newArrayList();
		supplierList.add(new Select2Vo("ALL", "所有货商"));

		for (Supplier supplier : _supplierList) {
			Select2Vo vo = new Select2Vo(supplier.getSupplierBwId().trim(), supplier.getName().trim());

			supplierList.add(vo);
		}
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("supplierList", mapper.toJson(supplierList));
	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String optDate2_start = ServletRequestUtils.getStringParameter(request, "optDate2_start");
		String optDate2_end = ServletRequestUtils.getStringParameter(request, "optDate2_end");
		String optDate1_end = ServletRequestUtils.getStringParameter(request, "optDate1_end");
		String optDate1_start = ServletRequestUtils.getStringParameter(request, "optDate1_start");
		String supplier = ServletRequestUtils.getStringParameter(request, "supplier");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");

		model.addAttribute("optDate2_start", optDate2_start);
		model.addAttribute("optDate2_end", optDate2_end);
		model.addAttribute("optDate1_end", optDate1_end);
		model.addAttribute("optDate1_start", optDate1_start);
		model.addAttribute("supplier", supplier);
		model.addAttribute("orgId", orgId);

		// 初始化页面下拉菜单控件
		initPageControls(model);

		// =============================================================
		// 含有所有货商
		// =============================================================
		List<String> supplierNoList = Lists.newArrayList(supplier.split(","));
		if (supplierNoList.contains("ALL")) {// 含有所有货商
			List<Supplier> _supplierList = supplierManager.getAllSupplierList();

			supplierNoList.clear();

			for (Supplier _supplier : _supplierList) {
				supplierNoList.add(_supplier.getSupplierBwId().trim());
			}
		}
		String supplierNoArray = StringUtils.join(supplierNoList, ",");
		// =============================================================

		List<List<SupplierSalesContrastVo>> voList = salesContrastBySupplierManager.search(
				DateUtils.transDateFormat(optDate1_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate1_end, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_end, "yyyy-MM-dd", "yyyyMMdd"), supplierNoArray, orgId);

		if (StringUtils.isBlank(orgId)) {
			List<SupplierSalesContrastVo> voTotalList = salesContrastBySupplierManager.calTotal(voList, supplierNoArray);
			voList.add(0, voTotalList);
			System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
			Collections.sort(voTotalList, new Comparator<SupplierSalesContrastVo>() {
				@Override
				public int compare(SupplierSalesContrastVo o1, SupplierSalesContrastVo o2) {
					BigDecimal v1 = o1.getSaleRqty2();
					BigDecimal v2 = o2.getSaleRqty2();

					return v1.intValue() > v2.intValue() ? -1 : 1;
				}
			});
		}

		model.addAttribute("contrastList", voList);

		return "report/salesContrastBySupplierReport";
	}
}
