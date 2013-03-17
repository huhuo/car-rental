package com.huhuo.cmconsumer.consumer;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.cmconsumer.CarModuleConsumerTest;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;

public class DaoConsumerTest extends CarModuleConsumerTest {

	@Resource(name="cmconsumerDaoConsumer")
	private IDaoConsumer idaoConsumer;
	
	@Test
	public void crud() {
		ModelConsumer t = new ModelConsumer();
		t.setUsername("收到了快递费");
		t.setEmail("wuyuxuan2014@gmail.com");
		Integer row = idaoConsumer.add(t);
		print(row);
	}
	
	@Test
	public void findModels() {
		List<ModelConsumer> list = idaoConsumer.findModels(ModelConsumer.class, null, null);
		print(list);
	}
	
	@Test
	public void findByCondition() {
		Condition<ModelConsumer> condition = new Condition<ModelConsumer>(null, null, null, new Page(0, 10));
		List<ModelConsumer> list = idaoConsumer.findByCondition(condition);
		print(list);
	}
	
}
