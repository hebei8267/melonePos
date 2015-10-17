package com.tjhx.service.receipt;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.receipt.InvoiceJpaDao;
import com.tjhx.dao.receipt.InvoiceMyBatisDao;
import com.tjhx.entity.member.User;
import com.tjhx.entity.receipt.Invoice;
import com.tjhx.globals.SysConfig;
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
		// 快递公司
		_invoice.setExpressCompany(invoice.getExpressCompany());

		// 备注
		_invoice.setDescTxt(invoice.getDescTxt());
		// 设置发票状态--已处理
		_invoice.setInvoiceStatus("2");

		invoiceJpaDao.save(_invoice);
	}

	/**
	 * @param invoice
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public String createInvoiceDrawFile(Invoice invoice) throws ParsePropertyException, InvalidFormatException, IOException {
		List<Invoice> _list = invoiceMyBatisDao.getInvoiceDrawList(invoice);
		if (null == _list || _list.size() == 0) {
			return null;
		}

		List<Invoice> fileList = Lists.newArrayList();
		for (Invoice _invoice : _list) {
			// 发票状态1-申请2-已处理
			if ("2".equals(_invoice.getInvoiceStatus())) {
				_invoice.setServiceDateShow(DateUtils.transDateFormat(_invoice.getServiceDateShow(), "yyyy-MM-dd", "yyyy/MM/dd"));
				_invoice.setAppDate(DateUtils.transDateFormat(_invoice.getAppDate(), "yyyyMMdd", "yyyy/MM/dd"));
				fileList.add(_invoice);
			}

			if ("01".equals(_invoice.getExpressCompany())) {
				_invoice.setExpressCompany("申通");
			} else if ("02".equals(_invoice.getExpressCompany())) {
				_invoice.setExpressCompany("中通");
			} else if ("03".equals(_invoice.getExpressCompany())) {
				_invoice.setExpressCompany("顺丰");
			} else if ("04".equals(_invoice.getExpressCompany())) {
				_invoice.setExpressCompany("圆通");
			} else if ("05".equals(_invoice.getExpressCompany())) {
				_invoice.setExpressCompany("韵达");
			} else if ("06".equals(_invoice.getExpressCompany())) {
				_invoice.setExpressCompany("汇通");
			}
		}

		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("invoiceDrawList", fileList);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xlsx";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_INVOICE_DRAW, map, tmpFilePath);

		return tmpFileName;
	}

	private final static String XML_CONFIG_INVOICE_DRAW = "/excel/Invoice_Draw_Template.xlsx";
}
