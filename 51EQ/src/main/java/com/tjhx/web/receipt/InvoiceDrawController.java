package com.tjhx.web.receipt;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.receipt.Invoice;
import com.tjhx.globals.Constants;
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
	public String invoiceDrawSearchList_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
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

		model.addAttribute("invoice", invoice);

		return "invoice/invoiceDrawForm";
	}

	@RequestMapping(value = "draw")
	public String invoiceDraw_Action(@ModelAttribute("invoice") Invoice invoice, Model model, HttpSession session) {

		invoiceDrawManager.updateInvoiceDraw(invoice, getUserInfo(session));

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/invoiceDraw";
	}
}
