package com.tjhx.web.report;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		String _now = DateUtils.getCurFormatDate("yyyyMMdd");
		// 同期增长额 Tab
		_init1(_now, model);
		// 增长率折线图Tab初期化
		_init2(_now, model);
		return "report/salesDayChartReport";
	}

	/**
	 * 增长率折线图Tab初期化
	 * 
	 * @param nowDate
	 * @param model
	 * @throws ParseException
	 */
	private void _init2(String nowDate, Model model) throws ParseException {
		List<Organization> _orgList = orgManager.getAllOrganization();
		_orgList.remove(new Organization(Constants.ROOT_ORG_ID));

		model.addAttribute("orgList", _orgList);
	}

	/**
	 * 同期增长额Tab初期化
	 * 
	 * @param now
	 * @param model
	 * @throws ParseException
	 */
	private void _init1(String nowDate, Model model) throws ParseException {
		// 统计日期
		String _optDate = DateUtils.getNextDateFormatDate(nowDate, -1, "yyyyMMdd");

		model.addAttribute("optDate", _optDate);

		// 取得每日各店销售汇总（根据日期）
		List<SalesDayTotal> salesDayTotalList = salesDayTotalManager.getSalesDayTotalListByOptDate(_optDate);

		model.addAttribute("salesDayTotalList", salesDayTotalList);
	}
}
