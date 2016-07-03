package com.tjhx.web.report;

import java.text.ParseException;
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
import com.tjhx.entity.info.ItemType;
import com.tjhx.service.info.GrossProfitAbcManager;
import com.tjhx.service.info.ItemTypeManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.GrossProfitAbcVo;
import com.tjhx.vo.Select2Vo;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/grossProfitAbc")
public class GrossProfitAbcController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private ItemTypeManager itemTypeManager;
	@Resource
	private GrossProfitAbcManager grossProfitAbcManager;

	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		// 初始化页面下拉菜单控件
		initPageControls(model);

		model.addAttribute("abcParam1", "70");
		model.addAttribute("abcParam2", "20");
		model.addAttribute("abcParam3", "10");

		return "report/grossProfitAbcReport";
	}

	/**
	 * 初始化页面下拉菜单控件
	 * 
	 * @param model
	 */
	private void initPageControls(Model model) {
		// 初始化机构下拉菜单
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);
		// 初始化类型下拉菜单
		List<ItemType> _itemTypeList = itemTypeManager.getAllItemType();
		List<Select2Vo> itemTypeList = Lists.newArrayList();
		itemTypeList.add(new Select2Vo("ALL", "所有类别"));

		for (ItemType itemType : _itemTypeList) {
			Select2Vo vo = new Select2Vo(itemType.getItemNo().trim(), itemType.getItemShortName().trim());

			itemTypeList.add(vo);
		}
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("itemTypeList", mapper.toJson(itemTypeList));
	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException, ParseException {

		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");

		String itemType = ServletRequestUtils.getStringParameter(request, "itemType");
		String itemSubno = ServletRequestUtils.getStringParameter(request, "itemSubno");
		String itemName = ServletRequestUtils.getStringParameter(request, "itemName");
		String abcType = ServletRequestUtils.getStringParameter(request, "abcType");
		int abcParam1 = ServletRequestUtils.getIntParameter(request, "abcParam1");
		int abcParam2 = ServletRequestUtils.getIntParameter(request, "abcParam2");
		int abcParam3 = ServletRequestUtils.getIntParameter(request, "abcParam3");

		// 初始化页面下拉菜单控件
		initPageControls(model);

		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);
		model.addAttribute("orgId", orgId);
		model.addAttribute("itemType", itemType);
		model.addAttribute("itemSubno", itemSubno);
		model.addAttribute("itemName", itemName);
		model.addAttribute("abcType", abcType);
		model.addAttribute("abcParam1", abcParam1);
		model.addAttribute("abcParam2", abcParam2);
		model.addAttribute("abcParam3", abcParam3);

		List<String> itemNoList = Lists.newArrayList(itemType.split(","));
		String itemNoArray = StringUtils.join(itemNoList, ",");
		GrossProfitAbcVo vo = grossProfitAbcManager.getGrossProfitAbcInfo(optDateShow_start, optDateShow_end, orgId, itemNoArray, itemSubno,
				itemName, abcType, abcParam1, abcParam2, abcParam3);

		model.addAttribute("vo", vo);

		return "report/grossProfitAbcReport";
	}
}
