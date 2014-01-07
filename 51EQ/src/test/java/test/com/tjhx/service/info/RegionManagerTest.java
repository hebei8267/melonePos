package test.com.tjhx.service.info;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.info.RegionJpaDao;
import com.tjhx.entity.info.Region;

public class RegionManagerTest extends SpringTransactionalTestCase {
	@Resource
	private RegionJpaDao regionJpaDao;

	@Test
	@Rollback(false)
	public void saveNewSupplier() {
		Region region1 = new Region();
		region1.setName("不定");
		region1.setCode("0");
		regionJpaDao.save(region1);

		Region region2 = new Region();
		region2.setName("武汉");
		region2.setCode("0001");
		regionJpaDao.save(region2);

		Region region3 = new Region();
		region3.setName("广州");
		region3.setCode("0002");
		regionJpaDao.save(region3);
	}
}
