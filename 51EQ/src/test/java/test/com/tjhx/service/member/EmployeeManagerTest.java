package test.com.tjhx.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.member.EmployeeJpaDao;
import com.tjhx.dao.member.EmployeeMyBatisDao;
import com.tjhx.daozk.UserInfoMyBatisDao;
import com.tjhx.entity.member.Employee;
import com.tjhx.entity.zknet.UserInfo;

public class EmployeeManagerTest extends SpringTransactionalTestCase {
	@Resource
	private EmployeeJpaDao employeeJpaDao;
	@Resource
	private UserInfoMyBatisDao userInfoMyBatisDao;
	@Resource
	private EmployeeMyBatisDao employeeMyBatisDao;

	@Test
	@Rollback(false)
	public void syn_ZKNET_UserInfo() {
		employeeMyBatisDao.deleteEmployeeByEmpType("1");

		List<UserInfo> userList = userInfoMyBatisDao.getUserInfoList();
		for (UserInfo userInfo : userList) {
			Employee emp = new Employee();

			emp.setZkid(userInfo.getUserid());
			emp.setCode(userInfo.getBadgenumber());
			emp.setName(userInfo.getName());
			emp.setZkOrgId(userInfo.getDefaultdeptid());
			emp.setEmpType("1");
			emp.setWorkFlg("1");

			employeeJpaDao.save(emp);

		}
	}
}
