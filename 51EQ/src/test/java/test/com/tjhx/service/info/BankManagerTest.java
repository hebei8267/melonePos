package test.com.tjhx.service.info;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.info.Bank;
import com.tjhx.service.info.BankManager;

public class BankManagerTest extends SpringTransactionalTestCase {
	@Resource
	private BankManager bankManager;

	@Test
	@Rollback(false)
	public void saveNewBank() {
		Bank bank = new Bank();
		bank.setBankId("95533");
		bank.setName("建设银行");
		bankManager.addNewBank(bank);

		Bank bank1 = new Bank();
		bank1.setBankId("95555");
		bank1.setName("招商银行");
		bankManager.addNewBank(bank1);
		
		Bank bank2 = new Bank();
		bank2.setBankId("95501");
		bank2.setName("深圳发展银行");
		bankManager.addNewBank(bank2);
		
		
		Bank bank3 = new Bank();
		bank3.setBankId("95508");
		bank3.setName("广发银行");
		bankManager.addNewBank(bank3);
		   
		Bank bank4 = new Bank();
		bank4.setBankId("95528");
		bank4.setName("浦发银行");
		bankManager.addNewBank(bank4);
		
		Bank bank5 = new Bank();
		bank5.setBankId("95558");
		bank5.setName("中信银行");
		bankManager.addNewBank(bank5);
		
		Bank bank6 = new Bank();
		bank6.setBankId("95559");
		bank6.setName("交通银行");
		bankManager.addNewBank(bank6);
		       
		Bank bank7 = new Bank();
		bank7.setBankId("95561");
		bank7.setName("兴业银行");
		bankManager.addNewBank(bank7);  
		
		
		Bank bank8 = new Bank();
		bank8.setBankId("95566");
		bank8.setName("中国银行");
		bankManager.addNewBank(bank8);    
		
		
		Bank bank9 = new Bank();
		bank9.setBankId("95568");
		bank9.setName("民生银行");
		bankManager.addNewBank(bank9);  
		
		
		Bank bank10 = new Bank();
		bank10.setBankId("95577");
		bank10.setName("华夏银行");
		bankManager.addNewBank(bank10);     
		       
		       
		       
		Bank bank11 = new Bank();
		bank11.setBankId("95580");
		bank11.setName("邮政储蓄银行");
		bankManager.addNewBank(bank11);  
		       
		   
		Bank bank12 = new Bank();
		bank12.setBankId("95588");
		bank12.setName("工商银行");
		bankManager.addNewBank(bank12);  
		
		
		Bank bank13 = new Bank();
		bank13.setBankId("95595");
		bank13.setName("光大银行");
		bankManager.addNewBank(bank13);  
		
		
		Bank bank14 = new Bank();
		bank14.setBankId("95599");
		bank14.setName("农业银行");
		bankManager.addNewBank(bank14);  
		       
		       
		      
	}
}