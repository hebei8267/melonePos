package com.tjhx.web.accounts;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.MonthSaleTarget;
import com.tjhx.entity.info.SalesMonthTotalItem;
import com.tjhx.entity.info.SalesMonthTotal_Show;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.accounts.MonthSaleTargetManager;
import com.tjhx.service.info.SalesMonthTotalItemManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/monthSaleTarget")
public class MonthSaleTargetController extends BaseController {
	@Resource
	private MonthSaleTargetManager monthSaleTargetManager;
	@Resource
	private SalesMonthTotalItemManager salesMonthTotalItemManager;
	@Resource
	private OrganizationManager orgManager;

	private void initYearList(Model model) {
		model.addAttribute("yearList", getOptYearList());
	}

	/**
	 * 初始化月销售目标页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		initYearList(model);

		return "accounts/monthSaleTarget";
	}

	@RequestMapping(value = "show")
	public String show_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException,
			IllegalAccessException, InvocationTargetException, ParseException {
		model.addAttribute("showFlg", true);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		initYearList(model);

		String optDateY = ServletRequestUtils.getStringParameter(request, "optDateY");
		model.addAttribute("optDateY", optDateY);
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);

		// 机构信息
		List<String> orgNameList = new ArrayList<String>();
		model.addAttribute("orgNameList", orgNameList);

		// 取得机构去年月销售信息
		List<List<SalesMonthTotal_Show>> _list = getLastYearSales(orgId, optDateY, orgNameList);
		model.addAttribute("lastYearSalesList", _list);

		// 取得机构本年与销售目标信息
		getCurrentYearSales(orgId, optDateY, _list);

		return "accounts/monthSaleTarget";
	}

	/**
	 * 取得机构本年月销售信息
	 * 
	 * @param orgId
	 * @param optDateY
	 * @throws ParseException
	 */
	private void getCurrentYearSales(String orgId, String optDateY, List<List<SalesMonthTotal_Show>> _list) throws ParseException {
		if (StringUtils.isNotBlank(orgId)) {// 单机构本年月销售信息

			List<SalesMonthTotal_Show> _salesTotalShowList = _list.get(0);

			// 取得月销售目标信息(根据机构编号/年份)
			List<MonthSaleTarget> _monthSaleTargetList = monthSaleTargetManager.getByOrgIdAndOptDateY(orgId, optDateY);

			// DB数据导入显示列表（机构本年月销售信息）
			copySalesTotalShowList2(_salesTotalShowList, _monthSaleTargetList);
		} else {// 所有门店机构本年月销售信息
			List<Organization> _orgList = orgManager.getSubOrganization();

			int index = 0;
			for (Organization org : _orgList) {

				List<SalesMonthTotal_Show> _salesTotalShowList = _list.get(index);

				// 取得月销售目标信息(根据机构编号/年份)
				List<MonthSaleTarget> _monthSaleTargetList = monthSaleTargetManager.getByOrgIdAndOptDateY(org.getId(), optDateY);

				// DB数据导入显示列表（机构本年月销售信息）
				copySalesTotalShowList2(_salesTotalShowList, _monthSaleTargetList);

				index++;
			}
		}
	}

	/**
	 * 取得机构去年月销售信息
	 * 
	 * @param orgId
	 * @param optDateY
	 * @throws ParseException
	 */
	private List<List<SalesMonthTotal_Show>> getLastYearSales(String orgId, String optDateY, List<String> orgNameList)
			throws ParseException {
		List<List<SalesMonthTotal_Show>> _list = new ArrayList<List<SalesMonthTotal_Show>>();
		if (StringUtils.isNotBlank(orgId)) {// 单机构去年月销售信息
			SalesMonthTotalItem _param = new SalesMonthTotalItem();
			_param.setOrgId(orgId);
			_param.setOptDateY(DateUtils.getNextYearFormatDate(optDateY, -1, "yyyy"));

			// 取得指定门店指定年份合计销售信息
			List<SalesMonthTotalItem> _salesTotalList = salesMonthTotalItemManager.getSalesTotalList_ByOrgAndYear(_param);

			List<SalesMonthTotal_Show> _salesTotalShowList = initSalesTotalShowList(orgId, optDateY);

			copySalesTotalShowList1(_salesTotalShowList, _salesTotalList);

			_list.add(_salesTotalShowList);
			orgNameList.add(orgId);
		} else {// 所有门店机构去年月销售信息
			List<Organization> _orgList = orgManager.getSubOrganization();

			for (Organization organization : _orgList) {
				SalesMonthTotalItem _param = new SalesMonthTotalItem();
				_param.setOrgId(organization.getId());
				_param.setOptDateY(DateUtils.getNextYearFormatDate(optDateY, -1, "yyyy"));

				// 取得指定门店指定年份合计销售信息
				List<SalesMonthTotalItem> _salesTotalList = salesMonthTotalItemManager.getSalesTotalList_ByOrgAndYear(_param);

				List<SalesMonthTotal_Show> _salesTotalShowList = initSalesTotalShowList(orgId, optDateY);

				copySalesTotalShowList1(_salesTotalShowList, _salesTotalList);

				_list.add(_salesTotalShowList);
				orgNameList.add(organization.getId());
			}
		}

		return _list;
	}

	/**
	 * 初始化显示列表（机构去年月销售信息）
	 * 
	 * @param orgId
	 * @param optDateY
	 * @param _salesTotalShowList
	 * @throws ParseException
	 */
	private List<SalesMonthTotal_Show> initSalesTotalShowList(String orgId, String optDateY) throws ParseException {
		List<SalesMonthTotal_Show> _salesTotalShowList = new ArrayList<SalesMonthTotal_Show>();

		for (int i = 1; i <= 12; i++) {
			SalesMonthTotal_Show _salesTotalShow = new SalesMonthTotal_Show();
			// 机构编号
			_salesTotalShow.setOrgId(orgId);
			// 年月1
			_salesTotalShow.setOptDateYM1(DateUtils.getNextYearFormatDate(optDateY, -1, "yyyy") + String.format("%02d", i));
			// 去年同期销售额
			_salesTotalShow.setSaleRamt2(null);

			_salesTotalShowList.add(_salesTotalShow);
		}

		return _salesTotalShowList;
	}

	/**
	 * DB数据导入显示列表（机构去年月销售信息）
	 * 
	 * @param _salesTotalShowList
	 * @param _salesTotalList
	 */
	private void copySalesTotalShowList1(List<SalesMonthTotal_Show> _salesTotalShowList, List<SalesMonthTotalItem> _salesTotalList) {
		for (SalesMonthTotal_Show _show : _salesTotalShowList) {
			for (SalesMonthTotalItem _sale : _salesTotalList) {
				if (_show.getOptDateYM1().equals(_sale.getOptDateYM())) {
					_show.setSaleRamt1(_sale.getSaleRamt());
				}
			}
		}
	}

	/**
	 * DB数据导入显示列表（机构本年月销售信息）
	 * 
	 * @param _salesTotalShowList
	 * @param _salesTotalList
	 * @throws ParseException
	 */
	private void copySalesTotalShowList2(List<SalesMonthTotal_Show> _salesTotalShowList, List<MonthSaleTarget> _monthSaleTarget)
			throws ParseException {
		for (SalesMonthTotal_Show _show : _salesTotalShowList) {
			for (MonthSaleTarget _sale : _monthSaleTarget) {
				if (_show.getOptDateYM1().equals(
						DateUtils.getNextYearFormatDate(_sale.getOptDateY(), -1, "yyyy") + _sale.getOptDateM())) {
					_show.setSaleRamt2(_sale.getSaleTargetAmt());
				}
			}
		}
	}

	@RequestMapping(value = "save")
	public String save_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String _orgId[] = ServletRequestUtils.getStringParameters(request, "_orgId");
		String saleRamtTarget[] = ServletRequestUtils.getStringParameters(request, "saleRamtTarget");

		String optDateY = ServletRequestUtils.getStringParameter(request, "optDateY");
		model.addAttribute("optDateY", optDateY);
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);

		List<MonthSaleTarget> newObjList = new ArrayList<MonthSaleTarget>();
		for (int i = 0; i < _orgId.length; i++) {

			for (int j = 0; j < 12; j++) {
				MonthSaleTarget m = new MonthSaleTarget();

				// 机构编号
				m.setOrgId(_orgId[i]);
				// 年份
				m.setOptDateY(optDateY);
				// 月份
				m.setOptDateM(String.format("%02d", (j + 1)));
				// 月销售目标额
				if (StringUtils.isNotBlank(saleRamtTarget[i * 12 + j]) && Double.parseDouble(saleRamtTarget[i * 12 + j]) != 0) {
					m.setSaleTargetAmt(new BigDecimal(saleRamtTarget[i * 12 + j]));

					newObjList.add(m);
				}
			}
		}

		// 添加新月销售目标信息
		monthSaleTargetManager.saveMonthSaleTarget(orgId, optDateY, newObjList);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/monthSaleTarget/show";
	}

}