package test.com.tjhx.service.struct;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;

public class OrganizationManagerTest extends SpringTransactionalTestCase {

	@Resource
	private OrganizationManager organizationManager;

	@Test
	@Rollback(false)
	public void saveNewOrg() {
		Organization org = new Organization();
		org.setName("总部");
		org.setBwId("00D");
		org.setZkId(1);

		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg1() {
		Organization org = new Organization();
		org.setName("01D");
		org.setBwId("01D");
		org.setZkId(2);
		org.setBwBranchNo("0101");

		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg2() {
		Organization org = new Organization();
		org.setName("02D");
		org.setBwId("02D");
		org.setZkId(3);
		org.setBwBranchNo("0201");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg3() {
		Organization org = new Organization();
		org.setName("03D");
		org.setBwId("03D");
		org.setZkId(4);
		org.setBwBranchNo("0301");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg4() {
		Organization org = new Organization();
		org.setName("04D");
		org.setBwId("04D");
		org.setZkId(5);
		org.setBwBranchNo("0601");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg5() {
		Organization org = new Organization();
		org.setName("05D");
		org.setBwId("05D");
		org.setZkId(6);
		org.setBwBranchNo("0501");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg6() {
		Organization org = new Organization();
		org.setName("06D");
		org.setBwId("06D");
		org.setZkId(7);
		org.setBwBranchNo("0701");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg7() {
		Organization org = new Organization();
		org.setName("07D");
		org.setBwId("07D");
		org.setZkId(8);
		org.setBwBranchNo("1001");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg8() {
		Organization org = new Organization();
		org.setName("08D");
		org.setBwId("08D");
		org.setZkId(9);
		org.setBwBranchNo("1101");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg9() {
		Organization org = new Organization();
		org.setName("09D");
		org.setBwId("09D");
		org.setZkId(10);
		org.setBwBranchNo("1201");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg10() {
		Organization org = new Organization();
		org.setName("10D");
		org.setBwId("10D");
		org.setZkId(11);
		org.setBwBranchNo("1301");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg11() {
		Organization org = new Organization();
		org.setName("11D");
		org.setBwId("11D");
		org.setZkId(12);
		org.setBwBranchNo("1401");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg12() {
		Organization org = new Organization();
		org.setName("12D");
		org.setBwId("12D");
		org.setZkId(13);
		org.setBwBranchNo("1501");
		organizationManager.addNewOrganization(org);
	}

	@Test
	@Rollback(false)
	public void saveNewOrg13() {
		Organization org = new Organization();
		org.setName("13D");
		org.setBwId("13D");
		org.setZkId(14);
		org.setBwBranchNo("1701");
		organizationManager.addNewOrganization(org);
	}
}
