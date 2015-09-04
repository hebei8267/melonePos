/**
 * 
 */
package com.tjhx.service.order;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.order.ReplenishOrderDetailJpaDao;
import com.tjhx.dao.order.ReplenishOrderDetailMyBatisDao;
import com.tjhx.dao.order.ReplenishOrderJpaDao;
import com.tjhx.dao.order.ReplenishOrderMyBatisDao;
import com.tjhx.entity.order.ReplenishOrder;
import com.tjhx.entity.order.ReplenishOrderDetail;
import com.tjhx.entity.order.ReplenishOrderVo;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

/**
 * @author he_bei
 * 
 */
@Service
@Transactional(readOnly = true)
public class ReplenishOrderManager {
	private final static String XML_CONFIG_TRANSFER_ORDER = "/excel/ReplenishOrder_CFG.xml";
	@Resource
	private ReplenishOrderDetailMyBatisDao replenishOrderDetailMyBatisDao;
	@Resource
	private ReplenishOrderMyBatisDao replenishOrderMyBatisDao;
	@Resource
	private ReplenishOrderDetailJpaDao replenishOrderDetailJpaDao;
	@Resource
	private ReplenishOrderJpaDao replenishOrderJpaDao;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 根据批次号删除调货单信息
	 * 
	 * @param batchId
	 */
	public void cleanBatchInfo(String batchId) {
		// 根据批次号删除要货单信息
		replenishOrderMyBatisDao.delReplenishOrderByBatchId(batchId);
		// 根据批次号删除要货单明细信息
		replenishOrderDetailMyBatisDao.delReplenishOrderDetailByBatchId(batchId);
	}

	/**
	 * 读取调货单信息
	 * 
	 * @param string
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public List<ReplenishOrderVo> readReplenishOrderFile(String filePath) throws IOException, SAXException,
			InvalidFormatException {
		InputStream inputXML = new BufferedInputStream(
				ReqBillManager.class.getResourceAsStream(XML_CONFIG_TRANSFER_ORDER));

		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);

		InputStream inputXLS = new BufferedInputStream(new FileInputStream(filePath));

		List<ReplenishOrderVo> replenishOrderVoList = Lists.newArrayList();
		Map<String, List<ReplenishOrderVo>> map = Maps.newHashMap();

		map.put("replenishOrderVoList", replenishOrderVoList);
		XLSReadStatus readStatus = mainReader.read(inputXLS, map);

		if (Boolean.TRUE.equals(readStatus.isStatusOK())) {

			return replenishOrderVoList;

		}

		return null;
	}

	/**
	 * 保存调货单信息
	 * 
	 * @param order
	 */
	@Transactional(readOnly = false)
	public void saveReplenishOrder(ReplenishOrder order) {

		if (order.getDetailList() == null || order.getDetailList().size() == 0) {
			return;
		}
		replenishOrderJpaDao.save(order);

		for (ReplenishOrderDetail detail : order.getDetailList()) {
			replenishOrderDetailJpaDao.save(detail);
		}

	}

	/**
	 * @param orderNo
	 * @param orgId
	 * @param orderState
	 * @return
	 */
	public List<ReplenishOrder> getReplenishOrderList(String orderNo, String orgId, String orderState) {
		Map<String, String> param = Maps.newHashMap();
		if (StringUtils.isNotBlank(orderNo)) {
			if (orderNo.length() == 8) {
				param.put("orderBatchId", orderNo);
			} else {
				param.put("orderNo", orderNo);
			}
		}

		if (StringUtils.isNotBlank(orgId)) {
			param.put("replenishOrgId", orgId.substring(3));
		}

		param.put("orderState", orderState);

		return replenishOrderMyBatisDao.getReplenishOrderList(param);
	}

	/**
	 * 取得调货单信息-根据调货单编号
	 * 
	 * @param orderNo
	 * @return
	 */
	public ReplenishOrder getReplenishOrderByOrderNo(String orderNo) {
		ReplenishOrder order = replenishOrderJpaDao.findByOrderNo(orderNo);

		Map<String, String> param = Maps.newHashMap();
		param.put("orderNo", orderNo);
		List<ReplenishOrderDetail> detailList = replenishOrderDetailMyBatisDao.findReplenishOrderDetailByOrderNo(param);

		order.setDetailList(detailList);

		return order;
	}

	/**
	 * 更新调货单明细信息-补货数量
	 * 
	 * @param orderNo
	 * @param productBarcodes
	 * @param replenishNums
	 */
	@Transactional(readOnly = false)
	public void updateReplenishOrderDetail_replenishNums(String orderNo, String description, String[] productBarcodes,
			String[] replenishNums) {
		ReplenishOrder order = replenishOrderJpaDao.findByOrderNo(orderNo);
		if (null == order) {
			return;
		}
		order.setDescription(description);
		replenishOrderJpaDao.save(order);

		for (int i = 0; i < productBarcodes.length; i++) {

			ReplenishOrderDetail detail = replenishOrderDetailJpaDao.findOneByOrderNoAndproductBarcode(orderNo,
					productBarcodes[i]);

			// 错误补货数量,全部置为0
			try {
				detail.setReplenishNum(Integer.valueOf(replenishNums[i]));
			} catch (Exception e) {
				detail.setReplenishNum(0);
			}

			replenishOrderDetailJpaDao.save(detail);
		}

	}

	/**
	 * 删除补货单-根据补货单编号
	 * 
	 * @param string
	 */
	@Transactional(readOnly = false)
	public void delReplenishOrder(String orderNo) {
		// 根据补货单编号删除要货单信息
		replenishOrderMyBatisDao.delReplenishOrderByOrderNo(orderNo);
		// 根据补货单编号删除要货单明细信息
		replenishOrderDetailMyBatisDao.delReplenishOrderDetailByOrderNo(orderNo);
	}

	/**
	 * 补货单-发货
	 * 
	 * @param string
	 */
	@Transactional(readOnly = false)
	public void sendReplenishOrder(String orderNo) {
		ReplenishOrder order = replenishOrderJpaDao.findByOrderNo(orderNo);
		// 补货单状态 01-编辑中 02-收货中 03-收货完成 99-已完成
		order.setOrderState("02");
		order.setSendDate(DateUtils.getCurrentDateShortStr());
		replenishOrderJpaDao.save(order);
	}

	/**
	 * 更新调货单明细信息-收货数量
	 * 
	 * @param orderNo
	 * @param productBarcodes
	 * @param receiptNums
	 */
	@Transactional(readOnly = false)
	public void updateReplenishOrderDetail_receiptNums(String orderNo, String description, String[] productBarcodes,
			String[] receiptNums) {

		ReplenishOrder order = replenishOrderJpaDao.findByOrderNo(orderNo);
		if (null == order) {
			return;
		}
		order.setDescription(description);
		replenishOrderJpaDao.save(order);

		for (int i = 0; i < productBarcodes.length; i++) {

			ReplenishOrderDetail detail = replenishOrderDetailJpaDao.findOneByOrderNoAndproductBarcode(orderNo,
					productBarcodes[i]);

			// 错误补货数量,全部置为0
			try {
				detail.setReceiptNum(Integer.valueOf(receiptNums[i]));
			} catch (Exception e) {
				detail.setReceiptNum(0);
			}

			replenishOrderDetailJpaDao.save(detail);
		}

	}

	/**
	 * 收货完成－>已完结
	 * 
	 * @param orderNo
	 */
	@Transactional(readOnly = false)
	public void replenishOrderSuccess(String orderNo) {
		ReplenishOrder order = replenishOrderJpaDao.findByOrderNo(orderNo);

		// 补货单状态 01-编辑中 02-收货中 03-收货完成 99-已完成
		order.setOrderState("99");

		replenishOrderJpaDao.save(order);
	}

	/**
	 * 校验出货数与发货数是否一致
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean receiptNumCheck(String orderNo, boolean subOrgFlg) {
		Map<String, String> param = Maps.newHashMap();
		param.put("orderNo", orderNo);
		List<ReplenishOrderDetail> detailList = replenishOrderDetailMyBatisDao.findReplenishOrderDetailByOrderNo(param);

		boolean successFlg = true;
		for (ReplenishOrderDetail replenishOrderDetail : detailList) {
			// 收货数量
			int receiptNum = replenishOrderDetail.getReceiptNum();
			// 补货数量
			int replenishNum = replenishOrderDetail.getReplenishNum();

			if (replenishNum == receiptNum) {
				continue;
			} else {
				successFlg = false;
				break;
			}
		}

		ReplenishOrder order = replenishOrderJpaDao.findByOrderNo(orderNo);
		if (successFlg) {
			// 补货单状态 01-编辑中 02-收货中 03-收货完成 99-已完成
			order.setOrderState("03");
		} else {
			if (subOrgFlg) {// 门店操作时-增加错误计数
				order.setErrorNum(order.getErrorNum() + 1);
			}

		}

		if (subOrgFlg && StringUtils.isBlank(order.getReceiveDate())) {
			order.setReceiveDate(DateUtils.getCurrentDateShortStr());
		}

		replenishOrderJpaDao.save(order);

		return successFlg;
	}

	/**
	 * 生成补货单下载文件
	 * 
	 * @param orderNoArray
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public String createExportFile(String[] orderNoArray) throws ParsePropertyException, InvalidFormatException,
			IOException {

		List<ReplenishOrder> _list = Lists.newArrayList();
		for (String orderNo : orderNoArray) {
			if (StringUtils.isNotBlank(orderNo)) {
				_list.add(getReplenishOrderByOrderNo(orderNo));
			}
		}

		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("replenishOrderList", _list);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_REPLENISH_ORDER, map, tmpFilePath);

		return tmpFileName;
	}

	private final static String XML_CONFIG_REPLENISH_ORDER = "/excel/Replenish_Order_Template.xls";

	/**
	 * 取得机构收货错误信息列表
	 * 
	 * @param orgId
	 * @return
	 */
	public List<ReplenishOrder> getReplenishOrderReport(String orgId) {
		if (null != orgId && orgId.length() == 6) {
			orgId = orgId.substring(3);
		}
		Map<String, String> param = Maps.newHashMap();
		param.put("orgId", orgId);

		List<ReplenishOrder> odList = replenishOrderMyBatisDao.getReceiveErrNumInfo(param);

		return odList;

	}

	public List<ReplenishOrder> getOrgReplenishOrderRanking(String receiveDateYM) {
		if (StringUtils.isBlank(receiveDateYM)) {
			return null;
		}
		List<ReplenishOrder> odList = replenishOrderMyBatisDao.getOrgReceiveErrNumInfo(receiveDateYM);
		if (null == odList || odList.size() == 0) {
			return null;
		}

		Comparator<ReplenishOrder> comparator = new Comparator<ReplenishOrder>() {
			public int compare(ReplenishOrder o1, ReplenishOrder o2) {
				return o1.getErrorRate() > o2.getErrorRate() ? -1 : 1;
			}
		};
		Collections.sort(odList, comparator);

		int toIndex = 0;
		if (odList.size() > 6) {
			toIndex = 6;
		} else {
			toIndex = odList.size();
		}

		return odList.subList(0, toIndex);
	}
}
