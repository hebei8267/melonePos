/**
 * 
 */
package com.tjhx.web.affair;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.RunInspect;
import com.tjhx.entity.affair.RunInspectDetail;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.RunInspectManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

/**
 * 门店巡查报告(运营)
 */
@Controller
@RequestMapping(value = "/runInspect")
public class RunInspectController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private RunInspectManager runInspectManager;

	/**
	 * 初始化门店巡查报告(运营)信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list_Action(Model model) {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);
		return "affair/runInspectList";
	}

	/**
	 * 删除门店巡查报告(运营)信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String del_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			runInspectManager.delRunInspectByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/runInspect/list";
	}

	/**
	 * 取得门店巡查报告(运营)信息列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		List<RunInspect> _list = runInspectManager.getRunInspectList(orgId,
				DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"));
		model.addAttribute("runInspectList", _list);

		return "affair/runInspectList";
	}

	/**
	 * 编辑销售流水信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}")
	public String view_Action(@PathVariable("id") Integer id, Model model) {
		RunInspect runInspect = runInspectManager.getRunInspectByUuid(id);
		if (null == runInspect) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/runInspect/list";
		} else {
			getRunInspectInfo(runInspect, model);

			return "affair/runInspectViewForm";
		}
	}

	private void getRunInspectInfo(RunInspect runInspect, Model model) {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		model.addAttribute("uuid", runInspect.getUuid());
		// 店铺
		model.addAttribute("orgId", runInspect.getOrgId());
		// 当班负责人
		model.addAttribute("dutyPerson", runInspect.getDutyPerson());
		// 评核日期
		model.addAttribute("optDateShow", DateUtils.transDateFormat(runInspect.getOptDate(), "yyyyMMdd", "yyyy-MM-dd"));
		// 评核员
		model.addAttribute("assessors", runInspect.getAssessors());
		// 收银台礼仪-得分
		model.addAttribute("score1", runInspect.getScore1());
		// 卖场巡检-得分
		model.addAttribute("score2", runInspect.getScore2());
		// 意见或建议-收银台礼仪
		model.addAttribute("comments", runInspect.getComments());
		// 意见或建议-卖场巡检
		model.addAttribute("comments2", runInspect.getComments2());
		// 店铺反馈问题及跟进
		model.addAttribute("feedback", runInspect.getFeedback());
		// 货品问题的发现及跟进
		model.addAttribute("goodsIssue", runInspect.getGoodsIssue());
		// 现场违纪违规及处罚情况
		model.addAttribute("penaltyCase", runInspect.getPenaltyCase());
		// 培训统计
		model.addAttribute("trainingStatistics", runInspect.getTrainingStatistics());
		// 库存统计
		model.addAttribute("inventoryStatistics", runInspect.getInventoryStatistics());

		init_model_detailList(model, runInspect.getTrsId());
	}

	/**
	 * 编辑销售流水信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String edit_Action(@PathVariable("id") Integer id, Model model) {

		RunInspect runInspect = runInspectManager.getRunInspectByUuid(id);
		if (null == runInspect) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/runInspect/list";
		} else {
			getRunInspectInfo(runInspect, model);

			return "affair/runInspectForm";
		}

	}

	private void init_model_detailList(Model model, String trsId) {
		List<RunInspectDetail> _detailList = runInspectManager.getRunInspectDetailList(trsId);

		for (RunInspectDetail run : _detailList) {
			model.addAttribute("c" + run.getTypeNo() + run.getId(), run.getSelectFlg());
		}
	}

	/**
	 * 新增门店巡查报告(运营)信息初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String new_init_Action(Model model) {

		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);
		model.addAttribute("score1", 0);
		model.addAttribute("score2", 0);
		return "affair/runInspectForm";
	}

	/**
	 * 保存门店巡查报告(运营)信息
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "save")
	public String save_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String uuid = ServletRequestUtils.getStringParameter(request, "uuid");
		String comments = ServletRequestUtils.getStringParameter(request, "comments");
		String comments2 = ServletRequestUtils.getStringParameter(request, "comments2");
		String feedback = ServletRequestUtils.getStringParameter(request, "feedback");
		String goodsIssue = ServletRequestUtils.getStringParameter(request, "goodsIssue");
		String penaltyCase = ServletRequestUtils.getStringParameter(request, "penaltyCase");
		String trainingStatistics = ServletRequestUtils.getStringParameter(request, "trainingStatistics");
		String inventoryStatistics = ServletRequestUtils.getStringParameter(request, "inventoryStatistics");
		// 店铺
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		// 当班负责人
		String dutyPerson = ServletRequestUtils.getStringParameter(request, "dutyPerson");
		// 评核日期
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");
		// 评核员
		String assessors = ServletRequestUtils.getStringParameter(request, "assessors");
		// 收银台礼仪-得分
		int score1 = ServletRequestUtils.getIntParameter(request, "score1", 0);
		// 卖场巡检-得分
		int score2 = ServletRequestUtils.getIntParameter(request, "score2", 0);

		String[] typeNos = ServletRequestUtils.getStringParameters(request, "typeNo");
		String[] ids = ServletRequestUtils.getStringParameters(request, "id");
		boolean[] itemSelect = new boolean[typeNos.length];
		for (int i = 0; i < typeNos.length; i++) {
			itemSelect[i] = ServletRequestUtils.getBooleanParameter(request, "c" + typeNos[i] + ids[i]);
		}

		String optDate = DateUtils.transDateFormat(optDateShow, "yyyy-MM-dd", "yyyyMMdd");

		if (StringUtils.isBlank(uuid)) {// 新增操作
			try {

				runInspectManager.addNewRunInspect(orgId, dutyPerson, optDate, assessors, comments, comments2, feedback,
						goodsIssue, penaltyCase, trainingStatistics, inventoryStatistics, typeNos, ids, itemSelect, score1,
						score2);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/runInspect/new";
			}
		} else {// 修改操作
			try {
				runInspectManager.updateRunInspect(orgId, dutyPerson, optDate, assessors, comments, comments2, feedback,
						goodsIssue, penaltyCase, trainingStatistics, inventoryStatistics, typeNos, ids, itemSelect, score1,
						score2);

			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/runInspect/edit/" + uuid;
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/runInspect/list";
	}

}
