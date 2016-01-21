package com.tjhx.web.info;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.info.SupplierSignRun;
import com.tjhx.entity.info.SupplierSignRun_Show;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
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

	private void initYearList(Model model) throws ParseException {
		model.addAttribute("yearList", getOptYearList());
	}

	private void initSupplierList(Model model) {
		List<Supplier> _supList = supplierSignRunManager.getSupplierList();

		Map<String, String> supList = new LinkedHashMap<String, String>();
		supList.put("", "");
		for (Supplier supplier : _supList) {

			supList.put(supplier.getSupplierBwId(), supplier.getName());

			model.addAttribute("supList", supList);
		}
	}

	/**
	 * 取得特殊标记-货品供应商页面
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "init")
	public String init_Action(Model model, HttpServletRequest request) throws ParseException {
		_init(model);

		return "info/supplierSignRunList";
	}

	/**
	 * 取得特殊标记-货品供应商页面
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "init_boss")
	public String init_boss_Action(Model model, HttpServletRequest request) throws ParseException {
		_init(model);

		return "info/supplierSignRunList_Boss";
	}

	private void _init(Model model) throws ParseException {
		model.addAttribute("showFlg", false);

		initYearList(model);
		initSupplierList(model);

		model.addAttribute("optDateY", DateUtils.getCurrentYear());
	}

	/**
	 * 显示特殊标记-货品供应商信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 */
	@RequestMapping(value = "show")
	public String show_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException,
			IllegalAccessException, InvocationTargetException, ParseException {
		_show_Action(model, request);

		return "info/supplierSignRunList";
	}

	/**
	 * 文件导出
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "export")
	public void cashReportExport_Action(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException, ParsePropertyException, InvalidFormatException, IOException,
			IllegalAccessException, InvocationTargetException {
		String optDateY = ServletRequestUtils.getStringParameter(request, "optDateY");
		String supplierBwId = ServletRequestUtils.getStringParameter(request, "supplierBwId");

		String downLoadFileName = supplierSignRunManager.createSupplierSignRunFile(optDateY, supplierBwId);

		if (null == downLoadFileName) {
			return;
		}
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String downLoadPath = sysConfig.getReportTmpPath() + downLoadFileName;

		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(downLoadFileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {

		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	/**
	 * 显示特殊标记-货品供应商信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 */
	@RequestMapping(value = "show_boss")
	public String show_boss_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException,
			IllegalAccessException, InvocationTargetException, ParseException {
		_show_Action(model, request);

		return "info/supplierSignRunList_Boss";
	}

	private void _show_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException,
			IllegalAccessException, InvocationTargetException, ParseException {
		model.addAttribute("showFlg", true);

		initYearList(model);
		initSupplierList(model);

		String optDateY = ServletRequestUtils.getStringParameter(request, "optDateY");
		model.addAttribute("optDateY", optDateY);
		String supplierBwId = ServletRequestUtils.getStringParameter(request, "supplierBwId");
		model.addAttribute("supplierBwId", supplierBwId);

		List<SupplierSignRun_Show> _supSignRunList = supplierSignRunManager.getSupplierSignRunList(optDateY,
				supplierBwId);

		model.addAttribute("supSignRunList", _supSignRunList);
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
	 * 赊购挂账-不挂
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "clean")
	public String clean_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		SupplierSignRun run = new SupplierSignRun();

		BeanUtils.populate(run, request.getParameterMap());

		supplierSignRunManager.delRunInfo(run);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplierSignRun/init";
	}

	/**
	 * 保存特殊标记-货品供应商 流水信息
	 * 
	 * <pre>
	 * 特殊标记-货品供应商 流水类型
	 * 1. 赊购挂账 -挂账
	 * 2. 对账通知
	 * 3. 对账完成
	 * 4. 结算付款
	 * 5. 退货申请
	 * 6. 退货确认
	 * </pre>
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "defaultSave")
	public String defaultSave_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		SupplierSignRun run = new SupplierSignRun();

		BeanUtils.populate(run, request.getParameterMap());
		if ("2".equals(run.getRunType())) {// 对账通知
			run.setCheckNoticeDate(DateUtils.transDateFormat(run.getCheckNoticeDate(), "yyyy-MM-dd", "yyyyMMdd"));
		} else if ("3".equals(run.getRunType())) {// 对账完成
			run.setCheckDate(DateUtils.transDateFormat(run.getCheckDate(), "yyyy-MM-dd", "yyyyMMdd"));
		} else if ("4".equals(run.getRunType())) {// 结算付款
			run.setPaymentDate(DateUtils.transDateFormat(run.getPaymentDate(), "yyyy-MM-dd", "yyyyMMdd"));
		} else if ("5".equals(run.getRunType())) {// 退货申请
			run.setAppDate(DateUtils.transDateFormat(run.getAppDate(), "yyyy-MM-dd", "yyyyMMdd"));
		} else if ("6".equals(run.getRunType())) {// 退货确认
			run.setConfirmDate(DateUtils.transDateFormat(run.getConfirmDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		supplierSignRunManager.saveRunInfo(run);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/supplierSignRun/editInit/" + run.getSupplierBwId()
				+ "/" + run.getOptDateY();
	}

}
