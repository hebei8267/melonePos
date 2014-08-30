/**
 * 
 */
package com.tjhx.web.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.BorrowItem;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.BorrowItemManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/borrowItem")
public class BorrowItemController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private BorrowItemManager borrowItemManager;

	/**
	 * 物件借还信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String borrowItemList_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_Null_Root(orgManager, model);
		// 初始化物件借还状态下拉列表
		initStatusList(model);
		// 初始化物件类型下拉列表
		initItemTypeList(model);

		return "affair/borrowItemList";
	}

	/**
	 * 初始化物件借还状态下拉列表
	 * 
	 * @param model
	 */
	private void initStatusList(Model model) {
		Map<String, String> statusList = new LinkedHashMap<String, String>();

		statusList.put("", "");
		statusList.put("1", "在库");
		statusList.put("0", "借出");

		model.addAttribute("statusList", statusList);
	}

	/**
	 * 初始化物件类型下拉列表
	 * 
	 * @param model
	 */
	private void initItemTypeList(Model model) {
		Map<String, String> itemTypeList = new LinkedHashMap<String, String>();
		// 01-证书、02-执照、03-公章、04-押金收据、99-其他
		itemTypeList.put("", "");
		itemTypeList.put("01", "证书");
		itemTypeList.put("02", "执照");
		itemTypeList.put("03", "公章");
		itemTypeList.put("04", "押金收据出");
		itemTypeList.put("99", "其他");
		model.addAttribute("itemTypeList", itemTypeList);
	}

	/**
	 * 查询按钮点击
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "search")
	public String search_Action(HttpServletRequest request, Model model) throws ServletRequestBindingException {
		// 物件名称
		String itemName = ServletRequestUtils.getStringParameter(request, "itemName");
		// 物件类型
		String itemType = ServletRequestUtils.getStringParameter(request, "itemType");
		// 所属机构
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");

		model.addAttribute("itemName", itemName);
		model.addAttribute("itemType", itemType);
		model.addAttribute("orgId", orgId);

		List<BorrowItem> _list = borrowItemManager.getBorrowItemList(itemName, itemType, orgId);
		model.addAttribute("borrowItemList", _list);

		ReportUtils.initOrgList_Null_Root(orgManager, model);
		// 初始化物件借还状态下拉列表
		initStatusList(model);
		// 初始化物件类型下拉列表
		initItemTypeList(model);

		return "affair/borrowItemList";
	}

	/**
	 * 删除物件信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delBorrowItem_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			borrowItemManager.delBorrowItemByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/borrowItem/list";
	}

	/**
	 * 新增物件初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initBorrowItem_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_Null_Root(orgManager, model);

		// 初始化物件类型下拉列表
		initItemTypeList(model);

		BorrowItem borrowItem = new BorrowItem();
		model.addAttribute("borrowItem", borrowItem);

		return "affair/borrowItemForm";
	}

	/**
	 * 新增/修改 物件信息
	 * 
	 * @param borrowItem
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveBorrowItem_Action(@ModelAttribute("borrowItem") BorrowItem borrowItem, Model model,
			HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {

		if (null != borrowItem && StringUtils.isNotBlank(borrowItem.getOptDate())) {
			borrowItem.setOptDate(DateUtils.transDateFormat(borrowItem.getOptDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}

		if (null == borrowItem.getUuid()) {// 新增操作
			try {
				borrowItemManager.addNewBorrowItem(borrowItem);
			} catch (ServiceException ex) {
				ReportUtils.initOrgList_Null_Root(orgManager, model);

				// 初始化物件类型下拉列表
				initItemTypeList(model);

				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				return "affair/borrowItemForm";
			}
		} else {// 修改操作
			try {
				borrowItemManager.updateBorrowItem(borrowItem);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/borrowItem/list";
	}

	/**
	 * 编辑物件信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editBorrowItem_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		BorrowItem borrowItem = borrowItemManager.getBorrowItemByUuid(id);
		if (null == borrowItem) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/borrowItem/list";
		} else {
			ReportUtils.initOrgList_Null_Root(orgManager, model);

			// 初始化物件类型下拉列表
			initItemTypeList(model);

			borrowItem.setOptDate(DateUtils.transDateFormat(borrowItem.getOptDate(), "yyyyMMdd", "yyyy-MM-dd"));

			model.addAttribute("borrowItem", borrowItem);

			return "affair/borrowItemForm";
		}

	}
}
