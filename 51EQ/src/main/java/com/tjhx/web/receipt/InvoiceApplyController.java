package com.tjhx.web.receipt;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.receipt.Invoice;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.receipt.InvoiceApplyManager;
import com.tjhx.service.receipt.InvoiceDrawManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/invoiceApply")
public class InvoiceApplyController extends BaseController {
	@Resource
	private InvoiceApplyManager invoiceApplyManager;
	@Resource
	private InvoiceDrawManager invoiceDrawManager;

	@RequestMapping(value = { "list", "" })
	public String invoiceApplyList_Action(Model model, HttpSession session) throws ServletRequestBindingException {

		List<Invoice> invoiceApplyList = invoiceApplyManager.getInvoiceApplyList_1(getUserInfo(session)
				.getOrganization().getId(), DateUtils.getCurrentDateShortStr());

		model.addAttribute("invoiceApplyList", invoiceApplyList);

		return "invoice/invoiceApplyList";
	}

	@RequestMapping(value = "list/{date}")
	public String invoiceApplyList_Date_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {
		List<Invoice> invoiceApplyList = invoiceApplyManager.getInvoiceApplyList_2(getUserInfo(session)
				.getOrganization().getId(), date);

		model.addAttribute("invoiceApplyList", invoiceApplyList);

		return "invoice/invoiceApplyList";
	}

	/**
	 * 初始化发票种类列表
	 * 
	 * @param model
	 */
	private void initInvoiceTypeList(Model model) {

		Map<String, String> invoiceTypeList = new LinkedHashMap<String, String>();
		invoiceTypeList.put("", "");
		invoiceTypeList.put("1", "机打");
		invoiceTypeList.put("2", "手写");
		invoiceTypeList.put("4", "其他");

		model.addAttribute("invoiceTypeList", invoiceTypeList);
	}

	/**
	 * 申请发票
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initInvoiceApply_Action(Model model) {
		Invoice invoice = new Invoice();

		model.addAttribute("invoice", invoice);
		initInvoiceTypeList(model);

		return "invoice/invoiceApplyForm";
	}

	/**
	 * 编辑未开具的发票
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editInvoiceApply_Action(@PathVariable("id") Integer id, Model model) {

		Invoice invoice = invoiceApplyManager.getinvoiceApplyByUuid(id);
		if (null == invoice) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/invoiceApply/list";
		} else {
			model.addAttribute("invoice", invoice);

			initInvoiceTypeList(model);

			return "invoice/invoiceApplyForm";
		}

	}

	@RequestMapping(value = "view/{id}")
	public String viewInvoiceApply_Action(@PathVariable("id") Integer id, Model model) {
		Invoice invoice = invoiceDrawManager.getInvoiceByUuid_MyBatis(id);
		model.addAttribute("invoice", invoice);
		return "invoice/invoiceViewForm";
	}

	/**
	 * 删除未开具的发票
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delInvoiceApply_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			invoiceApplyManager.delInvoiceApplyByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/invoiceApply/list";
	}

	/**
	 * 保存发票
	 * 
	 * @param invoice
	 * @param model
	 * @param session
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveInvoiceApply_Action(@ModelAttribute("invoice") Invoice invoice, Model model, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {

		if (null == invoice.getUuid()) {// 新增操作
			invoiceApplyManager.addNewInvoiceApply(invoice, getUserInfo(session));
		} else {// 修改操作
			try {
				invoiceApplyManager.updateInvoiceApply(invoice, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initInvoiceTypeList(model);

				return "invoice/invoiceApplyForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/invoiceApply/list";
	}
}
