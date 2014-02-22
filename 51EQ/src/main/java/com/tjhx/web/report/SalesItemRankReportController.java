package com.tjhx.web.report;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.info.ItemType;
import com.tjhx.service.info.ItemTypeManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

/**
 * 类别销售信息排名
 */
@Controller
@RequestMapping(value = "/salesItemRankReport")
public class SalesItemRankReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private ItemTypeManager itemTypeManager;

	/**
	 * 框架页面初始化
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = { "init" })
	public String init_Action(Model model) throws ServletRequestBindingException {

		return "report/salesItemRankReport";
	}

	/**
	 * 单日统计页面初始化
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = { "init_tab1" })
	public String init_tab1_Action(Model model) throws ServletRequestBindingException {

		init_common(model);

		return "report/salesItemRankReport_tab1";
	}

	/**
	 * 时间区间统计页面初始化
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = { "init_tab2" })
	public String init_tab2_Action(Model model) throws ServletRequestBindingException {

		init_common(model);

		return "report/salesItemRankReport_tab2";
	}

	private void init_common(Model model) {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);
		// 取得所有商品类别
		getItemTypeList(model);
	}

	/**
	 * 取得所有商品类别
	 * 
	 * @param model
	 */
	private void getItemTypeList(Model model) {
		List<ItemType> _itemTypeList = itemTypeManager.getAllItemType();

		Map<String, String> itemTypeList = new LinkedHashMap<String, String>();

		itemTypeList.put("", "");
		for (ItemType it : _itemTypeList) {
			itemTypeList.put(it.getItemNo(), it.getItemShortName());
		}
		model.addAttribute("itemTypeList", itemTypeList);
	}

	/**
	 * 单日统计页面-查询
	 * 
	 * @return
	 */
	@RequestMapping(value = { "search_day" })
	public String search_day_Action(Model model) {
		init_common(model);

		return "report/salesItemRankReport_tab1";
	}

	/**
	 * 时间区间统计页面-查询
	 * 
	 * @return
	 */
	@RequestMapping(value = { "search_day_interval" })
	public String search_day_interval_Action(Model model) {
		init_common(model);
		return "report/salesItemRankReport_tab2";
	}
}
