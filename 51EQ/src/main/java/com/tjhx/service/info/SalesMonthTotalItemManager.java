package com.tjhx.service.info;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.CashDailyMyBatisDao;
import com.tjhx.dao.accounts.MonthSaleTargetJpaDao;
import com.tjhx.dao.info.SalesDayTotalItemJpaDao;
import com.tjhx.dao.info.SalesDayTotalItemMyBatisDao;
import com.tjhx.dao.info.SalesDayTotalJpaDao;
import com.tjhx.dao.info.SalesMonthTotalItemJpaDao;
import com.tjhx.dao.info.SalesMonthTotalItemMyBatisDao;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.entity.accounts.MonthSaleTarget;
import com.tjhx.entity.info.SalesDayTotal;
import com.tjhx.entity.info.SalesDayTotalItem;
import com.tjhx.entity.info.SalesMonthTotalItem;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.MngUserStatisticsDetail;
import com.tjhx.vo.MngUserStatisticsOrgDetail;
import com.tjhx.vo.MngUserStatisticsTotal;

@Service
@Transactional(readOnly = true)
public class SalesMonthTotalItemManager {
	@Resource
	private SalesDayTotalItemJpaDao salesDayTotalItemJpaDao;
	@Resource
	private SalesDayTotalItemMyBatisDao salesDayTotalItemMyBatisDao;
	@Resource
	private SalesDayTotalItemManager salesDayTotalItemManager;
	@Resource
	private SalesMonthTotalItemJpaDao salesMonthTotalItemJpaDao;
	@Resource
	private SalesMonthTotalItemMyBatisDao salesMonthTotalItemMyBatisDao;
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesDayTotalJpaDao salesDayTotalJpaDao;
	@Resource
	private CashDailyMyBatisDao cashDailyMyBatisDao;
	@Resource
	private MonthSaleTargetJpaDao monthSaleTargetJpaDao;

	private final static String XML_CONFIG_SALES_MONTH = "/excel/Sales_Month_Template.xlsx";

	/**
	 * 门店月销售计算
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void calOrgSalesMonthTotal() throws ParseException {
		// 取得门店日销售计算-重计算天数
		List<String> optDateList = salesDayTotalItemManager.calOptDate();
		// 取得门店月销售计算-重计算月数
		List<String> _optYMList = calOptYearMonth(optDateList);
		List<Organization> _orgList = orgManager.getSubOrganization();

		for (String _optYM : _optYMList) {
			// 删除指定年月的所有门店销售信息
			salesMonthTotalItemMyBatisDao.delSalesMonthTotalInfo(_optYM);

			for (Organization org : _orgList) {
				SalesDayTotalItem _param = new SalesDayTotalItem();

				String optY = DateUtils.transDateFormat(_optYM, "yyyyMM", "yyyy");
				String optM = DateUtils.transDateFormat(_optYM, "yyyyMM", "MM");

				_param.setOrgId(org.getId());
				_param.setOptDateY(optY);
				_param.setOptDateM(optM);

				// 取得合计实销数量（指定年/月/机构）
				List<SalesDayTotalItem> _monthSaleTotalList = salesDayTotalItemManager.getSumSalesMonthTotalList(_param);

				for (SalesDayTotalItem monthSaleTotal : _monthSaleTotalList) {
					SalesMonthTotalItem _salesMonthTotal = new SalesMonthTotalItem();

					// 机构编号
					_salesMonthTotal.setOrgId(org.getId());
					// 机构资金编号
					_salesMonthTotal.setBranchNo(org.getBwBranchNo());
					// 日期-年月
					_salesMonthTotal.setOptDateYM(optY + optM);
					// 日期-年
					_salesMonthTotal.setOptDateY(optY);
					// 类别编号
					_salesMonthTotal.setItemClsNo(monthSaleTotal.getItemClsNo());
					// 销售数量
					_salesMonthTotal.setSaleQty(monthSaleTotal.getSaleQty());
					// 销售金额
					_salesMonthTotal.setSaleAmt(monthSaleTotal.getSaleAmt());
					// 退货数量
					_salesMonthTotal.setRetQty(monthSaleTotal.getRetQty());
					// 退货金额
					_salesMonthTotal.setRetAmt(monthSaleTotal.getRetAmt());
					// 赠送数量
					_salesMonthTotal.setGiveQty(monthSaleTotal.getGiveQty());
					// 实销数量
					_salesMonthTotal.setSaleRqty(monthSaleTotal.getSaleRqty());
					// 实销金额
					_salesMonthTotal.setSaleRamt(monthSaleTotal.getSaleRamt());
					// 实销价格
					if (monthSaleTotal.getSaleRqty().compareTo(BigDecimal.ZERO) == 0) {
						_salesMonthTotal.setSalePrice(new BigDecimal(0));
					} else {
						_salesMonthTotal.setSalePrice(monthSaleTotal.getSaleRamt().divide(monthSaleTotal.getSaleRqty(), 2,
								BigDecimal.ROUND_UP));
					}

					salesMonthTotalItemJpaDao.save(_salesMonthTotal);
				}
			}
		}
	}

	/**
	 * 取得门店月销售计算-重计算月数
	 * 
	 * @param optDateList
	 * @return
	 */
	private List<String> calOptYearMonth(List<String> optDateList) {
		List<String> _optYMList = new ArrayList<String>();

		for (String _optDate : optDateList) {
			String _optMonth = DateUtils.transDateFormat(_optDate, "yyyyMMdd", "yyyyMM");
			if (!_optYMList.contains(_optMonth)) {
				_optYMList.add(_optMonth);
			}
		}

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int _month = sysConfig.getSalesMonthTotalItemMonths();
		if (_optYMList.size() > _month) {// 从计算月份
			_optYMList = _optYMList.subList(0, _month);
		}

		return _optYMList;
	}

	/**
	 * 取得全部门店指定年份合计销售信息
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalList_ByYear(SalesMonthTotalItem param) {
		return salesMonthTotalItemMyBatisDao.getSalesTotalList_ByYear(param);
	}

	/**
	 * 取得指定门店指定年份合计销售信息
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalList_ByOrgAndYear(SalesMonthTotalItem param) {
		return salesMonthTotalItemMyBatisDao.getSalesTotalList_ByOrgAndYear(param);
	}

	/**
	 * 取得指定门店指定年份合计销售信息(实际销售额，门店填报)
	 * 
	 * @param param
	 * @return
	 */
	public List<SalesMonthTotalItem> getSalesTotalList_ByOrgAndYear_SJ(SalesMonthTotalItem param) {
		return salesMonthTotalItemMyBatisDao.getSalesTotalList_ByOrgAndYear_SJ(param);
	}

	/**
	 * 创建数据下载文件
	 * 
	 * @param optDateYList
	 * @param orgList
	 * @return
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String createReportFile(List<String> optDateYList, List<Organization> orgList) throws ParsePropertyException,
			InvalidFormatException, IOException, ParseException {
		// 各店近4年销售数据
		List<Map<String, Object>> _mapList = getSalesTotalList_ByOrgAndYear_DL(optDateYList, orgList);
		// 全部门店近4年销售数据
		_mapList.add(0, getSalesTotalList_ByYear_DL(optDateYList));

		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("salesList", _mapList);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xlsx";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_SALES_MONTH, map, tmpFilePath);

		return tmpFileName;
	}

	/**
	 * 各店近4年销售数据
	 * 
	 * @param optDateYList
	 * @param _orgList
	 * @return
	 * @throws ParseException
	 */
	private List<Map<String, Object>> getSalesTotalList_ByOrgAndYear_DL(List<String> optDateYList, List<Organization> _orgList)
			throws ParseException {
		List<Map<String, Object>> _mapList = new ArrayList<Map<String, Object>>();
		// 各店近4年销售数据
		for (Organization org : _orgList) {

			List<List<SalesMonthTotalItem>> _salesTotalShowList = new ArrayList<List<SalesMonthTotalItem>>();
			for (String optDateY : optDateYList) {
				SalesMonthTotalItem _param = new SalesMonthTotalItem();
				_param.setOptDateY(optDateY);
				_param.setOrgId(org.getId());
				List<SalesMonthTotalItem> _salesYearTotal = getSalesTotalList_ByOrgAndYear(_param);

				_salesTotalShowList.add(_salesYearTotal);
			}

			_mapList.add(formatFileMap(org.getName(), optDateYList, _salesTotalShowList));
		}
		return _mapList;
	}

	/**
	 * 全部门店近4年销售数据(数据下载)
	 * 
	 * @param optDateYList
	 * @return
	 * @throws ParseException
	 */
	private Map<String, Object> getSalesTotalList_ByYear_DL(List<String> optDateYList) throws ParseException {

		// 全部门店近4年销售数据
		List<List<SalesMonthTotalItem>> _salesTotalShowList = new ArrayList<List<SalesMonthTotalItem>>();

		for (String optDateY : optDateYList) {
			SalesMonthTotalItem _param = new SalesMonthTotalItem();
			_param.setOptDateY(optDateY);

			List<SalesMonthTotalItem> _salesYearTotal = getSalesTotalList_ByYear(_param);

			_salesTotalShowList.add(_salesYearTotal);
		}

		return formatFileMap("EQ+ 合计", optDateYList, _salesTotalShowList);
	}

	/**
	 * 格式化门店近4年销售数据
	 * 
	 * @param orgName
	 * @param optDateYList
	 * @param salesShowList
	 * @return
	 * @throws ParseException
	 */
	private Map<String, Object> formatFileMap(String orgName, List<String> optDateYList, List<List<SalesMonthTotalItem>> salesShowList)
			throws ParseException {
		Map<String, Object> showMap = new HashMap<String, Object>();
		Map<String, BigDecimal> _saleMap = new HashMap<String, BigDecimal>();

		showMap.put("orgName", orgName);

		// 初始化-年
		int _index_y = 1;
		for (String optDateY : optDateYList) {
			showMap.put("y" + _index_y, optDateY);
			_index_y++;
		}
		// 初始化-月销售额合计
		int _currentYear = DateUtils.getCurrentYearNum();
		for (List<SalesMonthTotalItem> list : salesShowList) {

			for (SalesMonthTotalItem sales : list) {
				int _y = new Integer(sales.getOptDateYM().substring(0, 4));
				int _m = new Integer(sales.getOptDateYM().substring(4));

				showMap.put("s_y" + (_currentYear - _y + 1) + "m" + _m, sales.getSaleRamt());
				_saleMap.put(sales.getOptDateYM(), sales.getSaleRamt());
			}
		}
		// 计算环比
		calMoM(_saleMap, showMap);
		// 计算同比
		calYoY(_saleMap, showMap);

		return showMap;
	}

	/**
	 * 计算同比year-on-year
	 * 
	 * @param _saleMap
	 * @param showMap
	 */
	private void calYoY(Map<String, BigDecimal> _saleMap, Map<String, Object> showMap) {
		int _currentYear = DateUtils.getCurrentYearNum();

		for (Entry<String, BigDecimal> entry : _saleMap.entrySet()) {
			String key = entry.getKey();
			BigDecimal value = entry.getValue();

			int _y = new Integer(key.substring(0, 4));
			int _y1 = new Integer(key.substring(0, 4)) - 1;
			String _sm = key.substring(4);
			int _im = new Integer(_sm);

			BigDecimal _yoyValue = _saleMap.get("" + _y1 + _sm);
			if (null == _yoyValue) {
				showMap.put("yoy_y" + (_currentYear - _y + 1) + "m" + _im, "/");
			} else {
				BigDecimal _value = value.subtract(_yoyValue);
				showMap.put("yoy_y" + (_currentYear - _y + 1) + "m" + _im,
						_value.multiply(new BigDecimal(100)).divide(_yoyValue, 2, RoundingMode.UP) + "%");
			}
		}
	}

	/**
	 * 计算环比 month-on-month
	 * 
	 * @param _saleMap
	 * @param showMap
	 * @throws ParseException
	 */
	private void calMoM(Map<String, BigDecimal> _saleMap, Map<String, Object> showMap) throws ParseException {
		int _currentYear = DateUtils.getCurrentYearNum();

		for (Entry<String, BigDecimal> entry : _saleMap.entrySet()) {
			String key = entry.getKey();
			BigDecimal value = entry.getValue();

			int _y = new Integer(key.substring(0, 4));
			int _im = new Integer(key.substring(4));

			BigDecimal _yoyValue = _saleMap.get(DateUtils.getNextDateFormatDate(key, -1, "yyyyMM"));
			if (null == _yoyValue) {
				showMap.put("mom_y" + (_currentYear - _y + 1) + "m" + _im, "/");
			} else {
				BigDecimal _value = value.subtract(_yoyValue);
				showMap.put("mom_y" + (_currentYear - _y + 1) + "m" + _im,
						_value.multiply(new BigDecimal(100)).divide(_yoyValue, 2, RoundingMode.UP) + "%");
			}
		}
	}

	/**
	 * 月销售信息对比(图形)[按品牌]
	 */
	public List<SalesMonthTotalItem> getSalesTotalMonthListByBrand() {
		List<SalesMonthTotalItem> _list = salesMonthTotalItemMyBatisDao.getSalesTotalMonthListByBrand();

		List<SalesMonthTotalItem> _list_13 = salesMonthTotalItemMyBatisDao.getSalesTotalMonthListByBrand_13D();

		_list.addAll(_list_13);
		return _list;
	}

	/**
	 * 月销售信息对比(图形)[按督导]
	 */
	public List<SalesMonthTotalItem> getSalesTotalMonthListByMngUser() {
		List<SalesMonthTotalItem> _list = salesMonthTotalItemMyBatisDao.getSalesTotalMonthListByMngUser();
		return _list;
	}

	/**
	 * 月销售信息对比(列表)[按督导]
	 * 
	 * @return
	 */
	public MngUserStatisticsTotal getMngUserStatisticsInfo() {
		MngUserStatisticsTotal total = new MngUserStatisticsTotal();

		List<Organization> orgList = orgManager.getOpenSubOrganization();
		Map<String, Organization> orgMap = getOpenSubOrganizationMap(orgList);

		// 统计日期
		total.setOptDate(getLastAllOrgCashDailyOptDate(orgList));

		String optDateY = DateUtils.transDateFormat(total.getOptDate(), "yyyyMMdd", "yyyy");
		String optDateM = DateUtils.transDateFormat(total.getOptDate(), "yyyyMMdd", "MM");

		// 取得全部机构截止指定日期的销售流水合计信息
		List<SalesDayTotal> salesDayTotalList = (List<SalesDayTotal>) salesDayTotalJpaDao.findByOptDate(total.getOptDate());
		// 取得全部机构指定日期的销售目标信息
		List<MonthSaleTarget> _saleTargetList = monthSaleTargetJpaDao.findByOptDateYM(optDateY, optDateM);

		// 填充销售流水合计信息
		Map<String, List<MngUserStatisticsOrgDetail>> _mngOrgMap = fillCashDailyInfo(orgList, salesDayTotalList, orgMap);
		// 填充销售目标信息
		fillMonthSaleTargetInfo(_mngOrgMap, _saleTargetList);
		total.setMngOrgMap(_mngOrgMap);

		// 计算合计信息
		calTotalInfo(total);

		// 计算督导统计信息一览
		Map<String, MngUserStatisticsDetail> mngMap = calMngUserStatisticsDetail(_mngOrgMap);
		MngUserStatisticsDetail totalSDetail = calTotal(mngMap);
		// 其他计算
		_calOther(mngMap, totalSDetail);
		total.setsDetailList(mngMap, totalSDetail);

		// 计算最终督导负责的各个门店的统计信息
		formatMngOrgMap(_mngOrgMap, mngMap, total);

		return total;

	}

	/**
	 * 计算最终督导负责的各个门店的统计信息
	 * 
	 * @param _mngOrgMap
	 * @param mngMap
	 * @param total
	 * @param totalSDetail
	 */
	private void formatMngOrgMap(Map<String, List<MngUserStatisticsOrgDetail>> mngOrgMap, Map<String, MngUserStatisticsDetail> mngMap,
			MngUserStatisticsTotal total) {
		for (Entry<String, List<MngUserStatisticsOrgDetail>> entry : mngOrgMap.entrySet()) {

			MngUserStatisticsDetail _sDetail = mngMap.get(entry.getKey());

			List<MngUserStatisticsOrgDetail> _list = entry.getValue();

			for (MngUserStatisticsOrgDetail _orgdetail : _list) {

				// 任务完成店数（区域）
				_orgdetail.setTaskCmpOrgCnt(_sDetail.getTaskCmpOrgCnt());
				// 店任务（预计）贡献率-合计
				_orgdetail.setExpAmtRateTotal(_sDetail.getTaskCmpOrgRate());
				// 当月任务贡献率（店铺）
				BigDecimal _expAmtRate = new BigDecimal("0");
				if (null != _orgdetail.getSaleMonthTargetAmt() & null != _sDetail.getSaleMonthTargetAmt()
						& _orgdetail.getSaleMonthTargetAmt().compareTo(BigDecimal.ZERO) == 1
						& _sDetail.getSaleMonthTargetAmt().compareTo(BigDecimal.ZERO) == 1) {

					_expAmtRate = _orgdetail.getSaleMonthTargetAmt().divide(_sDetail.getSaleMonthTargetAmt(), 4, BigDecimal.ROUND_UP);
					_expAmtRate = _expAmtRate.multiply(new BigDecimal("100"));
					_expAmtRate = _expAmtRate.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_UP);

				}
				_orgdetail.setExpAmtRate(_expAmtRate);
				// 当月销售贡献率（店铺）
				BigDecimal _amtRate = new BigDecimal("0");
				if (null != _orgdetail.getExpMonthAmt() & null != _sDetail.getExpMonthAmt()
						& _orgdetail.getExpMonthAmt().compareTo(BigDecimal.ZERO) == 1
						& _sDetail.getExpMonthAmt().compareTo(BigDecimal.ZERO) == 1) {

					_amtRate = _orgdetail.getExpMonthAmt().divide(_sDetail.getExpMonthAmt(), 4, BigDecimal.ROUND_UP);
					_amtRate = _amtRate.multiply(new BigDecimal("100"));
					_amtRate = _amtRate.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_UP);

				}
				_orgdetail.setAmtRate(_amtRate);
				// 销售贡献指数增幅（店铺）
				_orgdetail.setRatescope(_orgdetail.getAmtRate().subtract(_orgdetail.getExpAmtRate()));
				// 当月销售贡献率(全部)
				BigDecimal _amtRateTotal = new BigDecimal("0");
				if (null != _orgdetail.getExpMonthAmt() & null != total.getExpMonthAmt()
						& _orgdetail.getExpMonthAmt().compareTo(BigDecimal.ZERO) == 1
						& total.getExpMonthAmt().compareTo(BigDecimal.ZERO) == 1) {

					_amtRateTotal = _orgdetail.getExpMonthAmt().divide(total.getExpMonthAmt(), 4, BigDecimal.ROUND_UP);
					_amtRateTotal = _amtRateTotal.multiply(new BigDecimal("100"));
					_amtRateTotal = _amtRateTotal.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_UP);

				}
				_orgdetail.setAmtRateTotal(_amtRateTotal);
			}
		}

	}

	/**
	 * 其他计算
	 * 
	 * @param mngMap
	 * @param totalSDetail
	 */
	private void _calOther(Map<String, MngUserStatisticsDetail> mngMap, MngUserStatisticsDetail totalSDetail) {
		for (Entry<String, MngUserStatisticsDetail> entry : mngMap.entrySet()) {
			MngUserStatisticsDetail _datail = entry.getValue();

			BigDecimal _expAmtRate = new BigDecimal(0);
			if (null != _datail.getSaleMonthTargetAmt() & null != totalSDetail.getSaleMonthTargetAmt()
					& _datail.getSaleMonthTargetAmt().compareTo(BigDecimal.ZERO) == 1
					& totalSDetail.getSaleMonthTargetAmt().compareTo(BigDecimal.ZERO) == 1) {
				_expAmtRate = _datail.getSaleMonthTargetAmt().divide(totalSDetail.getSaleMonthTargetAmt(), 4, BigDecimal.ROUND_UP);
				_expAmtRate = _expAmtRate.multiply(new BigDecimal("100"));
				_expAmtRate = _expAmtRate.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_UP);
			}

			// 当月店任务（预计）贡献率（区域）
			_datail.setExpAmtRate(_expAmtRate);

			BigDecimal _amtRate = new BigDecimal(0);
			if (null != _datail.getExpMonthAmt() & null != totalSDetail.getExpMonthAmt()
					& _datail.getExpMonthAmt().compareTo(BigDecimal.ZERO) == 1
					& totalSDetail.getExpMonthAmt().compareTo(BigDecimal.ZERO) == 1) {
				_amtRate = _datail.getExpMonthAmt().divide(totalSDetail.getExpMonthAmt(), 4, BigDecimal.ROUND_UP);
				_amtRate = _amtRate.multiply(new BigDecimal("100"));
				_amtRate = _amtRate.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_UP);
			}
			// 当月店销售贡献率（区域）
			_datail.setAmtRate(_amtRate);
			// 当月销售贡献指数增幅（区域）
			_datail.setRatescope(_amtRate.subtract(_expAmtRate));

		}
	}

	/**
	 * 计算督导统计信息一览－合计
	 * 
	 * @param mngMap
	 * @return
	 */
	private MngUserStatisticsDetail calTotal(Map<String, MngUserStatisticsDetail> mngMap) {
		MngUserStatisticsDetail _total = new MngUserStatisticsDetail();

		// 店铺数量
		int orgCnt = 0;
		// 任务完成店数（全部）
		int taskCmpOrgCnt = 0;

		for (Entry<String, MngUserStatisticsDetail> entry : mngMap.entrySet()) {
			MngUserStatisticsDetail _detail = entry.getValue();
			// 督导员
			_total.setMngUserId(_detail.getMngUserId());
			// 督导员
			_total.setMngUserName("合计");
			// 店铺数量
			orgCnt += _detail.getOrgCnt();
			// 本月任务
			_total.setSaleMonthTargetAmt(_total.getSaleMonthTargetAmt().add(_detail.getSaleMonthTargetAmt()));
			// 当前已完成
			_total.setAmtByNow(_total.getAmtByNow().add(_detail.getAmtByNow()));
			// 月预计完成
			_total.setExpMonthAmt(_total.getExpMonthAmt().add(_detail.getExpMonthAmt()));
			// 任务完成店数（全部）
			taskCmpOrgCnt += _detail.getTaskCmpOrgCnt();

		}
		// 店铺数量
		_total.setOrgCnt(orgCnt);
		// 任务完成店数（全部）
		_total.setTaskCmpOrgCnt(taskCmpOrgCnt);

		return _total;
	}

	/**
	 * 计算督导统计信息一览
	 * 
	 * @param mngOrgMap
	 * @return
	 */
	private Map<String, MngUserStatisticsDetail> calMngUserStatisticsDetail(Map<String, List<MngUserStatisticsOrgDetail>> mngOrgMap) {
		Map<String, MngUserStatisticsDetail> mngMap = Maps.newHashMap();

		for (Entry<String, List<MngUserStatisticsOrgDetail>> entry : mngOrgMap.entrySet()) {
			MngUserStatisticsDetail sDetail = new MngUserStatisticsDetail();
			mngMap.put(entry.getKey(), sDetail);

			List<MngUserStatisticsOrgDetail> _list = entry.getValue();

			// 店铺数量
			int orgCnt = 0;
			// 任务完成店数（全部）
			int taskCmpOrgCnt = 0;

			for (MngUserStatisticsOrgDetail _orgDetail : _list) {
				// 督导员
				sDetail.setMngUserId(_orgDetail.getMngUserId());
				// 督导员
				sDetail.setMngUserName(_orgDetail.getMngUserName());
				// 店铺数量
				orgCnt++;
				// 本月任务
				sDetail.setSaleMonthTargetAmt(sDetail.getSaleMonthTargetAmt().add(_orgDetail.getSaleMonthTargetAmt()));
				// 当前已完成
				sDetail.setAmtByNow(sDetail.getAmtByNow().add(_orgDetail.getAmtByNow()));
				// 月预计完成
				sDetail.setExpMonthAmt(sDetail.getExpMonthAmt().add(_orgDetail.getExpMonthAmt()));
				// 任务完成店数（全部）
				if (_orgDetail.getTaskGrowthamt().compareTo(BigDecimal.ZERO) == 1) {
					taskCmpOrgCnt++;
				}

			}
			// 店铺数量
			sDetail.setOrgCnt(orgCnt);
			// 任务完成店数（全部）
			sDetail.setTaskCmpOrgCnt(taskCmpOrgCnt);

		}

		return mngMap;
	}

	/**
	 * 计算合计信息
	 * 
	 * @param total
	 */
	private void calTotalInfo(MngUserStatisticsTotal total) {
		// 当月任务额
		BigDecimal monthTaskAmt = new BigDecimal("0");
		// 当前完成额
		BigDecimal monthCompleteAmt = new BigDecimal("0");
		// 预计本月销售
		BigDecimal expMonthAmt = new BigDecimal("0");
		// 统计店铺数量
		int orgCnt = 0;
		// 任务完成店数
		int orgCompleteCnt = 0;

		for (Entry<String, List<MngUserStatisticsOrgDetail>> entry : total.getMngOrgMap().entrySet()) {
			List<MngUserStatisticsOrgDetail> _list = entry.getValue();

			for (MngUserStatisticsOrgDetail _orgDetail : _list) {
				monthTaskAmt = monthTaskAmt.add(_orgDetail.getSaleMonthTargetAmt());
				monthCompleteAmt = monthCompleteAmt.add(_orgDetail.getAmtByNow());
				expMonthAmt = expMonthAmt.add(_orgDetail.getExpMonthAmt());
				orgCnt++;

				if (_orgDetail.getTaskGrowthamt().compareTo(BigDecimal.ZERO) == 1) {
					orgCompleteCnt++;
				}
			}
		}

		total.setMonthTaskAmt(monthTaskAmt);
		total.setMonthCompleteAmt(monthCompleteAmt);
		total.setExpMonthAmt(expMonthAmt);
		total.setOrgCnt(orgCnt);
		total.setOrgCompleteCnt(orgCompleteCnt);

	}

	private Map<String, Organization> getOpenSubOrganizationMap(List<Organization> orgList) {
		Map<String, Organization> map = Maps.newHashMap();
		for (Organization org : orgList) {
			map.put(org.getId(), org);
		}
		return map;
	}

	/**
	 * 填充销售目标信息
	 * 
	 * @param _mngOrgMap
	 * @param _saleTargetList
	 */
	private void fillMonthSaleTargetInfo(Map<String, List<MngUserStatisticsOrgDetail>> _mngOrgMap, List<MonthSaleTarget> _saleTargetList) {
		Map<String, MonthSaleTarget> _map = Maps.newHashMap();

		for (MonthSaleTarget monthSaleTarget : _saleTargetList) {
			_map.put(monthSaleTarget.getOrgId(), monthSaleTarget);
		}

		for (Entry<String, List<MngUserStatisticsOrgDetail>> entry : _mngOrgMap.entrySet()) {
			List<MngUserStatisticsOrgDetail> _list = entry.getValue();

			for (MngUserStatisticsOrgDetail _datail : _list) {
				MonthSaleTarget _mst = _map.get(_datail.getOrgId());

				if (null == _mst) {
					continue;
				}

				// 本月任务
				_datail.setSaleMonthTargetAmt(_mst.getSaleTargetAmt());

			}
		}
	}

	/**
	 * 填充销售流水合计信息
	 * 
	 * @param orgMap
	 * 
	 * @param mngOrgMap
	 * @param salesDayTotal
	 */
	private Map<String, List<MngUserStatisticsOrgDetail>> fillCashDailyInfo(List<Organization> orgList,
			List<SalesDayTotal> salesDayTotalList, Map<String, Organization> orgMap) {
		Map<String, List<MngUserStatisticsOrgDetail>> _mngOrgMap = Maps.newHashMap();

		for (SalesDayTotal salesDayTotal : salesDayTotalList) {
			String mngUserId = getMngUserId(orgList, salesDayTotal.getOrgId());

			List<MngUserStatisticsOrgDetail> _list = _mngOrgMap.get(mngUserId);

			if (null == _list) {
				_list = Lists.newArrayList();
				_mngOrgMap.put(mngUserId, _list);
			}

			_list.add(copyCashDaily(salesDayTotal, orgMap));
		}

		return _mngOrgMap;
	}

	/**
	 * 取得销售流水合计信息
	 * 
	 * @param orgMap
	 * 
	 * @param cashDaily
	 * @return
	 */
	private MngUserStatisticsOrgDetail copyCashDaily(SalesDayTotal salesDayTotal, Map<String, Organization> orgMap) {
		MngUserStatisticsOrgDetail _datail = new MngUserStatisticsOrgDetail();

		Organization org = orgMap.get(salesDayTotal.getOrgId());
		// 督导员
		_datail.setMngUserId(org.getMngUserId());
		// 督导员
		_datail.setMngUserName(org.getMngUserName());
		// 机构
		_datail.setOrgId(salesDayTotal.getOrgId());
		// 当前已完成
		_datail.setAmtByNow(salesDayTotal.getPosAmtByNow());
		// 月预计完成
		_datail.setExpMonthAmt(salesDayTotal.getExpMonthPosAmt());

		return _datail;
	}

	/**
	 * 取得机构对应的督导人员编号
	 * 
	 * @param orgId
	 * @return
	 */
	private String getMngUserId(List<Organization> orgList, String orgId) {
		for (Organization org : orgList) {
			if (org.getId().equals(orgId)) {
				return org.getMngUserId();
			}
		}
		return null;
	}

	/**
	 * 取得全部机构最近一次日结时间
	 * 
	 * @return
	 */
	private String getLastAllOrgCashDailyOptDate(List<Organization> orgList) {

		List<CashDaily> _cntList = cashDailyMyBatisDao.getCashDaily_OrgCnt();

		for (CashDaily cashDaily : _cntList) {
			if (cashDaily.getOrgCnt().intValue() == orgList.size()) {
				return cashDaily.getOptDate();
			}
		}

		return null;
	}

}
