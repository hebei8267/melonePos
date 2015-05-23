package com.tjhx.web.receipt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.receipt.Invoice;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.ServiceException;
import com.tjhx.service.receipt.InvoiceDrawManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/invoiceDraw")
public class InvoiceDrawController extends BaseController {
	@Resource
	private InvoiceDrawManager invoiceDrawManager;
	@Resource
	private OrganizationManager orgManager;

	@RequestMapping(value = { "list", "" })
	public String invoiceDrawList_Action(Model model, HttpSession session) throws ParseException {
		String appDateShow = DateUtils.getCurrentMonth();
		model.addAttribute("appDateShow", appDateShow);

		Invoice _invoice = new Invoice();
		String appDateY = DateUtils.transDateFormat(appDateShow, "yyyy-MM", "yyyy");
		String appDateM = DateUtils.transDateFormat(appDateShow, "yyyy-MM", "MM");

		_invoice.setAppDateY(appDateY);
		_invoice.setAppDateM(appDateM);
		List<Invoice> _invoiceDrawList = invoiceDrawManager.searchInvoiceDrawList(_invoice);

		model.addAttribute("invoiceDrawList", _invoiceDrawList);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "invoice/invoiceDrawList";
	}

	@RequestMapping(value = "search")
	public String invoiceDrawSearchList_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String appDateShow = ServletRequestUtils.getStringParameter(request, "appDateShow");
		String invoiceNum = ServletRequestUtils.getStringParameter(request, "invoiceNum");
		model.addAttribute("orgId", orgId);
		model.addAttribute("appDateShow", appDateShow);
		model.addAttribute("invoiceNum", invoiceNum);

		Invoice _invoice = new Invoice();
		if (StringUtils.isNotBlank(orgId)) {
			_invoice.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(appDateShow)) {
			String appDateY = DateUtils.transDateFormat(appDateShow, "yyyy-MM", "yyyy");
			String appDateM = DateUtils.transDateFormat(appDateShow, "yyyy-MM", "MM");

			_invoice.setAppDateY(appDateY);
			_invoice.setAppDateM(appDateM);
		}
		if (StringUtils.isNotBlank(invoiceNum)) {
			_invoice.setInvoiceNum(invoiceNum);
		}

		List<Invoice> _invoiceDrawList = invoiceDrawManager.searchInvoiceDrawList(_invoice);
		model.addAttribute("invoiceDrawList", _invoiceDrawList);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "invoice/invoiceDrawList";
	}

	@RequestMapping(value = "drawInit/{id}")
	public String invoiceDrawInit_Action(@PathVariable("id") Integer id, Model model) {
		Invoice invoice = null;
		try {
			invoice = invoiceDrawManager.getInvoiceByUuid_MyBatis(id);
		} catch (ServiceException ex) {
			// 添加错误消息
			addInfoMsg(model, ex.getMessage());

			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/invoiceDraw";
		}

		initCompanyList(model);
		model.addAttribute("invoice", invoice);

		return "invoice/invoiceDrawForm";
	}

	/**
	 * 初始化快递公司列表信息
	 * 
	 * @param model
	 */
	public static void initCompanyList(Model model) {

		Map<String, String> companyList = new LinkedHashMap<String, String>();

		companyList.put("", "");
		companyList.put("01", "申通");
		companyList.put("02", "中通");
		companyList.put("03", "顺丰");
		companyList.put("04", "圆通");
		companyList.put("05", "韵达");
		companyList.put("06", "汇通");

		model.addAttribute("companyList", companyList);
	}

	@RequestMapping(value = "draw")
	public String invoiceDraw_Action(@ModelAttribute("invoice") Invoice invoice, Model model, HttpSession session) {

		invoiceDrawManager.updateInvoiceDraw(invoice, getUserInfo(session));

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/invoiceDraw";
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

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String appDateShow = ServletRequestUtils.getStringParameter(request, "appDateShow");
		String invoiceNum = ServletRequestUtils.getStringParameter(request, "invoiceNum");
		model.addAttribute("orgId", orgId);
		model.addAttribute("appDateShow", appDateShow);
		model.addAttribute("invoiceNum", invoiceNum);

		Invoice _invoice = new Invoice();
		if (StringUtils.isNotBlank(orgId)) {
			_invoice.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(appDateShow)) {
			String appDateY = DateUtils.transDateFormat(appDateShow, "yyyy-MM", "yyyy");
			String appDateM = DateUtils.transDateFormat(appDateShow, "yyyy-MM", "MM");

			_invoice.setAppDateY(appDateY);
			_invoice.setAppDateM(appDateM);
		}
		if (StringUtils.isNotBlank(invoiceNum)) {
			_invoice.setInvoiceNum(invoiceNum);
		}

		String downLoadFileName = invoiceDrawManager.createInvoiceDrawFile(_invoice);

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
}
