/**
 * 
 */
package test.com.tjhx.service.info;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.info.BudgetSubjectJpaDao;
import com.tjhx.entity.info.BudgetSubject;

/**
 * @author he_bei
 * 
 */
public class BudgetSubjectManagerTest extends SpringTransactionalTestCase {
	@Resource
	private BudgetSubjectJpaDao budgetSubjectJpaDao;

	@Test
	@Rollback(false)
	public void saveNewBank() {
		BudgetSubject sub = new BudgetSubject();

		// 预算科目名称
		sub.setSubName("根预算科目");
		// 级别
		sub.setLevel(0);
		// 排序
		sub.setSortIndex(1);

		budgetSubjectJpaDao.save(sub);
	}
}
