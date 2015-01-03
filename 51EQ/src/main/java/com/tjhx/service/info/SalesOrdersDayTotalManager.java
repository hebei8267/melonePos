/**
 * 
 */
package com.tjhx.service.info;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesOrdersDayTotalJpaDao;
import com.tjhx.dao.info.SalesOrdersDayTotalMyBatisDao;
import com.tjhx.daobw.DaySalesOrdersTotalMyBatisDao;
import com.tjhx.entity.info.SalesOrdersDayTotal;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

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
}
