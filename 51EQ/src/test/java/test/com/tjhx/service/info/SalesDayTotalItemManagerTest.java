/**
 * 
 */
package test.com.tjhx.service.info;

import java.text.ParseException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.service.info.SalesDayTotalItemManager;
import com.tjhx.service.info.SalesMonthTotalItemManager;

/**
 * @author he_bei
 * 
 */
public class SalesDayTotalItemManagerTest extends SpringTransactionalTestCase {
	@Autowired
	private SalesDayTotalItemManager salesDayTotalItemManager;
	@Autowired
	private SalesMonthTotalItemManager salesMonthTotalItemManager;

	@Test
	@Rollback(false)
	public void test() throws ParseException {
		// 取得百威系统各门店日销售信息
		salesDayTotalItemManager.getOrgSalesDayTotal();
		// #################################################
		// 门店月销售计算
		salesMonthTotalItemManager.calOrgSalesMonthTotal();
	}
}
