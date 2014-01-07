package test.com.tjhx.service.accounts;

import java.text.ParseException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.service.accounts.CashDailyManager;

public class CashDailyManagerTest extends SpringTransactionalTestCase {
	@Resource
	private CashDailyManager cashDailyManager;

	@Test
	@Rollback(false)
	public void getAll1() throws ParseException {
		cashDailyManager.calOptDate();
	}

}
