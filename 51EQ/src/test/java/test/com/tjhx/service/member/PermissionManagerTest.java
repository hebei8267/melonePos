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
		Permission perm1 = new Permission();
		Function fun1 = functionJpaDao.findOne(1);
		perm1.setRole(role);
		perm1.setFunction(fun1);
		permissionJpaDao.save(perm1);

		// ---------------------------------------------
		Permission perm2 = new Permission();
		Function fun2 = functionJpaDao.findOne(2);
		perm2.setRole(role);
		perm2.setFunction(fun2);
		permissionJpaDao.save(perm2);

		// ---------------------------------------------
		Permission perm3 = new Permission();
		Function fun3 = functionJpaDao.findOne(3);
		perm3.setRole(role);
		perm3.setFunction(fun3);
		permissionJpaDao.save(perm3);

		// ---------------------------------------------
		Permission perm4 = new Permission();
		Function fun4 = functionJpaDao.findOne(4);
		perm4.setRole(role);
		perm4.setFunction(fun4);
		permissionJpaDao.save(perm4);

		// 报表---------------------------------------------
		Permission perm9 = new Permission();
		Function fun9 = functionJpaDao.findOne(9);
		perm9.setRole(role);
		perm9.setFunction(fun9);
		permissionJpaDao.save(perm9);

		// 报表---------------------------------------------
		Permission perm10 = new Permission();
		Function fun10 = functionJpaDao.findOne(10);
		perm10.setRole(role);
		perm10.setFunction(fun10);
		permissionJpaDao.save(perm10);

		// 报表---------------------------------------------
		Permission perm11 = new Permission();
		Function fun11 = functionJpaDao.findOne(11);
		perm11.setRole(role);
		perm11.setFunction(fun11);
		permissionJpaDao.save(perm11);

		// 报表---------------------------------------------
		Permission perm12 = new Permission();
		Function fun12 = functionJpaDao.findOne(12);
		perm12.setRole(role);
		perm12.setFunction(fun12);
		permissionJpaDao.save(perm12);

		// 发票开具---------------------------------------------
		Permission perm14 = new Permission();
		Function fun14 = functionJpaDao.findOne(14);
		perm14.setRole(role);
		perm14.setFunction(fun14);
		permissionJpaDao.save(perm14);

		// 门店巡查报告---------------------------------------------
		Permission perm15 = new Permission();
		Function fun15 = functionJpaDao.findOne(15);
		perm15.setRole(role);
		perm15.setFunction(fun15);
		permissionJpaDao.save(perm15);

		// 考勤信息-总部查看---------------------------------------------
		Permission perm17 = new Permission();
		Function fun17 = functionJpaDao.findOne(17);
		perm17.setRole(role);
		perm17.setFunction(fun17);
		permissionJpaDao.save(perm17);

		// 公告/消息---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// 总部-门店备用金---------------------------------------------
		Permission perm21 = new Permission();
		Function fun21 = functionJpaDao.findOne(21);
		perm21.setRole(role);
		perm21.setFunction(fun21);
		permissionJpaDao.save(perm21);
	}

	// 总部管理人员
	@Test
	@Rollback(false)
	public void saveNewPerm2() {
		Role role = roleJpaDao.findOne(2);
		// 报表---------------------------------------------
		Permission perm9 = new Permission();
		Function fun9 = functionJpaDao.findOne(9);
		perm9.setRole(role);
		perm9.setFunction(fun9);
		permissionJpaDao.save(perm9);

		// 报表---------------------------------------------
		Permission perm10 = new Permission();
		Function fun10 = functionJpaDao.findOne(10);
		perm10.setRole(role);
		perm10.setFunction(fun10);
		permissionJpaDao.save(perm10);

		// 报表---------------------------------------------
		Permission perm11 = new Permission();
		Function fun11 = functionJpaDao.findOne(11);
		perm11.setRole(role);
		perm11.setFunction(fun11);
		permissionJpaDao.save(perm11);

		// 报表---------------------------------------------
		Permission perm12 = new Permission();
		Function fun12 = functionJpaDao.findOne(12);
		perm12.setRole(role);
		perm12.setFunction(fun12);
		permissionJpaDao.save(perm12);

		// 发票开具---------------------------------------------
		Permission perm14 = new Permission();
		Function fun14 = functionJpaDao.findOne(14);
		perm14.setRole(role);
		perm14.setFunction(fun14);
		permissionJpaDao.save(perm14);

		// 门店巡查报告---------------------------------------------
		Permission perm15 = new Permission();
		Function fun15 = functionJpaDao.findOne(15);
		perm15.setRole(role);
		perm15.setFunction(fun15);
		permissionJpaDao.save(perm15);

		// 考勤信息-总部查看---------------------------------------------
		Permission perm17 = new Permission();
		Function fun17 = functionJpaDao.findOne(17);
		perm17.setRole(role);
		perm17.setFunction(fun17);
		permissionJpaDao.save(perm17);

		// 公告/消息---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// 总部-门店备用金---------------------------------------------
		Permission perm21 = new Permission();
		Function fun21 = functionJpaDao.findOne(21);
		perm21.setRole(role);
		perm21.setFunction(fun21);
		permissionJpaDao.save(perm21);
	}

	// 店长
	@Test
	@Rollback(false)
	public void saveNewPerm3() {
		Role role = roleJpaDao.findOne(3);

		// ---------------------------------------------
		Permission perm5 = new Permission();
		Function fun5 = functionJpaDao.findOne(5);
		perm5.setRole(role);
		perm5.setFunction(fun5);
		permissionJpaDao.save(perm5);

		// ---------------------------------------------
		Permission perm6 = new Permission();
		Function fun6 = functionJpaDao.findOne(6);
		perm6.setRole(role);
		perm6.setFunction(fun6);
		permissionJpaDao.save(perm6);

		// ---------------------------------------------
		Permission perm7 = new Permission();
		Function fun7 = functionJpaDao.findOne(7);
		perm7.setRole(role);
		perm7.setFunction(fun7);
		permissionJpaDao.save(perm7);

		// ---------------------------------------------
		Permission perm8 = new Permission();
		Function fun8 = functionJpaDao.findOne(8);
		perm8.setRole(role);
		perm8.setFunction(fun8);
		permissionJpaDao.save(perm8);

		// ---------------------------------------------
		Permission perm13 = new Permission();
		Function fun13 = functionJpaDao.findOne(13);
		perm13.setRole(role);
		perm13.setFunction(fun13);
		permissionJpaDao.save(perm13);

		// 考勤信息-查看---------------------------------------------
		Permission perm16 = new Permission();
		Function fun16 = functionJpaDao.findOne(16);
		perm16.setRole(role);
		perm16.setFunction(fun16);
		permissionJpaDao.save(perm16);

		// 公告/消息---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// 考勤信息-维护---------------------------------------------
		Permission perm19 = new Permission();
		Function fun19 = functionJpaDao.findOne(19);
		perm19.setRole(role);
		perm19.setFunction(fun19);
		permissionJpaDao.save(perm19);

		// 门店-门店备用金---------------------------------------------
		Permission perm20 = new Permission();
		Function fun20 = functionJpaDao.findOne(20);
		perm20.setRole(role);
		perm20.setFunction(fun20);
		permissionJpaDao.save(perm20);
	}

	// 店助
	@Test
	@Rollback(false)
	public void saveNewPerm4() {
		Role role = roleJpaDao.findOne(4);

		// ---------------------------------------------
		Permission perm5 = new Permission();
		Function fun5 = functionJpaDao.findOne(5);
		perm5.setRole(role);
		perm5.setFunction(fun5);
		permissionJpaDao.save(perm5);

		// ---------------------------------------------
		Permission perm7 = new Permission();
		Function fun7 = functionJpaDao.findOne(7);
		perm7.setRole(role);
		perm7.setFunction(fun7);
		permissionJpaDao.save(perm7);

		// ---------------------------------------------
		Permission perm13 = new Permission();
		Function fun13 = functionJpaDao.findOne(13);
		perm13.setRole(role);
		perm13.setFunction(fun13);
		permissionJpaDao.save(perm13);

		// 考勤信息-查看---------------------------------------------
		Permission perm16 = new Permission();
		Function fun16 = functionJpaDao.findOne(16);
		perm16.setRole(role);
		perm16.setFunction(fun16);
		permissionJpaDao.save(perm16);

		// 公告/消息---------------------------------------------
		Permission perm18 = new Permission();
		Function fun18 = functionJpaDao.findOne(18);
		perm18.setRole(role);
		perm18.setFunction(fun18);
		permissionJpaDao.save(perm18);

		// 门店-门店备用金---------------------------------------------
		Permission perm20 = new Permission();
		Function fun20 = functionJpaDao.findOne(20);
		perm20.setRole(role);
		perm20.setFunction(fun20);
		permissionJpaDao.save(perm20);
	}
}
