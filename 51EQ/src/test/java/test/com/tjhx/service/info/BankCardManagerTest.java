package test.com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.dao.info.BankJpaDao;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.info.Bank;
import com.tjhx.entity.info.BankCard;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.info.BankCardManager;

public class BankCardManagerTest extends SpringTransactionalTestCase {
	@Resource
	private BankCardManager bankCardManager;
	@Resource
	private OrganizationJpaDao orgJpaDao;
	@Resource
	private BankJpaDao bankJpaDao;

	@Test
	@Rollback(false)
	public void saveNewBank() {
		List<String> fileContent = FileUtils.readFileContent("c:\\banklist.txt");
		for (String _string : fileContent) {
			String[] strArray = _string.split(",");

			BankCard bankCard = new BankCard();

			Bank bank = bankJpaDao.findByName(strArray[1]);
			bankCard.setBank(bank);
			bankCard.setBankCardNo(strArray[2]);
			Organization _dbOrganization = orgJpaDao.findByBwId(strArray[0]);

			bankCard.setOrgId(_dbOrganization.getId());
			bankCardManager.addNewBankCard(bankCard);
		}

	}
}