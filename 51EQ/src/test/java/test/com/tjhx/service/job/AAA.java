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

/**
 * @author he_bei
 * 
 */
public class AAA extends SpringTransactionalTestCase {
	private static Logger logger = LoggerFactory.getLogger(AAA.class);
	@Autowired
	private CashDailyManager cashDailyManager;

	@Test
	@Rollback(false)
	public void tt() throws ParseException {
		logger.info("同步百威金卡销售额 Begin");
		cashDailyManager.synBwGoldCardSaleAmt();
		logger.info("同步百威金卡销售额 End");
	}
}
