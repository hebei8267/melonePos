package com.tjhx.web.info;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.info.ItemType;
import com.tjhx.globals.Constants;
import com.tjhx.service.info.ItemTypeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/itemType")
public class ItemTypeController extends BaseController {
	@Resource
	private ItemTypeManager itemTypeManager;

	/**
	 * 取得商品种类信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String supplierList_Action(Model model, HttpServletRequest request) {
		List<ItemType> itemTypeList = itemTypeManager.getAllItemType();

		model.addAttribute("itemTypeList", itemTypeList);

		return "info/itemTypeList";
	}

	/**
	 * 同步百威商品种类信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "bwDataSynBtn")
	public String bwDataSyn_Action(Model model) {
		// 同步百威商品种类信息
		itemTypeManager.bwDataSyn();

		addInfoMsg(model, "TIP_MSG_ITEM_TYPE_001");

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/itemType/list";
	}
}
