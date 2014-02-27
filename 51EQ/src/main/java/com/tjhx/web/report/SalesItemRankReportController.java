package com.tjhx.web.report;

import java.util.ArrayList;
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
import com.tjhx.entity.struct.Organization;
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
		String orderMode = ServletRequestUtils.getStringParameter(request, "orderMode");

		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);
		model.addAttribute("orgId", orgId);
		model.addAttribute("itemTypeNo", itemTypeNo);
		model.addAttribute("orderMode", orderMode);

		String optDateStart = null;
		String optDateEnd = null;
		if (StringUtils.isNotBlank(optDateShow_start)) {
			optDateStart = DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd");
		}
		if (StringUtils.isNotBlank(optDateShow_end)) {
			optDateEnd = DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd");
		}

		List<String> orgNameList = new ArrayList<String>();
		List<List<SalesDayTotalGoods>> goodList = new ArrayList<List<SalesDayTotalGoods>>();
		if (StringUtils.isBlank(orgId)) { // 全机构
			List<Organization> _orgList = orgManager.getSubOrganization();
			for (Organization org : _orgList) {
				// 机构名称
				orgNameList.add(org.getName());

				if (orderMode.equals("qty")) {// 按销量排序
					goodList.add(_search_OrderQty(optDateStart, optDateEnd, org.getId(), itemTypeNo));
				} else {// 按销售额排序
					goodList.add(_search_OrderAmt(optDateStart, optDateEnd, org.getId(), itemTypeNo));
				}
			}
		} else { // 单机构
			// 机构名称
			orgNameList.add(orgId.substring(3));

			if (orderMode.equals("qty")) {// 按销量排序
				goodList.add(_search_OrderQty(optDateStart, optDateEnd, orgId, itemTypeNo));
			} else {// 按销售额排序
				goodList.add(_search_OrderAmt(optDateStart, optDateEnd, orgId, itemTypeNo));
			}
		}

		model.addAttribute("orgNameList", orgNameList);
		model.addAttribute("goodList", goodList);

		return "report/salesItemRankReport";
	}

	private List<SalesDayTotalGoods> _search_OrderQty(String optDateStart, String optDateEnd, String orgId, String itemTypeNo) {
		// 取得指定店指定时间区间内销售信息排名(按类别)--按销量排序
		return salesDayTotalGoodsManager.getSalesItemRankInfoList_OrderQty(optDateStart, optDateEnd, orgId, itemTypeNo);
	}

	private List<SalesDayTotalGoods> _search_OrderAmt(String optDateStart, String optDateEnd, String orgId, String itemTypeNo) {
		// 取得指定店指定时间区间内销售信息排名(按类别)--按销售额排序
		return salesDayTotalGoodsManager.getSalesItemRankInfoList_OrderAmt(optDateStart, optDateEnd, orgId, itemTypeNo);
	}
}
