package com.tjhx.web.info;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.info.OrgWeekSalesRanking;
import com.tjhx.service.info.OrgWeekSalesRankingManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/orgWeekSalesRanking")
public class OrgWeekSalesRankingController extends BaseController {
	@Resource
	private OrgWeekSalesRankingManager orgWeekSalesRankingManager;

	/**
	 * 取得机构销售等级／排名
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) {
		List<OrgWeekSalesRanking> _list = orgWeekSalesRankingManager.getOrgWeekSalesRankingInfo();
		if (null != _list && _list.size() > 0) {
			OrgWeekSalesRanking _tmp = _list.get(0);
			model.addAttribute("optDateWSection", _tmp.getOptDateWSection());
			model.addAttribute("optDateWSection_Last", _tmp.getOptDateWSection_Last());
		}
		model.addAttribute("orgWeekSalesRankingList", _list);

		return "info/orgWeekSalesRankingList";
	}
}
