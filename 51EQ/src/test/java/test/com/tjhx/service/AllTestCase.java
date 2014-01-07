package test.com.tjhx.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.com.tjhx.service.info.BankCardManagerTest;
import test.com.tjhx.service.info.BankManagerTest;
import test.com.tjhx.service.info.RegionManagerTest;
import test.com.tjhx.service.member.FunctionManagerTest;
import test.com.tjhx.service.member.PermissionManagerTest;
import test.com.tjhx.service.member.RoleManagerTest;
import test.com.tjhx.service.member.UserManagerTest;
import test.com.tjhx.service.struct.OrganizationManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
	RegionManagerTest.class,
	//SupplierManagerTest.class,
	OrganizationManagerTest.class,
	RoleManagerTest.class,
	UserManagerTest.class,
	
	FunctionManagerTest.class,
	PermissionManagerTest.class,
	
	// ??????????????
	BankManagerTest.class,
	BankCardManagerTest.class
                    })
public class AllTestCase {

	

}
