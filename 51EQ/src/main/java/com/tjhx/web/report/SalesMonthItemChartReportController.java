package com.tjhx.web.report;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesMonthTotalItemMyBatisDao;
import com.tjhx.entity.info.SalesMonthTotalItem;
import com.tjhx.entity.info.SalesMonthTotal_Show;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesMonthItemChartReport")
public class SalesMonthItemChartReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesMonthTotalItemMyBatisDao salesMonthTotalItemMyBatisDao;

	/**
	 * 全部门店近三年销售数据
	 * 
	 * @param optDateYList
	 * @param _orgSumSalesJsonList
	 */
	private void getSalesTotalList_ByYear(List<String> optDateYList, List<String> _orgSumSalesJsonList) {
		JsonMapper mapper = new JsonMapper();
		// 全部门店近三年销售数据
		List<SalesMonthTotal_Show> _salesTotalShowList = new ArrayList<SalesMonthTotal_Show>();
		initSalesTotalShowList(Constants.ROOT_ORG_ID, _salesTotalShowList, optDateYList);
		for (String optDateY : optDateYList) {
			SalesMonthTotalItem _param = new SalesMonthTotalItem();
			_param.setOptDateY(optDateY);
			List<SalesMonthTotalItem> _salesYearTotal = salesMonthTotalItemMyBatisDao.getSalesTotalList_ByYear(_param);

			copyDate2SalesTotalShowList(_salesTotalShowList, _salesYearTotal);
		}
		_orgSumSalesJsonList.add(mapper.toJson(_salesTotalShowList));
	}

	/**
	 * 各店近三年销售数据
	 * 
	 * @param _orgList
	 * @param optDateYList
	 * @param _orgSumSalesJsonList
	 * @param _orgNameList
	 */
	private void getSalesTotalList_ByOrgAndYear(List<Organization> _orgList, List<String> optDateYList,
			List<String> _orgSumSalesJsonList, List<String> _orgNameList) {
		JsonMapper mapper = new JsonMapper();
		// 各店近三年销售数据
		for (Organization org : _orgList) {
			List<SalesMonthTotal_Show> _salesTotalShowList = new ArrayList<SalesMonthTotal_Show>();
			initSalesTotalShowList(org.getId(), _salesTotalShowList, optDateYList);

			for (String optDateY : optDateYList) {
				SalesMonthTotalItem _param = new SalesMonthTotalItem();
				_param.setOptDateY(optDateY);
				_param.setOrgId(org.getId());
				List<SalesMonthTotalItem> _salesYearTotal = salesMonthTotalItemMyBatisDao
						.getSalesTotalList_ByOrgAndYear(_param);

				copyDate2SalesTotalShowList(_salesTotalShowList, _salesYearTotal);
			}

			_orgSumSalesJsonList.add(mapper.toJson(_salesTotalShowList));
			_orgNameList.add(org.getName());
		}

	}

	@RequestMapping(value = "bar_init")
	public String salesDayChartReport1_Action(Model model) throws ParseException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int _yearNum = sysConfig.getSalesMonthTotalShowYearNum();

		List<Organization> _orgList = orgManager.getSubOrganization();
		List<String> optDateYList = calOptDateY(_yearNum);

		List<String> _orgSumSalesJsonList = new ArrayList<String>();
		List<String> _orgNameList = new ArrayList<String>();
		_orgNameList.add("合计");
		// 全部门店近4年销售数据
		getSalesTotalList_ByYear(optDateYList, _orgSumSalesJsonList);
		// 各店近4年销售数据
		getSalesTotalList_ByOrgAndYear(_orgList, optDateYList, _orgSumSalesJsonList, _orgNameList);

		for (int i = 0; i < _yearNum; i++) {
			model.addAttribute("optDateYM" + (i + 1), optDateYList.get(i));
			model.addAttribute("optDateYM" + (i + 1), optDateYList.get(i));
			model.addAttribute("optDateYM" + (i + 1), optDateYList.get(i));
			model.addAttribute("optDateYM" + (i + 1), optDateYList.get(i));
		}

		model.addAttribute("orgSumSalesJsonList", _orgSumSalesJsonList);
		model.addAttribute("orgNameList", _orgNameList);

		return "report/salesMonthItemChartReport_bar";
	}

	private void copyDate2SalesTotalShowList(List<SalesMonthTotal_Show> _salesTotalShowList,
			List<SalesMonthTotalItem> _salesYearTotal) {

		for (SalesMonthTotalItem _salesMonthTotalItem : _salesYearTotal) {
			for (SalesMonthTotal_Show _salesMonthTotalShow : _salesTotalShowList) {
				int equalsRes = _salesMonthTotalShow.myEquals(_salesMonthTotalItem);
				if (0 != equalsRes) {
					_salesMonthTotalShow.copyData(_salesMonthTotalItem, equalsRes);
				}
			}
		}
	}

	private void initSalesTotalShowList(String orgId, List<SalesMonthTotal_Show> _salesTotalShowList,
			List<String> optDateYList) {

		for (int i = 1; i <= 12; i++) {
			SalesMonthTotal_Show _salesTotalShow = new SalesMonthTotal_Show();
			// 机构编号
			_salesTotalShow.setOrgId(orgId);
			// 月份
			_salesTotalShow.setOptDateM(String.format("%02d", i));
			// 年月1
			_salesTotalShow.setOptDateYM1(optDateYList.get(0) + String.format("%02d", i));
			// 年月2
			_salesTotalShow.setOptDateYM2(optDateYList.get(1) + String.format("%02d", i));
			// 年月3
			_salesTotalShow.setOptDateYM3(optDateYList.get(2) + String.format("%02d", i));
			// 年月4
			_salesTotalShow.setOptDateYM4(optDateYList.get(3) + String.format("%02d", i));
			_salesTotalShowList.add(_salesTotalShow);
		}

	}

	/**
	 * 取得月销售信息对比(图形)页面年份显示个数
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDateY(int _yearNum) throws ParseException {
		List<String> _optDateYList = new ArrayList<String>();

		String _now = DateUtils.getCurrentYear();
		_optDateYList.add(_now);

		for (int i = 1; i < _yearNum; i++) {
			_optDateYList.add(DateUtils.getNextYearFormatDate(_now, -i, "yyyy"));
		}

		return _optDateYList;
	}

}
