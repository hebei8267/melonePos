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

//	@Test
//	@Rollback(false)
//	// 系统管理员
//	public void saveNewFun1() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("用户管理");
//		// 功能URL */
//		fun.setFunUrl("user");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 系统管理员
//	public void saveNewFun2() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("职员管理");
//		// 功能URL */
//		fun.setFunUrl("employee");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 系统管理员
//	public void saveNewFun3() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("机构管理");
//		// 功能URL */
//		fun.setFunUrl("organization");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 系统管理员
//	public void saveNewFun4() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("供应商管理");
//		// 功能URL */
//		fun.setFunUrl("supplier");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 系统管理员
//	public void saveNewFun5() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("银行卡管理");
//		// 功能URL */
//		fun.setFunUrl("bankCard");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 系统管理员
//	public void saveNewFun6() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("商品管理");
//		// 功能URL */
//		fun.setFunUrl("goods");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 系统管理员
//	public void saveNewFun7() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("商品类别管理");
//		// 功能URL */
//		fun.setFunUrl("itemType");
//
//		functionJpaDao.save(fun);
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun8() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("销售信息-录入");
//		// 功能URL */
//		fun.setFunUrl("cashRun");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun9() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("销售信息-日结");
//		// 功能URL */
//		fun.setFunUrl("cashDaily");
//
//		functionJpaDao.save(fun);
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun10() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("入库信息-录入");
//		// 功能URL */
//		fun.setFunUrl("storeRun");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun11() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("入库信息-审核");
//		// 功能URL */
//		fun.setFunUrl("storeRunAudit");
//
//		functionJpaDao.save(fun);
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun12() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("入库信息-报表");
//		// 功能URL */
//		fun.setFunUrl("storeReport");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun13() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("刷卡信息");
//		// 功能URL */
//		fun.setFunUrl("cardReport");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun14() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("日结信息");
//		// 功能URL */
//		fun.setFunUrl("dailyReport");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun15() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("存款信息");
//		// 功能URL */
//		fun.setFunUrl("bankCheck/init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun16() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("销售信息(表格)");
//		// 功能URL */
//		fun.setFunUrl("cashReport");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun17() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("销售信息(图形)");
//		// 功能URL */
//		fun.setFunUrl("cashChartReport");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun18() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("库存信息(表格)");
//		// 功能URL */
//		fun.setFunUrl("storeChartReport/list");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun19() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("库存信息(图形)");
//		// 功能URL */
//		fun.setFunUrl("storeChartReport");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun20() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("商品销售信息一览(按条码)");
//		// 功能URL */
//		fun.setFunUrl("salesDayGoodsReport");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun21() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("商品周销售信息一览");
//		// 功能URL */
//		fun.setFunUrl("salesWeekGoodsTotalReport/init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun22() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("类别销售信息一览(图形)");
//		// 功能URL */
//		fun.setFunUrl("salesDayItemChartReport/bar_init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun23() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("类别销售金额对比(图形)");
//		// 功能URL */
//		fun.setFunUrl("salesDayItemChartReport/pie_init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun24() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("货商销售信息一览(图形)");
//		// 功能URL */
//		fun.setFunUrl("salesDaySupChartReport/bar_init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun25() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("货商销售金额对比(图形)");
//		// 功能URL */
//		fun.setFunUrl("salesDaySupChartReport/pie_init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun26() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("商品周销售信息＆重计算");
//		// 功能URL */
//		fun.setFunUrl("salesWeekTotal/reCalInit");
//
//		functionJpaDao.save(fun);
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	@Rollback(false)
//	public void saveNewFun27() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("发票信息-申请");
//		// 功能URL */
//		fun.setFunUrl("invoiceApply");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun28() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("发票信息-开票");
//		// 功能URL */
//		fun.setFunUrl("invoiceDraw");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun29() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("门店巡查报告");
//		// 功能URL */
//		fun.setFunUrl("inspect");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun30() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("考勤信息-查看");
//		// 功能URL */
//		fun.setFunUrl("punchClock/list");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun31() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("排班表(查看)");
//		// 功能URL */
//		fun.setFunUrl("workSchedule/historyList");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun32() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("排班表(维护)");
//		// 功能URL */
//		fun.setFunUrl("workSchedule/list");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun33() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("兼职信息(维护)");
//		// 功能URL */
//		fun.setFunUrl("tmpEmployee/list");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 门店
//	public void saveNewFun34() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("工作时间(维护)");
//		// 功能URL */
//		fun.setFunUrl("workType/list");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	// 总部
//	public void saveNewFun35() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("考勤信息(查看)");
//		// 功能URL */
//		fun.setFunUrl("punchClock/manage");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun36() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("公告/消息");
//		// 功能URL */
//		fun.setFunUrl("msgInfo");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun37() {
//		Function fun = new Function();
//		// 功能显示名称
//		// 门店-门店备用金
//		fun.setDisplayName("门店-门店备用金");
//		// 功能URL */
//		fun.setFunUrl("pettyCash/list");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun38() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("总部-门店备用金");
//		// 功能URL */
//		fun.setFunUrl("pettyCash/manageList");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun39() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("门店备用金(结转)");
//		// 功能URL */
//		fun.setFunUrl("pettyCash/carryOverInit");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun40() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("反日结(销售信息)");
//		// 功能URL */
//		fun.setFunUrl("cashCounterDaily/init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun41() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("月销售金额对比(图形)");
//		// 功能URL */
//		fun.setFunUrl("salesMonthItemChartReport/bar_init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun42() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("挂账供应商结算进度表-会计用");
//		// 功能URL */
//		fun.setFunUrl("supplierSignRun/init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun43() {
//		Function fun = new Function();
//		// 功能显示名称
//		fun.setDisplayName("挂账供应商结算进度表-BOSS用");
//		// 功能URL */
//		fun.setFunUrl("supplierSignRun/init_boss");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun44() {
//		Function fun = new Function();
//		// 功能显示名称--会计、BOSS用
//		fun.setDisplayName("日销售信息对比");
//		// 功能URL */
//		fun.setFunUrl("salesDayChartReport/init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun45() {
//		Function fun = new Function();
//		// 功能显示名称--会计、BOSS用
//		fun.setDisplayName("销售排名信息(按类别)--总部");
//		// 功能URL */
//		fun.setFunUrl("salesItemRankReport/init");
//
//		functionJpaDao.save(fun);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewFun46() {
//		Function fun = new Function();
//		// 功能显示名称--门店
//		fun.setDisplayName("日销售信息对比--门店");
//		// 功能URL */
//		fun.setFunUrl("salesDayChartReport/init_shop");
//
//		functionJpaDao.save(fun);
//	}

//	@Test
//	@Rollback(false)
//	public void saveNewFun47() {
//		Function fun = new Function();
//		// 功能显示名称--门店
//		fun.setDisplayName("门店备用金分析(图形)");
//		// 功能URL */
//		fun.setFunUrl("pettyCash/analytics_init");
//
//		functionJpaDao.save(fun);
//	}
//	
//	@Test
//	@Rollback(false)
//	public void saveNewFun48() {
//		Function fun = new Function();
//		// 功能显示名称--门店
//		fun.setDisplayName("代金卷类别管理");
//		// 功能URL */
//		fun.setFunUrl("coupon/list");
//
//		functionJpaDao.save(fun);
//	}
	
	@Test
	@Rollback(false)
	public void saveNewFun49() {
		Function fun = new Function();
		// 功能显示名称--门店
		fun.setDisplayName("月销售目标管理");
		// 功能URL */
		fun.setFunUrl("monthSaleTarget/init");

		functionJpaDao.save(fun);
	}
}
