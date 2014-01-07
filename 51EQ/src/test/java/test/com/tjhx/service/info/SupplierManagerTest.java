package test.com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.dao.info.RegionJpaDao;
import com.tjhx.dao.info.SupplierJpaDao;
import com.tjhx.entity.info.Region;
import com.tjhx.entity.info.Supplier;

public class SupplierManagerTest extends SpringTransactionalTestCase {

	@Resource
	private SupplierJpaDao supplierJpaDao;
	@Resource
	private RegionJpaDao regionJpaDao;

	@Test
	@Rollback(false)
	public void saveNewSupplier() {
		List<String> fileContent = FileUtils.readFileContent("c:\\Supplier.txt");

		for (String _string : fileContent) {
			String[] strArray = _string.split(",");
			Supplier supplier = new Supplier();

			supplier.setSupplierBwId(strArray[0]);

			Region region = regionJpaDao.findByName(strArray[1]);
			supplier.setRegion(region);

			// 付款方式 1-现款商户 2-月结商户 4-不定
			if ("现款商户".equals(strArray[2])) {
				supplier.setPayType("1");
			} else if ("月结商户".equals(strArray[2])) {
				supplier.setPayType("2");
			} else {
				supplier.setPayType("4");
			}

			supplier.setName(strArray[3]);

			supplierJpaDao.save(supplier);
		}

	}
}
