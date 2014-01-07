package test.com.tjhx.service.affair;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.affair.WorkTypeJpaDao;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.affair.WorkType;
import com.tjhx.entity.struct.Organization;

public class WorkTypeJpaDaoTest extends SpringTransactionalTestCase {
	@Resource
	private WorkTypeJpaDao workTypeJpaDao;

	@Resource
	private OrganizationJpaDao orgJpaDao;

	@Test
	@Rollback(false)
	public void add() {
		workTypeJpaDao.deleteAll();

		List<Organization> orgList = (List<Organization>) orgJpaDao.findAll();
		for (Organization organization : orgList) {
			WorkType w1 = new WorkType();

			w1.setName("早班");
			w1.setUseFlg("0");
			w1.setOrganization(organization);

			workTypeJpaDao.save(w1);
			// ----------------------------------------------
			WorkType w2 = new WorkType();

			w2.setName("晚班");
			w2.setUseFlg("0");
			w2.setOrganization(organization);

			workTypeJpaDao.save(w2);
			// ----------------------------------------------
			WorkType w3 = new WorkType();

			w3.setName("全天班");
			w3.setUseFlg("0");
			w3.setOrganization(organization);

			workTypeJpaDao.save(w3);
			// ----------------------------------------------
			WorkType w4 = new WorkType();

			w4.setName("高峰班1");
			w4.setUseFlg("0");
			w4.setOrganization(organization);
			w4.setEditFlg(true);

			workTypeJpaDao.save(w4);
			// ----------------------------------------------
			WorkType w5 = new WorkType();

			w5.setName("高峰班2");
			w5.setUseFlg("0");
			w5.setOrganization(organization);
			w5.setEditFlg(true);

			workTypeJpaDao.save(w5);
			// ----------------------------------------------
			WorkType w6 = new WorkType();

			w6.setName("高峰班3");
			w6.setUseFlg("0");
			w6.setOrganization(organization);
			w6.setEditFlg(true);

			workTypeJpaDao.save(w6);
			// ----------------------------------------------
			WorkType w7 = new WorkType();

			w7.setName("高峰班4");
			w7.setUseFlg("0");
			w7.setOrganization(organization);
			w7.setEditFlg(true);

			workTypeJpaDao.save(w7);
			// ----------------------------------------------
			WorkType w8 = new WorkType();

			w8.setName("高峰班5");
			w8.setUseFlg("0");
			w8.setOrganization(organization);
			w8.setEditFlg(true);

			workTypeJpaDao.save(w8);

		}
	}

}
