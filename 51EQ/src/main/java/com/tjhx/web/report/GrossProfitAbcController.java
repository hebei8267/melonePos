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
import com.tjhx.entity.info.ItemType;
import com.tjhx.entity.info.SalesDayTotalGoods;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.info.GrossProfitAbcManager;
import com.tjhx.service.info.ItemTypeManager;
import com.tjhx.service.info.SupplierManager;
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
	private SupplierManager supplierManager;
	@Resource
	private GrossProfitAbcManager grossProfitAbcManager;

	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		// 初始化页面下拉菜单控件
		initPageControls(model);

		model.addAttribute("abcParam1", "70");
		model.addAttribute("abcParam2", "20");
		model.addAttribute("abcParam3", "10");

		model.addAttribute("dTable", "0");

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
		// 初始化类型下拉菜单---类别
		List<ItemType> _itemTypeList = itemTypeManager.getAllItemType();
		List<Select2Vo> itemTypeList = Lists.newArrayList();
		itemTypeList.add(new Select2Vo("ALL", "所有类别"));

		for (ItemType itemType : _itemTypeList) {
			Select2Vo vo = new Select2Vo(itemType.getItemNo().trim(), itemType.getItemShortName().trim());

			itemTypeList.add(vo);
		}
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("itemTypeList", mapper.toJson(itemTypeList));

		// 初始化类型下拉菜单---货商
		List<Supplier> _supplierList = supplierManager.getAllSupplierList();
		List<Select2Vo> supplierList = Lists.newArrayList();
		supplierList.add(new Select2Vo("ALL", "所有货商"));

		for (Supplier supplier : _supplierList) {
			Select2Vo vo = new Select2Vo(supplier.getSupplierBwId().trim(), supplier.getName().trim());

			supplierList.add(vo);
		}
		model.addAttribute("supplierList", mapper.toJson(supplierList));
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
		String supplier = ServletRequestUtils.getStringParameter(request, "supplier");

		String dTable = ServletRequestUtils.getStringParameter(request, "dTable");
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
		model.addAttribute("supplier", supplier);
		model.addAttribute("dTable", dTable);

		List<String> itemNoList = Lists.newArrayList(itemType.split(","));
		String itemNoArray = StringUtils.join(itemNoList, ",");

		List<String> supplierBwIdList = Lists.newArrayList(supplier.split(","));
		String supplierBwIdArray = StringUtils.join(supplierBwIdList, ",");

		GrossProfitAbcVo vo = grossProfitAbcManager.getGrossProfitAbcInfo(optDateShow_start, optDateShow_end, orgId, itemNoArray,
				itemSubno, itemName, abcType, abcParam1, abcParam2, abcParam3, supplierBwIdArray, dTable);

		model.addAttribute("vo", vo);

		return "report/grossProfitAbcReport";
	}

	@RequestMapping(value = "abcModal")
	public String abcModal_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String itemSubno = ServletRequestUtils.getStringParameter(request, "itemSubno");
		String goodsName = ServletRequestUtils.getStringParameter(request, "goodsName");
		String optDateShowStart = ServletRequestUtils.getStringParameter(request, "optDateShowStart");
		String optDateShowEnd = ServletRequestUtils.getStringParameter(request, "optDateShowEnd");

		// 取得销售门店信息（根据条码、销售时间段）
		List<SalesDayTotalGoods> _saleOrgList = grossProfitAbcManager.getSaleOrgInfo(itemSubno, optDateShowStart, optDateShowEnd);
		// 取得库存门店信息（根据条码）
		List<SalesDayTotalGoods> _storeOrgList = grossProfitAbcManager.getStoreOrgInfo(itemSubno);

		model.addAttribute("optDateShowStart", optDateShowStart);
		model.addAttribute("optDateShowEnd", optDateShowEnd);
		model.addAttribute("itemSubno", itemSubno);
		model.addAttribute("goodsName", goodsName);
		model.addAttribute("goodList", initSalesDayTotalGoodsList(_saleOrgList, _storeOrgList));

		return "report/grossProfitAbcReport_abcModal";
	}

	private List<SalesDayTotalGoods> initSalesDayTotalGoodsList(List<SalesDayTotalGoods> saleOrgList, List<SalesDayTotalGoods> storeOrgList) {
		List<SalesDayTotalGoods> _tmplist = Lists.newArrayList();

		List<Organization> orgList = orgManager.getOpenSubOrganization();

		// 初始化全部机构的显示信息
		for (Organization org : orgList) {
			SalesDayTotalGoods _s = new SalesDayTotalGoods();
			_s.setOrgId(org.getId());
			_tmplist.add(_s);
		}

		// 初始化销售数量
		for (SalesDayTotalGoods salesGoods : _tmplist) {
			SalesDayTotalGoods goods = _getSalesGoodsByOrgId(saleOrgList, salesGoods.getOrgId());

			if (null != goods) {
				salesGoods.setPosQty(goods.getPosQty());
			}
		}

		// 初始化库存数量
		for (SalesDayTotalGoods salesGoods : _tmplist) {
			SalesDayTotalGoods goods = _getSalesGoodsByOrgId(storeOrgList, salesGoods.getOrgId());

			if (null != goods) {
				salesGoods.setStockQty(goods.getStockQty());
			}
		}

		// 分门店品牌
		List<SalesDayTotalGoods> _eqList = calSalesDayTotalGoodsList_EQ(orgList, _tmplist);
		List<SalesDayTotalGoods> _infancyList = calSalesDayTotalGoodsList_Infancy(orgList, _tmplist);

		SalesDayTotalGoods totalGoods = new SalesDayTotalGoods();
		for (SalesDayTotalGoods salesGoods : _tmplist) {
			calTotalGoods(totalGoods, "合计", salesGoods);
		}

		List<SalesDayTotalGoods> _reList = Lists.newArrayList();
		_reList.add(totalGoods);
		_reList.addAll(_eqList);
		_reList.addAll(_infancyList);

		return _reList;
	}

	private List<SalesDayTotalGoods> calSalesDayTotalGoodsList_Infancy(List<Organization> orgList, List<SalesDayTotalGoods> list) {
		List<SalesDayTotalGoods> _eqList = Lists.newArrayList();

		SalesDayTotalGoods totalGoods = new SalesDayTotalGoods();
		for (SalesDayTotalGoods goods : list) {

			for (Organization _org : orgList) {
				if ("Infancy".equals(_org.getBrand()) && _org.getId().equals(goods.getOrgId())) {
					_eqList.add(goods);
					calTotalGoods(totalGoods, "Infancy", goods);
				}
			}

		}

		_eqList.add(0, totalGoods);

		return _eqList;
	}

	private List<SalesDayTotalGoods> calSalesDayTotalGoodsList_EQ(List<Organization> orgList, List<SalesDayTotalGoods> list) {
		List<SalesDayTotalGoods> _eqList = Lists.newArrayList();

		SalesDayTotalGoods totalGoods = new SalesDayTotalGoods();
		for (SalesDayTotalGoods goods : list) {

			for (Organization _org : orgList) {
				if ("EQ+".equals(_org.getBrand()) && _org.getId().equals(goods.getOrgId())) {
					_eqList.add(goods);
					calTotalGoods(totalGoods, "EQ+", goods);
				}
			}

		}

		_eqList.add(0, totalGoods);

		return _eqList;

	}

	/**
	 * 计算合计
	 * 
	 * @param totalReqBill
	 * @param barcode
	 * @param orgIdName
	 * @param reqBill
	 */
	private void calTotalGoods(SalesDayTotalGoods totalGoods, String orgIdName, SalesDayTotalGoods goods) {
		totalGoods.setOrgId(orgIdName);
		if (null != goods.getPosQty()) {
			totalGoods.setPosQty(totalGoods.getPosQty().add(goods.getPosQty()));
		}
		if (null != goods.getStockQty()) {
			totalGoods.setStockQty(totalGoods.getStockQty().add(goods.getStockQty()));
		}
	}

	/**
	 * 取得机构 库存信息
	 * 
	 * @param goodList
	 * @param orgId
	 * @return
	 */
	private SalesDayTotalGoods _getSalesGoodsByOrgId(List<SalesDayTotalGoods> goodList, String orgId) {
		for (SalesDayTotalGoods goods : goodList) {
			if (goods.getOrgId().equals(orgId)) {
				return goods;
			}
		}

		return null;
	}

	/**
	 * 文件导出-合计
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 * @throws ParseException
	 */
	@RequestMapping(value = "export")
	public void AbcdTableDataExport_Action(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException, ParsePropertyException, InvalidFormatException, IOException, ParseException {

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
		String supplier = ServletRequestUtils.getStringParameter(request, "supplier");
		String dTable = ServletRequestUtils.getStringParameter(request, "dTable");

		List<String> itemNoList = Lists.newArrayList(itemType.split(","));
		String itemNoArray = StringUtils.join(itemNoList, ",");

		List<String> supplierBwIdList = Lists.newArrayList(supplier.split(","));
		String supplierBwIdArray = StringUtils.join(supplierBwIdList, ",");

		String downLoadFileName = grossProfitAbcManager.createAbcdTableFile(optDateShow_start, optDateShow_end, orgId, itemNoArray,
				itemSubno, itemName, abcType, abcParam1, abcParam2, abcParam3, supplierBwIdArray, dTable);

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
}
