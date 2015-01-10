/**
 * 
 */
package com.tjhx.service.info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesOrdersDayTotalJpaDao;
import com.tjhx.dao.info.SalesOrdersDayTotalMyBatisDao;
import com.tjhx.daobw.DaySalesOrdersTotalMyBatisDao;
import com.tjhx.entity.info.SalesOrdersDayTotal;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.OrgSalesQuota;

@Service
@Transactional(readOnly = true)
public class SalesOrdersDayTotalManager {
	@Resource
	private DaySalesOrdersTotalMyBatisDao daySalesOrdersTotalMyBatisDao;
	@Resource
	private SalesOrdersDayTotalMyBatisDao salesOrdersDayTotalMyBatisDao;
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesOrdersDayTotalJpaDao salesOrdersDayTotalJpaDao;

	/**
	 * 取得门店日销售计算-重计算天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurrentDateShortStr();

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getSalesDayTotalItemDays(); i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyyMMdd"));
		}

		return _optDateList;
	}

	/**
	 * 销售单日汇总
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void bwDataSyn() throws ParseException {
		// 取得销售单日汇总-重计算天数
		List<String> optDateList = calOptDate();
		for (String _optDate : optDateList) {
			String _endDate = DateUtils.getNextDateFormatDate(_optDate, 1, "yyyyMMdd");

			// 删除指定日期的所有门店销售单日汇总信息
			salesOrdersDayTotalMyBatisDao.delSalesOrdersDayTotalInfo(_optDate);

			List<Organization> _orgList = orgManager.getSubOrganization();

			for (Organization org : _orgList) {
				Map<String, String> paramMap = Maps.newHashMap();
				paramMap.put("startDate", DateUtils.transDateFormat(_optDate, "yyyyMMdd", "yyyy-MM-dd"));
				paramMap.put("endDate", DateUtils.transDateFormat(_endDate, "yyyyMMdd", "yyyy-MM-dd"));
				paramMap.put("branchNo", org.getBwBranchNo());
				List<SalesOrdersDayTotal> _list = daySalesOrdersTotalMyBatisDao.getSalesOrdersDayTotalList(paramMap);

				calTotal(_optDate, org, _list);
			}

		}

	}

	/**
	 * @param _optDate
	 * @param org
	 * @param _list
	 */
	@Transactional(readOnly = false)
	private void calTotal(String optDate, Organization org, List<SalesOrdersDayTotal> _list) {
		SalesOrdersDayTotal _res = new SalesOrdersDayTotal();

		// 机构编号
		_res.setOrgId(org.getId());
		// 机构资金编号
		_res.setBranchNo(org.getBwBranchNo());
		// 日期-年月
		_res.setOptDateYM(DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyyMM"));
		// 日期-年
		_res.setOptDateY(DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy"));
		// 日期
		_res.setOptDate(optDate);

		for (SalesOrdersDayTotal salesOrdersDayTotal : _list) {
			// 销售金额
			_res.setSaleAmt(_res.getSaleAmt().add(salesOrdersDayTotal.getSaleAmt()));
			// 销售单数量
			_res.setOrdersNum(_res.getOrdersNum() + salesOrdersDayTotal.getOrdersNum());
			// 销售单均价
			_res.setOrdersAvgPrice(_res.getOrdersAvgPrice().add(salesOrdersDayTotal.getOrdersAvgPrice()));
		}

		salesOrdersDayTotalJpaDao.save(_res);
	}

	/**
	 * 取得门店销售指标
	 * 
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 * @param preStartOptDate
	 * @param preEndOptDate
	 * @param orgId
	 * @param orderMode
	 */
	public List<OrgSalesQuota> getContrastList(String currentStartOptDate, String currentEndOptDate, String preStartOptDate,
			String preEndOptDate, String orgId, String orderMode) {

		List<OrgSalesQuota> voList = initBlankVoList(orgId);
		copyList1Value(voList, preStartOptDate, preEndOptDate, orgId);
		copyList2Value(voList, currentStartOptDate, currentEndOptDate, orgId);

		return formatVoList(voList, orgId, orderMode);
	}

	/**
	 * @param voList
	 * @param orgId
	 * @param orderMode
	 * @return
	 */
	private List<OrgSalesQuota> formatVoList(List<OrgSalesQuota> voList, String orgId, String orderMode) {

		if ("amt".equals(orderMode)) {// 排序方式-客单价
			Collections.sort(voList, new SalesQuotaPriceComparator());
		} else {// 排序方式-客单数
			Collections.sort(voList, new SaleQuotaOrdersNumComparator());
		}

		if (StringUtils.isBlank(orgId)) {
			calTotal(voList);// 计算合计
		}

		return voList;
	}

	/**
	 * 计算合计
	 * 
	 * @param voList
	 * @return
	 */
	private void calTotal(List<OrgSalesQuota> voList) {
		OrgSalesQuota vo = new OrgSalesQuota();

		vo.setOrgName("合计");

		for (OrgSalesQuota qVo : voList) {
			// 销售金额1
			vo.setSaleAmt1(vo.getSaleAmt1().add(qVo.getSaleAmt1()));
			// 销售单数量1
			vo.setOrdersNum1(vo.getOrdersNum1() + qVo.getOrdersNum1());

			// 销售金额2
			vo.setSaleAmt2(vo.getSaleAmt2().add(qVo.getSaleAmt2()));
			// 销售单数量2
			vo.setOrdersNum2(vo.getOrdersNum2() + qVo.getOrdersNum2());
		}
		if (vo.getOrdersNum1() != 0) {
			// 销售单均价1
			vo.setOrdersAvgPrice1(vo.getSaleAmt1().divide(new BigDecimal(vo.getOrdersNum1()), 2, BigDecimal.ROUND_UP));
		}
		if (vo.getOrdersNum2() != 0) {
			// 销售单均价2
			vo.setOrdersAvgPrice2(vo.getSaleAmt2().divide(new BigDecimal(vo.getOrdersNum2()), 2, BigDecimal.ROUND_UP));
		}

		voList.add(0, vo);
	}

	/**
	 * @param voList
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 * @param orgId
	 */
	private void copyList2Value(List<OrgSalesQuota> voList, String currentStartOptDate, String currentEndOptDate, String orgId) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", currentStartOptDate);
		param.put("optDateEnd", currentEndOptDate);
		param.put("orgId", orgId);
		List<SalesOrdersDayTotal> list2 = salesOrdersDayTotalMyBatisDao.getSalesOrdersDayTotalList(param);

		for (OrgSalesQuota vo : voList) {
			int _index = list2.indexOf(vo);
			if (-1 != _index) {
				SalesOrdersDayTotal value = list2.get(_index);
				// 销售金额2
				vo.setSaleAmt2(value.getSaleAmt());
				// 销售单数量2
				vo.setOrdersNum2(value.getOrdersNum());
				if (vo.getOrdersNum2() != 0) {
					// 销售单均价2
					vo.setOrdersAvgPrice2(vo.getSaleAmt2().divide(new BigDecimal(vo.getOrdersNum2()), 2, BigDecimal.ROUND_UP));
				}
			}
		}
	}

	/**
	 * @param voList
	 * @param preStartOptDate
	 * @param preEndOptDate
	 * @param orgId
	 */
	private void copyList1Value(List<OrgSalesQuota> voList, String preStartOptDate, String preEndOptDate, String orgId) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", preStartOptDate);
		param.put("optDateEnd", preEndOptDate);
		param.put("orgId", orgId);
		List<SalesOrdersDayTotal> list1 = salesOrdersDayTotalMyBatisDao.getSalesOrdersDayTotalList(param);

		for (OrgSalesQuota vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				SalesOrdersDayTotal value = list1.get(_index);
				// 销售金额1
				vo.setSaleAmt1(value.getSaleAmt());
				// 销售单数量1
				vo.setOrdersNum1(value.getOrdersNum());
				if (vo.getOrdersNum1() != 0) {
					// 销售单均价1
					vo.setOrdersAvgPrice1(vo.getSaleAmt1().divide(new BigDecimal(vo.getOrdersNum1()), 2, BigDecimal.ROUND_UP));
				}
			}
		}
	}

	/**
	 * @param orgId
	 * @return
	 */
	private List<OrgSalesQuota> initBlankVoList(String orgId) {
		List<OrgSalesQuota> voList = Lists.newArrayList();

		List<Organization> orgList = null;

		if (StringUtils.isBlank(orgId)) {// 全机构
			orgList = orgManager.getSubOrganization();
		} else {
			Organization org = orgManager.getOrganizationByOrgIdInCache(orgId);

			orgList = Lists.newArrayList();
			orgList.add(org);
		}

		for (Organization org : orgList) {
			OrgSalesQuota vo = new OrgSalesQuota();

			// 机构编号
			vo.setOrgId(org.getId());
			// 机构名称
			vo.setOrgName(org.getName());

			voList.add(vo);
		}
		return voList;
	}
}

class SalesQuotaPriceComparator implements Comparator<OrgSalesQuota> {

	@Override
	public int compare(OrgSalesQuota o1, OrgSalesQuota o2) {
		BigDecimal v1 = o1.getOrdersAvgPrice2();
		BigDecimal v2 = o2.getOrdersAvgPrice2();

		if (v1.intValue() == v2.intValue()) {
			return 0;
		} else {
			return v1.intValue() > v2.intValue() ? -1 : 1;
		}
	}

}

class SaleQuotaOrdersNumComparator implements Comparator<OrgSalesQuota> {

	@Override
	public int compare(OrgSalesQuota o1, OrgSalesQuota o2) {
		int v1 = o1.getOrdersNum2();
		int v2 = o2.getOrdersNum2();

		if (v1 == v2) {
			return 0;
		} else {
			return v1 > v2 ? -1 : 1;
		}
	}

}