/**
 * 
 */
package com.tjhx.web.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.ItemType;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.info.ItemTypeManager;
import com.tjhx.service.info.SalesContrastByItemManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.ItemSalesContrastVo;
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
		ReportUtils.initOrgList_NoNRoot(orgManager, model);
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
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		// 初始化页面下拉菜单控件
		initPageControls(model);

		String[] orgIds = ServletRequestUtils.getStringParameters(request, "orgId");
		String optDate2_start = ServletRequestUtils.getStringParameter(request, "optDate2_start");
		String optDate2_end = ServletRequestUtils.getStringParameter(request, "optDate2_end");
		String optDate1_end = ServletRequestUtils.getStringParameter(request, "optDate1_end");
		String optDate1_start = ServletRequestUtils.getStringParameter(request, "optDate1_start");
		String itemType = ServletRequestUtils.getStringParameter(request, "itemType");
		String orderMode = ServletRequestUtils.getStringParameter(request, "orderMode");

		model.addAttribute("optDate2_start", optDate2_start);
		model.addAttribute("optDate2_end", optDate2_end);
		model.addAttribute("optDate1_end", optDate1_end);
		model.addAttribute("optDate1_start", optDate1_start);
		model.addAttribute("itemType", itemType);
		model.addAttribute("orgIdList", Lists.newArrayList(orgIds));
		model.addAttribute("orderMode", orderMode);

		// =============================================================
		// 含有所有类别
		// =============================================================
		List<String> itemNoList = Lists.newArrayList(itemType.split(","));
		if (itemNoList.contains("ALL")) {// 含有所有类别
			List<ItemType> _itemList = itemTypeManager.getAllItemType();

			itemNoList.clear();

			for (ItemType _itemType : _itemList) {
				itemNoList.add(_itemType.getItemNo().trim());
			}
		}

		// =============================================================
		String itemNoArray = StringUtils.join(itemNoList, ",");
		List<List<ItemSalesContrastVo>> voList = salesContrastByItemManager.search(
				DateUtils.transDateFormat(optDate1_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate1_end, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_end, "yyyy-MM-dd", "yyyyMMdd"), itemNoArray, orgIds, orderMode);

		model.addAttribute("contrastList", voList);
		return "report/salesContrastByItemReport";
	}

	/**
	 * 文件导出-明细
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws ServletRequestBindingException
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "export")
	public void cashReportExport_Action(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException, ParsePropertyException, InvalidFormatException, IOException, ParseException {

		String[] orgIds = ServletRequestUtils.getStringParameters(request, "orgId");
		String optDate2_start = ServletRequestUtils.getStringParameter(request, "optDate2_start");
		String optDate2_end = ServletRequestUtils.getStringParameter(request, "optDate2_end");
		String optDate1_end = ServletRequestUtils.getStringParameter(request, "optDate1_end");
		String optDate1_start = ServletRequestUtils.getStringParameter(request, "optDate1_start");
		String itemType = ServletRequestUtils.getStringParameter(request, "itemType");
		String orderMode = ServletRequestUtils.getStringParameter(request, "orderMode");

		// =============================================================
		// 含有所有类别
		// =============================================================
		List<String> itemNoList = Lists.newArrayList(itemType.split(","));
		if (itemNoList.contains("ALL")) {// 含有所有类别
			List<ItemType> _itemList = itemTypeManager.getAllItemType();

			itemNoList.clear();

			for (ItemType _itemType : _itemList) {
				itemNoList.add(_itemType.getItemNo().trim());
			}
		}

		// =============================================================
		String itemNoArray = StringUtils.join(itemNoList, ",");

		String downLoadFileName = salesContrastByItemManager.createItemSalesContrastFile(
				DateUtils.transDateFormat(optDate1_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate1_end, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDate2_end, "yyyy-MM-dd", "yyyyMMdd"), itemNoArray, orgIds, orderMode);

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
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(downLoadFileName.getBytes("utf-8"), "ISO8859-1"));
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

}
