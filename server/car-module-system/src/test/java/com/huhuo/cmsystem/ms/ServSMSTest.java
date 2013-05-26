package com.huhuo.cmsystem.ms;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huhuo.cmsystem.CarModuleSystemTest;

public class ServSMSTest extends CarModuleSystemTest {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name= "cmsystemServSMS")
	private IServSMS iServSMS;
	
	@Test
	public void crud() {
		balance();
		
//		send();
		
	}
	private void send() {
		String result = iServSMS.send(Long.valueOf(1), Long.valueOf(5), "测试短信，我是大虎货，我来自大越，我向往大汉，我是吴酱酱", "success");
		logger.debug("===================================");
		logger.debug("发送结果：" + result);
		logger.debug("===================================");
	}
	private void balance() {
		String balance = iServSMS.balance();
		logger.debug("===================================");
		logger.debug("短信余额：" + balance);
		logger.debug("===================================");
	}
	
	
	
}
