package com.tjhx.service.receipt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.receipt.InvoiceJpaDao;
import com.tjhx.entity.member.User;
import com.tjhx.entity.receipt.Invoice;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class InvoiceApplyManager {
	@Resource
	private InvoiceJpaDao invoiceJpaDao;

	@Transactional(readOnly = false)
	public void addNewInvoiceApply(Invoice invoice, User user) {

		// 机构编号
		invoice.setOrgId(user.getOrganization().getId());
		// 设置申请日期
		invoice.setAppDateShow(DateUtils.getCurFormatDate("yyyy-MM-dd"));
		invoice.setAppDateY(DateUtils.getCurFormatDate("yyyy"));
		invoice.setAppDateM(DateUtils.getCurFormatDate("MM"));
		invoice.setAppDate(DateUtils.getCurrentDateShortStr());
		// 设置发票状态--申请
		invoice.setInvoiceStatus("1");
		// 取得是否邮寄客户1-需要0-不需要
		if ("0".equals(invoice.getNeedPost())) {
			invoice.setCustomerName(null);
			invoice.setCustomerTel(null);
			invoice.setCustomerAdd(null);
		}
		invoiceJpaDao.save(invoice);
	}

	public List<Invoice> getInvoiceApplyList_1(String orgId, String currentDate) {
		String appDateY = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "yyyy");
		String appDateM = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "MM");
		return getInvoiceApplyList(orgId, appDateY, appDateM);
	}

	public List<Invoice> getInvoiceApplyList_2(String orgId, String date) {
		String appDateY = DateUtils.transDateFormat(date, "yyyy-MM", "yyyy");
		String appDateM = DateUtils.transDateFormat(date, "yyyy-MM", "MM");
		return getInvoiceApplyList(orgId, appDateY, appDateM);
	}

	public List<Invoice> getInvoiceApplyList(String orgId, String appDateY, String appDateM) {

		List<Invoice> _list = (List<Invoice>) invoiceJpaDao.findByOrgId_AppDateY_AppDateM(orgId, appDateY, appDateM,
				new Sort(new Sort.Order(Sort.Direction.DESC, "appDate"), new Sort.Order(Sort.Direction.DESC, "uuid")));

		return _list;
	}

	@Transactional(readOnly = false)
	public void delInvoiceApplyByUuid(int uuid) {
		invoiceJpaDao.delete(uuid);
	}

	public Invoice getinvoiceApplyByUuid(Integer uuid) {
		return invoiceJpaDao.findOne(uuid);
	}

	@Transactional(readOnly = false)
	public void updateInvoiceApply(Invoice invoice, User userInfo) {
		Invoice _invoice = invoiceJpaDao.findOne(invoice.getUuid());
		if (null == _invoice) {
			// 发票申请信息不存在!
			throw new ServiceException("ERR_MSG_INVOICE_APP_001");
		}

		// 申请人名称
		_invoice.setAppName(invoice.getAppName());
		// 申请日期-显示
		_invoice.setAppDateShow(DateUtils.getCurFormatDate("yyyy-MM-dd"));
		// 申请日期-年
		_invoice.setAppDateY(DateUtils.getCurFormatDate("yyyy"));
		// 申请日期-月
		_invoice.setAppDateM(DateUtils.getCurFormatDate("MM"));
		// 申请日期
		_invoice.setAppDate(DateUtils.getCurrentDateShortStr());
		// 发票种类 1-机打 2-手写 4-其他
		_invoice.setInvoiceType(invoice.getInvoiceType());
		// 发票台头
		_invoice.setTitle(invoice.getTitle());
		// 发票内容
		_invoice.setContent(invoice.getContent());
		// 发票金额
		_invoice.setAmt(invoice.getAmt());
		// 送达日期
		_invoice.setServiceDateShow(invoice.getServiceDateShow());
		// 是否邮寄客户 1-需要 0-不需要
		_invoice.setNeedPost(invoice.getNeedPost());
		if ("0".equals(invoice.getNeedPost())) {
			_invoice.setCustomerName(null);// 客户姓名
			_invoice.setCustomerTel(null);// 客服电话
			_invoice.setCustomerAdd(null);// 客户地址
		} else {
			_invoice.setCustomerName(invoice.getCustomerName());// 客户姓名
			_invoice.setCustomerTel(invoice.getCustomerTel());// 客服电话
			_invoice.setCustomerAdd(invoice.getCustomerAdd());// 客户地址
		}

		invoiceJpaDao.save(_invoice);
	}

}
