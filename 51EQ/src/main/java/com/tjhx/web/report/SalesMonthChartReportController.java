package com.tjhx.web.report;

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

	@RequestMapping(value = {""})
	public String init_Action(Model model) throws ServletRequestBindingException {
		// 初始化机构下拉菜单
		ReportUtils.initOrgList_NoNRoot(orgManager, model);

		return "report/salesMonthChartReport";

	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) {
		String[] orgIds = ServletRequestUtils.getStringParameters(request, "orgId");

		// 初始化机构下拉菜单
		ReportUtils.initOrgList_NoNRoot(orgManager, model);

		Map<String, SalesMonthTotal_Org_Show> _map = Maps.newHashMap();
		for (String orgId : orgIds) {
			List<SalesMonthTotalItem> subDataList = salesMonthTotalItemMyBatisDao.getSalesTotalMonthList(orgId);

			for (SalesMonthTotalItem item : subDataList) {
				SalesMonthTotal_Org_Show vo = _map.get(item.getOptDateYM());

				if (null == vo) {
					vo = new SalesMonthTotal_Org_Show();
					vo.setOptDateYM(item.getOptDateYM());
				}

				if (item.getOrgId().equals("00001D")) {
					vo.setSaleRamt01(item.getSaleRamt());
				} else if (item.getOrgId().equals("00002D")) {
					vo.setSaleRamt02(item.getSaleRamt());
				} else if (item.getOrgId().equals("00003D")) {
					vo.setSaleRamt03(item.getSaleRamt());
				} else if (item.getOrgId().equals("00004D")) {
					vo.setSaleRamt04(item.getSaleRamt());
				} else if (item.getOrgId().equals("00005D")) {
					vo.setSaleRamt05(item.getSaleRamt());
				} else if (item.getOrgId().equals("00006D")) {
					vo.setSaleRamt06(item.getSaleRamt());
				} else if (item.getOrgId().equals("00007D")) {
					vo.setSaleRamt07(item.getSaleRamt());
				} else if (item.getOrgId().equals("00008D")) {
					vo.setSaleRamt08(item.getSaleRamt());
				} else if (item.getOrgId().equals("00009D")) {
					vo.setSaleRamt09(item.getSaleRamt());
				} else if (item.getOrgId().equals("00010D")) {
					vo.setSaleRamt10(item.getSaleRamt());
				} else if (item.getOrgId().equals("00011D")) {
					vo.setSaleRamt11(item.getSaleRamt());
				} else if (item.getOrgId().equals("00012D")) {
					vo.setSaleRamt12(item.getSaleRamt());
				} else if (item.getOrgId().equals("00013D")) {
					vo.setSaleRamt13(item.getSaleRamt());
				} else if (item.getOrgId().equals("00014D")) {
					vo.setSaleRamt14(item.getSaleRamt());
				} else if (item.getOrgId().equals("00015D")) {
					vo.setSaleRamt15(item.getSaleRamt());
				} else if (item.getOrgId().equals("00016D")) {
					vo.setSaleRamt16(item.getSaleRamt());
				} else if (item.getOrgId().equals("00017D")) {
					vo.setSaleRamt17(item.getSaleRamt());
				} else if (item.getOrgId().equals("00018D")) {
					vo.setSaleRamt18(item.getSaleRamt());
				} else if (item.getOrgId().equals("00019D")) {
					vo.setSaleRamt19(item.getSaleRamt());
				} else if (item.getOrgId().equals("00020D")) {
					vo.setSaleRamt20(item.getSaleRamt());
				} else if (item.getOrgId().equals("00021D")) {
					vo.setSaleRamt21(item.getSaleRamt());
				} else if (item.getOrgId().equals("00022D")) {
					vo.setSaleRamt22(item.getSaleRamt());
				} else if (item.getOrgId().equals("00023D")) {
					vo.setSaleRamt23(item.getSaleRamt());
				} else if (item.getOrgId().equals("00024D")) {
					vo.setSaleRamt24(item.getSaleRamt());
				} else if (item.getOrgId().equals("00025D")) {
					vo.setSaleRamt25(item.getSaleRamt());
				} else if (item.getOrgId().equals("00026D")) {
					vo.setSaleRamt26(item.getSaleRamt());
				} else if (item.getOrgId().equals("00027D")) {
					vo.setSaleRamt27(item.getSaleRamt());
				} else if (item.getOrgId().equals("00028D")) {
					vo.setSaleRamt28(item.getSaleRamt());
				} else if (item.getOrgId().equals("00029D")) {
					vo.setSaleRamt29(item.getSaleRamt());
				} else if (item.getOrgId().equals("00030D")) {
					vo.setSaleRamt30(item.getSaleRamt());
				} else if (item.getOrgId().equals("00031D")) {
					vo.setSaleRamt31(item.getSaleRamt());
				} else if (item.getOrgId().equals("00032D")) {
					vo.setSaleRamt32(item.getSaleRamt());
				} else if (item.getOrgId().equals("00033D")) {
					vo.setSaleRamt33(item.getSaleRamt());
				}
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
		model.addAttribute("dataList", mapper.toJson(_list));
		model.addAttribute("orgIds", Lists.newArrayList(orgIds));
		return "report/salesMonthChartReport";
	}
}
