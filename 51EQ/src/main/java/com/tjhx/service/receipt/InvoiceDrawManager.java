package com.tjhx.service.receipt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.receipt.InvoiceJpaDao;
import com.tjhx.dao.receipt.InvoiceMyBatisDao;
import com.tjhx.entity.member.User;
import com.tjhx.entity.receipt.Invoice;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class InvoiceDrawManager {
	@Resource
	private InvoiceMyBatisDao invoiceMyBatisDao;

	@Resource
	private InvoiceJpaDao invoiceJpaDao;

	public List<Invoice> searchInvoiceDrawList(Invoice invoice) {
		return invoiceMyBatisDao.getInvoiceDrawList(invoice);
	}

	public Invoice getInvoiceByUuid_MyBatis(Integer uuid) {
		Invoice invoice = new Invoice();
		invoice.setUuid(uuid);

		List<Invoice> _invoiceDrawList = invoiceMyBatisDao.getInvoiceDrawList(invoice);

		if (null == _invoiceDrawList || _invoiceDrawList.size() == 0) {
			// 发票申请信息不存在!
			throw new ServiceException("ERR_MSG_INVOICE_APP_001");

		} else {
			return _invoiceDrawList.get(0);
		}

	}

	@Transactional(readOnly = false)
	public void updateInvoiceDraw(Invoice invoice, User userInfo) {
		Invoice _invoice = invoiceJpaDao.findOne(invoice.getUuid());

		if (null == _invoice) {
			// 发票申请信息不存在!
			throw new ServiceException("ERR_MSG_INVOICE_APP_001");
		}
		// 发票号
		_invoice.setInvoiceNum(invoice.getInvoiceNum());
		// 发票来源
		_invoice.setInvoiceSrc(invoice.getInvoiceSrc());
		// 备注
		_invoice.setDescTxt(invoice.getDescTxt());
		// 设置发票状态--已处理
		_invoice.setInvoiceStatus("2");

		invoiceJpaDao.save(_invoice);
	}
}
