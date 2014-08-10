package test.com.tjhx.service.member;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.member.Role;
import com.tjhx.service.member.RoleManager;

public class RoleManagerTest extends SpringTransactionalTestCase {
	@Resource
	private RoleManager roleManager;

//	@Test
//	@Rollback(false)
//	public void saveNewRole1() {
//		Role role = new Role();
//		role.setName("系统管理员");
//		roleManager.addNewRole(role);
//	}
//
//	// -----------------------------------------------------
//	@Test
//	@Rollback(false)
//	public void saveNewRole2() {
//		Role role = new Role();
//		role.setName("总部人员-会计");
//		roleManager.addNewRole(role);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewRole3() {
//		Role role = new Role();
//		role.setName("总部人员-普通");
//		roleManager.addNewRole(role);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewRole4() {
//		Role role = new Role();
//		role.setName("总部人员-库管");
//		roleManager.addNewRole(role);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewRole5() {
//		Role role = new Role();
//		role.setName("总部人员-Boss");
//		roleManager.addNewRole(role);
//	}
//
//	// -----------------------------------------------------
//	@Test
//	@Rollback(false)
//	public void saveNewRole6() {
//		Role role = new Role();
//		role.setName("店长");
//		roleManager.addNewRole(role);
//	}
//
//	@Test
//	@Rollback(false)
//	public void saveNewRole7() {
//		Role role = new Role();
//		role.setName("店助");
//		roleManager.addNewRole(role);
//	}
	
	
	@Test
	@Rollback(false)
	public void saveNewRole8() {
		Role role = new Role();
		role.setName("物流");
		roleManager.addNewRole(role);
	}

}
