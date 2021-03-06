package com.tjhx.service.info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.CashDailyJpaDao;
import com.tjhx.dao.accounts.CashDailyMyBatisDao;
import com.tjhx.dao.info.OrgWeekSalesRankingJpaDao;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.entity.info.OrgWeekSalesRanking;

@Service
@Transactional(readOnly = true)
public class OrgWeekSalesRankingManager {
	@Resource
	private OrgWeekSalesRankingJpaDao orgWeekSalesRankingJpaDao;
	@Resource
	private CashDailyJpaDao cashDailyJpaDao;
	@Resource
	private CashDailyMyBatisDao cashDailyMyBatisDao;
	@Resource
	private OrganizationJpaDao orgJpaDao;

	/**
	 * 计算机构销售等级／排名
	 * 
	 * @throws ParseException
	 */
	public void calOrgWeekSalesRanking() throws ParseException {

		String endDate = getCalOptDate();
		String beginDate = DateUtils.getNextDateFormatDate(endDate, -6, "yyyyMMdd");

		int year = Integer.valueOf(DateUtils.transDateFormat(endDate, "yyyyMMdd", "yyyy"));
		int weekOfYear = DateUtils.getWeekOfYear(endDate);

		if (checkLastWeekInfoExist(year, weekOfYear)) {// 当前时间节点上周机构周销售排名信息存在
			return;
		}

		if (!checkCashDailyInfo(endDate)) {// 未全部日结
			return;
		}

		Map<String, String> param = Maps.newHashMap();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		List<CashDaily> _cdList = cashDailyMyBatisDao.getCashDailyListByWeek(param);

		List<OrgWeekSalesRanking> _reList = Lists.newArrayList();

		for (CashDaily _cashDaily : _cdList) {
			OrgWeekSalesRanking _rObj = new OrgWeekSalesRanking();

			// 年份
			_rObj.setOptDateY(year);
			// 星期(周)
			_rObj.setOptDateW(weekOfYear);
			// 周区间
			_rObj.setOptDateWSection(beginDate + "-" + endDate);
			// 门店编号
			_rObj.setOrgId(_cashDaily.getOrgId());
			// 销售金额
			_rObj.setSaleCashAmt(_cashDaily.getSaleAmt());

			_reList.add(_rObj);
		}

		// 计算排名
		calRanking(_reList);

		// 计算等级
		calLevel(_reList);

		for (OrgWeekSalesRanking orgWeekSalesRanking : _reList) {
			orgWeekSalesRankingJpaDao.save(orgWeekSalesRanking);
		}
	}

	/**
	 * 计算排名
	 * 
	 * @param list
	 */
	private void calRanking(List<OrgWeekSalesRanking> list) {
		Collections.sort(list, new OrgWeekSalesRankingComparator_Amt());

		int _rank = 0;
		for (int i = 0; i < list.size(); i++) {

			OrgWeekSalesRanking obj = list.get(i);

			if ("00013D".equals(obj.getOrgId()) || "00023D".equals(obj.getOrgId())) {// 特殊处理对13/23店
				// 排名
				obj.setRanking(0);
			} else {
				// 排名
				obj.setRanking(++_rank);
			}

		}
	}

	/**
	 * 计算等级
	 * 
	 * @param list
	 */
	private void calLevel(List<OrgWeekSalesRanking> list) {
		Collections.sort(list, new OrgWeekSalesRankingComparator_Rank());

		for (int i = 0; i < list.size(); i++) {
			OrgWeekSalesRanking obj = list.get(i);
			if ("00013D".equals(obj.getOrgId()) || "00023D".equals(obj.getOrgId())) {// 特殊处理对13/23店
				continue;
			}

			if (i < 5) {
				// 等级
				obj.setLevel("A");
			} else if (i < 8) {
				// 等级
				obj.setLevel("B");
			} else if (i < 11) {
				// 等级
				obj.setLevel("C");
			} else if (i < 14) {
				// 等级
				obj.setLevel("D");
			} else if (i < 17) {
				// 等级
				obj.setLevel("E");
			} else if (i < 20) {
				// 等级
				obj.setLevel("F");
			} else if (i < 23) {
				// 等级
				obj.setLevel("G");
			} else if (i < 26) {
				// 等级
				obj.setLevel("H");
			} else if (i < 29) {
				// 等级
				obj.setLevel("I");
			} else if (i < 32) {
				// 等级
				obj.setLevel("J");
			} else if (i < 35) {
				// 等级
				obj.setLevel("K");
			} else if (i < 38) {
				// 等级
				obj.setLevel("L");
			}

			int rl = obj.getRanking() % 3;
			if (0 == rl) {
				// 等级排名
				obj.setRankingLevel(3);
			} else {
				// 等级排名
				obj.setRankingLevel(rl);
			}

		}
	}

	/**
	 * 检查指定日期机构的填报信息是否以全部日结
	 * 
	 * @return TRUE-全部日结 FALSE-未全部日结
	 */
	private boolean checkCashDailyInfo(String calOptDate) {
		Long c1 = cashDailyJpaDao.getCashDailyCount(calOptDate);
		Long c2 = orgJpaDao.getOpenOrganizationCount();

		return c1 == (c2 - 1);// 除去总部
	}

	/**
	 * 检查当前时间节点上周机构周销售排名信息是否存在
	 * 
	 * @return TRUE-存在 FALSE-不存在
	 * @throws ParseException
	 */
	private boolean checkLastWeekInfoExist(int year, int weekOfYear) throws ParseException {
		long _count = orgWeekSalesRankingJpaDao.getRankingCount(year, weekOfYear);
		return _count > 0;
	}

	/**
	 * 取得上周的结束日（以当前时间为准）
	 * 
	 * @return
	 * @throws ParseException
	 */
	private String getCalOptDate() throws ParseException {
		String nowDate = DateUtils.getCurrentDateShortStr();
		// String nowDate = "20160301";
		int weekOfYear = DateUtils.getWeekOfYear(nowDate);
		String _endDate = DateUtils.getBeginDate_WeekOfYear(DateUtils.transDateFormat(nowDate, "yyyyMMdd", "yyyy"), weekOfYear);
		return DateUtils.getNextDateFormatDate(_endDate, -1, "yyyyMMdd");
	}

	public static void main(String[] args) throws ParseException {
		// OrgWeekSalesRankingManager a = new OrgWeekSalesRankingManager();
		// String endDate = a.getCalOptDate();
		// String beginDate = DateUtils.getNextDateFormatDate(endDate, -6, "yyyyMMdd");
		// System.out.println(endDate);
		// System.out.println(beginDate);

		// SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		// Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, 2016);
		// cal.set(Calendar.WEEK_OF_YEAR, -1);
		// cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		String nowDate = DateUtils.getCurrentDateShortStr();
		int weekOfYear = DateUtils.getWeekOfYear(nowDate);
		String _endDate = DateUtils.getBeginDate_WeekOfYear(DateUtils.transDateFormat(nowDate, "yyyyMMdd", "yyyy"), weekOfYear);

		System.out.println(DateUtils.getNextDateFormatDate(_endDate, -8, "yyyyMMdd"));
	}

	/**
	 * 取得机构销售等级／排名
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<OrgWeekSalesRanking> getOrgWeekSalesRankingInfo() {
		int _optDateY = DateUtils.getCurrentYearNum();
		Integer _optDateW = orgWeekSalesRankingJpaDao.getMaxOptDateW(_optDateY);

		if (null == _optDateW) {
			_optDateY = _optDateY - 1;
			_optDateW = orgWeekSalesRankingJpaDao.getMaxOptDateW(_optDateY);
		}
		List<OrgWeekSalesRanking> _list1 = orgWeekSalesRankingJpaDao.getOrgWeekSalesRankingList(_optDateY, _optDateW);// 取得最新销售等级／排名信息
		// ================================================================================================================================================
		List<OrgWeekSalesRanking> _list2 = null;
		_optDateW = _optDateW - 1;
		if (0 == _optDateW) {
			_optDateY = _optDateY - 1;
			_optDateW = orgWeekSalesRankingJpaDao.getMaxOptDateW(_optDateY);
			_list2 = orgWeekSalesRankingJpaDao.getOrgWeekSalesRankingList(_optDateY, _optDateW);// 取得最新销售等级／排名信息
		} else {
			_list2 = orgWeekSalesRankingJpaDao.getOrgWeekSalesRankingList(_optDateY, _optDateW);// 取得最新销售等级／排名信息
		}

		mergeList(_list1, _list2);
		return _list1;
	}

	private void mergeList(List<OrgWeekSalesRanking> _list1, List<OrgWeekSalesRanking> _list2) {
		Map<String, OrgWeekSalesRanking> _map = Maps.newHashMap();

		for (OrgWeekSalesRanking ranking : _list2) {
			_map.put(ranking.getOrgId(), ranking);
		}

		for (OrgWeekSalesRanking ranking : _list1) {
			OrgWeekSalesRanking _tmp = _map.get(ranking.getOrgId());

			if (null != _tmp) {
				// 年份
				ranking.setOptDateY_Last(_tmp.getOptDateY());
				// 星期(周)
				ranking.setOptDateW_Last(_tmp.getOptDateW());
				// 周区间
				ranking.setOptDateWSection_Last(_tmp.getOptDateWSection());
				// 销售金额
				ranking.setSaleCashAmt_Last(_tmp.getSaleCashAmt());
				// 排名
				ranking.setRanking_Last(_tmp.getRanking());
				// 等级
				ranking.setLevel_Last(_tmp.getLevel());
				// 等级排名
				ranking.setRankingLevel_Last(_tmp.getRankingLevel());
			}

		}
	}
}

class OrgWeekSalesRankingComparator_Amt implements Comparator<OrgWeekSalesRanking> {

	@Override
	public int compare(OrgWeekSalesRanking o1, OrgWeekSalesRanking o2) {
		BigDecimal v1 = o1.getSaleCashAmt();
		BigDecimal v2 = o2.getSaleCashAmt();

		if (v1.intValue() == v2.intValue()) {
			return 0;
		} else {
			return v1.intValue() > v2.intValue() ? -1 : 1;
		}
	}
}

class OrgWeekSalesRankingComparator_Rank implements Comparator<OrgWeekSalesRanking> {

	@Override
	public int compare(OrgWeekSalesRanking o1, OrgWeekSalesRanking o2) {
		int v1 = o1.getRanking();
		int v2 = o2.getRanking();

		if (v1 == v2) {
			return 0;
		} else {
			return v1 > v2 ? 1 : -1;
		}
	}

}
