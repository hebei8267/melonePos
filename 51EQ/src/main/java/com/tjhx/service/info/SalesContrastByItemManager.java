package com.tjhx.service.info;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.dao.info.SalesDayTotalItemMyBatisDao;
import com.tjhx.dao.info.StoreDetailMyBatisDao;
import com.tjhx.entity.info.ItemType;
import com.tjhx.entity.info.SalesDayTotalItem;
import com.tjhx.entity.info.StoreDetail;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.ItemSalesContrastVo;

@Service
@Transactional(readOnly = true)
public class SalesContrastByItemManager {
	@Resource
	private SalesDayTotalItemMyBatisDao salesDayTotalItemMyBatisDao;
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private ItemTypeManager itemTypeManager;
	@Resource
	private StoreDetailMyBatisDao storeDetailMyBatisDao;

	private String[] formatOrgIdArray(String[] orgIds) {
		String[] _orgIds = new String[orgIds.length];
		for (int i = 0; i < _orgIds.length; i++) {
			_orgIds[i] = "'" + orgIds[i] + "'";
		}
		return _orgIds;

	}

	/**
	 * 销售数据1设置
	 * 
	 * @param list1
	 * @return
	 */
	private void copyList1Value(List<ItemSalesContrastVo> voList, String optDate1Start, String optDate1End, String[] orgIds,
			String itemNoArray) {

		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", optDate1Start);
		param.put("optDateEnd", optDate1End);
		param.put("orgId", StringUtils.join(formatOrgIdArray(orgIds), ","));
		param.put("itemType", itemNoArray);
		List<SalesDayTotalItem> list1 = salesDayTotalItemMyBatisDao.getContrastList(param);

		for (ItemSalesContrastVo vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				SalesDayTotalItem value = list1.get(_index);
				// 实销数量1
				vo.setSaleRqty1(value.getSaleRqty());
				// 实销金额1
				vo.setSaleRamt1(value.getSaleRamt());
				if (value.getSaleRqty().compareTo(BigDecimal.ZERO) == 1) {
					// 实销均价1
					vo.setSalePrice1(value.getSaleRamt().divide(value.getSaleRqty(), 2, BigDecimal.ROUND_UP));
				}
			}
		}

	}

	/**
	 * 取得销售数据对比信息
	 * 
	 * @param optDate1Start
	 * @param optDate1End
	 * @param optDate2Start
	 * @param optDate2End
	 * @param itemNoArray
	 * @param orgId
	 * @param orderMode
	 * @return
	 */
	public List<List<ItemSalesContrastVo>> search(String optDate1Start, String optDate1End, String optDate2Start,
			String optDate2End, String itemNoArray, String[] orgIds, String orderMode) {

		List<ItemSalesContrastVo> voList = initBlankVoList(itemNoArray, Lists.newArrayList(orgIds));
		copyList1Value(voList, optDate1Start, optDate1End, orgIds, itemNoArray);
		copyList2Value(voList, optDate2Start, optDate2End, orgIds, itemNoArray);
		copyStore1Value(voList, optDate1End, orgIds, itemNoArray);
		copyStore2Value(voList, optDate2End, orgIds, itemNoArray);
		return formatVoList(voList, orgIds, itemNoArray, orderMode);
	}

	/**
	 * @param optDate1Start
	 * @param optDate1End
	 * @param optDate2Start
	 * @param optDate2End
	 * @param itemNoArray
	 * @param orgIds
	 * @param orderMode
	 * @return
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public String createItemSalesContrastFile(String optDate1Start, String optDate1End, String optDate2Start, String optDate2End,
			String itemNoArray, String[] orgIds, String orderMode) throws ParsePropertyException, InvalidFormatException, IOException {

		List<List<ItemSalesContrastVo>> list = search(optDate1Start, optDate1End, optDate2Start, optDate2End, itemNoArray,
				orgIds, orderMode);
		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("optDate1Start", optDate1Start);
		map.put("optDate1End", optDate1End);
		map.put("optDate2Start", optDate2Start);
		map.put("optDate2End", optDate2End);
		map.put("itemSalesContrastList", list);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_ITEM_SALES_CONTRAST, map, tmpFilePath);

		return tmpFileName;
	}

	private final static String XML_CONFIG_ITEM_SALES_CONTRAST = "/excel/Item_Sales_Contrast_Template.xls";

	/**
	 * 库存数据2设置
	 * 
	 * @param voList
	 * @param optDate1End
	 * @param orgId
	 * @param itemNoArray
	 */
	private void copyStore1Value(List<ItemSalesContrastVo> voList, String optDate1End, String[] orgIds, String itemNoArray) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateEnd", optDate1End);
		param.put("orgId", StringUtils.join(formatOrgIdArray(orgIds), ","));
		param.put("itemType", itemNoArray);

		List<StoreDetail> sList = storeDetailMyBatisDao.getItemContrastStoreList(param);

		for (ItemSalesContrastVo vo : voList) {
			int _index = sList.indexOf(vo);
			if (-1 != _index) {
				StoreDetail sd = sList.get(_index);
				// 库存数量1
				vo.setStockTotalQty1(sd.getStockQty());
				// 库存金额1
				vo.setStockTotalAmt1(sd.getStockAmt());
			}
		}
	}

	/**
	 * 库存数据2设置
	 * 
	 * @param voList
	 * @param optDate2End
	 * @param orgId
	 * @param itemNoArray
	 */
	private void copyStore2Value(List<ItemSalesContrastVo> voList, String optDate2End, String[] orgIds, String itemNoArray) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateEnd", optDate2End);
		param.put("orgId", StringUtils.join(formatOrgIdArray(orgIds), ","));
		param.put("itemType", itemNoArray);

		List<StoreDetail> sList = storeDetailMyBatisDao.getItemContrastStoreList(param);

		for (ItemSalesContrastVo vo : voList) {
			int _index = sList.indexOf(vo);
			if (-1 != _index) {
				StoreDetail sd = sList.get(_index);
				// 库存数量2
				vo.setStockTotalQty2(sd.getStockQty());
				// 库存金额2
				vo.setStockTotalAmt2(sd.getStockAmt());
			}
		}
	}

	/**
	 * 销售数据2设置
	 * 
	 * @param voList
	 * @param optDate1Start
	 * @param optDate1End
	 * @param orgId
	 * @param itemNoArray
	 */
	private void copyList2Value(List<ItemSalesContrastVo> voList, String optDate2Start, String optDate2End, String[] orgIds,
			String itemNoArray) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", optDate2Start);
		param.put("optDateEnd", optDate2End);
		param.put("orgId", StringUtils.join(formatOrgIdArray(orgIds), ","));
		param.put("itemType", itemNoArray);
		List<SalesDayTotalItem> list1 = salesDayTotalItemMyBatisDao.getContrastList(param);

		for (ItemSalesContrastVo vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				SalesDayTotalItem value = list1.get(_index);
				// 实销数量2
				vo.setSaleRqty2(value.getSaleRqty());
				// 实销金额2
				vo.setSaleRamt2(value.getSaleRamt());
				if (value.getSaleRqty().compareTo(BigDecimal.ZERO) == 1) {
					// 实销均价2
					vo.setSalePrice2(value.getSaleRamt().divide(value.getSaleRqty(), 2, BigDecimal.ROUND_UP));
				}
				// 销售额增长/下降率
				if (vo.getSaleRamt1().compareTo(BigDecimal.ZERO) == 1) {
					BigDecimal tmp = vo.getSaleRamt2().subtract(vo.getSaleRamt1());
					tmp = tmp.divide(vo.getSaleRamt1(), 2, BigDecimal.ROUND_UP);
					tmp = tmp.multiply(new BigDecimal("100"));
					vo.setSalesContrast(tmp);
				}

			}
		}

	}

	/**
	 * 初始化空白销售信息对比VO对象列表
	 * 
	 * @param itemNoArray
	 * @return
	 */
	private List<ItemSalesContrastVo> initBlankVoList(String itemNoArray, List<String> orgIdList) {

		List<ItemSalesContrastVo> voList = Lists.newArrayList();

		List<String> itemNoList = Lists.newArrayList(itemNoArray.split(","));

		List<Organization> orgList = Lists.newArrayList();
		for (String orgId : orgIdList) {
			if (StringUtils.isNotBlank(orgId)) {
				Organization org = orgManager.getOrganizationByOrgIdInCache(orgId);

				orgList.add(org);
			}
		}

		for (Organization org : orgList) {
			for (String itemNo : itemNoList) {
				ItemSalesContrastVo vo = new ItemSalesContrastVo();
				// 机构编号
				vo.setOrgId(org.getId());
				// 机构名称
				vo.setOrgName(org.getName());
				// 类型编号
				vo.setItemClsNo(itemNo);
				// 类型名称
				ItemType itemType = itemTypeManager.getByItemNo(itemNo);
				if (null != itemType) {
					vo.setItemName(itemType.getItemShortName());
				}

				voList.add(vo);
			}
		}
		return voList;
	}

	/**
	 * 校验是否有略过的机构空行
	 * 
	 * @param tmpList
	 * @return
	 */
	private List<List<ItemSalesContrastVo>> formatVoList(List<ItemSalesContrastVo> voList, String[] orgIds, String itemNoArray,
			String orderMode) {

		List<List<ItemSalesContrastVo>> _list = Lists.newArrayList();
		List<ItemSalesContrastVo> _subList = null;
		String tmpOrgId = null;
		for (ItemSalesContrastVo vo : voList) {
			if (null == tmpOrgId || !tmpOrgId.equals(vo.getOrgId())) {
				tmpOrgId = vo.getOrgId();
				_subList = Lists.newArrayList();

				_list.add(_subList);
			}
			_subList.add(vo);
		}

		for (List<ItemSalesContrastVo> _voList : _list) {
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(_voList, new ItemSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(_voList, new ItemSaleRqtyComparator());
			}

		}

		// 计算合计
		if (ArrayUtils.isNotEmpty(orgIds) && orgIds.length > 1) {
			List<ItemSalesContrastVo> voTotalList = calTotal(_list, itemNoArray);
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(voTotalList, new ItemSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(voTotalList, new ItemSaleRqtyComparator());
			}
			_list.add(0, voTotalList);
		}
		return _list;
	}

	/**
	 * 计算合计
	 * 
	 * @param voList
	 * @return
	 */
	private List<ItemSalesContrastVo> calTotal(List<List<ItemSalesContrastVo>> voList, String itemNoArray) {
		List<String> itemNoList = Lists.newArrayList(itemNoArray.split(","));

		List<ItemSalesContrastVo> _list = Lists.newArrayList();

		for (String itemNo : itemNoList) {
			ItemSalesContrastVo vo = new ItemSalesContrastVo();
			// 机构名称
			vo.setOrgName("合计");
			// 类型编号
			vo.setItemClsNo(itemNo);
			_list.add(vo);
		}
		// ======================================================================================================

		for (List<ItemSalesContrastVo> salesContrastVo : voList) {

			for (ItemSalesContrastVo vo : salesContrastVo) {
				int _index = _list.indexOf(vo);
				if (-1 != _index) {
					ItemSalesContrastVo value = _list.get(_index);

					// 类型名称
					value.setItemName(vo.getItemName());

					// 实销数量1
					value.setSaleRqty1(value.getSaleRqty1().add(vo.getSaleRqty1()));
					// 实销金额1
					value.setSaleRamt1(value.getSaleRamt1().add(vo.getSaleRamt1()));

					// 实销数量2
					value.setSaleRqty2(value.getSaleRqty2().add(vo.getSaleRqty2()));
					// 实销金额2
					value.setSaleRamt2(value.getSaleRamt2().add(vo.getSaleRamt2()));

					// 库存数量1
					value.setStockTotalQty1(value.getStockTotalQty1().add(vo.getStockTotalQty1()));
					// 库存数量2
					value.setStockTotalQty2(value.getStockTotalQty2().add(vo.getStockTotalQty2()));
				
					// 库存金额1
					value.setStockTotalAmt1(value.getStockTotalAmt1().add(vo.getStockTotalAmt1()));
					// 库存金额2
					value.setStockTotalAmt2(value.getStockTotalAmt2().add(vo.getStockTotalAmt2()));
				
				}

			}

		}

		for (ItemSalesContrastVo _vo : _list) {
			if (_vo.getSaleRqty1().compareTo(BigDecimal.ZERO) == 1) {
				// 实销均价1
				_vo.setSalePrice1(_vo.getSaleRamt1().divide(_vo.getSaleRqty1(), 2, BigDecimal.ROUND_UP));
			}
			if (_vo.getSaleRqty2().compareTo(BigDecimal.ZERO) == 1) {
				// 实销均价2
				_vo.setSalePrice2(_vo.getSaleRamt2().divide(_vo.getSaleRqty2(), 2, BigDecimal.ROUND_UP));
			}

			if (_vo.getSaleRamt1().compareTo(BigDecimal.ZERO) == 1) {
				// 销售额增长/下降率
				BigDecimal tmp = _vo.getSaleRamt2().subtract(_vo.getSaleRamt1());
				tmp = tmp.divide(_vo.getSaleRamt1(), 2, BigDecimal.ROUND_UP);
				tmp = tmp.multiply(new BigDecimal("100"));
				_vo.setSalesContrast(tmp);
			}
		}
		return _list;
	}
}

class ItemSaleRqtyComparator implements Comparator<ItemSalesContrastVo> {

	@Override
	public int compare(ItemSalesContrastVo o1, ItemSalesContrastVo o2) {
		BigDecimal v1 = o1.getSaleRqty2();
		BigDecimal v2 = o2.getSaleRqty2();

		if (v1.intValue() == v2.intValue()) {
			return 0;
		} else {
			return v1.intValue() > v2.intValue() ? -1 : 1;
		}
	}

}

class ItemSaleRamtComparator implements Comparator<ItemSalesContrastVo> {

	@Override
	public int compare(ItemSalesContrastVo o1, ItemSalesContrastVo o2) {
		BigDecimal v1 = o1.getSaleRamt2();
		BigDecimal v2 = o2.getSaleRamt2();

		if (v1.intValue() == v2.intValue()) {
			return 0;
		} else {
			return v1.intValue() > v2.intValue() ? -1 : 1;
		}
	}

}
