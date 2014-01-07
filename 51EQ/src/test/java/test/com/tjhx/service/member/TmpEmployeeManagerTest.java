package test.com.tjhx.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.member.EmployeeJpaDao;
import com.tjhx.dao.member.EmployeeMyBatisDao;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.member.Employee;
import com.tjhx.entity.struct.Organization;

public class TmpEmployeeManagerTest extends SpringTransactionalTestCase {
	@Resource
	private EmployeeJpaDao employeeJpaDao;

	@Resource
	private EmployeeMyBatisDao employeeMyBatisDao;

	@Resource
	private OrganizationJpaDao orgJpaDao;

	@Test
	@Rollback(false)
	public void add_TMP_UserInfo() {
		employeeMyBatisDao.deleteEmployeeByEmpType("2");

		List<Organization> orgList = (List<Organization>) orgJpaDao.findAll();
		for (Organization organization : orgList) {
			for (int i = 1; i < 11; i++) {
				Employee emp = new Employee();

				emp.setName("兼职人员" + i);
				// 2-兼职
				emp.setEmpType("2");
				// 0-离职
				emp.setWorkFlg("0");
				/** 用户关联机构 */
				emp.setOrganization(organization);

				emp.setCode(organization.getId() + "TMP" + String.format("%02d", i));

				employeeJpaDao.save(emp);

			}
		}

	}

}
