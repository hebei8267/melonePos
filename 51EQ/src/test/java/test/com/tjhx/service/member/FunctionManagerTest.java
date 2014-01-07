package test.com.tjhx.service.member;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.member.FunctionJpaDao;
import com.tjhx.entity.member.Function;

public class FunctionManagerTest extends SpringTransactionalTestCase {
	@Resource
	private FunctionJpaDao functionJpaDao;

	@Test
	@Rollback(false)
	public void saveNewFun1() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("用户管理");
		// 功能URL */
		fun.setFunUrl("user");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun2() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("机构管理");
		// 功能URL */
		fun.setFunUrl("organization");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun3() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("供应商管理");
		// 功能URL */
		fun.setFunUrl("supplier");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun4() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("银行卡管理");
		// 功能URL */
		fun.setFunUrl("bankCard");

		functionJpaDao.save(fun);
	}

	// -------------------------------------------------------------
	@Test
	@Rollback(false)
	public void saveNewFun5() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("销售信息-录入");
		// 功能URL */
		fun.setFunUrl("cashRun");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun6() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("销售信息-日结");
		// 功能URL */
		fun.setFunUrl("cashDaily");

		functionJpaDao.save(fun);
	}

	// -------------------------------------------------------------
	@Test
	@Rollback(false)
	public void saveNewFun7() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("入库信息-录入");
		// 功能URL */
		fun.setFunUrl("storeRun");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun8() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("入库信息-审核");
		// 功能URL */
		fun.setFunUrl("storeRunAudit");

		functionJpaDao.save(fun);
	}

	// -------------------------------------------------------------
	@Test
	@Rollback(false)
	public void saveNewFun9() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("入库信息-报表");
		// 功能URL */
		fun.setFunUrl("storeReport");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun10() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("刷卡信息-报表");
		// 功能URL */
		fun.setFunUrl("cardReport");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun11() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("销售信息-报表");
		// 功能URL */
		fun.setFunUrl("cashReport");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun12() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("日结信息-报表");
		// 功能URL */
		fun.setFunUrl("dailyReport");

		functionJpaDao.save(fun);
	}

	// -------------------------------------------------------------
	@Test
	@Rollback(false)
	public void saveNewFun13() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("发票信息-申请");
		// 功能URL */
		fun.setFunUrl("invoiceApply");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun14() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("发票信息-开票");
		// 功能URL */
		fun.setFunUrl("invoiceDraw");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun15() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("门店巡查报告");
		// 功能URL */
		fun.setFunUrl("inspect");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun16() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("考勤信息-查看");
		// 功能URL */
		fun.setFunUrl("punchClock/list");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun17() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("考勤信息-总部查看");
		// 功能URL */
		fun.setFunUrl("punchClock/manage");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun18() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("公告/消息");
		// 功能URL */
		fun.setFunUrl("msgInfo/list");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun19() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("排班表(维护)");
		// 排班表(维护)
		// 兼职信息(维护)
		// 工作时间(维护)
		// 功能URL */
		// fun.setFunUrl();

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun20() {
		Function fun = new Function();
		// 功能显示名称
		// 门店-门店备用金
		fun.setDisplayName("门店-门店备用金");
		// 功能URL */
		fun.setFunUrl("pettyCash/list");

		functionJpaDao.save(fun);
	}

	@Test
	@Rollback(false)
	public void saveNewFun21() {
		Function fun = new Function();
		// 功能显示名称
		fun.setDisplayName("总部-门店备用金");
		// 功能URL */
		fun.setFunUrl("pettyCash/manageList");

		functionJpaDao.save(fun);
	}
}
