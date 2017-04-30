package test.com.tjhx.service.job;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.schedule.MShopFtpJob;
import com.tjhx.service.accounts.MShopFtpManager;

public class CCC extends SpringTransactionalTestCase {
	private static Logger logger = LoggerFactory.getLogger(MShopFtpJob.class);

	@Autowired
	private MShopFtpManager mShopFtpManager;

	@Test
	@Rollback(false)
	public void execute() throws Exception {
		logger.info("M+商场销售数据同步FTP Begin");
		mShopFtpManager.synFtpFile();
		logger.info("M+商场销售数据同步FTP End");

	}
}
