package test.com.tjhx.service.order;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;
import org.springside.modules.utils.SpringContextHolder;
import org.xml.sax.SAXException;

import com.tjhx.dao.order.ReqBillMyBatisDao;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.order.ReqBillManager;

public class ReqBillManagerTest extends SpringTransactionalTestCase {
	@Resource
	private ReqBillManager reqBillManager;
	@Resource
	private ReqBillMyBatisDao reqBillMyBatisDao;

	// ##############################################
	// ??????????????????????????????????????????????
	private static String batchId = "20131228";

	@Test
	@Rollback(false)
	public void test00() throws InvalidFormatException, IOException, SAXException {
		// 处理编号
		reqBillManager.cleanBatchInfo(batchId);
	}

	@Test
	@Rollback(false)
	public void test01() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\01D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00001D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test02() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\02D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00002D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test03() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\03D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00003D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test04() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\04D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00004D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test05() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\05D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00005D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test06() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\06D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00006D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test07() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\07D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00007D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test08() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\08D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00008D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test09() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\09D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00009D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test10() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\10D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00010D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test11() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\11D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00011D", reqBillList);
	}

	// @Test
	// @Rollback(false)
	// public void test12() throws InvalidFormatException, IOException,
	// SAXException {
	//
	// List<ReqBill> reqBillList =
	// reqBillManager.readReqBillFile("D:\\门店要货单-输入\\12D.xls");
	// if (null == reqBillList || reqBillList.size() == 0) {
	// System.out.println("############无效文件");
	// }
	// reqBillManager.saveReqBillFile(batchId, "00012D", reqBillList);
	// }

	@Test
	@Rollback(false)
	public void test13() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\13D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00013D", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test15() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\15D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "00015D", reqBillList);
	}

	private List<String> getOrgIdList(List<ReqBill> list) {
		List<String> defOrgIdArr = new ArrayList<String>();
		defOrgIdArr.add("00001D");
		defOrgIdArr.add("00002D");
		defOrgIdArr.add("00003D");
		defOrgIdArr.add("00004D");
		defOrgIdArr.add("00005D");
		defOrgIdArr.add("00006D");
		defOrgIdArr.add("00007D");
		defOrgIdArr.add("00008D");
		defOrgIdArr.add("00009D");
		defOrgIdArr.add("00010D");
		defOrgIdArr.add("00011D");
		defOrgIdArr.add("00012D");
		defOrgIdArr.add("00013D");
		defOrgIdArr.add("00015D");

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

	@Test
	public void output1() throws ParsePropertyException, InvalidFormatException, IOException {

		int _index = 0;
		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(batchId);

		List<ReqBill> _list = new ArrayList<ReqBill>();
		for (Supplier supplier : supList) {

			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(batchId);
			reqBill.setSupplierName(supplier.getName());

			List<ReqBill> list = reqBillMyBatisDao.getOrgListBySupplier(reqBill);

			_index++;
			ReqBill _reqBill = new ReqBill(); // 最终统计单个供应商本次供应多少个门店
			_reqBill.setIndex(_index);
			_reqBill.setSupplierName(supplier.getName());
			_reqBill.setOrgIdList(getOrgIdList(list));
			_list.add(_reqBill);

			reqBillManager.writeReqBillFileToHeadOffice(batchId, supplier.getName(), _list);
		}
	}

	// 生产供应商文件
	@Test
	public void output2() throws InvalidFormatException, IOException, SAXException {
		int _index = 0;
		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(batchId);
		for (Supplier supplier : supList) {

			System.out.println(supplier.getName());

			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(batchId);
			reqBill.setSupplierName(supplier.getName());
			List<ReqBill> list = reqBillMyBatisDao.getReqBillList(reqBill);
			// 计算建议采购数量
			calPurchase(list);

			list = addBlankRow(list);
			reqBillManager.writeReqBillFileToSupplier(batchId, supplier.getName(), list);

			for (ReqBill reqBill2 : list) {
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
	 * 计算建议采购数量
	 */
	private void calPurchase(List<ReqBill> list) {
		for (ReqBill reqBill : list) {
			if (reqBill.getStockQty().compareTo(BigDecimal.ZERO) == -1) {// 小于零，负库存
				continue;// 不处理
			}

			BigDecimal _posQty = reqBill.getPosQty1().add(reqBill.getPosQty2()).add(reqBill.getPosQty3())
					.add(reqBill.getPosQty4());
			_posQty = _posQty.divide(new BigDecimal(4), BigDecimal.ROUND_DOWN);

			// 建议采购数量-低（平均值×1）
			reqBill.setLowPurchase(_posQty.subtract(reqBill.getStockQty()));
			// 建议采购数量-高（平均值×2）
			reqBill.setHighPurchase(_posQty.multiply(new BigDecimal(2)).subtract(reqBill.getStockQty()));
		}
	}

	// 生产供应商文件-插入图片
	@Test
	public void output3() throws FileNotFoundException, IOException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(batchId);
		for (Supplier supplier : supList) {
			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(batchId);
			reqBill.setSupplierName(supplier.getName());
			List<ReqBill> list = reqBillMyBatisDao.getReqBillList(reqBill);

			reqBillManager.writeReqBillImageFileToSupplier(sysConfig.getReqBillSupplierOutputPath() + batchId + "/"
					+ supplier.getName() + "_" + batchId + ".xls",
					getImagePathList(sysConfig.getProductImgPath(), list));
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
