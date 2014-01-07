package com.tjhx.web.report;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.StoreRun;
import com.tjhx.service.accounts.StoreRunManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/storeReport")
public class StoreReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private StoreRunManager storeRunManager;

	/**
	 * 取得货物入库流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list", "" })
	public String storeReportList_Action(Model model) throws ServletRequestBindingException {

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "report/storeListReport";
	}

	/**
	 * 取得货物入库流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws ParseException
	 */
	@RequestMapping(value = "search")
	public String storeReportSearch_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String recordDateShow = ServletRequestUtils.getStringParameter(request, "recordDateShow");
		String recordNo = ServletRequestUtils.getStringParameter(request, "recordNo");
		model.addAttribute("orgId", orgId);
		model.addAttribute("recordDateShow", recordDateShow);
		model.addAttribute("recordNo", recordNo);

		StoreRun _storeRun = new StoreRun();
		if (StringUtils.isNotBlank(orgId)) {
			_storeRun.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(recordDateShow)) {
			String recordDateY = DateUtils.transDateFormat(recordDateShow, "yyyy-MM", "yyyy");
			String recordDateM = DateUtils.transDateFormat(recordDateShow, "yyyy-MM", "MM");

			_storeRun.setRecordDateY(recordDateY);
			_storeRun.setRecordDateM(recordDateM);
		}
		if (StringUtils.isNotBlank(recordNo)) {
			_storeRun.setRecordNo(recordNo);
		}

		List<StoreRun> _storeRunList = storeRunManager.searchReportList(_storeRun);
		model.addAttribute("storeRunList", _storeRunList);
		StoreRun totalStoreRun = storeRunManager.calTotal(_storeRunList);
		model.addAttribute("totalStoreRun", totalStoreRun);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "report/storeListReport";
	}

	@RequestMapping(value = "detail/{recordNo}")
	public String storeReportView_Action(@PathVariable("recordNo") String recordNo, Model model) {
		StoreRun _storeRun = new StoreRun();
		if (StringUtils.isNotBlank(recordNo)) {
			_storeRun.setRecordNo(recordNo);
		}

		List<StoreRun> _storeRunList = storeRunManager.searchReportList(_storeRun);
		if (null != _storeRunList && _storeRunList.size() > 0) {
			model.addAttribute("storeRun", _storeRunList.get(0));
		}

		return "report/storeDetailReport";
	}
}
