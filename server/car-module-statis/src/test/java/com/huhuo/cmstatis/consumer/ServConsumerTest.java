package com.huhuo.cmstatis.consumer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.cmstatis.CarModuleStatisTest;
import com.huhuo.integration.util.TimeUtils;
import com.huhuo.integration.util.TimeUtils.HPattern;

public class ServConsumerTest extends CarModuleStatisTest {

	@Resource(name="cmstatisServConsumer")
	private IServConsumer iServConsumer;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void getAmountByDate() {
		Date begin = TimeUtils.parse("2013-05-12 17:36:40", HPattern.LONG);
		Date end = TimeUtils.parse("2013-05-14 11:15:57", HPattern.LONG);
		List<Map<String, Object>> list = iServConsumer.getAmountByDate(begin, end);
		print(list);
	}
	
}
