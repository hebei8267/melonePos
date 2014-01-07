package test.com.tjhx.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.member.User;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.member.UserManager;

public class UserManagerTest extends SpringTransactionalTestCase {
	@Resource
	private UserManager userManager;
	@Resource
	private OrganizationJpaDao organizationJpaDao;

	@Test
	@Rollback(false)
	public void saveNewUser() {
		User user = new User();
		user.setLoginName("admin");
		user.setName("系统管理员");

		user.setOrgUuid("1");
		user.setRoleUuid("1");

		userManager.addNewUser(user);
	}

	@Test
	@Rollback(false)
	public void saveNewUser1() {
		List<String> fileContent = FileUtils.readFileContent("c:\\User.txt");

		for (String _string : fileContent) {
			String[] strArray = _string.split(",");
			User user = new User();

			user.setLoginName(strArray[0]);
			user.setName(strArray[1]);

			Organization org = organizationJpaDao.findByBwId(strArray[2]);
			user.setOrganization(org);
			if (strArray[0].endsWith("0")) {// 店长
				user.setRoleUuid("3");
			} else {
				user.setRoleUuid("4");
			}

			userManager.addNewUser(user);
		}
	}

}
