package com.tjhx.web.info;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.info.SupplierSignRun_Show;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.service.info.SupplierSignRunManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/supplierSignRun")
public class SupplierSignRunController extends BaseController {
	@Resource
	private SupplierSignRunManager supplierSignRunManager;
	@Resource
	private SupplierManager supplierManager;

	private void initYearList(Model model) {
		Map<String, String> yearList = new LinkedHashMap<String, String>();

		yearList.put("", "");
		yearList.put("2013", "2013");
		yearList.put("2014", "2014");
		yearList.put("2015", "2015");

		model.addAttribute("yearList", yearList);
	}

	/**
	 * 取得特殊标记-货品供应商页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) {
		model.addAttribute("showFlg", false);

		initYearList(model);

		return "info/supplierSignRunList";
	}

	/**
	 * 显示特殊标记-货品供应商信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "show")
	public String show_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		model.addAttribute("showFlg", true);

		initYearList(model);

		String optDateY = ServletRequestUtils.getStringParameter(request, "optDateY");
		model.addAttribute("optDateY", optDateY);

		List<SupplierSignRun_Show> _supSignRunList = supplierSignRunManager.getSupplierSignRunList(optDateY);

		model.addAttribute("supSignRunList", _supSignRunList);
		return "info/supplierSignRunList";
	}

	@RequestMapping(value = "editInit/{supId}/{optDateY}")
	public String edit_Action(@PathVariable("supId") String supId, @PathVariable("optDateY") String optDateY,
			Model model) {
		model.addAttribute("supId", supId);
		model.addAttribute("optDateY", optDateY);
		model.addAttribute("supName", supplierManager.getSupplierByBwId(supId).getName());

		return "info/supplierSignRunForm";
	}

	// 赊购挂账-不挂
	@RequestMapping(value = "noLoan")
	public String noLoan_Action(Model model, HttpServletRequest request) {
		return null;
	}

	// 赊购挂账-挂账
	@RequestMapping(value = "loan")
	public String loan_Action(Model model, HttpServletRequest request) {
		return null;
	}
}
