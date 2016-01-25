package com.tjhx.web.report;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.dao.info.SalesMonthTotalItemMyBatisDao;
import com.tjhx.entity.info.SalesMonthTotalItem;
import com.tjhx.entity.info.SalesMonthTotal_Org_Show;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesMonthChartReport")
public class SalesMonthChartReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesMonthTotalItemMyBatisDao salesMonthTotalItemMyBatisDao;

	@RequestMapping(value = { "" })
	public String init_Action(Model model) throws ServletRequestBindingException {
		// 初始化机构下拉菜单
		ReportUtils.initOrgList_NoNRoot(orgManager, model);

		return "report/salesMonthChartReport";

	}

	private void calTotal(List<List<SalesMonthTotalItem>> totalList, Model model, JsonMapper mapper) {

		Map<String, SalesMonthTotal_Org_Show> _map = Maps.newHashMap();
		for (List<SalesMonthTotalItem> subDataList : totalList) {

			for (SalesMonthTotalItem item : subDataList) {
				SalesMonthTotal_Org_Show vo = _map.get(item.getOptDateYM());

				if (null == vo) {
					vo = new SalesMonthTotal_Org_Show();
					vo.setOptDateYM(item.getOptDateYM());

				}

				if (item.getOptDateYM().equals(vo.getOptDateYM())) {
					vo.setSaleTotalRamt(vo.getSaleTotalRamt().add(item.getSaleRamt()));

					if (item.getSaleRamt().compareTo(BigDecimal.ZERO) == 1) {// 大于0
						vo.setOrgCount(vo.getOrgCount() + 1);
					}
				}

				_map.put(item.getOptDateYM(), vo);
			}

		}

		List<SalesMonthTotal_Org_Show> _list = Lists.newArrayList(_map.values());
		Collections.sort(_list, new Comparator<SalesMonthTotal_Org_Show>() {

			@Override
			public int compare(SalesMonthTotal_Org_Show o1, SalesMonthTotal_Org_Show o2) {
				return o1.getOptDateYM().compareTo(o2.getOptDateYM());
			}

		});
		model.addAttribute("totalList", mapper.toJson(_list));
	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) {
		String[] orgIds = ServletRequestUtils.getStringParameters(request, "orgId");

		// 初始化机构下拉菜单
		ReportUtils.initOrgList_NoNRoot(orgManager, model);

		List<List<SalesMonthTotalItem>> totalList = Lists.newArrayList();

		Map<String, SalesMonthTotal_Org_Show> _map = Maps.newHashMap();

		for (String orgId : orgIds) {

			List<SalesMonthTotalItem> subDataList = salesMonthTotalItemMyBatisDao.getSalesTotalMonthList(orgId);
			totalList.add(subDataList);

			for (SalesMonthTotalItem item : subDataList) {
				SalesMonthTotal_Org_Show vo = _map.get(item.getOptDateYM());

				if (null == vo) {
					vo = new SalesMonthTotal_Org_Show();
					vo.setOptDateYM(item.getOptDateYM());
				}

				vo.setSaleRamt(item.getOrgId(), item.getSaleRamt());

				_map.put(item.getOptDateYM(), vo);
			}

		}

		List<SalesMonthTotal_Org_Show> _list = Lists.newArrayList();
		for (Entry<String, SalesMonthTotal_Org_Show> entry : _map.entrySet()) {
			_list.add(entry.getValue());
		}

		Comparator<SalesMonthTotal_Org_Show> comparator = new Comparator<SalesMonthTotal_Org_Show>() {
			public int compare(SalesMonthTotal_Org_Show o1, SalesMonthTotal_Org_Show o2) {
				return Integer.parseInt(o1.getOptDateYM()) > Integer.parseInt(o2.getOptDateYM()) ? 1 : -1;
			}
		};
		Collections.sort(_list, comparator);

		JsonMapper mapper = new JsonMapper();

		List<Map<String, Object>> _dataList = Lists.newArrayList();

		for (SalesMonthTotal_Org_Show sObj : _list) {
			Map<String, Object> _obj = Maps.newHashMap();
			_obj.put("optDateYM", sObj.getOptDateYM());
			_obj.put("saleTotalRamt", sObj.getSaleTotalRamt());
			_obj.put("orgCount", sObj.getOrgCount());

			for (Entry<String, BigDecimal> entry : sObj.getSaleRamtMap().entrySet()) {
				_obj.put(entry.getKey(), entry.getValue());
			}

			_dataList.add(_obj);
		}
		model.addAttribute("dataList", mapper.toJson(_dataList));
		model.addAttribute("orgIds", Lists.newArrayList(orgIds));

		calTotal(totalList, model, mapper);

		return "report/salesMonthChartReport";
	}
}
