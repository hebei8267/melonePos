/**
 * 
 */
package com.tjhx.web.order;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.ItemType;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.order.ReplenishOrder;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.info.ItemTypeManager;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.service.order.ReplenishOrderManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.Select2Vo;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

/**
 * 补货单
 */
@Controller
@RequestMapping(value = "/replenishOrder")
public class ReplenishOrderController extends BaseController {
	@Resource
	private ReplenishOrderManager replenishOrderManager;
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SupplierManager supplierManager;
	@Resource
	private ItemTypeManager itemTypeManager;

	/**
	 * 门店--补货单管理
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "list")
	public String list_Action(Model model, HttpServletRequest request) {
		// 补货单状态-下拉菜单
		initOrderState(model);

		initItemTypeList(model);
		initSupplierList(model);

		model.addAttribute("optDateShow_start", DateUtils.getNextDateFormatDate(-30, "yyyy-MM-dd"));
		model.addAttribute("optDateShow_end", DateUtils.getCurFormatDate("yyyy-MM-dd"));

		return "order/replenishOrderList";
	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request, HttpSession session) throws ServletRequestBindingException,
			ParseException {
		// 补货单状态-下拉菜单
		initOrderState(model);

		initItemTypeList(model);
		initSupplierList(model);

		String orderNo = ServletRequestUtils.getStringParameter(request, "orderNo");
		String orderState = ServletRequestUtils.getStringParameter(request, "orderState");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");

		model.addAttribute("orderNo", orderNo);
		model.addAttribute("orderState", orderState);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		// 货号
		String barcode = ServletRequestUtils.getStringParameter(request, "barcode");
		// 商品类别
		String itemType = ServletRequestUtils.getStringParameter(request, "itemType");
		// 货商
		String supplier = ServletRequestUtils.getStringParameter(request, "supplier");
		model.addAttribute("barcode", barcode);
		model.addAttribute("itemType", itemType);
		model.addAttribute("supplier", supplier);

		List<ReplenishOrder> list = replenishOrderManager.getReplenishOrderList(orderNo, getUserInfo(session).getOrgId(), orderState,
				DateUtils.getNextDateFormatDate(DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"), -1, "yyyyMMdd",
						"yyyyMMdd"), DateUtils.getNextDateFormatDate(DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"),
						1, "yyyyMMdd", "yyyyMMdd"), barcode, itemType, supplier);

		model.addAttribute("replenishOrderList", list);

		return "order/replenishOrderList";
	}

	/**
	 * 门店--补货单编辑
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{orderNo}")
	public String edit_Action(@PathVariable("orderNo") String orderNo, Model model, HttpServletRequest request) {
		ReplenishOrder order = replenishOrderManager.getReplenishOrderByOrderNo(orderNo);

		model.addAttribute("order", order);

		return "order/replenishOrderEdit";
	}

	@RequestMapping(value = "editSave")
	public String editSave_Action(HttpServletRequest request, Model model) throws ServletRequestBindingException {
		String orderNo = ServletRequestUtils.getStringParameter(request, "orderNo");
		String description = ServletRequestUtils.getStringParameter(request, "description");
		String[] productBarcodes = ServletRequestUtils.getStringParameters(request, "productBarcode");
		String[] receiptNums = ServletRequestUtils.getStringParameters(request, "receiptNum");

		replenishOrderManager.updateReplenishOrderDetail_receiptNums(orderNo, description, productBarcodes, receiptNums);
		boolean result = replenishOrderManager.receiptNumCheck(orderNo, true);
		if (result) {
			addInfoMsg(model, "TIP_MSG_RECEIVING_CHECK_FINISH");

			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/replenishOrder/list";
		} else {
			addInfoMsg(model, "TIP_MSG_RECEIVING_CHECK_ERROR");

			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/replenishOrder/edit/" + orderNo;
		}

	}

	/**
	 * 门店--补货单查看
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{orderNo}")
	public String view_Action(@PathVariable("orderNo") String orderNo, Model model) {
		ReplenishOrder order = replenishOrderManager.getReplenishOrderByOrderNo(orderNo);

		model.addAttribute("order", order);

		return "order/replenishOrderView";
	}

	// ======================================================================
	// 总部
	// ======================================================================
	/**
	 * 补货单状态-下拉菜单
	 * 
	 * @param orgManager
	 * @param model
	 */
	private void initOrderState(Model model) {

		Map<String, String> stateList = new LinkedHashMap<String, String>();

		stateList.put("", "");
		stateList.put("01", "编辑中");
		stateList.put("02", "收货中");
		stateList.put("03", "收货完成");
		stateList.put("99", "已完结");

		model.addAttribute("stateList", stateList);
	}

	/**
	 * 总部--补货单管理(查询)
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws ParseException
	 */
	@RequestMapping(value = "manageSearch")
	public String manageSearch_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException, ParseException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);
		// 补货单状态-下拉菜单
		initOrderState(model);
		initItemTypeList(model);
		initSupplierList(model);

		String orderNo = ServletRequestUtils.getStringParameter(request, "orderNo");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String orderState = ServletRequestUtils.getStringParameter(request, "orderState");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");

		model.addAttribute("orderNo", orderNo);
		model.addAttribute("orgId", orgId);
		model.addAttribute("orderState", orderState);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		// 货号
		String barcode = ServletRequestUtils.getStringParameter(request, "barcode");
		// 商品类别
		String itemType = ServletRequestUtils.getStringParameter(request, "itemType");
		// 货商
		String supplier = ServletRequestUtils.getStringParameter(request, "supplier");
		model.addAttribute("barcode", barcode);
		model.addAttribute("itemType", itemType);
		model.addAttribute("supplier", supplier);

		List<ReplenishOrder> list = replenishOrderManager.getReplenishOrderList(orderNo, orgId, orderState, DateUtils
				.getNextDateFormatDate(DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"), -1, "yyyyMMdd", "yyyyMMdd"),
				DateUtils.getNextDateFormatDate(DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"), 1, "yyyyMMdd",
						"yyyyMMdd"), barcode, itemType, supplier);

		model.addAttribute("replenishOrderList", list);

		return "order/replenishOrderManageList";
	}

	/**
	 * 总部--补货单管理
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "manageList")
	public String manageList_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);
		// 补货单状态-下拉菜单
		initOrderState(model);
		initItemTypeList(model);
		initSupplierList(model);

		model.addAttribute("optDateShow_start", DateUtils.getNextDateFormatDate(-30, "yyyy-MM-dd"));
		model.addAttribute("optDateShow_end", DateUtils.getCurFormatDate("yyyy-MM-dd"));

		return "order/replenishOrderManageList";
	}

	/**
	 * 总部--补货单编辑
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "manageEdit/{orderNo}")
	public String manageEdit_Action(@PathVariable("orderNo") String orderNo, Model model, HttpServletRequest request) {
		ReplenishOrder order = replenishOrderManager.getReplenishOrderByOrderNo(orderNo);

		model.addAttribute("order", order);

		return "order/replenishOrderManageEdit";
	}

	@RequestMapping(value = "manageEditSave")
	public String manageEditSave_Action(HttpServletRequest request, Model model) throws ServletRequestBindingException {
		String orderNo = ServletRequestUtils.getStringParameter(request, "orderNo");
		String description = ServletRequestUtils.getStringParameter(request, "description");
		String[] productBarcodes = ServletRequestUtils.getStringParameters(request, "productBarcode");
		String[] replenishNums = ServletRequestUtils.getStringParameters(request, "replenishNum");

		replenishOrderManager.updateReplenishOrderDetail_replenishNums(orderNo, description, productBarcodes, replenishNums);
		boolean result = replenishOrderManager.receiptNumCheck(orderNo, false);

		if (result) {
			addInfoMsg(model, "TIP_MSG_RECEIVING_CHECK_FINISH");

			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/replenishOrder/manageList";
		} else {
			addInfoMsg(model, "TIP_MSG_RECEIVING_CHECK_ERROR");

			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/replenishOrder/manageEdit/" + orderNo;
		}

	}

	@RequestMapping(value = "manageSuccessSave/{orderNo}")
	public String manageSuccessSave_Action(@PathVariable("orderNo") String orderNo) throws ServletRequestBindingException {

		replenishOrderManager.replenishOrderSuccess(orderNo);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/replenishOrder/manageList";
	}

	/**
	 * 删除补货单
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "manageDel")
	public String manageDel_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			replenishOrderManager.delReplenishOrder(idArray[i]);
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/replenishOrder/manageList";
	}

	/**
	 * 下载补货单
	 * 
	 * @param ids
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	@RequestMapping(value = "export")
	public void export_Action(@RequestParam("uuids") String ids, HttpServletResponse response) throws IOException, ParsePropertyException,
			InvalidFormatException {
		String[] orderNoArray = ids.split(",");

		String downLoadFileName = replenishOrderManager.createExportFile(orderNoArray);

		if (null == downLoadFileName) {
			return;
		}
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String downLoadPath = sysConfig.getReportTmpPath() + downLoadFileName;

		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(downLoadFileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {

		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	/**
	 * 补货单-发货
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "manageSend")
	public String manageSend_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			replenishOrderManager.sendReplenishOrder(idArray[i]);
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/replenishOrder/manageList";
	}

	/**
	 * 初始化页面下拉菜单控件
	 * 
	 * @param model
	 */
	private void initItemTypeList(Model model) {
		// 初始化类型下拉菜单
		List<ItemType> _itemTypeList = itemTypeManager.getAllItemType();
		List<Select2Vo> itemTypeList = Lists.newArrayList();

		for (ItemType itemType : _itemTypeList) {
			Select2Vo vo = new Select2Vo(itemType.getItemNo().trim(), itemType.getItemShortName().trim());

			itemTypeList.add(vo);
		}
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("itemTypeList", mapper.toJson(itemTypeList));
	}

	/**
	 * 初始化页面下拉菜单控件
	 * 
	 * @param model
	 */
	private void initSupplierList(Model model) {
		// 初始化类型下拉菜单
		List<Supplier> _supplierList = supplierManager.getAllSupplierList();
		List<Select2Vo> supplierList = Lists.newArrayList();

		for (Supplier supplier : _supplierList) {
			Select2Vo vo = new Select2Vo(supplier.getSupplierBwId().trim(), supplier.getName().trim());

			supplierList.add(vo);
		}
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("supplierList", mapper.toJson(supplierList));
	}
}
