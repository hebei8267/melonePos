package test.com.tjhx.service.info;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;
import org.xml.sax.SAXException;

import com.tjhx.service.info.AccountFlowManager;

public class AccountFlowManagerTest extends SpringTransactionalTestCase {
	@Resource
	private AccountFlowManager accountFlowManager;

	@Test
	@Rollback(false)
	public void saveNewAccountSubject() throws InvalidFormatException, IOException, SAXException,
			NumberFormatException, ParseException {
		boolean b = accountFlowManager.loadAccountFlowFile("/Users/tao_tao/Downloads/BBB.xls");
		System.out.println(b);
	}
}
