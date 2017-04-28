package com.tjhx.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.accounts.MShopFtpManager;

/**
 * M+商场销售数据同步FTP
 */
public class MShopFtpJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(MShopFtpJob.class);

	@Autowired
	private MShopFtpManager mShopFtpManager;

	@Override
	public void execute() throws Exception {
		logger.info("M+商场销售数据同步FTP Begin");
		mShopFtpManager.synFtpFile();
		logger.info("M+商场销售数据同步FTP End");

	}

}
