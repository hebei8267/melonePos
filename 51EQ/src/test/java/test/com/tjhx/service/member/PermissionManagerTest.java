package test.com.tjhx.service.member;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.member.FunctionJpaDao;
import com.tjhx.dao.member.PermissionJpaDao;
import com.tjhx.dao.member.RoleJpaDao;
import com.tjhx.entity.member.Function;
import com.tjhx.entity.member.Permission;
import com.tjhx.entity.member.Role;

public class PermissionManagerTest extends SpringTransactionalTestCase {
	@Resource
	private FunctionJpaDao functionJpaDao;
	@Resource
	private PermissionJpaDao permissionJpaDao;
	@Resource
	private RoleJpaDao roleJpaDao;

	// 系统管理员
	@Test
	@Rollback(false)
	public void saveNewPerm1() {
		Role role = roleJpaDao.findOne(1);
		// ---------------------------------------------
		// 用户管理
		// ---------------------------------------------
		Permission perm1 = new Permission();
		Function fun1 = functionJpaDao.findOne(1);
		perm1.setRole(role);
		perm1.setFunction(fun1);
		permissionJpaDao.save(perm1);

		// ---------------------------------------------
		// 职员管理
		// ---------------------------------------------
		Permission perm2 = new Permission();
		Function fun2 = functionJpaDao.findOne(2);
		perm2.setRole(role);
		perm2.setFunction(fun2);
		permissionJpaDao.save(perm2);

		// ---------------------------------------------
		// 机构管理
		// ---------------------------------------------
		Permission perm3 = new Permission();
		Function fun3 = functionJpaDao.findOne(3);
		perm3.setRole(role);
		perm3.setFunction(fun3);
		permissionJpaDao.save(perm3);

		// ---------------------------------------------
		// 供应商管理
		// ---------------------------------------------
		Permission perm4 = new Permission();
		Function fun4 = functionJpaDao.findOne(4);
		perm4.setRole(role);
		perm4.setFunction(fun4);
		permissionJpaDao.save(perm4);

		// ---------------------------------------------
		// 银行卡管理
		// ---------------------------------------------
		Permission perm5 = new Permission();
		Function fun5 = functionJpaDao.findOne(5);
		perm5.setRole(role);
		perm5.setFunction(fun5);
		permissionJpaDao.save(perm5);

		// ---------------------------------------------
		// 商品管理
		// ---------------------------------------------
		Permission perm6 = new Permission();
		Function fun6 = functionJpaDao.findOne(6);
		perm6.setRole(role);
		perm6.setFunction(fun6);
		permissionJpaDao.save(perm6);

		// ---------------------------------------------
		// 商品类别管理
		// ---------------------------------------------
		Permission perm7 = new Permission();
		Function fun7 = functionJpaDao.findOne(7);
		perm7.setRole(role);
		perm7.setFunction(fun7);
		permissionJpaDao.save(perm7);

		// ================================================================
		// ---------------------------------------------
		// 报表-刷卡信息
		// ---------------------------------------------
		Permission perm13 = new Permission();
		Function fun13 = functionJpaDao.findOne(13);
		perm13.setRole(role);
		perm13.setFunction(fun13);
		permissionJpaDao.save(perm13);

		// ---------------------------------------------
		// 报表-日结信息
		// ---------------------------------------------
		Permission perm14 = new Permission();
		Function fun14 = functionJpaDao.findOne(14);
		perm14.setRole(role);
		perm14.setFunction(fun14);
		permissionJpaDao.save(perm14);

		// ---------------------------------------------
		// 报表-存款信息
		// ---------------------------------------------
		Permission perm15 = new Permission();
		Function fun15 = functionJpaDao.findOne(15);
		perm15.setRole(role);
		perm15.setFunction(fun15);
		permissionJpaDao.save(perm15);

		// ---------------------------------------------
		// 报表-销售信息(表格)
		// ---------------------------------------------
		Permission perm16 = new Permission();
		Function fun16 = functionJpaDao.findOne(16);
		perm16.setRole(role);
		perm16.setFunction(fun16);
		permissionJpaDao.save(perm16);

		// ---------------------------------------------
		// 报表-销售信息(图形)
		// ---------------------------------------------
		Permission perm17 = new Permission();
		Function fun17 = functionJpaDao.findOne(17);
		perm17.setRole(role);
		perm17.setFunction(fun17);
		permissionJpaDao.save(perm17);

		// ---------------------------------------------
		// 报表-库存信息(表格)
		// ---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// ---------------------------------------------
		// 报表-库存信息(图形)
		// ---------------------------------------------
		Permission perm19 = new Permission();
		Function fun19 = functionJpaDao.findOne(19);
		perm19.setRole(role);
		perm19.setFunction(fun19);
		permissionJpaDao.save(perm19);

		// ---------------------------------------------
		// 报表-商品销售信息一览(按条码)
		// ---------------------------------------------
		Permission perm20 = new Permission();
		Function fun20 = functionJpaDao.findOne(20);
		perm20.setRole(role);
		perm20.setFunction(fun20);
		permissionJpaDao.save(perm20);

		// ---------------------------------------------
		// 报表-商品周销售信息一览
		// ---------------------------------------------
		Permission perm21 = new Permission();
		Function fun21 = functionJpaDao.findOne(21);
		perm21.setRole(role);
		perm21.setFunction(fun21);
		permissionJpaDao.save(perm21);

		// ---------------------------------------------
		// 报表-类别销售信息一览(图形)
		// ---------------------------------------------
		Permission perm22 = new Permission();
		Function fun22 = functionJpaDao.findOne(22);
		perm22.setRole(role);
		perm22.setFunction(fun22);
		permissionJpaDao.save(perm22);

		// ---------------------------------------------
		// 报表-类别销售金额对比(图形)
		// ---------------------------------------------
		Permission perm23 = new Permission();
		Function fun23 = functionJpaDao.findOne(23);
		perm23.setRole(role);
		perm23.setFunction(fun23);
		permissionJpaDao.save(perm23);

		// ---------------------------------------------
		// 报表-货商销售信息一览(图形)
		// ---------------------------------------------
		Permission perm24 = new Permission();
		Function fun24 = functionJpaDao.findOne(24);
		perm24.setRole(role);
		perm24.setFunction(fun24);
		permissionJpaDao.save(perm24);

		// ---------------------------------------------
		// 报表-货商销售金额对比(图形)
		// ---------------------------------------------
		Permission perm25 = new Permission();
		Function fun25 = functionJpaDao.findOne(25);
		perm25.setRole(role);
		perm25.setFunction(fun25);
		permissionJpaDao.save(perm25);

		// ---------------------------------------------
		// 报表-商品周销售信息＆重计算
		// ---------------------------------------------
		Permission perm26 = new Permission();
		Function fun26 = functionJpaDao.findOne(26);
		perm26.setRole(role);
		perm26.setFunction(fun26);
		permissionJpaDao.save(perm26);

		// ---------------------------------------------
		// 发票开具
		// ---------------------------------------------
		Permission perm28 = new Permission();
		Function fun28 = functionJpaDao.findOne(28);
		perm28.setRole(role);
		perm28.setFunction(fun28);
		permissionJpaDao.save(perm28);
		// ---------------------------------------------
		// 门店备用金
		// ---------------------------------------------
		Permission perm38 = new Permission();
		Function fun38 = functionJpaDao.findOne(38);
		perm38.setRole(role);
		perm38.setFunction(fun38);
		permissionJpaDao.save(perm38);
		// ---------------------------------------------
		// 门店备用金(结转)
		// ---------------------------------------------
		Permission perm39 = new Permission();
		Function fun39 = functionJpaDao.findOne(39);
		perm39.setRole(role);
		perm39.setFunction(fun39);
		permissionJpaDao.save(perm39);

		// ---------------------------------------------
		// 门店巡查报告
		// ---------------------------------------------
		Permission perm29 = new Permission();
		Function fun29 = functionJpaDao.findOne(29);
		perm29.setRole(role);
		perm29.setFunction(fun29);
		permissionJpaDao.save(perm29);

		// ---------------------------------------------
		// 考勤信息-总部查看
		// ---------------------------------------------
		Permission perm35 = new Permission();
		Function fun35 = functionJpaDao.findOne(35);
		perm35.setRole(role);
		perm35.setFunction(fun35);
		permissionJpaDao.save(perm35);
		// ---------------------------------------------
		// 公告/消息
		// ---------------------------------------------
		Permission perm36 = new Permission();
		Function fun36 = functionJpaDao.findOne(36);
		perm36.setRole(role);
		perm36.setFunction(fun36);
		permissionJpaDao.save(perm36);

		// ---------------------------------------------
		// 反日结(销售信息)
		// ---------------------------------------------
		Permission perm40 = new Permission();
		Function fun40 = functionJpaDao.findOne(40);
		perm40.setRole(role);
		perm40.setFunction(fun40);
		permissionJpaDao.save(perm40);

		// ---------------------------------------------
		// 月销售金额对比(图形)
		// ---------------------------------------------
		Permission perm41 = new Permission();
		Function fun41 = functionJpaDao.findOne(41);
		perm41.setRole(role);
		perm41.setFunction(fun41);
		permissionJpaDao.save(perm41);
	}

	// 总部人员-会计
	@Test
	@Rollback(false)
	public void saveNewPerm2() {
		Role role = roleJpaDao.findOne(2);
		// ================================================================
		// ---------------------------------------------
		// 报表-刷卡信息
		// ---------------------------------------------
		Permission perm13 = new Permission();
		Function fun13 = functionJpaDao.findOne(13);
		perm13.setRole(role);
		perm13.setFunction(fun13);
		permissionJpaDao.save(perm13);

		// ---------------------------------------------
		// 报表-日结信息
		// ---------------------------------------------
		Permission perm14 = new Permission();
		Function fun14 = functionJpaDao.findOne(14);
		perm14.setRole(role);
		perm14.setFunction(fun14);
		permissionJpaDao.save(perm14);

		// ---------------------------------------------
		// 报表-存款信息
		// ---------------------------------------------
		Permission perm15 = new Permission();
		Function fun15 = functionJpaDao.findOne(15);
		perm15.setRole(role);
		perm15.setFunction(fun15);
		permissionJpaDao.save(perm15);

		// ---------------------------------------------
		// 报表-销售信息(表格)
		// ---------------------------------------------
		Permission perm16 = new Permission();
		Function fun16 = functionJpaDao.findOne(16);
		perm16.setRole(role);
		perm16.setFunction(fun16);
		permissionJpaDao.save(perm16);

		// ---------------------------------------------
		// 报表-销售信息(图形)
		// ---------------------------------------------
		Permission perm17 = new Permission();
		Function fun17 = functionJpaDao.findOne(17);
		perm17.setRole(role);
		perm17.setFunction(fun17);
		permissionJpaDao.save(perm17);

		// ---------------------------------------------
		// 报表-库存信息(表格)
		// ---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// ---------------------------------------------
		// 报表-库存信息(图形)
		// ---------------------------------------------
		Permission perm19 = new Permission();
		Function fun19 = functionJpaDao.findOne(19);
		perm19.setRole(role);
		perm19.setFunction(fun19);
		permissionJpaDao.save(perm19);

		// ---------------------------------------------
		// 报表-商品销售信息一览(按条码)
		// ---------------------------------------------
		Permission perm20 = new Permission();
		Function fun20 = functionJpaDao.findOne(20);
		perm20.setRole(role);
		perm20.setFunction(fun20);
		permissionJpaDao.save(perm20);

		// ---------------------------------------------
		// 报表-商品周销售信息一览
		// ---------------------------------------------
		Permission perm21 = new Permission();
		Function fun21 = functionJpaDao.findOne(21);
		perm21.setRole(role);
		perm21.setFunction(fun21);
		permissionJpaDao.save(perm21);

		// ---------------------------------------------
		// 发票开具
		// ---------------------------------------------
		Permission perm28 = new Permission();
		Function fun28 = functionJpaDao.findOne(28);
		perm28.setRole(role);
		perm28.setFunction(fun28);
		permissionJpaDao.save(perm28);
		// ---------------------------------------------
		// 门店备用金
		// ---------------------------------------------
		Permission perm38 = new Permission();
		Function fun38 = functionJpaDao.findOne(38);
		perm38.setRole(role);
		perm38.setFunction(fun38);
		permissionJpaDao.save(perm38);
		// ---------------------------------------------
		// 门店备用金(结转)
		// ---------------------------------------------
		Permission perm39 = new Permission();
		Function fun39 = functionJpaDao.findOne(39);
		perm39.setRole(role);
		perm39.setFunction(fun39);
		permissionJpaDao.save(perm39);

		// ---------------------------------------------
		// 门店巡查报告
		// ---------------------------------------------
		Permission perm29 = new Permission();
		Function fun29 = functionJpaDao.findOne(29);
		perm29.setRole(role);
		perm29.setFunction(fun29);
		permissionJpaDao.save(perm29);

		// ---------------------------------------------
		// 考勤信息-总部查看
		// ---------------------------------------------
		Permission perm35 = new Permission();
		Function fun35 = functionJpaDao.findOne(35);
		perm35.setRole(role);
		perm35.setFunction(fun35);
		permissionJpaDao.save(perm35);
		// ---------------------------------------------
		// 公告/消息
		// ---------------------------------------------
		Permission perm36 = new Permission();
		Function fun36 = functionJpaDao.findOne(36);
		perm36.setRole(role);
		perm36.setFunction(fun36);
		permissionJpaDao.save(perm36);

		// ---------------------------------------------
		// 反日结(销售信息)
		// ---------------------------------------------
		Permission perm40 = new Permission();
		Function fun40 = functionJpaDao.findOne(40);
		perm40.setRole(role);
		perm40.setFunction(fun40);
		permissionJpaDao.save(perm40);

	}

	// 总部人员-普通
	@Test
	@Rollback(false)
	public void saveNewPerm3() {
		Role role = roleJpaDao.findOne(3);
		// ================================================================

		// ---------------------------------------------
		// 报表-销售信息(表格)
		// ---------------------------------------------
		Permission perm16 = new Permission();
		Function fun16 = functionJpaDao.findOne(16);
		perm16.setRole(role);
		perm16.setFunction(fun16);
		permissionJpaDao.save(perm16);

		// ---------------------------------------------
		// 报表-销售信息(图形)
		// ---------------------------------------------
		Permission perm17 = new Permission();
		Function fun17 = functionJpaDao.findOne(17);
		perm17.setRole(role);
		perm17.setFunction(fun17);
		permissionJpaDao.save(perm17);

		// ---------------------------------------------
		// 报表-库存信息(表格)
		// ---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// ---------------------------------------------
		// 报表-库存信息(图形)
		// ---------------------------------------------
		Permission perm19 = new Permission();
		Function fun19 = functionJpaDao.findOne(19);
		perm19.setRole(role);
		perm19.setFunction(fun19);
		permissionJpaDao.save(perm19);

		// ---------------------------------------------
		// 报表-商品销售信息一览(按条码)
		// ---------------------------------------------
		Permission perm20 = new Permission();
		Function fun20 = functionJpaDao.findOne(20);
		perm20.setRole(role);
		perm20.setFunction(fun20);
		permissionJpaDao.save(perm20);

		// ---------------------------------------------
		// 报表-商品周销售信息一览
		// ---------------------------------------------
		Permission perm21 = new Permission();
		Function fun21 = functionJpaDao.findOne(21);
		perm21.setRole(role);
		perm21.setFunction(fun21);
		permissionJpaDao.save(perm21);

		// ---------------------------------------------
		// 公告/消息
		// ---------------------------------------------
		Permission perm36 = new Permission();
		Function fun36 = functionJpaDao.findOne(36);
		perm36.setRole(role);
		perm36.setFunction(fun36);
		permissionJpaDao.save(perm36);

		// ---------------------------------------------
		// 考勤信息-总部查看
		// ---------------------------------------------
		Permission perm35 = new Permission();
		Function fun35 = functionJpaDao.findOne(35);
		perm35.setRole(role);
		perm35.setFunction(fun35);
		permissionJpaDao.save(perm35);

	}

	// 总部人员-库管
	@Test
	@Rollback(false)
	public void saveNewPerm4() {
		Role role = roleJpaDao.findOne(4);
		// ================================================================

		// ---------------------------------------------
		// 报表-销售信息(表格)
		// ---------------------------------------------
		Permission perm16 = new Permission();
		Function fun16 = functionJpaDao.findOne(16);
		perm16.setRole(role);
		perm16.setFunction(fun16);
		permissionJpaDao.save(perm16);

		// ---------------------------------------------
		// 报表-销售信息(图形)
		// ---------------------------------------------
		Permission perm17 = new Permission();
		Function fun17 = functionJpaDao.findOne(17);
		perm17.setRole(role);
		perm17.setFunction(fun17);
		permissionJpaDao.save(perm17);

		// ---------------------------------------------
		// 报表-库存信息(表格)
		// ---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// ---------------------------------------------
		// 报表-库存信息(图形)
		// ---------------------------------------------
		Permission perm19 = new Permission();
		Function fun19 = functionJpaDao.findOne(19);
		perm19.setRole(role);
		perm19.setFunction(fun19);
		permissionJpaDao.save(perm19);

		// ---------------------------------------------
		// 报表-商品销售信息一览(按条码)
		// ---------------------------------------------
		Permission perm20 = new Permission();
		Function fun20 = functionJpaDao.findOne(20);
		perm20.setRole(role);
		perm20.setFunction(fun20);
		permissionJpaDao.save(perm20);

		// ---------------------------------------------
		// 报表-商品周销售信息一览
		// ---------------------------------------------
		Permission perm21 = new Permission();
		Function fun21 = functionJpaDao.findOne(21);
		perm21.setRole(role);
		perm21.setFunction(fun21);
		permissionJpaDao.save(perm21);

		// ---------------------------------------------
		// 报表-商品周销售信息＆重计算
		// ---------------------------------------------
		Permission perm26 = new Permission();
		Function fun26 = functionJpaDao.findOne(26);
		perm26.setRole(role);
		perm26.setFunction(fun26);
		permissionJpaDao.save(perm26);
		// ---------------------------------------------
		// 公告/消息
		// ---------------------------------------------
		Permission perm36 = new Permission();
		Function fun36 = functionJpaDao.findOne(36);
		perm36.setRole(role);
		perm36.setFunction(fun36);
		permissionJpaDao.save(perm36);

	}

	// Boss
	@Test
	@Rollback(false)
	public void saveNewPerm5() {
		Role role = roleJpaDao.findOne(5);

		// ---------------------------------------------
		// 报表-刷卡信息
		// ---------------------------------------------
		Permission perm13 = new Permission();
		Function fun13 = functionJpaDao.findOne(13);
		perm13.setRole(role);
		perm13.setFunction(fun13);
		permissionJpaDao.save(perm13);

		// ---------------------------------------------
		// 报表-日结信息
		// ---------------------------------------------
		Permission perm14 = new Permission();
		Function fun14 = functionJpaDao.findOne(14);
		perm14.setRole(role);
		perm14.setFunction(fun14);
		permissionJpaDao.save(perm14);

		// ---------------------------------------------
		// 报表-存款信息
		// ---------------------------------------------
		Permission perm15 = new Permission();
		Function fun15 = functionJpaDao.findOne(15);
		perm15.setRole(role);
		perm15.setFunction(fun15);
		permissionJpaDao.save(perm15);

		// ---------------------------------------------
		// 报表-销售信息(表格)
		// ---------------------------------------------
		Permission perm16 = new Permission();
		Function fun16 = functionJpaDao.findOne(16);
		perm16.setRole(role);
		perm16.setFunction(fun16);
		permissionJpaDao.save(perm16);

		// ---------------------------------------------
		// 报表-销售信息(图形)
		// ---------------------------------------------
		Permission perm17 = new Permission();
		Function fun17 = functionJpaDao.findOne(17);
		perm17.setRole(role);
		perm17.setFunction(fun17);
		permissionJpaDao.save(perm17);

		// ---------------------------------------------
		// 报表-库存信息(表格)
		// ---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// ---------------------------------------------
		// 报表-库存信息(图形)
		// ---------------------------------------------
		Permission perm19 = new Permission();
		Function fun19 = functionJpaDao.findOne(19);
		perm19.setRole(role);
		perm19.setFunction(fun19);
		permissionJpaDao.save(perm19);

		// ---------------------------------------------
		// 报表-商品销售信息一览(按条码)
		// ---------------------------------------------
		Permission perm20 = new Permission();
		Function fun20 = functionJpaDao.findOne(20);
		perm20.setRole(role);
		perm20.setFunction(fun20);
		permissionJpaDao.save(perm20);

		// ---------------------------------------------
		// 报表-商品周销售信息一览
		// ---------------------------------------------
		Permission perm21 = new Permission();
		Function fun21 = functionJpaDao.findOne(21);
		perm21.setRole(role);
		perm21.setFunction(fun21);
		permissionJpaDao.save(perm21);

		// ---------------------------------------------
		// 报表-类别销售信息一览(图形)
		// ---------------------------------------------
		Permission perm22 = new Permission();
		Function fun22 = functionJpaDao.findOne(22);
		perm22.setRole(role);
		perm22.setFunction(fun22);
		permissionJpaDao.save(perm22);

		// ---------------------------------------------
		// 报表-类别销售金额对比(图形)
		// ---------------------------------------------
		Permission perm23 = new Permission();
		Function fun23 = functionJpaDao.findOne(23);
		perm23.setRole(role);
		perm23.setFunction(fun23);
		permissionJpaDao.save(perm23);

		// ---------------------------------------------
		// 报表-货商销售信息一览(图形)
		// ---------------------------------------------
		Permission perm24 = new Permission();
		Function fun24 = functionJpaDao.findOne(24);
		perm24.setRole(role);
		perm24.setFunction(fun24);
		permissionJpaDao.save(perm24);

		// ---------------------------------------------
		// 报表-货商销售金额对比(图形)
		// ---------------------------------------------
		Permission perm25 = new Permission();
		Function fun25 = functionJpaDao.findOne(25);
		perm25.setRole(role);
		perm25.setFunction(fun25);
		permissionJpaDao.save(perm25);

		// ---------------------------------------------
		// 报表-商品周销售信息＆重计算
		// ---------------------------------------------
		Permission perm26 = new Permission();
		Function fun26 = functionJpaDao.findOne(26);
		perm26.setRole(role);
		perm26.setFunction(fun26);
		permissionJpaDao.save(perm26);

		// ---------------------------------------------
		// 发票开具
		// ---------------------------------------------
		Permission perm28 = new Permission();
		Function fun28 = functionJpaDao.findOne(28);
		perm28.setRole(role);
		perm28.setFunction(fun28);
		permissionJpaDao.save(perm28);
		// ---------------------------------------------
		// 门店备用金
		// ---------------------------------------------
		Permission perm38 = new Permission();
		Function fun38 = functionJpaDao.findOne(38);
		perm38.setRole(role);
		perm38.setFunction(fun38);
		permissionJpaDao.save(perm38);
		// ---------------------------------------------
		// 门店备用金(结转)
		// ---------------------------------------------
		Permission perm39 = new Permission();
		Function fun39 = functionJpaDao.findOne(39);
		perm39.setRole(role);
		perm39.setFunction(fun39);
		permissionJpaDao.save(perm39);

		// ---------------------------------------------
		// 门店巡查报告
		// ---------------------------------------------
		Permission perm29 = new Permission();
		Function fun29 = functionJpaDao.findOne(29);
		perm29.setRole(role);
		perm29.setFunction(fun29);
		permissionJpaDao.save(perm29);

		// ---------------------------------------------
		// 考勤信息-总部查看
		// ---------------------------------------------
		Permission perm35 = new Permission();
		Function fun35 = functionJpaDao.findOne(35);
		perm35.setRole(role);
		perm35.setFunction(fun35);
		permissionJpaDao.save(perm35);
		// ---------------------------------------------
		// 公告/消息
		// ---------------------------------------------
		Permission perm36 = new Permission();
		Function fun36 = functionJpaDao.findOne(36);
		perm36.setRole(role);
		perm36.setFunction(fun36);
		permissionJpaDao.save(perm36);

		// ---------------------------------------------
		// 反日结(销售信息)
		// ---------------------------------------------
		Permission perm40 = new Permission();
		Function fun40 = functionJpaDao.findOne(40);
		perm40.setRole(role);
		perm40.setFunction(fun40);
		permissionJpaDao.save(perm40);

		// ---------------------------------------------
		// 月销售金额对比(图形)
		// ---------------------------------------------
		Permission perm41 = new Permission();
		Function fun41 = functionJpaDao.findOne(41);
		perm41.setRole(role);
		perm41.setFunction(fun41);
		permissionJpaDao.save(perm41);
	}

	// 店长
	@Test
	@Rollback(false)
	public void saveNewPerm6() {
		Role role = roleJpaDao.findOne(6);
		// ---------------------------------------------
		// 门店-销售信息-录入
		// ---------------------------------------------
		Permission perm8 = new Permission();
		Function fun8 = functionJpaDao.findOne(8);
		perm8.setRole(role);
		perm8.setFunction(fun8);
		permissionJpaDao.save(perm8);

		// ---------------------------------------------
		// 门店-销售信息-日结
		// ---------------------------------------------
		Permission perm9 = new Permission();
		Function fun9 = functionJpaDao.findOne(9);
		perm9.setRole(role);
		perm9.setFunction(fun9);
		permissionJpaDao.save(perm9);

		// ---------------------------------------------
		// 门店-入库信息-录入
		// ---------------------------------------------
		Permission perm10 = new Permission();
		Function fun10 = functionJpaDao.findOne(10);
		perm10.setRole(role);
		perm10.setFunction(fun10);
		permissionJpaDao.save(perm10);

		// ---------------------------------------------
		// 门店-入库信息-审核
		// ---------------------------------------------
		Permission perm11 = new Permission();
		Function fun11 = functionJpaDao.findOne(11);
		perm11.setRole(role);
		perm11.setFunction(fun11);
		permissionJpaDao.save(perm11);

		// ---------------------------------------------
		// 门店-发票信息-申请
		// ---------------------------------------------
		Permission perm27 = new Permission();
		Function fun27 = functionJpaDao.findOne(27);
		perm27.setRole(role);
		perm27.setFunction(fun27);
		permissionJpaDao.save(perm27);

		// ---------------------------------------------
		// 公告/消息
		// ---------------------------------------------
		Permission perm36 = new Permission();
		Function fun36 = functionJpaDao.findOne(36);
		perm36.setRole(role);
		perm36.setFunction(fun36);
		permissionJpaDao.save(perm36);

		// ---------------------------------------------
		// 考勤信息-查看
		// ---------------------------------------------
		Permission perm30 = new Permission();
		Function fun30 = functionJpaDao.findOne(30);
		perm30.setRole(role);
		perm30.setFunction(fun30);
		permissionJpaDao.save(perm30);

		// ---------------------------------------------
		// 排班表-查看
		// ---------------------------------------------
		Permission perm31 = new Permission();
		Function fun31 = functionJpaDao.findOne(31);
		perm31.setRole(role);
		perm31.setFunction(fun31);
		permissionJpaDao.save(perm31);

		// ---------------------------------------------
		// 排班表-维护
		// ---------------------------------------------
		Permission perm32 = new Permission();
		Function fun32 = functionJpaDao.findOne(32);
		perm32.setRole(role);
		perm32.setFunction(fun32);
		permissionJpaDao.save(perm32);

		// ---------------------------------------------
		// 兼职信息-维护
		// ---------------------------------------------
		Permission perm33 = new Permission();
		Function fun33 = functionJpaDao.findOne(33);
		perm33.setRole(role);
		perm33.setFunction(fun33);
		permissionJpaDao.save(perm33);

		// ---------------------------------------------
		// 工作时间-维护
		// ---------------------------------------------
		Permission perm34 = new Permission();
		Function fun34 = functionJpaDao.findOne(34);
		perm34.setRole(role);
		perm34.setFunction(fun34);
		permissionJpaDao.save(perm34);

		// 门店-门店备用金---------------------------------------------
		Permission perm37 = new Permission();
		Function fun37 = functionJpaDao.findOne(37);
		perm37.setRole(role);
		perm37.setFunction(fun37);
		permissionJpaDao.save(perm37);
	}

	// 店助
	@Test
	@Rollback(false)
	public void saveNewPerm7() {

		Role role = roleJpaDao.findOne(7);
		// ---------------------------------------------
		// 门店-销售信息-录入
		// ---------------------------------------------
		Permission perm8 = new Permission();
		Function fun8 = functionJpaDao.findOne(8);
		perm8.setRole(role);
		perm8.setFunction(fun8);
		permissionJpaDao.save(perm8);

		// ---------------------------------------------
		// 门店-入库信息-录入
		// ---------------------------------------------
		Permission perm10 = new Permission();
		Function fun10 = functionJpaDao.findOne(10);
		perm10.setRole(role);
		perm10.setFunction(fun10);
		permissionJpaDao.save(perm10);

		// ---------------------------------------------
		// 门店-发票信息-申请
		// ---------------------------------------------
		Permission perm27 = new Permission();
		Function fun27 = functionJpaDao.findOne(27);
		perm27.setRole(role);
		perm27.setFunction(fun27);
		permissionJpaDao.save(perm27);

		// ---------------------------------------------
		// 公告/消息
		// ---------------------------------------------
		Permission perm36 = new Permission();
		Function fun36 = functionJpaDao.findOne(36);
		perm36.setRole(role);
		perm36.setFunction(fun36);
		permissionJpaDao.save(perm36);

		// ---------------------------------------------
		// 考勤信息-查看
		// ---------------------------------------------
		Permission perm30 = new Permission();
		Function fun30 = functionJpaDao.findOne(30);
		perm30.setRole(role);
		perm30.setFunction(fun30);
		permissionJpaDao.save(perm30);

		// ---------------------------------------------
		// 排班表-查看
		// ---------------------------------------------
		Permission perm31 = new Permission();
		Function fun31 = functionJpaDao.findOne(31);
		perm31.setRole(role);
		perm31.setFunction(fun31);
		permissionJpaDao.save(perm31);

		// 门店-门店备用金---------------------------------------------
		Permission perm37 = new Permission();
		Function fun37 = functionJpaDao.findOne(37);
		perm37.setRole(role);
		perm37.setFunction(fun37);
		permissionJpaDao.save(perm37);

	}
}
