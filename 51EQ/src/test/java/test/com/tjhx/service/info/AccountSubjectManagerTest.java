package test.com.tjhx.service.info;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.info.AccountSubjectJpaDao;
import com.tjhx.entity.info.AccountSubject;

public class AccountSubjectManagerTest extends SpringTransactionalTestCase {
	@Resource
	private AccountSubjectJpaDao accountSubjectJpaDao;

	@Test
	@Rollback(false)
	public void saveNewAccountSubject() {
		AccountSubject sub = new AccountSubject();

		sub.setSubId("00");
		// 记账科目名称ßÏß
		sub.setSubName("根记账科目");
		// 级别
		sub.setLevel(0);
		// 排序
		sub.setSortIndex(1);

		accountSubjectJpaDao.save(sub);
	}
}
