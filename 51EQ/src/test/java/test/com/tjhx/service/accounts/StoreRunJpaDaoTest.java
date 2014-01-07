package test.com.tjhx.service.accounts;

import javax.annotation.Resource;

import org.junit.Test;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.accounts.CashRunJpaDao;
import com.tjhx.dao.accounts.StoreRunJpaDao;

public class StoreRunJpaDaoTest extends SpringTransactionalTestCase {
	@Resource
	private StoreRunJpaDao storeRunJpaDao;
	
	@Resource
	private CashRunJpaDao cashRunJpaDao;

	
	@Test
	public void getAll1() {
		
		Long result = cashRunJpaDao.checkJobType_AllDay("001", "20130131");
		System.out.println(result);
	}
}
