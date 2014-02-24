package com.tjhx.web.report;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.ItemType;
import com.tjhx.entity.info.SalesDayTotalGoods;
import com.tjhx.service.info.ItemTypeManager;
import com.tjhx.service.info.SalesDayTotalGoodsManager;
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
	@Resource
	private SalesDayTotalGoodsManager salesDayTotalGoodsManager;

	/**
	 * 框架页面初始化
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = { "init" })
	public String init_Action(Model model) throws ServletRequestBindingException {
		init_common(model);

		return "report/salesItemRankReport";
	}

	/**
	 * 页面初始化-公共
	 * 
	 * @param model
	 */
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
	 * 统计页面-查询
	 * 
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = { "search" })
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		init_common(model);

		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String itemTypeNo = ServletRequestUtils.getStringParameter(request, "itemTypeNo");

		model.addAttribute("optDateShow_start", optDateShow_end);
		model.addAttribute("optDateShow_end", optDateShow_end);
		model.addAttribute("orgId", orgId);
		model.addAttribute("itemTypeNo", itemTypeNo);

		String optDateStart = null;
		String optDateEnd = null;
		if (StringUtils.isNotBlank(optDateShow_start)) {
			optDateStart = DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd");
		}
		if (StringUtils.isNotBlank(optDateShow_end)) {
			optDateEnd = DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd");
		}

		// TODO????? 全机构
		List<SalesDayTotalGoods> goodList = salesDayTotalGoodsManager.getSalesItemRankInfoList_OrderQty(optDateStart, optDateEnd,
				orgId, itemTypeNo);

		model.addAttribute("goodList", goodList);
		return "report/salesItemRankReport";
	}

}
