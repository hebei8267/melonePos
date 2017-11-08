/**
 * 
 */
package com.tjhx.service.info;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.dao.info.SalesDayTotalSupMyBatisDao;
import com.tjhx.dao.info.StoreDetailMyBatisDao;
import com.tjhx.entity.info.SalesDayTotalSup;
import com.tjhx.entity.info.StoreDetail;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.SupplierSalesContrastVo;

@Service
@Transactional(readOnly = true)
public class SalesContrastBySupplierManager {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SupplierManager supplierManager;
	@Resource
	private StoreDetailMyBatisDao storeDetailMyBatisDao;
	@Resource
	private SalesDayTotalSupMyBatisDao salesDayTotalSupMyBatisDao;

	/**
	 * @param voList
	 * @param supplierArray
	 * @return
	 */
	public List<SupplierSalesContrastVo> calTotal(List<List<SupplierSalesContrastVo>> voList, String supplierArray, String orgName) {
		List<String> supplierNoList = Lists.newArrayList(supplierArray.split(","));

		List<SupplierSalesContrastVo> _list = Lists.newArrayList();

		for (String supplierNo : supplierNoList) {
			SupplierSalesContrastVo vo = new SupplierSalesContrastVo();
			// 机构名称
			vo.setOrgName(orgName);
			// 类型编号
			vo.setSupplierNo(supplierNo);
			_list.add(vo);
		}
		// ======================================================================================================

		for (List<SupplierSalesContrastVo> salesContrastVo : voList) {

			for (SupplierSalesContrastVo vo : salesContrastVo) {
				int _index = _list.indexOf(vo);
				if (-1 != _index) {
					SupplierSalesContrastVo value = _list.get(_index);

					// 类型名称
					value.setSupplierName(vo.getSupplierName());

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

		for (SupplierSalesContrastVo _vo : _list) {
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

	/**
	 * 取得销售数据对比信息
	 * 
	 * @param transDateFormat
	 * @param transDateFormat2
	 * @param transDateFormat3
	 * @param transDateFormat4
	 * @param supplierArray
	 * @param orgId
	 * @param orderMode
	 * @return
	 */
	public List<List<SupplierSalesContrastVo>> search(String optDate1Start, String optDate1End, String optDate2Start, String optDate2End,
			String supplierArray, String[] orgIds, String orderMode) {
		List<SupplierSalesContrastVo> voList = initBlankVoList(supplierArray, Lists.newArrayList(orgIds));
		copyList1Value(voList, optDate1Start, optDate1End, orgIds, supplierArray);
		copyList2Value(voList, optDate2Start, optDate2End, orgIds, supplierArray);
		copyStore1Value(voList, optDate1End, orgIds, supplierArray);
		copyStore2Value(voList, optDate2End, orgIds, supplierArray);
		return formatVoList(voList, orgIds, supplierArray, orderMode);
	}

	private String getOrgBrand(List<Organization> orgList, String orgId) {
		for (Organization org : orgList) {
			if (org.getId().equals(orgId)) {
				return org.getBrand();
			}
		}
		return null;
	}

	/**
	 * @param voList
	 * @return
	 */
	private List<List<SupplierSalesContrastVo>> formatVoList(List<SupplierSalesContrastVo> voList, String[] orgIds, String supplierArray,
			String orderMode) {
		List<Organization> orgList = orgManager.getOpenSubOrganization();

		List<List<SupplierSalesContrastVo>> _list_EQ = Lists.newArrayList();
		List<List<SupplierSalesContrastVo>> _list_Infancy = Lists.newArrayList();
		List<List<SupplierSalesContrastVo>> _list_Am = Lists.newArrayList();

		List<SupplierSalesContrastVo> _subList = null;
		String tmpOrgId = null;
		for (SupplierSalesContrastVo vo : voList) {
			String _brand = getOrgBrand(orgList, vo.getOrgId());

			if ("EQ+".equals(_brand)) {
				if (null == tmpOrgId || !tmpOrgId.equals(vo.getOrgId())) {
					tmpOrgId = vo.getOrgId();
					_subList = Lists.newArrayList();

					_list_EQ.add(_subList);
				}
				_subList.add(vo);
			} else if ("Infancy".equals(_brand)) {
				if (null == tmpOrgId || !tmpOrgId.equals(vo.getOrgId())) {
					tmpOrgId = vo.getOrgId();
					_subList = Lists.newArrayList();

					_list_Infancy.add(_subList);
				}
				_subList.add(vo);
			} else if ("AmpleLife".equals(_brand)) {
				if (null == tmpOrgId || !tmpOrgId.equals(vo.getOrgId())) {
					tmpOrgId = vo.getOrgId();
					_subList = Lists.newArrayList();

					_list_Am.add(_subList);
				}
				_subList.add(vo);
			}

		}
		// 排序-EQ+
		for (List<SupplierSalesContrastVo> _voList : _list_EQ) {
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(_voList, new SupSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(_voList, new SupSaleRqtyComparator());
			}
		}

		// 排序-Infancy
		for (List<SupplierSalesContrastVo> _voList : _list_Infancy) {
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(_voList, new SupSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(_voList, new SupSaleRqtyComparator());
			}
		}
		// 排序-AmpleLife
		for (List<SupplierSalesContrastVo> _voList : _list_Am) {
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(_voList, new SupSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(_voList, new SupSaleRqtyComparator());
			}
		}

		List<List<SupplierSalesContrastVo>> _list = Lists.newArrayList();

		if (ArrayUtils.isNotEmpty(orgIds) && orgIds.length > 1) {

			// 计算合计-EQ+
			List<SupplierSalesContrastVo> voTotalList_EQ = calTotal(_list_EQ, supplierArray, "EQ+");
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(voTotalList_EQ, new SupSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(voTotalList_EQ, new SupSaleRqtyComparator());
			}

			// 计算合计-Infancy
			List<SupplierSalesContrastVo> voTotalList_In = calTotal(_list_Infancy, supplierArray, "Infancy");
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(voTotalList_In, new SupSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(voTotalList_In, new SupSaleRqtyComparator());
			}

			// 计算合计-Infancy
			List<SupplierSalesContrastVo> voTotalList_Am = calTotal(_list_Am, supplierArray, "AmpleLife");
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(voTotalList_Am, new SupSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(voTotalList_Am, new SupSaleRqtyComparator());
			}

			_list.addAll(_list_EQ);
			_list.addAll(_list_Infancy);
			_list.addAll(_list_Am);

			// 计算合计
			List<SupplierSalesContrastVo> voTotalList = calTotal(_list, supplierArray, "合计");
			if ("amt".equals(orderMode)) {// 排序方式-销售额
				Collections.sort(voTotalList, new SupSaleRamtComparator());
			} else {// 排序方式-销售量
				Collections.sort(voTotalList, new SupSaleRqtyComparator());
			}

			_list.add(0, voTotalList_EQ);
			_list.add(0, voTotalList_In);
			_list.add(0, voTotalList_Am);
			_list.add(0, voTotalList);
		}
		return _list;
	}

	/**
	 * @param voList
	 * @param optDate2End
	 * @param orgId
	 * @param supplierArray
	 */
	private void copyStore2Value(List<SupplierSalesContrastVo> voList, String optDate2End, String[] orgIds, String supplierArray) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateEnd", optDate2End);
		param.put("orgId", StringUtils.join(formatOrgIdArray(orgIds), ","));
		param.put("supplierArray", supplierArray);

		List<StoreDetail> sList = storeDetailMyBatisDao.getSupplierContrastStoreList(param);

		for (SupplierSalesContrastVo vo : voList) {
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
	 * @param voList
	 * @param optDate1End
	 * @param orgId
	 * @param supplierArray
	 */
	private void copyStore1Value(List<SupplierSalesContrastVo> voList, String optDate1End, String[] orgIds, String supplierArray) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateEnd", optDate1End);
		param.put("orgId", StringUtils.join(formatOrgIdArray(orgIds), ","));
		param.put("supplierArray", supplierArray);

		List<StoreDetail> sList = storeDetailMyBatisDao.getSupplierContrastStoreList(param);

		for (SupplierSalesContrastVo vo : voList) {
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
	 * @param voList
	 * @param optDate2Start
	 * @param optDate2End
	 * @param orgId
	 * @param supplierArray
	 */
	private void copyList2Value(List<SupplierSalesContrastVo> voList, String optDate2Start, String optDate2End, String[] orgIds,
			String supplierArray) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", optDate2Start);
		param.put("optDateEnd", optDate2End);
		param.put("orgId", StringUtils.join(formatOrgIdArray(orgIds), ","));
		param.put("supplierBwId", supplierArray);
		List<SalesDayTotalSup> list1 = salesDayTotalSupMyBatisDao.getContrastList(param);

		for (SupplierSalesContrastVo vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				SalesDayTotalSup value = list1.get(_index);
				// 实销数量2
				vo.setSaleRqty2(value.getSaleQty());
				// 实销金额2
				vo.setSaleRamt2(value.getSaleAmt());
				if (value.getSaleQty().compareTo(BigDecimal.ZERO) == 1) {
					// 实销均价2
					vo.setSalePrice2(value.getSaleAmt().divide(value.getSaleQty(), 2, BigDecimal.ROUND_UP));
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

	private String[] formatOrgIdArray(String[] orgIds) {
		String[] _orgIds = new String[orgIds.length];
		for (int i = 0; i < _orgIds.length; i++) {
			_orgIds[i] = "'" + orgIds[i] + "'";
		}
		return _orgIds;

	}

	/**
	 * @param voList
	 * @param optDate1Start
	 * @param optDate1End
	 * @param orgId
	 * @param supplierArray
	 */
	private void copyList1Value(List<SupplierSalesContrastVo> voList, String optDate1Start, String optDate1End, String[] orgIds,
			String supplierArray) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", optDate1Start);
		param.put("optDateEnd", optDate1End);
		param.put("orgId", StringUtils.join(formatOrgIdArray(orgIds), ","));
		param.put("supplierBwId", supplierArray);

		List<SalesDayTotalSup> list1 = salesDayTotalSupMyBatisDao.getContrastList(param);

		for (SupplierSalesContrastVo vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				SalesDayTotalSup value = list1.get(_index);
				// 实销数量1
				vo.setSaleRqty1(value.getSaleQty());
				// 实销金额1
				vo.setSaleRamt1(value.getSaleAmt());
				if (value.getSaleQty().compareTo(BigDecimal.ZERO) == 1) {
					// 实销均价1
					vo.setSalePrice1(value.getSaleAmt().divide(value.getSaleQty(), 2, BigDecimal.ROUND_UP));
				}
			}
		}

	}

	/**
	 * 初始化空白销售信息对比VO对象列表
	 * 
	 * @param supplierArray
	 * @param orgId
	 * @return
	 */
	private List<SupplierSalesContrastVo> initBlankVoList(String supplierArray, List<String> orgIdList) {
		List<SupplierSalesContrastVo> voList = Lists.newArrayList();

		List<String> supplierNoList = Lists.newArrayList(supplierArray.split(","));

		List<Organization> orgList = Lists.newArrayList();
		for (String orgId : orgIdList) {
			if (StringUtils.isNotBlank(orgId)) {
				Organization org = orgManager.getOrganizationByOrgIdInCache(orgId);

				orgList.add(org);
			}
		}

		for (Organization org : orgList) {
			for (String supplierNo : supplierNoList) {
				SupplierSalesContrastVo vo = new SupplierSalesContrastVo();
				// 机构编号
				vo.setOrgId(org.getId());
				// 机构名称
				vo.setOrgName(org.getName());
				// 货商编号
				vo.setSupplierNo(supplierNo);
				// 货商名称
				Supplier supplier = supplierManager.getSupplierByBwId(supplierNo);
				if (null != supplier) {
					vo.setSupplierName(supplier.getName());
				}

				voList.add(vo);
			}
		}

		return voList;
	}
}

class SupSaleRamtComparator implements Comparator<SupplierSalesContrastVo> {

	@Override
	public int compare(SupplierSalesContrastVo o1, SupplierSalesContrastVo o2) {
		BigDecimal v1 = o1.getSaleRamt2();
		BigDecimal v2 = o2.getSaleRamt2();

		if (v1.intValue() == v2.intValue()) {
			return 0;
		} else {
			return v1.intValue() > v2.intValue() ? -1 : 1;
		}
	}

}

class SupSaleRqtyComparator implements Comparator<SupplierSalesContrastVo> {
	@Override
	public int compare(SupplierSalesContrastVo o1, SupplierSalesContrastVo o2) {
		BigDecimal v1 = o1.getSaleRqty2();
		BigDecimal v2 = o2.getSaleRqty2();

		if (v1.intValue() == v2.intValue()) {
			return 0;
		} else {
			return v1.intValue() > v2.intValue() ? -1 : 1;
		}
	}
}