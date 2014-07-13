package com.tjhx.web.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.order.CouponManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cashReport")
public class CashReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private CashDailyManager cashDailyManager;
	@Resource
	private CouponManager couponManager;

	@RequestMapping(value = { "list", "" })
	public String cashReportList_Action(Model model) throws ServletRequestBindingException {

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		model.addAttribute("DEFAULT_RETAINED_AMT", sysConfig.getDefaultRetainedAmt());

		return "report/cashListReport";
	}

	private void commonProcess(Model model, HttpServletRequest request, CashDaily _cashDaily)
			throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		if (StringUtils.isNotBlank(orgId)) {
			_cashDaily.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(optDateShow_start)) {
			_cashDaily.setOptDateStart(DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (StringUtils.isNotBlank(optDateShow_end)) {
			_cashDaily.setOptDateEnd(DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"));
		}
	}

	private void commonProcess(Model model, HttpServletRequest request, CashRun _cashRun) throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		if (StringUtils.isNotBlank(orgId)) {
			_cashRun.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(optDateShow_start)) {
			_cashRun.setOptDateStart(DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (StringUtils.isNotBlank(optDateShow_end)) {
			_cashRun.setOptDateEnd(DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"));
		}
	}

	/**
	 * 文件导出-合计
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	@RequestMapping(value = "export2")
	public void cashReportExport2_Action(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException, ParsePropertyException, InvalidFormatException, IOException {
		CashDaily _cashDaily = new CashDaily();
		commonProcess(model, request, _cashDaily);

		String downLoadFileName = cashDailyManager.createCashReportFile(_cashDaily);

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
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(downLoadFileName.getBytes("utf-8"), "ISO8859-1"));
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
		return;
	}

	/**
	 * 文件导出-明细
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws ServletRequestBindingException
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "export")
	public void cashReportExport_Action(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException, ParsePropertyException, InvalidFormatException, IOException, ParseException {

		CashRun _cashRun = new CashRun();
		commonProcess(model, request, _cashRun);

		String downLoadFileName = cashDailyManager.createCashReportFile(_cashRun);

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
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(downLoadFileName.getBytes("utf-8"), "ISO8859-1"));
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

	@RequestMapping(value = "search")
	public String cashReportSearch_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {

		CashDaily _cashDaily = new CashDaily();
		commonProcess(model, request, _cashDaily);

		List<CashDaily> _cashDailyList = cashDailyManager.searchReportList(_cashDaily);
		model.addAttribute("cashDailyList", _cashDailyList);
		CashDaily totalCashDaily = cashDailyManager.calTotal_CashDaily(_cashDailyList);
		model.addAttribute("totalCashDaily", totalCashDaily);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		model.addAttribute("DEFAULT_RETAINED_AMT", sysConfig.getDefaultRetainedAmt());
		// 与百威销售额-差额-额度
		model.addAttribute("BW_SALE_DIF_AMOUNT", sysConfig.getBwSaleDifAmount());

		return "report/cashListReport";
	}

	@RequestMapping(value = "detail/{optDate}/{orgId}")
	public String cashReportView_Action(@PathVariable("optDate") String optDate, @PathVariable("orgId") String orgId, Model model) {
		CashRun _cashRun = new CashRun();
		_cashRun.setOptDate(optDate);
		_cashRun.setOrgId(orgId);
		List<CashRun> _list = cashDailyManager.cashDailyDetail(_cashRun);

		if (null != _list && _list.size() > 0) {
			CashRun cashRun1 = _list.get(0);
//			if (StringUtils.isNotBlank(cashRun1.getCouponNo())) {//TODO ？？？？？？？？？？？？？？？
//				cashRun1.setCouponNo(couponManager.getCouponByNoInCache(cashRun1.getCouponNo(), cashRun1.getOrgId()).getName());
//			}
			model.addAttribute("cashRun1", cashRun1);
		}
		if (null != _list && _list.size() > 1) {
			CashRun cashRun2 = _list.get(1);
//			if (StringUtils.isNotBlank(cashRun2.getCouponNo())) {
//				cashRun2.setCouponNo(couponManager.getCouponByNoInCache(cashRun2.getCouponNo(), cashRun2.getOrgId()).getName());
//			}
			model.addAttribute("cashRun2", cashRun2);
		}

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		model.addAttribute("DEFAULT_RETAINED_AMT", sysConfig.getDefaultRetainedAmt());

		return "report/cashDetailReport";
	}
}
