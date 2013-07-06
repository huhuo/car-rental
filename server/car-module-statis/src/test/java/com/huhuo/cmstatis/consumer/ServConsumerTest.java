package com.huhuo.cmstatis.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
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
	
	@Test
	public void testdataBatch() {
		Date today = new Date();
		for(int offset=0; offset<365; offset++) {
			int max = RandomUtils.nextInt(30);
			List<ModelConsumer> list = new ArrayList<ModelConsumer>();
			for(int count=0; count<max; count++) {
				ModelConsumer t = new ModelConsumer();
				Date createTime = TimeUtils.offsetDate(-offset, today);
				t.setAddress("北京海淀区");
				t.setCreateTime(createTime);
				list.add(t);
			}
			iServConsumer.addBatch(list);
		}
	}
	
}
