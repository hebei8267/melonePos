package test.com.tjhx.service.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.service.info.AccountFlowManager;

public class DDD extends SpringTransactionalTestCase {
	@Autowired
	private AccountFlowManager accountFlowManager;

	@Test
	@Rollback(false)
	public void execute() throws Exception {
		logger.info("计算会计余额 Begin");
		accountFlowManager.calBalanceAmt();
		logger.info("计算会计余额 End");

	}
}
