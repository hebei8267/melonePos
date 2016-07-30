/**
 * 
 */
package test.com.tjhx.service.job;

import java.text.ParseException;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.info.OrgWeekSalesRankingManager;
import com.tjhx.service.info.SalesDayTotalManager;
import com.tjhx.service.info.SalesOrdersDayTotalManager;
import com.tjhx.service.info.SalesWeekTotalGoodsManager2;
import com.tjhx.service.info.StoreDetailManager;

/**
 * @author he_bei
 * 
 */
public class AAA extends SpringTransactionalTestCase {
	private static Logger logger = LoggerFactory.getLogger(AAA.class);
	@Autowired
	private SalesOrdersDayTotalManager salesOrdersDayTotalManager;
	@Autowired
	private CashDailyManager cashDailyManager;
	@Autowired
	private SalesWeekTotalGoodsManager2 salesWeekTotalGoodsManager2;
	@Resource
	private SalesDayTotalManager salesDayTotalManager;
	@Resource
	private OrgWeekSalesRankingManager orgWeekSalesRankingManager;
	@Autowired
	private StoreDetailManager storeDetailManager;
	@Test
	@Rollback(false)
	public void tt() throws ParseException {
		// logger.info("同步支付宝销售额 Begin");
		// cashDailyManager.synZFBSaleAmt();
		// logger.info("同步支付宝销售额 Begin");

//		logger.info("同步百威销售额（销售增长率） Begin");
//		salesDayTotalManager.synBwSaleAmt();
//		logger.info("同步百威销售额（销售增长率） End");
		
//		orgWeekSalesRankingManager.calOrgWeekSalesRanking();
//		
		
		
		
		logger.info("清理t_store_detail Begin");
		// 清理t_store_detail(数据量过大,其数据已汇总到t_store_day_total)
		// storeDetailManager.initTable();
		logger.info("清理t_store_detail End");

		// ##########################################################
		logger.info("取得门店库存信息 Begin");
		// 取得门店库存信息
		storeDetailManager.getOrgStoreDetail();
		logger.info("取得门店库存信息 End");

		
	}
}
