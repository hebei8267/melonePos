package test.com.tjhx.service.info;

import java.text.ParseException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.service.info.SalesDayTotalManager;

public class SalesDayTotalManagerTest extends SpringTransactionalTestCase {
	@Resource
	private SalesDayTotalManager salesDayTotalManager;

	@Test
	@Rollback(false)
	public void test() throws ParseException {

		salesDayTotalManager.synBwSaleAmt();

	}

}
