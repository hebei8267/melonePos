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
		Collections.sort(list, new OrgWeekSalesRankingComparator());

		for (int i = 0; i < list.size(); i++) {
			OrgWeekSalesRanking obj = list.get(i);
			// 排名
			obj.setRanking(i + 1);
		}
	}

	/**
	 * 计算等级
	 * 
	 * @param list
	 */
	private void calLevel(List<OrgWeekSalesRanking> list) {
		for (int i = 0; i < list.size(); i++) {
			OrgWeekSalesRanking obj = list.get(i);
			if (i < 4) {
				// 等级
				obj.setLevel("A");
			} else if (i < 8) {
				// 等级
				obj.setLevel("B");
			} else if (i < 12) {
				// 等级
				obj.setLevel("C");
			} else if (i < 16) {
				// 等级
				obj.setLevel("D");
			} else if (i < 20) {
				// 等级
				obj.setLevel("E");
			} else if (i < 24) {
				// 等级
				obj.setLevel("F");
			} else if (i < 28) {
				// 等级
				obj.setLevel("G");
			} else if (i < 32) {
				// 等级
				obj.setLevel("H");
			} else if (i < 36) {
				// 等级
				obj.setLevel("I");
			} else if (i < 40) {
				// 等级
				obj.setLevel("J");
			} else if (i < 44) {
				// 等级
				obj.setLevel("K");
			} else if (i < 48) {
				// 等级
				obj.setLevel("L");
			}

			int rl = obj.getRanking() % 4;
			if (0 == rl) {
				// 等级排名
				obj.setRankingLevel(4);
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
		int weekOfYear = DateUtils.getWeekOfYear(nowDate);
		String _endDate = DateUtils.getBeginDate_WeekOfYear(DateUtils.transDateFormat(nowDate, "yyyyMMdd", "yyyy"), weekOfYear);
		return DateUtils.getNextDateFormatDate(_endDate, -1, "yyyyMMdd");
	}

	public static void main(String[] args) throws ParseException {
		OrgWeekSalesRankingManager a = new OrgWeekSalesRankingManager();
		String endDate = a.getCalOptDate();
		String beginDate = DateUtils.getNextDateFormatDate(endDate, -6, "yyyyMMdd");
		System.out.println(endDate);
		System.out.println(beginDate);
	}
}

class OrgWeekSalesRankingComparator implements Comparator<OrgWeekSalesRanking> {

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
