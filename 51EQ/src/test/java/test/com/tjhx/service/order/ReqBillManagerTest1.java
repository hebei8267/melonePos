package test.com.tjhx.service.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;
import org.xml.sax.SAXException;

import com.google.common.collect.Maps;
import com.tjhx.dao.order.ReqBillMyBatisDao;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.order.ReqBillManager;
import com.tjhx.service.struct.OrganizationManager;

/**
 * 要货单
 */
public class ReqBillManagerTest1 extends SpringTransactionalTestCase {
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

		// 单个门店要货单信息增强
		reqBillListFile(orgList);

	}

	/**
	 * 单个门店要货单信息增强
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	private void reqBillListFile(List<Organization> orgList) throws ParsePropertyException, InvalidFormatException, IOException {
		for (Organization org : orgList) {
			Map<String, String> param = Maps.newHashMap();
			param.put("batchId", BATCH_ID);
			param.put("orgId", org.getId());

			List<ReqBill> _reqBillList = reqBillMyBatisDao.getReqBillListByOrgId(param);

			if (null == _reqBillList || _reqBillList.size() == 0) {
				continue;
			}

			// 计算建议采购数量
			calPurchase(_reqBillList);
			// 生成单个门店要货单信息增强文件
			reqBillManager.writeReqBillByOrg(BATCH_ID, org.getName(), _reqBillList);
		}

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

}
