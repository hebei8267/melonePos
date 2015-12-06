/**
 * 
 */
package test.com.tjhx.service.order;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.entity.order.ReplenishOrder;
import com.tjhx.entity.order.ReplenishOrderDetail;
import com.tjhx.entity.order.ReplenishOrderVo;
import com.tjhx.service.order.ReplenishOrderManager;

/**
 * 配货单
 */
public class ReplenishOrderManagerTest extends SpringTransactionalTestCase {
	@Resource
	private ReplenishOrderManager replenishOrderManager;

	// ##############################################
	// 批次号
	private static String batchId = "99999999";

	// ##############################################

	/**
	 * 根据批次号删除调货单信息
	 * 
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws SAXException
	 */
	@Test
	@Rollback(false)
	public void test00() throws InvalidFormatException, IOException, SAXException {
		// 清除调货单信息
		replenishOrderManager.cleanBatchInfo(batchId);
	}

	/**
	 * 读取调货单信息-保存至数据库中
	 * 
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws SAXException
	 */
	@Test
	@Rollback(false)
	public void test01() throws InvalidFormatException, IOException, SAXException {

		List<ReplenishOrderVo> replenishOrderList = replenishOrderManager.readReplenishOrderFile("C:\\AAAAAA配货单\\11111.xlsx");
		if (null == replenishOrderList || replenishOrderList.size() == 0) {
			System.out.println("############无效文件????????????????");
		} else {
			saveReplenishOrder(replenishOrderList, initOrgReplenishOrderList());
			System.out.println("############调货单保存成功????????????????");
		}
	}

	/**
	 * 初始化全部空白门店补货单
	 * 
	 * @param replenishOrderList
	 * @return
	 */
	private Map<String, Map<String, Integer>> initOrgReplenishOrderList() {

		Map<String, Map<String, Integer>> map = Maps.newHashMap();

		map.put("01D", generateOrgReplenishOrder());
		map.put("02D", generateOrgReplenishOrder());
		map.put("03D", generateOrgReplenishOrder());
		map.put("04D", generateOrgReplenishOrder());
		map.put("05D", generateOrgReplenishOrder());
		map.put("06D", generateOrgReplenishOrder());
		map.put("07D", generateOrgReplenishOrder());
		map.put("08D", generateOrgReplenishOrder());
		map.put("09D", generateOrgReplenishOrder());
		map.put("10D", generateOrgReplenishOrder());
		map.put("11D", generateOrgReplenishOrder());
		map.put("13D", generateOrgReplenishOrder());
		map.put("15D", generateOrgReplenishOrder());
		map.put("16D", generateOrgReplenishOrder());
		map.put("17D", generateOrgReplenishOrder());
		map.put("18D", generateOrgReplenishOrder());
		map.put("19D", generateOrgReplenishOrder());
		map.put("20D", generateOrgReplenishOrder());
		map.put("21D", generateOrgReplenishOrder());
		map.put("22D", generateOrgReplenishOrder());
		map.put("23D", generateOrgReplenishOrder());
		map.put("24D", generateOrgReplenishOrder());
		map.put("25D", generateOrgReplenishOrder());
		map.put("26D", generateOrgReplenishOrder());
		map.put("27D", generateOrgReplenishOrder());

		return map;
	}

	/**
	 * 初始化单个空白门店补货单
	 * 
	 * @return
	 */
	private Map<String, Integer> generateOrgReplenishOrder() {
		Map<String, Integer> _map = Maps.newHashMap();
		return _map;
	}

	/**
	 * 设置各门店补货数量（按货物编号）
	 * 
	 * @param orgOrderMap
	 * @param productNo
	 * @param productNum
	 * @param orgId
	 */
	private void setReplenishOrderInfo(Map<String, Map<String, Integer>> orgOrderMap, String productNo, String productNum,
			String orgId) {

		if (StringUtils.isNotBlank(productNum) && !"0".equals(productNum)) {
			Map<String, Integer> goods = orgOrderMap.get(orgId);

			Integer goodsNum = goods.get(productNo);
			if (null == goodsNum) {
				goods.put(productNo, Integer.parseInt(productNum));
			} else {
				goods.put(productNo, goodsNum + Integer.parseInt(productNum));
			}
		}
	}

	/**
	 * 保存补货单数据至数据库
	 * 
	 * @param replenishOrderList
	 * @param orgOrderMap
	 */
	private void saveReplenishOrder(List<ReplenishOrderVo> replenishOrderList, Map<String, Map<String, Integer>> orgOrderMap) {

		boolean firstFlg = true;
		for (ReplenishOrderVo vo : replenishOrderList) {
			if (firstFlg) {
				firstFlg = false;
				continue;
			}

			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum1(), "01D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum2(), "02D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum3(), "03D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum4(), "04D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum5(), "05D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum6(), "06D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum7(), "07D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum8(), "08D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum9(), "09D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum10(), "10D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum11(), "11D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum13(), "13D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum15(), "15D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum16(), "16D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum17(), "17D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum18(), "18D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum19(), "19D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum20(), "20D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum21(), "21D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum22(), "22D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum23(), "23D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum24(), "24D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum25(), "25D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum26(), "26D");
			setReplenishOrderInfo(orgOrderMap, vo.getProductNo(), vo.getProductNum27(), "27D");
		}

		_saveReplenishOrder(orgOrderMap);
	}

	/**
	 * 保存补货单数据至数据库
	 * 
	 * @param orgOrderMap
	 */
	private void _saveReplenishOrder(Map<String, Map<String, Integer>> orgOrderMap) {
		for (Entry<String, Map<String, Integer>> entry : orgOrderMap.entrySet()) {
			String orgId = entry.getKey();

			ReplenishOrder order = new ReplenishOrder();

			// 补货单生成批次号
			order.setOrderBatchId(batchId);
			// 补货单编号
			order.setOrderNo(batchId + "-" + orgId);
			// 补货机构代码
			order.setReplenishOrgId(orgId);
			// 补货单状态 01-编辑中 02-已发货 03-收货中 99-已确认 */
			order.setOrderState("01");
			List<ReplenishOrderDetail> detailList = Lists.newArrayList();
			// 补货单明细列表
			order.setDetailList(detailList);

			Map<String, Integer> goodsMap = entry.getValue();
			for (Entry<String, Integer> detail : goodsMap.entrySet()) {
				ReplenishOrderDetail _detail = new ReplenishOrderDetail();

				// 补货单生成批次号
				_detail.setOrderBatchId(batchId);
				// 补货单编号
				_detail.setOrderNo(batchId + "-" + orgId);
				// 商品条码
				_detail.setProductBarcode(detail.getKey());
				// 补货数量
				_detail.setReplenishNum(detail.getValue());

				detailList.add(_detail);
			}

			// 保存调货单信息
			replenishOrderManager.saveReplenishOrder(order);

		}

	}
}
