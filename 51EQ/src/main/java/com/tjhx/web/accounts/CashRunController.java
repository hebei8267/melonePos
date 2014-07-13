package com.tjhx.web.accounts;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.entity.info.BankCard;
import com.tjhx.entity.order.Coupon;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.accounts.CashRunManager;
import com.tjhx.service.accounts.PrePaymentsManager;
import com.tjhx.service.info.BankCardManager;
import com.tjhx.service.order.CouponManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cashRun")
public class CashRunController extends BaseController {
	@Resource
	private CashRunManager cashRunManager;
	@Resource
	private BankCardManager bankCardManager;
	@Resource
	private CouponManager couponManager;
	@Resource
	private PrePaymentsManager prePaymentsManager;

	/**
	 * 计算班前余额
	 * 
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "calInitAmt")
	public String calInitAmt_Action(HttpServletRequest request, HttpSession session) throws ServletRequestBindingException,
			ParseException {
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");
		String optDate = DateUtils.transDateFormat(optDateShow, "yyyy-MM-dd", "yyyyMMdd");

		BigDecimal initAmt = cashRunManager.getInitAmt(getUserInfo(session).getOrganization().getId(), optDate);

		return initAmt == null ? "0" : initAmt.toString();
	}

	/**
	 * 计算当班预付款合计
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "calPrePaymentsAmt")
	public String calPrePaymentsAmt_Action(HttpServletRequest request, HttpSession session)
			throws ServletRequestBindingException, ParseException {
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");
		String optDate = DateUtils.transDateFormat(optDateShow, "yyyy-MM-dd", "yyyyMMdd");
		Integer jobType = ServletRequestUtils.getIntParameter(request, "jobType");

		// 取得顾客/会员预付款（现金充值）合计信息
		BigDecimal cashAmt = prePaymentsManager.getOrgPrePaymentsInfo_By_Cash(getUserInfo(session).getOrganization().getId(),
				optDate, jobType);
		// 取得顾客/会员预付款（现金充值）合计信息
		BigDecimal cardAmt = prePaymentsManager.getOrgPrePaymentsInfo_By_Card(getUserInfo(session).getOrganization().getId(),
				optDate, jobType);

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("cashAmt", cashAmt == null ? "0" : cashAmt.toString());
		resultMap.put("cardAmt", cardAmt == null ? "0" : cardAmt.toString());

		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(resultMap);
	}

	// @ResponseBody
	// @RequestMapping(value = "getBankCardNoList")
	// public String getBankCardNoList_Action(HttpServletRequest request) throws
	// ServletRequestBindingException {
	// String bankId = ServletRequestUtils.getStringParameter(request,
	// "bankId");
	// List<BankCard> _list = bankCardManager.getAllBankCard(bankId);
	// return new JsonMapper().toJson(_list);
	// }

	/**
	 * 初始化银行卡列表
	 * 
	 * @param model
	 */
	private void initBankCardList(Model model, String orgId) {
		List<BankCard> _list = bankCardManager.getAllBankCard(orgId);

		Map<String, String> bankCardList = new LinkedHashMap<String, String>();
		bankCardList.put("", "");
		for (BankCard _bankCard : _list) {
			bankCardList.put(_bankCard.getBankCardNo(), _bankCard.getBankCardNo());
		}
		model.addAttribute("bankCardList", bankCardList);
	}

	/**
	 * 取得销售流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list", "" })
	public String cashRunList_Action(Model model, HttpSession session) throws ParseException {
		List<CashRun> cashRunList = cashRunManager.getAllCashRunByOrgId_1(getUserInfo(session).getOrganization().getId(),
				DateUtils.getCurrentDateShortStr());

		model.addAttribute("cashRunList", cashRunList);

		CashRun totalCashRun = cashRunManager.calTotal(cashRunList);
		model.addAttribute("totalCashRun", totalCashRun);

		return "accounts/cashRunList";
	}

	/**
	 * 取得销售流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "list/{date}")
	public String cashRunList_Date_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {
		List<CashRun> cashRunList = cashRunManager.getAllCashRunByOrgId_2(getUserInfo(session).getOrganization().getId(), date);

		model.addAttribute("cashRunList", cashRunList);

		CashRun totalCashRun = cashRunManager.calTotal(cashRunList);
		model.addAttribute("totalCashRun", totalCashRun);

		return "accounts/cashRunList";
	}

	/**
	 * 编辑销售流水信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editCashRun_Action(@PathVariable("id") Integer id, Model model, HttpSession session) {

		CashRun cashRun = cashRunManager.getCashRunByUuid(id);
		if (null == cashRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cashRun/list";
		} else {
			model.addAttribute("cashRun", cashRun);

			initJobTypeList(model);
			initBankCardList(model, getUserInfo(session).getOrganization().getId());

			// 初始化代金卷信息列表
			initCouponList(model, getUserInfo(session).getOrganization().getId());

			return "accounts/cashRunForm";
		}

	}

	/**
	 * 新增销售流水初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initCashRun_Action(Model model, HttpSession session) {

		CashRun cashRun = new CashRun();
		cashRun.setSaleCashAmt(null);
		cashRun.setCashAmt(null);
		cashRun.setCardAmt(null);
		cashRun.setCardAmtBw(null);
		cashRun.setCardNum(null);
		cashRun.setDepositAmt(null);
		cashRun.setReportAmt(null);
		cashRun.setCouponValue(null);
		model.addAttribute("cashRun", cashRun);

		initJobTypeList(model);
		initBankCardList(model, getUserInfo(session).getOrganization().getId());

		// 初始化代金卷信息列表
		initCouponList(model, getUserInfo(session).getOrganization().getId());

		return "accounts/cashRunForm";
	}

	/**
	 * 初始化代金卷信息列表
	 * 
	 * @param model
	 */
	private void initCouponList(Model model, String orgId) {
		List<Coupon> _couponList = couponManager.getCouponListInCache(orgId);

		Map<String, String> couponList = new LinkedHashMap<String, String>();

		couponList.put("", "");
		for (Coupon coupon : _couponList) {
			couponList.put(coupon.getCouponNo(), coupon.getName());
		}

		model.addAttribute("couponList", couponList);
	}

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
	 * 删除销售流水信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delCashRun_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			cashRunManager.delCashRunByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cashRun/list";
	}

	/**
	 * 新增/修改 销售流水信息
	 * 
	 * @param cashRun
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveCashRun_Action(@ModelAttribute("cashRun") CashRun cashRun, Model model, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {
		try {
			cashRunManager.cashDailyCheck(getUserInfo(session).getOrganization().getId(),
					DateUtils.transDateFormat(cashRun.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd"));
		} catch (Exception ex) {
			// 添加错误消息
			addInfoMsg(model, ex.getMessage());

			initJobTypeList(model);
			initBankCardList(model, getUserInfo(session).getOrganization().getId());

			// 初始化代金卷信息列表
			initCouponList(model, getUserInfo(session).getOrganization().getId());

			return "accounts/cashRunForm";
		}

		if (null == cashRun.getUuid()) {// 新增操作
			try {
				cashRunManager.addNewCashRun(cashRun, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initJobTypeList(model);
				initBankCardList(model, getUserInfo(session).getOrganization().getId());

				// 初始化代金卷信息列表
				initCouponList(model, getUserInfo(session).getOrganization().getId());

				return "accounts/cashRunForm";
			}
		} else {// 修改操作
			try {
				cashRunManager.updateCashRun(cashRun, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initJobTypeList(model);
				initBankCardList(model, getUserInfo(session).getOrganization().getId());

				// 初始化代金卷信息列表
				initCouponList(model, getUserInfo(session).getOrganization().getId());

				return "accounts/cashRunForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cashRun/list";
	}
}