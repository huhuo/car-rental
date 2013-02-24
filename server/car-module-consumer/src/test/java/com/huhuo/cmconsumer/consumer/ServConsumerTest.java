package com.huhuo.cmconsumer.consumer;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.consumer.ModelConsumer;
import com.huhuo.cmconsumer.BaseTest;
import com.huhuo.integration.db.mysql.Page;

public class ServConsumerTest extends BaseTest {

	@Resource(name="cmconsumerServConsumer")
	private IServConsumer iServConsumer;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelConsumer> list = iServConsumer.findModels(new Page(0, 15));
		print(list);
	}
	
}
