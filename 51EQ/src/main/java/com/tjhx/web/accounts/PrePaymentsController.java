package com.tjhx.web.accounts;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.PrePayments;
import com.tjhx.globals.Constants;
import com.tjhx.service.accounts.PrePaymentsManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/prePayments")
public class PrePaymentsController extends BaseController {
	@Resource
	private PrePaymentsManager prePaymentsManager;

	/**
	 * 初始化上班类型列表
	 * 
	 * @param model
	 */
	private void initJobTypeList(Model model) {

		Map<String, String> jobTypeList = new LinkedHashMap<String, String>();
		jobTypeList.put("", "");
		jobTypeList.put("1", "早班");
		jobTypeList.put("2", "晚班");
		jobTypeList.put("4", "全天班");

		model.addAttribute("jobTypeList", jobTypeList);
	}

	/**
	 * 取得顾客/会员预付款（充值、消费）信息列表
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = { "list", "" })
	public String prePaymentsList_Action(Model model, HttpServletRequest request, HttpSession session)
			throws ServletRequestBindingException {
		String optDateShow = DateUtils.getCurrentDateShortStr();

		String orgId = getUserInfo(session).getOrganization().getId();

		List<PrePayments> prePaymentsList = prePaymentsManager.getPrePaymentsList(orgId, optDateShow, optDateShow, null);

		model.addAttribute("prePaymentsList", prePaymentsList);

		model.addAttribute("optDateShow_start", DateUtils.transDateFormat(optDateShow, "yyyyMMdd", "yyyy-MM-dd"));
		model.addAttribute("optDateShow_end", DateUtils.transDateFormat(optDateShow, "yyyyMMdd", "yyyy-MM-dd"));

		return "accounts/prePaymentsList";
	}

	/**
	 * 取得顾客/会员预付款（充值、消费）信息列表
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request, HttpSession session) throws ParseException,
			ServletRequestBindingException {

		String optDateShowStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShowEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String phoneNum = ServletRequestUtils.getStringParameter(request, "phoneNum");

		String orgId = getUserInfo(session).getOrganization().getId();

		List<PrePayments> prePaymentsList = prePaymentsManager.getPrePaymentsList(orgId,
				DateUtils.transDateFormat(optDateShowStart, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDateShowEnd, "yyyy-MM-dd", "yyyyMMdd"), phoneNum);

		model.addAttribute("prePaymentsList", prePaymentsList);

		model.addAttribute("optDateShow_start", optDateShowStart);
		model.addAttribute("optDateShow_end", optDateShowEnd);
		model.addAttribute("phoneNum", phoneNum);

		return "accounts/prePaymentsList";
	}

	/**
	 * 消费信息
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "consumption")
	public String consumption_Action(Model model) {
		PrePayments _p = new PrePayments();
		_p.setAmt(null);
		model.addAttribute("prePayments", _p);

		initJobTypeList(model);

		return "accounts/PrePaymentsForm_consumption";
	}

	/**
	 * 充值信息
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "recharge")
	public String recharge_Action(Model model) {
		PrePayments _p = new PrePayments();
		_p.setAmt(null);
		model.addAttribute("prePayments", _p);

		initJobTypeList(model);

		return "accounts/PrePaymentsForm_recharge";
	}

	/**
	 * 删除PrePayments信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delPrePayments_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			prePaymentsManager.delPrePaymentsByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/prePayments/list";
	}

	/**
	 * 新增顾客/会员预付款（充值、消费）信息
	 * 
	 * @param prePayments
	 * @return
	 */
	@RequestMapping(value = "save")
	public String savePrePayments_Action(@ModelAttribute("prePayments") PrePayments prePayments) {

		/** 日期 */
		prePayments.setOptDate(DateUtils.transDateFormat(prePayments.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd"));
		/** 日期-年 */
		prePayments.setOptDateY(DateUtils.transDateFormat(prePayments.getOptDateShow(), "yyyy-MM-dd", "yyyy"));
		/** 日期-月 */
		prePayments.setOptDateM(DateUtils.transDateFormat(prePayments.getOptDateShow(), "yyyy-MM-dd", "MM"));

		prePaymentsManager.addNewPrePayments(prePayments);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/prePayments/list";
	}
}