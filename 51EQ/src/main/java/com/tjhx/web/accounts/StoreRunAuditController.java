package com.tjhx.web.accounts;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.StoreRun;
import com.tjhx.globals.Constants;
import com.tjhx.service.accounts.StoreRunManager;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/storeRunAudit")
public class StoreRunAuditController extends BaseController {
	@Resource
	private StoreRunManager storeRunManager;
	@Resource
	private SupplierManager supplierManager;

	/**
	 * 取得货物入库流水信息列表(审核)
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list", "" })
	public String storeRunAuditList_Action(Model model, HttpSession session) throws ParseException {
		List<StoreRun> storeRunList = storeRunManager.getAllStoreRunByOrgId_1(getUserInfo(session).getOrganization()
				.getId(), DateUtils.getCurrentDateShortStr());

		model.addAttribute("storeRunList", storeRunList);

		StoreRun totalStoreRun = storeRunManager.calTotal(storeRunList);
		model.addAttribute("totalStoreRun", totalStoreRun);

		return "accounts/storeRunAuditList";
	}

	/**
	 * 取得货物入库流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "list/{date}")
	public String storeRunAuditList_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {
		List<StoreRun> storeRunList = storeRunManager.getAllStoreRunByOrgId_2(getUserInfo(session).getOrganization()
				.getId(), date);

		model.addAttribute("storeRunList", storeRunList);

		StoreRun totalStoreRun = storeRunManager.calTotal(storeRunList);
		model.addAttribute("totalStoreRun", totalStoreRun);

		return "accounts/storeRunAuditList";
	}

	/**
	 * 初始化审核货物入库流水信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "confirmInit/{id}")
	public String auditStoreRun_Action(@PathVariable("id") Integer id, Model model) {

		StoreRun storeRun = storeRunManager.getStoreRunByUuid(id);
		if (null == storeRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storeRunAudit";
		} else {
			model.addAttribute("storeRun", storeRun);

			StoreRunUtils.initSupplierList(model, supplierManager);
			StoreRunUtils.initStoreTypeList(model);

			return "accounts/storeRunAuditForm";
		}

	}

	/**
	 * 货物入库流水信息-审核确认
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "confirm/{id}")
	public String auditConfirmStoreRun_Action(@PathVariable("id") Integer storeRunUuid, Model model) {

		storeRunManager.auditConfirmStoreRun(storeRunUuid);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storeRunAudit";
	}

}
