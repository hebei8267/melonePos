package com.tjhx.web.report;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * 初期化
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
		_init1(_optDate, model);

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
	public String search_tab1_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException,
			ParseException {
		String _optDate = ServletRequestUtils.getStringParameter(request, "optDate");
		if (StringUtils.isNotBlank(_optDate)) {
			_optDate = DateUtils.transDateFormat(_optDate, "yyyy-MM-dd", "yyyyMMdd");
		}
		// 同期增长额 Tab
		_init1(_optDate, model);

		return "report/salesDayChartReport_tab1";
	}

	/**
	 * 同期增长额Tab处理
	 * 
	 * @param optDate
	 * @param model
	 * @throws ParseException
	 */
	private void _init1(String optDate, Model model) throws ParseException {
		model.addAttribute("optDate", optDate);
		// 取得每日各店销售汇总（根据日期）
		List<SalesDayTotal> salesDayTotalList = salesDayTotalManager.getSalesDayTotalListByOptDate(optDate);
		model.addAttribute("salesDayTotalList", salesDayTotalList);
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
	public String init_tab2_Action(Model model) throws ParseException, IllegalAccessException,
			InvocationTargetException {
		String _now = DateUtils.getCurFormatDate("yyyyMMdd");
		// 统计日期
		String _optDate = DateUtils.getNextDateFormatDate(_now, -1, "yyyyMMdd");
		model.addAttribute("optDate", _optDate);

		List<Organization> _orgList = orgManager.getAllOrganization();
		_orgList.remove(new Organization(Constants.ROOT_ORG_ID));
		model.addAttribute("orgList", _orgList);

		// 取得指定时间区间（近30天）
		List<String> _optDateList = calOptDate(_optDate);
		model.addAttribute("optDateList", _optDateList);
		
		// 取得指定机构近期(近30天)的销售增长率
		List<List<SalesDayTotal>> _posAmtRateList = initPosAmtRateList(_optDateList, _orgList);

		model.addAttribute("posAmtRateList", _posAmtRateList);

		return "report/salesDayChartReport_tab2";
	}

	private void copyProperties(SalesDayTotal destObj, List<SalesDayTotal> dbPosAmtRateList)
			throws IllegalAccessException, InvocationTargetException {

		for (SalesDayTotal _dbObj : dbPosAmtRateList) {
			if (_dbObj.equals(destObj)) {
				BeanUtils.copyProperties(destObj, _dbObj);
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
	 * 取得指定时间区间（近30天）
	 * 
	 * @param optDate
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDate(String optDate) throws ParseException {
		List<String> _optDateList = new ArrayList<String>();
		_optDateList.add(optDate);
		for (int i = 1; i < 31; i++) {
			String _optDate = DateUtils.getNextDateFormatDate(optDate, -i, "yyyyMMdd");

			_optDateList.add(_optDate);
		}

		return _optDateList;
	}

}
