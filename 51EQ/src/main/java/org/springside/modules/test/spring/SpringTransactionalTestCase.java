/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.springside.modules.test.spring;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Spring的支持数据库访问, 事务控制和依赖注入的JUnit4 集成测试基类. 相比Spring原基类名字更短并保存了dataSource变量.
 * 
 * 子类需要定义applicationContext文件的位置, 如:
 * 
 * @ContextConfiguration(locations = { "/applicationContext.xml" })
 * 
 * @author calvin
 */
@ActiveProfiles("development")
@DirtiesContext
@ContextConfiguration(locations = { "classpath:/applicationContext.xml"
			,"classpath:/applicationContext-memcached.xml" })
@TransactionConfiguration(transactionManager = "defaultTransactionManager")
public abstract class SpringTransactionalTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	protected DataSource dataSource;

	@Override
	@Resource
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}
}
