/**
 * 
 */
package com.tjhx.web.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.google.common.collect.Lists;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.ItemType;
import com.tjhx.service.info.ItemTypeManager;
import com.tjhx.service.info.SalesContrastByItemManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.SalesContrastVo;
import com.tjhx.vo.Select2Vo;
import com.tjhx.web.BaseController;

/**
 * 销售对比-按商品类别
 */
@Controller
@RequestMapping(value = "/salesContrastByItem")
public class SalesContrastByItemController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private ItemTypeManager itemTypeManager;
	@Resource
	private SalesContrastByItemManager salesContrastByItemManager;

	@RequestMapping(value = "init")
	public String init_Action(Model model) {
		// 初始化页面下拉菜单控件
		initPageControls(model);

		return "report/salesContrastByItemReport";
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
		List<ItemType> _itemTypeList = itemTypeManager.getAllItemType();
		List<Select2Vo> itemTypeList = Lists.newArrayList();
		for (ItemType itemType : _itemTypeList) {
			Select2Vo vo = new Select2Vo(itemType.getItemNo().trim(), itemType.getItemShortName().trim());

			itemTypeList.add(vo);
		}
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("itemTypeList", mapper.toJson(itemTypeList));
	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String optDate2_start = ServletRequestUtils.getStringParameter(request, "optDate2_start");
		String optDate2_end = ServletRequestUtils.getStringParameter(request, "optDate2_end");
		String optDate1_end = ServletRequestUtils.getStringParameter(request, "optDate1_end");
		String optDate1_start = ServletRequestUtils.getStringParameter(request, "optDate1_start");
		String itemType = ServletRequestUtils.getStringParameter(request, "itemType");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");

		model.addAttribute("optDate2_start", optDate2_start);
		model.addAttribute("optDate2_end", optDate2_end);
		model.addAttribute("optDate1_end", optDate1_end);
		model.addAttribute("optDate1_start", optDate1_start);
		model.addAttribute("itemType", itemType);
		model.addAttribute("orgId", orgId);

		// 初始化页面下拉菜单控件
		initPageControls(model);

		List<List<SalesContrastVo>> voList = salesContrastByItemManager.search(
				DateUtils.transDateFormat(optDate1_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate1_end, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_end, "yyyy-MM-dd", "yyyyMMdd"), itemType, orgId);

		model.addAttribute("contrastList", voList);

		return "report/salesContrastByItemReport";
	}

}
