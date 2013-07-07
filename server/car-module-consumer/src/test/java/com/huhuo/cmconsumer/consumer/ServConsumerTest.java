package com.huhuo.cmconsumer.consumer;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.cmconsumer.CarModuleConsumerTest;
import com.huhuo.integration.db.mysql.Page;

public class ServConsumerTest extends CarModuleConsumerTest {

	@Resource(name="cmconsumerServConsumer")
	private IServConsumer iServConsumer;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelConsumer> list = iServConsumer.findModels(new Page<ModelConsumer>(0L, 15L));
		print(list);
	}
	
}
