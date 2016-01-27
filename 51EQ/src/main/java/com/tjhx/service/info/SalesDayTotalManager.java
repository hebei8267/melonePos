package com.tjhx.service.info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesDayTotalJpaDao;
import com.tjhx.dao.info.SalesDayTotalMyBatisDao;
import com.tjhx.dao.info.SalesMonthTotalItemMyBatisDao;
import com.tjhx.daobw.DailySaleMyBatisDao;
import com.tjhx.entity.bw.DailySale;
import com.tjhx.entity.info.SalesDayTotal;
import com.tjhx.entity.info.SalesMonthTotalItem;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.accounts.MonthSaleTargetManager;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class SalesDayTotalManager {
	@Resource
	private DailySaleMyBatisDao dailySaleMyBatisDao;
	@Resource
	private OrganizationManager organizationManager;
	@Resource
	private SalesDayTotalJpaDao salesDayTotalJpaDao;
	@Resource
	private SalesDayTotalMyBatisDao salesDayTotalMyBatisDao;
	@Resource
	private SalesMonthTotalItemMyBatisDao salesMonthTotalItemMyBatisDao;
	@Resource
	private MonthSaleTargetManager monthSaleTargetManager;

	private Set<String> _calYM = new HashSet<String>();
	private Map<String, BigDecimal> _orgYMPosAmt = new HashMap<String, BigDecimal>();

	/**
	 * 取得所有机构-百威销售信息（根据指定的日期）
	 * 
	 * @param optDateList
	 */
	private void getBwSaleAmt(List<String> optDateList) {
		for (String optDate : optDateList) {
			List<DailySale> _bwDailySaleList = dailySaleMyBatisDao.getDailySaleList(optDate);

			for (DailySale dailySale : _bwDailySaleList) {

				Organization org = organizationManager.getOrganizationByBwBranchNo(dailySale.getOrgBranchNo());

				if (null == org) {
					continue;
				}

				SalesDayTotal _salesDayTotal = new SalesDayTotal();

				String year = DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy");
				String month = DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM");
				String days = DateUtils.transDateFormat(optDate, "yyyyMMdd", "dd");

				// 缓存计算的年月信息
				_calYM.add(year + month);

				// 日期
				_salesDayTotal.setOptDate(optDate);
				// 日期-年
				_salesDayTotal.setOptDateY(year);
				// 日期-月
				_salesDayTotal.setOptDateM(month);
				// 机构编号
				_salesDayTotal.setOrgId(org.getId());
				// 机构资金编号
				_salesDayTotal.setBranchNo(org.getBwBranchNo());
				// 当日销售金额
				_salesDayTotal.setPosAmt(dailySale.getBwSaleAmt());
				// 截止天数
				_salesDayTotal.setNowDays(Integer.parseInt(days));
				// 本月天数
				_salesDayTotal.setMonthDays(DateUtils.getMonthDays(year, month));

				salesDayTotalJpaDao.save(_salesDayTotal);

			}
		}
	}

	/**
	 * 同步百威销售额
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void synBwSaleAmt() throws ParseException {
		// 取得同步百威销售额-重计算天数
		List<String> optDateList = calOptDate();
		// 删除所有机构-百威销售信息（根据指定的日期）
		delBwSaleAmt(optDateList);
		// 取得所有机构-百威销售信息（根据指定的日期）
		getBwSaleAmt(optDateList);

		// 重计算每日各店销售汇总信息
		for (String ym : _calYM) {
			String year = ym.substring(0, 4);
			String month = ym.substring(4);
			recalSalesDayTotalInfo(year, month);
		}

		// 重计算每日各店销售汇总信息的排名（根据销售增长率）
		recalRanking(optDateList);
	}

	/**
	 * 删除所有机构-百威销售信息（根据指定的日期）
	 * 
	 * @param optDateList
	 */
	private void delBwSaleAmt(List<String> optDateList) {
		for (String optDate : optDateList) {
			salesDayTotalMyBatisDao.delSalesDayTotalInfo(optDate);
		}
	}

	/**
	 * 重计算每日各店销售汇总信息的排名（根据销售金额）
	 * 
	 * @param optDateList
	 */
	private void recalRanking(List<String> optDateList) {
		for (String optDate : optDateList) {
			List<SalesDayTotal> _sList = (List<SalesDayTotal>) salesDayTotalJpaDao
					.findByOptDateOrderBy_Ranking(optDate);

			int rank = 1;
			for (SalesDayTotal _s : _sList) {

				// 门店-13D不参加排名
				if (Constants.ORG_ID_13.equals(_s.getOrgId())) {
					// 排名
					_s.setRanking(0);
				} else {
					// 排名
					_s.setRanking(rank);
				}

				rank++;
				salesDayTotalJpaDao.save(_s);
			}
		}

	}

	/**
	 * 重计算每日各店销售汇总信息
	 * 
	 * @param year
	 * @param month
	 * @throws ParseException
	 */
	private void recalSalesDayTotalInfo(String year, String month) throws ParseException {
		List<Organization> _orgList = organizationManager.getAllOrganization();

		for (Organization org : _orgList) {
			// 不计算根节点
			if (Constants.ROOT_ORG_ID.equals(org.getId())) {
				continue;
			}

			BigDecimal _posAmtByNow = new BigDecimal(0);

			List<SalesDayTotal> _sList = (List<SalesDayTotal>) salesDayTotalJpaDao.findOneByOrgIdAndOptDateYM(
					org.getId(), year, month);
			for (SalesDayTotal _salesDayTotal : _sList) {
				_posAmtByNow = _posAmtByNow.add(_salesDayTotal.getPosAmt());

				// 截止现在销售金额
				_salesDayTotal.setPosAmtByNow(_posAmtByNow);
				// 预计本月销售
				_salesDayTotal.setExpMonthPosAmt(_posAmtByNow.divide(new BigDecimal(_salesDayTotal.getNowDays()), 2,
						BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(_salesDayTotal.getMonthDays())));
				// 去年同期/上月/认为指定的销售额
				_salesDayTotal.setPreYearMonthPosAmt(getOrgYMPosAmt(org.getId(), year, month));
				// 销售增长额(去年同期销售)=预计本月销售-去年同期销售
				_salesDayTotal.setPosAmtIncrease(_salesDayTotal.getExpMonthPosAmt().subtract(
						_salesDayTotal.getPreYearMonthPosAmt()));
				// 销售增长率(去年同期销售)=销售增长额/去年同期销售
				BigDecimal _preYearMonthPosAmt = _salesDayTotal.getPreYearMonthPosAmt();
				if (null == _preYearMonthPosAmt || _preYearMonthPosAmt.compareTo(BigDecimal.ZERO) == 0) {
					_preYearMonthPosAmt = new BigDecimal(1);
				}
				_salesDayTotal.setPosAmtRate(_salesDayTotal.getPosAmtIncrease()
						.divide(_preYearMonthPosAmt, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
				// 本月天数-截止天数
				int _d = _salesDayTotal.getMonthDays() - _salesDayTotal.getNowDays();
				if (0 == _d) {
					_d = 1;
				}
				// 月销售目标额
				_salesDayTotal.setSaleTargetAmt(monthSaleTargetManager.getMonthSaleTargetAmt(_salesDayTotal.getOrgId(),
						_salesDayTotal.getOptDateY(), _salesDayTotal.getOptDateM()));

				// 日需销售金额=(月销售目标额-截止金额)/(本月天数-截止天数)
				_salesDayTotal.setDayNeededPosAmt(_salesDayTotal.getSaleTargetAmt()
						.subtract(_salesDayTotal.getPosAmtByNow())
						.divide(new BigDecimal(_d), 2, BigDecimal.ROUND_HALF_EVEN));

				// 销售增长额(本月销售目标额)=预计本月销售-本月销售目标额
				_salesDayTotal.setPosAmtIncrease2(_salesDayTotal.getExpMonthPosAmt().subtract(
						_salesDayTotal.getSaleTargetAmt()));
				// 销售增长率(本月销售目标额)=销售增长额(本月销售目标额)/本月销售目标额
				BigDecimal _saleTargetAmt = _salesDayTotal.getSaleTargetAmt();
				if (null == _saleTargetAmt || _saleTargetAmt.compareTo(BigDecimal.ZERO) == 0) {
					_saleTargetAmt = new BigDecimal(1);
				}
				_salesDayTotal.setPosAmtRate2(_salesDayTotal.getPosAmtIncrease2()
						.divide(_saleTargetAmt, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));

				salesDayTotalJpaDao.save(_salesDayTotal);
			}

		}

	}

	/**
	 * 取得指定机构/年月销售额合计
	 * 
	 * @return
	 * @throws ParseException
	 */
	private BigDecimal getOrgYMPosAmt(String orgId, String year, String month) throws ParseException {
		BigDecimal _res = null;
		// ========================================
		// 取得去年同期销售
		// ========================================
		String _year = String.valueOf(Integer.parseInt(year) - 1);

		_res = _orgYMPosAmt.get(orgId + _year + month);
		if (null == _res) {
			SalesMonthTotalItem param = new SalesMonthTotalItem();
			param.setOptDateYM(_year + month);
			param.setOrgId(orgId);

			// 取得指定门店/月份合计销售信息
			SalesMonthTotalItem _salesTotal = salesMonthTotalItemMyBatisDao.getSalesTotal_ByOrgAndYearMonth(param);
			if (null != _salesTotal) {
				_res = _salesTotal.getSaleRamt();
				_orgYMPosAmt.put(orgId + _year + month, _res);

				if (Constants.ORG_ID_02.equals(orgId)) {
					_res = _res.multiply(new BigDecimal(1.6));
				}
			}
		}
		// ========================================
		// 取得上月销售
		// ========================================
		// TODO 20150530临时屏蔽!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (("00020D".equals(orgId) && "2014".equals(_year) && "05".equals(month)) || null == _res) {
			String _yearmonth = DateUtils.getNextMonthFormatDate(year + month, -1, "yyyyMM");
			_res = _orgYMPosAmt.get(orgId + _yearmonth);

			if (null == _res) {
				SalesMonthTotalItem param = new SalesMonthTotalItem();
				param.setOptDateYM(_yearmonth);
				param.setOrgId(orgId);
				// 取得指定门店/月份合计销售信息
				SalesMonthTotalItem _salesTotal = salesMonthTotalItemMyBatisDao.getSalesTotal_ByOrgAndYearMonth(param);
				if (null != _salesTotal) {
					_res = _salesTotal.getSaleRamt();
					_orgYMPosAmt.put(orgId + _yearmonth, _res);
				}
			}
		}
		// TODO 20150705临时屏蔽!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (("00001D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(212435);
		}
		if (("00002D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(200854);
		}
		if (("00004D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(186660);
		}
		if (("00005D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(103782);
		}
		if (("00006D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(134006);
		}
		if (("00007D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(228722);
		}
		if (("00009D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(119718);
		}
		if (("00011D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(132900);
		}
		if (("00013D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(646608);
		}
		if (("00015D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(144608);
		}
		if (("00016D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(63504);
		}
		if (("00017D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(72439);
		}
		if (("00018D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(60115);
		}
		if (("00019D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(66908);
		}
		if (("00020D".equals(orgId) && "2014".equals(_year) && "07".equals(month))) {
			_res = new BigDecimal(54349);
		}
		// ========================================
		// FIXME 机构/年月销售额合计(人为设定销售目标)
		// ========================================
		if (null == _res) {
			if (Constants.ORG_ID_13.equals(orgId)) {
				_res = new BigDecimal(600000);
			} else {
				_res = new BigDecimal(100000);
			}
		}
		return _res;

	}

	/**
	 * 同步百威销售额-重计算天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurFormatDate("yyyyMMdd");

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = sysConfig.getSynBwSaleDays(); i > 0; i--) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyyMMdd"));
		}
		return _optDateList;
	}

	/**
	 * 取得每日各店销售汇总（根据日期）
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<SalesDayTotal> getSalesDayTotalListByOptDate(String optDate) {
		return (List<SalesDayTotal>) salesDayTotalJpaDao.findByOptDate(optDate);
	}

}
