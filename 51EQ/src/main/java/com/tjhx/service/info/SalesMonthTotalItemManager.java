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

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesDayTotalItemJpaDao;
import com.tjhx.dao.info.SalesDayTotalItemMyBatisDao;
import com.tjhx.dao.info.SalesMonthTotalItemJpaDao;
import com.tjhx.dao.info.SalesMonthTotalItemMyBatisDao;
import com.tjhx.entity.info.SalesDayTotalItem;
import com.tjhx.entity.info.SalesMonthTotalItem;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

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

	private final static String XML_CONFIG_SALES_MONTH = "/excel/Sales_Month_Template.xls";

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

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
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
	private Map<String, Object> formatFileMap(String orgName, List<String> optDateYList,
			List<List<SalesMonthTotalItem>> salesShowList) throws ParseException {
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

}
