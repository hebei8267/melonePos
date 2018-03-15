package test.com.tjhx.service.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
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
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.order.ReqBillMyBatisDao;
import com.tjhx.entity.info.SalesDayTotalGoods;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.info.GrossProfitAbcManager;
import com.tjhx.service.order.ReqBillManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.GrossProfitAbcVo;

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
	@Resource
	private GrossProfitAbcManager grossProfitAbcManager;
	// ##############################################
	private static String BATCH_ID = "20131228";
	private static String INPUT_FILE_PATH = "/Users/tao_tao/Downloads/门店要货单-输入/";

	// ##############################################

	@Test
	@Rollback(false)
	public void run() throws InvalidFormatException, IOException, SAXException, ParsePropertyException, ParseException {
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
	 * @throws ParseException
	 */
	private void reqBillListFile(List<Organization> orgList) throws ParsePropertyException, InvalidFormatException, IOException,
			ParseException {
		for (Organization org : orgList) {
			Map<String, String> param = Maps.newHashMap();
			param.put("batchId", BATCH_ID);
			param.put("orgId", org.getId());

			List<ReqBill> _reqBillList = reqBillMyBatisDao.getReqBillListByOrgId(param);

			String sd = DateUtils.getNextDateFormatDate(DateUtils.getCurFormatDate("yyyy-MM-dd"), -31, "yyyy-MM-dd");
			String sd3 = DateUtils.getNextDateFormatDate(DateUtils.getCurFormatDate("yyyy-MM-dd"), -91, "yyyy-MM-dd");
			String ed = DateUtils.getNextDateFormatDate(DateUtils.getCurFormatDate("yyyy-MM-dd"), -1, "yyyy-MM-dd");
			// 门店ABC-近一月
			Map<String, String> orgAbcMap = getOrgAbcMap(sd, ed, org.getId());
			// 全店ABC-近一月
			Map<String, String> abcMap = getOrgAbcMap(sd, ed);
			// 门店ABC-近三月
			Map<String, String> orgAbc3Map = getOrgAbcMap(sd3, ed, org.getId());
			// 全店ABC-近三月
			Map<String, String> abc3Map = getOrgAbcMap(sd3, ed);

			if (null == _reqBillList || _reqBillList.size() == 0) {
				continue;
			}

			// 填充对象信息(计算建议采购数量/ABC排名)
			fillObj(_reqBillList, orgAbcMap, abcMap, orgAbc3Map, abc3Map);
			// 生成单个门店要货单信息增强文件
			reqBillManager.writeReqBillByOrg(BATCH_ID, org.getName(), _reqBillList);
		}

	}

	/**
	 * 取得指定全店ABC报表信息
	 * 
	 * @param startDate
	 * @param endDate
	 * @param orgId 为空时取得全店
	 * @return
	 * @throws ParseException
	 */
	private Map<String, String> getOrgAbcMap(String startDate, String endDate) throws ParseException {
		return getOrgAbcMap(startDate, endDate, null);
	}

	/**
	 * 取得指定门店ABC报表信息
	 * 
	 * @param startDate
	 * @param endDate
	 * @param orgId 为空时取得全店
	 * @return
	 * @throws ParseException
	 */
	private Map<String, String> getOrgAbcMap(String startDate, String endDate, String orgId) throws ParseException {
		// (1个月门店)合计销售金额统计 - 1 0 - 隐藏D报表
		GrossProfitAbcVo orgVo = grossProfitAbcManager.getGrossProfitAbcInfo(startDate, endDate, orgId, null, null, null, "1", 70, 20, 10,
				null, "0");

		Map<String, String> res = Maps.newHashMap();

		for (int i = 0; i < orgVo.getListA().size(); i++) {
			SalesDayTotalGoods good = orgVo.getListA().get(i);
			res.put(good.getItemBarcode(), "A" + (i + 1));
		}

		for (int i = 0; i < orgVo.getListB().size(); i++) {
			SalesDayTotalGoods good = orgVo.getListB().get(i);
			res.put(good.getItemBarcode(), "B" + (i + 1));
		}

		for (int i = 0; i < orgVo.getListC().size(); i++) {
			SalesDayTotalGoods good = orgVo.getListC().get(i);
			res.put(good.getItemBarcode(), "C" + (i + 1));
		}

		return res;
	}

	/**
	 * 填充对象信息(计算建议采购数量/ABC排名)
	 */
	private void fillObj(List<ReqBill> list, Map<String, String> orgAbcMap, Map<String, String> abcMap, Map<String, String> orgAbc3Map,
			Map<String, String> abc3Map) {

		for (ReqBill reqBill : list) {
			// ================================================================================
			// ABC排名
			// ================================================================================
			String orgAbcRanking = orgAbcMap.get(reqBill.getBarcode());
			reqBill.setOrgAbc(orgAbcRanking);// 门店ABC-近一月

			String abcRanking = abcMap.get(reqBill.getBarcode());
			reqBill.setAbc(abcRanking);// 全店ABC-近一月

			String orgAbc3Ranking = orgAbc3Map.get(reqBill.getBarcode());
			reqBill.setOrgAbc3(orgAbc3Ranking);// 门店ABC-近三月

			String abc3Ranking = abc3Map.get(reqBill.getBarcode());
			reqBill.setAbc3(abc3Ranking);// 全店ABC-近三月

			// ================================================================================
			// 计算建议采购数量
			// ================================================================================
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
