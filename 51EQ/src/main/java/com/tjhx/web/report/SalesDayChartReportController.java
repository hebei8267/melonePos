package com.tjhx.web.report;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.SalesDayTotal;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.info.SalesDayTotalManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesDayChartReport")
public class SalesDayChartReportController extends BaseController {
	@Resource
	private SalesDayTotalManager salesDayTotalManager;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 门店页面-初期化
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "init_shop")
	public String init_shop_Action(Model model, HttpSession session) throws ParseException {
		String _now = DateUtils.getCurFormatDate("yyyyMMdd");
		// 统计日期-1
		String _optDate1 = DateUtils.getNextDateFormatDate(_now, -1, "yyyyMMdd");
		model.addAttribute("optDate1", _optDate1);
		model.addAttribute("optDate1SalesList", _init_shop(_optDate1, model, getUserInfo(session).getOrganization().getId()));

		// 统计日期-2
		String _optDate2 = DateUtils.getNextDateFormatDate(_now, -2, "yyyyMMdd");
		model.addAttribute("optDate2", _optDate2);
		model.addAttribute("optDate2SalesList", _init_shop(_optDate2, model, getUserInfo(session).getOrganization().getId()));
		// 统计日期-3
		String _optDate3 = DateUtils.getNextDateFormatDate(_now, -3, "yyyyMMdd");
		model.addAttribute("optDate3", _optDate3);
		model.addAttribute("optDate3SalesList", _init_shop(_optDate3, model, getUserInfo(session).getOrganization().getId()));

		return "report/salesDayChartReport_shop";
	}

	/**
	 * 同期增长额Tab处理
	 * 
	 * @param optDate
	 * @param model
	 * @throws ParseException
	 */
	private List<SalesDayTotal> _init_shop(String optDate, Model model, String orgId) throws ParseException {
		model.addAttribute("optDate", optDate);
		// 取得每日各店销售汇总（根据日期）
		List<SalesDayTotal> _dbSalesDayTotalList = salesDayTotalManager.getSalesDayTotalListByOptDate(optDate);

		Map<String, SalesDayTotal> orgKeyMap = new HashMap<String, SalesDayTotal>();
		for (SalesDayTotal saleInfo : _dbSalesDayTotalList) {
			orgKeyMap.put(saleInfo.getOrgId(), saleInfo);
		}

		Map<Integer, SalesDayTotal> rankingKeyMap = new HashMap<Integer, SalesDayTotal>();
		for (SalesDayTotal saleInfo : _dbSalesDayTotalList) {
			rankingKeyMap.put(saleInfo.getRanking(), saleInfo);
		}

		int _orgRanking = getRankingByOrgId(orgKeyMap, orgId);

		return initOrgRankingList(_orgRanking, rankingKeyMap);
	}

	private List<SalesDayTotal> initOrgRankingList(int orgRanking, Map<Integer, SalesDayTotal> rankingKeyMap) {
		if (-1 == orgRanking) {
			return null;
		} else if (0 == orgRanking) {
			List<SalesDayTotal> _res = new ArrayList<SalesDayTotal>();
			_res.add(rankingKeyMap.get(orgRanking));
			return _res;
		} else if (1 == orgRanking) {
			List<SalesDayTotal> _res = new ArrayList<SalesDayTotal>();

			_res.add(rankingKeyMap.get(orgRanking));

			_res.add(rankingKeyMap.get(orgRanking + 1));

			return _res;
		} else {
			List<SalesDayTotal> _res = new ArrayList<SalesDayTotal>();

			_res.add(rankingKeyMap.get(orgRanking - 1));

			_res.add(rankingKeyMap.get(orgRanking));

			_res.add(rankingKeyMap.get(orgRanking + 1));

			return _res;
		}
	}

	private int getRankingByOrgId(Map<String, SalesDayTotal> orgKeyMap, String orgId) {
		SalesDayTotal saleInfo = orgKeyMap.get(orgId);
		if (null != saleInfo) {
			return saleInfo.getRanking();
		} else {
			return -1;
		}
	}

	/**
	 * 总部页面-初期化
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "init")
	public String init_Action(Model model) throws ParseException {

		return "report/salesDayChartReport";
	}

	/**
	 * 同期增长额Tab初期化
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "init_tab1")
	public String init_tab1_Action(Model model) throws ParseException {
		String _now = DateUtils.getCurFormatDate("yyyyMMdd");
		// 统计日期
		String _optDate = DateUtils.getNextDateFormatDate(_now, -1, "yyyyMMdd");
		// 同期增长额 Tab
		_init_tab1(_optDate, model);

		return "report/salesDayChartReport_tab1";
	}

	/**
	 * 同期增长额Tab查询按钮点击
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws ParseException
	 */
	@RequestMapping(value = "search_tab1")
	public String search_tab1_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException, ParseException {
		String _optDate = ServletRequestUtils.getStringParameter(request, "optDate");
		if (StringUtils.isNotBlank(_optDate)) {
			_optDate = DateUtils.transDateFormat(_optDate, "yyyy-MM-dd", "yyyyMMdd");
		}
		// 同期增长额 Tab
		_init_tab1(_optDate, model);

		return "report/salesDayChartReport_tab1";
	}

	/**
	 * 同期增长额Tab处理
	 * 
	 * @param optDate
	 * @param model
	 * @throws ParseException
	 */
	private void _init_tab1(String optDate, Model model) throws ParseException {
		model.addAttribute("optDate", optDate);
		// 取得每日各店销售汇总（根据日期）
		List<SalesDayTotal> salesDayTotalList = salesDayTotalManager.getSalesDayTotalListByOptDate(optDate);

		List<Organization> orgList = orgManager.getSubOrganization();
		List<SalesDayTotal> _salesDayTotalList = Lists.newArrayList();

		for (Organization org : orgList) {
			SalesDayTotal _tmpSalesDayTotal = new SalesDayTotal(optDate, org.getId());

			if (!salesDayTotalList.contains(_tmpSalesDayTotal)) {
				_salesDayTotalList.add(_tmpSalesDayTotal);
			}
		}

		_salesDayTotalList.addAll(salesDayTotalList);
		Collections.sort(_salesDayTotalList, new SalesDayTotalComparator());

		_salesDayTotalList.add(0, calTotal(_salesDayTotalList));

		model.addAttribute("salesDayTotalList", _salesDayTotalList);
	}

	private SalesDayTotal calTotal(List<SalesDayTotal> salesDayTotalList) {
		SalesDayTotal total = new SalesDayTotal();
		// 机构编号
		total.setOrgId("合计");

		for (SalesDayTotal salesDayTotal : salesDayTotalList) {
			// 当日销售金额
			total.setPosAmt(total.getPosAmt().add(salesDayTotal.getPosAmt()));
			// 截止现在销售金额
			total.setPosAmtByNow(total.getPosAmtByNow().add(salesDayTotal.getPosAmtByNow()));
			// 截止天数
			total.setNowDays(salesDayTotal.getNowDays());
			// 本月天数
			total.setMonthDays(salesDayTotal.getMonthDays());
			// 预计本月销售
			total.setExpMonthPosAmt(total.getExpMonthPosAmt().add(salesDayTotal.getExpMonthPosAmt()));
			// 去年同期销售
			total.setPreYearMonthPosAmt(total.getPreYearMonthPosAmt().add(salesDayTotal.getPreYearMonthPosAmt()));
			// 销售增长额(去年同期销售)
			total.setPosAmtIncrease(total.getPosAmtIncrease().add(salesDayTotal.getPosAmtIncrease()));
			// 销售增长率(去年同期销售)=销售增长额/去年同期销售
			BigDecimal _preYearMonthPosAmt = total.getPreYearMonthPosAmt();
			if (null == _preYearMonthPosAmt || _preYearMonthPosAmt.compareTo(BigDecimal.ZERO) == 0) {
				_preYearMonthPosAmt = new BigDecimal(1);
			}
			total.setPosAmtRate(total.getPosAmtIncrease().divide(_preYearMonthPosAmt, 4, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100)));
			// 月销售目标额
			total.setSaleTargetAmt(total.getSaleTargetAmt().add(salesDayTotal.getSaleTargetAmt()));
			// 销售增长额(本月销售目标额)
			total.setPosAmtIncrease2(total.getPosAmtIncrease2().add(salesDayTotal.getPosAmtIncrease2()));
			// 销售增长率(本月销售目标额)=销售增长额(本月销售目标额)/本月销售目标额
			BigDecimal _saleTargetAmt = total.getSaleTargetAmt();
			if (null == _saleTargetAmt || _saleTargetAmt.compareTo(BigDecimal.ZERO) == 0) {
				_saleTargetAmt = new BigDecimal(1);
			}
			total.setPosAmtRate2(total.getPosAmtIncrease2().divide(_saleTargetAmt, 4, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100)));

			// 日需销售金额 */
			total.setDayNeededPosAmt(total.getDayNeededPosAmt().add(salesDayTotal.getDayNeededPosAmt()));
		}
		return total;
	}

	/**
	 * 增长率折线图Tab初期化
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "init_tab2")
	public String init_tab2_Action(Model model) throws ParseException, IllegalAccessException, InvocationTargetException {
		String _now = DateUtils.getCurFormatDate("yyyyMMdd");
		// 统计日期
		String _optDate = DateUtils.getNextDateFormatDate(_now, -1, "yyyyMMdd");
		model.addAttribute("optDate", _optDate);

		List<Organization> _orgList = orgManager.getAllOrganization();
		_orgList.remove(new Organization(Constants.ROOT_ORG_ID));
		model.addAttribute("orgList", _orgList);

		// 取得指定时间区间（近40天）
		List<String> _optDateList = calOptDate(_optDate);
		model.addAttribute("optDateList", _optDateList);

		// 取得指定机构近期(近40天)的销售增长率
		List<List<SalesDayTotal>> _posAmtRateList = initPosAmtRateList(_optDateList, _orgList);

		model.addAttribute("posAmtRateList", _posAmtRateList);

		return "report/salesDayChartReport_tab2";
	}

	private void copyProperties(SalesDayTotal destObj, List<SalesDayTotal> dbPosAmtRateList) throws IllegalAccessException,
			InvocationTargetException {

		for (SalesDayTotal _dbObj : dbPosAmtRateList) {
			if (_dbObj.equals(destObj)) {
				BeanUtils.copyProperties(destObj, _dbObj);

				BigDecimal _rate_b = destObj.getPosAmtRate();

				if (_rate_b.compareTo(BigDecimal.ZERO) > 0) {
					double _rate_d = _rate_b.doubleValue();
					if (_rate_d < 20) {
						_rate_d = 20;
					} else if (_rate_d > 100) {
						_rate_d = 100;
					}
					_rate_d = (100 - _rate_d) / 100 * 255;
					String _rate_s = Integer.toHexString((int) _rate_d);
					if (_rate_s.length() == 1) {
						_rate_s = "0" + _rate_s;
					}
					destObj.setHtmlColor("#FF" + _rate_s + _rate_s);
				} else {
					double _rate_d = _rate_b.doubleValue() * -1;
					if (_rate_d < 20) {
						_rate_d = 20;
					} else if (_rate_d > 100) {
						_rate_d = 100;
					}
					_rate_d = (100 - _rate_d) / 100 * 255;
					String _rate_s = Integer.toHexString((int) _rate_d);
					if (_rate_s.length() == 1) {
						_rate_s = "0" + _rate_s;
					}
					destObj.setHtmlColor("#" + _rate_s + "FF" + _rate_s);
				}
			}
		}
	}

	/**
	 * 初始化显示列表
	 * 
	 * @param optDateList
	 * @param orgList
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private List<List<SalesDayTotal>> initPosAmtRateList(List<String> optDateList, List<Organization> orgList)
			throws IllegalAccessException, InvocationTargetException {
		List<List<SalesDayTotal>> _posAmtRateList = new ArrayList<List<SalesDayTotal>>();

		for (String optDate : optDateList) {
			List<SalesDayTotal> _list = new ArrayList<SalesDayTotal>();

			// 取得每日各店销售汇总（根据日期）
			List<SalesDayTotal> dbPosAmtRateList = salesDayTotalManager.getSalesDayTotalListByOptDate(optDate);

			for (Organization org : orgList) {
				// 初始化空白每日各店销售汇总信息
				SalesDayTotal _showObj = new SalesDayTotal(optDate, org.getId());

				copyProperties(_showObj, dbPosAmtRateList);

				_list.add(_showObj);
			}

			_posAmtRateList.add(_list);
		}

		return _posAmtRateList;
	}

	/**
	 * 取得指定时间区间（近40天）
	 * 
	 * @param optDate
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDate(String optDate) throws ParseException {
		List<String> _optDateList = new ArrayList<String>();
		_optDateList.add(optDate);
		for (int i = 1; i < 41; i++) {
			String _optDate = DateUtils.getNextDateFormatDate(optDate, -i, "yyyyMMdd");

			_optDateList.add(_optDate);
		}

		return _optDateList;
	}

}

class SalesDayTotalComparator implements Comparator<SalesDayTotal> {

	@Override
	public int compare(SalesDayTotal o1, SalesDayTotal o2) {
		return o1.getOrgId().compareTo(o2.getOrgId());
	}
}