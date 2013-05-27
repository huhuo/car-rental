package com.huhuo.cmsystem.ms;

import java.text.SimpleDateFormat;
import java.util.Date;

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
//		balance();
		
		send();
		
	}
	private void send() {
		SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = iServSMS.send(Long.valueOf(1), Long.valueOf(5), "测试短信，驰通驰通，路路畅通,hello,world,122333,发送时间："+ sdf.format(new Date()) +"utf-8", "success");
		logger.debug("===================================");
		logger.debug("预期结果：" + "success" + "发送结果：" + result);
		logger.debug("===================================");
	}
	private void balance() {
		String balance = iServSMS.balance();
		logger.debug("===================================");
		logger.debug("短信余额：" + balance);
		logger.debug("===================================");
	}
	
	
	
}
