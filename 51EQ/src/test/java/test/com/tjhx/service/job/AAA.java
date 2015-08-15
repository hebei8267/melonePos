/**
 * 
 */
package test.com.tjhx.service.job;

import java.text.ParseException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.info.SalesOrdersDayTotalManager;
import com.tjhx.service.info.SalesWeekTotalGoodsManager2;

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

	@Test
	@Rollback(false)
	public void tt() throws ParseException {
		logger.info("同步支付宝销售额 Begin");
		cashDailyManager.synZFBSaleAmt();
		logger.info("同步支付宝销售额 Begin");
	}
}
