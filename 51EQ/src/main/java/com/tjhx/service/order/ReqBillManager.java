package com.tjhx.service.order;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.FileUtils;
import com.tjhx.dao.order.ReqBillJpaDao;
import com.tjhx.dao.order.ReqBillMyBatisDao;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class ReqBillManager {

	@Resource
	private ReqBillJpaDao reqBillJpaDao;
	@Resource
	private ReqBillMyBatisDao reqBillMyBatisDao;
	@Resource
	private OrganizationManager orgManager;

	private final static String XML_CONFIG_READ_REQ_BILL = "/excel/Req_Bill_Org_Read_CFG.xml";
	private final static String XML_CONFIG_WRITE_REQ_BILL = "/excel/Req_Bill_Supplier_Template.xlsx";
	private final static String XML_CONFIG_WRITE_REQ_BILL_SUM = "/excel/Req_Bill_Sum_Supplier_Template.xlsx";
	private final static String XML_CONFIG_WRITE_REQ_BILL_EQ = "/excel/Req_Bill_EQ_Template.xlsx";
	private final static String XML_CONFIG_WRITE_REQ_BILL_ORG = "/excel/Req_Bill_Org_Template.xls";

	/**
	 * @param batchId
	 * @param supplierName
	 * @param list
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public void writeReqBillFileToHeadOffice(String batchId, String supplierName, List<ReqBill> list) throws ParsePropertyException,
			InvalidFormatException, IOException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		Map<String, List<ReqBill>> map = new HashMap<String, List<ReqBill>>();
		map.put("reqBillList", list);

		// 自动建立文件夹
		FileUtils.mkdir(sysConfig.getReqBillSupplierOutputPath() + batchId + "/");

		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_WRITE_REQ_BILL_EQ, map,
				sysConfig.getReqBillSupplierOutputPath() + batchId + "/#########EQ_" + batchId + ".xlsx");
	}

	/**
	 * @param batchId
	 * @param supplierName
	 * @param list
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public void writeReqBillSumFileToSupplier(String batchId, String supplierName, List<ReqBill> dataList, List<Organization> orgNameList)
			throws ParsePropertyException, InvalidFormatException, IOException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reqBillList", dataList);
		map.put("orgNameList", initOrgNameList(orgNameList));
		// 自动建立文件夹
		FileUtils.mkdir(sysConfig.getReqBillSupplierOutputPath() + batchId + "/");

		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_WRITE_REQ_BILL_SUM, map,
				sysConfig.getReqBillSupplierOutputPath() + batchId + "/" + supplierName + "_合计_" + batchId + ".xlsx");
	}

	private List<Organization> initOrgNameList(List<Organization> orgList) {
		List<Organization> _orgNameList = Lists.newArrayList();
		// 区分品牌机构

		// =========================================
		// EQ+
		// =========================================
		Organization org = new Organization();
		org.setId("EQ+");
		org.setName("EQ+");
		_orgNameList.add(org);
		for (Organization organization : orgList) {
			if (organization.getBrand().equals("EQ+")) {
				org = new Organization();
				org.setId(organization.getId());
				org.setName(organization.getName());
				_orgNameList.add(org);
			}

		}

		// =========================================
		// Infancy
		// =========================================
		org = new Organization();
		org.setId("Infancy");
		org.setName("Infancy");
		_orgNameList.add(org);
		for (Organization organization : orgList) {
			if (organization.getBrand().equals("Infancy")) {
				org = new Organization();
				org.setId(organization.getId());
				org.setName(organization.getName());
				_orgNameList.add(org);
			}

		}

		return _orgNameList;
	}

	/**
	 * @param batchId
	 * @param supplierName
	 * @param list
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public void writeReqBillFileToSupplier(String batchId, String supplierName, List<ReqBill> dataList) throws ParsePropertyException,
			InvalidFormatException, IOException {

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reqBillList", dataList);

		// 自动建立文件夹
		FileUtils.mkdir(sysConfig.getReqBillSupplierOutputPath() + batchId + "/");

		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_WRITE_REQ_BILL, map,
				sysConfig.getReqBillSupplierOutputPath() + batchId + "/" + supplierName + "_" + batchId + ".xlsx");
	}

	/**
	 * 插入图片
	 * 
	 * @param filePath
	 * @param imagePathList
	 * @param list
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeReqBillImageFileToSupplier(String filePath, List<String> imagePathList) throws FileNotFoundException, IOException {
		File xlsFile = new File(filePath);
		if (!xlsFile.exists()) {// excel文件不存在
			return;
		}

		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(xlsFile)));
		// 生成一个表格
		HSSFSheet sheet = wb.getSheetAt(0);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// 填充图片
		int _index = 1;
		for (String imagePath : imagePathList) {
			_index++;
			File imgFile = new File(imagePath);
			if (!imgFile.exists()) {// image文件不存在
				continue;
			}
			_insertImage(wb, patriarch, _getImageData(ImageIO.read(imgFile)), _index, 15);
		}

		FileOutputStream fout = new FileOutputStream(filePath);
		// 输出到文件
		wb.write(fout);
		fout.close();
	}

	/**
	 * 读取门店要货单信息
	 * 
	 * @param orgId 门店编号
	 * @param filePath 文件全路径
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public List<ReqBill> readReqBillFile(String filePath) throws IOException, SAXException, InvalidFormatException {

		File _file = new File(filePath);
		System.out.println("####################文件：" + filePath);
		if (!_file.exists()) {
			System.out.println("????????????????文件：" + filePath + " 不存在！");
			return null;
		}

		InputStream inputXLS = new BufferedInputStream(new FileInputStream(_file));

		InputStream inputXML = new BufferedInputStream(ReqBillManager.class.getResourceAsStream(XML_CONFIG_READ_REQ_BILL));
		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);

		List<ReqBill> reqBillList = new ArrayList<ReqBill>();
		Map<String, List<ReqBill>> map = new HashMap<String, List<ReqBill>>();
		map.put("reqBillList", reqBillList);
		XLSReadStatus readStatus = mainReader.read(inputXLS, map);

		if (Boolean.TRUE.equals(readStatus.isStatusOK())) {
			return reqBillList;
		} else {
			return null;
		}
	}

	@Transactional(readOnly = false)
	public void cleanBatchInfo(String batchId) {
		reqBillMyBatisDao.delReqBillByBatchId(batchId);
	}

	/**
	 * 保存门店要货单信息
	 * 
	 * @param batchId
	 * @param orgId
	 * @param reqBillList
	 */
	@Transactional(readOnly = false)
	public void saveReqBillFile(String batchId, String orgId, List<ReqBill> reqBillList) {

		int _index = 1;
		for (ReqBill reqBill : reqBillList) {
			reqBill.setBatchId(batchId);
			reqBill.setOrgId(orgId);
			reqBill.setIndex(_index);

			reqBill.setSupplierName(reqBill.getSupplierName().replaceAll("/", "_"));
			_index++;

			reqBillJpaDao.save(reqBill);
		}
	}

	// 自定义的方法,插入某个图片到指定索引的位置
	private static void _insertImage(HSSFWorkbook wb, HSSFPatriarch pa, byte[] data, int row, int column) {
		// 单元格为标,以左上为起点,想右移,范围0-1023 dx1 must be between 0 and 1023
		int x1 = 0;
		// 单元格为标,以左上为起点,想下移,范围0-255 dy1 must be between 0 and 255
		int y1 = 0;
		// 单元格为标,以右上为起点,想左移,范围0-1023 dx1 must be between 0 and 1023
		int x2 = 1023;
		// 单元格为标,以右下为起点,想上移,范围0-1023 dy1 must be between 0 and 255
		int y2 = 255;
		HSSFClientAnchor anchor = new HSSFClientAnchor(x1, y1, x2, y2, (short) column, row, (short) column, row);

		anchor.setAnchorType(3);
		pa.createPicture(anchor, wb.addPicture(data, HSSFWorkbook.PICTURE_TYPE_JPEG));
	}

	// 从图片里面得到字节数组
	private static byte[] _getImageData(BufferedImage bi) {
		try {
			// 转化成PNG
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ImageIO.write(bi, "PNG", bout);
			return bout.toByteArray();
		} catch (Exception exe) {
			exe.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws InvalidFormatException, IOException, SAXException {
		ReqBillManager reqBillManager = new ReqBillManager();
		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\eclipse-cdoi\\workspace\\BBB\\src\\DD.xlsx");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		// reqBillManager.saveReqBillFile("20120604", "02", reqBillList);
		for (ReqBill reqBill : reqBillList) {
			System.out.print(reqBill.getProductNo() + "\t");
			System.out.print(reqBill.getBarcode() + "\t");
			System.out.print(reqBill.getProductName() + "\t");
			System.out.print(reqBill.getInventoryNum() + "\t");
			System.out.print(reqBill.getAppNum() + "\t");
			System.out.print(reqBill.getRefPrice() + "\t");
			System.out.print(reqBill.getSupplierName() + "\t");
			System.out.print(reqBill.getRemarks() + "\t");
			System.out.print(reqBill.getRemarks() == null);
			System.out.println();
		}
	}

	/** 设置单商品全机构补货单明细 */
	public void insertGoodOrgReqBillList(String barcode, String batchId, List<ReqBill> orgReqBillList) {
		Map<String, String> param = Maps.newHashMap();
		param.put("batchId", batchId);
		param.put("barcode", barcode);

		List<ReqBill> _dataList = reqBillMyBatisDao.getReqBillListByBarcode(param);

		int eqAppNum = 0;
		int inAppNum = 0;

		for (ReqBill _data : _dataList) {

			for (ReqBill reqBill : orgReqBillList) {
				if (_data.getOrgId().equals(reqBill.getOrgId())) {
					reqBill.setAppNum(_data.getAppNum());

					// 按门店品牌计算
					Organization org = orgManager.getOrganizationByOrgIdInCache(reqBill.getOrgId());
					if (null == org) {
						continue;
					}
					if (org.getBrand().equals("EQ+")) {
						eqAppNum += _data.getAppNum();
					} else {
						inAppNum += _data.getAppNum();
					}
				}
			}

		}

		// 设置-按门店品牌计算-结果
		for (ReqBill reqBill : orgReqBillList) {
			if ("EQ+".equals(reqBill.getOrgId())) {
				reqBill.setAppNum(eqAppNum);
			} else if ("Infancy".equals(reqBill.getOrgId())) {
				reqBill.setAppNum(inAppNum);
			}
		}

	}

	/**
	 * 生成单个门店要货单信息增强文件
	 * 
	 * @param batchId
	 * @param orgName
	 * @param reqBillDataList
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public void writeReqBillByOrg(String batchId, String orgName, List<ReqBill> reqBillDataList) throws ParsePropertyException,
			InvalidFormatException, IOException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reqBillList", reqBillDataList);

		// 自动建立文件夹
		FileUtils.mkdir(sysConfig.getReqBillSupplierOutputPath() + batchId + "/");

		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_WRITE_REQ_BILL_ORG, map,
				sysConfig.getReqBillSupplierOutputPath() + batchId + "/" + orgName + ".xls");

	}
}
