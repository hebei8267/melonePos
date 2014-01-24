package test.com.tjhx.security;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.security.MenuJpaDao;
import com.tjhx.entity.security.Menu;

public class MenuTestData extends SpringTransactionalTestCase {

	@Resource
	private MenuJpaDao menuJpaDao;
	@Test
	@Rollback(false)
	public void saveNewMenu0() {
		Menu menu = new Menu();
		// 父菜单节点
		menu.setParentMenu(null);
		// 菜单编号
		menu.setId("root");
		// 菜单名称
		menu.setName("root");
		// 菜单排序序号
		menu.setIndex(0);
		// 备注
		menu.setRemark(null);
		// 导航地址
		menu.setNavLink(null);
		// 图标
		menu.setIcon("fa-home");

		menuJpaDao.save(menu);
	}
	@Test
	@Rollback(false)
	public void saveNewMenu1() {
		Menu menu = new Menu();
		// 父菜单节点
		menu.setParentMenu(null);
		// 菜单编号
		menu.setId("home");
		// 菜单名称
		menu.setName("首页");
		// 菜单排序序号
		menu.setIndex(0);
		// 备注
		menu.setRemark(null);
		// 导航地址
		menu.setNavLink(null);
		// 图标
		menu.setIcon("fa-home");

		menuJpaDao.save(menu);
	}

	// =================================================================
	@Test
	@Rollback(false)
	public void saveNewMenu2() {
		Menu menu = new Menu();
		// 父菜单节点
		menu.setParentMenu(null);
		// 菜单编号
		menu.setId("securityManage");
		// 菜单名称
		menu.setName("权限管理");
		// 菜单排序序号
		menu.setIndex(1);
		// 备注
		menu.setRemark(null);
		// 导航地址
		menu.setNavLink(null);
		// 图标
		menu.setIcon("fa-sitemap");

		menuJpaDao.save(menu);
	}

	@Test
	@Rollback(false)
	public void saveNewMenu21() {
		Menu menu = new Menu();
		Menu parentMenu = menuJpaDao.findById("securityManage");
		// 父菜单节点
		menu.setParentMenu(parentMenu);
		// 菜单编号
		menu.setId("userManage");
		// 菜单名称
		menu.setName("用户管理");
		// 菜单排序序号
		menu.setIndex(1);
		// 备注
		menu.setRemark(null);
		// 导航地址
		menu.setNavLink(null);
		// 图标
		menu.setIcon(null);

		menuJpaDao.save(menu);
	}

	@Test
	@Rollback(false)
	public void saveNewMenu22() {
		Menu menu = new Menu();
		Menu parentMenu = menuJpaDao.findById("securityManage");
		// 父菜单节点
		menu.setParentMenu(parentMenu);
		// 菜单编号
		menu.setId("roleManage");
		// 菜单名称
		menu.setName("角色管理");
		// 菜单排序序号
		menu.setIndex(1);
		// 备注
		menu.setRemark(null);
		// 导航地址
		menu.setNavLink(null);
		// 图标
		menu.setIcon(null);

		menuJpaDao.save(menu);
	}

	@Test
	@Rollback(false)
	public void saveNewMenu23() {
		Menu menu = new Menu();
		Menu parentMenu = menuJpaDao.findById("securityManage");
		// 父菜单节点
		menu.setParentMenu(parentMenu);
		// 菜单编号
		menu.setId("orgManage");
		// 菜单名称
		menu.setName("组织管理");
		// 菜单排序序号
		menu.setIndex(1);
		// 备注
		menu.setRemark(null);
		// 导航地址
		menu.setNavLink(null);
		// 图标
		menu.setIcon(null);

		menuJpaDao.save(menu);
	}

	@Test
	@Rollback(false)
	public void saveNewMenu24() {
		Menu menu = new Menu();
		Menu parentMenu = menuJpaDao.findById("securityManage");
		// 父菜单节点
		menu.setParentMenu(parentMenu);
		// 菜单编号
		menu.setId("menuManage");
		// 菜单名称
		menu.setName("功能管理");
		// 菜单排序序号
		menu.setIndex(1);
		// 备注
		menu.setRemark(null);
		// 导航地址
		menu.setNavLink(null);
		// 图标
		menu.setIcon(null);

		menuJpaDao.save(menu);
	}

	// =================================================================
	@Test
	@Rollback(false)
	public void saveNewMenu3() {
		Menu menu = new Menu();
		// 父菜单节点
		menu.setParentMenu(null);
		// 菜单编号
		menu.setId("sysManage");
		// 菜单名称
		menu.setName("系统管理");
		// 菜单排序序号
		menu.setIndex(0);
		// 备注
		menu.setRemark(null);
		// 导航地址
		menu.setNavLink(null);
		// 图标
		menu.setIcon("fa-home");

		menuJpaDao.save(menu);
	}

}
