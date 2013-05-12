package com.huhuo.carservicecore.csm.consumer;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;

public class DaoConsumerTest extends CarServiceCoreTest {

	@Autowired
	private IDaoConsumer iDaoConsumer;
	
	@Test
	public void crud() {
		// add
		ModelConsumer t = new ModelConsumer();
		t.setAddress("北京万树园");
		t.setBirthday(new Date());
		t.setEmail("binhong@gmail.com");
		t.setNation("中国");
		t.setGender(2);
		iDaoConsumer.add(t);
		ModelConsumer actual = iDaoConsumer.find(t.getId());
		Assert.assertEquals("failed to add ModelConsumer", t, actual);
		// update
		t.setUsername("wuyuxuan");
		iDaoConsumer.update(t);
		actual = iDaoConsumer.find(t.getId());
		Assert.assertEquals("failed to update ModelConsumer", t, actual);
		// delete
		iDaoConsumer.deletePhysical(t);
		actual = iDaoConsumer.find(t.getId());
		Assert.assertNull("failed to delete ModelConsumer", actual);
	}
	
	@Test
	public void autoInject() {
		List<ModelConsumer> list = iDaoConsumer.findModels(0, 10);
		print(list);
	}
	
}
