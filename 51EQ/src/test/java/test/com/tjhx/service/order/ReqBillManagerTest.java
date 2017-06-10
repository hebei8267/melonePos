package test.com.tjhx.service.order;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;
import org.springside.modules.utils.SpringContextHolder;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.order.ReqBillMyBatisDao;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.order.ReqBillManager;
import com.tjhx.service.struct.OrganizationManager;

/**
 * 要货单
 */
public class ReqBillManagerTest extends SpringTransactionalTestCase {
	@Resource
	private ReqBillManager reqBillManager;
	@Resource
	private ReqBillMyBatisDao reqBillMyBatisDao;
	@Resource
	private OrganizationManager orgManager;
	// ##############################################
	private static String BATCH_ID = "20131228";
	private static String INPUT_FILE_PATH = "/Users/tao_tao/Downloads/门店要货单-输入/";

	// ##############################################

	@Test
	@Rollback(false)
	public void run() throws InvalidFormatException, IOException, SAXException {
		// 清理既有数据-根据批次号
		reqBillManager.cleanBatchInfo(BATCH_ID);

		List<Organization> orgList = orgManager.getOpenSubOrganization();

		saveReqBillFileData(orgList);

		// 统计单个供应商本次供应多少个门店
		supplier2OrgGoodListFile(orgList);

		// 单个生产供应商商品汇总文件
		supplierSumGoodListFile();
		// 单个生产供应商商品汇总文件(合计)
		supplierSumGoodListFile2(orgList);
	}

	/**
	 * 将文件数据保存至数据库
	 * 
	 * @param orgList
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws SAXException
	 */
	private void saveReqBillFileData(List<Organization> orgList) throws InvalidFormatException, IOException, SAXException {
		for (Organization org : orgList) {
			List<ReqBill> reqBillList = reqBillManager.readReqBillFile(INPUT_FILE_PATH + org.getName() + ".xls");
			if (null == reqBillList || reqBillList.size() == 0) {
				System.out.println("############无效文件");
				continue;
			}
			reqBillManager.saveReqBillFile(BATCH_ID, org.getId(), reqBillList);
		}
	}

	private List<String> _getOrgIdList(List<ReqBill> list, List<Organization> orgList) {
		List<String> defOrgIdArr = new ArrayList<String>();

		for (Organization org : orgList) {
			defOrgIdArr.add(org.getId());
		}

		List<String> orgIdArr = new ArrayList<String>();
		for (ReqBill reqBill : list) {
			orgIdArr.add(reqBill.getOrgId());
		}

		List<String> _orgList = new ArrayList<String>();
		for (String org : defOrgIdArr) {
			if (orgIdArr.contains(org)) {
				_orgList.add(org);
			} else {
				_orgList.add("");
			}
		}

		return _orgList;
	}

	/**
	 * 统计单个供应商本次供应多少个门店
	 * 
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private void supplier2OrgGoodListFile(List<Organization> orgList) throws ParsePropertyException, InvalidFormatException, IOException {

		int _index = 0;
		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(BATCH_ID);

		List<ReqBill> _list = new ArrayList<ReqBill>();
		for (Supplier supplier : supList) {

			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(BATCH_ID);
			reqBill.setSupplierName(supplier.getName());

			List<ReqBill> list = reqBillMyBatisDao.getOrgListBySupplier(reqBill);

			_index++;
			ReqBill _reqBill = new ReqBill(); // 最终统计单个供应商本次供应多少个门店
			_reqBill.setIndex(_index);
			_reqBill.setSupplierName(supplier.getName());
			_reqBill.setOrgIdList(_getOrgIdList(list, orgList));
			_list.add(_reqBill);

			reqBillManager.writeReqBillFileToHeadOffice(BATCH_ID, supplier.getName(), _list);
		}
	}

	/**
	 * 单个生产供应商商品汇总文件
	 * 
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws SAXException
	 */
	private void supplierSumGoodListFile() throws InvalidFormatException, IOException, SAXException {
		int _index = 0;
		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(BATCH_ID);

		for (Supplier supplier : supList) {

			System.out.println(supplier.getName());

			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(BATCH_ID);
			reqBill.setSupplierName(supplier.getName());
			reqBill.setOptDate(DateUtils.getNextDateFormatDate(-1, "yyyyMMdd"));
			List<ReqBill> dataList = reqBillMyBatisDao.getReqBillList(reqBill);
			// 计算建议采购数量
			calPurchase(dataList);

			dataList = addBlankRow(dataList);
			reqBillManager.writeReqBillFileToSupplier(BATCH_ID, supplier.getName(), dataList);

			for (ReqBill reqBill2 : dataList) {
				_index++;
				System.out.print(reqBill2.getOrgId() + "\t");
				System.out.print(reqBill2.getProductName() + "\t");
				System.out.println();
			}
			System.out.println("############################################################");
		}

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@全部数据行" + _index);
	}

	/**
	 * 单个生产供应商商品汇总文件(合计)
	 * 
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws SAXException
	 */
	private void supplierSumGoodListFile2(List<Organization> orgList) throws InvalidFormatException, IOException, SAXException {
		int _index = 0;
		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(BATCH_ID);
		for (Supplier supplier : supList) {

			System.out.println(supplier.getName());

			Map<String, String> param = Maps.newHashMap();
			param.put("batchId", BATCH_ID);
			param.put("supplierName", supplier.getName());
			param.put("optDate", DateUtils.getNextDateFormatDate(-30, "yyyyMMdd"));

			List<ReqBill> dataList = reqBillMyBatisDao.getReqBillSumList(param);

			// 初始化单商品全机构补货单明
			initOrgReqBillList(dataList, orgList);

			reqBillManager.writeReqBillSumFileToSupplier(BATCH_ID, supplier.getName(), dataList, orgList);

			for (ReqBill reqBill2 : dataList) {
				_index++;
				System.out.print(reqBill2.getProductName() + "\t");
				System.out.println();
			}
			System.out.println("############################################################");
		}

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@全部数据行" + _index);
	}

	/**
	 * 计算建议采购数量
	 */
	private void calPurchase(List<ReqBill> list) {

		for (ReqBill reqBill : list) {
			if (reqBill.getStockQty().compareTo(BigDecimal.ZERO) == -1) {// 小于零，负库存
				continue;// 不处理
			}

			// 月销售
			BigDecimal _posQty_m = reqBill.getPosQty1().add(reqBill.getPosQty2()).add(reqBill.getPosQty3()).add(reqBill.getPosQty4());
			// 周销售
			BigDecimal _posQty_w = _posQty_m.divide(new BigDecimal(4), BigDecimal.ROUND_DOWN);

			// 建议采购数量-低（平均值×1）
			reqBill.setLowPurchase(_posQty_w.subtract(reqBill.getStockQty()));
			// 建议采购数量-高（平均值×2）
			reqBill.setHighPurchase(_posQty_w.multiply(new BigDecimal(2)).subtract(reqBill.getStockQty()));

			// 30天库销比
			if (_posQty_m.compareTo(BigDecimal.ZERO) != 0) {
				reqBill.setStockRatio(reqBill.getStockQty().divide(_posQty_m, 2));
			}
		}
	}

	/**
	 * 初始化单商品全机构补货单明
	 * 
	 * @param list
	 * @param orgList
	 */
	private void initOrgReqBillList(List<ReqBill> list, List<Organization> orgList) {
		for (ReqBill reqBill : list) {
			// 库销比
			if (null == reqBill.getPosAmtTotal() && reqBill.getPosAmtTotal().compareTo(BigDecimal.ZERO) != 0) {
				reqBill.setStockRatio(reqBill.getStockQty().divide(reqBill.getPosAmtTotal()));
			}
			// 初始化单商品全机构补货单明细
			reqBill.setOrgReqBillList(_initOrgReqBillList(orgList, reqBill.getBarcode()));
		}
	}

	/** 初始化单商品全机构补货单明细 */
	private List<ReqBill> _initOrgReqBillList(List<Organization> orgList, String barcode) {
		List<ReqBill> orgReqBillList = Lists.newArrayList();

		// 区分品牌机构

		// =========================================
		// EQ+
		// =========================================
		ReqBill reqBill = new ReqBill();
		reqBill.setOrgId("EQ+");
		orgReqBillList.add(reqBill);
		for (Organization organization : orgList) {
			if (organization.getBrand().equals("EQ+")) {
				reqBill = new ReqBill();
				reqBill.setOrgId(organization.getId());
				orgReqBillList.add(reqBill);
			}

		}

		// =========================================
		// Infancy
		// =========================================
		reqBill = new ReqBill();
		reqBill.setOrgId("Infancy");
		orgReqBillList.add(reqBill);
		for (Organization organization : orgList) {
			if (organization.getBrand().equals("Infancy")) {
				reqBill = new ReqBill();
				reqBill.setOrgId(organization.getId());
				orgReqBillList.add(reqBill);
			}

		}

		// 设置单商品全机构补货单明细
		reqBillManager.insertGoodOrgReqBillList(barcode, BATCH_ID, orgReqBillList);

		return orgReqBillList;
	}

	// 生产供应商文件-插入图片
	private void t_output4() throws FileNotFoundException, IOException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(BATCH_ID);
		for (Supplier supplier : supList) {
			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(BATCH_ID);
			reqBill.setSupplierName(supplier.getName());
			List<ReqBill> list = reqBillMyBatisDao.getReqBillList(reqBill);

			reqBillManager.writeReqBillImageFileToSupplier(sysConfig.getReqBillSupplierOutputPath() + BATCH_ID + "/" + supplier.getName()
					+ "_" + BATCH_ID + ".xls", getImagePathList(sysConfig.getProductImgPath(), list));
		}
	}

	private static List<String> getImagePathList(String productImgPath, List<ReqBill> list) {
		list = addBlankRow(list);
		List<String> imagePathList = new ArrayList<String>();
		for (ReqBill reqBill : list) {

			// System.out.println(productImgPath + reqBill.getProductNo() +
			// ".jpg");
			imagePathList.add(productImgPath + reqBill.getProductNo() + ".jpg");
		}
		return imagePathList;
	}

	private static List<ReqBill> addBlankRow(List<ReqBill> list) {
		List<ReqBill> _list = new ArrayList<ReqBill>();

		String _tmpOrgId = null;
		for (ReqBill reqBill : list) {
			if (!reqBill.getOrgId().equals(_tmpOrgId)) {
				if (null != _tmpOrgId) {
					_list.add(new ReqBill());
					_list.add(new ReqBill());
				}
				_tmpOrgId = reqBill.getOrgId();
			}
			_list.add(reqBill);
		}
		return _list;
	}
}
