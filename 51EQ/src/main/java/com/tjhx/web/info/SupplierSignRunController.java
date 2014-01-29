package com.tjhx.web.info;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.SupplierSignRun;
import com.tjhx.entity.info.SupplierSignRun_Show;
import com.tjhx.globals.Constants;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.service.info.SupplierSignRunManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/supplierSignRun")
public class SupplierSignRunController extends BaseController {
	@Resource
	private SupplierSignRunManager supplierSignRunManager;
	@Resource
	private SupplierManager supplierManager;

	private void initYearList(Model model) {
		Map<String, String> yearList = new LinkedHashMap<String, String>();

		yearList.put("", "");
		yearList.put("2013", "2013");
		yearList.put("2014", "2014");
		yearList.put("2015", "2015");

		model.addAttribute("yearList", yearList);
	}

	/**
	 * 取得特殊标记-货品供应商页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) {
		model.addAttribute("showFlg", false);

		initYearList(model);

		return "info/supplierSignRunList";
	}

	/**
	 * 显示特殊标记-货品供应商信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "show")
	public String show_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		model.addAttribute("showFlg", true);

		initYearList(model);

		String optDateY = ServletRequestUtils.getStringParameter(request, "optDateY");
		model.addAttribute("optDateY", optDateY);

		List<SupplierSignRun_Show> _supSignRunList = supplierSignRunManager.getSupplierSignRunList(optDateY);

		model.addAttribute("supSignRunList", _supSignRunList);
		return "info/supplierSignRunList";
	}

	/**
	 * 特殊标记-货品供应商信息编辑初始化
	 * 
	 * @param supId
	 * @param optDateY
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editInit/{supId}/{optDateY}")
	public String edit_Action(@PathVariable("supId") String supId, @PathVariable("optDateY") String optDateY,
			Model model) {
		model.addAttribute("supId", supId);
		model.addAttribute("optDateY", optDateY);
		model.addAttribute("supName", supplierManager.getSupplierByBwId(supId).getName());

		return "info/supplierSignRunForm";
	}

	/**
	 * 取得特殊标记-货品供应商信息
	 * 
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@ResponseBody
	@RequestMapping(value = "getRunInfo")
	public String getRunInfo(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		// 供应商编号-百威
		String supplierBwId = ServletRequestUtils.getStringParameter(request, "supplierBwId");
		// 日期-年
		String optDateY = ServletRequestUtils.getStringParameter(request, "optDateY");
		// 日期-月
		String optDateM = ServletRequestUtils.getStringParameter(request, "optDateM");
		System.out.println("optDateM " + optDateM);
		// 流水类型
		String runType = ServletRequestUtils.getStringParameter(request, "runType");
		SupplierSignRun _dbRun = supplierSignRunManager.findSupplierSignRunByNaturalId(supplierBwId, optDateY,
				optDateM, runType);

		if (null != _dbRun && StringUtils.isNotBlank(_dbRun.getCheckNoticeDate())) {
			_dbRun.setCheckNoticeDate(DateUtils.transDateFormat(_dbRun.getCheckNoticeDate(), "yyyyMMdd", "yyyy-MM-dd"));
		}
		if (null != _dbRun && StringUtils.isNotBlank(_dbRun.getCheckDate())) {
			_dbRun.setCheckDate(DateUtils.transDateFormat(_dbRun.getCheckDate(), "yyyyMMdd", "yyyy-MM-dd"));
		}
		if (null != _dbRun && StringUtils.isNotBlank(_dbRun.getPaymentDate())) {
			_dbRun.setPaymentDate(DateUtils.transDateFormat(_dbRun.getPaymentDate(), "yyyyMMdd", "yyyy-MM-dd"));
		}
		if (null != _dbRun && StringUtils.isNotBlank(_dbRun.getAppDate())) {
			_dbRun.setAppDate(DateUtils.transDateFormat(_dbRun.getAppDate(), "yyyyMMdd", "yyyy-MM-dd"));
		}
		if (null != _dbRun && StringUtils.isNotBlank(_dbRun.getConfirmDate())) {
			_dbRun.setConfirmDate(DateUtils.transDateFormat(_dbRun.getConfirmDate(), "yyyyMMdd", "yyyy-MM-dd"));
		}

		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_dbRun);
	}

	/**
	 * 对账通知保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "noticeSave")
	public String noticeSave_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		SupplierSignRun run = new SupplierSignRun();

		BeanUtils.populate(run, request.getParameterMap());
		run.setCheckNoticeDate(DateUtils.transDateFormat(run.getCheckNoticeDate(), "yyyy-MM-dd", "yyyyMMdd"));

		supplierSignRunManager.saveRunInfo(run);

		// TODO
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplierSignRun/init";
	}

	/**
	 * 对账完成保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "checkSave")
	public String checkSave_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		SupplierSignRun run = new SupplierSignRun();

		BeanUtils.populate(run, request.getParameterMap());
		run.setCheckDate(DateUtils.transDateFormat(run.getCheckDate(), "yyyy-MM-dd", "yyyyMMdd"));

		supplierSignRunManager.saveRunInfo(run);

		// TODO
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplierSignRun/init";
	}

	/**
	 * 结算付款保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "paymentSave")
	public String paymentSave_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		SupplierSignRun run = new SupplierSignRun();

		BeanUtils.populate(run, request.getParameterMap());
		run.setPaymentDate(DateUtils.transDateFormat(run.getPaymentDate(), "yyyy-MM-dd", "yyyyMMdd"));

		supplierSignRunManager.saveRunInfo(run);

		// TODO
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplierSignRun/init";
	}

	/**
	 * 退货申请保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "appSave")
	public String appSave_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		SupplierSignRun run = new SupplierSignRun();

		BeanUtils.populate(run, request.getParameterMap());
		run.setAppDate(DateUtils.transDateFormat(run.getAppDate(), "yyyy-MM-dd", "yyyyMMdd"));

		supplierSignRunManager.saveRunInfo(run);

		// TODO
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplierSignRun/init";
	}

	@RequestMapping(value = "confirmSave")
	public String confirmSave_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		SupplierSignRun run = new SupplierSignRun();

		BeanUtils.populate(run, request.getParameterMap());
		run.setConfirmDate(DateUtils.transDateFormat(run.getConfirmDate(), "yyyy-MM-dd", "yyyyMMdd"));

		supplierSignRunManager.saveRunInfo(run);

		// TODO
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplierSignRun/init";
	}
}
